package example;

import org.jsoup.helper.HttpConnection.Response;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

import org.codehaus.plexus.util.Base64;
import org.json.simple.JSONObject;






public class TestAPI {
//  @Test
//  public  static void main( String[] args) {
//	  
//	  io.restassured.response.Response r1= RestAssured.get("https://reqres.in/api/users?page=2");
//	 System.out.println(r1.getStatusCode());
//	 System.out.println(r1.getStatusLine());
//	 System.out.println(r1.body().asString());
//	 System.out.println(r1.headers());
//  }


//@Test(enabled= false)
//public void getmethod() {
//	io.restassured.response.Response r1 = RestAssured.get("https://reqres.in/api/users?page=2");
//	 System.out.println(r1.getStatusCode());
//	 System.out.println(r1.getStatusLine());
//	 System.out.println(r1.body().asString());
//	 System.out.println(r1.headers());
//	
//	 Assert.assertEquals(200, r1.getStatusCode());
//}
@Test
public void getmethod() {
	
	
	RestAssured.useRelaxedHTTPSValidation();
	given().
	when().get("https://reqres.in/api/users?page=2").
	then().statusCode(200).statusLine("HTTP/1.1 200 OK").header("Content-Type", "application/json; charset=utf-8").
	body("data.id[1]", equalTo(8)).body("data.first_name",hasItem("Lindsay"));
}

	@Test
	public void post() {
		JSONObject data= new JSONObject();
		data.put("name","Aishwraya");
		data.put("surname", "Patil");
		System.out.println(data);
		given().when().body(data.toJSONString()).post("https://reqres.in/api/users").then().statusCode(201);
		
	}
	
	@Test
	public void delete() {
		RestAssured.useRelaxedHTTPSValidation();
		given().when().delete("https://reqres.in/api/users/2").then().statusCode(204);
		
		
	}
	
	@Test
	
	public void auth() {
		String data=  "postman:password";
		 byte[] encodevaluse= Base64.encodeBase64(data.getBytes());
		String encodestring =new String(encodevaluse);
		given().header("Authorization","Basic" +encodestring).get("http://postman-echo.com/basic-auth").then().statusCode(400);
	}
	
	
}






