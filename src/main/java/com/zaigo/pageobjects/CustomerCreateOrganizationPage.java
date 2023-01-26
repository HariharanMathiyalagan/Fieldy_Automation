package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.locks.Condition;

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

public class CustomerCreateOrganizationPage extends BaseClass {

	String SaveCompleteButton = "Save & Complete";

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
	WebDriver driver;
	WebDriverWait wait;

	public CustomerCreateOrganizationPage(WebDriver driver) {
		this.driver = driver;

	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
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
		wait = new WebDriverWait(driver, 50);
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
	By LeadSource = By.xpath("//input[@data-dropdownlist='lead-source']");
	By PhoneNumber = By.id("phones__number__0");
	By PhoneNumberError = By.id("phones__number__0_error");
	By SaveComplete = By.id("customerdrop");
	By Next = By.xpath("//*[@data-automationid='next']");
	By Logo = By.xpath("//label[@for='company_logo']");
	By LogoError = By.id("company_logo_error");
	By MaxSizeLogoError = By.xpath("//div[text()='File Size Not Allowed More Than 2 MB']");
	By Heading = By.xpath("//a[@data-goesto='organization-view']");
	By Social = By.xpath("//*[text()='Social']");
	By AlreadyExistsMail = By.xpath("//*[text()='Customer with Company Email already exists']");
	By Yes = By.xpath("//*[text()='Yes']");
	By CreateResponseMessage = By.xpath("//*[text()='Customer created successfully']");
	By Text = By.xpath("//*[text()='Customer Name']");
	By TotalCount = By.id("Total-number-customer-count");

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

	By ContactName = By.xpath("//*[text()='Organization Name']");

	public int totalCount() throws InterruptedException {
		this.assertName(ContactName, "Organization Name");
		String text2 = this.getText(TotalCount);
		int parseInt = Integer.parseInt(text2);
		return parseInt;
	}

	public void modulePage() throws InterruptedException, AWTException {
		this.assertName(Dashboard, "Company Performance");
//		Thread.sleep(5000);
		this.mouseActionClick(Customer);
		this.assertName(Text, "Customer Name");
		this.mouseActionClick(Organization);
		this.assertName(ContactName, "Organization Name");
		this.getCount();
		this.mouseActionClick(AddOrganization);

	}

	public void mandatoryValidation() throws InterruptedException {
		Thread.sleep(5000);
		this.assertName(SaveComplete, SaveCompleteButton);
		this.mouseActionClick(SaveComplete);
	}

	public String errorMandatory() {
		String text2 = this.getText(OrganizationError);
		return text2;

	}

	public void text() {
		this.inputText(OrganizationName, "Demo");

	}

	public void maxSizeLogoValidation() throws AWTException, InterruptedException {
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("dsc00531");

	}

	public String errorLogo() {
		String text2 = this.getText(MaxSizeLogoError);
		return text2;

	}

	public void formatLogoValidation() throws InterruptedException, AWTException {
		this.assertName(Heading, "Customer / Create Organization");
		Thread.sleep(4000);
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("sample-file.pdf");

	}

	public String formatlogoError() {
		String text2 = this.getText(LogoError);
		return text2;

	}

	public void logoFile() throws InterruptedException, AWTException {

		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("1622641377484");

	}

	public void alreadyExistOrganizationName() {
		String text2 = this.getText(ListFirstName);
		this.mouseActionClick(AddOrganization);
		this.validationTab(OrganizationName, text2);
		this.mouseActionClick(Next);
		this.mouseActionClick(Previous);
	}

	public void clearOrganization() {
		this.clearField(OrganizationName);

	}

	public void backButton() {
		this.mouseActionClick(Heading);
		this.mouseActionClick(Yes);

	}

	public void maxValidationOrganization() {
		this.validationTab(OrganizationName, characters256);

	}

	public void input() {
		this.inputText(OrganizationName, "Organization");

	}

	public void maxValidationWebsite() {
		this.validationTab(Website, characters256);

	}

	public String errorWebsite() {
		String text2 = this.getText(WebsiteError);
		return text2;

	}

	public void clearWebsite() {
		this.clearField(Website);

	}

	public void maxValidationAddress1Field() {
		this.validationTab(Address1, characters256);

	}

	public String errorAddress1Field() {
		String text2 = this.getText(Address1Error);
		return text2;

	}

	public void clearAddress1Field() {
		this.clearField(Address1);

	}

	public void maxValidationAddress2Field() {
		this.validationTab(Address2, characters256);

	}

	public String errorAddress2Field() {
		String text2 = this.getText(Address2Error);
		return text2;

	}

	public void clearAddress2Field() {
		this.clearField(Address2);

	}

	public void maxValidationCityField() {
		this.validationTab(City, characters256);

	}

	public String errorCityField() {
		String text2 = this.getText(CityError);
		return text2;

	}

	public void clearCityField() {
		this.clearField(City);

	}

	public void maxValidationStateField() {
		this.scrollDown();
		this.validationTab(State, characters256);

	}

	public String errorStateField() {
		String text2 = this.getText(StateError);
		return text2;

	}

	public void clearStateField() {
		this.clearField(State);

	}

	public void minValidationZipcodeField() {
		this.validationTab(ZipCode, "12");

	}

	public String errorZipcodeField() {
		String text2 = this.getText(ZipCodeError);
		return text2;

	}

	public void clearZipcodeField() {
		this.clearField(ZipCode);

	}

	public void maxValidationZipcodeField() {
		this.validationTab(ZipCode, "21321312133131313");

	}

	public void maxValidationEmailField() {
		this.validationTab(Email, characters256);

	}

	public String errorEmail() {
		String text2 = this.getText(EmailError);
		return text2;

	}

	public void clearEmailField() {
		this.clearField(Email);

	}

	public void validEmailField() {
		this.validationTab(Email, "jshdasjdh");

	}

	public void minValidationPhoneNumberField() {
		this.validationTab(PhoneNumber, "22");

	}

	public String errorPhoneNumber() {
		String text2 = this.getText(PhoneNumberError);
		return text2;

	}

	public void clearPhoneNumber() {
		this.clearField(PhoneNumber);

	}

	public void maxValidationPhoneNumberField() {
		this.validationTab(PhoneNumber, "23121321234567654323456133321");

	}

	public void nextButton() {
		this.mouseActionClick(Next);

	}

	public void previousButton() {
		this.mouseActionClick(Previous);
		this.mouseActionClick(Previous);
		this.mouseActionClick(Previous);

	}

	public void loopNextButton() {
		for (int i = 1; i < 5; i++) {
			this.mouseActionClick(Next);
		}

	}

	By FirstName = By.id("contacts__first_name__0");
	By LastName = By.id("contacts__last_name__0");
	By ContactEmail = By.id("contacts__email__0");
	By ContactPhoneNumber = By.id("contacts__phone__0");
	By JobTittle = By.id("contacts__job_title__0");
	By AddMoreContact = By.id("add-more-contact-customer-organization");
	By FirstNameError = By.id("contacts__first_name__0_error");
	By LastNameError = By.id("contacts__last_name__0_error");
	By ContactEmailError = By.id("contacts__email__0_error");
	By ContactPhoneNumberError = By.id("contacts__phone__0_error");
	By JobTittleError = By.id("contacts__job_title__0_error");

	public void maxValidationFirstName() {
		this.validationTab(FirstName, characters256);

	}

	public String errorFirstName() {
		String text2 = this.getText(FirstNameError);
		return text2;

	}

	public void clearFirstName() {
		this.clearField(FirstName);

	}

	public void maxValidationLastName() {
		this.validationTab(LastName, characters256);

	}

	public String errorLastNameField() {
		String text2 = this.getText(LastNameError);
		return text2;

	}

	public void clearLastNameField() {
		this.clearField(LastName);

	}

	public void maxValidationContactEmail() {
		this.validationTab(ContactEmail, characters256);

	}

	public String errorContactEmail() {
		String text2 = this.getText(ContactEmailError);
		return text2;

	}

	public void clearContactEmail() {
		this.clearField(ContactEmail);

	}

	public void validateContactEmail() {
		this.validationTab(ContactEmail, "hdsjd");

	}

	public void minValidationContactPhoneNumber() {
		this.validationTab(ContactPhoneNumber, "12");

	}

	public String errorContactPhoneNumber() {
		String text2 = this.getText(ContactPhoneNumberError);
		return text2;

	}

	public void clearContactPhoneNumber() {
		this.clearField(ContactPhoneNumber);

	}

	public void maxValidationContactPhoneNumber() {
		this.validationTab(ContactPhoneNumber, "456455445664464564646");

	}

	public void maxValidationJobTittle() {
		this.validationTab(JobTittle, characters256);

	}

	public String errorContactJobTittle() {
		String text2 = this.getText(JobTittleError);
		return text2;

	}

	public void clearContactJobTittle() {
		this.clearField(JobTittle);

	}

	By MakethisProperty = By.id("addresses__is_primary__0");
	By PropertyName = By.id("addresses__location_name__0");
	By PropertyFirstName = By.id("addresses__contact_person_first_name__0");
	By PropertyLastName = By.id("addresses__contact_person_last_name__0");
	By ContactPersonName = By.id("addresses__name__0");
	By PropertyAddress1 = By.id("addresses__line_1__0");
	By PropertyAddress2 = By.id("addresses__line_2__0");
	By StreetName = By.id("addresses__line_2__0");
	By StateName = By.id("addresses__state__0");
	By CityName = By.id("addresses__city__0");
	By PropertyZipcode = By.id("addresses__zipcode__0");

	By DeleteLocation = By.xpath("//div[@class='accordion-body']//child::div[text()='Delete Property']");
	By AddProperty = By.id("add-more-property-customer-organization");

	By ErrorPropertyName = By.id("addresses__location_name__0_error");
	By ErrorContactPersonName = By.id("addresses__name__0_error");
	By ErrorPropertyFirstName = By.id("addresses__contact_person_first_name__0_error");
	By ErrorPropertyLastName = By.id("addresses__contact_person_last_name__0_error");
	By ErrorPropertyAddress1 = By.id("addresses__line_1__0_error");
	By ErrorPropertyAddress2 = By.id("addresses__line_2__0_error");
	By ErrorStreetName = By.id("addresses__line_2__0_error");
	By ErrorStateName = By.id("addresses__state__0_error");
	By ErrorCityName = By.id("addresses__city__0_error");
	By ErrorZipCode = By.id("addresses__zipcode__0_error");

	By Previous = By.xpath("//i[@class='fa fa-chevron-left font-14 pr-2']");

	public void maxValidationPropertyName() {
		this.validationTab(PropertyName, characters256);

	}

	public String errorContactPropertyName() {
		String text2 = this.getText(ErrorPropertyName);
		return text2;

	}

	public void clearContactPropertyName() {
		this.clearField(PropertyName);

	}

	public void maxValidationPropertyFirstNamee() {
		this.validationTab(PropertyFirstName, characters256);

	}

	public String errorPropertyFirstName() {
		String text2 = this.getText(ErrorPropertyFirstName);
		return text2;

	}

	public void clearPropertyFirstName() {
		this.clearField(PropertyFirstName);

	}

	public void maxValidationPropertyLastNamee() {
		this.validationTab(PropertyLastName, characters256);

	}

	public String errorPropertyLastName() {
		String text2 = this.getText(ErrorPropertyLastName);
		return text2;

	}

	public void clearPropertyLastName() {
		this.clearField(PropertyLastName);

	}

	public void maxValidationPropertyAddress1() {
		this.validationTab(PropertyAddress1, characters256);

	}

	public String errorContactPropertyAddress1() {
		String text2 = this.getText(ErrorPropertyAddress1);
		return text2;

	}

	public void clearContactPropertyAddress1() {
		this.clearField(PropertyAddress1);

	}

	public void maxValidationPropertyAddress2() {
		this.validationTab(PropertyAddress2, characters256);

	}

	public String errorContactPropertyAddress2() {
		String text2 = this.getText(ErrorPropertyAddress2);
		return text2;

	}

	public void clearContactPropertyAddress2() {
		this.clearField(PropertyAddress2);

	}

	public void maxValidationPropertyCityName() {
		this.mouseActionClick(MakethisProperty);
		this.validationTab(CityName, characters256);

	}

	public String errorContactPropertyCityName() {
		String text2 = this.getText(ErrorCityName);
		return text2;

	}

	public void clearContactPropertyCityName() {
		this.clearField(CityName);

	}

	public void maxValidationPropertyStateName() {
		this.validationTab(StateName, characters256);

	}

	public String errorContactPropertyStateName() {
		String text2 = this.getText(ErrorStateName);
		return text2;

	}

	public void clearContactPropertyStateName() {
		this.clearField(StateName);

	}

	public void minValidationPropertyZipcode() {
		this.validationTab(PropertyZipcode, "23");

	}

	public String errorContactPropertyZipcode() {
		String text2 = this.getText(ErrorZipCode);
		return text2;

	}

	public void clearContactPropertyZipcode() {
		this.clearField(PropertyZipcode);

	}

	public void maxValidationPropertyZipcode() {
		this.validationTab(PropertyZipcode, "545465646456446454");

	}

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

	public void maxValidationProductName() {
		this.validationTab(ProductName, characters256);

	}

	public String errorProductName() {
		String text2 = this.getText(ErrorProductName);
		return text2;

	}

	public void clearProductName() {
		this.clearField(ProductName);

	}

	public void maxValidationBrandName() {
		this.validationTab(BrandName, characters256);

	}

	public String errorBrandName() {
		String text2 = this.getText(ErrorBrandName);
		return text2;

	}

	public void clearBrandName() {
		this.clearField(BrandName);

	}

	public void maxValidationModelNumber() {
		this.validationTab(ModelNumber, characters256);

	}

	public String errorModelNumber() {
		String text2 = this.getText(ErrorModelNumber);
		return text2;

	}

	public void clearModelNumber() {
		this.clearField(ModelNumber);

	}

	public void maxValidationSerialNumber() {
		this.validationTab(SerialNumber, characters256);

	}

	public String errorSerialNumber() {
		String text2 = this.getText(ErrorSerialNumber);
		return text2;

	}

	public void clearSerialNumber() {
		this.clearField(SerialNumber);

	}

	public void warrantyInformation() {
		this.dropDownByIndex(WarrantyInformation, 1);

	}

	public void maxValidationAccessHours() {
		this.validationTab(AccessHours, characters256);

	}

	public String errorAccessHours() {
		String text2 = this.getText(ErrorAccessHours);
		return text2;

	}

	public void clearAccessHours() {
		this.clearField(AccessHours);

	}

	public void maxValidationInstallationNotes() {
		this.validationTab(InstallationNotes, characters2048);

	}

	public String errorInstallationNotes() {
		String text2 = this.getText(ErrorInstallationNotes);
		return text2;

	}

	public void clearInstallationNotes() {
		this.clearField(InstallationNotes);

	}

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}

