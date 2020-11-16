package Reusables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.apache.commons.lang3.Range;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.*;
public class ModularReuse{
	WebDriver driver;
	FuncReuse reuse;
	JSONParsing jp;
	static ExtentTest test;
	static ExtentReports report;
	/*
	 * This Class is to reuse the Methods as per Module-wise approach.
	 */
	//Used Parameterized Constructor to Instantiate Driver, methods and Extend reports
	public ModularReuse(WebDriver driver, ExtentTest test, ExtentReports report) throws IOException{
		this.driver = driver;			
		ModularReuse.test = test;
		ModularReuse.report = report;
		reuse = new FuncReuse();
		jp = new JSONParsing();
	}
	/*
	 * The method menu_select helps to click any method available in the Header of webpage using dynamic xpath axes
	 * with parameter as String menu
	 */
	public void menu_select(String menu) throws IOException {
		reuse.static_wait(driver,10);
		reuse.click_event(driver, "//*[@id='h_sub_menu']");
		reuse.click_event(driver, "//*[@class='chrome']//a[text()[contains(.,'"+menu+"')]]");
		report("Menu Selection Selected: " + menu);
	}
	/*
	 * The method choose_city is used to enter the city name in Search box using Javascript Executor class
	 * and click on the drop down list after search result found on the screen using dynamic web table.
	 */
	public void choose_city(String city_name) throws InterruptedException, IOException {
		reuse.page_load(driver);
		reuse.enter_event(driver,"//*[@id='searchBox']",city_name);
		//driver.findElement(By.xpath("//*[@id='searchBox']")).sendKeys(city_name);
		reuse.static_wait(driver, 2);
		List<WebElement> cities = driver.findElements(By.xpath("//*[@id='messages']//div"));
		for(WebElement city: cities) {
			if(city.findElement(By.tagName("Label")).getAttribute("for").equalsIgnoreCase(city_name)) {
				WebElement web_lbl = city.findElement(By.tagName("Label"));
				web_lbl.findElement(By.tagName("input")).click();
			}
		}
		report("City has chosen: " + city_name);
	}
	/*
	 * The method validate_city helps to validate the City name by comparing the City dislpayed in map
	 * and given input parameter using Assertion class 
	 */
	public void validate_city(String city_name) throws IOException, ParseException {
		String temp_celcius = driver.findElement(By.xpath("//*[@title='"+city_name+"']//div[@class ='temperatureContainer']/span[1]")).getText();
		String temp_fahrenheit = driver.findElement(By.xpath("//*[@title='"+city_name+"']//div[@class ='temperatureContainer']/span[2]")).getText();
		String city_in_map = driver.findElement(By.xpath("//*[@title='"+city_name+"']//div[@class = 'cityText']")).getText();
		try{
			Assert.assertEquals(city_in_map.toString(), city_name.toString());
			report("Both Given City and expected outcome City value met: " + "Given City : " + city_name + "UI Result : " + city_in_map);
		}
		catch(AssertionError ae) {
			ae.printStackTrace();
			reportFail("The city name from Map and given city name not match, Hence failed : "
					+ "Given city : " + city_name + "UI Result : " + city_in_map);
		}
		
	}
	/*
	 * The method map_city_selection helps to get the output from the input parameter - city_name
	 * This method get the Humidity, Degree and Fahrenheit value available in the map after clicking city name
	 */
	public void map_city_selection(String city_name) throws IOException, ParseException, InterruptedException {
		driver.findElement(By.xpath("//*[@title='"+city_name+"']//div[@class = 'cityText']")).click();
		Thread.sleep(2000);
		List<WebElement> list_selection = driver.findElements(By.xpath("//*[@id='map_canvas']/div[1]/div[6]/div/div[1]//span"));
		for(WebElement selection : list_selection) {
			if(!selection.getText().toString().isEmpty()) {
				System.out.println(selection.getText().toString());
				if(selection.getText().contains("Humidity")) {
					String []str = selection.getText().split(":");
					jp.write_json(str[0], str[1].replace("%", ""));
					report("Humidity : " + selection.getText().toString());
				}
				else if(selection.getText().contains("Degrees")) {
					String []str = selection.getText().split(":");
					jp.write_json("temp_celcius", str[1]);
					report("Temperate Degree Celcius : " + selection.getText().toString());
				}
				else if(selection.getText().contains("Fahrenheit")) {
					String []str = selection.getText().split(":");
					jp.write_json("temp_fahrenheit", str[1]);
					report("Temperature in Fahrenheit : " + selection.getText().toString());
				}
			}
		}
	}
	public void report(String msg) throws IOException {
		test.log(LogStatus.PASS, test.addScreenCapture(reuse.take_Screenprint(driver)),msg);
	}
	public void reportFail(String msg) throws IOException {
		test.log(LogStatus.FAIL, test.addScreenCapture(reuse.take_Screenprint(driver)),msg);
	}
}
