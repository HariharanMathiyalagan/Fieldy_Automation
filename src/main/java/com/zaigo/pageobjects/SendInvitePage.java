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
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

public class SendInvitePage extends BaseClass {

	private WebDriver driver;
	private WebDriverWait wait;

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

	private By usermenu = By.id("team-menu");

	// private By usermenu=By.xpath("//a[@data-automationid='user']");

	private By usersubmenu = By.id("team-user-menu");

	private By userinvitebtn = By.xpath("//button[@data-n-linkto='team_user_user_sendinvite']");

	private By sendbtn = By.xpath("//button[@data-automationid='send-invite']");

	// fn
	@FindAll({ @FindBy(xpath = "//*[text()='First Name']"), @FindBy(xpath = "//*[text()='No Result Found']") })
	WebElement PageLand;
	private By firstname = By.id("send_invite__first_name__0");
	By ListLabel = By.xpath("//*[@class='page-header-left back-btn']");

	private By firstnameerr = By.id("send_invite__first_name__0_error");

	// ln

	private By lastname = By.id("send_invite__last_name__0");
	private By lastnameerr = By.id("send_invite__last_name__0_error");
	// type

//			private By type=By.xpath("//button[@data-automationid='usertype']");

	// email

	private By email = By.id("send_invite__email__0");

	private By emailerr = By.id("send_invite__email__0_error");

	private By next = By.xpath("//span[@class='right-icon']");
	By messageBox = By.id("message");
	private By mesage = By.xpath("//label[text()='Message']");

	private By messageerr = By.id("message_error");

	private By popuptext = By.xpath("//button[@data-automationid='send-invite']");

	private By closepopup = By.xpath("//img[@data-exitpopup='team_user_user']");

	private By clickyes = By.xpath("//button[@data-modalconfiratmionpopup='exit-yes']");

	private By dndtype = By.xpath("//input[@data-dropdownlist='user-invite']");
	private By dndAdmin = By.xpath("//div[text()='Admin']");
	private By typeerr = By.id("send_invite__user_type__0_error");

	private By clickAdd = By.xpath("//*[@data-automationid='add-more']");

	private By previous = By.xpath("//*[text()='Previous']");

	private By getSuccessmessage = By.xpath("//*[text()='Invitation sent successfully to 1 user(s)']");

	private By clickSaveandComplete = By.xpath("//button[@data-automationid='send-invite']");
	By Message = By.xpath("//*[@class='js-snackbar__message']");
	By Cancel = By.xpath("//*[@class='js-snackbar__close bold']");
	By Label = By.xpath("//a[@data-exitpopup='team_user_user__all__role']");
	By Team = By.id("team-menu");
	By Tittle = By.xpath("//*[@id='team-company-details-company-name']//*[@class='company']");
	By User = By.id("team-user-menu");

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

	private void invisible(By element) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
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

	private void visibility(By element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();

	}

	private void visibility(WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

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

	private void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}

	public String errorField(String value) {
		if (value.equals("FirstName")) {
			String text = this.getText(firstnameerr);
			return text;
		} else if (value.equals("LastName")) {
			String text = this.getText(lastnameerr);
			return text;
		} else if (value.equals("Email")) {
			String text = this.getText(emailerr);
			return text;
		} else if (value.equals("UserType")) {
			String text = this.getText(typeerr);
			return text;
		} else if (value.equals("Message")) {
			String text = this.getText(messageerr);
			return text;
		}
		return value;
	}

	public String clearField(String value) {
		if (value.equals("FirstName")) {
			this.clearField(firstname);
		} else if (value.equals("LastName")) {
			this.clearField(lastname);
		} else if (value.equals("Email")) {
			this.clearField(email);
		} else if (value.equals("Message")) {
			this.clearField(messageBox);
		}
		return value;
	}

	public void firstName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(firstname, characters256);
		}
	}

	public void lastName(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(lastname, characters256);
		}
	}

	public void email(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(email, characters256);
		} else if (value.equals("InValid")) {
			this.validationTab(email, "dsfhhdsf");
		} else if (value.equals("UniqueContractor")) {
			this.validationTab(email, "fieldy@mailinator.com");
		}
	}

	public void message(String value) {
		if (value.equals("MaxValidation")) {
			this.validationTab(messageBox, characters2048);
		}
	}

	static String message;

	public String responseMessage(String value) throws IOException, InterruptedException {
		Boolean conditionCheck = true;
		if (value.equals("Message")) {
			if (this.conditionChecking(Message)) {
				message = this.getText(Message);
				this.invisible(Message);
			} else {
				do {
					this.mouseActionClick(previous);
					this.clearField("Email");
					String fakeEmail = faker.internet().safeEmailAddress();
					this.inputText(email, fakeEmail);
					this.mouseActionClick(next);
					this.mouseActionClick(sendbtn);
					if (this.conditionChecking(Message)) {
						this.responseMessage("Message");
						if (message.equals(getPropertyValue("CustomerCreatedMessage"))) {
							conditionCheck = false;
						}
					}
				} while (conditionCheck);
			}
		}
		return message;
	}

	public String clickEvent(String value) {
		if (value.equals("SendInvite")) {
			this.mouseActionClick(sendbtn);
			if (this.conditionChecking(firstnameerr)) {

			} else {
				do {
					this.mouseActionClick(sendbtn);
				} while (!this.conditionChecking(firstnameerr));
			}
		} else if (value.equals("Next")) {
			this.mouseActionClick(next);
		} else if (value.equals("Navigate")) {
			this.mouseActionClick(Team);
			this.visibility(Tittle);
			this.mouseActionClick(User);
			this.visibility(PageLand);
			this.mouseActionClick(userinvitebtn);
		} else if (value.equals("ButtonPresent")) {
			String text = this.getText(sendbtn);
			return text;
		} else if (value.equals("ClickButton")) {
			this.mouseActionClick(sendbtn);
		}
		return value;
	}

	public String labelValidation(String value) {
		if (value.equals("FormLabel")) {
			String label = this.getText(Label);
			return label;
		} else if (value.equals("ListLabel")) {
			String label = this.getText(ListLabel);
			return label;
		}
		return value;
	}

	public void validFillData(String value) throws IOException {
		if (value.equals("UserDetails")) {
			this.inputText(firstname, fakeFirstName);
			this.inputText(lastname, fakeLastName);
			this.inputText(email, fakeEmail);
			this.mouseActionClick(dndtype);
			this.mouseActionClick(dndAdmin);
		} else if (value.equals("Message")) {
			this.inputText(messageBox, getPropertyValue("Notes"));
		}

	}

}
