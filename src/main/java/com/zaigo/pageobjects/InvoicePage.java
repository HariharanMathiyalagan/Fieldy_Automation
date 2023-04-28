package com.zaigo.pageobjects;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.github.javafaker.Faker;

public class InvoicePage extends BaseClass {

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
	String characters16 = RandomStringUtils.randomAlphabetic(20);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String numberCharacter15 = RandomStringUtils.randomNumeric(15);
	String QuantityValue = RandomStringUtils.randomNumeric(2);
	String PriceValue = RandomStringUtils.randomNumeric(4);
	String DisTaxValue = RandomStringUtils.randomNumeric(2);
	String TaxValue = RandomStringUtils.randomNumeric(2);
	String ReferencePrefix = RandomStringUtils.randomAlphabetic(3).toUpperCase();
	String ReferenceNo = RandomStringUtils.randomNumeric(3);

	public InvoicePage(WebDriver driver) {
		this.driver = driver;

	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	private void inputText(WebElement element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
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

	public Boolean valuePresentCondition(WebElement element, String value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 30);
			text = wait.until(ExpectedConditions.textToBePresentInElement(element, value));
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean valuePresentCondition(By element, String value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 50);
			text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking1(By element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 2);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private void clickButton(WebElement element) {
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

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	private void validationTab(WebElement element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text, Keys.TAB);
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

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	private void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}

	public Boolean valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		Boolean until = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		return until;
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	By ContactListName = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[2]/span/a");
	By OrganizationListName = By
			.xpath("//*[@id='fieldy-customer-organization-list_aserpttbl']/tbody/tr[2]/td[2]/span/a");
	By ClickOrganizationInvoice = By.xpath("//*[@id='customer-organization-nav-menu']/div/ul/li[6]/a");
	By CreateOrganizationInvoice = By.xpath("//*[@id='customer-organization-detail-invoice']/div[1]/div[5]/button");
	By ClickContactInvoice = By.xpath("//*[@id='customer-contact-nav-menu']/div/ul/li[6]/a");
	By CreateGlobalInvoice = By.xpath("//*[@data-automationid='invoice_create']");
	By CreateContactInvoice = By.xpath("//*[@id='customer-contact-invoice']/div[1]/div[5]/button");
	By CustomerName = By.id("customer-name");
	By ContactName = By.id("customer-name-input-field");
	By Invalid = By.xpath("//*[text()='No Result Found']");

	@FindAll({ @FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='Invoice No']") })
	private WebElement StartInvalid;
	By InvoiceLable = By.xpath("//*[text()='Invoice No']");
	By TotalCount = By.id("total-invoice-count");
	By CreateInvoiceLabel = By.xpath("(//*[@data-draftback='invoicedraft'])[1]");
	By EditInvoiceLabel = By.xpath("//*[@data-dropzonereset='invocie']");
	By Reference = By.id("reference_no");
	By due_on_receipt = By.xpath("//select[@id='invoice-due-by-filter']//option[1]");
	By DueonReceipt = By.id("invoice-due-by-filter");
	By DueDate = By.id("doc_expiry_date");
	By InvoiceTittle = By.id("invoice_title");
	By InventoryItem = By.xpath("//*[@id='quoteitem-0']/div[1]/div[1]/input[1]");
	By Quantity = By.id("items__quantity__0");
	By Price = By.id("items__price__0");
	By Discount = By.id("items__discount__0");
	By Tax = By.id("items__tax__0");
	By Total = By.id("items__total__0");
	By Description = By.id("items__description__0");
	By AddMoreItems = By.id("invoice-inventory-form-lists");
	By Cancel = By.xpath("(//*[@class='eicon-close'])[2]");
	By SubTotal = By.id("Invoice__total__price");
	By DiscountTotal = By.id("Invoice__total__discount");
	By TaxTotal = By.id("Invoice__total__tax");
	By TotalAmount = By.id("Invoice__total__totalamount");
	By Notes = By.id("notes");
	By Preview = By.xpath("//*[@data-automationid=\"invoice-perview\"]");
	By Save = By.id("invoicedrop");
	By Send = By.xpath(
			"//*[@class='d-flex justify-content-end bd-highlight mt-2 modal-footer-fixed']//child::*[@class='btn btn-bg-blue font-14 w-100 pr-2 pl-2']");
	By GlobalSend = By.xpath("//*[@class='dropzoneform btn btn-bg-blue font-14 w-100 pr-2 pl-2']");
	By ErrorReference = By.id("reference_no_error");
	By ErrorDueDate = By.id("doc_expiry_date_error");
	By ErrorInvoiceTittle = By.id("invoice_title_error");
	By ErrorInventoryItem = By.id("items__item_name__0_error");
	By ErrorInventoryMax = By.xpath("//*[text()='Not Allowed More than 256 characters']");
	By ErrorQuantity = By.id("items__quantity__0_error");
	By ErrorPrice = By.id("items__price__0_error");
	By ErrorDiscount = By.id("items__discount__0_error");
	By ErrorTax = By.id("items__tax__0_error");
	By ErrorDescription = By.id("items__description__0_error");
	By ErrorNotes = By.id("notes_error");
	By Search = By.id("invoice-search-filter");
	@FindAll({ @FindBy(id = "invoice-search-enter"), @FindBy(id = "invoice-search-filter-enter") })
	WebElement SearchButton;
	@FindAll({ @FindBy(xpath = "//*[text()='No Data Found']"),
			@FindBy(xpath = "//*[@id='items__item_name__0-autocomplete-list']//div[1]") })
	WebElement InventoryFirstItem;
	By InventoryName = By.xpath("//*[@id='items__item_name__0-autocomplete-list']//div[1]");
//	By ListInvoiceNo = By.xpath("(//*[@class='ellipsis-100'])[3]");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[2]/td[2]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[2]/td[2]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[2]/td[2]") })
	WebElement ListInvoiceNo1;
	By GlobalListCustomerName = By.xpath("//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[2]/td[4]");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[3]/td[2]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[3]/td[2]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[3]/td[2]") })
	WebElement ListInvoiceNo;
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[2]/td[3]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[2]/td[3]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[2]/td[3]") })
	WebElement ListReference1;
	By ListCustomerName = By.xpath("//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[3]/td[4]");
//	By ListReference = By.xpath("(//*[@class='ellipsis-100'])[4]");
	@FindAll({
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]") })
	WebElement ThreeDots;
	@FindAll({
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]") })
	WebElement ContactEdit;
	By ListAwaitingStatus = By.xpath("//*[text()='Awaiting Payment']");
	By ListPartialStatus = By.xpath("//*[text()='Partial Payment']");
	By ListDraftStatus = By.xpath("(//*[text()='Draft'])[1]");
	By ListPaidStatus = By.xpath("(//*[text()='Paid'])[1]");
	By PayButton = By.xpath("//*[@data-automationid='invoice_addpayment_popup']");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[3]/td[7]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[3]/td[7]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[3]/td[8]") })
	WebElement TotalValue;
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[3]/td[8]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[3]/td[8]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[3]/td[9]") })
	WebElement PaidAmount;
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[3]/td[9]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[3]/td[9]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[3]/td[10]") })
	WebElement DueAmount;
	By AmountPaid = By.id("amount_paid");
	By GlobalListInvoiceStatus = By.xpath("(//*[@class='p-2 pt-1 pb-1'])[8]");
	By Yes = By.xpath("//*[text()='Yes']");
	By Update = By.xpath("//*[text()='Update']");
	By Reset = By.xpath("//*[text()=' Reset Search']");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-invoice-list_aserpttbl']/tbody/tr[2]/td[5]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-invoice-list_aserpttbl']/tbody/tr[2]/td[5]"),
			@FindBy(xpath = "//*[@id='fieldy-main-invoice-list_aserpttbl']/tbody/tr[2]/td[5]") })
	WebElement ListDueDate;
	By GlobalListDueDate = By.xpath("(//*[@class='ellipsis-100'])[4]");
	By Apply = By.xpath("//*[@data-searchbutton='invoice_filter']");
	By DueFrom = By.id("invoice-from-date-filter");
	By Filter = By.xpath("//*[@gloss='Filter']");
	By OrgContactName = By.id("id_customer");
	@FindAll({
			@FindBy(xpath = "//*[@id='contactdropdownlist' and contains(@style,'display:block;')]//child::div[1]//div[1]"),
			@FindBy(xpath = "//*[@id='contactdropdownlist2' and contains(@style,'display:block;')]//child::div[1]//div[1]"),
			@FindBy(xpath = "//*[text()=' No Data Found!']"),
			@FindBy(xpath = "//*[@id='contactdropdownlist3' and contains(@style,'display:block;')]//child::div[1]//div[1]") })
	WebElement CustomerListField;
	By OrgContactAdd = By.xpath("//*[@class='add_new_btn3 btn-30 btn btn-bg-blue pr-2 pl-2']");
	By OrganizationFirstName = By.id("contacts__first_name__0");
	By OrganizationLastName = By.id("contacts__last_name__0");
	By OrganizationEmail = By.id("contacts__email__0");
	By OrganizationPhoneNumber = By.id("contacts__phone__0");
	By OrganizationJobTittle = By.id("contacts__job_title__0");
	By Invoice = By.id("invoice-menu");
	By Dashboard = By.xpath("//*[text()=' Company Performance']");
	By RadioOrganization = By.id("organization");
	By RadioContact = By.id("contact");
	By GlobalCustomerName = By.id("id_customer_group");
	By FirstName = By.id("first_name");
	By LastName = By.id("last_name");
	By Email = By.id("email");
	By Phone = By.id("phones__number__0");
	By Address1 = By.id("addresses__line_1__0");
	By Address2 = By.id("addresses__line_2__0");
	By StateName = By.id("addresses__state__0");
	By CityName = By.id("addresses__city__0");
	By Zipcode = By.id("addresses__zipcode__0");
	By Add = By.xpath("//*[@id='id_customer_group-autocomplete-list1']//child::span");
	By OrgAdd = By.xpath("//*[@id='id_customer_group-autocomplete-list']//child::span");
	By InvoiceSearchButton = By.id("invoice-search-enter");
	By OrganizationName = By.id("company_name");
	By OrgPhoneNumber = By.xpath("(//*[@id='phones__number__0'])[3]");
	By OrgAddress1 = By.id("line_1");
	By OrgAddress2 = By.id("line_2");
	By OrgCity = By.id("city");
	By OrgState = By.id("state");
	By OrgZipcode = By.id("zipcode");
	By OrgEmail = By.xpath("(//*[@id='email'])[3]");
	By Website = By.xpath("(//*[@id='website'])[1]");

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='organization-create']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='contact-create']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='organization-contact-create']") })
	WebElement SaveButton;

	public void clearFields(String value) {
		if (value.equals("Reference")) {
			this.clearField(Reference);
		} else if (value.equals("DueDate")) {
			this.visibility(due_on_receipt);
			this.dropDownByIndex(DueonReceipt, 8);
			this.clearField(DueDate);
		} else if (value.equals("EditDueDate")) {
			this.clearField(DueDate);
		} else if (value.equals("InvoiceTittle")) {
			this.clearField(InvoiceTittle);
		} else if (value.equals("Inventory")) {
			this.clearField(InventoryItem);
		} else if (value.equals("Quantity")) {
			this.clearField(Quantity);
		} else if (value.equals("Price")) {
			this.clearField(Price);
		} else if (value.equals("Discount")) {
			this.clearField(Discount);
		} else if (value.equals("Tax")) {
			this.clearField(Tax);
		} else if (value.equals("Description")) {
			this.clearField(Description);
		} else if (value.equals("Notes")) {
			this.clearField(Notes);
		} else if (value.equals("Search")) {
			this.clearField(Search);
		}
	}

	public void clearAllFields() {
		List<String> asList = Arrays.asList("Reference", "InvoiceTittle", "EditDueDate", "Inventory", "Quantity",
				"Discount", "Tax", "Description", "Notes", "Price");
		for (int i = 0; i < asList.size(); i++) {
			this.clearFields(asList.get(i));
		}
	}

	public String calculationValidation() {
		String QuantityValue = this.getTextAttribute(Quantity);
		String PriceValue = this.getTextAttribute(Price);
		String DiscountValue = this.getTextAttribute(Discount);
		String TaxValue = this.getTextAttribute(Tax);
		String calculation = calculation(QuantityValue, PriceValue, DiscountValue, TaxValue);
		return calculation;
	}

	public String errorValidation(String value) {
		if (value.equals("ErrorReference")) {
			String text = this.getText(ErrorReference);
			return text;
		} else if (value.equals("ErrorDueDate")) {
			String text = this.getText(ErrorDueDate);
			return text;
		} else if (value.equals("ErrorInvoiceTittle")) {
			String text = this.getText(ErrorInvoiceTittle);
			return text;
		} else if (value.equals("ErrorInventoryItem")) {
			String text = this.getText(ErrorInventoryItem);
			return text;
		} else if (value.equals("ErrorMaxInventoryItem")) {
			String text = this.getText(ErrorInventoryMax);
			return text;
		} else if (value.equals("ErrorQuantity")) {
			String text = this.getText(ErrorQuantity);
			return text;
		} else if (value.equals("ErrorPrice")) {
			String text = this.getText(ErrorPrice);
			return text;
		} else if (value.equals("ErrorDiscount")) {
			String text = this.getText(ErrorDiscount);
			return text;
		} else if (value.equals("ErrorTax")) {
			String text = this.getText(ErrorTax);
			return text;
		} else if (value.equals("ErrorDescription")) {
			String text = this.getText(ErrorDescription);
			return text;
		} else if (value.equals("ErrorNotes")) {
			String text = this.getText(ErrorNotes);
			return text;
		} else if (value.equals("PastDateError")) {
			By ErrorPastDate = By
					.xpath("//*[text()='The doc expiry date must be a date after or equal to issue date.']");
			String text = this.getText(ErrorPastDate);
			return text;
		}
//		else if (value.equals("ErrorContact")) {
//			String text = this.getText(ErrorCustomerName);
//			return text;
//		}
		return value;
	}

	public void pickFirstItem(String value) throws InterruptedException {
		Boolean condition = true;
		if (value.equals("Contact")) {
			this.mouseActionClick(InventoryItem);
			this.mouseActionClick(InventoryFirstItem);
		} else if (value.equals("Organization")) {
			this.mouseActionClick(InventoryItem);
			if (this.getText(InventoryFirstItem).equals("No Data Found")) {
				do {
					Thread.sleep(5000);
					this.mouseActionClick(InventoryItem);
					if (this.conditionChecking(InventoryName)) {
						condition = false;
					}
				} while (condition);
				this.mouseActionClick(InventoryFirstItem);
			} else {
				this.mouseActionClick(InventoryFirstItem);
			}

		} else if (value.equals("ClickInventory")) {
			this.mouseActionClick(InventoryItem);
		}

	}

	public String invocieLandPage(String value) {
		if (value.equals("CreateLabel")) {
			String text = this.getText(CreateInvoiceLabel);
			return text;
		} else if (value.equals("EditLabel")) {
			String text = this.getText(ListReference1);
			this.clickButton(ThreeDots);
			this.mouseActionClick(ContactEdit);
			this.valuePresent(Reference, text);
			String text1 = this.getText(EditInvoiceLabel);
			return text1;
		}
		return value;
	}

	public void saveFunction(String value) throws InterruptedException {
		if (value.equals("Mandatory")) {
			this.mouseActionClick(Save);
			if (this.conditionChecking1(ErrorDueDate)) {
			} else {
				do {
					this.mouseActionClick(Save);
				} while (!this.conditionChecking1(ErrorDueDate));
			}
		} else if (value.equals("ClickButton")) {
			this.mouseActionClick(Save);
		}

	}

	public void resetFunction() {
		this.mouseActionClick(Reset);

	}

	public String labelValidation(String value) {
		if (value.equals("Contact")) {
			String text = this.getText(ContactListName);
			this.mouseActionClick(ContactListName);
			this.assertName(ContactListName, text);
			this.mouseActionClick(ClickContactInvoice);
			String contactName = this.customerName("DetailScreenCustomerName");
			this.getCount();
			this.assertName(CreateContactInvoice, "Create Invoice");
			this.mouseActionClick(CreateContactInvoice);
			return contactName;
		} else if (value.equals("Organization")) {
			String text = this.getText(OrganizationListName);
			this.mouseActionClick(OrganizationListName);
			this.assertName(OrganizationListName, text);
			this.mouseActionClick(ClickOrganizationInvoice);
			String organizationName = this.customerName("DetailScreenCustomerName");
			this.getCount();
			this.assertName(CreateOrganizationInvoice, "Create Invoice");
			this.mouseActionClick(CreateOrganizationInvoice);
			return organizationName;
		} else if (value.equals("GlobalContactInvoice") || value.equals("Global")) {
			this.mouseActionClick(Invoice);
			this.visibility(StartInvalid);
			this.mouseActionClick(CreateGlobalInvoice);
			if (value.equals("GlobalContactInvoice")) {
				this.clearFields("Quantity");
				this.clearFields("Price");
			}
		} else if (value.equals("ContactAPI")) {
			this.mouseActionClick(Save);
			if (this.conditionChecking1(ErrorDueDate)) {
			} else {
				do {
					this.mouseActionClick(Save);
				} while (!this.conditionChecking1(ErrorDueDate));
			}
		} else if (value.equals("OrganizationAPI")) {
			this.mouseActionClick(RadioOrganization);
		}
		return value;

	}

	static String getName;

	public String customerName(String value) {
		if (value.equals("DetailScreenCustomerName")) {
			getName = this.getText(CustomerName);
			return getName;
		} else if (value.equals("PlaceHolderName")) {
			this.valuePresent(ContactName, getName);
			String textAttribute = this.getTextAttribute(ContactName);
			return textAttribute;
		}
		return value;
	}

	static int parseInt;

	public int getCount() {
		this.visibility(StartInvalid);
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOfElementLocated(TotalCount)).getText();
		parseInt = Integer.parseInt(text2);
		return parseInt;

	}

	public int countValidation(int value) {
		if (value == 1) {
			int a = parseInt + 1;
			return a;
		} else if (value == 2) {
			this.visibility(StartInvalid);
			String text2 = this.getText(TotalCount);
			int parseInt = Integer.parseInt(text2);
			return parseInt;
		}
		return value;
	}

	public void validationQuantity(String value) {
		if (value.equals("EmptyValidation")) {
			this.validationTab(Quantity, "-1");
		} else if (value.equals("MaxQuantity")) {
			this.validationTab(Quantity, numberCharacter15);
		} else if (value.equals("AfterDecimalPoint")) {
			this.validationTab(Quantity, "4564.5445664");
		} else if (value.equals("Value")) {
			this.inputText(Quantity, "10");
		} else if (value.equals("BeforeDecimalPoint")) {
			this.validationTab(Quantity, "231231231231231231231.00000000");
		}
	}

	public void maxValidationReference() {
		this.validationTab(Reference, characters16);
	}

	public void maxValidationInvoiceTittle() {
		this.validationTab(InvoiceTittle, characters256);
	}

	public void maxInventoryItem() throws IOException, InterruptedException {
		this.validationTab(InventoryItem, getPropertyValue("256Characters"));

	}

	public void priceValidation(String value) {
		if (value.equals("EmptyValidation")) {
			this.validationTab(Price, "-1");
		} else if (value.equals("MaxPrice")) {
			this.validationTab(Price, numberCharacter15);
		} else if (value.equals("AfterDecimalPoint")) {
			this.validationTab(Price, "4564.5445664");
		} else if (value.equals("BeforeDecimalPoint")) {
			this.validationTab(Price, "231231231231231.005454554");
		} else if (value.equals("value")) {
			this.inputText(Price, PriceValue);
		}
	}

	public void discountValidation(String value) {
		if (value.equals("BeforeDecimalPoint")) {
			this.validationTab(Discount, "0000.00");
		} else if (value.equals("AfterDecimalPoint")) {
			this.validationTab(Discount, "23.0000");
		} else if (value.equals("DiscountLimit")) {
			this.validationTab(Discount, "2131231");
		} else if (value.equals("MaxDiscount")) {
			this.validationTab(Discount, "101");
		} else if (value.equals("value")) {
			this.validationTab(Discount, DisTaxValue);
		}

	}

	public void taxValidation(String value) {
		if (value.equals("BeforeDecimalPoint")) {
			this.validationTab(Tax, "0000.00");
		} else if (value.equals("AfterDecimalPoint")) {
			this.validationTab(Tax, "23.0000");
		} else if (value.equals("TaxLimit")) {
			this.validationTab(Tax, "2131231");
		} else if (value.equals("MaxTax")) {
			this.validationTab(Tax, "101");
		} else if (value.equals("value")) {
			this.inputText(Tax, DisTaxValue);
		}
	}

	public void descriptionValidation(String value) throws IOException {
		if (value.equals("MaxCharacter")) {
			this.validationTab(Description, characters256);
		} else if (value.equals("value")) {
			this.inputText(Description, getPropertyValue("Description"));
		}
	}

	public void maxNotes() {
		this.validationTab(Notes, characters2048);

	}

	static String currentDate;

	public String dateValidation(String value) throws InterruptedException, ParseException {
		if (value.equals("FutureDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			String currentDate = sdf.format(cal.getTime());
			this.inputText(DueDate, currentDate);
		} else if (value.equals("Current")) {
			this.inputText(DueDate, "01/14/2023");
		} else if (value.equals("CurrentDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 0);
			currentDate = sdf.format(cal.getTime());
			return currentDate;
		} else if (value.equals("PastDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -1);
			String pastDate = sdf.format(cal.getTime());
			this.inputText(DueDate, pastDate);
			this.mouseActionClick(Save);
		} else if (value.equals("GlobalPastDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -1);
			String pastDate = sdf.format(cal.getTime());
			this.inputText(DueDate, pastDate);
			this.mouseActionClick(GlobalSend);
		} else if (value.equals("Convertion")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
			Date parse = sdf.parse(text);
			sdf = new SimpleDateFormat("MM/dd/yyyy");
			String format = sdf.format(parse);
			this.mouseActionClick(Filter);
			this.inputText(DueFrom, format);
			this.mouseActionClick(Apply);
		} else if (value.equals("DueDate")) {
			text = this.getText(ListDueDate);
			return text;
		} else if (value.equals("GlobalDueDate")) {
			text = this.getText(GlobalListDueDate);
			return text;
		}
		return value;
	}

	static String text;

	public String inventoryItemValidation(String value) {
		if (value.equals("Calculation")) {
			this.inputText(InventoryItem, "Bike");
			this.clearField(Price);
			this.validationTab(Price, PriceValue);
			this.clearField(Discount);
			this.validationTab(Discount, DisTaxValue);
			this.clearField(Tax);
			this.validationTab(Tax, TaxValue);
			String calculationValidation = this.calculationValidation();
			String actualAmount = "₹ " + calculationValidation;
			return actualAmount;
		} else if (value.equals("ExpectedAmount")) {
			String calculationValidation = this.calculationValidation();
			String expectedAmount = "₹ " + calculationValidation;
			return expectedAmount;
		}
		return value;
	}

	public void CRUDValidation(String value) throws InterruptedException, IOException, ParseException {
		if (value.equals("Create") || value.equals("CreateValue")) {
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			if (value.equals("Create")) {
				this.dateValidation("FutureDate");
			} else if (value.equals("CreateValue")) {
				this.pickFirstItem("Organization");
			}
			this.inputText(InvoiceTittle, fakeTittle);
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
		} else if (value.equals("Edit")) {
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(InvoiceTittle, fakeTittle);
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
		} else if (value.equals("DraftOrg")) {
			String text = this.getText(CustomerName);
			this.mouseActionClick(CreateOrganizationInvoice);
			this.valuePresent(ContactName, text);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateInvoiceLabel);
			this.mouseActionClick(Yes);
		} else if (value.equals("Draft")) {
			String text = this.getText(CustomerName);
			this.mouseActionClick(CreateContactInvoice);
			this.valuePresent(ContactName, text);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateInvoiceLabel);
			this.mouseActionClick(Yes);
		} else if (value.equals("CilckCreateInvoice")) {
			this.mouseActionClick(CreateGlobalInvoice);
		} else if (value.equals("ClickOrgRadio")) {
			this.mouseActionClick(RadioOrganization);
		} else if (value.equals("GlobalContactDraft")) {
			this.autoCompleteField("GlobalContact");
			this.autoCompleteField("VisibleCustomerName");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateInvoiceLabel);
			this.mouseActionClick(Yes);
		} else if (value.equals("GlobalOrgDraft")) {
			this.autoCompleteField("GlobalOrganization");
			this.autoCompleteField("VisibleCustomerOrgName");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateInvoiceLabel);
			this.mouseActionClick(Yes);
		}

	}

	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By PaidMessage = By.xpath("//*[text()='Invoice payment updated successfully']");
	static String response;
	static String alternateMessage;

	public String responseMessage(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message")) {
			if (this.conditionChecking(Message)) {
				response = this.getText(Message);
				this.invisible(Message);
				return response;
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(SaveButton);
					if (this.conditionChecking(Message)) {
						response = this.getText(Message);
						this.invisible(Message);
						if (response.equals(getPropertyValue("CustomerCreatedMessage"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateFunction")) {
			do {
				if (response.equals(getPropertyValue("ContactEmailAlreadyMessage"))) {
					this.clearField(Email);
					String fakeEmail = faker.internet().safeEmailAddress();
					this.inputText(Email, fakeEmail);
					this.mouseActionClick(SaveButton);
				} else if (response.equals(getPropertyValue("CompanyAlreadyMessage"))) {
					this.clearField(OrganizationName);
					this.inputText(OrganizationName, fakeCompanyName);
					this.mouseActionClick(SaveButton);
				} else if (response.equals(getPropertyValue("CompanyEmailAlreadyMessage"))) {
					this.clearField(OrgEmail);
					this.inputText(OrgEmail, fakeEmail);
					this.mouseActionClick(SaveButton);
				} else if (response.equals(getPropertyValue("CompanyContactEmailMessage"))) {
					this.clearField(OrganizationEmail);
					this.inputText(OrganizationEmail, fakeEmail);
					this.mouseActionClick(SaveButton);
				}
				if (this.conditionChecking(Message)) {
					alternateMessage = this.getText(Message);
					this.invisible(Message);
					if (alternateMessage.equals(getPropertyValue("CustomerCreatedMessage"))) {
						conditionCheck = false;
					}
				} else {
					do {
						Thread.sleep(10000);
						this.mouseActionClick(SaveButton);
						if (this.conditionChecking(Message)) {
							alternateMessage = this.getText(Message);
							this.invisible(Message);
							if (alternateMessage.equals(getPropertyValue("CustomerCreatedMessage"))) {
								conditionCheck = false;
							}
						}
					} while (conditionCheck);
				}
			} while (conditionCheck);
		}
		return value;

	}

	static String SearchData;
	public static double PartialPay;

	public String listTextValidation(String value) throws IOException, InterruptedException {
		if (value.equals("ListPartialStatus")) {
			String text = this.getText(ListPartialStatus);
			return text;
		} else if (value.equals("ListAwaitingStatus")) {
			String text = this.getText(ListAwaitingStatus);
			return text;
		} else if (value.equals("ListDraftStatus")) {
			String text = this.getText(ListDraftStatus);
			return text;
		} else if (value.equals("ListPaidStatus")) {
			String text = this.getText(ListPaidStatus);
			return text;
		} else if (value.equals("GlobalListStatus")) {
			String text = this.getText(GlobalListInvoiceStatus);
			return text;
		} else if (value.equals("InvoiceNo")) {
			SearchData = this.getText(ListInvoiceNo);
			return SearchData;
		} else if (value.equals("Reference")) {
			SearchData = this.getText(ListReference1);
			return SearchData;
		} else if (value.equals("SearchInvoiceNo")) {
			SearchData = this.getText(ListInvoiceNo1);
			return SearchData;
		} else if (value.equals("SearchReference")) {
			SearchData = this.getText(ListReference1);
			return SearchData;
		} else if (value.equals("GlobalCustomerName")) {
			this.elementtobeClickable(InvoiceSearchButton);
			SearchData = this.getText(ListCustomerName);
			return SearchData;
		} else if (value.equals("SearchGlobalCustomerName")) {
			return this.getText(GlobalListCustomerName);
		} else if (value.equals("SearchData")) {
			this.inputText(Search, SearchData);
			this.mouseActionClick(SearchButton);
		} else if (value.equals("CustomerSearchData")) {
			this.inputText(Search, SearchData);
			this.mouseActionClick(InvoiceSearchButton);
		} else if (value.equals("Invalid")) {
			this.tagValidation(Search, "sdfsfsdfsfs");
		} else if (value.equals("InvalidList")) {
			String text = this.getText(Invalid);
			return text;
		} else if (value.equals("TotalValue")) {
			SearchData = this.getText(TotalValue);
			double parseInt = Double.parseDouble(SearchData);
			PartialPay = parseInt / 2;
			this.mouseActionClick(PayButton);
			return SearchData;
		} else if (value.equals("PartialPayment")) {
			DecimalFormat f = new DecimalFormat("0.00");
			SearchData = f.format(PartialPay);
		} else if (value.equals("Paid")) {
			SearchData = this.getText(DueAmount);
			this.mouseActionClick(PayButton);
		} else if (value.equals("PayAmount")) {
			this.inputText(AmountPaid, SearchData);
			this.mouseActionClick(Update);
			this.responseMessage("Message");
			this.invisible(PaidMessage);
		}
		return value;
	}

	public double doubleValue(double value) throws IOException {
		if (value == 1) {
			SearchData = this.getText(PaidAmount);
			double parseDouble = Double.parseDouble(SearchData);
			return parseDouble;
		} else if (value == 2) {
			String text = this.getText(TotalValue);
			double parseDouble = Double.parseDouble(text);
			return parseDouble;

		}
		return value;
	}

	By PopupOpen = By.xpath("//*[contains(@class,'fadeIn')]//child::h5");
	static String ContactFirstName;
	static String ContactLastName;
	static String OrgName;

	public void autoCompleteField(String value) throws InterruptedException, IOException {
		Boolean condition = true;
		if (value.equals("OrganizationContactCreate")) {
			this.inputText(OrgContactName, fakeFirstName);
			this.mouseActionClick(OrgContactAdd);
			if (!this.conditionChecking(PopupOpen)) {
				do {
					this.mouseActionClick(OrgContactAdd);
					if (this.conditionChecking(PopupOpen)) {
						condition = false;
					}
				} while (condition);
			}
			this.inputText(OrganizationFirstName, fakeFirstName);
			ContactFirstName = this.getTextAttribute(OrganizationFirstName);
			this.inputText(OrganizationLastName, fakeLastName);
			ContactLastName = this.getTextAttribute(OrganizationLastName);
			this.inputText(OrganizationEmail, fakeEmail);
			this.inputText(OrganizationPhoneNumber, fakePhoneNumber);
			this.inputText(OrganizationJobTittle, fakeTittle);
			this.mouseActionClick(SaveButton);
		} else if (value.equals("VisibleName")) {
			if (!this.valuePresentCondition(OrgContactName, ContactFirstName + " " + ContactLastName)) {
				this.inputText(OrgContactName, ContactFirstName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("OrganizationContactCreate");
						this.responseMessage("Message");
						if (this.valuePresentCondition(OrgContactName, ContactFirstName + " " + ContactLastName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		} else if (value.equals("GlobalContact")) {
			this.inputText(GlobalCustomerName, fakeFirstName);
			this.mouseActionClick(Add);
			if (!this.conditionChecking(PopupOpen)) {
				do {
					this.mouseActionClick(Add);
					if (this.conditionChecking(PopupOpen)) {
						condition = false;
					}
				} while (condition);
			}
			this.inputText(FirstName, fakeFirstName);
			ContactFirstName = this.getTextAttribute(FirstName);
			this.inputText(LastName, fakeLastName);
			ContactLastName = this.getTextAttribute(LastName);
			this.inputText(Email, fakeEmail);
			this.inputText(Phone, fakePhoneNumber);
			this.inputText(Address1, fakeAddress1);
			this.inputText(Address2, fakeAddress2);
			this.inputText(CityName, fakeCity);
			this.inputText(StateName, fakeState);
			this.inputText(Zipcode, fakeZipcode);
			this.mouseActionClick(SaveButton);
		} else if (value.equals("VisibleCustomerName")) {
			if (!this.valuePresentCondition(GlobalCustomerName, ContactFirstName + " " + ContactLastName)) {
				this.inputText(GlobalCustomerName, ContactFirstName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("GlobalContact");
						this.responseMessage("Message");
						if (this.valuePresentCondition(GlobalCustomerName, ContactFirstName + " " + ContactLastName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		} else if (value.equals("GlobalOrganization")) {
			this.inputText(GlobalCustomerName, fakeCompanyName);
			this.mouseActionClick(OrgAdd);
			if (!this.conditionChecking(PopupOpen)) {
				do {
					this.mouseActionClick(OrgAdd);
					if (this.conditionChecking(PopupOpen)) {
						condition = false;
					}
				} while (condition);
			}
			this.inputText(OrganizationName, fakeCompanyName);
			OrgName = this.getTextAttribute(OrganizationName);
			this.inputText(OrgPhoneNumber, fakePhoneNumber);
			this.inputText(OrgEmail, fakeEmail);
			this.inputText(Website, fakeWebsite);
			this.inputText(OrgAddress1, fakeAddress1);
			this.inputText(OrgAddress2, fakeAddress2);
			this.inputText(OrgCity, fakeCity);
			this.inputText(OrgState, fakeState);
			this.inputText(OrgZipcode, fakeZipcode);
			this.mouseActionClick(SaveButton);
		} else if (value.equals("VisibleCustomerOrgName")) {
			if (!this.valuePresentCondition(GlobalCustomerName, OrgName)) {
				this.inputText(GlobalCustomerName, OrgName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("GlobalOrganization");
						this.responseMessage("Message");
						if (this.valuePresentCondition(GlobalCustomerName, OrgName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		}
	}

	public void createFunction() throws IOException, InterruptedException {
		if (!response.equals(getPropertyValue("CustomerCreatedMessage"))) {
			this.responseMessage("AlternateFunction");
		}
	}

}
