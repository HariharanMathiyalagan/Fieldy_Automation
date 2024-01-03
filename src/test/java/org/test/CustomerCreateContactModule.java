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
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.EditDetailScreenCompaniesPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerCreateContactModule extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
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
	private void contactModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Navigate to Customer Contact Page");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String editContact = initElements.modulePage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerContactList"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CustomerContactList"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
		}

	}

	@Test(priority = 1)
	private void createLabel() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Customer Contact Page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String editContact = edit.LabelValidation("Create");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContactCreatePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("ContactCreatePage"))) {
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

	@Test(priority = 2)
	private void mandatoryValidation() throws AWTException, IOException {
		extentTest = extentReports.createTest(
				"Check Customer Contact Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CustomerCreateContactPage mandatoryValidation = PageFactory.initElements(driver,
				CustomerCreateContactPage.class);
		mandatoryValidation.firstNameValidation("MandatoryValidation");
//		String errorMandatoryValidation = mandatoryValidation.errorField("FirstName");
		String errorMandatoryValidation = mandatoryValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
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
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact First Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.firstNameValidation("MaxValidation");
//		String errorFirstName = maxValidation.errorField("FirstName");
		String errorFirstName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation First Name Field is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation First Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("FirstName");
			maxValidation.clearFields("Name");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("FirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("FirstNameValidation.png");
			maxValidation.clearFields("FirstName");
			maxValidation.clearFields("Name");
		}

	}

	@Test(priority = 4)
	private void maxValidationLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Last Name Field exceed its max-256 limit");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.lastNameValidation("MaxValidation");
//		String errorFirstName = minValidation.errorField("LastName");
		String errorFirstName = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("LastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LastNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LastNameValidation.png");
			minValidation.clearFields("LastName");
		}
	}

//	@Test(priority = 5)
	private void maxValidationJobTittle() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Job Tittle Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.jobTittleValidation("MaxValidation");
//		String errorJobTittle = maxValidation.errorField("JobTittle");
		String errorJobTittle = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorJobTittle);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorJobTittle.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("JobTittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("JobTittleValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("JobTittleValidation.png");
			maxValidation.clearFields("JobTittle");
		}

	}

	@Test(priority = 6)
	private void maxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Email Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.emailValidation("MaxValidation");
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
			File file = new File("MaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxEmailValidation.png");
			maxValidation.clearFields("Email");
		}

	}

	@Test(priority = 7)
	private void maxValidationTaxNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Customer Contact] Tax Number Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidationLocationField = PageFactory.initElements(driver,
				CustomerCreateContactPage.class);
		maxValidationLocationField.taxNumber("MaxValidation");
		String assertionMessage = maxValidationLocationField.errorMessage();
//		String assertionMessage = maxValidationLocationField.errorFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("TaxNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLastNameMaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLastNameMaxValidation.png");
			maxValidationLocationField.clearFields("TaxNumber");
		}
	}

	@Test(priority = 8)
	private void invalidValidationEmailField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when Customer Contact invalid email is entered in Email Field");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.emailValidation("ValidEmail");
//		String errorEmail = maxValidation.errorField("Email");
		String errorEmail = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InvalidEmailValidation.png");
			maxValidation.clearFields("Email");
		}
	}

	@Test(priority = 9)
	private void specialCharacterPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when special character provided in Phone Number field");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.phoneNumberValidation("SpecialCharacter");
//		String errorPhoneNumber = minValidation.errorField("PhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterPhoneNumber"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("SpecialCharacterPhoneNumber"))) {
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

	@Test(priority = 10)
	private void invalidPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when alphabetic character provided in Phone Number field");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.phoneNumberValidation("ValidPhoneNumber");
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

	@Test(priority = 11)
	private void minValidationPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.phoneNumberValidation("MinValidation");
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
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 12)
	private void maxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Phone Number Field exceed its max-20 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.phoneNumberValidation("MaxValidation");
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
			File file = new File("MaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPhoneNumberValidation.png");
			maxValidation.clearFields("PhoneNumber");
			maxValidation.nextButton();
		}

	}

	@Test(priority = 13)
	private void maxValidationPropertyFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property First Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyFirstNameValidation("MaxValidation");
