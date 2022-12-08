package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.utility.BrowserSetup;

public class Functional_Test {
	String MandatoryErrorMessage = "Required Field";
	String PasswordCondition = "Password must have one lower case letter and one upper case letter and one number";
	String MinimumValidatioPassword = "Enter minimum 8 characters";
	String MisMatchPassword = "Confirm password should match with new password";
	String Max2048Validation = "Not Allowed More than 2048 characters";
	String Max256CharacterValidation = "Not Allowed More than 256 characters";
	String ValidEmail = "Enter a valid Email";
	String BussinessNameAlready = "Business Name Already Exists";
	String AlreadyExistedEmail = "Email Already Exists";
	String IndustryMaxValidation = "Not Allowed More than 64 characters";

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("OnBoarding.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();

	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();

	}

	@Test(priority = 0)
	public void LaunchingOnBoarding() throws MalformedURLException, IOException {
		extentTest = extentReports.createTest("Verify the OnBoarding URL Response Code Validation");
		OnBoardingPage boardingPage = new OnBoardingPage(driver);
		boardingPage.login();
		int responseCode = boardingPage.responseCode();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + responseCode);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + "200");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseCode == 200) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//			System.out.println(Status.PASS);
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			System.out.println(Status.PASS);
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void createNewTenant() throws AWTException, InterruptedException {
		OnBoardingPage boardingPage = new OnBoardingPage(driver);
		boardingPage.firstPage();
		boardingPage.secoundPage();
		boardingPage.thirdPage();
		boardingPage.fourthPage();
		boardingPage.fifthPage();
		boardingPage.sixthPage();
		boardingPage.functionalityCompaniesEdit();

	}
	
	

}
