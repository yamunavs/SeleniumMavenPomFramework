package base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.utilityclasses.Constants;
import com.utilityclasses.ExtentReportsUtility;
import com.utilityclasses.PropertiesUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static Logger logger=null;
	protected static ExtentReportsUtility ExtentReport=ExtentReportsUtility.getInstance();
	protected static WebDriver driver; 
 
	@BeforeTest
	public void setUpbeforeTest() {
		//ExtentReport=ExtentReportsUtility.getInstance();
		//ExtentReport.logTestInfo("BeforeTest method started");
		logger= LogManager.getLogger(BaseTest.class.getName());
		System.out.println("inside before test");
		//ExtentReport=
		
	}
	

	@BeforeMethod
	@Parameters("browsername")
	public void setupBeforeMethod(@Optional("chrome")  String browserName,Method method) {
		
		logger.info("Started TestScript name = "+method.getName());
		//ExtentReport.logTestInfo("Started TestScript name = "+method.getName());
		getDriverInstance(browserName);
		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");
		String URL = ob.getPropertyData("url");
		goToUrl(URL);
	}
	
    @AfterMethod
	public void teardownAfterMethod() {
		driver.close();
	}
    
    
    public WebDriver returnDriverInstance() {
		return driver;
	}

	public static void getDriverInstance(String browsername) {

		switch (browsername) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();

			break;

		default:
			logger.info("Browser is not found");
			ExtentReport.logTestInfo("Browser is not found");
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public static void goToUrl(String url) {
		driver.get(url);
		logger.info("going to url :"+url);
		//ExtentReport.logTestInfo("going to url :"+url);
	}

	public  String getScreenShotBase(WebDriver driver)  {
		String date= new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		String currDir=System.getProperty("user.dir");
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		String img=screenshot.getScreenshotAs(OutputType.BASE64);
		return img;
		
		}
		
		
	}
	

