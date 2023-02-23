package com.zaigo.pageobjects;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
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

	private void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private int listWebElement(By element) {
		wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)).size();
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

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public void valuePresent(WebElement element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	private void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
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
	By EndTime = By.id("schedule_to_time");
	By Crew = By.id("crew-radio-button");
	By Single = By.id("technician-radio-button");
	By Technician = By.xpath("//*[@class='floating-input form-control user-view ']");
	By Technician1 = By.xpath("//*[@data-dropdownlist='technician-list']");
	By TechnicianFirstName = By
			.xpath("(//*[@class='p-2 list-hover-bg request-technician-list w-20-ellipsis w-100'])[1]");
	By TechnicianSecoundName = By
			.xpath("(//*[@class='p-2 list-hover-bg request-technician-list w-20-ellipsis w-100'])[2]");
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

	By Dispatch = By.xpath("(//*[@gloss='Dispatch'])[2]");
	By Start = By.xpath("(//*[@gloss='Start'])[1]");
	By Complete = By.xpath("(//*[@gloss='Completed'])[1]");
	By Cancel = By.xpath("//*[@gloss='Cancel']");
	By Delete = By.xpath("//*[@gloss='Delete']");
	By Back = By.xpath("//*[@alt='back_arrow']");
	By Label = By.xpath("(//*[@data-draftback='requestdraft'])[2]");
	By Label1 = By.xpath("//*[@data-draftback='requestdraft']");
	By SearchContactBox = By.id("customer-contact-request-search-filter");
	By SearchOrganizationBox = By.id("customer-company-search-filter");
	By Reset = By.xpath("//*[text()=' Reset Search']");
	By SearchLocation = By.id("customer-contact-request-card-profile-location");
	By InvlaidSearch = By.xpath("//*[@class='title']");
	By GlobalInvlaidSearch = By.xpath("//*[text()='No Result Found']");
	By Filter = By.xpath("//*[@class='eiconmenu20-white-filter']");
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
	By SelectTechnician = By.xpath(
			"//*[@class='d-flex mt-2']//*[@class='create-header page-header-left back-btn font-weight-bold black-text ']");
	By StatusRequest = By.id("customer-contact-request-card-status");
	By Edit = By.xpath("//*[@class='fa fa-pencil ']");
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
	By ThreeDots = By.xpath("(//*[@class='fa fa-ellipsis-v'])[2]");
	By GlobalEdit = By.xpath("(//*[@data-n-linkto='main_request_edit'])[1]");
	By GlobalDispatch = By.xpath("(//*[@data-tabposition=\"undefined\"])[10]");
	By GlobalStart = By.xpath("(//*[@data-tabposition=\"undefined\"])[13]");
	By GlobalComplete = By.xpath("(//*[@data-tabposition=\"undefined\"])[16]");
	By GlobalCancel = By.xpath("(//*[@data-tabposition=\"undefined\"])[40]");
	By ThreeDotsCancel = By.xpath("(//*[@class='fa fa-ellipsis-v'])[3]");
	By GlobalCancelledStatus = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[12]");
	By GlobalSearchRequestNo = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[2]");
	By GlobalCustomerName = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[1]");
	By GlobalLocation = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[4]");
	By GlobalDate = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[3]");
	By GlobalSearchBox = By.id("request-search-input-filter");
	By GlobalFrom = By.xpath("(//*[@data-setdatelimitmax='schedule_from_date'])[1]");
	By GlobalTo = By.xpath("(//*[@data-setdatelimitmax='schedule_from_date'])[2]");
	By RadioButtonOrg = By.xpath("(//*[@class='mr-2 mb-2'])[2]");
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
	@FindAll({ @FindBy(id = "customer-contact-request-count"), @FindBy(id = "customer-company-list-count") })
	WebElement TotalCount;
	@FindAll({ @FindBy(xpath = "//*[@id='customer-company-search-filter']"),
			@FindBy(xpath = "//*[@id='job-search-input-filter']"),
			@FindBy(xpath = "//*[@id='customer-contact-request-search-filter']") })
	WebElement Search;

	By CustomerName = By.id("customer-name");
	@FindAll({ @FindBy(xpath = "//*[@id='request-create']//*[@id='customer-name-input-field']"),
			@FindBy(xpath = "//*[@class='col-lg-12 mt-3 switchcontact d-block']//*[@id='id_user_customer']"),
			@FindBy(xpath = "//*[@class='min-hight-600  bg-white p-2']//*[@id='id_customer_group']") })
	WebElement CustomerField;

	static int parseInt;

	public int getCount() throws InterruptedException {
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOf(TotalCount)).getText();
		parseInt = Integer.parseInt(text2);
		return parseInt;
	}

	@FindAll({ @FindBy(xpath = "(//*[text()='Request No :'])[1]"), @FindBy(xpath = "//*[text()='No Data Available']"),
			@FindBy(xpath = "//*[text()='Customer']") })
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

	public String customerContactRequestListPage() throws InterruptedException {
		String getName = this.getText(ContactListName);
		this.mouseActionClick(ContactListName);
		this.assertName(ContactListName, getName);
		this.mouseActionClick(ClickContactRequest);
		String customerName2 = this.customerName("DetailScreenCustomerName");
		this.assertName(CreateContactRequest, "Create Request");
		this.visibility(JobList);
		this.getCount();
		this.mouseActionClick(CreateContactRequest);
		return customerName2;
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

	public String requestLandPage() {
		String text = this.getText(CreateRequestLabel);
		return text;
	}

	public void mandatoryLocationField() {
		this.validationTab(Location, "       ");

	}

	public String locationError() {
		String text = this.getText(ErrorLocation);
		return text;
	}

	public void picKLocation() {
		this.inputText(Location, fakeState);
		this.mouseActionClick(firstLocation);
	}

	public void maxValidationLocationField() throws InterruptedException, IOException {
		Thread.sleep(2000);
		this.validationTab(Location, getPropertyValue("2048Characters"));
	}

	public void clearLocation() {
		this.clearField(Location);
	}

	public void maxValidationTittle() throws InterruptedException {
		this.validationTab(Tittle, characters256);
	}

	public String tittleError() {
		String text = this.getText(ErrorTittle);
		return text;
	}

	public void clearTittle() {
		this.clearField(Tittle);
	}

	public void mandatoryDescriptionField() {
		this.validationTab(Description, " ");
	}

	public String descriptionError() {
		String text = this.getText(ErrorDescription);
		return text;
	}

	public void maxValidationDescription() throws IOException {
		this.validationTab(Description, getPropertyValue("2048Characters"));
	}

	public void clearDescription() {
		this.clearField(Description);
		this.inputText(Description, "Data");
	}

	public void maxCharacterTag() {
		this.tagValidation(Tags, characters256);
	}

	public String tagsError() {
		String text = this.getText(ErrorTags);
		return text;
	}

	public void clearTag() {
		this.clearField(Tags);

	}

	public void duplicateTags() {
		this.tagValidation(Tags, fakeFaxNumber);
		this.tagValidation(Tags, fakeFaxNumber);
	}

	public void removeTags() {
		this.mouseActionClick(TagRemove);
	}

	public void maxTagCountValidation() {
		for (int i = 1; i < 66; i++) {
			String fakeTags = RandomStringUtils.randomAlphanumeric(4);
			this.tagValidation(Tags, fakeTags);
		}
	}

	public void removeMultipleTags() {
		for (int i = 1; i < 32; i++) {
			By TagRemove = By.xpath("(//*[@class='tag__remove'])[" + i + "]");
			this.mouseActionClick(TagRemove);
		}

	}

	public void maxValidationNotes() throws IOException {
		this.validationTab(Notes, getPropertyValue("2048Characters"));
	}

	public String notesError() {
		String text = this.getText(ErrorNotes);
		return text;
	}

	public void clearNotes() {
		this.clearField(Notes);
	}

	public void currentPickerFromDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(StartDate, currentDate);
	}

	public void futureStartTime() throws InterruptedException {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(2).toString();
		this.inputText(StartTime, futureTime);

	}

	public String fromDateTimeScheduleRequest() throws IOException, InterruptedException {
		this.picKLocation();
		this.dropDownByIndex(Priority, 2);
		this.currentPickerFromDate();
		this.futureStartTime();
		this.assertName(SelectTechnician, "Select Technician");
		this.clearField(Tags);
		this.inputText(Tittle, fakeTittle);
		this.clearField(Description);
		this.inputText(Description, getPropertyValue("Description"));
		this.inputText(Notes, getPropertyValue("Notes"));
		String text = this.getText(SaveComplete);
		this.mouseActionClick(SaveComplete);
		return text;

	}

	public String editRequests() throws IOException {
		this.picKLocation();
		this.clearField(Tags);
		this.inputText(Tittle, fakeTittle);
		this.clearField(Description);
		this.inputText(Description, getPropertyValue("Description"));
		this.inputText(Notes, getPropertyValue("Notes"));
		this.mouseActionClick(Technician);
		this.mouseActionClick(TechnicianFirstName);
		String text = this.getText(SaveComplete);
		this.mouseActionClick(SaveComplete);
		return text;
	}

	public String createdMessage() {
		String text = this.getText(RequestCreatedMessage);
		return text;

	}

	public String requestStatus() {
		String text = this.getText(StatusRequest);
		return text;

	}

	public void editRequest() throws InterruptedException {
		this.mouseActionClick(Edit);
		Thread.sleep(15000);
		this.scrollDown();
		this.assertName(SelectTechnician, "Select Technician");
		this.mouseActionClick(Technician);
		this.mouseActionClick(TechnicianFirstName);
		this.mouseActionClick(SaveComplete);

	}

	public String updatedMessage() {
		String text = this.getText(RequestUpdatedMessage);
		return text;

	}

	public void customerContactRequest() throws InterruptedException {
		Thread.sleep(2000);
		this.mouseActionClick(CreateContactRequest);
		Thread.sleep(2000);

	}

	public void currentPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(EndDate, currentDate);
	}

	public void createdRequest() throws InterruptedException, IOException {
		this.picKLocation();
		this.dropDownByIndex(Priority, 2);
		this.inputText(Tittle, fakeTittle);
		this.inputText(Description, getPropertyValue("Description"));
		this.currentPickerFromDate();
		this.currentPickerToDate();
		this.inputText(StartTime, "10.00");
		this.inputText(EndTime, "18.00");
		Thread.sleep(5000);
		this.scrollDown();
		this.mouseActionClick(Crew);
		this.mouseActionClick(Single);
		this.assertName(TechnicianLabel, "Technician");
		this.mouseActionClick(Technician);
		this.mouseActionClick(TechnicianSecoundName);
		this.tagValidation(Tags, randomCharacter);
		this.inputText(Notes, getPropertyValue("Notes"));
		this.mouseActionClick(SaveComplete);

	}

	public String dispatchTiggerFunction() {
		this.mouseActionClick(Dispatch);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestDispatchMessage);
		return text;
	}

	public String startTiggerFunction() {
		this.mouseActionClick(Start);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestStartedMessgae);
		return text;
	}

	public String completedTiggerFunction() {
		this.mouseActionClick(Complete);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestCompletedMessage);
		return text;
	}

	public String cancelledTigerFunction() {
		this.mouseActionClick(Cancel);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestCancelledMessage);
		return text;

	}

	public String cancelRequestStatus() {
		String text = this.getText(CancelledStatus);
		return text;

	}

	public void draftRequest() throws IOException, InterruptedException {
		String text = this.getText(CustomerName);
		this.mouseActionClick(CreateContactRequest);
		this.valuePresent(CustomerField, text);
		this.mouseActionClick(Back);
		this.mouseActionClick(Yes);
		this.assertName(RequestDraftedMessage, "Request drafted");

	}

	public String deletedTiggerFunction() {
		this.mouseActionClick(Delete);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestDeletedMessage);
		return text;
	}

	public String searchRequestNo() {
		String text = this.getText(RequestNo);
		this.tagValidation(SearchContactBox, text);
		String text2 = this.getText(RequestNo);
		return text2;

	}

	public void resetOption() {
		this.mouseActionClick(Reset);

	}

	public String searchLocation() {
		String text = this.getText(SearchLocation);
		this.tagValidation(SearchContactBox, text);
		String text2 = this.getText(SearchLocation);
		return text2;

	}

	public void clearSearch() {
		this.clearField(SearchContactBox);

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

	public void filterByDate() {
		this.mouseActionClick(Filter);
		String currentFilterPickerFromDate = this.currentFilterPickerFromDate();
		String currentFilterPickerToDate = this.currentFilterPickerToDate();
		this.inputText(FilterContactFrom, currentFilterPickerFromDate);
		this.inputText(FilterContactTo, currentFilterPickerToDate);
		this.mouseActionClick(Apply);

	}

	public String validateListFromDate() {
		String text = this.getText(ScheduleFrom);
		return text;

	}

	public String validateToDate() {
		String text = this.getText(ScheduleTo);
		return text;

	}

	public void requestLabel() {
		this.assertName(RequestLabelNo, "Request No :");

	}

	public String invalidSearch() {
		this.tagValidation(SearchContactBox, randomCharacter);
		String text2 = this.getText(InvlaidSearch);
		return text2;

	}

	public void customerOrganizationRequestListPage() throws InterruptedException {
		String text = this.getText(OrganizationListName);
		this.mouseActionClick(OrganizationListName);
		this.assertName(OrganizationListName, text);
		this.mouseActionClick(ClickOrganizationRequest);
		this.assertName(CreateOrganizationRequest, "Create Request");
		this.mouseActionClick(CreateOrganizationRequest);
	}

	public void organizationContactCreate() {
		this.inputText(OrgContactName, fakeFirstName);
		this.mouseActionClick(OrgContactAdd);
		this.inputText(OrganizationFirstName, fakeFirstName);
		this.inputText(OrganizationLastName, fakeLastName);
//		this.inputText(OrganizationEmail, fakeEmail);
		this.inputText(OrganizationPhoneNumber, fakePhoneNumber);
		this.inputText(OrganizationRequestTittle, fakeTittle);
		this.mouseActionClick(OrganizationContactSave);
	}

	public String responseMessageCreateContact() {
		String text2 = this.getText(ContactCreateMessage);
		return text2;

	}

	public void customerOrganizationRequest() throws InterruptedException {
		Thread.sleep(2000);
		this.mouseActionClick(CreateOrganizationRequest);
		Thread.sleep(2000);

	}

	public void draftRequest1() throws IOException, InterruptedException {
		this.mouseActionClick(CreateOrganizationRequest);
		Thread.sleep(5000);
		this.mouseActionClick(Back);
		this.mouseActionClick(Yes);
		this.assertName(RequestDraftedMessage, "Request drafted");

	}

	public String searchRequestNo1() {
		String text = this.getText(RequestNo);
		this.tagValidation(SearchOrganizationBox, text);
		String text2 = this.getText(RequestNo);
		return text2;

	}

	public String searchLocation1() {
		String text = this.getText(SearchLocation);
		this.tagValidation(SearchOrganizationBox, text);
		String text2 = this.getText(SearchLocation);
		return text2;

	}

	public void filterByDate1() {
		this.mouseActionClick(Filter);
		String currentFilterPickerFromDate = this.currentFilterPickerFromDate();
		String currentFilterPickerToDate = this.currentFilterPickerToDate();
		this.inputText(FilterOrganizationFrom, currentFilterPickerFromDate);
		this.inputText(FilterOrganizationTo, currentFilterPickerToDate);
		this.mouseActionClick(Apply);

	}

	public void clearSearch1() {
		this.clearField(SearchOrganizationBox);

	}

	public String invalidSearch1() {
		this.tagValidation(SearchOrganizationBox, randomCharacter);
		String text2 = this.getText(InvlaidSearch);
		return text2;

	}

	public void module() throws InterruptedException {
		this.assertName(AssertDashboard, "Company Performance");
		this.mouseActionClick(Request);
		this.assertName(ListCustomer, "Customer");
		this.mouseActionClick(CreateGlobalRequest);

	}

	public void mandatoryContactField() throws InterruptedException {
		this.assertName(Label1, "Create Request");
		Thread.sleep(5000);
		this.mouseActionClick(SaveComplete);

	}

	public String errorContact() {
		String text = this.getText(ContactNameError);
		return text;
	}

	public void contactCreation() throws InterruptedException {
		this.inputText(GlobalCustomer, fakeFirstName);
		this.mouseActionClick(Add);
		this.inputText(FirstName, fakeFirstName);
		this.inputText(LastName, fakeLastName);
		this.inputText(Email, fakeEmail);
		this.inputText(Phone, fakePhoneNumber);
		this.inputText(Address1, fakeAddress1);
		this.inputText(Address2, fakeAddress2);
		this.inputText(CityName, fakeCity);
		this.inputText(StateName, fakeState);
		this.inputText(Zipcode, fakeZipcode);
		Thread.sleep(2000);
		this.mouseActionClick(Save);

	}

	public void globalRequest() throws InterruptedException {
		Thread.sleep(2000);
		this.mouseActionClick(CreateGlobalRequest);
		Thread.sleep(2000);

	}

	public void switchOrganization() throws InterruptedException {
		this.assertName(Label1, "Create Request");
		this.mouseActionClick(RadioButtonOrg);
		Thread.sleep(2500);

	}

	public String globalRequestStatus() {
		String text = this.getText(GlobalStatus);
		return text;

	}

	public String globalDispatchTiggerFunction() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(GlobalDispatch);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestDispatchMessage);
		return text;
	}

	public String globalStartTiggerFunction() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(GlobalStart);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestStartedMessgae);
		return text;
	}

	public String globalCompletedTiggerFunction() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(GlobalComplete);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestCompletedMessage);
		return text;
	}

	public String globalCancelledTigerFunction() {
		this.mouseActionClick(ThreeDotsCancel);
		this.mouseActionClick(GlobalCancel);
		this.mouseActionClick(Yes);
		String text = this.getText(RequestCancelledMessage);
		return text;

	}

	public String cancelGlobalRequestStatus() {
		String text = this.getText(GlobalCancelledStatus);
		return text;

	}

	public String searchCustomerName() {
		String text = this.getText(GlobalCustomerName);
		this.tagValidation(GlobalSearchBox, text);
		String text2 = this.getText(GlobalCustomerName);
		return text2;

	}

	public String searchRequestNo2() {
		String text = this.getText(GlobalSearchRequestNo);
		this.tagValidation(GlobalSearchBox, text);
		String text2 = this.getText(GlobalSearchRequestNo);
		return text2;

	}

	public String searchLocation2() {
		String text = this.getText(GlobalLocation);
		this.tagValidation(GlobalSearchBox, text);
		String text2 = this.getText(GlobalLocation);
		return text2;

	}

	public void filterByDate2() throws InterruptedException {
		this.mouseActionClick(Filter);
		String currentFilterPickerFromDate = this.currentFilterPickerFromDate();
		String currentFilterPickerToDate = this.currentFilterPickerToDate();
		this.inputText(GlobalFrom, currentFilterPickerFromDate);
		this.inputText(GlobalTo, currentFilterPickerToDate);
		Thread.sleep(2000);
		this.mouseActionClick(Apply);

	}

	public String validateScheduledDate() {
		String text = this.getText(GlobalDate);
		return text;

	}

	public void clearSearch2() {
		this.clearField(GlobalSearchBox);

	}

	public String invalidSearch2() {
		this.tagValidation(GlobalSearchBox, randomCharacter);
		String text2 = this.getText(GlobalInvlaidSearch);
		return text2;

	}

	public void mandatoryOrganizationField() throws InterruptedException {
		this.assertName(Label1, "Create Request");
		this.mouseActionClick(RadioButtonOrg);
		Thread.sleep(5000);
		this.scrollDown();
		this.mouseActionClick(SaveComplete);
		this.scrollUp();

	}

	public void organizationCreation() throws InterruptedException {
		this.inputText(GlobalCustomer, fakeCompanyName);
		this.mouseActionClick(OrgAdd);
		this.inputText(OrganizationName, fakeCompanyName);
		this.inputText(OrgPhoneNumber, fakePhoneNumber);
//		this.inputText(OrgEmail, fakeEmail);
		this.inputText(Website, fakeWebsite);
		this.inputText(OrgAddress1, fakeAddress1);
		this.inputText(OrgAddress2, fakeAddress2);
		this.inputText(OrgCity, fakeCity);
		this.inputText(OrgState, fakeState);
		this.inputText(OrgZipcode, fakeZipcode);
		Thread.sleep(2000);
		this.mouseActionClick(SaveOrg);

	}

	public String clearValidation(String value) {
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
		} else if (value.equals("OrganizationSearch")) {
			this.clearField(SearchOrganizationBox);
		} else if (value.equals("GlobalSearch")) {
			this.clearField(GlobalSearchBox);
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

	static String ListData;

	public String listData(String value) {
		if (value.equals("SearchData")) {
			this.tagValidation(Search, ListData);
		} else if (value.equals("CustomerRequestNo1")) {
			ListData = this.getText(RequestNo);
			return ListData;
		} else if (value.equals("CustomerLocation")) {
			ListData = this.getText(SearchLocation);
			return ListData;
		} else if (value.equals("CustomerRequestNo2")) {
			ListData = this.getText(RequestNo2);
			return ListData;
		}
		return value;
	}

	public void requestStatusCreation() {
		String text = this.getText(SearchLocation);
		this.mouseActionClick(Edit);
		this.valuePresent(Location, text);

	}

	public void clearAllFields(String value) {
		if (value.equals("Page")) {
			List<String> asList = Arrays.asList("Location", "Description", "Tittle", "Notes");
			for (int i = 0; i < asList.size(); i++) {
				this.clearValidation(asList.get(i));
			}
		}
	}

	public String dateFrom() {
		String currentFilterPickerFromDate = this.currentFilterPickerFromDateFormat();
		return currentFilterPickerFromDate;
	}

	public String dateTo() {
		String currentFilterPickerToDate = this.currentFilterPickerToDateFormat();
		return currentFilterPickerToDate;
	}
}
