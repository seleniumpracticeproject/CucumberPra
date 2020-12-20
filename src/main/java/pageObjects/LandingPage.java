package pageObjects;

import org.apache.commons.codec.binary.Base64;
import java.util.List;
import java.util.Set;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import mysupport_library.ExtentTestManager;
import mysupport_library.TestData;
import mysupport_library.Util;

//public class LandingPage extends masterSteps{
public class LandingPage{

	// ************************************************************************************
	// make the object of the class if you are implementing some classes
	// RestServices res = new RestServices();
	//TestData testData = new TestData();
	public static String employeeID = "";

	// ***************************************************************************************
	// if the element is fixed in the page
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(),'username')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement username;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(),'password')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement password;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(),'loginbutton')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement loginbutton;

	// ***************************************************************************************

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(),'password')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement employeeidEditBox;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(),'loginbutton')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement submitbutton;

	// ***************************************************************************************

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(),'hello')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement hellotab;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//a[contains(@href,'/home')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement hellotab2;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//a[contains(.,'2020')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement calmonthHeader;

	// if the element is dynamic remove @CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(),'hello')]") })
	// @FindAll({@FindBy (how = How.XPATH, using = "//input[@name = 'hello')]")})
	// @FindAll({@FindBy (how = How.XPATH, using = "//button[@id = 'hello')]")})
	WebElement hellotabsall;

	// if there are list of elements with the same locators
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@class = 'hello')]") })
	List<WebElement> hellotabs;
	public WebDriver driver;

	// **************************************************************************************
	// making constructor of the class
	public LandingPage() {
		//this.driver = supper.driver;
		PageFactory.initElements((WebDriver) driver, this);
	}

	// ************************************************************************************

	// :::::::::::::::::::: setting username and password:::::::::::::
	public void setCredentials() {
		Util.ClickElement(driver, username);
		Util.enterText(driver, username, TestData.strUserName);
		Util.ClickElement(driver, password);
		Util.enterText(driver, password, decodePswd(TestData.strPassword));
		Util.ClickButton(driver, loginbutton);
	}

	// method to decode password
	public String decodePswd(String pswd) {
		try {
			//byte[] decodedBytes = Base64.getDecoder().decode(pswd);
			//pswd = new String(decodedBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pswd;
	}

	// ::::::::::: after login using credentials if there is an particular id
	public void login(String employeeId) {
		try {
			Util.ClickButton(driver, employeeidEditBox);
			Util.enterText(driver, employeeidEditBox, employeeID);
			Util.ClickButton(driver, submitbutton);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// :::::::::::::::::: methods on that page :::::::::::::
	public void clickHelloTab() {
		try {
			Util.ClickElement(driver, hellotab);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "hello tab is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (

		Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "hello tab is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void DuplicateErrorMessage() throws Exception {
//		if (hellotabsall.isDisplayed() == true) {
//			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
//			ExtentTestManager.getTest().log(LogStatus.PASS, "Error message is displayed",
//					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
//			Assert.assertTrue("Error message is displayed", true);
//		} else {
//			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
//			ExtentTestManager.getTest().log(LogStatus.FAIL, "Error message is not displayed",
//					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
//			Assert.assertFalse("Error message is not displayed", true);
//		}
	}

	public void colorcheck() {
//		String RedColorCode = "rgba (255, 0, 0, 1)";
//		String HelloColor = hellotab.getCssValue("boarder-top-color");
//		if (HelloColor.equals(RedColorCode)) {
//			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
//			ExtentTestManager.getTest().log(LogStatus.PASS, "color code is matched",
//					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
//			Assert.assertTrue("color code is matched", true);
//		} else {
//			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
//			ExtentTestManager.getTest().log(LogStatus.FAIL, "color code is not matched",
//					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
//			Assert.assertFalse("color code is not matched", true);
//		}
	}

	public void validateCurrentContractMonth() {
//		try {
//			Month curMonth = Util.getCurrentMonth();
//			String currentContractualMonth = curMonth.toString();
//			if (calmonthHeader.getText().contains(currentContractualMonth)) {
//				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
//				ExtentTestManager.getTest().log(LogStatus.PASS, " header is matched",
//						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
//				Assert.assertTrue("header is matched", true);
//			} else {
//				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
//				ExtentTestManager.getTest().log(LogStatus.FAIL, "header is not matched",
//						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
//				Assert.assertFalse("header is not matched", true);
//			}
//
//		} catch (Exception e) {
//
//		}
	}

	public Date calDeparttime(Date date) throws ParseException {
		String title = calmonthHeader.getAttribute("title");
		String Departure = title.substring(13, 18);
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date DepartureTime = dateFormat.parse(Departure);
		Date CalanderDepartureDate = Date.from(DepartureTime.toInstant().plus(Duration.ofHours(1)));
		return CalanderDepartureDate;
	}

	public static int convertStringToNumber(String numStr) {
		char ch[] = numStr.toCharArray();
		int sum = 0;
		int zeroAscii = (int) '0';
		for (char c : ch) {
			int tmpAscii = (int) c;
			sum = (sum * 10) + (tmpAscii + zeroAscii);
		}
		return sum;
	}

	public Date departureTime(Date date) throws Throwable {
		int a = convertStringToNumber(hellotab2.getText());
		int hours = a / 100;
		int minutes = a % 100;
		String s1 = String.format("%d:%02d", hours, minutes);
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date SeqDepartureTime = dateFormat.parse(s1);
		return SeqDepartureTime;
	}

	// ************************************************************************************************
	// sending payload to read the json response and getting the result
	public String getMemberJsonResponse(String Month) {
		String jsonresponse = "";
		try {
			String payloadJson = "{ \"contractMonths\" : [" + " \"" + "month" + "\"" + "]}";
			// see how to create payload from the ....file
			String endpoint = "member/v4/getmember";
			// jsonresponse = Restservice.postESOAService(endpoint, payloadJson);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return jsonresponse;
	}

	public void validateMyinfo() {
		
			Month curmonth = Util.getCurrentMonth();
			Integer currentYear = Util.getCurrentYear();
			String StrCurmonth = curmonth.toString().substring(0, 3) + currentYear.toString();
			String jsonresponse = getMemberJsonResponse(StrCurmonth);
			
//			String empid = Restservice.readJson("$.employeeId", jsonresponse);
//			String empName = Restserviece.readJson("$..employeeName", jsonresponse);
//			String empName1 = Restserviece.readJson("$..employeeName[0].surName", jsonresponse);
			
			Integer something = Integer.parseInt(Restservice.readJson("$.something[0].somevalue", jsonresponse));
			Integer hour = something / 60;
			Integer min = something % 60;
					String somethingHour = hour.toString() + ":" + min.toString();
		}

	// do some knowledge for trim/split and replace method
	public void validateDepartureDate() {
		Calendar cal = Calendar.getInstance();
		int currentDate = cal.get(Calendar.DAY_OF_MONTH);
		String[] monthName = { "JAN", "FEB" };
		String departToDate = Integer.toString(currentDate);
		String departFromDate = Integer.toString(currentDate + 2);
		String currentMonth = monthName[cal.get(Calendar.MONTH)];
		String currentYear = Integer.toString(cal.get(Calendar.YEAR));

	}

	public void verifyMinCreditHours(String minHrs, String minMins) {
		try {
			if (minHrs.contains("hh")){
				minHrs = Integer.toString(0) + Integer.toString(0);
				
			}
			if (minHrs.contains("mm")) {
				minMins = Integer.toString(0)+ Integer.toString(0);
			}
		}
		catch(Exception e) {
			
		}
	}

	public static int[] getTotalBallotsWithNonZeroTAFBValue(List<WebElement> pumin, List<WebElement> puomin,
			List<WebElement> template) {
		int pu_totalTAFB = pumin.size();
		int puoutside_totalTAFB = puomin.size();
		int template_totalTAFB = template.size();
		return new int[] { pu_totalTAFB, puoutside_totalTAFB, template_totalTAFB };
	}

}
