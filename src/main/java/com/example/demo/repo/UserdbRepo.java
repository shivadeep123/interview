package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.User;

@Repository
public interface UserdbRepo extends JpaRepository<User, Integer>{

}

