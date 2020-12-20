package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage1 {
//here we have to place locators 
//here we have to create some methods to perform the actions on the locators
	public WebDriver driver;

	By email = By.cssSelector("[id='email']");
	By password = By.cssSelector("[type='password']");
	By login = By.cssSelector("[type='submit']");

	public LoginPage1(WebDriver driver) {
		this.driver = driver;
		
	}

	public WebElement getEmail() {
		return driver.findElement(email);

	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}

	public WebElement getLogin() {
		return driver.findElement(login);
	}

}
