/**
 * 
 */
package com.nagarro.juiceJunction.authentication.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.nagarro.juiceJunction.authentication.payload.ApiResponse;

/**
 * @author palak
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> constraintViolationExceptionHandler(ConstraintViolationException ex) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
		}
		System.out.println(errors);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> httpClientErrorExceptionHandler(HttpClientErrorException ex) {
		String msg = ex.getResponseBodyAsString();
		System.out.println(msg);
		if (ex.getStatusCode() == HttpStatus.BAD_REQUEST)
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(WrongCredentialException.class)
	public ResponseEntity<String> WrongCredentialExceptionHandler(WrongCredentialException ex){
		String msg = ex.getMessage();
		return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
	}
}
