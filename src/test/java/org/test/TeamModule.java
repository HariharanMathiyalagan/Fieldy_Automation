package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.github.javafaker.Faker;
import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.CreateUserPage;
import com.zaigo.pageobjects.CustomerCreateOrganizationPage;
import com.zaigo.pageobjects.EditContractorCompaniesPage;
import com.zaigo.pageobjects.EditDetailScreenCompaniesPage;
import com.zaigo.pageobjects.EditUserPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.SendInvitePage;
import com.zaigo.utility.BrowserSetup;

public class TeamModule extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("TeamModule.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();

	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}

	@Test(priority = 1)
	public void verify() throws IOException {
		// Single Account User
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValue("UserName"));
		loginInPage.passwordField(getPropertyValue("Password"));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("1.png");
		}
	}

	@Test(priority = 2)
	public void teamModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Details Screen Tenant Name");
		EditDetailScreenCompaniesPage module = new EditDetailScreenCompaniesPage(driver);
		module.modulePage();
		String assertTittle = module.assertTittle();
		extentTest.log(Status.INFO, "Actual Result is -" + assertTittle);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TenantOrganizationName"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertTittle.equals(getPropertyValue("TenantOrganizationName"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("2.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("2.png");
		}

	}

	@Test(priority = 3)
	public void edit() throws InterruptedException {
		extentTest = extentReports.createTest("Navigate to Contractor Location Page");
		EditDetailScreenCompaniesPage edit = new EditDetailScreenCompaniesPage(driver);
		edit.editContent();
	}

	@Test(priority = 4)
	private void addLocation() throws IOException {
		extentTest = extentReports.createTest("Verify the Company Information Updated Successfully");
		EditDetailScreenCompaniesPage add = new EditDetailScreenCompaniesPage(driver);
		add.addLocation();
		String responseMessage = add.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedTenantMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("UpdatedTenantMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("3.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("3.png");
		}
		String assertTittle = add.assertTittle();
		Assert.assertEquals(assertTittle, getPropertyValue("TenantOrganizationName"));
	}

	@Test(priority = 5)
	private void deleteLocation() throws IOException {
		extentTest = extentReports.createTest("Verify the Company Location Deleted Successfully");
		EditDetailScreenCompaniesPage delete = new EditDetailScreenCompaniesPage(driver);
		delete.deleteLocation();
		String deleteMessage = delete.deleteMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + deleteMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TenantLocationDeleteMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (deleteMessage.equals(getPropertyValue("TenantLocationDeleteMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("4.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("4.png");
		}
		String assertTittle = delete.assertTittle();
		Assert.assertEquals(assertTittle, getPropertyValue("TenantOrganizationName"));
	}

//	@Test(priority = 6)
//	private void maxSizeProfileField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Contractor Company Maximum Size Profile Field");
//		CreateContractorPage maxSizeProfile = new CreateContractorPage(this.driver);
//		maxSizeProfile.clickContractors();
////		maxSizeProfile.createContractorButtonj();
//		maxSizeProfile.maxSizeLogoValidation();
//		String errorLogo = maxSizeProfile.errorLogo();
//		extentTest.log(Status.INFO, "Actual Result is -" + errorLogo);
//		extentTest.log(Status.INFO, "Expected Result is -" + MaxSizeLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorLogo.equals(MaxSizeLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("5.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("5.png");
//		}
//
//	}

//	@Test(priority = 7)
//	private void fileFormatvalidation() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Contractor Company File Format in Profile Field");
//		CreateContractorPage maxSizeProfile = new CreateContractorPage(this.driver);
//		maxSizeProfile.formatLogoValidation();
//		String errorLogo = maxSizeProfile.fileLogoError();
//		extentTest.log(Status.INFO, "Actual Result is -" + errorLogo);
//		extentTest.log(Status.INFO, "Expected Result is -" + FormatValidationLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorLogo.equals(FormatValidationLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("6.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("6.png");
//		}
//
//	}

	@Test(priority = 6)
	public void verifyContractorNameMandatory() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Check Company Contractor Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.clickContractors();
		contractorPage.clickSaveandComplete();
		String error_text = contractorPage.contractorNameError();
		extentTest.log(Status.INFO, "Actual Result is -" + error_text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (error_text.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("7.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("7.png");
		}

	}

	@Test(priority = 7)
	public void verifyContractorNumberMinValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Company Contractor form]");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.contractorName("Contractor X");
		contractorPage.contractorPhone("123");
		contractorPage.clickSaveandComplete();
		String email_error = contractorPage.contractorPhoneError();
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("8.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("8.png");
		}

	}

	@Test(priority = 8)
	public void verifyContractorEmailFormat() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Compamy Contrctor form] invalid email is entered in Email Field");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.clearPhone();
		contractorPage.contractorEmail("aibcda");
		contractorPage.clickSaveandComplete();
		String email_error = contractorPage.contractorEmailError();
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("9.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("9.png");
		}

	}

	@Test(priority = 9)
	public void verifyContractorNumberMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Company Contractor Phone Number Field exceed its max-20 limit");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.clearContractorEmail();
		contractorPage.contractorPhone("12312312311890021344535564532212323432113");
		contractorPage.clickSaveandComplete();
		String email_error = contractorPage.contractorPhoneError();
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("10.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("10.png");
		}

	}

	@Test(priority = 10)
	public void verifyFaxMinValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Fax field of [Company Contractor form]");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.clearPhone();
		contractorPage.contractorFax("77");
		String fax_error = contractorPage.contractorFaxError();
		extentTest.log(Status.INFO, "Actual Result is -" + fax_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (fax_error.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("11.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("11.png");
		}

	}

	@Test(priority = 11)
	public void verifyFaxMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Company Contractor Fax Name Field exceed its max-20 limit");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.clearPhone();
		contractorPage.contractorFax("777777777777777778888888888888877777777777777777777777");
		contractorPage.clickSaveandComplete();
		String fax_error = contractorPage.contractorFaxError();
		extentTest.log(Status.INFO, "Actual Result is -" + fax_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (fax_error.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("12.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("12.png");
		}
	}

	@Test(priority = 12)
	public void verifyCpersonFirstNameMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] First Name Field exceed its max-256 limit");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.clearFax();
		contractorPage.contractorContactPersonFirstName(getPropertyValue("256Characters"));
		contractorPage.clickSaveandComplete();
		String cperson_error = contractorPage.contractorContactFirstNameError();
		extentTest.log(Status.INFO, "Actual Result is -" + cperson_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (cperson_error.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearContractorPersonFirstName();
			contractorPage.clearContractorEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("13.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("13.png");
			contractorPage.clearContractorPersonFirstName();
			contractorPage.clearContractorEmail();
		}

	}

	@Test(priority = 13)
	public void verifyCpersonLastNameMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] Last Name Field exceed its max-256 limit");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.clearFax();
		contractorPage.contractorContactPersonLastName(getPropertyValue("256Characters"));
		contractorPage.clickSaveandComplete();
		String cperson_error = contractorPage.contractorContactLastNameError();
		extentTest.log(Status.INFO, "Actual Result is -" + cperson_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (cperson_error.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearContractorPersonLastName();
			contractorPage.clearContractorEmail();
//			driver.quit();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("13.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("13.png");
			contractorPage.clearContractorPersonLastName();
			contractorPage.clearContractorEmail();
//			driver.quit();
		}

	}

	@Test(priority = 14)
	private void maxValidationLocationNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] Location Name Field exceed its max-256 limit");
		CreateContractorPage maxValidationLocationField = new CreateContractorPage(this.driver);
		maxValidationLocationField.maxValidationLocationName();
		String assertionMessage = maxValidationLocationField.locationErrorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearLocationName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("14.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("14.png");
			maxValidationLocationField.clearLocationName();
		}

	}

	@Test(priority = 15)
	private void validateEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Compamy Contrctor Location Page] invalid email is entered in Email Field");
		CreateContractorPage validateEmail = new CreateContractorPage(this.driver);
		validateEmail.validateEmail();
		String emailErrorMessage = validateEmail.emailErrorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + emailErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (emailErrorMessage.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validateEmail.clearEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("15.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("15.png");
			validateEmail.clearEmail();
		}

	}

	@Test(priority = 16)
	private void maxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] Email Field exceed its max-256 limit");
		CreateContractorPage maxValidation = new CreateContractorPage(this.driver);
		maxValidation.maxValidationEmail();
		String emailErrorMessage = maxValidation.emailErrorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + emailErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (emailErrorMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("16.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("16.png");
			maxValidation.clearEmail();
		}

	}

	@Test(priority = 17)
	private void minValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Company Contractor Location Page]");
		CreateContractorPage minValidation = new CreateContractorPage(this.driver);
		minValidation.minValidationPhoneNumber();
		String phoneNumberErrorMessage = minValidation.phoneNumberErrorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + phoneNumberErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (phoneNumberErrorMessage.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearPhoneNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("17.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("17.png");
			minValidation.clearPhoneNumber();
		}

	}

	@Test(priority = 18)
	private void maxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] Location Phone Number Field exceed its max-20 limit");
		CreateContractorPage minValidation = new CreateContractorPage(this.driver);
		minValidation.maxValidationPhoneNumberField();
		String phoneNumberErrorMessage = minValidation.phoneNumberErrorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + phoneNumberErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (phoneNumberErrorMessage.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearPhoneNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("18.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("18.png");
			minValidation.clearPhoneNumber();
		}

	}

	@Test(priority = 19)
	private void maxValidationAddress1Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] Address1 Field exceed its max-256 limit");
		CreateContractorPage maxValidation = new CreateContractorPage(this.driver);
		maxValidation.maxValidationAddress1();
		String errorAddress1Message = maxValidation.errorAddress1Message();
		extentTest.log(Status.INFO, "Actuals Result is -" + errorAddress1Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearAddress1();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("19.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("19.png");
		}

	}

	@Test(priority = 20)
	private void maxValidationAddress2Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] Address2 Field exceed its max-256 limit");
		CreateContractorPage maxValidation = new CreateContractorPage(this.driver);
		maxValidation.maxValidationAddress2();
		String errorAddress2Message = maxValidation.errorAddress2Message();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearAddress2();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("20.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("20.png");
		}

	}

	@Test(priority = 21)
	private void maxValidationStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] State Name Field exceed its max-45 limit");
		CreateContractorPage maxValidation = new CreateContractorPage(this.driver);
		maxValidation.maxValidationStateName();
		String errorAddress2Message = maxValidation.errorStateNameMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearStateName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("21.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("21.png");
		}

	}

	@Test(priority = 22)
	private void maxValidationCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] City Name Field exceed its max-256 limit");
		CreateContractorPage maxValidation = new CreateContractorPage(this.driver);
		maxValidation.maxValidationCityName();
		String errorAddress2Message = maxValidation.errorCityNameMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearCityName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("22.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("22.png");
		}

	}

	@Test(priority = 23)
	private void maxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] Zipcode Field exceed its max-10 limit");
		CreateContractorPage maxValidation = new CreateContractorPage(this.driver);
		maxValidation.maxValidationZipCode();
		String errorAddress2Message = maxValidation.errorZipCodeMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearZipCode();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("23.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("23.png");
			maxValidation.clearZipCode();
		}

	}

	@Test(priority = 24)
	private void minValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Company Contractor Location Page]");
		CreateContractorPage maxValidation = new CreateContractorPage(this.driver);
		maxValidation.minValidationZipCode();
		String errorAddress2Message = maxValidation.errorZipCodeMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearZipCode();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("24.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("24.png");
			maxValidation.clearZipCode();
		}

	}

	@Test(priority = 25)
	private void characterValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the special charcter validation in Zipcode field");
		CreateContractorPage maxValidation = new CreateContractorPage(this.driver);
		maxValidation.specialCharacterValidationZipCode();
		String errorAddress2Message = maxValidation.errorZipCodeMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearZipCode();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("24.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("24.png");
			maxValidation.clearZipCode();

		}

	}

	@Test(priority = 26)
	private void maxValidationContactPerson() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Comapany] Location Contact Person Name Field exceed its max-256 limit");
		CreateContractorPage maxContactPerson = new CreateContractorPage(this.driver);
		maxContactPerson.maxValidationContactPerson();
		String errorContactPersonMessage = maxContactPerson.errorContactPersonMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPersonMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max512CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPersonMessage.equals(getPropertyValue("Max512CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxContactPerson.clearContactPersons();
			maxContactPerson.previousButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("25.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("25.png");
			maxContactPerson.clearContactPersons();
			maxContactPerson.previousButton();
		}
	}

	@Test(priority = 27)
	public void verifySaveButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Contractor Company Save & Complete Button is displayed or Not");
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		String text_button = contractorPage.getButtonText();
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + "Save & Complete");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals("Save & Complete")) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
		}

	}

	@Test(priority = 28)
	public void createContract() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the Contractor Company has Successfully Created");
		CreateContractorPage create = new CreateContractorPage(driver);
		create.CreateContractor();
		create.Location();
