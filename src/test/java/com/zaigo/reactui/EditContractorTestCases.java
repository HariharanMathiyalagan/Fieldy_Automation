package com.zaigo.reactui;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.EditContractorPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class EditContractorTestCases {
	
	
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
		 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		 String text = contractorPage.dashBoardUserMenuText();
		 Assert.assertEquals(text, "User");
	    
	}

	
	
	@Test
	(priority=17)
	public void verifyContractorab() 
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
		
		 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		 contractorPage.dashBoardUserMenu();
		 contractorPage.clickVendorTab();
		 contractorPage.clickActionMenu();
		 contractorPage.clickEdit();
		 contractorPage.contractorName("");
		 contractorPage.clickNextButton();
		 String text =contractorPage.contractorNameError();
		 Assert.assertEquals(text, "This field is required");
		 contractorPage.clickCloseButton();
		 contractorPage.clickYesButton();
		 
		 
	    
	}
	
	
	@Test
	(priority=4)
	public void verifyEmptyEmail() throws InterruptedException 
	{
		//Verify the Error Message if the email field is Null
		
		 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		 contractorPage.dashBoardUserMenu();
		 contractorPage.clickVendorTab();
		 contractorPage.clickActionMenu();
		 contractorPage.clickEdit();
		 contractorPage.contractorEmail("");
		 contractorPage.clickNextButton();
		 String text =contractorPage.contractorEmailError();
		 Assert.assertEquals(text, "This field is required");
		 contractorPage.clickCloseButton();
		 contractorPage.clickYesButton();
		 
	    
	}
	
	
	@Test
	(priority=5)
	public void verifyEmptyContactPerson() 
	{
		//Verify the Error Message if the contact person is null
		
		 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		 contractorPage.dashBoardUserMenu();
		 contractorPage.clickVendorTab();
		 contractorPage.clickActionMenu();
		 contractorPage.clickEdit();
		 contractorPage.contractorContactPerson("");
		 contractorPage.clickNextButton();
		 String text =contractorPage.contractorContactNameError();
		 Assert.assertEquals(text, "This field is required");
		 contractorPage.clickCloseButton();
		 contractorPage.clickYesButton();
		 
	    
	}
	

	@Test
	(priority=6)
	public void verifyBlankSpaceName() throws InterruptedException 
	{
		//Verify the Error Message if the Name field is Null
		
		 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		 contractorPage.dashBoardUserMenu();
		 contractorPage.clickVendorTab();
		 contractorPage.clickActionMenu();
		 contractorPage.clickEdit();
		 contractorPage.contractorName("  ");
		 String text =contractorPage.contractorNameError();
		 Assert.assertEquals(text, "Blank space not allowed");
		 contractorPage.clickCloseButton();
		 contractorPage.clickYesButton();
		 
		 
	    
	}
	
	
	@Test
	(priority=7)
	public void verifyBlankSpaceEmail() throws InterruptedException 
	{
		//Verify the Error Message if the email field is Null
		
		 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		 contractorPage.dashBoardUserMenu();
		 contractorPage.clickVendorTab();
		 contractorPage.clickActionMenu();
		 contractorPage.clickEdit();
		 contractorPage.contractorEmail("    ");
		 String text =contractorPage.contractorEmailError();
		 Assert.assertEquals(text, "Enter a valid email address");
		 contractorPage.clickCloseButton();
		 contractorPage.clickYesButton();
		 
	    
	}
	
	
	@Test
	(priority=8)
	public void verifyBlankSpaceContactPerson() 
	{
		//Verify the Error Message if the contact person is null
		
		 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		 contractorPage.dashBoardUserMenu();
		 contractorPage.clickVendorTab();
		 contractorPage.clickActionMenu();
		 contractorPage.clickEdit();
		 contractorPage.contractorContactPerson("    ");
		 String text =contractorPage.contractorContactNameError();
		 Assert.assertEquals(text, "Blank space not allowed");
		 contractorPage.clickCloseButton();
		 contractorPage.clickYesButton();
		 
	    
	}
	
