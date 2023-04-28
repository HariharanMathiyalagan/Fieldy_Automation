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
import com.zaigo.pageobjects.CustomerCreateOrganizationPage;
import com.zaigo.pageobjects.CustomerCreateOrganizationPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerCreateOrganizationModule extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
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

	@BeforeMethod
	public void deleteBeforeCatch() {
		driver.manage().deleteAllCookies();
	}

	@AfterMethod
	public void deleteAfterCatch() {
		driver.manage().deleteAllCookies();
	}

	@Test(priority = 0) // 1-Login
	public void loginPage() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password"));
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
			File file = new File("OrganizationLogin.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrganizationLogin.png");
		}
	}

	@Test(priority = 1)
	private void modulePage() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Navigate to Customer Organization Page");
		CustomerCreateOrganizationPage modulePage = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		String editContact = modulePage.modulePage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerOrganizationList"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CustomerOrganizationList"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ListName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ListName.png");
		}

	}

	@Test(priority = 2)
	private void createLabel() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Customer Organization Page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String editContact = edit.LabelValidation("Create");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("OrganizationCreatePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("OrganizationCreatePage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Create.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Create.png");
		}

	}

	@Test(priority = 3)
	private void maxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Check Customer Organization Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.organizationName("MandatoryValidation");
//		String errorMandatory = maxValidation.errorField("OrganizationName");
		String errorMandatory = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("maxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("maxValidation.png");
		}

	}

	@Test(priority = 4)
	private void maxValidationOrganizationField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.organizationName("MaxValidation");
//		String errorMandatory = maxValidation.errorField("OrganizationName");
		String errorMandatory = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("OrganizationName");
			maxValidation.organizationName("Input");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxOrganizationName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxOrganizationName.png");
			maxValidation.clearFields("OrganizationName");
			maxValidation.organizationName("Input");
		}

	}

	@Test(priority = 5)
	private void maxValidationWebsiteField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Website Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.website("MaxValidation");
//		String errorMandatory = maxValidation.errorField("Website");
		String errorMandatory = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Website");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxWebsite.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxWebsite.png");
			maxValidation.clearFields("Website");
		}

	}

	@Test(priority = 6)
	private void maxValidationAddress1Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Address1 Field exceed its max-256 limit");
		CustomerCreateOrganizationPage address1Validation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		address1Validation.address1("MaxValidation");
//		String errorAddress1Field = address1Validation.errorField("Address1");
		String errorAddress1Field = address1Validation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			address1Validation.clearFields("Address1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAddress1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAddress1Validation.png");
			address1Validation.clearFields("Address1");
		}

	}

	@Test(priority = 7)
	private void maxValidationAddress2Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Address2 Field exceed its max-256 limit");
		CustomerCreateOrganizationPage address2Validation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		address2Validation.address2("MaxValidation");
//		String errorAddress1Field = address2Validation.errorField("Address2");
		String errorAddress1Field = address2Validation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			address2Validation.clearFields("Address2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAddress2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAddress2Validation.png");
			address2Validation.clearFields("Address2");
		}

	}

	@Test(priority = 8)
	private void maxValidationCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization City Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage cityValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		cityValidation.city("MaxValidation");
//		String errorAddress1Field = cityValidation.errorField("City");
		String errorAddress1Field = cityValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			cityValidation.clearFields("City");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCityValidation.png");
			cityValidation.clearFields("City");
		}

	}

	@Test(priority = 9)
	private void maxValidationStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization State Name Field exceed its max-45 limit");
		CustomerCreateOrganizationPage stateValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		stateValidation.state("MaxValidation");
//		String errorStateField = stateValidation.errorField("State");
		String errorStateField = stateValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorStateField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorStateField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			stateValidation.clearFields("State");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgStateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgStateValidation.png");
			stateValidation.clearFields("State");
		}
	}

	@Test(priority = 10)
	private void minValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in zipcode field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.zipCode("MinValidation");
//		String errorZipcodeField = minValidation.errorField("Zipcode");
		String errorZipcodeField = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinZipcodeValidation.png");
			minValidation.clearFields("Zipcode");
		}

	}

	@Test(priority = 11)
	private void maxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Zipcode Field exceed its max-10 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.zipCode("MaxValidation");
//		String errorZipcodeField = maxValidation.errorField("Zipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxZipcodeValidation.png");
			maxValidation.clearFields("Zipcode");
		}
	}

	@Test(priority = 12)
	private void specialCharacterZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when special character provided in Organization Zipcode field");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.zipCode("SpecialCharacter");
//		String errorZipcodeField = maxValidation.errorField("Zipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearFields("Zipcode");
		}
	}

	@Test(priority = 13)
	private void emailValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify error message is displayed when invalid email is entered in Email Field");
		CustomerCreateOrganizationPage emailValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		emailValidation.email("ValidEmail");
//		String errorEmail = emailValidation.errorField("Email");
		String errorEmail = emailValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			emailValidation.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInvalidEmailValidation.png");
			emailValidation.clearFields("Email");
		}
	}

	@Test(priority = 14)
	private void maxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Email Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.email("MaxValidation");
