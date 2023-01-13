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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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

	By ContactListName = By.xpath("(//*[@data-n-linkto='customer_contact_timeline'])[1]");
	By OrganizationListName = By.xpath("(//*[@data-n-linkto='customer_organization_timeline'])[1]");
	By ClickContactRequest = By.xpath("//*[@data-detailheadermenu='cstmr-contact-request']");
	By ClickOrganizationRequest = By.xpath("//*[@data-detailheadermenu='cstmr-organization-request']");
	By CreateContactRequest = By.xpath("//*[@data-automationid='customer-contact-request-create']");
	By CreateOrganizationRequest = By.xpath("//*[@data-automationid='customer-organization-request-create']");
	By CreateJobLabel = By.xpath("//*[@data-menuselector='request-menu']");
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
	By RequestNo = By.xpath("(//*[@class='id-number'])[1]");
	By SearchContactBox = By.id("customer-contact-request-search-filter");
	By SearchOrganizationBox = By.id("customer-company-request-search-filter");
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
	By OrganizationJobTittle = By.id("contacts__job_title__0");
	By OrganizationContactSave = By.id("organization-contact-create");
	By SelectTechnician = By.xpath(
			"//*[@class='d-flex mt-2']//*[@class='create-header page-header-left back-btn font-weight-bold black-text ']");
	By StatusJob = By.id("customer-contact-request-card-status");
	By Edit = By.xpath("//*[@class='fa fa-pencil ']");
	By TechnicianLabel = By.xpath("//*[text()='Technician']");
	By CancelledStatus = By.xpath("//*[text()='cancelled']");
	By JobNo = By.xpath("(//*[@class='id-number'])[1]");
	By ScheduleFrom = By.xpath(
			"(//*[text()='Schedule From  : ']//following-sibling::*[@id='customer-contact-request-card-booking-time'])[1]");

	By ScheduleTo = By.xpath(
			"(//*[text()='Schedule To : ']//following-sibling::*[@id='customer-contact-request-card-booking-time'])[1]");
	By JobLabelNo = By.xpath("(//*[text()='Request No :'])[1]");
	
	public void customerContactRequestListPage() throws InterruptedException {
		String text = this.getText(ContactListName);
		this.mouseActionClick(ContactListName);
		this.assertName(ContactListName, text);
		this.mouseActionClick(ClickContactRequest);
		this.assertName(CreateContactRequest, "Create Request");
		this.mouseActionClick(CreateContactRequest);
	}

	public String requestLandPage() {
		String text = this.getText(CreateJobLabel);
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
		Thread.sleep(6000);
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

	public void fromDateTimeScheduleJob() throws IOException, InterruptedException {
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
		this.assertName(SaveComplete, "Schedule Request");
		Thread.sleep(2000);
		this.mouseActionClick(SaveComplete);

	}

	public String createdMessage() {
		String text = this.getText(RequestCreatedMessage);
		return text;

	}

	public String jobStatus() {
		String text = this.getText(StatusJob);
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

	public String updatedMessage() {
		String text = this.getText(RequestUpdatedMessage);
		return text;

	}

	public void customerContactJob() throws InterruptedException {
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

	public void createdJob() throws InterruptedException, IOException {
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

	public String cancelJobStatus() {
		String text = this.getText(CancelledStatus);
		return text;

	}

	public void draftJob() throws IOException, InterruptedException {
		this.mouseActionClick(CreateContactRequest);
		Thread.sleep(5000);
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

	public String searchJobNo() {
		String text = this.getText(JobNo);
		this.tagValidation(SearchContactBox, text);
		String text2 = this.getText(JobNo);
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

	public String currentFilterPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
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

	public void jobLabel() {
		this.assertName(JobLabelNo, "Request No :");

	}
	
	public String invalidSearch() {
		this.tagValidation(SearchContactBox, randomCharacter);
		String text2 = this.getText(InvlaidSearch);
		return text2;

	}

}
