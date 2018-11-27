package com.airwallex.core;

import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.airwallex.exception.CalculatorException;

import lombok.Data;

@Service
@Data
public class Calculator {

	private Stack<Double> stack = new Stack<Double> ();
	
	private Stack<Double> unsettledStack = new Stack<Double> ();
	
	public void process(String inputValue) throws CalculatorException {
		
	}
	
	
	public String parseResult() {
        StringBuilder settledResult = new StringBuilder("Stack: ");
        stack.forEach(value -> {
        	settledResult.append(value + "");
        	settledResult.append(value + " ");
        });
        if (!CollectionUtils.isEmpty(unsettledStack)) {
        	settledResult.append("\r\n");
        	settledResult.append("the( ");
        	unsettledStack.forEach(value -> {
        		settledResult.append(value + "");
        		settledResult.append(",");
        	});
        	settledResult.deleteCharAt(settledResult.length() - 1);
        	settledResult.append( " were not pushed on to the stack due to the previous error)");
        	System.out.println(settledResult.toString());
        }
        return settledResult.toString();
	}
	
	
        
}
