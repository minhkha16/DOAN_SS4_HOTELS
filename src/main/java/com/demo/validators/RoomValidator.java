package com.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.demo.entities.Hotel;
import com.demo.entities.Room;

@Component
public class RoomValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Room.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
