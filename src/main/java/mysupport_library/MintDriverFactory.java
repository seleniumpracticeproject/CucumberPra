//package mysupport_library;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.ios.IOSDriver;
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Properties;
//import org.apache.log4j.Logger;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//public class MintDriverFactory {
//
//	private static Properties mobileProperties;
//	static Logger log = Logger.getLogger(MintDriverFactory.class);
//	
//	private MintDriverFactory() {
//		
//	}
//	@SuppressWarnings ("rawtypes")
//		public static AppiumDriver getmintAppiumDriver(MobileExecutionPlatform executionPlatform, String deviceName, String mintHost, String mobileVersion) {
//			AppiumDriver driver =null;
//			mobileProperties = Settings.getInstance();
//			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//			desiredCapabilities.setCapability("username", mobileProperties.getProperty("mintUsername"));
//			desiredCapabilities.setCapability("password", mobileProperties.getProperty("mintPassword"));
//			try {
//				switch(executionPlatform) {
//				case ANDROID:
//					desiredCapabilities.setCapability("platformName", "Android");
//					desiredCapabilities.setCapability("deviceName", "deviceName");
//					desiredCapabilities.setCapability("platformVersion", "mobileVersion");
//					desiredCapabilities.setCapability("app", mobileProperties.getProperty("mintAndroidApplicationName"));
//					try {
//						driver = new AndroidDriver(new URL(mintHost),desiredCapabilities);
//					}
//					catch(MalformedURLExeception e) {
//						e.printStackTrace();
//					}
//					break;
//				case IOS:
//				case WEB_ANDROID:
//				case WEB_IOS:
//				default:
//					throw new Exception ("Unhandled Execution Mode!");
//				}
//			
//		}catch (Exception ex) {
//			ex.printStackTrace();
//			log.error(ex.getMessage());
//		}
//			return driver;
//	}
//	
//	public static WebDriver getmintAppiumRemoteWebDriver(MobileExecutionPlatform executionPlatform, String deviceName, String mintHost, String mobileVersion) {
//		WebDriver driver =null;
//		mobileProperties = Settings.getInstance();
//		String appPath = installApplication(installApp);
//		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//		try {
//			switch(executionPlatform) {
//			case ANDROID:
//				desiredCapabilities.setCapability("platformName", "Android");
//				desiredCapabilities.setCapability("deviceName", "deviceName");
//				desiredCapabilities.setCapability("platformVersion", "Version");
//				desiredCapabilities.setCapability("platformVersion", "appPath");
//				desiredCapabilities.setCapability("appPackage", mobileProperties.getProperty("Applicatin_Package_Name"));
//				desiredCapabilities.setCapability("appActivity", mobileProperties.getProperty("Applicatin_Package_Name"));
//				try {
//					driver = new RemoteWebDriver(new URL(appiumURL),desiredCapabilities);
//				}
//				catch(MalformedURLExeception e) {
//					e.printStackTrace();
//				}
//				break;
//			case IOS:
//			case WEB_ANDROID:
//			case WEB_IOS:
//			default:
//				throw new Exception ("Unhandled Execution Mode!");
//			}
//		
//	}catch (Exception ex) {
//		ex.printStackTrace();
//		log.error(ex.getMessage());
//	}
//		return driver;
//}
//	private static String installApplication(Boolean installApp) {
//		String AppPath = "";
//		try {
//			if(installApp) {
//				File path = new File (mobileProperties.getProperty("AndroidApplicationPath"));
//				AppPath = path.getAbsolutePath();
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//		}
//			return AppPath;
//	}
//	
//}