//		driver.quit();
		String asssertCreate = create.asssertCreate();
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + "Contractor have been created successfully");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals("Contractor have been created successfully")) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
		}

	}

	@Test(priority = 29)
	private void companiesContractorCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the Companies Contractor Created Count is added in the Total Contractor Count");
		CreateContractorPage create = new CreateContractorPage(driver);
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
			File file = new File("CompaniesContractorCount.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CompaniesContractorCount.png");
		}

	}

	@Test(priority = 30)
	public void contractotCompanyNameExistedValidation() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest(
				"Verify [Contractor Company Name Already Exists] ContractorCompany form, Error is dispalyed when already existing Contractor Company Name is provided");
		CreateContractorPage create = new CreateContractorPage(driver);
		create.alreadyExistsValidation();
		String asssertCreate = create.contractorNameError();
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ExistedCompanyName"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals(getPropertyValue("ExistedCompanyName"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.backPage();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
			create.backPage();
		}

	}

	@Test(priority = 31)
	private void contractorAlreadyEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify [Email Already Exists] Contactor Company form, Error is dispalyed when already existing Email Name is provided");
		CreateContractorPage create = new CreateContractorPage(driver);
		create.alreadyEamil();
		String asssertCreate = create.errorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ExistedCompanyName"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals(getPropertyValue("ExistedCompanyName"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.backPage();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
			create.backPage();
		}

	}

	@Test(priority = 32)
	public void listValidationName() throws IOException {
		extentTest = extentReports.createTest("Verify the Contractor Company List Validation");
		CreateContractorPage listValidation = new CreateContractorPage(driver);
		String validation = listValidation.validation();
		extentTest.log(Status.INFO, "Actual Result is -" + validation);
		extentTest.log(Status.INFO, "Expected Result is -" + validation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (validation.equals(validation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("28.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("28.png");
		}

	}

	@Test(priority = 33)
	private void searchCompanyNameListValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Contractor Company Search Company Name List Validation");
		CreateContractorPage listValidation = new CreateContractorPage(driver);
		String searchListCompanyNameValidation = listValidation.searchListCompanyNameValidation();
		String listCompanyName = listValidation.listCompanyName();
		extentTest.log(Status.INFO, "Actual Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Expected Result is -" + searchListCompanyNameValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listCompanyName.equals(searchListCompanyNameValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidation.clearSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("29.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("29.png");
			listValidation.clearSearchField();
		}

	}

	@Test(priority = 34)
	private void searchNameListValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Contractor Company Search Name List Validation");
		CreateContractorPage listValidation = new CreateContractorPage(driver);
		String searchListCompanyNameValidation = listValidation.searchListNameValidation();
		String listCompanyName = listValidation.listName();
		extentTest.log(Status.INFO, "Actual Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Expected Result is -" + searchListCompanyNameValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listCompanyName.equals(searchListCompanyNameValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidation.clearSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("30.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("30.png");
			listValidation.clearSearchField();
		}

	}

	@Test(priority = 35)
	private void searchEmailListValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Contractor Company Search Email List Validation");
		CreateContractorPage listValidation = new CreateContractorPage(driver);
		String searchListCompanyNameValidation = listValidation.searchEmailListValidation();
		String listCompanyName = listValidation.listEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Expected Result is -" + searchListCompanyNameValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listCompanyName.equals(searchListCompanyNameValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidation.clearSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("31.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("31.png");
			listValidation.clearSearchField();
		}

	}

	@Test(priority = 36)
	private void searchPhoneNumberListValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Contractor Company Search Email List Validation");
		CreateContractorPage listValidation = new CreateContractorPage(driver);
		String searchListCompanyNameValidation = listValidation.searchPhoneNumberListValidation();
		String listCompanyName = listValidation.listPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Expected Result is -" + searchListCompanyNameValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listCompanyName.equals(searchListCompanyNameValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidation.clearSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("32.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("32.png");
			listValidation.clearSearchField();
		}

	}

	@Test(priority = 37)
	public void invalidValidationData() throws IOException {
		extentTest = extentReports.createTest("Verify the Contractor Company Invalid List Valdiation");
		CreateContractorPage errorValidation = new CreateContractorPage(driver);
		errorValidation.invalidData("asfvcsv");
		String invlaidValidate = errorValidation.invlaidValidate();
		extentTest.log(Status.INFO, "Actual Result is -" + invlaidValidate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (invlaidValidate.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			errorValidation.reserOption();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("33.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("33.png");
			errorValidation.reserOption();
		}

	}

	@Test(priority = 38)
	private void editContractorPage() throws IOException {
		extentTest = extentReports.createTest("Verify the User Land on the Edit Page");
		EditContractorCompaniesPage companiesPage = new EditContractorCompaniesPage(driver);
		String editContractor = companiesPage.editContractor();
		extentTest.log(Status.INFO, "Actual Result is -" + editContractor);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorEditPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContractor.equals(getPropertyValue("CompanyContractorEditPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("34.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("34.png");
		}

	}

	@Test(priority = 39)
	private void editContractorDetails() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the User to Edit the Contractor Details and Successfully Updated");
		EditContractorCompaniesPage companiesPage = new EditContractorCompaniesPage(driver);
		companiesPage.editContractorDetails();
		String responseMessage = companiesPage.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("CompanyContractorUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("35.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("35.png");
		}

	}

	@Test(priority = 40)
	private void deleteContractorDetails() throws IOException {
		extentTest = extentReports
				.createTest("Verify the User to Delete the Contractor Details and Successfully Deleted");
		EditContractorCompaniesPage companiesPage = new EditContractorCompaniesPage(driver);
		String deleteFunction = companiesPage.deleteFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + deleteFunction);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ComapanyContractorDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (deleteFunction.equals(getPropertyValue("ComapanyContractorDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("36.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("36.png");
		}

	}

	@Test(priority = 41)
	private void landingUserFormPage() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Land on User Create Form Page");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.userPageLanding();
		String landingUserFormPage = landing.landingUserFormPage();
		extentTest.log(Status.INFO, "Actual Result is -" + landingUserFormPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamCreateUserPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (landingUserFormPage.equals(getPropertyValue("TeamCreateUserPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("37.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("37.png");
		}

	}

//	@Test(priority = 40)
//	private void maxSizeLogoField() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the Maximum Size Validation Profile Field");
//		CreateUserPage landing = new CreateUserPage(driver);
//		landing.maxSizeLogo();
//		String errorLogo = landing.errorLogo();
//		extentTest.log(Status.INFO, "Actual Result - Maximum Size Validation is -" + errorLogo);
//		extentTest.log(Status.INFO, "Expected Result - Maximum Size Validation is -" + MaxSizeLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorLogo.equals(MaxSizeLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("38.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("38.png");
//		}
//	}

//	@Test(priority = 41)
//	private void fileFormatValidation() throws InterruptedException, AWTException, IOException {
//		extentTest = extentReports.createTest("Verify the File Format Validation Profile Field");
//		CreateUserPage landing = new CreateUserPage(driver);
//		landing.fileFormatLogo();
//		String errorLogo = landing.errorFormatLogo();
//		extentTest.log(Status.INFO, "Actual Result - File Format Validation is -" + errorLogo);
//		extentTest.log(Status.INFO, "Expected Result - File Format Validation is -" + FormatValidationLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorLogo.equals(FormatValidationLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("39.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("39.png");
//		}
//
//	}

	@Test(priority = 42)
	private void mandatoryValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Creation form page, First Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateUserPage landing = new CreateUserPage(driver);
		String mandatoryValidationFirstNameField = landing.mandatoryValidationFirstNameField();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("40.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("40.png");
		}

	}

	@Test(priority = 43)
	private void mandatoryValidationTypeField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User creation form page, User Type field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateUserPage landing = new CreateUserPage(driver);
		String mandatoryValidationFirstNameField = landing.mandatoryValidationTypeField();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("42.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("42.png");
		}

	}

	@Test(priority = 44)
	private void mandatoryValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User creation form page, Email field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateUserPage landing = new CreateUserPage(driver);
		String mandatoryValidationFirstNameField = landing.mandatoryValidationEmailField();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("41.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("41.png");
		}

	}

	@Test(priority = 45)
	private void maxValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] First Name Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationFirstName();
		String mandatoryValidationFirstNameField = landing.errorFirstName();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearFirstName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("43.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("43.png");
			landing.clearFirstName();
		}

	}

	@Test(priority = 46)
	private void maxValidationLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Last Name Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationLastName();
		String mandatoryValidationFirstNameField = landing.errorLastName();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearLastName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("44.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("44.png");
			landing.clearLastName();
		}

	}

	@Test(priority = 47)
	private void maxValidationJobTittleField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Job Tittle Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationJobTittle();
		String mandatoryValidationFirstNameField = landing.errorJobTittle();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearJobTittle();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("45.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("45.png");
			landing.clearJobTittle();
		}

	}

	@Test(priority = 48)
	private void maxValidationEmailFields() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Job Tittle Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationEmail();
		String mandatoryValidationFirstNameField = landing.errorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("46.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("46.png");
			landing.clearEmail();
		}

	}

	@Test(priority = 49)
	private void invalidEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Team User form] invalid email is entered in Email Field");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.invalidEmail();
		String mandatoryValidationFirstNameField = landing.errorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearEmail();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("47.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("47.png");
			landing.clearEmail();

		}

	}

	@Test(priority = 50)
	private void minValidatonPhoneNumber() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Team User form]");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.minValidationPhoneNumber();
		String mandatoryValidationFirstNameField = landing.errorPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearPhoneNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("48.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("48.png");
			landing.clearPhoneNumber();

		}

	}

	@Test(priority = 51)
	private void maxValidatonPhoneNumber() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Phone Number Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationPhoneNumber();
		String mandatoryValidationFirstNameField = landing.errorPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearPhoneNumber();
			landing.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("49.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("49.png");
			landing.clearPhoneNumber();
			landing.nextButton();
		}

	}

	@Test(priority = 52)
	private void maxValidationLocationName() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Location Name Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationLocationName();
		String mandatoryValidationFirstNameField = landing.errorLocatioName();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearLocationName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("50.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("50.png");
			landing.clearLocationName();
		}

	}

	@Test(priority = 53)
	private void maxValidationAddress1() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Address1 Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationAddress1();
		String mandatoryValidationFirstNameField = landing.errorAddress1();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearAddress1();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("51.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("51.png");
			landing.clearAddress1();
		}

	}

	@Test(priority = 54)
	private void maxValidationAddress2() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Address2 Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationAddress2();
		String mandatoryValidationFirstNameField = landing.errorAddress2();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearAddress2();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("52.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("52.png");
			landing.clearAddress2();
		}

	}

	@Test(priority = 55)
	private void maxValidationState() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] State Name Field exceed its max-45 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationState();
		String mandatoryValidationFirstNameField = landing.errorState();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearState();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("53.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("53.png");
			landing.clearState();
		}

	}

	@Test(priority = 56)
	private void maxValidationCity() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] City Name Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationCity();
		String mandatoryValidationFirstNameField = landing.errorCity();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearCity();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("54.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("54.png");
			landing.clearCity();
		}

	}

	@Test(priority = 57)
	private void minValidationZipcode() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Team User form]");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.minValidationZipcode();
		String mandatoryValidationFirstNameField = landing.errorZipcode();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearZipcode();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("55.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("55.png");
			landing.clearZipcode();
		}

	}

	@Test(priority = 58)
	private void specialCharacterValidationZipcode() throws IOException {
		extentTest = extentReports.createTest("Verify the Special Character Validation Zipcode Field");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.specialCharacterZipcode();
		String mandatoryValidationFirstNameField = landing.errorZipcode();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearZipcode();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("56.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("56.png");
			landing.clearZipcode();
		}

	}

	@Test(priority = 59)
	private void maxValidationZipcode() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when [Team User] Zipcode Field exceed its max-10 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationZipcode();
		String mandatoryValidationFirstNameField = landing.errorZipcode();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearZipcode();
			landing.previousButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("57.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("57.png");
			landing.clearZipcode();
			landing.previousButton();
		}

	}

	@Test(priority = 60)
	private void userCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User Form Create Successfully");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.basicpage();
		landing.locationpage();
		String createMessage = landing.createMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UserCreatedMessgae"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("UserCreatedMessgae"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");

		}

	}

	@Test(priority = 61)
	private void createdListName() throws IOException {
		extentTest = extentReports.createTest("Verify the User Created in the List Page");
		CreateUserPage landing = new CreateUserPage(driver);
		String createUserList = landing.createUserList();
		extentTest.log(Status.INFO, "Actual Result is -" + createUserList);
		extentTest.log(Status.INFO, "Expected Result is -" + createUserList);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createUserList.equals(createUserList)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("59.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("59.png");
		}
	}

	@Test(priority = 62)
	private void alreadyExistedMailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify [Email Already Exists] User form, Error is dispalyed when already existing Email is provided");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.getEmaiList();
		String createUserList = landing.alreadyEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + createUserList);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createUserList.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("60.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("60.png");
		}
	}

	@Test(priority = 63)
	private void searchNameValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the First Name in Search Field then the List is Appear");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchName = landing.searchName();
		String listFirstName = landing.listFirstName();
		extentTest.log(Status.INFO, "Actual Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Expected Result is -" + searchName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listFirstName.equals(searchName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearUserSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("61.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("61.png");
			landing.clearUserSearchField();
		}

	}

	@Test(priority = 63)
	private void searchPhoneNumberValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Phone Number in Search Field then the List is Appear");
		CreateUserPage landing = new CreateUserPage(driver);
//		Thread.sleep(5000);
		String searchPhoneNumber = landing.searchPhoneNumber();
		String listPhoneNumber = landing.listPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listPhoneNumber.equals(searchPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearUserSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("62.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("62.png");
			landing.clearUserSearchField();
		}

	}

	@Test(priority = 64)
	private void searchEmailValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Email in Search Field then the List is Appear");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchPhoneNumber = landing.searchEmail();
		String listPhoneNumber = landing.listEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listPhoneNumber.equals(searchPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearUserSearchField();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("63.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("63.png");
			landing.clearUserSearchField();
		}

	}

	@Test(priority = 65)
	private void invalidDataValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Invlaid data in Search Field");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchPhoneNumber = landing.searchInvalid();
		extentTest.log(Status.INFO, "Actual Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchPhoneNumber.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.resetButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("64.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("64.png");
			landing.resetButton();
		}
	}

	@Test(priority = 66)
	private void landingOnEditForm() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Land on Edit Page and Validate the Label");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchPhoneNumber = landing.editPage();
		extentTest.log(Status.INFO, "Actual Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamUserEditPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchPhoneNumber.equals(getPropertyValue("TeamUserEditPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("65.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("65.png");
		}

	}

	@Test(priority = 67)
	private void editUserDetails() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify to Edit the User Details & Form Submission");
		CreateUserPage landing = new CreateUserPage(driver);
		String editUserDetails = landing.editUserDetails();
		extentTest.log(Status.INFO, "Actual Result is -" + editUserDetails);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UserUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editUserDetails.equals(getPropertyValue("UserUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("66.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("66.png");
		}

	}

	@Test(priority = 68)
	private void deleteUserDetails() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify to Delete the User Details");
		CreateUserPage landing = new CreateUserPage(driver);
		String editUserDetails = landing.deleteUserDetails();
		extentTest.log(Status.INFO, "Actual Result is -" + editUserDetails);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UserDeleteMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editUserDetails.equals(getPropertyValue("UserDeleteMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("67.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("67.png");
		}

	}

	@Test(priority = 69)
	private void landingContractorFormPage() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Landing on Contractor Form Page");
		CreateUserPage landing = new CreateUserPage(driver);
		String landingContractorFormPage = landing.landingContractorFormPage();
		extentTest.log(Status.INFO, "Actual Result is -" + landingContractorFormPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamUserCreateContractorPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (landingContractorFormPage.equals(getPropertyValue("TeamUserCreateContractorPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("68.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("68.png");
		}

	}

//	@Test(priority = 70)
//	private void contractorMaximumSizeLogoValidation() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify to Validate the Contractor Page Max Size Logo");
//		CreateUserPage landing = new CreateUserPage(driver);
//		landing.maxSizeLogo();
//		String errorLogo = landing.errorLogo();
//		extentTest.log(Status.INFO, "Actual Result - Maximum Size Validation is -" + errorLogo);
//		extentTest.log(Status.INFO, "Expected Result - Maximum Size Validation is -" + MaxSizeLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorLogo.equals(MaxSizeLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("69.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("69.png");
//		}
//	}

//	@Test(priority = 71)
//	private void fileFormatValidations() throws InterruptedException, AWTException, IOException {
//		extentTest = extentReports.createTest("Verify to Validate the Contractor Page File Format Profile Field");
//		CreateUserPage landing = new CreateUserPage(driver);
//		landing.fileFormatLogo();
//		String errorLogo = landing.errorFormatLogo();
//		extentTest.log(Status.INFO, "Actual Result - Maximum Size Validation is -" + errorLogo);
//		extentTest.log(Status.INFO, "Expected Result - Maximum Size Validation is -" + FormatValidationLogo);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorLogo.equals(FormatValidationLogo)) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("70.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("70.png");
//		}
//
//	}

	@Test(priority = 72)
	private void mandatoryValidationContractorFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Contractor creation form page, First Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateUserPage landing = new CreateUserPage(driver);
		String mandatoryValidationFirstNameField = landing.mandatoryValidationFirstNameField();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("71.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("71.png");
		}

	}

	@Test(priority = 73)
	private void mandatoryValidationContractorEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Contractor creation form page, Email field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateUserPage landing = new CreateUserPage(driver);
		String mandatoryValidationFirstNameField = landing.mandatoryValidationEmailField();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("72.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("72.png");
		}
	}

	@Test(priority = 74)
	private void mandatoryOrganizationName() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Check the User Contractor creation form page, Company Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateUserPage landing = new CreateUserPage(driver);
		String mandatoryValidationFirstNameField = landing.mandatoryValidationOrganizationField();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("73.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("73.png");
		}

	}

	@Test(priority = 75)
	private void maxValidationContractorFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] First Name Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationFirstName();
		String mandatoryValidationFirstNameField = landing.errorFirstName();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearFirstName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("74.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("74.png");
			landing.clearFirstName();
		}
	}

	@Test(priority = 76)
	private void maxValidationContractorLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Last Name Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationLastName();
		String mandatoryValidationFirstNameField = landing.errorLastName();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearLastName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("75.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("75.png");
			landing.clearLastName();
		}

	}

	@Test(priority = 77)
	private void invalidContractorEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Team User Contrctor form] invalid email is entered in Email Field");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.invalidEmail();
		String mandatoryValidationFirstNameField = landing.errorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearEmail();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("76.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("76.png");
			landing.clearEmail();

		}

	}

	@Test(priority = 78)
	private void maxValidationContractorEmailFields() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Email Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationEmail();
		String mandatoryValidationFirstNameField = landing.errorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearEmail();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("77.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("77.png");
			landing.clearEmail();
		}

	}

	@Test(priority = 79)
	private void minValidatonContractorPhoneNumber() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Team User Contactor form]");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.minValidationPhoneNumber();
		String mandatoryValidationFirstNameField = landing.errorPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearPhoneNumber();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("78.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("78.png");
			landing.clearPhoneNumber();

		}

	}

	@Test(priority = 80)
	private void maxValidatonContractorPhoneNumber() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Phone Number Field exceed its max-20 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationPhoneNumber();
		String mandatoryValidationFirstNameField = landing.errorPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearPhoneNumber();
			landing.nextButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("79.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("79.png");
			landing.clearPhoneNumber();
			landing.nextButton();
		}

	}

	@Test(priority = 81)
	private void maxValidationContractorLocationName() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Location Name Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationLocationName();
		String mandatoryValidationFirstNameField = landing.errorLocatioName();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearLocationName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("80.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("80.png");
			landing.clearLocationName();
		}

	}

	@Test(priority = 82)
	private void maxValidationContractorAddress1() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Address1 Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationAddress1();
		String mandatoryValidationFirstNameField = landing.errorAddress1();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearAddress1();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("81.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("81.png");
			landing.clearAddress1();
		}

	}

	@Test(priority = 83)
	private void maxValidationContractorAddress2() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Address2 Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationAddress2();
		String mandatoryValidationFirstNameField = landing.errorAddress2();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearAddress2();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("82.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("82.png");
			landing.clearAddress2();
		}

	}

	@Test(priority = 84)
	private void maxValidationContractorState() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] State Name Field exceed its max-45 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationState();
		String mandatoryValidationFirstNameField = landing.errorState();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearState();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("83.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("83.png");
			landing.clearState();
		}

	}

	@Test(priority = 85)
	private void maxValidationContractorCity() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] City Name Field exceed its max-256 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationCity();
		String mandatoryValidationFirstNameField = landing.errorCity();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearCity();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("84.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("84.png");
			landing.clearCity();
		}

	}

	@Test(priority = 86)
	private void minValidationContractorZipcode() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Team User Contractor form]");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.minValidationZipcode();
		String mandatoryValidationFirstNameField = landing.errorZipcode();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearZipcode();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("85.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("85.png");
			landing.clearZipcode();
		}

	}

	@Test(priority = 87)
	private void specialCharacterValidationContractorZipcode() throws IOException {
		extentTest = extentReports.createTest("Verify the Special Character Validation Contractor Zipcode Field");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.specialCharacterZipcode();
		String mandatoryValidationFirstNameField = landing.errorZipcode();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearZipcode();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("86.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("86.png");
			landing.clearZipcode();
		}

	}

	@Test(priority = 88)
	private void maxValidationContractorZipcode() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Zipcode Field exceed its max-10 limit");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.maxValidationZipcode();
		String mandatoryValidationFirstNameField = landing.errorZipcode();
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearZipcode();
			landing.previousButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("87.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("87.png");
			landing.clearZipcode();
			landing.previousButton();
		}

	}

	@Test(priority = 89)
	private void userContractorCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Contractor Form Create Successfully");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.basicContractorPage();
		String locationContractorPage = landing.locationContractorPage();
		extentTest.log(Status.INFO, "Actual Result is -" + locationContractorPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContractorCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (locationContractorPage.equals(getPropertyValue("ContractorCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("88.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("88.png");

		}

	}

	@Test(priority = 90)
	private void userContractorCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the User Contractor Created Count is added in the Total Contractor Count");
		CreateUserPage create = new CreateUserPage(driver);
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
			File file = new File("UserContractorCount.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UserContractorCount.png");
		}

	}

	@Test(priority = 91)
	private void createdContractorListName() throws IOException {
		extentTest = extentReports.createTest("Verify the Contractor Created in the List Page");
		CreateUserPage landing = new CreateUserPage(driver);
		String createUserList = landing.createUserList();
		extentTest.log(Status.INFO, "Actual Result is -" + createUserList);
		extentTest.log(Status.INFO, "Expected Result is -" + createUserList);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createUserList.equals(createUserList)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("89.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("89.png");
		}
	}

	@Test(priority = 92)
	private void alreadyExistedContractorMailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"erify [Email Already Exists] User Contractor form, Error is dispalyed when already existing Email is provided");
		CreateUserPage landing = new CreateUserPage(driver);
		landing.getEmaiContractorList();
		String createUserList = landing.alreadyContractorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + createUserList);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createUserList.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("90.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("90.png");
		}
	}

	@Test(priority = 93)
	private void searchContractorNameValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify the Contractor First Name in Search Field then the List is Appear");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchName = landing.searchContractorFirstName();
		String listFirstName = landing.listFirstName();
		extentTest.log(Status.INFO, "Actual Result is -" + searchName);
		extentTest.log(Status.INFO, "Expected Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchName.equals(listFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearContractorSearch();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("91.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("91.png");
			landing.clearContractorSearch();
		}

	}

	@Test(priority = 94)
	private void searchContractorPhoneNumberValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the Contractor Phone Number in Search Field then the List is Appear");
		CreateUserPage landing = new CreateUserPage(driver);
