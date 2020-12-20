package mysupport_library;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public abstract class TestListener implements ITestListener {
	private static String getTEstMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();

	}

	@Override
	public void onStart(ITestContext iTestContext) {

	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentManager.getReporter().flush();
		ExtentManager.getReporter().close();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {

	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

	}
}
