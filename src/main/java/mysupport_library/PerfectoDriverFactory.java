package mysupport_library;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PerfectoDriverFactory {
	private static Properties mobileProperties = Settings.getInstance();
	// static com.sun.org.slf4j.internal.Logger log =
	// Logger.getLogger(MintDriverFactory.class);

	private PerfectoDriverFactory() {

	}

	private static URL getUrl(String remoteUrl) {
		URL url = null;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			// log.error(e.getMessage());
		}
		return url;
	}

	private static DesiredCapabilities getPerfectoExecutionCapabilities() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setPlatform(Platform.ANY);
		desiredCapabilities.setJavascriptEnabled(true);
		mobileProperties = Settings.getInstance();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoUser"));
		return desiredCapabilities;
	}

	public static WebDriver getperfectoRemoteWebDriverByDevicePlatform(String deviceId, String osVersion,
			Browser browser, String remoteUrl, MobileExecutionPlatform executionPlatform) {

		String platformName = "";
		if (executionPlatform.equals("ANDROID")) {
			platformName = "Android";
		} else if (executionPlatform.equals("IOS")) {
			platformName = "ios";
		}
		DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities();
		desiredCapabilities.setBrowserName(browser.getValue());
		desiredCapabilities.setCapability("platformName", platformName);
		desiredCapabilities.setCapability("platformVersion", osVersion);
		URL url = getUrl(remoteUrl);
		return new RemoteWebDriver(url, desiredCapabilities);
	}

	public static WebDriver getperfectoRemoteWebDriverByDeviceModel(String manufacturer, String model, Browser browser,
			String remoteUrl) {

		DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities();
		desiredCapabilities.setCapability("manufacturer", manufacturer);
		desiredCapabilities.setCapability("model", model);
		URL url = getUrl(remoteUrl);
		return new RemoteWebDriver(url, desiredCapabilities);
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getPerfectoAppiumDriver(SeleniumTestParameters testParameters) {
		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("ursr", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
		try {
			switch (testParameters.getMobileExecutionPlatform()) {
			case ANDROID:
				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufaturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_Activity_Name"));
				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					// log.error(e.getMessage());
				}
				break;
			case IOS:
			case WEB_ANDROID:
			case WEB_IOS:
			default:
				throw new Exception("Unhandled Execution Model");
			}
		} catch (Exception ex) {
			// log.error(ex.getMessage());
		}
		return driver;
	}

	public static WebDriver getPerfectoRemoteDriver(SeleniumTestParameters testParameters) {
		RemoteWebDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
		try {
			switch (testParameters.getMobileExecutionPlatform()) {
			case WEB_ANDROID:
				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("browserName", "chrome");
				desiredCapabilities.setCapability("takeScreenshot", true);
				try {
					driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					// log.error(e.getMessage());
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
