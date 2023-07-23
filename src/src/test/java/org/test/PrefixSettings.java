package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.PrefixSettingPage;
import com.zaigo.utility.BrowserSetup;

public class PrefixSettings extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("PrefixSettings.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();

	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}

	@BeforeMethod
	public void deleteBeforeCatch() {
		driver.manage().deleteAllCookies();
	}

	@AfterMethod
	public void deleteAfterCatch() {
		driver.manage().deleteAllCookies();
	}

	@Test(priority = -1) // 1-Login
	public void loginPage() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password", getPropertyValue("Enviromment")));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(loginInPage.getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality.png");
		}
	}

	@Test(priority = 0)
	private void gotoprefixsettings() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on Prefix Settings");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		String text = edit.modulePage();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PrefixSettingsLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("PrefixSettingsLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality1.png");
		}

	}

	@Test(priority = 1)
	private void errorMessageforEmptyJobPrefix() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed For Job Field is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.modulePage();
		edit.clearField("JobPrefix");
		edit.clickSubmit();
		String text = edit.getErrorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Jobprefixerrormessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Jobprefixerrormessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality2.png");
		}

	}

	@Test(priority = 2)
	private void errorMessageforMaxValidation() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Error Message Displayed For Job Field have more than 45 characters");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.clearField("JobPrefix");
		edit.enterJobPrefix();
		edit.clickSubmit();
		edit.clearField("JobPrefix");
		String text = edit.getErrorMessageMax();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Jobprefixerrormessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Jobprefixerrormessagemax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality3.png");
		}

	}

	@Test(priority = 3)
	private void errorMessageforPrefixRequired() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed For Job No Field is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.clearField("JobPrefixNumber");
		edit.clickSubmit();
		String text = edit.getErrorMessagePreFixNumberRequired();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobPrefixRequired"));
		System.out.println(getPropertyValue("JobPrefixRequired"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("JobNumberNull"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality4.png");
		}

	}

	@Test(priority = 4)
	private void errorMessageforRequestPrefixMandatory() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Request Prefix is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.validData("JobPrefix");
		edit.mandatoryValidation("RequestPrefix");
		String text = edit.getErrorMessageRequestPreFixNumberRequired();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobPrefixRequired"));
		System.out.println(getPropertyValue("JobPrefixRequired"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ReqPrefixNull"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality4.png");
		}

	}

	@Test(priority = 5)
	private void errorMessageforRequestPrefixMaxCharacters() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Request Prefix is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.validData("JobPrefix");
		edit.maxValidation("RequestPrefix");
		String text = edit.getErrorMessageRequestPreFixNumberMax2();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobPrefixRequired"));
		System.out.println(getPropertyValue("JobPrefixRequired"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ReqPreMax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality5.png");
		}

	}

	@Test(priority = 6)
	private void errorMessageforRequestPrefixNumberMandatory() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Request Prefix Number is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.validData("JobPrefix");
		edit.mandatoryValidation("RequestPrefixNumber");
		String text = edit.getErrorMessageRequestPreFixNumberReq();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobPrefixRequired"));
		System.out.println(getPropertyValue("JobPrefixRequired"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ReqPrefixNumNull"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality6.png");
		}

	}

	@Test(priority = 7)
	private void errorMessageforRequestPrefixNumberMax() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Request Prefix Number with max");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.validData("JobPrefix");
		edit.maxValidation("RequestPrefixNumber");
		String text = edit.getErrorMessageRequestPreFixNumberMax();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ReqPrefixNumMax"));
		System.out.println(getPropertyValue("ReqPrefixNumMax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ReqPrefixNumMax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality9.png");
		}

	}

	@Test(priority = 8)
	private void errorMessageforQuotePrefixMandatory() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Quote Prefix  is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.mandatoryValidation("QuotePrefix");
		String text = edit.getErrorMessageQuotePrfixMandatory();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuotePrefixMandatory"));
		System.out.println(getPropertyValue("JobPrefixRequired"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("QuotePrefixMandatory"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality10.png");
		}

	}

	@Test(priority = 9)
	private void errorMessageforQuotePrefixMax() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Quote Prefix  with max");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.validData("JobPrefix");
		edit.maxValidation("QuotePrefix");
		String text = edit.getErrorMessageQuotePrefixMax();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuotePrefixMax"));
		System.out.println(getPropertyValue("QuotePrefixMax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("QuotePrefixMax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality11.png");
		}

	}

	@Test(priority = 10)
	private void errorMessageforQuoteNumMandatory() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Quote Num  is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.mandatoryValidation("QuotePrefixNumber");
		String text = edit.getErrorMessageQuoteNumMandatory();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuoteNumMandatory"));
		System.out.println(getPropertyValue("QuoteNumMandatory"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("QuoteNumMandatory"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality12.png");
		}

	}

	@Test(priority = 11)
	private void errorMessageforQuoteNumMax() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Quote Number with max");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.validData("JobPrefix");
		edit.maxValidation("QuotePrefixNumber");
		String text = edit.getErrorMessageQuoteNumMax();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuotePrefixMax"));
		System.out.println(getPropertyValue("QuotePrefixMax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("QuotePrefixMax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality13.png");
		}

	}

	@Test(priority = 12)
	private void errorMessageforInvoicePrefixMandatory() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Invoice Prefix  is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.mandatoryValidation("InvoicePrefix");
		String text = edit.getErrorMessageInvoiceNameMandatory();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvoicePrefixNameMandatory"));
		System.out.println(getPropertyValue("InvoicePrefixNameMandatory"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("InvoicePrefixNameMandatory"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality14.png");
		}

	}

	@Test(priority = 13)
	private void errorMessageforInvoiceNameMax() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Invoice Prefix  is Max");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.maxValidation("InvoicePrefix");
		String text = edit.getErrorMessageInvoiceNameMax();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvoiceNameMax"));
		System.out.println(getPropertyValue("InvoiceNameMax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("InvoiceNameMax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality15.png");
		}

	}
//Invoice Number 

	@Test(priority = 14)
	private void errorMessageforInvoiceNumMandatory() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Invoice Prefix  is Null");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.mandatoryValidation("InvoicePrefixNumber");
		String text = edit.getErrorMessageInvoiceNumNull();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvoiceNumMandatory"));
		System.out.println(getPropertyValue("InvoiceNumMandatory"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("InvoiceNumMandatory"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality14.png");
		}

	}

	@Test(priority = 15)
	private void errorMessageforInvoiceNumMax() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Invoice Prefix  is Max");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.maxValidation("InvoicePrefixNumber");
		String text = edit.getErrorMessageInvoiceNumMax();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvoiceNumMax"));
		System.out.println(getPropertyValue("InvoiceNumMax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("InvoiceNumMax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality15.png");
		}

	}
	
	//job_duplicate
	
	@Test(priority = 15)
	private void errorMessageforJobDuplicateNumber() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed If Invoice Prefix  is Max");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.jobInput();
		String text = edit.getDuplicateErrorMessageRequest();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DuplicateMessage"));
		System.out.println(getPropertyValue("DuplicateMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("DuplicateMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality17.png");
		}

	}

	

	@Test(priority = 16)
	private void verifySuccessMessage() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Success message Displayed");
		PrefixSettingPage edit = PageFactory.initElements(driver, PrefixSettingPage.class);
		edit.Refresh();
		edit.clickSubmit();
		String text = edit.getSuccessMessage();
		System.out.println(text);
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SuccessMessage"));
		System.out.println(getPropertyValue("SuccessMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("SuccessMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality16.png");
		}

	}

}