//		String errorPropertyName = maxValidation.errorField("PropertyFirstName");
		String errorPropertyName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPropertyName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyFirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyFirstNameValidation.png");
			maxValidation.clearFields("PropertyFirstName");
		}

	}

	@Test(priority = 14)
	private void maxValidationPropertyLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Last Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyLastNameValidation("MaxValidation");
//		String errorPropertyName = maxValidation.errorField("PropertyLastName");
		String errorPropertyName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPropertyName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyLastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyFirstNameValidation.png");
			maxValidation.clearFields("PropertyLastName");
		}

	}

	@Test(priority = 15)
	private void maxValidationPropertyNameField() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyNameValidation("MaxValidation");
//		String errorContactPerson = maxValidation.errorField("PropertyName");
		String errorContactPerson = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPerson);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyNameValidation.png");
			maxValidation.clearFields("PropertyName");
		}

	}

	@Test(priority = 16)
	private void maxValidationAddress1() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Address1 Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyAddress1Validation("MaxValidation");
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
			File file = new File("Address1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Address1Validation.png");
			maxValidation.clearFields("PropertyAddress1");
		}

	}

	@Test(priority = 17)
	private void maxValidationAddress22Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Address2 Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyAddress2Validation("MaxValidation");
//		String errorAddress1Field = maxValidation.errorField("PropertyAddress2");
		String errorAddress1Field = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyAddress2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Address2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Address2Validation.png");
			maxValidation.clearFields("PropertyAddress2");
		}
	}

	@Test(priority = 18)
	private void maxValidationCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property City Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyCityValidation("MaxValidation");
//		String errorCityField = maxValidation.errorField("City");
		String errorCityField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorCityField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("City");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CityValidation.png");
			maxValidation.clearFields("City");
		}

	}

	@Test(priority = 19)
	private void maxValidationStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property State Field exceed its max-45 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyStateValidation("MaxValidation");
//		String errorStateField = maxValidation.errorField("State");
		String errorStateField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorStateField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorStateField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("State");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("StateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("StateValidation.png");
			maxValidation.clearFields("State");
		}
	}

	@Test(priority = 20)
	private void minValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.propertyZipcodeValidation("MinValidation");
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
			File file = new File("MinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinZipcodeValidation.png");
			minValidation.clearFields("Zipcode");
		}
	}

	@Test(priority = 21)
	private void maxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Zipcode Field exceed its max-10 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyZipcodeValidation("MaxValidation");
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
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearFields("Zipcode");
		}
	}

	@Test(priority = 22)
	private void specialCharacterZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when special character provided in Property Zipcode field");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyZipcodeValidation("SpecialCharacter");
//		String errorZipcodeField = maxValidation.errorField("Zipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");
			maxValidation.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearFields("Zipcode");
			maxValidation.nextButton();
		}
	}

	@Test(priority = 23)
	private void maxValidationProductNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Product Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.productNameValidation("MaxValidation");
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
			File file = new File("ProductValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ProductValidation.png");
			maxValidation.clearFields("ProductName");
		}
	}

	@Test(priority = 24)
	private void maxValidationBrandNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Brand Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.brandNameValidation("MaxValidation");
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
			File file = new File("BrandValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BrandValidation.png");
			maxValidation.clearFields("BrandName");
		}
	}

	@Test(priority = 25)
	private void maxValdidationModelNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Model Number Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.modelNumberValidation("MaxValidation");
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
			File file = new File("ModelValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ModelValidation.png");
			maxValidation.clearFields("ModelNumber");
		}
	}

	@Test(priority = 26)
	private void maxValidationSerialNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Serial Number Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.serialNumberValidation("MaxValidation");
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
			File file = new File("SerialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SerialValidation.png");
			maxValidation.clearFields("SerialNumber");
		}
	}

	@Test(priority = 27)
	private void dateValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Date Installed Field enter the Future Date");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.dateInstalledValidation("MaxValidation");
		String errorSerialNumberField = maxValidation.responseMessage("CustomerCreate");
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

	@Test(priority = 28)
	private void maxValidationAccessHoursField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Access Hours Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.accessHoursValidation("MaxValidation");
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
			File file = new File("AccessValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AccessValidation.png");
			maxValidation.clearFields("AccessHours");
		}
	}

	@Test(priority = 29)
	private void maxValidationInstallationNotesField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Installation Notes Field exceed its max-2048 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.installationNotesValidation("MaxValidation");
