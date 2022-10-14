package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;

public class CreateContractorPage extends BaseClass {

	private WebDriver driver;
	private WebDriverWait wait;

	private By team = By.id("team-menu");

	private By company = By.xpath("//span[text()='Companies']");

	private By contractor = By.xpath("//div[@id='inner-id']//following::a[text()='Contractor']");

	private By createcontractorbutton = By.xpath("//button[@data-tabformid='team-company-contractor']");

	private By usermenu = By.xpath("//a[@data-automationid='user']");

//	private By contractorrmenu=By.xpath("//a[@data-automationid='vendorw']");

	private By contractortab = By.xpath("//a[@data-automationid='contractor']");

	private By contractorcreatebtn = By.xpath("//*[@id='root']/div/div/div[3]/div/div/button");

	private By contractorname = By.id("name");

	private By contractornameer = By.id("name_error");

	private By nextbtn = By.xpath("//button[@data-automationid='next']");

	private By contractoremail = By.xpath("//input[@data-automationid='email']");

	private By contractoremailerr = By.id("email_error");

	private By contractorcperson = By.id("contact_person_name");

	private By contractorcpersonerr = By.id("contact_person_name_error");

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

	private By saveform = By.xpath("//button[@data-formsubmit='company_contractor_create']");

	private By successmessage = By.xpath("//*[@data-automationid='sucmessage']");

	// Address

	private By addlocationbtn = By.xpath("//button[@data-automationid='anotherLocation']");

	private By lineone = By.xpath("//input[@data-automationid='noBuildingFlatName']");

	private By lineoneerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");

	private By linetwo = By.xpath("//input[@data-automationid='streetName']");

	private By linetwoerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");

	private By addressname = By.xpath("//button[@data-automationid='saveAndComplete']");

	private By addressnameerr = By.xpath("//button[@data-automationid='saveAndComplete']");

	private By city = By.xpath("//input[@data-automationid='cityVillage']");

	private By cityerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");

	private By zipcode = By.xpath("//input[@data-automationid='zipCode']");

	private By zipcoderr = By.xpath("//*[contains(text(),'The field must be minimum 6')]");

	private By zipcoderr1 = By.xpath("//*[contains(text(),'Not allowed more than 30 characters')]");

	private By country = By.xpath("//button[@data-automationid='saveAndComplete']");

	private By state = By.xpath("//button[@data-automationid='saveAndComplete']");

	private By clickmenu = By.xpath("//button[@data-automationid='activeContractor']");

	private By clickDelete = By.xpath("//*[@data-automationid='Delete Contractor']");

	private By listname = By.xpath("//h3[contains(text(),'erg')]");

	private By contractordetail = By.xpath("//button [@data-automationid='close']//following::h3");

	private By clickclosebutton = By.xpath("//button[@data-automationid='c']");

	By NameValidation = By.xpath("(//a[@data-goesto='user-profile-view'])[1]");

	By SearchBox = By.xpath("//input[@data-automationid='search']");

	By ClickSearchBox = By.xpath("//input[@data-automationid='search']");

	By ErrorValidation = By.xpath("//div[text()='No Result Found']");

	By Search = By.id("team-company-search-button");

	By ContractorCount = By.xpath("total-company-contractor-count");

	By Email = By.id("login");
	By Pass = By.id("password");
	By Click = By.xpath("//button[@type='submit']");
	By Team = By.xpath("//a[@id='team-menu']");
	// By contractor =
	// By.xpath("//div[@id='inner-id']//following::a[text()='Contractor']");
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

	By AddContractor = By.xpath("//button[@data-formsactions='create']");

