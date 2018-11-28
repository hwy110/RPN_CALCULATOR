package com.airwallex.operator;

import java.util.Stack;

import org.springframework.stereotype.Service;

import com.airwallex.core.Calculator;
import com.airwallex.exception.CalculatorException;

@Service
public class OperatorSqrtServiceImpl implements OperatorService {

	@Override
	public void execute(Calculator calculator) throws CalculatorException {
		
		Stack<Double> stack = calculator.getStack();
		if (stack.size() >= 1) {
			Double n1 = stack.pop();
			stack.push(Math.sqrt(n1));
		} else {
			throw new CalculatorException("cannot caculator");
		}
		
	}

}