//		String errorAccessHours = maxValidation.errorField("InstallationNotes");
		String errorAccessHours = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAccessHours);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("InstallationNotes");
			maxValidation.clearFields("Previous");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InstallationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InstallationValidation.png");
			maxValidation.clearFields("InstallationNotes");
			maxValidation.clearFields("Previous");
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

	@Test(priority = 30)
	private void CreateContact() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify a new Customer Contact is created successfully through [Create Contact]");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		initElements.contactPage();
		initElements.propertyPage();
		initElements.equipmentPage("");
		String responseMessageCreateContact1 = initElements.responseMessage("CustomerCreate");
		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseMessageCreateContact1);
		extentTest.log(Status.INFO,
				"Expected Result create response messages is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact1.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
			initElements.responseMessage("AlternateFunction");
		}

	}

//	@Test(priority = 31)
	private void customerContactCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the Customer Contact Created Count is added in the Total Contact Count");
		CustomerCreateContactPage create = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		int actualTotal = create.listCountValidation(1);
		int expectedResult = create.listCountValidation(2);
		extentTest.log(Status.INFO, "Actual Result of Total Customer Contact Count is -" + actualTotal);
		extentTest.log(Status.INFO, "Expected Result of Total Customer Contact Count is -" + expectedResult);
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

	@Test(priority = 32)
	private void alreadyExistedMail() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify [Email Already Exists] Error is dispalyed when already existing mail ID is provided");
		CustomerCreateContactPage alreadyExisted = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		alreadyExisted.emailValidation("UniqueEmail");
//		String responseMessageCreateContact = alreadyExisted.errorField("Email");
		String responseMessageCreateContact = alreadyExisted.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessageCreateContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//			alreadyExisted.nextFunction();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AlreadyEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AlreadyEmailValidation.png");
		}
		alreadyExisted.nextFunction();
		listData = alreadyExisted.listValidation("ListName");
	}

	static String listData;

