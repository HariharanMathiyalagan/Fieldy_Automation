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
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import bsh.This;
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

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String maxPhoneNumber = RandomStringUtils.randomNumeric(25);
	String minPhoneNumber = RandomStringUtils.randomNumeric(2);

	By Dashboard = By.xpath("//div[@data-menuselector='dashboard-menu']");
	By Team = By.id("user-active");
	By Tittle = By.xpath("//*[@id='team-company-details-company-name']//*[@class='company']");
	By Tittle1 = By.xpath("//*[@id='team-company-details-company-name']//div//div[1]");
	By Edit = By.xpath("//*[@id='team-company-details-company-name']/div[1]/div[2]");
	By Next = By.xpath("//span[text()='Next']");
	By CompanyWebsite = By.id("company_website");
	By ErrorCompanyWebsite = By.id("company_website_error");
	By FirstName = By.id("first_name");
	By ErrorFirstName = By.id("first_name_error");
	By LastName = By.id("last_name");
	By ErrorLastName = By.id("last_name_error");
	By Location = By.id("addresses__name__1");
	By Email = By.id("addresses__email__1");
	By ContactPerson = By.id("addresses__contact_person__1");
	By PhoneNumber = By.id("addresses__phone_number__1");
	By Building = By.id("addresses__line_1__1");
	By Street = By.id("addresses__line_2__1");
	By City = By.id("addresses__city__1");
	By State = By.id("addresses__state__1");
	By Zipcode = By.id("addresses__zipcode__1");
	By Description = By.id("notes");
	By ErrorDescription = By.id("notes_error");
	By TaxNumber = By.id("tax_number");
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'in-validate')]//following-sibling::div[3]"),
			@FindBy(xpath = "//*[contains(@class,'in-validate')]//following-sibling::div[1]") })
	WebElement ErrorMessage;
	By ErrorLocation = By.id("addresses__name__1_error");
	By ErrorEmail = By.id("addresses__email__1_error");
	By ErrorContactPerson = By.id("addresses__contact_person__1_error");
	By ErrorPhoneNumber = By.id("addresses__phone_number__1_error");
	By ErrorBuilding = By.id("addresses__line_1__1_error");
	By ErrorStreet = By.id("addresses__line_2__1_error");
	By ErrorCity = By.id("addresses__city__1_error");
	By ErrorState = By.id("addresses__state__1_error");
	By ErrorZipcode = By.id("addresses__zipcode__1_error");
	By ErrorZipcode1 = By.xpath("//*[text()='Atleast 3 characters required']");

	By AddMoreLocation = By.id("add-more-team-company-location");

	By AccountOwner = By.id("account_owner");

	By Save_Complete = By.id("team-company-edit-submit");

	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div");

	By response = By.xpath("//span[text()='Company Information updated successfully']");

	By deleteLocation = By.xpath("//*[@id=\"accordion-2\"]/div[2]/div[2]");

	By dots = By.xpath("//*[@id='Team-Company-Card-Details-Section']/div[2]/div");

	By delete = By.xpath("//*[@id='Team-Company-Card-Details-Section']/div[2]/div/div[1]/div/div/div[2]/a/i");

	By Yes = By.xpath("//*[text()='Yes']");

	By message = By.xpath("//span[text()='Deleted Successfully']");

	By CompanyName = By.id("company_name");

	By Label = By.xpath("//*[@id='breadcrumb_placement']/div/ol//li[3]");

	By ResponseMessage = By.xpath("//*[@class='js-snackbar__message']");

	By Cancel = By.xpath("//*[@class='js-snackbar__close bold']");

	public EditDetailScreenCompaniesPage(WebDriver driver) {
		this.driver = driver;
	}

	public Boolean conditionChecking(By element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 20);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");

	}

	public Boolean conditionChecking1(WebElement element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 2);
			text = wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
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

	public void clickAddMore() {
		this.scrollDown();
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
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(delete));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void clickYes() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(Yes));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public String deleteMessage() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
		// Assert.assertEquals(text, "Deleted Successfully");
		return text;

	}

	public void modulePage() throws InterruptedException {
//		Thread.sleep(6000);
		this.clickTeam();
		// this.assertTittle();

	}

	private void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public Boolean valuePresent(By element, String value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 10);
			text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		} catch (Exception e) {
			return text;
		}
		return text;

	}

	public String editContent() throws InterruptedException {
		String text = this.getText(Tittle1);
		this.clickEdit();
		this.visible(Spinner);
		this.invisible(Spinner);
		this.valuePresent(CompanyName, text);
//		this.elementtobeClickable(Next);
		String text2 = this.getText(Label);
		return text2;

	}

	public void addLocation() throws IOException {
		this.scrollDown();
		this.clickAddMore();
		this.scrollDown();
		this.inputLocation("London");
		this.inputEmail(fakeEmail);
		this.inputContactPerson(fakeFirstName + fakeLastName);
		this.inputPhoneNumber(fakePhoneNumber);
		this.inputBuilding(fakeAddress1);
		this.inputStreet(fakeAddress2);
		this.inputCity(fakeCity);
		this.inputState(fakeState);
		this.inputZipcode(fakeZipcode);
		this.clickSaveComplete();
		this.assertTittle();

	}

	public void deleteLocation() {
		this.mouseAction();
		this.clickDelete();
		this.clickYes();

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

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	private void visible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
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

	private String getText(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return until;

	}

	private String getAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
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

	public void clearFields(String value) {
		if (value.equals("Location")) {
			this.clearField(Location);
		} else if (value.equals("Email")) {
			this.clearField(Email);
		} else if (value.equals("ContactPerson")) {
			this.clearField(ContactPerson);
		} else if (value.equals("PhoneNumber")) {
			this.clearField(PhoneNumber);
		} else if (value.equals("Address1")) {
			this.clearField(Building);
		} else if (value.equals("Address2")) {
			this.clearField(Street);
		} else if (value.equals("City")) {
			this.clearField(City);
		} else if (value.equals("State")) {
			this.clearField(State);
		} else if (value.equals("Zipcode")) {
			this.clearField(Zipcode);
		} else if (value.equals("FirstName")) {
			this.clearField(FirstName);
		} else if (value.equals("LastName")) {
			this.clearField(LastName);
		} else if (value.equals("CompanyWebsite")) {
			this.clearField(CompanyWebsite);
		} else if (value.equals("Description")) {
			this.clearField(Description);
		} else if (value.equals("TaxNumber")) {
			this.clearField(TaxNumber);
		}
	}

	public void firstName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(FirstName, characters256);
		}

	}

	public void lastName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LastName, characters256);
		}
	}

	public void companyWebsite(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(CompanyWebsite, characters2048);
		}

	}

	public void locationName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Location, characters256);
		}
	}

	public void email(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Email, characters256);
		} else if (value.equals("Unique")) {
			this.validationTab(Email, "fsajhsafkjsa");
		} else if (value.equals("InvalidValidEmail")) {
			this.validationTab(Email, "sdfkjsfhkj");
		}
	}

	public void contactPerson(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(ContactPerson, characters512);
		}
	}

	public void PhoneNumber(String value) {
		if (this.getAttribute(PhoneNumber).length() > 0) {
			this.clearField(PhoneNumber);
		}
		if (value.equals("MaxValidation")) {
			this.validationTab(PhoneNumber, "2143567897654325675432425987654");
		} else if (value.equals("MinValidation")) {
			this.validationTab(PhoneNumber, "234");
		} else if (value.equals("Invalid")) {
			this.valuePresent(PhoneNumber, "dsjfhsfh");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(PhoneNumber, "!@#$%^&");
		}

	}

	public void address1(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Building, characters256);
		}

	}

	public void address2(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Street, characters256);
		}

	}

	public void city(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(City, characters256);
		}
	}

	public void state(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(State, characters256);
		}

	}

	public void zipcode(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Zipcode, "1234567876543456765");
		} else if (value.equals("MinValidation")) {
			this.validationTab(Zipcode, "12");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(Zipcode, "!@#$");
		}

	}

	public void description(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Description, characters2048);
		}
	}

	public void taxNumber(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(TaxNumber, characters2048);
		}
	}

	public String errorFields(String value) {
		if (value.equals("Location")) {
			return this.getText(ErrorLocation);
		} else if (value.equals("Email")) {
			return this.getText(ErrorEmail);
		} else if (value.equals("ContactPerson")) {
			return this.getText(ErrorContactPerson);
		} else if (value.equals("PhoneNumber")) {
			return this.getText(ErrorPhoneNumber);
		} else if (value.equals("Address1")) {
			return this.getText(ErrorBuilding);
		} else if (value.equals("Address2")) {
			return this.getText(ErrorStreet);
		} else if (value.equals("City")) {
			return this.getText(ErrorCity);
		} else if (value.equals("State")) {
			return this.getText(ErrorState);
		} else if (value.equals("Zipcode")) {
			return this.getText(ErrorZipcode);
		} else if (value.equals("FirstName")) {
			return this.getText(ErrorFirstName);
		} else if (value.equals("LastName")) {
			return this.getText(ErrorLastName);
		} else if (value.equals("CompanyWebsite")) {
			return this.getText(ErrorCompanyWebsite);
		} else if (value.equals("Description")) {
			return this.getText(ErrorDescription);
		}
		return value;
	}

	public String errorMessage() {
		if (!this.conditionChecking1(ErrorMessage)) {
			do {
				this.mouseActionClick(Save_Complete);
				this.invisible(Spinner);
			} while (!this.conditionChecking1(ErrorMessage));
		}
		return this.getText(ErrorMessage);
	}

	public void fieldsFillData(String value) throws IOException {
		if (value.equals("BasicPage")) {
			this.inputText(CompanyWebsite, fakeWebsite);
			this.inputText(FirstName, fakeFirstName);
			this.inputText(LastName, fakeLastName);
			this.inputText(TaxNumber, maxPhoneNumber);
			this.clickNext();
		} else if (value.equals("LocationPage")) {
			this.scrollDown();
			this.clickAddMore();
			this.scrollDown();
			this.inputLocation("London");
			this.inputEmail(fakeEmail);
			this.inputContactPerson(fakeFirstName + fakeLastName);
			this.inputPhoneNumber(fakePhoneNumber);
			this.inputBuilding(fakeAddress1);
			this.inputStreet(fakeAddress2);
			this.inputCity(fakeCity);
			this.inputState(fakeState);
			this.inputZipcode(fakeZipcode);
			this.mouseActionClick(Next);
		} else if (value.equals("Description")) {
			this.inputText(Description, getPropertyValue("Description"));
			this.mouseActionClick(Save_Complete);
		}

	}

	static String toasterMessage;

	public String responseMessages(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message")) {
			if (this.conditionChecking(ResponseMessage)) {
				toasterMessage = this.getText(ResponseMessage);
				this.invisible(Cancel);
				return toasterMessage;
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(Save_Complete);
					if (this.conditionChecking(ResponseMessage)) {
						toasterMessage = this.getText(ResponseMessage);
						this.invisible(ResponseMessage);
						if (toasterMessage.equals(getPropertyValue("UpdatedTenantMessage"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateFunction")) {
			if (toasterMessage == getPropertyValue("BussinessNameAlready")) {
				this.mouseActionClick(Team);
			}
		}
		return value;
	}

}
