package com.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Name {
	public static void main(String[] args) {
		// Example array of integers

		Random r = new Random();
		int u = 4;
		int nextInt;
		do {
			nextInt = r.nextInt(u);
		} while (nextInt == 0);
		String randomNumeric;
		do {
			randomNumeric = RandomStringUtils.randomNumeric(nextInt);
		} while (randomNumeric.equals("0") || randomNumeric.equals("00") || randomNumeric.equals("000")
				|| randomNumeric.startsWith("0") || randomNumeric.startsWith("00"));
		System.out.println(randomNumeric);
	}
}
