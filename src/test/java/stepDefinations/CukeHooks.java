package stepDefinations;

import java.util.Properties;
import org.testng.asserts.SoftAssert;
import mysupport_library.Settings;

public class CukeHooks {
	static java.util.logging.Logger log;
	public static Properties properties = Settings.getInstance();
	public static SoftAssert softAssert = new SoftAssert();
	public static String scenarioName = "";
	static {
		// log = java.util.logging.Logger.getLogger(CukeHooks.class);
	}

//@Before
//public void setUp(Scenario scenario) {
//	try {
//		currentScenario = scenario;
//		ExtentTestManager.startTest(getScenarioDtl(scenario.getName(), "Test Case Name:"), scenario.getName());
//		propertiesFileAccess = properties;
//		Thread.sleep(20000);
//		currentTestParameters = DriverManager.getTestParameters();
//		currentTestParameters.setScenarioName(scenario.getName));
//		scenarioName = scenario.getName();
//		//**********************
//		log.info("Running the Scenario:" + scenario.getName());
//		if(Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) {
//			invokeForMobileExecution(scenario);
//		}else {
//			invokeForDesktopExecution(scenario);
//			
//		}
//	}catch (Exception e) {
//		e.printStackTrace();
//		log.error("Error at Before Scenario" + e.getMessage());
//	}
//}
//
//	private void invokeForDesktopExecution(Scenario scenario) {
//		switch (currentTestparameters.getExecutionMode()) {
//		case LOCAL:
//		case REMOTE:
//		case SAUCELABS:
//			log.info("Running the Scenario:" + scenario.getname() + "in" + currentTestParameters.getExecutionMode());
//			WebDriver driver = DriverFactory.createinstanceWebDriver(currentTestParameters);
//			DriverManager.setWebDriver(driver);
//			break;
//		default:
//			try {
//				throw new Exception("Unhandled Execution Model");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private void invokeForMobileExecution(Scenario scenario) {
//		switch (currentTestparameters.getExecutionMode()) {
//		case MOBILE:
//		case SAUCELABS:
//		case PERFECTO:
//		case MINT:
//			log.info("Running the Scenario:" + scenario.getname() + "in" + currentTestParameters.getExecutionMode());
//			AppiumDriver driver = DriverFactory.createinstanceWebDriver(currentTestParameters);
//			DriverManager.setAppiumDriver(driver);
//			break;
//		default:
//			try {
//				throw new Exception("Unhandled Execution Model");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//@SuppressWarnings("rawtypes")
//@After
//public void embedScreenshot(Scenario scenario) {
//	Webdriver driver = null;
//	try {
//		driver = DriverManager.getWebDriver();
//		ExtentTestManager.endTest();
//		if(Boolean.valueOf(properties.getProperty("TrackTCInRally"))) {
//			integrateRally(scenario,driver);
//		}
//		if(Boolean.valueOf(properties.getProperty("TrackIssueInJira"))) {
//			updateDefectInJira(scenario);
//		}
//		if(Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))&&Boolean.valueOf(properties.getProperty("PerfectoReportGeneration"))) {
//			capturePerfectoReportsForMobile(scenario);
//			
//		}
//		
//		}catch (Exception ex) {
//			ex.printStackTrace();
//			log.error("Problem while closing the Driver Object" + ex.getMessage());
//		}finally {
//			if(Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) {
//				if(currentTestParameters.getExecutionMode()==ExecutionMode.SEETEST) {
//					
//				}else {
//					if(driver! = null) {
//						driver.quit();
//					}
//				}
//				else {
//					driver = DriverManager.getWebDriver();
//					if(driver! = null) {
//						capturePerfectoReportForDesktop(scenario, currentTestParameters, driver);
//						driver.quit();
//					}
//				}
//			}
//}
//}
//
//	private void updateDefectInJira(Scenario scenario) throws IOException {
//		if (scenario.isFailed()) {
//			try {
//				if (Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) {
//					if (currentTestParameters.getExecutionMode() == ExecutionMode.SEETEST) {
//						Util.takeScreenshot(DriverManager.getSeetestDriver());
//						scenario.embed(screenshot, "image/png");
//					} else {
//						byte[] screenshot = Util.takeScreenshot(DriverManager.getAppiumDriver());
//						scenario.embed(screenshot, "image/png");
//					}
//				} else {
//					byte[] screenshot = Util.takeScreenshot(DriverManager.getWebDriver());
//					scenario.embed(screenshot, " image/png");
//				}
//				File filePath = ((TakeScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
//			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
//				somePlatformsDontSupportScreenshots.printStackTrace();
//				log.error(somePlatformsDontSupportScreenshots.getMessage());
//			}
//		}
//	}
//
//	private String getFileName(Browser browser, String deviceName) {
//		String fileName = "";
//		if (browser == null) {
//			fileName = deviceName;
//		} else if (devicename == null) {
//			fileName = browser.toString();
//		} else {
//			fileName = "report";
//		}
//		return filename;
//	}
//
//	@SuppressWarnings("rawtypes")
//	private void capturePerfectoReportsForMobile(Scenario scenario) {
//		try {
//			AppiumDriver driver = DriverManager.getAppiumDriver();
//			driver.close();
//			String Udid;
//			if (!(driver.getCapabilities().getCapability("model") == null)) {
//				Udid = diver.getCapabilities().getCapability("model").toString();
//			} else {
//				Udid = diver.getCapabilities().getCapability("deviceName").toString();
//			}
//			PerfectoLabUtils.downloadReport(driver, "pdf",
//					Util.getResultPath() + Util.getFileSeparator() + scenario.getName() + "_" + Udid);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("Problem while downloading Perfecto Report for " + scenario.getName());
//
//		}
//	}
//
//	private void capturePerfetoReportForDesktop(Scenario scenario, SeleniumTestparameters testparameters,
//			WebDriver driver) {
//		if (Boolean.valueOf(properties.getProperty("PerfectoReportGeneration"))) {
//			driver.close();
//			Map<String, Object> params = new HashMap<String, Object>();
//			((RemoteWebDriver) driver).executeScript("mobile:execution:close", params);
//			params.clear();
//			try {
//				PerfectoLabUtils.downloadRepot((RemoteWebDriver) driver, "pdf",
//						Util.getResultsPath() + Util.getFileSeparator() + scenario.getName() + "_"
//								+ getFileName(testParameters.getBrowser(), testParametrs.getDeviceName()));
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static void generateCustomReports() {
//		CucumberResultsOverview overviewReports = new CucumberResultsOverview();
//		overviewReports.setOutputDirectory("target");
//		overviewReports.setOutputName("cucumber-results");
//		overviewReports.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
//		try {
//			overviewReports.executeFeaturesOverviewReport();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//public static void copyReportsFolder() {
//	String timeStampResultPath = TimeStamp.getInstance();
//	File source = new File(Util.getTargetPath);
//	File source1 = new File (Util.getAllurePath);
//	File dest = new File(timeStampResultpath);
//	File dest1 = new File (timeStampResultpath + Util.getFileSeparator()+ "Allure-reports");
//	if(!dest1.isDirectory()){
//		dest1.mkdirs();
//		}
//	try {
//		FileUtils.copyDirectory(source, dest);
//		FileUtils.copyDirectory(source1, dest1);
//		try {
//			FileUtils.cleanDirectory(source);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		TimeStamp.reportPathWithTimeStamp = null;
//	}}
//
//	public static String getScenarioDtl(String strScenarioOutline, String strTarget) {
//		String ScenarioDetails = "";
//		try {
//			ScenarioDetails = strScenarioOutline.substring(strScenarioOutline.indexOf(strTarget) + strTarget.length(),
//					strScenarioOutline.indexOf(".", strScenarioOutline.indexOf(strTarget))).trim();
//
//		} catch (Exception e) {
//
//		}
//		return ScenarioDetails;
//
//	}
//
//public static void integrateRally (Scenario scenario, WebDriver driver) {
//	try {
//		String strVerdictStatus = "";
//		String tagName = "";
//		String strStoryID = "";
//		String storyRef = "";
//		String testCaseRef = "";
//		String scenarioDesc = scenario.getName();
//		String strNote = "";
//		strNote = strNote + "Results can be viewed from:" + System.getenv("BUILD_URL");
//		String strTCNote = "Test case created from Automatin.please refer the feature file in automation for detailded steps";
//		if(scenario.getStatus().equalsIgnoreCase("passed")) {
//			strVerdictStatus = "Pass";
//			
//		}else if
//			(scenario.getStatus().equalsIgnoreCase("failed")) {
//				strVerdictStatus = "Fail";
//			}
//		else if 
//		(scenario.getStatus().equalsIgnoreCase("skipped")) {
//			strVerdictStatus = "skipped";
//		}
//		else {
//			strVerdictStatus = "error";
//		}
//	}
//	Collectin <String > strTagName
//}

}
