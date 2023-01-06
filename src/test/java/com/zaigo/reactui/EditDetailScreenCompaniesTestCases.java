//package com.zaigo.reactui;
//
//import java.io.IOException;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.zaigo.pageobjects.EditDetailScreenCompaniesPage;
//import com.zaigo.pageobjects.LoginPage;
//import com.zaigo.utility.BrowserSetup;
//
//public class EditDetailScreenCompaniesTestCases {
//
//	private WebDriver driver = null;
//	private LoginPage loginInPage = null;
//	ExtentReports extentReports;
//	ExtentHtmlReporter extentHtmlReporter;
//	ExtentTest extentTest;
//
//	@BeforeClass
//	public void setup() {
//		extentReports = new ExtentReports();
//		extentHtmlReporter = new ExtentHtmlReporter("DetailScreen Companies.html");
//		extentReports.attachReporter(extentHtmlReporter);
//		this.driver = BrowserSetup.startBrowser();
//	}
//
//	@AfterClass
//	public void exitBrowser() {
//		this.driver.quit();
//		this.extentReports.flush();
//
//	}
//
//	@AfterMethod
//	public void setVariableEmpty() {
//		loginInPage = null;
//	}
//
//	@Test(priority = 0) // 1-Login
//	public void loginPage() throws InterruptedException, IOException {
//		extentTest = extentReports.createTest(
//				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
//		LoginPage loginInPage = new LoginPage(this.driver);
//		loginInPage.setUserCredentials("fieldy@zaiportal.com", "Zaiserve@123");
//		loginInPage.clickLoginButton();
//		String text = loginInPage.dashBoardText();
//		extentTest.log(Status.INFO, "Actual Error Validation :" + text);
//		extentTest.log(Status.INFO, "Expected Error Validation :" + "Shagul");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (text.equals("Shagul")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//
//			
//		}
//	}
//
//	@Test(priority = 1)
//	public void teamModule() throws InterruptedException {
//		extentTest = extentReports.createTest("Verify the Details Screen Tenant Name");
//		EditDetailScreenCompaniesPage module = new EditDetailScreenCompaniesPage(driver);
//		module.modulePage();
//		String assertTittle = module.assertTittle();
//		extentTest.log(Status.INFO, "Actual Error Validation :" + assertTittle);
//		extentTest.log(Status.INFO, "Expected Error Validation :" + "Zaiportal Tenant 3");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (assertTittle.equals("Zaiportal Tenant 3")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//
//		}
//
//	}
//
//	@Test(priority = 2)
//	public void edit() throws InterruptedException {
//		extentTest = extentReports.createTest("Navigate to Contractor Location Page");
//		EditDetailScreenCompaniesPage edit = new EditDetailScreenCompaniesPage(driver);
//		edit.editContent();
//	}
//
//	@Test(priority = 3)
//	private void addLocation() {
//		extentTest = extentReports.createTest("Verify the Company Information Updated Successfully");
//		EditDetailScreenCompaniesPage add = new EditDetailScreenCompaniesPage(driver);
//		String alpha = RandomStringUtils.randomAlphabetic(6);
//		String number = RandomStringUtils.randomNumeric(6);
//		String alph = RandomStringUtils.randomAlphabetic(3);
//		String num = RandomStringUtils.randomNumeric(10);
//		add.addLocation(alpha, alpha + "@" + alph + "." + alph, alpha, num, alpha, alpha, alpha, alpha, number);
//		String responseMessage = add.responseMessage();
//		extentTest.log(Status.INFO, "Actual Error Validation :" + responseMessage);
//		extentTest.log(Status.INFO, "Expected Error Validation :" + "Company Information updated successfully");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (responseMessage.equals("Company Information updated successfully")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//
//		}
//	}
//
//	@Test(priority = 4)
//	private void deleteLocation() {
//		extentTest = extentReports.createTest("Verify the Company Location Deleted Successfully");
//		EditDetailScreenCompaniesPage delete = new EditDetailScreenCompaniesPage(driver);
//		delete.deleteLocation();
//		String deleteMessage = delete.deleteMessage();
//		extentTest.log(Status.INFO, "Actual Error Validation :" + deleteMessage);
//		extentTest.log(Status.INFO, "Expected Error Validation :" + "Deleted Successfully");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (deleteMessage.equals("Deleted Successfully")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//
//		}
//	}
//
//}
