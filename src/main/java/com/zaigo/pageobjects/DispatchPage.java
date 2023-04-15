package com.zaigo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BaseClass;

public class DispatchPage extends BaseClass {
	WebDriverWait wait;
	WebDriver driver;

	public DispatchPage(WebDriver driver) {
		this.driver = driver;
	}

	private void dragandDrop(By elementFrom, By elementTo) {
		wait = new WebDriverWait(driver, 10);
		WebElement From = wait.until(ExpectedConditions.visibilityOfElementLocated(elementFrom));
		WebElement To = wait.until(ExpectedConditions.visibilityOfElementLocated(elementTo));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(From, To).build().perform();

	}

	public String getText(By element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
		return until;
	}

	public String getText(WebElement element) {
		wait = new WebDriverWait(driver, 30);
		String until = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return until;
	}

	public void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isEnabled();
	}

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	public void mouseActionClick(By element) {
		wait = new WebDriverWait(driver, 100);
		WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(until).click().build().perform();
	}

	By Unassigned = By.xpath("//*[@id='external-events-listing']//div[1]//div[1]//div[1]//div[2]//div[1]");

	By DragFrom = By.xpath("//*[@id='external-events-listing']/div[1]/div[1]/div[1]/div[1]");

	By DragTo = By.xpath("//*[@id='calendar']/div[2]/div/table/tbody/tr/td[3]/div/div/div/div[1]/table/tbody/tr/td[1]");

	By Message = By.xpath("//*[@class='js-snackbar__message']");

	By Dispatch = By.id("dispatch-menu");

	By Next = By.xpath("//*[@id='calendar']/div[1]/div[1]/div/button[2]");

	By Label = By.xpath("//*[@id='fieldy-body-ele']/div[1]/div[1]/header/div/div[1]/div");

	@FindAll({ @FindBy(xpath = "//*[@id='fieldy-main-request-all-list_aserpttbl']/tbody/tr[2]/td[1]/span/a"),
			@FindBy(xpath = "//*[@id='fieldy-main-job-all-list_aserpttbl']/tbody/tr[2]/td[1]/span/a") })
	WebElement ListPage;

	By UnAssignButton = By.xpath("//*[@id='dispatch-container']/div/div/div/div[3]/div[1]/span");

	By DispatchButton = By.xpath("//*[@id='dispatch-container']/div/div/div/div[3]/div[2]/span");

	By CancelButton = By.xpath("//*[@id='dispatch-container']/div/div/div/div[3]/div[3]/span");

	By Yes = By.xpath("//*[text()='Yes']");

	By CalendarData = By.xpath("//*[@id='calendar']//*[contains(text(),'" + customerName + "')]");

	By Job = By.id("job-menu");

	By Invalid = By.xpath("//*[@id='external-events-listing']/div/div");

	static String customerName;

	public String modulePage() {
		customerName = this.getText(ListPage);
		this.mouseActionClick(Dispatch);
		this.visibility(Invalid);
		String text = this.getText(Label);
		return text;
	}

	public void assignAppoinment(String value) throws InterruptedException {
		if (value.equals("Assign") || value.equals("Assigned")) {
			if (value.equals("Assign")) {
				this.mouseActionClick(Next);
			}
			if (this.getText(Unassigned).equals("Unassigned")) {
				this.dragandDrop(DragFrom, DragTo);
			} else {
				Thread.sleep(5000);
				this.dragandDrop(DragFrom, DragTo);
			}

		}
	}

	public String responseMessage() {
		String text = this.getText(Message);
		this.invisible(Message);
		return text;
	}

	public void tiggerFunction(String value) {
		this.mouseActionClick(CalendarData);
		if (value.equals("Unassign")) {
			this.mouseActionClick(UnAssignButton);
		} else if (value.equals("Dispatch")) {
			this.mouseActionClick(DispatchButton);
		} else if (value.equals("Cancel")) {
			this.mouseActionClick(CancelButton);
		}
		this.mouseActionClick(Yes);
	}

}
