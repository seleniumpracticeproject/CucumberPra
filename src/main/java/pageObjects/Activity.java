package pageObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.google.common.io.CharStreams;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import io.restassured.path.xml.XmlPath;
import mysupport_library.TestData;
import mysupport_library.Util;

//public class Restservice extends master{
public class Restservice {

	public String wrapperBaeDEVURL = "";
	public String ccsServiceBaseURL = "";
	public String tokenURL = "";

	public String getMemberCloud = "";
	public String strResponseIntext = "";
	public String strModifiedJson = "";
	public String GetCrewMember = "";

	@SuppressWarnings({ "unused" })
	public Restservice() {
//		if (CukeHooks.properties.getProperty("ENV").equalsIgnoreCase("CLOUD")) {
//			EndPointCloud setEndpoint = new EndPointCloud(this);
//		} else if (CukeHooks.properties.getProperty("ENV").equalsIgnoreCase("UNPREM")) {
//			EndPointCloud setEndpoint = new EndPointOnPrem(this);
//		}
	}

	public String initializeTestDataFiles(String jsonFileName) {
		String strFileContent = "";
		try {
			String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
			relativePath = relativePath + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"
					+ Util.getFileSeparator() + "resources" + Util.getFileSeparator() + "WS";
			String jsonTestDataFile = relativePath + Util.getFileSeparator() + jsonFileName;
			strFileContent = readFile(jsonTestDataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strFileContent;
	}

	private String readFile(String pathname) {
		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContents.toString();
	}

	public String modifyJson(String jsonInput, String jayPath, String tagValue) {
		String strModifiedJson = "";
		try {
			DocumentContext json = JsonPath.parse(jsonInput);
			strModifiedJson = json.set(jayPath, tagValue).jsonString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strModifiedJson;
	}

	public String postFOSRequest(String payload) {
		String reply = "";
//		try {
//			Response response = RestAssured.given().baseUri("FOSURL").body(payload)
//					.header("Content-Type", "application/xml").post();
//			XmlPath jsXpath = new XmlPath(response.asString());
//			reply = jsXpath.getString("Reply");
//			System.out.println("Reply is: " + reply);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		return reply;
	}

	public String modifyxml(String xmlInput, String nodeValue) {
		String strModifiedXml = "";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new InputSource(new StringReader(xmlInput)));
			NodeList list = doc.getElementsByTagName("Entry");
			Node entry = list.item(0);
			System.out.println(entry.getTextContent());
			entry.setTextContent(nodeValue);
			strModifiedXml = DomToString(doc);
			System.out.println(strModifiedXml);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strModifiedXml;
	}

	public static String DomToString(Document doc) {
		try {
			StringWriter sw = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UFT-8");
			transformer.transform(new DOMSource(doc), new StreamResult(sw));
			return sw.toString();
		} catch (Exception ex) {
			throw new RuntimeException("Error converting to String", ex);

		}

	}

	public static String postESOAService(String strEndPoint, String strJSON) {
		String strResponse = "";
//		Response response = null;
//		try {
//			List<Header> headerlist = new ArrayList<Header>();
//			headerlist.add(new Header("Authorization", TestData.strBasicAuth));
//			headerlist.add(new Header("Content-Type", "application/json"));
//			headerlist.add(new Header("source", TestData.strSource));
//			Headers headers = new Headers(headerlist);
//			try {
//				response = RestAssured.given().proxy("http://proxyaddress").baseUri(ccsServiceBaseURL).body(strJSON)
//						.basePath(strEndPoint).headers(headers).post();
//
//			} catch (Exception ex) {
//				response = RestAssured.given().baseUri(ccsServiceBaseURL).body(strJSON).basePath(strEndPoint)
//						.headers(headers).post();
//			}
//			InputStream strm = response.getBody().asInputStream();
//			Reader reader = new InputStreamReader(strm);
//			strResponse = CharStreams.toString(reader);
//			System.out.println(strResponseIntext);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		return strResponse;
	}

	public String postServiceCall(String strEndPoint, String strJSON) {
		String responseTxt = "";
		try {
//			if (CukeHooks.properties.getProperty("ENV").equalsIgnoreCase("CLOUD")) {
//				String token = getToken();
//				responseTxt = searchSequences(strEndPoint, token, strJSON);
//			} else if (CukeHooks.properties.getProperty("ENV").equalsIgnoreCase("ONPREM")) {
//				responseTxt = postESOAService(strEndPoint, strJSON);
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responseTxt;
	}

	private String searchSequences(String strEndPoint, String token, String strJSON) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToken() {
		String strResponse = "";
		String token = "";
//		Response response = null;
//		try {
//			List<Header> headerlist = new ArrayList<Header>();
//			headerlist.add(new Header("Content-Type", "application/x-www-form-urlencoded"));
//			headerlist.add(new Header("source", TestData.strSource));
//			headerlist.add(new Header("Authorization", "basic............."));
//			Map<String, String> reqObject = new HashMap<>();
//			Headers headers = new Headers(headerlist);
//			try {
//				response = RestAssured.given().proxy("http//.......").baseUri(tokenURL)
//						.formParam("grant-type", "client_credintialls").headers(headers).post();
//			} catch (Exception ex) {
//				response = RestAssured.given().baseUri(tokenURL).formParam("grant-type", "client_credintialls")
//						.headers(headers).post();
//			}
//			InputStream strm = response.getBody().asInputStream();
//			Reader reader = new InputStreamReader(strm);
//			strResponse = CharStreams.toString(reader);
//			token = readJson("$.token", strResponse);
//			System.out.println(token);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
		return token;
	}

	public static String readJson(String jsonQuery, String strResponseText) {
		// TODO Auto-generated method stub
		String jsonVal = "1";
//		try {
//			String readFile = JsonPath.parse(strResponseText).read(jsonQuery).toString();
//			if(readFile!= null) {
//				jsonVal = readFile.replace("[", "").replace("]", "").replace("\"", "").replace("\\", "").trim();
//			}
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
		return jsonVal;
	}

	public void getMember(String strJson) {
//		try {
//			List<Header> headerlist = new ArrayList<Header>();
//			headerlist.add(new Header("Content-Type", "application/x-www-form-urlencoded"));
//			headerlist.add(new Header("source", TestData.strSource));
//			Headers headers = new Headers(headerlist);
//			Response response = RestAssured.given().baseUri(ccsServiceBaseURL).body(strJson).basePath(GetCrewMember)
//					.headers(headers).post();
//			InputStream strm = response.getBody().asInputStream();
//			Reader reader = new InputStreamReader(strm);
//			strResponseIntext = CharStreams.toString(reader);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}
	
	public String getCrewMemberJsonREsponse(String month) {
		String jsonresponse = "";
		try {
			String employeeNum = "200";
			String payloadJson = "enter payload here";
			String endpoint = "enter endpoint here";
			jsonresponse = Restservice.postESOAService(endpoint, payloadJson);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return jsonresponse;
	}

	public static String SortbyKey(HashMap<String, String> map) {
		String lastKey = "";
		try {
			TreeMap<String, String> sorted = new TreeMap<>(map);
			for (Map.Entry<String, String> entry : sorted.entrySet()) {
				lastKey = entry.getKey();
			}
		} catch (Exception ex) {

		}
		return lastKey;
	}

	
	public static void saveSearchRequestResult(String strResponseText) {
//		TestData.searchSequencesResponse = strResponseTxt;
//		String employeeIDList = RestService.readJson(JsonXmalPath.employeeIDSearch, strResponseTXt)
//				TestData.saveEmployeeIDs(employeeIDList.split("\\,"));
	}
}
