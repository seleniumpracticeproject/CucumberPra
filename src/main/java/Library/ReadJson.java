package Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.common.io.CharStreams;


public class ReadJson {

	public Object testDataObject;
	public JSONArray testDataJsonArray;
	public static HashMap<String, Object> testDataMap = new HashMap<String, Object>();
	public static HashMap<String, Object> testDataSetMap = new HashMap<String, Object>();

//	static Properties properties = Settings.getInstance();
//	public static final String environment = properties.getProperty("ENV");
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

	public ReadJson() {
		//initializeTestDataFiles();
		//loadTestDataFromJSONFile();
	}

	//1. provide the path of test data
	private void initializeTestDataFiles() {
		// TODO Auto-generated method stub
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		relativePath = relativePath + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"
				+ Util.getFileSeparator() + "resources";
		jsonTestDataFile = relativePath + Util.getFileSeparator() + "testdata.json";
	}
	
	//2. load the TestData/input json file
	public void loadTestDataFromJSONFile() {
		// TODO Auto-generated method stub
		loadTestDataFromJSONFile(jsonTestDataFile, "json");
	}

	//2. load the TestData/input json file
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

	//3. parsing json parseTestDataObject
	@SuppressWarnings({ "unchcked", "unused" })
	public static void parseTestDataObject(JSONObject testDataRow) {
		JSONObject testDataObject = (JSONObject) testDataRow.get("testdata");
		HashMap<String, String> testCaseData = new HashMap<String, String>();
		//if (CukeHooks.properties.getProperty("ENV").equalsIgnoreCase("CLOUD")) {
		//strGlobalURl = (String) testDataObject.get("Cloud_URL");
		//} else if (CukeHooks.properties.getProperty("ENV").equalsIgnoreCase("ONPREM")) {
		//strGlobalURl = (String) testDataObject.get("OnPrem_URL");
		//}
		
		strGlobalURl = (String) testDataObject.get("Cloud_URL");
		strBase = (String) testDataObject.get("Base");
		strBasicAuth = (String) testDataObject.get("basicAuth");
		strUserName = (String) testDataObject.get("userName");
		strPassword = (String) testDataObject.get("password");
		String userStory = (String) testDataObject.get("userStory");
		String testCaseId = (String) testDataObject.get("testCaseId");
		}

	//parsing json as a parseTestDataSetObject
	@SuppressWarnings({ "unused" })
	public void parseTestDataSetObject(JSONObject testDataSet, String userStory, String testCaseId) {
		JSONObject testDataObject = (JSONObject) testDataSet.get("dataset");
		HashMap<String, String> testCaseData = new HashMap<String, String>();
		testDataSetMap.put(testCaseId + "_", testDataObject);
	}

	

	public static void saveEmployeeIDs(String[] employeeIDList) {
		employees = Arrays.asList(employeeIDList).stream().filter(e -> !e.equals("0")).collect(Collectors.toList());
		System.out.println("employees are:" + employees);
	}

	public void loadTestDataFromCSVFile() {
		loadTestDataFromCSVFile(csvTestDataFile);
	}

	@SuppressWarnings("unchecked")
	//if the test data is in the csv file
	public void loadTestDataFromCSVFile(String fileName) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = "";
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		relativePath = relativePath + Util.getFileSeparator() + "test" + Util.getFileSeparator() + "resources";
		String testDataCSVFile = relativePath + Util.getFileSeparator() + "testdata.csv";
		try {
			br = new BufferedReader(new FileReader(testDataCSVFile));
			String[] columnHeaders = br.readLine().split(csvSplitBy);
			int columnCount = columnHeaders.length;
			JSONObject jsonTestData = new JSONObject();
			while ((line = br.readLine()) != null) {
				String[] testData = line.split(csvSplitBy);
				String mapKey = testData[0] + "_" + testData[1] + "_" + testData[2];
				String mapKeyOnlyTestId = testData[1] + "_" + testData[2];
				for (int i = 0; i < columnCount; i++) {
					jsonTestData.put(columnHeaders[i], testData[i]);
				}
				testDataSetMap.put(mapKey, jsonTestData);
				testDataSetMap.put(mapKeyOnlyTestId, jsonTestData);
				System.out.println("map" + testDataSetMap);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public Object getDataValue(String fileName, String testCaseId, String dataSetId, String dataKey) {
		String[] fileNameParts = fileName.split("\\.");
		String fileExtension = fileNameParts[fileNameParts.length - 1];
		if (fileExtension.equalsIgnoreCase("json")) {
			loadTestDataFromJSONFile(fileName, "json");
		} else if (fileExtension.equalsIgnoreCase("csv")) {
			loadTestDataFromCSVFile(fileName);
		}
		JSONObject testDataRow = (JSONObject) testDataSetMap.get(testCaseId + "_" + dataSetId);
		String dataValue = (String) testDataRow.get(dataKey);
		return dataValue;
	}

//	public Object getDataValue(String testCaseId, String dataSetId, String dataKey) {
//		JSONObject testDataRow = (JSONObject) testDataSetMap.get(testCaseId + "_" + dataSetId);
//		String dataValue = (String) testDataRow.get(dataKey);
//		return dataValue;
//	}
//	public static String sortbykey(HashMap<String, String> map) {
//		String lastKey = "";
//		try {
//			TreeMap<String, String> sorted = new TreeMap<>(map);
//			for (map.Entry<String, String> entry : sorted.entrySet()) {
//				lastKey = entry.getKey();
//			}
//		} catch (Exception ex) {
//
//		}
//		return lastKey;
//	}

//	public void createMember(String StrJson) {
//		String createMember = null;
//		String strResponseIntext = "";
//		try {
//			List<Header> headerlist = new ArrayList<Header>();
//			headerlist.add(new Header("Content_Type", "application/json"));
//			Headers headers = new Headers(headerlist);
//			Response response = RestAssured.given().baseUri(createMember).body(StrJson).headers(headers).post();
//			InputStream strm = response.getBody().asInputStream();
//			Reader reader = new InputStreamReader(strm);
//			strResponseIntext = CharStreams.toString(reader);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	public static String readJson(String jsonQuery, String strResponseText) {
		String jsonVal = "1";
		try {
			String readFile = com.jayway.jsonpath.JsonPath.parse(strResponseText).read(jsonQuery).toString();
			if(readFile != null) {
				jsonVal = readFile.replace("[", "").replace("]", "").replace("\"", "").replace("\\","").trim();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Something went wrong in readJson(String jsonQuerh, String strResponseText) function in CMEAppServices.java.Message: " + ex.getMessage());
		}
		return jsonVal;
	}
}
