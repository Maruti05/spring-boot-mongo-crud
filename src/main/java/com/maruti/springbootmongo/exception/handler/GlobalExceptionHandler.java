package com.maruti.springbootmongo.exception.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.maruti.springbootmongo.dto.ErrorDto;
import com.maruti.springbootmongo.exception.AppException;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> resp=new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleException(AppException ex,HttpServletRequest req){
		ErrorDto error=new ErrorDto();
		error.setError(ex.getLocalizedMessage());
		error.setHttpStatus(HttpStatus.NOT_FOUND);
		error.setTime_stamp(LocalDateTime.now());
		error.setCode(HttpStatus.NOT_FOUND.value());
		error.setPath(req.getRequestURI());
		return new ResponseEntity<ErrorDto>(error,new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
}
