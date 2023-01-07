package com.base;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.github.javafaker.File;
import com.github.javafaker.FunnyName;
import com.github.javafaker.Number;

public class Name {

	public static void main(String[] args) {
		  int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    Random random = new Random();

		    String generatedString = random.ints(leftLimit, rightLimit + 1)
		      .limit(targetStringLength)
		      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		      .toString();

		    System.out.println(generatedString);
		
		
	}
}
