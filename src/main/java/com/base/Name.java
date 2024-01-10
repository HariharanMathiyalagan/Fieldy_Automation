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

	public static void main(String[] args) throws MalformedURLException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "incognito",
				"--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(options);
		driver.get(
				"https://qaapp.zaigotech.com/public/invoice/view/?hashid=$2y$10$cFe.9QAOMggoXEryYBgB9.FrMyzpFXpNXN4lUMbdjMi16qeaC4q5.");
		String url = driver.getCurrentUrl();
//		connection = (HttpURLConnection) new URL(currentUrl).openConnection();
//		connection.setRequestMethod("HEAD");
//		connection.connect();
//		int responseCode = connection.getResponseCode();
//		if (responseCode == 200) {
//			System.out.println(true);
//		} else {
//			System.out.println(false);
//		}
		try {
			// Create a custom HttpClient that ignores SSL certificate issues
			CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier((host, session) -> true)
					.build();

			// Create an HTTP GET request
			HttpGet request = new HttpGet(url);

			// Execute the request and get the response
			HttpResponse response = httpClient.execute(request);

			// Print the response code
			System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

			// Close the HttpClient
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