	By AttachmentFile = By.id("customerDropZone");
	By ErrorAttachmentFile = By.xpath("//div[@class='dropzone-error invalid-feedback']");
	By Delete = By.xpath("(//span[text()='Delete'])[10]");

	public void maxSizeValidationAttachmentFile() throws AWTException, InterruptedException {
		this.mouseActionClick(AttachmentFile);
		Thread.sleep(1000);
		attachmentFile("4889");

	}

	public String errorAttachmentFile() {
		String text2 = this.getText(ErrorAttachmentFile);
		return text2;

	}

	public void fileFormatValidation() throws InterruptedException, AWTException {
		this.mouseActionClick(AttachmentFile);
		Thread.sleep(1000);
		attachmentFile("kms_portfolio_new1.html");

	}

	public void maxFileUploadSizeValidation() throws InterruptedException, AWTException {
		for (int i = 1; i < 11; i++) {
			this.mouseActionClick(AttachmentFile);
			Thread.sleep(1000);
			attachmentFile("sample-file.pdf");
			this.scrollDown();
			By AssertionFile = By.xpath("(//span[@class='fieldy-dropzone-title'])[" + i + "]");
			wait = new WebDriverWait(driver, 10);
			String text = wait.until(ExpectedConditions.visibilityOfElementLocated(AssertionFile)).getText();
			Assert.assertEquals(text, "sample-file.pdf");

		}
		Thread.sleep(2000);
		this.mouseActionClick(AttachmentFile);
		Thread.sleep(1000);
		attachmentFile("sample-file.pdf");
		this.scrollDown();

	}

