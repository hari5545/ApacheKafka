package com.example.demo.response;

public class UserResponse {
 
	protected int statusCode;
	protected String message;
	protected Object obj;
	
	
	public UserResponse() {
		super();
	}


	public UserResponse(int statusCode, String message, Object obj) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.obj = obj;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}


	@Override
	public String toString() {
		return "UserResponse [statusCode=" + statusCode + ", message=" + message + ", obj=" + obj + "]";
	}
	
}
