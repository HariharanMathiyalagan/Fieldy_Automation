package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

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
import com.github.javafaker.Faker;

public class OnBoardingPage extends BaseClass {
	public static String BussinessName;
	public static String TenantFirstName;
	public static String TenantLastName;
	public static String TenantEmail;
	WebDriver driver;
	WebDriverWait wait;
	Faker faker = new Faker(new Locale("en-IND"));
	String fakeFirstName = faker.name().firstName();
	String fakeLastName = faker.name().lastName();
	String fakeEmail = faker.internet().safeEmailAddress();
	String fakePhoneNumber = faker.phoneNumber().phoneNumber();
	String fakeAddress1 = faker.address().buildingNumber();
	String fakeAddress2 = faker.address().streetName();
	String fakeCity = faker.address().city();
	String fakeState = faker.address().state();
	String fakeZipcode = faker.address().zipCode();
	String fakeWebsite = faker.company().url();
	String fakeCompanyName = faker.company().name().replaceAll("[^a-zA-Z0-9]", " ");
	String fakeFaxNumber = faker.number().digits(14);
	String fakeTittle = faker.name().title();

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

	public void updateValue() throws IOException {
		String CompanyName = BussinessName;
		String FirstName = TenantFirstName;
		String LastName = TenantLastName;
		String Email = TenantEmail;
		Properties properties = new Properties();
		properties.put("BussinessName", CompanyName);
		properties.put("FirstName", FirstName);
		properties.put("LastName", LastName);
		properties.put("UserName", Email);
		FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir") + "\\Folder\\Update.properties");
		properties.store(fo, "Update Value");

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

	private void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	private void inVisible(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

	}

	public String getText(By element) {
		wait = new WebDriverWait(driver, 100);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;

	}

	public String getAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;

	}

	public void visible(By element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();

	}

	public Boolean conditionChecking(By element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 5);
			text = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
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
	By xpath = By.xpath("(//input[@name='industryname'])[1]");
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
	By LastNameError = By.id("last_name_error");
	By EmailError = By.id("email_error");
	By ClickTrail = By.xpath("//*[@class='top-btn']");
	By LocationHeading = By.xpath("//*[text()='Where are you located?']");
	By ErrorLocation = By.id("addresses_error");
	By HomeCleaning = By.xpath("//*[@value='Home Cleaning']");
	By EmployeeCount = By.xpath("//*[text()='101-500 Employees']");
	By Booking = By.xpath("//*[@value='Online Booking']");
	By location = By.id("addresses");
	By firstLocation = By.xpath("(//*[@class='pac-item'])[1]");
	By CreatingTeanant = By.xpath("//*[text()='We're just adding some finishing touches']");
	By ButtonVisible = By.xpath("//*[@disabled='true']");
	By DashBoard = By.xpath("//*[text()=' Company Performance']");
	By OwnerName = By.id("dashboard-customer-name");

	HttpURLConnection connection;

