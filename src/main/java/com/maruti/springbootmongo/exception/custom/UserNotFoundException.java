package com.maruti.springbootmongo.exception.custom;

import com.maruti.springbootmongo.exception.ServiceException;

public class UserNotFoundException extends ServiceException{

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
