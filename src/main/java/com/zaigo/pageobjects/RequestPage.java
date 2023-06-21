package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class RequestPage extends BaseClass {

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

	public RequestPage(WebDriver driver) {
		this.driver = driver;

	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	private void elementtobeClickable(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

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

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private int listWebElement(By element) {
		wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)).size();
	}

	public void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 20);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
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

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	private void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	private void tagValidation(WebElement element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text, Keys.ENTER);
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
		wait = new WebDriverWait(driver, 50);
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

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public Boolean valuePresent(WebElement element, String value) {
		wait = new WebDriverWait(driver, 50);
		Boolean text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		return text;
	}

	private void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	String text;

	public String errorMessage(By value) {
		if (!this.conditionChecking(value)) {
			do {
				text = this.getText(value);
			} while (!this.conditionChecking(value));
		}
		return text;

	}

	By ContactListName = By.xpath("(//*[@data-n-linkto='customer_contact_timeline'])[1]");
	By OrganizationListName = By.xpath("(//*[@data-n-linkto='customer_organization_timeline'])[1]");
	By ClickContactRequest = By.xpath("//*[@data-detailheadermenu='cstmr-contact-request']");
	By ClickOrganizationRequest = By.xpath("//*[@data-detailheadermenu='cstmr-organization-request']");
	By CreateContactRequest = By.xpath("//*[@data-automationid='customer-contact-request-create']");
	By CreateOrganizationRequest = By.xpath("//*[@data-automationid='customer-organization-request-create']");
	By CreateRequestLabel = By.xpath("//*[@data-menuselector='request-menu']");
	By Location = By.id("location");
	By Tittle = By.id("title");
	By Description = By.id("description");
	By Tags = By.className("tag__input");
	By Notes = By.id("notes");
	By SaveComplete = By.id("scheduledrop");
	By BussinessUnit = By.xpath("//*[@data-dropdownlist='business-unit']");
	By ServiceType = By.xpath("//*[@data-dropdownlist='service-type']");
	By firstLocation = By.xpath("(//*[@class='pac-item'])[1]");
	By ErrorLocation = By.id("location_error");
	By ErrorTittle = By.id("title_error");
	By ErrorDescription = By.id("description_error");
	By ErrorTags = By.id("tags__tags__0_error");
	By ErrorNotes = By.id("notes_error");
	By TagRemove = By.xpath("//*[@class='tag__remove']");
	By RequestNo = By.xpath("(//*[@class='id-number'])[1]");
	By RequestNo2 = By.xpath("(//*[@class='id-number'])[2]");
	By StartDate = By.id("schedule_from_date");
	By EndDate = By.id("schedule_to_date");
	By StartTime = By.id("schedule_from_time");
	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div/div");
	By EndTime = By.id("schedule_to_time");
	By Crew = By.id("crew-radio-button");
	By Single = By.id("technician-radio-button");
	By Technician = By.xpath("//*[@class='floating-input form-control user-view ']");
	By Technician1 = By.xpath("//*[@data-dropdownlist='technician-list']");
	By TechnicianFirstName = By.xpath("//*[@id='technician_ids-autocomplete-list']/div[1]");
	By TechnicianSecoundName = By.xpath("//*[@id='technician_ids-autocomplete-list']/div[2]");
	By TechnicianThirdName = By.xpath("//*[@id='technician_ids-autocomplete-list']/div[3]");
	By Priority = By.id("priority");
	By General = By.xpath("//*[@class='p-2 list-hover-bg team-business-unit w-20-ellipsis w-100']");
	By Repair = By.xpath("//*[@class='p-2 list-hover-bg team-service-type w-20-ellipsis w-100']");
	By EalierTime = By.xpath("//*[text()='From Time should be current or future time only']");
	By TimeMismatch = By.xpath("//*[text()='Start time should be earlier than End time']");
	By RequestCreatedMessage = By.xpath("//*[text()='Request Created Successfully']");
	By RequestUpdatedMessage = By.xpath("//*[text()='Request Updated Successfully']");
	By RequestDispatchMessage = By.xpath("//*[text()='Request dispatched successfully']");
	By RequestStartedMessgae = By.xpath("//*[text()='Request started successfully']");
	By RequestCompletedMessage = By.xpath("//*[text()='Request completed Successfully']");
	By RequestCancelledMessage = By.xpath("//*[text()='Request has been cancelled successfully']");
	By RequestDeletedMessage = By.xpath("//*[text()='Request deleted successfully']");
	By RequestDraftedMessage = By.xpath("//*[text()='Request drafted']");
	By Yes = By.xpath("//*[text()='Yes']");
	By No = By.xpath("//*[text()='No']");
	By Back = By.xpath("//*[@alt='back_arrow']");
	By Label = By.xpath("(//*[@data-draftback='requestdraft'])[2]");
	By Label1 = By.xpath("//*[@data-draftback='requestdraft']");
	By SearchContactBox = By.id("customer-contact-request-search-filter");
	By SearchOrganizationBox = By.id("customer-company-search-filter");
	By Reset = By.xpath("//*[text()=' Reset Search']");
	By SearchLocation = By.id("customer-contact-request-card-profile-location");
	By InvlaidSearch = By.xpath("//*[@class='title']");
	By GlobalInvlaidSearch = By.xpath("//*[text()='No Result Found']");