//		String errorEmail = maxValidation.errorField("Email");
		String errorEmail = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxEmailValidation.png");
			maxValidation.clearFields("Email");
		}
	}

	@Test(priority = 15)
	private void invalidPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when alphabetic character provided in Phone Number field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.phoneNumber("ValidPhoneNumber");
//		String errorPhoneNumber = minValidation.errorField("PhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidPhoneNumberMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("ValidPhoneNumberMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 16)
	private void minValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in phone number field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.phoneNumber("MinValidation");
//		String errorPhoneNumber = minValidation.errorField("PhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinPhoneNumberValidation.png");
			minValidation.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 17)
	private void maxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Phone Number Field exceed its max-20 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.phoneNumber("MaxValidation");
//		String errorPhoneNumber = maxValidation.errorField("PhoneNumber");
		String errorPhoneNumber = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PhoneNumber");
			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxPhoneNumberValidation.png");
			maxValidation.clearFields("PhoneNumber");
			maxValidation.nextButton();
		}

	}

	@Test(priority = 18)
	private void maxValidationContactFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Contact First Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.firstName("MaxValidation");
//		String errorFirstName = maxValidation.errorField("ContactFirstName");
		String errorFirstName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactFirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxFirstNameValidation.png");
			maxValidation.clearFields("ContactFirstName");
		}
	}

	@Test(priority = 19)
	private void maxValidationContactLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Contact Last Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.lastName("MaxValidation");
//		String errorFirstName = maxValidation.errorField("ContactLastName");
		String errorFirstName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactLastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LastNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LastNameValidation.png");
			maxValidation.clearFields("ContactLastName");
		}
	}

	@Test(priority = 20)
	private void maxValidationContactEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Contact Email Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.contactEmail("MaxValidation");
//		String errorEmail = maxValidation.errorField("ContactEmail");
		String errorEmail = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxEmailValidation.png");
			maxValidation.clearFields("ContactEmail");
		}
	}

	@Test(priority = 21)
	private void validateContactEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when Customer Organization Contact invalid email is entered in Email Field");
		CustomerCreateOrganizationPage validateEmail = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		validateEmail.contactEmail("ValidEmail");
//		String errorEmail = validateEmail.errorField("ContactEmail");
		String errorEmail = validateEmail.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validateEmail.clearFields("ContactEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInvalidEmailValidation.png");
			validateEmail.clearFields("ContactEmail");
		}
	}

	@Test(priority = 23)
	private void minValidationContactPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in phone number field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.contactPhoneNumber("MinValidation");
//		String errorPhoneNumber = minValidation.errorField("ContactPhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("ContactPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinPhoneNumberValidation.png");
			minValidation.clearFields("ContactPhoneNumber");
		}
	}

	@Test(priority = 24)
	private void maxValidationContactPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Contact Phone Number Field exceed its max-20 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.contactPhoneNumber("MaxValidation");
//		String errorPhoneNumber = maxValidation.errorField("ContactPhoneNumber");
		String errorPhoneNumber = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactPhoneNumber");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPhoneNumberValidation.png");
			maxValidation.clearFields("ContactPhoneNumber");

		}
	}

	@Test(priority = 25)
	private void invalidContactPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when alphabetic character provided in Contact Phone Number field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.contactPhoneNumber("ValidPhoneNumber");
//		String errorPhoneNumber = minValidation.errorField("ContactPhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidPhoneNumberMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("ValidPhoneNumberMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("ContactPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearFields("ContactPhoneNumber");
		}

	}

	@Test(priority = 26)
	private void maxValidationJobTittleField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Job Tittles Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.jobTittle("MaxValidation");
//		String errorJobTittle = maxValidation.errorField("ContactJobTittle");
		String errorJobTittle = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorJobTittle);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorJobTittle.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactJobTittle");
			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxJobTittleValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxJobTittleValidation.png");
			maxValidation.clearFields("ContactJobTittle");
			maxValidation.nextButton();
		}

	}

	@Test(priority = 27)
	private void maxValidationPropertyNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyName("MaxValidation");
//		String errorPropertyName = maxValidation.errorField("PropertyName");
		String errorPropertyName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPropertyName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyNameValidation.png");
			maxValidation.clearFields("PropertyName");
		}

	}

	@Test(priority = 28)
	private void maxValidationPropertyFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property First Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyFirstName("MaxValidation");
//		String errorContactPerson = maxValidation.errorField("PropertyFirstName");
		String errorContactPerson = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPerson);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyFirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyFirstNameValidation.png");
			maxValidation.clearFields("PropertyFirstName");
		}

	}

	@Test(priority = 29)
	private void maxValidationPropertyLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Last Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyLastName("MaxValidation");
//		String errorContactPerson = maxValidation.errorField("PropertyLastName");
		String errorContactPerson = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPerson);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyLastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyFirstNameValidation.png");
			maxValidation.clearFields("PropertyLastName");
		}

	}

	@Test(priority = 30)
	private void maxValidationPropertyAddress1Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Address1 Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyAddress1("MaxValidation");
//		String errorAddress1Field = maxValidation.errorField("PropertyAddress1");
		String errorAddress1Field = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyAddress1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxAddress1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxAddress1Validation.png");
			maxValidation.clearFields("PropertyAddress1");
		}

	}

	@Test(priority = 31)
	private void maxValidationPropertyAddress2Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Address2 Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyAddress2("MaxValidation");
