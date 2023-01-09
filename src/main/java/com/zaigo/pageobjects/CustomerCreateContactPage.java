package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.sql.Savepoint;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.lang.model.element.Element;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.internal.BaseClassFinder;

import com.base.BaseClass;
import com.github.javafaker.Faker;

public class CustomerCreateContactPage extends BaseClass {

	WebDriverWait wait;
	WebDriver driver;

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);

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

	By DashBoard = By.id("customer-main");
	By Today = By.xpath("(//div[@class='mb-2']//parent::div)[4]");
	By Customer = By.id("customer-main");
	By Contact = By.id("customer-contact-menu");
	By AddContact = By.id("scheduledrop");
	By ErrorLogo = By.id("logo_error");
	By FormatErrorLogo = By.xpath("//div[text()='Only jpg,jpeg,png Formats Allowed']");

	By Yes = By.xpath("//*[text()='Yes']");
	By ResponseMessage = By.xpath("//*[@class='created_successfully d-flex d-none']");
	By ListName = By.xpath("(//a[@data-n-linkto='customer_contact_timeline'])[1]");
	By Search = By.id("customer-contact-search-value");
	By SearchButton = By.id("customer-contact-search-button");
	By InvalidList = By.xpath("//div[text()='No Result Found']");

	private void ClickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();

	}

	private void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();

	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);

	}

	private void validationCheckingInputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);

	}

	private void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();

	}

	private void alertAccept() {
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
	}

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	private void dashBoard() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(DashBoard)).getText();
		Assert.assertEquals(text, "Customer");

	}

	private void today() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Today)).getText();
		Assert.assertEquals(text, "Today's List");

	}

	private void clickCustomer() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Customer)).click();

	}

	private void clickContact() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(Contact));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();

	}

	private void mouseClickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();

	}

	private void clickAddContact() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddContact)).click();

	}

	By Text = By.xpath("//td[text()='Customer Name']");

	public void modulePage() throws InterruptedException {
		this.dashBoard();
		Thread.sleep(5000);
		this.clickCustomer();
		this.assertName(Text, "Customer Name");
		this.clickContact();
		this.clickAddContact();

	}

	By Tittle = By.xpath("//a[@data-exitpopup='customer_contact']");
	By SaveComplete = By.id("customerdrop");
	By ErrorFirstName = By.id("first_name_error");

	private void assertTittle() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Tittle)).getText();
		Assert.assertEquals(text, "Customer / Add Contact");

	}

	private void clickSaveComplete() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(SaveComplete));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();

	}

	By Virus = By.xpath("//div[@id='dropid-4189']");
	By FirstName = By.id("first_name");
	By MaxErrorFirstName = By.id("first_name_error");
	By LastName = By.id("last_name");
	By MaxErrorLastName = By.id("last_name_error");
	By JobTittle = By.id("job_title");
	By ErrorJobTittle = By.id("job_title_error");
	By Email = By.id("email");
	By ErrorEmail = By.id("email_error");
	By Phone = By.id("phones__number__0");
	By ErrorPhoneNo = By.id("phones__number__0_error");
	By Organization = By.xpath("//input[@placeholder='Choose Organization Name']");
	By New = By.xpath("//button[@data-modalfetch='shorter_organization_create']");
	By Logo = By.xpath("//input[@id='logo']//following::label[@for='logo']");
	By LeadSources = By.xpath("//input[@data-dropdownlist='lead-source']");
	By Social = By.xpath("//*[text()='Social']");

	private void logoFormatValidation() throws AWTException {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(Logo));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
		attachmentFile(System.getProperty("user.dir" + "\\Validation Files\\png.png"));
	}

	private void firstName(String firstName) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName)).sendKeys(firstName);

	}

	private void blankFirstName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName)).sendKeys("    ", Keys.TAB);

	}

	private void maxFirstName() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearFirstName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName)).clear();

	}

	private void lastName(String lastName) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LastName)).sendKeys(lastName);

	}

	private void maxLastName() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LastName)).sendKeys(maxCharacter, Keys.TAB);
	}

	private void clearLastName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LastName)).clear();

	}

	private void maxJobTittle() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(JobTittle)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearJobTittle() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(JobTittle)).clear();

	}

	private void maxEmail() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Email)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearEmail() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Email)).clear();

	}

	private void Email(String email) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Email)).sendKeys(email, Keys.TAB);

	}

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	private void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}

	private void minValidationPhoneNumber() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Phone)).sendKeys("9875", Keys.TAB);

	}

	private void maxValidationPhoneNumber() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Phone)).sendKeys("98757777888988887788787", Keys.TAB);

	}

	private void clearPhone() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Phone)).clear();

	}

	private void assertName(By element, String Name) {
		wait = new WebDriverWait(driver, 15);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(text, Name);

	}

	By AlreadyEmail = By.xpath("//span[text()='The e-mail is already exit']");
	By Next = By.xpath("//*[text()='Next']");
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

	private void clickNext() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(Next)).click();

	}

	private void clickProperty() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(MakethisProperty));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();

	}

	private void maxValidationPropertyName() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PropertyName)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearPropertyName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PropertyName)).clear();
	}

	private void maxValidationContactPersonName() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(513);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ContactPersonName)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearContactPerson() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ContactPersonName)).clear();

	}

	private void maxValidationAddress1() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Address1)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearAddress1() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Address1)).clear();

	}

	private void maxValidationAddress2() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Address2)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearAddress2() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Address2)).clear();

	}

	private void maxValidationStateName() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StateName)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearStateName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StateName)).clear();

	}

	private void maxValidationCityName() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomAlphabetic(257);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CityName)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearCityName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CityName)).clear();

	}

	private void minValidationZipCode() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Zipcode)).sendKeys("33", Keys.TAB);

	}

	private void maxValidationZipCode() {
		wait = new WebDriverWait(driver, 10);
		String maxCharacter = RandomStringUtils.randomNumeric(15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Zipcode)).sendKeys(maxCharacter, Keys.TAB);

	}

	private void clearZipCode() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Zipcode)).clear();

	}

	private void clickDeleteLocation() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(DeleteLocation)).click();

	}

	private void clickAddProperty() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddProperty)).click();

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
	String randomAlphabetic = RandomStringUtils.randomAlphabetic(257);
	String num = RandomStringUtils.randomNumeric(4);
	String MinValidationZipPhone = RandomStringUtils.randomNumeric(2);
	String MaxValidationZipPhone = RandomStringUtils.randomNumeric(15);
	String MaxValidationStateName = RandomStringUtils.randomAlphabetic(50);
	String PhoneNumber = RandomStringUtils.randomNumeric(9);

	private void max256Fields(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(randomAlphabetic, Keys.TAB);

	}

	private void max2048Fields(By element) {
		wait = new WebDriverWait(driver, 10);
		String randomAlphabetic2 = RandomStringUtils.randomAlphabetic(2049);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(randomAlphabetic2, Keys.TAB);

	}

	private void min3Fields(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(MinValidationZipPhone, Keys.TAB);

	}

	private void max12Fields(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(MaxValidationZipPhone, Keys.TAB);

	}

	private void error3ErrorMessage(By element) {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(text, MinValidationZipPhone);

	}

	private void clearEquipmentPage(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();

	}

	private void dropDownWarantyInformation() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(WarrantyInformation));
		Select select = new Select(until);
		select.selectByIndex(1);
	}

	By AttachmentFile = By.id("customerDropZone");
	By ErrorAttachmentFile = By.xpath("//div[@class='dropzone-error invalid-feedback']");
	By attached = By.className("dropzone-uploads");

	private void attachmentFile(By element) throws AWTException, InterruptedException {
		wait = new WebDriverWait(driver, 20);
		WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(element2).click().build().perform();
	}

	private void uploadMaxSizeFile() throws AWTException, InterruptedException {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AttachmentFile)).click();
		Thread.sleep(2000);
		attachmentFile("4889");
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorAttachmentFile)).getText();
		Assert.assertEquals(text, "File is too big (24.67MiB). Max filesize: 20MiB.");
	}

	By MaxFile = By.xpath("//div[@class='dropzone-error invalid-feedback']");
	By Delete = By.xpath("(//span[text()='Delete'])[10]");

	private void multipleUpload() throws AWTException, InterruptedException {
		for (int i = 1; i < 11; i++) {
			this.attachmentFile(AttachmentFile);
			Thread.sleep(2000);
			attachmentFile("sample-file.pdf");
			Thread.sleep(1500);
			this.scrollDown();
			By AssertionFile = By.xpath("(//span[@class='fieldy-dropzone-title'])[" + i + "]");
			wait = new WebDriverWait(driver, 10);
			String text = wait.until(ExpectedConditions.visibilityOfElementLocated(AssertionFile)).getText();
			Assert.assertEquals(text, "sample-file.pdf");

		}
		this.attachmentFile(AttachmentFile);
		Thread.sleep(2000);
		attachmentFile("sample-file.pdf");
		this.scrollDown();
		this.assertName(MaxFile, "Maximum upload limit reached");
		this.mouseActionClick(Delete);
	}

	public void attachmentFiles() throws AWTException, InterruptedException {
		this.clickNext();
		this.uploadMaxSizeFile();
		this.multipleUpload();
		for (int i = 0; i < 2; i++) {
			this.ClickButton(Previous);
		}
	}

	By ImagePreview = By.id("imagePreview");

	public void validationFormOrganizationContactPage() throws AWTException {
		this.clearFirstName();
		this.inputText(FirstName, "Manoj");
		this.inputText(LastName, "Kumar");
		this.ClickButton(Organization);
		this.ClickButton(Virus);
		this.inputText(JobTittle, "Replacement&Testing");
		this.ClickButton(LeadSources);
		this.ClickButton(Social);
		this.inputText(Email, "yahoo@gmail.com");
		this.inputText(Phone, "9" + PhoneNumber);
		this.clickNext();

	}

	public void validationFormPropertyPage() {
		this.ClickButton(MakethisProperty);
		this.inputText(PropertyName, "Work Location");
		this.inputText(ContactPersonName, "Ravi");
		this.inputText(Address1, "25/825");
		this.inputText(Address2, "Wastern Street");
		this.inputText(StateName, "TamilNadu");
		this.inputText(CityName, "Chennai");
		this.inputText(Zipcode, "624889");
		this.clickNext();

	}

	public void validationFormEquipmentPage() {
		this.inputText(ProductName, "Samsung");
		this.inputText(BrandName, "Neo QLED TVs");
		this.inputText(ModelNumber, "7894562135478789");
		this.inputText(SerialNumber, "8794562155");
		this.inputText(DateInstalled, "08/25/2022");
		this.inputText(AccessHours, "8hrs");
		this.inputText(InstallationNotes, randomAlphabetic);

	}

	By AddNew = By.xpath("//div[@class='no-data-found text-break']//child::button");
	By CompanyName = By.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='company_name']");
	By OrgPhoneNumber = By
			.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='phones__number__0']");
	By OrgEmail = By.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='email']");
	By OrgWebsite = By.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='website']");
	By OrgBuilding = By.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='line_1']");
	By OrgStreet = By.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='line_2']");
	By OrgState = By.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='state']");
	By OrgCity = By.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='city']");
	By OrgZipcode = By.xpath("//form[@id='customer_organization_create_edit']//child::input[@id='zipcode']");
	By ErrorCompanyName = By
			.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='company_name_error']");
	By ErrorPhoneNumber = By
			.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='phones__number__0_error']");
	By ErrorMail = By.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='email_error']");
	By ErrorWebsite = By.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='website_error']");
	By ErrorBuilding = By.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='line_1_error']");
	By ErrorStreet = By.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='line_2_error']");
	By ErrorState = By.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='state_error']");
	By ErrorCity = By.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='city_error']");
	By ErrorZipcode = By.xpath("//form[@id='customer_organization_create_edit']//child::div[@id='zipcode_error']");
	By OrgSaveComplete = By
			.xpath("//div[@class='d-flex row justify-content-between ']//child::button[@id='organization-create']");

	private void mouseAction(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();

	}

	By Status = By.id("customer-contact-status-active");
	By Filter = By.xpath("//span[@data-timeline-open='customercontact']");
	By Apply = By.xpath("//div[@class='col-lg-4 col-md-4 col-sm-6 col-12 pt-2']//child::button");
	By ListPhoneNumber = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[3]");
	By ListSocial = By.xpath("(//input[@class='mr-3'])[2]");
	By ListLeadSource = By.id("customer-contact-lead-source-search");
	By ListEmail = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[4]");

	private void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);

	}

	private String getText(By element) {
		wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();

	}

	private void filterValidation() {
		this.ClickButton(Filter);

	}

