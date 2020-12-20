package mysupport_library;

public enum ExecutionMode {

	LOCAL ("local"),
	REMOTE("remote"),
	PERFECTO("perfecto"),
	MOBILE("appium"),
	SEETEST("seetest"),
	MINT("mint"),
	SAUCELABS("saucelabs"),
	BROWSERSTACK("browserstack"),
	;
	private String value;
	ExecutionMode(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
