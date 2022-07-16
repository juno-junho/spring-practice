package com.luv2code.aopdemo;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.luv2code.aopdemo.dao.AccountDAO;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.luv2code")
public class DemoConfig {
//	@Bean
//	public AccountDAO accountDAO() {
//		return new AccountDAO();
//	}	   
}
