package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.User;
import com.example.demo.exception.ErrorHandlingController;
import com.example.demo.response.JasonResponse;
import com.example.demo.repo.UserdbRepo;

@RestController
@RequestMapping("/userdetails")

public class UserController {
	
	@Autowired
	UserdbRepo repo;
	


	@RequestMapping(value="/findall", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public JasonResponse findall(){
		
		//return repo.findAll();
		List<User> userData = (List<User>) repo.findAll();
		if( userData.isEmpty())
		            {
			         return null;
			         }else{
		        		return new JasonResponse(userData.size(), "SUCCESS", userData);
		           }
		
		
	}	
	
	//@GetMapping("/notes/{id}") to get userdetails byID
	@RequestMapping(value="/findall/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") int Id) throws Exception{
		User usr = repo.findOne(Id);
		ErrorHandlingController et=new ErrorHandlingController();
	    if(usr == null) {    	
	    	et.throwNoUserException();
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(usr);
	}
	
	//to create new user data
	@RequestMapping(value="/adduser", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<User> createNew(@Valid @NotEmpty @RequestBody User usr) throws Exception{
		
/*		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String today = dateFormat.format(date);
		String dob =usr.getDob();
		System.out.println(dateFormat.format(date)); */
		
		SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd"); 
 
        String today=   getToday("yyyy-MM-dd");
        String dob =usr.getDob();
       Date d = dateFormat.parse(dob);
       Date d1 = dateFormat.parse(today);
     
		List<User> users = (List<User>) repo.findAll();
		ErrorHandlingController et=new ErrorHandlingController();
		
		for (int i=0; i< users.size(); i++){
			
			String email =users.get(i).getEmail();
			
			if(email.equalsIgnoreCase(usr.getEmail()))
				et.throwEmailRegistered();
			return ResponseEntity.notFound().build();
		}
		System.out.println(d1+" "+d);
		// to umcomment once date isactive  set
		if(d.compareTo(d1) >0){
			System.out.println("input date morethan currentdate");
			et.throwDateofBirth();
			return ResponseEntity.notFound().build();
			
		}
		
	     repo.save(usr);
	     return ResponseEntity.ok().body(usr);
	}
	
	//to delete user details 
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") int Id) throws Exception{
		
		ErrorHandlingController et=new ErrorHandlingController();
		User usr = repo.findOne(Id);
	    if(usr == null) {
	        et.throwNoUserException();
	    }
	    
	    if(!(usr.getisActive())){
	    repo.delete(usr);
	    return ResponseEntity.ok().body(usr);}
	    else{
	    	
	    	et.throwActiveUserException();
	    	return ResponseEntity.ok().build();
	    }
	}
	//to update 
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<User> updateUsr(@PathVariable(value = "id") int Id ,@Valid @RequestBody User usrDetails) throws Exception {
		
		ErrorHandlingController et=new ErrorHandlingController();
		
		User usr = repo.findOne(Id);
	    if(usr == null) {
	    	 et.throwNoUserException();
	    	 }
	 
	    usr.setPinCode(usrDetails.getPinCode());
	    //usr.setDob(usrDetails.getDob());
	    
	    
	    User updatedusr =repo.save(usr);
		return ResponseEntity.ok(updatedusr);
	}
	
	 public static String getToday(String format){
	     Date date = new Date();
	     return new SimpleDateFormat(format).format(date);
	 }

}