	public void deleteFile() {
		for (int i = 1; i < 6; i++) {
			By Delete = By.xpath("(//span[text()='Delete'])[" + i + "]");
			this.mouseActionClick(Delete);
		}
		for (int i = 1; i < 4; i++) {
			By Delete = By.xpath("(//span[text()='Delete'])[" + i + "]");
			this.mouseActionClick(Delete);
		}

		for (int i = 1; i < 4; i++) {
			this.mouseActionClick(Previous);
		}

	}

	public void organizationPage() throws InterruptedException, AWTException {
		String add = RandomStringUtils.randomAlphanumeric(4);
		String EMail = "Tony" + add + "@gmail.com";
//		this.mouseActionClick(Logo);
//		Thread.sleep(1000);
//		attachmentFile("human-community-five-people-symbol-illustration-web-91578287");
		this.clearField(OrganizationName);
		this.inputText(OrganizationName, fakeCompanyName);
		this.inputText(Website, fakeWebsite);
		this.inputText(Address1, fakeAddress1);
		this.inputText(Address2, fakeAddress2);
		this.inputText(City, fakeCity);
		this.inputText(State, fakeState);
		this.scrollDown();
		this.inputText(ZipCode, fakeZipcode);
		this.inputText(Email, fakeEmail);
		Thread.sleep(2000);
		this.mouseActionClick(LeadSource);
		this.mouseActionClick(Social);
		this.inputText(PhoneNumber, fakePhoneNumber);
		this.mouseActionClick(Next);

	}

