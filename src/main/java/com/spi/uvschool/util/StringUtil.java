/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.util;

import static org.springframework.util.Assert.hasText;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class StringUtil {

	private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}$");

	public static void minLength(String str, int len) throws IllegalArgumentException {
		hasText(str);
		if (str.length() < len) {
			throw new IllegalArgumentException();
		}
	}

	public static void maxLength(String str, int len) throws IllegalArgumentException {
		hasText(str);
		if (str.length() > len) {
			throw new IllegalArgumentException();
		}
	}

	public static void validEmail(String email) throws IllegalArgumentException {
		minLength(email, 4);
		maxLength(email, 255);
		if (!email.contains("@") || StringUtils.containsWhitespace(email)) {
			throw new IllegalArgumentException();
		}
	}

	public static boolean isValidUuid(String uuid) {
		return UUID_PATTERN.matcher(uuid).matches();
	}

	public static boolean isValid(String str) {
		try {
			hasText(str);
		} catch (IllegalArgumentException e) {
			return false;
		}
		if (str.length() < 0) {
			return false;
		} else {
			return true;
		}
	}

	public static String formatDecimal(double number) {
		String pattern = "###.##";

		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String format = decimalFormat.format(number);

		return format;
	}

	public static String getCommaSerperatedValues(List<String> list) {
		int listSize = list.size();
		int count = 0;
		StringBuffer studentNames = new StringBuffer();
		while (count < listSize) {
			String string = list.get(count);
			if (count == 0) {
				studentNames = studentNames.append(string);
			} else if (count == (listSize - 1)) {
				studentNames = studentNames.append(" and ").append(string);
			} else {
				studentNames = studentNames.append(", ").append(string);
			}
			count++;
		}
		return studentNames.toString();
	}

	private static String replaceCharAt(String s, int pos, char c) {
		return s.substring(0, pos) + c + s.substring(pos + 1);
	}
	public static String generatePasswordKey(int length){
    	String alphabet = 
    	        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
    	int n = alphabet.length(); //10

    	String result = new String(); 
    	Random r = new Random(); //11

    	for (int i=0; i<length; i++) //12
    	    result = result + alphabet.charAt(r.nextInt(n)); //13

    	return result;
    	}

}
