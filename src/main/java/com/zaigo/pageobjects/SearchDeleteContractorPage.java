package com.zaigo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchDeleteContractorPage {
	
	private WebDriver driver;
	private WebDriverWait wait;

	
	
    private By usermenu=By.xpath("//a[@data-automationid='user']");
	
	
//	private By contractorrmenu=By.xpath("//a[@data-automationid='vendor']");
	
	private By contractortab=By.xpath("//a[@data-automationid='contractor']");
	private By searchfield = By.xpath("//input[@data-automationid='search']");
	private By searchbutton = By.xpath("//*[@id=\'root\']/div/div/div[3]/div[1]/div[1]/div/div/div/div/button[2]");
	private By actionbutton = By.xpath("//button[@data-automationid='activeContractor']");
	private By delete = By.xpath("//li[@data-automationid='Delete Contractor']");
	private By successmessage = By.xpath("//h3[@data-automationid='sucmessage']");
	private By yesbtn = By.xpath("//button[@data-automationid='yesBtn']");
    private By nores = By.xpath("//td[contains(text(),'No Search results found')]");
		
    
    public String getContractorTabText() 
    {
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractortab)));
		return driver.findElement(contractortab).getText();
	}
    
    
    
    
    
    public String getNoResult() 
    {
		wait.until(ExpectedConditions.visibilityOfElementLocated((nores)));
		return driver.findElement(nores).getText();
	}
    
    
	
	
	public void clickSearchButton() 
	 {
			wait.until(ExpectedConditions.visibilityOfElementLocated((searchbutton)));
			driver.findElement(searchbutton).click();
     }
	
	public String getSearchField() 
	 {
			wait.until(ExpectedConditions.visibilityOfElementLocated((searchfield)));
			return driver.findElement(searchfield).getAttribute("placeholder");
     }
	
	
	
	
	 public void clickUser() 
	 {
			wait.until(ExpectedConditions.visibilityOfElementLocated((usermenu)));
			driver.findElement(usermenu).click();
     }
	   
	 public void clickYesButton() 
	 {
			wait.until(ExpectedConditions.visibilityOfElementLocated((yesbtn)));
			driver.findElement(yesbtn).click();
     }
	 
	 
	 
	 public void clickMenu() 
	 {
			wait.until(ExpectedConditions.visibilityOfElementLocated((actionbutton)));
			driver.findElement(actionbutton).click();
     }
	   
	 public void clickDelete() 
	 {
			wait.until(ExpectedConditions.visibilityOfElementLocated((delete)));
			driver.findElement(delete).click();
     }
	 
	    
	 public void EnterSearch(String services) 
	    {
			wait.until(ExpectedConditions.visibilityOfElementLocated((searchfield)));
			driver.findElement(searchfield).sendKeys(services);
		}
	    
	 public void clickContractor() 
	    {
			wait.until(ExpectedConditions.visibilityOfElementLocated((contractortab)));
			driver.findElement(contractortab).click();
		}
	    
	 public String getSuccessMessage() 
	    {
			wait.until(ExpectedConditions.visibilityOfElementLocated((successmessage)));
			return driver.findElement(successmessage).getText();
		}
	
	
	
	
	
	
	
	
	
	
	
	
	public SearchDeleteContractorPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 10);
	}
	
	
	

}