//	By Filter = By.xpath("//*[@class='eiconmenu20-white-filter']");
	By FilterContactFrom = By.id("customer-contact-request-from-date-filter");
	By FilterContactTo = By.id("customer-contact-request-to-date-filter");
	By FilterOrganizationFrom = By.id("customer-company-request-from-date");
	By FilterOrganizationTo = By.id("customer-company-request-to-date");
	By Apply = By.xpath("//*[@data-automationid='contact-apply']");
	By CrewSize = By.id("crew_size");
	By OrganizationFirstName = By.id("contacts__first_name__0");
	By OrganizationLastName = By.id("contacts__last_name__0");
	By OrganizationEmail = By.id("contacts__email__0");
	By OrganizationPhoneNumber = By.id("contacts__phone__0");
	By OrganizationRequestTittle = By.id("contacts__job_title__0");
	By OrganizationContactSave = By.id("organization-contact-create");
	By SelectTechnician = By.xpath("//*[@id=\"assign-technician\"]/div[1]/div[1]");
	By StatusRequest = By.id("customer-contact-request-card-status");
	By TechnicianLabel = By.xpath("//*[text()='Technician']");
	By CancelledStatus = By.xpath("//*[text()='cancelled']");
	By ScheduleFrom = By.xpath(
			"(//*[text()='Schedule From  : ']//following-sibling::*[@id='customer-contact-request-card-booking-time'])[1]");

	By ScheduleTo = By.xpath(
			"(//*[text()='Schedule To : ']//following-sibling::*[@id='customer-contact-request-card-booking-time'])[1]");
	By RequestLabelNo = By.xpath("(//*[text()='Request No :'])[1]");
	By OrgContactName = By.id("id_user_customer");
	By OrgContactAdd = By.xpath("//*[@class='add_new_btn3 btn-30 btn btn-bg-blue pr-2 pl-2']");
	By ContactCreateMessage = By.xpath("//*[text()='Customer created successfully']");
	By AssertDashboard = By.xpath("//*[text()=' Company Performance']");
	By Request = By.id("request-menu");
	By ListCustomer = By.xpath("//*[text()='Customer']");
	By CreateGlobalRequest = By.xpath("//*[@data-automationid='request-create']");
	By ContactNameError = By.id("id_customer_group_error");
	By GlobalCustomer = By.id("id_customer_group");
	By Add = By.xpath("//*[@class='add_new_btn btn btn-30 btn-bg-blue pr-2 pl-2 ']");
	By FirstName = By.id("first_name");
	By LastName = By.id("last_name");
	By Email = By.id("email");
	By Phone = By.id("phones__number__0");
	By Address1 = By.id("addresses__line_1__0");
	By Address2 = By.id("addresses__line_2__0");
	By StateName = By.id("addresses__state__0");
	By CityName = By.id("addresses__city__0");
	By Zipcode = By.id("addresses__zipcode__0");
	By Save = By.id("contact-create");
	By GlobalStatus = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[6]");
	By ThreeDots = By.xpath("//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]//i");
	By GlobalEdit = By.xpath("(//*[@data-n-linkto='main_request_edit'])[1]");
	By GlobalDispatch = By.xpath("(//*[@data-tabposition=\"undefined\"])[10]");
	By GlobalStart = By.xpath("(//*[@data-tabposition=\"undefined\"])[13]");
	By GlobalComplete = By.xpath("(//*[@data-tabposition=\"undefined\"])[16]");
	By GlobalCancel = By.xpath("(//*[@data-tabposition=\"undefined\"])[40]");
	By ThreeDotsCancel = By.xpath("//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[3]/td[1]/div/div[1]/i");
	By GlobalCancelledStatus = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[12]");
	By GlobalSearchRequestNo = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[2]");
	By GlobalCustomerName = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[1]");
	By GlobalLocation = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[4]");
	By GlobalDate = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[3]");
	By GlobalSearchBox = By.id("request-search-input-filter");
	By GlobalFrom = By.xpath("(//*[@data-setdatelimitmax='schedule_from_date'])[1]");
	By GlobalTo = By.xpath("(//*[@data-setdatelimitmax='schedule_from_date'])[2]");
	By RadioButtonContact = By.xpath("//*[@id='contact-organization-request']/div[1]/input");
	By RadioButtonOrganization = By.xpath("//*[@id='contact-organization-request']/div[2]/input");
	By OrgEmail = By.xpath("(//*[@id='email'])[2]");
	By OrgPhoneNumber = By.xpath("(//*[@id='phones__number__0'])[2]");
	By OrgAddress1 = By.id("line_1");
	By OrgAddress2 = By.id("line_2");
	By OrgCity = By.id("city");
	By OrgState = By.id("state");
	By OrgZipcode = By.id("zipcode");
	By SaveOrg = By.id("organization-create");
	By OrganizationName = By.id("company_name");
	By OrgAdd = By.xpath("//*[@class='add_new_btn2 btn btn-30 btn-bg-blue pr-2 pl-2 ']");
	By Website = By.xpath("(//*[@id='website'])[1]");
	By PopupOpen = By.xpath("//*[contains(@class,'fadeIn')]//child::h5");
	@FindAll({ @FindBy(id = "customer-contact-request-count"), @FindBy(id = "customer-company-list-count") })
	WebElement TotalCount;

	By CustomerName = By.id("customer-name");
	@FindAll({ @FindBy(xpath = "//*[@id='request-create']//*[@id='customer-name-input-field']"),
			@FindBy(xpath = "//*[@class='min-hight-modal-pop bg-white p-2 mb-2']//*[@id='id_customer_group']") })
	WebElement CustomerField;

	@FindAll({ @FindBy(xpath = "//*[@id='id_user_customer']") })
	WebElement SubCustomerField;
	@FindAll({
			@FindBy(xpath = "//*[@id='contactdropdownlist' and contains(@style,'display:block;')]//child::div[1]//div[1]"),
			@FindBy(xpath = "//*[@id='contactdropdownlist2' and contains(@style,'display:block;')]//child::div[1]//div[1]"),
			@FindBy(xpath = "//*[text()=' No Data Found!']"),
			@FindBy(xpath = "//*[@id='contactdropdownlist3' and contains(@style,'display:block;')]//child::div[1]//div[1]") })
	WebElement CustomerListField;
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
	By JobTittle = By.id("contacts__job_title__0");
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

	@FindAll({
			@FindBy(xpath = "//*[@class='add_new_customer_button3']//button[@data-modalfetch='shorter_organization_contact_create']"),
			@FindBy(xpath = "//*[@class='add_new_customer_button2']//button[@data-modalfetch='shorter_organization_create']"),
			@FindBy(xpath = "//*[@class='add_new_customer_button no-add_new_customer_button']//button[@data-modalfetch='shorter_contact_create']") })
	WebElement AddCustomer;
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-list_aserpttbl']/tbody/tr[2]/td[2]/span"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-list_aserpttbl']/tbody/tr[2]/td[2]/span"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[2]/td[2]") })
	WebElement CustomerNameList;

	@FindAll({ @FindBy(xpath = "//*[@id='customer-contact-nav-menu']/div/ul/li[2]/a"),
			@FindBy(xpath = "//*[@id='customer-organization-nav-menu']/div/ul/li[2]/a") })
	WebElement RequestTab;

	@FindAll({ @FindBy(xpath = "//*[@data-automationid='customer-contact-request-create']"),
			@FindBy(xpath = "//*[@data-automationid='customer-organization-request-create']"),
			@FindBy(xpath = "//*[@data-automationid='request-create']") })
	public static WebElement CreateButton;

	@FindAll({ @FindBy(id = "customer-contact-nav-route"), @FindBy(id = "customer-company-nav-route"),
			@FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/div[1]/header/div/div/div"),
			@FindBy(xpath = "//*[@id='request-create']/header/div/div/div") })
	WebElement ListPage;

	@FindAll({
			@FindBy(xpath = "//*[@id='customer-contact-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[2]"),
			@FindBy(xpath = "//*[@id='customer-company-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[2]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]") })
	WebElement Edit;

	@FindAll({
			@FindBy(xpath = "//*[@id='customer-company-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[4]"),
			@FindBy(xpath = "//*[@id='customer-contact-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[4]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[4]") })
	WebElement Dispatch;

	@FindAll({
			@FindBy(xpath = "//*[@id='customer-contact-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[2]"),
			@FindBy(xpath = "//*[@id='customer-company-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[2]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[5]") })
	WebElement Start;

	@FindAll({
			@FindBy(xpath = "//*[@id='customer-contact-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[4]"),
			@FindBy(xpath = "//*[@id='customer-company-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[4]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[6]") })
	WebElement Complete;

	@FindAll({
			@FindBy(xpath = "//*[@id='customer-company-request-card-details']/div[2]/div[1]/div/div/div/div[3]/div[7]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']//tr[3]//td[1]//li[7]"),
			@FindBy(xpath = "//*[@id='customer-contact-request-card-details']/div[2]/div[1]/div/div/div/div[3]/div[7]") })
	WebElement Cancel;

	@FindAll({
			@FindBy(xpath = "//*[@id='customer-contact-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[2]"),
			@FindBy(xpath = "//*[@id='customer-company-request-card-details']/div[1]/div[1]/div/div/div/div[3]/div[2]") })
	WebElement Delete;

	@FindAll({
			@FindBy(xpath = "//*[@id='customer-contact-request-card-details']/div[1]/div[1]/div/div/div/div[1]/div/div/div[3]/span[2]"),
			@FindBy(xpath = "//*[@id='customer-company-request-card-details']/div[1]/div[1]/div/div/div/div[1]/div/div/div[3]/span[2]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[2]/td[3]") })
	WebElement ListRequestNo;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[3]/td[3]"),
			@FindBy(xpath = "//*[@id='customer-contact-request-card-details']/div[2]/div[1]/div/div/div/div[1]/div/div/div[3]/span[2]"),
			@FindBy(xpath = "//*[@id='customer-company-request-card-details']/div[2]/div[1]/div/div/div/div[1]/div/div/div[3]/span[2]") })
	WebElement CancelListRequestNo;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']//tr[2]//td[5]"),
			@FindBy(xpath = "//*[@id='contact-request-nav-status']//following::div[@id='customer-contact-request-card-profile-location'][1]"),
			@FindBy(xpath = "//*[@id='organization-request-nav-status']//following::div[@id='customer-contact-request-card-profile-location'][1]") })
	WebElement ListLocationName;

	@FindAll({
			@FindBy(xpath = "//*[@id='contact-request-nav-status']//following::span[@id='customer-contact-request-card-booking-time'][1]"),
			@FindBy(xpath = "//*[@id='organization-request-nav-status']//following::span[@id='customer-contact-request-card-booking-time'][1]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']//tr[2]//td[4]") })
	WebElement ListFromDate;

	@FindAll({
			@FindBy(xpath = "//*[@id='organization-request-nav-status']//following::span[@id='customer-contact-request-card-booking-time'][2]"),
			@FindBy(xpath = "//*[@id='contact-request-nav-status']//following::span[@id='customer-contact-request-card-booking-time'][2]") })
	WebElement ListToDate;

	@FindAll({ @FindBy(xpath = "//*[@id='contact-nav-status']//following::span[4]"),
			@FindBy(xpath = "//*[@id='organization-request-nav-status']//following::span[4]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']//tr[2]//td[7]//span") })
	WebElement Status;

	@FindAll({ @FindBy(xpath = "//*[@id='contact-nav-status']//following::span[25]"),
			@FindBy(xpath = "//*[@id='organization-request-nav-status']//following::span[25]"),
			@FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']//tr[3]//td[7]") })
	WebElement CancelStatus;

	@FindAll({ @FindBy(id = "customer-contact-request-search-filter"), @FindBy(id = "customer-company-search-filter"),
			@FindBy(id = "request-search-input-filter") })
	WebElement Search;

	@FindAll({ @FindBy(xpath = "//*[@id='job-show-details-timeline']/div[1]/div[2]/button/div"),
			@FindBy(xpath = "//*[@id='request-show-details-timeline']/div[1]/div[4]/button/div") })
	WebElement Filter;

	@FindAll({ @FindBy(id = "customer-company-request-from-date"),
			@FindBy(id = "customer-contact-request-from-date-filter"), @FindBy(id = "request-main-from-date-filter") })
	WebElement FilterFromDate;

	@FindAll({ @FindBy(id = "request-main-to-date-filter"), @FindBy(id = "customer-contact-request-to-date-filter"),
			@FindBy(id = "customer-company-request-to-date") })
	WebElement FilterToDate;

	@FindAll({ @FindBy(xpath = "//*[@id='id_customer_groups']"),
			@FindBy(xpath = "//*[@id='id_customer_orgcntgroup' and contains(@class,'field-input')]") })
	WebElement ContactNumber;
	static int parseInt;

	public int getCount() throws InterruptedException {
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOf(TotalCount)).getText();
		parseInt = Integer.parseInt(text2);
		return parseInt;
	}

	@FindAll({ @FindBy(xpath = "(//*[text()='Request No :'])[1]"), @FindBy(xpath = "//*[text()='No Data Available']"),
			@FindBy(xpath = "//*[text()='Customer']"), @FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement JobList;

	public int countValidation(int value) {
		if (value == 1) {
			int a = parseInt + 1;
			return a;
		} else if (value == 2) {
			this.visibility(JobList);
			String text2 = this.getText(TotalCount);
			int parseInt = Integer.parseInt(text2);
			return parseInt;
		}
		return value;
	}

	public void picKLocation() {
		this.inputText(Location, fakeState);
		if (!conditionChecking1(firstLocation, 2)) {
			do {
				this.clearField(Location);
				String fakeState = faker.address().state();
				this.inputText(Location, fakeState);
			} while (!conditionChecking1(firstLocation, 2));
		} else {
			this.mouseActionClick(firstLocation);
		}

	}

	public void currentPickerFromDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(StartDate, currentDate);
	}

	public void currentPickerFromDate1() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(StartDate, currentDate);
	}

	public void futureStartTime() throws InterruptedException {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(2).toString();
		this.inputText(StartTime, futureTime);

	}

	public void currentPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(EndDate, currentDate);
	}

	public String currentFilterPickerFromDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public String currentFilterPickerFromDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public String currentFilterPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public String currentFilterPickerToDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	static String ContactFirstName;
	static String ContactLastName;
	public static String ContactPhoneNumber;

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
			ContactPhoneNumber = this.getTextAttribute(PhoneNumber);
			this.inputText(JobTittle, fakeTittle);
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
			ContactPhoneNumber = this.getTextAttribute(PhoneNumber);
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

	public String clearFields(String value) {
		if (value.equals("Location")) {
			this.clearField(Location);
		} else if (value.equals("Description")) {
			this.clearField(Description);
		} else if (value.equals("Tittle")) {
			this.clearField(Tittle);
		} else if (value.equals("Tag")) {
			this.clearField(Tags);
		} else if (value.equals("Notes")) {
			this.clearField(Notes);
		} else if (value.equals("Search")) {
			this.clearField(Search);
		} else if (value.equals("StartDate")) {
			this.clearField(StartDate);
		} else if (value.equals("EndDate")) {
			this.clearField(EndDate);
		} else if (value.equals("StartTime")) {
			this.clearField(StartTime);
		} else if (value.equals("EndTime")) {
			this.clearField(EndTime);
		} else if (value.equals("TagRemove")) {
			this.mouseActionClick(TagRemove);
		} else if (value.equals("RemoveMultipleTag")) {
			int listWebElement = this.listWebElement(TagRemove);
			for (int i = 0; i < listWebElement - 1; i++) {
				this.mouseActionClick(TagRemove);
			}
		}
		return value;
	}

	public void clearAllFields() {
		List<String> asList = Arrays.asList("Location", "Description", "Tittle", "Notes");
		for (int i = 0; i < asList.size(); i++) {
			this.clearFields(asList.get(i));
		}
	}

	public Boolean conditionChecking(By element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 50);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking(WebElement element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 50);
			text = wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean valuePresentCondition(By element, String value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 20);
			text = wait.until(ExpectedConditions.textToBePresentInElementLocated(element, value));
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

	public Boolean conditionChecking1(By element, int value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, value);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking1(WebElement element, int value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, value);
			text = wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public String techcnianNotAvailable() {
		String name2 = responseMessage;
		int startIndex = name2.indexOf("Technician");
		if (startIndex != -1) {
			responseMessage = name2.substring(startIndex);
		}
		return responseMessage;
	}

	By Message = By.xpath("//*[@class='js-snackbar__message']");

	static String responseMessage;
	static String alternateResponseMessage;
	static int increment = 2;

	public String message(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message") || value.equals("FormMessage")) {
			if (this.conditionChecking(Message)) {
				responseMessage = this.getText(Message);
				this.invisible(Message);
			} else {
				do {
					Thread.sleep(10000);
					if (value.equals("Message")) {
						this.mouseActionClick(SaveButton);
					} else if (value.equals("FormMessage")) {
						this.mouseActionClick(SaveComplete);
					}
					if (this.conditionChecking(Message)) {
						responseMessage = this.getText(Message);
						this.invisible(Message);
						if (responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))
								|| responseMessage.equals(getPropertyValue("RequestCreatedMessage"))
								|| responseMessage.equals(getPropertyValue("RequestUpdatedMessage"))
								|| responseMessage.equals(getPropertyValue("ContactEmailAlreadyMessage"))
								|| responseMessage.equals(getPropertyValue("CompanyEmailAlreadyMessage"))
								|| responseMessage.equals(getPropertyValue("CompanyContactEmailMessage"))
								|| responseMessage.equals(getPropertyValue("CompanyAlreadyMessage"))
								|| responseMessage.equals(getPropertyValue("TechnicianAvailability"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateFunction") || value.equals("AlternateFormMessage")) {
			do {
				if (responseMessage.equals(getPropertyValue("ContactEmailAlreadyMessage"))
						|| responseMessage.equals(getPropertyValue("CompanyEmailAlreadyMessage"))
						|| responseMessage.equals(getPropertyValue("CompanyContactEmailMessage"))) {
					this.clearField(EmailField);
					String fakeEmail = faker.internet().safeEmailAddress();
					this.inputText(EmailField, fakeEmail);
					this.mouseActionClick(SaveButton);
				} else if (responseMessage.equals(getPropertyValue("CompanyAlreadyMessage"))) {
					this.clearField(OrganizationName);
					String fakeCompanyName = faker.company().name();
					this.inputText(OrganizationName, fakeCompanyName);
					this.mouseActionClick(SaveButton);
				} else if (responseMessage.equals(getPropertyValue("TechnicianAvailability"))) {
					this.scrollDown();
					this.mouseActionClick(Technician);
					increment++;
					this.mouseActionClick(
							By.xpath("//*[@id='technician_ids-autocomplete-list']//div[" + increment + "]"));
					this.mouseActionClick(SaveComplete);
				}
				if (this.conditionChecking(Message)) {
					responseMessage = this.getText(Message);
					this.invisible(Message);
					if (responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))
							|| responseMessage.equals(getPropertyValue("RequestCreatedMessage"))
							|| responseMessage.equals(getPropertyValue("RequestUpdatedMessage"))) {
						conditionCheck = false;
					}
				} else {
					do {
						Thread.sleep(10000);
						if (value.equals("Message")) {
							this.mouseActionClick(SaveButton);
						} else if (value.equals("AlternateFormMessage")) {
							this.mouseActionClick(SaveComplete);
						}
						if (this.conditionChecking(Message)) {
							responseMessage = this.getText(Message);
							this.invisible(Message);
							if (responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))
									|| responseMessage.equals(getPropertyValue("RequestCreatedMessage"))
									|| responseMessage.equals(getPropertyValue("RequestUpdatedMessage"))) {
								conditionCheck = false;
							} else {
								this.message("AlternateFunction");
							}
						}
					} while (conditionCheck);
				}
			} while (conditionCheck);

		}
		return responseMessage;
	}

	public String labelValidation(String value) throws InterruptedException {
		if (value.equals("Customer")) {
			this.mouseActionClick(CustomerNameList);
			this.mouseActionClick(RequestTab);
			this.visibility(JobList);
			this.getCount();
		} else if (value.equals("Global") || value.equals("ListLabel")) {
			this.mouseActionClick(Request);
			this.visibility(JobList);
			if (value.equals("Customer")) {
				this.visibility(ListCustomer);
			}
		} else if (value.equals("CreateLabel")) {
			this.mouseActionClick(CreateButton);
			this.visibility(SaveComplete);
		} else if (value.equals("EditLabel")) {
			String text = this.getText(ListLocationName);
			this.mouseActionClick(Edit);
			this.valuePresent(Location, text);
		} else if (value.equals("GlobalEdit")) {
			String text = this.getText(ListLocationName);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.valuePresent(Location, text);
		}
		return this.getText(ListPage);
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

	public void location(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Location, characters2048);
		}
	}

	public void description(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Description, characters2048);
		}
	}

	public void tags(String value) {
		if (value.equals("MaxValidation")) {
			this.tagValidation(Tags, characters256);
		} else if (value.equals("DuplicateTags")) {
			this.tagValidation(Tags, fakeFaxNumber);
			this.tagValidation(Tags, fakeFaxNumber);
		} else if (value.equals("MaxLimitTag")) {
			for (int i = 1; i < 66; i++) {
				String fakeTags = RandomStringUtils.randomAlphanumeric(4);
				this.tagValidation(Tags, fakeTags);
			}
		}
	}

	public String contactNumberField(String value) {
		if (value.equals("Prepopulate")) {
			return this.getTextAttribute(ContactNumber);
		}
		return value;
	}

	public void tittle(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Tittle, characters256);
		}
	}

	public void notes(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Notes, characters2048);
		}
	}

	String customer;

	public String customerField() {
		if (this.conditionChecking1(ContactNameError, 2)) {
			customer = this.getText(ContactNameError);
		} else {
			customer = "null";
		}
		return customer;
	}

	public String clickEvent(String value) {
		if (value.equals("ButtonPresent")) {
			return this.getText(SaveComplete);
		} else if (value.equals("ButtonClick")) {
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("RadioContact")) {
			this.mouseActionClick(SaveComplete);
			if (this.conditionChecking1(ErrorLocation, 2)) {
			} else {
				do {
					this.mouseActionClick(SaveComplete);
				} while (!this.conditionChecking1(ErrorLocation, 2));
			}
		} else if (value.equals("RadioOrganization")) {
			this.mouseActionClick(RadioButtonOrganization);
			this.mouseActionClick(SaveComplete);
			if (this.conditionChecking1(ErrorLocation, 2)) {
			} else {
				do {
					this.mouseActionClick(SaveComplete);
				} while (!this.conditionChecking1(ErrorLocation, 2));
			}
		}
		return value;
	}

	public void validData(String value) throws InterruptedException, IOException, AWTException {
		if (value.equals("Unassigned")) {
			this.dropDownByIndex(Priority, 2);
			this.currentPickerFromDate();
			this.futureStartTime();
			this.assertName(SelectTechnician, "Select Technician");
			this.clearField(Tags);
			this.clearField(Description);
		} else if (value.equals("Updated")) {
			this.clearField(Tags);
			this.clearField(Description);
			this.scrollDown();
			this.mouseActionClick(Technician);
			this.mouseActionClick(TechnicianFirstName);
			if (!(this.getTextAttribute(Technician).length() > 0)) {
				do {
					this.mouseActionClick(Technician);
					this.mouseActionClick(TechnicianFirstName);
				} while (!(this.getTextAttribute(Technician).length() > 0));
			}
		} else if (value.equals("Schedule") || value.equals("GlobalSchedule") || value.equals("CreateSchedule")) {
			if (value.equals("Schedule")) {
				this.visibility(JobList);
				this.customerName("DetailScreenCustomerName");
				this.mouseActionClick(CreateButton);
				this.customerName("PlaceHolderName");
			} else if (value.equals("CreateSchedule")) {
				this.customerName("PlaceHolderName");
			}
			this.dropDownByIndex(Priority, 2);
			this.inputText(Tittle, fakeTittle);
			this.currentPickerFromDate();
			this.inputText(StartTime, "10.00");
			this.invisible(Spinner);
			this.currentPickerToDate();
			this.inputText(EndTime, "18.00");
			this.invisible(Spinner);
			this.scrollDown();
			this.assertName(TechnicianLabel, "Technician");
			this.mouseActionClick(Technician);
			this.mouseActionClick(TechnicianSecoundName);
			if (!(this.getTextAttribute(Technician).length() > 0)) {
				do {
					this.mouseActionClick(Technician);
					this.mouseActionClick(TechnicianSecoundName);
				} while (!(this.getTextAttribute(Technician).length() > 0));
			}
		}
		this.scrollUp();
		this.picKLocation();
		this.inputText(Tittle, fakeTittle);
		this.inputText(Description, getPropertyValue("Description"));
		this.tagValidation(Tags, randomCharacter);
		this.inputText(Notes, getPropertyValue("Notes"));
		if (value.equals("Unassigned")) {
			this.attachmentFileCheck("URLCheck");
		}
		this.mouseActionClick(SaveComplete);
	}

	public void draft(String value) {
		if (value.equals("Draft")) {
			this.customerName("DetailScreenCustomerName");
			this.mouseActionClick(CreateButton);
			this.customerName("PlaceHolderName");
			this.mouseActionClick(Back);
			this.mouseActionClick(Yes);
		}
	}

	public void tiggerFunction(String value) {
		if (value.equals("Dispatach") || value.equals("GlobalDispatch")) {
			if (value.equals("GlobalDispatch")) {
				this.mouseActionClick(ThreeDots);
			}
			this.mouseActionClick(Dispatch);
		} else if (value.equals("Start") || value.equals("GlobalStart")) {
			if (value.equals("GlobalStart")) {
				this.mouseActionClick(ThreeDots);
			}
			this.mouseActionClick(Start);
		} else if (value.equals("Complete") || value.equals("GlobalComplete")) {
			if (value.equals("GlobalComplete")) {
				this.mouseActionClick(ThreeDots);
			}
			this.mouseActionClick(Complete);
		} else if (value.equals("Complete") || value.equals("GlobalComplete")) {
			if (value.equals("GlobalComplete")) {
				this.mouseActionClick(ThreeDots);
			}
			this.mouseActionClick(Complete);
		} else if (value.equals("Cancel") || value.equals("GlobalCancel")) {
			if (value.equals("GlobalCancel")) {
				this.mouseActionClick(ThreeDotsCancel);
			}
			this.mouseActionClick(Cancel);
		} else if (value.equals("Delete")) {
			this.mouseActionClick(Delete);
		}
		this.mouseActionClick(Yes);
	}

	public String errorField(String value) {
		if (value.equals("Location")) {
			return this.getText(ErrorLocation);
		} else if (value.equals("Description")) {
			return this.getText(ErrorDescription);
		} else if (value.equals("Tittle")) {
			return this.getText(ErrorTittle);
		} else if (value.equals("Tag")) {
			return this.getText(ErrorTags);
		} else if (value.equals("Notes")) {
			return this.getText(ErrorNotes);
		} else if (value.equals("CustomerField")) {
			return this.getText(ContactNameError);
		}
		return value;
	}

	@FindAll({ @FindBy(id = "customer-company-search-filter-enter"),
			@FindBy(id = "customer-contact-requset-search-button"), @FindBy(id = "request-search-enter") })
	WebElement SearchButton;

	static String listData;

	public String listValidation(String value) {
		if (value.equals("SearchData")) {
			this.elementtobeClickable(SearchButton);
			this.inputText(Search, listData);
			this.mouseActionClick(SearchButton);
		} else if (value.equals("Location")) {
			if (!this.conditionChecking(ListLocationName)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(ListLocationName));
			}
			listData = this.getText(ListLocationName);
		} else if (value.equals("RequestNo")) {
			if (!this.conditionChecking(ListRequestNo)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(ListRequestNo));
			}
			listData = this.getText(ListRequestNo);
		} else if (value.equals("CancelRequestNo")) {
			if (!this.conditionChecking(CancelListRequestNo)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(CancelListRequestNo));
			}
			listData = this.getText(CancelListRequestNo);
		} else if (value.equals("Status")) {
			listData = this.getText(Status);
		} else if (value.equals("CancelStatus")) {
			listData = this.getText(CancelStatus);
		} else if (value.equals("CustomerName")) {
			if (!this.conditionChecking(CustomerNameList)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(CustomerNameList));
			}
			listData = this.getText(CustomerNameList);
		} else if (value.equals("Invlaid")) {
			this.tagValidation(Search, "fdsshfsfkshfkshf");
		} else if (value.equals("Invalid")) {
			return this.getText(JobList);
		}
		return listData;
	}

	public String dateFrom() {
		String currentFilterPickerFromDate = this.currentFilterPickerFromDateFormat();
		return currentFilterPickerFromDate;
	}

	public String dateTo() {
		String currentFilterPickerToDate = this.currentFilterPickerToDateFormat();
		return currentFilterPickerToDate;
	}

	public void filterByDate() {
		this.mouseActionClick(Filter);
		String currentFilterPickerFromDate = this.currentFilterPickerFromDate();
		String currentFilterPickerToDate = this.currentFilterPickerToDate();
		this.inputText(FilterFromDate, currentFilterPickerFromDate);
		this.inputText(FilterToDate, currentFilterPickerToDate);
		this.mouseActionClick(Apply);
	}

	public String validateListFromDate() {
		if (!this.conditionChecking(ListFromDate)) {
			do {
				driver.navigate().refresh();
			} while (!this.conditionChecking(ListFromDate));
		}
		return this.getText(ListFromDate);
	}

	public String validateToDate() {
		if (!this.conditionChecking(ListToDate)) {
			do {
				driver.navigate().refresh();
			} while (!this.conditionChecking(ListToDate));
		}
		return this.getText(ListToDate);
	}

	public void createFunction() throws IOException, InterruptedException {
		if (!responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))) {
			this.message("AlternateFunction");
		}
	}

	By Attachment = By.xpath("//*[@id='scheduleDropzone']");
	@FindAll({ @FindBy(xpath = "//*[@id='previews']/div/div/div[1]/span"),
			@FindBy(xpath = "//*[@id='edit-list-file']/div/div[1]/span") })
	WebElement FirstAttachment;
	HttpURLConnection connection;
	List<String> list;

	public void attachmentFileCheck(String value)
			throws AWTException, MalformedURLException, IOException, InterruptedException {
		this.scrollDown();
		if (value.equals("URLCheck")) {
			this.mouseActionClick(Attachment);
			Thread.sleep(2000);
			BaseClass.attachmentFile(System.getProperty("user.dir") + "\\ImagePicture\\Free_Test_Data_1MB_PDF.pdf");
			if (!this.conditionChecking1(FirstAttachment, 3)) {
				do {
					this.mouseActionClick(Attachment);
					BaseClass.attachmentFile(
							System.getProperty("user.dir") + "\\ImagePicture\\Free_Test_Data_1MB_PDF.pdf");
				} while (!this.conditionChecking1(FirstAttachment, 3));
			}
		} else if (value.equals("CheckResponse") || value.equals("LoopNext")) {
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
}
