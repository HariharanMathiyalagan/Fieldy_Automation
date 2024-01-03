package com.zaigo.pageobjects;

import java.awt.AWTException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.base.BaseClass;
import com.github.javafaker.Faker;

public class JobPage extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;
	Faker faker = new Faker(new Locale("en-GB"));
	String fakeFirstName = faker.name().firstName();
	String fakeLastName = faker.name().lastName();
	String fakeEmail = faker.internet().safeEmailAddress();
	String fakePhoneNumber = faker.phoneNumber().phoneNumber();
	String fakeTittle = faker.name().title();
	String fakeAddress1 = faker.address().buildingNumber();
	String fakeAddress2 = faker.address().streetName();
	String fakeCity = faker.address().city();
	String fakeState = faker.address().state();
	String fakeZipcode = faker.address().zipCode();
	String fakeWebsite = faker.company().url();
	String fakeCompanyName = faker.company().name();
	String fakeFaxNumber = faker.number().digits(14);

	public void timePicker() {
		LocalTime now = LocalTime.now();
		String pastTime = now.minusMinutes(30).toString();
		String futureTime = now.plusHours(2).toString();
	}

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	By PopupOpen = By.xpath("//*[contains(@class,'fadeIn')]//child::h5");
	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-customer-contact-list_aserpttbl']//tr[2]//td[2]//a"),
			@FindBy(xpath = "//*[@id='fieldy-customer-organization-list_aserpttbl']//tr[2]//td[2]//a"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[2]"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement CustomerNameList;
	@FindAll({ @FindBy(xpath = "//*[@id='contact-job-nav-status']//following::span[3]"),
			@FindBy(xpath = "//*[@id='organization-job-nav-status']//following::span[3]"),
			@FindBy(xpath = "//*[@id='fieldy-main-job-all-list_aserpttbl']//tr[2]//td[3]"),
			@FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='No Data Available']"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[3]") })
	WebElement ListJobNumber1;
	@FindAll({ @FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[2]/td[3]"),
			@FindBy(xpath = "//*[@id='contact-job-nav-status']//following::span[24]"),
			@FindBy(xpath = "//*[@id='organization-job-nav-status']//following::span[24]"),
			@FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='No Data Available']") })
	WebElement ListJobNumber2;
	@FindAll({ @FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[5]"),
			@FindBy(xpath = "//*[@id='organization-job-nav-status']//following::div[@id='customer-contact-request-card-profile-location'][1]"),
			@FindBy(xpath = "//*[@id='contact-job-nav-status']//following::div[@id='customer-contact-request-card-profile-location'][1]"),
			@FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='No Data Available']") })
	WebElement ListLocationName;

	@FindAll({ @FindBy(xpath = "//*[@id='tags-ele-0']/div"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[6]") })
	WebElement ListTechnician;
	@FindAll({
			@FindBy(xpath = "//*[@id='contact-job-nav-status']//following::span[@id='customer-contact-request-card-booking-time'][1]"),
			@FindBy(xpath = "//*[@id='organization-job-nav-status']//following::span[@id='customer-contact-request-card-booking-time'][1]"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[4]"),
			@FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='No Data Available']") })
	WebElement ListFromDate;
	@FindAll({
			@FindBy(xpath = "//*[@id='organization-job-nav-status']//following::span[@id='customer-contact-request-card-booking-time'][2]"),
			@FindBy(xpath = "//*[@id='contact-job-nav-status']//following::span[@id='customer-contact-request-card-booking-time'][2]"),
			@FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='No Data Available']") })
	WebElement ListToDate;
	@FindAll({ @FindBy(xpath = "//*[@id='customer-organization-nav-menu']//li[3]"),
			@FindBy(xpath = "//*[@id='customer-contact-nav-menu']//li[3]") })
	WebElement ClickTabJob;
	@FindAll({ @FindBy(xpath = "//*[@id='activesubscription']/section/div[5]/div[1]/div[2]/div[1]/div/div[2]/div[4]"),
			@FindBy(xpath = "//*[@data-automationid='customer-organization-job-create']"),
			@FindBy(xpath = "//*[@data-n-linkto='main_job_create']") })
	public static WebElement CreateButton;
	By CustomerName = By.id("customer-name");
	By ClickJob = By.xpath("//*[@data-menuswitcher='cstmr-contact-job']");
	@FindAll({ @FindBy(xpath = "//*[@id='job-create']//*[@id='customer-name-input-field']"),
			@FindBy(xpath = "//*[@class='min-hight-600  bg-white p-2']//*[@id='id_customer_group']") })
	WebElement CustomerField;

	@FindAll({ @FindBy(xpath = "//*[@class='col-lg-12 mt-3 switchcontact d-block']//*[@id='id_user_customer']"),
			@FindBy(xpath = "//*[@class='col-lg-12 switchcontact d-block']//*[@id='id_user_customer']") })
	WebElement SubCustomerField;
	By ClickOrganizationJob = By.xpath("//*[@data-menuswitcher='cstmr-organization-job']");
	By JobLabel = By.id("//*[text()='Total Job ']");
	@FindAll({ @FindBy(xpath = "//*[@data-automationid='customer-organization-job-create']"),
			@FindBy(xpath = "//*[@data-automationid='customer-contact-job-create']"),
			@FindBy(xpath = "//*[@data-automationid='job-create']") })
	WebElement CreateJobButton;
	@FindAll({
			@FindBy(xpath = "//*[@class='add_new_customer_button3']//button[@data-modalfetch='shorter_organization_contact_create']"),
			@FindBy(xpath = "//*[@class='add_new_customer_button2']//button[@data-modalfetch='shorter_organization_create']"),
			@FindBy(xpath = "//*[@class='add_new_customer_button no-add_new_customer_button']//button[@data-modalfetch='shorter_contact_create']") })
	WebElement AddCustomer;
	@FindAll({ @FindBy(xpath = "//span[@data-timeline-open='organizationjob']"),
			@FindBy(xpath = "//span[@data-timeline-open='contactjob']") })
	WebElement FilterButton;
	By CreateJob = By.xpath("//*[@data-automationid='customer-contact-job-create']");
	By CreateOrganizationJob = By.xpath("//*[@data-automationid='customer-organization-job-create']");
	By CreateJobLabel = By.xpath("//*[@data-menuselector='job-menu']");
	By CreateGlobalJob = By.xpath("//*[@data-automationid='job-create']");
	By Job = By.id("jobs");
	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div");
	By JobListLabel = By.xpath("//*[@data-menuselector='job-menu']");
	By GlobalCustomer = By.id("id_customer_group");
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='first_name']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='contacts__first_name__0']") })
	WebElement FirstNameField;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='last_name']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='contacts__last_name__0']") })
	WebElement LastNameField;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='email']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='contacts__email__0']") })
	WebElement EmailField;
	@FindAll({ @FindBy(xpath = "//*[@class='modal d-block animated fadeIn']//*[@id='phones__number__0']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='contacts__phone__0']") })
	WebElement PhoneNumber;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='line_1']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='addresses__line_1__0']") })
	WebElement Address1Field;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='line_2']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='addresses__line_2__0']") })
	WebElement Address2Field;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='city']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='addresses__city__0']") })
	WebElement CityField;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='state']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='addresses__state__0']") })
	WebElement StateField;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='zipcode']"),
			@FindBy(xpath = "//*[@id='addresses__zipcode__0']") })
	WebElement ZipcodeField;
	@FindAll({ @FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='organization-create']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='contact-create']"),
			@FindBy(xpath = "//*[contains(@class,'fadeIn')]//*[@id='organization-contact-create']") })
	WebElement SaveButton;
	By Recurring = By.xpath("//*[@id='onetime-recurring-job']/div[2]/input");
	By RepeatJob = By.xpath("//*[@id='daily_recurring_pattern']");
	By Weekly = By.xpath("//*[@id='daily-job-pattern-dropdown']/div[2]/div/div[2]");
	By Monthly = By.xpath("//*[@id='daily-job-pattern-dropdown']/div[2]/div/div[3]");
	By Yearly = By.xpath("//*[@id='daily-job-pattern-dropdown']/div[2]/div/div[4]");
	@FindAll({
			@FindBy(xpath = "//*[@id='day_of_month' and contains(@class,'field-input')]//ancestor::*[@id='monthly-job-day-of-month']//input[1]"),
			@FindBy(xpath = "//*[@id='day_of_week' and contains(@class,'field-input')]//parent::*//input[1]"),
			@FindBy(xpath = "//*[@id='month' and contains(@class,'field-input')]//parent::*//input[1]") })
	WebElement PickDay;
	By Year_Month = By.xpath(
			"//*[@id='day_of_month' and contains(@class,'field-input')]//ancestor::*[@id='yearly-day-of-month']//input[1]");
	@FindAll({
			@FindBy(xpath = "//*[@id='day_of_week' and contains(@class,'field-input')]//ancestor::div[@id='weekly-job-days-dropdown']//div[2]//div[1]//div[1]"),
			@FindBy(xpath = "//*[@id='day_of_month' and contains(@class,'field-input')]//ancestor::div[@id='monthly-job-day-of-month']//div[2]//div[1]//div[1]"),
			@FindBy(xpath = "//*[@id='month' and contains(@class,'field-input')]//ancestor::div[@id='yearly-job-month-dropdown']//div[contains(@class,'d-block')]//div//div[1]"),
			@FindBy(xpath = "//*[@id='day_of_month' and contains(@class,'field-input')]//ancestor::*[@id='yearly-day-of-month']//div[contains(@class,'d-block')]//div[1]//div[1]") })
	WebElement SelectDay;

	By Website = By.xpath("//*[@id='customer_organization_create_edit']//*[@id='website']");
	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By Cancel1 = By.xpath("//*[@class='js-snackbar__close bold']");
	By Location = By.id("location");
	By Tittle = By.id("title");
	By Description = By.id("description");
	By Tags = By.className("tag__input");
	By Notes = By.id("notes");
	By SaveComplete = By.id("scheduledrop");
	By BussinessUnit = By.xpath("//*[@data-dropdownlist='business-unit']");
	By ServiceType = By.xpath("//*[@data-dropdownlist='service-type']");
	@FindAll({ @FindBy(xpath = "//*[@class='pac-item'][1]") })
	WebElement LocationPick;
