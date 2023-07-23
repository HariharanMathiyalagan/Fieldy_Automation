//package com.zaigo.reactui;
//
//import java.awt.AWTException;
//import java.io.IOException;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.zaigo.pageobjects.CreateContractorPage;
//import com.zaigo.pageobjects.CreateVendorPage;
//import com.zaigo.pageobjects.EditContractor;
//import com.zaigo.pageobjects.LoginPage;
//import com.zaigo.utility.BrowserSetup;
//
//public class CreateContractorTestCases {
//
//	private WebDriver driver = null;
//	private LoginPage loginInPage = null;
//
//	@BeforeClass
//	public void setup() {
//		this.driver = BrowserSetup.startBrowser();
//	}
//
//	@AfterClass
//	public void exitBrowser() {
//		this.driver.quit();
//	}
//
//	@AfterMethod
//	public void setVariableEmpty() {
//		loginInPage = null;
//	}
//
//	@Test(priority = 1)
//	public void verifyContractorTab() throws InterruptedException, IOException {
//		// Verify User Tab
//
//		LoginPage loginInPage = new LoginPage(this.driver);
//		loginInPage.setUserCredentials("fieldy@zaiportal.com", "Zaiserve@123");
//		loginInPage.clickLoginButton();
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		String text = loginInPage.dashBoardText();
//		Assert.assertEquals(text, "Dashboard");
//		contractorPage.clickTeam();
//		// contractorPage.clickCompany();
//		contractorPage.clickContractor();
//		contractorPage.createContractorButtonj();
//
//	}
//
//	@Test(priority = 2)
//	public void verifyContractorNameMandatory() throws InterruptedException {
//
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		Thread.sleep(2000);
//		contractorPage.clickSaveandComplete();
//
//		String error_text = contractorPage.contractorNameError();
//		Assert.assertEquals(error_text, "Required Field");
//		// Thread.sleep(1000);
//		// q
//
//	}
//
//	@Test(priority = 4)
//	public void verifyContractorNumberMinValidation() throws InterruptedException {
//
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		contractorPage.contractorEmail("@gmail.com");
//		contractorPage.contractorPhone("123");
//		contractorPage.clickSaveandComplete();
//		String email_error = contractorPage.contractorPhoneError();
//		Assert.assertEquals(email_error, "Atleast 6 digits required");
//
//	}
//
//	@Test(priority = 3)
//	public void verifyContractorEmailFormat() throws InterruptedException {
//
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		contractorPage.contractorName("Contractor y");
//		contractorPage.contractorEmail("aibcda");
//		contractorPage.clickSaveandComplete();
//		String email_error = contractorPage.contractorEmailError();
//		Assert.assertEquals(email_error, "Enter a valid Email");
//
//	}
//
//	@Test(priority = 5)
//	public void verifyContractorNumberMaxValidation() throws InterruptedException {
//
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		contractorPage.contractorPhone("123123123118900");
//		contractorPage.clickSaveandComplete();
//		String email_error = contractorPage.contractorPhoneError();
//		Assert.assertEquals(email_error, "Not Allowed More than 12 digits");
//
//	}
//
//	@Test(priority = 6)
//	public void verifyFaxMinValidation() throws InterruptedException {
//
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		contractorPage.clearPhone();
//		contractorPage.contractorFax("77");
//		String fax_error = contractorPage.contractorFaxError();
//		Assert.assertEquals(fax_error, "Atleast 3 digits required");
//
//	}
//
//	@Test(priority = 7)
//	public void verifyFaxMaxValidation() throws InterruptedException {
//
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		contractorPage.clearPhone();
//		contractorPage.contractorFax("777777777777777778888888888888877777777777777777777777");
//		contractorPage.clickSaveandComplete();
//		String fax_error = contractorPage.contractorFaxError();
//		Assert.assertEquals(fax_error, "Not Allowed More than 25 digits");
//
//	}
//
//	@Test(priority = 8)
//	public void verifyCpersonMaxValidation() throws InterruptedException {
//
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		contractorPage.clearFax();
//		contractorPage.contractorContactPerson(
//				"Tambaram,chennai Tambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,ww w wwkdmwemw ewewkmewkekewewewklwklwlkwkwwkelwelwweewwwwmwmwmwmswmskwmskwmksmwksmkswsTambaram,chennai Tambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,chennaiTambaram,ww w wwkdmwemw ewewkmewkekewewewklwklwlkwkwwkelwelwweewwwwmwmwmwmswmskwmskwmksmwksmkswss");
//		contractorPage.clickSaveandComplete();
//		String cperson_error = contractorPage.contractorContactNameError();
//		Assert.assertEquals(cperson_error, "Not Allowed More than 512 characters");
//
//	}
//
//	@Test(priority = 9)
//	public void clearFields() {
//		CreateContractorPage clear = new CreateContractorPage(this.driver);
//		clear.clearContractorPerson();
//		clear.clearContractorEmail();
//		clear.conMaxValidation();
//
//	}
//
////	@Test(enabled= false)
////	public void verifyWebsiteValidation() throws InterruptedException
////	{
////		
////		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
////		contractorPage.clearContactPerson();
////		contractorPage.contractorWebsite("abcd");
////		contractorPage.clickSaveandComplete();
////		String web_error=contractorPage.contractorWebsiteError();
////		Assert.assertEquals(web_error,"Enter a valid URL(http(s)://sample.co)");
////		
////		
////	}
//
//	@Test(priority = 10)
//	public void verifySaveButtonExist() throws InterruptedException {
//
//		CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//		String text_button = contractorPage.getButtonText();
//		Assert.assertEquals(text_button, "Save & Complete");
//
//	}
//
//	@Test(priority = 11)
//	public void createContract() throws InterruptedException {
//		CreateContractorPage create = new CreateContractorPage(driver);
//		String r = RandomStringUtils.randomAlphabetic(3);
//		create.CreateContractor("Dhamu" + r, "dhamuy" + r + "@zaigoinfotech.com", "Dhamu" + r, "9599580891", "684",
//				"https://expermobileappweb.web.app/", "Chennai", "Mani@gmail.com", "Mani", "8765412309", "245",
//				"IndraNagar", "Karnataka", "Banglore", "620054");
//		create.Location("Chennai", "Mani@gmail.com", "Mani", "8765412309", "245", "IndraNagar", "Karnataka", "Banglore",
//				"620054");
////		String text = create.validation();
////		Assert.assertEquals(text, "Dhamu" + r);
//
//	}
//
//	@Test(priority = 12)
//	public void listValidationName() {
//		CreateContractorPage listValidation = new CreateContractorPage(driver);
//		listValidation.listValidation();
//	}
//
////	@Test(priority = 12)
////	public void validationName() {
////		CreateContractorPage validation = new CreateContractorPage(driver);
////		validation.nameValidation("Dhamu" + r);
////	}
//
//	@Test(priority = 13)
//	public void invalidValidationData() {
//		CreateContractorPage errorValidation = new CreateContractorPage(driver);
//		errorValidation.invalidData("asfvcsv");
//	}
//
//	/*
//	 * 
//	 * @Test (priority=21) public void verifyContractorab() { //Verify the Vendor
//	 * Tab
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); String text =
//	 * contractorPage.dashBoardVendorMenuText(); Assert.assertEquals(text,
//	 * "Contractor");
//	 * 
//	 * 
//	 * }
//	 * 
//	 * @Test (priority=3) public void verifyCreateButton() throws
//	 * InterruptedException { //Verify the CreateButton and Enter the Name
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorName("contractor Name");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * }
//	 * 
//	 * 
//	 * @Test (priority=4) public void verifyEmptyName() throws InterruptedException
//	 * { //Verify the Error Message if the Name field is Null
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton(); contractorPage.clickNextButton();
//	 * String text =contractorPage.contractorNameError(); Assert.assertEquals(text,
//	 * "This field is required"); contractorPage.clickCloseButton();
//	 * contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * 
//	 * }
//	 * 
//	 * @Test (priority=5) public void verifyEmptyEmail() throws InterruptedException
//	 * { //Verify the Error Message if the email field is Null
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton(); contractorPage.clickNextButton();
//	 * String text =contractorPage.contractorEmailError(); Assert.assertEquals(text,
//	 * "This field is required"); contractorPage.clickCloseButton();
//	 * contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * 
//	 * @Test (priority=6) public void verifyEmptyContactPerson() { //Verify the
//	 * Error Message if the email field is Null
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton(); contractorPage.clickNextButton();
//	 * String text =contractorPage.contractorContactNameError();
//	 * Assert.assertEquals(text, "This field is required");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * @Test (priority=7) public void verifyNameEmptySpaces() { //Verify the
//	 * blankspace not allowed error message
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorName("   "); contractorPage.clickNextButton();
//	 * String text =contractorPage.contractorNameError(); Assert.assertEquals(text,
//	 * "Blank space not allowed"); contractorPage.clickCloseButton();
//	 * contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * @Test (priority=8) public void verifyEmailEmptySpaces() { //Verify Email
//	 * Empty spaces
//	 * 
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorEmail("    "); contractorPage.clickNextButton();
//	 * String text =contractorPage.contractorEmailError(); Assert.assertEquals(text,
//	 * "This field is required"); contractorPage.clickCloseButton();
//	 * contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * 
//	 * @Test (priority=9) public void verifyEmailFormat() { //Verify Format
//	 * 
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorEmail("asdffg"); contractorPage.clickNextButton();
//	 * String text =contractorPage.contractorEmailError(); Assert.assertEquals(text,
//	 * "Enter a valid email address"); contractorPage.clickCloseButton();
//	 * contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * 
//	 * @Test (priority=10) public void verifyCnameEmptySpaces() { //Verify Contact
//	 * Person Empty spaces
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorContactPerson("       ");
//	 * contractorPage.clickNextButton(); String text
//	 * =contractorPage.contractorContactNameError();
//	 * Assert.assertEquals(text,"Blank space not allowed");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * @Test (priority=11) public void verifyWebsiteFormat() { //Verify Contact
//	 * Person Empty spaces
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorWebsite("hello"); contractorPage.clickNextButton();
//	 * String text =contractorPage.contractorWebsiteError();
//	 * Assert.assertEquals(text,"Enter a valid website URL");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * 
//	 * 
//	 * @Test (priority=12) public void verifyPhoneFormat() { //Verify Phone Field
//	 * Accepts Only the Number field.
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorPhone("123"); contractorPage.clickNextButton();
//	 * String text = contractorPage.contractorPhoneError();
//	 * Assert.assertEquals(text,"Input should contains 10 digits");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * @Test (priority=13) public void verifyMaxPhone() { //Verify Phone Field
//	 * accepts 20 digits
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorPhone("99402115639940211563");
//	 * contractorPage.clickNextButton(); String text =
//	 * contractorPage.contractorPhoneError();
//	 * Assert.assertEquals(text,"Input should contains 10 digits");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * @Test (priority=14) public void verifyMaxContactPerson() {
//	 * 
//	 * 
//	 * //Verify the max validation if Contact person Exceeds the Limit 50
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorContactPerson(
//	 * "ghgfhghgjgjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjthgthjjjj"); String
//	 * text =contractorPage.contractorContactNameError(); Assert.assertEquals(text,
//	 * "Not allowed more than 50 characters"); contractorPage.clickCloseButton();
//	 * contractorPage.clickYesButton();
//	 * 
//	 * }
//	 * 
//	 * @Test (priority=15) public void verifyMaxName() throws InterruptedException {
//	 * //Verify the max vendor name validation if Exceeds the Limit 50
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton(); contractorPage.
//	 * contractorName("Lorem ipsum dolor sit amet, consectetuer adipiscinddede ddededdcdc ddcdc"
//	 * ); contractorPage.clickNextButton(); String text
//	 * =contractorPage.contractorNameError(); Assert.assertEquals(text,
//	 * "Not allowed more than 50 characters"); contractorPage.clickCloseButton();
//	 * contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * 
//	 * }
//	 * 
//	 * 
//	 * @Test (priority=16) public void verifyLineOneMaxCharacters() throws
//	 * InterruptedException {
//	 * 
//	 * //check the Line One has Max characters validation
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorName("hello");
//	 * contractorPage.contractorEmail("email@gmail.com");
//	 * contractorPage.contractorContactPerson("cperson hello");
//	 * contractorPage.clickNextButton(); contractorPage.clickAddLocation();
//	 * contractorPage.clickLocationOne(); contractorPage.
//	 * contractorLineOne("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu"
//	 * ); contractorPage.clickSaveandComplete(); String text
//	 * =contractorPage.contractorLineOneError();
//	 * Assert.assertEquals(text,"Not allowed more than 150 characters");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * 
//	 * }
//	 * 
//	 * 
//	 * @Test (priority=17) public void verifyLineTwoMaxCharacters() throws
//	 * InterruptedException {
//	 * 
//	 * //check the Line Two has Max characters validation CreateContractorPage
//	 * contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorName("hello");
//	 * contractorPage.contractorEmail("email@gmail.com");
//	 * contractorPage.contractorContactPerson("cperson hello");
//	 * contractorPage.clickNextButton(); contractorPage.clickAddLocation();
//	 * contractorPage.clickLocationOne(); contractorPage.
//	 * contractorLineTwo("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu"
//	 * ); contractorPage.clickSaveandComplete(); String text
//	 * =contractorPage.contractorLineTwoError();
//	 * Assert.assertEquals(text,"Not allowed more than 150 characters");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton(); }
//	 * 
//	 * 
//	 * 
//	 * 
//	 * @Test (priority=18) public void verifyCityMaxCharacters() throws
//	 * InterruptedException {
//	 * 
//	 * //check City with Max character validations
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorName("hello");
//	 * contractorPage.contractorEmail("email@gmail.com");
//	 * contractorPage.contractorContactPerson("cperson hello");
//	 * contractorPage.clickNextButton(); contractorPage.clickAddLocation();
//	 * contractorPage.clickLocationOne(); contractorPage.
//	 * contractorCity("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu"
//	 * ); contractorPage.clickSaveandComplete(); String text
//	 * =contractorPage.contractorCityError();
//	 * Assert.assertEquals(text,"Not allowed more than 150 characters");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton(); }
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * @Test (priority=19) public void verifyMinZipcode() throws
//	 * InterruptedException { // //check zipcode accepts the 3 digits
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorName("hello");
//	 * contractorPage.contractorEmail("email@gmail.com");
//	 * contractorPage.contractorContactPerson("cperson hello");
//	 * contractorPage.clickNextButton(); contractorPage.clickAddLocation();
//	 * contractorPage.clickLocationOne(); contractorPage.contractorZipcode("123");
//	 * contractorPage.clickSaveandComplete(); String text
//	 * =contractorPage.contractorZipcodeError();
//	 * Assert.assertEquals(text,"The field must be minimum 6");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * }
//	 * 
//	 * 
//	 * 
//	 * @Test (priority=20) public void verifyWithValidDetails() throws
//	 * InterruptedException { // //check zipcode WITH 30 + CHARCAERS
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorName("hello");
//	 * contractorPage.contractorEmail("email@gmail.com");
//	 * contractorPage.contractorContactPerson("cperson hello");
//	 * contractorPage.clickNextButton(); contractorPage.clickAddLocation();
//	 * contractorPage.clickLocationOne(); contractorPage.contractorZipcode(
//	 * "123123456123123456123123456123123456123123456123123456"); String text
//	 * =contractorPage.contractorZipcodeError1();
//	 * Assert.assertEquals(text,"Not allowed more than 30 characters");
//	 * contractorPage.clickCloseButton(); contractorPage.clickYesButton();
//	 * 
//	 * }
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * 
//	 * @Test (priority=2) public void verifyContractorCreated() throws
//	 * InterruptedException { //Contractor Created successfully
//	 * 
//	 * CreateContractorPage contractorPage = new CreateContractorPage(this.driver);
//	 * contractorPage.dashBoardUserMenu(); contractorPage.clickVendorTab();
//	 * contractorPage.contractorCreateButton();
//	 * contractorPage.contractorName("hello");
//	 * contractorPage.contractorEmail("emailu781@gmail.com");
//	 * contractorPage.contractorContactPerson("cperson hello");
//	 * //contractorPage.uploadImage("C:\\Users\\lenovo\\Pictures\\picjpg.jpg");
//	 * contractorPage.clickSaveandComplete(); String text
//	 * =contractorPage.contractorSuccessMessage();
//	 * Assert.assertEquals(text,"Company Created successfully");
//	 * contractorPage.clickCloseButton();
//	 * 
//	 * }
//	 * 
//	 * 
//	 * 
//	 */
//
//}
