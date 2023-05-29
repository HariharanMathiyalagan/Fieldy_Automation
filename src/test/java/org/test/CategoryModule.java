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
import com.zaigo.pageobjects.CategoryPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.ProductServicePage;
import com.zaigo.pageobjects.TaxPage;
import com.zaigo.utility.BrowserSetup;

public class CategoryModule extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String ListField;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CategoryModule.html");
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
		extentTest = extentReports
				.createTest("Verify the Fieldy Dashboard Page is launched when valid Email & Password is provided");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password", getPropertyValue("Enviromment")));
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

	@Test(priority = 1)
	private void settingModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Navigate to Category Settings page");
		CategoryPage initElements = PageFactory.initElements(driver, CategoryPage.class);
		String editContact = initElements.modulePage("ListPage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ListCategory"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("ListCategory"))) {
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
	private void createFormLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Category Page");
		CategoryPage initElements = PageFactory.initElements(driver, CategoryPage.class);
		String editContact = initElements.modulePage("CreatePage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreateCategory"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CreateCategory"))) {
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

	@Test(priority = 3)
	private void mandatoryValidationCategoryName() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Category Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CategoryPage initElements = PageFactory.initElements(driver, CategoryPage.class);
		initElements.categoryName("Mandatory");
		String editContact = initElements.errorFields("CategoryName");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ProductErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ProductErrorMandatoryError.png");
		}
	}

	@Test(priority = 4)
	private void maximumValidationCategoryName() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Category Name Field exceed its max-256 limit");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		mandatory.categoryName("MaxValidation");
		String errorPasswordField = mandatory.errorFields("CategoryName");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("CategoryName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("CategoryName");
		}

	}

	@Test(priority = 5)
	private void maximumValidationDescription() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description Name Field exceed its max-256 limit");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		mandatory.description("MaxValidation");
		String errorPasswordField = mandatory.errorFields("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("Description");
		}
	}

	@Test(priority = 6)
	private void createButton() throws IOException, AWTException {
		extentTest = extentReports.createTest(
				"Verify the Create Category page Save & Compelete Button is displayed in the Create form page");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		String errorPasswordField = mandatory.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SaveButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("SaveButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobNotesMaximumValidation.png");
		}
	}

	static String listData;

	@Test(priority = 7)
	private void createCategoryProduct() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify created successful message is displayed, when the Category Created");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		listData = mandatory.validData("Product");
		String errorPasswordField = mandatory.message();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CategoryCreated"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CategoryCreated"))) {
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

	@Test(priority = 8)
	private void createCategoryProductList()
			throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify the Created Category is in " + listData + " Type");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		String errorPasswordField = mandatory.listValidation("ListType");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + listData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(listData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listData = mandatory.listValidation("ListCategoryName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			listData = mandatory.listValidation("ListCategoryName");
		}
	}

	@Test(priority = 9)
	private void searchProductName() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest(
				"Enter the Category Name:(" + listData + ") in the Search field & Category list retrived successfully");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		mandatory.listValidation("SearchData");
		String errorPasswordField = mandatory.listValidation("ListCategoryName");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + listData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(listData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchJobNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchJobNo.png");
			mandatory.clearFields("Search");
		}
	}

	@Test(priority = 10)
	private void searchInvalid() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		mandatory.listValidation("Invalid");
		String errorPasswordField = mandatory.listValidation("InvalidResult");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clickEvent("Reset");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchInvalid.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchInvalid.png");
			mandatory.clickEvent("Reset");
		}
	}

	@Test(priority = 11)
	private void editFormLabel() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Category Page");
		CategoryPage initElements = PageFactory.initElements(driver, CategoryPage.class);
		String editContact = initElements.modulePage("EditPage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditCategory"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("EditCategory"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearFields("CategoryName");
			initElements.clearFields("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearFields("CategoryName");
			initElements.clearFields("Description");
		}
	}

	@Test(priority = 12)
	private void editmandatoryValidationCategoryName() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Category Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CategoryPage initElements = PageFactory.initElements(driver, CategoryPage.class);
		initElements.clickEvent("ClickButton");
		String editContact = initElements.errorFields("CategoryName");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ProductErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ProductErrorMandatoryError.png");
		}
	}

	@Test(priority = 13)
	private void editmaximumValidationCategoryName() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Category Name Field exceed its max-256 limit");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		mandatory.categoryName("MaxValidation");
		String errorPasswordField = mandatory.errorFields("CategoryName");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("CategoryName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("CategoryName");
		}

	}

	@Test(priority = 14)
	private void editmaximumValidationDescription() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description Name Field exceed its max-256 limit");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		mandatory.description("MaxValidation");
		String errorPasswordField = mandatory.errorFields("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("Description");
		}
	}

	@Test(priority = 15)
	private void updateButton() throws IOException, AWTException {
		extentTest = extentReports
				.createTest("Verify the Edit Category page Update Button is displayed in the Edit form page");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		String errorPasswordField = mandatory.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("UpdatedButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobNotesMaximumValidation.png");
		}
	}

	@Test(priority = 16)
	private void UpdateCategoryProduct() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify updated successful message is displayed, when the Category Updated");
		CategoryPage mandatory = PageFactory.initElements(driver, CategoryPage.class);
		listData = mandatory.validData("Product");
		String errorPasswordField = mandatory.message();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CategoryEdited"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CategoryEdited"))) {
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

	@Test(priority = 17)
	private void deleteProduct() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify deleted successfully message is displayed, when the Category Deleted");
		CategoryPage landing = PageFactory.initElements(driver, CategoryPage.class);
		landing.listValidation("Delete");
		String createMessage = landing.message();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CategoryDeleted"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("CategoryDeleted"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clickEvent("Visible");
			ListField = landing.validData("ReflectionProduct");
			landing.message();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.clickEvent("Visible");
			ListField = landing.validData("ReflectionProduct");
			landing.message();
		}
	}

	@Test(priority = 18)
	private void reflectCategoryProduct() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the newly created Category Product Name is:" + ListField
				+ " & it's reflect the Category field in the Product Create Page");
		CategoryPage landing = PageFactory.initElements(driver, CategoryPage.class);
		String createMessage = landing.refection("ProductWait");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.backEvent("Edit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.backEvent("Edit");
		}
	}

	@Test(priority = 19)
	private void inactiveProductCategory() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Inactive Category Product Name is:" + ListField
				+ " & it's not reflect the Category field in the Product Create Page");
		CategoryPage landing = PageFactory.initElements(driver, CategoryPage.class);
		String createMessage = landing.refection("CreateProduct");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidData"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("InvalidData"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.backEvent("Delete");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.backEvent("Delete");
		}
	}

	@Test(priority = 20)
	private void deleteProductCategory() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Delete Category Product Name is:" + ListField
				+ " & it's not reflect the Category field in the Product Create Page");
		CategoryPage landing = PageFactory.initElements(driver, CategoryPage.class);
		String createMessage = landing.refection("CreateProduct");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidData"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("InvalidData"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.backEvent("Create");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.backEvent("Create");
		}
	}

	@Test(priority = 21)
	private void reflectCategoryService() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the newly created Category Service Name is:" + ListField
				+ " & it's reflect the Category field in the Service Create Page");
		CategoryPage landing = PageFactory.initElements(driver, CategoryPage.class);
		String createMessage = landing.refection("ServiceWait");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.backEvent("Edit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.backEvent("Edit");
		}
	}

	@Test(priority = 22)
	private void inactiveProductService() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Inactive Category Service Name is:" + ListField
				+ " & it's not reflect the Category field in the Service Create Page");
		CategoryPage landing = PageFactory.initElements(driver, CategoryPage.class);
		String createMessage = landing.refection("CreateService");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidData"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("InvalidData"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.backEvent("Delete");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.backEvent("Delete");
		}
	}

	@Test(priority = 23)
	private void deleteProductService() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Delete Category Service Name is:" + ListField
				+ " & it's not reflect the Category field in the Service Create Page");
		CategoryPage landing = PageFactory.initElements(driver, CategoryPage.class);
		String createMessage = landing.refection("CreateService");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidData"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("InvalidData"))) {
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
}
