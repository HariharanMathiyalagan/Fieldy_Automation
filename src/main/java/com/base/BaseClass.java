package com.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Company;
import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	public static void browserConfigChrome() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public static void browserConfigEdge() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	public static void openUrl(String Url) {
		driver.get(Url);
	}

	public static void NavigateUrl(String value) {
		driver.navigate().to(value);
	}

	public static void NavigateBack() {
		driver.navigate().back();
	}

	public static void NavigateForward() {
		driver.navigate().forward();
	}

	public static void NavigateRefersh() {
		driver.navigate().refresh();
	}

	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public WebElement locatedbyId(String Id) {
		WebElement findElement = driver.findElement(By.id(Id));
		return findElement;

	}

	public WebElement locatedbyName(String Name) {
		WebElement findElement = driver.findElement(By.name(Name));
		return findElement;
	}

	public WebElement locatedbyXpath(String Xpath) {
		WebElement findElement = driver.findElement(By.xpath(Xpath));
		return findElement;
	}

	public static void inputText(WebElement element, String value) {
		element.sendKeys(value);
	}

	public static void Click(WebElement element) {
		element.click();
	}

	public static void getText(WebElement element) {
		element.getText();
	}

	public static void AlertAccept() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void AlertDismiss() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public static void AlertSendKeys(String value) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}

	public static void AlertgetText() {
		Alert alert = driver.switchTo().alert();
		alert.getText();
	}

	public static void frame() {
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		int size = frames.size();
		System.out.println(size);
	}

	public static void mouseAction(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public static void DragandDrop(WebElement source, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
	}

	public static void wait(int value) throws InterruptedException {
		Thread.sleep(value);
	}

	public static void windowsHandled(int value) {
		String parentWindow = driver.getWindowHandle();
		Set<String> AllWindowsId = driver.getWindowHandles();
		List<String> EveryWindows = new ArrayList();
		EveryWindows.addAll(AllWindowsId);
		String childWindow = EveryWindows.get(value);
		driver.switchTo().window(childWindow);
	}

	public static void selectByIndex(WebElement dnd, int value) {
		Select select = new Select(dnd);
		select.selectByIndex(value);
	}

	public static void text(String txt, WebElement text) {
		String attribute = text.getAttribute("value");
		System.out.println(txt + attribute);
	}

	public static void quitBrowser() {
		driver.quit();
	}

	public static void closeBrowser() {
		driver.close();
	}

	public static void scrollUp(WebElement ref) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true)", ref);
	}

	public static void scrollDown(WebElement ref) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(false)", ref);
	}

	public static void ScreenShots(String path) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		File f = new File(path);
		FileUtils.copyFile(screenshotAs, f);
	}

	public static void referesh() {
		driver.navigate().refresh();

	}

	public static void hold() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void attachmentFile(String location) throws AWTException {
		StringSelection selection = new StringSelection(location);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public String getPropertyValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\Folder\\config.properties");
		properties.load(stream);
		String value = (String) properties.get(key);
		return value;

	}

	public static String excelRead(String sheet, int rowIndex, int cellIndex) throws IOException {// 27
		String value = null;

		File f = new File(System.getProperty("user.dir") + "\\Folder\\Automation Test Data.xlsx");

		FileInputStream fin = new FileInputStream(f);

		Workbook w = new XSSFWorkbook(fin);

		Sheet s = w.getSheet(sheet);

		Row row = s.getRow(rowIndex);

		Cell cell = row.getCell(cellIndex);

		CellType cellType = cell.getCellType();

		switch (cellType) {
		case STRING:
			value = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat sim = new SimpleDateFormat("dd-mm-yyyy");
				value = sim.format(dateCellValue);
			} else {
				double numericCellValue = cell.getNumericCellValue();
				long l = (long) numericCellValue;
				value = String.valueOf(l);
			}
			break;
		default:
			break;
		}
		return value;

	}

	public static void excelWrite(String sheet, int row, int cell, String value) throws IOException {// 28
		File f = new File(System.getProperty("user.dir") + "\\Folder\\Automation Test Data.xlsx");

		FileInputStream fin = new FileInputStream(f);

		Workbook w = new XSSFWorkbook(fin);

		Sheet s = w.getSheet(sheet);

		Row r = s.getRow(row);

		Cell c = r.getCell(cell);

		c.setCellValue(value);
		FileOutputStream fout = new FileOutputStream(f);
		w.write(fout);
	}

	public void fakeFirstName() {
		Faker faker = new Faker();
		String firstName = faker.name().firstName();

	}

	public static void fakeLastName() {
		Faker faker = new Faker();
		String lastName = faker.name().lastName();

	}

	public void fakePhoneNumber() {
		Faker faker = new Faker();
		String phoneNumber = faker.phoneNumber().phoneNumber();

	}

	public void fakeAddress1() {
		Faker faker = new Faker();
		String Address1 = faker.address().buildingNumber();

	}

	public void fakeCity() {
		Faker faker = new Faker();
		String city = faker.address().city();

	}

	public void fakeState() {
		Faker faker = new Faker();
		String state = faker.address().state();

	}

	public void fakeAddress2() {
		Faker faker = new Faker();
		String Address1 = faker.address().streetAddress();

	}

	public void fakeZipcode() {
		Faker faker = new Faker();
		String Address1 = faker.address().zipCode();

	}
	
	public void fakeWebsite() {
		Faker faker = new Faker();
		String website = faker.company().url();

	}
	
	public void fakeCompanyName() {
		Faker faker = new Faker();
		String companyName = faker.company().name();

	}

	
}
