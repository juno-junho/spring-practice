package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan("com.luv2code.springdemo")
//<context:component-scan base-package="com.luv2code.springdemo"/> �̰��̶� ������.
@PropertySource("classpath:sport.properties")
public class SportConfig {

	// define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	//���� method�̸��� bean id�� �Ǵ°��̴�.
	
	
	// define bean for our swim coach AND inject dependency
	@Bean
	public Coach swimCoach() {
		return new SwimCoach(sadFortuneService());
	}
	
	//calling bean method ->spring�� intercept�ؼ� actual bean�� reference�� �ִ� ���̴�.
}
