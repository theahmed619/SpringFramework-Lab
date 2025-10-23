package com.example.yeshendrayt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.yeshendrayt.service.CalculatorService;

@SpringBootTest
class SpringBootInterviewQ1ApplicationTests {

	@Autowired
	private CalculatorService calculatorService;
	
	int initialValue=0;
	
	@BeforeEach
	void setup() {
		initialValue=10;
	}
	
	@Test
	void test() {
		int a=5;
		int b=0;
		//int result=calculatorService.divide(a, b);
		//assertEquals(12, result);
		assertEquals(4, calculatorService.add(2, 2));
		assertEquals(12, calculatorService.add(initialValue, 2));
		//assertThrows(ArithmeticException.class, ()->calculatorService.divide(a, b));
	}
	
	@Test
	void contextLoads() {
	}

}
