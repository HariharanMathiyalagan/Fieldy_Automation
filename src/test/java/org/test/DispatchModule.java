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
import com.zaigo.pageobjects.DispatchPage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.RequestPage;
import com.zaigo.pageobjects.TeamUserPage;
import com.zaigo.utility.BrowserSetup;

public class DispatchModule extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("DispatchModule.html");
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
		loginInPage.passwordField(getPropertyValue("Password"));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(loginInPage.getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Exp	ected Validation are Equal");
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
	private void userCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify a new User is created successfully through [Team User]");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("Navigate");
		landing.labelValidation("ListLabel");
		landing.labelValidation("FormLabel");
		String createMessage = landing.createUser();
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
			landing.responseMessage("AlternateFunction");

		}

	}

	@Test(priority = 1)
	public void requestModule() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("Verify Global Request List Page is opened when clicking on Global Request");
		RequestPage module = PageFactory.initElements(driver, RequestPage.class);
		String editContact = module.labelValidation("ListLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestListPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("RequestListPage"))) {
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

	@Test(priority = 2)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Create Request page is opened from Global-> Request -> Create Request");
		RequestPage requestPage = PageFactory.initElements(driver, RequestPage.class);
		String requestLandPage = requestPage.labelValidation("CreateLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + requestLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatePageRequestLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (requestLandPage.equals(getPropertyValue("CreatePageRequestLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateOrganizationRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateOrganizationRequestLabel.png");
		}
	}

	@Test(priority = 3)
	private void autoCompleteContactCreationRequest() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Contact Creation in the Autocomplete field");
		RequestPage contactMandatory = PageFactory.initElements(driver, RequestPage.class);
		contactMandatory.autoCompleteField("ContactCreate");
		String errorContact = contactMandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContact.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contactMandatory.autoCompleteField("GlobalContactVisibleName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AutocompleteContactCreate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AutocompleteContactCreate.png");
			contactMandatory.message("AlternateFunction");
			contactMandatory.autoCompleteField("GlobalContactVisibleName");
		}
	}

	@Test(priority = 4)
	private void unsssignedRequest() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Unassigned Request is created successfully from Global Request Contact->Create Request");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.validData("Unassigned");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactUnscheduleRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactUnscheduleRequest.png");
		}
	}

	@Test(priority = 5)
	public void dispatchModule() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest("Verify Dispatch Page is opened when clicking on Dispatch");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		String editContact = module.modulePage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("DispatchLabel"))) {
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

	@Test(priority = 6)
	public void assignRequest() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest(
				"While drag & drop the Unassigned request change into Scheduled Request in Dispatch Module");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		module.assignAppoinment("Assign");
		String editContact = module.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchRequestAssignedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("DispatchRequestAssignedMessage"))) {
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

	@Test(priority = 7)
	public void unassignRequest() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("In Dispatch Module, verify the Schedule Request changed into Unassigned Request");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		module.tiggerFunction("Unassign");
		String editContact = module.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchRequestUnassignedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("DispatchRequestUnassignedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Unassigned.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Unassigned.png");
		}
	}

	@Test(priority = 8)
	public void dispatchRequest() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("In Dispatch Module, verify the Schedule Request changed into Dispatch Request");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		module.assignAppoinment("Assigned");
		module.responseMessage();
		module.tiggerFunction("Dispatch");
		String editContact = module.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchRequestDispatchMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("DispatchRequestDispatchMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Dispatch.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Dispatch.png");
		}
	}

	@Test(priority = 9)
	public void cancelRequest() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("In Dispatch Module, verify the Schedule Request changed into Dispatch Request");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		module.tiggerFunction("Unassign");
		String editContact = module.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("RequestCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("Cancel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("Cancel.png");
		}
	}

	@Test(priority = 10)
	public void jobModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify Global Job List Page is opened when clicking on Global Job");
		JobPage module = PageFactory.initElements(driver, JobPage.class);
		String editContact = module.labelValidation("Global");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobListPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("JobListPage"))) {
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

	@Test(priority = 11)
	private void labelJobValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify Create Job page is opened from Contacts-> Jobs -> Create Job");
		JobPage jobPage = PageFactory.initElements(driver, JobPage.class);
		String jobLandPage = jobPage.labelValidation("CreateLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatePageJobLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("CreatePageJobLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateJobLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateJobLabel.png");
		}
	}

	@Test(priority = 14)
	private void autoCompleteContactCreationJob() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Contact Creation in the Autocomplete field");
		JobPage contactMandatory = PageFactory.initElements(driver, JobPage.class);
		contactMandatory.autoCompleteField("ContactCreate");
		String errorContact = contactMandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContact.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contactMandatory.autoCompleteField("GlobalContactVisibleName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AutocompleteContactCreate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AutocompleteContactCreate.png");
			contactMandatory.message("AlternateFunction");
			contactMandatory.autoCompleteField("GlobalContactVisibleName");
		}

	}

	@Test(priority = 15)
	private void unsssignedJob() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Unassigned Job is created successfully from Contact->Create Global Job");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Unassigned");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
		}
	}

	@Test(priority = 16)
	public void assignJob() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("While drag & drop the Unassigned Job change into Scheduled Job in Dispatch Module");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		module.modulePage();
		module.assignAppoinment("Assign");
		String editContact = module.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchJobAssignedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("DispatchJobAssignedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AssignJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AssignJob.png");
		}
	}

	@Test(priority = 17)
	public void unassignJob() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("In Dispatch Module, verify the Schedule Job changed into Unassigned Job");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		module.tiggerFunction("Unassign");
		String editContact = module.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchJobUnassignedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("DispatchJobUnassignedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnassignedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnassignedJob.png");
		}
	}

	@Test(priority = 18)
	public void dispatchJob() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest("In Dispatch Module, verify the Schedule Job changed into Dispatch Job");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		module.assignAppoinment("Assigned");
		module.responseMessage();
		module.tiggerFunction("Dispatch");
		String editContact = module.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchJobDispatchMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("DispatchJobDispatchMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DispatchJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DispatchJob.png");
		}
	}

	@Test(priority = 19)
	public void cancelJob() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest("In Dispatch Module, verify the Dispatch Job changed into Cancel Job");
		DispatchPage module = PageFactory.initElements(driver, DispatchPage.class);
		module.tiggerFunction("Unassign");
		String editContact = module.responseMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("JobCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CancelJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CancelJob.png");
		}
	}

}
