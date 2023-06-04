package com.zaigo.pageobjects;

import java.text.DecimalFormat;

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

public class SubscriptionPage extends BaseClass {
	WebDriver driver;
	WebDriverWait wait;

	public SubscriptionPage(WebDriver driver) {
		this.driver = driver;
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

	private void mouseActionClick(WebElement element) {
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

	By Profile = By.xpath("//*[@id='avator-ele']");
	By Subscribtion = By.xpath("//*[@id='myDropdown']//li[2]");
	By StarterAmount = By.id("starter_price");
	By TeamAmount = By.id("team_price");
	By PowerHouseAmount = By.id("power_house_price");
	By StarterDropDown = By.id("starter_select");
	By TeamDropDown = By.id("team_select");
	By PowerHouseDropDown = By.id("power_house_select");
	By StarterChoosePlan = By.id("starter_plan_button");
	By TeamChoosePlan = By.id("team_plan_button");
	By PowerHouseChoosePlan = By.id("power_house_plan_button");
	By ProRataAmount = By.xpath("//*[@id='tb_total_pro_rata_amount']");
	By TotalAmount = By.xpath("//*[@id='tb_total_amount']");
	@FindAll({ @FindBy(xpath = "//*[@id='upgrade-proceed-to-payment']/div[3]/div/div[2]/div[2]/button"),
			@FindBy(xpath = "//*[@id='tb_proceed_to_payment']"), @FindBy(xpath = "//*[@id='rzp-button1']"),
			@FindBy(xpath = "//*[@id='subscription_contianers']/div[2]/div/div/div[3]/button") })
	WebElement SubmitButton;
	By MonthlyAmount = By.xpath("//*[@id='monthly-amount']");
	By YearlyAmount = By.xpath("//*[@id='yearly-amount']");
	By Spinner = By.xpath("//*[@id='spinnerDiv']/div/div/div");
	@FindAll({ @FindBy(xpath = "//*[contains(text(),'Payment Details')]"),
			@FindBy(xpath = "//*[contains(text(),'Billing Contact :')]//ancestor::*[@id='fieldy-body-ele']//*[@id='upgrade-proceed-to-payment']//header//div//div//div//span"),
			@FindBy(xpath = "//*[@id='starter_plan_header']//ancestor::*[@id='fieldy-body-ele']//*[@id='change_plan_monthly-header']//header//div//div//div//span"),
			@FindBy(xpath = "//*[text()='Confirm Order']//ancestor::*[@id='fieldy-body-ele']//*[@id='change_plan_monthly-header']//header//div//div//div//span") })
	WebElement Label;
	By MainAmount = By.xpath("//*[@id='main-total']");
	By AnnualButton = By.xpath("//*[@id='plantab']/button[2]");

	public void modulePage() {
		this.mouseAction(Profile);
		this.mouseActionClick(Subscribtion);
		this.invisible(Spinner);
		this.mouseActionClick(SubmitButton);
		if (!this.conditionChecking(Label, 2)) {
			do {
				this.mouseActionClick(SubmitButton);
			} while (!this.conditionChecking(Label, 2));
		}
	}

	public String labelValidation(String value) {
		if (value.equals("StarterPlan") || value.equals("TeamPlan") || value.equals("PowerHousePlan")) {
			switch (value) {
			case "StarterPlan":
				this.mouseActionClick(StarterChoosePlan);
				break;
			case "TeamPlan":
				this.mouseActionClick(TeamChoosePlan);
			case "PowerHousePlan":
				this.mouseActionClick(PowerHouseChoosePlan);
			default:
				break;
			}
			this.visibility(ProRataAmount);
		} else if (value.equals("1") || value.equals("2") || value.equals("3")) {
			this.mouseActionClick(SubmitButton);
			switch (value) {
			case "2":
				this.visibility(By.xpath(
						"//*[contains(text(),'Billing Contact :')]//ancestor::*[@id='fieldy-body-ele']//*[@id='upgrade-proceed-to-payment']//header//div//div//div//span"));
				break;
			case "3":
				this.visibility(By.xpath("//*[contains(text(),'Payment Details')]"));
				break;
			default:
				break;
			}
		}
		this.invisible(Spinner);
		return this.getText(Label);
	}

	public double convertion(int value) {
		if (value == 1) {
			String intamount = intialAmount.replace("₹", "").replace(",", "");
			return Double.parseDouble(intamount);
		} else if (value == 2) {
			String proamount = proAmount.replace("₹", "").replace(",", "");
			return Double.parseDouble(proamount);
		}
		return value;
	}

	static String proAmount;
	static String intialAmount;

	public void subscriptionFlow(String value) {
		if (value.equals("Annual")) {
			this.mouseActionClick(AnnualButton);
		} else if (value.equals("Starter")) {
			intialAmount = this.getText(StarterAmount);
			this.dropDownByIndex(StarterDropDown, 2);
			proAmount = this.getTextAttribute(StarterDropDown);
		} else if (value.equals("Team")) {
			intialAmount = this.getText(TeamAmount);
			this.dropDownByIndex(TeamDropDown, 2);
			proAmount = this.getTextAttribute(TeamDropDown);
		} else if (value.equals("PowerHouse")) {
			intialAmount = this.getText(PowerHouseAmount);
			this.dropDownByIndex(PowerHouseDropDown, 2);
			proAmount = this.getTextAttribute(PowerHouseDropDown);
		}
	}

	public String getValue(String value) {
		if (value.equals("ProAmount")) {
			return this.getText(ProRataAmount);
		} else if (value.equals("TotalAmount")) {
			return this.getText(TotalAmount);
		} else if (value.equals("BillingAmount") || value.equals("YearlyBillingAmount")) {
			switch (value) {
			case "BillingAmount":
				return this.getText(MonthlyAmount);
			case "YearlyBillingAmount":
				return this.getText(YearlyAmount);
			default:
				break;
			}
		} else if (value.equals("MainAmount")) {
			return this.getText(MainAmount);
		}
		return value;
	}

	public static double convertion;
	public static String valueOf;

	public String calculation(String value) {
		if (value.equals("ProAmount")) {
			convertion = this.convertion(1) * this.convertion(2);
		} else if (value.equals("TotalAmount")) {
			convertion = convertion * 18 / 100 + convertion;
		}
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		String format = decimalFormat.format(convertion);
		valueOf = String.valueOf(format);
		return "₹" + valueOf;
	}
}