//		String errorAddress1Field = maxValidation.errorField("PropertyAddress2");
		String errorAddress1Field = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyAddress2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxAddress2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxAddress2Validation.png");
			maxValidation.clearFields("PropertyAddress2");
		}
	}

	@Test(priority = 32)
	private void maxValidationPropertyStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property State Name Field exceed its max-45 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyState("MaxValidation");
//		String errorCityField = maxValidation.errorField("PropertyState");
		String errorCityField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorCityField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyState");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgStateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgStateValidation.png");
			maxValidation.clearFields("PropertyState");
		}

	}

	@Test(priority = 33)
	private void maxValidationPropertyCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property City Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyCity("MaxValidation");
//		String errorCityField = maxValidation.errorField("PropertyCity");
		String errorCityField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorCityField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyCity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCityValidation.png");
			maxValidation.clearFields("PropertyCity");
		}

	}

	@Test(priority = 34)
	private void specialCharacterPropertyZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when special character provided in Property Zipcode field");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyZipcode("SpecialCharacter");
//		String errorZipcodeField = maxValidation.errorField("PropertyZipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyZipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearFields("PropertyZipcode");
		}
	}

	@Test(priority = 35)
	private void minValidationPropertyZipCodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in zipcode field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.propertyZipcode("MinValidation");
//		String errorZipcodeField = minValidation.errorField("PropertyZipcode");
		String errorZipcodeField = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("PropertyZipcode");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinZipcodeValidation.png");
			minValidation.clearFields("PropertyZipcode");

		}
	}

	@Test(priority = 36)
	private void maxValidationPropertyZipCodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Zipcode Field exceed its max-10 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyZipcode("MaxValidation");
//		String errorZipcodeField = maxValidation.errorField("PropertyZipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyZipcode");
			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxZipcodeValidation.png");
			maxValidation.clearFields("PropertyZipcode");
			maxValidation.nextButton();
		}
	}

	@Test(priority = 37)
	private void maxValidationProductNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Product Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.productName("MaxValidation");
//		String errorProductField = maxValidation.errorField("ProductName");
		String errorProductField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorProductField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorProductField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ProductName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgProductValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgProductValidation.png");
			maxValidation.clearFields("ProductName");
		}
	}

	@Test(priority = 38)
	private void maxValidationBrandNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Brand Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.brandName("MaxValidation");
//		String errorBrandField = maxValidation.errorField("BrandName");
		String errorBrandField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorBrandField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorBrandField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("BrandName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgBrandValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgBrandValidation.png");
			maxValidation.clearFields("BrandName");
		}
	}

	@Test(priority = 39)
	private void maxValidationModelNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Model Number Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.modelNumber("MaxValidation");
//		String errorModelField = maxValidation.errorField("ModelNumber");
		String errorModelField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorModelField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorModelField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ModelNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgModelValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgModelValidation.png");
			maxValidation.clearFields("ModelNumber");
		}

	}

	@Test(priority = 40)
	private void maxValidationSerialNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Serial Number Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.serialNumber("MaxValidation");
//		String errorSerialNumberField = maxValidation.errorField("SerialNumber");
		String errorSerialNumberField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorSerialNumberField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorSerialNumberField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("SerialNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSerialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSerialValidation.png");
			maxValidation.clearFields("SerialNumber");
		}
	}

	@Test(priority = 41)
	private void dateValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Date Installed Field enter the Future Date");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.dateInstalled("MaxValidation");
		String errorSerialNumberField = maxValidation.responseMessage("ResponseMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorSerialNumberField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DateMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorSerialNumberField.equals(getPropertyValue("DateMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("DateInstalled");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SerialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SerialValidation.png");
			maxValidation.clearFields("DateInstalled");
		}
	}

	@Test(priority = 42)
	private void maxValidationAccessHoursField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Access Hours Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.accessHours("MaxValidation");
//		String errorAccessHours = maxValidation.errorField("AccessHours");
		String errorAccessHours = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAccessHours);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("AccessHours");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAccessValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAccessValidation.png");
			maxValidation.clearFields("AccessHours");
		}

	}

	@Test(priority = 43)
	private void maxValidationInstallationNotesField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Installation Notes Field exceed its max-2048 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.installationNotes("MaxValidation");
//		String errorAccessHours = maxValidation.errorField("InstallationNotes");
		String errorAccessHours = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAccessHours);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("InstallationNotes");
			maxValidation.loopPreviousButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInstallationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInstallationValidation.png");
			maxValidation.clearFields("InstallationNotes");
			maxValidation.loopPreviousButton();
		}
	}

	@Test(priority = 44)
	private void createOrganization() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify created successful message is displayed, when the Customer Organization Created");
		CustomerCreateOrganizationPage create = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		create.organizationPage();
		create.contactPage("CreateContact");
		create.propertyPage();
		create.equipmentPage();
		String listName = create.responseMessage("ResponseMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + listName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
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
			create.responseMessage("AlternateFunction");
		}

	}

	@Test(priority = 45)
	private void customerOrganizationCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the Customer Organization Created Count is added in the Total Organization Count");
		CustomerCreateOrganizationPage create = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		int actualTotal = create.actualResult();
		int expectedResult = create.totalCount();
		extentTest.log(Status.INFO, "Actual Result is -" + actualTotal);
		extentTest.log(Status.INFO, "Expected Result is -" + expectedResult);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualTotal == expectedResult) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
		}

	}

	@Test(priority = 46)
	private void organizationNameAlreadyExistValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify [Organization Already Exists] Error is displayed when already existing Organization Name is provided");
		CustomerCreateOrganizationPage alreadyValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		alreadyValidation.organizationName("UniqueValidation");