//		Thread.sleep(5000);
		String searchPhoneNumber = landing.searchContractorPhoneNumber();
		String listPhoneNumber = landing.listContractorPhoneNumber();
		extentTest.log(Status.INFO, "Actual Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchPhoneNumber.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearContractorSearch();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("92.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("92.png");
			landing.clearContractorSearch();
		}

	}

	@Test(priority = 95)
	private void searchContractorEmailValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Contractor Email in Search Field then the List is Appear");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchPhoneNumber = landing.searchContractorEmail();
		String listPhoneNumber = landing.listContractorEmail();
		extentTest.log(Status.INFO, "Actual Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchPhoneNumber.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearContractorSearch();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("93.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("93.png");
			landing.clearContractorSearch();
		}

	}

	@Test(priority = 96)
	private void filterByCompany() throws IOException {
		extentTest = extentReports
				.createTest("Verify the Contractor Filters Company Name in Search Field then the List is Appear");
		CreateUserPage landing = new CreateUserPage(driver);
		String filterCompanySearch = landing.filterCompanySearch();
		String listCompanyName = landing.listCompanyName();
		extentTest.log(Status.INFO, "Actual Result is -" + filterCompanySearch);
		extentTest.log(Status.INFO, "Expected Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listCompanyName.equals(filterCompanySearch)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearContractorSearch();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("94.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("94.png");
			landing.clearContractorSearch();
		}

	}

	@Test(priority = 97)
	private void invalidDatasValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Invlaid data in Contractor Search Field");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchPhoneNumber = landing.invalidSearchButton();
		extentTest.log(Status.INFO, "Actual Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchPhoneNumber.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.resetButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("95.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("95.png");
			landing.resetButton();
		}
	}

	@Test(priority = 98)
	private void landingOnContractorEditForm() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Land on Contractor Edit Page and Validate the Label");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchPhoneNumber = landing.editpageLanding();
		extentTest.log(Status.INFO, "Actual Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorEditPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchPhoneNumber.equals(getPropertyValue("CompanyContractorEditPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("96.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("96.png");
		}

	}

	@Test(priority = 99)
	private void editContractorDetail() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify to Edit the Contractor Details & Form Submission");
		CreateUserPage landing = new CreateUserPage(driver);
		String editUserDetails = landing.editContractorDetails();
		extentTest.log(Status.INFO, "Actual Result is -" + editUserDetails);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContractorUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editUserDetails.equals(getPropertyValue("ContractorUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("97.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("97.png");
		}

	}

	@Test(priority = 100)
	private void deleteContractorDetail() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify to Delete the Contractor Details");
		CreateUserPage landing = new CreateUserPage(driver);
		String editUserDetails = landing.deleteContractorDetails();
		extentTest.log(Status.INFO, "Actual Result is -" + editUserDetails);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContractorDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editUserDetails.equals(getPropertyValue("ContractorDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("98.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("98.png");
		}

	}

	@Test(priority = 101)
	private void landOnSendInvitePage() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Land on Send Invite Page and Validate the Label");
		CreateUserPage landing = new CreateUserPage(driver);
		String searchPhoneNumber = landing.landSendInvite();
		extentTest.log(Status.INFO, "Actual Result is -" + searchPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamSendInvitePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (searchPhoneNumber.equals(getPropertyValue("TeamSendInvitePage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("99.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("99.png");
		}

	}

	@Test(priority = 102)
	public void verifyEmailRequired() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Check the Send Invite form page, Email field is set as Mandatory & Error Message is displayed when it is BLANK");
		SendInvitePage invitePage = new SendInvitePage(this.driver);
		// invitePage.clickSendInvite();
//		Thread.sleep(1000);
		invitePage.clickInvite();
		String text = invitePage.getEmailErrorText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("100.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("100.png");
		}
	}

	@Test(priority = 103)
	private void verfiyEmailFormat() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Send Invite form] invalid email is entered in Email Field");
		SendInvitePage invitePage = new SendInvitePage(this.driver);
