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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	WebDriverWait wait;

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

	public static void Click(WebElement element) {
		element.click();
	}

//	public String getText(WebElement element) {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
//		return text;
//	}

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

	public void selectByIndex(WebElement element, int num) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		Select select = new Select(until);
		select.selectByIndex(num);
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

	public String getPropertyImageValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(
				System.getProperty("user.dir") + "\\ImagePicture\\Free_Test_Data_1MB_PDF.pdf");
		properties.load(stream);
		String value = (String) properties.get(key);
		return value;

	}

	public String getPropertyValue(String key, String value) throws IOException {
		if (value.equals("dev")) {
			Properties properties = new Properties();
			FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\Folder\\DEV.properties");
			properties.load(stream);
			return (String) properties.get(key);
		} else if (value.equals("qa")) {
			Properties properties = new Properties();
			FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\Folder\\QA.properties");
			properties.load(stream);
			return (String) properties.get(key);
		} else if (value.equals("pro")) {
			Properties properties = new Properties();
			FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\Folder\\LIVE.properties");
			properties.load(stream);
			return (String) properties.get(key);
		}
		return value;

	}

	public static String getBrowserValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\Folder\\config.properties");
		properties.load(stream);
		String value = (String) properties.get(key);
		return value;

	}

	public String getPropertyValueUpdate(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\Folder\\Update.properties");
		properties.load(stream);
		String value = (String) properties.get(key);
		return value;

		
	}

	public String calculation(String quantity, String price, String discount, String tax) {
		double quantityValue = Double.parseDouble(quantity);
		double priceValue = Double.parseDouble(price);
		double discountValue = Double.parseDouble(discount);
		double taxValue = Double.parseDouble(tax);
		double grossAmount = ((quantityValue * priceValue) - (quantityValue * priceValue * (discountValue / 100)))
				+ (((quantityValue * priceValue) - (quantityValue * priceValue * (discountValue / 100)))
						* (taxValue / 100));
		DecimalFormat f = new DecimalFormat("0.00");
		String format = f.format(grossAmount);
		return format;

	}

}
