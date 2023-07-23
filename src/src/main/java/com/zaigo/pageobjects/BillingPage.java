package com.zaigo.pageobjects;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.github.javafaker.Faker;

public class BillingPage extends BaseClass {
	WebDriver driver;
	WebDriverWait wait;

	public BillingPage(WebDriver driver) {
		this.driver = driver;
	}

	Faker faker = new Faker(new Locale("en-IND"));
	String BusinessName = faker.app().name();
	String characters45 = RandomStringUtils.randomAlphabetic(50);
	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String prefixName = RandomStringUtils.randomAlphabetic(4).toUpperCase();
	String prefixNumber = RandomStringUtils.randomNumeric(50);
	String email_txt = "normal@gmail.com";
	String zipcode = "600075";

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	public void inputText(WebElement element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
	}

	private void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

//	private void clearField(WebElement element) {
//		wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOf(element)).clear();
//	}
//
	private void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	private void elementtobeClickable(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private void clickButton(WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	private int listWebElement(By element) {
		wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)).size();
	}

	private void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 20);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	private void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	private void tagValidation(WebElement element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text, Keys.ENTER);
	}

	private void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	private String getText(By element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;
	}

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	private String getText(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return until;
	}

	private void mouseAction(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();
	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}

	public Boolean valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		Boolean text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		return text;
	}

	public Boolean valuePresent(WebElement element, String value) {
		wait = new WebDriverWait(driver, 50);
		Boolean text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		return text;
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	public String getTextAttribute(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getAttribute("value");
		return until;
	}

	public Boolean conditionChecking(WebElement element, int value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, value);
			text = wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking(By element, int value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, value);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	By Profile = By.xpath("//*[@id='avator-ele']");
	By Subscribtion = By.xpath("//*[@id='myDropdown']//li[2]");
	By StarterAmount = By.id("starter_price");
	By TeamAmount = By.id("team_price");
	By PowerHouseAmount = By.id("power_house_price");
	By StarterDropDown = By.id("starter_select");
	By TeamDropDown = By.id("team_select");
	By PowerHouse = By.id("power_house_select");
	By StarterChoosePlan = By.xpath("//*[@id='starter_plan_header']//button");
	By TeamChoosePlan = By.xpath("//*[@id='team_plan_header']//button");
	By PowerHouseChoosePlan = By.xpath("//*[@id='power_house_plan_header']//button");
	By ProRataAmount = By.xpath("//*[@id='tb_total_pro_rata_amount']");
	By TotalAmount = By.xpath("//*[@id='tb_total_amount']");
	@FindAll({ @FindBy(xpath = "//*[@id='upgrade-proceed-to-payment']/div[3]/div/div[2]/div[2]/button"),
			@FindBy(xpath = "//*[@id='tb_proceed_to_payment']"), @FindBy(xpath = "//*[@id='rzp-button1']"),
			@FindBy(xpath = "//*[@id='subscription_contianers']/div[2]/div/div/div[3]/button") })
	WebElement SubmitButton;
	By MonthlyAmount = By.xpath("//*[@id='monthly-amount']");
	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div/div");
	@FindAll({ @FindBy(xpath = "//*[contains(text(),'Payment Details')]"),
			@FindBy(xpath = "//*[contains(text(),'Billing Contact :')]//ancestor::*[@id='fieldy-body-ele']//*[@id='upgrade-proceed-to-payment']//header//div//div//div//span"),
			@FindBy(xpath = "//*[@id='starter_plan_header']//ancestor::*[@id='fieldy-body-ele']//*[@id='change_plan_monthly-header']//header//div//div//div//span"),
			@FindBy(xpath = "//*[text()='Confirm Order']//ancestor::*[@id='fieldy-body-ele']//*[@id='change_plan_monthly-header']//header//div//div//div//span") })
	WebElement Label;
	By MainAmount = By.xpath("//*[@id='main-total']");
	By pay_btn = By.xpath("//button[contains(text(),'Pay')]");
	By FirstName = By.id("billing_first_name");
	By Firstname_errormessage = By.id("billing_first_name_error");
	By Lastname = By.id("billing_last_name");
	By Lastname_errormessage = By.id("billing_last_name_error");
	By Companyfield = By.id("company");
	By company_error = By.id("company_error");
	By email = By.id("billing_email");
	By Email_error = By.id("billing_email_error");
	By billing_address = By.id("billing_address_one");
	By address_error = By.id("billing_address_one_error");
	By city = By.id("billing_city");
	By city_error = By.id("billing_city_error");
	By state = By.id("billing_state");
	By state_error = By.id("billing_state_error");
	By Zipcode = By.id("billing_zipcode");
	By Zipcode_error = By.id("billing_zipcode_error");
	By lable = By.xpath("//span[@data-automationid='payment-details']");

	public void clearField(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
	}

	public void click_pay() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(pay_btn)).click();
	}

	public String FirstnameErrorMessage() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(Firstname_errormessage)).getText();
		return until;

	}

	public String LastnameErrorMessage() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(Lastname_errormessage)).getText();
		return until;

	}

	public String CompanyErrorMessage() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(company_error)).getText();
		return until;

	}

	public String AddressError() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(address_error)).getText();
		return until;

	}

	public String EmailErrorMessage() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(Email_error)).getText();
		return until;

	}

	public String CityErrorMessage() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(city_error)).getText();
		return until;

	}

	public String StateErrorMessage() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(state_error)).getText();
		return until;

	}

	public String ZipcodeErrorMessage() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(Zipcode_error)).getText();
		return until;

	}

	public String PaymentLable() {

		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(lable)).getText();
		return until;

	}

	public void modulePage() {
		this.mouseAction(Profile);
		this.mouseActionClick(Subscribtion);
		this.invisible(Spinner);
		this.mouseActionClick(SubmitButton);
		if (!this.conditionChecking(Label, 2)) {
			do {
				this.mouseActionClick(SubmitButton);
			} while (!this.conditionChecking(Label, 2));
		}
	}

	public void visitPaymentPage() {
		this.mouseActionClick(pay_btn);
		if (!this.conditionChecking(Label, 2))
			do {

				this.mouseActionClick(pay_btn);

			} while (!this.conditionChecking(Label, 2));
	}

	public String labelValidation(String value) {
		if (value.equals("ChoosePlan")) {
			this.mouseActionClick(StarterChoosePlan);
			if (!this.conditionChecking(ProRataAmount, 3)) {
				do {
					this.mouseActionClick(StarterChoosePlan);
				} while (!this.conditionChecking(ProRataAmount, 3));
			}
			this.visibility(ProRataAmount);
		} else if (value.equals("1") || value.equals("2") || value.equals("3")) {
			this.mouseActionClick(SubmitButton);
			switch (value) {
			case "2":
				this.visibility(By.xpath(
						"//*[contains(text(),'Billing Contact :')]//ancestor::*[@id='fieldy-body-ele']//*[@id='upgrade-proceed-to-payment']//header//div//div//div//span"));
				break;
			case "3":
				this.visibility(By.xpath("//*[contains(text(),'Payment Details')]"));
				break;
			default:
				break;
			}
		}
		this.invisible(Spinner);
		return this.getText(Label);
	}

	public double convertion(int value) {
		if (value == 1) {
			String intamount = intialAmount.replace("₹", "").replace(",", "");
			return Double.parseDouble(intamount);
		} else if (value == 2) {
			String proamount = proAmount.replace("₹", "").replace(",", "");
			return Double.parseDouble(proamount);
		}
		return value;
	}

	static String proAmount;
	static String intialAmount;

	public void subscriptionFlow(String value) {
		if (value.equals("Starter")) {
			intialAmount = this.getText(StarterAmount);
			this.dropDownByIndex(StarterDropDown, 2);
			proAmount = this.getTextAttribute(StarterDropDown);
		}
	}

	public String getValue(String value) {
		if (value.equals("ProAmount")) {
			return this.getText(ProRataAmount);
		} else if (value.equals("TotalAmount")) {
			return this.getText(TotalAmount);
		} else if (value.equals("BillingAmount")) {
			return this.getText(MonthlyAmount);
		} else if (value.equals("MainAmount")) {
			return this.getText(MainAmount);
		}
		return value;
	}

	public static double convertion;
	public static String valueOf;

	public String calculation(String value) {
		if (value.equals("Starter")) {
			convertion = this.convertion(1) * 3;
		} else if (value.equals("TotalAmount")) {
			convertion = convertion * 18 / 100 + convertion;
		}
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		String format = decimalFormat.format(convertion);
		valueOf = String.valueOf(format);
		return "₹" + valueOf;
	}

	public void validData(String value) throws IOException {
		if (value.equals("firstname")) {
			this.clearField(FirstName);
			this.inputText(FirstName, BusinessName);
		} else if (value.equals("lastname")) {
			this.clearField(Lastname);
			this.inputText(Lastname, BusinessName);
		} else if (value.equals("email")) {
			this.clearField(email);
			this.inputText(email, email_txt);
		} else if (value.equals("companyname")) {
			this.clearField(Companyfield);
			this.inputText(Companyfield, BusinessName);
		} else if (value.equals("address")) {
			this.clearField(billing_address);
			this.inputText(billing_address, BusinessName);
		} else if (value.equals("city")) {
			this.clearField(city);
			this.validationTab(city, BusinessName);
		} else if (value.equals("state")) {
			this.clearField(state);
			this.validationTab(state, BusinessName);
		} else if (value.equals("zipcode")) {
			this.clearField(Zipcode);
			this.validationTab(Zipcode, zipcode);
		}
	}

	public void maxValidation(String value) throws IOException {
		if (value.equals("email")) {
			this.validationTab(email, characters45);
		} else if (value.equals("firstname")) {
			this.validationTab(FirstName, characters256);
		} else if (value.equals("lastname")) {
			this.validationTab(Lastname, characters256);
		} else if (value.equals("company")) {
			this.validationTab(Companyfield, characters256);
		} else if (value.equals("address")) {
			this.validationTab(billing_address, characters256);
		} else if (value.equals("city")) {
			this.validationTab(city, characters256);
		} else if (value.equals("state")) {
			this.validationTab(state, characters256);
		} else if (value.equals("zipcode")) {
			this.validationTab(Zipcode, prefixNumber);
		}

	}

	public void clearField(String value) {
		if (value.equals("firstname")) {
			this.clearField(FirstName);
		} else if (value.equals("LastName")) {
			this.clearField(Lastname);
		} else if (value.equals("company")) {
			this.clearField(Companyfield);
		} else if (value.equals("addressone")) {
			this.clearField(billing_address);
		} else if (value.equals("email")) {
			this.clearField(email);
		} else if (value.equals("state")) {
			this.clearField(state);
		} else if (value.equals("city")) {
			this.clearField(city);
		} else if (value.equals("zipcode")) {
			this.clearField(Zipcode);
		}
	}

}
