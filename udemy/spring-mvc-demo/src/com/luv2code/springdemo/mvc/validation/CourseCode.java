package com.luv2code.springdemo.mvc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } ) // method�� field�� �� ������̼��� ������ �� �ִٴ� �ǹ�. ��� ������̼� �����Ҽ� �ִ��� ����.
@Retention(RetentionPolicy.RUNTIME) // how long will the marked annotation be stored or used?
// retain this annotation in the byte code and also use it at Runtime by the JVM
public @interface CourseCode {

	// define default course code
	// user�� pass���� �ʴ´ٸ� default value�� LUV�̴�.
	public String value() default "LUV";
	
	// define default error message
	public String message() default "must start with LUV";
	
	// define default groups : group validation constraints together
	public Class<?>[] groups() default {};
	// Groups : can group related constraints
	
	// define default payloads : give additional information about the validation error
	public Class<? extends Payload>[] payload() default {};
	// Payload : provide custom details about validation failure (severity level, error code etc..)
}
