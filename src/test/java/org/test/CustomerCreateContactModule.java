package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerCreateContactModule extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerContactModule.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();

	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}

	@Test(priority = -1) // 1-Login
	public void loginPage() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(loginInPage.getPropertyValue("UserName"));
		loginInPage.passwordField(loginInPage.getPropertyValue("Password"));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + text);
		extentTest.log(Status.INFO,
				"Expected Result Validation Data -" + loginInPage.getPropertyValue("ValidationOfLandingPage"));
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
	private void contactModule() throws InterruptedException {
		extentTest = extentReports.createTest("Navigate to Customer Contact Page");
		CustomerCreateContactPage module = new CustomerCreateContactPage(driver);
		module.modulePage();

	}

//	@Test(priority = 1)
//	private void maxSizeValidationProfileField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Maximum Size of Profile Field Validation");
//		CustomerCreateContactPage maxSizeFile = new CustomerCreateContactPage(driver);
//		maxSizeFile.maxSizeProfile();
//		String maxSizeProfileField = maxSizeFile.maxSizeProfileField();
//		extentTest.log(Status.INFO, "Actual Result for Maximum Size of Profile Field is -" + maxSizeProfileField);
//		extentTest.log(Status.INFO, "Expected Result for Maximum Size of Profile Field is -" + MaxSizeLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (maxSizeProfileField.equals(MaxSizeLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("MaxSizeProfilePic.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("MaxSizeProfilePic.png");
//		}
//
//	}