	public String create() {
		String text2 = this.getText(CreateResponseMessage);
		return text2;

	}

	public String listFirstName() {
		String text2 = this.getText(ListFirstName);
		return text2;

	}

	public String alreadyExistEmail() throws InterruptedException, AWTException {
		String text = this.getText(ListEmail);
		this.mouseActionClick(AddOrganization);
//		this.mouseActionClick(Logo);
//		Thread.sleep(1500);
//		attachmentFile("pic");
		this.inputText(OrganizationName, "jdshjhadjs");
		this.validationTab(Email, text);
		this.scrollDown();
		this.mouseActionClick(Next);
		this.mouseActionClick(Previous);
		String text2 = this.getText(EmailError);
		this.mouseActionClick(Heading);
		this.mouseActionClick(Yes);
//		Thread.sleep(10000);
//		driver.navigate().refresh();
		return text2;
	}

	public void contactPage() {
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
		this.mouseActionClick(Next);

	}

	public void propertyPage() {
		this.inputText(PropertyFirstName, fakeFirstName);
		this.inputText(PropertyLastName, fakeLastName);
		this.inputText(PropertyName, "Fieldy");
		this.inputText(PropertyAddress1, fakeAddress1);
		this.inputText(PropertyAddress2, fakeAddress2);
		this.inputText(CityName, fakeCity);
		this.inputText(StateName, fakeState);
		this.inputText(PropertyZipcode, fakeZipcode);
		this.mouseActionClick(Next);

	}

