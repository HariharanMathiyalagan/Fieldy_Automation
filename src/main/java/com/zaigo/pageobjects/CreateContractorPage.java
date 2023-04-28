package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.Keys;
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

public class CreateContractorPage extends BaseClass {

	private WebDriver driver;
	private WebDriverWait wait;

	Faker faker = new Faker(new Locale("en-IND"));
	String fakeFirstName = faker.name().firstName();
	String fakeLastName = faker.name().lastName();
	String fakeContactPersonName = faker.name().fullName();
	String fakeEmail = faker.internet().safeEmailAddress();
	String fakePhoneNumber = faker.phoneNumber().phoneNumber();
	String fakeAddress1 = faker.address().buildingNumber();
	String fakeAddress2 = faker.address().streetName();
	String fakeCity = faker.address().city();
	String fakeState = faker.address().state();
	String fakeZipcode = faker.address().zipCode();
	String fakeWebsite = faker.company().url();
	String fakeCompanyName = faker.company().name();
	String fakeFaxNumber = faker.number().digits(7);
	String fakecountry = faker.address().country();

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String maxPhoneNumber = RandomStringUtils.randomNumeric(25);
	String minPhoneNumber = RandomStringUtils.randomNumeric(2);
	private By team = By.id("team-menu");

	By Tittle = By.xpath("//*[@id='team-company-details-company-name']//*[@class='company']");

	private By company = By.xpath("//span[text()='Companies']");

	private By contractor = By.xpath("//div[@id='inner-id']//following::a[text()='Contractor']");
	
	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div/div");
	
	private By createcontractorbutton = By.xpath("//button[@data-tabformid='team-company-contractor']");

	private By usermenu = By.xpath("//a[@data-automationid='user']");

	private By contractortab = By.xpath("//a[@data-automationid='contractor']");

	private By contractorcreatebtn = By.xpath("//*[@id='root']/div/div/div[3]/div/div/button");

	private By CompanyName = By.id("name");

	private By ErrorCompanyName = By.id("name_error");

	private By nextbtn = By.xpath("//button[@data-automationid='next']");

	private By contractoremail = By.xpath("//input[@data-automationid='email']");

	private By contractoremailerr = By.id("email_error");

	private By contractorcpersonfirstname = By.id("first_name");

	private By contractorcpersonlastname = By.id("last_name");

	private By contractorcpersonerrfirstname = By.id("first_name_error");

	private By contractorcpersonerrlastname = By.id("last_name_error");

	private By contractorphone = By.id("phone");

	private By contractorphoneerr = By.id("phone_error");

	private By contractorfax = By.xpath("//input[@data-automationid='fax']");

	private By contractorfaxerror = By.xpath("//div[@id='fax_error']");

	private By contractorsite = By.id("website");

	private By contractorsiteerr = By.id("website_error");

	private By locationone = By.xpath("//*[@data-automationid='Location 1']");

	private By contractorimageupload = By.id("imageUpload");

	private By imagerror = By.xpath("//*[@data-automationid='imageerror']");

	private By closepopup = By.xpath("//button[@data-automationid='close']");

	private By warningpopup = By.xpath("//button[@data-automationid='yesBtn']");

	private By saveform = By.xpath("//*[@data-automationid='save_complete']");

	private By successmessage = By.xpath("//*[@data-automationid='sucmessage']");

	// Address

	private By addlocationbtn = By.xpath("//button[@data-automationid='anotherLocation']");

	private By lineone = By.xpath("//input[@data-automationid='noBuildingFlatName']");

	private By lineoneerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");

	private By linetwo = By.xpath("//input[@data-automationid='streetName']");

	private By linetwoerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");

	private By addressname = By.xpath("//button[@data-automationid='saveAndComplete']");

	By addressnameerr = By.xpath("//button[@data-automationid='saveAndComplete']");

	By city = By.xpath("//input[@data-automationid='cityVillage']");

	By cityerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");

	By zipcode = By.xpath("//input[@data-automationid='zipCode']");

	By zipcoderr = By.xpath("//*[contains(text(),'The field must be minimum 6')]");

	By zipcoderr1 = By.xpath("//*[contains(text(),'Not allowed more than 30 characters')]");

	By country = By.xpath("//button[@data-automationid='saveAndComplete']");

