package base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilityclasses.Constants;
import com.utilityclasses.ExtentReportsUtility;

public class BasePage {
	protected static WebDriver driver=null;
	protected static Logger logger=LogManager.getLogger(BasePage.class.getName());
	protected static ExtentReportsUtility ExtentReport=ExtentReportsUtility.getInstance();
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public static WebElement findElement(String locator, String locValue) {
		switch (locator) {
		case "id":
			return driver.findElement(By.id(locValue));

		case "xpath":
			return driver.findElement(By.xpath(locValue));
		case "class":
			return driver.findElement(By.className(locValue));

		default:
			logger.info("Error-locator not found");
			
			break;
		}
		return null;
	}

	public static void enterText(WebElement element, String text,String name) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(text);
			logger.info(name+"entered in field");
			ExtentReport.logTestInfo(name+"entered in field");
		} else {
			logger.info("Error-" + element + "not found");
			
		}
	}
	public static void clickElement(WebElement element, String name ) {
		element.click();
		logger.info(name+" is clicked");
		ExtentReport.logTestInfo(name +" clicked");
	}
	
	

	public static String getTextFromElement(WebElement element) {
		if (element.isDisplayed()) {
			return element.getText();
		} else {
			logger.info("Element not found");
			return null;
		}
	}

	public static void waitToLoadWebPage(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	

	public static void ThreadSleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void FileDialogPasteAndEnter(String path) {
		StringSelection selection = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		Robot robot = null;
		try {
			robot = new Robot();
			Thread.sleep(500);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void javaScriptexecutor(WebElement element, String script) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(script, element);

	}

	public static void selectFromDropDown(WebElement element, String text) {
		Select select = new Select(element);
		
		select.selectByVisibleText(text);
		logger.info(text+" is selected from dropdown");
		ExtentReport.logTestInfo(text+" is selected from dropdown");
	}

	public static void selectCheckbox(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
		logger.info(element.getText()+" is selected from checkbox");
		ExtentReport.logTestInfo(element.getText()+" is selected from checkbox");
	}
	 
	public  void getScreenShotOfThePage(WebDriver driver)  {
		String date= new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		String currDir=System.getProperty("user.dir");
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File imgFile=screenshot.getScreenshotAs(OutputType.FILE);
		File destFile= new File(Constants.SCREENSHOTS_DIRECTORY_PATH+date+".png");
		try {
			FileHandler.copy(imgFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String getTitleOfThisPage() {
		return driver.getTitle();
	}
}
