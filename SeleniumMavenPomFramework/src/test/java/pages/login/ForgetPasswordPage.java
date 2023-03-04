package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class ForgetPasswordPage extends BasePage{
	
	public ForgetPasswordPage(WebDriver driver) {
		super(driver);
		
	}
@FindBy(id = "un") WebElement userName;
@FindBy(id = "continue")WebElement continueButton;

public void enterUserName(String data) {
	enterText(userName, data, "userName");
}
public void clickContinueButton() {
	clickElement(continueButton, "continueButton");
}	
public String getTitleOfPage() {
	return getTitleOfThisPage();
}
	
}
