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
	static HttpURLConnection connection;
	String First;

	public static void main(String[] args) throws MalformedURLException, IOException {
		Name n = new Name();
		n.method123("Ram");
	}

	public String method123(String value) {
		if (value.equals("Hari")) {
			return First;
		} else if (value.equals("Ram")) {
			return First;
		}
//		if (!value.equals("Ram")) {
//			return "Naveen";
//
//		}
		return value;
	}
}
