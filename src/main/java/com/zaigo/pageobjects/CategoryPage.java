package com.zaigo.pageobjects;

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

public class CategoryPage extends BaseClass {
	WebDriver driver;
	WebDriverWait wait;
	Faker faker = new Faker(new Locale("en-IND"));
	String fakeTaxName = faker.book().genre();
	String fakeCategoryName = faker.aviation().airport();

	String characters256 = RandomStringUtils.randomAlphabetic(257);
	String characters512 = RandomStringUtils.randomAlphabetic(513);
	String randomCharacter = RandomStringUtils.randomAlphabetic(6);
	String characters16 = RandomStringUtils.randomAlphabetic(20);
	String characters2048 = RandomStringUtils.randomAlphabetic(2049);
	String numberCharacter15 = RandomStringUtils.randomNumeric(15);
	String QuantityValue = RandomStringUtils.randomNumeric(2);
	String PriceValue = RandomStringUtils.randomNumeric(4);
	String DisTaxValue = RandomStringUtils.randomNumeric(2);
	String TaxValue = RandomStringUtils.randomNumeric(2);
	String ReferencePrefix = RandomStringUtils.randomAlphabetic(3).toUpperCase();
	String ReferenceNo = RandomStringUtils.randomNumeric(3);

	public CategoryPage(WebDriver driver) {
		this.driver = driver;

	}

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

