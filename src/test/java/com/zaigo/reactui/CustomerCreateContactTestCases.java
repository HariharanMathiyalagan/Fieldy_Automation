package com.zaigo.reactui;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerCreateContactTestCases {

	private WebDriver driver = null;
	private LoginPage loginInPage = null;

	@BeforeClass
	public void setup() {
		this.driver = BrowserSetup.startBrowser();
	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
	}

	@AfterMethod
	public void setVariableEmpty() {
		loginInPage = null;
	}

	@Test(priority = 0) // 1-Login
	public void loginPage() throws InterruptedException {
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("fieldy@zaiportal.com", "Zaiserve@123");
		loginInPage.clickLoginButton();
	}

	@Test(priority = 1)
	private void contactModule() throws InterruptedException {
		CustomerCreateContactPage module = new CustomerCreateContactPage(driver);
		module.modulePage();

	}

	@Test(priority = 2)
	private void maxSizeValidationProfileField() throws AWTException, InterruptedException {
		CustomerCreateContactPage maxSizeFile = new CustomerCreateContactPage(driver);
		maxSizeFile.maxSizeProfile();

	}

	@Test(priority = 3)
	private void fileFormatValidationProfileField() throws InterruptedException, AWTException {
		CustomerCreateContactPage fileFormat = new CustomerCreateContactPage(driver);
		fileFormat.fileFormatProfile();

	}

	@Test(priority = 4)
	private void mandatoryValidation() throws AWTException {
		CustomerCreateContactPage mandatoryValidation = new CustomerCreateContactPage(driver);
		mandatoryValidation.mandatoryValidation();
	}

	@Test(priority = 5)
	private void maxValidationFirstNameField() throws AWTException, InterruptedException {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationFirstName();

	}

	@Test(priority = 6)
	private void maxValidationLastNameField() {
		CustomerCreateContactPage minValidation = new CustomerCreateContactPage(driver);
		minValidation.maxValidationLastName();

	}

	@Test(priority = 7)
	private void maxValidationJobTittle() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationJobTittle();

	}

	@Test(priority = 8)
	private void maxValidationEmailField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationEmail();

	}

	@Test(priority = 9)
	private void invalidValidationEmailField() throws AWTException, InterruptedException {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.invalidEmail();

	}

	@Test(priority = 10)
	private void minValidationPhoneNumberField() throws AWTException, InterruptedException {
		CustomerCreateContactPage minValidation = new CustomerCreateContactPage(driver);
		minValidation.minValidationPhone();

	}

	@Test(priority = 11)
	private void maxValidationPhoneNumberField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationPhone();

	}

	@Test(priority = 12)
	private void maxValidationPropertyNameField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationPropertyNamee();

	}

	@Test(priority = 13)
	private void maxValidationContactPersonNameeField() throws InterruptedException {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationContactPersonNamee();

	}

	@Test(priority = 14)
	private void maxValidationAddress1() throws InterruptedException {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationAddress11();
	}

	@Test(priority = 15)
	private void maxValidationAddress22Field() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationAddress22();
	}

	@Test(priority = 16)
	private void maxValidationCityField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationCity();
	}

	@Test(priority = 17)
	private void maxValidationStateField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationState();
	}

	@Test(priority = 18)
	private void minValidationZipcodeField() {
		CustomerCreateContactPage minValidation = new CustomerCreateContactPage(driver);
		minValidation.minValidationZipcode();
	}

	@Test(priority = 19)
	private void maxValidationZipcodeField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationZipcode();
	}

	@Test(priority = 20)
	private void maxValidationProductNameField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationProductName();
	}

	@Test(priority = 21)
	private void maxValidationBrandNameField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationBrandName();
	}

	@Test(priority = 22)
	private void maxValdidationModelNumberField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValdidationModelNumber();
	}

	@Test(priority = 23)
	private void maxValidationSerialNumberField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationSerialNumber();
	}

	@Test(priority = 24)
	private void maxValidationAccessHoursField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationAccessHours();
	}

	@Test(priority = 24)
	private void maxValidationInstallationNotesField() {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationInstallationNotes();
	}

	@Test(priority = 25)
	private void maxValidationAttachmentFileField() throws AWTException, InterruptedException {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxValidationAttachmentFile();
	}

	@Test(priority = 26)
	private void fileFormatValidationAttachmentField() throws AWTException, InterruptedException {
		CustomerCreateContactPage fileFormat = new CustomerCreateContactPage(driver);
		fileFormat.fileFormatValidation();

	}

	@Test(priority = 27)
	private void maxLimitAttachmentField() throws AWTException, InterruptedException {
		CustomerCreateContactPage maxValidation = new CustomerCreateContactPage(driver);
		maxValidation.maxLimitAttachmentField();
	}

	@Test(priority = 28)
	private void CreateContact() throws AWTException, InterruptedException {
		CustomerCreateContactPage create = new CustomerCreateContactPage(driver);
		create.propertyPage();
		create.equipmentPage();
		create.contactPage();
	}

	@Test(priority = 29)
	private void alreadyExistedMail() throws InterruptedException {
		CustomerCreateContactPage alreadyExisted = new CustomerCreateContactPage(driver);
		alreadyExisted.alreadyExistMail();

	}

	@Test(priority = 30)
	private void characterFilterValidation() {
		CustomerCreateContactPage filter = new CustomerCreateContactPage(driver);
		filter.alphabetsFilters();

	}

	@Test(priority = 31)
	private void searchListNameField() {
		CustomerCreateContactPage search = new CustomerCreateContactPage(driver);
		search.searchListName();

	}

	@Test(priority = 32)
	private void searchInvalidListName() {
		CustomerCreateContactPage invalid = new CustomerCreateContactPage(driver);
		invalid.searchInvalidListName();

	}

}
