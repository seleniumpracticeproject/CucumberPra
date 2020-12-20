package mysupport_library;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
	private static Properties mobileProperties = Settings.getInstance();
	//static com.sun.org.slf4j.internal.Logger log = Logger.getLogger(MintDriverFactory.class);

	private WebDriverFactory() {

	}

	public static WebDriver getWebDriver(Browser browser) {
		// TODO Auto-generated method stub
		return null;
	}

	public static WebDriver getRemoteWebDriver(Browser browser, String browserVersion, Platform platform,
			String property) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean getBrowserWindowSize() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void getImplicitlyWait(TimeUnit seconds) {
		// TODO Auto-generated method stub
		
	}
}
