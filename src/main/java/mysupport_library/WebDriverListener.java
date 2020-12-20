package mysupport_library;

import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

public abstract class WebDriverListener implements IInvokedMethodListener {
	//static Logger log = Logger.getLogger(WebDriverListener.class);
	//CukeeeHooooks cukeHOoks = new CukeeeHooooks();
	private static Properties properties = Settings.getInstance();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		SeleniumTestParameters testParameters = new SeleniumTestParameters();
		//log.debug("BEGINNIG: com.UA.supportLibraries.WebDriverListenerbeforerInvocation");
		if (method.isTestMethod()) {
			try {
				setDefaultTestParameters(method, testParameters);
				//DriverManager.setTestParameters(testParameters);
			} catch (Exception ex) {
				//log.error(ex.getMessage());
				ex.printStackTrace();
			}
		} else {

		}
		//log.debug("END: org.stng.jbehave.LocalWebDriverListener.beforeInvocation");
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		//log.debug("BEGINNING: WebDriverListener.afterInvocation");

		if (method.isTestMethod()) {
			try {
				BaseTestMethod bm = (BaseTestMethod) testResult.getMethod();
				Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
				f.setAccessible(true);
				String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + "-"
						+ bm.getMethodName() + "-";
				//log.info("Renaming test method name from: '" + bm.getMethodName() + "'to:'" + newTestName);
				f.set(bm, newTestName);
			} catch (Exception ex) {
				System.out.println("afterInvocation exception:\n" + ex.getMessage());
				ex.printStackTrace();
			}
		}
		//log.debug("End: WebDriverListener.afterInvocation");
	}

	private void setDefaultTestParameters(IInvokedMethod method, SeleniumTestParameters testParameters) {
		try {
			String executionMode = method.getTestMethod().getXmlTest().getLocalParameters().get("ExecutionsMode");
			switch (executionMode) {
			case "LOCAL":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				}
				break;

			case "REMOTE":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserVersion") == null) {
					testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));
				} else {
					testParameters.setBrowserVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserVersion"));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("Platform") == null) {
					testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));
				} else {
					testParameters.setPlatform(
							Platform.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("Platform")));
				}
				break;
			case "MOBILE":
				// write the code related to mobile
			case "PERFECTO":
				// write the code related to perfecto
			case "SEETEST":
				// write the code related to seetest
			case "SAUCELABS":
				// write the code related to saucelabs
			case "MINT":
				// write the code related to mint
			default:
				testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("DefaultBrowser")));
				}
			}
		} catch (Exception ex) {
			//log.error(ex.getMessage());
		}

	}

}
