package com.zaigo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EditContractor {
	WebDriver driver;
	WebDriverWait wait;
	By Email = By.id("login");
	By Pass = By.id("password");
	By Click = By.xpath("//button[@type='submit']");
	By Team = By.xpath("//a[@id='team-menu']");
	By contractor = By.xpath("//div[@id='inner-id']//following::a[text()='Contractor']");
	By ThreeDots = By.xpath("//*[@id=\"fieldy-user-company-contractor-list_aserpttbl\"]/tbody/tr[2]/td[8]/div/div[1]");
	By Edit = By
			.xpath("//*[@id=\"fieldy-user-company-contractor-list_aserpttbl\"]/tbody/tr[2]/td[8]/div/div[2]/ul/li[1]");

	By Name = By.id("name");
	By SaveNext = By.xpath("//*[@id=\"team-company-contractor\"]/div/div/div[2]/button");
	By SaveNxt = By.xpath("//button[@data-spinloader='company_contractor_create_edit']");
	By Assertion = By.xpath("//span[text()='Contractor have been updated successfully']");

	By Delete = By
			.xpath("//*[@id=\"fieldy-user-company-contractor-list_aserpttbl\"]/tbody/tr[2]/td[8]/div/div[2]/ul/li[2]");
	By Yes = By.xpath("//button[text()='Yes']");
	By DeleteAssert = By.xpath("//span[text()='Contractor have been deleted successfully']");

	By txtEmail = By.id("email");
	By txtContactPerson = By.id("contact_person_name");
	By txtPhoneNo = By.id("phone");
	By txtFax = By.id("fax");
	By txtWebSite = By.id("website");
	By clickNext = By.xpath("//*[@id=\"team-company-contractor\"]/div/div/div[1]/button[2]/span");
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
	By AddContractor = By.xpath("//button[@data-formsactions='create']");

	public EditContractor(WebDriver driver) {
		this.driver = driver;

	}

	private void Login(String UserName, String Password) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Email)).sendKeys(UserName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Pass)).sendKeys(Password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Click)).click();
	}

	private void clickTeam() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Team)).click();
	}

	private void clickUser() {
		wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.visibilityOfElementLocated()).click();
	}

	private void clickContractor() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(contractor)).click();

	}

	private void EditField() {
		wait = new WebDriverWait(driver, 10);
		WebElement MOA = wait.until(ExpectedConditions.visibilityOfElementLocated(ThreeDots));
		Actions actions = new Actions(driver);
		actions.moveToElement(MOA).perform();
		WebElement click = wait.until(ExpectedConditions.visibilityOfElementLocated(Edit));
		actions.moveToElement(click).click().build().perform();

	}

	private void EditName(String CompanyName) throws InterruptedException {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(CompanyName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();

		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Assertion)).getText();
		Assert.assertEquals(text, "Contractor have been updated successfully");
	}

	private void deleteField() {
		wait = new WebDriverWait(driver, 10);
		WebElement MOA = wait.until(ExpectedConditions.visibilityOfElementLocated(ThreeDots));
		Actions actions = new Actions(driver);
		actions.moveToElement(MOA).perform();
		WebElement click = wait.until(ExpectedConditions.visibilityOfElementLocated(Delete));
		actions.moveToElement(click).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Delete)).click();
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Delete)).getText();
		Assert.assertEquals(text, "Contractor have been deleted successfully");
	}

	private void ContractorField(String CompanyName, String textEmail, String person, String phoneNo, String fax,
			String webSite, String location, String tEmail, String contact, String Number, String Flat, String Street,
			String State, String City, String Zip) throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		driver.navigate().refresh();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(AddContractor)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Name)).sendKeys(CompanyName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).sendKeys(textEmail);
		wait.until(ExpectedConditions.visibilityOfElementLocated(txtContactPerson)).sendKeys(person);
		wait.until(ExpectedConditions.visibilityOfElementLocated(txtPhoneNo)).sendKeys(phoneNo);
		wait.until(ExpectedConditions.visibilityOfElementLocated(txtFax)).sendKeys(fax);
		wait.until(ExpectedConditions.visibilityOfElementLocated(txtWebSite)).sendKeys(webSite);
//		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(clickNext));
//		Actions actions = new Actions(driver);
//		actions.moveToElement(until).click().build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(LocationName)).sendKeys(location);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(comEmail)).sendKeys(tEmail);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ContactPerson)).sendKeys(contact);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(PhoneNo)).sendKeys(Number);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(FlatName)).sendKeys(Flat);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(StreetName)).sendKeys(Street);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(StateName)).sendKeys(State);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(CityName)).sendKeys(City);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(ZipCode)).sendKeys(Zip);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(CreateContractor)).getText();
		Assert.assertEquals(text, "Contractor have been created successfully");

	}

	public void loginField(String UserName, String Password) {
		this.Login(UserName, Password);

	}

	public void userModuleField() throws InterruptedException {
		this.clickTeam();
		Thread.sleep(5000);
		this.clickTeam();
		this.clickContractor();

	}

	public void CreateContractor(String CompanyName, String textEmail, String person, String phoneNo, String fax,
			String webSite, String location, String tEmail, String contact, String Number, String Flat, String Street,
			String State, String City, String Zip) throws InterruptedException {
		this.ContractorField(CompanyName, textEmail, person, phoneNo, fax, webSite, location, tEmail, contact, Number,
				Flat, Street, State, City, Zip);

	}

	public void editField() {
		this.EditField();

	}

	public void editName(String name) throws InterruptedException {
		Thread.sleep(3000);
		this.EditName(name);

	}

	public void dltField() {
		this.deleteField();

	}

}
