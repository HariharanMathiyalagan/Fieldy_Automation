package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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

	public CustomerCreateContactPage(WebDriver driver) {
		this.driver = driver;
	}

	By DashBoard = By.xpath("//*[text()=' Company Performance']");
	By Today = By.xpath("(//div[@class='mb-2']//parent::div)[4]");
	By Customer = By.id("customer-main");
	By Contact = By.id("customer-contact-menu");
	By AddContact = By.id("scheduledrop");
	By ErrorLogo = By.id("logo_error");
	By FormatErrorLogo = By.xpath("//div[text()='Only jpg,jpeg,png Formats Allowed']");
	By CreateContactLabel = By.xpath("//*[@data-goesto='contractor-view']");
	By Yes = By.xpath("//*[text()='Yes']");
	By ResponseMessage = By.xpath("//*[@class='created_successfully d-flex d-none']");
	By ListName = By.xpath("(//a[@data-n-linkto='customer_contact_timeline'])[1]");
	By Search = By.id("customer-contact-search-value");
	By SearchButton = By.id("customer-contact-search-button");
	By InvalidList = By.xpath("//div[text()='No Result Found']");
	By TotalCount = By.id("Total-number-customer-count");

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


	@FindAll({ @FindBy(xpath = "//*[text()='Contact Name']"), @FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ContactName;
	@FindAll({ @FindBy(xpath = "//*[text()='Customer Name']"), @FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement Text;
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
			this.Displayed(ContactName);
			String text2 = this.getText(TotalCount);
			int expected = Integer.parseInt(text2);
			return expected;
		}
		return value;

	}

	By CustomerContact = By.xpath("//*[@class='page-header-left back-btn']");

	public String modulePage() throws InterruptedException, IOException {
		this.assertName(DashBoard, "Company Performance");
		this.mouseActionClick(Customer);
//		this.assertName(Text, "Customer Name");
		this.Displayed(Text);
		this.mouseActionClick(Contact);
		this.Displayed(ContactName);
		String text2 = this.getText(CustomerContact);
		this.getCount();
		this.mouseActionClick(AddContact);
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
	By LeadSources = By.xpath("//input[@data-dropdownlist='lead-source']");
	By Social = By.xpath("//*[text()='Social']");
	By AlreadyEmail = By.xpath("//span[text()='The e-mail is already exit']");
	By Next = By.xpath("//*[@data-automationid='next']");
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
	By Dots = By.xpath("(//i[@class='fa fa-ellipsis-v'])[2]");
	By Edit = By.xpath("(//li[@data-n-linkto='customer_contact_edit'])[1]");
	By Deleted = By.xpath("(//li[@data-tabformid='undefined'])[2]");
	By EditLabel = By.xpath("//*[@data-exitpopup='customer_contact']");
	By ErrorDateInstalled = By.xpath("//*[text()='DATE_INSTALLED 1: date_installed exceeds current_date limit']");
	By reset = By.xpath("//*[@onclick=\"generateCustomerContactTable('','','','','reset')\"]");
	By Status = By.id("customer-contact-status-active");
	By Filter = By.xpath("//span[@data-timeline-open='customercontact']");
	By Apply = By.xpath("//div[@class='col-lg-4 col-md-4 col-sm-6 col-12 pt-2']//child::button");
	By ListPhoneNumber = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[3]");
	By ListSocial = By.xpath("(//input[@class='mr-3'])[2]");
	By ListLeadSource = By.id("customer-contact-lead-source-search");
	By ListEmail = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[4]");
	By Cancel = By.xpath("//*[@class='js-snackbar__close bold']");

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
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
		} else if (value.equals("LeadSource")) {
			String text2 = this.getText(ListLeadSources);
			return text2;
		}
		return value;

	}

	By ListLeadSources = By.xpath("(//*[@class='false'])[5]");

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
		}

	}

	public String errorField(String value) {
		if (value.equals("FirstName")) {
			return this.getText(ErrorFirstName);
		} else if (value.equals("LastName")) {
			return this.getText(ErrorLastName);
		} else if (value.equals("Email")) {
			return this.getText(ErrorEmail);
		} else if (value.equals("JobTittle")) {
			return this.getText(ErrorJobTittle);
		} else if (value.equals("PhoneNumber")) {
			return this.getText(ErrorPhoneNo);
		} else if (value.equals("PropertyFirstName")) {
			return this.getText(ErrorPropertyFirstName);
		} else if (value.equals("PropertyLastName")) {
			return this.getText(ErrorPropertyLastName);
		} else if (value.equals("PropertyName")) {
			return this.getText(ErrorPropertyName);
		} else if (value.equals("PropertyAddress1")) {
			return this.getText(ErrorAddress1);
		} else if (value.equals("PropertyAddress2")) {
			return this.getText(ErrorAddress2);
		} else if (value.equals("City")) {
			return this.getText(ErrorCityName);
		} else if (value.equals("State")) {
			return this.getText(ErrorStateName);
		} else if (value.equals("Zipcode")) {
			return this.getText(ErrorZipCode);
		} else if (value.equals("ProductName")) {
			return this.getText(ErrorProductName);
		} else if (value.equals("BrandName")) {
			return this.getText(ErrorBrandName);
		} else if (value.equals("ModelNumber")) {
			return this.getText(ErrorModelNumber);
		} else if (value.equals("SerialNumber")) {
			return this.getText(ErrorSerialNumber);
		} else if (value.equals("DateInstalled")) {
			return this.getText(ErrorDateInstalled);
		} else if (value.equals("AccessHours")) {
			return this.getText(ErrorAccessHours);
		} else if (value.equals("InstallationNotes")) {
			return this.getText(ErrorInstallationNotes);
		}
		return value;

	}

	public void firstNameValidation(String value) {
		if (value.equals("MandatoryValidation")) {
			for (int i = 0; i <= 5; i++) {
				this.mouseActionClick(SaveComplete);
				this.mouseActionClick(Next);
				this.elementtobeClickable(SaveComplete);
			}
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

	public String responseMessage(String value) throws IOException {
		if (value.equals("CustomerCreate")) {
			responseMessage = this.getText(Message);
			this.mouseActionClick(Cancel);
			return responseMessage;
		} else if (value.equals("AlternateFunction")) {
			if (responseMessage.equals(getPropertyValue("ContactEmailAlreadyMessage"))) {
				this.mouseActionClick(Contact);
				this.visibility(ContactName);
			}

		}
		return value;

	}

	public void clearAllFields(String value) {
		if (value.equals("ContactPage")) {
			List<String> asList = Arrays.asList("FirstName", "LastName", "Email", "JobTittle", "PhoneNumber");
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
		this.inputText(PropertyLastName, fakeLastName);
		this.inputText(PropertyName, fakeCompanyName);
		this.inputText(Address1, fakeAddress1);
		this.inputText(Address2, fakeAddress2);
		this.inputText(StateName, fakeState);
		this.inputText(CityName, fakeCity);
		this.inputText(Zipcode, fakeZipcode);
		this.mouseActionClick(Next);

	}

	public void equipmentPage() throws InterruptedException {
		this.inputText(ProductName, "Samsung");
		this.inputText(BrandName, "Neo QLED TVs");
		this.inputText(ModelNumber, fakeFaxNumber);
		this.inputText(SerialNumber, "8794562155");
		this.inputText(DateInstalled, "08/25/2022");
		this.dropDownByIndex(WarrantyInformation, 1);
		this.inputText(AccessHours, "8hrs");
		this.inputText(InstallationNotes, characters256);
		Thread.sleep(2000);
		this.mouseActionClick(SaveComplete);

	}

	public void contactPage() throws AWTException, InterruptedException, IOException {
		this.clearFields("FirstName");
		this.inputText(FirstName, fakeFirstName);
		this.inputText(LastName, fakeLastName);
		this.inputText(JobTittle, fakeTittle);
		this.scrollDown();
		this.inputText(Email, fakeEmail);
		this.inputText(Phone, fakePhoneNumber);
		Thread.sleep(5000);
		this.mouseActionClick(LeadSources);
		this.mouseActionClick(Social);
		this.mouseActionClick(Next);

	}

	public void nextButton() {
		this.mouseActionClick(Next);

	}

}