//		driver.navigate().refresh();
		invitePage.enterFirstName("sdsddfd");
		invitePage.enterEmail("asdf");
		Thread.sleep(1000);
		invitePage.clickInvite();
		String text = invitePage.getEmailErrorText();
		extentTest.log(Status.INFO, "Actual Result -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("102.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("102.png");
		}

	}

	@Test(priority = 104)
	private void verifyFirstName() throws IOException {
		extentTest = extentReports.createTest(
				"Check the Send Invite form page, First Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		SendInvitePage invitePage = new SendInvitePage(this.driver);
		invitePage.clearEnterFirstName();
		invitePage.clickInvite();
		invitePage.clickNext();
		String text = invitePage.firstNameError();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("103.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("103.png");
		}

	}

	@Test(priority = 105)
	private void verifyFirstNamewithBlankSpace() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the First Name with Blank Space in Send Invite Page");
		SendInvitePage invitePage = new SendInvitePage(this.driver);
//		driver.navigate().refresh();
		invitePage.enterEmail("email@gmail.com");
		invitePage.enterFirstName("  ");
		Thread.sleep(2000);
		invitePage.clickInvite();
		String text = invitePage.firstNameError();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("104.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("104.png");
		}

	}

	@Test(priority = 106)
	private void verifyUserEnterMessage() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify to Enter the Message in the Message Field in Send Invite Page");
		SendInvitePage invitePage = new SendInvitePage(this.driver);
		invitePage.clickInvite();
		invitePage.clearEnterFirstName();
		invitePage.enterFirstName("Firstname");
