package com.test.springdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsNotNullConstraintValidator implements ConstraintValidator<IsNotNull, String>{

	@Override
	public boolean isValid(String theValue, ConstraintValidatorContext validatorContext) {
		return theValue != "";
	}

}