//****************************************************************************************************************
	public void maxSizeProfile() throws AWTException, InterruptedException {
		Thread.sleep(4000);
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		this.attachmentFile("dsc00531");

	}

	public String maxSizeProfileField() {
		String text = this.getText(ErrorLogo);
		return text;

	}

	public String errorFileFormat() {
		String text2 = this.getText(FormatErrorLogo);
		return text2;

	}

	public void fileFormatProfile() throws AWTException, InterruptedException {
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		this.attachmentFile("sample-file.pdf");

	}

	public void uploadProfile() throws AWTException, InterruptedException {
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		this.attachmentFile("pexels-suliman-sallehi-1704488");

	}

	public void mandatoryValidation() {
		this.validationTab(FirstName, " ");

	}

	public String errorMandatoryValidation() {
		String text2 = this.getText(ErrorFirstName);
		this.inputText(FirstName, "Demo");
		return text2;

	}

	public void maxValidationFirstName() throws InterruptedException {
		Thread.sleep(2000);
		this.validationTab(FirstName, characters256);
		this.mouseActionClick(SaveComplete);

	}

	public String errorFirstName() {
		String text2 = this.getText(ErrorFirstName);
		return text2;

	}

	public void clearFirstNameField() {
		this.clearField(FirstName);

	}

	public void maxValidationLastName() {
		this.validationTab(LastName, characters256);

	}

	public String errorLastNameField() {
		String text2 = this.getText(MaxErrorLastName);
		return text2;

	}

	public void clearLastNameField() {
		this.clearField(LastName);

	}

	public void maxValidationJobTittle() {
		this.validationTab(JobTittle, characters256);

	}

	public String errorJobTittle() {
		String text2 = this.getText(ErrorJobTittle);
		return text2;

	}

	public void clearJobTittleField() {
		this.clearField(JobTittle);
	}

	public void maxValidationEmail() {
		this.scrollDown();
		this.validationTab(Email, characters256);

	}

	public String errorEmail() {
		String text2 = this.getText(ErrorEmail);
		return text2;

	}

	public void clearEmailField() {
		this.clearField(Email);

	}

	public void invalidEmail() {
		this.validationTab(Email, "sdbhsd");

	}

