package com.airwallex;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import com.airwallex.config.PropertyConfig;
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
		System.out.println(">>>>>>>>>>>>>>>Input Sample:1 3 2 4 *<<<<<<<<<<<<<");
		System.out.print("input NPC line:");
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			if (!validateInput(s)) {
				System.out.println("Please enter the correct format by following the notice");
				System.out.print("input NPC line:");
				continue;
			}
			try {
				calculator.process(s);
			} catch (CalculatorException e) {
				System.out.println(e.getMessage());
			}finally {
				System.out.println(calculator.parseResult());
			}
			List<Double> numbers = calculator.getErrorNumber();
			printErroMessage(numbers);
			System.out.print("input NPC line:");
		}
	}
	
	private boolean validateInput(String inputValue) {
		Pattern pattern = Pattern.compile(PropertyConfig.regexPetten);
		Matcher matcher = pattern.matcher(inputValue);
		return matcher.matches();
	}
	
	private void printErroMessage(List<Double> numbers) {
		if (!CollectionUtils.isEmpty(numbers)) {
			StringBuilder sb = new StringBuilder();
			sb.append("(the ");
			numbers.forEach(value -> {
				sb.append(value);
				sb.append(",");
			});
			sb.deleteCharAt(sb.length() - 1);
			sb.append(" were not pushed on to the stack due to the previous error");
			System.out.println(sb.toString());
		}
	}
}
