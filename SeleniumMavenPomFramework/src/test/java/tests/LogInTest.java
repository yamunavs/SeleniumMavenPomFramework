package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilityclasses.ExtentReportsUtility;
import com.utilityclasses.PropertiesUtilities;

import base.BaseTest;
import pages.home.HomePage;
import pages.login.ForgetPasswordPage;
import pages.login.LoginPage;
@Listeners(com.utilityclasses.TestEventListenersUtility.class)
public class LogInTest extends BaseTest {

   
	@Test
	public void error_logIn() throws IOException {
		logger.info("inside errorlogin method");
		ExtentReport.logTestInfo("inside errorlogin method");
		String expected = "Please enter your password.";
		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");

		String username = ob.getPropertyData("login.valid.usename");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterUserName(username);
		
		logger.info("valid username is entered");
		ExtentReport.logTestInfo("valid username is entered");
		logger.info("password field is cleared");
		ExtentReport.logTestInfo("password field is cleared");

		loginPage.clickLogin();
		String actual=loginPage.getErrorMessage();
		
		assertEquals(actual, expected);
		
		

	}

	@Test
	public void logIn_to_Salesforce() {
		String expectedPageTitle = "Home Page ~ Salesforce - Developer Edition";

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");

		String username = ob.getPropertyData("login.valid.usename");
		LoginPage loginPage= new LoginPage(driver);
		loginPage.enterUserName(username);
		

		//logger.info("valid username is entered in username Field");
		//ExtentReport.logTestInfo("valid username is entered in username Field");

		String password = ob.getPropertyData("login.valid.password");
		loginPage.enterPassword(password);
		
		//logger.info("valid password is entered in password field");
		//ExtentReport.logTestInfo("valid password is entered in password field");

		driver=loginPage.clickLogin();
		HomePage homePage= new HomePage(driver);
		homePage.waitUntilLoggedInUserAppear();
		
		String actual =homePage.getTitleOfPage();
		assertEquals(actual, expectedPageTitle);
		
		

	}

	@Test
	public void checkRememberMe() {

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");

		String username = ob.getPropertyData("login.valid.usename");
		String expected = username;
		LoginPage loginPage= new LoginPage(driver);
		loginPage.enterUserName(username);
		
		logger.info("Valid username is entered in username field");
		ExtentReport.logTestInfo("Valid username is entered in username field");

		String password = ob.getPropertyData("login.valid.password");
		loginPage.enterPassword(password);
		
		logger.info("valid password is entered in password field");
		ExtentReport.logTestInfo("valid password is entered in password field");

		loginPage.clickRembemberMe();
		driver=loginPage.clickLogin();
		
		HomePage homePage= new HomePage(driver);
		homePage.clickLoggedInUser();
		homePage.clickLogout();
		
		String actual = loginPage.getUserName();
		assertEquals(actual, expected);
		logger.info("TestCase3 is passed");
		ExtentReport.logTestInfo(" checkRememberMe method ended");
		

	}

	@Test
	public void forgetPasswordTest() {
		String expectedTitle = "Check Your Email | Salesforce";

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");
		String username = ob.getPropertyData("login.valid.usename");
		LoginPage loginPage= new LoginPage(driver);
		driver=loginPage.clickForgetPassword();
		
		ForgetPasswordPage forgetPasswordPage=new ForgetPasswordPage(driver);
		forgetPasswordPage.enterUserName(username);

		logger.info("username is entered in username field");
		ExtentReport.logTestInfo("username is entered in username field");
		forgetPasswordPage.clickContinueButton();

		String actualTitle = forgetPasswordPage.getTitleOfPage();
		assertEquals(actualTitle, expectedTitle);
		logger.info("TestCase4A is passed");
		ExtentReport.logTestInfo(" forgetPasswordTest method ended");
		

	}

	@Test
	public void LogInErrorWithInvalidInputs() {
		String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";

		PropertiesUtilities ob = new PropertiesUtilities();
		ob.loadFile("applicationDataProperties");
		String username = ob.getPropertyData("login.invalid.usename");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterUserName(username);
		
		logger.info("invalid username is entered in username field");
		ExtentReport.logTestInfo("invalid username is entered in username field");

		String password = ob.getPropertyData("login.invalid.password");
		loginPage.enterPassword(password);
		
		logger.info("invalid password is entered in password field");
		ExtentReport.logTestInfo("invalid password is entered in password field");
		loginPage.clickLogin();
		
		
		String actual = loginPage.getErrorForInvalidInput();
		assertEquals(actual, expected);
		logger.info("TestCase4B is passed");
		ExtentReport.logTestInfo(" LogInErrorWithInvalidInputs method ended");

	}

}
