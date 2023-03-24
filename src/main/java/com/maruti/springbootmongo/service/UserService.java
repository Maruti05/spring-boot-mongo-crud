package com.maruti.springbootmongo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.maruti.springbootmongo.collections.User;
import com.maruti.springbootmongo.exception.ServiceException;

public interface UserService {

	User insertUser(User user);

	List<User> getAllUsers();

	String deleteUserById(String id) throws ServiceException;

	List<User> getPersonStartWith(String name) throws ServiceException;

	List<User> getUsersByAgeBetween(Integer minAge, Integer maxAge) throws ServiceException;

	Page<User> serachUsers(String name, Integer minAge, Integer maxAge, String city, Integer page, Integer size);

	Void deleteAll();

}
