package com.airwallex.operator;

import java.util.Stack;

import org.springframework.stereotype.Service;

import com.airwallex.core.Calculator;

@Service
public class OperatorClearServiceImpl implements OperatorService {

	@Override
	public void execute(Calculator calculator) {

		Stack<Double> stack = calculator.getStack();
		stack.removeAllElements();
	}
}
