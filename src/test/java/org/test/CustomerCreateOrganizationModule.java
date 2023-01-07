package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.CustomerCreateOrganizationPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

import net.bytebuddy.implementation.bind.annotation.BindingPriority;

public class CustomerCreateOrganizationModule extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerOrganizationModule.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();
	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}

	@Test(priority = 1) // 1-Login
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
			File file = new File("OrganizationLogin.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrganizationLogin.png");
		}
	}

	@Test(priority = 2)
	private void modulePage() throws InterruptedException, AWTException {
		extentTest = extentReports.createTest("Navigate to Customer Organization Page");
		CustomerCreateOrganizationPage modulePage = new CustomerCreateOrganizationPage(driver);
		modulePage.modulePage();

	}

//	@Test(priority = 2)
//	public void maxSizeValidationLogoField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Maximum Size of Profile Field Validation");
//		CustomerCreateOrganizationPage logoSizeValidation = new CustomerCreateOrganizationPage(driver);
//		logoSizeValidation.maxSizeLogoValidation();
//		String errorLogo = logoSizeValidation.errorLogo();
//		extentTest.log(Status.INFO, "Actual Result for Maximum Size of Profile Field is -" + errorLogo);
//		extentTest.log(Status.INFO, "Expected Result for Maximum Size of Profile Field is -" + MaxSizeLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorLogo.equals(MaxSizeLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("OrganizationMaxSizeProfilePic.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("OrganizationMaxSizeProfilePic.png");
//		}
//
//	}
//
//	@Test(priority = 1)
//	private void formatLogoValidation() throws InterruptedException, AWTException, IOException {
//		extentTest = extentReports.createTest("Verify the File Format of Profile Field Validation");
//		CustomerCreateOrganizationPage formatLogoValidation = new CustomerCreateOrganizationPage(driver);
//		formatLogoValidation.formatLogoValidation();
//		String maxSizeProfileField = formatLogoValidation.formatlogoError();
//		extentTest.log(Status.INFO, "Actual Result for File Format in Profile Field is -" + maxSizeProfileField);
//		extentTest.log(Status.INFO, "Expected Result for File Format in Profile Field is -" + FormatValidationLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (maxSizeProfileField.equals(FormatValidationLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("OrganizationFileFormatProfileField.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("OrganizationFileFormatProfileField.png");
//		}
//
//	}

	@Test(priority = 3)
	private void mandatoryValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation in Organization Page");
		CustomerCreateOrganizationPage mandatoryValidation = new CustomerCreateOrganizationPage(driver);
		mandatoryValidation.mandatoryValidation();
		String errorMandatory = mandatoryValidation.errorMandatory();
		extentTest.log(Status.INFO, "Actual Result - Mandatory Validation is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result - Mandatory Validation is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatoryValidation.input();		
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MandatoryValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MandatoryValidation.png");
			mandatoryValidation.input();		
		}

	}

	@Test(priority = 4)
	private void maxValidationOrganizationField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Organization Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationOrganization();
		String errorMandatory = maxValidation.errorMandatory();
		extentTest.log(Status.INFO, "Actual Result - Mandatory Validation is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result - Mandatory Validation is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearOrganization();
			maxValidation.input();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxOrganizationName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxOrganizationName.png");
			maxValidation.clearOrganization();
			maxValidation.input();
		}

	}

	@Test(priority = 5)
	private void maxValidationWebsiteField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Website Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationWebsite();
		String errorMandatory = maxValidation.errorWebsite();
		extentTest.log(Status.INFO, "Actual Result - Mandatory Validation is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result - Mandatory Validation is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearWebsite();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxWebsite.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxWebsite.png");
			maxValidation.clearWebsite();
		}

	}

	@Test(priority = 6)
	private void maxValidationAddress1Field() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Address1 Field");
		CustomerCreateOrganizationPage address1Validation = new CustomerCreateOrganizationPage(driver);
		address1Validation.maxValidationAddress1Field();
		String errorAddress1Field = address1Validation.errorAddress1Field();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Address1 Field is -" + errorAddress1Field);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Address1 Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			address1Validation.clearAddress1Field();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAddress1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAddress1Validation.png");
			address1Validation.clearAddress1Field();
		}

	}

	@Test(priority = 7)
	private void maxValidationAddress2Field() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Address2 Field");
		CustomerCreateOrganizationPage address2Validation = new CustomerCreateOrganizationPage(driver);
		address2Validation.maxValidationAddress2Field();
		String errorAddress1Field = address2Validation.errorAddress2Field();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Address1 Field is -" + errorAddress1Field);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Address1 Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			address2Validation.clearAddress2Field();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAddress2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAddress2Validation.png");
			address2Validation.clearAddress2Field();
		}

	}

	@Test(priority = 8)
	private void maxValidationCityField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation City Name Field");
		CustomerCreateOrganizationPage cityValidation = new CustomerCreateOrganizationPage(driver);
		cityValidation.maxValidationCityField();
		String errorAddress1Field = cityValidation.errorCityField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Address1 Field is -" + errorAddress1Field);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Address1 Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			cityValidation.clearCityField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCityValidation.png");
			cityValidation.clearCityField();
		}

	}

	@Test(priority = 9)
	private void maxValidationStateField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation State Name Field");
		CustomerCreateOrganizationPage stateValidation = new CustomerCreateOrganizationPage(driver);
		stateValidation.maxValidationStateField();
		String errorStateField = stateValidation.errorStateField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation State Name Field is -" + errorStateField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation State Name Field is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorStateField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			stateValidation.clearStateField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgStateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgStateValidation.png");
			stateValidation.clearStateField();
		}
	}

	@Test(priority = 10)
	private void minValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the Minimum Validation Zip Code Field");
		CustomerCreateOrganizationPage minValidation = new CustomerCreateOrganizationPage(driver);
		minValidation.minValidationZipcodeField();
		String errorZipcodeField = minValidation.errorZipcodeField();
		extentTest.log(Status.INFO, "Actual Result - Minimum Validation ZipCode Field is -" + errorZipcodeField);
		extentTest.log(Status.INFO,
				"Expected Result - Minimum Validation ZipCode Field is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearZipcodeField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinZipcodeValidation.png");
			minValidation.clearZipcodeField();
		}

	}

	@Test(priority = 11)
	private void maxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Zip Code Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationZipcodeField();
		String errorZipcodeField = maxValidation.errorZipcodeField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Zipcode Field is -" + errorZipcodeField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Zipcode Field is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearZipcodeField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxZipcodeValidation.png");
			maxValidation.clearZipcodeField();
		}
	}

	@Test(priority = 12)
	private void emailValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Validation InValid Email Field");
		CustomerCreateOrganizationPage emailValidation = new CustomerCreateOrganizationPage(driver);
		emailValidation.validEmailField();
		String errorEmail = emailValidation.errorEmail();
		extentTest.log(Status.INFO, "Actual Result - InValidation for Email Field is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result - InValidation for Email Field is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			emailValidation.clearEmailField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInvalidEmailValidation.png");
			emailValidation.clearEmailField();
		}
	}

	@Test(priority = 13)
	private void maxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Email Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationEmailField();
		String errorEmail = maxValidation.errorEmail();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Email Field is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Last Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearEmailField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxEmailValidation.png");
			maxValidation.clearEmailField();
		}
	}

	@Test(priority = 14)
	private void minValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Minimum Validation Phone Number Field");
		CustomerCreateOrganizationPage minValidation = new CustomerCreateOrganizationPage(driver);
		minValidation.minValidationPhoneNumberField();
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
			File file = new File("OrgMinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinPhoneNumberValidation.png");
			minValidation.clearPhoneNumber();
		}

	}

	@Test(priority = 15)
	private void maxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Phone Number Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPhoneNumberField();
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
			File file = new File("OrgMaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxPhoneNumberValidation.png");
			maxValidation.clearPhoneNumber();
			maxValidation.nextButton();
		}

	}

	@Test(priority = 16)
	private void maxValidationContactFirstNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation First Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationFirstName();
		String errorFirstName = maxValidation.errorFirstName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation First Name Field is -" + errorFirstName);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation First Name Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFirstName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxFirstNameValidation.png");
			maxValidation.clearFirstName();
		}
	}

	@Test(priority = 17)
	private void maxValidationContactLastNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Last Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationLastName();
		String errorFirstName = maxValidation.errorLastNameField();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Last Name Field is -" + errorFirstName);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Last Name Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearLastNameField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LastNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LastNameValidation.png");
			maxValidation.clearLastNameField();
		}
	}

	@Test(priority = 18)
	private void maxValidationContactEmailField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Contact Email Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationContactEmail();
		String errorEmail = maxValidation.errorContactEmail();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Email Field is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation Last Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxEmailValidation.png");
			maxValidation.clearContactEmail();
		}
	}

	@Test(priority = 19)
	private void validateContactEmailField() throws IOException {
		extentTest = extentReports.createTest("Verify the Validation InValid Email Field");
		CustomerCreateOrganizationPage validateEmail = new CustomerCreateOrganizationPage(driver);
		validateEmail.validateContactEmail();
		String errorEmail = validateEmail.errorContactEmail();
		extentTest.log(Status.INFO, "Actual Result - InValidation for Email Field is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result - InValidation for Email Field is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validateEmail.clearContactEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInvalidEmailValidation.png");
			validateEmail.clearContactEmail();
		}
	}

	@Test(priority = 20)
	private void minValidationContactPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Minimum Validation Phone Number Field");
		CustomerCreateOrganizationPage minValidation = new CustomerCreateOrganizationPage(driver);
		minValidation.minValidationContactPhoneNumber();
		String errorPhoneNumber = minValidation.errorContactPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result - Minimum Validation Phone Number Field is -" + errorPhoneNumber);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Phone Number Field is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearContactPhoneNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinPhoneNumberValidation.png");
			minValidation.clearContactPhoneNumber();
		}
	}

	@Test(priority = 21)
	private void maxValidationContactPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Contact Phone Number Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationContactPhoneNumber();
		String errorPhoneNumber = maxValidation.errorContactPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Contact Phone Number Field is -" + errorPhoneNumber);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Contact Phone Number Field is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactPhoneNumber();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPhoneNumberValidation.png");
			maxValidation.clearContactPhoneNumber();

		}
	}

	@Test(priority = 22)
	private void maxValidationJobTittleField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Job Tittle Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationJobTittle();
		String errorJobTittle = maxValidation.errorContactJobTittle();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Job Tittle Field is -" + errorJobTittle);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Job Tittle Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorJobTittle.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactJobTittle();
			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxJobTittleValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxJobTittleValidation.png");
			maxValidation.clearContactJobTittle();
			maxValidation.nextButton();
		}

	}

	@Test(priority = 23)
	private void maxValidationPropertyNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPropertyName();
		String errorPropertyName = maxValidation.errorContactPropertyName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Property Name Field is -" + errorPropertyName);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Property Name Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactPropertyName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyNameValidation.png");
			maxValidation.clearContactPropertyName();
		}

	}

	@Test(priority = 24)
	private void maxValidationPropertyFirstNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property First Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPropertyFirstNamee();
		String errorContactPerson = maxValidation.errorPropertyFirstName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation First Name Field is -" + errorContactPerson);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation First Name Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearPropertyFirstName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyFirstNameValidation.png");
			maxValidation.clearPropertyFirstName();
		}

	}

	@Test(priority = 25)
	private void maxValidationPropertyLastNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property Last Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPropertyLastNamee();
		String errorContactPerson = maxValidation.errorPropertyLastName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Last Name Field is -" + errorContactPerson);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Last Name Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearPropertyLastName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyFirstNameValidation.png");
			maxValidation.clearPropertyLastName();
		}

	}

	@Test(priority = 26)
	private void maxValidationPropertyAddress1Field() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property Address1 Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPropertyAddress1();
		String errorAddress1Field = maxValidation.errorContactPropertyAddress1();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Address1 Field is -" + errorAddress1Field);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Address1 Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactPropertyAddress1();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxAddress1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxAddress1Validation.png");
			maxValidation.clearContactPropertyAddress1();
		}

	}

	@Test(priority = 27)
	private void maxValidationPropertyAddress2Field() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Property Validation Address2 Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPropertyAddress2();
		String errorAddress1Field = maxValidation.errorContactPropertyAddress2();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Address2 Field is -" + errorAddress1Field);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Address2 Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactPropertyAddress2();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxAddress2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxAddress2Validation.png");
			maxValidation.clearContactPropertyAddress2();
		}
	}

	@Test(priority = 28)
	private void maxValidationPropertyStateField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property State Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPropertyStateName();
		String errorCityField = maxValidation.errorContactPropertyStateName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation State Name Field is -" + errorCityField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation State Name Field is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactPropertyStateName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgStateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgStateValidation.png");
			maxValidation.clearContactPropertyStateName();
		}

	}

	@Test(priority = 29)
	private void maxValidationPropertyCityField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property City Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPropertyCityName();
		String errorCityField = maxValidation.errorContactPropertyCityName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation City Name Field is -" + errorCityField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation City Name Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactPropertyCityName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCityValidation.png");
			maxValidation.clearContactPropertyCityName();
		}

	}

	@Test(priority = 30)
	private void minValidationPropertyZipCodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the Minimum Validation Property Zip Code Field");
		CustomerCreateOrganizationPage minValidation = new CustomerCreateOrganizationPage(driver);
		minValidation.minValidationPropertyZipcode();
		String errorZipcodeField = minValidation.errorContactPropertyZipcode();
		extentTest.log(Status.INFO,
				"Actual Result - Minimum Validation Property ZipCode Field is -" + errorZipcodeField);
		extentTest.log(Status.INFO,
				"Expected Result - Minimum Validation Property ZipCode Field is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearContactPropertyZipcode();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinZipcodeValidation.png");
			minValidation.clearContactPropertyZipcode();

		}
	}

	@Test(priority = 31)
	private void maxValidationPropertyZipCodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Property Zip Code Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationPropertyZipcode();
		String errorZipcodeField = maxValidation.errorContactPropertyZipcode();
		extentTest.log(Status.INFO,
				"Actual Result - Maximum Validation Property Zipcode Field is -" + errorZipcodeField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Property Zipcode Field is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearContactPropertyZipcode();
			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxZipcodeValidation.png");
			maxValidation.clearContactPropertyZipcode();
			maxValidation.nextButton();
		}
	}

	@Test(priority = 32)
	private void maxValidationProductNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Product Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationProductName();
		String errorProductField = maxValidation.errorProductName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Product Name Field is -" + errorProductField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Product Name Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorProductField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearProductName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgProductValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgProductValidation.png");
			maxValidation.clearProductName();
		}
	}

	@Test(priority = 33)
	private void maxValidationBrandNameField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Brand Name Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationBrandName();
		String errorBrandField = maxValidation.errorBrandName();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Brand Name Field is -" + errorBrandField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Brand Name Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorBrandField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearBrandName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgBrandValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgBrandValidation.png");
			maxValidation.clearBrandName();
		}
	}

	@Test(priority = 34)
	private void maxValidationModelNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Model Number Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationModelNumber();
		String errorModelField = maxValidation.errorModelNumber();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Model Number Field is -" + errorModelField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Model Number Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorModelField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearModelNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgModelValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgModelValidation.png");
			maxValidation.clearModelNumber();
		}

	}

	@Test(priority = 35)
	private void maxValidationSerialNumberField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Serial Number Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationSerialNumber();
		String errorSerialNumberField = maxValidation.errorSerialNumber();
		extentTest.log(Status.INFO,
				"Actual Result - Maximum Validation Serial Number Field is -" + errorSerialNumberField);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Serial Number Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorSerialNumberField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearSerialNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSerialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSerialValidation.png");
			maxValidation.clearSerialNumber();
		}
	}

	@Test(priority = 36)
	private void maxValidationAccessHoursField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Access Hours Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationAccessHours();
		String errorAccessHours = maxValidation.errorAccessHours();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation Access Hours Field is -" + errorAccessHours);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Access Hours Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearAccessHours();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAccessValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAccessValidation.png");
			maxValidation.clearAccessHours();
		}

	}

	@Test(priority = 37)
	private void maxValidationInstallationNotesField() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation Installation Notes Field");
		CustomerCreateOrganizationPage maxValidation = new CustomerCreateOrganizationPage(driver);
		maxValidation.maxValidationInstallationNotes();
		String errorAccessHours = maxValidation.errorInstallationNotes();
		extentTest.log(Status.INFO,
				"Actual Result - Maximum Validation Installation Notes Field is -" + errorAccessHours);
		extentTest.log(Status.INFO,
				"Expected Result - Maximum Validation Installation Notes Field is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearInstallationNotes();
			maxValidation.previousButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInstallationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInstallationValidation.png");
			maxValidation.clearInstallationNotes();
			maxValidation.previousButton();
		}
	}

