package com.luv2code.springdemo.mvc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } ) // method나 field에 이 어노테이션을 적용할 수 있다는 의미. 어디에 어노테이션 적용할수 있는지 설정.
@Retention(RetentionPolicy.RUNTIME) // how long will the marked annotation be stored or used?
// retain this annotation in the byte code and also use it at Runtime by the JVM
public @interface CourseCode {

	// define default course code
	// user가 pass하지 않는다면 default value는 LUV이다.
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