	public void login() throws MalformedURLException, IOException {
		driver.get("https://getfieldy.com/");
		this.mouseActionClick(ClickTrail);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		String currentUrl = driver.getCurrentUrl();
		connection = (HttpURLConnection) new URL(currentUrl).openConnection();
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

	public void mandatoryEmailValidation() {
		this.clearField(Email);
		this.mouseActionClick(Continue);

	}

	public String manditoryValidations() {
		String text = this.getText(CompanyError);
		return text;

	}

	public void emailText() throws IOException {
		driver.get(getPropertyValue("OnBoardingURL"));
//		this.mouseActionClick(ClickTrail);
//		Set<String> windowHandles = driver.getWindowHandles();
//		ArrayList<String> list = new ArrayList<String>(windowHandles);
//		driver.switchTo().window(list.get(1));
		this.inputText(Email, "asddajs@dada.das");

	}

	public void maximumValidationBussinessName() throws IOException {
		this.validationTab(CompanyName, getPropertyValue("256Characters"));
//		this.mouseActionClick(Continue);

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
		this.inputText(CompanyName, fakeCompanyName);
	}

	public void alreadyBussinessName() {
		this.inputText(CompanyName, "Trevino and Trujillo Inc");
		this.mouseActionClick(Continue);

	}

	public void specialCharacterBussinessName() {
		this.validationTab(CompanyName, "!@#$%^&*");
//		this.mouseActionClick(Continue);

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
		String text = this.getText(LastNameError);
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
		this.validationTab(Email, "fieldy@mailinator.com");

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
		this.mouseActionClick(Continue);

	}

	public String errorIndustryField() {
		String text = this.getText(IndustryError);
		return text;

	}

	public void clearIndustry() {
		this.clearField(Industry);
		this.mouseActionClick(MyBussinessType);
		this.mouseActionClick(xpath);
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

	public void mandatoryLocationValidation() {
		this.assertName(LocationHeading, "Where are you located?");
		this.mouseActionClick(Continue);

	}

	public String requiredFieldLocation() {
		String text = this.getText(ErrorLocation);
		return text;

	}

	public void maximumValidationLocation() throws IOException {
		this.validationTab(location, getPropertyValue("256Characters"));

	}

	public void clearLocation() {
		this.clearField(location);
		this.inputText(location, "Chennai");
		this.mouseActionClick(firstLocation);
		this.elementtobeClickable(Continue);
		this.mouseActionClick(Continue);
		this.mouseActionClick(Continue);
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
		this.inputText(Password, "63247456524545745");
		this.mouseActionClick(Continue);

	}

	public void confirmPasswordFieldCondition() {
		this.inputText(ConfirmPassword, "63247456524545745");
		this.mouseActionClick(Continue);
		this.mouseActionClick(Continue);

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

	public String createTenantValidation() {
		String text = this.getText(CreatingTeanant);
		return text;

	}

	public String dashBoardPage() {
		String text = this.getText(DashBoard);
		return text;
	}

	public void validFillData(String value) throws IOException {
		if (value.equals("FirstPage")) {
			BussinessName = this.getAttribute(CompanyName);
			this.inputText(FirstName, fakeFirstName);
			TenantFirstName = this.getAttribute(FirstName);
			this.inputText(LastName, fakeLastName);
			TenantLastName = this.getAttribute(LastName);
			this.inputText(Email, fakeEmail);
			TenantEmail = this.getAttribute(Email);
			this.validationTab(BussinessWebSite, fakeWebsite);
			if (this.conditionChecking(Continue)) {
				this.mouseActionClick(Continue);
			} else {
				do {
					if (errorEmail().equals(getPropertyValue("AlreadyExistedEmail1"))) {
						Faker faker = new Faker(new Locale("en-IND"));
						this.clearField(Email);
						String fakeEmail = faker.internet().safeEmailAddress();
						this.validationTab(Email, fakeEmail);
						TenantEmail = this.getAttribute(Email);
					} else if (errorMessageBussinessName().equals(getPropertyValue("BussinessNameAlready"))) {
						Faker faker = new Faker(new Locale("en-IND"));
						this.clearBussinessName();
						String fakeCompanyName = faker.company().name().replaceAll("[^a-zA-Z0-9]", " ");
						this.validationTab(CompanyName, fakeCompanyName);
						BussinessName = this.getAttribute(CompanyName);
					} else if (errorEmail().equals(getPropertyValue("AlreadyExistedEmail1"))
							&& errorMessageBussinessName().equals(getPropertyValue("BussinessNameAlready"))) {
						Faker faker = new Faker(new Locale("en-IND"));
						this.clearBussinessName();
						this.clearEmail();
						String fakeEmail = faker.internet().safeEmailAddress();
						String fakeCompanyName = faker.company().name().replaceAll("[^a-zA-Z0-9]", " ");
						this.validationTab(Email, fakeEmail);
						TenantEmail = this.getAttribute(Email);
						this.validationTab(CompanyName, fakeCompanyName);
						BussinessName = this.getAttribute(CompanyName);
					}
				} while (!this.conditionChecking(Continue));
				this.mouseActionClick(Continue);
			}

		} else if (value.equals("Password")) {
			this.inputText(Password, getPropertyValue("Password"));
			this.inputText(ConfirmPassword, getPropertyValue("Password"));
			this.mouseActionClick(Continue);
		}

	}

	public String urlGet() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public String expectedURL() throws IOException {
		String expectedURL = "https://" + (BussinessName.toLowerCase().replaceAll("\\s", ""))
				+ getPropertyValue("DomainURL") + "/";
		return expectedURL;
	}

	public String getOwnerName() {
		String text = this.getText(OwnerName);
		return text;
	}

	public String expectedOwnerName() {
		String Name = TenantFirstName + " " + TenantLastName;
		return Name;

	}

	public void fillData() throws IOException {
		driver.get(getPropertyValue("OnBoardingURL"));
		this.inputText(CompanyName, fakeCompanyName);
		this.validFillData("FirstPage");
		this.mouseActionClick(xpath);
		this.mouseActionClick(Continue);
		this.sizeCompany();
		this.radioButtonCurrent();
		this.clearLocation();
		this.validFillData("Password");

	}

}
