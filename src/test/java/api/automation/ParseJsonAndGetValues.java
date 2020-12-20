package api.automation;

import static com.jayway.restassured.RestAssured.given;

import java.nio.file.Paths;

import org.testng.reporters.Files;

import com.jayway.restassured.path.json.JsonPath;

public class ParseJsonAndGetValues {
	
	//this method is used just to run the application
	public static void main(String[] args) {
		//ParseJsonAndGetValues.getResponseFromApi();
		System.out.println("hello java");
	}
	
//	public void ReadJsonFromProject() {
//		given().log().all().queryParam("query key", "query value").header("Content-Type", "application/json")
//		.body(Files.readFile(Paths.get("path of the json kept in project"))).when().post("copy and paste the resource here").then().log()
//		.all().assertThat().statusCode(200);
//	}
	
	//you get the response from the api and store as 
	//"Response" as in string
	//here i am using dummy response
	public static String getResponseFromApi() {
		return "{\r\n" + 
				"  \"dashboard\" : {\r\n" + 
				"    \"ProjectName\" : \"Hello\",\r\n" + 
				"    \"AppUrl\" : \"facebook.com\",\r\n" + 
				"    \"username\" : \"Yuba\",\r\n" + 
				"    \"password\" : \"Regmi\"\r\n" + 
				"  }}";
	}
	
	public static void ReadJson() {
		//JsonPaht is the class which is used to parse the json object
		//there is another class called pojo which is also used to parse the json object
		JsonPath js = new JsonPath(ParseJsonAndGetValues.getResponseFromApi());
		int count = js.getInt("dashboard.size()");//to get the size
		js.get("dashboard[0].ProjectName");//to get the value of key ProjectName
		
		//looping
		for(int i =0; i< count; i++) {
			js.getInt("dashboard["+i+"].ProjectName");//if project Name is int
			String str = js.get("dashboard["+i+"].ProjectName").toString();//if Project name is string
			if(str.equalsIgnoreCase("some requied value")) {
				String reqvalue = js.get("dashboard["+i+"].somevalue").toString();
				System.out.println(reqvalue);
				
				//if we need to do the multiply
				int a = 10;
				int b = 2;
				int c = a*b;
				int sum = 0;
				sum = sum + c;
			}
		}
		
	}
	//Files.readAllBytes(Path.get("provide the path of json files"))
	//
	

}
