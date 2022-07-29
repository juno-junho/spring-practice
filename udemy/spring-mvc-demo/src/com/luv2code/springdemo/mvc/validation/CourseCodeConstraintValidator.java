package com.luv2code.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator 
	implements ConstraintValidator<CourseCode, String> {
	// String�ΰ��� �ʵ忡 ������ �ִ����� ���� �ٸ���. Course Code�� String�̹Ƿ�.
	
	// define a course Prefix that we can validate against
	private String coursePrefix;
	
	
	@Override 
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
		// .value() -> actually access the attribute value for that given annotation.
		// assign the value from passed in from our annotation
		
	}
	
	@Override // business logic�� ���°�
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
		// thecode : HTML form data entered by the user, 
		// theConstraintValidatorContext : �߰����� error message�� ���⿡ ��ġ�� �� �ִ�.
		
		boolean result;
		
		if (theCode != null) {
			//null check�� ����� �Ѵ�.
			result = theCode.startsWith(coursePrefix);
		}else {
			result = true; // �� �ʵ�� required�� �ƴϱ⶧���� �ƹ��͵� �Է¾��ϸ� �����Ű��
		}
		// need to check for null. �ƹ��͵� �Է������ʴ� null�϶��� ó��������Ѵ�.
		
		return result;
	}

}