//	@Test(priority = 2)
//	private void fileFormatValidationProfileField() throws InterruptedException, AWTException, IOException {
//		extentTest = extentReports.createTest("Verify the File Format of Profile Field Validation");
//		CustomerCreateContactPage fileFormat = new CustomerCreateContactPage(driver);
//		fileFormat.fileFormatProfile();
//		String maxSizeProfileField = fileFormat.errorFileFormat();
//		extentTest.log(Status.INFO, "Actual Result for File Format in Profile Field is -" + maxSizeProfileField);
//		extentTest.log(Status.INFO, "Expected Result for File Format in Profile Field is -" + FormatValidationLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (maxSizeProfileField.equals(FormatValidationLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("FileFormatProfileField.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("FileFormatProfileField.png");
//		}
//
//	}

	@Test(priority = 4)
	private void mandatoryValidation() throws AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation in Contact Page");
		CustomerCreateContactPage mandatoryValidation = new CustomerCreateContactPage(driver);
		mandatoryValidation.mandatoryValidation();
		String errorMandatoryValidation = mandatoryValidation.errorMandatoryValidation();
		extentTest.log(Status.INFO, "Actual Result - Mandatory Validation is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO,
				"Expected Result - Mandatory Validation is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MandatoryValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MandatoryValidation.png");
		}
	}

	@Test(priority = 3)
	private void maxValidationFirstNameField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation First Name Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationFirstName();
		String errorFirstName = maxValidation.errorFirstName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation First Name Field is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation First Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFirstNameField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("FirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("FirstNameValidation.png");
			maxValidation.clearFirstNameField();
		}

	}

	@Test(priority = 5)
	private void maxValidationLastNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Last Name Field");
		CustomerCreateContactPage minValidation = new CustomerCreateContactPage(driver);
		minValidation.maxValidationLastName();
		String errorFirstName = minValidation.errorLastNameField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Last Name Field is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Last Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearLastNameField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LastNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LastNameValidation.png");
			minValidation.clearLastNameField();
		}
	}

	@Test(priority = 6)
	private void maxValidationJobTittle() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Job Tittle Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationJobTittle();
		String errorJobTittle = maxValidation.errorJobTittle();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Job Tittle Field is -" + errorJobTittle);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Job Tittle Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorJobTittle.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearJobTittleField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("JobTittleValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("JobTittleValidation.png");
			maxValidation.clearJobTittleField();
		}

	}

	@Test(priority = 7)
	private void maxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Email Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationEmail();
		String errorEmail = maxValidation.errorEmail();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Email Field is -" + errorEmail);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Last Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearEmailField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxEmailValidation.png");
			maxValidation.clearEmailField();
		}

	}

	@Test(priority = 8)
	private void invalidValidationEmailField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Validation InValid Email Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.invalidEmail();
		String errorEmail = maxValidation.errorEmail();
		extentTest.log(Status.INFO, "Actual Result - InValidation for Email Field is -" + errorEmail);
		extentTest.log(Status.INFO,
				"Expected Result - InValidation for Email Field is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearEmailField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InvalidEmailValidation.png");
			maxValidation.clearEmailField();
		}
	}

	@Test(priority = 9)
	private void minValidationPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Minimum Validation Phone Number Field");
		CustomerCreateContactPage minValidation = new CustomerCreateContactPage(driver);
		minValidation.minValidationPhone();
		String errorPhoneNumber = minValidation.errorPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result - Minimum Validation Phone Number Field is -" + errorPhoneNumber);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Phone Number Field is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearPhoneNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearPhoneNumber();
		}

	}

	@Test(priority = 10)
	private void maxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Phone Number Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationPhone();
		String errorPhoneNumber = maxValidation.errorPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Phone Number Field is -" + errorPhoneNumber);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Phone Number Field is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearPhoneNumber();
			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPhoneNumberValidation.png");
			maxValidation.clearPhoneNumber();
			maxValidation.nextButton();
		}

	}

	@Test(priority = 11)
	private void maxValidationPropertyFirstNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property First Name Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationPropertyFirstName();
		String errorPropertyName = maxValidation.errorPropertyFirstName();
		extentTest.log(Status.INFO,
				"Actual Result - Maximum Validation Property First Name Field is -" + errorPropertyName);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Property First Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearPropertyFirstNameField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyFirstNameValidation.png");
			maxValidation.clearPropertyFirstNameField();
		}

	}

	@Test(priority = 12)
	private void maxValidationPropertyLastNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property Last Name Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationPropertyLastName();
		String errorPropertyName = maxValidation.errorPropertyLastName();
		extentTest.log(Status.INFO,
				"Actual Result - Maximum Validation Property Last Name Field is -" + errorPropertyName);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Property Last Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearPropertyLastNameField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyFirstNameValidation.png");
			maxValidation.clearPropertyLastNameField();
		}

	}

	@Test(priority = 13)
	private void maxValidationPropertyNameeField() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property Name Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationPropertyNamee();
		String errorContactPerson = maxValidation.errorContactPerson();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Property Name Field is -" + errorContactPerson);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Property Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactPersonField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyNameValidation.png");
			maxValidation.clearContactPersonField();
		}

	}

	@Test(priority = 14)
	private void maxValidationAddress1() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Address1 Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationAddress11();
		String errorAddress1Field = maxValidation.errorAddress1Field();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Address1 Field is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Address1 Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearAddress1Field();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Address1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Address1Validation.png");
			maxValidation.clearAddress1Field();
		}

	}

	@Test(priority = 15)
	private void maxValidationAddress22Field() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Address2 Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationAddress22();
		String errorAddress1Field = maxValidation.errroAddress2Field();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Address2 Field is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Address2 Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearAddress2Field();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Address2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Address2Validation.png");
			maxValidation.clearAddress2Field();
		}
	}

	@Test(priority = 16)
	private void maxValidationCityField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation City Name Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationCity();
		String errorCityField = maxValidation.errorCityField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation City Name Field is -" + errorCityField);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation City Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearCityField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CityValidation.png");
			maxValidation.clearCityField();
		}

	}

	@Test(priority = 17)
	private void maxValidationStateField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation State Name Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationState();
		String errorStateField = maxValidation.errorStateField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation State Name Field is -" + errorStateField);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation State Name Field is -"
				+ getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorStateField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearStateField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("StateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("StateValidation.png");
			maxValidation.clearStateField();
		}
	}

	@Test(priority = 18)
	private void minValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the Minimum Validation Zip Code Field");
		CustomerCreateContactPage minValidation = new CustomerCreateContactPage(driver);
		minValidation.minValidationZipcode();
		String errorZipcodeField = minValidation.errorZipcodeField();
		extentTest.log(Status.INFO, "Actual Result - Minimum Validation ZipCode Field is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result - Minimum Validation ZipCode Field is -"
				+ getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearZipcodeField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinZipcodeValidation.png");
			minValidation.clearZipcodeField();
		}
	}

	@Test(priority = 19)
	private void maxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Zip Code Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationZipcode();
		String errorZipcodeField = maxValidation.errorZipcodeField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Zipcode Field is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Zipcode Field is -"
				+ getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearZipcodeField();
			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearZipcodeField();
			maxValidation.nextButton();
		}
	}

	@Test(priority = 20)
	private void maxValidationProductNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Product Name Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationProductName();
		String errorProductField = maxValidation.errorProductField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Product Name Field is -" + errorProductField);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Product Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorProductField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearProductField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ProductValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ProductValidation.png");
			maxValidation.clearProductField();
		}
	}

	@Test(priority = 21)
	private void maxValidationBrandNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Brand Name Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationBrandName();
		String errorBrandField = maxValidation.errorBrandField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Brand Name Field is -" + errorBrandField);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Brand Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorBrandField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearBrandField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("BrandValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BrandValidation.png");
			maxValidation.clearBrandField();
		}
	}

	@Test(priority = 22)
	private void maxValdidationModelNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Model Number Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValdidationModelNumber();
		String errorModelField = maxValidation.errorModelField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Model Number Field is -" + errorModelField);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Model Number Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorModelField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearModelField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ModelValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ModelValidation.png");
			maxValidation.clearModelField();
		}
	}

	@Test(priority = 23)
	private void maxValidationSerialNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Serial Number Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationSerialNumber();
		String errorSerialNumberField = maxValidation.errorSerialNumberField();
		extentTest.log(Status.INFO,
				"Actual Result - Maximum Validation Serial Number Field is -" + errorSerialNumberField);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Serial Number Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorSerialNumberField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearSerialNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SerialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SerialValidation.png");
			maxValidation.clearSerialNumber();
		}
	}

	@Test(priority = 24)
	private void maxValidationAccessHoursField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Access Hours Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationAccessHours();
		String errorAccessHours = maxValidation.errorAccessHours();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Access Hours Field is -" + errorAccessHours);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Access Hours Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearAccessHours();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AccessValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AccessValidation.png");
			maxValidation.clearAccessHours();
		}
	}

	@Test(priority = 25)
	private void maxValidationInstallationNotesField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Installation Notes Field");
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationInstallationNotes();
		String errorAccessHours = maxValidation.errorInstallation();
		extentTest.log(Status.INFO,
				"Actual Result - Maximum Validation Installation Notes Field is -" + errorAccessHours);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Installation Notes Field is -"
				+ getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearInstallation();