//	@Test(priority = 39)
//	private void maxSizeValidationAttachmentField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Maximum Size Validation Attachment Field");
//		CustomerCreateOrganizationPage maxFileValidation = new CustomerCreateOrganizationPage(driver);
//		maxFileValidation.maxSizeValidationAttachmentFile();
//		String errorAccessHours = maxFileValidation.errorAttachmentFile();
//		extentTest.log(Status.INFO, "Actual Result - Maximum Size Validation Attachment Field is -" + errorAccessHours);
//		extentTest.log(Status.INFO, "Expected Result - Maximum Size Validation Attachment Field is -"
//				+ "File is too big. Max file size: 20MB.");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorAccessHours.equals("File is too big. Max file size: 20MB.")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("OrgMaxAttachmentValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("OrgMaxAttachmentValidation.png");
//
//		}
//	}
//
//	@Test(priority = 40)
//	private void fileFormatAttachmentField() throws InterruptedException, AWTException, IOException {
//		extentTest = extentReports.createTest("Verify the File Format Validation Attachment Field");
//		CustomerCreateOrganizationPage fileFormat = new CustomerCreateOrganizationPage(driver);
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
//			File file = new File("OrgFormatValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("OrgFormatValidation.png");
//		}
//	}
//
//	@Test(priority = 41)
//	private void maxFileUploadSizeValidationField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Maximum File Limit Validation Attachment Field");
//		CustomerCreateOrganizationPage maxFileValidation = new CustomerCreateOrganizationPage(driver);
//		maxFileValidation.maxFileUploadSizeValidation();
//		String errorAccessHours = maxFileValidation.errorAttachmentFile();
//		extentTest.log(Status.INFO,
//				"Actual Result - Maximum File Limit Validation Attachment Field is -" + errorAccessHours);
//		extentTest.log(Status.INFO, "Expected Result - Maximum File Limit Validation Attachment Field is -"
//				+ "Maximum upload limit reached");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorAccessHours.equals("File count size exceeds the maximum limit of 10")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//			maxFileValidation.deleteFile();
////			maxFileValidation.loopNextButton();
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("OrgLimitValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("OrgLimitValidation.png");
//			maxFileValidation.deleteFile();
////			maxFileValidation.loopNextButton();
//
//		}
//	}

	@Test(priority = 38)
	private void createOrganization() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Customer Organization Successful Message");
		CustomerCreateOrganizationPage create = new CustomerCreateOrganizationPage(driver);
		create.organizationPage();
		create.contactPage();
		create.propertyPage();
		create.equipmentPage();
		String listName = create.create();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + listName);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listName.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCreateValidation.png");
		}

	}
	

	@Test(priority = 39)
	private void organizationNameAlreadyExistValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Already Existed in Organization Name");
		CustomerCreateOrganizationPage alreadyValidation = new CustomerCreateOrganizationPage(driver);
		alreadyValidation.alreadyExistOrganizationName();
		String errorMandatory = alreadyValidation.errorMandatory();
		extentTest.log(Status.INFO, "Actual Result - Mandatory Validation is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result - Mandatory Validation is -" + getPropertyValue("ExistedCompanyName"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("ExistedCompanyName"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			alreadyValidation.backButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrganizationNameAlreadyExisted.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrganizationNameAlreadyExisted.png");
			alreadyValidation.backButton();
		}

	}

	@Test(priority = 40)
	private void alreadyExistedEmail() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the Already Existed in Organization Email Field");
		CustomerCreateOrganizationPage alreadyEmail = new CustomerCreateOrganizationPage(driver);
		String alreadyExistEmail = alreadyEmail.alreadyExistEmail();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + alreadyExistEmail);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (alreadyExistEmail.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAlreadyEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAlreadyEmailValidationValidation.png");
		}

	}

	@Test(priority = 41)
	private void createOrganizationListFirstName() throws IOException {
		extentTest = extentReports.createTest("Verify the Create Organization in the First Name List Field");
		CustomerCreateOrganizationPage listCreate = new CustomerCreateOrganizationPage(driver);
		String createListFirstName = listCreate.createListFirstName();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + createListFirstName);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + createListFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createListFirstName.equals(createListFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgFirstName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgFirstName.png");
		}

	}

	@Test(priority = 42)
	private void characterListValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Create Organization in the First Name List Field");
		CustomerCreateOrganizationPage characterList = new CustomerCreateOrganizationPage(driver);
		characterList.characterListValidation();
		String searchNameListValidation = characterList.searchNameListValidation();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + searchNameListValidation);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + searchNameListValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchNameListValidation.equals(searchNameListValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			characterList.clearSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSearchName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSearchName.png");
			characterList.clearSearchField();
		}

	}

	@Test(priority = 43)
	private void searchOrganizationNameListValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Organization Name Search Filter in List Validation");
		CustomerCreateOrganizationPage searchName = new CustomerCreateOrganizationPage(driver);
		String phoneNumber = searchName.searchNameValidation();
		String listPhoneNumber = searchName.searchNameListValidation();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + phoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listPhoneNumber.equals(phoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			searchName.clearSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSearchListName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSearchListName.png");
			searchName.clearSearchField();
		}

	}

	@Test(priority = 44)
	private void searchPhoneNumberListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Phone Number Search Filter in List Validation");
		CustomerCreateOrganizationPage searchPhone = new CustomerCreateOrganizationPage(driver);
		String searchPhoneNumberValidation = searchPhone.searchPhoneNumberValidation();
		String listPhoneNumber = searchPhone.listPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + searchPhoneNumberValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listPhoneNumber.equals(searchPhoneNumberValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			searchPhone.clearSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSearchPhoneNumber.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSearchPhoneNumber.png");
			searchPhone.clearSearchField();
		}

	}

	@Test(priority = 45)
	private void searchEmailListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Email Search Filter in List Validation");
		CustomerCreateOrganizationPage searchEmail = new CustomerCreateOrganizationPage(driver);
		String searchEmailValidation = searchEmail.searchEmailValidation();
		String listEmailField = searchEmail.listEmailField();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + listEmailField);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + searchEmailValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listEmailField.equals(searchEmailValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSearchEmail.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSearchEmail.png");

		}

	}

	@Test(priority = 46)
	private void filterListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Filter Field in List Validation");
		CustomerCreateOrganizationPage filter = new CustomerCreateOrganizationPage(driver);
		String filterValidation = filter.filterValidation();
		String listFilterValidation = filter.listFilterValidation();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + listFilterValidation);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + filterValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFilterValidation.equals(filterValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			filter.clearSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("orgfilter.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("orgfilter.png");
			filter.clearSearchField();
		}

	}

	@Test(priority = 47)
	private void searchInvalid() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Search Filter Invalid Data in List Validation");
		CustomerCreateOrganizationPage invalid = new CustomerCreateOrganizationPage(driver);
		invalid.searchInvalidValidation();
		String invalidSearch = invalid.invalidSearch();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + invalidSearch);
		extentTest.log(Status.INFO, "Expected Result - Created List Name -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (invalidSearch.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			invalid.resetOption();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Invalid.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Invalid.png");
			invalid.resetOption();
			invalid.clickOrganization();
		}

	}

	@Test(priority = 48)
	private void editContactList() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Edit Organization Details");
		CustomerCreateOrganizationPage edit = new CustomerCreateOrganizationPage(driver);
		edit.editContact();
		String responseMessageCreateContact = edit.updateMessage();
		extentTest.log(Status.INFO, "Actual Result - Edit Contact Details -" + responseMessageCreateContact);
		extentTest.log(Status.INFO, "Expected Result - Edit Contact Details -" + getPropertyValue("CustomerUpdatedMesssage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact.equals(getPropertyValue("CustomerUpdatedMesssage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgEdit.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgEdit.png");
		}

	}

	@Test(priority = 49)
	private void deleteContactList() throws IOException {
		extentTest = extentReports.createTest("Verify the Delete Organization List");
		CustomerCreateOrganizationPage edit = new CustomerCreateOrganizationPage(driver);
		edit.deleteContact();
		String responseMessageCreateContact = edit.deleteMessage();
		extentTest.log(Status.INFO, "Actual Result - Delete Response Message -" + responseMessageCreateContact);
		extentTest.log(Status.INFO, "Expected Result - Delete Response Message -" + getPropertyValue("CustomerDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact.equals(getPropertyValue("CustomerDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgDelete.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgDelete.png");
		}

	}

}
