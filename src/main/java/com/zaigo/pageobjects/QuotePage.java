package com.zaigo.pageobjects;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
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
	String characters16 = RandomStringUtils.randomAlphabetic(20);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String numberCharacter15 = RandomStringUtils.randomNumeric(15);
	String QuantityValue = RandomStringUtils.randomNumeric(1);
	String PriceValue = RandomStringUtils.randomNumeric(4);
	String DisTaxValue = RandomStringUtils.randomNumeric(2);
	String TaxValue = RandomStringUtils.randomNumeric(2);
	String ReferencePrefix = RandomStringUtils.randomAlphabetic(3).toUpperCase();
	String ReferenceNo = RandomStringUtils.randomNumeric(3);

	public QuotePage(WebDriver driver) {
		this.driver = driver;

	}

	private void getCSSValue(By element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getCssValue("font-family");
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

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	By ContactListName = By.xpath("(//*[@data-n-linkto='customer_contact_timeline'])[1]");
	By ClickContactQuote = By.xpath("//*[@data-detailheadermenu='cstmr-contact-quote']");
	By CreateContactQuote = By.xpath("//*[@data-automationid='customer-contact-quote-create']");
	By CreateQuoteLabel = By.xpath("(//*[@data-draftback='quotedraft'])[1]");
	By CustomerName = By.id("customer-name");
	By ContactName = By.id("customer-name-input-field");
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
	By Cancel = By.xpath("(//*[@class='eicon-close'])[2]");
	By SubTotal = By.id("Quote__total__price");
	By DiscountTotal = By.id("Quote__total__discount");
	By TaxTotal = By.id("Quote__total__tax");
	By TotalAmount = By.id("Quote__total__totalamount");
	By Notes = By.id("notes");
	By Preview = By.xpath("//*[@data-automationid=\"quote-perview\"]");
	By Save = By.id("quotedrop");
	By Send = By.xpath(
			"//*[@class='d-flex justify-content-end bd-highlight mt-2 modal-footer-fixed']//child::*[@class='btn btn-bg-blue font-14 w-100 pr-2 pl-2']");
	By GlobalSend = By.xpath("//*[@class='dropzoneform btn btn-bg-blue font-14 w-100 pr-2 pl-2']");
	By ErrorReference = By.id("reference_no_error");
	By ErrorExpiryDate = By.id("expiry_date_error");
	By ErrorQuoteTittle = By.id("doc_title_error");
	By ErrorInventoryItem = By.id("items__item_name__0_error");
	By ErrorInventoryMax = By.xpath("//*[text()='Not Allowed More than 256 characters']");
	By ErrorQuantity = By.id("items__quantity__0_error");
	By ErrorPrice = By.id("items__price__0_error");
	By ErrorDiscount = By.id("items__discount__0_error");
	By ErrorTax = By.id("items__tax__0_error");
	By ErrorDescription = By.id("items__description__0_error");
	By ErrorNotes = By.id("notes_error");
	By QuoteCreatedMessage = By.xpath("//*[text()='Quote created successfully']");
	By QuoteUpdatedMessage = By.xpath("//*[text()='Quote updated successfully']");
	By Yes = By.xpath("//*[text()='Yes']");
	By ListQuoteNo = By.xpath("(//*[@class='p-2 pt-3 pb-3'])[1]");
	By ListTittle = By.xpath("(//*[@class='p-2 pt-3 pb-3'])[2]");
	By ListReference = By.xpath("(//*[@class='p-2 pt-3 pb-3'])[3]");
	By GlobalListReference = By.xpath("(//*[@class='p-2 pt-1 pb-1 '])[1]");
	By GlobalListQuoteNo = By.xpath("(//*[@class='p-2 pt-1 pb-1'])[1]");
	By GlobalListTittle = By.xpath("(//*[@class='p-2 pt-1 pb-1'])[2]");
	By GlobalListCustomerName = By.xpath("(//*[@class='p-2 pt-1 pb-1'])[3]");
	By TotalCount = By.id("total-quote-count");
	By InventoryFirstItem = By.xpath("(//*[@class='p-2 list-hover-bg quote-inventory-unit w-20-ellipsis w-100'])[1]");
	By Dashboard = By.xpath("//*[text()=' Company Performance']");
	By Amount = By.id("Quote__total__totalamount");
	By ResponseMessage = By.xpath("//*[@class='js-snackbar__message']");
	By CreatedMesssage = By.xpath("//*[text()='Quote created successfully']");
	By UpdatedMessage = By.xpath("//*[text()='Quote updated successfully']");
	By ListUpdateMessage = By.xpath("//*[text()='Quote status updated successfully']");
	By Invalid = By.xpath("//*[text()='No Result Found']");
	By QuoteLable = By.xpath("//*[text()='Quotes No.']");
	By ThreeDots = By.xpath("(//*[@class='fa fa-ellipsis-v'])[2]");
	By Edit = By.xpath("(//*[@data-n-linkto='customer_contact_quote_edit'])[1]");
	By GlobalEdit = By.xpath("(//*[@data-n-linkto='quote_edit'])[1]");
	By EditOrg = By.xpath("(//*[@data-n-linkto='customer_organization_quote_edit'])[1]");
	By ListQuoteStatus = By.xpath("(//*[@class='p-2 pt-3 pb-3'])[8]");
	By GlobalListQuoteStatus = By.xpath("(//*[@class='p-2 pt-1 pb-1'])[8]");
	By Search = By.id("quote-search-filter");
	By Update = By.xpath("//*[text()='Update']");
	By UpdateOrg = By.xpath("//*[@data-list-namme='customer-organization-quote']");
	By ChooseStatus = By.xpath("//*[@class='floating-input field-input']");

	By OrganizationListName = By.xpath("(//*[@data-n-linkto='customer_organization_timeline'])[1]");
	By ClickOrganizationQuote = By.xpath("//*[@data-menuswitcher='cstmr-organization-quote']");
	By CreateOrganizationQuote = By.xpath("//*[@data-automationid='customer-organization-quote-create']");
	By OrgContactName = By.id("id_customer");
	By OrgContactAdd = By.xpath("//*[@class='add_new_btn3 btn-30 btn btn-bg-blue pr-2 pl-2']");
	By OrganizationFirstName = By.id("contacts__first_name__0");
	By OrganizationLastName = By.id("contacts__last_name__0");
	By OrganizationEmail = By.id("contacts__email__0");
	By OrganizationPhoneNumber = By.id("contacts__phone__0");
	By OrganizationJobTittle = By.id("contacts__job_title__0");
	By OrganizationContactSave = By.id("organization-contact-create");
	By ContactCreateMessage = By.xpath("//*[text()='Customer created successfully']");
	By Quote = By.id("quote-menu");
	By CreateGlobalQuote = By.xpath("//*[@data-formsactions='create']");
	By GlobalCustomerName = By.id("id_customer_group");
	By RadioOrganization = By.id("organization");
	By RadioContact = By.id("contact");
	By ErrorCustomerName = By.id("id_customer_group_error");
	By ContactFirstName = By.id("first_name");
	By ContactLastName = By.id("last_name");
	By Email = By.id("email");
	By Phone = By.id("phones__number__0");
	By Address1 = By.id("addresses__line_1__0");
	By Address2 = By.id("addresses__line_2__0");
	By StateName = By.id("addresses__state__0");
	By CityName = By.id("addresses__city__0");
	By Zipcode = By.id("addresses__zipcode__0");
	By SaveContact = By.id("contact-create");
	By Add = By.xpath("//*[@class='add_new_btn btn-30 btn btn-bg-blue pr-2 pl-2 ']");
	By OrgAdd = By.xpath("//*[@class='add_new_btn2 btn-30 btn btn-bg-blue pr-2 pl-2']");
	By QutePreview = By.xpath("//*[text()='Quote Preview']");
	By Close = By.xpath("//*[@data-automationid='contact-cancel']");
	By OrganizationName = By.id("company_name");
	By OrgPhoneNumber = By.xpath("(//*[@id='phones__number__0'])[3]");
	By OrgAddress1 = By.id("line_1");
	By OrgAddress2 = By.id("line_2");
	By OrgCity = By.id("city");
	By OrgState = By.id("state");
	By OrgZipcode = By.id("zipcode");
	By OrgEmail = By.xpath("(//*[@id='email'])[3]");
	By Website = By.xpath("(//*[@id='website'])[1]");
	By SaveOrg = By.id("organization-create");

	static String FirstName;
	static String LastName;
	static String OrgName;

	public void autoCompleteCreation(String value) {
		if (value.equals("CustomerOrganizationContact")) {
			this.inputText(OrgContactName, fakeFirstName);
			this.mouseActionClick(OrgContactAdd);
			this.inputText(OrganizationFirstName, fakeFirstName);
			FirstName = this.getTextAttribute(OrganizationFirstName);
			this.inputText(OrganizationLastName, fakeLastName);
			LastName = this.getTextAttribute(OrganizationLastName);
			this.inputText(OrganizationEmail, fakeEmail);
			this.inputText(OrganizationPhoneNumber, fakePhoneNumber);
			this.inputText(OrganizationJobTittle, fakeTittle);
			this.mouseActionClick(OrganizationContactSave);
		} else if (value.equals("VisibleName")) {
			this.valuePresent(OrgContactName, FirstName + " " + LastName);
		} else if (value.equals("VisibleCustomerName")) {
			this.valuePresent(GlobalCustomerName, FirstName + " " + LastName);
		} else if (value.equals("VisibleCustomerOrgName")) {
			this.valuePresent(GlobalCustomerName, OrgName);
		} else if (value.equals("GlobalContact")) {
			this.inputText(GlobalCustomerName, fakeFirstName);
			this.mouseActionClick(Add);
			this.inputText(ContactFirstName, fakeFirstName);
			FirstName = this.getTextAttribute(ContactFirstName);
			this.inputText(ContactLastName, fakeLastName);
			LastName = this.getTextAttribute(ContactLastName);
			this.inputText(Email, fakeEmail);
			this.inputText(Phone, fakePhoneNumber);
			this.inputText(Address1, fakeAddress1);
			this.inputText(Address2, fakeAddress2);
			this.inputText(CityName, fakeCity);
			this.inputText(StateName, fakeState);
			this.inputText(Zipcode, fakeZipcode);
			this.mouseActionClick(SaveContact);
		} else if (value.equals("GlobalOrganization")) {
			this.inputText(GlobalCustomerName, fakeCompanyName);
			this.mouseActionClick(OrgAdd);
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
			this.mouseActionClick(SaveOrg);
		}

	}

	public String customerQuoteListPage(String value) throws InterruptedException {
		if (value.equals("CustomerContact")) {
			String text = this.getText(ContactListName);
			this.mouseActionClick(ContactListName);
			this.assertName(ContactListName, text);
			this.mouseActionClick(ClickContactQuote);
			String contactName = this.customerName("DetailScreenCustomerName");
			this.getCount();
			this.assertName(CreateContactQuote, "Create Quote");
			this.mouseActionClick(CreateContactQuote);
			return contactName;
		} else if (value.equals("CustomerOrganzation")) {
			String text = this.getText(OrganizationListName);
			this.mouseActionClick(OrganizationListName);
			this.assertName(OrganizationListName, text);
			this.mouseActionClick(ClickOrganizationQuote);
			String organizationName = this.customerName("DetailScreenCustomerName");
			this.getCount();
			this.assertName(CreateOrganizationQuote, "Create Quote");
			this.mouseActionClick(CreateOrganizationQuote);
			return organizationName;
		} else if (value.equals("GlobalContactQuote")) {
			this.assertName(Dashboard, "Company Performance");
			this.mouseActionClick(Quote);
			this.assertName(QuoteLable, "Quotes No.");
			this.mouseActionClick(CreateGlobalQuote);
			this.clearFields("Quantity");
			this.clearFields("Price");
		} else if (value.equals("ContactAPI")) {
			for (int i = 0; i < 3; i++) {
				this.mouseActionClick(RadioOrganization);
				this.mouseActionClick(Save);
				this.mouseActionClick(RadioContact);
				this.mouseActionClick(Save);
			}
		} else if (value.equals("OrganizationAPI")) {
			for (int i = 0; i < 3; i++) {
				this.mouseActionClick(RadioOrganization);
				this.mouseActionClick(Save);
				this.mouseActionClick(RadioContact);
				this.mouseActionClick(Save);
				this.mouseActionClick(RadioOrganization);
			}
		}
		return value;

	}

	public String quoteLandPage() {
		String text = this.getText(CreateQuoteLabel);
		return text;
	}

	public void clearFields(String value) {
		if (value.equals("Reference")) {
			this.clearField(Reference);
		} else if (value.equals("Expiry")) {
			this.clearField(ExpiryDate);
		} else if (value.equals("QuoteTittle")) {
			this.clearField(QuoteTittle);
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

	static String currentDate;

	public String dateValidation(String value) throws InterruptedException {
		if (value.equals("FutureDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			String currentDate = sdf.format(cal.getTime());
			this.inputText(ExpiryDate, currentDate);
		} else if (value.equals("Current")) {
			this.inputText(ExpiryDate, "01/14/2023");
		} else if (value.equals("CurrentDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 0);
			currentDate = sdf.format(cal.getTime());
			return currentDate;
		} else if (value.equals("PastDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -1);
			String pastDate = sdf.format(cal.getTime());
			this.inputText(ExpiryDate, pastDate);
			this.mouseActionClick(Send);
		} else if (value.equals("GlobalPastDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -1);
			String pastDate = sdf.format(cal.getTime());
			this.inputText(ExpiryDate, pastDate);
			this.mouseActionClick(GlobalSend);
		}
		return value;
	}

	public String errorValidation(String value) {
		if (value.equals("ErrorReference")) {
			String text = this.getText(ErrorReference);
			return text;
		} else if (value.equals("ErrorExpiryDate")) {
			String text = this.getText(ErrorExpiryDate);
			return text;
		} else if (value.equals("ErrorQuoteTittle")) {
			String text = this.getText(ErrorQuoteTittle);
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
					.xpath("//*[text()='The doc expiry date must be a date after or equal to " + currentDate + ".']");
			String text = this.getText(ErrorPastDate);
			return text;
		} else if (value.equals("ErrorContact")) {
			String text = this.getText(ErrorCustomerName);
			return text;
		}
		return value;
	}

	public void maxValidationReference() {
		this.validationTab(Reference, characters16);
	}

	public void maxValidationQuoteTittle() {
		this.validationTab(QuoteTittle, characters256);
	}

	public void maxInventoryItem() throws IOException, InterruptedException {
//		Thread.sleep(5000);
		this.validationTab(InventoryItem, getPropertyValue("256Characters"));

	}

	public void validationQuantity(String value) {
		if (value.equals("EmptyValidation")) {
			this.validationTab(Quantity, "-1");
		} else if (value.equals("MaxQuantity")) {
			this.validationTab(Quantity, numberCharacter15);
		} else if (value.equals("AfterDecimalPoint")) {
			this.validationTab(Quantity, "4564.5445664");
		} else if (value.equals("Value")) {
			this.inputText(Quantity, QuantityValue);
		} else if (value.equals("BeforeDecimalPoint")) {
			this.validationTab(Quantity, "231231231231231231231.00000000");
		}
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

	public void saveFunction() throws InterruptedException {
		this.mouseActionClick(Save);
	}

	public void pickFirstItem(String value) throws InterruptedException {
		if (value.equals("Contact")) {
			this.mouseActionClick(InventoryItem);
			this.mouseActionClick(InventoryFirstItem);
		} else if (value.equals("Organization")) {
			Thread.sleep(5000);
			this.mouseActionClick(InventoryItem);
			this.mouseActionClick(InventoryFirstItem);
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

	static String getName;
//	private String text;

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

	public String responseMessage(String value) {
		if (value.equals("Create")) {
			String text = this.getText(CreatedMesssage);
			return text;
		} else if (value.equals("Update")) {
			String text = this.getText(UpdatedMessage);
			return text;
		} else if (value.equals("ListUpdate")) {
			String text = this.getText(ListUpdateMessage);
			return text;
		} else if (value.equals("CreateContact")) {
			String text = this.getText(ContactCreateMessage);
			return text;
		}
		return value;

	}

	static String SearchQuoteNo;
	static String SearchQuoteTittle;
	static String SearchQuoteReference;
	static String SearchQuoteCustomer;

	public String listTextValidation(String value) {
		if (value.equals("ListStatus")) {
			String text = this.getText(ListQuoteStatus);
			return text;
		} else if (value.equals("GlobalListStatus")) {
			String text = this.getText(GlobalListQuoteStatus);
			return text;
		} else if (value.equals("QuoteNo")) {
			SearchQuoteNo = this.getText(ListQuoteNo);
			return SearchQuoteNo;
		} else if (value.equals("Tittle")) {
			SearchQuoteTittle = this.getText(ListTittle);
			return SearchQuoteTittle;
		} else if (value.equals("Reference")) {
			SearchQuoteReference = this.getText(ListReference);
			return SearchQuoteReference;
		} else if (value.equals("GlobalQuoteNo")) {
			SearchQuoteNo = this.getText(GlobalListQuoteNo);
			return SearchQuoteNo;
		} else if (value.equals("GlobalTittle")) {
			SearchQuoteTittle = this.getText(GlobalListTittle);
			return SearchQuoteTittle;
		} else if (value.equals("GlobalReference")) {
			SearchQuoteReference = this.getText(GlobalListReference);
			return SearchQuoteReference;
		} else if (value.equals("GlobalCustomerName")) {
			SearchQuoteCustomer = this.getText(GlobalListCustomerName);
			return SearchQuoteCustomer;
		} else if (value.equals("SearchQuoteNo")) {
			this.tagValidation(Search, SearchQuoteNo);
		} else if (value.equals("SearchQuoteTittle")) {
			this.tagValidation(Search, SearchQuoteTittle);
		} else if (value.equals("SearchQuoteReference")) {
			this.tagValidation(Search, SearchQuoteReference);
		} else if (value.equals("SearchCustomerName")) {
			this.tagValidation(Search, SearchQuoteCustomer);
		} else if (value.equals("Invalid")) {
			this.tagValidation(Search, "sdfsfsdfsfs");
		} else if (value.equals("InvalidList")) {
			String text = this.getText(Invalid);
			return text;
		}
		return value;
	}

	static int parseInt;

	public int getCount() {
		this.assertName(Invalid, "No Result Found");
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
			this.assertName(QuoteLable, "Quotes No.");
			String text2 = this.getText(TotalCount);
			int parseInt = Integer.parseInt(text2);
			return parseInt;
		}
		return value;
	}

	public void CRUDValidation(String value) throws InterruptedException, IOException {
		if (value.equals("Create")) {
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
		} else if (value.equals("Edit")) {
			String text = this.getText(ListReference);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.valuePresent(Reference, text);
			this.dropDownByIndex(QuoteStatus, 1);
			this.clearField(Reference);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(Save);
		} else if (value.equals("GlobalEdit")) {
			String text = this.getText(GlobalListReference);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(GlobalEdit);
			this.valuePresent(Reference, text);
			this.dropDownByIndex(QuoteStatus, 1);
			this.clearField(Reference);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(Save);
		} else if (value.equals("EditOrg")) {
			String text = this.getText(ListReference);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(EditOrg);
			this.valuePresent(Reference, text);
			this.dropDownByIndex(QuoteStatus, 1);
			this.clearField(Reference);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(Save);
		} else if (value.equals("DraftOrg")) {
			String text = this.getText(CustomerName);
			this.mouseActionClick(CreateOrganizationQuote);
			this.valuePresent(ContactName, text);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateQuoteLabel);
			this.mouseActionClick(Yes);
		} else if (value.equals("Draft")) {
			String text = this.getText(CustomerName);
			this.mouseActionClick(CreateContactQuote);
			this.valuePresent(ContactName, text);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateQuoteLabel);
			this.mouseActionClick(Yes);
		} else if (value.equals("CilckCreateQuote")) {
			this.mouseActionClick(CreateGlobalQuote);
		} else if (value.equals("ClickOrgRadio")) {
			this.mouseActionClick(RadioOrganization);
		} else if (value.equals("GlobalContactDraft")) {
			this.autoCompleteCreation("GlobalContact");
			this.autoCompleteCreation("VisibleCustomerName");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateQuoteLabel);
			this.mouseActionClick(Yes);
		} else if (value.equals("GlobalOrgDraft")) {
			this.autoCompleteCreation("GlobalOrganization");
			this.autoCompleteCreation("VisibleCustomerOrgName");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateQuoteLabel);
			this.mouseActionClick(Yes);
		} else if (value.equals("DraftEdit")) {
			String text = this.getText(ListReference);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.valuePresent(Reference, text);
			this.clearFields("Reference");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.pickFirstItem("Contact");
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
			this.assertName(UpdatedMessage, getPropertyValue("UpdatedMessage"));
			this.mouseActionClick(ListQuoteStatus);
			this.mouseActionClick(Update);
			this.assertName(ListUpdateMessage, getPropertyValue("QuoteStatusUpdateMessage"));
		} else if (value.equals("GlobalDraftEdit")) {
			String text = this.getText(GlobalListReference);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(GlobalEdit);
			this.valuePresent(Reference, text);
			this.clearFields("Reference");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.pickFirstItem("Contact");
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
			this.assertName(UpdatedMessage, getPropertyValue("UpdatedMessage"));
			this.mouseActionClick(GlobalListQuoteStatus);
			this.mouseActionClick(Update);
			this.assertName(ListUpdateMessage, getPropertyValue("QuoteStatusUpdateMessage"));
		} else if (value.equals("DraftEditOrg")) {
			String text = this.getText(ListReference);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(EditOrg);
			this.valuePresent(Reference, text);
			this.clearFields("Reference");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.pickFirstItem("Contact");
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
			this.assertName(UpdatedMessage, getPropertyValue("UpdatedMessage"));
			this.mouseActionClick(ListQuoteStatus);
			this.mouseActionClick(UpdateOrg);
			this.assertName(ListUpdateMessage, getPropertyValue("QuoteStatusUpdateMessage"));
		} else if (value.equals("GlobalCreateDeclined")) {
			this.autoCompleteCreation("GlobalContact");
			this.autoCompleteCreation("VisibleCustomerName");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.pickFirstItem("Contact");
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
			this.mouseActionClick(GlobalListQuoteStatus);
			this.dropDownByIndex(ChooseStatus, 1);
			this.mouseActionClick(Update);
			this.assertName(ListUpdateMessage, getPropertyValue("QuoteStatusUpdateMessage"));
		} else if (value.equals("GlobalCreateOrgDeclined")) {
			this.autoCompleteCreation("GlobalOrganization");
			this.autoCompleteCreation("VisibleCustomerOrgName");
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.pickFirstItem("Contact");
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
			this.mouseActionClick(GlobalListQuoteStatus);
			this.dropDownByIndex(ChooseStatus, 1);
			this.mouseActionClick(Update);
			this.assertName(ListUpdateMessage, getPropertyValue("QuoteStatusUpdateMessage"));
		} else if (value.equals("CreateDeclined")) {
			String text = this.getText(CustomerName);
			this.mouseActionClick(CreateContactQuote);
			this.valuePresent(ContactName, text);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.pickFirstItem("Contact");
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
			this.mouseActionClick(ListQuoteStatus);
			this.dropDownByIndex(ChooseStatus, 1);
			this.mouseActionClick(Update);
			this.assertName(ListUpdateMessage, getPropertyValue("QuoteStatusUpdateMessage"));
		} else if (value.equals("CreateDeclinedOrg")) {
			String text = this.getText(CustomerName);
			this.mouseActionClick(CreateOrganizationQuote);
			this.valuePresent(ContactName, text);
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.pickFirstItem("Contact");
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
			this.mouseActionClick(ListQuoteStatus);
			this.dropDownByIndex(ChooseStatus, 1);
			this.mouseActionClick(UpdateOrg);
			this.assertName(ListUpdateMessage, getPropertyValue("QuoteStatusUpdateMessage"));
		}

	}

}
