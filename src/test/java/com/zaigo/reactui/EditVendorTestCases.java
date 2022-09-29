package com.zaigo.reactui;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zaigo.pageobjects.CreateVendorPage;
import com.zaigo.pageobjects.EditContractorPage;
import com.zaigo.pageobjects.EditVendorPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class EditVendorTestCases {

	
	
	private WebDriver driver = null;
	private LoginPage loginInPage = null;

	@BeforeClass
	public void setup() {
		this.driver = BrowserSetup.startBrowser();
	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
	}
	
	@AfterMethod
	public void setVariableEmpty() {
		loginInPage = null;
	}
	
	
	@Test
	(priority=1)
	public void verifyUserTab() 
	{
		//Verify the User Tab
		
		 LoginPage loginInPage = new LoginPage(this.driver);
	     loginInPage.setUserCredentials("sriram@zaigoinfotech.com", "Zaiserve@123");
		 loginInPage.clickLoginButton();
		 EditVendorPage vendorpage = new EditVendorPage(this.driver);
		 String text = vendorpage.dashBoardUserMenuText();
		 Assert.assertEquals(text, "User");
	    
	}
	
	@Test
	(priority=2)
	public void verifyVedorTab() 
	{
		//Verify the contractor tab 
		
		 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		 contractorPage.dashBoardUserMenu();
		 String text = contractorPage.dashBoardVendorMenuText();
		 Assert.assertEquals(text, "Contractor");
		    
	    
	}
	
	
	@Test
	(priority=3)
	public void verifyEmptyName() throws InterruptedException 
	{
		//Verify the Error Message if the Name field is Null
		
		 EditVendorPage vendorpage = new EditVendorPage(this.driver);
		 vendorpage.dashBoardUserMenu();
		 vendorpage.clickVendorTab();
		 vendorpage.clickActionMenu();
		 vendorpage.clickEditMenu();
		 vendorpage.vendorName("");
		 vendorpage.clickSaveandComplete();
		 String text =vendorpage.vendorNameError();
		 Assert.assertEquals(text, "This field is required");
		 vendorpage.clickCloseButton();
		 vendorpage.clickYesButton();
		 
		 
	    
	}
	
	
	
	@Test
	(priority=4)
	public void verifyEmptyEmail() throws InterruptedException 
	{
		//Verify the Error Message if the email field is Null
		
		 EditVendorPage vendorPage = new EditVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.clickActionMenu();
		 vendorPage.clickEditMenu();
		 vendorPage.vendorEmail("");
		 vendorPage.clickSaveandComplete();
		 String text =vendorPage.vendorEmailError();
		 Assert.assertEquals(text, "This field is required");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	
	@Test
	(priority=5)
	public void verifyEmptyContactPerson() 
	{
		//Verify the Error Message if the contact person is empty
		
		 EditVendorPage vendorPage = new EditVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.clickActionMenu();
		 vendorPage.clickEditMenu();
		 vendorPage.vendorContactPerson("");
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorContactNameError();
		 Assert.assertEquals(text, "This field is required");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	
	@Test
	(priority=6)
	public void verifyWebsiteFormat() 
	{
		//Verify Contact Person Empty spaces
		
		 EditVendorPage vendorPage = new EditVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.clickActionMenu();
		 vendorPage.clickEditMenu();
		 vendorPage.vendorWebsite("hello");
		 String text =vendorPage.vendorWebsiteError();
		 Assert.assertEquals(text,"Enter a valid website URL");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	
	/*
	
	
	

	@Test
	(priority=11)
	public void verifyEnterProducts() throws InterruptedException 
	{
		//valid case 
		
		
		
		 CreateVendorPageObject vendorPage = new CreateVendorPageObject(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorName("hello");
		 vendorPage.vendorEmail("email@gmail.com");
		 vendorPage.vendorContactPerson("cperson hello");
	     vendorPage.clickSaveandComplete();
	     
	     //Add Success alert
	     
	     
	     String text =vendorPage.vendorSuccessMessage();
		 Assert.assertEquals(text,"Company Created successfully");
		 
		 vendorPage.clickCloseButton();
		 
	}
	
	
	
	@Test
	(priority=12)
	public void verifyDuplicateEmail() throws InterruptedException 
	{
		//Verify the Error Message if the email field is Null
		
		
		 CreateVendorPageObject vendorPage = new CreateVendorPageObject(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.clickNextButton();
		 vendorPage.vendorName("sxxx");
		 vendorPage.vendorContactPerson("c name");
		 vendorPage.vendorEmail("email@gmail.com");
		 vendorPage.clickSaveandComplete();
		 String text =vendorPage.vendorEmailError();
		 Assert.assertEquals(text, "Email already Exists");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	
	*/
	
	@Test
	(priority=7)
	public void verifyLineOneMaxCharacters() throws InterruptedException 
	{
		//check the Line One has Max characters validation
		
		 EditVendorPage vendorPage = new EditVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorName("hello");
		 vendorPage.vendorEmail("email@gmail.com");
		 vendorPage.vendorContactPerson("cperson hello");
		 vendorPage.clickNextButton();
		 vendorPage.clickAddLocation();
		 vendorPage.clickLocationOne();
		 vendorPage.vendorLineOne("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu");
		 vendorPage.clickSaveandComplete();
	     String text =vendorPage.vendorLineOneError();
		 Assert.assertEquals(text,"Not allowed more than 150 characters");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
	     
		 
	}
	
	
	@Test
	(priority=8)
	public void verifyLineTwoMaxCharacters() throws InterruptedException 
	{
		//check the Line Two has Max characters validation
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorName("hello");
		 vendorPage.vendorEmail("email@gmail.com");
		 vendorPage.vendorContactPerson("cperson hello");
		 vendorPage.clickNextButton();
		 vendorPage.clickAddLocation();
		 vendorPage.clickLocationOne();
		 vendorPage.vendorLineTwo("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu");
		 vendorPage.clickSaveandComplete();
	     String text =vendorPage.vendorLineTwoError();
		 Assert.assertEquals(text,"Not allowed more than 150 characters");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	}
	
	
	
	@Test
	(priority=9)
	public void verifyCityMaxCharacters() throws InterruptedException 
	{
		//check City with Max character validations
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorName("hello");
		 vendorPage.vendorEmail("email@gmail.com");
		 vendorPage.vendorContactPerson("cperson hello");
		 vendorPage.clickNextButton();
		 vendorPage.clickAddLocation();
		 vendorPage.clickLocationOne();
		 vendorPage.vendorCity("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu");
		 vendorPage.clickSaveandComplete();
	     String text =vendorPage.vendorCityError();
		 Assert.assertEquals(text,"Not allowed more than 150 characters");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
	}
	
	
	
	
	
	
	
	@Test
	(priority=10)
	public void verifyMinZipcode() throws InterruptedException 
	{
		//check zipcode accepts the alphabets
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorName("hello");
		 vendorPage.vendorEmail("email@gmail.com");
		 vendorPage.vendorContactPerson("cperson hello");
		 vendorPage.clickNextButton();
		 vendorPage.clickAddLocation();
		 vendorPage.clickLocationOne();
		 vendorPage.vendorZipcode("12");
		 vendorPage.clickSaveandComplete();
	     String text =vendorPage.vendorZipcodeError();
		 Assert.assertEquals(text,"The field must be minimum 6");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	}
	
	
	@Test
	(priority=11)
	public void verifyMaxName() throws InterruptedException 
	{
		//Verify the max vendor name validation if Exceeds the Limit 50
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorName("Lorem ipsum dolor sit amet, consectetuer adipiscin");
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorNameError();
		 Assert.assertEquals(text, "Not allowed more than 25 characters");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
		 
	    
	}
	
	
	
	
	@Test
	(priority=12)
	public void verifyMaxContactPerson() 
	{
		//Verify the max validation if Contact person Exceeds the Limit 50
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorContactPerson("Lorem ipsum dolor sit amet, consectetuer adipiscin adipiscinadipiscinadipiscinadipiscinadipiscinadipiscinadipiscin");
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorContactNameError();
		 Assert.assertEquals(text, "Not allowed more than 50 characters");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	 }
	
	
	
	
	@Test
	(priority=13)
	public void verifyFaxMaxValidation() throws InterruptedException 
	{
		//Verify the Fax Field have the Max valdations
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorFax("asddddddcddcdcdcdc");
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorFaxError();
		 Assert.assertEquals(text, "This field is required");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}

	
	
	
	
}
