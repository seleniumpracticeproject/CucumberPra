package mysupport_library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import stepDefinations.CukeeeHooooks;

public class TestData {

	public Object testDataObject;
	public JSONArray testDataJsonArray;
	public static HashMap<String, Object> testDataMap = new HashMap<String, Object>();
	public static HashMap<String, Object> testDataSetMap = new HashMap<String, Object>();

	static Properties properties = Settings.getInstance();
	public static final String environment = properties.getProperty("ENV");
	public static String strGlobalURl = "";
	public String jsonTestDataFile = "";
	public static String strBase = "";
	public static String strBasicAuth = "";
	public static String strSource = "";
	public String csvTestDataFile = "";
	public String customTestDataFile = "";
	public static String strUserName = "";
	public static String strPassword = "";
	public static List<String> employees = null;
	public static String[] reservedEmployees = null;
	public static List<String> employeesWithFutureSequences = null;
	public static List<String> futureSequences = null;

	public TestData() {
		initializeTestDataFiles();
		loadTestDataFromJSONFile();
	}

	private void loadTestDataFromJSONFile() {
		// TODO Auto-generated method stub
		loadTestDataFromJSONFile(jsonTestDataFile, "json");
	}

	@SuppressWarnings("Unchecked")
	public static void loadTestDataFromJSONFile(String fileName, String strkey) {
		// TODO Auto-generated method stub
		JSONParser jsonParser = new JSONParser();
		Object testDataObject = null;
		try (FileReader reader = new FileReader(fileName)) {
			testDataObject = jsonParser.parse(reader);
			JSONArray testDataJsonArray = (JSONArray) testDataObject;
			testDataMap.put(strkey, testDataJsonArray);
			if (strkey.equalsIgnoreCase("json")) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchcked", "unused" })
	public static void parseTestDataObject(JSONObject testDataRow) {
		JSONObject testDataObject = (JSONObject) testDataRow.get("testdata");
		HashMap<String, String> testCaseData = new HashMap<String, String>();
//		if (CukeHooks.properties.getProperty("ENV").equalsIgnoreCase("CLOUD")) {
//			strGlobalURl = (String) testDataObject.get("Cloud_URL");
//		} else if (CukeHooks.properties.getProperty("ENV").equalsIgnoreCase("ONPREM")) {
//			strGlobalURl = (String) testDataObject.get("OnPrem_URL");
//		}
		strBase = (String) testDataObject.get("Base");
		strBasicAuth = (String) testDataObject.get("basicAuth");
		strUserName = (String) testDataObject.get("userName");
		strPassword = (String) testDataObject.get("password");
		String userStory = (String) testDataObject.get("userStory");
		String testCaseId = (String) testDataObject.get("testCaseId");
		strUserName = (String) testDataObject.get("userName");
		strPassword = (String) testDataObject.get("password");
	}

	@SuppressWarnings({ "unused" })
	public void parseTestDataSetObject(JSONObject testDataSet, String userStory, String testCaseId) {
		JSONObject testDataObject = (JSONObject) testDataSet.get("dataset");
		HashMap<String, String> testCaseData = new HashMap<String, String>();
		testDataSetMap.put(testCaseId + "_", testDataObject);
	}

	private void initializeTestDataFiles() {
		// TODO Auto-generated method stub
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		relativePath = relativePath + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"
				+ Util.getFileSeparator() + "resources";
		jsonTestDataFile = relativePath + Util.getFileSeparator() + "testdata.json";
	}

	public static void saveEmployeeIDs(String[] employeeIDList) {
		employees = Arrays.asList(employeeIDList).stream().filter(e -> !e.equals("0")).collect(Collectors.toList());
		System.out.println("employees are:" + employees);
	}

	public void loadTestDataFromCSVFile() {
		loadTestDataFromCSVFile(csvTestDataFile);
	}

	@SuppressWarnings("unchecked")
	public void loadTestDataFromCSVFile(String fileName) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = "";
		String relativePath = new File (System.getProperty("user.dir")).getAbsolutePath();
		relativePath = relativePath + Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources";
		String testDataCSVFile = relativePath + Util.getFileSeparator() + "testdata.csv";
		try {
			br = new BufferedReader(new FileReader(testDataCSVFile));
			String[]columnHeaders = br.readLine().split(csvSplitBy);
			int columnCount = columnHeaders.length;
			JSONObject jsonTestData = new JSONObject();
			while((line = br.readLine())!= null) {
				String [] testData = line.split(csvSplitBy);
				String mapKey = testData[0] + "_" + testData[1] + "_" + testData[2];
				String mapKeyOnlyTestId = testData[1] + "_" + testData[2];
for(int i = 0; i< columnCount; i++) {
	jsonTestData.put(columnHeaders[i], testData[i]);
}
testDataSetMap.put(mapKey, jsonTestData);
testDataSetMap.put(mapKeyOnlyTestId, jsonTestData);
System.out.println("map" + testDataSetMap);
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br!= null) {
				try {
					br.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public Object getDataValue(String testCaseId, String dataSetId, String dataKey) {
		JSONObject testDataRow = (JSONObject) testDataSetMap.get(testCaseId + "_" + dataSetId);
		String dataValue = (String) testDataRow.get(dataKey);
		return dataValue;
	}
	public Object getDataValue(String fileName, String testCaseId, String dataSetId, String dataKey) {
		String [] fileNameParts = fileName.split("\\.");
		String fileExtension = fileNameParts[fileNameParts.length-1];
		if(fileExtension.equalsIgnoreCase("json")) {
			loadTestDataFromJSONFile(fileName, "json");
		}else if(fileExtension.equalsIgnoreCase("csv")) {
			loadTestDataFromCSVFile(fileName);
		}
		JSONObject testDataRow = (JSONObject) testDataSetMap.get(testCaseId + "_" + dataSetId);
		String dataValue = (String) testDataRow.get(dataKey);
		return dataValue;
	}
}
