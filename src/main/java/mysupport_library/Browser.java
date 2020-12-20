package mysupport_library;

public enum Browser {
	CHROME ("chrome"),
	FIREFOX("firefox"),
	GHOST_DRIVER("phantomjs"),
	HTML_UNIT("htmlunit"),
	INTERNET_EXPLORER("internet explorer"),
	OPERA("opera"),
	SAFARI("safari"),
	;
	
	private String value;
	Browser (String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
