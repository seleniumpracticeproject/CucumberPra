package mysupport_library;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class DriverFactory {
	//static com.sun.org.slf4j.internal.Logger log = Logger.getLogger(DriverFactory.class);
	private static Properties mobileProperties = Settings.getInstance();

	@SuppressWarnings("rawtypes")
	public static AppiumDriver createInstance(SeleniumTestParameters testParameters) {
		AppiumDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {
			case MOBILE:
				driver = AppiumDriverFactory.getAppiumDriver(testParameters);
				break;
			case PERFECTO:
				driver = PerfectoDriverFactory.getPerfectoAppiumDriver(testParameters);
				break;
			case SAUCELABS:
				driver = SaucelabsDriverFactory.getSauceAppiumDriver(testParameters.getMobileExecutionPlatform(),
						testParameters.getDeviceName(), mobileProperties.getProperty("SauceHost"), testParameters);
				break;
			case MINT:
			default:
				throw new Exception("Unhandled Execution Mode!");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			//log.error(ex.getMessage());
		}
		return driver;
	}

public static WebDriver createInstanceWebDriver(SeleniumTestParameters testParameters) {
	WebDriver driver = null;
	try {
		switch (testParameters.getExecutionMode()) {
		case LOCAL:
			driver = WebDriverFactory.getWebDriver(testParameters.getBrowser());
			break;
		case REMOTE:
			driver = WebDriverFactory.getRemoteWebDriver(testParameters.getBrowser(), testParameters.getBrowserVersion(), testParameters.getPlatform(), mobileProperties.getProperty("RemoteURL"));
		case PERFECTO:
			driver = PerfectoDriverFactory.getPerfectoRemoteDriver(testParameters);
			break;
		case SAUCELABS:
			driver = SaucelabsDriverFactory.getSauceRemoteWebDriver(mobileProperties.getProperty("SauceHost"), testParameters.getBrowser(), testParameters.getBrowserVersion(), testParameters.getPlatform(), testParameters);
			break;
			default:
				throw new Exception ("Unhandled Execution Mode!");
		}
	}catch(Exception ex) {
		ex.printStackTrace();
		//log.error(ex.getMessage());
	}
	return driver;
}

private WebDriver createRemoteDriver() throws Exception{
	WebDriver driver = null;
	try {
		DesiredCapabilities caps = new DesiredCapabilities();
		final String URL = "USE SOME URL";
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("browserVersion", "81.0");
driver  = new RemoteWebDriver (new java.net.URL(URL), caps);
if(WebDriverFactory.getBrowserWindowSize())
	driver.manage().window().maximize();
WebDriverFactory.getImplicitlyWait(TimeUnit.SECONDS);
	}catch (Exception ex) {
		ex.printStackTrace();
	}
	return driver;
}
}
