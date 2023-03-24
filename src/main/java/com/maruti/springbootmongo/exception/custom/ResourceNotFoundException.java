package com.maruti.springbootmongo.exception.custom;

import com.maruti.springbootmongo.exception.ServiceException;

public class ResourceNotFoundException extends ServiceException {

	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