//	@Test(priority = 33)
	private void characterFilterValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Alphabets Filters in List Validation");
		CustomerCreateContactPage filter = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String listValidation = filter.listValidation("AlphabetFilter");
		listData = filter.listValidation("ListName");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listData)) {
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

	@Test(priority = 34)
	private void searchListNameField() throws IOException {
		extentTest = extentReports.createTest("Enter the Customer Contact Name:" + listData
				+ " in the Search field & Customer [Contact Name] list retrived successfully");
		CustomerCreateContactPage search = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		search.listValidation("SearchData");
		String listFirstName = search.listValidation("ListName");
		extentTest.log(Status.INFO, "Actual Result is -" + listData);
		extentTest.log(Status.INFO, "Expected Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SearchValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SearchValidation.png");
		}
		search.clearFields("Search");
		listData = search.listValidation("ListPhoneNumber");
	}

	@Test(priority = 35)
	private void searchListPhoneNumberField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the Customer Contact Phone Number:" + listData
				+ " in the Search field & Customer Contact [Phone Number] list retrived successfully");
		CustomerCreateContactPage search = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		search.listValidation("SearchData");
		String listFirstName = search.listValidation("ListPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + listData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SearchPhoneValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SearchPhoneValidation.png");
		}
		search.clearFields("Search");
		listData = search.listValidation("ListEmail");
	}

	@Test(priority = 36)
	private void searchListEmailField() throws IOException {
		extentTest = extentReports.createTest("Enter the Customer Contact Email:" + listData
				+ " in the Search field & Customer Contact [Email] list retrived successfully");
		CustomerCreateContactPage search = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		search.listValidation("SearchData");
		String listFirstName = search.listValidation("ListEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + listData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			search.clearFields("Search");
			listData = search.listValidation("LeadSource");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SearchEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SearchEmailValidation.png");
			search.clearFields("Search");
			listData = search.listValidation("LeadSource");
		}

	}

	@Test(priority = 37)
	private void filterField() throws IOException {
		extentTest = extentReports.createTest("Verify to Pick the Filter field & select Lead Source is:" + listData);
		CustomerCreateContactPage search = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		search.listValidation("FilterList");
		String listFirstName = search.listValidation("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + listData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("FilterValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("FilterValidation.png");
		}
		listData = search.listValidation("IndustryType");
	}

	@Test(priority = 37)
	private void filterIndustryField() throws IOException {
		extentTest = extentReports.createTest("Verify to Pick the Filter field & select Industry Type is:" + listData);
		CustomerCreateContactPage search = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		search.listValidation("IndustryFilter");
		String listFirstName = search.listValidation("IndustryType");
		extentTest.log(Status.INFO, "Actual Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + listData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(listData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			search.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("FilterValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("FilterValidation.png");
			search.clearFields("Search");
		}

	}

	@Test(priority = 38)
	private void searchInvalidListName() throws IOException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		CustomerCreateContactPage invalid = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		invalid.searchInvalidListName();
		String listFirstName = invalid.errorList();
		extentTest.log(Status.INFO, "Actual Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
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

	@Test(priority = 39)
	private void editLabel() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Customer Contact Page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String editContact = edit.LabelValidation("Edit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContactEditPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("ContactEditPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			edit.visibleName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Edit.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Edit.png");
			edit.visibleName();
		}

	}

	@Test(priority = 40)
	public void firstNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the First Name:" + CustomerCreateContactPage.firstName
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.firstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.firstName)) {
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

	@Test(priority = 41)
	public void lastNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Last Name:" + CustomerCreateContactPage.lastName
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.lastName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.lastName)) {
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

	@Test(priority = 42)
	public void leadSourcePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Lead Source Name:" + CustomerCreateContactPage.leadSources
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("LeadSources");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.leadSources);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.leadSources)) {
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

	@Test(priority = 42)
	public void industryTypePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Industry Type Name:" + CustomerCreateContactPage.IndustryTypes
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("IndustryType");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.IndustryTypes);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.IndustryTypes)) {
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

	@Test(priority = 43)
	public void emailPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Email:" + CustomerCreateContactPage.email
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.email);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.email)) {
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

//	@Test(priority = 44)
	public void jobTittlePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job Tittle:" + CustomerCreateContactPage.jobTittle
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("JobTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.jobTittle);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.jobTittle)) {
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

	@Test(priority = 44)
	public void taxNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Tax Number:" + CustomerCreateContactPage.taxNumber
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("TaxNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.taxNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.taxNumber)) {
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

	@Test(priority = 45)
	public void phoneNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Phone Number:" + CustomerCreateContactPage.phoneNumber
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.phoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.phoneNumber)) {
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

	@Test(priority = 46)
	public void propertyFirstNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property First Name:" + CustomerCreateContactPage.propertyFirstName
						+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("PropertyFirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.firstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.firstName)) {
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

	@Test(priority = 47)
	public void propertyLastNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Property Last Name:" + CustomerCreateContactPage.propertyLastName
						+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("PropertyLastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.lastName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.lastName)) {
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

	@Test(priority = 48)
	public void propertyNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Property Name:" + CustomerCreateContactPage.propertyName
				+ " is prepopulated in the customer contact form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("PropertyName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.propertyName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.propertyName)) {
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

	@Test(priority = 49)
	public void locationAddress1Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Property Location Address1:"
				+ CustomerCreateContactPage.address1 + " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("LocationAddress1");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.address1);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.address1)) {
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

	@Test(priority = 50)
	public void locationAddress2Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Property Location Address2:"
				+ CustomerCreateContactPage.address2 + " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("LocationAddress2");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.address2);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.address2)) {
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

	@Test(priority = 51)
	public void locationCityPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Property Location City:" + CustomerCreateContactPage.cityName
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("LocationCity");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.cityName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.cityName)) {
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

	@Test(priority = 52)
	public void locationStatePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Property Location State:"
				+ CustomerCreateContactPage.stateName + " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("LocationState");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.stateName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.stateName)) {
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

	@Test(priority = 53)
	public void locationZipcodePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Property Location Zipcode:"
				+ CustomerCreateContactPage.zipcode + " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.zipcode);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.zipcode)) {
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

	@Test(priority = 54)
	public void productNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Equipment Product Name:"
				+ CustomerCreateContactPage.productName + " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("ProductName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.productName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.productName)) {
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

	@Test(priority = 55)
	public void brandNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Equipment Brand Name:" + CustomerCreateContactPage.brandName
				+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("BrandName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.brandName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.brandName)) {
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

	@Test(priority = 56)
	public void modelNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Equipment Model Number:"
				+ CustomerCreateContactPage.modelNumber + " is prepopulated in the customer contact form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("ModelNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.modelNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.modelNumber)) {
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
	public void serialNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Equipment Serial Number:"
				+ CustomerCreateContactPage.serialNumber + " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("SerialNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.serialNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.serialNumber)) {
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
	public void dateInstalledPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Equipment Date Installed:"
				+ CustomerCreateContactPage.dateInstalled + " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("DateInstalled");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.dateInstalled);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.dateInstalled)) {
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

//	@Test(priority = 59)
	public void warrantyInformationPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Equipment Warranty Information:" + CustomerCreateContactPage.warrantyInformation
						+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("WarrantyInformation");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.warrantyInformation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.warrantyInformation)) {
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
	public void accessHoursPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Equipment Access Hours:"
				+ CustomerCreateContactPage.accessHours + " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("AccessHours");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.accessHours);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.accessHours)) {
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
	public void installationNotesPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Equipment Installation:" + CustomerCreateContactPage.installationNotes
						+ " is prepopulated in the customer contact edit form page");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String assertionMessage = edit.prepopulationFields("InstallationNotes");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CustomerCreateContactPage.installationNotes);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CustomerCreateContactPage.installationNotes)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			edit.clearFields("Previous");
			edit.clearAllFields("ContactPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
			edit.clearFields("Previous");
			edit.clearAllFields("ContactPage");
		}

	}

	@Test(priority = 62)
	private void editMandatoryValidation() throws AWTException, IOException {
		extentTest = extentReports.createTest(
				"Check Customer Contact Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CustomerCreateContactPage mandatoryValidation = PageFactory.initElements(driver,
				CustomerCreateContactPage.class);
		mandatoryValidation.firstNameValidation("MandatoryValidation");
//		String errorMandatoryValidation = mandatoryValidation.errorField("FirstName");
		String errorMandatoryValidation = mandatoryValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
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

	@Test(priority = 63)
	private void editMaxValidationFirstNameField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact First Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.firstNameValidation("MaxValidation");
//		String errorFirstName = maxValidation.errorField("FirstName");
		String errorFirstName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result - Maximum Validation First Name Field is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result - Maximum Validation First Name Field is -"
				+ getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("FirstName");
			maxValidation.clearFields("Name");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("FirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("FirstNameValidation.png");
			maxValidation.clearFields("FirstName");
			maxValidation.clearFields("Name");
		}

	}

	@Test(priority = 64)
	private void editMaxValidationLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Last Name Field exceed its max-256 limit");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.lastNameValidation("MaxValidation");
