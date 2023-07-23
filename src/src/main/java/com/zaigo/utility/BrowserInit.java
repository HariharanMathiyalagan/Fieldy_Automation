//package com.zaigo.utility;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//
//public class BrowserInit {
//	
//	BrowserSetup browser = new BrowserSetup();
//	
//	@BeforeMethod
//	public void OpenCrmSite() {
//		WebDriver driverInstance = browser.startBrowser();
//		BrowserThread.getInstance().setDriver(driverInstance);
//		WebDriver driver = BrowserThread.getInstance().getDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		String BASE_URL = System.getenv("APP_URL");
//		driver.navigate().to(BASE_URL);
//	}
//	
//	@AfterMethod
//	public void tearDown() {
//		BrowserThread.getInstance().closeBrowser();
//	}
//
//}
