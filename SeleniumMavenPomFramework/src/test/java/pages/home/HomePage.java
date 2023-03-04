package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class HomePage extends BasePage{
	
	@FindBy(id = "userNav") WebElement LoggedInUser;
	@FindBy(xpath = "//a[@title='Logout']") WebElement logout;
	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
    public void waitUntilLoggedInUserAppear() {
		waitToLoadWebPage(LoggedInUser);
	}
    public void clickLoggedInUser() {
    	waitToLoadWebPage(LoggedInUser);
		clickElement(LoggedInUser, "LoggedInUser");
	}
	
	public void clickLogout() {
		clickElement(logout, "logout");
	}
	
	
	
	 public String getTitleOfPage() {
		
		return  getTitleOfThisPage();
	}
}
