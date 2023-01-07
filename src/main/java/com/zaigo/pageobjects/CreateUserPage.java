package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class CreateUserPage extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;

	Faker faker = new Faker(new Locale("en-IND"));
	String fakeFirstName = faker.name().firstName();
	String fakeLastName = faker.name().lastName();
	String fakeEmail = faker.name().firstName().toLowerCase();
	String fakePhoneNumber = faker.phoneNumber().phoneNumber();
	String fakeAddress1 = faker.address().buildingNumber();
	String fakeAddress2 = faker.address().streetName();
	String fakeCity = faker.address().city();
	String fakeState = faker.address().state();
	String fakeZipcode = faker.address().zipCode();
	String fakeWebsite = faker.company().url();
	String fakeCompanyName = faker.company().name();
	String fakeTittle = faker.name().title();

	public CreateUserPage(WebDriver driver) {
		this.driver = driver;
	}

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
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
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
		wait = new WebDriverWait(driver, 20);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;

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

	By User = By.id("team-user-menu");
	By CreateUser = By.xpath("//*[@data-formdynamic='user_create_edit']");
	By Search = By.id("team-user-user-user-search-filter");
	By SearchButton = By.id("team-user-user-search-button");
	By ContractorSearch = By.id("team-user-contractor-search-main");
	By ContractorSearchButton = By.id("team-user-contractor-search-btn");
	By Reset = By.xpath("//*[text()=' Reset Search']");
	By ListFirstName = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[1]");
	By ListPhoneNumber = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[4]	");
	By ListEmail = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[5]");
	By ListContractorPhoneNumber = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[5]");
	By ListContractorEmail = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[6]");
	By ThreeDots = By.xpath("(//*[@class='dot dot-hover'])[1]");
	By Edit = By.xpath("(//li[@data-formsactions='edit'])[1]");
	By Delete = By.xpath("(//li[@data-tabformid='undefined'])[1]");
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
	By Admin = By.id("dropid-61");
	By Manager = By.id("dropid-203");
	By Operator = By.id("dropid-204");
	By Technician = By.id("dropid-205");
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
	By InValid = By.xpath("//div[text()='No Result Found']");
	By DropDownSearch = By.id("team-user-contractor-dropdown-serach");
	By ListCompanyFirstName = By.xpath("(//*[@class='p-2 pt-1 pb-1 text-ellipsis'])[3]");
	By CreateContractorMessage = By.xpath("//*[text()='Contractor user created successfully']");
	By UpdateContractorMessage = By.xpath("//*[text()='Contractor user information updated successfully']");
	By DeleteContractorMessage = By.xpath("//*[text()='Contractor user have been deleted successfully']");
	By Name = By.xpath("//td[text()='First Name']");

	public void userPageLanding() {
		this.mouseActionClick(User);
		this.mouseActionClick(CreateUser);

	}

	public String landingUserFormPage() {
		String text = this.getText(UserCreateLabelHeading);
		return text;
	}

	public String landingContractorFormPage() {
		this.mouseActionClick(Contractor);
		this.mouseActionClick(CreateContractor);
		String text = this.getText(ContractorCreateLabelHeading);
		return text;

	}

	public void maxSizeLogo() throws AWTException, InterruptedException {
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("dsc00531");

	}

	public String errorLogo() {
		String text = this.getText(LogoError);
		return text;

	}

	public String errorFormatLogo() {
		String text = this.getText(FileLogo);
		return text;

	}

	public void fileFormatLogo() throws InterruptedException, AWTException {
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("WhatsApp Image 2022-07-06 at 6.09.01 PM");

	}

	public String mandatoryValidationFirstNameField() {
		this.assertName(SaveComplete, "Save & Complete");
		this.mouseActionClick(SaveComplete);
		String text = this.getText(ErrorFirstName);
		this.inputText(FirstName, "Demo");
		return text;
	}

	public String mandatoryValidationTypeField() {
		this.mouseActionClick(Next);
		String text = this.getText(ErrorType);
		this.mouseActionClick(Type);
		this.mouseActionClick(Admin);
		return text;

	}

	public String mandatoryValidationEmailField() {
		this.mouseActionClick(SaveComplete);
		String text = this.getText(ErrorEmail);
		this.inputText(Email, "jsddhb@dhs.in");
		return text;

	}

	public void maxValidationFirstName() throws IOException {
		this.validationTab(FirstName, getPropertyValue("256Characters"));
	}

	public String errorFirstName() {
		String text = this.getText(ErrorFirstName);
		return text;

	}

	public void clearFirstName() {
		this.clearField(FirstName);
		this.inputText(FirstName, "Demo");

	}

	public void maxValidationLastName() throws IOException {
		this.validationTab(LastName, getPropertyValue("256Characters"));

	}

	public String errorLastName() {
		String text = this.getText(ErrorLastName);
		return text;

	}

	public void clearLastName() {
		this.clearField(LastName);

	}

	public void maxValidationJobTittle() throws IOException {
		this.validationTab(JobTittle, getPropertyValue("256Characters"));
		this.clearField(Email);

	}

	public String errorJobTittle() {
		String text = this.getText(ErrorJobTittle);
		return text;

	}

	public void clearJobTittle() {
		this.scrollDown();
		this.clearField(JobTittle);

	}

	public void maxValidationEmail() throws IOException {
		this.validationTab(Email, getPropertyValue("256Characters"));

	}

	public String errorEmail() {
		String text = this.getText(ErrorEmail);
		return text;

	}

	public void clearEmail() {
		this.clearField(Email);
		this.inputText(Email, "jdfbs@cdd.in");

	}

	public void invalidEmail() throws IOException {
		this.validationTab(Email, "jhsdiusgd");

	}

	public void minValidationPhoneNumber() {
		this.validationTab(PhoneNumber, "12");

	}

	public String errorPhoneNumber() {
		String text = this.getText(ErrorPhoneNumber);
		return text;

	}

	public void clearPhoneNumber() {
		this.clearField(PhoneNumber);

	}

	public void maxValidationPhoneNumber() {
		this.validationTab(PhoneNumber, "2314569897845621232134234243456721");

	}

	public void nextButton() {
		this.mouseActionClick(Next);

	}

	public void maxValidationLocationName() throws IOException {
		this.validationTab(LocationName, getPropertyValue("256Characters"));

	}

	public String errorLocatioName() {
		String text = this.getText(ErrorLocationName);
		return text;

	}

	public void clearLocationName() {
		this.clearField(LocationName);

	}

	public void maxValidationAddress1() throws IOException {
		this.validationTab(Address1, getPropertyValue("256Characters"));

	}

	public String errorAddress1() {
		String text = this.getText(ErrorAddress1);
		return text;

	}

	public void clearAddress1() {
		this.clearField(Address1);

	}

	public void maxValidationAddress2() throws IOException {
		this.validationTab(Address2, getPropertyValue("256Characters"));

	}

	public String errorAddress2() {
		String text = this.getText(ErrorAddress2);
		return text;

	}

	public void clearAddress2() {
		this.clearField(Address2);

	}

	public void maxValidationState() throws IOException {
		this.validationTab(State, getPropertyValue("256Characters"));

	}

	public String errorState() {
		String text = this.getText(ErrorState);
		return text;

	}

	public void clearState() {
		this.clearField(State);

	}

	public void maxValidationCity() throws IOException {
		this.validationTab(City, getPropertyValue("256Characters"));

	}

	public String errorCity() {
		String text = this.getText(ErrorCity);
		return text;

	}

	public void clearCity() {
		this.clearField(City);

	}

	public void maxValidationZipcode() throws IOException {
		this.validationTab(Zipcode, "231231456456978");

	}

	public String errorZipcode() {
		String text = this.getText(ErrorZipcode);
		return text;

	}

	public void clearZipcode() {
		this.clearField(Zipcode);

	}

	public void minValidationZipcode() throws IOException {
		this.validationTab(Zipcode, "23");

	}

	public void specialCharacterZipcode() {
		this.validationTab(Zipcode, "@#$%^&");

	}

	public void previousButton() {
		this.mouseActionClick(Previous);

	}

	public void basicpage() throws AWTException, InterruptedException, IOException {
		String randomNumeric = RandomStringUtils.randomNumeric(8);
		String num = RandomStringUtils.randomNumeric(3);
//		this.mouseActionClick(Logo);
//		Thread.sleep(1000);
//		attachmentFile("istockphoto-825383494-612x612");
		this.clearField(FirstName);
		this.inputText(FirstName, fakeFirstName);
		this.inputText(LastName, fakeLastName);
		this.inputText(JobTittle, fakeTittle);
		this.scrollDown();
		this.clearField(Email);
		this.inputText(Email, fakeEmail + "@mailinator.com");
		this.inputText(PhoneNumber, fakePhoneNumber);
		this.mouseActionClick(Next);

	}

	public void locationpage() throws IOException {
		this.inputText(LocationName, excelRead("Team User", 1, 6));
		this.inputText(Address1, fakeAddress1);
		this.inputText(Address2, fakeAddress2);
		this.inputText(State, fakeState);
		this.inputText(City, fakeCity);
		this.inputText(Zipcode, fakeZipcode);
		this.mouseActionClick(SaveComplete);

	}

	public String createMessage() {
		String text = this.getText(SuccessfulMessage);
		return text;

	}

	public void getEmaiList() {
		String text = this.getText(ListEmail);
		this.mouseActionClick(CreateUser);
		this.inputText(FirstName, "Gav");
		this.mouseActionClick(Type);
		this.mouseActionClick(Admin);
		this.validationTab(Email, text);
	}

	public String alreadyEmail() throws IOException {
		String text = this.getText(ErrorEmail);
		this.mouseActionClick(UserCreateLabelHeading);
		this.mouseActionClick(Yes);
		this.assertName(HeadingFirstName, "First Name");
		return text;

	}

	public String createUserList() {
		String text = this.getText(ListFirstName);
		return text;

	}

	public void clearUserSearchField() {
		this.clearField(Search);

	}

	public String searchName() {
		String text = this.getText(ListFirstName);
		this.inputText(Search, text);
		this.mouseActionClick(SearchButton);
		return text;

	}

	public String listFirstName() {
		String text = this.getText(ListFirstName);
		return text;

	}

	public String searchPhoneNumber() {
		String text = this.getText(ListPhoneNumber);
		this.inputText(Search, text);
		this.mouseActionClick(SearchButton);
		return text;

	}

	public String listPhoneNumber() {
		String text = this.getText(ListPhoneNumber);
		return text;

	}

	public String listContractorPhoneNumber() {
		String text = this.getText(ListContractorPhoneNumber);
		return text;

	}

	public String searchEmail() {
		String text = this.getText(ListEmail);
		this.inputText(Search, text);
		this.mouseActionClick(SearchButton);
		return text;

	}

	public String listEmail() {
		String text = this.getText(ListEmail);
		return text;

	}

	public String listContractorEmail() {
		String text = this.getText(ListContractorEmail);
		return text;

	}

	public String searchInvalid() {
		this.inputText(Search, "dsjhfishiudsf");
		this.mouseActionClick(SearchButton);
		String text = this.getText(InValid);
		return text;

	}

	public void resetButton() {
		this.mouseActionClick(Reset);

	}

	public String editPage() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(Edit);
		String text = this.getText(UserCreateLabelHeading);
		return text;

	}

	public String editUserDetails() throws InterruptedException, AWTException {
		Thread.sleep(3500);
//		this.mouseActionClick(Logo);
//		Thread.sleep(1000);
//		attachmentFile("pic");
//		this.mouseAction(Next);
//		this.mouseAction(Previous);
		this.clearField(FirstName);
		this.inputText(FirstName, fakeFirstName);
		this.mouseActionClick(SaveComplete);
		String text = this.getText(UserUpdatedMessgae);
		return text;
	}

	public String deleteUserDetails() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(Delete);
		this.mouseActionClick(Yes);
		String text = this.getText(DeletedMessage);
		return text;

	}

	public String mandatoryValidationOrganizationField() throws InterruptedException {
		this.scrollDown();
		this.mouseActionClick(Organization);
		this.mouseActionClick(SaveComplete);
		this.scrollDown();
		String text = this.getText(CompanyNameError);
		Thread.sleep(2000);
		this.mouseActionClick(CompanyName);
		this.mouseActionClick(FirstCompanyName);
		return text;

	}

	public void basicContractorPage() throws InterruptedException, AWTException {
//		this.mouseActionClick(Logo);
//		Thread.sleep(1000);
//		attachmentFile("new_logo");
		this.clearField(FirstName);
		this.inputText(FirstName, fakeFirstName);
		this.inputText(LastName, fakeLastName);
		this.mouseActionClick(BussinessUnit);
		this.mouseActionClick(General);
		this.mouseActionClick(ServiceType);
		this.mouseActionClick(Repair);
		this.clearField(Email);
		String randomNumeric = RandomStringUtils.randomNumeric(4);
		this.inputText(Email, fakeEmail + "@mailinator.com");
		this.inputText(PhoneNumber, fakePhoneNumber);
		this.mouseActionClick(Next);

	}

	public String locationContractorPage() throws IOException {
		this.inputText(LocationName, excelRead("Team Details Screen", 1, 0));
		this.inputText(Address1, fakeAddress1);
		this.inputText(Address2, fakeAddress2);
		this.inputText(City, fakeCity);
		this.inputText(State, fakeState);
		this.inputText(Zipcode, fakeZipcode);
		this.mouseActionClick(SaveComplete);
		String text = this.getText(CreateContractorMessage);
		return text;
	}

	public void getEmaiContractorList() {
		String text = this.getText(ListContractorEmail);
		this.mouseActionClick(CreateContractor);
		this.inputText(FirstName, "Functional");
		this.validationTab(Email, text);
	}

	public String alreadyContractorEmail() {
		String text = this.getText(ErrorEmail);
		this.mouseActionClick(ContractorCreateLabelHeading);
		this.mouseActionClick(Yes);
		this.assertName(HeadingFirstName, "First Name");
		return text;

	}

	public void clearContractorSearch() {
		this.clearField(ContractorSearch);

	}

	public String searchContractorFirstName() {
		String text = this.getText(ListFirstName);
		this.inputText(ContractorSearch, text);
		this.mouseActionClick(ContractorSearchButton);
		return text;
	}

	public String searchContractorPhoneNumber() {
		String text = this.getText(ListContractorPhoneNumber);
		this.inputText(ContractorSearch, text);
		this.mouseActionClick(ContractorSearchButton);
		return text;
	}

	public String searchContractorEmail() {
		String text = this.getText(ListContractorEmail);
		this.inputText(ContractorSearch, text);
		this.mouseActionClick(ContractorSearchButton);
		return text;
	}

	public String invalidSearchButton() {
		this.inputText(ContractorSearch, "shdugsfgdssf");
		this.mouseActionClick(ContractorSearchButton);
		String text = this.getText(InValid);
		return text;

	}

	public String filterCompanySearch() {
		this.mouseAction(FilterByCompany);
		String text = this.getText(ListCompnayName);
		this.mouseActionClick(ListFilterCompany);
		this.mouseActionClick(FilterSearch);
		return text;

	}

	public String listCompanyName() {
		String text = this.getText(ListCompanyFirstName);
		return text;

	}

	public String editpageLanding() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(Edit);
		String text = this.getText(ContractorCreateLabelHeading);
		return text;

	}

	public String editContractorDetails() throws InterruptedException, AWTException, IOException {
		Thread.sleep(3500);
//		this.mouseActionClick(Logo);
//		Thread.sleep(1000);
//		attachmentFile("1622641377484");
//		this.mouseAction(Next);
//		this.mouseAction(Previous);
		this.clearField(FirstName);
		this.inputText(FirstName, fakeFirstName);
		this.clearField(Email);
		String randomNumeric = RandomStringUtils.randomNumeric(3);
		this.inputText(Email, fakeEmail + "@mailinator.com");
		this.mouseActionClick(Next);
		this.clearField(LocationName);
		this.inputText(LocationName, excelRead("Team Details Screen", 1, 0));
		this.mouseActionClick(SaveComplete);
		String text = this.getText(UpdateContractorMessage);
		return text;

	}

	public String deleteContractorDetails() {
		this.mouseActionClick(ThreeDots);
		this.mouseActionClick(Delete);
		this.mouseActionClick(Yes);
		String text = this.getText(DeleteContractorMessage);
		return text;

	}

	public String landSendInvite() {
		this.mouseActionClick(User);
		this.mouseActionClick(SendInvite);
		String text = this.getText(UserCreateLabelHeading);
		return text;

	}

}
