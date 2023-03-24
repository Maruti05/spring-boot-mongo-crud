package com.maruti.springbootmongo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.maruti.springbootmongo.collections.User;
import com.maruti.springbootmongo.exception.ServiceException;
import com.maruti.springbootmongo.exception.custom.ResourceNotFoundException;
import com.maruti.springbootmongo.exception.custom.UserNotFoundException;
import com.maruti.springbootmongo.repo.UserRepo;
import com.maruti.springbootmongo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
	private UserRepo userRepo;

    @Autowired
    private MongoTemplate template;
    
	@Override
	public User insertUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public String deleteUserById(String id) throws ServiceException {
		User user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("user not found with id: "+id));
		if(user!=null)
		   userRepo.deleteById(id);
		return "User deleted succesfully ";
	}

	@Override
	public List<User> getPersonStartWith(String name) throws ServiceException {
		List<User> users = userRepo.findByNameStartsWith(name);
		if(users.isEmpty()) {
			throw new ResourceNotFoundException("Sorry users are not present ");
			}
		return users;
	}

	@Override
	public List<User> getUsersByAgeBetween(Integer minAge, Integer maxAge) throws ServiceException {
		List<User> users = userRepo.findPersonByAgeBetween(minAge-1, maxAge+1);
		if(users.isEmpty()) {
			throw new ResourceNotFoundException("Sorry users are not present ");
			}
		return users;
	}

	@Override
	public Page<User> serachUsers(String name, Integer minAge, Integer maxAge, String city, Integer page,
			Integer size) {
		Pageable pageAble = PageRequest.of(page, size);
		Query query=new Query().with(pageAble);
		List<Criteria> criterias=new ArrayList<Criteria>();
		if(name!=null && !name.isEmpty())
			criterias.add(Criteria.where("name").regex(name,"i"));
		
		if(minAge !=null &&maxAge!=null)
			criterias.add(Criteria.where("age").gte(minAge).lte(maxAge));
		
		if(city!=null && !city.isEmpty())
			criterias.add(Criteria.where("addresses.city").is(city));
		
		if(!criterias.isEmpty())
			query.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[0])));
		
		Page<User> users = PageableExecutionUtils.getPage(template.find(query, User.class), pageAble, ()->
			template.count(query.skip(0).limit(0),User.class)
		);
		return users;
	}

	@Override
	public Void deleteAll() {
		userRepo.deleteAll();
		return null;
	}

}