//changeName
	public void minValidationPhone() {
		this.validationTab(Phone, "21");
//		this.assertName(ErrorPhoneNo, Min6CharacterValidation);

	}

	public String errorPhoneNumber() {
		String text2 = this.getText(ErrorPhoneNo);
		return text2;

	}

	public void clearPhoneNumber() {
		this.clearField(Phone);

	}

	public void maxValidationPhone() {
		this.validationTab(Phone, "4564564565313456456456");

	}

	public void nextButton() {
		this.mouseActionClick(Next);

	}

//changeName	
	public void maxValidationPropertyFirstName() {
		this.validationTab(PropertyFirstName, characters256);

	}

	public void maxValidationPropertyLastName() {
		this.validationTab(PropertyLastName, characters256);

	}

	public String errorPropertyFirstName() {
		String text2 = this.getText(ErrorPropertyFirstName);
		return text2;

	}

	public String errorPropertyLastName() {
		String text2 = this.getText(ErrorPropertyLastName);
		return text2;

	}

	public void clearPropertyFirstNameField() {
		this.clearField(PropertyFirstName);

	}

	public void clearPropertyLastNameField() {
		this.clearField(PropertyLastName);

	}

//changeName
	public void maxValidationPropertyNamee() {
		this.validationTab(PropertyName, characters256);

	}

	public String errorContactPerson() {
		String text2 = this.getText(ErrorPropertyName);
		return text2;

	}

	public void clearContactPersonField() {
		this.clearField(PropertyName);

	}

	// changeName
	public void maxValidationAddress11() {
		this.validationTab(Address1, characters256);

	}

	public String errorAddress1Field() {
		String text2 = this.getText(ErrorAddress1);
		return text2;

	}

	public void clearAddress1Field() {
		this.clearField(Address1);

	}

	// changeName
	public void maxValidationAddress22() {
		this.validationTab(Address2, characters256);
//		this.assertName(ErrorAddress2, );

	}

	public String errroAddress2Field() {
		String text2 = this.getText(ErrorAddress2);
		return text2;

	}

	public void clearAddress2Field() {
		this.clearField(Address2);

	}

	// changeName
	public void maxValidationCity() {
		this.validationTab(CityName, characters256);

	}

	public String errorCityField() {
		String text2 = this.getText(ErrorCityName);
		return text2;

	}

	public void clearCityField() {
		this.clearField(CityName);

	}

	public void maxValidationState() {
		this.validationTab(StateName, characters256);

	}

	public String errorStateField() {
		String text2 = this.getText(ErrorStateName);
		return text2;

	}

	public void clearStateField() {
		this.clearField(StateName);

	}

	public void minValidationZipcode() {
		this.mouseActionClick(MakethisProperty);
		this.validationTab(Zipcode, "21");

	}

	public String errorZipcodeField() {
		String text2 = this.getText(ErrorZipCode);
		return text2;

	}

	public void clearZipcodeField() {
		this.clearField(Zipcode);

	}

	public void maxValidationZipcode() {
		this.validationTab(Zipcode, "231456787912321321321");

	}

	public void maxValidationProductName() {
		this.validationTab(ProductName, characters256);

	}

	public String errorProductField() {
		String text2 = this.getText(ErrorProductName);
		return text2;

	}

	public void clearProductField() {
		this.clearField(ProductName);

	}

	public void maxValidationBrandName() {
		this.validationTab(BrandName, characters256);

	}

	public String errorBrandField() {
		String text2 = this.getText(ErrorBrandName);
		return text2;

	}

	public void clearBrandField() {
		this.clearField(BrandName);

	}

	public void maxValdidationModelNumber() {
		this.validationTab(ModelNumber, characters256);

	}

	public String errorModelField() {
		String text2 = this.getText(ErrorModelNumber);
		return text2;

	}

	public void clearModelField() {
		this.clearField(ModelNumber);

	}

	public void maxValidationSerialNumber() {
		this.validationTab(SerialNumber, characters256);

	}

	public String errorSerialNumberField() {
		String text2 = this.getText(ErrorSerialNumber);
		return text2;

	}

	public void clearSerialNumber() {
		this.clearField(SerialNumber);
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

	public String errorInstallation() {
		String text2 = this.getText(ErrorInstallationNotes);
		return text2;

	}

	public void clearInstallation() {
		this.clearField(InstallationNotes);
		this.mouseActionClick(Previous);
		this.mouseActionClick(Previous);

	}

	public void maxValidationAttachmentFile() throws AWTException, InterruptedException {
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

	public void maxLimitAttachmentField() throws AWTException, InterruptedException {
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

	}

	public void propertyPage() {
//		for (int i = 0; i < 2; i++) {
//			this.mouseActionClick(Previous);
//		}
		this.inputText(PropertyFirstName, fakeFirstName);
		this.inputText(PropertyLastName, fakeLastName);
		this.inputText(PropertyName, fakeCompanyName);
		this.inputText(Address1, fakeAddress1);
		this.inputText(Address2, fakeAddress2);
		this.inputText(StateName, fakeState);
		this.inputText(CityName, fakeCity);
		this.inputText(Zipcode, fakeZipcode);
		this.clickNext();

	}

	public void equipmentPage() {
		this.inputText(ProductName, "Samsung");
		this.inputText(BrandName, "Neo QLED TVs");
		this.inputText(ModelNumber, fakeFaxNumber);
		this.inputText(SerialNumber, "8794562155");
		this.inputText(DateInstalled, "08/25/2022");
		this.dropDownByIndex(WarrantyInformation, 1);
		this.inputText(AccessHours, "8hrs");
		this.inputText(InstallationNotes, randomAlphabetic);
//		for (int i = 0; i < 2; i++) {
//			this.mouseActionClick(Previous);
//		}
		this.mouseActionClick(SaveComplete);

	}

	String a = num;
	String b = "1";
	String c = a + b;

	public void contactPage() throws AWTException, InterruptedException, IOException {
//		this.uploadProfile();
		this.clearFirstName();
		this.inputText(FirstName, fakeFirstName);
		this.inputText(LastName, fakeLastName);
		this.inputText(JobTittle, fakeTittle);
		this.scrollDown();
		this.inputText(Email, fakeEmail);
		this.inputText(Phone, fakePhoneNumber);
		Thread.sleep(1500);
		this.ClickButton(LeadSources);
		this.ClickButton(Social);
		this.ClickButton(Next);

	}

	public String responseMessageCreateContact() {
		String text2 = this.getText(ContactCreateMessage);
		return text2;

	}

	By Name = By.xpath("//td[text()='Name']");

	public String createListFirstName() {
		String text2 = this.getText(Name);
		return text2;
	}

	public String checkName() {
		String text2 = this.getText(Text);
		return text2;

	}

	public void alreadyExistMail() throws InterruptedException {
		String text = this.getText(ListEmail);
		this.ClickButton(AddContact);
		this.inputText(FirstName, "Ajith");
		this.scrollDown();
		this.validationTab(Email, text);

	}

	public void nextFunction() {
		this.ClickButton(Tittle);
		this.ClickButton(Yes);

	}

	public void alphabetsFilters() {
		String str = this.getText(ListName);
		char first = str.charAt(0);
		By Alpbabet = By.xpath("//*[@data-filteralphacon='" + first + "']");
		this.ClickButton(Alpbabet);

	}

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

	By reset = By.xpath("//*[@onclick=\"generateCustomerContactTable('','','','','reset')\"]");

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

	By Dots = By.xpath("(//i[@class='fa fa-ellipsis-v'])[2]");
	By Edit = By.xpath("(//li[@data-n-linkto='customer_contact_edit'])[1]");
	By ContactCreateMessage = By.xpath("//*[text()='Customer created successfully']");
	By Update = By.xpath("//*[text()='Customer details updated successfully']");
	By DeletedMessage = By.xpath("//*[text()='Customer deleted successfully']");
	By Deleted = By.xpath("(//li[@data-tabformid='undefined'])[2]");

	public void editContact() throws AWTException, InterruptedException {
		this.mouseActionClick(Dots);
		this.mouseActionClick(Edit);
		Thread.sleep(3000);
//		this.mouseActionClick(Logo);
//		Thread.sleep(1000);
//		attachmentFile("istockphoto-825383494-612x612");
		this.clearField(FirstName);
		this.inputText(FirstName, "Ajith");
		this.mouseActionClick(SaveComplete);

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

}
