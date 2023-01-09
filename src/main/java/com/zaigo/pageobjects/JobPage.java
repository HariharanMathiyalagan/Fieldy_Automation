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

	private void timePicker() {
		LocalTime now = LocalTime.now();
		String pastTime = now.minusMinutes(30).toString();
		String futureTime = now.plusHours(2).toString();
	}

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);

	public JobPage(WebDriver driver) {
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
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 20);
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

	By ListName = By.xpath("(//a[@data-n-linkto='customer_contact_timeline'])[1]");
	By CustomerName = By.id("customer-name");
	By ClickJob = By.xpath("//*[@data-menuswitcher='cstmr-contact-job']");
	By JobLabel = By.id("//*[text()='Total Job ']");
	By CreateJob = By.xpath("//*[@data-automationid='customer-contact-job-create']");
	By CreateJobLabel = By.xpath("//*[@data-menuselector='job-menu']");

	public void customerJobListPage() throws InterruptedException {
		String text = this.getText(ListName);
		this.mouseActionClick(ListName);
		this.assertName(ListName, text);
		this.mouseActionClick(ClickJob);
		this.assertName(CreateJob, "Create Job");
		this.mouseActionClick(CreateJob);
	}

	public String jobLandPage() {
		String text = this.getText(CreateJobLabel);
		return text;
	}

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
//	By Technician = 
//	By TechnicianFirstName = 
	By Priority = By.id("priority");
	By General = By.className("p-2 list-hover-bg team-business-unit w-20-ellipsis w-100");
	By Repair = By.className("p-2 list-hover-bg team-service-type w-20-ellipsis w-100");
	By EalierTime = By.xpath("//*[text()='From Time should be current or future time only']");
	By TimeMismatch = By.xpath("//*[text()='Start time should be earlier than End time']");
	By JobCreatedMessage = By.xpath("//*[text()='Job created successfully']");
	By JobUpdatedMessage = By.xpath("//*[text()=Job Updated successfully");
	By JobDispatchMessage = By.xpath("//*[text()='Job dispatched successfully']");
	By JobStartedMessgae = By.xpath("//*[text()='Job started successfully'");
	By JobCompletedMessage = By.xpath("//*[text()='Job completed successfully']");
	By JobCancelledMessage = By.xpath("//*[text()='Job cancelled successfully']");
	By JobDeletedMessage = By.xpath("//*[text()='Job deleted successfully']");
	
//	@FindBys({
//	@FindBy(xpath="//*[text()='Appointment from date,time and Appointment to date needed']");
//	@FindBy(xpath="//*[text()='Appointment to date needed']")
//	})
	By ErrorToTime = By.xpath("//*[text()='Appointment to date needed']");
	
	
	
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

	/* End */
	/* Fields Validations */
	/* Start */
	public void mandatoryDescriptionField() {
		this.validationTab(Description, " ");
	}

	public void maxValidationDescription() throws IOException {
		this.validationTab(Description, getPropertyValue("2048Characters"));
	}

	public void mandatoryLocationField() {
		this.validationTab(Location, "       ");

	}

	public void maxValidationLocationField() throws InterruptedException, IOException {
		Thread.sleep(2000);
		this.validationTab(Location, getPropertyValue("2048Characters"));
	}

	public void picKLocation() {
		this.inputText(Location, fakeState);
		this.mouseActionClick(firstLocation);
	}

	public void maxValidationTittle() {
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
		cal.add(Calendar.DAY_OF_MONTH, 0);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(StartDate, currentDate);
	}

	public void currentPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 0);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(EndDate, currentDate);
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
		String futureTime = now.plusMinutes(30).toString();
		this.inputText(StartTime, futureTime);

	}

	public void futureToTime() {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(2).toString();
		this.inputText(StartTime, futureTime);
	}

	public void fromDateTimeScheduleJob() throws IOException {
		this.mouseActionClick(BussinessUnit);
		this.mouseActionClick(General);
		this.mouseActionClick(ServiceType);
		this.mouseActionClick(Repair);
		this.dropDownByIndex(Priority, 2);
		this.inputText(Tittle, fakeTittle);
		this.clearField(Description);
		this.inputText(Description, getPropertyValue("Description"));
		this.currentPickerFromDate();
		this.futureToTime();
		this.inputText(Notes, getPropertyValue("Notes"));
		this.mouseActionClick(SaveComplete);
		
	}
	
	private void unassignedJob() {
		
		

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

	public void clearEndDate() {
		this.clearField(EndDate);
	}
	/* End */

}