	private void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 100);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	private void mouseActionClick(WebElement element) {
		wait = new WebDriverWait(driver, 10);
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

	public boolean elementPresent(By element) {
		wait = new WebDriverWait(driver, 50);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
	}

	private void validationTab(By element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text, Keys.TAB);
	}

	private void validationTab(WebElement element, String text) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text, Keys.TAB);
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

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

	}

	private void invisible(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	private String getText(WebElement element) {
		wait = new WebDriverWait(driver, 10);
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

	public String getTextAttribute(By element) {
		wait = new WebDriverWait(driver, 10);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getAttribute("value");
		return until;
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

	By settings_menu = By.xpath("//a[@id='settings-menu']");

	By Category = By.xpath("//*[text()='Category']");

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/header/div/div/div"),
			@FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/div[1]/header/div/div") })
	WebElement Label;

	By ListCategoryName = By.xpath("//*[@id='fieldy-category-list_aserpttbl']//tr[2]//td[1]//span");

	By ListType = By.xpath("//*[@id='fieldy-category-list_aserpttbl']//tr[2]//td[5]//span");

	By ThreeDots = By.xpath("//*[@id='fieldy-category-list_aserpttbl']//tr[2]//td[6]");

	By Edit = By.xpath("//*[@id='fieldy-category-list_aserpttbl']//tr[2]//td[6]//li[1]");

	By Delete = By.xpath("//*[@id='fieldy-category-list_aserpttbl']//tr[2]//td[6]//li[2]");

	By CreateDate = By.xpath("//*[@id='fieldy-category-list_aserpttbl']//tr[2]//td[2]//span");

	By Status = By.xpath("//*[@id='fieldy-category-list_aserpttbl']//tr[2]//td[4]//span");

	By Type = By.xpath("//*[@id='fieldy-category-list_aserpttbl']//tr[2]//td[5]//span");

	@FindAll({ @FindBy(id = "category-search-value"), @FindBy(id = "settings-category-user-view-search"),
			@FindBy(xpath = "//*[@placeholder='Search by Service Name ...']//ancestor::div[8]//table//tr[2]//td[3]//span") })
	WebElement Search;

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-body-ele']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/button"),
			@FindBy(xpath = "//*[contains(text(),'Save')]"), @FindBy(xpath = "//*[contains(text(),'Update')]"),
			@FindBy(xpath = "//*[@id='job-show-details-timeline']/div[2]/div[3]/button") })
	WebElement Button;

	By ProductService = By.xpath("//*[text()=' Product / Service']");

	By Service = By.xpath("//*[text()='Service']");

	By CategoryField = By.id("settings-category-user-view");

	By CategoryName = By.id("name");

	By CategoryType = By.id("type");

	By Description = By.id("description");

	By ErrorCategoryName = By.id("name_error");

	By ErrorDescription = By.id("description_error");

	By Message = By.xpath("//*[@class='js-snackbar__message']");

	@FindAll({ @FindBy(xpath = "//*[@id='customer-lead-source-div']//div[1]//div[1]//div[1]"),
			@FindBy(xpath = "//*[text()='No Data Available']") })
	WebElement CategoryListName;

	@FindAll({ @FindBy(xpath = "//*[text()='Name']"), @FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement ListPage;

	By Reset = By.xpath("//*[contains(text(),'Reset Search')]");

	By Yes = By.xpath("//*[text()='Yes']");

	By Inactive = By.xpath("//*[@id='categories_create_edit']/div/div[1]/div[1]/div[2]/label[2]/input");

	public String errorFields(String value) {
		if (value.equals("CategoryName")) {
			return this.getText(ErrorCategoryName);
		} else if (value.equals("Description")) {
			return this.getText(ErrorDescription);
		}
		return value;
	}

	public void clearFields(String value) {
		if (value.equals("CategoryName")) {
			this.clearField(CategoryName);
		} else if (value.equals("Description")) {
			this.clearField(Description);
		} else if (value.equals("Search")) {
			this.clearField(Search);
		}
	}

	public void categoryName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(CategoryName, characters2048);
		} else if (value.equals("Mandatory")) {
			this.mouseActionClick(Button);
			if (this.conditionChecking1(ErrorCategoryName)) {
			} else {
				do {
					this.mouseActionClick(Button);
				} while (!this.conditionChecking1(ErrorCategoryName));
			}
		}
	}

	public void description(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(Description, characters2048);
		}
	}

	public String modulePage(String value) {
		if (value.equals("ListPage")) {
			this.mouseActionClick(settings_menu);
			this.mouseActionClick(Category);
		} else if (value.equals("CreatePage")) {
			this.visibility(ListPage);
			this.mouseActionClick(Button);
			this.visibility(CategoryName);
		} else if (value.equals("EditPage")) {
			String text = this.getText(ListCategoryName);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.valuePresent(CategoryName, text);
		}
		String text = this.getText(Label);
		return text;
	}

	static String categoryType;

	public String validData(String value) throws IOException {
		if (value.equals("Product")) {
			this.inputText(CategoryName, fakeCategoryName);
			categoryType = this.getTextAttribute(CategoryType);
			String str = categoryType;
			categoryType = str.substring(0, 1).toUpperCase() + str.substring(1);
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
		} else if (value.equals("ReflectService")) {
			this.inputText(CategoryName, fakeCategoryName);
			categoryType = this.getTextAttribute(CategoryName);
			this.dropDownByIndex(CategoryType, 1);
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
		} else if (value.equals("ReflectionProduct")) {
			this.inputText(CategoryName, fakeCategoryName);
			categoryType = this.getTextAttribute(CategoryName);
			this.inputText(Description, getPropertyValue("QuoteInvoiceDescription"));
		}
		this.mouseActionClick(Button);
		return categoryType;
	}

	public String clickEvent(String value) {
		if (value.equals("ClickButton") || value.equals("Visible")) {
			this.mouseActionClick(Button);
			if (value.equals("Visible")) {
				this.visibility(CategoryName);
			}
			if (this.conditionChecking1(ErrorCategoryName)) {

			} else {
				do {
					this.mouseActionClick(Button);
				} while (!this.conditionChecking1(ErrorCategoryName));
			}
		} else if (value.equals("ButtonPresent")) {
			return this.getText(Button);
		} else if (value.equals("Reset")) {
			this.mouseActionClick(Reset);
		}
		return value;
	}

	static String data;

	public String backEvent(String value) throws IOException {
		this.mouseActionClick(settings_menu);
		this.mouseActionClick(Category);
		if (value.equals("Edit")) {
			String text = this.getText(ListCategoryName);
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Edit);
			this.valuePresent(CategoryName, text);
			this.mouseActionClick(Inactive);
			this.mouseActionClick(Button);
		} else if (value.equals("Delete")) {
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Delete);
			this.mouseActionClick(Yes);
		} else if (value.equals("Create")) {
			this.visibility(ListCategoryName);
			this.mouseActionClick(Button);
			data = this.validData("ReflectService");
		}
		this.message();
		return data;

	}

	static String message;

	public String message() throws IOException {
		Boolean Condition = false;
		if (this.conditionChecking(Message)) {
			message = this.getText(Message);
			this.invisible(Message);
		} else {
			do {
				this.clearField(CategoryName);
				String fakeCategoryName = faker.aviation().airport();
				this.inputText(CategoryName, fakeCategoryName);
				this.mouseActionClick(Button);
				String message2 = this.message();
				if (message2.equals(getPropertyValue("CategoryCreated"))
						|| message2.equals(getPropertyValue("CategoryEdited"))) {
					Condition = true;
				}
			} while (Condition);
		}
		return message;
	}

	static String listData;

	public String listValidation(String value) {
		if (value.equals("SearchData")) {
			this.tagValidation(Search, listData);
		} else if (value.equals("ListCategoryName")) {
			listData = this.getText(ListCategoryName);
		} else if (value.equals("ListType")) {
			listData = this.getText(ListType);
		} else if (value.equals("Invalid")) {
			this.tagValidation(Search, "dsfsjfhdsf");
		} else if (value.equals("InvalidResult")) {
			listData = this.getText(ListPage);
		} else if (value.equals("Delete")) {
			this.mouseActionClick(ThreeDots);
			this.mouseActionClick(Delete);
			this.mouseActionClick(Yes);
		}
		return listData;
	}

	public String refection(String value) throws InterruptedException {
		this.mouseActionClick(settings_menu);
		this.mouseActionClick(ProductService);
		this.visibility(ListPage);
		if (value.equals("CreateProduct") || value.equals("ProductWait")) {
			this.mouseActionClick(Button);
			if (value.equals("ProductWait")) {
				Thread.sleep(5000);
			}
		} else if (value.equals("CreateService") || value.equals("ServiceWait")) {
			this.mouseActionClick(Service);
			this.visibility(Search);
			this.visibility(ListPage);
			this.mouseActionClick(Button);
			if (value.equals("ServiceWait")) {
				Thread.sleep(5000);
			}
		}
		this.scrollDown();
		this.mouseActionClick(CategoryField);
		this.validationTab(Search, categoryType);
		String text = this.getText(CategoryListName);
		return text;
	}
}