//	By firstLocation = By.xpath("(//*[@class='pac-item'])[1]");
//	By secoundLocation = By.xpath("(//div[@class='pac-item'])[7]");
	By ErrorLocation = By.id("location_error");
	By ErrorTittle = By.id("title_error");
	By ErrorDescription = By.id("description_error");
	By ErrorTags = By.id("tags__tags__0_error");
	By ErrorNotes = By.id("notes_error");
	By TagRemove = By.xpath("//*[@class='tag__remove']");
	By StartDate = By.id("schedule_from_date");
	By EndDate = By.id("schedule_to_date");
	By StartTime = By.id("schedule_from_time");
	By EndTime = By.id("schedule_to_time");
	By Crew = By.id("crew-radio-button");
	By Single = By.id("technician-radio-button");
	By Technician = By.xpath("//*[@id='technician-request']/div[2]/div[1]/input[1]");
	By Technician1 = By.xpath("//*[@data-dropdownlist='technician-list']");
	By TechnicianFirstName = By.xpath("//*[@id='technician_ids-autocomplete-list']//div[1]");
	By TechnicianSecoundName = By.xpath("//*[@id='technician_ids-autocomplete-list']//div[2]");
	By TechnicianThirdName = By.xpath("//*[@id='technician_ids-autocomplete-list']//div[3]");
	By Priority = By.id("priority");
//	By General = By.xpath("//*[@id='id_business_unit-autocomplete-list']//div[1]");
	@FindAll({ @FindBy(xpath = "//*[@id='id_business_unit-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[contains(text(),'No Data Found')]") })
	WebElement General;
