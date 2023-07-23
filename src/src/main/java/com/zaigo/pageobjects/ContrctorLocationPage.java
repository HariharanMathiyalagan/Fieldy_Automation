package com.zaigo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContrctorLocationPage {
	WebDriver driver;
	WebDriverWait wait;

	public ContrctorLocationPage(WebDriver driver) {
		this.driver = driver;
	}

	By Email = By.id("login");
	By Password = By.id("password");
	By Login = By.xpath("//button[@data-automationid='login']");
	By Dashboard = By.xpath("//div[@data-menuselector='dashboard-menu']");

	private void inputEmail(String email) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Email)).sendKeys(email);

	}

	private void inputPassword(String password) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Password)).sendKeys(password);

	}

	private void clickLogin() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(Login)).click();
	}

	private void assertion() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Dashboard)).getText();
		Assert.assertEquals(text, "Dashboard");

	}

	public void LoginCredential(String email, String password) {
		this.inputEmail(email);
		this.inputPassword(password);
		this.clickLogin();
		this.assertion();

	}

	By Team = By.id("team-menu");
	By Contractor = By.xpath("(//a[@data-n-linkto='team_companies_contractor'])[2]");
	By AddContractor = By.xpath("//button[@data-tabformid='team-company-contractor']");

	private void clickTeam() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(Team)).click();

	}

	private void clickContractor() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(Contractor)).click();

	}

	private void clickAddContractor() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(AddContractor)).click();

	}

	public void modulePage() {
		this.clickTeam();
		this.clickContractor();
		

	}

	By CompanyName = By.id("name");
	By ContractorEmail = By.id("email");
	By ContractPerson = By.id("contact_person_name");
	By Phone = By.id("phone");
	By Fax = By.id("fax");
	By Website = By.id("website");
	By Next = By.xpath("//span[text()='Next']");

	private void inputCompanyName(String name) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CompanyName)).sendKeys(name);

	}

	private void inputContractorEmail(String email) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ContractorEmail)).sendKeys(email);

	}

	private void inputContractPerson(String person) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ContractPerson)).sendKeys(person);

	}

	private void inputPhone(String phone) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Phone)).sendKeys(phone);

	}

	private void inputFax(String fax) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Fax)).sendKeys(fax);

	}

	private void inputWebsite(String website) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Website)).sendKeys(website);

	}

	private void clickNext() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Next)).click();
	}

	public void ContractorField(String company, String email, String person, String phone, String fax, String website) {
		this.clickAddContractor();
		this.inputCompanyName(company);
		this.inputContractorEmail(email);
		this.inputContractPerson(person);
		this.inputPhone(phone);
		this.inputFax(fax);
		this.inputWebsite(website);
		this.clickNext();

	}

	By LocationName = By.id("addresses__name__0");
	By comEmail = By.id("addresses__email__0");
	By ContactPerson = By.id("addresses__contact_person__0");
	By PhoneNo = By.id("addresses__phone_number__0");
	By FlatName = By.id("addresses__line_1__0");
	By StreetName = By.id("addresses__line_2__0");
	By StateName = By.id("addresses__state__0");
	By CityName = By.id("addresses__city__0");
	By ZipCode = By.id("addresses__zipcode__0");
	By CreateContractor = By.xpath("//span[text()='Contractor have been created successfully']");
	By SaveNext = By.xpath("//*[@id=\"team-company-contractor\"]/div/div/div[2]/button");

	private void LocationField(String location, String tEmail, String contact, String Number, String Flat,
			String Street, String State, String City, String Zip) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LocationName)).sendKeys(location);
		wait.until(ExpectedConditions.visibilityOfElementLocated(comEmail)).sendKeys(tEmail);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ContactPerson)).sendKeys(contact);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PhoneNo)).sendKeys(Number);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlatName)).sendKeys(Flat);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StreetName)).sendKeys(Street);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StateName)).sendKeys(State);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CityName)).sendKeys(City);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ZipCode)).sendKeys(Zip);
		// Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(CreateContractor)).getText();
		Assert.assertEquals(text, "Contractor have been created successfully");

	}

	public void Location(String location, String tEmail, String contact, String Number, String Flat, String Street,
			String State, String City, String Zip) {
		this.LocationField(location, tEmail, contact, Number, Flat, Street, State, City, Zip);

	}

}
