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
import java.util.Stack;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hc.core5.http.impl.nio.ExpandableBuffer;
import org.openqa.selenium.By;
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
import com.zaigo.pageobjects.OnBoardingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Name {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/login");
		driver.switchTo().newWindow(WindowType.WINDOW);
	}
}


