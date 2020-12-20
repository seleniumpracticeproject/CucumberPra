package api.automation;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import junit.framework.Assert;

//in rest intenally they are using http/https protocal
public class GetMethod {
	public void getResponseFromUriWoutParameters() {
		// here uri = http://services.groupkt.com/country/get/all
		Response response = RestAssured.get("http://services.groupkt.com/country/get/all");
		//getting response content
		System.out.println(response.asString());
		//getting response status code
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(),"200");;
		//for validating response content type
		System.out.println(response.getContentType());
		Assert.assertEquals(response.getContentType(),"application/json;charset=UFT-8");;

	}
	public void getResponseFromUriParameters() {
		// here uri = http://services.groupkt.com/country/get/all/?fname=spiderman&lname=red
		Response response = RestAssured.get("http://services.groupkt.com/country/get/all");
		//getting response content
		System.out.println(response.asString());
		//getting response status code
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(),"200");;
		//for validating response content type
		System.out.println(response.getContentType());
		Assert.assertEquals(response.getContentType(),"application/json;charset=UFT-8");;

	}
	
	}