//		String errorMandatory = alreadyValidation.errorField("OrganizationName");
		String errorMandatory = alreadyValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ExistedCompanyName"));
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

	static String listValidation;

	@Test(priority = 47)
	private void alreadyExistedEmail() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest(
				"Verify [Email Already Exists] Error is dispalyed when already existing mail ID is provided");
		CustomerCreateOrganizationPage alreadyEmail = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		alreadyEmail.email("UniqueEmail");
//		String alreadyExistEmail = alreadyEmail.errorField("Email");
		String alreadyExistEmail = alreadyEmail.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + alreadyExistEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (alreadyExistEmail.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			alreadyEmail.backButton();
			listValidation = alreadyEmail.listValidation("CharacterFirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAlreadyEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAlreadyEmailValidationValidation.png");
			alreadyEmail.backButton();
			listValidation = alreadyEmail.listValidation("CharacterFirstName");
		}

	}

	@Test(priority = 49)
	private void characterListValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify the Created Organization Name & pick the character filter is " + listValidation);
		CustomerCreateOrganizationPage characterList = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		listValidation = characterList.listValidation("AlphabetFilter");
		String searchNameListValidation = characterList.listValidation("OrganizationName");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + searchNameListValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(searchNameListValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSearchName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSearchName.png");
		}

	}

	@Test(priority = 50)
	private void searchOrganizationNameListValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Enter the Customer Organization Name:" + listValidation
				+ " in the Search field & Customer [Organization Name] list retrived successfully");
		CustomerCreateOrganizationPage searchName = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		listValidation = searchName.listValidation("OrganizationName");
		String listPhoneNumber = searchName.listValidation("SearchField");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listPhoneNumber.equals(listValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			searchName.clearFields("Search");
			listValidation = searchName.listValidation("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSearchListName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSearchListName.png");
			searchName.clearFields("Search");
			listValidation = searchName.listValidation("PhoneNumber");
		}

	}

	@Test(priority = 51)
	private void searchPhoneNumberListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the Customer Organization Phone Number:" + listValidation
				+ " in the Search field & Customer [Organization Phone Number] list retrived successfully");
		CustomerCreateOrganizationPage searchName = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		listValidation = searchName.listValidation("PhoneNumber");
		String listPhoneNumber = searchName.listValidation("SearchField");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			searchName.clearFields("Search");
			listValidation = searchName.listValidation("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSearchPhoneNumber.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSearchPhoneNumber.png");
			searchName.clearFields("Search");
			listValidation = searchName.listValidation("Email");
		}

	}

	@Test(priority = 52)
	private void searchEmailListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the Customer Organization Email:" + listValidation
				+ " in the Search field & Customer [Organization Email] list retrived successfully");
		CustomerCreateOrganizationPage searchEmail = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		listValidation = searchEmail.listValidation("Email");
		String listEmailField = searchEmail.listValidation("SearchField");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listEmailField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listEmailField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidation = searchEmail.listValidation("LeadSource");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSearchEmail.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSearchEmail.png");
			listValidation = searchEmail.listValidation("LeadSource");
		}

	}

	@Test(priority = 53)
	private void filterListValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify to Pick the Filter field & select Lead Source is:" + listValidation);
		CustomerCreateOrganizationPage filter = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		filter.listValidation("Filter");
		String listFilterValidation = filter.listValidation("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listFilterValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listFilterValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			filter.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("orgfilter.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("orgfilter.png");
			filter.clearFields("Search");
		}

	}

	@Test(priority = 54)
	private void searchInvalid() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		CustomerCreateOrganizationPage invalid = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		invalid.listValidation("InvalidSearch");
		String invalidSearch = invalid.listValidation("Invalid");
		extentTest.log(Status.INFO, "Actual Result is -" + invalidSearch);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
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
		}

	}

	@Test(priority = 55)
	private void editLabel() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Customer Contact Page");
		CustomerCreateOrganizationPage invalid = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String editContact = invalid.LabelValidation("Edit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("OrganizationEditPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("OrganizationEditPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			invalid.visibleName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Edit.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Edit.png");
			invalid.visibleName();
		}
	}

	@Test(priority = 56)
	public void organizationNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Organization Name:" + CustomerCreateOrganizationPage.organizationName
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("OrganizationName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.organizationName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.organizationName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}
	}

	@Test(priority = 57)
	public void websitePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Website:" + CustomerCreateOrganizationPage.website
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("Website");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.website);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.website)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}
	}

	@Test(priority = 58)
	public void Address1Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Address1:" + CustomerCreateOrganizationPage.address1
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("Address1");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.address1);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.address1)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 59)
	public void Address2Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Address2:" + CustomerCreateOrganizationPage.address2
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("Address2");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.address2);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.address2)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 60)
	public void CityPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the City:" + CustomerCreateOrganizationPage.city
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("City");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.city);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.city)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 61)
	public void StatePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the State:" + CustomerCreateOrganizationPage.state
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("State");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.state);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.state)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 62)
	public void ZipcodePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Zipcode:" + CustomerCreateOrganizationPage.zipCode
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("Zipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.zipCode);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.zipCode)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 63)
	public void leadSourcePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Lead Source Name:" + CustomerCreateOrganizationPage.leadSource
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("LeadSources");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.leadSource);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.leadSource)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 64)
	public void phoneNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Phone Number:" + CustomerCreateOrganizationPage.phoneNumber
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.phoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.phoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			edit.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
			edit.nextButton();
		}
	}

	@Test(priority = 65)
	public void firstNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Contact First Name:" + CustomerCreateOrganizationPage.firstName
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.firstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.firstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}
	}

	@Test(priority = 66)
	public void lastNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Contact Last Name:" + CustomerCreateOrganizationPage.lastName
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.lastName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.lastName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 67)
	public void emailPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Contact Email:" + CustomerCreateOrganizationPage.contactEmail
				+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("ContactEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.contactEmail);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.contactEmail)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 68)
	public void jobTittlePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Contact Job Tittle:" + CustomerCreateOrganizationPage.jobTittle
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("JobTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.jobTittle);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.jobTittle)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 69)
	public void contactphoneNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Contact Phone Number:" + CustomerCreateOrganizationPage.contactPhoneNumber
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("ContactPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.contactPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.contactPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			edit.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
			edit.nextButton();
		}
	}

	@Test(priority = 70)
	public void propertyFirstNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property First Name:" + CustomerCreateOrganizationPage.propertyFirstName
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("PropertyFirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.propertyFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.propertyFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}
	}

	@Test(priority = 71)
	public void propertyLastNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property Last Name:" + CustomerCreateOrganizationPage.propertyLastName
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("PropertyLastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.propertyLastName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.propertyLastName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 72)
	public void propertyNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Property Name:" + CustomerCreateOrganizationPage.propertyName
				+ " is prepopulated in the customer contact form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("PropertyName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.propertyName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.propertyName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 73)
	public void locationAddress1Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property Location Address1:" + CustomerCreateOrganizationPage.propertyAddress1
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("LocationAddress1");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.propertyAddress1);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.propertyAddress1)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 74)
	public void locationAddress2Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property Location Address2:" + CustomerCreateOrganizationPage.propertyAddress2
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("LocationAddress2");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.propertyAddress2);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.propertyAddress2)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 75)
	public void locationCityPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property Location City:" + CustomerCreateOrganizationPage.propertyCityName
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("LocationCity");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.propertyCityName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.propertyCityName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 76)
	public void locationStatePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property Location State:" + CustomerCreateOrganizationPage.propertyStateName
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("LocationState");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.propertyStateName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.propertyStateName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 77)
	public void locationZipcodePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property Location Zipcode:" + CustomerCreateOrganizationPage.propertyZipcode
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.propertyZipcode);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.propertyZipcode)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			edit.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
			edit.nextButton();
		}

	}

	@Test(priority = 78)
	public void productNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Equipment Product Name:" + CustomerCreateOrganizationPage.productName
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("ProductName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.productName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.productName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}
	}

	@Test(priority = 79)
	public void brandNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Equipment Brand Name:" + CustomerCreateOrganizationPage.brandName
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("BrandName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.brandName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.brandName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 80)
	public void modelNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Equipment Model Number:"
				+ CustomerCreateOrganizationPage.modelNumber + " is prepopulated in the customer contact form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("ModelNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.modelNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.modelNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 81)
	public void serialNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Equipment Serial Number:" + CustomerCreateOrganizationPage.serialNumber
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("SerialNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.serialNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.serialNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 82)
	public void dateInstalledPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Equipment Date Installed:" + CustomerCreateOrganizationPage.dateInstalled
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("DateInstalled");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.dateInstalled);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.dateInstalled)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 83)
	public void warrantyInformationPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Equipment Warranty Information:" + CustomerCreateOrganizationPage.warrantyInformation
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("WarrantyInformation");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.warrantyInformation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.warrantyInformation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 84)
	public void accessHoursPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Equipment Access Hours:" + CustomerCreateOrganizationPage.accessHours
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("AccessHours");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.accessHours);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.accessHours)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 85)
	public void installationNotesPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Equipment Installation:" + CustomerCreateOrganizationPage.installationNotes
						+ " is prepopulated in the customer organization edit form page");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		String assertionMessage = edit.prepopulationFields("InstallationNotes");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateOrganizationPage.installationNotes);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateOrganizationPage.installationNotes)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			edit.loopPreviousButton();
			edit.clearAllFields("OrganizationPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
			edit.loopPreviousButton();
			edit.clearAllFields("OrganizationPage");
		}

	}

	@Test(priority = 86)
	private void editmaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Check Customer Organization Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.organizationName("maxValidation");
