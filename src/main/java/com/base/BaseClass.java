package com.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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

	public void scrollDown(WebElement ref) {
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
				java.util.Date dateCellValue = cell.getDateCellValue();
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

		XSSFWorkbook w = new XSSFWorkbook(fin);
		XSSFSheet sheetAt = w.getSheetAt(0);
		sheetAt.getRow(row).getCell(cell).setCellValue(value);
		System.out.println(value);
		FileOutputStream fileOutputStream = new FileOutputStream(f);
		w.write(fileOutputStream);
//		w.close();
	}

	public String getdata(String filenamepath, String sheetname, int rowno, int cellno) throws IOException {

		String data = null;

		File file = new File(filenamepath);

		FileInputStream stream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(stream);

		Sheet sheet = workbook.getSheet(sheetname);

		Row row = sheet.getRow(rowno);

		org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellno);

		CellType type = cell.getCellType();

		switch (type) {
		case STRING:
			data = cell.getStringCellValue();

			break;
		case NUMERIC:

			if (DateUtil.isCellDateFormatted(cell)) {

				java.util.Date date = cell.getDateCellValue();

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

				data = dateFormat.format(date);
			}

			else {

				double d = cell.getNumericCellValue();
				BigDecimal b = BigDecimal.valueOf(d);
				data = b.toString();

			}

			break;

		default:
			break;
		}

		return data;
	}

	public void updatedata(String filenamepath, String sheetname, int rowno, int cellno, String olddata, String newdata)
			throws IOException {

		File file = new File(filenamepath);

		FileInputStream stream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(stream);

		Sheet sheet = workbook.getSheet(sheetname);

		Row row = sheet.getRow(rowno);

		org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellno);

		String value = cell.getStringCellValue();

		if (value.equals(olddata)) {

			cell.setCellValue(newdata);
		}

		FileOutputStream o = new FileOutputStream(file);
		workbook.write(o);

	}

	public void Writedata()
			throws IOException, InvalidFormatException {

		File file = new File(System.getProperty("user.dir") + "\\Folder\\Automation Test Data.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Onboarding");

		int rowNum = sheet.getLastRowNum()+1;
		Row row = sheet.createRow(rowNum);

		Cell cell = row.createCell(0);
		cell.setCellValue("Test Result");

		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\Folder\\Automation Test Data.xlsx");
		workbook.write(fos);
		fos.close();
		workbook.close();

	}

	public void createworkbook(String filenamepath, String sheetname, int rowno, int cellno, String data)
			throws IOException {

		File file = new File(filenamepath);

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet(sheetname);

		Row row = sheet.getRow(rowno);

		org.apache.poi.ss.usermodel.Cell cell = row.createCell(cellno);

		cell.setCellValue(data);

		FileOutputStream o = new FileOutputStream(file);
		workbook.write(o);

	}
}
