package com.spi.uvschool.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringListConverter{
	
	private static StringListConverter instance = null;
	
	public static StringListConverter getInstance(){
		if(instance == null){
			instance = new StringListConverter();
		}
		return instance;
	}

	private static final String SEPARATOR = "|";

	public String convertListToString(List<String> strings) {
		return StringUtils.join(strings, SEPARATOR);
	}

	public List<String> convertStringToList(String string) {
		return Arrays.asList(string.split(SEPARATOR));
	}

}