	public void equipmentPage() throws InterruptedException {
		String Note = RandomStringUtils.randomAlphabetic(500);
		this.inputText(ProductName, "Sony");
		this.inputText(BrandName, "Sony X10");
		this.inputText(ModelNumber, fakeFaxNumber);
		this.inputText(SerialNumber, "8765645345978");
		this.inputText(DateInstalled, "09/18/2022");
		this.dropDownByIndex(WarrantyInformation, 1);
		this.inputText(AccessHours, "8hrs");
		this.inputText(InstallationNotes, Note);
		Thread.sleep(2000);
		this.mouseActionClick(SaveComplete);
	}

	By ListFirstName = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[1]");
	By Search = By.id("customer-organization-search-box");
	By ListPhoneNumber = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[3]");
	By ListEmail = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[4]");
	By Filter = By.xpath("//span[@data-timeline-open='customerorganization']");
	By LeadSourceCheckBox = By.xpath("(//input[@id='filter-organization-leadSource-checkbox'])[2]");
	By ListLeadSource = By.id("customer-organization-lead-input-place");
	By Status = By.id("customer-contact-status-active");
	By Apply = By.xpath("//*[@class='col-lg-4 col-md-2 col-sm-2 col-6 pt-2']//*");
	By SearchButton = By.id("customer-organization-search-enter");
	By Invalid = By.xpath("//*[text()='No Result Found']");

	public String createListFirstName() {
		String text = this.getText(ListFirstName);
		return text;
	}

