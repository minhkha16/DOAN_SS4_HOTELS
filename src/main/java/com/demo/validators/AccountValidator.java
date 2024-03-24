package com.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demo.entities.Account;
import com.demo.entities.DatePrice;
import com.demo.entities.Hotel;

@Component
public class AccountValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Account.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
