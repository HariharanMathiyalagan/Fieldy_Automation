package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.pageobjects.SendInvitePage;
import com.zaigo.pageobjects.TeamUserPage;
import com.zaigo.utility.BrowserSetup;

public class TeamSendInvite extends BaseClass{
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String listValidation;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("TeamUserSendInvite.html");
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

	@Test(priority = 0)
	public void verify() throws IOException {
		// Single Account User
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
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

	@Test(priority = 1)
	public void TeamUserPage() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Team User List Page");
		SendInvitePage edit = PageFactory.initElements(driver, SendInvitePage.class);
		edit.clickEvent("Navigate");
		String assertionMessage = edit.labelValidation("ListLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamListLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("TeamListLabel"))) {
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
	
	@Test(priority = 2)
	private void landingOnSendInviteForm() throws IOException {
		extentTest = extentReports
				.createTest("Verify the User to Land on Team Send Invite Page and Validate the Label");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		String labelValidation = landing.labelValidation("FormLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + labelValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamSendInvite"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (labelValidation.equals(getPropertyValue("TeamSendInvite"))) {
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
	
	@Test(priority = 3)
	private void mandatoryValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Send Invite form page, First Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		landing.clickEvent("SendInvite");
		String mandatoryValidationFirstNameField = landing.errorField("FirstName");
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

	@Test(priority = 4)
	private void mandatoryValidationTypeField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Send Invite form page, User Type field is set as Mandatory & Error Message is displayed when it is BLANK");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		String mandatoryValidationFirstNameField = landing.errorField("UserType");
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

	@Test(priority = 5)
	private void mandatoryValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Send Invite form page, Email field is set as Mandatory & Error Message is displayed when it is BLANK");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		String mandatoryValidationFirstNameField = landing.errorField("Email");
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
	
	@Test(priority = 6)
	private void maxValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team UserSend Invite] First Name Field exceed its max-256 limit");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		landing.firstName("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("FirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("43.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("43.png");
			landing.clearField("FirstName");
		}

	}

	@Test(priority = 7)
	private void maxValidationLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Send Invite] Last Name Field exceed its max-256 limit");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		landing.lastName("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("LastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("44.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("44.png");
			landing.clearField("LastName");
		}

	}

	@Test(priority = 9)
	private void maxValidationEmailFields() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Send Invite] Job Tittle Field exceed its max-256 limit");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		landing.email("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("46.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("46.png");
			landing.clearField("Email");
		}

	}

	@Test(priority = 10)
	private void invalidEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Team User Send Invite] invalid email is entered in Email Field");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		landing.email("InValid");
		String mandatoryValidationFirstNameField = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Email");
			landing.validFillData("UserDetails");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("47.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("47.png");
			landing.clearField("Email");
			landing.validFillData("UserDetails");

		}

	}
	
	@Test(priority = 11)
	private void maxValidationMessage() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Send Invite] Message Field exceed its max-256 limit");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		landing.clickEvent("Next");
		landing.message("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Message");
			landing.validFillData("Message");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("51.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("51.png");
			landing.clearField("Message");
			landing.validFillData("Message");
		}

	}
	
	@Test(priority = 12)
	public void verifySaveButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the [Team User Send Invite] Send Invite Button is displayed in the Send Invite form page");
		SendInvitePage contractorPage = PageFactory.initElements(driver, SendInvitePage.class);
		String text_button = contractorPage.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SendInviteButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("SendInviteButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
			contractorPage.clickEvent("ClickButton");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
			contractorPage.clickEvent("ClickButton");
		}

	}
	
	@Test(priority = 13)
	private void userSendInvite() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify a new User is invite send successfully through [Team User Send Invite]");
		SendInvitePage landing = PageFactory.initElements(driver, SendInvitePage.class);
		String createMessage = landing.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SendInviteMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("SendInviteMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.responseMessage("AlternateFunction");

		}

	}
	
}
