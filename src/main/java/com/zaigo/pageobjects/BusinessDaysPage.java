package com.zaigo.pageobjects;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import javax.xml.xpath.XPath;

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

public class BusinessDaysPage extends BaseClass {
	static String responseMessage;
	WebDriver driver;
	WebDriverWait wait;

	public BusinessDaysPage(WebDriver driver) {
		this.driver = driver;
	}

	Faker faker = new Faker(new Locale("en-IND"));
	String BusinessName = faker.app().name();
	String fakeFirstName = faker.name().firstName();
	String fakeCompanyName = faker.company().name();
	String characters4 = RandomStringUtils.randomAlphabetic(4);
	String characters2048 = RandomStringUtils.randomAlphabetic(5000);

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	private void inputText(WebElement element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
	}

	private void clearField(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
	}

	private void clearField(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
	}

	private void elementtobeClickable(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 100);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void assertName(By element, String text) {
		wait = new WebDriverWait(driver, 50);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		Assert.assertEquals(until, text);
	}

	public void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	private void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	private void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	private void dropDownByIndex(WebElement element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	private String getText(By element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;
	}

	private String getText(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return until;
	}

	private void mouseAction(By element) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();
	}

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	private void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}

	public void valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public void valuePresent(WebElement element, String value) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
	}

	public void invisible(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	public Boolean conditionChecking(By element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 50);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking1(By element) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 2);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}
	
	public String getMessage()
	{
		String businessName = this.getText(Message);
		return businessName;
	}
	
	By Job_ServiceType = By.xpath("//input[@placeholder='Choose Service Type'][1]");

	By Yes = By.xpath("//*[text()='Yes']");

	By Message = By.xpath("//*[@class='js-snackbar__message']");

	By Cancel = By.xpath("//*[@class='js-snackbar__close bold']");

	By FormLabel = By.xpath("//*[@id='category-create']/div/div[1]/div/div[1]");

	By settings_menu = By.xpath("//div[@data-n-linkto='settings']//i");

	By settings_business = By.xpath("//div[text()=' Business Settings']");

	By settings_business_settings_label = By.xpath("//div[@id='breadcrumb_placement']//ol//li[3]");

	By list_bussiness_unit_name = By.xpath("//*[@id='bussiness-unit-ele']/div[1]/div/div/div[1]");

	By business_unit_status = By.xpath("//*[@id='bussiness-unit-ele']/div[1]/div/div/div[2]");

	By business_unit_label = By.xpath("//*[text()=' Business Unit']");

	public static By create_button = By.id("bussines_unit_click");

	By bussiness_name = By.xpath("//input[@id='name']");

	By bussiness_name_error = By.xpath("//div[@id='name_error']");

	By bussiness_status = By.id("status");

	By bussiness_status_save_btn = By.xpath("//button[@id='category_submit_btn']");

	By bussiness_status_close = By.xpath("//span[@id='nav-close-popup']");

	By edit_btn = By.xpath("//*[@id='bussiness-unit-ele']/div/div/div/div[3]/div[1]/div/i");

	By delete_btn = By.xpath("//*[@id='bussiness-unit-ele']/div/div/div/div[3]/div[2]/div/i");

	// Lead Source

	By lead_source_label = By.xpath("//*[text()=' Lead Source']");

	public static By lead_source_create_btn = By.id("lead_Source_click");

	By leadsource_name = By.xpath("//input[@id='name']");

	By list_leadsource_status = By.xpath("//*[@id='lead-source-ele']/div[1]/div/div/div[2]");

	By list_lead_source_name = By.xpath("//*[@id='lead-source-ele']/div[1]/div/div/div[1]");

	By leadsource_error = By.xpath("//div[@id='name_error']");

	By leadsource_status = By.id("status");

	By leadsource_save_btn = By.xpath("//button[@id='category_submit_btn']");

	By leadsource_closebutton = By.xpath("//span[@id='nav-close-popup']");

	By leadsource_edit_btn = By.xpath("//*[@id='lead-source-ele']/div[1]/div/div/div[3]/div[1]/div/i");

	By leadsource_delete_btn = By.xpath("//*[@id='lead-source-ele']/div[1]/div/div/div[3]/div[2]/div/i");

	// Service Type

	By service_type_label = By.xpath("//*[text()=' Service Type']");

	public static By service_create_btn = By.id("service_type_click");

	By service_name = By.xpath("//input[@id='name']");

	By service_list_status = By.xpath("//*[@id='service-type-business-settings']/div/div/div/div[2]");

	By list_service_type_name = By.xpath("//*[@id='service-type-business-settings']/div/div/div/div[1]");

	By service_error = By.xpath("//div[@id='name_error']");

	By service_status = By.id("status");

	By service_save_btn = By.xpath("//button[@id='category_submit_btn']");

	By service_edit_btn = By.xpath("//*[@id='service-type-business-settings']/div/div/div/div[3]/div[1]/div/i");

	By service_delete_btn = By.xpath("//*[@id='service-type-business-settings']/div/div/div/div[3]/div[2]/div/i");

	// Checking Field

	By Customer = By.id("customers");

	By Contact = By.xpath("//*[@id='customers']//li[1]");

	By Organization = By.xpath("//*[@id='customers']//li[2]");
	

	@FindAll({ @FindBy(xpath = "//*[@data-automationid='contact-creation']"), @FindBy(id = "scheduledrop"),
			@FindBy(xpath = "//*[@id='job-show-details-timeline']/div[1]/div[3]/button[1]"),
			@FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/div/div[2]/div[1]/div/div[3]/div[4]/button"),
			@FindBy(xpath ="//*[@gloss=' Create Job']"),
			@FindBy(xpath="//*[@gloss=' Create Contractor']"),
			@FindBy(xpath="//*[@id='scheduledrop']")})
	WebElement CreateButton;

	By SaveButton = By.id("customerdrop");

	@FindAll({ @FindBy(id = "first_name"), @FindBy(id = "company_name") })
	WebElement CustomerNameField;

	@FindAll({ @FindBy(xpath = "//*[text()='Contact Name']"), @FindBy(xpath = "//*[text()='Customer Name']"),
			@FindBy(xpath = "//*[text()='Organization Name']"), @FindBy(xpath = "//*[text()='No Result Found']"),
			@FindBy(xpath = "//*[text()='Customer']"), @FindBy(xpath = "//*[text()=' First Name ']") })
	WebElement CheckPage;

	By Next = By.xpath("//*[text()='Next']");

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-list_aserpttbl']//tr[2]//td[2]//span"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-list_aserpttbl']//tr[2]//td[2]//span") })
	WebElement ListName;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-organization-list_aserpttbl']//tr[2]//td[9]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-contact-list_aserpttbl']//tr[2]//td[9]") })
	WebElement ThreeDots;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-list_aserpttbl']//tr[2]//td[9]//li[1]"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-list_aserpttbl']//tr[2]//td[9]//li[1]") })
	WebElement Edit;

