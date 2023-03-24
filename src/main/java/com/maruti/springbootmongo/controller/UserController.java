package com.maruti.springbootmongo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maruti.springbootmongo.collections.User;
import com.maruti.springbootmongo.exception.AppException;
import com.maruti.springbootmongo.exception.ServiceException;
import com.maruti.springbootmongo.service.UserService;

@RestController
@RequestMapping("/user/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/insert-user")
	public ResponseEntity<?> insertUser(@Valid @RequestBody User user){
		return new ResponseEntity<User>(userService.insertUser(user),HttpStatus.CREATED);
		
	}
	@GetMapping("/all-users")
	public ResponseEntity<?> getAllUsers(){
		return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable String id) throws AppException{
		return new ResponseEntity<String>(userService.deleteUserById(id),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getPersonStartWith(@RequestParam String name) throws AppException{
		return new ResponseEntity<List<User>>(userService.getPersonStartWith(name),HttpStatus.OK);
	}
	
	@GetMapping("/user-beetween")
	public ResponseEntity<?> getUsersByAgeBetween(@RequestParam Integer minAge,@RequestParam Integer maxAge) throws AppException{
		return new ResponseEntity<List<User>>(userService.getUsersByAgeBetween(minAge,maxAge),HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> serachUsers(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer minAge,
			@RequestParam(required = false) Integer maxAge,
			@RequestParam(required = false) String city,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size
			){
				return new ResponseEntity<Page<User>>(userService.serachUsers(name,minAge,maxAge,city,page,size),HttpStatus.OK);
		
	}
	
	@GetMapping("/deleteall")
	public ResponseEntity<?> deleteAll(){
		return new ResponseEntity<Void>(userService.deleteAll(),HttpStatus.OK);
	}
}
