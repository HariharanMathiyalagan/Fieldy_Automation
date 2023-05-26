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
	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String prefixName = RandomStringUtils.randomAlphabetic(4).toUpperCase();
	String prefixNumber = RandomStringUtils.randomNumeric(50);

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
	By prefix_settings_label = By.xpath("//*[@id=\'setting-nav-menu\']/div/nav/div/ul/li[2]/a");
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
	By ErrorMessageJob = By.xpath("//span[text()='The jobs_prefix is required']");
	By ErrorMessageJobMax = By.xpath("//span[text()='The jobs prefix may not be greater than 45 characters.']");
	By ErrorMessagePrefixRequired = By.xpath("//span[text()='This job prefix number is required.']");
	By ErrorMessageReqPrefixRequired = By.xpath("//span[text()='The requests_prefix is required']");
	By ErrorMessageReqMax = By.xpath("//span[text()='The requests prefix may not be greater than 45 characters.']");
	By ErrorMessageReqNumRequired = By.xpath("//span[text()='This request prefix number is required.']");
	By ErrorMessageReqNumMax2 = By
			.xpath("//span[text()='The request prefix number may not be greater than 45 characters.']");
	By ErrorMessageQuotePrefixmandatory = By.xpath("//span[text()='The quote_prefix is required']");
	By ErrorMessageQuotePrefixMax = By
			.xpath("//span[text()='The quote prefix may not be greater than 45 characters.']");

	By ErrorMessageQuoteNummandatory = By.xpath("//span[text()='This quote prefix number is required.']");
	By ErrorMessageQuoteNumMax = By
			.xpath("//span[text()='The quote prefix number may not be greater than 45 characters.']");

	
	
	By ErrorMessageInvoiceNameMandatory = By
			.xpath("//span[text()='The invoice_prefix is required']");

	By ErrorMessageInvoiceNameMax = By.xpath("//span[text()='The invoice prefix may not be greater than 45 characters.']");
	
	
	By ErrorMessageInvoiceNumMandatory = By
			.xpath("//span[text()='This invoice prefix number is required.']");
	
	By ErrorMessageInvoiceNumMax = By
			.xpath("//span[text()='The invoice prefix number may not be greater than 45 characters.']");
			
	
	
	By SuccessMessage = By
			.xpath("//span[text()='Tenant business settings Updated successfully.']");
	
	
	By duplicateErroMessageReq = By.xpath("//span[text()='The sequence is already exists for the request']");
	
	public String modulePage() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.mouseActionClick(settings_menu);
		this.mouseActionClick(settings_business);
		this.visibility(list_bussiness_unit_name);
		this.mouseActionClick(Prefix_Setting);
		String text = this.getText(prefix_settings_label);
		this.invisible(Loader);
		Thread.sleep(5000);
		return text;
	}

	public void jobprefix(String value) {
		// TODO Auto-generated method stub

		if (value.equals("maximumvalidation")) {
			this.inputText(Prefix_Job_Name, characters256);
		}
		this.clickButton(Btn_SaveChanges);

	}

	public Boolean conditionChecking(By element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 20);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	static String message;

	public String responsemessage(String value) {
		if (this.conditionChecking(Message)) {
			message = this.getText(Message);
			return message;
		} else {
			do {
				this.clickButton(Btn_SaveChanges);

			} while (!this.conditionChecking(Message));
		}
		return message;
	}

	public void clickSubmit() {

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(Btn_SaveChanges)).click();

	}

	public void enterJobPrefix() throws IOException {

		this.inputText(Prefix_Job_Name, characters256);
	}

	public String getErrorMessage() {

		String eMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageJob)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageJob));
		return eMessage;
	}

	public String getErrorMessageMax() {

		String getErrorMaxMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageJobMax))
				.getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageJobMax));
		return getErrorMaxMessage;
	}

	public String getErrorMessagePreFixNumberRequired() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessagePrefixRequired)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessagePrefixRequired));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageRequestPreFixNumberRequired() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageReqPrefixRequired)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageReqPrefixRequired));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageRequestPreFixNumberMax2() {

		String getErrorRequiredMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageReqMax))
				.getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageReqMax));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageRequestPreFixNumberReq() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageReqNumRequired)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageReqNumRequired));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageRequestPreFixNumberMax() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageReqNumMax2)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageReqNumMax2));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageQuotePrfixMandatory() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageQuotePrefixmandatory)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageQuotePrefixmandatory));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageQuotePrefixMax() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageQuotePrefixMax)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageQuotePrefixMax));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageQuoteNumMandatory() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageQuoteNummandatory)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageQuoteNummandatory));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageQuoteNumMax() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageQuoteNumMax)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageQuoteNumMax));
		return getErrorRequiredMessage;

	}

	public String getErrorMessageInvoiceNameMandatory() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageInvoiceNameMandatory)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageInvoiceNameMandatory));
		return getErrorRequiredMessage;

	}
	
	
	public String getErrorMessageInvoiceNameMax() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageInvoiceNameMax)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageInvoiceNameMax));
		return getErrorRequiredMessage;

	}
	
	
	public String getErrorMessageInvoiceNumNull() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageInvoiceNumMandatory)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageInvoiceNumMandatory));
		return getErrorRequiredMessage;

	}
	
	
	public String getErrorMessageInvoiceNumMax() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessageInvoiceNumMax)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ErrorMessageInvoiceNumMax));
		return getErrorRequiredMessage;

	}
	
	public String getDuplicateErrorMessageRequest() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(duplicateErroMessageReq)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(duplicateErroMessageReq));
		return getErrorRequiredMessage;

	}
	
	
	
	public String getSuccessMessage() {

		String getErrorRequiredMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(SuccessMessage)).getText();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(SuccessMessage));
		return getErrorRequiredMessage;

	}
	
	public void Refresh() throws InterruptedException {
		//wait.until(ExpectedConditions.visibilityOfElementLocated(Prefix_Job_No));
		
		driver.get(driver.getCurrentUrl());
		Thread.sleep(3000);
	}
	
	

	public void clearField() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Prefix_Job_No));
		driver.findElement(Prefix_Job_No).clear();
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
			this.validationTab(Prefix_Request_Name, characters256);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("RequestPrefixNumber")) {
			this.validationTab(Prefix_Request_No, prefixNumber);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("QuotePrefix")) {
			this.validationTab(Prefix_Quote_Name, characters45);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("QuotePrefixNumber")) {
			this.validationTab(Prefix_Quote_No, prefixNumber);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("InvoicePrefix")) {
			this.validationTab(Prefix_Invoice_Name, characters45);
			this.mouseActionClick(Btn_SaveChanges);
		} else if (value.equals("InvoicePrefixNumber")) {
			this.validationTab(Prefix_Invoice_No,prefixNumber);
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
			this.clearField(Prefix_Quote_Name);
		} else if (value.equals("QuotePrefixNumber")) {
			this.clearField(Prefix_Quote_No);
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
