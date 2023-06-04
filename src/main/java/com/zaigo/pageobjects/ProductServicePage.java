package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.xml.xpath.XPath;

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

public class ProductServicePage extends BaseClass {
	WebDriver driver;
	WebDriverWait wait;
	Faker faker = new Faker(new Locale("en-IND"));
	String fakeFirstName = faker.name().firstName();
	String fakeLastName = faker.name().lastName();
	String fakeEmail = faker.internet().safeEmailAddress();
	String fakePhoneNumber = faker.phoneNumber().phoneNumber();
	String fakeTittle = faker.name().title();
	String fakeAddress1 = faker.address().buildingNumber();
	String fakeAddress2 = faker.address().streetName();
	String fakeCity = faker.address().city();
	String fakeState = faker.address().state();
	String fakeZipcode = faker.address().zipCode();
	String fakeWebsite = faker.company().url();
	String fakeCompanyName = faker.company().name();
	String fakeFaxNumber = faker.number().digits(6);
	String fakeProductName = faker.book().genre();

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

	public ProductServicePage(WebDriver driver) {
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

	public void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 100);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 100);
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

	By Spinner = By.xpath("//*[@id='spinnerDiv']//div//div//div");

	By settings_menu = By.xpath("//a[@id='settings-menu']");

	By Product_Service = By.xpath("//*[text()=' Product / Service']");

	By Service = By.xpath("//*[text()='Service']");

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/header/div/div/div/a"),
			@FindBy(xpath = "//*[@id='product-create']/header/div/div/div") })
	WebElement Label;
	@FindAll({ @FindBy(xpath = "//*[@id='job-show-details-timeline']/div[1]/div[3]/button"),
			@FindBy(xpath = "//*[@data-automationid='product-create']") })
	public static WebElement CreateButton;

	By Search = By.id("product-service-serach-filter");

	By Filter = By.xpath("//*[@id='job-show-details-timeline']/div[2]/div[2]/button/div");

	By FilterCategory = By.id("settings-category-user-view-filter");

	By Apply = By.xpath("//*[contains(text(),'Apply')]");

	@FindAll({
			@FindBy(xpath = "//*[@placeholder='Search by Service Name ...']//ancestor::div[@id='fieldy-body-ele']//*[text()='Name']"),
			@FindBy(xpath = "//*[text()='No Result Found']"),
			@FindBy(xpath = "//*[@placeholder='Search by Product Name ...']//ancestor::div[@id='fieldy-body-ele']//*[text()='Name']") })
	WebElement ListPage;
	@FindAll({ @FindBy(xpath = "//*[text()='Quotes No']"), @FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement QuoteListPage;

	By ListInventoryName = By.xpath("//*[@id='fieldy-product-service-list_aserpttbl']//tr[2]//td[2]");

	@FindAll({ @FindBy(xpath = "//*[@placeholder='Search by Product Name ...']//ancestor::div[8]//table//tr[2]//td[5]"),
			@FindBy(xpath = "//*[@placeholder='Search by Service Name ...']//ancestor::div[8]//table//tr[2]//td[4]") })
	WebElement ListTaxable;

	@FindAll({
			@FindBy(xpath = "//*[@placeholder='Search by Product Name ...']//ancestor::div[8]//table//tr[2]//td[1]/div[1]/div/i"),
			@FindBy(xpath = "//*[@placeholder='Search by Service Name ...']//ancestor::div[8]//table//tr[2]//td[1]/div[1]/div/i") })
	WebElement ThreeDots;

	@FindAll({
			@FindBy(xpath = "//*[@placeholder='Search by Product Name ...']//ancestor::div[8]//table//tr[2]//td[1]//li[1]"),
			@FindBy(xpath = "//*[@placeholder='Search by Service Name ...']//ancestor::div[8]//table//tr[2]//td[1]//li[1]") })
	WebElement Edit;

	@FindAll({
			@FindBy(xpath = "//*[@placeholder='Search by Product Name ...']//ancestor::div[8]//table//tr[2]//td[1]//li[2]"),
			@FindBy(xpath = "//*[@placeholder='Search by Service Name ...']//ancestor::div[8]//table//tr[2]//td[1]//li[2]") })
	WebElement Delete;

	@FindAll({ @FindBy(xpath = "//*[@id='modal-confirmation-popup']//*[text()='Yes']"),
			@FindBy(xpath = "//*[text()='Yes']") })
	WebElement Yes;

	By TaxableRadio = By.xpath("//*[@data-istax='yes']");

	By NoTaxableRadio = By.xpath("//*[@data-istax='no']");

	By InventoryName = By.id("name");

	By SKU = By.id("sku");

	By Description = By.id("description");

	By Category = By.id("settings-category-user-view");

	By CategoryCheckBox = By.xpath("//*[@id='settings-category']//input");

	By Price = By.id("price");

	By TaxName = By.xpath("//*[@id='product_service_create_edit']/div[5]/div[1]/div[2]/input[1]");

	@FindAll({ @FindBy(xpath = "//*[@id='tax-autocomplete-list']/div[1]"),
			@FindBy(xpath = "//*[text()='No Data Found']") })
	WebElement FirstTaxName;

	By TaxPercentage = By.id("tax_percentage");

	By QuantityHand = By.id("current_stock");

	By LowStock = By.id("low_stock");

	By ErrorInventoryName = By.id("name_error");

	By ErrorSKU = By.id("sku_error");

	By ErrorDescription = By.id("description_error");

	By ErrorPrice = By.id("price_error");

	By ErrorQuantityHand = By.id("current_stock_error");

	By ErrorLowStock = By.id("low_stock_error");

	By ErrorTaxName = By.id("tax_error");

	By ErrorTaxPercentage = By.id("tax_percentage_error");

	By SaveComplete = By.id("proserdrop");

	By Save = By.xpath("//*[@id='quotedrop']");

	By Message = By.xpath("//*[@class='js-snackbar__message']");

	By Reset = By.xpath("//*[text()=' Reset Search']");

	By Invalid = By.xpath("//*[text()='No Result Found']");

	By Quote = By.id("quote-menu");

	By Contact = By.id("contact");

	By Organizaiton = By.id("organization");

	By InventoryField = By.xpath("//*[@id='quoteitem-0']/div[1]/div[1]/input[1]");
	@FindAll({ @FindBy(xpath = "//*[contains(text(),'Will be added')]"),
			@FindBy(xpath = "//*[@id='items__item_name__0-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[text()='No Data Found']") })
	WebElement InventoryFirstName;

	By Inactive = By.xpath("//*[@value='inactive']");

	public String modulePage(String value) {
		if (value.equals("Product")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(Product_Service);
		} else if (value.equals("Service")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(Product_Service);
			this.mouseActionClick(Service);
		}
		String text = this.getText(Label);
		return text;

	}

	public void clearFields(String value) throws AWTException {
		if (value.equals("InventoryName")) {
			this.clearField(InventoryName);
		} else if (value.equals("SKU")) {
			this.clearField(SKU);
		} else if (value.equals("Description")) {
			this.clearField(Description);
		} else if (value.equals("Price")) {
			this.clearField(Price);
		} else if (value.equals("TaxPercentage")) {
			this.clearField(TaxPercentage);
		} else if (value.equals("QuantityHand")) {
			this.clearField(QuantityHand);
		} else if (value.equals("LowStock")) {
			this.clearField(LowStock);
		} else if (value.equals("Search")) {
			this.clearField(Search);
		} else if (value.equals("TaxName")) {
			this.scrollDown();
			this.mouseActionClick(TaxName);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		}
	}

	public void clearAllFields(String value) throws AWTException {
		if (value.equals("Product")) {
			List<String> asList = Arrays.asList("InventoryName", "SKU", "Description", "Price", "QuantityHand",
					"LowStock");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		} else if (value.equals("Service")) {
			List<String> asList = Arrays.asList("InventoryName", "Description", "Price");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		}

	}

	public String errorFields(String value) {
		if (value.equals("InventoryName")) {
			return this.getText(ErrorInventoryName);
		} else if (value.equals("SKU")) {
			return this.getText(ErrorSKU);
		} else if (value.equals("Description")) {
			return this.getText(ErrorDescription);
		} else if (value.equals("Price")) {
			return this.getText(ErrorPrice);
		} else if (value.equals("TaxPercentage")) {
			return this.getText(ErrorTaxPercentage);
		} else if (value.equals("TaxName")) {
			return this.getText(ErrorTaxName);
		} else if (value.equals("QuantityHand")) {
			return this.getText(ErrorQuantityHand);
		} else if (value.equals("LowStock")) {
			return this.getText(ErrorLowStock);
		}
		return value;
	}

	public void inventoryName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(InventoryName, characters2048);
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("Unique")) {
			String text = this.getText(ListInventoryName);
			this.mouseActionClick(CreateButton);
			this.validationTab(InventoryName, text);
		} else if (value.equals("Mandatory")) {
			this.mouseActionClick(SaveComplete);
			if (this.conditionChecking(ErrorInventoryName, 2)) {
			} else {
				do {
					this.mouseActionClick(SaveComplete);
				} while (!this.conditionChecking(ErrorInventoryName, 2));
			}
		}
	}

	public void sku(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(SKU, characters256);
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(SKU, "!@#$%^&*");
		}
	}

	public void description(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Description, characters2048);
		}
	}

	public void price(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Price, numberCharacter15);
		} else if (value.equals("AfterDecimal")) {
			this.validationTab(Price, "12.121");
		} else if (value.equals("BeforeDecimal")) {
			this.validationTab(Price, "32121322222.32");
		}
	}

	public void taxPercentage(String value) {
		if (value.equals("MaxValidation")) {
			this.inputText(TaxPercentage, "1221212112");
		} else if (value.equals("AfterDecimal")) {
			this.inputText(TaxPercentage, "12.121");
		} else if (value.equals("BeforeDecimal")) {
			this.inputText(TaxPercentage, "32121322222.32");
		}
		this.mouseActionClick(SaveComplete);
	}

	public void quantityHand(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(QuantityHand, numberCharacter15);
		} else if (value.equals("AfterDecimal")) {
			this.validationTab(QuantityHand, "12.121");
		} else if (value.equals("BeforeDecimal")) {
			this.validationTab(QuantityHand, "21212121212121212121212121.22");
		}
	}

	public void lowStock(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LowStock, numberCharacter15);
		} else if (value.equals("AfterDecimal")) {
			this.validationTab(LowStock, "12.121");
		} else if (value.equals("BeforeDecimal")) {
			this.validationTab(LowStock, "21212121212121212121212121.22");
		}
	}

	static String ResponseMessage;
	static String message;

	public String message(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message")) {
			if (conditionChecking(Message, 20)) {
				ResponseMessage = this.getText(Message);
				this.invisible(Message);
				return ResponseMessage;
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(SaveComplete);
					if (conditionChecking(Message, 20)) {
						ResponseMessage = this.getText(Message);
						if (ResponseMessage.equals(getPropertyValue("InventoryCreatedMessage"))
								|| ResponseMessage.equals(getPropertyValue("InventoryUpdatedMessage"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateFunction")) {
			do {
				if (ResponseMessage.equals(getPropertyValue("AlreadyExists"))) {
					String fakeProductName = faker.book().genre();
					this.clearField(InventoryName);
					this.inputText(InventoryName, fakeProductName);
					this.mouseActionClick(SaveComplete);
					message = this.getText(Message);
					this.invisible(Message);
				}
				if (message.equals(getPropertyValue("InventoryCreatedMessage"))
						|| message.equals(getPropertyValue("InventoryUpdatedMessage"))) {
					conditionCheck = false;
				}
			} while (conditionCheck);
		}
		return value;
	}

	public String clickEvent(String value) {
		if (value.equals("SaveButton")) {
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("CreateButton")) {
			this.mouseActionClick(CreateButton);
		} else if (value.equals("ButtonPresent")) {
			return this.getText(SaveComplete);
		} else if (value.equals("Reset")) {
			this.mouseActionClick(Reset);
		} else if (value.equals("Service")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(Product_Service);
			this.visibility(ListPage);
			this.mouseActionClick(Service);
		} else if (value.equals("Product")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(Product_Service);
			this.visibility(ListPage);
		}
		return value;
	}

	public String validData(String value) throws IOException {
		if (value.equals("FillFields")) {
			this.inputText(InventoryName, fakeProductName);
			this.inputText(SKU, fakeFaxNumber);
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.scrollDown();
			this.mouseActionClick(Category);
			this.mouseActionClick(CategoryCheckBox);
			this.mouseActionClick(Price);
			this.inputText(Price, PriceValue);
		} else if (value.equals("Taxable")) {
			this.mouseActionClick(TaxName);
			if (this.getText(FirstTaxName).equals("No Data Found")) {
				this.mouseActionClick(NoTaxableRadio);
			} else {
				this.mouseActionClick(FirstTaxName);
			}
			this.inputText(QuantityHand, PriceValue);
			this.inputText(LowStock, PriceValue);
		} else if (value.equals("MandatoryData")) {
			this.inputText(InventoryName, fakeProductName);
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Price, PriceValue);
		} else if (value.equals("FillFieldService")) {
			this.inputText(InventoryName, fakeProductName);
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.scrollDown();
			this.mouseActionClick(Category);
			this.mouseActionClick(CategoryCheckBox);
			this.mouseActionClick(Price);
			this.inputText(Price, PriceValue);
		} else if (value.equals("ServiceTaxable")) {
			this.mouseActionClick(TaxName);
			if (this.getText(FirstTaxName).equals("No Data Found")) {
				this.scrollUp();
				this.mouseActionClick(NoTaxableRadio);
			} else {
				this.mouseActionClick(FirstTaxName);
			}
		}
		this.mouseActionClick(SaveComplete);
		return value;
	}

	public String labelValidation(String value) {
		if (value.equals("Create")) {
			this.visibility(ListPage);
			this.mouseActionClick(CreateButton);
			this.visibility(SaveComplete);
		} else if (value.equals("Edit")) {
			String text = this.getText(ListInventoryName);
			this.mouseActionClick(ThreeDots);
			if (!conditionChecking(Edit, 2)) {
				do {
					this.mouseActionClick(ThreeDots);
				} while (!conditionChecking(Edit, 2));
			}
			this.mouseActionClick(Edit);
			this.valuePresent(InventoryName, text);
		}
		String text = this.getText(Label);
		return text;
	}

	static String listData;

	public String listValidation(String value) {
		if (value.equals("SearchData")) {
			this.tagValidation(Search, listData);
		} else if (value.equals("InventoryName")) {
			listData = this.getText(ListInventoryName);
			return listData;
		} else if (value.equals("Taxable")) {
			String text = this.getText(ListTaxable);
			return text;
		} else if (value.equals("Invalid")) {
			this.tagValidation(Search, "sdfsfsfewdsfv");
		} else if (value.equals("InvalidResult")) {
			return this.getText(Invalid);
		} else if (value.equals("Delete")) {
			this.mouseActionClick(ThreeDots);
			if (!conditionChecking(Delete, 2)) {
				do {
					this.mouseActionClick(ThreeDots);
				} while (!conditionChecking(Delete, 2));
			}
			this.mouseActionClick(Delete);
			this.mouseActionClick(Yes);
		}
		return value;
	}

	public String createProdutService(String value) throws IOException, InterruptedException {
		if (value.equals("Create")) {
			this.mouseActionClick(CreateButton);
			this.inputText(InventoryName, fakeProductName);
			listData = this.getTextAttribute(InventoryName);
			this.mouseActionClick(NoTaxableRadio);
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.mouseActionClick(Price);
			this.inputText(Price, PriceValue);
		} else if (value.equals("Edit")) {
			this.visibility(ListPage);
			listData = this.getText(ListInventoryName);
			this.mouseActionClick(ThreeDots);
			if (!conditionChecking(Edit, 2)) {
				do {
					this.mouseActionClick(ThreeDots);
				} while (!conditionChecking(Edit, 2));
			}
			this.mouseActionClick(Edit);
			this.valuePresent(InventoryName, listData);
			this.mouseActionClick(Inactive);
		}
		this.mouseActionClick(SaveComplete);
		String message2 = this.message("Message");
		if (message2.equals(getPropertyValue("InventoryCreatedMessage"))
				|| message2.equals(getPropertyValue("InventoryUpdatedMessage"))) {
		} else {
			this.message("AlternateFunction");
		}
		return listData;
	}

	public String deleteProduct() throws IOException, InterruptedException {
		this.visibility(ListPage);
		String text = this.getText(ListInventoryName);
		this.mouseActionClick(ThreeDots);
		if (!conditionChecking(Edit, 2)) {
			do {
				this.mouseActionClick(ThreeDots);
			} while (!conditionChecking(Edit, 2));
		}
		this.mouseActionClick(Delete);
		this.mouseActionClick(Yes);
		this.message("Message");
		return text;
	}

	public String reflectedFunction() throws InterruptedException {
		this.mouseActionClick(Quote);
		this.visibility(QuoteListPage);
		this.mouseActionClick(CreateButton);
		for (int i = 0; i < 10; i++) {
			this.mouseActionClick(Organizaiton);
			this.mouseActionClick(Contact);
			this.mouseActionClick(Save);
		}
		this.mouseActionClick(InventoryField);
		if (this.getText(InventoryFirstName).equals("No Data Found")) {
			Thread.sleep(10000);
			this.mouseActionClick(InventoryField);
			this.visibility(InventoryFirstName);
		} else {
			this.visibility(InventoryFirstName);
		}
		this.inputText(InventoryField, listData);
		String text = this.getText(InventoryFirstName);
		return text;

	}

}