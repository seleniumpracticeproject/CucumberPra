package testRunner;
//package testNGRunner;
//
//import java.io.File;
//import java.io.IOException;
//import org.apache.commons.io.FileUtils;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotaton.AfterTest;
//import com.pageObjects.JsonXmlPath;
//import com.pageObjects.RestServices;
//import com.supportLibraries.TestData;
//import com.supportLibraries.TimeStamp;
//import com.supportLibraries.Util;
//import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
//import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
//import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
//import cucumber.api.CucumberOptions;
//import cucumber.api.testng.AbstractTestNGCucumberTests;
//
//@ExtendedCucumberOptions(jsonReport = "target/cucumber-report/Smoke/cucumber.json",
//jsonUsageReport = "targer/cucumber-report/Smoke/cucumber-usage.json",
//outputFolder = "target/cucumber-report/Smoke",
//detailedReport = true, detailedAggregatedReport = true,
//overviewReport = true, usageReport = true,
//retryCount = 2, toPDF = true)
//please notice that com.stepdefination.cukehooks class is in the same package as teh steps definations. it has two methods that are executed before or after scenario. i am using it to delete cookies and take a screenshots if scenario fails.
//@CucumberOptions(
//		features = "src/test/resources/features",
//					glue = {"com.stepDefinitions"},
//					tags = {"@Smoke"},
//					monochrome = true,
//					plugin = {"prett", "pretty:targer/cucumber-report/Smoke/cucumber.json",
//							"html:target/cucumber-report/Smoke", 
//							"json:target/cucumber-report/Smoke/cucumber.json",
//							"junit:trarget/cucumber-report/Smoke/cucumber-junitreport.xml",
//							"ru.yandex.qatools.allure.cucumberjvm.AllureReporter"
//							})
//
//public class SmokeTest extends AbstractTestNGCucumberTests{
//	public static String[]arrEmployee = null;
//	@AfterTest(alwaysRun = true)
//	private void test() {
//		generateCustomReports();
//		copyReportsFolder();
//	}
//	private void generateCustomReports () {
//		CucumberResultsOverview overviewReports = new CucumberResultsOverview();
//		overviewReports.setOutputDirectory("target");
//		overviewReports.setOutputName("cucumber-results");
//		overviewReports.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
//		try {
//			overviewReports.executeFeaturesOverviewReport();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		CucumberDetailedResults detailedResults = new CucumberDetailedResulsts();
//		detailedResults.setOutputDirectory("target");
//		detailedResults.setOutputName("cucumber-results");
//		detailedResults.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
//		try {
//			detailedResults.executeDetailedTesultsReport(false, true);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void copyReportsFolder() {
//		String timeStampResultPath = TimeStamp.getInstance();
//		File sourceCucumber = new File (Util.getTargetpath());
//		File destCucumber = new File(timeStampResultPath);
//		File destCucumberLatest = new File("Latest");
//		try {
//			FileUtils.copyDirectory (sourceCucumber, destCucumberLatest);
//			FileUtils.copyDirectory(sourceCucumber, destCucumber);
//			try {
//				//FileUtils.cleanDirectory(sourceCucumber);
//			}catch (Exception e) {
//
//			}
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		timeStamp.reportPathWithTimeStamp = null;
//	}
//
//	@AfterSuite
//	private void copyStoredReports() {
//		//any customizations after execution can be added here
//	}
//}
