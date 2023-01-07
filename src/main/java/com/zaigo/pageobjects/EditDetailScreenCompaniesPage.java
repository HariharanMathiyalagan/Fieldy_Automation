package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
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
import org.testng.Assert;

import com.base.BaseClass;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditDetailScreenCompaniesPage extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;

	Faker faker = new Faker(new Locale("en-IND"));
	String fakeFirstName = faker.name().firstName();
	String fakeLastName = faker.name().lastName();
	String fakeEmail = faker.internet().emailAddress();
	String fakePhoneNumber = faker.phoneNumber().phoneNumber();
	String fakeAddress1 = faker.address().buildingNumber();
	String fakeAddress2 = faker.address().streetName();
	String fakeCity = faker.address().city();
	String fakeState = faker.address().state();
	String fakeZipcode = faker.address().zipCode();
	String fakeWebsite = faker.company().url();
	String fakeCompanyName = faker.company().name();

	By Dashboard = By.xpath("//div[@data-menuselector='dashboard-menu']");
	By Team = By.id("team-menu");
	By Tittle = By.xpath("//*[@id='team-company-details-company-name']//*[@class='company']");
	By Edit = By.xpath("//div[@class='col-lg-2 col-md-2 col-sm-6 col-6']//child::button[@data-tabposition='1']");
	By Next = By.xpath("//span[text()='Next']");

	By Location = By.id("addresses__name__1");
	By Email = By.id("addresses__email__1");
	By ContactPerson = By.id("addresses__contact_person__1");
	By PhoneNumber = By.id("addresses__phone_number__1");
	By Building = By.id("addresses__line_1__1");
	By Street = By.id("addresses__line_2__1");
	By City = By.id("addresses__city__1");
	By State = By.id("addresses__state__1");
	By Zipcode = By.id("addresses__zipcode__1");

	By AddMoreLocation = By.id("add-more-team-company-location");

	By AccountOwner = By.id("account_owner");

	By Save_Complete = By.id("team-company-edit-submit");

	By response = By.xpath("//span[text()='Company Information updated successfully']");

	By deleteLocation = By.xpath("//*[@id=\"accordion-2\"]/div[2]/div[2]");

	By dots = By.xpath("(//*[@class='fa fa-ellipsis-v'])[3]");

	By delete = By.xpath("(//*[@class='drop-down-list']//li)[4]");

	By Yes = By.xpath("//*[text()='Yes']");

	By message = By.xpath("//span[text()='Deleted Successfully']");

	public EditDetailScreenCompaniesPage(WebDriver driver) {
		this.driver = driver;
	}

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");

	}

	private void dashBoard() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Dashboard)).getText();
		Assert.assertEquals(text, "Dashboard");

	}

	private void clickTeam() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Team)).click();

	}

	public String assertTittle() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Tittle)).getText();
		// Assert.assertEquals(text, "Zaiportal Tenant 3");
		return text;

	}

	private void clickEdit() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Edit)).click();
	}

	private void clickNext() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(Next)).click();

	}

	private void clickAddMore() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddMoreLocation)).click();

	}

	private void inputLocation(String LocationName) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Location)).sendKeys(LocationName);

	}

	private void inputEmail(String textEmail) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Email)).sendKeys(textEmail);

	}

	private void inputContactPerson(String contactPerson) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ContactPerson)).sendKeys(contactPerson);

	}

	private void inputPhoneNumber(String textNumber) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PhoneNumber)).sendKeys(textNumber);

	}

	private void inputBuilding(String textBuilding) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Building)).sendKeys(textBuilding);

	}

	private void inputStreet(String textStreet) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Street)).sendKeys(textStreet);

	}

	private void inputCity(String textCity) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(City)).sendKeys(textCity);

	}

	private void inputState(String textState) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(State)).sendKeys(textState);

	}

	private void inputZipcode(String textZipcode) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Zipcode)).sendKeys(textZipcode);

	}

	private void clickSaveComplete() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Save_Complete)).click();

	}

	public String responseMessage() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(response)).getText();
		Assert.assertEquals(text, "Company Information updated successfully");
		return text;

	}

	private void delete() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteLocation)).click();

	}

	private void mouseAction() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(dots));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();

	}

	private void clickDelete() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(delete)).click();

	}

	private void clickYes() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(Yes)).click();

	}

	public String deleteMessage() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
		// Assert.assertEquals(text, "Deleted Successfully");
		return text;

	}

	public void modulePage() throws InterruptedException {
		Thread.sleep(6000);
		this.clickTeam();
		// this.assertTittle();

	}

	public void editContent() throws InterruptedException {
		this.clickEdit();
		this.scrollDown();
		Thread.sleep(3000);
		this.clickNext();

	}

	public void addLocation() throws IOException {
		this.scrollDown();
		this.clickAddMore();
		this.scrollDown();
		String num = RandomStringUtils.randomAlphanumeric(4);
//		this.inputLocation(excelRead("Team Details Screen", 1, 0));
//		this.inputEmail(num + excelRead("Team Details Screen", 1, 1) + "@mailinator.com");
//		this.inputContactPerson(excelRead("Team Details Screen", 1, 2));
//		this.inputPhoneNumber(excelRead("Team Details Screen", 1, 3));
//		this.inputBuilding(excelRead("Team Details Screen", 1, 4));
//		this.inputStreet(excelRead("Team Details Screen", 1, 5));
//		this.inputCity(excelRead("Team Details Screen", 1, 6));
//		this.inputState(excelRead("Team Details Screen", 1, 7));
//		this.inputZipcode(excelRead("Team Details Screen", 1, 8));
		this.inputLocation(excelRead("Team Details Screen", 1, 0));
		this.inputEmail(fakeEmail);
		this.inputContactPerson(fakeFirstName + fakeLastName);
		this.inputPhoneNumber(fakePhoneNumber);
		this.inputBuilding(fakeAddress1);
		this.inputStreet(fakeAddress2);
		this.inputCity(fakeCity);
		this.inputState(fakeState);
		this.inputZipcode(fakeZipcode);

		this.clickSaveComplete();
		// this.responseMessage();
		this.assertTittle();

	}

	public void deleteLocation() {
		this.mouseAction();
		this.clickDelete();
		this.clickYes();
		// this.deleteMessage();
		// this.assertTittle();
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
		wait = new WebDriverWait(driver, 10);
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
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;

	}

	private void mouseAction(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();

	}

	By Logo = By.xpath("//label[@for='image']");
	By LogoError = By.id("image_error");
	By EditHeading = By.xpath("//a[@data-exitpopup='team_companies_company']");

	public void maxSizeLogoValidation() throws AWTException, InterruptedException {
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("dsc00531");

	}

	public String errorLogo() {
		String text2 = this.getText(LogoError);
		return text2;

	}

	public void formatLogoValidation() throws InterruptedException, AWTException {
		this.assertName(EditHeading, "Edit Company");
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("sample-file.pdf");

	}

}
