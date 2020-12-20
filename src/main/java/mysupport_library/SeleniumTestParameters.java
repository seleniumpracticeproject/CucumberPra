package mysupport_library;

import org.openqa.selenium.Platform;

public class SeleniumTestParameters {
	private ExecutionMode executionMode;
	private Browser browser;
	private Platform platform;
	private String deviceName;
	private MobileExecutionPlatform mobileExecutionPlatform;
	private String mobileOsVersion;
	private String manufacturer;
	private String modelName;
	private boolean isDeviceUdid;
	private String scenarioName;
	private String environMent;
	private String BrowserVersion;

	public SeleniumTestParameters() {
		//constructor
	}

	//:::::: for environment mode
		public void setEnvironment(String environMent) {
			this.environMent = environMent;
		}
		public String getEnvironment() {
			return environMent;
		}
		
	//:::::: for execution mode
	public void setExecutionMode(ExecutionMode executionMode) {
		this.executionMode = executionMode;
	}
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	//::::::::: for mobile execution platform
	public void setMobileExecutionPlatform(MobileExecutionPlatform mobileExecutionPlatform) {
		this.mobileExecutionPlatform = mobileExecutionPlatform;
	}

	public MobileExecutionPlatform getMobileExecutionPlatform() {
		return mobileExecutionPlatform;
	}

	// ::::::::: for Browser verson
	public void setBrowserVersion(String BrowserVersion) {
		this.BrowserVersion = BrowserVersion;
	}

	public String getBrowserVersion() {
		return BrowserVersion;
	}

	// ::::::: for browser
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public Browser getBrowser() {
		return browser;
	}

	// ::::::::::: for mobile version
	public void setmobileOSVersion(String mobileOsVersion) {
		this.mobileOsVersion = mobileOsVersion;
	}

	public String getMobileOSVersion() {
		return mobileOsVersion;
	}

	// :::::::: for device name
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	// :::::::: for manufacturerName
	public void setManuFacturerName(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManuFacturerName() {
		return manufacturer;
	}

	// :::::::: for modelname
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}

	// ::::::::: for device udid set and get method
	public void setIsDeviceUdid(boolean isDeviceUdid) {
		this.isDeviceUdid = isDeviceUdid;
	}

	public boolean getIsDeviceUdid() {
		return isDeviceUdid;
	}

	// ::::::::: for ScenarioName set and get method
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getScnarioName() {
		return scenarioName;
	}

	// ::::::::: for platform set and get method
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Platform getPlatform() {
		return platform;
	}

}