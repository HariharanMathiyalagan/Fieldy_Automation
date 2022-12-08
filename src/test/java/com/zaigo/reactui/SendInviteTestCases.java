package com.zaigo.reactui;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.SendInvitePage;
import com.zaigo.utility.BrowserSetup;

public class SendInviteTestCases {

	private WebDriver driver = null;
	private LoginPage loginInPage = null;

	@BeforeClass
	public void setup() {
		this.driver = BrowserSetup.startBrowser();
	}

	@AfterClass
	public void exitBrowser() {
		driver.quit();
	}

	@AfterMethod
	public void setVariableEmpty() {
		loginInPage = null;
	}

	@Test(priority = 1)
	public void verifyUserTab() throws IOException {
		// Verify the User Tab

		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("fieldy@zaiportal.com", "Zaiserve@123");
		loginInPage.clickLoginButton();
		SendInvitePage invitePage = new SendInvitePage(this.driver);
		String text = invitePage.getUserTab();
		Assert.assertEquals(text, "Team");

	}

	@Test(priority = 2)
	public void verifySendInvitePopup() throws InterruptedException {
		// Verify the Send invite popup

		SendInvitePage invitePage = new SendInvitePage(this.driver);

		Thread.sleep(5000);
		invitePage.clickUserTab();
		invitePage.clickUserSubMenu();
		invitePage.clickSendInvite();
		String text = invitePage.popupText();
		Assert.assertEquals(text, "Send Invite");
//		 invitePage.clickClose();
//		 invitePage.clickYes();
	}

	@Test(priority = 3)
	public void verifyEmailRequired() throws InterruptedException {
		// Verify the Email format

		SendInvitePage invitePage = new SendInvitePage(this.driver);
		// invitePage.clickSendInvite();
		Thread.sleep(1000);
		invitePage.clickInvite();
		String text = invitePage.getEmailErrorText();
		Assert.assertEquals(text, "Required Field");
//		 invitePage.clickClose();
//		 invitePage.clickYes();
	}

//	
//	
//	
//	
//	
	@Test(priority = 4)
	public void verifyEmailFormat() throws InterruptedException {
		// Verify the Email format

		SendInvitePage invitePage = new SendInvitePage(this.driver);
		driver.navigate().refresh();
		invitePage.enterEmail("asdf");
		Thread.sleep(1000);
		invitePage.clickInvite();
		String text = invitePage.getEmailErrorText();
		Assert.assertEquals(text, "Enter a valid Email");
//		 invitePage.clickClose();
//		 inverfiyvitePage.clickYes();
	}

//	
//	
	@Test(priority = 5)
	public void verifyFirtName() throws InterruptedException {
		// Verify the Email format

		SendInvitePage invitePage = new SendInvitePage(this.driver);
		invitePage.clickInvite();
		invitePage.clickNext();
		String text = invitePage.firstNameError();
		Assert.assertEquals(text, "Required Field");
//		 invitePage.clickClose();
//		 invitePage.clickYes();
	}

//	
//	
//	
	@Test(priority = 6)
	public void verifyFirtNameWithBlankSpace() throws InterruptedException {
		// Verify the Email format

		SendInvitePage invitePage = new SendInvitePage(this.driver);
		// invitePage.clickInvite();
		driver.navigate().refresh();
//		 invitePage.clickInvite();
		invitePage.enterFirstName("  ");
		Thread.sleep(2000);
		invitePage.clickInvite();
		String text = invitePage.firstNameError();
		Assert.assertEquals(text, "Required Field");

	}

//	
//	
//	
	@Test(priority = 7)
	public void verifyUserEnterMessage() throws InterruptedException {
		// Verify user can enter the message

		SendInvitePage invitePage = new SendInvitePage(this.driver);
		invitePage.clickInvite();
		invitePage.enterFirstName("Firstname");
		invitePage.enterEmail("email@gmail.com");
		Thread.sleep(2000);
		invitePage.dndTypes();
		invitePage.clickNext();
		String text = invitePage.getMessageText();
		Assert.assertEquals(text, "Message");
//		 invitePage.clickClose();
//		 invitePage.clickYes();
	}

//	
	@Test(priority = 8)
	public void verifyAddMoreButton() throws InterruptedException {
		// Verify add more button is present

		SendInvitePage invitePage = new SendInvitePage(this.driver);
		invitePage.clickPrevious();
		String text = invitePage.getAddMoreText();
		Assert.assertEquals(text, "Add Users");
//		 invitePage.clickClose();
//		 invitePage.clickYes();
	}

//	
//	
	@Test(priority = 9)
	public void verifyUserSendInvite() throws InterruptedException {
		// Verify user can send invite without message.

		SendInvitePage invitePage = new SendInvitePage(this.driver);
//		 invitePage.clickSendInvite();
		driver.navigate().refresh();
		invitePage.enterFirstName("Hariharan");
		String r = RandomStringUtils.randomNumeric(3);
		invitePage.enterEmail("hariharan.m" + r + "@zaigoinfotech.com");
		Thread.sleep(2000);
		invitePage.dndTypes();
		invitePage.clickSubmit();
		
		String text = invitePage.getSuccessMessages();
		Assert.assertEquals(text, "Invitation sent successfully to 1 user(s)");
		
		
//		 invitePage.clickClose();
//		 invitePage.clickYes();
	}
//	
//	
//	@Test
//	(priority=10)
//	public void verifyUserSendInviteWithMessage() throws InterruptedException 
//	String r = RandomStringUtils.randomNumeric(3);
		
//		//Verify user can sendinvite with message
//		
//		 SendInvitePage invitePage = new SendInvitePage(this.driver);
//		 invitePage.clickSendInvite();
//		 invitePage.enterFirstName("Firstname");
//		 invitePage.enterEmail("email@gmail.com");
//		 invitePage.clickNext();
//		 invitePage.enterMessage("message");
//		 invitePage.clickSubmit();
//		 String text= invitePage.getSuccessMessages();
//		 Assert.assertEquals(text, "Message");
//		 invitePage.clickClose();
//		 invitePage.clickYes();
//	}

}
