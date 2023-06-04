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
import com.zaigo.pageobjects.BillingPage;
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.PrefixSettingPage;
import com.zaigo.pageobjects.SubscriptionPage;
import com.zaigo.utility.BrowserSetup;

public class BillingInformation extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("BillingInformationPage.html");
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

	@Test(priority = 1)
	public void modulepage() throws IOException {
		extentTest = extentReports.createTest("Verify the User to land on the Subscription Choose Plan Page");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.modulePage();
		module.labelValidation("ChoosePlan");
		module.labelValidation("2");
	}

	@Test(priority = 2)
	public void errorMessageforMandatoryFirstName() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for First name null");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.clearField("firstname");
		module.click_pay();
		String text = module.FirstnameErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Firstnameerrormessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Firstnameerrormessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails1.png");
		}
	}

	@Test(priority = 3)
	public void errorMessageforMandatoryLastName() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Last name null");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.clearField("LastName");
		module.click_pay();
		String text = module.LastnameErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Lastnameerrormessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Lastnameerrormessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails2.png");
		}
	}

	@Test(priority = 4)
	public void errorMessageforMandatoryEmail() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Email null");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.clearField("email");
		module.click_pay();
		String text = module.EmailErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + "");
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EmailErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("EmailErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 5)
	public void errorMessageforMandatoryCompanyName() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for CompanyName null");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.clearField("company");
		module.click_pay();
		String text = module.CompanyErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("CompanyErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails4.png");
		}
	}

	@Test(priority = 6)
	public void errorMessageforMandatoryAddressone() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Addressone null");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.clearField("addressone");
		module.click_pay();
		String text = module.AddressError();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AddressErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("AddressErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails5.png");
		}
	}

	@Test(priority = 7)
	public void errorMessageforMandatoryState() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for State null");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.clearField("state");
		module.click_pay();
		String text = module.StateErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("StateErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("StateErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails6.png");
		}
	}

	@Test(priority = 8)
	public void errorMessageforMandatoryCity() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for City null");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.clearField("city");
		module.click_pay();
		String text = module.CityErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CityErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("CityErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails7.png");
		}
	}

	@Test(priority = 9)
	public void errorMessageforMandatoryZipcode() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Zipcode null");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.clearField("zipcode");
		module.click_pay();
		String text = module.ZipcodeErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ZipcodeErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ZipcodeErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails8.png");
		}
	}

	@Test(priority = 10)
	public void errorMessageforInvalidEmail() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Invalid Email");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.maxValidation("email");
		module.click_pay();
		String text = module.EmailErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("InvalidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 11)
	public void errorMessageforMaxFirstName() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for name exceed max characters ");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.maxValidation("firstname");
		module.click_pay();
		String text = module.FirstnameErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 12)
	public void errorMessageforMaxLastName() throws IOException {
		extentTest = extentReports
				.createTest("Verify the Error Message Displayed for Last name exceed max characters ");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.maxValidation("lastname");
		module.click_pay();
		String text = module.LastnameErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 13)
	public void errorMessageforMaxCompanytName() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Company exceed max characters ");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.maxValidation("company");
		module.click_pay();
		String text = module.CompanyErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 14)
	public void errorMessageforMaxAddressName() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Address exceed max characters ");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.maxValidation("address");
		module.click_pay();
		String text = module.AddressError();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 15)
	public void errorMessageforMaxCityName() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for City exceed max characters ");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.maxValidation("city");
		module.click_pay();
		String text = module.CityErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 16)
	public void errorMessageforMaxStateName() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for State exceed max characters ");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.maxValidation("state");
		module.click_pay();
		String text = module.StateErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 17)
	public void errorMessageforMaxZipcode() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Zipcode exceed max characters ");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.maxValidation("zipcode");
		module.click_pay();
		String text = module.ZipcodeErrorMessage();
		System.out.println(text);
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}

	@Test(priority = 18)
	public void verifythePaymentPage() throws IOException {
		extentTest = extentReports.createTest("Verify the Error Message Displayed for Zipcode exceed max characters ");
		BillingPage module = PageFactory.initElements(driver, BillingPage.class);
		module.validData("firstname");
		module.validData("lastname");
		module.validData("email");
		module.validData("companyname");
		module.validData("address");
		module.validData("city");
		module.validData("state");
		module.validData("zipcode");
		module.visitPaymentPage();
		String text = module.PaymentLable();
		// String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Lable"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("Lable"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Billingdetails3.png");
		}
	}
}
