package com.zaigo.pageobjects;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.github.javafaker.Faker;

public class JobPage extends BaseClass {

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

	public void timePicker() {
		LocalTime now = LocalTime.now();
		String pastTime = now.minusMinutes(30).toString();
		String futureTime = now.plusHours(2).toString();
	}

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);

	By ContactListName = By.xpath("(//*[@data-n-linkto='customer_contact_timeline'])[1]");
	By OrganizationListName = By.xpath("(//*[@data-n-linkto='customer_organization_timeline'])[1]");
	By CustomerName = By.id("customer-name");
	By ClickJob = By.xpath("//*[@data-menuswitcher='cstmr-contact-job']");
	By ClickOrganizationJob = By.xpath("//*[@data-menuswitcher='cstmr-organization-job']");
	By JobLabel = By.id("//*[text()='Total Job ']");
	By CreateJob = By.xpath("//*[@data-automationid='customer-contact-job-create']");
	By CreateOrganizationJob = By.xpath("//*[@data-automationid='customer-organization-job-create']");
	By CreateJobLabel = By.xpath("//*[@data-menuselector='job-menu']");
	By CreateGlobalJob = By.xpath("//*[@data-automationid='job-create']");
	By Job = By.id("job-menu");
	By GlobalCustomer = By.id("id_customer_group");
	By ContactNameError = By.id("id_customer_group_error");
	By Add = By.xpath("//*[@class='add_new_btn btn btn-30 btn-bg-blue pr-2 pl-2 ']");
	By FirstName = By.id("first_name");
	By LastName = By.id("last_name");
	By Email = By.id("email");
	By OrgEmail = By.xpath("(//*[@id='email'])[2]");
	By Phone = By.id("phones__number__0");
	By OrgPhoneNumber = By.xpath("(//*[@id='phones__number__0'])[2]");
	By OrgAddress1 = By.id("line_1");
	By OrgAddress2 = By.id("line_2");
	By OrgCity = By.id("city");
	By OrgState = By.id("state");
	By OrgZipcode = By.id("zipcode");
	By Address1 = By.id("addresses__line_1__0");
	By Address2 = By.id("addresses__line_2__0");
	By StateName = By.id("addresses__state__0");
	By CityName = By.id("addresses__city__0");
	By Zipcode = By.id("addresses__zipcode__0");
	By Save = By.id("contact-create");
	By SaveOrg = By.id("organization-create");
	By Website = By.xpath("(//*[@id='website'])[1]");
	By ContactCreatedMessage = By.xpath("//*[text()='Customer created successfully']");

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
	By JobCreatedMessage = By.xpath("//*[text()='Job Created Successfully']");
	By JobUpdatedMessage = By.xpath("//*[text()='Job Updated Successfully']");
	By JobDispatchMessage = By.xpath("//*[text()='Job dispatched successfully']");
	By JobStartedMessgae = By.xpath("//*[text()='Job started successfully']");
	By JobCompletedMessage = By.xpath("//*[text()='Job completed successfully']");
	By JobCancelledMessage = By.xpath("//*[text()='Job cancelled successfully']");
	By JobDeletedMessage = By.xpath("//*[text()='Job deleted successfully']");
	By JobDraftedMessage = By.xpath("//*[text()='Job Drafted']");
	By StatusJob = By.id("customer-contact-request-card-status");
	By Edit = By.xpath("(//*[@class='fa fa-pencil '])[1]");
	By SelectTechnician = By.xpath(
			"//*[@class='d-flex mt-2']//*[@class='create-header page-header-left back-btn font-weight-bold black-text ']");
	By Yes = By.xpath("//*[text()='Yes']");
	By No = By.xpath("//*[text()='No']");
	By Dispatch = By.xpath("(//*[@gloss='Dispatch'])[2]");
	By Start = By.xpath("(//*[@gloss='Start'])[1]");
	By Complete = By.xpath("(//*[@gloss='Completed'])[1]");
	By Cancel = By.xpath("//*[@gloss='Cancel']");
	By Delete = By.xpath("//*[@gloss='Delete']");
	By Back = By.xpath("//*[@alt=' back_arrow']");
	By Label = By.xpath("//*[@data-draftback='jobdraft']");
	By JobNo = By.xpath("(//*[@class='id-number'])[1]");
	By SearchBox = By.id("customer-contact-job-search");
	By SearchOrganizationBox = By.id("customer-company-job-search");
	By Reset = By.xpath("//*[text()=' Reset Search']");
	By SearchLocation = By.id("customer-contact-request-card-profile-location");
	By InvlaidSearch = By.xpath("//*[@class='title']");
	By GlobalInvlaidSearch = By.xpath("//*[text()='No Result Found']");
	By Filter = By.xpath("//*[@class='eiconmenu20-white-filter']");
	By FilterFrom = By.id("customer-contact-job-filter-from-date");
	By FilterOrganizationFrom = By.id("customer-company-job-from-date");
	By FilterTo = By.id("customer-contact-job-filter-to-date");
	By FilterOrganizationTo = By.id("customer-company-job-to-date");
	By Apply = By.xpath("//*[@data-automationid='contact-apply']");
	By JobType = By.id("job-type-filter");
	By ErrorToTime = By.xpath("//*[text()='Appointment from date,time and Appointment to date needed']");
	By CrewSize = By.id("crew_size");
	By CrewTech = By.id("settings-category-user-view");
	By SelectTech = By.xpath("(//*[@type='checkbox'])[1]");
	By Status = By.xpath("//*[text()='Status  ']");
	By OrganizationFirstName = By.id("contacts__first_name__0");
	By OrganizationLastName = By.id("contacts__last_name__0");
	By OrganizationEmail = By.id("contacts__email__0");
	By OrganizationPhoneNumber = By.id("contacts__phone__0");
	By OrganizationJobTittle = By.id("contacts__job_title__0");
	By OrganizationContactSave = By.id("organization-contact-create");
	By OrgContactName = By.id("id_user_customer");
	By OrgContactAdd = By.xpath("//*[@class='add_new_btn3 btn-30 btn btn-bg-blue pr-2 pl-2']");
	By OrgAdd = By.xpath("//*[@class='add_new_btn2 btn btn-30 btn-bg-blue pr-2 pl-2 ']");
	By ContactName = By.id("customer-name-input-field");

	public JobPage(WebDriver driver) {
		this.driver = driver;

	}

	public void organizationContactCreate() throws InterruptedException {
		this.inputText(OrgContactName, fakeFirstName);
		this.mouseActionClick(OrgContactAdd);
		this.inputText(OrganizationFirstName, fakeFirstName);
		this.inputText(OrganizationLastName, fakeLastName);
		this.inputText(OrganizationEmail, fakeEmail);
		this.inputText(OrganizationPhoneNumber, fakePhoneNumber);
		this.inputText(OrganizationJobTittle, fakeTittle);
		Thread.sleep(2000);
		this.mouseActionClick(OrganizationContactSave);
	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	private void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	private void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

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
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	public String customerContactJobListPage() throws InterruptedException {
		String text = this.getText(ContactListName);
		this.mouseActionClick(ContactListName);
		this.assertName(ContactListName, text);
		this.mouseActionClick(ClickJob);
		String customerName = this.customerName("DetailScreenCustomerName");
		this.assertName(CreateJob, "Create Job");
		this.mouseActionClick(CreateJob);
		return customerName;
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

	public void customerOrganizationJobListPage() throws InterruptedException {
		String text = this.getText(OrganizationListName);
		this.mouseActionClick(OrganizationListName);
		this.assertName(OrganizationListName, text);
		this.mouseActionClick(ClickOrganizationJob);
		this.assertName(CreateOrganizationJob, "Create Job");
		this.mouseActionClick(CreateOrganizationJob);
	}

	public String jobLandPage() {
		String text = this.getText(CreateJobLabel);
		return text;
	}

//	@FindBys({
//	@FindBy(xpath="//*[text()='Appointment from date,time and Appointment to date needed']");
//	@FindBy(xpath="//*[text()='Appointment to date needed']")
//	})

	/* Error field */
	/* Start */
	public String locationError() {
		String text = this.getText(ErrorLocation);
		return text;
	}

	public String descriptionError() {
		String text = this.getText(ErrorDescription);
		return text;
	}

	public String tittleError() {
		String text = this.getText(ErrorTittle);
		return text;
	}

	public String notesError() {
		String text = this.getText(ErrorNotes);
		return text;
	}

	public String tagsError() {
		String text = this.getText(ErrorTags);
		return text;
	}

	public String errorTimePicker() {
		String text = this.getText(EalierTime);
		return text;
	}

	public String errorMismatchTime() {
		String text = this.getText(TimeMismatch);
		return text;

	}

	public String errorToTime() {
		String text = this.getText(ErrorToTime);
		return text;

	}

	public String createdMessage() {
		String text = this.getText(JobCreatedMessage);
		return text;

	}

	public String updatedMessage() {
		String text = this.getText(JobUpdatedMessage);
		return text;

	}

	public String errorContact() {
		String text = this.getText(ContactNameError);
		return text;
	}

	/* End */
	/* Fields Validations */
	/* Start */

	By AssertDashboard = By.xpath("//*[text()=' Company Performance']");
	By ListCustomer = By.xpath("//*[text()='Customer']");

	public void module() throws InterruptedException {
		this.assertName(AssertDashboard, "Company Performance");
		this.mouseActionClick(Job);
		this.assertName(ListCustomer, "Customer");
		this.mouseActionClick(CreateGlobalJob);

	}

	public void mandatoryContactField() throws InterruptedException {
		this.assertName(Label, "Create Job");
		Thread.sleep(5000);
		this.mouseActionClick(SaveComplete);

	}

	By RadioButtonOrg = By.xpath("(//*[@class='mr-2 mb-2'])[2]");

	public void mandatoryOrganizationField() throws InterruptedException {
		this.assertName(Label, "Create Job");
		this.mouseActionClick(RadioButtonOrg);
		Thread.sleep(5000);
		this.scrollDown();
		this.mouseActionClick(SaveComplete);
		this.scrollUp();

	}

	public void switchOrganization() throws InterruptedException {
		this.assertName(Label, "Create Job");
		this.mouseActionClick(RadioButtonOrg);
		Thread.sleep(2500);

	}

	By ContactCreateMessage = By.xpath("//*[text()='Customer created successfully']");

	public String responseMessageCreateContact() {
		String text2 = this.getText(ContactCreateMessage);
		return text2;

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

	By OrganizationName = By.id("company_name");

	public void organizationCreation() throws InterruptedException {
		this.inputText(GlobalCustomer, fakeCompanyName);
		this.mouseActionClick(OrgAdd);
		this.inputText(OrganizationName, fakeCompanyName);
		this.inputText(OrgPhoneNumber, fakePhoneNumber);
		this.inputText(OrgEmail, fakeEmail);
		this.inputText(Website, fakeWebsite);
		this.inputText(OrgAddress1, fakeAddress1);
		this.inputText(OrgAddress2, fakeAddress2);
		this.inputText(OrgCity, fakeCity);
		this.inputText(OrgState, fakeState);
		this.inputText(OrgZipcode, fakeZipcode);
		Thread.sleep(2000);
		this.mouseActionClick(SaveOrg);

	}

	public void mandatoryDescriptionField() {
		this.validationTab(Description, " ");
	}

	public void mandatoryValidationContactName() {
		this.mouseActionClick(SaveComplete);

	}

	public void maxValidationDescription() throws IOException {
		this.validationTab(Description, getPropertyValue("2048Characters"));
	}

	public void mandatoryLocationField() {
		this.validationTab(Location, "       ");

	}

	public void maxValidationLocationField() throws InterruptedException, IOException {
//		Thread.sleep(2000);
		this.validationTab(Location, getPropertyValue("2048Characters"));
	}

	public void picKLocation() {
		this.inputText(Location, fakeState);
		this.mouseActionClick(firstLocation);
	}

	public void maxValidationTittle() throws InterruptedException {
//		Thread.sleep(6000);
		this.validationTab(Tittle, characters256);
	}

	public void maxValidationNotes() throws IOException {
		this.validationTab(Notes, getPropertyValue("2048Characters"));
	}

	public void duplicateTags() {
		this.tagValidation(Tags, fakeFaxNumber);
		this.tagValidation(Tags, fakeFaxNumber);
	}

	public void maxCharacterTag() {
		this.tagValidation(Tags, characters256);
	}

	public void maxTagCountValidation() {
		for (int i = 1; i < 66; i++) {
			String fakeTags = RandomStringUtils.randomAlphanumeric(4);
			this.tagValidation(Tags, fakeTags);
		}
	}

	public void currentPickerFromDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(StartDate, currentDate);
	}

	public String currentFilterPickerFromDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public void currentPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(EndDate, currentDate);
	}

	public String currentFilterPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public void pastTime() {
		LocalTime now = LocalTime.now();
		String pastTime = now.minusMinutes(30).toString();
		this.inputText(StartTime, pastTime);

	}

	public void startTime() {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(1).toString();
		String pastTime = now.minusMinutes(90).toString();
		this.inputText(StartTime, futureTime);
		this.inputText(EndTime, pastTime);

	}

	public void futureStartTime() {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(2).toString();
		this.inputText(StartTime, futureTime);

	}

	public void futureToTime() {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(2).toString();
		this.inputText(EndTime, futureTime);
	}

	public void fromDateTimeScheduleJob() throws IOException, InterruptedException {
		this.picKLocation();
		this.mouseActionClick(BussinessUnit);
		this.mouseActionClick(General);
		this.mouseActionClick(ServiceType);
		this.mouseActionClick(Repair);
		this.dropDownByIndex(Priority, 2);
		this.currentPickerFromDate();
		this.futureStartTime();
		this.assertName(SelectTechnician, "Select Technician");
		this.clearField(Tags);
		this.inputText(Tittle, fakeTittle);
		this.clearField(Description);
		this.inputText(Description, getPropertyValue("Description"));
		this.inputText(Notes, getPropertyValue("Notes"));
		this.assertName(SaveComplete, "Schedule Job");
		Thread.sleep(2000);
		this.mouseActionClick(SaveComplete);

	}

	public String jobStatus() {
		String text = this.getText(StatusJob);
		return text;

	}

	By GlobalStatus = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[6]");

	public String globalJobStatus() {
		String text = this.getText(GlobalStatus);
		return text;

	}

	By CancelledStatus = By.xpath("//*[text()='cancelled']");
	By GlobalCancelledStatus = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[12]");

	public String cancelJobStatus() {
		String text = this.getText(CancelledStatus);
		return text;

	}

	public String cancelGlobalJobStatus() {
		String text = this.getText(GlobalCancelledStatus);
		return text;

	}

	public void editJob() throws InterruptedException {
		this.mouseActionClick(Edit);
		Thread.sleep(15000);
		this.scrollDown();
		this.assertName(SelectTechnician, "Select Technician");
		this.mouseActionClick(Technician);
		this.mouseActionClick(TechnicianFirstName);
		this.mouseActionClick(SaveComplete);

	}

	By ThreeDots = By.xpath("(//*[@class='fa fa-ellipsis-v'])[2]");
	By GlobalEdit = By.xpath("(//*[@data-n-linkto='job_edit'])[1]");

	public void globalEditJob() throws InterruptedException {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(GlobalEdit);
		Thread.sleep(15000);
		this.scrollDown();
		this.assertName(SelectTechnician, "Select Technician");
		this.mouseActionClick(Technician);
		this.mouseActionClick(TechnicianFirstName);
		this.mouseActionClick(SaveComplete);

	}

	public void editJob1() throws InterruptedException {
		this.mouseActionClick(Edit);
		Thread.sleep(15000);
		this.scrollDown();
		this.assertName(SelectTechnician, "Select Technician");
		this.mouseActionClick(Crew);
		this.dropDownByIndex(CrewSize, 1);
		this.mouseActionClick(CrewTech);
		this.mouseActionClick(SelectTech);
		this.mouseActionClick(SaveComplete);

	}

	public void createdJob() throws InterruptedException, IOException {
		this.picKLocation();
		this.mouseActionClick(BussinessUnit);
		this.mouseActionClick(General);
		this.mouseActionClick(ServiceType);
		this.mouseActionClick(Repair);
		this.dropDownByIndex(Priority, 2);
		this.inputText(Tittle, fakeTittle);
		this.inputText(Description, getPropertyValue("Description"));
		this.currentPickerFromDate();
		this.currentPickerToDate();
		this.inputText(StartTime, "10.00");
		this.inputText(EndTime, "18.00");
		Thread.sleep(5000);
		this.mouseActionClick(Crew);
		this.mouseActionClick(Single);
		this.assertName(TechnicianLabel, "Technician");
		this.mouseActionClick(Technician);
		this.mouseActionClick(TechnicianSecoundName);
		this.tagValidation(Tags, randomCharacter);
		this.inputText(Notes, getPropertyValue("Notes"));
		this.mouseActionClick(SaveComplete);

	}

	public void customerOrganizationJob() throws InterruptedException {
		Thread.sleep(2000);
		this.mouseActionClick(CreateOrganizationJob);
		Thread.sleep(2000);

	}

	public void customerContactJob() throws InterruptedException {
		Thread.sleep(2000);
		this.mouseActionClick(CreateJob);
		Thread.sleep(2000);

	}

	public void globalJob() throws InterruptedException {
		Thread.sleep(2000);
		this.mouseActionClick(CreateGlobalJob);
		Thread.sleep(2000);

	}

	By TechnicianLabel = By.xpath("//*[text()='Technician']");
	By GlobalDispatch = By.xpath("(//*[@data-tabposition=\"undefined\"])[10]");
	By GlobalStart = By.xpath("(//*[@data-tabposition=\"undefined\"])[13]");
	By GlobalComplete = By.xpath("(//*[@data-tabposition=\"undefined\"])[16]");
	By GlobalCancel = By.xpath("(//*[@data-tabposition=\"undefined\"])[40]");

	public String dispatchTiggerFunction() {
		this.mouseActionClick(Dispatch);
		this.mouseActionClick(Yes);
		String text = this.getText(JobDispatchMessage);
		return text;
	}

	public String globalDispatchTiggerFunction() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(GlobalDispatch);
		this.mouseActionClick(Yes);
		String text = this.getText(JobDispatchMessage);
		return text;
	}

	public String startTiggerFunction() {
		this.mouseActionClick(Start);
		this.mouseActionClick(Yes);
		String text = this.getText(JobStartedMessgae);
		return text;
	}

	public String globalStartTiggerFunction() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(GlobalStart);
		this.mouseActionClick(Yes);
		String text = this.getText(JobStartedMessgae);
		return text;
	}

	public String completedTiggerFunction() {
		this.mouseActionClick(Complete);
		this.mouseActionClick(Yes);
		String text = this.getText(JobCompletedMessage);
		return text;
	}

	public String globalCompletedTiggerFunction() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(GlobalComplete);
		this.mouseActionClick(Yes);
		String text = this.getText(JobCompletedMessage);
		return text;
	}

	By CustomerError = By.id("id_customer_group_error");
	By GlobalSearchJobNo = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[2]");
	By GlobalCustomerName = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[1]");
	By GlobalLocation = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[4]");
	By GlobalDate = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[3]");
	By GlobalSearchBox = By.id("job-search-input-filter");
	By GlobalFrom = By.xpath("(//*[@data-setdatelimitmax='schedule_from_date'])[1]");
	By GlobalTo = By.xpath("(//*[@data-setdatelimitmax='schedule_from_date'])[2]");

	public void draftJob() throws IOException, InterruptedException {
		this.mouseActionClick(CreateJob);
		Thread.sleep(5000);
		this.mouseActionClick(Back);
		this.mouseActionClick(Yes);
		this.assertName(JobDraftedMessage, "Job Drafted");

	}

	public void draftJob1() throws IOException, InterruptedException {
		this.mouseActionClick(CreateOrganizationJob);
		Thread.sleep(5000);
		this.mouseActionClick(Back);
		this.mouseActionClick(Yes);
		this.assertName(JobDraftedMessage, "Job Drafted");

	}

	public String deletedTiggerFunction() {
		this.mouseActionClick(Delete);
		this.mouseActionClick(Yes);
		String text = this.getText(JobDeletedMessage);
		return text;
	}

	public String cancelledTigerFunction() {
		this.mouseActionClick(Cancel);
		this.mouseActionClick(Yes);
		String text = this.getText(JobCancelledMessage);
		return text;

	}

	By ThreeDotsCancel = By.xpath("(//*[@class='fa fa-ellipsis-v'])[3]");

	public String globalCancelledTigerFunction() {
		this.mouseActionClick(ThreeDotsCancel);
		this.mouseActionClick(GlobalCancel);
		this.mouseActionClick(Yes);
		String text = this.getText(JobCancelledMessage);
		return text;

	}

	public String jobNo() {
		String text = this.getText(JobNo);
		return text;
	}

	public String searchJobNo() {
		String text = this.getText(JobNo);
		this.tagValidation(SearchBox, text);
		String text2 = this.getText(JobNo);
		return text2;

	}

	public String searchJobNo1() {
		String text = this.getText(JobNo);
		this.tagValidation(SearchOrganizationBox, text);
		String text2 = this.getText(JobNo);
		return text2;

	}

	public String searchJobNo2() {
		String text = this.getText(GlobalSearchJobNo);
		this.tagValidation(GlobalSearchBox, text);
		String text2 = this.getText(GlobalSearchJobNo);
		return text2;

	}

	public String searchCustomerName() {
		String text = this.getText(GlobalCustomerName);
		this.tagValidation(GlobalSearchBox, text);
		String text2 = this.getText(GlobalCustomerName);
		return text2;

	}

	By JobLabelNo = By.xpath("(//*[text()='Job No :'])[1]");

	public void resetOption() {
		this.mouseActionClick(Reset);

	}

	public void jobLabel() {
		this.assertName(JobLabelNo, "Job No :");

	}

	public void jobLabe2() {
		this.assertName(Label, "Customer");

	}

	public String searchLocation() {
		String text = this.getText(SearchLocation);
		this.tagValidation(SearchBox, text);
		String text2 = this.getText(SearchLocation);
		return text2;

	}

	public String searchLocation1() {
		String text = this.getText(SearchLocation);
		this.tagValidation(SearchOrganizationBox, text);
		String text2 = this.getText(SearchLocation);
		return text2;

	}

	public String searchLocation2() {
		String text = this.getText(GlobalLocation);
		this.tagValidation(GlobalSearchBox, text);
		String text2 = this.getText(GlobalLocation);
		return text2;

	}

	public String invalidSearch() {
		this.tagValidation(SearchBox, randomCharacter);
		String text2 = this.getText(InvlaidSearch);
		return text2;

	}

	public String invalidSearch1() {
		this.tagValidation(SearchOrganizationBox, randomCharacter);
		String text2 = this.getText(InvlaidSearch);
		return text2;

	}

	public String invalidSearch2() {
		this.tagValidation(GlobalSearchBox, randomCharacter);
		String text2 = this.getText(GlobalInvlaidSearch);
		return text2;

	}

	public void filterByDate() {
		this.mouseActionClick(Filter);
		String currentFilterPickerFromDate = this.currentFilterPickerFromDate();
		String currentFilterPickerToDate = this.currentFilterPickerToDate();
		this.inputText(FilterFrom, currentFilterPickerFromDate);
		this.inputText(FilterTo, currentFilterPickerToDate);
		this.mouseActionClick(Apply);

	}

	public void filterByDate1() {
		this.mouseActionClick(Filter);
		String currentFilterPickerFromDate = this.currentFilterPickerFromDate();
		String currentFilterPickerToDate = this.currentFilterPickerToDate();
		this.inputText(FilterOrganizationFrom, currentFilterPickerFromDate);
		this.inputText(FilterOrganizationTo, currentFilterPickerToDate);
		this.mouseActionClick(Apply);

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

	public void unScheduleJob() throws IOException {
		this.picKLocation();
		this.mouseActionClick(BussinessUnit);
		this.mouseActionClick(General);
		this.mouseActionClick(ServiceType);
		this.mouseActionClick(Repair);
		this.inputText(Description, getPropertyValue("Description"));
		this.tagValidation(Tags, randomCharacter);
		this.inputText(Notes, getPropertyValue("Notes"));
		this.mouseActionClick(SaveComplete);

	}

	public void unassignedJob() throws InterruptedException {
		this.mouseActionClick(Edit);
		this.elementtobeClickable(SaveComplete);
		this.mouseActionClick(StartDate);
		this.currentPickerFromDate();
		this.futureStartTime();
//		this.mouseActionClick(Crew);
		Thread.sleep(5000);
		this.scrollDown();
		this.elementtobeClickable(SaveComplete);
		this.mouseActionClick(SaveComplete);

	}

	public void rescheduleJob() {
		this.mouseActionClick(Edit);
		this.elementtobeClickable(SaveComplete);
		this.inputText(EndTime, "20.00");
		this.mouseActionClick(Technician);
		this.mouseActionClick(TechnicianFirstName);
		this.elementtobeClickable(SaveComplete);
		this.mouseActionClick(SaveComplete);

	}

	By ScheduleFrom = By.xpath(
			"(//*[text()='Schedule From  : ']//following-sibling::*[@id='customer-contact-request-card-booking-time'])[1]");

	By ScheduleTo = By.xpath(
			"(//*[text()='Schedule To : ']//following-sibling::*[@id='customer-contact-request-card-booking-time'])[1]");

	public String validateListFromDate() {
		String text = this.getText(ScheduleFrom);
		return text;

	}

	public String validateScheduledDate() {
		String text = this.getText(GlobalDate);
		return text;

	}

	public String validateToDate() {
		String text = this.getText(ScheduleTo);
		return text;

	}

	/* End */
	/* Clear Fields */
	/* Start */
	public void clearLocation() {
		this.clearField(Location);
	}

	public void clearDescription() {
		this.clearField(Description);
		this.inputText(Description, "Data");
	}

	public void clearTittle() {
		this.clearField(Tittle);
	}

	public void clearNotes() {
		this.clearField(Notes);
	}

	public void clearTag() {
		this.clearField(Tags);

	}

	public void removeTags() {
		this.mouseActionClick(TagRemove);
	}

	public void removeMultipleTags() {
		for (int i = 1; i < 32; i++) {
			By TagRemove = By.xpath("(//*[@class='tag__remove'])[" + i + "]");
			this.mouseActionClick(TagRemove);
		}

	}

	public void clearSearch() {
		this.clearField(SearchBox);

	}

	public void clearSearch1() {
		this.clearField(SearchOrganizationBox);

	}

	public void clearSearch2() {
		this.clearField(GlobalSearchBox);

	}

	/* End */

	public String jobStatusCreation(String value) throws IOException, InterruptedException {
		if (value.equals("Unschedule")) {
			this.picKLocation();
			this.mouseActionClick(BussinessUnit);
			this.mouseActionClick(General);
			this.mouseActionClick(ServiceType);
			this.mouseActionClick(Repair);
			this.inputText(Description, getPropertyValue("Description"));
			this.tagValidation(Tags, randomCharacter);
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("Unassigned")) {
			this.picKLocation();
			this.mouseActionClick(BussinessUnit);
			this.mouseActionClick(General);
			this.mouseActionClick(ServiceType);
			this.mouseActionClick(Repair);
			this.dropDownByIndex(Priority, 2);
			this.currentPickerFromDate();
			this.futureStartTime();
			this.assertName(SelectTechnician, "Select Technician");
			this.clearField(Tags);
			this.inputText(Tittle, fakeTittle);
			this.clearField(Description);
			this.inputText(Description, getPropertyValue("Description"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.assertName(SaveComplete, "Schedule Job");
			Thread.sleep(2000);
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("EditUnassigned")) {
			this.mouseActionClick(Edit);
			this.elementtobeClickable(SaveComplete);
			this.mouseActionClick(StartDate);
			this.currentPickerFromDate();
			this.futureStartTime();
			this.elementtobeClickable(SaveComplete);
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("EditReschedule")) {
			this.mouseActionClick(Edit);
			this.elementtobeClickable(SaveComplete);
			this.inputText(EndTime, "20.00");
			this.mouseActionClick(Technician);
			this.mouseActionClick(TechnicianFirstName);
			this.elementtobeClickable(SaveComplete);
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("Create")) {
			Thread.sleep(2000);
			this.mouseActionClick(CreateJob);
			Thread.sleep(2000);
		}
		return value;

	}

}
