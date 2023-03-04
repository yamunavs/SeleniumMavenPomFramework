package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(id = "username")
	WebElement username;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(id = "Login")
	WebElement loginButton;
	@FindBy(id = "error")
	WebElement error;
	@FindBy(id = "rememberUn")
	WebElement rememberMe;
	@FindBy(id = "username_container")
	WebElement rememberedUserName;
	@FindBy(id = "forgot_password_link")
	WebElement forgetpassword;
	@FindBy(id = "error")
	WebElement errorInvalid;

	public void enterUserName(String data) {
		waitToLoadWebPage(username);
		enterText(username, data, "username");
	}

	public void enterPassword(String data) {
		enterText(password, data, "password");
	}

	public WebDriver clickLogin() {
		clickElement(loginButton, "loginButton");
		return driver;
	}

	public String getErrorMessage() {
		waitToLoadWebPage(error);
		return getTextFromElement(error);
	}

	public void clickRembemberMe() {
		clickElement(rememberMe, "rememberMe");
	}

	public String getUserName() {
		waitToLoadWebPage(rememberedUserName);
		return getTextFromElement(rememberedUserName);
	}

	public WebDriver clickForgetPassword() {
		clickElement(forgetpassword, "forgetpassword");
		return driver;
	}

	public String getErrorForInvalidInput() {
		waitToLoadWebPage(errorInvalid);
		return getTextFromElement(errorInvalid);
	}
}
