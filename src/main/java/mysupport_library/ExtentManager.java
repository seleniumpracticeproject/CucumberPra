package mysupport_library;

import java.io.File;
import java.util.Date;
import com.relevantcodes.extentreports.ExtentReports;

import Library.Util;

public class ExtentManager {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		String workingDir = System.getProperty("user.dir");
		String timeAppend = Util.getFormattedTime(new Date(), "ddMMYY_hhmmss");
		if (extent == null) {
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				extent = new ExtentReports(workingDir + "\\ExtentReports\\ExtentReportResults_" + timeAppend + ".html",
						true);
			}
			else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				extent = new ExtentReports(workingDir + "/ExtentReports/ExtentReportResults_" + timeAppend + ".html",
						true);
			}
			extent.loadConfig(new File(workingDir + "\\src\\test\\resources\\extent-config.xml"));
		}
		return extent;
	}
}
