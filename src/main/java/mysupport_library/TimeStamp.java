package mysupport_library;

import java.io.File;
import java.util.Properties;

import Library.Util;

public class TimeStamp {
	public static volatile String reportPathWithTimeStamp;
	public static volatile String OldreportPathWithTimeStamp;
	public static volatile String allureReportPathWithTimeStamp;

	private TimeStamp() {
		
	}

	public static String getInstance() {
		if (reportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (reportPathWithTimeStamp == null) {
					Properties properties = Settings.getInstance();
					String timeStamp = "Run_" + Util.getCurrentFormattedTime(properties.getProperty("DateFormatString"))
							.replace("", "_").replace(":", "_");
					reportPathWithTimeStamp = Util.getResultPath() + Util.getFileSeparator() + timeStamp;
					new File(reportPathWithTimeStamp).mkdirs();
				}
			}
		}
		return reportPathWithTimeStamp;
	}

	public static String getOldReportInstance() {
		if (OldreportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (OldreportPathWithTimeStamp == null) {
					Properties properties = Settings.getInstance();
					String timeStamp = "Run_" + Util.getCurrentFormattedTime(properties.getProperty("DateFormatString"))
							.replace(",", "_").replace(":", "_");
					OldreportPathWithTimeStamp = Util.getOldResultPath() + Util.getFileSeparator() + timeStamp;
					new File(OldreportPathWithTimeStamp).mkdirs();
				}
			}
		}
		return OldreportPathWithTimeStamp;
	}

	public static String getInstanceAllure() {
		if (allureReportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (allureReportPathWithTimeStamp == null) {
					Properties properties = Settings.getInstance();
					String timeStamp = "Allure-Result_Run_"
							+ Util.getCurrentFormattedTime(properties.getProperty("DateFormateString")).replace("", "_")
									.replace(":", "-");
					allureReportPathWithTimeStamp = Util.getResultPath() + Util.getFileSeparator() + timeStamp;
					new File(allureReportPathWithTimeStamp).mkdirs();

				}
			}
		}
		return allureReportPathWithTimeStamp;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();

	}
}
