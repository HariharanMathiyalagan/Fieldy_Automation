package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.github.javafaker.Faker;

import freemarker.ext.jsp._FreeMarkerPageContext2;

public class TeamUserPage extends BaseClass {

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
	String fakeTittle = faker.name().title();
	String fakecountry = faker.address().country();
	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String maxPhoneNumber = RandomStringUtils.randomNumeric(25);
	String minPhoneNumber = RandomStringUtils.randomNumeric(2);

	public TeamUserPage(WebDriver driver) {
		this.driver = driver;
	}

	public void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
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

	public void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	public void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	public void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	public void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public String getText(By element) {
		wait = new WebDriverWait(driver, 20);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;

	}

	public void invisible(By element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

	}

	public void elementClickable(By element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public String getText(WebElement element) {
		wait = new WebDriverWait(driver, 20);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return until;

	}

	private void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();

	}

	private void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

	}

	public void mouseAction(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();

	}

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}

	By Team = By.id("team-menu");
	By Tittle = By.xpath("//*[@id='team-company-details-company-name']//*[@class='company']");
	By User = By.id("team-user-menu");
	By ListLabel = By.xpath("//*[@class='page-header-left back-btn']");
	By CreateUser = By.xpath("//*[@data-formdynamic='user_create_edit']");
	By Search = By.id("team-user-user-user-search-filter");
	By SearchButton = By.id("team-user-user-search-button");
	By ContractorSearch = By.id("team-user-contractor-search-main");
	By ContractorSearchButton = By.id("team-user-contractor-search-btn");
	By Reset = By.xpath("//*[text()=' Reset Search']");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-user-user-contractor-list_aserpttbl']/tbody/tr[2]/td[3]"),
			@FindBy(xpath = "//*[@id='fieldy-user-user-user-list_aserpttbl']/tbody/tr[2]/td[3]") })
	WebElement ListFirstName;
	By ListPhoneNumber = By.xpath("//*[@id='fieldy-user-user-user-list_aserpttbl']/tbody/tr[2]/td[6]");
	By ListEmail = By.xpath("//*[@id='fieldy-user-user-user-list_aserpttbl']/tbody/tr[2]/td[7]");
	By ListContractorPhoneNumber = By.xpath("//*[@id='fieldy-user-user-contractor-list_aserpttbl']/tbody/tr[2]/td[7]");
	By ListContractorEmail = By.xpath("//*[@id='fieldy-user-user-contractor-list_aserpttbl']/tbody/tr[2]/td[8]");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-user-user-user-list_aserpttbl']/tbody/tr[2]/td[1]/div"),
			@FindBy(xpath = "//*[@id='fieldy-user-user-contractor-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[1]/i") })
	WebElement ThreeDots;
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-user-user-user-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]"),
			@FindBy(xpath = "//*[@id='fieldy-user-user-contractor-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[2]") })
	WebElement Edit;
	@FindAll({
			@FindBy(xpath = "//*[@id='fieldy-user-user-contractor-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[3]"),
			@FindBy(xpath = "//*[@id='fieldy-user-user-user-list_aserpttbl']/tbody/tr[2]/td[1]/div/div[2]/ul/li[3]") })
	WebElement Delete;
	By SendInvite = By.xpath("//*[@data-formdynamic='user_send_invite']");
	By Contractor = By.xpath("//a[@data-n-linkto='team_user_contractor']");
	By Logo = By.xpath("//label[@for='user_image']");
	By LogoError = By.id("user_image_error");
	By FileLogo = By.xpath("//div[text()='Only png,jpeg,jpg Formats Allowed']");
	By FirstName = By.id("first_name");
	By ErrorFirstName = By.id("first_name_error");
	By LastName = By.id("last_name");
	By ErrorLastName = By.id("last_name_error");
	By Type = By.xpath("//input[@data-dropdownlist='user-type']");
	By ErrorType = By.id("role_id_error");
	By Admin = By.xpath("(//*[@class='p-2 list-hover-bg team-user-type w-20-ellipsis w-100'])[1]");
	By Manager = By.xpath("(//*[@class='p-2 list-hover-bg team-user-type w-20-ellipsis w-100'])[2]");
	By Operator = By.xpath("(//*[@class='p-2 list-hover-bg team-user-type w-20-ellipsis w-100'])[3]");
	By Technician = By.xpath("(//*[@class='p-2 list-hover-bg team-user-type w-20-ellipsis w-100'])[4]");
	By JobTittle = By.id("job_title");
	By ErrorJobTittle = By.id("job_title_error");
	By Email = By.id("email");
	By ErrorEmail = By.id("email_error");
	By PhoneNumber = By.id("phones__number__0");
	By ErrorPhoneNumber = By.id("phones__number__0_error");
	By Next = By.xpath("//*[text()='Next']");
	By Previous = By.xpath("//*[text()='Previous']");
	By SaveComplete = By.xpath("//*[@data-automationid='save_complete']");
	By LocationName = By.id("addresses__name__0");
	By ErrorLocationName = By.id("addresses__name__0_error");
	By Address1 = By.id("addresses__line_1__0");
	By ErrorAddress1 = By.id("addresses__line_1__0_error");
	By Address2 = By.id("addresses__line_2__0");
	By ErrorAddress2 = By.id("addresses__line_2__0_error");
	By State = By.id("addresses__state__0");
	By ErrorState = By.id("addresses__state__0_error");
	By City = By.id("addresses__city__0");
	By ErrorCity = By.id("addresses__city__0_error");
	By Zipcode = By.id("addresses__zipcode__0");
	By ErrorZipcode = By.id("addresses__zipcode__0_error");
	By BussinessUnit = By.xpath("//input[@data-dropdownlist='business-unit']");
	By General = By.id("business_unit_id-autocomplete-list");
	By ServiceType = By.xpath("//input[@data-dropdownlist='service-type']");
	By Repair = By.id("service_type_id-autocomplete-list");
	By Organization = By.xpath("//input[@value='1']");
	By CompanyName = By.xpath("//input[@data-dropdownlist='contractor-company']");
	By FirstCompanyName = By
			.xpath("(//div[@class='p-2 list-hover-bg team-contractor-company w-20-ellipsis w-100'])[1]");
	By CompanyNameError = By.id("company_id_error");
	By CreateContractor = By.xpath("//*[@data-tabformid='team-user-contract-create']");
	By FilterByCompany = By.id("Organization-filter-reset");
	By ListFilterCompany = By.xpath("(//input[@type='checkbox'])[1]");
	By ListCompnayName = By.xpath("(//div[@id='SearchableDropdown'])[1]");
	By FilterSearch = By.xpath("(//i[@class='fa fa-search'])[2]");
	By UserCreateLabelHeading = By.xpath("//a[@data-exitpopup='team_user_user__all__role']");
	By ContractorCreateLabelHeading = By.xpath("//a[@data-exitpopup='team_user_contractor']");
	By SuccessfulMessage = By.xpath("//*[text()='User created successfully']");
	By UserUpdatedMessgae = By.xpath("//*[text()='User information updated successfully']");
	By DeletedMessage = By.xpath("//*[text()='User have been deleted successfully']");
	By Yes = By.xpath("//*[text()='Yes']");
	By HeadingFirstName = By.xpath("//td[text()='First Name']");
	By InValid = By.xpath("//*[text()='No Result Found']");
	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div/div");
	By DropDownSearch = By.id("team-user-contractor-dropdown-serach");
	By ListCompanyFirstName = By.xpath("//*[@id='fieldy-user-user-contractor-list_aserpttbl']/tbody/tr[2]/td[5]");
	By CreateContractorMessage = By.xpath("//*[text()='Contractor user created successfully']");
	By UpdateContractorMessage = By.xpath("//*[text()='Contractor user information updated successfully']");
	By DeleteContractorMessage = By.xpath("//*[text()='Contractor user have been deleted successfully']");
	By Name = By.xpath("//td[text()='First Name']");
	By TotalCount = By.id("All-Count");
	By ContractorCount = By.id("total-user-contractor-count");
	@FindAll({ @FindBy(xpath = "//*[text()='First Name']"), @FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement PageLand;
	static int parseInt;

	public int getCount(int value) {
		if (value == 1) {
			wait = new WebDriverWait(driver, 10);
			String text2 = wait.until(ExpectedConditions.visibilityOfElementLocated(TotalCount)).getText();
			StringBuffer buffer = new StringBuffer();
			StringBuffer append = null;
			for (int i = 0; i < text2.length(); i++) {
				if (Character.isDigit(text2.charAt(i))) {
					append = buffer.append(text2.charAt(i));
					parseInt = Integer.parseInt(append.toString());
				}
			}
			return parseInt;
		} else if (value == 2) {
			String text = this.getText(ContractorCount);
			parseInt = Integer.parseInt(text);
			return parseInt;
		}
		return value;
	}

	public String actualResult(String value) {
		if (value.equals("User")) {
			int a = parseInt + 1;
			String s = String.valueOf(a);
			String valueOf = "All Users (" + s + ")";
			return valueOf;
		} else if (value.equals("Contractor")) {
			int actual = parseInt + 1;
			String valueOf = String.valueOf(actual);
			return valueOf;
		}
		return value;
	}

	public String totalCount(String value) throws InterruptedException {
		if (value.equals("User")) {
			this.visibility(PageLand);
			String text2 = this.getText(TotalCount);
			return text2;
		} else if (value.equals("Contractor")) {
			this.visibility(PageLand);
			String text2 = this.getText(ContractorCount);
			return text2;
		}
		return value;
	}

	public void clearField(String value) {
		if (value.equals("FirstName")) {
			this.clearField(FirstName);
		} else if (value.equals("LastName")) {
			this.clearField(LastName);
		} else if (value.equals("JobTittle")) {
			this.clearField(JobTittle);
		} else if (value.equals("UserType")) {
			this.clearField(Type);
		} else if (value.equals("Email")) {
			this.clearField(Email);
		} else if (value.equals("PhoneNumber")) {
			this.clearField(PhoneNumber);
		} else if (value.equals("LocationName")) {
			this.clearField(LocationName);
		} else if (value.equals("Address1")) {
			this.clearField(Address1);
		} else if (value.equals("Address2")) {
			this.clearField(Address2);
		} else if (value.equals("City")) {
			this.clearField(City);
		} else if (value.equals("State")) {
			this.clearField(State);
		} else if (value.equals("Zipcode")) {
			this.clearField(Zipcode);
		} else if (value.equals("Search")) {
			this.clearField(Search);
		} else if (value.equals("ContractorSearch")) {
			this.clearField(ContractorSearch);
		}
	}

	public String errorField(String value) {
		if (value.equals("FirstName")) {
			String text = this.getText(ErrorFirstName);
			return text;
		} else if (value.equals("LastName")) {
			String text = this.getText(ErrorLastName);
			return text;
		} else if (value.equals("JobTittle")) {
			String text = this.getText(ErrorJobTittle);
			return text;
		} else if (value.equals("Email")) {
			String text = this.getText(ErrorEmail);
			return text;
		} else if (value.equals("UserType")) {
			String text = this.getText(ErrorType);
			return text;
		} else if (value.equals("PhoneNumber")) {
			String text = this.getText(ErrorPhoneNumber);
			return text;
		} else if (value.equals("LocationName")) {
			String text = this.getText(ErrorLocationName);
			return text;
		} else if (value.equals("Address1")) {
			String text = this.getText(ErrorAddress1);
			return text;
		} else if (value.equals("Address2")) {
			String text = this.getText(ErrorAddress2);
			return text;
		} else if (value.equals("City")) {
			String text = this.getText(ErrorCity);
			return text;
		} else if (value.equals("State")) {
			String text = this.getText(ErrorState);
			return text;
		} else if (value.equals("Zipcode")) {
			String text = this.getText(ErrorZipcode);
			return text;
		} else if (value.equals("Invalid")) {
			String text = this.getText(InValid);
			return text;
		} else if (value.equals("CompanyName")) {
			String text = this.getText(CompanyNameError);
			return text;
		}
		return value;
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

	public void jobTittle(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(JobTittle, characters256);
		}
	}

	public void email(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Email, characters256);
		} else if (value.equals("Unique")) {
			String text = this.getText(ListEmail);
			this.mouseActionClick(CreateUser);
			this.validationTab(Email, text);
		} else if (value.equals("InValid")) {
			this.validationTab(Email, "dsfhhdsf");
		} else if (value.equals("UniqueContractor")) {
			String text = this.getText(ListContractorEmail);
			this.mouseActionClick(CreateContractor);
			this.validationTab(Email, text);
		}

	}

	public void phoneNumber(String value) {
		if (value.equals("MinValidation")) {
			this.validationTab(PhoneNumber, "123");
		} else if (value.equals("MaxValidation")) {
			this.validationTab(PhoneNumber, "1231234567891234567812345678");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(PhoneNumber, "!@#$%^&*(*&^%");
		}
	}

	public void locationName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(LocationName, characters256);
		}
	}

	public void address1(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Address1, characters256);
		}

	}

	public void address2(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Address2, characters256);
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
			this.validationTab(Zipcode, "123456787676543234");
		} else if (value.equals("MinValidation")) {
			this.validationTab(Zipcode, "21");
		} else if (value.equals("SpecialCharacter")) {
			this.validationTab(Zipcode, "!@#$%^");
		}

	}

	By Label = By.xpath("//a[@data-exitpopup='team_user_user__all__role']");
	By ContractorLabel = By.xpath("//a[@data-exitpopup='team_user_contractor']");
	static String label;

	public String labelValidation(String value) {
		if (value.equals("FormLabel")) {
			label = this.getText(Label);
			return label;
		} else if (value.equals("ListLabel")) {
			label = this.getText(ListLabel);
			return label;
		} else if (value.equals("ContractorLabel")) {
			label = this.getText(ContractorLabel);
			return label;
		}
		return value;
	}

	public String clickEvent(String value) {
		if (value.equals("SaveUpdate")) {
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("Next")) {
			this.mouseActionClick(Next);
		} else if (value.equals("Previous")) {
			this.mouseActionClick(Previous);
		} else if (value.equals("ButtonPresent")) {
			String text = this.getText(SaveComplete);
			return text;
		} else if (value.equals("Navigate")) {
			this.mouseActionClick(Team);
			this.visibility(Tittle);
			this.mouseActionClick(User);
			this.visibility(PageLand);
			this.getCount(1);
			this.mouseActionClick(CreateUser);
		} else if (value.equals("NavigateContractor")) {
			this.mouseActionClick(Team);
			this.visibility(Tittle);
			this.mouseActionClick(User);
			this.mouseActionClick(Contractor);
			this.visibility(PageLand);
			this.getCount(2);
			this.mouseActionClick(CreateContractor);
		} else if (value.equals("Back")) {
			this.mouseActionClick(Label);
			this.mouseActionClick(Yes);
		} else if (value.equals("ContractorBack")) {
			this.mouseActionClick(ContractorLabel);
			this.mouseActionClick(Yes);
		} else if (value.equals("Reset")) {
			this.mouseActionClick(Reset);
		} else if (value.equals("Edit")) {
			String text = this.getText(ListFirstName);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.visibility(Label);
			this.invisible(Spinner);
//			driver.navigate().refresh();
			this.valuePresent(FirstName, text);
			this.elementClickable(Next);
		} else if (value.equals("ContractorEdit")) {
			String text = this.getText(ListFirstName);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.visibility(ContractorLabel);
			this.invisible(Spinner);
//			driver.navigate().refresh();
			this.valuePresent(FirstName, text);
			this.elementClickable(Next);
		} else if (value.equals("Delete")) {
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Delete);
			this.mouseActionClick(Yes);
		} else if (value.equals("OrganizationRadioButton")) {
			this.scrollDown();
			this.mouseActionClick(Organization);
			this.mouseActionClick(SaveComplete);
		}
		return value;
	}

	static String listData;

	public String listValidation(String value) {
		if (value.equals("SearchData")) {
			this.inputText(Search, listData);
			this.mouseActionClick(SearchButton);
		} else if (value.equals("FirstName")) {
			listData = this.getText(ListFirstName);
			return listData;
		} else if (value.equals("PhoneNumber")) {
			listData = this.getText(ListPhoneNumber);
			return listData;
		} else if (value.equals("Email")) {
			listData = this.getText(ListEmail);
			return listData;
		} else if (value.equals("Invalid")) {
			this.tagValidation(Search, "fsdjfkjsjfsjfl");
		} else if (value.equals("ContractorInvalid")) {
			this.tagValidation(ContractorSearch, "fsdjfkjsjfsjfl");
		} else if (value.equals("ContractorSearchData")) {
			this.tagValidation(ContractorSearch, listData);
		} else if (value.equals("ContractorPhoneNumber")) {
			listData = this.getText(ListContractorPhoneNumber);
			return listData;
		} else if (value.equals("ContractorListEmail")) {
			listData = this.getText(ListContractorEmail);
			return listData;
		} else if (value.equals("ContractorListCompany")) {
			listData = this.getText(ListCompanyFirstName);
			return listData;
		} else if (value.equals("FilterCompany")) {
			this.mouseAction(FilterByCompany);
			this.mouseActionClick(ListFilterCompany);
			this.mouseActionClick(FilterSearch);
			String text = this.getText(ListCompanyFirstName);
			return text;
		}
		return value;
	}

	public static String firstName;
	public static String lastName;
	public static String email;
	public static String type;
	public static String jobTittle;
	public static String phoneNumber;
	public static String locationName;
	public static String address1;
	public static String address2;
	public static String city;
	public static String state;
	public static String zipcode;
	public static String bussinessUnit;
	public static String serviceType;
	public static String companyName;

	public String prepopulationFields(String value) {
		if (value.equals("FirstName")) {
			String data = this.getTextAttribute(FirstName);
			return data;
		} else if (value.equals("LastName")) {
			String data = this.getTextAttribute(LastName);
			return data;
		} else if (value.equals("Type")) {
			String data = this.getTextAttribute(Type);
			return data;
		} else if (value.equals("JobTittle")) {
			String data = this.getTextAttribute(JobTittle);
			return data;
		} else if (value.equals("Email")) {
			String data = this.getTextAttribute(Email);
			return data;
		} else if (value.equals("PhoneNumber")) {
			String data = this.getTextAttribute(PhoneNumber);
			return data;
		} else if (value.equals("LocationName")) {
			String data = this.getTextAttribute(LocationName);
			return data;
		} else if (value.equals("BussinessUnit")) {
			String data = this.getTextAttribute(BussinessUnit);
			return data;
		} else if (value.equals("ServiceType")) {
			String data = this.getTextAttribute(ServiceType);
			return data;
		} else if (value.equals("LocationAddress1")) {
			String data = this.getTextAttribute(Address1);
			return data;
		} else if (value.equals("LocationAddress2")) {
			String data = this.getTextAttribute(Address2);
			return data;
		} else if (value.equals("LocationCity")) {
			String data = this.getTextAttribute(City);
			return data;
		} else if (value.equals("LocationState")) {
			String data = this.getTextAttribute(State);
			return data;
		} else if (value.equals("LocationZipcode")) {
			String data = this.getTextAttribute(Zipcode);
			return data;
		} else if (value.equals("CompanyName")) {
			String data = this.getTextAttribute(CompanyName);
			return data;
		}
		return value;
	}

	public void validateFillData(String value) {
		if (value.equals("Basic")) {
			Faker faker = new Faker(new Locale("en-IND"));
			String fakeFirstName = faker.name().firstName();
			String fakeLastName = faker.name().lastName();
			String fakeEmail = faker.internet().safeEmailAddress();
			String fakePhoneNumber = faker.phoneNumber().phoneNumber();
			String fakeTittle = faker.name().title();
			this.inputText(FirstName, fakeFirstName);
			firstName = this.getTextAttribute(FirstName);
			this.inputText(LastName, fakeLastName);
			lastName = this.getTextAttribute(LastName);
			this.mouseActionClick(Type);
			this.mouseActionClick(Technician);
			type = this.getTextAttribute(Type);
			this.inputText(JobTittle, fakeTittle);
			jobTittle = this.getTextAttribute(JobTittle);
			this.inputText(Email, fakeEmail);
			email = this.getTextAttribute(Email);
			this.inputText(PhoneNumber, fakePhoneNumber);
			phoneNumber = this.getTextAttribute(PhoneNumber);
		} else if (value.equals("Location")) {
			Faker faker = new Faker(new Locale("en-IND"));
			String fakeAddress1 = faker.address().buildingNumber();
			String fakeAddress2 = faker.address().streetName();
			String fakeCity = faker.address().city();
			String fakeState = faker.address().state();
			String fakeZipcode = faker.address().zipCode();
			this.inputText(LocationName, fakecountry);
			locationName = this.getTextAttribute(LocationName);
			this.inputText(Address1, fakeAddress1);
			address1 = this.getTextAttribute(Address1);
			this.inputText(Address2, fakeAddress2);
			address2 = this.getTextAttribute(Address2);
			this.inputText(City, fakeCity);
			city = this.getTextAttribute(City);
			this.inputText(State, fakeState);
			state = this.getTextAttribute(State);
			this.inputText(Zipcode, fakeZipcode);
			zipcode = this.getTextAttribute(Zipcode);
		} else if (value.equals("BasicContractor")) {
			Faker faker = new Faker(new Locale("en-IND"));
			String fakeFirstName = faker.name().firstName();
			String fakeLastName = faker.name().lastName();
			String fakeEmail = faker.internet().safeEmailAddress();
			String fakePhoneNumber = faker.phoneNumber().phoneNumber();
			this.inputText(FirstName, fakeFirstName);
			firstName = this.getTextAttribute(FirstName);
			this.inputText(LastName, fakeLastName);
			lastName = this.getTextAttribute(LastName);
			this.mouseActionClick(BussinessUnit);
			this.mouseActionClick(General);
			bussinessUnit = this.getTextAttribute(BussinessUnit);
			this.mouseActionClick(ServiceType);
			this.mouseActionClick(Repair);
			serviceType = this.getTextAttribute(ServiceType);
			this.inputText(Email, fakeEmail);
			email = this.getTextAttribute(Email);
			this.inputText(PhoneNumber, fakePhoneNumber);
			phoneNumber = this.getTextAttribute(PhoneNumber);
		} else if (value.equals("ContractorCompany")) {
			this.scrollDown();
			this.mouseActionClick(CompanyName);
			this.mouseActionClick(FirstCompanyName);
			companyName = this.getTextAttribute(CompanyName);
		}

	}

	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By Cancel = By.xpath("//*[@class='js-snackbar__close bold']");

	public String responseMessage(String value) throws IOException, InterruptedException {
		Boolean check = true;
		if (value.equals("Message")) {
			if (this.conditionChecking(Message)) {
				listData = this.getText(Message);
				this.invisible(Message);
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(SaveComplete);
					if (this.conditionChecking(Message)) {
						listData = this.getText(Message);
						this.invisible(Message);
						if (listData.equals(getPropertyValue("UserCreatedMessgae"))
								|| listData.equals(getPropertyValue("UserUpdatedMessage"))) {
							check = false;
						}
					}
				} while (check);
			}
		} else if (value.equals("AlternateFunction")) {
			if (listData.equals(getPropertyValue("UserEmailAlreadyExist"))
					&& (label.equals(getPropertyValue("TeamCreateUserPage"))
							|| label.equals(getPropertyValue("TeamUserEditPage")))) {
				this.mouseActionClick(User);
				this.visibility(PageLand);
			} else if (listData.equals(getPropertyValue("UserEmailAlreadyExist"))
					&& (label.equals(getPropertyValue("TeamCreateContractorPage"))
							|| label.equals(getPropertyValue("TeamEditContractorPage")))) {
				this.mouseActionClick(User);
				this.visibility(PageLand);
				this.mouseActionClick(Contractor);
				this.visibility(PageLand);
			}
		}
		return listData;
	}

	public void clearAllFields(String value) {
		if (value.equals("Basic")) {
			java.util.List<String> asList = Arrays.asList("FirstName", "LastName", "JobTittle", "Email", "PhoneNumber");
			this.scrollDown();
			for (int i = 0; i < asList.size(); i++) {
				this.clearField(asList.get(i));
			}
		} else if (value.equals("Location")) {
			java.util.List<String> asList = Arrays.asList("LocationName", "Address1", "Address2", "City", "State",
					"Zipcode");
			for (int i = 0; i < asList.size(); i++) {
				this.clearField(asList.get(i));
			}
		} else if (value.equals("ContractorBasic")) {
			java.util.List<String> asList = Arrays.asList("FirstName", "LastName", "Email", "PhoneNumber");
			this.scrollDown();
			for (int i = 0; i < asList.size(); i++) {
				this.clearField(asList.get(i));
			}
		}

	}

	static String response;
	static String listLabel;
	static String techCount;

	public String dataConditionCheck(String value) throws IOException, InterruptedException {
		if (value.equals("Condition")) {
			response = this.getText(PageLand);
			listLabel = this.getText(ListLabel);
			return response;
		} else if (value.equals("UserCreate") || value.equals("UserContractorCreate")) {
			if (value.equals("UserCreate")) {
				this.getCount(1);
				int userCount = 5 - parseInt;
				if (userCount <= 5) {
					for (int i = 0; i < userCount; i++) {
						this.mouseActionClick(CreateUser);
						this.validateFillData("Basic");
						this.clickEvent("Next");
						this.validateFillData("Location");
						this.clickEvent("SaveUpdate");
						this.responseMessage("Message");
						this.visibility(PageLand);
					}
				}
			} else if (value.equals("UserContractorCreate")) {
				this.getCount(2);
				int userContractorCount = 10 - parseInt;
				if (userContractorCount <= 10) {
					for (int i = 0; i < userContractorCount; i++) {
						this.mouseActionClick(CreateContractor);
						this.validateFillData("BasicContractor");
						this.clickEvent("Next");
						this.validateFillData("Location");
						this.clickEvent("SaveUpdate");
						this.responseMessage("Message");
						this.visibility(PageLand);
					}
				}
			}
		}
		return response;

	}

	public String createUser() throws InterruptedException, IOException {
		for (int i = 0; i < 3; i++) {
			this.mouseActionClick(CreateUser);
			this.validateFillData("Basic");
			this.clickEvent("Next");
			this.validateFillData("Location");
			this.clickEvent("SaveUpdate");
			this.responseMessage("Message");
		}
//		return this.responseMessage("Message");
		return listData;
	}

}
