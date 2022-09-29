package com.zaigo.pageobjects;

import java.awt.AWTException;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditDetailScreenCompaniesPage extends BaseClass{

	WebDriver driver;
	WebDriverWait wait;

	By Dashboard = By.xpath("//div[@data-menuselector='dashboard-menu']");
	By Team = By.id("team-menu");
	By Tittle = By.xpath("//div[text()='Zaiportal Tenant 3']");
	By Edit = By.xpath("//div[@class='col-lg-2 col-md-2 col-sm-6 col-6']//child::button[@data-tabposition='1']");
	By Next = By.xpath("//span[text()='Next']");

	By Location = By.id("addresses__name__2");
	By Email = By.id("addresses__email__2");
	By ContactPerson = By.id("addresses__contact_person__2");
	By PhoneNumber = By.id("addresses__phone_number__2");
	By Building = By.id("addresses__line_1__2");
	By Street = By.id("addresses__line_2__2");
	By City = By.id("addresses__city__2");
	By State = By.id("addresses__state__2");
	By Zipcode = By.id("addresses__zipcode__2");

	By AddMoreLocation = By.id("add-more-team-company-location");

	By AccountOwner = By.id("account_owner");

	By Save_Complete = By.id("team-company-edit-submit");

	By response = By.xpath("//span[text()='Company Information updated successfully']");

	By deleteLocation = By.xpath("//*[@id=\"accordion-2\"]/div[2]/div[2]");

	By dots = By.xpath("//*[@id=\"Team-Company-Card-Details-Section\"]/div[3]/div/div[1]/div/div[2]/div/div[1]");

	By delete = By
			.xpath("//*[@id=\"Team-Company-Card-Details-Section\"]/div[3]/div/div[1]/div/div[2]/div/div[2]/ul/li[2]");

	By Yes = By.xpath("//button[text()='Yes']");

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
		wait.until(ExpectedConditions.visibilityOfElementLocated(Yes)).click();

	}

	public String deleteMessage() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
		// Assert.assertEquals(text, "Deleted Successfully");
		return text;

	}

	public void modulePage() throws InterruptedException {
		Thread.sleep(5000);
		this.clickTeam();
		// this.assertTittle();

	}

	public void editContent() throws InterruptedException {
		this.clickEdit();
		this.scrollDown();
		Thread.sleep(2500);
		this.clickNext();

	}

	public void addLocation(String locate, String email, String contact, String phoneNumber, String building,
			String streetName, String cityName, String stateName, String zipCode) {
		this.scrollDown();
		this.clickAddMore();
		this.scrollDown();
		this.inputLocation(locate);
		this.inputEmail(email);
		this.inputContactPerson(contact);
		this.inputPhoneNumber(phoneNumber);
		this.inputBuilding(building);
		this.inputStreet(streetName);
		this.inputCity(cityName);
		this.inputState(stateName);
		this.inputZipcode(zipCode);
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
