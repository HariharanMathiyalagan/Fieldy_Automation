package com.base;

import java.awt.AWTException;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hc.core5.http.impl.nio.ExpandableBuffer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriver.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Category;
import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import com.sun.tools.javac.code.Attribute.Array;
import com.zaigo.pageobjects.InvoicePage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.pageobjects.QuotePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Name extends BaseClass {
	static HttpURLConnection connection;
	static List<String> list;
	WebDriver driver;

//	private void name1() throws IOException, InterruptedException, AWTException {
//		// TODO Auto-generated method stub
////		String value = "https://qaapp.zaigotech.com/public/quote/view/?hashid=$2y$10$.RTFzDoewmH8cYIr0nFeaJLXX1Z7AdQf.dKvw.OYjAnYYuHUDt.";
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.get("https://qaapp.zaigotech.com/login");
//		LoginPage loginInPage = new LoginPage(this.driver);
//		loginInPage.userField(getPropertyValueUpdate("UserName"));
//		loginInPage.passwordField(getPropertyValue("Password", getPropertyValue("Enviromment")));
//		loginInPage.clickLoginButton();
//		loginInPage.dashBoardText();
//		QuotePage module = PageFactory.initElements(driver, QuotePage.class);
//		String editContact = module.labelValidation("Global");
//		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
//		create.listTextValidation("PDFPage");
////		driver.switchTo().newWindow(WindowType.WINDOW);
////		JavascriptExecutor js = (JavascriptExecutor) driver;
//////		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
////		js.executeScript("window.open('" + value + "','_blank');");
//		Set<String> windowHandles = driver.getWindowHandles();
//		list = new ArrayList<String>(windowHandles);
//		driver.switchTo().window(list.get(1));
//		String currentUrl = driver.getCurrentUrl();
//		System.out.println(currentUrl);
//		connection = (HttpURLConnection) new URL(currentUrl).openConnection();
//		connection.setRequestMethod("HEAD");
//		connection.connect();
//
//	}
//
//	public void name2() throws IOException {
//		int responseCode = connection.getResponseCode();
//		if (responseCode == 200) {
//			System.out.println(true);
//		} else {
//			System.out.println(false);
//		}
//		driver.switchTo().window(list.get(0));
//	}
//
//	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
//		Name name = new Name();
//		name.name1();
//		name.name2();
//
//	}
	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			System.out.println("Internet is connected");
		} catch (MalformedURLException e) {
			System.out.println("Internet is not connected");
		} catch (IOException e) {
			System.out.println("Internet is not connected");
		}
	}

}