//	@Test
//	(priority=10)
//	public void verifyWebsiteFormat() 
//	{
//		//Verify Contact Person Empty spaces
//		
//		EditContractorPage contractorPage = new EditContractorPage(this.driver);
//		contractorPage.dashBoardUserMenu();
//		contractorPage.clickVendorTab();
//		contractorPage.clickActionMenu();
//		contractorPage.clickEdit();
//		contractorPage.contractorWebsite("asddf");
//		String text =contractorPage.contractorWebsiteError();
//		Assert.assertEquals(text,"Enter a valid website URL");
//		contractorPage.clickCloseButton();
//		contractorPage.clickYesButton();
//		 
//	    
//	}
	
	
	
	
	
	@Test
	(priority=9)
	public void verifyMaxPhone() 
	{
		//Verify Phone Field accepts 20 digits
		
		EditContractorPage contractorPage = new EditContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.clickActionMenu();
		contractorPage.clickEdit();
		contractorPage.contractorPhone("123123123123222");
		String text = contractorPage.contractorPhoneError();
		Assert.assertEquals(text,"Input should contains 10 digits");
		contractorPage.clickCloseButton();
		contractorPage.clickYesButton();
		 
	    
	}
	
	
	@Test
	(priority=11)
	public void verifyLineOneMaxCharacters() throws InterruptedException 
	{
		//
		//check the Line One has Max characters validation
		
		EditContractorPage contractorPage = new EditContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.clickActionMenu();
		contractorPage.clickEdit();
		contractorPage.contractorName("edit name");
		contractorPage.contractorEmail("email@gmail.com");
		contractorPage.contractorContactPerson("hello");
		contractorPage.clickNextButton();
		contractorPage.clickAddLocation();
		contractorPage.clickLocationOne();
		contractorPage.contractorLineOne("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu");	
	    String text =contractorPage.contractorLineOneError();
		Assert.assertEquals(text,"Not allowed more than 150 characters");
		contractorPage.clickCloseButton();
		contractorPage.clickYesButton();
	     
		 
	}
	
	
	@Test
	(priority=12)
	public void verifyLineTwoMaxCharacters() throws InterruptedException 
	{
		
		//check the Line Two has Max characters validation
		EditContractorPage contractorPage = new EditContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.clickActionMenu();
		contractorPage.clickEdit();
		contractorPage.contractorName("edit name");
		contractorPage.contractorEmail("email@gmail.com");
		contractorPage.contractorContactPerson("hello");
		contractorPage.clickNextButton();
		contractorPage.clickAddLocation();
		contractorPage.clickLocationOne();
		contractorPage.contractorLineTwo("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu");
		contractorPage.clickSaveandComplete();
	    String text =contractorPage.contractorLineTwoError();
		Assert.assertEquals(text,"Not allowed more than 150 characters");
		contractorPage.clickCloseButton();
		contractorPage.clickYesButton();
	}
	
	
	
	
	@Test
	(priority=13)
	public void verifyCityMaxCharacters() throws InterruptedException 
	{
		
		//check City with Max character validations
		
		EditContractorPage contractorPage = new EditContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.clickActionMenu();
		contractorPage.clickEdit();
		contractorPage.contractorName("edit name");
		contractorPage.contractorEmail("email@gmail.com");
		contractorPage.contractorContactPerson("hello");
		contractorPage.clickNextButton();
		contractorPage.clickAddLocation();
		contractorPage.clickLocationOne();
		contractorPage.contractorCity("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu");
		contractorPage.clickSaveandComplete();
	    String text =contractorPage.contractorCityError();
		Assert.assertEquals(text,"Not allowed more than 150 characters");
		contractorPage.clickCloseButton();
		contractorPage.clickYesButton();
	}
	
	
	
	
	
	
	
	
	@Test
	(priority=14)
	public void verifyMinZipcode() throws InterruptedException 
	{
		//
		//check zipcode accepts the 3 digits
		
		EditContractorPage contractorPage = new EditContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.clickActionMenu();
		contractorPage.clickEdit();
		contractorPage.contractorName("edit name");
		contractorPage.contractorEmail("email@gmail.com");
		contractorPage.contractorContactPerson("hello");
		contractorPage.clickNextButton();
		contractorPage.clickAddLocation();
		contractorPage.clickLocationOne();
		contractorPage.contractorZipcode("123");
		contractorPage.clickSaveandComplete();
	    String text =contractorPage.contractorZipcodeError();
		Assert.assertEquals(text,"The field must be minimum 6");
		contractorPage.clickCloseButton();
		contractorPage.clickYesButton();
		 
	}
	
	
	
	@Test
	(priority=15)
	public void verifyWithValidDetails() throws InterruptedException 
	{
		//
		//check zipcode WITH 30 + CHARCAERS
		
		EditContractorPage contractorPage = new EditContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.clickActionMenu();
		contractorPage.clickEdit();
		contractorPage.contractorName("edit name");
		contractorPage.contractorEmail("email@gmail.com");
		contractorPage.contractorContactPerson("hello");
		contractorPage.clickNextButton();
		contractorPage.clickAddLocation();
		contractorPage.clickLocationOne();
		contractorPage.contractorZipcode("123123456123123456123123456123123456123123456123123456");
	    String text =contractorPage.contractorZipcodeError1();
		Assert.assertEquals(text,"Not allowed more than 30 characters");
		contractorPage.clickCloseButton();
		contractorPage.clickYesButton();
		 
	}
	

	
	/*
	@Test
	(priority=16)
	public void verifyWithvalidImage() throws InterruptedException 
	
	{
		
		//check with valid Image file
		
		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.contractorCreateButton();
		contractorPage.uploadImage("C:\\Users\\lenovo\\Pictures\\picjpg.jpg");
		contractorPage.clickCloseButton();
		contractorPage.clickYesButton();
	 
	     
	 }
	
	
	*/
	

 @Test
(priority=2)
   public void verifyContractorUpdated() throws InterruptedException 
{
	//Contractor Created successfully
	
	 EditContractorPage contractorPage = new EditContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.clickActionMenu();
		contractorPage.clickEdit();
		contractorPage.contractorName("edit name");
		contractorPage.contractorEmail("emailyh@gmail.com");
		contractorPage.contractorContactPerson("hello");
	//contractorPage.uploadImage("C:\\Users\\lenovo\\Pictures\\picjpg.jpg");
	contractorPage.clickSaveandComplete();
	   Thread.sleep(2000);
    String text =contractorPage.contractorSuccessMessage();
	Assert.assertEquals(text,"Company Updated successfully");
	contractorPage.clickCloseButton();
	
}
	/*
      @Test
	(priority=18)
	   public void verifyContractorDeleted() throws InterruptedException 
	{
		//Contractor Deleted successfully
		
    	EditContractorPage contractorPage = new EditContractorPage(this.driver);
		contractorPage.dashBoardUserMenu();
		contractorPage.clickVendorTab();
		contractorPage.clickActionMenu();
		contractorPage.clickDeleteMenu();
		contractorPage.clickConfirmDelete();
		String text =contractorPage.contractorSuccessMessage();
		Assert.assertEquals(text,"Deleted Successfully");
	     
		 
	}
	*/

}