//		invitePage.enterEmail("email@gmail.com");
		Thread.sleep(3500);
		invitePage.dndTypes();
		invitePage.clickNext();
		String text = invitePage.getMessageText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PlaceholderMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("PlaceholderMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("105.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("105.png");
		}
	}

	@Test(priority = 107)
	private void verfiyAddMoreButton() throws IOException {
		extentTest = extentReports.createTest("Verify the Add User Button is Displayed in Send Invite Page");
		SendInvitePage invitePage = new SendInvitePage(this.driver);
		invitePage.clickPrevious();
		String text = invitePage.getAddMoreText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AddUserButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("AddUserButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("106.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("106.png");
		}
	}

	@Test(priority = 108)
	private void verifyUserSendInvite() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify to Check the Suuccessful Message in Send Invite Page");
		SendInvitePage invitePage = new SendInvitePage(this.driver);
		Faker faker = new Faker(new Locale("en-IND"));
		String r = RandomStringUtils.randomAlphanumeric(4);
		String fakeFirstName = faker.name().firstName();
		String fakeLastName = faker.name().lastName();
		String fakeEmail = faker.internet().safeEmailAddress();
		invitePage.clearEnterFirstName();
		invitePage.enterFirstName(fakeFirstName);
		invitePage.enterLastName(fakeLastName);
		invitePage.ClearEnterEmail();
		invitePage.enterEmail(fakeEmail);
		invitePage.clickSubmit();
		String text = invitePage.getSuccessMessages();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SendInviteMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("SendInviteMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("107.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("107.png");
		}

	}

}
