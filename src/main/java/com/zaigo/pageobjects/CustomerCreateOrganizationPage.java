package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Condition;

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

public class CustomerCreateOrganizationPage extends BaseClass {

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
	String fakeProductName = faker.book().genre();
	String fakeBrandName = faker.book().author();
	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String maxPhoneNumber = RandomStringUtils.randomNumeric(25);
	WebDriver driver;
	WebDriverWait wait;

	public CustomerCreateOrganizationPage(WebDriver driver) {
		this.driver = driver;

	}

	private void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
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
		wait = new WebDriverWait(driver, 50);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

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

	private void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	private String getText(By element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;

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

	public String getText(WebElement element) {
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

	public void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void elementtobeClickable(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	By Dashboard = By.xpath("//*[text()=' Company Performance']");
	By Customer = By.id("customer-main");
	By Organization = By.id("customer-organization-menu");
	By AddOrganization = By.xpath("//*[@data-automationid='contact-creation']");
	By OrganizationName = By.id("company_name");
	By OrganizationError = By.id("company_name_error");
	By Website = By.id("website");
	By WebsiteError = By.id("website_error");
	By Address1 = By.id("line_1");
	By Address1Error = By.id("line_1_error");
	By Address2 = By.id("line_2");
	By Address2Error = By.id("line_2_error");
	By City = By.id("city");
	By CityError = By.id("city_error");
	By State = By.id("state");
	By StateError = By.id("state_error");
	By ZipCode = By.id("zipcode");
	By ZipCodeError = By.id("zipcode_error");
	By Email = By.id("email");
	By EmailError = By.id("email_error");
	By TaxNumber = By.id("tax_number");
	@FindAll({ @FindBy(xpath = "//*[@id='customer_organization_create_edit']/div[1]/div[3]/div[10]/div[2]/input[1]"),
			@FindBy(xpath = "//*[@id='customer_organization_create_edit']/div[1]/div[2]/div[10]/div[2]/input[1]") })
	WebElement LeadSource;
	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div/div");
	By PhoneNumber = By.id("phones__number__0");
	By PhoneNumberError = By.id("phones__number__0_error");
	By SaveComplete = By.id("customerdrop");
	By Next = By.xpath("//*[@data-automationid='next']");
	By Logo = By.xpath("//label[@for='company_logo']");
	By LogoError = By.id("company_logo_error");
	By MaxSizeLogoError = By.xpath("//div[text()='File Size Not Allowed More Than 2 MB']");
	By Heading = By.xpath("//a[@data-goesto='organization-view']");
//	By Social = By.xpath("//*[text()='Social']");
	@FindAll({ @FindBy(xpath = "//*[@id='lead_source-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[text()='No Data Found']") })
	WebElement Social;
	By AlreadyExistsMail = By.xpath("//*[text()='Customer with Company Email already exists']");
	By Yes = By.xpath("//*[text()='Yes']");
	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By Cancel = By.xpath("//*[@class='js-snackbar__close bold']");

	@FindAll({ @FindBy(xpath = "//*[text()='Organization Name']"), @FindBy(xpath = "//*[text()='No Result Found']"),
			@FindBy(xpath = "//*[text()='Customer Name']") })
	WebElement CustomerList;

//	By Text = By.xpath("//*[text()='Customer Name']");
	By TotalCount = By.id("Total-number-customer-count");

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

	private void currentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(DateInstalled, currentDate);

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

	static String response;

	public String responseMessage(String value) throws IOException, InterruptedException {
		Boolean check = true;
		if (value.equals("ResponseMessage")) {
			if (this.conditionChecking(Message)) {
				response = this.getText(Message);
				this.invisible(Message);
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(SaveComplete);
					if (this.conditionChecking(Message)) {
						response = this.getText(Message);
						this.invisible(Message);
						if (response.equals(getPropertyValue("CustomerCreatedMessage"))
								|| response.equals(getPropertyValue("CustomerUpdatedMesssage"))
								|| response.equals(getPropertyValue("DateMessage"))) {
							check = false;
						}
					}
				} while (check);
			}
		} else if (value.equals("AlternateFunction")) {
			if (response.equals(getPropertyValue("CompanyAlreadyMessage"))
					|| response.equals(getPropertyValue("CompanyEmailAlreadyMessage"))
					|| response.equals(getPropertyValue("ContactEmailAlreadyMessage"))
					|| response.equals(getPropertyValue("CompanyContact2EmailMessage"))
					|| response.equals(getPropertyValue("CompanyContact3EmailMessage"))
					|| response.equals(getPropertyValue("CompanyEmailContact1Email"))
					|| response.equals(getPropertyValue("CompanyEmailContact2Email"))
					|| response.equals(getPropertyValue("CompanyEmailContact3Email"))) {
				this.mouseActionClick(Organization);
				this.visibility(CustomerList);
			}
		}
		return response;
	}

	static int parseInt;

	public int getCount() {
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOfElementLocated(TotalCount)).getText();
		parseInt = Integer.parseInt(text2);
		return parseInt;

	}

	public int actualResult() {
		int a = parseInt + 1;
		return a;
	}

	By CustomerOrganization = By.xpath("//*[@data-goesto='user-contractor-list']");

	public int totalCount() throws InterruptedException {
		this.visibility(CustomerList);
		String text2 = this.getText(TotalCount);
		int parseInt = Integer.parseInt(text2);
		return parseInt;
	}

	public String modulePage() throws InterruptedException, AWTException {
		this.mouseActionClick(Customer);
		this.elementtobeClickable(SearchButton);
		this.visibility(CustomerList);
		this.mouseActionClick(Organization);
		String text2 = this.getText(CustomerOrganization);
		this.elementtobeClickable(SearchButton);
		this.visibility(CustomerList);
		this.getCount();
		this.mouseActionClick(AddOrganization);
		return text2;
	}

	public void backButton() {
		this.mouseActionClick(Heading);
		this.mouseActionClick(Yes);

	}

	public void nextButton() {
		Boolean condition = true;
		this.mouseActionClick(Next);
		if (!this.conditionChecking1(Visible)) {
			do {
				this.mouseActionClick(Next);
				if (this.conditionChecking1(Visible)) {
					condition = false;
				}
			} while (condition);
		}

	}

	public void loopPreviousButton() {
		for (int i = 1; i < 4; i++) {
			this.mouseActionClick(Previous);
		}

	}

	@FindAll({
			@FindBy(xpath = "//*[contains(@class,'fieldy-tab-active')]//parent::div//input[@id='contacts__first_name__0']"),
			@FindBy(xpath = "//*[contains(@class,'fieldy-tab-active')]//parent::div//input[@id='addresses__contact_person_first_name__0']"),
			@FindBy(xpath = "//*[contains(@class,'fieldy-tab-active')]//parent::div//input[@id='equipments__product_name__0']") })
	WebElement Visible;
	By FirstName = By.id("contacts__first_name__0");
	By LastName = By.id("contacts__last_name__0");
	By ContactEmail = By.id("contacts__email__0");
	By ContactEmail2 = By.id("contacts__email__1");
	By ContactEmail3 = By.id("contacts__email__2");
	By ContactPhoneNumber = By.id("contacts__phone__0");
	By JobTittle = By.id("contacts__job_title__0");
	By AddMoreContact = By.id("add-more-contact-customer-organization");
	By FirstNameError = By.id("contacts__first_name__0_error");
	By LastNameError = By.id("contacts__last_name__0_error");
	By ContactEmailError = By.id("contacts__email__0_error");
	By ContactPhoneNumberError = By.id("contacts__phone__0_error");
	By JobTittleError = By.id("contacts__job_title__0_error");
	By MakethisProperty = By.id("addresses__is_primary__0");
	By PropertyName = By.id("addresses__location_name__0");
	By PropertyFirstName = By.id("addresses__contact_person_first_name__0");
	By PropertyLastName = By.id("addresses__contact_person_last_name__0");
	By ContactPersonName = By.id("addresses__name__0");
	By PropertyAddress1 = By.id("addresses__line_1__0");
	By PropertyAddress2 = By.id("addresses__line_2__0");
	By PropertyStateName = By.id("addresses__state__0");
	By PropertyCityName = By.id("addresses__city__0");
	By PropertyZipcode = By.id("addresses__zipcode__0");

	By DeleteLocation = By.xpath("//div[@class='accordion-body']//child::div[text()='Delete Property']");
	By AddProperty = By.id("add-more-property-customer-organization");

	By ErrorPropertyName = By.id("addresses__location_name__0_error");
	By ErrorContactPersonName = By.id("addresses__name__0_error");
	By ErrorPropertyFirstName = By.id("addresses__contact_person_first_name__0_error");
	By ErrorPropertyLastName = By.id("addresses__contact_person_last_name__0_error");
	By ErrorPropertyAddress1 = By.id("addresses__line_1__0_error");
	By ErrorPropertyAddress2 = By.id("addresses__line_2__0_error");
	By ErrorPropertyStreetName = By.id("addresses__line_2__0_error");
	By ErrorPropertyStateName = By.id("addresses__state__0_error");
	By ErrorPropertyCityName = By.id("addresses__city__0_error");
	By ErrorPropertyZipCode = By.id("addresses__zipcode__0_error");

	By Previous = By.xpath("//*[text()='Previous']");

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
	By ErrorDateInstalled = By.xpath("//*[text()='DATE_INSTALLED 1: date_installed exceeds current_date limit']");
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'in-validate')]//following-sibling::div[3]"),
			@FindBy(xpath = "//*[contains(@class,'in-validate')]//following-sibling::div[1]") })
	WebElement ErrorMessage;

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}

	public static String organizationName;
	public static String website;
	public static String address1;
	public static String address2;
	public static String city;
	public static String state;
	public static String zipCode;
	public static String email;
	public static String phoneNumber;
	public static String taxNumber;
	public static String leadSource;

	public static String firstName;
	public static String lastName;
	public static String jobTittle;
	public static String contactEmail;
	public static String contactPhoneNumber;

	public static String propertyFirstName;
	public static String propertyLastName;
	public static String propertyName;
	public static String propertyAddress1;
	public static String propertyAddress2;
	public static String propertyCityName;
	public static String propertyStateName;
	public static String propertyZipcode;

	public static String productName;
	public static String brandName;
	public static String modelNumber;
	public static String serialNumber;
	public static String dateInstalled;
	public static String warrantyInformation;
	public static String accessHours;
	public static String installationNotes;

	public void organizationPage() throws InterruptedException, AWTException {
		this.clearField(OrganizationName);
		this.inputText(OrganizationName, fakeCompanyName);
		organizationName = this.getTextAttribute(OrganizationName);
		this.inputText(Website, fakeWebsite);
		website = this.getTextAttribute(Website);
		this.inputText(Address1, fakeAddress1);
		address1 = this.getTextAttribute(Address1);
		this.inputText(Address2, fakeAddress2);
		address2 = this.getTextAttribute(Address2);
		this.inputText(City, fakeCity);
		city = this.getTextAttribute(City);
		this.inputText(State, fakeState);
		state = this.getTextAttribute(State);
		this.scrollDown();
		this.inputText(ZipCode, fakeZipcode);
		zipCode = this.getTextAttribute(ZipCode);
		this.inputText(TaxNumber, maxPhoneNumber);
		taxNumber = this.getTextAttribute(TaxNumber);
		this.inputText(Email, fakeEmail);
		email = this.getTextAttribute(Email);
		this.mouseActionClick(LeadSource);
		if (this.getText(Social).equals("No Data Found")) {
			Thread.sleep(5000);
			this.mouseActionClick(LeadSource);
			this.mouseActionClick(Social);
		} else {
			this.mouseActionClick(Social);
		}
		leadSource = this.getTextAttribute(LeadSource);
		this.inputText(PhoneNumber, fakePhoneNumber);
		phoneNumber = this.getTextAttribute(PhoneNumber);
		this.nextButton();

	}

	public void contactPage(String value) {
		if (value.equals("CreateContact")) {
			for (int i = 0; i < 3; i++) {
				Faker faker = new Faker(new Locale("en-IND"));
				String fakeFirstName = faker.name().firstName();
				String fakeLastName = faker.name().lastName();
				String fakeEmail = faker.internet().safeEmailAddress();
				String fakePhoneNumber = faker.phoneNumber().phoneNumber();
				String fakeTittle = faker.name().title();
				By FirstName = By.id("contacts__first_name__" + i);
				By LastName = By.id("contacts__last_name__" + i);
				By ContactEmail = By.id("contacts__email__" + i);
				By ContactPhoneNumber = By.id("contacts__phone__" + i);
				By JobTittle = By.id("contacts__job_title__" + i);
				By AddMoreContact = By.id("add-more-contact-customer-organization");
				this.inputText(FirstName, fakeFirstName);
				this.inputText(LastName, fakeLastName);
				this.inputText(ContactEmail, fakeEmail);
				this.inputText(ContactPhoneNumber, fakePhoneNumber);
				this.inputText(JobTittle, fakeTittle);
				this.mouseActionClick(AddMoreContact);
				this.scrollDown();
			}
			firstName = this.getTextAttribute(FirstName);
			lastName = this.getTextAttribute(LastName);
			contactEmail = this.getTextAttribute(ContactEmail);
			contactPhoneNumber = this.getTextAttribute(ContactPhoneNumber);
			jobTittle = this.getTextAttribute(JobTittle);
			this.nextButton();
		} else if (value.equals("EditContact")) {
			Faker faker = new Faker(new Locale("en-IND"));
			String fakeFirstName = faker.name().firstName();
			String fakeLastName = faker.name().lastName();
			String fakeEmail = faker.internet().safeEmailAddress();
			String fakePhoneNumber = faker.phoneNumber().phoneNumber();
			String fakeTittle = faker.name().title();
			this.inputText(FirstName, fakeFirstName);
			this.inputText(LastName, fakeLastName);
			this.inputText(ContactEmail, fakeEmail);
			this.inputText(ContactPhoneNumber, fakePhoneNumber);
			this.inputText(JobTittle, fakeTittle);
			this.nextButton();
		}
	}

	public void propertyPage() {
		this.inputText(PropertyFirstName, fakeFirstName);
		propertyFirstName = this.getTextAttribute(PropertyFirstName);
		this.inputText(PropertyLastName, fakeLastName);
		propertyLastName = this.getTextAttribute(PropertyLastName);
		this.inputText(PropertyName, fakeCompanyName);
		propertyName = this.getTextAttribute(PropertyName);
		this.inputText(PropertyAddress1, fakeAddress1);
		propertyAddress1 = this.getTextAttribute(PropertyAddress1);
		this.inputText(PropertyAddress2, fakeAddress2);
		propertyAddress2 = this.getTextAttribute(PropertyAddress2);
		this.inputText(PropertyCityName, fakeCity);
		propertyCityName = this.getTextAttribute(PropertyCityName);
		this.inputText(PropertyStateName, fakeState);
		propertyStateName = this.getTextAttribute(PropertyStateName);
		this.inputText(PropertyZipcode, fakeZipcode);
		propertyZipcode = this.getTextAttribute(PropertyZipcode);
		this.nextButton();

	}

	public void equipmentPage() throws InterruptedException, IOException {
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
		this.mouseActionClick(SaveComplete);
	}

	By ListFirstName = By.xpath("//*[@id='fieldy-customer-organization-list_aserpttbl']/tbody/tr[2]/td[2]/span/a");
	By Search = By.id("customer-organization-search-box");
	By ListPhoneNumber = By.xpath("//*[@id='fieldy-customer-organization-list_aserpttbl']/tbody/tr[2]/td[4]/a");
	By ListEmail = By.xpath("//*[@id='fieldy-customer-organization-list_aserpttbl']/tbody/tr[2]/td[5]/a");
	By Filter = By.xpath("//*[@id='customer-organization-timeline']/div/div[1]/div[4]/button/div");
	By LeadSourceCheckBox = By.xpath("//*[@id='customer-organization-lead-source-div']//div[1]//div[1]//input[1]");
	By ListLeadSource = By.id("customer-organization-lead-input-place");
	By Status = By.id("customer-contact-status-active");
	By Apply = By.xpath("//*[@id='customer-organization-timeline']/div/div[2]/div/div/div/div[3]/button");
	@FindAll({ @FindBy(id = "customer-organization-search-enter"), @FindBy(id = "customer-contact-search-button") })
	WebElement SearchButton;
	By Invalid = By.xpath("//*[text()='No Result Found']");
	By ListLead = By.xpath("//*[@id='fieldy-customer-organization-list_aserpttbl']//tr[2]//td[7]//span");

	static String listValue;

	public String listValidation(String value) {
		if (value.equals("SearchField")) {
			this.tagValidation(Search, listValue);
			return listValue;
		} else if (value.equals("OrganizationName")) {
			listValue = this.getText(ListFirstName);
			return listValue;
		} else if (value.equals("PhoneNumber")) {
			listValue = this.getText(ListPhoneNumber);
			return listValue;
		} else if (value.equals("Email")) {
			listValue = this.getText(ListEmail);
			return listValue;
		} else if (value.equals("AlphabetFilter")) {
			By Alpbabet = By.xpath("//*[@data-filteralphaorg='" + listValue + "']");
			this.mouseActionClick(Alpbabet);
			listValue = this.getText(ListFirstName);
			return listValue;
		} else if (value.equals("CharacterFirstName")) {
			String str = this.getText(ListFirstName);
			char first = str.charAt(0);
			listValue = String.valueOf(first);
			return listValue;
		} else if (value.equals("Filter")) {
			this.mouseActionClick(Filter);
			this.mouseActionClick(ListLeadSource);
			this.mouseActionClick(LeadSourceCheckBox);
			this.dropDownByIndex(Status, 1);
			this.mouseActionClick(Apply);
		} else if (value.equals("LeadSource")) {
			listValue = this.getText(ListLead);
			return listValue;
		} else if (value.equals("InvalidSearch")) {
			this.tagValidation(Search, "fshfskjh");
		} else if (value.equals("Invalid")) {
			listValue = this.getText(Invalid);
			return listValue;
		}
		return value;
	}

	By Dots = By.xpath("//*[@id='fieldy-customer-organization-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]");
	By Edit = By.xpath("//*[@id='fieldy-customer-organization-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[1]");
	By Update = By.xpath("//*[text()='Customer details updated successfully']");
	By DeletedMessage = By.xpath("//*[text()='Customer deleted successfully']");
	By Deleted = By
			.xpath("//*[@id='fieldy-customer-organization-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]");
	By reset = By.xpath("//*[@onclick=\"generateCustomerOrganizationTable('','','','','reset')\"]");

	public void resetOption() {
		this.mouseActionClick(reset);

	}

	public void deleteContact() {
		this.mouseActionClick(Dots);
		this.mouseActionClick(Deleted);
		this.mouseActionClick(Yes);

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

	public void clearFields(String value) {
		if (value.equals("OrganizationName")) {
			this.clearField(OrganizationName);
		} else if (value.equals("Website")) {
			this.clearField(Website);
		} else if (value.equals("Address1")) {
			this.clearField(Address1);
		} else if (value.equals("Address2")) {
			this.clearField(Address2);
		} else if (value.equals("City")) {
			this.clearField(City);
		} else if (value.equals("State")) {
			this.clearField(State);
		} else if (value.equals("Zipcode")) {
			this.clearField(ZipCode);
		} else if (value.equals("Email")) {
			this.clearField(Email);
		} else if (value.equals("PhoneNumber")) {
			this.clearField(PhoneNumber);
		} else if (value.equals("ContactFirstName")) {
			this.clearField(FirstName);
		} else if (value.equals("ContactLastName")) {
			this.clearField(LastName);
		} else if (value.equals("ContactEmail")) {
			this.clearField(ContactEmail);
		} else if (value.equals("ContactPhoneNumber")) {
			this.clearField(ContactPhoneNumber);
		} else if (value.equals("ContactJobTittle")) {
			this.clearField(JobTittle);
		} else if (value.equals("PropertyFirstName")) {
			this.clearField(PropertyFirstName);
		} else if (value.equals("PropertyLastName")) {
			this.clearField(PropertyLastName);
		} else if (value.equals("PropertyName")) {
			this.clearField(PropertyName);
		} else if (value.equals("PropertyAddress1")) {
			this.clearField(PropertyAddress1);
		} else if (value.equals("PropertyAddress2")) {
			this.clearField(PropertyAddress2);
		} else if (value.equals("PropertyCity")) {
			this.clearField(PropertyCityName);
		} else if (value.equals("PropertyState")) {
			this.clearField(PropertyStateName);
		} else if (value.equals("PropertyZipcode")) {
			this.clearField(PropertyZipcode);
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
		} else if (value.equals("TaxNumber")) {
			this.clearField(TaxNumber);
		}

	}

	public String organizationName(String value) {
		if (value.equals("MandatoryValidation")) {
			this.mouseActionClick(SaveComplete);
			if (conditionChecking1(OrganizationError)) {
			} else {
				do {
					this.mouseActionClick(SaveComplete);
				} while (!this.conditionChecking1(OrganizationError));
			}
		} else if (value.equals("UniqueValidation")) {
			String text2 = this.getText(ListFirstName);
			this.mouseActionClick(AddOrganization);
			this.validationTab(OrganizationName, text2);
			return text2;
		} else if (value.equals("MaxValidation")) {
			this.validationTab(OrganizationName, characters256);
		} else if (value.equals("Input")) {
			this.inputText(OrganizationName, fakeCompanyName);
		}
		return value;
	}

	public void website(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Website, characters2048);
		}

	}

	public void address1(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Address1, characters256);
		}

	}

	public void address2(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Address2, characters256);
		}
	}

	public void city(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(City, characters256);
		}
	}

	public void state(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(State, characters256);
		}
	}

	public void taxNumber(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(TaxNumber, characters2048);
		}
	}

	public void zipCode(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(ZipCode, "12345678909876543");
		} else if (value.equals("MinValidation")) {
			this.validationTab(ZipCode, "12");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(ZipCode, "!@#$");
		}
	}

	public void email(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Email, characters256);
		} else if (value.equals("UniqueEmail")) {
			String text2 = this.getText(ListEmail);
			this.mouseActionClick(AddOrganization);
			this.validationTab(Email, text2);
		} else if (value.equals("ValidEmail")) {
			this.validationTab(Email, "dsfdsfdsfds");
		}

	}

	public void phoneNumber(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PhoneNumber, "23111231313123131313123131313132131");
		} else if (value.equals("MinValidation")) {
			this.validationTab(PhoneNumber, "2132");
		} else if (value.equals("ValidPhoneNumber")) {
			this.validationTab(PhoneNumber, "dsfdsfdsfds");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(PhoneNumber, "!@#$%^&*");
		}

	}

	public void firstName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(FirstName, characters256);
		}

	}

	public void lastName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LastName, characters256);
		}

	}

	public void jobTittle(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(JobTittle, characters256);
		}

	}

	public void contactEmail(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(ContactEmail, characters256);
		} else if (value.equals("UniqueEmail")) {
			this.validationTab(ContactEmail, "fieldy@mailinator.com");
		} else if (value.equals("ValidEmail")) {
			this.validationTab(ContactEmail, "dsfdsfdsfds");
		}

	}

	public void contactPhoneNumber(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(ContactPhoneNumber, "1234567890987654345678765476545676");
		} else if (value.equals("MinValidation")) {
			this.validationTab(ContactPhoneNumber, "123");
		} else if (value.equals("ValidPhoneNumber")) {
			this.validationTab(ContactPhoneNumber, "dsfdsfdsfds");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(ContactPhoneNumber, "!@#$%^&*");
		}

	}

	public void propertyFirstName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyFirstName, characters256);
		}

	}

	public void propertyLastName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyLastName, characters256);
		}
	}

	public void propertyName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyName, characters256);
		}
	}

	public void propertyAddress1(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyAddress1, characters256);
		}
	}

	public void propertyAddress2(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyAddress2, characters256);
		}
	}

	public void propertyCity(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyCityName, characters256);
		}
	}

	public void propertyState(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyStateName, characters256);
		}
	}

	public void propertyZipcode(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(PropertyZipcode, "97845623198456231985623198562");
		} else if (value.equals("MinValidation")) {
			this.validationTab(PropertyZipcode, "12");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(PropertyZipcode, "!@#$");
		}
	}

	public void productName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(ProductName, characters256);
		}
	}

	public void brandName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(BrandName, characters256);
		}
	}

	public void modelNumber(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(ModelNumber, characters256);
		}
	}

	public void serialNumber(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(SerialNumber, characters256);
		}
	}

	public void dateInstalled(String value) {
		if (value.equals("MaxValidation")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			String currentDate = sdf.format(cal.getTime());
			this.validationTab(DateInstalled, currentDate);
			this.elementtobeClickable(SaveComplete);
			this.mouseActionClick(SaveComplete);
		}
	}

	public void accessHours(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(AccessHours, characters256);
		}
	}

	public void installationNotes(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(InstallationNotes, characters2048);
		}
	}

	static String ListContactName;
	By FormLabel = By.xpath("//*[@data-goesto='organization-view']");

	public String LabelValidation(String value) throws AWTException, InterruptedException {
		if (value.equals("Create")) {
			String text2 = this.getText(FormLabel);
			return text2;
		} else if (value.equals("Edit")) {
			ListContactName = this.getText(ListFirstName);
			this.mouseActionClick(Dots);
			this.mouseActionClick(Edit);
			String text2 = this.getText(FormLabel);
			return text2;
		}
		return value;

	}

	public void visibleName() {
		this.valuePresent(OrganizationName, ListContactName);
	}

	public void clearAllFields(String value) {
		if (value.equals("OrganizationPage")) {
			List<String> asList = Arrays.asList("OrganizationName", "Website", "Address1", "Address2", "City", "State",
					"Zipcode", "Email", "TaxNumber", "PhoneNumber");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		} else if (value.equals("ContactPage")) {
			List<String> asList = Arrays.asList("ContactFirstName", "ContactLastName", "ContactEmail",
					"ContactPhoneNumber", "ContactJobTittle");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		} else if (value.equals("PropertyPage")) {
			List<String> asList = Arrays.asList("PropertyFirstName", "PropertyLastName", "PropertyName",
					"PropertyAddress1", "PropertyAddress2", "PropertyCity", "PropertyState", "PropertyZipcode");
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

	public String prepopulationFields(String value) {
		if (value.equals("FirstName")) {
			String data = this.getTextAttribute(FirstName);
			return data;
		} else if (value.equals("LastName")) {
			String data = this.getTextAttribute(LastName);
			return data;
		} else if (value.equals("OrganizationName")) {
			String data = this.getTextAttribute(OrganizationName);
			return data;
		} else if (value.equals("Website")) {
			String data = this.getTextAttribute(Website);
			return data;
		} else if (value.equals("Address1")) {
			String data = this.getTextAttribute(Address1);
			return data;
		} else if (value.equals("Address2")) {
			String data = this.getTextAttribute(Address2);
			return data;
		} else if (value.equals("City")) {
			String data = this.getTextAttribute(City);
			return data;
		} else if (value.equals("State")) {
			String data = this.getTextAttribute(State);
			return data;
		} else if (value.equals("Zipcode")) {
			String data = this.getTextAttribute(ZipCode);
			return data;
		} else if (value.equals("LeadSources")) {
			String data = this.getTextAttribute(LeadSource);
			return data;
		} else if (value.equals("PhoneNumber")) {
			String data = this.getTextAttribute(PhoneNumber);
			return data;
		} else if (value.equals("JobTittle")) {
			String data = this.getTextAttribute(JobTittle);
			return data;
		} else if (value.equals("Email")) {
			String data = this.getTextAttribute(Email);
			return data;
		} else if (value.equals("ContactEmail")) {
			String data = this.getTextAttribute(ContactEmail);
			return data;
		} else if (value.equals("ContactPhoneNumber")) {
			String data = this.getTextAttribute(ContactPhoneNumber);
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
			String data = this.getTextAttribute(PropertyAddress1);
			return data;
		} else if (value.equals("LocationAddress2")) {
			String data = this.getTextAttribute(PropertyAddress2);
			return data;
		} else if (value.equals("LocationCity")) {
			String data = this.getTextAttribute(PropertyCityName);
			return data;
		} else if (value.equals("LocationState")) {
			String data = this.getTextAttribute(PropertyStateName);
			return data;
		} else if (value.equals("LocationZipcode")) {
			String data = this.getTextAttribute(PropertyZipcode);
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
