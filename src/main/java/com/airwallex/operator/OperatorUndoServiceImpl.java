package com.airwallex.operator;

import java.util.Stack;

import org.springframework.stereotype.Service;

import com.airwallex.core.Calculator;
import com.airwallex.exception.CalculatorException;

@Service
public class OperatorUndoServiceImpl implements OperatorService {

	@Override
	public void execute(Calculator calculator) throws CalculatorException {
		Stack<Double> stack = calculator.getStack();
		// TODO: unclear if no char in stack, but call undo
		if (stack.size() > 1) {
			stack.removeElementAt(stack.size() - 1);
		}
		// FIXME: cannot handle continue undo
	}
}
