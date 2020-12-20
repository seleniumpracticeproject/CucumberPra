//package Library;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.log4j.Logger;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverException;
//import org.openqa.selenium.remote.DriverCommand;
//import org.openqa.selenium.remote.RemoteExecuteMethod;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.server.DriverFactory;
//
////import driverfactory class from support libraries
////import SeleniumTestParameters from support libraries
//import cucumber.api.Scenario;
//
//import mysupport_library.SeleniumTestParameters;
//import io.appium.java_client.IOSDriver;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
//
//public abstract class Master {
//	//static Logger log = Logger.getLogger(DriverFactory.class);
//	Map<String, Object> perfectoCommand = new HashMap<>();
//	protected static Scenario currentScenario;
//	protected static SeleniumTestParameters currentTestParameters;
//	protected static Properties propertiesFileAcess;
//	private static HttpURLConnection httpURLConnect;
//	int responseStatus;
//	int responseCode;
//
//	@SuppressWarnings("rawtypes")
//	protected void openApp(final String appName, AppiumDriver driver) {
//		if (context.equals("NATIVE_APP")) {
//			final Map<String, Object> perfectoCommand = new HashMap<>();
//			perfctoCommand.put("name", appName);
//			driver.executeScript("mobile.application: open", perfectoCommand);
//		}
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void openAppWithIdentifier(final String context, final String identifier, AppimDriver driver) {
//		if (context.equals("NATIVE_APP")) {
//			perfectoCommand.put("identifier", identifier);
//			driver.executeScript("mobile: applicaion: open", perfectoCommand);
//			perfectoCommand.clear();
//		}
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void closeApp(final String context, final String appName, AppiumDriver driver) {
//		if (context.equals("NATIVE_APP")) {
//			perfectoCommand.put("name", appName);
//			try {
//				driver.executeScript("mobile: application: close", perfectoCommand);
//			} catch (final WebDriverException e) {
//
//			}
//		}
//	}
//
//	@SuppressWarnings("rawtypes")
//		protected void closeAppWithIdentifier (final String cntext, final String bundled, AppiumDriver driver) {
//			if (context.equals("NATIVE_APP")) {
//				PerfectoCommand.put("identifier", bundled);
//				try {
//					driver.executeScript("mobile:application :close", perfectoCommand);
//				}cach (final WebDriverException e){
//					
//				}
//
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected byte[] downloadReport(final String type, RemoteWebDriver driver) throws IOException {
//		final String command = "mobile: report : download";
//		final Map<String, String> params = new HashMap<>();
//		params.put(type, type);
//		final String report = (String) (driver).executeScript(command, params);
//		final byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report);
//		return reportBytes;
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected byte [] downloadWTReport (AppiumDriver driver) {
//		final String reportUrl = (String) driver.getCapabilities().getCapability ("windTunnelReportUrl");
//		String returnString = "<html><head><META http-equiv = \"refresh\"content = \"O;URL =";
//		returnString = returnString + reportUrl + "\"></head><bod/></html>";
//		return returnString.getBytes();
//	}
//	
//	@SuppressWarnings("rawtypes")
//	protected Boolean textCheckpoint(final String textToFind, final Integer timeout, AppiumDriver driver) {
//		perfectoCommand.put("content", textToFind);
//		perfectoCommand.put("timeout", timeout);
//		final Object result = driver.executeScript("mobile: checkpoint : text", perfectoCommand);
//		final Boolean resultBool = Boolean.valueOf(result.toString());
//		perfectoCommand.clear();
//		return resutlBool;
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void textClick(final String textToFind, final Integer timeout, AppiumDriver driver) {
//		perfectoCommand.put("content", textToFind);
//		perfectoCommand.put("timeout", timeout);
//		driver.executeScript("mobile: text : select", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void visualScrollToClick(final String label, final Integer threshold, AppiumDriver driver) {
//		perfectoCommand.put("label", label);
//		perfectoCommand.put("trhresold", threshold);
//		perfectoCommand.put("scrolling", "scroll");
//		driver.executeScript("mobile: button-text : click", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void visualScrollToClick(final String label, final Integer timeout, final String threshold,
//			AppiumDriver driver) {
//		perfectoCommand.put("label", label);
//		perfectoCommand.put("trhresold", threshold);
//		perfectoCommand.put("timeout", timeout);
//		driver.executeScript("mobile: button-text : click", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void visualClick(final String label, final Integer timeout, final String threshold,
//			final String labelDirection, final String labelOffset, AppiumDriver driver) {
//		perfectoCommand.put("label", label);
//		perfectoCommand.put("trhresold", threshold);
//		perfectoCommand.put("timeout", timeout);
//		perfectoCommand.put("label.direction", labelDirection);
//		perfectoCommand.put("label.offset", labelOffset);
//		driver.executeScript("mobile: button-text : click", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void imageClick(String imagePath, AppiumDriver driver) {
//		perfectoCommand.put("content", imagePath);
//		perfectoCommand.put("timeout", "5");
//		perfectoCommand.put("screen.top", "0%");
//		perfectoCommand.put("screen.height", "100%");
//		perfectoCommand.put("screen.left", "0%");
//		perfectoCommand.put("screen.width", "100%");
//		driver.executeScript("mobile: image : select", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected Boolean imageCheckpoint(String imagePath, AppiumDriver driver) {
//		perfectoCommand.put("content", imagePath);
//		perfectoCommand.put("timeout", "90");
//		perfectoCommand.put("screen.top", "0%");
//		perfectoCommand.put("screen.height", "100%");
//		perfectoCommand.put("screen.left", "0%");
//		perfectoCommand.put("screen.width", "100%");
//		Object result = driver.executeScript("mobile: image : find", perfectoCommand);
//		final Boolean resultBool = Boolean.valueOf(result.toString());
//		perfectoCommand.clear();
//		return resultBool;
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void putFileOnDevice(final String repositoryFile, final String handsetFile, AppiumDriver driver) {
//		perfectoCommand.put("repositoryFile", repositoryFile);
//		perfectoCommand.put("handsetFile", handsetFile);
//		driver.executeScript("mobile: media : put", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void getFileOnDevice(final String repositoryFile, final String handsetFile, AppiumDriver driver) {
//		perfectoCommand.put("repositoryFile", repositoryFile);
//		perfectoCommand.put("handsetFile", handsetFile);
//		driver.executeScript("mobile: media : get", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void deleteFromDevice(final String handsetFile, AppiumDriver driver) {
//		perfectoCommand.put("handsetFile", handsetFile);
//		driver.executeScript("mobile: media : delete", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void deleteFromRepository(final String repositoryFile, AppiumDriver driver) {
//		perfectoCommand.put("repositoryFile", repositoryFile);
//		driver.executeScript("mobile: media : delete", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void deviceKeypress(final String keyPress, AppiumDriver driver) {
//		perfectoCommand.put("keyPress", keyPress);
//		driver.executeScript("mobile: pressKey", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void swipe(final String x1, final String y1, final String x2, final String y2, AppiumDriver driver) {
//		final List<String> swipeCoordinates = new ArrayList<>();
//		swipeCoordinates.add(x1 + "," + y1);
//		swipeCoordinates.add(x2 + "," + y2);
//		perfectoCommand.put("location", swipeCoordinates);
//		driver.executeScript("mobile: touch : drag", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void swipeTillText(String textToFind, AppiumDriver driver) {
//		perfectoCommand.put("content", textToFind);
//		perfectoCommand.put("scrolling", "scroll");
//		perfectoCommand.put("maxscroll", "10");
//		perfectoCommand.put("next", "SWIPE_UP");
//		driver.executeScript("mobile: text : select", perfectoCommand);
//		perfectoCommand.clear();
//	}
//
//	public void PauseScript (int How_Long_To_Pause) {
//		//convert to seconds
//		How_Long_To_Pause = How_Long_To_Pause*1000;
//		try {
//			Thread.sleep(How_Long_To_Pause);
//		}catch (final InterruptedException ex) {
//			Thread.currentThread().interrupt();
//		}
//	}
//
//	protected static void switchToContext(RemoteWebDriver driver, String context) {
//		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("name", context);
//		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void scrollChecker(IOSDriver driver, String[] list) {
//		for (int i = 0; i < list.length; i++) {
//			MobileElement me = (MobileElement) driver.findElement(By.xpath("//UIAPickerWheel[" + (i + 1) + "]"));
//			int mget = getMonthint(me.getText().split(",")[0]);
//			if (i == 0) {
//				if (mget > getMonthInt(list[i])) {
//					scrollAndSearch(driver, list[i], me, true);
//				} else {
//					scrollAndSearch(driver, list[i], me, false);
//				}
//			} else {
//				if (Ineger.parseInt(me.getText().split(",")[0]) > Integer.parseInt(list[i])) {
//					scrollAndSearch(driver, list[i], me, true);
//				} else {
//					scrollAndSearch(driver, list[i], me, false);
//				}
//			}
//		}
//	}
//
//	// used to get the integer for a month based on the string of the month
//	private int getMonthInt(String month) {
//		int monthInt = 0;
//		switch (month) {
//		case "Jan":
//			monthInt = 1;
//			break;
//		case "January":
//			monthInt = 1;
//			break;
//		case "Feb":
//			monthInt = 2;
//			break;
//		case "February":
//			monthInt = 2;
//			break;
//		case "Mar":
//			monthInt = 3;
//			break;
//		case "March":
//			monthInt = 3;
//			break;
//		case "Apr":
//			monthInt = 4;
//			break;
//		case "April":
//			monthInt = 4;
//			break;
//		case "May":
//			monthInt = 5;
//			break;
//		case "Jun":
//			monthInt = 6;
//			break;
//		case "June":
//			monthInt = 6;
//			break;
//		case "Jul":
//			monthInt = 7;
//			break;
//		case "July":
//			monthInt = 7;
//			break;
//		case "Aug":
//			monthInt = 8;
//			break;
//		case "Agust":
//			monthInt = 8;
//			break;
//		case "Sep":
//			monthInt = 9;
//			break;
//		case "September":
//			monthInt = 9;
//			break;
//		case "Oct":
//			monthInt = 10;
//			break;
//		case "October":
//			monthInt = 10;
//			break;
//		case "Nov":
//			monthInt = 11;
//			break;
//		case "November":
//			monthInt = 11;
//			break;
//		case "Dec":
//			monthInt = 12;
//			break;
//		case "December":
//			monthInt = 12;
//			break;
//
//		}
//		return monthInt;
//	}
//
//	@SuppressWarnings("rawtypes")
//	private void scrollAndSearch(IOSDriver driver, String value, MobileElement me, Boolean direction) {
//		String x = getLocationX(me);
//		String y = getLocationY(me);
//		while (!driver.findElementByXpath(getXpathFromElement(me)).getText().contains(value)) {
//			swipe(driver, x, y, direction);
//		}
//	}
//
//	@SuppressWarnings("rawtypes")
//	private void scrollAndSearch(IOSDriver driver, String start, String end, Boolean up) {
//		String direction;
//		if (up) {
//			direction = start + "," + (Integer.parseInt(end) + 70);
//		} else {
//			direction = start + "," + (Integer.parseInt(end) - 70);
//		}
//		Map<String, Object> params1 = new HashMap<>();
//		params1.put("loation", start + "," + end);
//		driver.executeScript("mobile:touch:tap", params1);
//		Map<String, Object> params2 = new HashMap<>();
//		List<String> coordinates2 = new ArrayList<>();
//		coordinates2.add(direction);
//		params2.put("loaction", coordinates2);
//		params2.put("auxiliary", "notap");
//		params2.put("duration", "3");
//		driver.executeScript("mobile:touch:drag", params2);
//		Map<String, Object> params3 = new HashMap<>();
//		params2.put("loaction", "direction");
//		params2.put("operation", "up");
//		driver.executeScript("mobile:touch:tap", params3);
//	}
//
//	// get the objects x location in pixels
//	private String getLocationX(MobileElement me) {
//		int x = me.getLocation().x;
//		int width = (Integer.parseInt(me.getAttribut("width")) / 2) + x;
//		return width + "";
//	}
//
//	private String getLocationY(MobileElement me) {
//		int y = me.getLocation().y;
//		int height = (Integer.parseInt(me.getAttribut("width")) / 2) + y;
//		return height + "";
//	}
//
//	// parse webelement to retrieve the xpath used for identification
//	private String getXpathFromElement(MobileElement me) {
//		return (me.toString().split("->xpath:")[1]).substring(0,(me.toString().split("-> xpath:")[1])).length()-1);
//	}
//
//	@SuppressWarnings("rawtypes")
//	protected void drawLetter(final String letter, AppiumDriver driver) {
//		final List<String> coordinates = new ArrayList<>();
//		switch (Letter) {
//		case "A":
//			break;
//		case "B":
//			break;
//		case "C":
//			break;
//		case "D":
//			break;
//		case "E":
//			break;
//		case "F":
//			break;
//		case "G":
//			break;
//		case "H":
//			break;
//		case "I":
//			break;
//		case "J":
//			break;
//		case "K":
//			break;
//		case "L":
//			break;
//		case "M":
//			break;
//		case "N":
//			break;
//		case "O":
//			break;
//		case "P":
//			break;
//		case "Q":
//			break;
//		case "R":
//			break;
//		case "S":
//			break;
//		case "T":
//			break;
//		case "U":
//			break;
//		case "V":
//			break;
//		case "W":
//			break;
//		case "X":
//			coordinates.add("42%,40%");
//			coordinates.add("42%,40%");
//			perfectoCommand.put("location", coordinates);
//			driver.executeScript("mobile:touch:drag", perfectoCommand);
//			perfectoCommand.clear();
//			coordinates.clear();
//			break;
//		case "Y":
//			break;
//		case "Z":
//			break;
//
//		}
//	}
//
//	protected void brokenLinkValidator(String Url) {
//		urlLinkStatus(validationOfLinks(Url));
//	}
//
//	private String[] validationOfLinks(String urlToValidate) {
//		String[] responseArray = new String[3];
//		try {
//			URL url = new URL(urlToValidate);
//			httpURLConnect = (HttpURLConnection) url.openConnection();
//			httpURLConnect.setConnectTimeout(3000);
//			httpURLConnect.connect();
//			responseStatus = httpURLConnect.getResponseCode();
//			responseCode = responseStatus / 100;
//		} catch (Exception e) {
//
//		}
//		responseArray[0] = urlToValidate;
//		responseArray[1] = String.valueOf(responseCode);
//		responseArray[3] = String.valueOf(responseStatus);
//		return responseArray;
//	}
//
//	public void urlLinkStatus(String[] responseArray) {
//		try {
//			String linkValue = responseArray[0];
//			String responseValue = responseArray[1];
//			responseCode = Integer.valueOf(responseValue);
//			String responseStatus = responseArray[2];
//			switch (responseCode)
//			//case2:
//				currentScenario.write(linkValue + ":Response code:" + responseStatus + "-OK" + "&STATUS:PASS");
//			break;
//			case2:
//				currentScenario.write(linkValue + ":unknown Response code:" + responseStatus + "&STATUS:FAIL");
//			break;
//			case3:
//				currentScenario.write(linkValue + ":Response code:" + responseStatus + "&-Client error & STATUS:FAIL");
//			break;
//			case4:
//				currentScenario.write(linkValue + ":Response code:" + responseStatus + "&-Internal Server Error");
//			break;
//
//		}catch (Exception e) {
//			
//		}finally {
//			httpURLConnect.disconnect();
//		}
//	}
//	
//	//webdriver object creation
//	public static WebDriver getWebDriver() {
//		if(WebDriver.get() == null) {
//			//this is need when running tests from IDE
//			log.info("thread has no WebDriver, creating new one");
//			setWebDriver(DriverFactory.createInstanceWebDriver(null));
//		}
//		log.debug("Getting instance of remote driver" + WebDriver.get().getClass());
//		return WebDriver.get();
//	}
//
//	//appium driver object creation
//	
//	@SuppressWarnings("rawtypes")
//	public static AppiumDriver getAppiumDriver() {
//		if(appiumDriver.get() == null) {
//			//this is need when running tests from IDE
//			log.info("thread has no AppiumDriver, creating new one");
//			setAppiumDriver(DriverFactory.createInstance(null));
//		}
//		log.debug("Getting instance of remote driver" + appiumDriver.get().getClass());
//		return appiumDriver.get();
//	}
//	
//	@SuppressWarnings("rawtypes")
//	public static void setAppiumDriver(AppiumDriver driver) {
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		DriverManager.appiumDriver.set(driver);
//	}
//	@SuppressWarnings("rawtypes")
//	public static void setWebDriver(WebDriver driver) {
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		DriverManager.webDriver.set(driver);
//	}
//	@SuppressWarnings("rawtypes")
//	public static void setTestParameters(SeleniumTestParameters testParameters) {
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		DriverManager.testParameters.set(testParameters);
//	}
//	public static SeleniumTestParameters getTestParameters() {
//		return testParameters.get();
//	}
//}
