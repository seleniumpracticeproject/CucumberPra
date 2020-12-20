package api.automation;

import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;



//what do you mean by static import in rest api?
//if we put static keyword after import while importing any api and put * at the end
//then we can directly import the method 
//for ex: instead of RestAssured.get(uri) we can use get(uri)
//what do you mean by bdd option in rest assured?
//we can use given when and then like cucumber.

public class bddOptions {
	// given():all input details should be placed here
	// when(): whatever the task we perform while making request/sending request
	// when(): submi the api
	// then(): whatever the task we want to perform once the request is made and we
	// got response
	// then(): validate the response
	
//:::::::::::::::::::::::: post method ::::::::::::::::::
public void validateStatusCode() {
		RestAssured.baseURI = "write down the base uri";
		// here given is the static method in RestAssured class
		// so we need to put static keyword and (.*) while importing
		// import static com.jayway.restassured.RestAssured.*;
		given().queryParam("query key", "query value").header("Content-Type", "application/json")
				.body("copy and paste the whole json body").when().post("copy and paste the resource here").then()
				.assertThat().statusCode(200);
		// use log.all methods after given when and then to see in to console.
		given().log().all().queryParam("query key", "query value").header("Content-Type", "application/json")
				.body("copy and paste the whole json body").when().post("copy and paste the resource here").then().log()
				.all().assertThat().statusCode(200);
	}

public void validateJsonResponseBody() {
		RestAssured.baseURI = "write down the base uri";
		given().log().all().queryParam("query key", "query value").header("Content-Type", "application/json")
				.body("copy and paste the whole json body").when().post("copy and paste the resource here").then().log()
				.all().assertThat().body("some body key", equalTo("some body value"));
	}

public void validateJsonResponseHeader() {
		RestAssured.baseURI = "write down the base uri";
		given().log().all().queryParam("query key", "query value").header("Content-Type", "application/json")
				.body("copy and paste the whole json body").when().post("copy and paste the resource here").then().log()
				.all().assertThat().header("some header key", "some header value");
	}

//we can validate all of the above code in a single code
public void validateAllInSingleCode() {
		RestAssured.baseURI = "write down the base uri";
		given().log().all().queryParam("query key", "query value").header("Content-Type", "application/json")
				.body("copy and paste the whole json body").when().post("copy and paste the resource here").then().log()
				.all().assertThat().statusCode(200).body("some body key", equalTo("some body value"))
				.header("some header key", "some header value");
	}

//we can create one return method in another class for body/payload provided after given
//and call this method as folloings
public void validateJsvalidateStatusCode() {
		RestAssured.baseURI = "write down the base uri";
		given().log().all().queryParam("query key", "query value").header("Content-Type", "application/json")
				.body(payload.storePayload()).when().post("copy and paste the resource here").then().log().all()
				.assertThat().body("some body key", equalTo("some body value"));
	}

//log().all()method is used to see the log into console so i removed the log.all method for response only
//here we store all the response in string and print it
public String ExtractJsonResponseBodyString() {
	String Response = "";
		RestAssured.baseURI = "write down the base uri";
		Response = given().log().all().queryParam("query key", "query value")
				.header("Content-Type", "application/json").body("copy and paste the whole json body").when()
				.post("copy and paste the resource here").then().assertThat()
				.header("some header key", "some header value").extract().response().asString();
		System.out.println(Response);
		return Response;
	}

//how to parse the json you get from above response
public void ParseJsonResponse() {
	RestAssured.baseURI = "write down the base uri";
	String Response = given().log().all().queryParam("query key", "query value")
			.header("Content-Type", "application/json").body("copy and paste the whole json body").when()
			.post("copy and paste the resource here").then().assertThat()
			.header("some header key", "some header value").extract().response().asString();
	System.out.println(Response);
	//there is JsonPath class which helps to parse the json
	JsonPath js = new JsonPath(Response);
	js.getString("give the path what you want to extract");
	
}

//:::::::::::::::::::::: put method :::::::::::::::::::::::::
public void updateSomeValueInsideJsonBody() {
	RestAssured.baseURI = "write down the base uri";
	given().log().all().queryParam("query key", "query value").header("Content-Type", "application/json")
			.body(payload.storePayloadforsomeUpdate())
			.when().put("copy and paste the resource here")
			.then().log().all()
			.assertThat().body("msg", equalTo("updated successfully"));
}

//::::::::::::::: get method :::::::::::::::
public void getSomevalue() {
	//here you dont need to send the body in request
	//but send the body in the response
	RestAssured.baseURI = "write down the base uri";
	String getResponse = given().log().all().queryParam("query key", "query value").header("Content-Type", "application/json")
			.when().get("copy and paste the resource here")
			.then().log().all()
			.assertThat().extract().asString();
	JsonPath js = new JsonPath(getResponse);
	js.getString("give the path what you want to extract");
	
}
}
