package com.zaigo.pageobjects;

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
		wait = new WebDriverWait(driver, 10);
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
		driver.get("http://zaiportal.com/public/Onbording/meet.html");
		connection = (HttpURLConnection) new URL("http://zaiportal.com/public/Onbording/meet.html").openConnection();
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
		this.validationTab(BussinessWebSite, getPropertyValue("2048Characters"));
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
		this.validationTab(FirstName, getPropertyValue("256Characters"));
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
		this.validationTab(LastName, getPropertyValue("256Characters"));
		this.mouseActionClick(Continue);

	}

	public String errorLastName() {
		String text = this.getText(lastNameError);
		return text;

	}

	public void maximumValidationEmail() throws IOException {
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

}
