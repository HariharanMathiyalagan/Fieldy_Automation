package com.base;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
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
import java.util.Stack;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hc.core5.http.impl.nio.ExpandableBuffer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.zaigo.pageobjects.OnBoardingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Name extends BaseClass {
	static StringBuffer append;
	int parseInt;
	static String value1;

	private String method(int value) {
		String data[] = { "value1", "value2", "value3", "value4" };
		return data[value];

	}

	public static void main(String[] args) {
//		for (int i = 0; i < 10000; i++) {
//			Faker faker = new Faker();
//			String fakeFirstName = faker.name().firstName();
//			String fakeLastName = faker.name().lastName();
//			String fakeContactPersonName = faker.name().fullName();
//			String fakeAddress1 = faker.address().buildingNumber();
//			String fakeAddress2 = faker.address().streetName();
//			String fakeCity = faker.address().city();
//			String fakeState = faker.address().state();
//			String fakeZipcode = faker.address().zipCode();
//			System.out.println(fakeFirstName);
//		}
		Name name = new Name();
		System.out.println(name.method(2));

	}
}