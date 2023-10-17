package org.test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.pageobjects.SubscriptionPage;
import com.zaigo.utility.BrowserSetup;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OnBoardingModule extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
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

	@BeforeMethod
	public void deleteBeforeCatch() {
		driver.manage().deleteAllCookies();
	}

	@AfterMethod
	public void deleteAfterCatch() {
		driver.manage().deleteAllCookies();
	}

////	@Test(priority = 0)
//	public void LaunchingOnBoarding() throws MalformedURLException, IOException {
//		extentTest = extentReports.createTest("Verify the OnBoarding URL Response Code Validation");
//		OnBoardingPage boardingPage = new OnBoardingPage(driver);
//		boardingPage.login();
//		int responseCode = boardingPage.responseCode();
//		extentTest.log(Status.INFO, "Actual Result is -" + responseCode);
//		extentTest.log(Status.INFO, "Expected Result is -" + "200");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (responseCode == 200) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
////			System.out.println(Status.PASS);
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
////			System.out.println(Status.PASS);
//			driver.quit();
//		}
//	}

	@Test(priority = 0)
	public void mandatoryBussinessNameFieldValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Check Business Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		OnBoardingPage mandatory = PageFactory.initElements(driver, OnBoardingPage.class);
		mandatory.emailText();
		mandatory.mandatoryValidation();
		String manditoryValidations = mandatory.manditoryValidations();
		extentTest.log(Status.INFO, "Actual Result is -" + manditoryValidations);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (manditoryValidations.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OnBoarding Mandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OnBoarding Mandatory.png");

		}

	}

	@Test(priority = 1)
	public void mandatoryEmailFieldValidation() throws IOException {
		extentTest = extentReports
				.createTest("Check the Email field is set as Mandatory & Error Message is displayed when it is BLANK");
		OnBoardingPage mandatory = PageFactory.initElements(driver, OnBoardingPage.class);
		mandatory.mandatoryEmailValidation();
		String manditoryValidations = mandatory.manditoryValidations();
		extentTest.log(Status.INFO, "Actual Result is - " + manditoryValidations);
		extentTest.log(Status.INFO, "Expected Result is - " + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (manditoryValidations.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OnBoarding Mandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OnBoarding Mandatory.png");

		}

	}

	@Test(priority = 2)
	private void alreadyBussinessNameValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify [Business Name Already Exists] Error is dispalyed when already existing Bussiness Name is provided");
		OnBoardingPage alreadyBussiness = PageFactory.initElements(driver, OnBoardingPage.class);
		alreadyBussiness.alreadyBussinessName();
		String errorMessageBussinessName = alreadyBussiness.errorMessageBussinessName();
		extentTest.log(Status.INFO, "Actual Result Already Exited Data - " + errorMessageBussinessName);
		extentTest.log(Status.INFO,
				"Expected Result Already Existed Data - " + getPropertyValue("BussinessNameAlready"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMessageBussinessName.equals(getPropertyValue("BussinessNameAlready"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			alreadyBussiness.clearCompanyName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("BussinessNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BussinessNameValidation.png");
			alreadyBussiness.clearCompanyName();

		}
	}

	@Test(priority = 3)
	private void specialCharacterBussinessNameValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify the Error Message is displayed when the Bussiness Name field enter the Special Characters");
		OnBoardingPage alreadyBussiness = PageFactory.initElements(driver, OnBoardingPage.class);
		alreadyBussiness.specialCharacterBussinessName();
		String errorMessageBussinessName = alreadyBussiness.errorMessageBussinessName();
		extentTest.log(Status.INFO, "Actual Result is - " + errorMessageBussinessName);
		extentTest.log(Status.INFO,
				"Expected Result is - " + getPropertyValue("BussinessNameSpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMessageBussinessName.equals(getPropertyValue("BussinessNameSpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			alreadyBussiness.clearCompanyName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("BussinessNameSpecialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BussinessNameSpecialValidation.png");
			alreadyBussiness.clearCompanyName();

		}
	}

	@Test(priority = 4)
	public void maximumValidationBussinessName() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Business Name Field exceed its max-256 limit");
		OnBoardingPage maxValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		maxValidation.maximumValidationBussinessName();
		String errorMessageBussinessName = maxValidation.errorMessageBussinessName();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMessageBussinessName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMessageBussinessName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearBussinessName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("BussinessValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BussinessValidation.png");
			maxValidation.clearBussinessName();

		}
	}

	@Test(priority = 5)
	private void maximumValidationBussinessWebSite() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Business Website Field exceed its max-2048 limit");
		OnBoardingPage maxValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		maxValidation.maximumValidationBussinessWebSite();
		String errorWebsite = maxValidation.errorWebsite();
		extentTest.log(Status.INFO, "Actual Result is -" + errorWebsite);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorWebsite.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearWebsite();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("BussinessWebsiteValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BussinessWebsiteValidation.png");
			maxValidation.clearWebsite();

		}
	}

	@Test(priority = 6)
	private void maximumValidationFirstName() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when First Name Field exceed its max-256 limit");
		OnBoardingPage maxValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		maxValidation.maximumValidationFirstName();
		String errorFirstName = maxValidation.errorFirstName();
		extentTest.log(Status.INFO, "Actual Result is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFirstName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("FirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("FirstNameValidation.png");
			maxValidation.clearFirstName();

		}
	}

	@Test(priority = 7)
	private void maximumValidationLastName() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Last Name Field exceed its max-256 limit");
		OnBoardingPage maxValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		maxValidation.maximumValidationLastName();
		String errorLastName = maxValidation.errorLastName();
		extentTest.log(Status.INFO, "Actual Result is -" + errorLastName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorLastName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearLastName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LastNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LastNameValidation.png");
			maxValidation.clearLastName();

		}

	}

	@Test(priority = 8)
	private void maximumValidationEmail() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Email Field exceed its max-256 limit");
		OnBoardingPage maxValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		maxValidation.maximumValidationEmail();
		String errorEmail = maxValidation.errorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EmailValidation.png");
			maxValidation.clearEmail();

		}

	}

	@Test(priority = 9)
	private void validateEmailFormat() throws IOException {
		extentTest = extentReports
				.createTest("Verify error message is displayed when invalid email is entered in Email Field");
		OnBoardingPage validEmail = PageFactory.initElements(driver, OnBoardingPage.class);
		validEmail.validationEmail();
		String errorEmail = validEmail.errorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validEmail.clearEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EmailValidation.png");
			validEmail.clearEmail();

		}
	}

	@Test(priority = 10)
	private void existingEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify [Email Already Exists] Error is dispalyed when already existing mail ID is provided");
		OnBoardingPage existing = PageFactory.initElements(driver, OnBoardingPage.class);
		existing.alreadyExistValidation();
		String errorEmail = existing.errorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			existing.clearEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EmailValidation.png");
			existing.clearEmail();

		}

	}

	@Test(priority = 11)
	private void correctValidationEmail() throws IOException {
		extentTest = extentReports
				.createTest("Verify User is Landing in Industry Page after providing the Business details");
		OnBoardingPage validation = PageFactory.initElements(driver, OnBoardingPage.class);
		validation.validFillData("FirstPage");
		String messageLandingPage = validation.messageLandingPage();
		extentTest.log(Status.INFO, "Actual Result is -" + messageLandingPage);
		extentTest.log(Status.INFO, "Expected Result is -" + "Provide your Industry type");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (messageLandingPage.equals("Provide your Industry type")) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validation.updateValue();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Industry.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Industry.png");
			validation.updateValue();
		}

	}

	@Test(priority = 12)
	private void validateRadioButtonClickable() {
		extentTest = extentReports.createTest("Verify the Industry Radio Button are Clickable");
		OnBoardingPage radioButton = PageFactory.initElements(driver, OnBoardingPage.class);
		radioButton.industryRadioButton();

	}

	@Test(priority = 13)
	private void industryTypeFieldPresent() throws IOException {
		extentTest = extentReports
				.createTest("Verify Mention your industry information input field is Present in the Page");
		OnBoardingPage fieldPresent = PageFactory.initElements(driver, OnBoardingPage.class);
		fieldPresent.industryTypeFieldPresent();
		String messagePresent = fieldPresent.messagePresent();
		extentTest.log(Status.INFO, "Actual Result is -" + messagePresent);
		extentTest.log(Status.INFO, "Expected Result is -" + "Mention your industry information");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (messagePresent.equals("Mention your industry information")) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Industry.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Industry.png");

		}

	}

	@Test(priority = 14)
	private void maximumValidationIndustryField() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Industry type Field exceed its max-64 limit");
		OnBoardingPage industryValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		industryValidation.maximumValidationIndustryField();
		String errorIndustryField = industryValidation.errorIndustryField();
		extentTest.log(Status.INFO, "Actual Result is -" + errorIndustryField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max64CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorIndustryField.equals(getPropertyValue("Max64CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			industryValidation.clearIndustry();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Industry.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Industry.png");
			industryValidation.clearIndustry();

		}

	}

	@Test(priority = 15)
	private void sizeCompanyValidation() {
		extentTest = extentReports.createTest("Verify Available Size of the Company is Present");
		OnBoardingPage sizeCompany = PageFactory.initElements(driver, OnBoardingPage.class);
		sizeCompany.sizeCompany();

	}

	@Test(priority = 16)
	private void validationRadioButtonIntrested() throws IOException {
		extentTest = extentReports.createTest("Verify the User can Choose Intrest");
		OnBoardingPage radioButton = PageFactory.initElements(driver, OnBoardingPage.class);
		radioButton.radioButtonCurrent();

	}

	@Test(priority = 17)
	private void mandatoryValidationLocation() throws IOException {
		extentTest = extentReports
				.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
		OnBoardingPage mandatory = PageFactory.initElements(driver, OnBoardingPage.class);
		mandatory.mandatoryLocationValidation();
		String errorPasswordField = mandatory.requiredFieldLocation();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LocationMandatoryValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LocationMandatoryValidation.png");
		}

	}

	@Test(priority = 18)
	private void maximumValidationLocation() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Location Field exceed its max-256 limit");
		OnBoardingPage mandatory = PageFactory.initElements(driver, OnBoardingPage.class);
		mandatory.maximumValidationLocation();
		String errorPasswordField = mandatory.requiredFieldLocation();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearLocation();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LocationMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LocationMaximumValidation.png");
			mandatory.clearLocation();
		}

	}

	@Test(priority = 19)
	private void passwordMandatoryField() throws IOException {
		extentTest = extentReports
				.createTest("Verify Password field is set as Mandatory & Error Message is displayed when it is BLANK");
		OnBoardingPage mandatory = PageFactory.initElements(driver, OnBoardingPage.class);
		mandatory.passwordFieldMandatory();
		String errorPasswordField = mandatory.errorPasswordField();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PasswordMandatoryValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PasswordMandatoryValidation.png");
		}

	}

	@Test(priority = 20)
	private void minValidationPassword() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-8 limit is provided in password field");
		OnBoardingPage minValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		minValidation.minimumValidationPassword();
		String errorMinPassword = minValidation.errorMinPassword();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMinPassword);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min8ValidationPassword"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMinPassword.equals(getPropertyValue("Min8ValidationPassword"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearPassword();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PasswordMinValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PasswordMinValidation.png");
			minValidation.clearPassword();
		}

	}

	@Test(priority = 21)
	private void minValidationConfirmPassword() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-8 limit is provided in confirm password field");
		OnBoardingPage minValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		minValidation.minimumValidationConfirmPassword();
		String errorConfirmMessage = minValidation.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min8ValidationPassword"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(getPropertyValue("Min8ValidationPassword"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearConfirmPassword();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ConfirmPasswordMinValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ConfirmPasswordMinValidation.png");
			minValidation.clearConfirmPassword();
		}

	}

	@Test(priority = 22)
	private void passwordFieldConditions() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when Password Condition does not match in Password field");
		OnBoardingPage condition = PageFactory.initElements(driver, OnBoardingPage.class);
		condition.passwordFieldCondition();
		String errorPasswordField = condition.errorPasswordField();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PasswordCondition"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("PasswordCondition"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			condition.clearPassword();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PasswordConditionValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PasswordConditionValidation.png");
			condition.clearPassword();
		}

	}

	@Test(priority = 23)
	private void confirmPasswordFieldCondition() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when Password Condition does not match in Confirm Password field");
		OnBoardingPage condition = PageFactory.initElements(driver, OnBoardingPage.class);
		condition.confirmPasswordFieldCondition();
		String errorConfirmMessage = condition.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PasswordCondition"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(getPropertyValue("PasswordCondition"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			condition.clearConfirmPassword();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ConfirmPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ConfirmPasswordValidation.png");
			condition.clearConfirmPassword();
		}

	}

	@Test(priority = 24)
	private void lowerCaseValidation() throws IOException {
		extentTest = extentReports.createTest("Verify to Check the Confirm Password Lower Case Validation");
		OnBoardingPage lowerCaseValidation = PageFactory.initElements(driver, OnBoardingPage.class);
		lowerCaseValidation.lowercaseValidation();
		String errorConfirmMessage = lowerCaseValidation.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PasswordCondition"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(getPropertyValue("PasswordCondition"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			lowerCaseValidation.clearConfirmPassword();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LowerCaseValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LowerCaseValidation.png");
			lowerCaseValidation.clearConfirmPassword();
		}

	}

	@Test(priority = 25)
	private void mismatchPassword() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error message is displayed when confirm password does not match with the Password Field");
		OnBoardingPage mismatchPassword = PageFactory.initElements(driver, OnBoardingPage.class);
		mismatchPassword.mismatchPasswordValidation();
		String errorConfirmMessage = mismatchPassword.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MisMatchPassword"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(getPropertyValue("MisMatchPassword"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mismatchPassword.clearPassword();
			mismatchPassword.clearConfirmPassword();
			mismatchPassword.validFillData("Password");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MisMatchConfirmPasswordValidation.png");
			mismatchPassword.clearPassword();
			mismatchPassword.clearConfirmPassword();
			mismatchPassword.validFillData("Password");
		}

	}

	@Test(priority = 26)
	public void createTenant() throws IOException {
		extentTest = extentReports.createTest("Creating a New Tenant, the page is redirect into the Dashboard page");
		OnBoardingPage mismatchPassword = PageFactory.initElements(driver, OnBoardingPage.class);
		String errorConfirmMessage = mismatchPassword.dashBoardPage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MisMatchConfirmPasswordValidation.png");
		}

	}

	@Test(priority = 27)
	public void subDomineURL() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Check the Sub Domain URL");
		OnBoardingPage mismatchPassword = PageFactory.initElements(driver, OnBoardingPage.class);
		String actualURL = mismatchPassword.urlGet();
		String expectedURL = mismatchPassword.expectedURL();
		extentTest.log(Status.INFO, "Actual Result is -" + actualURL);
		extentTest.log(Status.INFO, "Expected Result is -" + expectedURL);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualURL.equals(expectedURL)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MisMatchConfirmPasswordValidation.png");
		}

	}

	@Test(priority = 28)
	public void ownerName() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Check the Sub Domain tenant owner profile name");
		OnBoardingPage mismatchPassword = PageFactory.initElements(driver, OnBoardingPage.class);
		String actualOwner = mismatchPassword.getOwnerName();
		String expectedOwner = mismatchPassword.expectedOwnerName();
		extentTest.log(Status.INFO, "Actual Result is -" + actualOwner);
		extentTest.log(Status.INFO, "Expected Result is -" + expectedOwner);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualOwner.equals(expectedOwner)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MisMatchConfirmPasswordValidation.png");
		}

	}

}
