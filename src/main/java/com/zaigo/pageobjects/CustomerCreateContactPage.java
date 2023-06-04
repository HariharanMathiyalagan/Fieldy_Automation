package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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

public class CustomerCreateContactPage extends BaseClass {

	WebDriverWait wait;
	WebDriver driver;

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String maxPhoneNumber = RandomStringUtils.randomNumeric(25);
	String minPhoneNumber = RandomStringUtils.randomNumeric(2);

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
	String fakeCompanyName = faker.company().name();
	String fakeFaxNumber = faker.number().digits(14);
	String fakeTittle = faker.name().title();
	String fakeProductName = faker.book().genre();
	String fakeBrandName = faker.book().author();
	public static String firstName;
	public static String lastName;
	public static String jobTittle;
	public static String email;
	public static String phoneNumber;
	public static String taxNumber;
	public static String leadSources;
	public static String IndustryTypes;
	public static String propertyFirstName;
	public static String propertyLastName;
	public static String propertyName;
	public static String address1;
	public static String address2;
	public static String stateName;
	public static String cityName;
	public static String zipcode;
	public static String productName;
	public static String brandName;
	public static String modelNumber;
	public static String serialNumber;
	public static String dateInstalled;
	public static String warrantyInformation;
	public static String accessHours;
	public static String installationNotes;

	public CustomerCreateContactPage(WebDriver driver) {
		this.driver = driver;
	}