	By state = By.xpath("//button[@data-automationid='saveAndComplete']");

	By clickmenu = By.xpath("//button[@data-automationid='activeContractor']");

	By clickDelete = By.xpath("//*[@data-automationid='Delete Contractor']");

	By listname = By.xpath("//h3[contains(text(),'erg')]");

	By contractordetail = By.xpath("//button [@data-automationid='close']//following::h3");

	By clickclosebutton = By.xpath("//button[@data-automationid='c']");

	By ThreeDots = By.xpath("//*[@id='fieldy-user-company-contractor-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]");

	By Edit = By
			.xpath("//*[@id='fieldy-user-company-contractor-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]");

	By NameValidation = By.xpath("(//a[@data-goesto='user-profile-view'])[1]");

	By SearchBox = By.xpath("//input[@data-automationid='search']");

	By ClickSearchBox = By.xpath("//input[@data-automationid='search']");

	By Invalid = By.xpath("//div[text()='No Result Found']");

	By Search = By.id("team-user-contract-search");

	By SearchButton = By.xpath("//*[@id='team-company-search-button']/span/i");

	By ContractorCount = By.id("total-company-contractor-count");

	By Email = By.id("login");
	By Pass = By.id("password");
	By Click = By.xpath("//button[@type='submit']");
	By Team = By.xpath("//a[@id='team-menu']");

	By Name = By.id("name");
	By SaveNext = By.xpath("//*[@id=\"team-company-contractor\"]/div/div/div[2]/button");
	By SaveNxt = By.xpath("//button[@data-spinloader='company_contractor_create_edit']");
	By Assertion = By.xpath("//span[text()='Contractor have been updated successfully']");

	By Delete = By
			.xpath("//*[@id='fieldy-user-company-contractor-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[3]");
	By Yes = By.xpath("//*[text()='Yes']");
	By DeleteAssert = By.xpath("//span[text()='Contractor have been deleted successfully']");

	By txtEmail = By.id("email");
	By txtContactPerson = By.id("contact_person_name");
	By txtPhoneNo = By.id("phone");
	By txtFax = By.id("fax");
	By txtWebSite = By.id("website");
	By clickNext = By.xpath("//*[@id=\"team-company-contractor\"]/div/div/div[1]/button[2]/span");

	By AddContractor = By.xpath("//button[@data-formsactions='create']");
	By TotalCount = By.id("total-company-contractor-count");
	By Label = By.xpath("//a[@data-exitpopup='team_companies_contractor']");

	private void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	static int parseInt;

