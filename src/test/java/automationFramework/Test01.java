package automationFramework;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Reusables.*;
import Utility.Contants;

public class Test01 {
	
	static WebDriver driver;
	ModularReuse mReuse;
	JSONParsing jp;
	RestReuse restReuse;
	static ExtentTest test;
	static ExtentReports report;
	/*
	 * Main class on the program
	 */
	/*
	 * Initializing the Before class method to invoke the necessary methods before the start of application
	 */
	@BeforeClass
	public void launch() throws Exception,IOException {
		System.setProperty(Contants.driver_name,System.getProperty("user.dir")+Contants.driver_path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Contants.URL);
		report = new ExtentReports(System.getProperty("user.dir")+Contants.report_path);
		test = report.startTest("Phase 01");
		mReuse = new ModularReuse(driver, test, report);
		jp = new JSONParsing();
	}
	/*
	 * This test method is Main method to access the webelement and achieve the expected outcome for UI page
	 */
	@Test
	public void searchq() throws IOException, InterruptedException, ParseException{
		
		mReuse.menu_select(jp.read_json("Data"));
		mReuse.choose_city(jp.read_json("City"));
		mReuse.validate_city(jp.read_json("City"));
		mReuse.map_city_selection(jp.read_json("City"));
	}
	/*
	 * This test method is Main method to Build the API and get expected outcome for Rest API
	 */
	@Test (dependsOnMethods = "searchq")
	public void build_api() throws IOException, ParseException {
		test = report.startTest("Phase 02");
		restReuse = new RestReuse();
		//using city_name to pass the value in dynamic URI for dynamic use
		String city_name = jp.read_json("City");
		// The String responseString is used to store the content of Http response.
		String responseString =
			given().
				param("name",jp.read_json("City")).
			when().
				get("https://api.openweathermap.org/data/2.5/weather?q={value}&appid=7fe67bf08c80ded756e598d6f8fedaea",city_name).asPrettyString();
		//Converting Http response into a JsonObject.
		JsonObject json_object = new Gson().fromJson(responseString, JsonObject.class);
		//Search for an element using getAsJSONObject method to get the specific value from Http Response
		JsonObject main_object = json_object.getAsJsonObject("main");
		String strHumidity = main_object.get("humidity").getAsString();
		String strTemperate_kelvin = main_object.get("temp").getAsString();
		//Using Assertion to validate the response body by comparing with UI value
		try {
			Assert.assertTrue(restReuse.humidity_range(Double.parseDouble(jp.read_json("Humidity")), 5).contains(Double.parseDouble(strHumidity)));
			test.log(LogStatus.PASS,"Actual and Expected range met : Expected Range= " + 
					String.valueOf(restReuse.humidity_range(Double.parseDouble(jp.read_json("Humidity")), 5)) + 
					" Actual = " + strHumidity);
		}
		catch(AssertionError e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Humidity value between UI and API not match : Expected Range between " + 
					String.valueOf(restReuse.humidity_range(Double.parseDouble(jp.read_json("Humidity")), 5)) + 
					" But the Actual value is - " + strHumidity);
		}
		try {
			Assert.assertTrue(restReuse.c_to_k_range(Double.parseDouble(jp.read_json("temp_celcius")), 5).contains(Double.parseDouble(strTemperate_kelvin)));
			test.log(LogStatus.PASS,"Actual and Expected range met : Expected Range= " + 
					String.valueOf(restReuse.c_to_k_range(Double.parseDouble(jp.read_json("temp_celcius")), 5)) + 
					" Actual = " + strTemperate_kelvin);
		}
		catch(AssertionError e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Degree Celcius value between UI and API not match : Expected Range between " + 
					String.valueOf(restReuse.c_to_k_range(Double.parseDouble(jp.read_json("temp_celcius")), 5)) + 
					" But the Actual value is - " + strTemperate_kelvin);
		}
		try {
			Assert.assertTrue(restReuse.f_to_k_range(Double.parseDouble(jp.read_json("temp_fahrenheit")),5).contains(Double.parseDouble(strTemperate_kelvin)));
			test.log(LogStatus.PASS,"Actual and Expected range met : Expected Range= " + 
					String.valueOf(restReuse.f_to_k_range(Double.parseDouble(jp.read_json("temp_fahrenheit")), 5)) + 
					" Actual = " + strTemperate_kelvin);
		}
		catch(AssertionError e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Fahernheit value between UI and API not match : Expected Range between " + 
					String.valueOf(restReuse.f_to_k_range(Double.parseDouble(jp.read_json("temp_fahrenheit")), 5)) + 
					" But the Actual value is - " + strTemperate_kelvin);
		}
	}
	/*
	 * This After class method close the result instance
	 */
	@AfterClass
	public static void endTest() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}
