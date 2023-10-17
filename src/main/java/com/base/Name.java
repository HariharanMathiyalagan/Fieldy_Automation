package com.base;

import java.awt.Color;
import java.util.Random;

import com.github.javafaker.Faker;

public class Name extends BaseClass {

public static void main(String[] args) {
	Faker faker = new Faker();
	String url = faker.internet().url();
String replace = url.replace("-", "");
	System.out.println(replace);
}
   
}
