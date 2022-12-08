package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;

public class OnBoardingPage extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;

	String MandatoryErrorMessage = "Required Field";
	String PasswordCondition = "Password must have one lower case letter and one upper case letter and one number";
	String MinimumValidatioPassword = "Enter minimum 8 characters";
	String MisMatchPassword = "Confirm password should match with new password";
	String Max2048Validation = "Not Allowed More than 2048 characters";
	String Max256CharacterValidation = "Not Allowed More than 256 characters";
	String ValidEmail = "Enter a valid Email";
	String BussinessNameAlready = "Business Name Already Exists";
	String AlreadyExistedEmail = "Email Already Exists";
	String IndustryMaxValidation = "Not Allowed More than 64 characters";

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(1280);

	public OnBoardingPage(WebDriver driver) {
		this.driver = driver;

	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	private void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
	}

	private void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	public String getText(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;

	}

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	By LandingHeading = By.xpath("//h2[text()='Great to meet you!']");
	By CompanyName = By.id("company_name");
	By BussinessWebSite = By.id("company_website");
	By FirstName = By.id("first_name");
	By LastName = By.id("last_name");
	By Email = By.id("email");
	By MyBussinessType = By.className("business");
	By IndustryField = By.xpath("//h3[text()='Mention your industry information']");
	By Continue = By.id("submit-btn");
	By ChooseIndustry = By.xpath("//input[@value='Heating & Air Conditioning']");
	By Industry = By.id("industry");
	By IndustryError = By.id("industry_error");
	By NextLandingPage = By.xpath("(//div[@class='floating-form meeting']//child::h2)[2]");
	By CompanySize = By.xpath("(//div[@class='floating-form meeting']//h2)[3]");
	By CompanyEmployee = By.id("empdiv1");

	By RadioButton = By.xpath("//input[@onclick='mustIntrestedCheckBox()']");

	By IntersetedToday = By.xpath("//h2[text()='Which Solution are you most interested in today?']");
	By PasswordHeading = By.xpath("//h2[text()='Enter Your Password']");
	By Password = By.id("password");
	By ConfirmPassword = By.id("password_confirmation");

	By PasswordError = By.id("password_error");
	By ConfirmPasswordError = By.id("password_confirmation_error");
	By Back = By.id("onboarding-back-btn");

	By CompanyError = By.id("company_name_error");
	By BussinessWebsiteError = By.id("company_website_error");
	By FirstNameError = By.id("first_name_error");
	By lastNameError = By.id("last_name_error");
	By EmailError = By.id("email_error");
	HttpURLConnection connection;

	public void login() throws MalformedURLException, IOException {
		driver.get("http://zaiportal.com/public/Onboarding/meet.html");
		connection = (HttpURLConnection) new URL("http://zaiportal.com/public/Onboarding/meet.html").openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();

	}

	public int responseCode() throws IOException {
		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			Assert.assertEquals(responseCode, 200);
			this.assertName(LandingHeading, "Great to meet you!");
		} else {
			driver.quit();
		}
		return responseCode;

	}

	public void mandatoryValidation() {
		this.inputText(BussinessWebSite, "sadjh");
		this.assertName(Continue, "Continue");
		this.mouseActionClick(Continue);

	}

	public String manditoryValidations() {
		String text = this.getText(CompanyError);
		return text;

	}

	public void emailText() {
		this.inputText(Email, "asddajs@dada.das");

	}

	public void maximumValidationBussinessName() throws IOException {
		this.inputText(CompanyName, getPropertyValue("256Characters"));
		this.mouseActionClick(Continue);

	}

	public String errorMessageBussinessName() {
		String text = this.getText(CompanyError);
		return text;

	}

	public void clearCompanyName() {
		this.clearField(CompanyName);

	}

	public void clearBussinessName() {
		this.clearField(CompanyName);
		this.inputText(CompanyName, randomCharacter);
	}

	public void alreadyBussinessName() {
		this.inputText(CompanyName, "slack");
		this.mouseActionClick(Continue);

	}

	public void maximumValidationBussinessWebSite() throws IOException {
		this.inputText(BussinessWebSite, getPropertyValue("2048Characters"));
		this.mouseActionClick(Continue);

	}

	public String errorWebsite() {
		String text = this.getText(BussinessWebsiteError);
		return text;

	}

	public void clearWebsite() {
		this.clearField(BussinessWebSite);

	}

	public void maximumValidationFirstName() throws IOException {
		this.inputText(FirstName, getPropertyValue("256Characters"));
		this.mouseActionClick(Continue);

	}

	public String errorFirstName() {
		String text = this.getText(FirstNameError);
		return text;

	}

	public void clearFirstName() {
		this.clearField(FirstName);

	}

	public void maximumValidationLastName() throws IOException {
		this.inputText(LastName, getPropertyValue("256Characters"));
		this.mouseActionClick(Continue);

	}

	public String errorLastName() {
		String text = this.getText(lastNameError);
		return text;

	}

	public void maximumValidationEmail() throws IOException {
		this.clearField(Email);
		this.validationTab(Email, getPropertyValue("256Characters"));
		this.mouseActionClick(Continue);

	}

	public void clearLastName() {
		this.clearField(LastName);

	}

	public void validationEmail() {
		this.validationTab(Email, "kjdnfiewnfniew");
		this.mouseActionClick(Continue);
	}

	public String errorEmail() {
		String text = this.getText(EmailError);
		return text;

	}

	public void clearEmail() {
		this.clearField(Email);

	}

	public void alreadyExistValidation() {
		this.validationTab(Email, "Hari@yahoo.com");

	}

	public void validEmail() {
		String Start = RandomStringUtils.randomNumeric(3);
		this.inputText(Email, "Hari" + Start + "@gmail.com");
		this.mouseActionClick(Continue);
		this.mouseActionClick(Continue);

	}

	public String messageLandingPage() {
		String text = this.getText(NextLandingPage);
		return text;

	}

	public void industryRadioButton() {
		for (int i = 1; i < 54; i++) {
			By xpath = By.xpath("(//input[@name='industryname'])[" + i + "]");
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).click();
		}
	}

	public void industryTypeFieldPresent() {
		this.mouseActionClick(MyBussinessType);

	}

	public String messagePresent() {
		String text = this.getText(IndustryField);
		return text;

	}

	public void maximumValidationIndustryField() {
		this.validationTab(Industry, characters256);

	}

	public String errorIndustryField() {
		String text = this.getText(IndustryError);
		return text;

	}

	public void clearIndustry() {
		this.clearField(Industry);
		this.mouseActionClick(Continue);

	}

	public void sizeCompany() {
		this.assertName(Continue, "Continue");
		for (int i = 1; i < 5; i++) {
			By xpath = By.id("empdiv" + i);
			wait = new WebDriverWait(driver, 10);
			WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
			Actions actions = new Actions(driver);
			actions.moveToElement(until).click().build().perform();
			String text = until.getText();
			Assert.assertEquals(text, text);
		}
		this.mouseActionClick(Continue);

	}

	public void radioButtonCurrent() {
		this.assertName(IntersetedToday, "Which Solution are you most interested in today?");
		for (int i = 1; i < 7; i++) {
			By xpath = By.xpath("(//input[@name='mostInrested'])[" + i + "]");
			wait = new WebDriverWait(driver, 10);
			WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
			Actions actions = new Actions(driver);
			actions.moveToElement(until).click().build().perform();
		}
		this.assertName(Continue, "Continue");
		this.mouseActionClick(Continue);
	}

	public void passwordFieldMandatory() {
		this.assertName(PasswordHeading, "Enter Your Password");
		this.assertName(Continue, "Continue");
		this.mouseActionClick(Continue);

	}

	public String errorPasswordField() {
		String text = this.getText(PasswordError);
		return text;

	}

	public void minimumValidationPassword() {
		this.validationTab(Password, "356");

	}

	public String errorMinPassword() {
		String text = this.getText(PasswordError);
		return text;

	}

	public void clearPassword() {
		this.clearField(Password);

	}

	public void minimumValidationConfirmPassword() {
		this.validationTab(ConfirmPassword, "356");

	}

	public String errorConfirmMessage() {
		String text = this.getText(ConfirmPasswordError);
		return text;

	}

	public void clearConfirmPassword() {
		this.clearField(ConfirmPassword);

	}

	public void passwordFieldCondition() {
		this.validationTab(Password, "63247456524545745");

	}

	public void confirmPasswordFieldCondition() {
		this.validationTab(ConfirmPassword, "63247456524545745");

	}

	public void lowercaseValidation() {
		this.inputText(Password, "Hari@1997");
		this.inputText(ConfirmPassword, "hari@1997");
		this.mouseActionClick(Continue);

	}

	public void mismatchPasswordValidation() {
		this.inputText(ConfirmPassword, "Mhari@1997");
		this.mouseActionClick(Continue);

	}

	public void createFirstPage() {
		for (int i = 0; i < 4; i++) {
			this.mouseActionClick(Back);
		}
		this.clearField(CompanyName);
		this.inputText(CompanyName, "OnePlus");
		this.inputText(BussinessWebSite, "www.oneplus.com");
		this.inputText(FirstName, "BBK");
		this.inputText(LastName, "Electronics");
		this.inputText(Email, "oneplus@plus.com");
		this.mouseActionClick(Continue);

	}

	By HomeCleaning = By.xpath("//*[@value='Home Cleaning']");

	public void createSecoundPage() {
		this.mouseActionClick(HomeCleaning);
		this.mouseActionClick(Continue);

	}

	By EmployeeCount = By.xpath("//*[text()='101-500 Employees']");

	public void createThirdPage() {
		this.mouseActionClick(EmployeeCount);
		this.mouseActionClick(Continue);

	}

	By Booking = By.xpath("//*[@value='Online Booking']");

	public void createFouthPage() {
		this.mouseActionClick(Booking);
		this.mouseActionClick(Continue);
		this.mouseActionClick(Continue);
	}

	public void createFifthPage() {
		this.inputText(Password, "Oneplus@123");
		this.inputText(ConfirmPassword, "Oneplus@123");
		this.mouseActionClick(Continue);

	}

	By CreatingTeanant = By.xpath("//*[text()='We're just adding some finishing touches']");

	public String createTenantValidation() {
		String text = this.getText(CreatingTeanant);
		return text;

	}

	String name = RandomStringUtils.randomAlphabetic(6);
	String OwnerFirstName = RandomStringUtils.randomAlphabetic(5);
	String OwnerLastName = RandomStringUtils.randomAlphabetic(5);

	/* Create Tenant User */
	public void firstPage() {

		this.inputText(CompanyName, "One" + name);
		this.inputText(BussinessWebSite, "www." + name + ".com");
		this.inputText(FirstName, OwnerFirstName);
		this.inputText(LastName, OwnerLastName);
		this.inputText(Email, name + "@mailinator.com");
		this.mouseActionClick(Continue);
		this.mouseActionClick(Continue);

	}

	public void secoundPage() {
		this.mouseActionClick(HomeCleaning);
		this.mouseActionClick(Continue);
	}

	public void thirdPage() {
		this.mouseActionClick(EmployeeCount);
		this.mouseActionClick(Continue);

	}

	public void fourthPage() {
		this.mouseActionClick(Booking);
		this.mouseActionClick(Continue);
		this.mouseActionClick(Continue);
	}

	By location = By.id("addresses");
	By firstLocation = By.xpath("(//*[@class='pac-item'])[1]");

	public void fifthPage() throws AWTException, InterruptedException {
		this.inputText(location, "Chennai");
		this.mouseActionClick(firstLocation);
		this.mouseActionClick(Continue);
		this.mouseActionClick(Continue);

	}

	By Dashboard = By.id("dashboard-menu");

	public void sixthPage() {
		this.inputText(Password, "Fieldy@123");
		this.inputText(ConfirmPassword, "Fieldy@123");
		this.mouseActionClick(Continue);
		this.assertName(Dashboard, "Dashboard");

	}

	/* Edit Owner Location */

	By Team = By.id("team-menu");
	By Tittle = By.xpath("//div[text()='Zaiportal Tenant 3']");
	By Edit = By.xpath("//div[@class='col-lg-2 col-md-2 col-sm-6 col-6']//child::button[@data-tabposition='1']");
	By Next = By.xpath("//span[text()='Next']");

	By Location = By.id("addresses__name__0");
	By EmailField = By.id("addresses__email__0");
	By ContactPerson = By.id("addresses__contact_person__0");
	By PhoneNumber = By.id("addresses__phone_number__0");
	By Building = By.id("addresses__line_1__0");
	By Street = By.id("addresses__line_2__0");
	By City = By.id("addresses__city__0");
	By State = By.id("addresses__state__0");
	By Zipcode = By.id("addresses__zipcode__0");
	By Save_Complete = By.id("team-company-edit-submit");

	String LocationName = RandomStringUtils.randomAlphabetic(10);
	String Phone = RandomStringUtils.randomNumeric(10);

	public void functionalityCompaniesEdit() {
		this.mouseActionClick(Team);
		this.mouseActionClick(Edit);
		this.mouseActionClick(Next);
		this.inputText(Location, LocationName);
		this.inputText(EmailField, name + "@mailinator.com");
		this.inputText(ContactPerson, OwnerFirstName + OwnerLastName);
		this.inputText(PhoneNumber, Phone);
		this.inputText(Street, name);
		this.mouseActionClick(Save_Complete);

	}

}
