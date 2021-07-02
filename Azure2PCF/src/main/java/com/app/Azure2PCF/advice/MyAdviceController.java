package com.app.Azure2PCF.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.Azure2PCF.customException.EmptyIdException;
import com.app.Azure2PCF.customException.EmptyInputException;

@ControllerAdvice
public class MyAdviceController {
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
		return new ResponseEntity<String>("Input fields is empty please look into it",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyIdException.class)
	public ResponseEntity<String> handleEmptyId(EmptyIdException emptyIdException){
		return new ResponseEntity<String>("Id not getting plase look into", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleEmptyNoSuchElementException(NoSuchElementException noSuchElementException){
		return new ResponseEntity<String>("No value present in db  please change it",HttpStatus.NOT_FOUND);
	}
}
