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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.CustomerCreateOrganizationPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.TeamUserPage;
import com.zaigo.utility.BrowserSetup;

public class BulkImportContact extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String dateFrom;
	static String dateTo;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerContactJob.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();

	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}

	@Test(priority = -2)
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

//	@Test(priority = 1)
//	public void TeamUserPage() throws InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the User to Land on the Team User List Page");
//		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
//		edit.clickEvent("Navigate");
//
//	}
//
//	@Test(priority = 2,invocationCount = 50)
//	private void landingOnCreateForm() throws IOException, InterruptedException {
//		extentTest = extentReports
//				.createTest("Verify the User to Land on Team User Create Page and Validate the Label");
//		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
//		landing.clickEvent("CreateButton");
//		landing.validateFillData("Basic");
//		landing.clickEvent("Next");
//		landing.validateFillData("Location");
//		landing.clickEvent("SaveUpdate");
//		String mandatoryValidationFirstNameField = landing.responseMessage("Message");
//		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
//		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamCreateUserPage"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (mandatoryValidationFirstNameField.equals(getPropertyValue("TeamCreateUserPage"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("65.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("65.png");
//		}
//
//	}
	// Contact
//	@Test(priority = -1)
//	private void contactModule() throws InterruptedException, IOException {
//		extentTest = extentReports.createTest("Navigate to Customer Contact Page");
//		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
//		String editContact = initElements.modulePage();
//		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
//		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerContactList"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (editContact.equals(getPropertyValue("CustomerContactList"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("ContactList.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("ContactList.png");
//		}
//
//	}
//
//	@Test(priority = 0,invocationCount = 50)
//	private void CreateContact() throws AWTException, InterruptedException, IOException {
//		extentTest = extentReports
//				.createTest("Verify a new Customer Contact is created successfully through [Create Contact]");
//		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
//		initElements.contactPage();
//		initElements.propertyPage();
//		initElements.equipmentPage();
//		String responseMessageCreateContact1 = initElements.responseMessage("CustomerCreate");
//		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseMessageCreateContact1);
//		extentTest.log(Status.INFO,
//				"Expected Result create response messages is -" + getPropertyValue("CustomerCreatedMessage"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (responseMessageCreateContact1.equals(getPropertyValue("CustomerCreatedMessage"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("CreateValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("CreateValidation.png");
//			initElements.responseMessage("AlternateFunction");
//		}
//
//	}

	// Organization
//	@Test(priority = -1)
//	private void contactModule() throws InterruptedException, IOException, AWTException {
//		extentTest = extentReports.createTest("Navigate to Customer Contact Page");
//		CustomerCreateOrganizationPage initElements = PageFactory.initElements(driver,
//				CustomerCreateOrganizationPage.class);
//		String editContact = initElements.modulePage();
//		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
//		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerOrganizationList"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (editContact.equals(getPropertyValue("CustomerOrganizationList"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("ContactList.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("ContactList.png");
//		}
//
//	}
//
//	@Test(priority = 0,invocationCount = 50)
//	private void createOrganization() throws InterruptedException, AWTException, IOException {
//		extentTest = extentReports
//				.createTest("Verify a new Customer Organization is created successfully through [Create Organization]");
//		CustomerCreateOrganizationPage create = PageFactory.initElements(driver,
//				CustomerCreateOrganizationPage.class);
//		create.organizationPage();
//		create.contactPage("CreateContact");
//		create.propertyPage();
//		create.equipmentPage();
//		String listName = create.responseMessage("ResponseMessage");
//		extentTest.log(Status.INFO, "Actual Result is -" + listName);
//		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (listName.equals(getPropertyValue("CustomerCreatedMessage"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("OrgCreateValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("OrgCreateValidation.png");
//			create.responseMessage("AlternateFunction");
//		}
//
//	}

	@Test(priority = 2)
	public void createLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Contractor Company Page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		edit.clickEvent("Navigate");
//		String assertionMessage = edit.labelValidation();
		extentTest.log(Status.INFO, "Actual Result is -" + null);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorCreatePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ("null".equals(getPropertyValue("CompanyContractorCreatePage"))) {
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

	@Test(priority = 3, invocationCount = 50)
	public void createContract() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the Contractor Company has Successfully Created");
		CreateContractorPage create = PageFactory.initElements(driver, CreateContractorPage.class);
		create.validData("BasicPage");
		create.validData("LocationPage");
		String asssertCreate = create.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals(getPropertyValue("CompanyContractorCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
			create.responseMessage("AlternateFunction");
		}

	}
	
//	@Test(priority = 1)
//	public void TeamUserPage() throws InterruptedException, IOException {
//		extentTest = extentReports.createTest(
//				"Verify the User to Land on the Team User Contractor Contractor List Page & Validate the Label");
//		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
//		edit.clickEvent("NavigateContractor");
//		String assertionMessage = edit.labelValidation("ListLabel");
//		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
//		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamContractorListLabel"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (assertionMessage.equals(getPropertyValue("TeamContractorListLabel"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("EditCompanyLabel.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
//		}
//
//	}
//
//	
	@Test(priority = 28,invocationCount = 50)
	private void userCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify a new User is created successfully through [Team User]");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.validateFillData("BasicContractor");
		landing.validateFillData("ContractorCompany");
		landing.clickEvent("Next");
		landing.validateFillData("Location");
		landing.clickEvent("SaveUpdate");
		String createMessage = landing.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContractorCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("ContractorCreatedMessage"))) {
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
