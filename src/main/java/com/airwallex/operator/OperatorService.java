package com.airwallex.operator;

import com.airwallex.core.Calculator;
import com.airwallex.exception.CalculatorException;

public interface OperatorService {

	public void execute(Calculator calculator) throws CalculatorException;
	
}
