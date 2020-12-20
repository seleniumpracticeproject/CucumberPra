package api.automation;

public class payload {

	// used for post
	public static String storePayload() {
		String payload = "{\r\n" + 
				"  \"dashboard\" : {\r\n" + 
				"    \"ProjectName\" : \"Hello\",\r\n" + 
				"    \"AppUrl\" : \"facebook.com\",\r\n" + 
				"    \"username\" : \"Yuba\",\r\n" + 
				"    \"password\" : \"Regmi\"\r\n" + 
				"  }}";
		return payload;

	}

	// used for put
	public static String storePayloadforsomeUpdate() {
		String employee = null;
		return "use the payload here using key name concatinated with the " + employee + "and other ";

	}

	// used for get
	public static String storeUpdatedName() {
		String employeeName = null;
		return "use the payload here using key name concatinated with the " + employeeName + "and other ";

	}
}