package com.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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

	public static String getPropertyValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "\\Folder\\config.properties");
		properties.load(stream);
		String value = (String) properties.get(key);
		return value;

	}

}
