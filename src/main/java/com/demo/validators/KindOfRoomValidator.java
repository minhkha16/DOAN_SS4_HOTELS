package com.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.demo.entities.Kindofroom;

@Component
public class KindOfRoomValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Kindofroom.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
