package com.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Company;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import com.github.javafaker.File;
import com.github.javafaker.FunnyName;
import com.github.javafaker.Number;

public class Name {

	public static void main(String[] args) {
//	Locale default1 = Locale.getDefault();
//	System.out.println(default1.getCountry());
//	System.out.println(default1.getDisplayLanguage());
//	Locale[] availableLocales = Locale.getAvailableLocales();
//for (Locale locale : availableLocales) {
//	System.out.println(locale);
//		Faker faker = new Faker(new Locale("en-IND"));
//		String characters = faker.ancient().titan();
//		System.out.println(characters);
//		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		System.out.println(currentDate);
	

	}
}
