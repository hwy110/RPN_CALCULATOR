package com.airwallex.operator;

import com.airwallex.config.PropertyConfig;

public class OperatorFactory {

	
	public static OperatorService getOperatorService(String operator) {
		String operatorClass  = PropertyConfig.operator.get(operator);
		Class<?> threadClazz;
		try {
			threadClazz = Class.forName(operatorClass);
			return (OperatorService) threadClazz.newInstance();
		} catch (ClassNotFoundException e) {
			return null;
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}
	
	
}
