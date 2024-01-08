package com.base;

import java.awt.Color;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.github.javafaker.Faker;

public class Name extends BaseClass {

	public static void main(String[] args) {

		String value = "hariharan";
		char[] charArray = value.toCharArray();
		for (int i = 0; i < value.length(); i++) {
			for (int j = i + 1; j < value.length(); j++) {
				if (charArray[i] == charArray[j]) {
					System.out.println(charArray[j]);
				}
			}
		}

	}

}
