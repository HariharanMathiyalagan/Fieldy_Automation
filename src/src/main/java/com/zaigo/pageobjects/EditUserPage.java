package com.zaigo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EditUserPage {

	WebDriver driver;
	WebDriverWait wait;

	public EditUserPage(WebDriver driver) {
		this.driver = driver;
	}

	By Email = By.id("login");
	By Pass = By.id("password");
	By Click = By.xpath("//button[@type='submit']");

	private void setEmail(String email) {
		driver.findElement(Email).sendKeys(email);
	}

	private void setPass(String pass) {
		driver.findElement(Pass).sendKeys(pass);
	}

	private void clickLogin() {
		driver.findElement(Click).click();
	}

	public void login(String UserName, String Password) throws InterruptedException {
		this.setEmail(UserName);
		this.setPass(Password);
		this.clickLogin();

	}

	By Team = By.id("team-menu");
	By User = By.id("team-user-menu");

	private void clickTeam() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Team)).click();
	}

	private void clickUser() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(User)).click();
	}

	By Dashboard = By.xpath("//div[@data-menuselector='dashboard-menu']");

	private void assertDashboard() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(Dashboard)).getText();
		Assert.assertEquals(text, "Dashboard");

	}

	public void modules() throws InterruptedException {
//		this.assertDashboard();
//		Thread.sleep(2000);
//		this.clickTeam();
		this.clickUser();

	}

	By Dots = By.xpath("//*[@id=\"fieldy-user-user-user-list_aserpttbl\"]/tbody/tr[3]/td[9]");
	By Edit = By.xpath("//*[@id=\"fieldy-user-user-user-list_aserpttbl\"]/tbody/tr[3]/td[9]/div/div[2]/ul/li[2]");
	Actions actions;

	private void mouseActionDots() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(Dots));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).perform();

	}

	private void clickEdit() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(Edit));
		actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();

	}

	private void assertionMessage() {
		wait = new WebDriverWait(driver, 10);
		String texts = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).getText();
		Assert.assertEquals(texts, "First Name");

	}

	public void editField() {
		this.assertionMessage();
		this.mouseActionDots();
		this.clickEdit();

	}

	By FirstName = By.id("first_name");
	By SaveNext = By.xpath("//button[@data-automationid='save_complete']");
	By SuccessMessage = By.xpath("//span[text()='User information updated successfully']");
	By active = By.xpath("//input[@value='active']");
	By value = By.xpath("//a[@data-exitpopup='team_user_user__all__role']");

	private void assertValue() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.elementToBeClickable(value)).getText();
		Assert.assertEquals(text, "Team / Edit User");

	}

	private void clickClearFirstName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(FirstName)).click();

	}

	private void selectName() {
		wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(FirstName));
		Actions actions = new Actions(driver);
		actions.doubleClick(until).perform();

	}

	private void clearFirstName() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName)).clear();

	}

	private void updateFirstName(String updateName) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName)).sendKeys(updateName);
	}

	private void clickSaveNext() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SaveNext)).click();
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessMessage)).getText();
		Assert.assertEquals(text, "User information updated successfully");

	}

	private void visibleName() {
		wait = new WebDriverWait(driver, 10);
		String attribute = wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName)).getAttribute("value");
		wait.until(ExpectedConditions.textToBePresentInElementValue(FirstName, attribute));

	}

	By name = By.xpath("//td[text()='First Name']");

	private void nameValidation() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(name)).getText();
		Assert.assertEquals(text, "First Name");

	}

	public void updateField(String updateName) throws InterruptedException {
		Thread.sleep(2000);
//		 this.visibleName();
//		this.assertValue();
//		this.clickClearFirstName();
//		this.selectName();
		this.clearFirstName();
		this.updateFirstName(updateName);
		this.clickSaveNext();
		this.nameValidation();

	}

	By Delete = By.xpath("//*[@id=\"fieldy-user-user-user-list_aserpttbl\"]/tbody/tr[3]/td[9]/div/div[2]/ul/li[3]");
	By Yes = By.xpath("//button[text()='Yes']");
	By DeleteMessage = By.xpath("//span[text()='User have been deleted successfully']");

	private void clickDelete() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Delete)).click();

	}

	private void clickYes() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Yes)).click();

	}

	By firstName = By.xpath("//td[text()='First Name']");

	private void verifyDeleteMessage() {
		wait = new WebDriverWait(driver, 10);
		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(DeleteMessage)).getText();
		Assert.assertEquals(text, "User have been deleted successfully");
		String texts = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).getText();
		Assert.assertEquals(texts, "First Name");
	}

	public void deleteField() throws InterruptedException {
		this.mouseActionDots();
		this.clickDelete();
		this.clickYes();
		//Thread.sleep(1000);
		this.verifyDeleteMessage();

	}

}
