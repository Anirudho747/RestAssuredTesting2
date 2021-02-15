
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

public class Frst {
	
@Test(enabled=false)
public void weatherFor1Day()
{
	
	 given()
	.param("q", "Kolkata")
	.param("appid", "5cf8430b7171d0e5ffe1fa10a7932bec")
	.get("http://api.openweathermap.org/data/2.5/weather")
	.then()
	.statusCode(200).log().all();
	
}

@Test(enabled=false)
public void weatherFor16Days()
{
	given()
	.param("q","Kolkata")
	.param("cnt","7")
	.param("appid", "5cf8430b7171d0e5ffe1fa10a7932bec")
	.get("api.openweathermap.org/data/2.5/forecast/daily")
	.then()
	.statusCode(200).log().all();
}

@Test(enabled=false)
public void xtract()
{
	System.out.println(given()
	.param("id", "2172797")
	.param("appid", "5cf8430b7171d0e5ffe1fa10a7932bec")
	.when()
	.get("http://api.openweathermap.org/data/2.5/weather")
	.then()
	.contentType(ContentType.JSON)
	.extract()
	.path("weather[0].main"));
}

@Test(enabled=false)
public void passParam()
{
	String desc = (given()
	.param("id", "2172797")
	.param("appid", "5cf8430b7171d0e5ffe1fa10a7932bec")
	.when()
	.get("http://api.openweathermap.org/data/2.5/weather")
	.then()
	.statusCode(200)
	.extract()
	.path("weather[0].description"));
	
	String lon = (given()
	.param("id", "2172797")
	.param("appid", "5cf8430b7171d0e5ffe1fa10a7932bec")
	.when()
	.get("http://api.openweathermap.org/data/2.5/weather")
	.then()
	.contentType(ContentType.JSON)
	.statusCode(200)
	.extract()
	.path("coord.lon")).toString();
	
	String lat = (given()
	.param("id", "2172797")
	.param("appid", "5cf8430b7171d0e5ffe1fa10a7932bec")
	.when()
	.get("http://api.openweathermap.org/data/2.5/weather")
	.then()
	.contentType(ContentType.JSON)
	.statusCode(200)
	.extract()
	.path("coord.lat")).toString();
	
	given()
	.param("lat", lat)
	.param("lon", lon)
	.param("appid", "5cf8430b7171d0e5ffe1fa10a7932bec")
	.when()
	.get("http://api.openweathermap.org/data/2.5/weather")
	.then()
	.statusCode(200)
	.log().all();
	
}

@Test
public void post()
{
//	given()
//	.param("q", "Kolkata")
//	.param("appid", "5cf8430b7171d0e5ffe1fa10a7932bec")
//	.when()
//	.get("http://api.openweathermap.org/data/2.5/weather")
//	.then()
//	.statusCode(200).log().all();
	
	JSONObject jsn = new JSONObject();
	
	jsn.put("email", "a@gmail.com");
	jsn.put("password", "pistol");
	
	given()
	.header("Content-Type","application/json")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(jsn.toJSONString())
	.when()
	.post("https://reqres.in/api/users")
	.then()
	.statusCode(201);
	
}

@Test
public void put()
{
	JSONObject jsn = new JSONObject();
	
	jsn.put("email", "a@gmail.com");
	jsn.put("password", "pistol");
	
	given()
	.header("Content-Type", "application/json")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(jsn.toJSONString())
	.when()
	.post("https://reqres.in/api/users/2")
	.then()
	.statusCode(201).log().all();
}

@Test
public void patch()
{
JSONObject jsn = new JSONObject();
	
	jsn.put("email", "a@gmail.com");
	jsn.put("password", "pistol");
	
	given()
	.header("Content-Type", "application/json")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(jsn.toJSONString())
	.when()
	.patch("https://reqres.in/api/users/2")
	.then()
	.statusCode(200).log().all();
}

@Test
public void delete()
{
   	given()
    .when()
    .delete("https://reqres.in/api/users/2")
	.then()
	.statusCode(200).log().all();
}
}
    