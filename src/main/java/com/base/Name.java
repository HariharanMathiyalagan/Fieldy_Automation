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

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.hc.core5.http.impl.nio.ExpandableBuffer;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
	static String value;

	@Test
	private void start() throws ParseException, IOException {
		// TODO Auto-generated method stub

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://qarecircle.zaicrm.com/login");
//		driver.findElement(By.id("phoneNumber")).sendKeys("9876543210");
//		driver.findElement(By.id("password")).sendKeys("password");
//		driver.findElement(By.xpath("//*[@class='btn btn-info btn-ghost-light p-2']")).click();
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-n-linkto='dashboard']")))
//				.isDisplayed();
//		wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//*[@class=\"nav-link collapsed collection-icon\"]"))).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-n-linkto=\"add_facility_list\"]")))
//				.click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=\"card-title mb-0\"])[1]")))
//				.isDisplayed();
		// *[@data-n-linkto="add_facility_list"]

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
//		
//		int a= 1;
//		int b =2 ;
//		int c=4;
//		if(a==b || b==c)
//		{
//
		boolean jim = false;
		
		
		
		

//		}

//		String value = "Manju";
//		
//		char[] a = value.toCharArray();
//		for (int i = 0; i < a.length; i++) {
//			
//		}

//		String[] s = { "Hello", "Manju", "How", "are", "you" };
//		int count =0;
//		
//		for
//		if (count == 0) {
//			System.out.println("How is Present");
//		}else {
//			System.out.println("How is not Present");
//		}
//	}

//		for (int i = 0; i < s.length; i++) {
//			int indexOf = s[i].indexOf("How", 2);
//			if (indexOf == 2) {
//				System.out.println("True");
//			}else {
//				System.out.println(false);
//			}
//		}
//
//	}

//		    String[] colours = {"Red", "Green", "Yellow", "Red"};
//		    
//		    // Check if a value exists in the colours array
//		    if(colours.length !== -1){
//		        document.write("Red already exists")
//		    } else{
//		        document.write("Red does not exists!")
//		    }
//	
//
//		Faker faker = new Faker(new Locale("en-IND"));
//		String creditCardExpiry = faker.app().name();
//		System.out.println(creditCardExpiry);

//		String input = "jerry";
//		String output = input.substring(0, 1).toUpperCase() + input.substring(1);
//System.out.println(output);

//		RandomDataGenerator dataGenerator = new RandomDataGenerator();
//		RandomGenerator randomGenerator = dataGenerator.getRandomGenerator();
//		System.out.println(randomGenerator);

//		Random random = new Random();
//		RandomService randomService = new RandomService();
//		String hex = randomService.hex(5);
//		System.out.println(hex);

//		int leftLimit = 97; // letter 'a'
//	    int rightLimit = 122; // letter 'z'
//	    int targetStringLength = 10;
//	    Random random = new Random();
//	    StringBuilder buffer = new StringBuilder(targetStringLength);
//	    for (int i = 0; i < targetStringLength; i++) {
//	        int randomLimitedInt = leftLimit + (int) 
//	          (random.nextFloat() * (rightLimit - leftLimit + 1));
//	        buffer.append((char) randomLimitedInt);
//	    }
//	    String generatedString = buffer.toString();
//
//	    System.out.println(generatedString);
		for (int i = 0; i < 1000; i++) {
			Faker faker = new Faker(new Locale("en-IND"));
			String url = faker.phoneNumber().phoneNumber();
			System.out.println(url);

		}