	public String searchNameValidation() throws InterruptedException {
		String text = this.getText(ListFirstName);
		this.inputText(Search, text);
		this.mouseActionClick(SearchButton);
		return text;

	}

	public String searchNameListValidation() {
		String text2 = this.getText(ListFirstName);
		return text2;

	}

	public void clearSearchField() {
		this.clearField(Search);

	}

	public String searchPhoneNumberValidation() {
		String text = this.getText(ListPhoneNumber);
		this.inputText(Search, text);
		this.mouseActionClick(SearchButton);
		return text;

	}

	public String listPhoneNumber() {
		String text2 = this.getText(ListPhoneNumber);
		return text2;

	}

	public String searchEmailValidation() {
		String text = this.getText(ListEmail);
		this.inputText(Search, text);
		this.mouseActionClick(SearchButton);
		return text;

	}

	public String listEmailField() {
		String text2 = this.getText(ListEmail);
		return text2;

	}

	public void searchInvalidValidation() {
		this.inputText(Search, "bdscciuuici");
		this.mouseActionClick(SearchButton);
//		this.assertName(Invalid, "No Result Found");

	}

	public String invalidSearch() {
		String text2 = this.getText(Invalid);
		return text2;

	}

	public void characterListValidation() {
		String str = this.getText(ListFirstName);
		char first = str.charAt(0);
		By CharacterName = By.xpath("//a[text()='" + first + "']");
		this.mouseActionClick(CharacterName);
		String text = this.getText(ListFirstName);
		this.assertName(ListFirstName, text);

	}

	public String filterValidation() throws InterruptedException {
		String text = this.getText(ListFirstName);
		this.mouseActionClick(Filter);
		this.mouseActionClick(ListLeadSource);
		this.mouseActionClick(LeadSourceCheckBox);
		this.dropDownByIndex(Status, 1);
		this.mouseActionClick(Apply);
		return text;

	}

	public String listFilterValidation() {
		String text2 = this.getText(ListFirstName);
		return text2;

	}

	By Dots = By.xpath("(//i[@class='fa fa-ellipsis-v'])[2]");
	By Edit = By.xpath("(//li[@data-tabformid='undefined'])[1]");
	By Update = By.xpath("//*[text()='Customer details updated successfully']");
	By DeletedMessage = By.xpath("//*[text()='Customer deleted successfully']");
	By Deleted = By.xpath("(//li[@data-tabformid='undefined'])[2]");
	By reset = By.xpath("//*[@onclick=\"generateCustomerOrganizationTable('','','','','reset')\"]");

	public void resetOption() {
		this.mouseActionClick(reset);

	}

	By ClickAll = By.xpath("//a[@data-filteralphaorg='all']");

	public void clickOrganization() throws InterruptedException {
		driver.navigate().refresh();
		this.mouseActionClick(Customer);
		this.assertName(Text, "Name");
		this.mouseActionClick(Organization);

	}

	public void all() {
		this.mouseActionClick(ClickAll);

	}

	By UpdateButton = By.id("customerdrop");

	public void editContact() throws AWTException, InterruptedException {
		this.mouseActionClick(Dots);
		this.mouseActionClick(Edit);
		Thread.sleep(3000);
//		this.mouseActionClick(Logo);
//		Thread.sleep(1000);
//		attachmentFile("istockphoto-825383494-612x612");
		this.clearField(OrganizationName);
		this.inputText(OrganizationName, fakeCompanyName);
		this.scrollDown();
		this.clearField(Email);
		this.inputText(Email, fakeEmail);
		this.clearField(PhoneNumber);
		this.inputText(PhoneNumber, fakePhoneNumber);
		this.assertName(UpdateButton, "Update");
		Thread.sleep(3000);
		this.mouseActionClick(UpdateButton);

	}

	public String updateMessage() {
		String text2 = this.getText(Update);
		return text2;

	}

	public String deleteMessage() {
		String text2 = this.getText(DeletedMessage);
		return text2;

	}

	public void deleteContact() {
		this.mouseActionClick(Dots);
		this.mouseActionClick(Deleted);
		this.mouseActionClick(Yes);

	}

	By Name = By.xpath("//td[text()='Organization Name']");

	public void Condition() {
		this.assertName(Name, "Organization Name");

	}

}
