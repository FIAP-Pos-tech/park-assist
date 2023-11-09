package br.com.postech.parkassist.utils;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

public class ValidaObj {
	
	
	public Set<String> validaObjeto(Validator validator, Object obj) {
		return validator.validate(obj).stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
   }

}