//		String errorFirstName = minValidation.errorField("LastName");
		String errorFirstName = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorFirstName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("LastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LastNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LastNameValidation.png");
			minValidation.clearFields("LastName");
		}
	}

//	@Test(priority = 65)
	private void editMaxValidationJobTittle() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Job Tittle Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.jobTittleValidation("MaxValidation");
//		String errorJobTittle = maxValidation.errorField("JobTittle");
		String errorJobTittle = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorJobTittle);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorJobTittle.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("JobTittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("JobTittleValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("JobTittleValidation.png");
			maxValidation.clearFields("JobTittle");
		}

	}

	@Test(priority = 66)
	private void editMaxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Email Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.emailValidation("MaxValidation");
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
			File file = new File("MaxEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxEmailValidation.png");
			maxValidation.clearFields("Email");
		}

	}

	@Test(priority = 67)
	private void editInvalidValidationEmailField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when Customer Contact invalid email is entered in Email Field");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.emailValidation("ValidEmail");
//		String errorEmail = maxValidation.errorField("Email");
		String errorEmail = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorEmail);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorEmail.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InvalidEmailValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InvalidEmailValidation.png");
			maxValidation.clearFields("Email");
		}
	}

	@Test(priority = 67)
	private void editmaxValidationTaxNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Customer Contact] Tax Number Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidationLocationField = PageFactory.initElements(driver,
				CustomerCreateContactPage.class);
		maxValidationLocationField.taxNumber("MaxValidation");
		String assertionMessage = maxValidationLocationField.errorMessage();
