package com.zaigo.pageobjects;

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

public class SendInvitePage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By usermenu = By.id("team-menu");

	// private By usermenu=By.xpath("//a[@data-automationid='user']");

	private By usersubmenu = By.id("team-user-menu");

	private By userinvitebtn = By.xpath("//button[@data-n-linkto='team_user_user_sendinvite']");

//			private By sendbtn=By.xpath("//button[@data-automationid='sendLink']");

	// fn
	private By firstname = By.id("send_invite__first_name__0");

	private By firstnameerr = By.id("send_invite__first_name__0_error");

	// ln

	private By lastname = By.xpath("//button[@data-automationid='lastName']");

	// type

//			private By type=By.xpath("//button[@data-automationid='usertype']");

	// email

	private By email = By.id("send_invite__email__0");

	private By emailerr = By.id("send_invite__email__0_error");

	private By next = By.xpath("//span[@class='right-icon']");

	private By mesage = By.xpath("//label[text()='Message']");

	private By popuptext = By.xpath("//button[@data-automationid='send-invite']");

	private By closepopup = By.xpath("//img[@data-exitpopup='team_user_user']");

	private By clickyes = By.xpath("//button[@data-modalconfiratmionpopup='exit-yes']");

	private By dndtype = By.xpath("//input[@data-dropdownlist='user-invite']");
	private By dndAdmin = By.xpath("//div[text()='Admin']");

	private By clickAdd = By.xpath("//*[@data-automationid='add-more']");

	private By previous = By.xpath("//*[text()='Previous']");

	private By getSuccessmessage = By.xpath("(//span[@class='pl-2'])[9]");

	private By clickSaveandComplete = By.xpath("//button[@data-automationid='send-invite']");

	public void clickSubmit() {
		wait.until(ExpectedConditions.elementToBeClickable((clickSaveandComplete)));
		driver.findElement(clickSaveandComplete).click();
	}

	public String getSuccessMessages() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((getSuccessmessage)));
		return driver.findElement(getSuccessmessage).getText();
	}

	public String getAddMoreText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickAdd)));
		return driver.findElement(clickAdd).getText();
	}

	public void clickClose() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((closepopup)));
		driver.findElement(closepopup).click();
	}

	public void clickPrevious() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((previous))).click();

	}

	public void clickYes() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickyes)));
		driver.findElement(clickyes).click();
	}

	public void dndTypes() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated((dndtype))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated((dndAdmin))).click();

	}

	public void clickNext() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((next)));
		driver.findElement(next).click();
	}

	public void enterMessage(String ContactPhone) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((mesage)));
		driver.findElement(mesage).sendKeys(ContactPhone);
	}

	public String getMessageText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((mesage)));
		return driver.findElement(mesage).getText();
	}

	public void enterFirstName(String ContactPhone) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((firstname)));
		driver.findElement(firstname).sendKeys(ContactPhone);
	}

	public void clearEnterFirstName() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((firstname)));
		driver.findElement(firstname).clear();
	}

	public String firstNameError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((firstnameerr)));
		return driver.findElement(firstnameerr).getText();
	}

	public void enterLastName(String ContactPhone) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((lastname)));
		driver.findElement(lastname).sendKeys(ContactPhone);
	}

	public void enterEmail(String ContactPhone) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((email)));
		driver.findElement(email).sendKeys(ContactPhone);
	}
	
	public void ClearEnterEmail() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((email)));
		driver.findElement(email).clear();
	}


	public String getEmailErrorText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((emailerr)));
		return driver.findElement(emailerr).getText();
	}

	public void clickSendInvite() {
		wait.until(ExpectedConditions.elementToBeClickable((userinvitebtn)));
		driver.findElement(userinvitebtn).click();
	}

	public void clickUserTab() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((usermenu)));
		driver.findElement(usermenu).click();
	}

	public String getUserTab() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((usermenu)));
		return driver.findElement(usermenu).getText();
	}

	public void clickUserSubMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((usersubmenu)));
		driver.findElement(usersubmenu).click();
	}

	public String popupText() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((popuptext)));
		return driver.findElement(popuptext).getText();
	}

	public void clickInvite() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((popuptext))).click();
	}

	public SendInvitePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 10);
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

}
