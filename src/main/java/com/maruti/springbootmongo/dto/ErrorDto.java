package com.maruti.springbootmongo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDto {

	private String error;

	private LocalDateTime time_stamp;
	
	private HttpStatus httpStatus;
	
	private Integer code;
	
	private String path;
}