//		String assertionMessage = maxValidationLocationField.errorFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("TaxNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLastNameMaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLastNameMaxValidation.png");
			maxValidationLocationField.clearFields("TaxNumber");
		}
	}

	@Test(priority = 68)
	private void editSpecialCharacterPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when special character provided in Phone Number field");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.phoneNumberValidation("SpecialCharacter");
//		String errorPhoneNumber = minValidation.errorField("PhoneNumber");
		String errorPhoneNumber = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterPhoneNumber"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("SpecialCharacterPhoneNumber"))) {
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

	@Test(priority = 69)
	private void editInvalidPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when alphabetic character provided in Phone Number field");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.phoneNumberValidation("ValidPhoneNumber");
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

	@Test(priority = 70)
	private void editInValidationPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.phoneNumberValidation("MinValidation");
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
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 71)
	private void editMaxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Phone Number Field exceed its max-20 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.phoneNumberValidation("MaxValidation");
//		String errorPhoneNumber = maxValidation.errorField("PhoneNumber");
		String errorPhoneNumber = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PhoneNumber");
			maxValidation.nextButton();
			maxValidation.clearAllFields("PropertyPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxPhoneNumberValidation.png");
			maxValidation.clearFields("PhoneNumber");
			maxValidation.nextButton();
			maxValidation.clearAllFields("PropertyPage");
		}

	}

	@Test(priority = 72)
	private void editMaxValidationPropertyFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property First Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyFirstNameValidation("MaxValidation");
//		String errorPropertyName = maxValidation.errorField("PropertyFirstName");
		String errorPropertyName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPropertyName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyFirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyFirstNameValidation.png");
			maxValidation.clearFields("PropertyFirstName");
		}

	}

	@Test(priority = 73)
	private void editMaxValidationPropertyLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Last Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyLastNameValidation("MaxValidation");
//		String errorPropertyName = maxValidation.errorField("PropertyLastName");
		String errorPropertyName = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPropertyName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPropertyName.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyLastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyFirstNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyFirstNameValidation.png");
			maxValidation.clearFields("PropertyLastName");
		}

	}

	@Test(priority = 74)
	private void editMaxValidationPropertyNameField() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyNameValidation("MaxValidation");
//		String errorContactPerson = maxValidation.errorField("PropertyName");
		String errorContactPerson = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPerson);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPerson.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PropertyNameValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PropertyNameValidation.png");
			maxValidation.clearFields("PropertyName");
		}

	}

	@Test(priority = 75)
	private void editMaxValidationAddress1() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Address1 Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyAddress1Validation("MaxValidation");
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
			File file = new File("Address1Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Address1Validation.png");
			maxValidation.clearFields("PropertyAddress1");
		}

	}

	@Test(priority = 76)
	private void editMaxValidationAddress22Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Address2 Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyAddress2Validation("MaxValidation");
//		String errorAddress1Field = maxValidation.errorField("PropertyAddress2");
		String errorAddress1Field = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress1Field);
		extentTest.log(Status.INFO, "Expected Result Field is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Field.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("PropertyAddress2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Address2Validation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Address2Validation.png");
			maxValidation.clearFields("PropertyAddress2");
		}
	}

	@Test(priority = 77)
	private void editMaxValidationCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property City Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyCityValidation("MaxValidation");
//		String errorCityField = maxValidation.errorField("City");
		String errorCityField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorCityField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorCityField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("City");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CityValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CityValidation.png");
			maxValidation.clearFields("City");
		}

	}

	@Test(priority = 78)
	private void editMaxValidationStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property State Field exceed its max-45 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyStateValidation("MaxValidation");
//		String errorStateField = maxValidation.errorField("State");
		String errorStateField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorStateField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorStateField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("State");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("StateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("StateValidation.png");
			maxValidation.clearFields("State");
		}
	}

	@Test(priority = 79)
	private void editMinValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field");
		CustomerCreateContactPage minValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		minValidation.propertyZipcodeValidation("MinValidation");
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
			File file = new File("MinZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinZipcodeValidation.png");
			minValidation.clearFields("Zipcode");
		}
	}

	@Test(priority = 80)
	private void editMaxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Zipcode Field exceed its max-10 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyZipcodeValidation("MaxValidation");
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
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearFields("Zipcode");
		}
	}

	@Test(priority = 81)
	private void editSpecialCharacterZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Property Zipcode Field exceed its max-10 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.propertyZipcodeValidation("SpecialCharacter");