//	By  = By.xpath("//*[@id='customer_contact_create_edit']/div[1]/div[2]/div[4]/div[2]/input[1]");

	@FindAll({ @FindBy(xpath = "//*[@id='job_create_edit']/div/div[1]/div[4]/div[2]/input[1]"),
			@FindBy(xpath = "//*[@id='user_contractor_create_edit']/div[1]/div[1]/div/div[3]/div[4]/div[2]/input[1]"),
			@FindBy(xpath="//*[@id='job_create_edit']/div/div[1]/div[6]/div[2]/input[1]"),
			@FindBy(xpath="//*[@id='user_contractor_create_edit']/div[1]/div[1]/div/div[4]/div[4]/div[2]/input[1]"),
			@FindBy(xpath="//*[@id='customer_contact_create_edit']/div[1]/div[2]/div[3]/div[2]/input[1]")})
	WebElement BusinessDaysFields;
	
	
	

	By Job = By.id("jobs");

	By RadioButtonContact = By.xpath("//*[@id='contact-organization-job']/div[1]/input");

	By RadioButtonOrganization = By.xpath("//*[@id='contact-organization-job']/div[2]/input");

	By Tittle = By.xpath("//*[@id='team-company-details-company-name']/div[1]");

	By Team = By.id("user-active");

	By User = By.xpath("//*[@id='user-active']//li[2]");

	By Contractor = By.xpath("//*[@id='user-contrator-ele']");

	By Spinner = By.xpath("//*[@id='spinnerDiv']//div//div//div");

	public String modulePage() {
		this.mouseActionClick(settings_menu);
		this.mouseActionClick(settings_business);
		String text = this.getText(settings_business_settings_label);
		return text;
	}

	public String businessDaysLabel(String value) {
		if (value.equals("BusinessUnit")) {
			return this.getText(business_unit_label);
		} else if (value.equals("LeadSource")) {
			return this.getText(lead_source_label);
		} else if (value.equals("ServiceType")) {
			return this.getText(service_type_label);
		}
		return value;
	}

	public String labelValidation(String value) throws InterruptedException {
		if (value.equals("BusinessUnitCreate")) {
			//this.visibility(list_bussiness_unit_name);
			this.mouseActionClick(create_button);
		} else if (value.equals("BusinessUnitEdit")) {
			String text = this.getText(list_bussiness_unit_name);
			this.mouseActionClick(edit_btn);
			this.valuePresent(bussiness_name, text);
			Thread.sleep(3000);
		} else if (value.equals("LeadSourceCreate")) {
			this.visibility(list_lead_source_name);
			this.mouseActionClick(lead_source_create_btn);
		} else if (value.equals("LeadSourceEdit")) {
			String text = this.getText(list_lead_source_name);
			this.mouseActionClick(leadsource_edit_btn);
			this.valuePresent(leadsource_name, text);
			Thread.sleep(3000);
		} else if (value.equals("ServiceTypeCreate")) {
			this.visibility(list_service_type_name);
			this.mouseActionClick(service_create_btn);
		} else if (value.equals("ServiceTypeEdit")) {
			String text = this.getText(list_service_type_name);
			this.mouseActionClick(service_edit_btn);
			this.valuePresent(service_name, text);
			Thread.sleep(3000);
		}
		return this.getText(FormLabel);
	}

	public String businessUnitField(String value) throws InterruptedException {
		if (value.equals("MaxValidation")) {
			this.validationTab(bussiness_name, characters2048);
		} else if (value.equals("Mandatory")) {
			this.mouseActionClick(bussiness_status_save_btn);
			if (this.conditionChecking1(bussiness_name_error)) {
			} else {
				do {
					this.mouseActionClick(bussiness_status_save_btn);
				} while (!this.conditionChecking1(bussiness_name_error));
			}
		} else if (value.equals("UniqueValidation")) {
			String text = this.getText(list_bussiness_unit_name);
			this.visibility(create_button);
			this.mouseActionClick(create_button);
			this.validationTab(bussiness_name, text);
		} else if (value.equals("ValidData")) {
			Thread.sleep(2000);
			if (!(this.getTextAttribute(bussiness_name).length() < 1)) {
				do {
					this.clearField(bussiness_name);
					Thread.sleep(2000);
				} while (!(this.getTextAttribute(bussiness_name).length() < 1));
			}
			this.inputText(bussiness_name, BusinessName);
//			this.inputText(bussiness_name, "Fixflex");
			String businessName = this.getTextAttribute(bussiness_name);
			return businessName;
		}
		return value;
	}

	public String leadSourceField(String value) throws InterruptedException {
		if (value.equals("MaxValidation")) {
			this.validationTab(leadsource_name, characters2048);
		} else if (value.equals("Mandatory")) {
			this.mouseActionClick(leadsource_save_btn);
			if (this.conditionChecking1(leadsource_error)) {
			} else {
				do {
					this.mouseActionClick(leadsource_save_btn);
				} while (!this.conditionChecking1(leadsource_error));
			}
		} else if (value.equals("UniqueValidation")) {
			String text = this.getText(list_lead_source_name);
			this.visibility(lead_source_create_btn);
			this.mouseActionClick(lead_source_create_btn);
			this.validationTab(leadsource_name, text);
		} else if (value.equals("ValidData")) {
			Thread.sleep(2000);
			if (!(this.getTextAttribute(leadsource_name).length() < 1)) {
				do {
					this.clearField(leadsource_name);
					Thread.sleep(2000);
				} while (!(this.getTextAttribute(leadsource_name).length() < 1));
			}
			this.inputText(leadsource_name, BusinessName);
			String businessName = this.getTextAttribute(leadsource_name);
			return businessName;
		}
		return value;

	}

	public String serviceTypeField(String value) throws InterruptedException {
		if (value.equals("MaxValidation")) {
			this.validationTab(service_name, characters2048);
		} else if (value.equals("Mandatory")) {
			this.mouseActionClick(service_save_btn);
			if (this.conditionChecking1(service_error)) {
			} else {
				do {
					this.mouseActionClick(service_save_btn);
				} while (!this.conditionChecking1(service_error));
			}
		} else if (value.equals("UniqueValidation")) {
			String text = this.getText(list_service_type_name);
			this.visibility(service_create_btn);
			this.mouseActionClick(service_create_btn);
			this.validationTab(service_name, text);
		} else if (value.equals("ValidData")) {
			Thread.sleep(2000);
			if (!(this.getTextAttribute(service_name).length() < 1)) {
				do {
					this.clearField(service_name);
					Thread.sleep(2000);
				} while (!(this.getTextAttribute(service_name).length() < 1));
			}
			this.inputText(service_name, BusinessName);
//			this.inputText(service_name, "Kanlam");
			String businessName = this.getTextAttribute(service_name);
			return businessName;
		}
		return value;

	}

	public String errorField(String value) {
		if (value.equals("BusinessUnit")) {
			return this.getText(bussiness_name_error);
		} else if (value.equals("LeadSource")) {
			return this.getText(leadsource_error);
		} else if (value.equals("ServiceType")) {
			return this.getText(service_error);
		}
		return value;
	}

	public void clearField(String value) {
//		for (int i = 0; i < 20; i++) {
		if (value.equals("BussinessUnit")) {
			this.clearField(bussiness_name);
		} else if (value.equals("LeadSource")) {
			this.clearField(leadsource_name);
		} else if (value.equals("ServiceType")) {
			this.clearField(service_name);
//			}
		}
	}

	public String buttonValidation(String value) {
		if (value.equals("ButtonPresent")) {
			return this.getText(bussiness_status_save_btn);
		} else if (value.equals("ButtonClick")) {
			this.mouseActionClick(bussiness_status_save_btn);
		} else if (value.equals("CancelButton")) {
			this.mouseActionClick(leadsource_closebutton);
		}
		return value;
	}

	public String message(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message")) {
			if (this.conditionChecking(Message)) {
				responseMessage = this.getText(Message);
				this.invisible(Message);
			} else {
				do {
					Thread.sleep(10000);
					this.mouseActionClick(bussiness_status_save_btn);
					if (this.conditionChecking(Message)) {
						responseMessage = this.getText(Message);
						if (responseMessage.equals(getPropertyValue("BusinessUnitCreatedMessage"))
								|| responseMessage.equals(getPropertyValue("BusinessUnitUpdatedMessage"))
								|| responseMessage.equals(getPropertyValue("LeadSourceCreatedMessage"))
								|| responseMessage.equals(getPropertyValue("LeadSourceUpdatedMessage"))
								|| responseMessage.equals(getPropertyValue("ServiceTypeCreatedMessage"))
								|| responseMessage.equals(getPropertyValue("ServiceTypeUpdatedMessage"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateFunction")) {
			do {

				if (responseMessage.equals(getPropertyValue("LeadSourceAlreadyExists"))
						|| responseMessage.equals(getPropertyValue("BusinessUnitAlreadyExists"))
						|| responseMessage.equals(getPropertyValue("ServiceTypeAlreadyExists"))) {
					this.clearField(bussiness_name);
					Faker faker = new Faker(new Locale("en-IND"));
					String BusinessName = faker.app().name();
					this.inputText(bussiness_name, BusinessName);
					this.mouseActionClick(service_save_btn);
				}
				if (this.conditionChecking(Message)) {
					responseMessage = this.getText(Message);
					this.invisible(Message);
					if (responseMessage.equals(getPropertyValue("BusinessUnitCreatedMessage"))
							|| responseMessage.equals(getPropertyValue("BusinessUnitUpdatedMessage"))
							|| responseMessage.equals(getPropertyValue("LeadSourceCreatedMessage"))
							|| responseMessage.equals(getPropertyValue("LeadSourceUpdatedMessage"))
							|| responseMessage.equals(getPropertyValue("ServiceTypeCreatedMessage"))
							|| responseMessage.equals(getPropertyValue("ServiceTypeUpdatedMessage"))) {
						conditionCheck = false;
					}
				} else {
					do {
						Thread.sleep(10000);
						this.mouseActionClick(bussiness_status_save_btn);
						if (this.conditionChecking(Message)) {
							responseMessage = this.getText(Message);
							if (responseMessage.equals(getPropertyValue("BusinessUnitCreatedMessage"))
									|| responseMessage.equals(getPropertyValue("BusinessUnitUpdatedMessage"))
									|| responseMessage.equals(getPropertyValue("LeadSourceCreatedMessage"))
									|| responseMessage.equals(getPropertyValue("LeadSourceUpdatedMessage"))
									|| responseMessage.equals(getPropertyValue("ServiceTypeCreatedMessage"))
									|| responseMessage.equals(getPropertyValue("ServiceTypeUpdatedMessage"))) {
								conditionCheck = false;
							} else if (responseMessage.equals(getPropertyValue("LeadSourceAlreadyExists"))
									|| responseMessage.equals(getPropertyValue("BusinessUnitAlreadyExists"))
									|| responseMessage.equals(getPropertyValue("ServiceTypeAlreadyExists"))) {
								this.message("AlternateFunction");
								conditionCheck = false;
							}
						}
					} while (conditionCheck);
				}
			} while (conditionCheck);
		}
		return responseMessage;
	}

	public String listValdidation(String value) {
		if (value.equals("BusinessUnit")) {
			return this.getText(list_bussiness_unit_name);
		} else if (value.equals("BusinessUnitStatus")) {
			return this.getText(business_unit_status);
		} else if (value.equals("BusinessUnitDelete")) {
			this.mouseActionClick(delete_btn);
			this.mouseActionClick(Yes);
		} else if (value.equals("LeadSource")) {
			return this.getText(list_lead_source_name);
		} else if (value.equals("LeadSourceStatus")) {
			return this.getText(list_leadsource_status);
		} else if (value.equals("LeadSourceDelete")) {
			this.mouseActionClick(leadsource_delete_btn);
			this.mouseActionClick(Yes);
		} else if (value.equals("ServiceType")) {
			return this.getText(list_service_type_name);
		} else if (value.equals("ServiceTypeStatus")) {
			return this.getText(service_list_status);
		} else if (value.equals("ServiceTypeDelete")) {
			this.mouseActionClick(service_delete_btn);
			this.mouseActionClick(Yes);
		}
		return value;
	}

	static String textAttribute;

	public String createBusinessDays(String value) throws IOException, InterruptedException {
		if (value.equals("CreatedLeadSource")) {
			this.mouseActionClick(lead_source_create_btn);
			this.inputText(leadsource_name, BusinessName);
			textAttribute = this.getTextAttribute(leadsource_name);
			this.mouseActionClick(leadsource_save_btn);
			this.message("Message");
			this.alternateMethod();
			return textAttribute;
		} else if (value.equals("EditLeadSource")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(settings_business);
			textAttribute = this.getText(list_lead_source_name);
			this.mouseActionClick(leadsource_edit_btn);
			this.valuePresent(leadsource_name, textAttribute);
			this.validationTab(leadsource_name, characters2048);
			this.clearField(leadsource_name);
			this.validationTab(leadsource_name, textAttribute);
			this.dropDownByIndex(leadsource_status, 1);
			this.mouseActionClick(leadsource_save_btn);
			this.message("Message");
			this.alternateMethod();
			return textAttribute;
		} else if (value.equals("DeleteLeadSource")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(settings_business);
			textAttribute = this.getText(list_lead_source_name);
			this.mouseActionClick(leadsource_delete_btn);
			this.mouseActionClick(Yes);
			this.message("Message");
			return textAttribute;
		} else if (value.equals("CreatedBusinessUnit")) {
			this.mouseActionClick(create_button);
			this.inputText(bussiness_name, BusinessName);
			textAttribute = this.getTextAttribute(bussiness_name);
			this.mouseActionClick(bussiness_status_save_btn);
			this.message("Message");
			this.alternateMethod();
			return textAttribute;
		} else if (value.equals("EditBusinessUnit")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(settings_business);
			textAttribute = this.getText(list_bussiness_unit_name);
			this.mouseActionClick(edit_btn);
			this.valuePresent(bussiness_name, textAttribute);
			this.dropDownByIndex(bussiness_status, 1);
			this.mouseActionClick(bussiness_status_save_btn);
			this.message("Message");
			this.alternateMethod();
			return textAttribute;
		} else if (value.equals("DeleteBusinessUnit")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(settings_business);
			textAttribute = this.getText(list_bussiness_unit_name);
			this.mouseActionClick(delete_btn);
			this.mouseActionClick(Yes);
			this.message("Message");
			return textAttribute;
		} else if (value.equals("CreatedServiceType")) {
			this.mouseActionClick(service_create_btn);
			this.inputText(service_name, BusinessName);
			textAttribute = this.getTextAttribute(service_name);
			this.mouseActionClick(service_save_btn);
			this.message("Message");
			this.alternateMethod();
			return textAttribute;
		} else if (value.equals("EditServiceType")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(settings_business);
			textAttribute = this.getText(list_service_type_name);
			this.mouseActionClick(service_edit_btn);
			this.valuePresent(service_name, textAttribute);
			this.validationTab(service_name, characters2048);
			this.clearField(service_name);
			this.inputText(service_name, textAttribute);
			this.dropDownByIndex(service_status, 1);
			this.mouseActionClick(service_save_btn);
			this.message("Message");
			this.alternateMethod();
			return textAttribute;
		} else if (value.equals("DeleteServiceType")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(settings_business);
			textAttribute = this.getText(list_service_type_name);
			this.mouseActionClick(service_delete_btn);
			this.mouseActionClick(Yes);
			this.message("Message");
			return textAttribute;
		}
		return value;
	}

	public void customerClick() {
		this.mouseActionClick(Customer);
	}

	public void organizationClick() {
		this.mouseActionClick(Organization);
	}

	public void contactClick() {
		this.mouseActionClick(Contact);
	}

	public void userClick() {
		this.mouseActionClick(User);
		//this.visibility(CheckPage);
	}

	public void teamClick() {
		this.mouseActionClick(Team);
		this.visibility(Tittle);
	}

	public String bussinessDays(String value) {
		if (value.equals("LeadSource")) {
			this.visibility(CheckPage);
			this.mouseActionClick(Contact);
			this.visibility(CheckPage);
			this.mouseActionClick(CreateButton);
			for (int i = 0; i < 7; i++) {
				this.mouseActionClick(SaveButton);
				this.mouseActionClick(Next);
			}
		} else if (value.equals("BussinessUnit")) {
			this.mouseActionClick(Job);
			this.visibility(CheckPage);
			this.mouseActionClick(CreateButton);
			for (int i = 0; i < 10; i++) {
				this.mouseActionClick(RadioButtonOrganization);
				this.mouseActionClick(RadioButtonContact);
			}
		} else if (value.equals("ServiceType")) {
			this.mouseActionClick(Contractor);
			//this.visibility(CheckPage);
			this.mouseActionClick(CreateButton);
//			this.mouseActionClick(Job);
		  //  this.visibility(CheckPage);
			
			
			
			}
		this.mouseActionClick(BusinessDaysFields);
		this.inputText(BusinessDaysFields, textAttribute);
		String text = this.getText(DropDownList);
		return text;
	}
	
	

	@FindAll({ @FindBy(xpath = "//*[@id='lead_source-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[contains(text(),'No Data Found')]"),
			@FindBy(xpath = "//*[@id='id_business_unit-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[@id='service_type_id-autocomplete-list']//div[1]") })
	WebElement DropDownList;

	public void bussinessUnitCheck(String value) {
		this.mouseActionClick(Job);
		this.visibility(CheckPage);
		this.mouseActionClick(CreateButton);
		for (int i = 0; i < 10; i++) {
			this.mouseActionClick(RadioButtonOrganization);
			this.mouseActionClick(RadioButtonContact);
		}
	}

	public void alternateMethod() throws IOException, InterruptedException {
		if (responseMessage.equals(getPropertyValue("BusinessUnitAlreadyExists"))
				|| responseMessage.equals(getPropertyValue("ServiceTypeAlreadyExists"))
				|| responseMessage.equals(getPropertyValue("LeadSourceAlreadyExists"))) {
			this.message("AlternateFunction");
		}

	}

}
