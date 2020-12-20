package testRunner;

import java.io.File;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import com.cucumber.listener.*;

////:::::::::::::::if you are using cucumber-junit dependency or jar
//@RunWith(Cucumber.class)
//@CucumberOptions(features = { "src/test/resources/feature" }, glue = { "stepDefination" }, tags = {"@Used-Car-Search"}, monochrome = true, plugin = {
//		"html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json",
//		"junit:target/cucumber-reports/cucumber.xml",
//		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
//public class RunCukeFeatures{
//
//}

//::::::::::::::::::if you are using cucumber-testng dependency or jar

@CucumberOptions(features = { "src/test/resources/feature" }, glue = { "stepDefinations" }, monochrome = true
		// plugin = {"pretty", "html:target/cucumber"},
		//plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-Extentreports/ExtentReport.html"}
		)
public class RunCukeFeatures extends AbstractTestNGCucumberTests {
//
//	@AfterClass
//	public static void generateExtentReport() {
//		Reporter.loadXMLConfig("src/test/resources/extent-config.xml");
//		// Reporter.setSystemInfo("Test User", System.getProperty("user.name"));
//		Reporter.setSystemInfo("User Name", "AJ");
//		Reporter.setSystemInfo("Application Name", "Test App ");
//		Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
//		Reporter.setSystemInfo("Environment", "Production");
//		Reporter.setTestRunnerOutput("Test Execution Cucumber Report");
//	}

}