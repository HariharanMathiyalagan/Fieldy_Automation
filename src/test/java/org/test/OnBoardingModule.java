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
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.utility.BrowserSetup;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OnBoardingModule {

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
	public void mandatoryFieldValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation in OnBoarding Page");
		OnBoardingPage mandatory = new OnBoardingPage(driver);
		mandatory.mandatoryValidation();
		String manditoryValidations = mandatory.manditoryValidations();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + manditoryValidations);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + MandatoryErrorMessage);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (manditoryValidations.equals(MandatoryErrorMessage)) {
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
		extentTest = extentReports.createTest("Verify the Already Existed Validation in Bussiness Name Field");
		OnBoardingPage alreadyBussiness = new OnBoardingPage(driver);
		alreadyBussiness.alreadyBussinessName();
		String errorMessageBussinessName = alreadyBussiness.errorMessageBussinessName();
		extentTest.log(Status.INFO, "Actual Result Already Exited Data -" + errorMessageBussinessName);
		extentTest.log(Status.INFO, "Expected Result Already Existed Data -" + BussinessNameAlready);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMessageBussinessName.equals(BussinessNameAlready)) {
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
	public void maximumValidationBussinessName() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Bussiness Name Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationBussinessName();
		String errorMessageBussinessName = maxValidation.errorMessageBussinessName();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorMessageBussinessName);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + Max256CharacterValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMessageBussinessName.equals(Max256CharacterValidation)) {
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

	@Test(priority = 4)
	private void maximumValidationBussinessWebSite() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Bussiness Website Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationBussinessWebSite();
		String errorWebsite = maxValidation.errorWebsite();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorWebsite);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + Max2048Validation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorWebsite.equals(Max2048Validation)) {
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

	@Test(priority = 5)
	private void maximumValidationFirstName() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in First Name Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationFirstName();
		String errorFirstName = maxValidation.errorFirstName();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + Max256CharacterValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(Max256CharacterValidation)) {
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

	@Test(priority = 6)
	private void maximumValidationLastName() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Last Name Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationLastName();
		String errorLastName = maxValidation.errorLastName();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorLastName);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + Max256CharacterValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorLastName.equals(Max256CharacterValidation)) {
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

	@Test(priority = 7)
	private void maximumValidationEmail() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Email Field");
		OnBoardingPage maxValidation = new OnBoardingPage(driver);
		maxValidation.maximumValidationEmail();
		String errorEmail = maxValidation.errorEmail();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + Max256CharacterValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(Max256CharacterValidation)) {
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

	@Test(priority = 8)
	private void validateEmailFormat() throws IOException {
		extentTest = extentReports.createTest("Verify the Valid Email Field");
		OnBoardingPage validEmail = new OnBoardingPage(driver);
		validEmail.validationEmail();
		String errorEmail = validEmail.errorEmail();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + ValidEmail);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(ValidEmail)) {
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

	@Test(priority = 9)
	private void existingEmailValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Already Existed Email Field");
		OnBoardingPage existing = new OnBoardingPage(driver);
		existing.alreadyExistValidation();
		String errorEmail = existing.errorEmail();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + AlreadyExistedEmail);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(AlreadyExistedEmail)) {
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

	@Test(priority = 10)
	private void correctValidationEmail() throws IOException {
		extentTest = extentReports.createTest("Verify the User is Landing on Industry Page");
		OnBoardingPage validation = new OnBoardingPage(driver);
		validation.validEmail();
		String messageLandingPage = validation.messageLandingPage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + messageLandingPage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + "Provide your Industry type");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (messageLandingPage.equals("What Industry are you in?")) {
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

	@Test(priority = 11)
	private void validateRadioButtonClickable() {
		extentTest = extentReports.createTest("Verify the Industry Radio Button is Clickable");
		OnBoardingPage radioButton = new OnBoardingPage(driver);
		radioButton.industryRadioButton();

	}

	@Test(priority = 12)
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

	@Test(priority = 13)
	private void maximumValidationIndustryField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Industry Field");
		OnBoardingPage industryValidation = new OnBoardingPage(driver);
		industryValidation.maximumValidationIndustryField();
		String errorIndustryField = industryValidation.errorIndustryField();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorIndustryField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + IndustryMaxValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorIndustryField.equals(IndustryMaxValidation)) {
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

	@Test(priority = 14)
	private void sizeCompanyValidation() {
		extentTest = extentReports.createTest("Verify Available Size of the Company is Present");
		OnBoardingPage sizeCompany = new OnBoardingPage(driver);
		sizeCompany.sizeCompany();

	}

	@Test(priority = 15)
	private void validationRadioButtonIntrested() throws IOException {
		extentTest = extentReports.createTest("Verify the User can Choose Intrest");
		OnBoardingPage radioButton = new OnBoardingPage(driver);
		radioButton.radioButtonCurrent();

	}

	@Test(priority = 16)
	private void passwordMandatoryField() throws IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation in Password Field");
		OnBoardingPage mandatory = new OnBoardingPage(driver);
		mandatory.passwordFieldMandatory();
		String errorPasswordField = mandatory.errorPasswordField();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + MandatoryErrorMessage);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(MandatoryErrorMessage)) {
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

	@Test(priority = 17)
	private void minValidationPassword() throws IOException {
		extentTest = extentReports.createTest("Verify the Minimum Validation in Password Field");
		OnBoardingPage minValidation = new OnBoardingPage(driver);
		minValidation.minimumValidationPassword();
		String errorMinPassword = minValidation.errorMinPassword();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorMinPassword);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + MinimumValidatioPassword);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMinPassword.equals(MinimumValidatioPassword)) {
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

	@Test(priority = 18)
	private void minValidationConfirmPassword() throws IOException {
		extentTest = extentReports.createTest("Verify the Minimum Validation in Confirm Password Field");
		OnBoardingPage minValidation = new OnBoardingPage(driver);
		minValidation.minimumValidationConfirmPassword();
		String errorConfirmMessage = minValidation.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + MinimumValidatioPassword);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(MinimumValidatioPassword)) {
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

	@Test(priority = 19)
	private void passwordFieldConditions() throws IOException {
		extentTest = extentReports.createTest("Verify to Check the Password Condition Validation");
		OnBoardingPage condition = new OnBoardingPage(driver);
		condition.passwordFieldCondition();
		String errorPasswordField = condition.errorPasswordField();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + PasswordCondition);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(PasswordCondition)) {
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

	@Test(priority = 20)
	private void confirmPasswordFieldCondition() throws IOException {
		extentTest = extentReports.createTest("Verify to Check the Confirm Password Condition Validation");
		OnBoardingPage condition = new OnBoardingPage(driver);
		condition.confirmPasswordFieldCondition();
		String errorConfirmMessage = condition.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + PasswordCondition);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(PasswordCondition)) {
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

	@Test(priority = 21)
	private void lowerCaseValidation() throws IOException {
		extentTest = extentReports.createTest("Verify to Check the Confirm Password Lower Case Validation");
		OnBoardingPage lowerCaseValidation = new OnBoardingPage(driver);
		lowerCaseValidation.lowercaseValidation();
		String errorConfirmMessage = lowerCaseValidation.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + PasswordCondition);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(PasswordCondition)) {
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

	@Test(priority = 22)
	private void mismatchPassword() throws IOException {
		extentTest = extentReports.createTest("Verify to Check the Mismatch in Confirm Password Field Validation");
		OnBoardingPage mismatchPassword = new OnBoardingPage(driver);
		mismatchPassword.mismatchPasswordValidation();
		String errorConfirmMessage = mismatchPassword.errorConfirmMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + MisMatchPassword);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(MisMatchPassword)) {
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

}