	public String validation() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated((NameValidation))).getText();
		return text;

	}

	public void nameValidationList(String name) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated((SearchBox)));
		until.sendKeys(name);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Search)).click();
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated((NameValidation))).getText();
		Assert.assertEquals(text, "Dhamu");
		until.clear();
	}

	By name1 = By.xpath("//td[text()='Company']");

	public void invalidValidation(String name) {
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOfElementLocated((name1))).getText();
		Assert.assertEquals(text2, "Company");
		wait.until(ExpectedConditions.visibilityOfElementLocated((SearchBox))).sendKeys(name, Keys.ENTER);

	}

	public String invlaidValidate() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated((ErrorValidation))).getText();
		return text;

	}

	public void clickCreateCompany() {
		wait.until(ExpectedConditions.elementToBeClickable((createcontractorbutton)));
		driver.findElement(createcontractorbutton).click();
	}

	public String getButtonText() {

		wait.until(ExpectedConditions.visibilityOfElementLocated((saveform)));
		return driver.findElement(saveform).getText();

	}

	public void clickCompany() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((company)));
		driver.findElement(company).click();
	}

	public void clickContractor() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractor)));
		driver.findElement(contractor).click();
	}

	public void clickContractors() {
		this.mouseActionClick(contractor);
		this.mouseActionClick(createcontractorbutton);

	}

	public void createContractorButtonj() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((createcontractorbutton)));
		driver.findElement(createcontractorbutton).click();
	}

	public void clearPhone() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorphone)));
		driver.findElement(contractorphone).clear();

	}

	public void clickTeam() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((team)));
		driver.findElement(team).click();
	}

	public String getCreatedContractorNameDetail() {

		wait.until(ExpectedConditions.visibilityOfElementLocated((contractordetail)));
		return driver.findElement(contractordetail).getText();
	}

	public void clickSuccessClose() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickclosebutton)));
		driver.findElement(clickclosebutton).click();
	}

	public void clickContractorName() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((listname)));
		driver.findElement(listname).click();
	}

	public String getCreatedContractorName() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((listname)));
		return driver.findElement(listname).getText();
	}

	public void clickActionMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickmenu)));
		driver.findElement(clickmenu).click();
	}

	public void clickDeleteMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickDelete)));
		driver.findElement(clickDelete).click();
	}

	public void clickLocationOne() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((locationone)));
		driver.findElement(locationone).click();
	}

	public void clickAddLocation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((addlocationbtn)));
		driver.findElement(addlocationbtn).click();
	}

	public void clickSaveandComplete() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((saveform)));
		driver.findElement(saveform).click();
	}

	public void clearFax() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorfax)));
		driver.findElement(contractorfax).clear();
	}

	public void uploadImage(String teamname) {
		wait.until(ExpectedConditions.presenceOfElementLocated(contractorimageupload));
		driver.findElement(contractorimageupload).sendKeys(teamname);
	}

	public void dashBoardUserMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((usermenu)));
		driver.findElement(usermenu).click();
	}

	public void clickNextButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((nextbtn)));
		driver.findElement(nextbtn).click();
	}

	public void clickYesButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((warningpopup)));
		driver.findElement(warningpopup).click();
	}

	public void clickCloseButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((closepopup)));
		driver.findElement(closepopup).click();
	}

	public String dashBoardUserMenuText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((usermenu)));
		return driver.findElement(usermenu).getText();
	}

	public String dashBoardVendorMenuText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractortab)));
		return driver.findElement(contractortab).getText();
	}

	public void clickVendorTab() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractortab)));
		driver.findElement(contractortab).click();
	}

	public void contractorCreateButton() {
		wait.until(ExpectedConditions.presenceOfElementLocated((contractorcreatebtn)));
		driver.findElement(contractorcreatebtn).click();
	}

	public void contractorName(String name) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorname)));
		driver.findElement(contractorname).sendKeys(name);
	}

	public void clearContractorName() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorname)));
		driver.findElement(contractorname).clear();
	}

	public void contractorEmail(String Email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractoremail)));
		driver.findElement(contractoremail).sendKeys(Email);

	}

	public void clearContractorEmail() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractoremail)));
		driver.findElement(contractoremail).clear();

	}

	public void clearContractorPerson() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorcperson)));
		driver.findElement(contractorcperson).clear();

	}

	public void contractorContactPerson(String ContactPerson) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorcperson)));
		driver.findElement(contractorcperson).sendKeys(ContactPerson);
	}

	public void contractorPhone(String ContactPhone) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorphone)));
		driver.findElement(contractorphone).sendKeys(ContactPhone);
	}

	public void contractorFax(String Fax) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorfax)));
		driver.findElement(contractorfax).sendKeys(Fax, Keys.TAB);
	}

	// Testing

	public void contractorWebsite(String Website) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorsite)));
		driver.findElement(contractorsite).sendKeys(Website);
	}

	public void clearContactPerson() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorcperson)));
		driver.findElement(contractorcperson).clear();

	}

	public String contractorNameError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractornameer)));
		return driver.findElement(contractornameer).getText();
	}

	public String contractorEmailError() {
		wait.until(ExpectedConditions.presenceOfElementLocated((contractoremailerr)));
		return driver.findElement(contractoremailerr).getText();
	}

	public String contractorFaxError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorfaxerror)));
		return driver.findElement(contractorfaxerror).getText();
	}

	public String contractorContactNameError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorcpersonerr)));
		return driver.findElement(contractorcpersonerr).getText();
	}

	public String contractorPhoneError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorphoneerr)));
		return driver.findElement(contractorphoneerr).getText();
	}

	public String contractorWebsiteError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractorsiteerr)));
		return driver.findElement(contractorsiteerr).getText();
	}

	public String contractorSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((successmessage)));
		return driver.findElement(successmessage).getText();
	}

	public String vendorImageError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((imagerror)));
		return driver.findElement(imagerror).getText();
	}

	// Address Form

	public void selectCountry() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(country));
		driver.findElement(country).click();
		Select select = new Select(driver.findElement(country));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		select.selectByVisibleText("india");
	}

	public void selectState() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(state));
		driver.findElement(state).click();
		Select select = new Select(driver.findElement(state));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		select.selectByVisibleText("tamil nadu");
	}

	public void contractorLineOne(String services) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((lineone)));
		driver.findElement(lineone).sendKeys(services);
	}

	public String contractorLineOneError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((lineoneerr)));
		return driver.findElement(lineoneerr).getText();
	}

	public void contractorLineTwo(String services) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((linetwo)));
		driver.findElement(linetwo).sendKeys(services);
	}

	public String contractorLineTwoError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((linetwoerr)));
		return driver.findElement(linetwoerr).getText();
	}

	public void contractorCity(String services) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((city)));
		driver.findElement(city).sendKeys(services);
	}

	public String contractorCityError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((cityerr)));
		return driver.findElement(cityerr).getText();
	}

	public void contractorAddressContactPerson(String services) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((addressname)));
		driver.findElement(addressname).sendKeys(services);
	}

	public String contractorContctPersonError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((addressnameerr)));
		return driver.findElement(addressnameerr).getText();
	}

	public void contractorZipcode(String services) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((zipcode)));
		driver.findElement(zipcode).sendKeys(services);
	}

	public String contractorZipcodeError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((zipcoderr)));
		return driver.findElement(zipcoderr).getText();
	}

	public String contractorZipcodeError1() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((zipcoderr1)));
		return driver.findElement(zipcoderr1).getText();
	}

	public void listValidation() {
		// this.clickTeam();
//		this.clickContractor();
		this.validation();

	}

	public void nameValidation(String name) {
		this.nameValidationList(name);

	}

	public void invalidData(String name) {
		this.invalidValidation(name);

	}

	private void ContractorField(String CompanyName, String textEmail, String person, String phoneNo, String fax,
			String webSite, String location, String tEmail, String contact, String Number, String Flat, String Street,
			String State, String City, String Zip) throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		// driver.navigate().refresh();
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
//		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();
//		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(CreateContractor)).getText();
//		Assert.assertEquals(text, "Contractor have been created successfully");

	}

	public void CreateContractor(String CompanyName, String textEmail, String person, String phoneNo, String fax,
			String webSite, String location, String tEmail, String contact, String Number, String Flat, String Street,
			String State, String City, String Zip) throws InterruptedException {
		this.ContractorField(CompanyName, textEmail, person, phoneNo, fax, webSite, location, tEmail, contact, Number,
				Flat, Street, State, City, Zip);

	}

	private void ErrorMessage() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Pre)).click();
	}

	private void changeEmail() {
		wait = new WebDriverWait(driver, 10);
		String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(4);
		wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(txtEmail)).sendKeys(randomAlphanumeric);

	}

	private void saveComplete() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext1)).click();

	}

	public void recheck() {
		this.ErrorMessage();
		this.changeEmail();
		this.saveComplete();
	}

	public CreateContractorPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 20);
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
	By SaveNext1 = By.xpath("//*[@id=\"team-company-contractor\"]/div/div/div[2]/button");
	By Next = By.xpath("//span[text()='Next']");
	By Pre = By.xpath("//span[text()='Previous']");

	private void clickNext() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Next)).click();
	}

	private void LocationField(String location, String tEmail, String contact, String Number, String Flat,
			String Street, String State, String City, String Zip) throws InterruptedException {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickNext)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(LocationName)).sendKeys(location);
		wait.until(ExpectedConditions.visibilityOfElementLocated(comEmail)).sendKeys(tEmail);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ContactPerson)).sendKeys(contact);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PhoneNo)).sendKeys(Number);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FlatName)).sendKeys(Flat);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StreetName)).sendKeys(Street);
		wait.until(ExpectedConditions.visibilityOfElementLocated(StateName)).sendKeys(State);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CityName)).sendKeys(City);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ZipCode)).sendKeys(Zip);
		// Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext1)).click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();

	}

	public String asssertCreate() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(CreateContractor)).getText();
		return text;

	}

	public void Location(String location, String tEmail, String contact, String Number, String Flat, String Street,
			String State, String City, String Zip) throws InterruptedException {
		this.LocationField(location, tEmail, contact, Number, Flat, Street, State, City, Zip);

	}

	public By errorLocationName = By.id("addresses__name__0_error");
	public By errorEmail = By.id("addresses__email__0_error");
	public By errorContactPerson = By.id("addresses__contact_person__0_error");
	public By errorPhoneNo = By.id("addresses__phone_number__0_error");
	public By errorNo = By.id("addresses__line_1__0_error");
	public By errorStreetName = By.id("addresses__line_2__0_error");
	public By errorStateName = By.id("addresses__state__0_error");
	public By errorCity = By.id("addresses__city__0_error");
	public By errorZipCode = By.id("addresses__zipcode__0_error");
	public By Previous = By.xpath("//*[text()='Previous']");

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
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
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

	String r = RandomStringUtils.randomAlphabetic(600);

	public void maxValidationLocationName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickNext)).click();
		this.validationTab(LocationName, r);

	}

	public String locationErrorMessage() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocationName)).getText();
		return text;

	}

	public void clearLocationName() {
		this.clearField(LocationName);

	}

	public void validateEmail() {
		this.validationTab(comEmail, "dnfjs");

	}

	public String emailErrorMessage() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorEmail)).getText();
		return text;

	}

	public void clearEmail() {
		this.clearField(comEmail);

	}

	public void maxValidationEmail() {
		this.validationTab(comEmail, r);

	}

	public void minValidationPhoneNumber() {
		this.validationTab(PhoneNo, "2134");

	}

	public String phoneNumberErrorMessage() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorPhoneNo)).getText();
		return text;

	}

	public void clearPhoneNumber() {
		this.clearField(PhoneNo);

	}

	public void maxValidationPhoneNumberField() {
		this.validationTab(PhoneNo, "978456213456454498797");

	}

	public void maxValidationAddress1() {
		this.validationTab(FlatName, r);

	}

	public String errorAddress1Message() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorNo)).getText();
		return text;

	}

	public void clearAddress1() {
		this.clearField(FlatName);

	}

	public void maxValidationAddress2() {
		this.validationTab(StreetName, r);

	}

	public String errorAddress2Message() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorStreetName)).getText();
		return text;

	}

	public void clearAddress2() {
		this.clearField(StreetName);

	}

	public void maxValidationStateName() {
		this.validationTab(StateName, r);

	}

	public String errorStateNameMessage() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorStateName)).getText();
		return text;

	}

	public void clearStateName() {
		this.clearField(StateName);

	}

	public void maxValidationCityName() {
		this.validationTab(CityName, r);

	}

	public String errorCityNameMessage() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorCity)).getText();
		return text;

	}

	public void clearCityName() {
		this.clearField(CityName);

	}

	public void specialCharacterValidationZipCode() {
		this.validationTab(ZipCode, "!@#$%^&");

	}

	public String errorZipCodeMessage() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorZipCode)).getText();
		return text;

	}

	public void clearZipCode() {
		this.clearField(ZipCode);

	}

	public void maxValidationContactPerson() {
		this.validationTab(ContactPerson, r);

	}

	public String errorContactPersonMessage() {
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(errorContactPerson)).getText();
		return text;

	}

	public void clearContactPersons() {
		this.clearField(ContactPerson);

	}

	public void minValidationZipCode() {
		this.validationTab(ZipCode, "12");

	}

	public void maxValidationZipCode() {
		this.validationTab(ZipCode, "12231212322121321123");

	}

	public void previousButton() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Previous)).click();

	}

	private void maxLocationField() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(Previous)).click();

	}

	public void conMaxValidation() {
		this.maxLocationField();
		this.clearContractorName();

	}

	By Logo = By.xpath("//label[@for='imageUpload']");
	By LogoError = By.id("company_logo_error");
	By FileLogo = By.xpath("//div[text()='Only png,jpeg,jpg Formats Allowed']");
	By EditHeading = By.xpath("//a[@data-exitpopup='team_companies_company']");
	By ListCompanyName = By.xpath("(//a[@data-detailheadermenu=\"tmusr-user-profile\"])[1]");
	By ListName = By.xpath("(//a[@class='ellipsis-100 '])[1]");
	By ListEmail = By.xpath("(//a[@class='ellipsis-150'])[1]");
	By ListPhoneNumber = By.xpath("(//td[@class='p-2 pt-1 pb-1 '])[1]");
	By CreateCont = By.xpath("//a[@data-exitpopup='team_companies_contractor']");

	public void maxSizeLogoValidation() throws AWTException, InterruptedException {
		this.assertName(CreateCont, "Team / Create Contractor");
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("dsc00531");

	}

	public String errorLogo() {
		String text2 = this.getText(LogoError);
		return text2;

	}

	public String fileLogoError() {
		String text2 = this.getText(FileLogo);
		return text2;
	}

	public void formatLogoValidation() throws InterruptedException, AWTException {
		this.mouseActionClick(Logo);
		Thread.sleep(1500);
		attachmentFile("sample-file.pdf");

	}

	public void logo() throws InterruptedException, AWTException {
		this.mouseActionClick(Logo);
		Thread.sleep(1000);
		attachmentFile("images");

	}

	public String searchListCompanyNameValidation() {
		String text = this.getText(ListCompanyName);
		this.inputText(SearchBox, text);
		this.mouseActionClick(Search);
		return text;
	}

	public void clearSearchField() {
		this.clearField(SearchBox);

	}

	public String listCompanyName() {
		String text = this.getText(ListCompanyName);
		return text;

	}

	public String searchListNameValidation() {
		String text = this.getText(ListName);
		this.inputText(SearchBox, text);
		this.mouseActionClick(Search);
		return text;

	}

	public String listName() {
		String text = this.getText(ListName);
		return text;

	}

	public String searchEmailListValidation() {
		String text = this.getText(ListEmail);
		this.inputText(SearchBox, text);
		this.mouseActionClick(Search);
		return text;

	}

	public String listEmail() {
		String text = this.getText(ListEmail);
		return text;

	}

	public String searchPhoneNumberListValidation() {
		String text = this.getText(ListPhoneNumber);
		this.inputText(SearchBox, text);
		this.mouseActionClick(Search);
		return text;

	}

	public String listPhoneNumber() {
		String text = this.getText(ListPhoneNumber);
		return text;

	}

	By resert = By.xpath("//a[text()=' Reset Search']");

	public void reserOption() {
		this.mouseActionClick(resert);
	}

}