//		String errorMandatory = maxValidation.errorField("OrganizationName");
		String errorMandatory = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("maxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("maxValidation.png");
		}

	}

	@Test(priority = 87)
	private void editmaxValidationOrganizationField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.organizationName("MaxValidation");
//		String errorMandatory = maxValidation.errorField("OrganizationName");
		String errorMandatory = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("OrganizationName");
			maxValidation.organizationName("Input");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxOrganizationName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxOrganizationName.png");
			maxValidation.clearFields("OrganizationName");
			maxValidation.organizationName("Input");
		}

	}

	@Test(priority = 88)
	private void editmaxValidationWebsiteField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Website Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.website("MaxValidation");
//		String errorMandatory = maxValidation.errorField("Website");
		String errorMandatory = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatory);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatory.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Website");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxWebsite.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxWebsite.png");
			maxValidation.clearFields("Website");
		}

	}

	@Test(priority = 89)
	private void editmaxValidationAddress1Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Address1 Field exceed its max-256 limit");
		CustomerCreateOrganizationPage address1Validation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		address1Validation.address1("MaxValidation");
//		String errorAddress1Field = address1Validation.errorField("Address1");
		String errorAddress1Field = address1Validation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			address1Validation.clearFields("Address1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAddress1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAddress1Validation.png");
			address1Validation.clearFields("Address1");
		}

	}

	@Test(priority = 90)
	private void editmaxValidationAddress2Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Address2 Field exceed its max-256 limit");
		CustomerCreateOrganizationPage address2Validation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		address2Validation.address2("MaxValidation");
