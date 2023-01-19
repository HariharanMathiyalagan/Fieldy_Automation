package com.zaigo.pageobjects;

import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.github.javafaker.Faker;

public class QuotePage extends BaseClass {
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
	String fakeFaxNumber = faker.number().digits(14);

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);

	public QuotePage(WebDriver driver) {
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

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();

	}

	private void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 20);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
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

	private String getText(By element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
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

	By ContactListName = By.xpath("(//*[@data-n-linkto='customer_contact_timeline'])[1]");
	By ClickContactQuote = By.xpath("//*[@data-detailheadermenu='cstmr-contact-quote']");
	By CreateContactQuote = By.xpath("//*[@data-automationid='customer-contact-quote-create']");
	By Reference = By.id("reference_no");
	By ExpiryDate = By.id("expiry_date");
	By QuoteStatus = By.id("quote_status");
	By QuoteTittle = By.id("doc_title");
	By InventoryItem = By.xpath(
			"//*[@class=\" wt-30 mandatory autosearch_with_createable_main custom-dropdown\"]//child::*[@class=\"form-control pl-2 pr-2 user-view\"]");
	By Quantity = By.id("items__quantity__0");
	By Price = By.id("items__price__0");
	By Discount = By.id("items__discount__0");
	By Tax = By.id("items__tax__0");
	By Total = By.id("items__total__0");
	By Description = By.id("items__description__0");
	By AddMoreItems = By.id("quote-inventory-form-lists");
	By SubTotal = By.id("Quote__total__price");
	By DiscountTotal = By.id("Quote__total__discount");
	By TaxTotal = By.id("Quote__total__tax");
	By TotalAmount = By.id("Quote__total__totalamount");
	By Notes = By.id("notes");
	By Preview = By.xpath("//*[@data-automationid=\"quote-perview\"]");
	By Save = By.id("quotedrop");
	By Send = By.xpath(
			"//*[@class='d-flex justify-content-end bd-highlight mt-2 modal-footer-fixed']//child::*[@class='btn btn-bg-blue font-14 w-100 pr-2 pl-2']");
	By ErrorReference = By.id("reference_no_error");
	By ErrorExpiryDate = By.id("expiry_date_error");
	By ErrorQuoteTittle = By.id("doc_title_error");
	By ErrorInventoryItem = By.id("items__item_name__0_error");
	By ErrorQuantity = By.id("items__quantity__0_error");
	By ErrorPrice = By.id("items__price__0_error");
	By ErrorDiscount = By.id("items__discount__0_error");
	By ErrorTax = By.id("items__tax__0_error");
	By ErrorDescription = By.id("items__description__0_error");
	By QuoteCreatedMessage = By.xpath("//*[text()='Quote created successfully']");
	By QuoteUpdatedMessage = By.xpath("//*[text()='Quote updated successfully']");
	By ErrorPastDate = By.xpath("//*[@class='js-snackbar__message']");
	By Yes = By.xpath("//*text()='Yes']");
	By ListQuoteNo = By.xpath("(//*[@class='p-2 pt-3 pb-3'])[1]");
	By ListTittle = By.xpath("(//*[@class='p-2 pt-3 pb-3'])[2]");
	By ListReference = By.xpath("(//*[@class='p-2 pt-3 pb-3'])[3]");
	By TotalCount = By.id("total-quote-count");

	public void customerContactQuoteListPage() throws InterruptedException {
		String text = this.getText(ContactListName);
		this.mouseActionClick(ContactListName);
		this.assertName(ContactListName, text);
		this.mouseActionClick(ClickContactQuote);
		this.assertName(CreateContactQuote, "Create Quote");
		this.mouseActionClick(CreateContactQuote);
	}

	
	
	
	
	
	
	
}
