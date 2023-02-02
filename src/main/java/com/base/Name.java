package com.base;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.zaigo.pageobjects.InvoicePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Name extends BaseClass {

	@Test
	private void start() throws ParseException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("");
	

//		String name = "Hari";

//		InvoicePage initElements = PageFactory.initElements(driver, InvoicePage.class);
//		initElements.clearAllFields();

//System.out.print(calculation("10", "221.35", "55", "55"));

//float a = 45.20f;
//System.out.println(a);
//		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, 3);
//		String currentDate = sdf.format(cal.getTime());
//		System.out.println(currentDate);

//		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
//		Date parse = sdf.parse();
//		System.out.println(parse);
//		sdf = new SimpleDateFormat("MM/dd/yyyy");
//		System.out.println(sdf.format(parse));
		
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, 3);
//		String currentDate = sdf.format(cal.getTime());
//		System.out.println(currentDate);
		

	}
}
