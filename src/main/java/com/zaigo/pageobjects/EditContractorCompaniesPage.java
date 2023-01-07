package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
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

public class EditContractorCompaniesPage extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;
	
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
	
	
	By Email = By.id("login");
	By Pass = By.id("password");
	By Click = By.xpath("//button[@type='submit']");

	By Team = By.id("team-menu");
	By contractor = By.xpath("//div[@id='inner-id']//following::a[text()='Contractor']");

	By ThreeDots = By.xpath("//*[@id=\"fieldy-user-company-contractor-list_aserpttbl\"]/tbody/tr[2]/td[8]/div/div[1]");
	By Edit = By.xpath("(//li[@data-tabformid='undefined'])[1]");

	By Name = By.id("name");
	By SaveNext = By.xpath("//button[@data-spinloader='company_contractor_create_edit']");
	By Assertion = By.xpath("//span[text()='Contractor have been updated successfully']");
	By clickNext = By.xpath("//*[text()='Next']");
	By clickPrevious = By.xpath("//*[text()='Previous']");

	public EditContractorCompaniesPage(WebDriver driver) {
		this.driver = driver;
	}

	By Dashboard = By.xpath("//div[@data-menuselector='dashboard-menu']");
//
//	private void assertDashboard() {
//		wait = new WebDriverWait(driver, 10);
//		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Dashboard)).getText();
//		Assert.assertEquals(text, "Dashboard");
//
//	}

//	Actions actions;
//
//	private void clickDots() {
//		wait = new WebDriverWait(driver, 10);
//		WebElement MOA = wait.until(ExpectedConditions.visibilityOfElementLocated(ThreeDots));
//		actions = new Actions(driver);
//		actions.moveToElement(MOA).perform();
//
//	}
//
//	private void clickEdit() {
//		wait = new WebDriverWait(driver, 10);
//		WebElement click = wait.until(ExpectedConditions.visibilityOfElementLocated(Edit));
//		actions.moveToElement(click).click().build().perform();
//
//	}
//
//	public void editField() {
//		this.clickDots();
//		this.clickEdit();
//
//	}

	By value = By.xpath("//a[@data-exitpopup='team_companies_contractor']");
//
//	private void assertPage() {
//		wait = new WebDriverWait(driver, 10);
//		String text = wait.until(ExpectedConditions.elementToBeClickable(value)).getText();
//		Assert.assertEquals(text, "Team / Edit Contractor");
//
//	}
//
//	private void clickClearName() {
//		wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.elementToBeClickable(Name)).click();
//
//	}
//
//	private void selectName() {
//		wait = new WebDriverWait(driver, 10);
//		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(Name));
//		Actions actions = new Actions(driver);
//		actions.doubleClick(until).perform();
//
//	}
//
//	private void clearName() {
//		wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).clear();
//
//	}
//
//	private void visibleName() {
//		wait = new WebDriverWait(driver, 10);
//		String attribute = wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).getAttribute("value");
//		wait.until(ExpectedConditions.textToBePresentInElementLocated(Name, attribute));
//	}
//
//	private void editName(String CompanyName) {
//		wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(CompanyName);
//
//	}
//
//	private void clickSaveNext() {
//		wait = new WebDriverWait(driver, 10);
//		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).getText();
//		Assert.assertEquals(until, "Save & Complete");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();
//		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Assertion)).getText();
//		Assert.assertEquals(text, "Contractor have been updated successfully");
//
//	}
//
//	public void editNameField(String CompanyName) throws InterruptedException {
//		// Thread.sleep(2000);
//		// this.visibleName();
//		this.assertPage();
//		Thread.sleep(2000);
//		this.clickClearName();
//		this.selectName();
//		this.clearName();
//		this.editName(CompanyName);
//		this.clickSaveNext();
//
//	}

	By attributeValue = By.xpath("//*[@id=\"contact_person_name\"]");

//	private void attributeData() {
//		wait = new WebDriverWait(driver, 10);
//		String attribute = wait.until(ExpectedConditions.visibilityOfElementLocated(attributeValue))
//				.getAttribute("value");
//		System.out.println(attribute);
//		Assert.assertEquals(attribute, attribute);
//
//	}

	By Delete = By.xpath("(//*[@data-tabformid='undefined'])[4]");

	By DeleteAssert = By.xpath("//span[text()='Contractor have been deleted successfully']");

	By Yes = By.xpath("//*[text()='Yes']");
	By Value = By.xpath("//*[@id=\"modal-confirmation-popup\"]/div/div/div/div/h4");
	By firstName = By.xpath("//*[text()='Company']");

//	private void clickYes() {
//		wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(Yes)).click();
//		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(DeleteAssert)).getText();
//		Assert.assertEquals(text, "Contractor have been deleted successfully");
//		String texts = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).getText();
//		Assert.assertEquals(texts, "Company");
//	}

	By Deletes = By.xpath("(//i[@class='fa fa-ellipsis-v'])[2]");

//	private void deleteField() {
//		wait = new WebDriverWait(driver, 10);
//		WebElement MOA = wait.until(ExpectedConditions.visibilityOfElementLocated(ThreeDots));
//		Actions actions = new Actions(driver);
//		actions.moveToElement(MOA).perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(Delete)).click();
//
//	}
//
//	public void dltField() {
//		// Thread.sleep(2000);
//		this.deleteField();
//		this.clickYes();
//	}

	public void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	public void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	public void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
	}

	public void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	public void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	public void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	public String getText(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;

	}

	By EditPage = By.xpath("//a[@data-exitpopup='team_companies_contractor']");

	public String editContractor() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(Edit);
		String text = this.getText(EditPage);
		return text;

	}

	By Logo = By.xpath("//label[@for='imageUpload']");
	By CompanyName = By.id("name");
	By Pre = By.xpath("//*[text()='Previous']");
	By AddMoreLocation = By.id("add-more-team-company-contractor-location");

	public void editContractorDetails() throws AWTException, InterruptedException {
		Thread.sleep(2000);
//		this.mouseActionClick(Logo);
//		Thread.sleep(1000);
//		attachmentFile("1622641377484");
//		this.mouseActionClick(clickNext);
//		this.mouseActionClick(AddMoreLocation);
//		this.mouseActionClick(clickPrevious);
		this.clearField(CompanyName);
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(3);
		this.inputText(CompanyName, fakeCompanyName);
		this.mouseActionClick(SaveNext);
	}

	public String responseMessage() {
		String text = this.getText(Assertion);
		return text;

	}

	public String deleteFunction() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(Delete);
		this.mouseActionClick(Yes);
		String text = this.getText(DeleteAssert);
		return text;

	}

}
