package mysupport_library;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


//class to encapsulate utility functions of the framework
public class Util {
	WebDriver driver;
	//static Logger log = Logger.getLogger(Util.class);
	private static Pattern pattern;
	private static Matcher matcher;
	private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
	private static final Integer PAGELOADTIME = 120;
	private static final Integer OBJLOADTIME = 30;
	private static final Integer OBJLOADTIMENOEXIST = 10;
	@FindAll({ @FindBy(how = How.ID, using = "loadingDiv"),
			@FindBy(how = How.XPATH, using = "//div[contains(@class, '........')]") })
	static WebElement loadingSpinner;

	// making constructor for class
	private Util() {
		// to prevent external instantiation of this class
	}

	// :::::::making directories::::::::
	// ****************FUNCTION START************************************
	public static String getResultPath() {
		File path = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "Results");
		if (!path.isDirectory()) {
			path.mkdirs();
		}
		return path.toString();
	}

	public static String getOldResultPath() {
		File path = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "ResultsOld");
		if (!path.isDirectory()) {
			path.mkdirs();
		}
		return path.toString();
	}

	public static String getTargetPath() {
		File targetPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator()
				+ "cucumber-report");
		return targetPath.toString();
	}

	public static String getAllurePath() {
		File targetPath = new File(
				Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator() + "site");
		return targetPath.toString();
	}

	public static String getAllureSourcePath() {
		File targetPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator()
				+ "allure-results");
		return targetPath.toString();
	}

	public static String getAbsolutePath() {
		System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		return relativePath;
	}

	public static String getFileSeparator() {
		System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
		return System.getProperty("file.separator");
	}

	public static String getAllureDestPath() {
		String targetPath = TimeStamp.getInstanceAllure();
		return targetPath.toString();
	}
	// ****************FUNCTION END************************************

	// :::::::::::taking screen shots::::::::::
	// ****************FUNCTION START**********************
	public static byte[] takeScreenshot(WebDriver driver) {
		
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		if (driver == null) {
			throw new RuntimeException("Report.driver is nto initialized!");
		}
		if (driver.getClass().getSimpleName().equals("HtmlUnitDriver") || driver.getClass().getGenericSuperclass()
				.toString().equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver")) {
			return null;// screenshots is not supported in headless mode
		}
		if (driver.getClass().getSimpleName().equals("RemoteWebDriver")) {
			Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
			if (capabilities.getBrowserName().equals("htmlunit")) {
				return null;// screenshots not supported in headless mode
			}
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			return (scrShot.getScreenshotAs(OutputType.BYTES));
		} else {
			return (scrShot.getScreenshotAs(OutputType.BYTES));
		}
	}

//	public static byte[] takeScreenshots(WebDriver driver) throws IOException {
//		File srcFile = new File(driver.client.capture());
//		int i = 0;
//		while (!srcFile.exists()) {
//			try {
//				Thread.sleep(1000);
//				i++;
//				if (i > 30) {
//					break;
//				}
//			} catch (InterruptedException ex) {
//				ex.printStackTrace();
//			}
//		}
//		byte[] bFile = Files.readAllBytes(new File(srcFile.toString()).toPath());
//		return bFile;
//
//	}

public static String base64Screenshot(WebDriver driver) {
	String strSnapshot = "";
	TakesScreenshot scrShot =((TakesScreenshot)driver);
	try {
		strSnapshot =  "data:image/png;base64," + scrShot.getScreenshotAs(OutputType.BASE64);
	}catch (Exception e) {
		//handle exception
	}
	return strSnapshot;
}
//****************FUNCTION END************************************

// function to return the current time
public static Date getCurrentTime() {
	Calendar calendar = Calendar.getInstance();
	return calendar.getTime();
}

//local date example	
	public void dateExample() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
	}

