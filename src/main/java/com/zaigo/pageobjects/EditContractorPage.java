package com.zaigo.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class EditContractorPage {
	
	private WebDriver driver;
	private WebDriverWait wait;

	private By usermenu=By.xpath("//a[@data-automationid='user']");
	
	
//	private By contractorrmenu=By.xpath("//a[@data-automationid='vendor']");
	
	private By contractortab=By.xpath("//a[@data-automationid='contractor']");
	
	private By contractorcreatebtn=By.xpath("//*[@data-automationid='createContractor']");
	
	private By contractorname=By.xpath("//input[@data-automationid='name']");
	
	private By contractornameer=By.xpath("//*[@data-automationid='name-error']");
	
	private By nextbtn = By.xpath("//button[@data-automationid='next']");
	
	
	private By contractoremail=By.xpath("//input[@data-automationid='email']");
	
	private By contractoremailerr=By.xpath("//*[@data-automationid='email-error']");
	
    private By contractorcperson=By.xpath("//input[@data-automationid='contactPerson']");
	
	private By contractorcpersonerr=By.xpath("//*[@data-automationid='contactperson-error']");
	
	private By contractorphone=By.xpath("//input[@data-automationid='phone']");
	
	private By contractorphoneerr=By.xpath("//*[@data-automationid='phone-error']");
	
	
	
	private By contractorfax=By.xpath("//input[@data-automationid='fax']");
	
	private By contractorfaxerror=By.xpath("//*[@data-automationid='fax']");
	
	
	private By contractorsite=By.xpath("//input[@data-automationid='website']");
	
	private By contractorsiteerr=By.xpath("//*[@data-automationid='website-error']");
	  
	private By locationone = By.xpath("//*[@data-automationid='Location 1']");
	
	private By contractorimageupload=By.id("imageUpload");
	
	private By imagerror = By.xpath("//*[@data-automationid='imageerror']");
	
		
	private By closepopup = By.xpath("//button[@data-automationid='close']");
	
	private By warningpopup = By.xpath("//button[@data-automationid='yesBtn']");
	
	private By saveform =By.xpath("//button[@data-automationid='saveAndComplete']");
	
	private By successmessage= By.xpath("//h3[@data-automationid='sucmessage']");
	
	private By editbtn = By.xpath("//*[@data-automationid='Edit Contractor']");
	
	private By menu = By.xpath("//*[@data-automationid='activeContractor']");
	private By Deleteconfirmyes = By.xpath("//button[@data-automationid='yesBtn']");
			
	//Address
	
	private By addlocationbtn = By.xpath("//button[@data-automationid='anotherLocation']");
	
	
	
	private By lineone = By.xpath("//input[@data-automationid='noBuildingFlatName']");
	
	private By lineoneerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");
	
	
    private By linetwo = By.xpath("//input[@data-automationid='streetName']");
	
	private By linetwoerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");
	
	
    private By addressname = By.xpath("//button[@data-automationid='saveAndComplete']");
	
	private By addressnameerr = By.xpath("//button[@data-automationid='saveAndComplete']");
	
	private By city = By.xpath("//input[@data-automationid='cityVillage']");
		
    private By cityerr = By.xpath("//*[contains(text(),'Not allowed more than 150 characters')]");
	    
    
    private By zipcode = By.xpath("//input[@data-automationid='zipCode']");
	
    private By zipcoderr = By.xpath("//*[contains(text(),'The field must be minimum 6')]");
    
    
    
    private By zipcoderr1 = By.xpath("//*[contains(text(),'Not allowed more than 30 characters')]");
    
    
    private By country = By.xpath("//button[@data-automationid='saveAndComplete']");
	
    private By state = By.xpath("//button[@data-automationid='saveAndComplete']");
    
    
    
   private By clickmenu = By.xpath("//button[@data-automationid='activeContractor']");
   
   private By clickDelete = By.xpath("//*[@data-automationid='Delete Contractor']");
   
   private By listname = By.xpath("//h3[contains(text(),'erg')]");
   
   private By contractordetail = By.xpath("//button [@data-automationid='close']//following::h3");
   
   private By clickclosebutton = By.xpath("//button[@data-automationid='c']");
    
   
   public String getCreatedContractorNameDetail()
   {
	   
   
		wait.until(ExpectedConditions.visibilityOfElementLocated((contractordetail)));
		return driver.findElement(contractordetail).getText();
	 }
   
   
   
   public void clickMenu() 
   {
		wait.until(ExpectedConditions.visibilityOfElementLocated((menu)));
	    driver.findElement(menu).click();
   }
   
   public void clickEdit() 
   {
		wait.until(ExpectedConditions.visibilityOfElementLocated((editbtn)));
	    driver.findElement(editbtn).click();
   }
   
   
   
   
   public void clickSuccessClose() 
   {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickclosebutton)));
	    driver.findElement(clickclosebutton).click();
    }
   
   
   
   public void clickContractorName() 
   {
		wait.until(ExpectedConditions.visibilityOfElementLocated((listname)));
	    driver.findElement(listname).click();
    }
   
   
   
   public String getCreatedContractorName() 
   {
		wait.until(ExpectedConditions.visibilityOfElementLocated((listname)));
		return driver.findElement(listname).getText();
    }
   
   
   public void clickActionMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickmenu)));
	driver.findElement(clickmenu).click();
	    }
   
   public void clickDeleteMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickDelete)));
	driver.findElement(clickDelete).click();
	    }
   
   
   
   
    public void clickLocationOne() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((locationone)));
	driver.findElement(locationone).click();
	    }
	
	
	public void clickAddLocation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((addlocationbtn)));
		driver.findElement(addlocationbtn).click();
	    }
	
	
	
	public void clickSaveandComplete() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((saveform)));
		driver.findElement(saveform).click();
	    }
	
	
	
	
	
	
	public void uploadImage(String teamname )
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(contractorimageupload));
		driver.findElement(contractorimageupload).sendKeys(teamname);
	}
	
	
	
	    public void dashBoardUserMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((usermenu)));
		driver.findElement(usermenu).click();
	    }
	    
	    public void clickNextButton() {
			wait.until(ExpectedConditions.visibilityOfElementLocated((nextbtn)));
			driver.findElement(nextbtn).click();
		    }
	    
	    public void clickYesButton() {
			wait.until(ExpectedConditions.visibilityOfElementLocated((warningpopup)));
			driver.findElement(warningpopup).click();
		    }
	    
	    
	    
	    
	    
	    public void clickCloseButton() 
	    {
			wait.until(ExpectedConditions.visibilityOfElementLocated((closepopup)));
			driver.findElement(closepopup).click();
		}
	    
	    
	    public String dashBoardUserMenuText() 
	    {
			wait.until(ExpectedConditions.visibilityOfElementLocated((usermenu)));
			return driver.findElement(usermenu).getText();
		 }
		
	    
	    
	    
	    public String dashBoardVendorMenuText() {
			wait.until(ExpectedConditions.visibilityOfElementLocated((contractortab)));
			return driver.findElement(contractortab).getText();
		    }
	    
	    
	    
		public void clickVendorTab()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractortab)));
	    driver.findElement(contractortab).click();
		}
		
		public void contractorCreateButton()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorcreatebtn)));
	    driver.findElement(contractorcreatebtn).click();
		}

		public void contractorName(String name) throws InterruptedException
		{
	    wait.until(ExpectedConditions.presenceOfElementLocated((contractorname)));
	  Thread.sleep(2000);
      driver.findElement(contractorname).clear();
	    driver.findElement(contractorname).sendKeys(name);
		}

		
		public void contractorEmail(String Email)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractoremail)));
	    driver.findElement(contractoremail).clear();
	    driver.findElement(contractoremail).sendKeys(Email);
		}
		
		
		public void contractorContactPerson(String ContactPerson)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorcperson)));
	    driver.findElement(contractorcperson).clear();
	    driver.findElement(contractorcperson).sendKeys(ContactPerson);
		}
	
	public void clickConfirmDelete() 
   {
		wait.until(ExpectedConditions.visibilityOfElementLocated((Deleteconfirmyes)));
	    driver.findElement(Deleteconfirmyes).click();
   }
   
		
		
		public void contractorPhone(String ContactPhone)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorphone)));
	    driver.findElement(contractorcperson).clear();
	    driver.findElement(contractorphone).sendKeys(ContactPhone);
		}
		
		public void contractorFax(String Fax)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorfax)));
	    driver.findElement(contractorfax).sendKeys(Fax);
		}
		
		
		
		
		public void contractorWebsite(String Website)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorsite)));
	    driver.findElement(contractorsite).clear();
	    driver.findElement(contractorsite).sendKeys(Website);
		}
		
		
		
		
		
		public String contractorNameError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractornameer)));
	    return driver.findElement(contractornameer).getText();
		}
		
		public String contractorEmailError()
		{
	    wait.until(ExpectedConditions.presenceOfElementLocated((contractoremailerr)));
	    return driver.findElement(contractoremailerr).getText();
		}
		
		
		public String contractorFaxError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorfaxerror)));
	    return driver.findElement(contractorfaxerror).getText();
		}
		
		
		
		public String contractorContactNameError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorcpersonerr)));
	    return driver.findElement(contractorcpersonerr).getText();
		}
		
		
		public String contractorPhoneError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorphoneerr)));
	    return driver.findElement(contractorphoneerr).getText();
		}
		
		public String contractorWebsiteError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((contractorsiteerr)));
	    return driver.findElement(contractorsiteerr).getText();
		}
		
		
		public String contractorSuccessMessage()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((successmessage)));
	    return driver.findElement(successmessage).getText();
		}
		
		
		public String vendorImageError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((imagerror)));
	    return driver.findElement(imagerror).getText();
		}
		
		
		
		
		
		
		//Address Form
		
		public void selectCountry()
		 {
				wait.until(ExpectedConditions.visibilityOfElementLocated(country));
				driver.findElement(country).click();
				Select select = new Select(driver.findElement(country));
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				select.selectByVisibleText("india");
		 }
		
		public void selectState()
		 {
			wait.until(ExpectedConditions.visibilityOfElementLocated(state));
			driver.findElement(state).click();
			Select select = new Select(driver.findElement(state));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			select.selectByVisibleText("tamil nadu");
		 }
		
		
		public void contractorLineOne(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((lineone)));
	    driver.findElement(lineone).sendKeys(services);
		}
		
		
		public String contractorLineOneError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((lineoneerr)));
	    return driver.findElement(lineoneerr).getText();
		}
		
		
		public void contractorLineTwo(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((linetwo)));
	    driver.findElement(linetwo).sendKeys(services);
		}
		
		
		public String contractorLineTwoError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((linetwoerr)));
	    return driver.findElement(linetwoerr).getText();
		}
		
		public void contractorCity(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((city)));
	    driver.findElement(city).sendKeys(services);
		}
		
		
		public String contractorCityError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((cityerr)));
	    return driver.findElement(cityerr).getText();
		}
		
		public void contractorAddressContactPerson(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((addressname)));
	    driver.findElement(addressname).sendKeys(services);
		}
		
		
		public String contractorContctPersonError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((addressnameerr)));
	    return driver.findElement(addressnameerr).getText();
		}
		
		public void contractorZipcode(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((zipcode)));
	    driver.findElement(zipcode).sendKeys(services);
		}
		
		
		public String contractorZipcodeError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((zipcoderr)));
	    return driver.findElement(zipcoderr).getText();
		}
		
		public String contractorZipcodeError1()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((zipcoderr1)));
	    return driver.findElement(zipcoderr1).getText();
		}
		
		
		
		
		
		
		public EditContractorPage(WebDriver driver) {
			this.driver = driver;
			this.wait = new WebDriverWait(this.driver, 10);
		}
		
    
    
    
    
    
    
    
    
    
    
    
    

}
