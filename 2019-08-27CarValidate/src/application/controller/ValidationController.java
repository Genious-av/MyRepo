package application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import DTO.Car;


@RestController
@Validated
public class ValidationController {
	
	@PostMapping("/body")
	public String checkBody(@Valid @RequestBody Car person ) {
		return person.toString();
	}
	
	
	

	
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	
    public List<String> handleBodyException(MethodArgumentNotValidException exception) {
		BindingResult bindingResult=exception.getBindingResult();
		List<String> result = new ArrayList<String>();
		result.addAll(bindingResult.getFieldErrors().stream().map((e)-> e.getDefaultMessage()+":"+ e.getRejectedValue())
															.collect(Collectors.toList()));
		
				 
		result.addAll(bindingResult.getAllErrors().stream()
															.filter(e->!e.getClass().equals(FieldError.class))
															.map((e)-> e.getDefaultMessage())
															.collect(Collectors.toList()));
		return result;
    }
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<String> handleParamException(ConstraintViolationException exception) {
		
		return exception.getConstraintViolations().stream()
				.map((e)-> e.getMessage()+": "+e.getInvalidValue())
				.collect(Collectors.toList());   
	}

}
