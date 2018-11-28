package com.airwallex.operator;

import java.util.Stack;

import org.springframework.stereotype.Service;

import com.airwallex.core.Calculator;
import com.airwallex.exception.CalculatorException;

@Service
public class OperatorAdditionServiceImpl implements OperatorService {

	@Override
	public void execute(Calculator calculator) throws CalculatorException {
		Stack<Double> stack = calculator.getStack();
		if (stack.size() >= 2) {
			Double n1 = stack.pop();
			Double n2 = stack.pop();
			stack.push(n1 + n2);
		} else {
			throw new CalculatorException("cannot caculato");
		}
	}
}
