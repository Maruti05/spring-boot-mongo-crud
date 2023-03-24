package com.maruti.springbootmongo.collections;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Document(collection = "user")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	@Id
	@Field("_id")
	private String id;

	@Indexed(name = "name_index", direction = IndexDirection.DESCENDING)
	@NotBlank(message = "Name should not be null")
	private String name;

	@Min(value = 1, message = "Age should be Graterthan Zero")
	@Max(value = 90, message = "Age Should be lessthan 90")
	private Integer age;

	@Field("date_of_birth")
	private LocalDate dob;

	@CreatedDate
	@Field("created_at")
	private LocalDateTime createdAt;
	
	private String gender;
	
	@Size(min = 1, message = "At least 1 hobby is needed ")
	private List<String> hobbies;

	@Field("is_married")
	private Boolean isMarried = false;

	private BigDecimal salary;

	@NotBlank(message = "Email should not be null")
	@Email(message = "Email is Not Valid")
	private String email;
	
	private List<Address> addresses;

}
