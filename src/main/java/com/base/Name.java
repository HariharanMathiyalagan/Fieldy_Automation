package com.base;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.github.javafaker.Faker;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Name extends BaseClass {
	static String[] split;

	public static void main(String[] args) {
		String value = "ram, sam, tom, pam";
		if (value.contains(", ")) {
			split = value.split(", ");
		} else if (value.contains(",")) {
			split = value.split(",");
		}
		for (String string : split) {
			System.out.println(string);
		}

	}
}