//		String errorAddress1Field = address2Validation.errorField("Address2");
		String errorAddress1Field = address2Validation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			address2Validation.clearFields("Address2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAddress2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAddress2Validation.png");
			address2Validation.clearFields("Address2");
		}

	}

	@Test(priority = 91)
	private void editmaxValidationCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization City Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage cityValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		cityValidation.city("MaxValidation");
//		String errorAddress1Field = cityValidation.errorField("City");
		String errorAddress1Field = cityValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			cityValidation.clearFields("City");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCityValidation.png");
			cityValidation.clearFields("City");
		}

	}

	@Test(priority = 92)
	private void editmaxValidationStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization State Name Field exceed its max-45 limit");
		CustomerCreateOrganizationPage stateValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		stateValidation.state("MaxValidation");
//		String errorStateField = stateValidation.errorField("State");
		String errorStateField = stateValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorStateField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorStateField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			stateValidation.clearFields("State");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgStateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgStateValidation.png");
			stateValidation.clearFields("State");
		}
	}

	@Test(priority = 93)
	private void editminValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in zipcode field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.zipCode("MinValidation");
//		String errorZipcodeField = minValidation.errorField("Zipcode");
		String errorZipcodeField = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinZipcodeValidation.png");
			minValidation.clearFields("Zipcode");
		}

	}

	@Test(priority = 94)
	private void editmaxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Zipcode Field exceed its max-10 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.zipCode("MaxValidation");
//		String errorZipcodeField = maxValidation.errorField("Zipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxZipcodeValidation.png");
			maxValidation.clearFields("Zipcode");
		}
	}

	@Test(priority = 95)
	private void editspecialCharacterZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Zipcode Field exceed its max-10 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.zipCode("SpecialCharacter");
//		String errorZipcodeField = maxValidation.errorField("Zipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearFields("Zipcode");
		}
	}

	@Test(priority = 96)
	private void editemailValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify error message is displayed when invalid email is entered in Email Field");
		CustomerCreateOrganizationPage emailValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		emailValidation.email("ValidEmail");
//		String errorEmail = emailValidation.errorField("Email");
		String errorEmail = emailValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			emailValidation.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInvalidEmailValidation.png");
			emailValidation.clearFields("Email");
		}
	}

	@Test(priority = 97)
	private void editmaxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Email Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.email("MaxValidation");
//		String errorEmail = maxValidation.errorField("Email");
		String errorEmail = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxEmailValidation.png");
			maxValidation.clearFields("Email");
		}
	}

	@Test(priority = 98)
	private void editinvalidPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when alphabetic character provided in Phone Number field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.phoneNumber("ValidPhoneNumber");
//		String errorPhoneNumber = minValidation.errorField("PhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidPhoneNumberMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("ValidPhoneNumberMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 99)
	private void editminValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in phone number field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.phoneNumber("MinValidation");
//		String errorPhoneNumber = minValidation.errorField("PhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinPhoneNumberValidation.png");
			minValidation.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 100)
	private void editmaxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Phone Number Field exceed its max-20 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.phoneNumber("MaxValidation");
//		String errorPhoneNumber = maxValidation.errorField("PhoneNumber");
		String errorPhoneNumber = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PhoneNumber");
			maxValidation.nextButton();
			maxValidation.clearAllFields("ContactPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxPhoneNumberValidation.png");
			maxValidation.clearFields("PhoneNumber");
			maxValidation.nextButton();
			maxValidation.clearAllFields("ContactPage");
		}

	}

	@Test(priority = 101)
	private void editmaxValidationContactFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Contact First Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.firstName("MaxValidation");