//local date example	
	public void InclusivedateExample() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd MM yy", Locale.US);
		for (int j = 0; j < 10; j++) {
			Calendar d = Calendar.getInstance();
			d.setTime(date);
			d.add(Calendar.DATE, j);
			String dep = dateFormat.format(d.getTime()).toUpperCase();
			d.add(Calendar.DATE, 1);
			String arr = dateFormat.format(d.getTime()).toUpperCase();
		}
	}

	// function to ruturn the current mongth
	public static Month getCurrentMonth() {
		LocalDate currentDate = LocalDate.now();
		Month m = currentDate.getMonth();
		return m;
	}

	// function to return first day of the month
	public static int getfirstDayofTheMonth() {
		Calendar cal = Calendar.getInstance();
		int firstDayOftheMonth = cal.getActualMinimum(Calendar.DATE);
		return firstDayOftheMonth;
	}

	// function to return the last day of the month
	public static int getLastDayofTheMonth() {
		Calendar cal = Calendar.getInstance();
		int lastDayOftheMonth = cal.getActualMaximum(Calendar.DATE);
		return lastDayOftheMonth;
	}

	public static DayOfWeek DayOfWeek(){
		LocalDate currentDate = LocalDate.now();
		DayOfWeek dow = currentDate.getDayOfWeek();
		return dow;
	}

	// function to return the current year
	public static int getCurrentYear() {
		LocalDate currentDate = LocalDate.now();
		int curYear = currentDate.getYear();
		return curYear;
	}

	// function to return the current time formatted as per the date format string
	// setting
	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime()).toUpperCase();
	}

	public static String getCurrentFormattedTimePlusOne(String dateFormatString, String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		if (Calendar.DATE <= 15) {
			calendar.set(Calendar.DATE, 16);
		} else {
			calendar.setTime(dateFormat.parse(date));
			calendar.add(Calendar.DATE, 1);
		}
		return dateFormat.format(calendar.getTime()).toUpperCase();
	}

	// function to format the given time variable as specified by the dateformat
	// string setting
	public static String getFormattedTime(Date time, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.format(time);
	}

	// function to get the time difference between 2 {@link Date} variables in
	// minutes/seconds format
	public static String getTimeDifference(Date startTime, Date endTime) {
		long timeDifferenceSeconds = (endTime.getTime() - startTime.getTime()) / 1000;
		// convert from milliseconds to seconds
		long timeDifferenceMinutes = timeDifferenceSeconds / 60;
		String timeDifferenceDetailed;
		if (timeDifferenceMinutes >= 60) {
			long timeDifferenceHours = timeDifferenceMinutes / 60;
			timeDifferenceDetailed = Long.toString(timeDifferenceHours) + "hour(s),"
					+ Long.toString(timeDifferenceMinutes % 60) + "minute(s),"
					+ Long.toString(timeDifferenceSeconds % 60) + "second(s)";
		} else {
			timeDifferenceDetailed = Long.toString(timeDifferenceMinutes) + "minute(s),"
					+ Long.toString(timeDifferenceSeconds % 60) + "second(s)";
		}
		return timeDifferenceDetailed;
	}

	// function to enter the text
	public static void enterText(WebDriver driver, WebElement element, String text) {
		try {
			if (Util.waitForElementClickable(driver, element)) {
				element.click();
				element.clear();
				element.sendKeys(text);
			} else {
				throw new NoSuchElementException("unable to find the input element");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function to navigate back
	public static void navigateBack(WebDriver driver) {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function to press any key
	public static void pressKey(WebDriver driver, WebElement element, Keys tab) {
		try {
			if (Util.waitFor(driver, element)) {
				element.sendKeys(tab);
			} else {
				throw new NoSuchElementException("unable to press the input element");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Boolean waitForElementClickable(WebDriver driver, WebElement ele) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIME);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			throw ex;
		}
		return objectPresence;
	}

	// function to wait to load the object
	public static Boolean waitFor(WebDriver driver, WebElement ele) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return objectPresence;
	}

	// function to wait to load object
	public static void waitForLoad(WebDriver driver) {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};

			WebDriverWait wait = new WebDriverWait(driver, PAGELOADTIME);
			wait.until(pageLoadCondition);
			Util.defaultwait(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function for the default wait
	public static void defaultwait(int intWaitVal) {
		try {
			Thread.sleep(intWaitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// function to validate text
	public static Boolean validateText(WebDriver driver, WebElement element, String strText, boolean blnExactMatch) {
		Boolean blnTextMatch = false;
		try {
			if (Util.waitFor(driver, element)){
				String eleText = element.getText().trim();
				if(eleText.length()<1) {
					eleText = element.getAttribute("value");
				}
				if((blnExactMatch && eleText.equalsIgnoreCase(strText))||(!blnExactMatch && eleText.contains(strText))) {
				blnExactMatch =true;
			}else {
				System.out.println("Expected text: "+ strText+ ". Actual text:"+ eleText);
			}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return blnExactMatch;
		}

	// function to click the button
	public static void ClickButton(WebDriver driver, WebElement ele) {
		try {
			if(Util.waitForElementClickable(driver, ele)) {
				ele.click();
				//log.info("Object clicked:" + ele.getText());
			}else {
				throw new NoSuchElementException("Unable to find Button to Tap");
			}
		}catch (Exception e) {
			try {
				ele.sendKeys("");
				ele.sendKeys("\n");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	// function to click the element
	public static void ClickElement(Object driver, WebElement ele) {
		try {
			Actions actn = new Actions((WebDriver) driver);
			actn.moveToElement(ele).click().build().perform();
		} catch (Exception e) {
			try {
				ele.sendKeys("");
				ele.sendKeys("\n");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	// function to select dropdown
	public static void SelectFromDropDown(WebDriver driver, By ele, String txtToBeSelected) {
		try {
			WebElement element = driver.findElement(ele);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Select select = new Select(element);
			select.selectByVisibleText(txtToBeSelected);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function to select drop down using index
	public static void SelectFromDropDownIndex(WebDriver driver, WebElement element) {
		try {
			// WebElement element = driver.findElement(ele);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Select select = new Select(element);
			select.selectByIndex(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function to select drop down using value
	public static void SelectFromDropDownByVal(WebDriver driver, By ele, String txtToBeSelected) {
		try {
			WebElement element = driver.findElement(ele);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Select select = new Select(element);
			select.selectByIndex(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function to select drop down using value
	public static void SelectFrmDropDown(WebDriver driver, WebElement ele, String txtToBeSelected, boolean exactMatch) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
			Select select = new Select(ele);
			if (exactMatch) {
				select.selectByVisibleText(txtToBeSelected);
			} else {
				List<WebElement> option = select.getOptions();
				for (int j = 0; j < option.size(); j++) {
					if (option.get(j).getText().toLowerCase().trim().contains(txtToBeSelected.trim().toLowerCase())) {
						select.selectByIndex(j);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function to click the text
	public static void ClickOnText(WebDriver driver, List<WebElement> elementList, String strText,
			boolean blnExactMatch) {
		try {
			WebElement element = null;
			for (int i = 0; i<elementList.size(); i++) {
				element = elementList.get(i);
				String elementTxt = element.getText();
				if (elementTxt.length() < 1) {
					elementTxt = element.getAttribute("value");
				}
				if ((blnExactMatch && elementTxt.equalsIgnoreCase(strText))
						|| (!blnExactMatch && elementTxt.contains(strText))) {
					if (Util.waitForElementClickable(driver, element)) {
						//log.info("Tap on text:" + strText);
						element.click();
					} else {
						throw new NoSuchElementException("Unable to find Button to Tap");
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function to wiat to load the object
	public static WebElement waitFor(WebDriver driver, By ele) {
		WebElement elementrtn = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			if (element != null) {
				elementrtn = element;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return elementrtn;
	}

	public static String updateContractMonth(String contractMonth, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		String updatedContractMonth = contractMonth;
		List<String> dateSplit = new ArrayList<>();
		String currentDateTime = getCurrentFormattedTime(dateFormatString);
		String currDate = currentDateTime.split("-")[0];
		if (Integer.parseInt(currDate) >= 27) {
			calendar.add(Calendar.MONTH, 1);
			String date = dateFormat.format(calendar.getTime()).toUpperCase();
			dateSplit = Arrays.asList(date.split("-"));
			updatedContractMonth = dateSplit.get(1) + dateSplit.get(2);
		}
		return updatedContractMonth;
	}

	// function to set clip-board text
	public String setClipboardText(String strData) {
		try {
			Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
			strData = (String) c.getData(DataFlavor.stringFlavor);
			System.out.println("printed" + strData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strData;
	}

	// function to get clip-board text
	public String getClipboardText() {
		String strData = "";
		try {
			Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
			strData = (String) c.getData(DataFlavor.stringFlavor);
			System.out.println("printed" + strData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strData;
	}

	// function to get the date
	public static String GetDate(String strDate) {
		try {
			String arrElmnt = null;
			Integer intDate = null;
			if (strDate.toUpperCase().contains("TODAY")) {
				Calendar cal = Calendar.getInstance();
				if (strDate.contains("+")) {
					arrElmnt = strDate.substring(strDate.indexOf("+") + 1);
					intDate = Integer.parseInt(arrElmnt);
					cal.add(Calendar.DATE, intDate);
				} else if (strDate.contains("-")) {
					arrElmnt = strDate.substring(strDate.indexOf("-") + 1);
					intDate = Integer.parseInt(arrElmnt);
					cal.add(Calendar.DATE, -intDate);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MM dd");
				strDate = sdf.format(cal.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}

	// function to wait to load the element
	public static Boolean waitFor(WebDriver driver, WebElement ele, int intLoadingTime) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, intLoadingTime);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return objectPresence;
	}

	// function to wait to load the element
	public static Boolean waitForNoExist(WebDriver driver, WebElement ele) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIMENOEXIST);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return !objectPresence;
	}

	// function to wait to load the element to be clickable
	public static Boolean waitForElementClickable(WebDriver driver, String xpath) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIME);
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath))));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			throw ex;
		}
		return objectPresence;
	}

	public static String objAttribute(WebDriver driver, String attribute, WebElement objName) {
		String objAttr = "";
		try {
			if (Util.waitFor(driver, objName)) {
				objAttr = objName.getAttribute(attribute);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return objAttr;
	}

	// function to validate for element enable
	public static Boolean valObjEnabled(WebDriver driver, WebElement objName) {
		Boolean blnEnable = false;
		try {
			if (Util.waitFor(driver, objName)) {
				if (objName.isEnabled()) {
					blnEnable = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return blnEnable;
	}

	public static Boolean validate24HoursTimeFormat(String attrText) {
		try {
			pattern = Pattern.compile(TIME24HOURS_PATTERN);
			matcher = pattern.matcher(attrText);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return matcher.matches();
	}

	public static void specialClick(WebDriver driver, WebElement elmnt) {
		try {
			new Actions(driver).moveToElement(elmnt).click().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Boolean setDataSpecial(WebDriver driver, WebElement elmnt, String strText) {
		String strActualText = "";
		boolean blnDataSetStatus = false;
		try {
			if (Util.waitForElementClickable(driver, elmnt)) {
				elmnt.click();
				elmnt.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				elmnt.sendKeys(Keys.DELETE);
				elmnt.sendKeys(strText);
			}
			if (elmnt.getAttribute("value").equalsIgnoreCase(strText)) {
				blnDataSetStatus = true;
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return blnDataSetStatus;
	}

	public static WebElement waitForObjToLoad(WebDriver driver, By ObjName) {
		long t0, t1;
		long intTimeInMillis = 15000;
		boolean blnObjectExistFlag = false;
		WebElement elementReturned = null;
		try {
			t0 = System.currentTimeMillis();
			do {
				Thread.sleep(200);
				List<WebElement> ElementList = driver.findElements(ObjName);
				for (int i = 0; i < ElementList.size(); i++) {
					elementReturned = ElementList.get(i);
					if (elementReturned != null) {
						new Actions(driver).moveToElement(elementReturned).build().perform();
					}
					if (elementReturned.isDisplayed()) {
						blnObjectExistFlag = true;
						break;
					}
				}
				ElementList.clear();
				t1 = System.currentTimeMillis();
			} while (t1 - t0 < intTimeInMillis && !blnObjectExistFlag);
		} catch (Exception ex) {

		}
		return elementReturned;
	}

	public static WebElement findChildElement(WebDriver driver, WebElement element, By ObjName) {
		long t0, t1;
		long intTimeInMillis = 15000;
		boolean blnObjectExistFlag = false;
		WebElement elementReturned = null;
		try {
			t0 = System.currentTimeMillis();
			do {
				Thread.sleep(200);
				List<WebElement> ElementList = driver.findElements(ObjName);
				for (int i = 0; i < ElementList.size(); i++) {
					elementReturned = ElementList.get(i);
					if (elementReturned != null) {
						new Actions(driver).moveToElement(elementReturned).build().perform();
					}
					if (elementReturned.isDisplayed()) {
						blnObjectExistFlag = true;
						break;
					}
				}
				ElementList.clear();
				t1 = System.currentTimeMillis();
			} while (t1 - t0 < intTimeInMillis && !blnObjectExistFlag);
		} catch (Exception ex) {

		}
		return elementReturned;
	}

	public static void ClickButton(WebDriver driver, WebElement ele, int n) throws InterruptedException {
		for (int i = 0; i < n; i++)
			try {
				ele.click();
				break;
			} catch (Exception e) {
				Thread.sleep(200);
				if (i == n - 1)
					throw e;
			}
	}

	public Boolean valObjExistance (By ObjName, Boolean blnFlag) {
		Boolean objExistnc = false;
		Boolean objReturnFlag = false;
		try {
			WebElement element = Util.waitForObjToLoad(driver, ObjName);
			if(element!= null) {
				objExistnc = true;
			}
			if(blnFlag == objExistnc) {
				objReturnFlag = true;
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return objReturnFlag;
	}
	public static void ClickButton(WebDriver driver, WebElement ele, ArrayList<WebElement> quitElements, int n)
			throws InterruptedException {
		for (int i = 0; i < n; i++)
			try {
				ele.click();
				break;
			} catch (Exception e) {
				for (WebElement we : quitElements)
					try {
						we.findElement(By.xpath("*"));
						//Log.error(String.format("Quit element came up %s", we));
						throw e;
					} catch (Exception e2) {
						quitElements = new ArrayList<WebElement>() {
							{
							}
						};
						Thread.sleep(200);
						if (i == n - 1)
							throw e;
					}
			}

	}

}