//	By Repair = By.xpath("//*[@class='p-2 list-hover-bg team-service-type w-20-ellipsis w-100']");
	@FindAll({ @FindBy(xpath = "//*[@id='id_service_type-autocomplete-list']//div[1]"),
			@FindBy(xpath = "//*[contains(text(),'No Data Found')]") })
	WebElement Repair;
	By EalierTime = By.xpath("//*[text()='From Time should be current or future time only']");
	By TimeMismatch = By.xpath("//*[text()='Start time should be earlier than End time']");
	By SelectTechnician = By.xpath("//*[@id='assign-technician']/div[1]/div[1]");
	By Yes = By.xpath("//*[text()='Yes']");
	By No = By.xpath("//*[text()='No']");

	@FindAll({ @FindBy(xpath = "//*[@id='contact-job-nav-status']//following::span[4]"),
			@FindBy(xpath = "//*[@id='organization-job-nav-status']//following::span[4]"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[7]"),
			@FindBy(xpath = "//*[text()='No Result Found']"), @FindBy(xpath = "//*[text()='No Data Available']") })
	WebElement StatusJob;

	@FindAll({ @FindBy(xpath = "//*[@id='contact-job-nav-status']//following::span[25]"),
			@FindBy(xpath = "//*[@id='organization-job-nav-status']//following::span[25]"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[2]/td[7]") })
	WebElement StatusJob1;

	@FindAll({ @FindBy(xpath = "(//*[@gloss='Dispatch'])[2]"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[1]/div/div[2]/ul/li[4]") })
	WebElement DispatchButton;
	@FindAll({ @FindBy(xpath = "(//*[@gloss='Start'])[1]"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[1]/div/div[2]/ul/li[2]") })
	WebElement StartButton;
	@FindAll({ @FindBy(xpath = "(//*[@gloss='Completed'])[1]"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[1]/div/div[2]/ul/li[4]") })
	WebElement CompleteButton;
	@FindAll({ @FindBy(xpath = "//*[@gloss='Cancel']"),
			@FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[2]/td[1]/div/div[2]/ul/li[7]") })
	WebElement CancelButton;
	By Delete = By.xpath("//*[@gloss='Delete']");
	By Back = By.xpath("//*[@alt=' back_arrow']");
	@FindAll({ @FindBy(id = "customer-contact-job-search"), @FindBy(id = "customer-company-job-search"),
			@FindBy(id = "searchInput") })
	WebElement SearchField;
	@FindAll({ @FindBy(xpath = "//*[text()='No Data Available']"), @FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement Invalid;
	By Filter = By.id("accordionExample1");
	@FindAll({ @FindBy(id = "customer-contact-job-filter-from-date"), @FindBy(id = "customer-company-job-from-date"),
			@FindBy(id = "from-date") })
	WebElement FilterFromField;
	@FindAll({ @FindBy(id = "customer-contact-job-filter-to-date"), @FindBy(id = "customer-company-job-to-date"),
			@FindBy(id = "to-date") })
	WebElement FilterToField;
	By Apply = By.id("filterBtn");
	By ErrorToTime = By.xpath("//*[text()='Appointment from date,time and Appointment to date needed']");
	By CrewSize = By.id("crew_size");
	By CrewTech = By.id("settings-category-user-view");
	By SelectTech = By.xpath("(//*[@type='checkbox'])[1]");
	By Status = By.xpath("//*[text()='Status  ']");
	By OrganizationJobTittle = By.id("contacts__job_title__0");
	By TechnicianLabel = By.xpath("//*[text()='Technician']");
	@FindAll({
			@FindBy(xpath = "//*[@id='contactdropdownlist' and contains(@style,'display:block;')]//child::div[1]//div[1]"),
			@FindBy(xpath = "//*[@id='contactdropdownlist2' and contains(@style,'display:block;')]//child::div[1]//div[1]"),
			@FindBy(xpath = "//*[text()=' No Data Found!']"),
			@FindBy(xpath = "//*[@id='contactdropdownlist3' and contains(@style,'display:block;')]//child::div[1]//div[1]") })
	WebElement CustomerListField;
	By SubCustomerListField = By.xpath("//*[@id='contactdropdownlist3']//child::div[1]//div[1]");

	By RadioButtonOrg = By.xpath("(//*[@class='mr-2 mb-2'])[2]");
	By RadioButton = By.xpath("(//*[@class='mr-2 mb-2'])[1]");
	By ContactCreateMessage = By.xpath("//*[text()='Customer created successfully']");
	By OrganizationName = By.id("company_name");

	@FindAll({ @FindBy(id = "cutomer-contact-job-count"), @FindBy(id = "cutomer-company-job-count") })
	WebElement TotalCount;

	@FindAll({ @FindBy(xpath = "//*[@id='id_customer_groups']"),
			@FindBy(xpath = "//*[@id='id_customer_orgcntgroup' and contains(@class,'field-input')]") })
	WebElement ContactNumber;

	@FindAll({ @FindBy(xpath = "//*[contains(text(),'No Data Found')]"),
			@FindBy(xpath = "//*[@id='technician_ids-autocomplete-list']//div[1]") })
	WebElement TechnicianName;

	public JobPage(WebDriver driver) {
		this.driver = driver;

	}

	static String ContactFirstName;
	static String ContactLastName;
	public static String ContactPhoneNumber;

	public void autoCompleteField(String value) throws InterruptedException, IOException {
		Boolean condition = true;
		if (value.equals("OrganizationContactCreate")) {
			this.inputText(SubCustomerField, fakeFirstName);
			if (this.conditionChecking(AddCustomer, 20)) {
				this.mouseActionClick(AddCustomer);
				if (!this.conditionChecking(PopupOpen, 5)) {
					do {
						this.mouseActionClick(AddCustomer);
						if (this.conditionChecking(PopupOpen, 5)) {
							condition = false;
						}
					} while (condition);
				}
				this.inputText(FirstNameField, fakeFirstName);
				ContactFirstName = this.getTextAttribute(FirstNameField);
				this.inputText(LastNameField, fakeLastName);
				ContactLastName = this.getTextAttribute(LastNameField);
				this.inputText(EmailField, fakeEmail);
				this.inputText(PhoneNumber, fakePhoneNumber);
				ContactPhoneNumber = this.getTextAttribute(PhoneNumber);
				this.inputText(OrganizationJobTittle, fakeTittle);
				this.mouseActionClick(SaveButton);
			} else {
				this.clearField(SubCustomerField);
				throw new SkipException("Skipping / Ignoring - Script not Ready for Execution ");
			}
		} else if (value.equals("VisibleName")) {
			if (!this.valuePresentCondition(SubCustomerField, ContactFirstName + " " + ContactLastName)) {
				this.inputText(SubCustomerField, ContactFirstName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("OrganizationContactCreate");
						this.message("Message");
						if (this.valuePresentCondition(SubCustomerField, ContactFirstName + " " + ContactLastName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		} else if (value.equals("GlobalContactVisibleName")) {
			if (!this.valuePresentCondition(CustomerField, ContactFirstName + " " + ContactLastName)) {
				this.inputText(CustomerField, ContactFirstName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("ContactCreate");
						this.message("Message");
						if (this.valuePresentCondition(CustomerField, ContactFirstName + " " + ContactLastName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		} else if (value.equals("OrgVisibleName")) {
			if (!this.valuePresentCondition(CustomerField, ContactFirstName)) {
				this.inputText(CustomerField, ContactFirstName);
				if (this.getText(CustomerListField).equals("No Data Found!")) {
					do {
						this.autoCompleteField("OrganizationCreate");
						this.message("Message");
						if (this.valuePresentCondition(CustomerField, ContactFirstName)) {
							condition = false;
						}
					} while (condition);
				} else {
					this.mouseActionClick(CustomerListField);
				}
			}
		} else if (value.equals("ContactCreate")) {
			this.inputText(CustomerField, fakeFirstName);
			if (!this.conditionChecking(AddCustomer, 20)) {
				do {
					this.clearField(CustomerField);
					this.inputText(CustomerField, fakeFirstName);
				} while (!this.conditionChecking(AddCustomer, 20));
			}
			this.mouseActionClick(AddCustomer);
			if (!this.conditionChecking(PopupOpen, 5)) {
				do {
					this.mouseActionClick(AddCustomer);
					if (this.conditionChecking(PopupOpen, 5)) {
						condition = false;
					}
				} while (condition);
			}
			this.inputText(FirstNameField, fakeFirstName);
			ContactFirstName = this.getTextAttribute(FirstNameField);
			this.inputText(LastNameField, fakeLastName);
			ContactLastName = this.getTextAttribute(LastNameField);
			this.inputText(EmailField, fakeEmail);
			this.inputText(PhoneNumber, fakePhoneNumber);
			ContactPhoneNumber = this.getTextAttribute(PhoneNumber);
			this.inputText(Address1Field, fakeAddress1);
			this.inputText(Address2Field, fakeAddress2);
			this.inputText(CityField, fakeCity);
			this.inputText(StateField, fakeState);
			this.inputText(ZipcodeField, fakeZipcode);
			this.clickButton(SaveButton);
		} else if (value.equals("OrganizationCreate")) {
			this.inputText(CustomerField, fakeCompanyName);
			if (!this.conditionChecking(AddCustomer, 20)) {
				do {
					this.clearField(CustomerField);
					this.inputText(CustomerField, fakeFirstName);
				} while (!this.conditionChecking(AddCustomer, 20));
			}
			this.mouseActionClick(AddCustomer);
			if (!this.conditionChecking(PopupOpen, 5)) {
				do {
					this.mouseActionClick(AddCustomer);
					if (this.conditionChecking(PopupOpen, 5)) {
						condition = false;
					}
				} while (condition);
			}
			this.inputText(OrganizationName, fakeCompanyName);
			ContactFirstName = this.getTextAttribute(OrganizationName);
			this.inputText(PhoneNumber, fakePhoneNumber);
			this.inputText(EmailField, fakeEmail);
			this.inputText(Website, fakeWebsite);
			this.inputText(Address1Field, fakeAddress1);
			this.inputText(Address2Field, fakeAddress2);
			this.inputText(CityField, fakeCity);
			this.inputText(StateField, fakeState);
			this.inputText(ZipcodeField, fakeZipcode);
			this.mouseActionClick(SaveButton);
		}
	}

	public Boolean valuePresentCondition(WebElement element, String value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, 50);
			text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	private void inputText(By element, String text) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
	}

	public void inputText(WebElement element, String text) {
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

	private void elementtobeClickable(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	private void clickButton(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private void clickButton(WebElement element) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	private int listWebElement(By element) {
		wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)).size();
	}

	private void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 20);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	public void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 30);
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

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	private void tagValidation(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.ENTER);
	}

	private void tagValidation(WebElement element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text, Keys.ENTER);
	}

	private void dropDownByIndex(By element, int num) {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Select select = new Select(until);
		select.selectByIndex(num);
	}

	private String getText(By element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;
	}

	private String getTexts(By element, int value) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)).get(value).getText();
		return until;
	}

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
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

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

	}

	public Boolean valuePresent(By element, String value) {
		wait = new WebDriverWait(driver, 50);
		Boolean text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		return text;
	}

	public Boolean valuePresent(WebElement element, String value) {
		wait = new WebDriverWait(driver, 50);
		Boolean text = wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
		return text;
	}

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
	}

	public String getTextAttribute(WebElement element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getAttribute("value");
		return until;
	}

	static int parseInt;

	public int getCount() throws InterruptedException {
		wait = new WebDriverWait(driver, 10);
		String text2 = wait.until(ExpectedConditions.visibilityOf(TotalCount)).getText();
		parseInt = Integer.parseInt(text2);
		return parseInt;

	}

	@FindAll({ @FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[2]"),
			@FindBy(xpath = "//*[text()='No Data Available']"), @FindBy(xpath = "//*[text()='Customer']"),
			@FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement JobList;

	@FindAll({ @FindBy(id = "customer-contact-nav-route"), @FindBy(id = "customer-company-nav-route"),
			@FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/div[1]/header/div/div/div/a"),
			@FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/div[1]/header/div/div/div"),
			@FindBy(xpath = "//*[@id='job-create']/header/div/div/div/a"),
			@FindBy(xpath = "//*[@id='breadcrumb_placement']/div/ol/li[2]"),
			@FindBy(xpath = "//*[@id='breadcrumb_placement']/div/ol/li[3]") })
	WebElement ListPage;

	public int countValidation(int value) {
		if (value == 1) {
			int a = parseInt + 1;
			return a;
		} else if (value == 2) {
			this.visibility(JobList);
			String text2 = this.getText(TotalCount);
			int parseInt = Integer.parseInt(text2);
			return parseInt;
		}
		return value;
	}

	public String labelValidation(String value) throws InterruptedException {
		if (value.equals("Customer")) {
			this.mouseActionClick(CustomerNameList);
			this.visibility(CustomerName);
			this.mouseActionClick(ClickTabJob);
			this.visibility(JobList);
			this.mouseActionClick(Filter);
			this.visibility(Apply);
			this.mouseActionClick(Apply);
			this.visibility(JobList);
			this.getCount();
		} else if (value.equals("Global")) {
			this.mouseActionClick(Job);
			this.visibility(JobList);
		} else if (value.equals("CreateLabel")) {
			this.mouseActionClick(CreateButton);
			this.visibility(SaveComplete);
		} else if (value.equals("EditLabel") || value.equals("GlobalEdit")) {
			String text = this.getText(ListLocationName);
			if (value.equals("GlobalEdit")) {
				this.mouseActionClick(ThreeDots);
			}
			this.mouseActionClick(EditButton);
			this.valuePresent(Location, text);
		}
		return this.getText(ListPage);
	}

	static String getName;

	public String customerName(String value) {
		if (value.equals("DetailScreenCustomerName")) {
			getName = this.getText(CustomerName);
			return getName;
		} else if (value.equals("PlaceHolderName")) {
			this.valuePresent(CustomerField, getName);
			String textAttribute = this.getTextAttribute(CustomerField);
			return textAttribute;
		}
		return value;
	}

	@FindAll({ @FindBy(xpath = "//*[text()='Customer']"), @FindBy(xpath = "//*[text()='No Result Found']"),
			@FindBy(xpath = "//*[text()='No Data Found']") })
	WebElement Name;
	By Reset = By.id("resetDiv");

	public String module() throws InterruptedException {
		this.mouseActionClick(Job);
		this.visibility(Name);
		String text = this.getText(Label);
		this.mouseActionClick(CreateButton);
		return text;

	}

	public void mandatoryContactField() throws InterruptedException {
		this.mouseActionClick(SaveComplete);
		if (this.conditionChecking1(ErrorLocation, 1)) {
		} else {
			do {
				this.mouseActionClick(SaveComplete);
			} while (!this.conditionChecking1(ErrorLocation, 1));
		}
	}

	String customer;

	public String customerField() {
		if (this.conditionChecking1(CustomerError, 1)) {
			customer = this.getText(CustomerError);
		} else {
			customer = "null";
		}
		return customer;
	}

	public void mandatoryOrganizationField() throws InterruptedException {
		this.mouseActionClick(RadioButtonOrg);
		this.mouseActionClick(SaveComplete);
		if (this.conditionChecking1(ErrorLocation, 1)) {
		} else {
			do {
				this.mouseActionClick(SaveComplete);
			} while (!this.conditionChecking1(ErrorLocation, 1));
		}
	}

	public void switchOrganization() throws InterruptedException {
		this.mouseActionClick(RadioButtonOrg);
	}

	static String firstName;
	static String lastName;
	static String orgContactName;

	public void picKLocation() {
		this.inputText(Location, fakeState);
		if (!conditionChecking1(LocationPick, 2)) {
			do {
				this.clearField(Location);
				String fakeState = faker.address().state();
				this.inputText(Location, fakeState);
			} while (!conditionChecking1(LocationPick, 2));
			this.mouseActionClick(LocationPick);
		} else {
			this.mouseActionClick(LocationPick);
		}
	}

	public void picKLocation1() {
		this.inputText(Location, fakeState);
		this.mouseActionClick(LocationPick);
	}

	public void maxTagCountValidation() {
		for (int i = 1; i < 66; i++) {
			String fakeTags = RandomStringUtils.randomAlphanumeric(4);
			this.tagValidation(Tags, fakeTags);
		}
	}

	public void currentPickerFromDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(StartDate, currentDate);
	}

	public void currentPickerFromDate1() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(StartDate, currentDate);
	}

	public String currentFilterPickerFromDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public String currentFilterPickerFromDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public void currentPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		this.inputText(EndDate, currentDate);
	}

	static SimpleDateFormat sdf;

	public String twoDaysPickerToDate(String value) {
		if (value.equals("ReturnValue")) {
			sdf = new SimpleDateFormat("MMM-dd-yyy");
		} else if (value.equals("Format")) {
			sdf = new SimpleDateFormat("dd/MM/yyy");
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 3);
		String currentDate = sdf.format(cal.getTime());
		if (value.equals("InputText")) {
			this.inputText(EndDate, currentDate);
		}
		return currentDate;
	}

	public String currentFilterPickerToDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public String currentFilterPickerToDate1() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public String currentFilterPickerToDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String currentDate = sdf.format(cal.getTime());
		return currentDate;
	}

	public void pastTime() {
		LocalTime now = LocalTime.now();
		String pastTime = now.minusMinutes(30).toString();
		this.inputText(StartTime, pastTime);

	}

	public void startTime() {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(1).toString();
		String pastTime = now.minusMinutes(90).toString();
		this.inputText(StartTime, futureTime);
		this.inputText(EndTime, pastTime);

	}

	public void futureStartTime() {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(2).toString();
		this.inputText(StartTime, futureTime);

	}

	public void futureToTime() {
		LocalTime now = LocalTime.now();
		String futureTime = now.plusHours(2).toString();
		this.inputText(EndTime, futureTime);
	}

	By ThreeDots = By.xpath("//*[@id='new_table_with_search']/tbody/tr[1]/td[1]/div");
	By GlobalEdit = By.xpath("(//*[@data-n-linkto='job_edit'])[1]");

	@FindAll({ @FindBy(xpath = "//*[@id='new_table_with_search']/tbody/tr[1]/td[1]/div//div[2]//ul//li[2]"),
			@FindBy(xpath = "//*[@id='customer-contact-job-all']/div/div[1]/div/div/div/div[3]/div[2]/a"),
			@FindBy(xpath = "//*[@id='customer-organization-job-all']/div[1]/div[1]/div/div/div/div[3]/div[2]/a") })
	WebElement EditButton;

	public void globalJob() throws InterruptedException {
		this.visibility(JobList);
		this.mouseActionClick(CreateButton);
	}

	By CustomerError = By.id("id_customer_group_error");
	By ThreeDotsCancel = By.xpath("//*[@id='new_table_with_search']/tbody/tr[2]/td[1]/div");

	public String dateFrom() {
		String currentFilterPickerFromDate = this.currentFilterPickerFromDate();
		return currentFilterPickerFromDate;
	}

	public String dateTo() {
		String currentFilterPickerToDate = this.currentFilterPickerToDate();
		return currentFilterPickerToDate;
	}

	public String jobStatusCreation(String value) throws IOException, InterruptedException, AWTException {
		driver.manage().deleteAllCookies();
		if (value.equals("Unassigned")) {
			this.mouseActionClick(BussinessUnit);
			if (this.getText(General).equals("No Data Found")) {
				do {
					Thread.sleep(3000);
					this.mouseActionClick(BussinessUnit);
				} while (this.getText(General).equals("No Data Found"));
				this.mouseActionClick(General);
			} else {
				this.mouseActionClick(General);
			}
			this.mouseActionClick(ServiceType);
			if (this.getText(Repair).equals("No Data Found")) {
				do {
					Thread.sleep(3000);
					this.mouseActionClick(BussinessUnit);
				} while (this.getText(Repair).equals("No Data Found"));
				this.mouseActionClick(Repair);
			} else {
				this.mouseActionClick(Repair);
			}
			this.dropDownByIndex(Priority, 2);
			this.picKLocation();
			this.scrollDown();
			this.currentPickerFromDate();
			this.futureStartTime();
			this.assertName(SelectTechnician, "Select Technician");
			this.clearField(Tags);
			this.inputText(Tittle, fakeTittle);
			this.clearField(Description);
			this.inputText(Description, getPropertyValue("Description"));
			this.inputText(Notes, getPropertyValue("Notes"));
			this.attachmentFileCheck("URLCheck");
			this.assertName(SaveComplete, "Schedule Job");
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("Edit")) {
			String text = this.getText(ListLocationName);
			this.mouseActionClick(EditButton);
			this.valuePresent(Location, text);
		} else if (value.equals("GlobalEdit")) {
			String text = this.getText(ListLocationName);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(EditButton);
			this.valuePresent(Location, text);
		} else if (value.equals("UpdateData")) {
			this.picKLocation();
			this.inputText(Tittle, fakeTittle);
			this.inputText(Description, getPropertyValue("Description"));
			this.validationTab(Tags, randomCharacter);
			this.inputText(Notes, getPropertyValue("Notes"));
			this.clearField(Tags);
			this.scrollDown();
			this.mouseActionClick(Technician);
			this.mouseActionClick(TechnicianFirstName);
			if (!(this.getTextAttribute(Technician).length() > 0)) {
				do {
					this.mouseActionClick(Technician);
					this.mouseActionClick(TechnicianFirstName);
				} while (!(this.getTextAttribute(Technician).length() > 0));
			}
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("Create")) {
			this.customerName("DetailScreenCustomerName");
			this.mouseActionClick(CreateJobButton);
			this.customerName("PlaceHolderName");
		} else if (value.equals("CustomerContactDraft")) {
			this.customerName("DetailScreenCustomerName");
			this.mouseActionClick(CreateButton);
			this.customerName("PlaceHolderName");
			this.mouseActionClick(Back);
			this.mouseActionClick(Yes);
			this.message("Message");
		} else if (value.equals("CreateJob") || value.equals("FromTime") || value.equals("TwoDaysWork")
				|| value.equals("DailyRecurring") || value.equals("WeeklyRecurring") || value.equals("MonthlyRecurring")
				|| value.equals("YearlyRecurring")) {
			this.picKLocation();
			this.mouseActionClick(BussinessUnit);
			if (this.getText(General).equals("No Data Found")) {
				do {
					Thread.sleep(3000);
					this.mouseActionClick(BussinessUnit);
				} while (this.getText(General).equals("No Data Found"));
				this.mouseActionClick(General);
			} else {
				this.mouseActionClick(General);
			}
			this.mouseActionClick(ServiceType);
			if (this.getText(Repair).equals("No Data Found")) {
				do {
					Thread.sleep(3000);
					this.mouseActionClick(BussinessUnit);
				} while (this.getText(Repair).equals("No Data Found"));
				this.mouseActionClick(Repair);
			} else {
				this.mouseActionClick(Repair);
			}
			this.dropDownByIndex(Priority, 2);
			this.inputText(Tittle, fakeTittle);
			this.inputText(Description, getPropertyValue("Description"));
			this.scrollDown();
			this.currentPickerFromDate();
			this.dropDownByIndex(StartTime, 41);
			this.invisible(Spinner);
			if (value.equals("CreateJob") || value.equals("DailyRecurring") || value.equals("WeeklyRecurring")
					|| value.equals("MonthlyRecurring") || value.equals("YearlyRecurring")) {
				if (value.equals("DailyRecurring") || value.equals("WeeklyRecurring")
						|| value.equals("MonthlyRecurring") || value.equals("YearlyRecurring")) {
					do {
						this.mouseActionClick(Recurring);
					} while (this.conditionChecking1(By.xpath("//*[@id='recurring-job' and contains(@class,'d-none')]"),
							3));
					if (value.equals("WeeklyRecurring") || value.equals("YearlyRecurring")
							|| value.equals("MonthlyRecurring")) {
						this.mouseActionClick(RepeatJob);
						switch (value) {
						case "WeeklyRecurring":
							this.mouseActionClick(Weekly);
							if (!this.getTextAttribute(RepeatJob).equals("Weekly")) {
								do {
									this.mouseActionClick(RepeatJob);
									this.mouseActionClick(Weekly);
								} while (!this.getTextAttribute(RepeatJob).equals("Weekly"));
							}
							break;
						case "MonthlyRecurring":
							this.mouseActionClick(Monthly);
							if (!this.getTextAttribute(RepeatJob).equals("Monthly")) {
								do {
									this.mouseActionClick(RepeatJob);
									this.mouseActionClick(Monthly);
								} while (!this.getTextAttribute(RepeatJob).equals("Monthly"));
							}
							break;
						case "YearlyRecurring":
							this.mouseActionClick(RepeatJob);
							if (this.conditionChecking1(Yearly, 3)) {
								this.mouseActionClick(Yearly);
							} else {
								do {
									this.mouseActionClick(RepeatJob);
									this.mouseActionClick(Yearly);
								} while (!this.getTextAttribute(RepeatJob).equals("Yearly"));
							}
							this.mouseActionClick(PickDay);
							this.mouseActionClick(SelectDay);
							break;
						default:
							break;
						}
						if (value.equals("YearlyRecurring")) {
							this.mouseActionClick(Year_Month);
							if (this.conditionChecking1(SelectDay, 2)) {
								this.mouseActionClick(SelectDay);
							} else {
								do {
									this.mouseActionClick(Year_Month);
								} while (!this.conditionChecking1(SelectDay, 2));
								this.mouseActionClick(SelectDay);
							}
						} else {
							this.mouseActionClick(PickDay);
							this.mouseActionClick(SelectDay);
						}
					}
				}
				this.currentPickerToDate();
				this.inputText(EndTime, "18.00");
				this.invisible(Spinner);
			} else if (value.equals("TwoDaysWork")) {
				this.invisible(Spinner);
				this.twoDaysPickerToDate("Format");
				this.twoDaysPickerToDate("InputText");
				this.dropDownByIndex(EndTime, 45);
				this.invisible(Spinner);
			}
			this.visibility(TechnicianLabel);
			this.scrollDown();
			this.tagValidation(Tags, randomCharacter);
			this.inputText(Notes, getPropertyValue("Notes"));
			this.mouseActionClick(Technician);
			this.mouseActionClick(TechnicianSecoundName);
			if (!(this.getTextAttribute(Technician).length() > 0)) {
				do {
					this.mouseActionClick(Technician);
					this.mouseActionClick(TechnicianSecoundName);
				} while (!(this.getTextAttribute(Technician).length() > 0));
			}
			this.mouseActionClick(SaveComplete);
		} else if (value.equals("BackNav")) {
			this.scrollUp();
			this.mouseActionClick(ListPage);
			this.mouseActionClick(No);
			this.mouseActionClick(JobList);
			this.tiggerFunction("Cancel");
			this.message("Message");
		}
		return value;

	}

	public String errorValidation(String value) {
		if (value.equals("Location")) {
			String text = this.getText(ErrorLocation);
			return text;
		} else if (value.equals("Description")) {
			String text = this.getText(ErrorDescription);
			return text;
		} else if (value.equals("Tittle")) {
			String text = this.getText(ErrorTittle);
			return text;
		} else if (value.equals("Tag")) {
			String text = this.getText(ErrorTags);
			return text;
		} else if (value.equals("Notes")) {
			String text = this.getText(ErrorNotes);
			return text;
		} else if (value.equals("Button")) {
			String text = this.getText(SaveComplete);
			return text;
		} else if (value.equals("ButtonClick")) {
			this.mouseActionClick(SaveComplete);
			if (!this.conditionChecking1(ErrorLocation, 1)) {
				do {
					this.mouseActionClick(SaveComplete);
				} while (!this.conditionChecking1(ErrorLocation, 1));
			}
		} else if (value.equals("CustomerName")) {
			String text = this.getText(CustomerError);
			return text;
		}
		return value;
	}

	public String clearValidation(String value) {
		if (value.equals("Location")) {
			this.clearField(Location);
		} else if (value.equals("Description")) {
			this.clearField(Description);
		} else if (value.equals("Tittle")) {
			this.clearField(Tittle);
		} else if (value.equals("Tag")) {
			this.clearField(Tags);
		} else if (value.equals("Notes")) {
			this.clearField(Notes);
		} else if (value.equals("StartDate")) {
			this.clearField(StartDate);
		} else if (value.equals("EndDate")) {
			this.clearField(EndDate);
		} else if (value.equals("StartTime")) {
			this.clearField(StartTime);
		} else if (value.equals("EndTime")) {
			this.clearField(EndTime);
		} else if (value.equals("TagRemove")) {
			this.mouseActionClick(TagRemove);
		} else if (value.equals("RemoveMultipleTag")) {
			int listWebElement = this.listWebElement(TagRemove);
			for (int i = 0; i < listWebElement - 1; i++) {
				this.mouseActionClick(TagRemove);
			}
		} else if (value.equals("Search")) {
			this.clearField(SearchField);
			this.clearField(SearchField);
		}
		return value;
	}

	public void tiggerFunction(String value) {
		if (value.equals("Dispatch")) {
			this.mouseActionClick(DispatchButton);
		} else if (value.equals("Start")) {
			this.mouseActionClick(StartButton);
		} else if (value.equals("Complete")) {
			this.mouseActionClick(CompleteButton);
		} else if (value.equals("Cancel")) {
			this.mouseActionClick(CancelButton);
		} else if (value.equals("Delete")) {
			this.mouseActionClick(Delete);
		}
		this.mouseActionClick(Yes);
	}

	public void globalTiggerFunction(String value) {
		if (value.equals("First")) {
			this.mouseActionClick(ThreeDots);
		} else if (value.equals("Secound")) {
			this.mouseActionClick(ThreeDotsCancel);
		}

	}

	public Boolean conditionChecking(By element, int value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, value);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking(WebElement element, int value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, value);
			text = wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking1(By element, int value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, value);
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public Boolean conditionChecking1(WebElement element, int value) {
		Boolean text = false;
		try {
			wait = new WebDriverWait(driver, value);
			text = wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public String techcnianNotAvailable() {
		if (responseMessage.contains("technician")) {
			String name2 = responseMessage;
			int startIndex = name2.indexOf("technician");
			if (startIndex != -1) {
				responseMessage = name2.substring(startIndex);
			}
		}
		return responseMessage;
	}

	static String responseMessage;
	static int increment;

	public String message(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message") || value.equals("FormMessage")) {
			if (this.conditionChecking(Message, 40)) {
				responseMessage = this.getTexts(Message, 0);
				this.invisible(Message);
				techcnianNotAvailable();
				return responseMessage;
			} else {
				do {
					Thread.sleep(10000);
					techcnianNotAvailable();
					if (value.equals("Message")) {
						this.mouseActionClick(SaveButton);
					} else if (value.equals("FormMessage")) {
						this.mouseActionClick(SaveComplete);
					}
					if (this.conditionChecking(Message, 40)) {
						responseMessage = this.getTexts(Message, 0);
						this.invisible(Message);
						if (responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))
								|| responseMessage.equals(getPropertyValue("JobCreatedMessage"))
								|| responseMessage.equals(getPropertyValue("JobUpdatedMessage"))
								|| responseMessage.equals(getPropertyValue("ContactEmailAlreadyMessage"))
								|| responseMessage.equals(getPropertyValue("CompanyEmailAlreadyMessage"))
								|| responseMessage.equals(getPropertyValue("CompanyContactEmailMessage"))
								|| responseMessage.equals(getPropertyValue("CompanyAlreadyMessage"))
								|| responseMessage.equals(getPropertyValue("TechnicianAvailability"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		} else if (value.equals("AlternateFunction") || value.equals("AlternateForm")) {
			do {
				techcnianNotAvailable();
				if (responseMessage.equals(getPropertyValue("ContactEmailAlreadyMessage"))
						|| responseMessage.equals(getPropertyValue("CompanyEmailAlreadyMessage"))
						|| responseMessage.equals(getPropertyValue("CompanyContactEmailMessage"))) {
					this.clearField(EmailField);
					String fakeEmail = faker.internet().safeEmailAddress();
					this.inputText(EmailField, fakeEmail);
					this.mouseActionClick(SaveButton);
				} else if (responseMessage.equals(getPropertyValue("CompanyAlreadyMessage"))) {
					this.clearField(OrganizationName);
					String fakeCompanyName = faker.company().name();
					this.inputText(OrganizationName, fakeCompanyName);
					firstName = this.getTextAttribute(OrganizationName);
					this.mouseActionClick(SaveButton);
				} else if (responseMessage.equals(getPropertyValue("TechnicianAvailability"))) {
					this.scrollDown();
					this.mouseActionClick(Technician);
					if (!this.conditionChecking(TechnicianFirstName, 3)) {
						do {
							this.mouseActionClick(Technician);
						} while (!this.conditionChecking(TechnicianFirstName, 3));
					}
					increment++;
					this.mouseActionClick(
							By.xpath("//*[@id='technician_ids-autocomplete-list']//div[" + increment + "]"));
					this.mouseActionClick(SaveComplete);
				}
				if (this.conditionChecking(Message, 40)) {
					responseMessage = this.getTexts(Message, 0);
					this.invisible(Message);
					if (responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))
							|| responseMessage.equals(getPropertyValue("JobCreatedMessage"))
							|| responseMessage.equals(getPropertyValue("JobUpdatedMessage"))) {
						conditionCheck = false;
					}
				} else {
					do {
						techcnianNotAvailable();
						Thread.sleep(10000);
						if (value.equals("AlternateFunction")) {
							if (this.conditionChecking(SaveButton, 10)) {
								this.mouseActionClick(SaveButton);
							}
						} else if (value.equals("AlternateForm")) {
							if (this.conditionChecking(SaveComplete, 10)) {
								this.mouseActionClick(SaveComplete);
							}
						}
						if (this.conditionChecking(Message, 40)) {
							responseMessage = this.getTexts(Message, 0);
							this.invisible(Message);
							if (responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))
									|| responseMessage.equals(getPropertyValue("JobCreatedMessage"))
									|| responseMessage.equals(getPropertyValue("JobUpdatedMessage"))) {
								conditionCheck = false;
							} else {
								this.message("AlternateFunction");
								conditionCheck = false;
							}
						}
					} while (conditionCheck);
				}
			} while (conditionCheck);
			increment = 0;
		}
		return value;
	}

	public void clearAllFields(String value) {
		if (value.equals("Page")) {
			List<String> asList = Arrays.asList("Location", "Description", "Tittle", "Notes");
			for (int i = 0; i < asList.size(); i++) {
				this.clearValidation(asList.get(i));
			}
		}
	}

	public void valuePresent() {
		String value = firstName + " " + lastName;
		this.valuePresent(GlobalCustomer, value);

	}

	By Label = By.xpath("//*[@data-menuselector='job-menu']");

	public String jobLandPage() {
		String text = this.getText(Label);
		return text;
	}

	public void locationField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Location, characters2048);
		}
	}

	public void descriptionField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Description, characters2048);
		}
	}

	public void tittleField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Tittle, characters2048);
		}
	}

	public String contactNumberField(String value) {
		if (value.equals("Prepopulate")) {
			return this.getTextAttribute(ContactNumber);
		}
		return value;
	}

	public void tagsField(String value) {
		if (value.equals("MaxCharacter")) {
			this.tagValidation(Tags, characters256);
		} else if (value.equals("DuplicateTags")) {
			this.tagValidation(Tags, fakeFaxNumber);
			this.tagValidation(Tags, fakeFaxNumber);
		} else if (value.equals("MaxLimitTags")) {
			for (int i = 1; i < 66; i++) {
				String fakeTags = RandomStringUtils.randomAlphanumeric(4);
				this.tagValidation(Tags, fakeTags);
			}
		}

	}

	public void notesField(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Notes, characters2048);
		}
	}

	@FindAll({ @FindBy(id = "job-search-enter"), @FindBy(id = "customer-contact-job-search-enter"),
			@FindBy(id = "searchBtn") })
	WebElement SearchButton;
	static String listData;

	public String listValidation(String value) {
		driver.manage().deleteAllCookies();
		if (value.equals("SearchData")) {
//			this.elementtobeClickable(SearchButton);
			this.inputText(SearchField, listData);
			do {
				this.mouseActionClick(SearchButton);
			} while (!this.conditionChecking1(By.xpath("//*[@id='fieldy-main-request-loader']//div//div[1]"), 5));
		} else if (value.equals("JobNo1")) {
			if (!this.conditionChecking(ListJobNumber1, 30)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(ListJobNumber1, 30));
			}
			listData = this.getText(ListJobNumber1);
		} else if (value.equals("JobNo2")) {
			if (!this.conditionChecking(ListJobNumber2, 30)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(ListJobNumber2, 30));
			}
			listData = this.getText(ListJobNumber2);
		} else if (value.equals("Location")) {
			if (!this.conditionChecking(ListLocationName, 30)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(ListLocationName, 30));
			}
			listData = this.getText(ListLocationName);
		} else if (value.equals("CustomerName")) {
			if (!this.conditionChecking(CustomerNameList, 30)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(CustomerNameList, 30));
			}
			listData = this.getText(CustomerNameList);
		} else if (value.equals("FromDate")) {
			if (!this.conditionChecking(ListFromDate, 30)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(ListFromDate, 30));
			}
			listData = this.getText(ListFromDate);
		} else if (value.equals("ToDate")) {
			if (!this.conditionChecking(ListToDate, 30)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(ListToDate, 30));
			}
			listData = this.getText(ListToDate);
		} else if (value.equals("Status")) {
			if (!this.conditionChecking(StatusJob, 30)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(StatusJob, 30));
			}
			listData = this.getText(StatusJob);
		} else if (value.equals("Status1")) {
			if (!this.conditionChecking(StatusJob1, 30)) {
				do {
					driver.navigate().refresh();
				} while (!this.conditionChecking(StatusJob1, 30));
			}
			listData = this.getText(StatusJob1);
		} else if (value.equals("FilterDatePicker")) {
			this.mouseActionClick(Filter);
			String currentFilterPickerFromDate = this.currentFilterPickerFromDateFormat();
			String currentFilterPickerToDate = this.currentFilterPickerToDateFormat();
			this.inputText(FilterFromField, currentFilterPickerFromDate);
			this.inputText(FilterToField, currentFilterPickerToDate);
			this.mouseActionClick(Apply);
		} else if (value.equals("Invlaid")) {
			this.tagValidation(SearchField, "sdfsffdsffds");
		} else if (value.equals("InvalidResult")) {
			listData = this.getText(Invalid);
		} else if (value.equals("Technician")) {
			listData = this.getText(ListTechnician);
		}
		return listData;
	}

	public String checkTechnician(String value) throws InterruptedException {
		if (value.equals("9.45A.M")) {
			this.dropDownByIndex(StartTime, 40);
		} else if (value.equals("10.30A.M")) {
			this.dropDownByIndex(StartTime, 43);
		} else if (value.equals("11.00A.M")) {
			this.dropDownByIndex(StartTime, 45);
		} else if (value.equals("8.15A.M")) {
			this.dropDownByIndex(StartTime, 34);
		} else if (value.equals("10.00A.M")) {
			this.dropDownByIndex(StartTime, 41);
		} else if (value.equals("11.45A.M")) {
			this.dropDownByIndex(StartTime, 48);
		}
		driver.manage().deleteAllCookies();
		this.invisible(Spinner);
		this.mouseActionClick(Technician);
		this.visibility(TechnicianName);
		this.inputText(Technician, listData);
		String text = this.getText(TechnicianName);
		return text;
	}

	public void createFunction() throws IOException, InterruptedException {
		if (!responseMessage.equals(getPropertyValue("CustomerCreatedMessage"))) {
			this.message("AlternateFunction");
		}
	}

	By Attachment = By.xpath("//*[@id='scheduleDropzone']");
	@FindAll({ @FindBy(xpath = "//*[@id='previews']/div/div/div[1]/span"),
			@FindBy(xpath = "//*[@id='edit-list-file']/div/div[1]/span") })
	WebElement FirstAttachment;
	HttpURLConnection connection;
	List<String> list;

	public void attachmentFileCheck(String value)
			throws AWTException, MalformedURLException, IOException, InterruptedException {
		this.scrollDown();
		if (value.equals("URLCheck")) {
			this.mouseActionClick(Attachment);
			Thread.sleep(2000);
			BaseClass.attachmentFile(System.getProperty("user.dir") + "\\ImagePicture\\Free_Test_Data_1MB_PDF.pdf");
			if (!this.conditionChecking1(FirstAttachment, 3)) {
				do {
					this.mouseActionClick(Attachment);
					BaseClass.attachmentFile(
							System.getProperty("user.dir") + "\\ImagePicture\\Free_Test_Data_1MB_PDF.pdf");
				} while (!this.conditionChecking1(FirstAttachment, 3));
			}
		} else if (value.equals("CheckResponse") || value.equals("LoopNext")) {
			this.mouseActionClick(FirstAttachment);
			Set<String> windowHandles = driver.getWindowHandles();
			list = new ArrayList<String>(windowHandles);
			driver.switchTo().window(list.get(1));
			String currentUrl = driver.getCurrentUrl();
			connection = (HttpURLConnection) new URL(currentUrl).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
		} else if (value.equals("ParentWindow")) {
			driver.switchTo().window(list.get(0));
		}
	}

	public int responseCode() throws IOException {
		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			return responseCode;
		} else {
			return responseCode;
		}
	}

	public String createandUpdateMessage() throws IOException, InterruptedException {
		String message2 = message("FormMessage");
		if (message2.equals(getPropertyValue("JobCreatedMessage"))
				|| message2.equals(getPropertyValue("JobUpdatedMessage"))) {
			return message2;
		} else {
			return message("AlternateForm");
		}
	}
}