//		String errorFirstName = maxValidation.errorField("ContactFirstName");
		String errorFirstName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactFirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxFirstNameValidation.png");
			maxValidation.clearFields("ContactFirstName");
		}
	}

	@Test(priority = 102)
	private void editmaxValidationContactLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Contact Last Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.lastName("MaxValidation");
//		String errorFirstName = maxValidation.errorField("ContactLastName");
		String errorFirstName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactLastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LastNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LastNameValidation.png");
			maxValidation.clearFields("ContactLastName");
		}
	}

	@Test(priority = 103)
	private void editmaxValidationContactEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Contact Email Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.contactEmail("MaxValidation");
//		String errorEmail = maxValidation.errorField("ContactEmail");
		String errorEmail = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxEmailValidation.png");
			maxValidation.clearFields("ContactEmail");
		}
	}

	@Test(priority = 104)
	private void editvalidateContactEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when Customer Organization Contact invalid email is entered in Email Field");
		CustomerCreateOrganizationPage validateEmail = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		validateEmail.contactEmail("ValidEmail");
//		String errorEmail = validateEmail.errorField("ContactEmail");
		String errorEmail = validateEmail.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validateEmail.clearFields("ContactEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInvalidEmailValidation.png");
			validateEmail.clearFields("ContactEmail");
		}
	}

	@Test(priority = 105)
	private void editminValidationContactPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in phone number field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.contactPhoneNumber("MinValidation");
		String errorPhoneNumber = minValidation.errorMessage();
//		String errorPhoneNumber = minValidation.errorField("ContactPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("ContactPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinPhoneNumberValidation.png");
			minValidation.clearFields("ContactPhoneNumber");
		}
	}

	@Test(priority = 106)
	private void editmaxValidationContactPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Contact Phone Number Field exceed its max-20 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.contactPhoneNumber("MaxValidation");
//		String errorPhoneNumber = maxValidation.errorField("ContactPhoneNumber");
		String errorPhoneNumber = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactPhoneNumber");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPhoneNumberValidation.png");
			maxValidation.clearFields("ContactPhoneNumber");

		}
	}

	@Test(priority = 107)
	private void editinvalidContactPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when alphabetic character provided in Contact Phone Number field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.contactPhoneNumber("ValidPhoneNumber");
//		String errorPhoneNumber = minValidation.errorField("ContactPhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidPhoneNumberMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("ValidPhoneNumberMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("ContactPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearFields("ContactPhoneNumber");
		}

	}

	@Test(priority = 108)
	private void editmaxValidationJobTittleField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Job Tittles Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.jobTittle("MaxValidation");
//		String errorJobTittle = maxValidation.errorField("ContactJobTittle");
		String errorJobTittle = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorJobTittle);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorJobTittle.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ContactJobTittle");
			maxValidation.nextButton();
			maxValidation.clearAllFields("PropertyPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxJobTittleValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxJobTittleValidation.png");
			maxValidation.clearFields("ContactJobTittle");
			maxValidation.nextButton();
			maxValidation.clearAllFields("PropertyPage");
		}

	}

	@Test(priority = 109)
	private void editmaxValidationPropertyNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyName("MaxValidation");
//		String errorPropertyName = maxValidation.errorField("PropertyName");
		String errorPropertyName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPropertyName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyNameValidation.png");
			maxValidation.clearFields("PropertyName");
		}

	}

	@Test(priority = 110)
	private void editmaxValidationPropertyFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property First Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyFirstName("MaxValidation");
//		String errorContactPerson = maxValidation.errorField("PropertyFirstName");
		String errorContactPerson = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPerson);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyFirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyFirstNameValidation.png");
			maxValidation.clearFields("PropertyFirstName");
		}

	}

	@Test(priority = 111)
	private void editmaxValidationPropertyLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Last Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyLastName("MaxValidation");
//		String errorContactPerson = maxValidation.errorField("PropertyLastName");
		String errorContactPerson = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPerson);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyLastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPropertyFirstNameValidation.png");
			maxValidation.clearFields("PropertyLastName");
		}

	}

	@Test(priority = 112)
	private void editmaxValidationPropertyAddress1Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Address1 Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyAddress1("MaxValidation");
//		String errorAddress1Field = maxValidation.errorField("PropertyAddress1");
		String errorAddress1Field = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyAddress1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxAddress1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxAddress1Validation.png");
			maxValidation.clearFields("PropertyAddress1");
		}

	}

	@Test(priority = 113)
	private void editmaxValidationPropertyAddress2Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Address2 Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyAddress2("MaxValidation");
//		String errorAddress1Field = maxValidation.errorField("PropertyAddress2");
		String errorAddress1Field = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyAddress2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxAddress2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxAddress2Validation.png");
			maxValidation.clearFields("PropertyAddress2");
		}
	}

	@Test(priority = 114)
	private void editmaxValidationPropertyStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property State Name Field exceed its max-45 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyState("MaxValidation");
//		String errorCityField = maxValidation.errorField("PropertyState");
		String errorCityField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorCityField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyState");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgStateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgStateValidation.png");
			maxValidation.clearFields("PropertyState");
		}

	}

	@Test(priority = 115)
	private void editmaxValidationPropertyCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property City Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyCity("MaxValidation");
