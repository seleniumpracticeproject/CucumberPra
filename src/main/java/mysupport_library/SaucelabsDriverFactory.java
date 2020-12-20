package mysupport_library;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SaucelabsDriverFactory {
	private static Properties mobileProperties;
	// static Logger log = Logger.getLogger(SaucelabsDriverFactory.class);

	private SaucelabsDriverFactory() {

	}

	public static WebDriver getSauceRemoteWebDriver(String sauceURL, Browser browser, String browserVersion,
			Platform platformName, SeleniumTestParameters testParameters) {
		WebDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platform", platformName);
		desiredCapabilities.setCapability("version", browserVersion);
		desiredCapabilities.setCapability("browserName", browser);
		desiredCapabilities.setCapability("name", testParameters.getScnarioName());
		try {
			driver = new RemoteWebDriver(new URL(sauceURL), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getSauceAppiumDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String sauceURL, SeleniumTestParameters testParameters) {
		AppiumDriver driver = null;
		mobileProperties = Settings.getInstance();
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (executionPlatform) {
			case ANDROID:
				desiredCapabilities.setCapability("appiumVersion",
						mobileProperties.getProperty("SaucelabAppiumdriverVersion"));
				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());
				desiredCapabilities.setCapability("app", mobileProperties.getProperty("SauceAndroidIdentifier"));
				desiredCapabilities.setCapability("name", testParameters.getScnarioName());
				try {
					driver = new AndroidDriver(new URL(sauceURL), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			default:
				throw new Exception("Unhandled Execution Model");
			}
		} catch (Exception ex) {
			// log.error(ex.getMessage());
			ex.printStackTrace();
		}
		return driver;
	}
}
