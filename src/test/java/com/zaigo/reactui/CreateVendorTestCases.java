package com.zaigo.reactui;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zaigo.pageobjects.CreateVendorPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;


public class CreateVendorTestCases {
	
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
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 String text = vendorPage.dashBoardUserMenuText();
		 Assert.assertEquals(text, "User");
		    
	    
	}
	
	

	
	@Test
	(priority=11)
	public void verifyVendorTab() 
	{
		//Verify the Vendor Tab
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 String text = vendorPage.dashBoardVendorMenuText();
		 Assert.assertEquals(text, "Vendor");
		    
	    
	}
	
	
	
	@Test
	(priority=3)
	public void verifyCreateButton() throws InterruptedException 
	{
		//Verify the CreateButton and Enter the Name
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 String text = vendorPage.getCreateButtonValue();
		 System.out.println(text);
		 Assert.assertEquals(text, "Create Vendor");
		 

	    
	}
	
	
	@Test
	(priority=4)
	public void verifyEmptyName() throws InterruptedException 
	{
		//Verify the Error Message if the Name field is Null
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorNameError();
		 Assert.assertEquals(text, "This field is required");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
		 
	    
	}
	
	
	
	@Test
	(priority=4)
	public void verifyEmptyEmail() throws InterruptedException 
	{
		//Verify the Error Message if the email field is Null
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorEmailError();
		 Assert.assertEquals(text, "This field is required");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	
	@Test
	(priority=6)
	public void verifyEmptyContactPerson() 
	{
		//Verify the Error Message if the email field is Null
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorContactNameError();
		 Assert.assertEquals(text, "This field is required");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	@Test
	(priority=7)
	public void verifyNameEmptySpaces() 
	{
		//Verify the blankspace not allowed error message
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorName("   ");
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorNameError();
		 Assert.assertEquals(text, "Blank space not allowed");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	@Test
	(priority=8)
	public void verifyEmailEmptySpaces() 
	{
		//Verify Email Empty spaces
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorEmail("    ");
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorEmailError();
		 Assert.assertEquals(text, "This field is required");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
    @Test
	(priority=9)
	public void verifyCnameEmptySpaces() 
	{
		//Verify Contact Person Empty spaces
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorContactPerson("    ");
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorContactNameError();
		 Assert.assertEquals(text,"Blank space not allowed");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	@Test
	(priority=10)
	public void verifyWebsiteFormat() 
	{
		//Verify Contact Person Empty spaces
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorWebsite("hello");
		 vendorPage.clickNextButton();
		 String text =vendorPage.vendorWebsiteError();
		 Assert.assertEquals(text,"Enter a valid website URL");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
		 
	    
	}
	
	
	
	
	
	

	@Test
	(priority=2)
	public void verifySuccessMessage() throws InterruptedException 
	{
		//valid case 
		
		
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.vendorName("hello");
		 vendorPage.vendorEmail("email12@gmail.com");
		 vendorPage.vendorContactPerson("cperson hello");
	     vendorPage.clickSaveandComplete();
	     
	     //check thee success message alert
	     
	     
	     String text =vendorPage.vendorSuccessMessage();
		 Assert.assertEquals(text,"Company Created successfully");
		 
		 vendorPage.clickCloseButton();
		 
	}
	
	/*
	
	@Test
	(priority=12)
	public void verifyDuplicateEmail() throws InterruptedException 
	{
		//Verify the Error Message if the email field is Null
		
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
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
	(priority=13)
	public void verifyLineOneMaxCharacters() throws InterruptedException 
	{
		//check the Line One has Max characters validation
		
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
		 vendorPage.vendorLineOne("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu");
		 vendorPage.clickSaveandComplete();
	     String text =vendorPage.vendorLineOneError();
		 Assert.assertEquals(text,"Not allowed more than 150 characters");
		 vendorPage.clickCloseButton();
		 vendorPage.clickYesButton();
	     
		 
	}
	
	
	@Test
	(priority=14)
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
	(priority=15)
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
	(priority=16)
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
	(priority=17)
	public void verifyWithvalidImage() throws InterruptedException 
	{
		//check with valid Image file
		
		 CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		 vendorPage.dashBoardUserMenu();
		 vendorPage.clickVendorTab();
		 vendorPage.vendorCreateButton();
		 vendorPage.uploadImage("C:\\Users\\lenovo\\Pictures\\picjpg.jpg");
		try
		{
		String textd =vendorPage.vendorImageError();
		}catch (Exception e) {
			// TODO: handle exception
			String hello=e.getMessage();
			System.out.print("Pass");
		}
		
		driver.get(driver.getCurrentUrl());
	
	     
	 }
	
	

	
//	@Test
//	(priority=21)
//	public void verifyMaxName() throws InterruptedException 
//	{
//		//Verify the max vendor name validation if Exceeds the Limit 50
//		
//		 CreateVendorPageObject vendorPage = new CreateVendorPageObject(this.driver);
//		 vendorPage.dashBoardUserMenu();
//		 vendorPage.clickVendorTab();
//		 vendorPage.vendorCreateButton();
//		 vendorPage.vendorName("Lorem ipsum dolor sit amet, consectetuer adipiscin");
//		 vendorPage.clickNextButton();
//		 String text =vendorPage.vendorNameError();
//		 Assert.assertEquals(text, "Name Should not have More than 50 characters");
//		 vendorPage.clickCloseButton();
//		 vendorPage.clickYesButton();
//		 
//		 
//	    
//	}
//	
//	
//	
//	
//	@Test
//	(priority=23)
//	public void verifyMaxContactPerson() 
//	{
//		//Verify the max validation if Contact person Exceeds the Limit 50
//		
//		 CreateVendorPageObject vendorPage = new CreateVendorPageObject(this.driver);
//		 vendorPage.dashBoardUserMenu();
//		 vendorPage.clickVendorTab();
//		 vendorPage.vendorCreateButton();
//		 vendorPage.vendorContactPerson("Lorem ipsum dolor sit amet, consectetuer adipiscin");
//		 vendorPage.clickNextButton();
//		 String text =vendorPage.vendorContactNameError();
//		 Assert.assertEquals(text, "Contact Person Should not have More than 50 characters");
//		 vendorPage.clickCloseButton();
//		 vendorPage.clickYesButton();
//		 
//	 }
//	
//	
//	
//	
//	@Test
//	(priority=25)
//	public void verifyFaxMaxValidation() throws InterruptedException 
//	{
//		//Verify the Fax Field have the Max valdations
//		
//		 CreateVendorPageObject vendorPage = new CreateVendorPageObject(this.driver);
//		 vendorPage.dashBoardUserMenu();
//		 vendorPage.clickVendorTab();
//		 vendorPage.vendorCreateButton();
//		 vendorPage.vendorFax("asddddddcddcdcdcdc");
//		 vendorPage.clickNextButton();
//		 String text =vendorPage.vendorFaxError();
//		 Assert.assertEquals(text, "This field is required");
//		 vendorPage.clickCloseButton();
//		 vendorPage.clickYesButton();
//		 
//	    
//	}

	/*

	
	@Test
	(priority=20)
	   public void verifyVendorDeleted() throws InterruptedException 
	{
		//Vendor Deleted successfully
		
		CreateVendorPage vendorPage = new CreateVendorPage(this.driver);
		vendorPage.dashBoardUserMenu();
		vendorPage.clickVendorTab();
		vendorPage.clickActionMenu();
		vendorPage.clickDeleteMenu();
		String text =vendorPage.vendorSuccessMessage();
		Assert.assertEquals(text,"Deleted Successfully");
	    
		 
	}
	

  */

//	@Test
//	(priority=18)
//	   public void verifySearch() throws InterruptedException 
//	{
//		//Vendor Search Listed sucessfuly
//		
//		
//		CreateVendorPageObject vendorPage = new CreateVendorPageObject(this.driver);
//		vendorPage.dashBoardUserMenu();
//		vendorPage.clickVendorTab();
//		Thread.sleep(3000);
//		vendorPage.enterSearch("email@gmail.com");
//		vendorPage.clickEnterSearch(Keys.ENTER);
//		String text =vendorPage.searchResult();
//		Assert.assertEquals(text,"email@gmail.com");
//		driver.get(driver.getCurrentUrl());
//	     
//		 
//	}
	
	
//	@Test
//	(priority=19)
//	   public void verifyDetailPage() throws InterruptedException 
//	{
//		//Vendor detail view
//		
//		
//		CreateVendorPageObject vendorPage = new CreateVendorPageObject(this.driver);
//		vendorPage.dashBoardUserMenu();
//		vendorPage.clickVendorTab();
//		vendorPage.clickName();
//		Thread.sleep(2000);
//		String text =vendorPage.detailView();
//		Assert.assertEquals(text,"hello");
//		vendorPage.clickDetailClose();
//		 
//	}

	
	
	

}
