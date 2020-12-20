package Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaunchApplication {
	WebDriver driver;
	// static Logger log = Logger.getLogger(common.class);
	// WebDriver driver = DriverManager.getWebDriver();
	WebDriverWait wait = new WebDriverWait(driver, 20);

	public LaunchApplication(WebDriver driver) {
		this.driver = driver;
	}

	public void launchApplication() {
		try {
			driver.get(ReadJson.strGlobalURl);
			driver.manage().window().maximize();
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
