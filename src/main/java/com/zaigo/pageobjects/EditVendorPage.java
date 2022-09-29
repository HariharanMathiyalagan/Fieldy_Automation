package com.zaigo.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditVendorPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	//Edit vendor page objects

    private By usermenu=By.xpath("//a[@data-automationid='user']");
	
	
	private By vendormenu=By.xpath("//a[@data-automationid='vendor']");
	
	private By vendortab=By.xpath("//a[@data-automationid='vendor']");
	
	private By vendorcreatebtn=By.xpath("//*[@data-automationid='createVendor']");
	
	private By vendorname=By.xpath("//*[@data-automationid='companyName']");
	private By vendornameerr=By.xpath("//*[@data-automationid='name-error']");
	
	private By nextbtn = By.xpath("//button[@data-automationid='next']");
	
	
	private By vendoremail=By.xpath("//input[@data-automationid='email']");
	private By vendoremailerr=By.xpath("//*[@data-automationid='email-error']");
	
    private By vendorcperson=By.xpath("//input[@data-automationid='contactPerson']");
	
	private By vendorcpersonerr=By.xpath("//*[@data-automationid='contact_person_name-error']");
	
	private By vendorphone=By.xpath("//input[@data-automationid='phone']");
	
	private By vendorphoneerr=By.xpath("//*[@data-automationid='phone-error']");
	
	
	
	private By vendorfax=By.xpath("//input[@data-automationid='fax']");
	
	private By vendorfaxerror=By.xpath("//*[@data-automationid='fax']");
	
	
	private By vendorsite=By.xpath("//input[@data-automationid='website']");
	
	private By vendorsiteerr=By.xpath("//*[@data-automationid='website-error']");
	
	
	private By vendorimageupload=By.id("imageUpload");
	
	private By imagerror = By.xpath("//input[@data-automationid='typeAndEnter']");
	
	private By vendorPdtServ = By.xpath("//input[@data-automationid='typeAndEnter']");	
	
	private By closepopup = By.xpath("//button[@data-automationid='close']");
	
	private By warningpopup = By.xpath("//button[@data-automationid='yesBtn']");
	
	private By saveform =By.xpath("//button[@data-automationid='saveAndComplete']");
	
	private By successmessage= By.xpath("//*[@data-automationid='sucmessage']");
	
	private By closesucc =By.xpath("//button[@data-automationid='close']");
	//Address
	
	private By addlocationbtn = By.xpath("//button[@data-automationid='anotherLocation']");
	
	
	private By locationone = By.xpath("//*[@data-automationid='Location 1']");
	
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
    
    private By country = By.xpath("//button[@data-automationid='saveAndComplete']");
	
    private By state = By.xpath("//button[@data-automationid='saveAndComplete']");
    
    private By actionmenu = By.xpath("//button[@data-automationid='vendorActive']");
    
    private By deletebtn = By.xpath("//*[@data-automationid='Delete Vendor']");
  
    private By searchfield = By.id("search");
    
    private By searchdata =By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[4]");
    
    private By detailview = By.xpath("//*[@data-automationid='close']//following::h3");
    
    private By list = By.xpath("//*[@data-automationid='vendorDetails']");
    
    private By closedetail = By.xpath("//button[@data-automationid='close']");
    
    //Edit
    
   private By clickedit = By.xpath("//*[@data-automationid='Edit Vendor']"); 
    
    
    public void clickDetailClose() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((closedetail)));
		driver.findElement(closedetail).click();
	    }
    
    
    public void clickEditMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((clickedit)));
		driver.findElement(clickedit).click();
	    }
    
    public void clickEnterSearch(Keys services) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((searchfield)));
		 driver.findElement(searchfield).sendKeys(services);
	    }
    
    public void clickName() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((list)));
		driver.findElement(list).click();
	    }
    
    public String detailView() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((detailview)));
		return driver.findElement(detailview).getText();
	    }
    
    public String getCreateButtonValue() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((detailview)));
		return driver.findElement(detailview).getAttribute("value");
	    }
    
    
    
    
    public void enterSearch(String enter) {
		wait.until(ExpectedConditions.visibilityOfElementLocated((searchfield)));
		 driver.findElement(searchfield).sendKeys(enter);
	    }
    
    
    public String searchResult() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((searchfield)));
		return driver.findElement(searchfield).getText();
	    }
    
    
    public void clickActionMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((actionmenu)));
		driver.findElement(actionmenu).click();
	    }
    
    
    public void clickDeleteMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((deletebtn)));
		driver.findElement(deletebtn).click();
	    }
    
    
    
    
    
    
    
    
    
    public void clickSuccCloseButton() {
  		wait.until(ExpectedConditions.visibilityOfElementLocated((closesucc)));
  		driver.findElement(closesucc).click();
  	    }
    
    
    
	
	public void vendorProductandServices(String services)
	{
    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorPdtServ)));
    driver.findElement(vendorPdtServ).sendKeys(services);
	}
	
	public void vendorclickEnter(Keys services)
	{
    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorPdtServ)));
    driver.findElement(vendorPdtServ).sendKeys(services);
	}
	
	
	public void clickAddLocation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((addlocationbtn)));
		driver.findElement(addlocationbtn).click();
	    }
	
	public void clickLocationOne() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((locationone)));
		driver.findElement(locationone).click();
	    }
	
	
	
	public void clickSaveandComplete() {
		wait.until(ExpectedConditions.visibilityOfElementLocated((saveform)));
		driver.findElement(saveform).click();
	    }
	
	
	
	
	
	
	public void uploadImage(String teamname )
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(vendorimageupload));
		driver.findElement(vendorimageupload).sendKeys(teamname);
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
			wait.until(ExpectedConditions.visibilityOfElementLocated((vendormenu)));
			return driver.findElement(vendormenu).getText();
		    }
	    
	    
	    
		public void clickVendorTab()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendortab)));
	    driver.findElement(vendortab).click();
		}
		
		public void vendorCreateButton()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorcreatebtn)));
	    driver.findElement(vendorcreatebtn).click();
		}

		public void vendorName(String name) throws InterruptedException
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorname)));
	    Thread.sleep(2000);
	    driver.findElement(vendorname).clear();
	    driver.findElement(vendorname).sendKeys(name);
		}

		
		public void vendorEmail(String Email)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendoremail)));
	    driver.findElement(vendoremail).clear();
	    driver.findElement(vendoremail).sendKeys(Email);
		}
		
		
		public void vendorContactPerson(String ContactPerson)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorcperson)));
	    driver.findElement(vendorcperson).clear();
	    driver.findElement(vendorcperson).sendKeys(ContactPerson);
		}
		
		
		public void vendorPhone(String ContactPhone)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorcperson)));
	    driver.findElement(vendorcperson).clear();
	    driver.findElement(vendorcperson).sendKeys(ContactPhone);
		}
		
		public void vendorFax(String Fax)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorfax)));
	    driver.findElement(vendorfax).sendKeys(Fax);
		}
		
		
		
		
		public void vendorWebsite(String Website)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorsite)));
	    driver.findElement(vendorsite).clear();
	    driver.findElement(vendorsite).sendKeys(Website);
		}
		
		
		
		
		
		public String vendorNameError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendornameerr)));
	    return driver.findElement(vendornameerr).getText();
		}
		
		public String vendorEmailError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendoremailerr)));
	    return driver.findElement(vendoremailerr).getText();
		}
		
		
		public String vendorFaxError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorfaxerror)));
	    return driver.findElement(vendorfaxerror).getText();
		}
		
		
		
		public String vendorContactNameError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorcpersonerr)));
	    return driver.findElement(vendorcpersonerr).getText();
		}
		
		
		public String vendorPhoneError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorphoneerr)));
	    return driver.findElement(vendorphoneerr).getText();
		}
		
		public String vendorWebsiteError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorsiteerr)));
	    return driver.findElement(vendorsiteerr).getText();
		}
		
		
		public String vendorSuccessMessage()
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
		
		
		public void vendorLineOne(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((lineone)));
	    driver.findElement(lineone).sendKeys(services);
		}
		
		
		public String vendorLineOneError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((lineoneerr)));
	    return driver.findElement(lineoneerr).getText();
		}
		
		
		public void vendorLineTwo(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((linetwo)));
	    driver.findElement(linetwo).sendKeys(services);
		}
		
		
		public String vendorLineTwoError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((linetwoerr)));
	    return driver.findElement(linetwoerr).getText();
		}
		
		public void vendorCity(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((city)));
	    driver.findElement(city).sendKeys(services);
		}
		
		
		public String vendorCityError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((cityerr)));
	    return driver.findElement(cityerr).getText();
		}
		
		public void vendorAddressContactPerson(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorPdtServ)));
	    driver.findElement(vendorPdtServ).sendKeys(services);
		}
		
		
		public String vendorContctPersonError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((vendorPdtServ)));
	    return driver.findElement(vendorPdtServ).getText();
		}
		
		public void vendorZipcode(String services)
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((zipcode)));
	    driver.findElement(zipcode).sendKeys(services);
		}
		
		
		public String vendorZipcodeError()
		{
	    wait.until(ExpectedConditions.visibilityOfElementLocated((zipcoderr)));
	    return driver.findElement(zipcoderr).getText();
		}
		
		
		
		
		
		
		
		public EditVendorPage(WebDriver driver) {
			this.driver = driver;
			this.wait = new WebDriverWait(this.driver, 10);
		}
		
		
	}


