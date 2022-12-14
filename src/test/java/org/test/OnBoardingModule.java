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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.utility.BrowserSetup;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OnBoardingModule extends BaseClass {

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

////	@Test(priority = 0)
//	public void LaunchingOnBoarding() throws MalformedURLException, IOException {
//		extentTest = extentReports.createTest("Verify the OnBoarding URL Response Code Validation");
//		OnBoardingPage boardingPage = new OnBoardingPage(driver);
//		boardingPage.login();
//		int responseCode = boardingPage.responseCode();
//		extentTest.log(Status.INFO, "Actual Result Validation Data -" + responseCode);
//		extentTest.log(Status.INFO, "Expected Result Validation Data -" + "200");
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

	@Test(priority = 1)
	public void mandatoryBussinessNameFieldValidation() throws IOException {
		extentTest = extentReports.createTest("Check the Business Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		OnBoardingPage mandatory = new OnBoardingPage(driver);
		mandatory.emailText();
		mandatory.mandatoryValidation();
		String manditoryValidations = mandatory.manditoryValidations();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + manditoryValidations);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("MandatoryErrorMessage"));
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
	public void mandatoryEmailFieldValidation() throws IOException {
		extentTest = extentReports.createTest("Check the Email field is set as Mandatory & Error Message is displayed when it is BLANK");
		OnBoardingPage mandatory = new OnBoardingPage(driver);
		mandatory.mandatoryEmailValidation();
		String manditoryValidations = mandatory.manditoryValidations();
		extentTest.log(Status.INFO, "Actual Result Validation Data - " + manditoryValidations);
		extentTest.log(Status.INFO, "Expected Result Validation Data - " + getPropertyValue("MandatoryErrorMessage"));
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
	

	@Test(priority = 3)
	private void alreadyBussinessNameValidation() throws IOException {
		extentTest = extentReports.createTest("Verify [Business Name Already Exists] Error is dispalyed when already existing Bussiness Name is provided");
		OnBoardingPage alreadyBussiness = new OnBoardingPage(driver);
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

	@Test(priority = 4)
	public void maximumValidationBussinessName() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Bussiness Name Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationBussinessName();
		String errorMessageBussinessName = maxValidation.errorMessageBussinessName();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorMessageBussinessName);
		extentTest.log(Status.INFO,
				"Expected Result Validation Data -" + getPropertyValue("Max256CharacterValidation"));
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
		extentTest = extentReports.createTest("Verify the Maximum Validation in Bussiness Website Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationBussinessWebSite();
		String errorWebsite = maxValidation.errorWebsite();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorWebsite);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("Max2048Validation"));
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
		extentTest = extentReports.createTest("Verify the Maximum Validation in First Name Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationFirstName();
		String errorFirstName = maxValidation.errorFirstName();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorFirstName);
		extentTest.log(Status.INFO,
				"Expected Result Validation Data -" + getPropertyValue("Max256CharacterValidation"));
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
		extentTest = extentReports.createTest("Verify the Maximum Validation in Last Name Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationLastName();
		String errorLastName = maxValidation.errorLastName();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorLastName);
		extentTest.log(Status.INFO,
				"Expected Result Validation Data -" + getPropertyValue("Max256CharacterValidation"));
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
		extentTest = extentReports.createTest("Verify the Maximum Validation in Email Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationEmail();
		String errorEmail = maxValidation.errorEmail();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorEmail);
		extentTest.log(Status.INFO,
				"Expected Result Validation Data -" + getPropertyValue("Max256CharacterValidation"));
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
		extentTest = extentReports.createTest("Verify the Valid Email Field");
		OnBoardingPage validEmail = new OnBoardingPage(driver);
		validEmail.validationEmail();
		String errorEmail = validEmail.errorEmail();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("ValidEmail"));
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
		extentTest = extentReports.createTest("Verify the Already Existed Email Field");
		OnBoardingPage existing = new OnBoardingPage(driver);
		existing.alreadyExistValidation();
		String errorEmail = existing.errorEmail();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("AlreadyExistedEmail"));
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
		extentTest = extentReports.createTest("Verify the User is Landing on Industry Page");
		OnBoardingPage validation = new OnBoardingPage(driver);
		validation.validEmail();
		String messageLandingPage = validation.messageLandingPage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + messageLandingPage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + "Provide your Industry type");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (messageLandingPage.equals("Provide your Industry type")) {
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

	@Test(priority = 12)
	private void validateRadioButtonClickable() {
		extentTest = extentReports.createTest("Verify the Industry Radio Button is Clickable");
		OnBoardingPage radioButton = new OnBoardingPage(driver);
		radioButton.industryRadioButton();

	}

	@Test(priority = 13)
	private void industryTypeFieldPresent() throws IOException {
		extentTest = extentReports.createTest("Verify the Mention your industry information is Present in the Page");
		OnBoardingPage fieldPresent = new OnBoardingPage(driver);
		fieldPresent.industryTypeFieldPresent();
		String messagePresent = fieldPresent.messagePresent();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + messagePresent);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + "Mention your industry information");
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
		extentTest = extentReports.createTest("Verify the Maximum Validation in Industry Field");
		OnBoardingPage industryValidation = new OnBoardingPage(driver);
		industryValidation.maximumValidationIndustryField();
		String errorIndustryField = industryValidation.errorIndustryField();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorIndustryField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("IndustryMaxValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorIndustryField.equals(getPropertyValue("IndustryMaxValidation"))) {
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
		OnBoardingPage sizeCompany = new OnBoardingPage(driver);
		sizeCompany.sizeCompany();

	}

	@Test(priority = 16)
	private void validationRadioButtonIntrested() throws IOException {
		extentTest = extentReports.createTest("Verify the User can Choose Intrest");
		OnBoardingPage radioButton = new OnBoardingPage(driver);
		radioButton.radioButtonCurrent();

	}
	@Test(priority = 17)
	private void mandatoryValidationLocation() throws IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation in Location Field");
		OnBoardingPage mandatory = new OnBoardingPage(driver);
		mandatory.mandatoryLocationValidation();
		String errorPasswordField = mandatory.requiredFieldLocation();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("MandatoryErrorMessage"));
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
		extentTest = extentReports.createTest("Verify the Maximum Validation in Location Field");
		OnBoardingPage mandatory = new OnBoardingPage(driver);
		mandatory.maximumValidationLocation();
		String errorPasswordField = mandatory.requiredFieldLocation();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("Max256CharacterValidation"));
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
		extentTest = extentReports.createTest("Verify the Mandatory Validation in Password Field");
		OnBoardingPage mandatory = new OnBoardingPage(driver);
		mandatory.passwordFieldMandatory();
		String errorPasswordField = mandatory.errorPasswordField();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("MandatoryErrorMessage"));
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
		extentTest = extentReports.createTest("Verify the Minimum Validation in Password Field");
		OnBoardingPage minValidation = new OnBoardingPage(driver);
		minValidation.minimumValidationPassword();
		String errorMinPassword = minValidation.errorMinPassword();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorMinPassword);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("Min8ValidationPassword"));
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
		extentTest = extentReports.createTest("Verify the Minimum Validation in Confirm Password Field");
		OnBoardingPage minValidation = new OnBoardingPage(driver);
		minValidation.minimumValidationConfirmPassword();
		String errorConfirmMessage = minValidation.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("Min8ValidationPassword"));
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
		extentTest = extentReports.createTest("Verify to Check the Password Condition Validation");
		OnBoardingPage condition = new OnBoardingPage(driver);
		condition.passwordFieldCondition();
		String errorPasswordField = condition.errorPasswordField();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("PasswordCondition"));
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
		extentTest = extentReports.createTest("Verify to Check the Confirm Password Condition Validation");
		OnBoardingPage condition = new OnBoardingPage(driver);
		condition.confirmPasswordFieldCondition();
		String errorConfirmMessage = condition.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("PasswordCondition"));
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
		OnBoardingPage lowerCaseValidation = new OnBoardingPage(driver);
		lowerCaseValidation.lowercaseValidation();
		String errorConfirmMessage = lowerCaseValidation.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("PasswordCondition"));
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
		extentTest = extentReports.createTest("Verify to Check the Mismatch in Confirm Password Field Validation");
		OnBoardingPage mismatchPassword = new OnBoardingPage(driver);
		mismatchPassword.mismatchPasswordValidation();
		String errorConfirmMessage = mismatchPassword.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("MisMatchPassword"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(getPropertyValue("MisMatchPassword"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mismatchPassword.clearConfirmPassword();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MisMatchConfirmPasswordValidation.png");
			mismatchPassword.clearConfirmPassword();
		}

	}

//	@Test(priority = 23)
	public void createTenant() {

	}

}

