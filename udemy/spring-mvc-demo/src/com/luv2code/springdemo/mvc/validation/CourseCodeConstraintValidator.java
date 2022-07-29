package com.luv2code.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator 
	implements ConstraintValidator<CourseCode, String> {
	// String인것은 필드에 무엇을 넣는지에 따라 다르다. Course Code는 String이므로.
	
	// define a course Prefix that we can validate against
	private String coursePrefix;
	
	
	@Override 
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
		// .value() -> actually access the attribute value for that given annotation.
		// assign the value from passed in from our annotation
		
	}
	
	@Override // business logic을 놓는곳
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
		// thecode : HTML form data entered by the user, 
		// theConstraintValidatorContext : 추가적인 error message를 여기에 배치할 수 있다.
		
		boolean result;
		
		if (theCode != null) {
			//null check를 해줘야 한다.
			result = theCode.startsWith(coursePrefix);
		}else {
			result = true; // 이 필드는 required가 아니기때문에 아무것도 입력안하면 통과시키기
		}
		// need to check for null. 아무것도 입력하지않는 null일때를 처리해줘야한다.
		
		return result;
	}

}