//		String errorCityField = maxValidation.errorField("PropertyCity");
		String errorCityField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorCityField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyCity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCityValidation.png");
			maxValidation.clearFields("PropertyCity");
		}

	}

	@Test(priority = 116)
	private void editspecialCharacterPropertyZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Zipcode Field exceed its max-10 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyZipcode("SpecialCharacter");
//		String errorZipcodeField = maxValidation.errorField("PropertyZipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyZipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearFields("PropertyZipcode");
		}
	}

	@Test(priority = 117)
	private void editminValidationPropertyZipCodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in zipcode field");
		CustomerCreateOrganizationPage minValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		minValidation.propertyZipcode("MinValidation");
//		String errorZipcodeField = minValidation.errorField("PropertyZipcode");
		String errorZipcodeField = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("PropertyZipcode");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMinZipcodeValidation.png");
			minValidation.clearFields("PropertyZipcode");

		}
	}

	@Test(priority = 118)
	private void editmaxValidationPropertyZipCodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Property Zipcode Field exceed its max-10 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.propertyZipcode("MaxValidation");
//		String errorZipcodeField = maxValidation.errorField("PropertyZipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyZipcode");
			maxValidation.nextButton();
			maxValidation.clearAllFields("EquipmentPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgMaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgMaxZipcodeValidation.png");
			maxValidation.clearFields("PropertyZipcode");
			maxValidation.nextButton();
			maxValidation.clearAllFields("EquipmentPage");
		}
	}

	@Test(priority = 119)
	private void editmaxValidationProductNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Product Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.productName("MaxValidation");
//		String errorProductField = maxValidation.errorField("ProductName");
		String errorProductField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorProductField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorProductField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ProductName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgProductValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgProductValidation.png");
			maxValidation.clearFields("ProductName");
		}
	}

	@Test(priority = 120)
	private void editmaxValidationBrandNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Brand Name Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.brandName("MaxValidation");
//		String errorBrandField = maxValidation.errorField("BrandName");
		String errorBrandField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorBrandField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorBrandField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("BrandName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgBrandValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgBrandValidation.png");
			maxValidation.clearFields("BrandName");
		}
	}

	@Test(priority = 121)
	private void editmaxValidationModelNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Model Number Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.modelNumber("MaxValidation");
//		String errorModelField = maxValidation.errorField("ModelNumber");
		String errorModelField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorModelField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorModelField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("ModelNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgModelValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgModelValidation.png");
			maxValidation.clearFields("ModelNumber");
		}

	}

	@Test(priority = 122)
	private void editmaxValidationSerialNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Serial Number Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.serialNumber("MaxValidation");
//		String errorSerialNumberField = maxValidation.errorField("SerialNumber");
		String errorSerialNumberField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorSerialNumberField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorSerialNumberField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("SerialNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgSerialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgSerialValidation.png");
			maxValidation.clearFields("SerialNumber");
		}
	}

	@Test(priority = 123)
	private void editdateValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Date Installed Field enter the Future Date");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.dateInstalled("MaxValidation");
		String errorSerialNumberField = maxValidation.responseMessage("ResponseMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorSerialNumberField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DateMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorSerialNumberField.equals(getPropertyValue("DateMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("DateInstalled");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SerialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SerialValidation.png");
			maxValidation.clearFields("DateInstalled");
		}
	}

	@Test(priority = 124)
	private void editmaxValidationAccessHoursField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Access Hours Field exceed its max-256 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.accessHours("MaxValidation");
//		String errorAccessHours = maxValidation.errorField("AccessHours");
		String errorAccessHours = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAccessHours);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("AccessHours");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgAccessValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgAccessValidation.png");
			maxValidation.clearFields("AccessHours");
		}

	}

	@Test(priority = 125)
	private void editmaxValidationInstallationNotesField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Organization Installation Notes Field exceed its max-2048 limit");
		CustomerCreateOrganizationPage maxValidation = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		maxValidation.installationNotes("MaxValidation");
//		String errorAccessHours = maxValidation.errorField("InstallationNotes");
		String errorAccessHours = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAccessHours);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("InstallationNotes");
			maxValidation.loopPreviousButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgInstallationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgInstallationValidation.png");
			maxValidation.clearFields("InstallationNotes");
			maxValidation.loopPreviousButton();
		}
	}

	@Test(priority = 126)
	private void editOrganization() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify updated successful message is displayed, when the Customer Organization Updated");
		CustomerCreateOrganizationPage create = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		create.organizationPage();
		create.contactPage("EditContact");
		create.propertyPage();
		create.equipmentPage();
		String listName = create.responseMessage("ResponseMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + listName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerUpdatedMesssage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listName.equals(getPropertyValue("CustomerUpdatedMesssage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCreateValidation.png");
			create.responseMessage("AlternateFunction");
		}

	}

	@Test(priority = 127)
	private void deleteContactList() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify deleted successful message is displayed, when the Customer Organization Deleted");
		CustomerCreateOrganizationPage edit = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		edit.deleteContact();
		String responseMessageCreateContact = edit.responseMessage("ResponseMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessageCreateContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerDeletedMessage"));
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
