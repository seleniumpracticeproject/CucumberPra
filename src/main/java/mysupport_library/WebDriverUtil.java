package mysupport_library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class WebDriverUtil {
	private WebDriver driver;

	public WebDriverUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitUntilElementLocated(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitUntilElementVisible(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitUntilElementEnabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitUntilElementDisabled(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, timeOutInSeconds))
				.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
	}

	public void selectListItem(By by, String item) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByVisibleText(item);
	}

	public Boolean objectExists(By by) {
		if (driver.findElements(by).size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean isTextpresent(String textPattern) {
		if (driver.findElement(By.cssSelector("BODY")).getText().matches(textPattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isAlertPresent(long timeOutInSeconds) {
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;

		}
	}
}
