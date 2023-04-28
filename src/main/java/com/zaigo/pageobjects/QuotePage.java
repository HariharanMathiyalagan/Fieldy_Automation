package com.zaigo.pageobjects;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

	public void invisible(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();

	}

	private void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 50);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void elementClickable(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	private void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 20);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
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

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
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

	public Boolean conditionChecking1(By element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 1);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean valuePresentCondition(WebElement element, String value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 50);
			text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-list_aserpttbl']//tr[2]//td[2]//span"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-list_aserpttbl']//tr[2]//td[2]//span") })
	WebElement CustomerListName;

	@FindAll({ @FindBy(xpath = "//*[@id='customer-details-hide']//ul//li[4]"),
			@FindBy(xpath = "//*[@id='customer-nav-menu-company']//ul//li[4]") })
	WebElement ClickTabQuote;

	@FindAll({ @FindBy(xpath = "//*[@data-automationid='customer-organization-quote-create']"),
			@FindBy(xpath = "//*[@data-automationid='customer-contact-quote-create']"),
			@FindBy(xpath = "//*[@id='job-show-details-timeline']//div[1]//div[3]//button") })
	WebElement CreateButton;

	@FindAll({ @FindBy(xpath = "//a[@data-draftback='quotedraft']"),
			@FindBy(xpath = "//a[@data-exitpopup='customer_contact_quote__all__status']"),
			@FindBy(xpath = "//a[@data-exitpopup='customer_organization_quote__all__status']"),
			@FindBy(xpath = "//*[@data-menuselector='quotes-menu']"),
			@FindBy(xpath = "//*[@id='quote-contact-edit']//header//div//div//div//a") })
	WebElement CreateQuoteLabel;

	@FindAll({ @FindBy(id = "customer-contact-nav-route"), @FindBy(id = "customer-company-nav-route"),
			@FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/div[1]/header/div/div/div"),
			@FindBy(xpath = "//*[@id='quote-create']/header/div/div/div/a") })
	WebElement ListPage;

	By CustomerName = By.id("customer-name");
	By Reference = By.id("reference_no");
	By ExpiryDate = By.id("expiry_date");
	By QuoteStatus = By.id("quote_status");
	By QuoteTittle = By.id("doc_title");
	By InventoryItem = By.xpath("//*[@id='quoteitem-0']/div[1]/div[1]/input[1]");
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
	By Send = By.xpath("//*[contains(text(),'Send')]");
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
	By Yes = By.xpath("//*[text()='Yes']");
	By StatusList = By.xpath("//*[contains(text(),'The doc expiry date must be a date after or equal')]");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-organization-quote-list']//tr[2]//td[2]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-quote-list']//tr[2]//td[2]"),
			@FindBy(xpath = "//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//td[2]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListQuoteNo;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-quote-list']//tr[2]//td[3]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-quote-list']//tr[2]//td[3]"),
			@FindBy(xpath = "//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//td[3]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListTittle;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-quote-list']//tr[2]//td[4]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-quote-list']//tr[2]//td[4]"),
			@FindBy(xpath = "//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//td[4]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListReference;

//	By GlobalListCustomerName = By.xpath("//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//td[5]");

	@FindAll({ @FindBy(xpath = "//*[text()='No Result Found']"),
			@FindBy(xpath = "//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//td[5]") })
	WebElement GlobalListCustomerName;
//	By TotalCount = By.id("total-quote-count");
	@FindAll({ @FindBy(id = "total-quote-count"), @FindBy(id = "quote-nav-count-all") })
	WebElement TotalCount;

	@FindAll({ @FindBy(xpath = "//*[@id='items__item_name__0-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[text()='No Data Found']") })
	WebElement InventoryFirstItem;
	By InventoryName = By.xpath("//*[@id='items__item_name__0-autocomplete-list']//div[1]");
	By Dashboard = By.xpath("//*[text()=' Company Performance']");
	By Amount = By.id("Quote__total__totalamount");
//	By Invalid = By.xpath("//*[text()='No Result Found']");
	@FindAll({ @FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='Quotes No']") })
	WebElement Invalid;
	By QuoteLable = By.xpath("//*[text()='Quotes No.']");
	@FindAll({
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-quote-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]/i"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-quote-list']//tr[2]//td[1]//div[1]//div[1]"),
			@FindBy(xpath = "//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//td[1]") })
	WebElement ThreeDots;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//ul//li[2]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-quote-list']//tr[2]//ul//li[2]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-quote-list']//tr[2]//ul//li[2]") })
	WebElement Edit;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-quote-list']//tr[2]//td[9]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-quote-list']//tr[2]//td[9]"),
			@FindBy(xpath = "//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//td[10]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListQuoteStatus;

	By Search = By.id("quote-search-filter");
	By SearchButton = By.id("quote-enter-search");
	By Update = By.xpath("//*[text()='Update']");
	By UpdateOrg = By.xpath("//*[@data-list-namme='customer-organization-quote']");
	By ChooseStatus = By.xpath("//*[@class='floating-input field-input']");
	By Quote = By.id("quote-menu");
	By RadioOrganization = By.id("organization");
	By RadioContact = By.id("contact");
	By ErrorCustomerName = By.id("id_customer_group_error");
	By OrganizationName = By.id("company_name");
	By Website = By.xpath("(//*[@id='website'])[1]");
	By OrganizationJobTittle = By.id("contacts__job_title__0");
	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By Cancel1 = By.xpath("//*[@class='js-snackbar__close bold']");
	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='first_name']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='contacts__first_name__0']") })
	WebElement FirstNameField;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='last_name']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='contacts__last_name__0']") })
	WebElement LastNameField;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='email']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='contacts__email__0']") })
	WebElement EmailField;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='phones__number__0']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='contacts__phone__0']") })
	WebElement PhoneNumber;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='line_1']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='addresses__line_1__0']") })
	WebElement Address1Field;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='line_2']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='addresses__line_2__0']") })
	WebElement Address2Field;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='city']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='addresses__city__0']") })
	WebElement CityField;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='state']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='addresses__state__0']") })
	WebElement StateField;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='zipcode']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='addresses__zipcode__0']") })
	WebElement ZipcodeField;

	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='organization-create']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='contact-create']"),
			@FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='organization-contact-create']") })
	WebElement SaveButton;

	@FindAll({ @FindBy(xpath = "//*[@id='quote-contact-create']//*[@id='customer-name-input-field']"),
			@FindBy(xpath = "//*[@id='quote-contact-create']//*[@id='id_customer_group']") })
	WebElement CustomerField;

	@FindAll({ @FindBy(xpath = "//*[@class='col-lg-12 switchcontact d-block']//*[@id='id_customer']") })
	WebElement SubCustomerField;
	By PopupOpen = By.xpath("//*[contains(@class,'fadeIn')]//child::h5");
	@FindAll({
			@FindBy(xpath = "//*[@class='add_new_customer_button3']//button[@data-modalfetch='shorter_organization_contact_create']"),
			@FindBy(xpath = "//*[@class='add_new_customer_button2']//button[@data-modalfetch='shorter_organization_create']"),
			@FindBy(xpath = "//*[@class='add_new_customer_button no-add_new_customer_button']//button[@data-modalfetch='shorter_contact_create']") })
	WebElement AddCustomer;
	By SubCustomerListField = By.xpath("//*[@id='contactdropdownlist3']//child::div[1]//div[1]");
	@FindAll({
			@FindBy(xpath = "//*[@id='contactdropdownlist' and contains(@style,'display:block;')]//child::div[1]//div[1]"),
			@FindBy(xpath = "//*[@id='contactdropdownlist2' and contains(@style,'display:block;')]//child::div[1]//div[1]"),
			@FindBy(xpath = "//*[text()=' No Data Found!']"),
			@FindBy(xpath = "//*[@id='contactdropdownlist3' and contains(@style,'display:block;')]//child::div[1]//div[1]") })
	WebElement CustomerListField;
	@FindAll({ @FindBy(xpath = "//*[@id='customer-contact-quote-list']//div[1]//div[4]//button[1]//div"),
			@FindBy(xpath = "//*[@id='qoute-show-details-timeline']//div[1]//div[4]//button[1]//div"),
			@FindBy(xpath = "//*[@id='job-show-details-timeline']//div[1]//div[2]//button[1]//div") })
	WebElement Filter;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-organization-quote-list_aserpttbl']//tr[2]//td[5]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-quote-list_aserpttbl']//tr[2]//td[5]"),
			@FindBy(xpath = "//*[@id='fieldy-main-quote-list_aserpttbl']//tr[2]//td[6]") })
	WebElement ListDate;

	By CreateFrom = By.id("quote-from-date-filter");
	By CreateTo = By.id("quote-to-date-filter");

	public int getCount(int value) {
		if (value == 1) {
			wait = new WebDriverWait(driver, 10);
			String text2 = wait.until(ExpectedConditions.visibilityOf(TotalCount)).getText();
			StringBuffer buffer = new StringBuffer();
			StringBuffer append = null;
			for (int i = 0; i < text2.length(); i++) {
				if (Character.isDigit(text2.charAt(i))) {
					append = buffer.append(text2.charAt(i));
					parseInt = Integer.parseInt(append.toString());
				}
			}
			return parseInt;
		}
		return value;
	}

	public String actualResult(String value) {
		if (value.equals("User")) {
			int a = parseInt + 1;
			String s = String.valueOf(a);
			String valueOf = "All ( " + s + " )";
			return valueOf;
		}
		return value;
	}

	public String totalCount(String value) throws InterruptedException {
		if (value.equals("User")) {
			this.visibility(Invalid);
			String text2 = this.getText(TotalCount);
			return text2;
		}
		return value;
	}

	static String ContactFirstName;
	static String ContactLastName;

	public void autoCompleteField(String value) throws InterruptedException, IOException {
		Boolean condition = true;
		if (value.equals("OrganizationContactCreate")) {
			this.inputText(SubCustomerField, fakeFirstName);
			this.mouseActionClick(AddCustomer);
			if (!this.conditionChecking(PopupOpen)) {
				do {
					this.mouseActionClick(AddCustomer);
					if (this.conditionChecking(PopupOpen)) {
						condition = false;
					}
				} while (condition);
			}
			this.inputText(FirstNameField, fakeFirstName);
			ContactFirstName = this.getTextAttribute(FirstNameField);
			this.inputText(LastNameField, fakeLastName);
			ContactLastName = this.getTextAttribute(LastNameField);
			this.inputText(EmailField, fakeEmail);
			this.inputText(PhoneNumber, fakePhoneNumber);
			this.inputText(OrganizationJobTittle, fakeTittle);
			this.mouseActionClick(SaveButton);
		} else if (value.equals("VisibleName")) {
			if (!this.valuePresentCondition(SubCustomerField, ContactFirstName + " " + ContactLastName)) {
				this.inputText(SubCustomerField, ContactFirstName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("OrganizationContactCreate");
						this.message("Message");
						if (this.valuePresentCondition(SubCustomerField, ContactFirstName + " " + ContactLastName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		} else if (value.equals("GlobalContactVisibleName")) {
			if (!this.valuePresentCondition(CustomerField, ContactFirstName + " " + ContactLastName)) {
				this.inputText(CustomerField, ContactFirstName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("ContactCreate");
						this.message("Message");
						if (this.valuePresentCondition(CustomerField, ContactFirstName + " " + ContactLastName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		} else if (value.equals("OrgVisibleName")) {
			if (!this.valuePresentCondition(CustomerField, ContactFirstName)) {
				this.inputText(CustomerField, ContactFirstName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("OrganizationCreate");
						this.message("Message");
						if (this.valuePresentCondition(CustomerField, ContactFirstName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		} else if (value.equals("ContactCreate")) {
			this.inputText(CustomerField, fakeFirstName);
			this.mouseActionClick(AddCustomer);
			if (!this.conditionChecking(PopupOpen)) {
				do {
					this.mouseActionClick(AddCustomer);
					if (this.conditionChecking(PopupOpen)) {
						condition = false;
					}
				} while (condition);
			}
			this.inputText(FirstNameField, fakeFirstName);
			ContactFirstName = this.getTextAttribute(FirstNameField);
			this.inputText(LastNameField, fakeLastName);
			ContactLastName = this.getTextAttribute(LastNameField);
			this.inputText(EmailField, fakeEmail);
			this.inputText(PhoneNumber, fakePhoneNumber);
			this.inputText(Address1Field, fakeAddress1);
			this.inputText(Address2Field, fakeAddress2);
			this.inputText(CityField, fakeCity);
			this.inputText(StateField, fakeState);
			this.inputText(ZipcodeField, fakeZipcode);
			this.mouseActionClick(SaveButton);
		} else if (value.equals("OrganizationCreate")) {
			this.inputText(CustomerField, fakeCompanyName);
			this.mouseActionClick(AddCustomer);
			if (!this.conditionChecking(PopupOpen)) {
				do {
					this.mouseActionClick(AddCustomer);
					if (this.conditionChecking(PopupOpen)) {
						condition = false;
					}
				} while (condition);
			}
			this.inputText(OrganizationName, fakeCompanyName);
			ContactFirstName = this.getTextAttribute(OrganizationName);
			this.inputText(PhoneNumber, fakePhoneNumber);
			this.inputText(EmailField, fakeEmail);
			this.inputText(Website, fakeWebsite);
			this.inputText(Address1Field, fakeAddress1);
			this.inputText(Address2Field, fakeAddress2);
			this.inputText(CityField, fakeCity);
			this.inputText(StateField, fakeState);
			this.inputText(ZipcodeField, fakeZipcode);
			this.mouseActionClick(SaveButton);
		}
	}

	@FindAll({ @FindBy(xpath = "//*[text()='Organization Name']"), @FindBy(xpath = "//*[text()='Contact Name']") })
	WebElement ListLabelName;

	public String labelValidation(String value) throws InterruptedException {
		if (value.equals("Customer")) {
			this.mouseActionClick(CustomerListName);
			this.visibility(CustomerName);
			this.mouseActionClick(ClickTabQuote);
			this.visibility(Invalid);
			this.getCount();
		} else if (value.equals("Global")) {
			this.mouseActionClick(Quote);
			this.mouseActionClick(Filter);
			this.visibility(Apply);
			this.visibility(Invalid);
			this.getCount(1);
		} else if (value.equals("CreateLabel")) {
			this.mouseActionClick(CreateButton);
			this.visibility(Send);
		} else if (value.equals("EditLabel") || value.equals("GlobalEdit")) {
			String text = this.getText(ListReference);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.valuePresent(Reference, text);
		}
		return this.getText(ListPage);
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
			this.clearField(Search);
		}
	}

	static String currentDate;

	public String dateValidation(String value) throws InterruptedException, ParseException {
		if (value.equals("FutureDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			String currentDate = sdf.format(cal.getTime());
			this.inputText(ExpiryDate, currentDate);
		} else if (value.equals("Current")) {
			this.inputText(ExpiryDate, "01/14/2023");
		} else if (value.equals("CurrentDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 0);
			currentDate = sdf.format(cal.getTime());
			return currentDate;
		} else if (value.equals("CurrentDateError")) {
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
			this.inputText(ExpiryDate, pastDate);
			this.mouseActionClick(Save);
		} else if (value.equals("GlobalPastDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -1);
			String pastDate = sdf.format(cal.getTime());
			this.inputText(ExpiryDate, pastDate);
			this.mouseActionClick(Send);
		} else if (value.equals("Convertion")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
			Date parse = sdf.parse(text);
			sdf = new SimpleDateFormat("MM/dd/yyyy");
			String format = sdf.format(parse);
			this.mouseActionClick(Filter);
			this.inputText(CreateFrom, format);
//			this.inputText(CreateTo, format);
			this.mouseActionClick(Apply);
		} else if (value.equals("ListCreateDate")) {
			text = this.getText(ListDate);
			return text;
		}
		return value;
	}

	By Apply = By.xpath("//*[contains(text(),'Apply')]");

	static String text;

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
			String text = this.getText(Message);
			this.mouseActionClick(Cancel1);
			return text;
		} else if (value.equals("ErrorContact")) {
			String text = this.getText(ErrorCustomerName);
			return text;
		}
		return value;
	}

	public void referenceField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Reference, characters16);
		}
	}

	public void tittleField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(QuoteTittle, characters256);
		}

	}

	public void inventoryItemField(String value) throws IOException, InterruptedException {
		if (value.equals("MaxValidation")) {
			this.validationTab(InventoryItem, getPropertyValue("256Characters"));
		}

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

	public void notesField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Notes, characters2048);
		}
	}

	public void saveFunction(String value) throws InterruptedException {
		if (value.equals("Mandatory") || value.equals("OrganizationMandatory")) {
			if (value.equals("OrganizationMandatory")) {
				this.mouseActionClick(RadioOrganization);
			}
			this.mouseActionClick(Save);
			if (this.conditionChecking1(ErrorExpiryDate)) {
			} else {
				do {
					this.mouseActionClick(Save);
				} while (!this.conditionChecking1(ErrorExpiryDate));
			}
		} else if (value.equals("ClickButton")) {
			this.mouseActionClick(Save);
		} else if (value.equals("Organization")) {
			this.mouseActionClick(RadioOrganization);
		}
	}

	public void pickFirstItem(String value) throws InterruptedException {
		Boolean condition = true;
		if (value.equals("Contact") || value.equals("Organization")) {
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

	public String customerName(String value) {
		if (value.equals("DetailScreenCustomerName")) {
			getName = this.getText(CustomerName);
			return getName;
		} else if (value.equals("PlaceHolderName")) {
			this.valuePresent(CustomerField, getName);
			String textAttribute = this.getTextAttribute(CustomerField);
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

	static String responseMessage;
	static String messageCheck;

	public String message(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("message")) {
			if (this.conditionChecking(Message)) {
				responseMessage = this.getText(Message);
				this.invisible(Message);
				return responseMessage;
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(SaveButton);
					if (this.conditionChecking(Message)) {
						responseMessage = this.getText(Message);
						this.invisible(Message);
						if (responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateFunction")) {
			do {
				if (responseMessage.equals(getPropertyValue("ContactEmailAlreadyMessage"))) {
					this.clearField(EmailField);
					String fakeEmail = faker.internet().safeEmailAddress();
					this.inputText(EmailField, fakeEmail);
					this.mouseActionClick(SaveButton);
				} else if (responseMessage.equals(getPropertyValue("CompanyAlreadyMessage"))) {
					this.clearField(OrganizationName);
					String fakeCompanyName = faker.company().name();
					this.inputText(OrganizationName, fakeCompanyName);
					this.mouseActionClick(SaveButton);
				} else if (responseMessage.equals(getPropertyValue("CompanyEmailAlreadyMessage"))) {
					this.clearField(EmailField);
					String fakeEmail = faker.internet().safeEmailAddress();
					this.inputText(EmailField, fakeEmail);
					this.mouseActionClick(SaveButton);
				} else if (responseMessage.equals(getPropertyValue("CompanyContactEmailMessage"))) {
					this.clearField(EmailField);
					String fakeEmail = faker.internet().safeEmailAddress();
					this.inputText(EmailField, fakeEmail);
					this.mouseActionClick(SaveButton);
				}
				if (this.conditionChecking(Message)) {
					messageCheck = this.getText(Message);
					this.invisible(Message);
					if (messageCheck.equals(getPropertyValue("CustomerCreatedMessage"))) {
						conditionCheck = false;
					}
				} else {
					do {
						Thread.sleep(10000);
						this.mouseActionClick(SaveButton);
						if (this.conditionChecking(Message)) {
							messageCheck = this.getText(Message);
							this.invisible(Message);
							if (messageCheck.equals(getPropertyValue("CustomerCreatedMessage"))) {
								conditionCheck = false;
							}
						}
					} while (conditionCheck);
				}
			} while (conditionCheck);
		}
		return value;
	}

	static String searchData;

	public String listTextValidation(String value) {
		driver.manage().deleteAllCookies();
		if (value.equals("ListStatus")) {
			String text = this.getText(ListQuoteStatus);
			return text;
		} else if (value.equals("QuoteNo")) {
			searchData = this.getText(ListQuoteNo);
			return searchData;
		} else if (value.equals("Tittle")) {
			searchData = this.getText(ListTittle);
			return searchData;
		} else if (value.equals("Reference")) {
			searchData = this.getText(ListReference);
			return searchData;
		} else if (value.equals("GlobalCustomerName")) {
			searchData = this.getText(GlobalListCustomerName);
			return searchData;
		} else if (value.equals("SearchData")) {
			this.elementClickable(SearchButton);
			this.tagValidation(Search, searchData);
		} else if (value.equals("Invalid")) {
			this.inputText(Search, "rewwrewrwrwrwrew");
			this.mouseActionClick(SearchButton);
		} else if (value.equals("InvalidList")) {
			String text = this.getText(Invalid);
			return text;
		}
		return value;
	}

	static int parseInt;

	public int getCount() {
		this.visibility(Invalid);
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOf(TotalCount)).getText();
		parseInt = Integer.parseInt(text2);
		return parseInt;
	}

	public int countValidation(int value) {
		if (value == 1) {
			int a = parseInt + 1;
			return a;
		} else if (value == 2) {
			String text2 = this.getText(TotalCount);
			int parseInt = Integer.parseInt(text2);
			return parseInt;
		}
		return value;
	}

	public void CRUDValidation(String value) throws InterruptedException, IOException, ParseException {
		if (value.equals("Create") || value.equals("CreateValue")) {
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			if (value.equals("CreateValue")) {
				this.pickFirstItem("Contact");
			}
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
		} else if (value.equals("DraftOrg") || value.equals("Draft") || value.equals("GlobalContactDraft")
				|| value.equals("GlobalOrgDraft")) {
			if (value.equals("DraftOrg") || value.equals("Draft")) {
				String text = this.getText(CustomerName);
				this.mouseActionClick(CreateButton);
				this.valuePresent(CustomerField, text);
			} else if (value.equals("GlobalContactDraft") || value.equals("GlobalOrgDraft")) {
				if (value.equals("GlobalContactDraft")) {
					this.autoCompleteField("ContactCreate");
					if (this.message("message").equals(getPropertyValue("CustomerCreatedMessage"))) {
					} else {
						this.message("AlternateFunction");
					}
					this.autoCompleteField("GlobalContactVisibleName");
				} else if (value.equals("GlobalOrgDraft")) {
					this.autoCompleteField("OrganizationCreate");
					if (this.message("message").equals(getPropertyValue("CustomerCreatedMessage"))) {
					} else {
						this.message("AlternateFunction");
					}
					this.autoCompleteField("OrgVisibleName");
				}
			}
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.mouseActionClick(CreateQuoteLabel);
			this.mouseActionClick(Yes);
			this.message("message");
		} else if (value.equals("CilckCreateQuote")) {
			this.mouseActionClick(CreateButton);
		} else if (value.equals("ClickOrgRadio")) {
			this.mouseActionClick(RadioOrganization);
		} else if (value.equals("DraftEdit") || value.equals("GlobalDraftEdit") || value.equals("DraftEditOrg")) {
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
			this.message("message");
			this.mouseActionClick(ListQuoteStatus);
			this.mouseActionClick(Update);
			this.message("message");
		} else if (value.equals("GlobalCreateDeclined") || value.equals("GlobalCreateOrgDeclined")
				|| value.equals("CreateDeclined") || value.equals("CreateDeclinedOrg")) {
			if (value.equals("GlobalCreateDeclined")) {
				this.autoCompleteField("ContactCreate");
				if (this.message("message").equals(getPropertyValue("CustomerCreatedMessage"))) {
				} else {
					this.message("AlternateFunction");
				}
				this.autoCompleteField("GlobalContactVisibleName");
			} else if (value.equals("GlobalCreateOrgDeclined")) {
				this.autoCompleteField("OrganizationCreate");
				if (this.message("message").equals(getPropertyValue("CustomerCreatedMessage"))) {
				} else {
					this.message("AlternateFunction");
				}
				this.autoCompleteField("OrgVisibleName");
			} else if (value.equals("CreateDeclined") || value.equals("CreateDeclinedOrg")) {
				String text = this.getText(CustomerName);
				this.mouseActionClick(CreateButton);
				this.valuePresent(CustomerField, text);
			}
			this.inputText(Reference, ReferencePrefix + "-" + ReferenceNo);
			this.dateValidation("FutureDate");
			this.inputText(QuoteTittle, fakeTittle);
			this.pickFirstItem("Contact");
			this.clearFields("Description");
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Save);
			this.message("message");
			this.mouseActionClick(ListQuoteStatus);
			this.dropDownByIndex(ChooseStatus, 1);
			this.mouseActionClick(Update);
			this.message("message");
			this.invisible(StatusList);
		}
	}

	public void clearAllFields() {
		List<String> asList = Arrays.asList("Reference", "QuoteTittle", "Expiry", "Inventory", "Quantity", "Discount",
				"Tax", "Description", "Notes", "Price");
		for (int i = 0; i < asList.size(); i++) {
			this.clearFields(asList.get(i));
		}
	}

	public void inventoryItem() {
		this.mouseActionClick(InventoryItem);
		this.mouseActionClick(InventoryFirstItem);

	}

	public void visible() {
		this.elementClickable(SearchButton);
	}

	By Reset = By.xpath("//*[contains(text(),'Reset Search')]");

	public void clickEvent() {
		this.clearField(Search);

	}

	public void createFunction() throws IOException, InterruptedException {
		if (!responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))) {
			this.message("AlternateFunction");
		}
	}
}
