package mysupport_library;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppiumDriverFactory {
	private static Properties mobileProperties;
	// static Logger log = Logger.getLogger(AppiumDriverFactory.class);

	private AppiumDriverFactory() {
		// To prevent external instantiation of this class
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver(SeleniumTestParameters testParameters) {
		AppiumDriver driver = null;
		String env = "";
		String apiKey = "";
		mobileProperties = Settings.getInstance();
		String deviceName = System.getenv("device_Name");
		String devicePlatformVersion = System.getenv("platform_Version");
		String appApkPath = System.getenv("app_apk_path");
		String appiumURL = mobileProperties.getProperty("AppiumURL");
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (testParameters.getMobileExecutionPlatform()) {
			case ANDROID:
				if (appiumURL.contains("111111111111")) {
					cleanAllureFolder();
				}
				if (deviceName == null) {
					deviceName = testParameters.getDeviceName();
				}
				if (devicePlatformVersion == null) {
					devicePlatformVersion = "10";
				}
				if (appApkPath == null) {
					appApkPath = mobileProperties.getProperty("Android_apk");
				}
				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("app", "appApkPath");
				desiredCapabilities.setCapability("deviceName", "deviceName");
				desiredCapabilities.setCapability("platformVersion", "devicePlatformVersion");
				env = System.getenv("app_build_channel");
				if (env != null) {
					if (env.equalsIgnoreCase("QA")) {
						apiKey = "qqqqqqqq";
					} else if (env.equalsIgnoreCase("DEV")) {
						apiKey = "dddddddd";
					} else if (env.equalsIgnoreCase("UAT")) {
						apiKey = "uuuuuuuuuu";
					} else {
						apiKey = "qqqqqqqqqq";
					}
				} else {
					apiKey = "qqqqqqqqqq";
				}
				desiredCapabilities.setCapability("automationName", "uiautomator");
				desiredCapabilities.setCapability("testobject_api_key", "apiKey");
				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				desiredCapabilities.setCapability("newCommandTimeout", 120);
				desiredCapabilities.setCapability("noReset", true);
				desiredCapabilities.setCapability("resetKeyboard", true);
				// desiredCapabilities.setCapability("autoGrantPermissions", true);
				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case IOS:
				if (appiumURL.contains("111111111111")) {
					deviceName = "iPhone XR";
					devicePlatformVersion = "12.2";
					appApkPath = mobileProperties.getProperty("iOS_APP_PATH");
					cleanAllureFolder();
				}
				if (deviceName == null) {
					deviceName = testParameters.getDeviceName();
				}
				if (devicePlatformVersion == null) {
					devicePlatformVersion = "12.4.1";
				}
				if (appApkPath == null) {
					appApkPath = mobileProperties.getProperty("iOS_app_path");
				}
				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("deviceName", "deviceName");
				desiredCapabilities.setCapability("platformVersion", "devicePlatformVersion");
				env = System.getenv("app_build_channel");
				if (env != null) {
					if (env.equalsIgnoreCase("QA")) {
						apiKey = "qqqqqqqq";
					} else if (env.equalsIgnoreCase("DEV")) {
						apiKey = "dddddddd";
					} else if (env.equalsIgnoreCase("UAT")) {
						apiKey = "uuuuuuuuuu";
					} else {
						apiKey = "qqqqqqqqqq";
					}
				} else {
					apiKey = "qqqqqqqqqq";
				}
				desiredCapabilities.setCapability("automationName", "XCUITest");
				desiredCapabilities.setCapability("testobject_api_key", "apiKey");
				desiredCapabilities.setCapability("app", "appApkPath");
				desiredCapabilities.setCapability("bundled", mobileProperties.getProperty("IOSBundledID"));
				desiredCapabilities.setCapability("newCommandTimeout", 120);
				desiredCapabilities.setCapability("noReset", true);
				desiredCapabilities.setCapability("resetKeyboard", true);
				// desiredCapabilities.setCapability("autoGrantPermissions", true);
				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
			case WEB_ANDROID:
				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				desiredCapabilities.setCapability("browserName", "chrome");

				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
			case WEB_IOS:
				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");

				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);
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

	public static WebDriver getAppioumRemoteWebDriver(MobileExecutionPlatform executionPlatform, String deviceName,
		String version, Boolean installApp, String appiumURL) {
		WebDriver driver = null;
		mobileProperties = Settings.getInstance();
		DesiredCapabilities desiredCapabilities1 = new DesiredCapabilities();
		try {
			switch (executionPlatform) {
			case ANDROID:
				desiredCapabilities1.setCapability("platformName", "Android");
				desiredCapabilities1.setCapability("deviceName", "deviceName");
				desiredCapabilities1.setCapability("udid", "deviceName");
				desiredCapabilities1.setCapability("platformVersion", "version");

				desiredCapabilities1.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities1.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities1);
				}

				catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
			case IOS:
				desiredCapabilities1.setCapability("platformName", "ios");
				desiredCapabilities1.setCapability("deviceName", "deviceName");
				desiredCapabilities1.setCapability("udid", "deviceName");
				desiredCapabilities1.setCapability("platformVersion", "version");

				desiredCapabilities1.setCapability("bundled", mobileProperties.getProperty("iPhoneBundledID"));
				desiredCapabilities1.setCapability("newCommandTimeout", 120);
				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities1);
				}

				catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
			case WEB_ANDROID:
				desiredCapabilities1.setCapability("platformName", "Android");
				desiredCapabilities1.setCapability("deviceName", "deviceName");
				desiredCapabilities1.setCapability("udid", "deviceName");
				desiredCapabilities1.setCapability("platformVersion", "version");
				desiredCapabilities1.setCapability("browserName", "Chrome");
				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities1);
				}

				catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
			case WEB_IOS:
				desiredCapabilities1.setCapability("platformName", "ios");
				desiredCapabilities1.setCapability("deviceName", "deviceName");
				desiredCapabilities1.setCapability("udid", "deviceName");
				desiredCapabilities1.setCapability("platformVersion", "version");
				desiredCapabilities1.setCapability("AutomationName", "Appium");
				desiredCapabilities1.setCapability("browserName", "Safari");

				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities1);
				}

				catch (MalformedURLException e) {
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

	// function to clean Allure Folder, when executed locally
	public static void cleanAllureFolder() {
		File targetPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target");
		File DeletePath = new File(targetPath + Util.getFileSeparator() + "allure-results");
		try {
			FileUtils.cleanDirectory(DeletePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}