//			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InstallationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InstallationValidation.png");
			maxValidation.clearInstallation();
//			maxValidation.nextButton();
		}
	}

//	@Test(priority = 26)
//	private void maxSizeValidationAttachmentFileField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Maximum Size Validation Attachment Field");
//		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
//		maxValidation.maxValidationAttachmentFile();
//		String errorAccessHours = maxValidation.errorAttachmentFile();
//		extentTest.log(Status.INFO, "Actual Result - Maximum Size Validation Attachment Field is -" + errorAccessHours);
//		extentTest.log(Status.INFO,
//				"Expected Result - Maximum Size Validation Attachment Field is -" + MaximumFileSize);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorAccessHours.equals(MaximumFileSize)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("MaxAttachmentValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("MaxAttachmentValidation.png");
//
//		}
//	}

//	@Test(priority = 27)
//	private void fileFormatValidationAttachmentField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the File Format Validation Attachment Field");
//		CustomerCreateContactPage fileFormat = new CustomerCreateContactPage(driver);
//		fileFormat.fileFormatValidation();
//		String errorAccessHours = fileFormat.errorAttachmentFile();
//		extentTest.log(Status.INFO, "Actual Result - File Format Validation Attachment Field is -" + errorAccessHours);
//		extentTest.log(Status.INFO, "Expected Result - File Format Validation Attachment Field is -"
//				+ "Only xls, xlsx, docx, ppt, jpg, jpeg, png, pptx, pdf Files Allowed");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorAccessHours.equals("Only xls, xlsx, docx, ppt, jpg, jpeg, png, pptx, pdf Format Allowed")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("FormatValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("FormatValidation.png");
//		}
//
//	}

