package com.airwallex.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PropertyConfig {

	public static String regexPetten;
		
	public static String operatorMapping;
	
	public static Map<String, String> operator = new HashMap<> ();
	
	@Value("${input.regex.petten}")
    public void setRegexPetten(String regexPetten) {
		PropertyConfig.regexPetten = regexPetten;
    }
	
	@Value("${operator.mapping}")
	public void setOperatorMapping(String operatorMapping) {
		PropertyConfig.operatorMapping = operatorMapping;
	}
	
	private static void transferOperatorMapping() {
		if (!StringUtils.isEmpty(operatorMapping)) {
			String [] mapping = operatorMapping.split(",");
			Arrays.asList(mapping).forEach(value -> {
				String [] operatorValue = value.split("\\|");
				operator.put(operatorValue[0], operatorValue[1]);
			});
		}
	}
	
	public static boolean isOperator(String character) {
		if (operator.size() == 0) {transferOperatorMapping();};
		return operator.containsKey(character);
	}
	
	public static boolean isMatchRegexPetten(String input) {
		return Pattern.matches(regexPetten, input);
	}
	
	
	
}
