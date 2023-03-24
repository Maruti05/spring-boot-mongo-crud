package com.maruti.springbootmongo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.maruti.springbootmongo.collections.User;

@Repository
public interface UserRepo extends MongoRepository<User, String>{

	List<User> findByNameStartsWith(String name);
	
	//List<User> findByAgeBetween(Integer minAge,Integer maxAge);
	
	@Query(value = "{'age':{$gt:?0,$lt:?1}}",fields = "{created_at:0}")
	List<User> findPersonByAgeBetween(Integer minAge,Integer maxAge);
}