//	@Test(priority = 28)
//	private void maxLimitAttachmentField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Maximum File Limit Validation Attachment Field");
//		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
//		maxValidation.maxLimitAttachmentField();
//		String errorAccessHours = maxValidation.errorAttachmentFile();
//		extentTest.log(Status.INFO,
//				"Actual Result - Maximum File Limit Validation Attachment Field is -" + errorAccessHours);
//		extentTest.log(Status.INFO, "Expected Result - Maximum File Limit Validation Attachment Field is -"
//				+ "File count size exceeds the maximum limit of 10");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorAccessHours.equals("Maximum upload limit reached")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//			maxValidation.deleteFile();
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("LimitValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("LimitValidation.png");
//			maxValidation.deleteFile();
//
//		}
//
//	}

	@Test(priority = 29)
	private void CreateContact() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Customer Contact Successful Message");
		CustomerCreateContactPage create = new CustomerCreateContactPage(driver);
		create.contactPage();
		create.propertyPage();
		create.equipmentPage();
		String responseMessageCreateContact = create.responseMessageCreateContact();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + responseMessageCreateContact);
		extentTest.log(Status.INFO,
				"Expected Result - Created List Name -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.responseMessageCreateContact();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
			create.responseMessageCreateContact();
		}

	}

	@Test(priority = 30)
	private void alreadyExistedMail() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Already Existed Validation Email Field");
		CustomerCreateContactPage alreadyExisted = new CustomerCreateContactPage(driver);
		alreadyExisted.alreadyExistMail();
		String responseMessageCreateContact = alreadyExisted.errorEmail();
		extentTest.log(Status.INFO,
				"Actual Result - Already Existed Validation Email Field is -" + responseMessageCreateContact);
		extentTest.log(Status.INFO, "Expected Result - Already Existed Validation Email Field is -"
				+ getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			alreadyExisted.nextFunction();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AlreadyEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AlreadyEmailValidation.png");
			alreadyExisted.nextFunction();
		}

	}

	@Test(priority = 31)
	private void characterFilterValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Alphabets Filters in List Validation");
		CustomerCreateContactPage filter = new CustomerCreateContactPage(driver);
		filter.alphabetsFilters();
		String listFirstName = filter.listFirstName();
		extentTest.log(Status.INFO, "Actual Result - Alphabets Filter List is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result - Alphabets Filter List is -" + listFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AlphaValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AlphaValidation.png");
		}

	}

	@Test(priority = 32)
	private void searchListNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Contact Name Search Filter in List Validation");
		CustomerCreateContactPage search = new CustomerCreateContactPage(driver);
		search.searchListName();
		String listFirstName = search.listFirstName();
		extentTest.log(Status.INFO,
				"Actual Result - Search Filter Validate Data in List Validation is -" + listFirstName);
		extentTest.log(Status.INFO,
				"Expected Result - Search Filter Validate Data in List Validation is -" + listFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			search.clearSearch();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SearchValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SearchValidation.png");
			search.clearSearch();
		}

	}

	@Test(priority = 33)
	private void searchListPhoneNumberField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Phone Number Search Filter in List Validation");
		CustomerCreateContactPage search = new CustomerCreateContactPage(driver);
		String listPhoneNumber = search.listPhoneNumber();
		String listFirstName = search.listDataPhoneNumber();
		extentTest.log(Status.INFO,
				"Actual Result - Search Filter Validate Phone Number Data in List Validation is -" + listFirstName);
		extentTest.log(Status.INFO,
				"Expected Result - Search Filter Validate Phone Number Data in List Validation is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			search.clearSearch();
			search.resetOption();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SearchPhoneValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SearchPhoneValidation.png");
			search.clearSearch();
			search.resetOption();
		}

	}

	@Test(priority = 34)
	private void searchListEmailField() throws IOException {
		extentTest = extentReports.createTest("Verify the Email Search Filter in List Validation");
		CustomerCreateContactPage search = new CustomerCreateContactPage(driver);
		String listPhoneNumber = search.listEmail();
		String listFirstName = search.listDataEmail();
		extentTest.log(Status.INFO,
				"Actual Result - Search Filter Validate Email Data in List Validation is -" + listFirstName);
		extentTest.log(Status.INFO,
				"Expected Result - Search Filter Validate Email Data in List Validation is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			search.clearSearch();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SearchEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SearchEmailValidation.png");
			search.clearSearch();
		}

	}

	@Test(priority = 35)
	private void filterField() throws IOException {
		extentTest = extentReports.createTest("Verify the Filter Filed in List Validation");
		CustomerCreateContactPage search = new CustomerCreateContactPage(driver);
		search.filterList();
		String listFirstName = search.listFirstName();
		extentTest.log(Status.INFO, "Actual Result - Filter Field List Validation is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result - Search Filter Filed List Validation is -" + listFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			search.clearSearch();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("FilterValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("FilterValidation.png");
			search.clearSearch();
		}

	}

	@Test(priority = 36)
	private void searchInvalidListName() throws IOException {
		extentTest = extentReports.createTest("Verify the Search Filter Invalid Data in List Validation");
		CustomerCreateContactPage invalid = new CustomerCreateContactPage(driver);
		invalid.searchInvalidListName();
		String listFirstName = invalid.errorList();
		extentTest.log(Status.INFO,
				"Actual Result - Search Filter Invalid Data in List Validation is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result - Search Filter Invalid Data in List Validation is -"
				+ getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			invalid.resetOption();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InvalidationData.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InvalidationData.png");
			invalid.resetOption();
		}
	}

	@Test(priority = 37)
	private void editContactList() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Edit Contact Details");
		CustomerCreateContactPage edit = new CustomerCreateContactPage(driver);
		edit.editContact();
		String responseMessageCreateContact = edit.updateMessage();
		extentTest.log(Status.INFO, "Actual Result - Edit Contact Details -" + responseMessageCreateContact);
		extentTest.log(Status.INFO,
				"Expected Result - Edit Contact Details -" + getPropertyValue("CustomerUpdatedMesssage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact.equals(getPropertyValue("CustomerUpdatedMesssage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Edit.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Edit.png");
		}

	}

	@Test(priority = 39)
	private void deleteContactList() throws IOException {
		extentTest = extentReports.createTest("Verify the Delete Contact List");
		CustomerCreateContactPage edit = new CustomerCreateContactPage(driver);
		edit.deleteContact();
		String responseMessageCreateContact = edit.deleteMessage();
		extentTest.log(Status.INFO, "Actual Result - Delete Response Message -" + responseMessageCreateContact);
		extentTest.log(Status.INFO,
				"Expected Result - Delete Response Message -" + getPropertyValue("CustomerDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact.equals(getPropertyValue("CustomerDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Delete.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Delete.png");
		}

	}

}
