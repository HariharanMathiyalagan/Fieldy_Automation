package com.zaigo.reactui;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.SearchDeleteContractorPage;
import com.zaigo.utility.BrowserSetup;

public class SearchDeleteContractorTestCases {
	
	
	 
			
			
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
				 CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
				 String text = contractorPage.dashBoardUserMenuText();
				 Assert.assertEquals(text, "User");
			    
			}

			
			@Test
			(priority=3)
			public void verifySearchField() 
			{
				//Verify the search field is present
				
				
				SearchDeleteContractorPage search = new SearchDeleteContractorPage(this.driver);
				search.clickUser();
				search.clickContractor();
				String textone=search.getSearchField();
				Assert.assertEquals(textone, "Search Here");
	
				 
			    
			}
			
			@Test
			(priority=2)
			public void verifyContractorTabText() 
			{
				//Verify the Contractor Tab 
				
				SearchDeleteContractorPage search = new SearchDeleteContractorPage(this.driver);
				search.clickUser();
				search.clickContractor();
				String text=search.getContractorTabText();
				Assert.assertEquals(text,"Contractor");
	
				 
			    
			}
	
			
			
			
			
			
			
			
			@Test
			(priority=4)
			public void verifyInvalidaData() 
			{
				//Verify the search with invalid data
				
				
				SearchDeleteContractorPage search = new SearchDeleteContractorPage(this.driver);
				search.clickUser();
				search.clickContractor();
				search.EnterSearch("Randomtext");
				search.clickSearchButton();
				String textone=search.getNoResult();
				Assert.assertEquals(textone, "No Search results found for Randomtext");
	
			}
			
			
			
			@Test
			(priority=5)
			public void SearchDeleteContractor() throws InterruptedException 
			{
				//Verify Search the contractor and Delete
				
				
				SearchDeleteContractorPage search = new SearchDeleteContractorPage(this.driver);
				search.clickUser();
				search.clickContractor();
				search.EnterSearch("email@gmail.com");
				search.clickSearchButton();
				search.clickMenu();
				search.clickDelete();
				search.clickYesButton();
				String text = search.getSuccessMessage();
				Assert.assertEquals(text, "Deleted Successfully");
				
			
		     }
			
			
			
}
