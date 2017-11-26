package com.example.demo.response;

import java.util.List;

public class JasonResponse {
	
	private Integer totalRecords;
	private String status;
	private String objectType;

	private List<? extends Object> objects;

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public String getStatus() {
		return status;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<? extends Object> getObjects() {
		return objects;
	}

	public void setObjects(List<? extends Object> objects) {
		this.objects = objects;
	}

	public String getObjectType() {
		if (objects != null || !objects.isEmpty()) {
			return objects.get(0).getClass().getSimpleName();
		}
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public JasonResponse() {
	}

	public JasonResponse(Integer totalRecords, String status, List<? extends Object> response) {
		this.totalRecords = totalRecords;
		this.status = status;
		this.objects = response;
	}

	@Override
	public String toString() {
		String returnObject = null;
		returnObject = "JasonResponse [totalRecords=" + totalRecords + ", status=" + status + ", objects=" + objects
				+ "]";
		return returnObject;
	}

}
