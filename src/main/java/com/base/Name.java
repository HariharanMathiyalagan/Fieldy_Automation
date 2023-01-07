package com.base;

import java.util.Locale;

import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.github.javafaker.File;
import com.github.javafaker.FunnyName;
import com.github.javafaker.Number;

public class Name {

	public static void main(String[] args) {

//		int n = str.length();
//		String str = "GeeksForGeeks";
//		char first = str.charAt(0);
//		System.out.println("First: " + first);
		Faker faker = new Faker(new Locale("en-IND"));
		String safeEmailAddress = faker.internet().safeEmailAddress();
		System.out.println(safeEmailAddress);
		
	}
}
