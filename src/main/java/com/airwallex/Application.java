package com.airwallex;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.airwallex.core.Calculator;
import com.airwallex.exception.CalculatorException;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	Calculator calculator;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>Notice<<<<<<<<<<<<<");
		System.out.println(">>>>>>>>>>>>>>>Receive strings containing whitespace separated lists of numbers and operators<<<<<<<<<<<<<");
		System.out.println(">>>>>>>>>>>>>>>Available operators are +,-,*,/,sqrt,undo,clear<<<<<<<<<<<<<");
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			try {
				calculator.process(s);
			} catch (CalculatorException e) {
				System.out.println(e.getMessage());
			}finally {
				System.out.println(calculator.parseResult());
			}
		}
	}
}
