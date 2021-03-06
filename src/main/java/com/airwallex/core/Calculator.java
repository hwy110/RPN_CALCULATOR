package com.airwallex.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.airwallex.config.PropertyConfig;
import com.airwallex.exception.CalculatorException;
import com.airwallex.operator.OperatorFactory;
import com.airwallex.operator.OperatorService;
import com.airwallex.utils.Iterables;

import lombok.Data;

@Service
@Data
public class Calculator {

	/** calculated stack */
	private Stack<Double> stack = new Stack<> ();
	
	/** temp input char */
	private Stack<String> unsettledStack = new Stack<> ();

	/** operator process error then left number will be saved */
	private List<Double> errorNumber = new ArrayList<> ();
	
	public void process(String inputValue) throws CalculatorException {
		if (StringUtils.isEmpty(inputValue)) {
			throw new CalculatorException("input value can't be null");
		}
		
		errorNumber.clear();
		
		String[] values = inputValue.trim().split("\\s+");
		Arrays.asList(values).forEach(value -> {
			unsettledStack.push(value);
		});
		
		executeProcess();
		
		unsettledStack.removeAllElements();
	}
	
	private void executeProcess() {
		// Local variable result defined in an enclosing scope must be final or effectively final
		// Tranfser to array
		boolean [] isContinueHanlde = new boolean [] {true};
		Iterables.forEach(unsettledStack, (index, value) -> {
			if (isContinueHanlde[0]) {
				if (PropertyConfig.isOperator(value)) {
					try {
						executeOperator(value);
					} catch (CalculatorException e) {
						StringBuilder sb = new StringBuilder();
						sb.append("operator ");
						sb.append(value);
						sb.append(" (position: ");
						sb.append(index*2 + 1);
						sb.append("):insucient parameters");
						System.out.println(sb.toString());
						isContinueHanlde[0] = false;
					}
				} else {
					try {
						stack.push(Double.valueOf(value));
					} catch (NumberFormatException e) {
						//FIXME: need to fix the regex which is not supporting some words like "no"
						//Now skip upon case
					}
				}
			} else {
				if (!PropertyConfig.isOperator(value)) {
					errorNumber.add(Double.valueOf(value));
				}
			}
		});
	}
	
	
	private void executeOperator(String type) throws CalculatorException {
		OperatorService operatorService = OperatorFactory.getOperatorService(type);
		operatorService.execute(this);		
	}
	
	public String parseResult() {
        StringBuilder settledResult = new StringBuilder("Stack: ");
        stack.forEach(value -> {
        	settledResult.append(value + "");
        	settledResult.append(" ");
        });
        return settledResult.toString();
	}
	
	
        
}
