package com.zaigo.utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup extends BaseClass {

	public static WebDriver startBrowser() throws IOException {
		if (getBrowserValue("BrowserName").equals("chrome")) {
			WebDriver driver = null;
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "incognito");
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			return driver;
		} else if (getBrowserValue("BrowserName").equals("edge")) {
			WebDriver driver = null;
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
//			options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "-inprivate");
			driver = new EdgeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			return driver;
		} else if (getBrowserValue("BrowserName").equals("firefox")) {
			WebDriver driver = null;
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "-private");
			driver = new FirefoxDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			return driver;
		}
		return null;
		// "--headless", "window-size=1920,1080"
	}

	public static WebDriverWait createWebDriverWait(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait;
	}

	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
