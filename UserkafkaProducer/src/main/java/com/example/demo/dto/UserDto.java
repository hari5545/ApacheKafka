package com.example.demo.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder
public class UserDto {
	
	protected String userName;
	
	protected String password;

	public UserDto() {
		super();
	}

	
	public UserDto( String userName, String password) {
		super();
	
		this.userName = userName;
		this.password = password;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [ userName=" + userName + ", password=" + password + "]";
	}
}

