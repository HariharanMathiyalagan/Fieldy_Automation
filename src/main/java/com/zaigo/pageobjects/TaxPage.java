package com.zaigo.pageobjects;

import java.io.IOException;
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
import org.testng.SkipException;

import com.base.BaseClass;
import com.github.javafaker.Business;
import com.github.javafaker.Faker;

public class TaxPage extends BaseClass {
	WebDriver driver;
	WebDriverWait wait;
	Faker faker = new Faker(new Locale("en-IND"));
	String fakeTaxName = faker.book().genre();
	String fakeTaxPercentage = faker.number().digits(2);

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters16 = RandomStringUtils.randomAlphabetic(20);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String numberCharacter15 = RandomStringUtils.randomNumeric(15);
	String QuantityValue = RandomStringUtils.randomNumeric(2);
	String PriceValue = RandomStringUtils.randomNumeric(4);
	String DisTaxValue = RandomStringUtils.randomNumeric(2);
	String TaxValue = RandomStringUtils.randomNumeric(2);
	String ReferencePrefix = RandomStringUtils.randomAlphabetic(3).toUpperCase();
	String ReferenceNo = RandomStringUtils.randomNumeric(3);

	public TaxPage(WebDriver driver) {
		this.driver = driver;

	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 20);
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
		wait = new WebDriverWait(driver, 100);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 10);
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

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	public boolean elementPresent(By element) {
		wait = new WebDriverWait(driver, 50);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
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

	private void dropDownByIndex(WebElement element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
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

	private void invisible(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf(element));

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
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	By settings_menu = By.xpath("//a[@id='settings-menu']");

	By TaxSettings = By.xpath("//*[text()=' Tax Settings']");

	By Label = By.xpath("//*[@id='fieldy-body-ele']//header//div//div//div//a");

	By Edit = By.xpath("//*[@id='tax-list']/div[1]/div/div/div[3]/button[1]");

	By Delete = By.xpath("//*[@id='tax-list']/div[1]/div/div/div[3]/button[2]");

	By ListTaxName = By.xpath("//*[@id='tax-list']/div[1]/div/div/div[1]");

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/div/div[1]/div/div/div/button"),
			@FindBy(xpath = "//*[@id='product-create']//*[contains(text(),'Save')]"),
			@FindBy(xpath = "//*[@id='product-create']//*[contains(text(),'Update')]"),
			@FindBy(xpath = "//*[@id='job-show-details-timeline']/div[2]/div[3]/button") })
	WebElement Button;

	By ProductLabel = By.xpath("//*[@id='product-create']/header/div/div/div/a");

	By TaxName = By.id("tax-name");

	By TaxPercentage = By.id("tax-rate");

	By TaxNameError = By.id("tax-name_error");

	By TaxPercentageError = By.id("tax-rate_error");

	By Message = By.xpath("//*[@class='js-snackbar__message']");

	By ProductService = By.xpath("//*[text()=' Product / Service']");

	By Back = By.xpath("//*[@id='fieldy-body-ele']/div[1]/header/div/div/div/a/img");

	By Yes = By.xpath("//*[text()='Yes']");

	By AssociateTaxCheckBox = By.xpath("//*[@id='tax-view']");

	By SubTaxName1 = By.xpath("//*[@id='associated_tax']//div[1]//div[1]//div[1]//div//input");

	By SubTaxName2 = By.xpath("//*[@id='associated_tax']//div//div[2]//div[1]//div//input");

	By SubTaxNumber1 = By.xpath("//*[@id='associated_tax']//div//div[1]//div[2]//div//input");

	By SubTaxNumber2 = By.xpath("//*[@id='associated_tax']//div//div[2]//div[2]//div//input");

	@FindAll({
			@FindBy(xpath = "//*[@placeholder='Search by Service Name ...']//ancestor::div[@id='fieldy-body-ele']//*[text()='Name']"),
			@FindBy(xpath = "//*[text()='No Result Found']"),
			@FindBy(xpath = "//*[@placeholder='Search by Product Name ...']//ancestor::div[@id='fieldy-body-ele']//*[text()='Name']") })
	WebElement ListPage;

	@FindAll({ @FindBy(xpath = "//*[@id='tax-autocomplete-list']/div[1]"),
			@FindBy(xpath = "//*[text()='No Data Found']") })
	WebElement FirstTaxName;

	public String modulePage(String value) {
		if (value.equals("ListPage")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(TaxSettings);
		} else if (value.equals("CreatePage")) {
			this.mouseActionClick(Button);
			this.visibility(TaxName);
		} else if (value.equals("EditPage")) {
			String text = this.getText(ListTaxName);
			this.mouseActionClick(Edit);
			this.valuePresent(TaxName, text);
		}
		String text = this.getText(Label);
		return text;
	}

	public void clearField(String value) {
		if (value.equals("TaxName")) {
			this.clearField(TaxName);
			this.inputText(TaxName, fakeTaxName);
		} else if (value.equals("TaxPercentage")) {
			this.clearField(TaxPercentage);
//			this.inputText(TaxPercentage, fakeTaxPercentage);
		} else if (value.equals("SubTaxName")) {
			this.clearField(SubTaxName1);
		} else if (value.equals("SubTaxPercentage")) {
			this.clearField(SubTaxNumber1);
		}
	}

	public String errorField(String value) {
		if (value.equals("TaxName")) {
			return this.getText(TaxNameError);
		} else if (value.equals("TaxPercentage")) {
			return this.getText(TaxPercentageError);
		}
		return value;
	}

	public void validRecord() {
		this.inputText(TaxName, fakeTaxName);
		this.inputText(TaxPercentage, fakeTaxPercentage);
	}

	static String ResponseMessage;

	public String message(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message")) {
			if (this.conditionChecking(Message, 20)) {
				ResponseMessage = this.getText(Message);
				this.invisible(Message);
				return ResponseMessage;
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(Button);
					if (this.conditionChecking(Message, 20)) {
						ResponseMessage = this.getText(Message);
						this.invisible(Message);
						if (ResponseMessage.equals(getPropertyValue("CreatedTax"))
								|| ResponseMessage.equals(getPropertyValue("EditedTax"))
								|| ResponseMessage.equals(getPropertyValue("AlreadyTax"))
								|| ResponseMessage.equals(getPropertyValue("AssociateTaxValidation"))
								|| ResponseMessage.equals(getPropertyValue("AssociateTaxNameUnique"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateMessage")) {
			do {
				if (ResponseMessage.equals(getPropertyValue("MaxTaxPercentage"))) {
					String TaxValue = RandomStringUtils.randomNumeric(2);
					this.clearField(TaxPercentage);
					this.inputText(TaxPercentage, TaxValue);
					this.mouseActionClick(Button);
				} else if (ResponseMessage.equals(getPropertyValue("AlreadyTax"))) {
					Faker faker = new Faker(new Locale("en-IND"));
					String fakeTaxName = faker.book().genre();
					String upperCase = RandomStringUtils.randomAlphabetic(5).toUpperCase();
					this.clearField(TaxName);
					this.inputText(TaxName, fakeTaxName + upperCase);
					this.mouseActionClick(Button);
				} else if (ResponseMessage.equals(getPropertyValue("AssociateTaxNameUnique"))) {
					Faker faker = new Faker(new Locale("en-IND"));
					String fakeTaxName = faker.book().genre();
					String upperCase = RandomStringUtils.randomAlphabetic(5).toUpperCase();
					this.clearField(SubTaxName1);
					this.inputText(SubTaxName1, fakeTaxName + upperCase);
					this.mouseActionClick(Button);
				}
				if (this.conditionChecking(Message, 20)) {
					ResponseMessage = this.getText(Message);
					this.invisible(Message);
					if (ResponseMessage.equals(getPropertyValue("CreatedTax"))
							|| ResponseMessage.equals(getPropertyValue("EditedTax"))) {
						conditionCheck = false;
					}
				} else {
					do {
						Thread.sleep(10000);
						this.mouseActionClick(Button);
						if (this.conditionChecking(Message, 20)) {
							ResponseMessage = this.getText(Message);
							this.invisible(Message);
							if (ResponseMessage.equals(getPropertyValue("CreatedTax"))
									|| ResponseMessage.equals(getPropertyValue("EditedTax"))) {
								conditionCheck = false;
							} else if (ResponseMessage.equals(getPropertyValue("MaxTaxPercentage"))
									|| ResponseMessage.equals(getPropertyValue("AlreadyTax"))
									|| ResponseMessage.equals("AssociateTaxNameUnique")) {
								this.message("AlternateMessage");
							}
						}
					} while (conditionCheck);
				}
			} while (conditionCheck);
		}
		return value;

	}

	static String taxName;
	static String taxPercentage;

	public void taxNameField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(TaxName, characters256);
			this.inputText(TaxPercentage, fakeTaxPercentage);
			this.mouseActionClick(Button);
		} else if (value.equals("Mandatory")) {
			this.mouseActionClick(Button);
			if (this.conditionChecking(TaxNameError, 2)) {
			} else {
				do {
					this.mouseActionClick(Button);
				} while (!this.conditionChecking(TaxNameError, 2));
			}
		} else if (value.equals("UniqueValidation")) {
			String text = this.getText(ListTaxName);
			this.mouseActionClick(Button);
			this.inputText(TaxName, text);
			this.inputText(TaxPercentage, "10");
			this.mouseActionClick(Button);
		} else if (value.equals("AssociateTaxUnique")) {
			taxName = this.getTextAttribute(TaxName);
			taxPercentage = this.getTextAttribute(TaxPercentage);
			this.mouseActionClick(AssociateTaxCheckBox);
			this.inputText(SubTaxName1, taxName);
			this.inputText(SubTaxNumber1, taxPercentage);
			this.mouseActionClick(Button);
		} else if (value.equals("TaxPercentage")) {
			this.inputText(SubTaxName1, fakeTaxName);
			int parseInt = Integer.parseInt(taxPercentage);
			int halfValue = parseInt / 2;
			String valueOf = String.valueOf(halfValue);
			this.inputText(SubTaxNumber1, valueOf);
			this.mouseActionClick(Button);
		} else if (value.equals("SubTaxValue")) {
			this.inputText(SubTaxNumber1, taxPercentage);
		}
	}

	public String texPercentageField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(TaxPercentage, "0");
			this.inputText(TaxName, fakeTaxName);
			String textAttribute = this.getTextAttribute(TaxName);
			this.mouseActionClick(Button);
			return textAttribute;
		} else if (value.equals("ValidData")) {
			this.inputText(TaxPercentage, fakeTaxPercentage);
		}
		return value;
	}

	public void validData() {
		this.inputText(TaxName, fakeTaxName);
		this.inputText(TaxPercentage, fakeTaxPercentage);
		this.mouseActionClick(Button);
	}

	public String clickEvent(String value) {
		if (value.equals("ClickButton")) {
			this.mouseActionClick(Button);
		} else if (value.equals("ButtonPresent")) {
			return this.getText(Button);
		} else if (value.equals("BackButton")) {
			this.mouseActionClick(Back);
			this.mouseActionClick(Yes);
		} else if (value.equals("Delete")) {
			if (this.conditionChecking(Delete, 5)) {
				this.mouseActionClick(Delete);
				this.mouseActionClick(Yes);
			} else {
				throw new SkipException("Skipping / Ignoring - Script not Ready for Execution ");
			}
		} else if (value.equals("CheckBox")) {
			this.mouseActionClick(AssociateTaxCheckBox);
		}
		return value;
	}

	public String listValidation() {
		String text = this.getText(ListTaxName);
		return text;
	}

	public void clearAllFields(String value) {
		this.clearField(TaxName);
		this.clearField(TaxPercentage);
		if (value.equals("SubTax")) {
			this.clearField(SubTaxName1);
			this.clearField(SubTaxNumber1);
		}
	}

	static String FieldTaxName;

	public String createEdit(String value) throws IOException, InterruptedException {
		if (value.equals("Create")) {
			this.mouseActionClick(Button);
			this.inputText(TaxName, fakeTaxName);
			FieldTaxName = this.getTextAttribute(TaxName);
			this.inputText(TaxPercentage, fakeTaxPercentage);
			this.mouseActionClick(Button);
			String message2 = this.message("Message");
			if (!message2.equals(getPropertyValue("CreatedTax"))) {
				this.message("AlternateMessage");
			}
			return FieldTaxName;
		} else if (value.equals("Edit")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(TaxSettings);
			String text = this.getText(ListTaxName);
			this.mouseActionClick(Edit);
			this.valuePresent(TaxName, text);
			this.clearAllFields("value");
			this.inputText(TaxName, fakeTaxName);
			FieldTaxName = this.getTextAttribute(TaxName);
			this.inputText(TaxPercentage, fakeTaxPercentage);
			this.mouseActionClick(Button);
			String message2 = this.message("Message");
			if (!message2.equals(getPropertyValue("EditedTax"))) {
				this.message("AlternateMessage");
			} else if (value.equals("Delete")) {
				this.mouseActionClick(settings_menu);
				this.mouseActionClick(TaxSettings);
				FieldTaxName = this.getText(TaxName);
				this.mouseActionClick(Delete);
				this.mouseActionClick(Yes);
				this.message("Message");
			}
			return FieldTaxName;
		}
		return value;
	}

	By TaxFieldName = By.xpath("//*[@id='product_service_create_edit']/div[5]/div[1]/div[2]/input[1]");

	public String reflection() {
		this.mouseActionClick(Label);
		this.mouseActionClick(ProductService);
		this.visibility(ListPage);
		this.mouseActionClick(Button);
		this.scrollDown();
		this.visibility(ProductLabel);
		for (int i = 0; i < 10; i++) {
			this.mouseActionClick(Button);
		}
		this.scrollDown();
		this.mouseActionClick(TaxFieldName);
		this.inputText(TaxFieldName, FieldTaxName);
		String text = this.getText(FirstTaxName);
		return text;
	}

}
