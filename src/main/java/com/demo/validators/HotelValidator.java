package com.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.demo.entities.Hotel;

@Component
public class HotelValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Hotel.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
