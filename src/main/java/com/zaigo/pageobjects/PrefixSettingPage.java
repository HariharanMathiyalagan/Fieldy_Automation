package com.zaigo.pageobjects;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

public class PrefixSettingPage extends BaseClass {
	static String responseMessage;
	WebDriver driver;
	WebDriverWait wait;

	public PrefixSettingPage(WebDriver driver) {
		this.driver = driver;
	}

	Faker faker = new Faker(new Locale("en-IND"));
	String BusinessName = faker.app().name();
	String characters45 = RandomStringUtils.randomAlphabetic(50);
	String prefixName = RandomStringUtils.randomAlphabetic(4).toUpperCase();
	String prefixNumber = RandomStringUtils.randomNumeric(4);

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	private void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	private void clearField(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
	}

	private void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	private void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	private void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	private void dropDownByIndex(WebElement element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	private String getText(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;
	}

	private String getText(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return until;
	}

	private void mouseAction(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();
	}

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	private void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(element, value));
	}

	public void invisible(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	public void invisible(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	@FindAll({ @FindBy(xpath = "//*[text()='The jobs prefix may not be greater than 45 characters']"),
			@FindBy(xpath = "//*[text()='The requests prefix may not be greater than 45 characters']"),
			@FindBy(xpath = "//*[text()='The invoice prefix may not be greater than 45 characters']"),
			@FindBy(xpath = "//*[text()='The quote prefix may not be greater than 45 characters']"),
			@FindBy(xpath = "//*[text()='The job prefix number may not be greater than 45 characters.']"),
			@FindBy(xpath = "//*{text()='The request prefix number may not be greater than 45 characters.']"),
			@FindBy(xpath = "//*[text()='The quote prefix number may not be greater than 45 characters.']"),
			@FindBy(xpath = "//*[text()='The invoice prefix number may not be greater than 45 characters.']") })
	WebElement error_max_character;

	@FindAll({ @FindBy(xpath = "//*[text()='The requests_prefix is required']"),
			@FindBy(xpath = "//*[text()='The jobs_prefix is required']"),
			@FindBy(xpath = "//*[text()='The quote_prefix is required']"),
			@FindBy(xpath = "//*[text()='The invoice_prefix is required']"),
			@FindBy(xpath = "//*[text()='This job prefix number is required.']"),
			@FindBy(xpath = "//*[text()='This quote prefix number is required.']"),
			@FindBy(xpath = "//*[text()='This request prefix number is required.']"),
			@FindBy(xpath = "//*[text()='This invoice prefix number is required.']") })
	WebElement error_mandatory;

	By settings_menu = By.xpath("//a[@id='settings-menu']");
	By settings_business = By.xpath("//div[text()=' Business Settings ']");
	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By Prefix_Setting = By.xpath("//*[@id='detail-business-settings']//ul//li[2]");
	By settings_business_settings_label = By.xpath("//*[@id='fieldy-body-ele']//header//a");
	By Prefix_Job_Name = By.xpath("//input[@id='prefix-value-job']");
	By Prefix_Job_No = By.xpath("//input[@id='prefix-number-job']");
	By Prefix_Request_Name = By.xpath("//input[@id='prefix-value-request']");
	By Prefix_Request_No = By.xpath("//input[@id='prefix-number-request']");
	By Prefix_Quote_Name = By.xpath("//input[@id='prefix-value-quote']");
	By Prefix_Quote_No = By.id("prefix-number-quote");
	By Prefix_Invoice_Name = By.id("prefix-value-invoice");
	By Prefix_Invoice_No = By.id("prefix-number-invoice");
	By Btn_SaveChanges = By.xpath("//button[contains(text(),'Save Changes')]");
	By list_bussiness_unit_name = By.xpath("//*[@id='bussiness-unit-ele']/div[1]/div/div/div[1]");
	By Loader = By.xpath("//*[@id='spinnerDiv']//child::div//div//div");
	By Tittle = By.xpath("//*[@title='Change the Job, Request, Quote & Invoice Prefix']");

	public String modulePage() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.mouseActionClick(settings_menu);
		this.mouseActionClick(settings_business);
		this.visibility(list_bussiness_unit_name);
		this.mouseActionClick(Prefix_Setting);
		String text = this.getText(settings_business_settings_label);
		this.invisible(Loader);
		Thread.sleep(5000);
		return text;
	}

	public void mandatoryValidation(String value) {
		if (value.equals("JobPrefix")) {
			this.clearField("JobPrefix");
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("JobPrefixNumber")) {
			this.clearField("JobPrefixNumber");
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("RequestPrefix")) {
			this.clearField("RequestPrefix");
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("RequestPrefixNumber")) {
			this.clearField("RequestPrefixNumber");
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("QuotePrefix")) {
			this.clearField("QuotePrefix");
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("QuotePrefixNumber")) {
			this.clearField("QuotePrefixNumber");
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("InvoicePrefix")) {
			this.clearField("InvoicePrefix");
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("InvoicePrefixNumber")) {
			this.clearField("InvoicePrefixNumber");
			this.mouseActionClick(Btn_SaveChanges);
		}
	}

	public void maxValidation(String value) throws IOException {
		if (value.equals("JobPrefix")) {
			this.validationTab(Prefix_Job_Name, characters45);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("JobPrefixNumber")) {
			this.validationTab(Prefix_Job_No, getPropertyValue("MaxNumber"));
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("RequestPrefix")) {
			this.validationTab(Prefix_Request_Name, characters45);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("RequestPrefixNumber")) {
			this.validationTab(Prefix_Request_No, getPropertyValue("MaxNumber"));
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("QuotePrefix")) {
			this.validationTab(Prefix_Quote_Name, characters45);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("QuotePrefixNumber")) {
			this.validationTab(Prefix_Quote_No, getPropertyValue("MaxNumber"));
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("InvoicePrefix")) {
			this.validationTab(Prefix_Invoice_Name, characters45);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("InvoicePrefixNumber")) {
			this.validationTab(Prefix_Invoice_No, getPropertyValue("MaxNumber"));
			this.mouseActionClick(Btn_SaveChanges);
		}

	}

	public void clearField(String value) {
		if (value.equals("JobPrefix")) {
			this.clearField(Prefix_Job_Name);
		} else if (value.equals("JobPrefixNumber")) {
			this.clearField(Prefix_Job_No);
		} else if (value.equals("RequestPrefix")) {
			this.clearField(Prefix_Request_Name);
		} else if (value.equals("RequestPrefixNumber")) {
			this.clearField(Prefix_Request_No);
		} else if (value.equals("QuotePrefix")) {
			this.clearField(Prefix_Quote_No);
		} else if (value.equals("QuotePrefixNumber")) {
			this.clearField(Prefix_Quote_Name);
		} else if (value.equals("InvoicePrefix")) {
			this.clearField(Prefix_Invoice_Name);
		} else if (value.equals("InvoicePrefixNumber")) {
			this.clearField(Prefix_Invoice_No);
		}
	}

	public void validData(String value) throws IOException {
		if (value.equals("JobPrefix")) {
			this.inputText(Prefix_Job_Name, prefixName);
		} else if (value.equals("JobPrefixNumber")) {
			this.inputText(Prefix_Job_No, prefixNumber);
		} else if (value.equals("RequestPrefix")) {
			this.inputText(Prefix_Request_Name, prefixName);
		} else if (value.equals("RequestPrefixNumber")) {
			this.inputText(Prefix_Request_No, prefixNumber);
		} else if (value.equals("QuotePrefix")) {
			this.inputText(Prefix_Quote_Name, prefixName);
		} else if (value.equals("QuotePrefixNumber")) {
			this.inputText(Prefix_Quote_Name, prefixNumber);
		} else if (value.equals("InvoicePrefix")) {
			this.inputText(Prefix_Invoice_Name, prefixName);
		} else if (value.equals("InvoicePrefixNumber")) {
			this.inputText(Prefix_Invoice_Name, prefixNumber);
		}
	}

	public String message(String value) {
		if (value.equals("Mandatory")) {
			String text = this.getText(error_mandatory);
			this.invisible(error_mandatory);
			return text;
		} else if (value.equals("MaxValidation")) {
			String text = this.getText(error_max_character);
			this.invisible(error_max_character);
			return text;
		}
		return value;
	}

}
