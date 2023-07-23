package com.zaigo.reactui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Testclass {

	
	@Test
	public void testcontractor() throws InterruptedException
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\Downloads\\chromedriver_win32 (6)\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://tenant4.zaiportal.com/login/");
		driver.findElement(By.id("login")).sendKeys("alpha@zaisuite.com");
		driver.findElement(By.id("password")).sendKeys("Zaiserve@123");
		driver.findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Team']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Team']")).click();
		
		
	}
}