//		String errorZipcodeField = maxValidation.errorField("Zipcode");
		String errorZipcodeField = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorZipcodeField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorZipcodeField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");
			maxValidation.nextButton();
			maxValidation.clearAllFields("EquipmentPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MaxZipcodeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MaxZipcodeValidation.png");
			maxValidation.clearFields("Zipcode");
			maxValidation.nextButton();
			maxValidation.clearAllFields("EquipmentPage");
			maxValidation.clearAllFields("Previous");
		}
	}

	@Test(priority = 82)
	private void editMaxValidationProductNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Product Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.productNameValidation("MaxValidation");
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
			File file = new File("ProductValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ProductValidation.png");
			maxValidation.clearFields("ProductName");
		}
	}

	@Test(priority = 83)
	private void editMaxValidationBrandNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Brand Name Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.brandNameValidation("MaxValidation");
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
			File file = new File("BrandValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BrandValidation.png");
			maxValidation.clearFields("BrandName");
		}
	}

	@Test(priority = 84)
	private void editMaxValdidationModelNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Model Number Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.modelNumberValidation("MaxValidation");
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
			File file = new File("ModelValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ModelValidation.png");
			maxValidation.clearFields("ModelNumber");
		}
	}

	@Test(priority = 85)
	private void editMaxValidationSerialNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Serial Number Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.serialNumberValidation("MaxValidation");
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
			File file = new File("SerialValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SerialValidation.png");
			maxValidation.clearFields("SerialNumber");
		}
	}

	@Test(priority = 86)
	private void editDateValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Date Installed Field enter the Future Date");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.dateInstalledValidation("MaxValidation");
		String errorSerialNumberField = maxValidation.responseMessage("CustomerCreate");
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

	@Test(priority = 87)
	private void editMaxValidationAccessHoursField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Access Hours Field exceed its max-256 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.accessHoursValidation("MaxValidation");
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
			File file = new File("AccessValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AccessValidation.png");
			maxValidation.clearFields("AccessHours");
		}
	}

	@Test(priority = 88)
	private void editMaxValidationInstallationNotesField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Customer Contact Installation Notes Field exceed its max-2048 limit");
		CustomerCreateContactPage maxValidation = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		maxValidation.installationNotesValidation("MaxValidation");
//		String errorAccessHours = maxValidation.errorField("InstallationNotes");
		String errorAccessHours = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAccessHours);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAccessHours.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("InstallationNotes");
			maxValidation.previousButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InstallationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InstallationValidation.png");
			maxValidation.clearFields("InstallationNotes");
			maxValidation.previousButton();
		}
	}

	@Test(priority = 89)
	private void checkResponseCode() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Attacthment response code in customer contact module");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		initElements.attachmentFileCheck("CheckResponse");
		int responseCode = initElements.responseCode();
		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseCode);
		extentTest.log(Status.INFO, "Expected Result create response messages is -" + 200);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseCode == 200) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.attachmentFileCheck("ParentWindow");
			initElements.clearFields("Previous");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
			initElements.attachmentFileCheck("ParentWindow");
			initElements.clearFields("Previous");
		}
	}

	@Test(priority = 89)
	private void editContactList() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify updated successful message is displayed, when the Customer Contact Updated");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		initElements.contactPage();
		initElements.propertyPage();
		initElements.equipmentPage("SaveComplete");
		String responseMessageCreateContact1 = initElements.responseMessage("CustomerCreate");
		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseMessageCreateContact1);
		extentTest.log(Status.INFO,
				"Expected Result create response messages is -" + getPropertyValue("CustomerUpdatedMesssage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact1.equals(getPropertyValue("CustomerUpdatedMesssage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Edit.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Edit.png");
			initElements.responseMessage("AlternateFunction");
		}

	}

	@Test(priority = 90)
	private void deleteContactList() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify deleted successful message is displayed, when the Customer Contact Deleted");
		CustomerCreateContactPage edit = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		edit.deleteContact();
		String responseMessageCreateContact = edit.responseMessage("CustomerCreate");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessageCreateContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerDeletedMessage"));
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