	public int getCount() {
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOfElementLocated(TotalCount)).getText();
		parseInt = Integer.parseInt(text2);
		return parseInt;

	}

	public int actualResult() {
		int a = parseInt + 1;
		return a;
	}

	@FindAll({ @FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='Company']") })
	private WebElement ContactName;

	public int totalCount() throws InterruptedException {
		this.visibility(ContactName);
		String text2 = this.getText(TotalCount);
		int parseInt = Integer.parseInt(text2);
		return parseInt;
	}

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public CreateContractorPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 20);
	}

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	By LocationName = By.id("addresses__name__0");
	By LocationEmail = By.id("addresses__email__0");
	By LocationContactPerson = By.id("addresses__contact_person__0");
	By LocationPhoneNumber = By.id("addresses__phone_number__0");
	By LocationAddress1 = By.id("addresses__line_1__0");
	By LocationAddress2 = By.id("addresses__line_2__0");
	By LocationState = By.id("addresses__state__0");
	By LocationCity = By.id("addresses__city__0");
	By LocationZipcode = By.id("addresses__zipcode__0");
	By CreateContractor = By.xpath("//span[text()='Contractor have been created successfully']");
	By SaveNext1 = By.xpath("//*[@id=\"team-company-contractor\"]/div/div/div[2]/button");
	By Next = By.xpath("//span[text()='Next']");
	By Pre = By.xpath("//span[text()='Previous']");

	public By ErrorLocationName = By.id("addresses__name__0_error");
	public By ErrorLocationEmail = By.id("addresses__email__0_error");
	public By ErrorLocationContactPerson = By.id("addresses__contact_person__0_error");
	public By ErrorLocationPhoneNumber = By.id("addresses__phone_number__0_error");
	public By ErrorAddress1 = By.id("addresses__line_1__0_error");
	public By ErrorAddress2 = By.id("addresses__line_2__0_error");
	public By ErrorState = By.id("addresses__state__0_error");
	public By ErrorCity = By.id("addresses__city__0_error");
	public By ErrorZipCode = By.id("addresses__zipcode__0_error");
	public By Previous = By.xpath("//*[text()='Previous']");

	public void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	public void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	public void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
	}

	private void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	public void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	public void assertName(WebElement element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		Assert.assertEquals(until, text);
	}

	private void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();

	}

	private void visibilitys(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();

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
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;

	}

	public String getText(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return until;

	}

	By Logo = By.xpath("//label[@for='imageUpload']");
	By LogoError = By.id("company_logo_error");
	By FileLogo = By.xpath("//div[text()='Only png,jpeg,jpg Formats Allowed']");
	By EditHeading = By.xpath("//a[@data-exitpopup='team_companies_company']");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-user-company-contractor-list_aserpttbl']/tbody/tr[2]/td[3]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListCompanyName;
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-user-company-contractor-list_aserpttbl']/tbody/tr[2]/td[4]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListName;
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-user-company-contractor-list_aserpttbl']/tbody/tr[2]/td[5]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListEmail;
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-user-company-contractor-list_aserpttbl']/tbody/tr[2]/td[6]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListPhoneNumber;
	By CreateCont = By.xpath("//*[@data-exitpopup='team_companies_contractor']");
	By resert = By.xpath("//a[text()=' Reset Search']");

	public void clearFields(String value) {
		if (value.equals("CompanyName")) {
			this.clearField(CompanyName);
		} else if (value.equals("FirstName")) {
			this.clearField(contractorcpersonfirstname);
		} else if (value.equals("LastName")) {
			this.clearField(contractorcpersonlastname);
		} else if (value.equals("Email")) {
			this.clearField(contractoremail);
		} else if (value.equals("PhoneNumber")) {
			this.clearField(contractorphone);
		} else if (value.equals("Fax")) {
			this.clearField(contractorfax);
		} else if (value.equals("Website")) {
			this.clearField(contractorsite);
		} else if (value.equals("LocationName")) {
			this.clearField(LocationName);
		} else if (value.equals("LocationEmail")) {
			this.clearField(LocationEmail);
		} else if (value.equals("LocationContactPerson")) {
			this.clearField(LocationContactPerson);
		} else if (value.equals("LocationPhoneNumber")) {
			this.clearField(LocationPhoneNumber);
		} else if (value.equals("LocationAddress1")) {
			this.clearField(LocationAddress1);
		} else if (value.equals("LocationAddress2")) {
			this.clearField(LocationAddress2);
		} else if (value.equals("LocationCity")) {
			this.clearField(LocationCity);
		} else if (value.equals("LocationState")) {
			this.clearField(LocationState);
		} else if (value.equals("LocationZipcode")) {
			this.clearField(LocationZipcode);
		} else if (value.equals("Search")) {
			this.clearField(Search);
		}

	}

	public String errorFields(String value) {
		if (value.equals("CompanyName")) {
			return this.getText(ErrorCompanyName);
		} else if (value.equals("FirstName")) {
			return this.getText(contractorcpersonerrfirstname);
		} else if (value.equals("LastName")) {
			return this.getText(contractorcpersonerrlastname);
		} else if (value.equals("Email")) {
			if (this.conditionChecking(contractoremailerr)) {
				return this.getText(contractoremailerr);
			} else {
				this.mouseActionClick(saveform);
				return this.getText(contractoremailerr);
			}
		} else if (value.equals("PhoneNumber")) {
			return this.getText(contractorphoneerr);
		} else if (value.equals("Fax")) {
			return this.getText(contractorfaxerror);
		} else if (value.equals("Website")) {
			return this.getText(contractorsiteerr);
		} else if (value.equals("LocationName")) {
			return this.getText(ErrorLocationName);
		} else if (value.equals("LocationEmail")) {
			return this.getText(ErrorLocationEmail);
		} else if (value.equals("LocationContactPerson")) {
			return this.getText(ErrorLocationContactPerson);
		} else if (value.equals("LocationPhoneNumber")) {
			return this.getText(ErrorLocationPhoneNumber);
		} else if (value.equals("LocationAddress1")) {
			return this.getText(ErrorAddress1);
		} else if (value.equals("LocationAddress2")) {
			return this.getText(ErrorAddress2);
		} else if (value.equals("LocationCity")) {
			return this.getText(ErrorCity);
		} else if (value.equals("LocationState")) {
			return this.getText(ErrorState);
		} else if (value.equals("LocationZipcode")) {
			return this.getText(ErrorZipCode);
		} else if (value.equals("Invalid")) {
			return this.getText(Invalid);
		}
		return value;
	}

	public String clickEvent(String value) throws IOException {
		if (value.equals("Next")) {
			this.mouseActionClick(Next);
		} else if (value.equals("Previous")) {
			this.mouseActionClick(Previous);
		} else if (value.equals("SaveButton")) {
			this.mouseActionClick(saveform);
			if (!this.conditionChecking(ErrorCompanyName)) {
				do {
					this.mouseActionClick(saveform);
				} while (!this.conditionChecking(ErrorCompanyName));
			}
		} else if (value.equals("Navigate")) {
			this.mouseActionClick(Team);
			this.visibilitys(Tittle);
			this.mouseActionClick(contractor);
			this.visibility(ContactName);
			this.getCount();
			this.mouseActionClick(createcontractorbutton);
		} else if (value.equals("BackButton")) {
			this.mouseActionClick(Label);
			this.mouseActionClick(Yes);
		} else if (value.equals("Reset")) {
			this.mouseActionClick(resert);
		} else if (value.equals("Edit")) {
			String text = this.getText(ListCompanyName);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.valuePresent(CompanyName, text);
//			driver.navigate().refresh();
			this.invisible(Spinner);
			this.valuePresent(CompanyName, text);
			this.elementtobeClickable(saveform);
		} else if (value.equals("Delete")) {
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Delete);
			this.mouseActionClick(Yes);
		} else if (value.equals("SubmissionButton")) {
			String text = this.getText(saveform);
			return text;
		} else if (value.equals("ClickButton")) {
			this.mouseActionClick(saveform);
		}
		return value;
	}

	public String labelValidation() {
		String text = this.getText(Label);
		return text;

	}

	public void companyName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(CompanyName, characters256);
		} else if (value.equals("Unique")) {
			String text = this.getText(ListCompanyName);
			this.mouseActionClick(createcontractorbutton);
			this.validationTab(CompanyName, text);
		} else if (value.equals("ValidData")) {
			this.inputText(CompanyName, fakeCompanyName);
			ContractorName = this.getTextAttribute(CompanyName);
		}

	}

	public void contractorPhoneNumber(String value) {
		if (value.equals("MinValidation")) {
			this.validationTab(contractorphone, "123");
		} else if (value.equals("MaxValidation")) {
			this.validationTab(contractorphone, "1231234567891234567812345678");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(contractorphone, "!@#$%^&*(*&^%");
		}
	}

	public void contractorEmail(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(contractoremail, characters256);
		} else if (value.equals("Unique")) {
			String text = this.getText(ListEmail);
			this.mouseActionClick(createcontractorbutton);
			this.validationTab(contractoremail, text);
		} else if (value.equals("InValid")) {
			this.validationTab(contractoremail, "dsfhhdsf");
		}

	}

	public void contractorFax(String value) {
		if (value.equals("MinValidation")) {
			this.validationTab(contractorfax, "1234");
		} else if (value.equals("MaxValidation")) {
			this.validationTab(contractorfax, "221323111311313131313");
		} else if (value.equals("InValid")) {
			this.validationTab(contractorfax, "jskjfdfshf");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(contractorfax, "!@#$%^&*");
		}

	}

	public void contractorFirstName(String value) {
		if (value.equals("MaxValdation")) {
			this.validationTab(contractorcpersonfirstname, characters256);
		}

	}

	public void contractorLastName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(contractorcpersonlastname, characters256);
		}

	}

	public void contractorWebsite(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(contractorsite, characters2048);
		}

	}

	public void locationName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationName, characters256);
		}

	}

	public void locationEmail(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationEmail, characters256);
		} else if (value.equals("InValid")) {
			this.validationTab(LocationEmail, "dsfhhdsf");
		}

	}

	public void locationContactPerson(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationContactPerson, characters512);
		}

	}

	public void locationPhoneNumber(String value) {
		if (value.equals("MinValidation")) {
			this.validationTab(LocationPhoneNumber, "123");
		} else if (value.equals("MaxValidation")) {
			this.validationTab(LocationPhoneNumber, "1231234567891234567812345678");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(LocationPhoneNumber, "!@#$%^&*(*&^%");
		}
	}

	public void locationAddress1(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationAddress1, characters256);
		}

	}

	public void locationAddress2(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationAddress2, characters256);
		}

	}

	public void locationCity(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationCity, characters256);
		}

	}

	public void locationState(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationState, characters256);
		}

	}

	public void locationZipcode(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationZipcode, "123456787676543234");
		} else if (value.equals("MinValidation")) {
			this.validationTab(LocationZipcode, "21");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(LocationZipcode, "!@#$%^");
		}

	}

	public static String ContractorName;
	public static String ContractorFirstName;
	public static String ContractorLastName;
	public static String ContractorEmail;
	public static String ContractorPhoneNumber;
	public static String ContractorWebSite;
	public static String ContractorFaxNumber;
	public static String ContractorLocationName;
	public static String ContractorLocationEmail;
	public static String ContractorLocationContactPerson;
	public static String ContractorLocationPhoneNumber;
	public static String ContractorLocationAddress1;
	public static String ContractorLocationAddress2;
	public static String ContractorLocationCity;
	public static String ContractorLocationState;
	public static String ContractorLocationZipcode;

	public String prepopulationFields(String value) {
		if (value.equals("CompanyName")) {
			String data = this.getTextAttribute(CompanyName);
			return data;
		} else if (value.equals("FirstName")) {
			String data = this.getTextAttribute(contractorcpersonfirstname);
			return data;
		} else if (value.equals("LastName")) {
			String data = this.getTextAttribute(contractorcpersonlastname);
			return data;
		} else if (value.equals("Email")) {
			String data = this.getTextAttribute(contractoremail);
			return data;
		} else if (value.equals("Fax")) {
			String data = this.getTextAttribute(contractorfax);
			return data;
		} else if (value.equals("PhoneNumber")) {
			String data = this.getTextAttribute(contractorphone);
			return data;
		} else if (value.equals("Website")) {
			String data = this.getTextAttribute(contractorsite);
			return data;
		} else if (value.equals("LocationName")) {
			String data = this.getTextAttribute(LocationName);
			return data;
		} else if (value.equals("LocationEmail")) {
			String data = this.getTextAttribute(LocationEmail);
			return data;
		} else if (value.equals("LocationContactPerson")) {
			String data = this.getTextAttribute(LocationContactPerson);
			return data;
		} else if (value.equals("LocationPhoneNumber")) {
			String data = this.getTextAttribute(LocationPhoneNumber);
			return data;
		} else if (value.equals("LocationAddress1")) {
			String data = this.getTextAttribute(LocationAddress1);
			return data;
		} else if (value.equals("LocationAddress2")) {
			String data = this.getTextAttribute(LocationAddress2);
			return data;
		} else if (value.equals("LocationCity")) {
			String data = this.getTextAttribute(LocationCity);
			return data;
		} else if (value.equals("LocationState")) {
			String data = this.getTextAttribute(LocationState);
			return data;
		} else if (value.equals("LocationZipcode")) {
			String data = this.getTextAttribute(LocationZipcode);
			return data;
		}
		return value;
	}

	public void validData(String value) throws IOException {
		if (value.equals("BasicPage")) {
			this.inputText(contractorcpersonfirstname, fakeFirstName);
			ContractorFirstName = this.getTextAttribute(contractorcpersonfirstname);
			this.inputText(contractorcpersonlastname, fakeLastName);
			ContractorLastName = this.getTextAttribute(contractorcpersonlastname);
			this.inputText(contractoremail, fakeEmail);
			ContractorEmail = this.getTextAttribute(contractoremail);
			this.inputText(contractorphone, fakePhoneNumber);
			ContractorPhoneNumber = this.getTextAttribute(contractorphone);
			this.inputText(contractorfax, fakeFaxNumber);
			ContractorFaxNumber = this.getTextAttribute(contractorfax);
			this.inputText(contractorsite, fakeWebsite);
			ContractorWebSite = this.getTextAttribute(contractorsite);
			this.clickEvent("Next");
		} else if (value.equals("LocationPage")) {
			this.inputText(LocationName, fakecountry);
			ContractorLocationName = this.getTextAttribute(LocationName);
			this.inputText(LocationEmail, fakeEmail);
			ContractorLocationEmail = this.getTextAttribute(LocationEmail);
			this.inputText(LocationContactPerson, fakeContactPersonName);
			ContractorLocationContactPerson = this.getTextAttribute(LocationContactPerson);
			this.inputText(LocationPhoneNumber, fakePhoneNumber);
			ContractorLocationPhoneNumber = this.getTextAttribute(LocationPhoneNumber);
			this.inputText(LocationAddress1, fakeAddress1);
			ContractorLocationAddress1 = this.getTextAttribute(LocationAddress1);
			this.inputText(LocationAddress2, fakeAddress2);
			ContractorLocationAddress2 = this.getTextAttribute(LocationAddress2);
			this.inputText(LocationCity, fakeCity);
			ContractorLocationCity = this.getTextAttribute(LocationCity);
			this.inputText(LocationState, fakeState);
			ContractorLocationState = this.getTextAttribute(LocationState);
			this.inputText(LocationZipcode, fakeZipcode);
			ContractorLocationZipcode = this.getTextAttribute(LocationZipcode);
			this.clickEvent("ClickButton");
		}

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

	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By Cancel = By.xpath("//*[@class='js-snackbar__close bold']");
	static String response;

	public String responseMessage(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message")) {
			if (this.conditionChecking(Message)) {
				response = this.getText(Message);
				this.invisible(Message);
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(saveform);
					if (this.conditionChecking(Message)) {
						response = this.getText(Message);
						this.invisible(Message);
						if (response.equals(getPropertyValue("CompanyContractorCreatedMessage"))
								|| response.equals(getPropertyValue("CompanyContractorUpdatedMessage"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateFunction")) {
			if (response.equals(getPropertyValue("AlreadyExistedEmail"))
					|| response.equals(getPropertyValue("CompanyNameExists"))) {
				this.clickEvent("BackButton");
			}
		}
		return response;
	}

	public String listValidation(String value) {
		if (value.equals("SearchData")) {
			this.inputText(Search, response);
			this.mouseActionClick(SearchButton);
		} else if (value.equals("ListCompanyName")) {
			response = this.getText(ListCompanyName);
			return response;
		} else if (value.equals("ListEmail")) {
			response = this.getText(ListEmail);
			return response;
		} else if (value.equals("ListPhoneNumber")) {
			response = this.getText(ListPhoneNumber);
			return response;
		} else if (value.equals("ListName")) {
			response = this.getText(ListName);
			return response;
		} else if (value.equals("Invalid")) {
			this.tagValidation(Search, "dfkjhjfhshkdsh");
		}
		return value;

	}

	public void clearAllFields(String value) {
		if (value.equals("Basic")) {
			List<String> asList = Arrays.asList("CompanyName", "FirstName", "LastName", "Email", "PhoneNumber", "Fax",
					"Website");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		} else if (value.equals("Location")) {
			List<String> asList = Arrays.asList("LocationName", "LocationEmail", "LocationContactPerson",
					"LocationPhoneNumber", "LocationAddress1", "LocationAddress2", "LocationCity", "LocationState",
					"LocationZipcode");
			for (int i = 0; i < asList.size(); i++) {
				this.clearFields(asList.get(i));
			}
		}

	}

	public String dataConditionCheck(String value) throws IOException, InterruptedException {
		if (value.equals("Condition")) {
			response = this.getText(ContactName);
			return response;
		} else if (value.equals("AlternateFunction")) {
			if (response.equals("Company")) {
				this.visibility(ContactName);
			} else if (response.equals("No Result Found")) {
				this.mouseActionClick(createcontractorbutton);
				this.inputText(CompanyName, fakeCompanyName);
				this.validData("BasicPage");
				this.validData("LocationPage");
				this.responseMessage("Message");
			}
		}
		return response;
	}

}