//		String fakeFirstName = faker.name().firstName();
//		System.out.println("First Name:" + fakeFirstName);
//		
//		String fakeLastName = faker.name().lastName();
//		System.out.println("Last Name:" + fakeLastName);
//		
//		String fakeEmail = faker.internet().safeEmailAddress();
//		System.out.println("Email:" + fakeEmail);
//		
//		String fakePhoneNumber = faker.phoneNumber().phoneNumber();
//		System.out.println("Phone Number:"+fakePhoneNumber);
//		
//		String fakeTittle = faker.name().title();
//		System.out.println("Job Tittle:"+fakeTittle);
//		
//		String fakeAddress1 = faker.address().buildingNumber();
//		System.out.println("Address1:"+fakeAddress1);
//		
//		String fakeAddress2 = faker.address().streetName();
//		System.out.println("Address2:"+fakeAddress2);
//		
//		String fakeCity = faker.address().city();
//		System.out.println("City:"+fakeCity);
//		
//		String fakeState = faker.address().state();
//		System.out.println("State:"+fakeState);
//		
//		String fakeZipcode = faker.address().zipCode();
//		System.out.println("Zipcode:"+fakeZipcode);
//		
//		String fakeWebsite = faker.company().url();
//		System.out.println("Website:"+fakeWebsite);
//		
//		String fakeCompanyName = faker.company().name();
//		System.out.println("Company Name:"+fakeCompanyName);
//		
//		String fakeFaxNumber = faker.number().digits(14);
//		System.out.println("Fax Number:"+fakeFaxNumber);
//		
//		String characters256 = RandomStringUtils.randomGraph(256);
//		System.out.println(characters256);
//		
//		String[] s = { "Hello", "Manju", "How", "are", "you" };
//		int count = 0;
//		for (int i = 0; i < s.length; i++) {
//			if (s[i].equals("How")) {
//				count = 1;
//				System.out.println(s[i]);
//				break;
//				
//			}
//		}
//		if (count == 0) {
//			System.out.println("How is Present");
//		}else {
//			System.out.println("How is not Present");
//		}
//		
//	
//	}
//		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\Automation\\Chrome Driver\\chromedriver.exe");
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get("https://dev.paygivapp.com/register");
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		boolean displayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Your Contact Info']"))).isDisplayed();
//		if (displayed) {
//			System.out.println("True");
//		}else {
//			System.out.println("False");
//		}
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname"))).sendKeys("Naveen");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname"))).clear();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname"))).sendKeys("123456");
//		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='The first name can only consist of alphabetical and space']"))).getText();
//		System.out.println(text);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname"))).clear();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname"))).sendKeys("N a v e e n");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname"))).clear();
////		driver.findElement(By.xpath("//*[@class=\"mt-2\"]")).isDisplayed();
//		driver.quit();
//		OnBoardingPage mandatory = new OnBoardingPage(driver);
//		System.out.println(OnBoardingPage.variable);

//		for (int i = 0; i < 10; i++) {
//			Faker faker = new Faker(new Locale("en-IND"));
//			String fakeCompanyName = faker.company().name().replaceAll("[^a-zA-Z0-9]", " ");
//			value = fakeCompanyName.replaceAll("\\s", "").toLowerCase();
////			System.out.println(lowerCase);
//		}
//
//		String val = value;
//		Properties properties = new Properties();
//		properties.put("Email", val);
//		FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir") + "\\Folder\\Update.properties");
//		properties.store(fo, "OutPut");
//
//		System.out.println(getPropertyValueUpdate("Email"));
//		
//		String input = "Jan-14-2023 | 10:00";
//		for (int i = 0; i < input.length(); i++) {
//			
//		}
//		
//		String output = input.substring(0, input.indexOf(" |"));
//		System.out.println(output);

//		String expectedURL = "https://" + (lowerCase.toLowerCase().replaceAll("\\s", ""))
//				+ getPropertyValue("DomainURL");
//
//		System.out.println(expectedURL);

	}
}

//	public static void main(String[] args) {
//

//	
//		System.out.println(replaceAll);
//	}

//		StringBuffer buffer = new StringBuffer();
//		String s = "Admin(10)";
//
//		for (int i = 0; i < s.length(); i++) {
//
//			if (Character.isDigit(s.charAt(i))) {
//				append = buffer.append(s.charAt(i));
//				parseInt = Integer.parseInt(append.toString());
//
//			}
//
//		}
//		System.out.println("Admin(" + (parseInt + 1) + ")");

//}