	By DashBoard = By.xpath("//*[text()=' Company Performance']");
	By Today = By.xpath("(//div[@class='mb-2']//parent::div)[4]");
	By Customer = By.id("customer-main");
	By Contact = By.id("customer-contact-menu");
	public static By AddContact = By.id("scheduledrop");
	By ErrorLogo = By.id("logo_error");
	By FormatErrorLogo = By.xpath("//div[text()='Only jpg,jpeg,png Formats Allowed']");
	By CreateContactLabel = By.xpath("//*[@data-goesto='contractor-view']");
	By Yes = By.xpath("//*[text()='Yes']");
	By ResponseMessage = By.xpath("//*[@class='created_successfully d-flex d-none']");
	By ListName = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[2]/span");
	By Search = By.id("customer-contact-search-value");
	By SearchButton = By.id("customer-contact-search-button");
	By InvalidList = By.xpath("//div[text()='No Result Found']");
	By TotalCount = By.id("Total-number-customer-count");
	@FindAll({
			@FindBy(xpath = "//*[contains(@class,'fieldy-tab-active')]//parent::div//input[@id='equipments__product_name__0']"),
			@FindBy(xpath = "//*[contains(@class,'fieldy-tab-active')]//parent::div//input[@id='addresses__contact_person_first_name__0']"),
			@FindBy(xpath = "//*[contains(@class,'fieldy-tab-active') and contains(text(),'Attachment')]") })
	WebElement SubPageVisible;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'in-validate')]//following-sibling::div[3]"),
			@FindBy(xpath = "//*[contains(@class,'in-validate')]//following-sibling::div[1]") })
	WebElement ErrorMessage;

	public void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	public void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	public void clearField(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
	}

	public void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 100);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void currentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(DateInstalled, currentDate);

	}

	public void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 100);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void invisible(By element) {
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	public void Displayed(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	public void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	public void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	public void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	public void dropDownByIndex(WebElement element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	public String getText(By element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;
	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

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

	@FindAll({ @FindBy(xpath = "//*[contains(text(),'Customer Name')]"),
			@FindBy(xpath = "//*[contains(text(),'No Result Found')]"),
			@FindBy(xpath = "//*[contains(text(),'Contact Name')]") })
	WebElement CustomerList;
	static int parseInt;

	public int getCount() {
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOfElementLocated(TotalCount)).getText();
		parseInt = Integer.parseInt(text2);
		return parseInt;

	}

	public int listCountValidation(int value) {
		if (value == 1) {
			int actual = parseInt + 1;
			return actual;
		} else if (value == 2) {
			this.Displayed(CustomerList);
			String text2 = this.getText(TotalCount);
			int expected = Integer.parseInt(text2);
			return expected;
		}
		return value;

	}

	By CustomerContact = By.xpath("//*[contains(text(),'Customer / Contact')]");
	By CustomerFilter = By.xpath("//*[@gloss='Filter']");

	public String modulePage() throws InterruptedException, IOException {
		this.mouseActionClick(Customer);
		this.elementtobeClickable(SearchButton);
		this.visibility(CustomerList);
		this.mouseActionClick(Contact);
		String text2 = this.getText(CustomerContact);
		this.elementtobeClickable(SearchButton);
		this.visibility(CustomerList);
		this.getCount();
		this.mouseActionClick(AddContact);
		if (!this.conditionChecking(CreateContactLabel)) {
			do {
				this.mouseActionClick(AddContact);
			} while (!this.conditionChecking(CreateContactLabel));

		}
		return text2;

	}

	By Tittle = By.xpath("//a[@data-exitpopup='customer_contact']");
	By SaveComplete = By.id("customerdrop");
	By ErrorFirstName = By.id("first_name_error");
	By Virus = By.xpath("//div[@id='dropid-4189']");
	By FirstName = By.id("first_name");
	By MaxErrorFirstName = By.id("first_name_error");
	By LastName = By.id("last_name");
	By ErrorLastName = By.id("last_name_error");
	By JobTittle = By.id("job_title");
	By ErrorJobTittle = By.id("job_title_error");
	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By Updated = By.xpath("//*[text()='Customer details updated successfully']");
	By Email = By.id("email");
	By ErrorEmail = By.id("email_error");
	By Phone = By.id("phones__number__0");
	By ErrorPhoneNo = By.id("phones__number__0_error");
	By Organization = By.xpath("//input[@placeholder='Choose Organization Name']");
	By New = By.xpath("//button[@data-modalfetch='shorter_organization_create']");
	By Logo = By.xpath("//input[@id='logo']//following::label[@for='logo']");
	@FindAll({ @FindBy(xpath = "//*[@id='customer_contact_create_edit']/div[1]/div[3]/div[4]/div[2]/input[1]"),
			@FindBy(xpath = "//*[@id='customer_contact_create_edit']/div[1]/div[2]/div[4]/div[2]/input[1]") })
	WebElement LeadSources;
	@FindAll({ @FindBy(xpath = "//*[@id='industry_type-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[text()='No Data Found']") })
	WebElement IndustryTypeDropDown;
	By FirstIndusrty = By.xpath("//*[@id='industry_type-autocomplete-list']//div[1]");
	@FindAll({ @FindBy(xpath = "//*[@id='customer_contact_create_edit']/div[1]/div[3]/div[5]/div[2]/input[1]"),
			@FindBy(xpath = "//*[@id='customer_contact_create_edit']/div[1]/div[2]/div[5]/div[2]/input[1]") })
	WebElement IndustryType;

	By TaxNumber = By.id("tax_number");
	@FindAll({ @FindBy(xpath = "//*[@id='lead_source-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[text()='No Data Found']") })
	WebElement Social;
	@FindAll({ @FindBy(xpath = "//*[@id='edit-list-file']/div/div[1]/span"),
			@FindBy(xpath = "//*[@id='previews']/div/div/div[1]/span") })
	WebElement FirstAttachment;
	By AlreadyEmail = By.xpath("//span[text()='The e-mail is already exit']");
	By Next = By.xpath("//*[contains(text(),'Next')]");
	By MakethisProperty = By.id("addresses__is_primary__0");
	By PropertyName = By.id("addresses__location_name__0");
	By PropertyFirstName = By.id("addresses__contact_person_first_name__0");
	By PropertyLastName = By.id("addresses__contact_person_last_name__0");
	By ContactPersonName = By.id("addresses__name__0");
	By Address1 = By.id("addresses__line_1__0");
	By Address2 = By.id("addresses__line_2__0");
	By StateName = By.id("addresses__state__0");
	By CityName = By.id("addresses__city__0");
	By Zipcode = By.id("addresses__zipcode__0");
	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div/div");
	By DeleteLocation = By.xpath("//div[@class='accordion-body']//child::div[text()='Delete Property']");
	By AddProperty = By.xpath("(//*[@data-automationid='add-more']//child::i[@class='fa fa-plus'])[2]");
	By ErrorPropertyName = By.id("addresses__location_name__0_error");
	By ErrorPropertyFirstName = By.id("addresses__contact_person_first_name__0_error");
	By ErrorPropertyLastName = By.id("addresses__contact_person_last_name__0_error");
	By ErrorContactPersonName = By.id("addresses__name__0_error");
	By ErrorAddress1 = By.id("addresses__line_1__0_error");
	By ErrorAddress2 = By.id("addresses__line_2__0_error");
	By ErrorStateName = By.id("addresses__state__0_error");
	By ErrorCityName = By.id("addresses__city__0_error");
	By ErrorZipCode = By.id("addresses__zipcode__0_error");
	By Previous = By.xpath("//i[@class='fa fa-chevron-left font-14 pr-2']");
	By ProductName = By.id("equipments__product_name__0");
	By ErrorProductName = By.id("equipments__product_name__0_error");
	By BrandName = By.id("equipments__brand_name__0");
	By ErrorBrandName = By.id("equipments__brand_name__0_error");
	By ModelNumber = By.id("equipments__model_number__0");
	By ErrorModelNumber = By.id("equipments__model_number__0_error");
	By SerialNumber = By.id("equipments__serial_number__0");
	By DateInstalled = By.id("equipments__date_installed__0");
	By ErrorSerialNumber = By.id("equipments__serial_number__0_error");
	By WarrantyInformation = By.id("equipments__warrenty_info__0");
	By AccessHours = By.id("equipments__access_hours__0");
	By ErrorAccessHours = By.id("equipments__access_hours__0_error");
	By InstallationNotes = By.id("equipments__installation_notes__0");
	By ErrorInstallationNotes = By.id("equipments__installation_notes__0_error");
	By Dots = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]");
	By Edit = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[1]");
	By Deleted = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]");
	By EditLabel = By.xpath("//*[@data-exitpopup='customer_contact']");
	By ErrorDateInstalled = By.xpath("//*[text()='DATE_INSTALLED 1: date_installed exceeds current_date limit']");
	By reset = By.xpath("//*[@onclick=\"generateCustomerContactTable('','','','','reset')\"]");
	By Status = By.id("customer-contact-status-active");
	By Filter = By.xpath("//*[@id='customer-contact-timeline']/div[1]/div[4]/button/div");
	By Apply = By.xpath("//*[@id='customer-contact-timeline']/div[2]/div/div/div/div[4]/button");
	By ListPhoneNumber = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[4]/a");
	By ListSocial = By.xpath("//*[@id='customer-lead-source-div']/div[1]/div[1]/div[1]/input[1]");
	By ListIndustry = By.xpath("//*[@id='customer-industry-type-div']/div[1]/div[1]/div[1]/input[1]");
	By ListLeadSource = By.id("customer-contact-lead-source-search");
	By ListIndustryType = By.id("customer-contact-industry-type-search");
	By ListEmail = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[5]/a");
	By Cancel = By.xpath("//*[@class='js-snackbar__close bold']");
	By Attachment = By.xpath("//*[@id='customerDropZone']/div/span/b");

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
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
			wait = new WebDriverWait(driver, 2);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking1(WebElement element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 2);
			text = wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public void mouseAction(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();

	}

	public String getText(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return text2;
	}

	public void alreadyExistMail() throws InterruptedException {
		String text = this.getText(ListEmail);
		this.mouseActionClick(AddContact);
		this.inputText(FirstName, "Ajith");
		this.scrollDown();
		this.validationTab(Email, text);

	}

	public void nextFunction() {
		this.mouseActionClick(Tittle);
		this.mouseActionClick(Yes);
	}

	static String data;

	public String listValidation(String value) {
		if (value.equals("AlphabetFilter")) {
			String str = this.getText(ListName);
			char first = str.charAt(0);
			By Alpbabet = By.xpath("//*[@data-filteralphacon='" + first + "']");
			this.mouseActionClick(Alpbabet);
			String text2 = this.getText(ListName);
			return text2;
		} else if (value.equals("ListName")) {
			data = this.getText(ListName);
			return data;
		} else if (value.equals("SearchData")) {
			this.tagValidation(Search, data);
		} else if (value.equals("ListPhoneNumber")) {
			data = this.getText(ListPhoneNumber);
			return data;
		} else if (value.equals("ListEmail")) {
			data = this.getText(ListEmail);
			return data;
		} else if (value.equals("FilterList")) {
			this.mouseActionClick(Filter);
			this.mouseActionClick(ListLeadSource);
			this.mouseActionClick(ListSocial);
			this.dropDownByIndex(Status, 1);
			this.mouseActionClick(Apply);
		} else if (value.equals("IndustryFilter")) {
			this.mouseActionClick(ListIndustryType);
			this.mouseActionClick(ListIndustry);
			this.mouseActionClick(Apply);
		} else if (value.equals("LeadSource")) {
			String text2 = this.getText(ListLeadSources);
			return text2;
		} else if (value.equals("IndustryType")) {
			String text2 = this.getText(ListIndustryTypes);
			return text2;
		}
		return value;

	}

	By ListLeadSources = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[7]/span");
	By ListIndustryTypes = By.xpath("//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[8]/span");

	public String listFirstName() {
		String text2 = this.getText(ListName);
		return text2;

	}

	public void searchListName() {
		String text2 = this.getText(ListName);
		this.inputText(Search, text2);
		this.mouseActionClick(SearchButton);

	}

	public void clearSearch() {
		this.clearField(Search);

	}

	public void searchInvalidListName() {
		this.inputText(Search, "sxrdcftyvghub");
		this.mouseActionClick(SearchButton);

	}

	public String errorList() {
		String text2 = this.getText(InvalidList);
		return text2;

	}

	public String listPhoneNumber() {
		String text2 = this.getText(ListPhoneNumber);
		this.inputText(Search, text2);
		this.mouseActionClick(SearchButton);
		return text2;

	}

	public String listDataPhoneNumber() {
		String text2 = this.getText(ListPhoneNumber);
		return text2;

	}

	public String listDataEmail() {
		String text2 = this.getText(ListEmail);
		return text2;

	}

	public String validaDataPhoneNumber() {
		String text2 = this.getText(ListPhoneNumber);
		return text2;

	}

	public String listEmail() {
		String text2 = this.getText(ListEmail);
		this.inputText(Search, text2);
		this.mouseActionClick(SearchButton);
		return text2;

	}

	public void resetOption() {
		this.mouseActionClick(reset);

	}

	public void filterList() {
		this.mouseActionClick(Filter);
		this.mouseActionClick(ListLeadSource);
		this.mouseActionClick(ListSocial);
		this.dropDownByIndex(Status, 1);
		this.mouseActionClick(Apply);

	}

	static String ListContactName;

	public String LabelValidation(String value) throws AWTException, InterruptedException {
		if (value.equals("Edit")) {
			ListContactName = this.getText(ListEmail);
			this.mouseActionClick(Dots);
			this.mouseActionClick(Edit);
			String text2 = this.getText(EditLabel);
			return text2;
		} else if (value.equals("Create")) {
			String text2 = this.getText(CreateContactLabel);
			return text2;
		}
		return value;

	}

	public void visibleName() {
		this.valuePresent(Email, ListContactName);
	}

	public void deleteContact() {
		this.mouseActionClick(Dots);
		this.mouseActionClick(Deleted);
		this.mouseActionClick(Yes);

	}

	public void clearFields(String value) {
		if (value.equals("Name")) {
			this.inputText(FirstName, "Ajith");
		} else if (value.equals("FirstName")) {
			this.clearField(FirstName);
		} else if (value.equals("LastName")) {
			this.clearField(LastName);
		} else if (value.equals("Email")) {
			this.clearField(Email);
		} else if (value.equals("JobTittle")) {
			this.clearField(JobTittle);
		} else if (value.equals("PhoneNumber")) {
			this.clearField(Phone);
		} else if (value.equals("PropertyFirstName")) {
			this.clearField(PropertyFirstName);
		} else if (value.equals("PropertyLastName")) {
			this.clearField(PropertyLastName);
		} else if (value.equals("PropertyName")) {
			this.clearField(PropertyName);
		} else if (value.equals("PropertyAddress1")) {
			this.clearField(Address1);
		} else if (value.equals("PropertyAddress2")) {
			this.clearField(Address2);
		} else if (value.equals("City")) {
			this.clearField(CityName);
		} else if (value.equals("State")) {
			this.clearField(StateName);
		} else if (value.equals("Zipcode")) {
			this.clearField(Zipcode);
		} else if (value.equals("ProductName")) {
			this.clearField(ProductName);
		} else if (value.equals("BrandName")) {
			this.clearField(BrandName);
		} else if (value.equals("ModelNumber")) {
			this.clearField(ModelNumber);
		} else if (value.equals("SerialNumber")) {
			this.clearField(SerialNumber);
		} else if (value.equals("DateInstalled")) {
			this.clearField(DateInstalled);
		} else if (value.equals("AccessHours")) {
			this.clearField(AccessHours);
		} else if (value.equals("InstallationNotes")) {
			this.clearField(InstallationNotes);
		} else if (value.equals("Search")) {
			this.clearField(Search);
		} else if (value.equals("Previous")) {
			this.mouseActionClick(Previous);
			this.mouseActionClick(Previous);
			this.mouseActionClick(Previous);
		} else if (value.equals("TaxNumber")) {
			this.clearField(TaxNumber);
		}

	}

	public String errorMessage() {
		if (!this.conditionChecking1(ErrorMessage)) {
			do {
				this.mouseActionClick(SaveComplete);
				this.invisible(Spinner);
			} while (!this.conditionChecking1(ErrorMessage));
		}
		return this.getText(ErrorMessage);
	}

	public void firstNameValidation(String value) throws IOException {
		if (value.equals("MandatoryValidation")) {
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("MaxValidation")) {
			this.validationTab(FirstName, characters256);
		}
	}

	public void lastNameValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LastName, characters256);
		}
	}

	public void jobTittleValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(JobTittle, characters256);
		}
	}

	public void emailValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Email, characters256);
		} else if (value.equals("UniqueEmail")) {
			String text2 = this.getText(ListEmail);
			this.mouseActionClick(AddContact);
			this.validationTab(Email, text2);
		} else if (value.equals("ValidEmail")) {
			this.validationTab(Email, "dsfdsfdsfds");
		}

	}

	public void phoneNumberValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Phone, maxPhoneNumber);
		} else if (value.equals("MinValidation")) {
			this.validationTab(Phone, minPhoneNumber);
		} else if (value.equals("ValidPhoneNumber")) {
			this.validationTab(Phone, "dsfdsfdsfds");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(Phone, "!@#$%^&*");
		}
	}

	public void taxNumber(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(TaxNumber, characters2048);
		}
	}

	public void propertyFirstNameValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyFirstName, characters256);
		}

	}

	public void propertyLastNameValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyLastName, characters256);
		}
	}

	public void propertyNameValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyName, characters256);
		}
	}

	public void propertyAddress1Validation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Address1, characters256);
		}
	}

	public void propertyAddress2Validation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Address2, characters256);
		}
	}

	public void propertyCityValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(CityName, characters256);
		}
	}

	public void propertyStateValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(StateName, characters256);
		}
	}

	public void propertyZipcodeValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Zipcode, maxPhoneNumber);
		} else if (value.equals("MinValidation")) {
			this.validationTab(Zipcode, minPhoneNumber);
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(Zipcode, "!@#$");
		}
	}

	public void productNameValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(ProductName, characters256);
		}
	}

	public void brandNameValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(BrandName, characters256);
		}
	}

	public void modelNumberValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(ModelNumber, characters256);
		}
	}

	public void serialNumberValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(SerialNumber, characters256);
		}
	}

	public void dateInstalledValidation(String value) {
		if (value.equals("MaxValidation")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			String currentDate = sdf.format(cal.getTime());
			this.validationTab(DateInstalled, currentDate);
			this.mouseActionClick(SaveComplete);
		}
	}

	public void accessHoursValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(AccessHours, characters256);
		}
	}

	public void installationNotesValidation(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(InstallationNotes, characters2048);
		}
	}

	static String responseMessage;

	public String responseMessage(String value) throws IOException, InterruptedException {
		Boolean check = true;
		if (value.equals("CustomerCreate")) {
			if (this.conditionChecking(Message)) {
				responseMessage = this.getText(Message);
				this.invisible(Message);
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(SaveComplete);
					if (this.conditionChecking(Message)) {
						responseMessage = this.getText(Message);
						this.invisible(Message);
						if (responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))
								|| responseMessage.equals(getPropertyValue("CustomerUpdatedMesssage"))
								|| responseMessage.equals(getPropertyValue("DateMessage"))) {
							check = false;
						}
					}
				} while (check);
			}
		} else if (value.equals("AlternateFunction")) {
			if (responseMessage.equals(getPropertyValue("ContactEmailAlreadyMessage"))) {
				this.mouseActionClick(Contact);
				this.visibility(CustomerList);
			}
		}
		return responseMessage;

	}

	public void clearAllFields(String value) {
		if (value.equals("ContactPage")) {
			List<String> asList = Arrays.asList("FirstName", "LastName", "Email", "JobTittle", "TaxNumber",
					"PhoneNumber");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		} else if (value.equals("PropertyPage")) {
			List<String> asList = Arrays.asList("PropertyFirstName", "PropertyLastName", "PropertyName",
					"PropertyAddress1", "PropertyAddress2", "City", "State", "Zipcode");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		} else if (value.equals("EquipmentPage")) {
			List<String> asList = Arrays.asList("ProductName", "BrandName", "ModelNumber", "SerialNumber",
					"DateInstalled", "AccessHours", "InstallationNotes");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		}
	}

	public void propertyPage() {
		this.inputText(PropertyFirstName, fakeFirstName);
		propertyFirstName = this.getTextAttribute(PropertyFirstName);
		this.inputText(PropertyLastName, fakeLastName);
		propertyLastName = this.getTextAttribute(PropertyLastName);
		this.inputText(PropertyName, fakeCompanyName);
		propertyName = this.getTextAttribute(PropertyName);
		this.inputText(Address1, fakeAddress1);
		address1 = this.getTextAttribute(Address1);
		this.inputText(Address2, fakeAddress2);
		address2 = this.getTextAttribute(Address2);
		this.inputText(StateName, fakeState);
		stateName = this.getTextAttribute(StateName);
		this.inputText(CityName, fakeCity);
		cityName = this.getTextAttribute(CityName);
		this.inputText(Zipcode, fakeZipcode);
		zipcode = this.getTextAttribute(Zipcode);
		this.nextButton();

	}

	public void equipmentPage(String value) throws InterruptedException, IOException {
		this.inputText(ProductName, fakeProductName);
		productName = this.getTextAttribute(ProductName);
		this.inputText(BrandName, fakeBrandName);
		brandName = this.getTextAttribute(BrandName);
		this.inputText(ModelNumber, fakeFaxNumber);
		modelNumber = this.getTextAttribute(ModelNumber);
		this.inputText(SerialNumber, maxPhoneNumber);
		serialNumber = this.getTextAttribute(SerialNumber);
		this.currentDate();
		dateInstalled = this.getTextAttribute(DateInstalled);
		this.dropDownByIndex(WarrantyInformation, 1);
		warrantyInformation = this.getTextAttribute(WarrantyInformation);
		this.inputText(AccessHours, "8hrs");
		accessHours = this.getTextAttribute(AccessHours);
		this.inputText(InstallationNotes, getPropertyValue("Notes"));
		installationNotes = this.getTextAttribute(InstallationNotes);
		if (value.equals("SaveComplete")) {
			this.mouseActionClick(SaveComplete);
		} else {
			this.nextButton();
		}
	}

	public void contactPage() throws AWTException, InterruptedException, IOException {
		this.clearFields("FirstName");
		this.inputText(FirstName, fakeFirstName);
		firstName = this.getTextAttribute(FirstName);
		this.inputText(LastName, fakeLastName);
		lastName = this.getTextAttribute(LastName);
		this.inputText(JobTittle, fakeTittle);
		jobTittle = this.getTextAttribute(JobTittle);
		this.scrollDown();
		this.inputText(Email, fakeEmail);
		email = this.getTextAttribute(Email);
		this.inputText(TaxNumber, maxPhoneNumber);
		taxNumber = this.getTextAttribute(TaxNumber);
		this.inputText(Phone, fakePhoneNumber);
		phoneNumber = this.getTextAttribute(Phone);
		this.mouseActionClick(LeadSources);
		if (this.getText(Social).equals("No Data Found")) {
			do {
				Thread.sleep(5000);
				this.mouseActionClick(LeadSources);
			} while (this.getText(Social).equals("No Data Found"));
			this.mouseActionClick(Social);
		} else {
			this.mouseActionClick(Social);
		}
		leadSources = this.getTextAttribute(LeadSources);
		this.mouseActionClick(IndustryType);
		if (this.getText(IndustryTypeDropDown).equals("No Data Found")) {
			do {
				Thread.sleep(5000);
				this.mouseActionClick(IndustryType);
			} while (!this.conditionChecking1(FirstIndusrty));
			this.mouseActionClick(IndustryTypeDropDown);
		} else {
			this.mouseActionClick(IndustryTypeDropDown);
		}
		IndustryTypes = this.getTextAttribute(IndustryType);
		this.nextButton();

	}

	HttpURLConnection connection;
	List<String> list;

	public void attachmentFileCheck(String value)
			throws AWTException, MalformedURLException, IOException, InterruptedException {
		if (value.equals("URLCheck")) {
			this.mouseActionClick(Attachment);
			BaseClass.attachmentFile(System.getProperty("user.dir") + "\\ImagePicture\\Free_Test_Data_1MB_PDF.pdf");
			if (!this.conditionChecking1(FirstAttachment)) {
				do {
					this.mouseActionClick(Attachment);
					BaseClass.attachmentFile(
							System.getProperty("user.dir") + "\\ImagePicture\\Free_Test_Data_1MB_PDF.pdf");
				} while (!this.conditionChecking1(FirstAttachment));
			}
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("CheckResponse") || value.equals("LoopNext")) {
			if (value.equals("LoopNext")) {
				for (int i = 0; i < 3; i++) {
					this.nextButton();
				}
			}
			this.mouseActionClick(FirstAttachment);
			Set<String> windowHandles = driver.getWindowHandles();
			list = new ArrayList<String>(windowHandles);
			driver.switchTo().window(list.get(1));
			String currentUrl = driver.getCurrentUrl();
			connection = (HttpURLConnection) new URL(currentUrl).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
		} else if (value.equals("ParentWindow")) {
			driver.switchTo().window(list.get(0));
		}
	}

	public int responseCode() throws IOException {
		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			return responseCode;
		} else {
			return responseCode;
		}
	}

	public void nextButton() {
		Boolean condition = true;
		this.mouseActionClick(Next);
		if (!this.conditionChecking1(SubPageVisible)) {
			do {
				this.mouseActionClick(Next);
				if (this.conditionChecking1(SubPageVisible)) {
					condition = false;
				}
			} while (condition);
		}

	}

	public String prepopulationFields(String value) {
		if (value.equals("FirstName")) {
			String data = this.getTextAttribute(FirstName);
			return data;
		} else if (value.equals("LastName")) {
			String data = this.getTextAttribute(LastName);
			return data;
		} else if (value.equals("LeadSources")) {
			String data = this.getTextAttribute(LeadSources);
			return data;
		} else if (value.equals("IndustryType")) {
			String data = this.getTextAttribute(IndustryType);
			return data;
		} else if (value.equals("JobTittle")) {
			String data = this.getTextAttribute(JobTittle);
			return data;
		} else if (value.equals("Email")) {
			String data = this.getTextAttribute(Email);
			return data;
		} else if (value.equals("PhoneNumber")) {
			String data = this.getTextAttribute(Phone);
			return data;
		} else if (value.equals("PropertyFirstName")) {
			String data = this.getTextAttribute(PropertyFirstName);
			return data;
		} else if (value.equals("PropertyLastName")) {
			String data = this.getTextAttribute(PropertyLastName);
			return data;
		} else if (value.equals("PropertyName")) {
			String data = this.getTextAttribute(PropertyName);
			return data;
		} else if (value.equals("LocationAddress1")) {
			String data = this.getTextAttribute(Address1);
			return data;
		} else if (value.equals("LocationAddress2")) {
			String data = this.getTextAttribute(Address2);
			return data;
		} else if (value.equals("LocationCity")) {
			String data = this.getTextAttribute(CityName);
			return data;
		} else if (value.equals("LocationState")) {
			String data = this.getTextAttribute(StateName);
			return data;
		} else if (value.equals("LocationZipcode")) {
			String data = this.getTextAttribute(Zipcode);
			return data;
		} else if (value.equals("ProductName")) {
			String data = this.getTextAttribute(ProductName);
			return data;
		} else if (value.equals("BrandName")) {
			String data = this.getTextAttribute(BrandName);
			return data;
		} else if (value.equals("ModelNumber")) {
			String data = this.getTextAttribute(ModelNumber);
			return data;
		} else if (value.equals("SerialNumber")) {
			String data = this.getTextAttribute(SerialNumber);
			return data;
		} else if (value.equals("DateInstalled")) {
			String data = this.getTextAttribute(DateInstalled);
			return data;
		} else if (value.equals("WarrantyInformation")) {
			String data = this.getTextAttribute(WarrantyInformation);
			return data;
		} else if (value.equals("AccessHours")) {
			String data = this.getTextAttribute(AccessHours);
			return data;
		} else if (value.equals("InstallationNotes")) {
			String data = this.getTextAttribute(InstallationNotes);
			return data;
		} else if (value.equals("TaxNumber")) {
			String data = this.getTextAttribute(TaxNumber);
			return data;
		}
		return value;
	}
}
