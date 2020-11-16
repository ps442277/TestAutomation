package Reusables;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utility.*;
public class FuncReuse {
	/*
	 * This Class is completely for reusing the fields which we are using in Webpage
	 */
	
	//Method take_Screenprint is used to take the screenshot of webpage and return the name of file with path to capture in Extent report
	public String take_Screenprint(WebDriver driver) throws IOException {
		String filepath = System.getProperty("user.dir")+Contants.ss_path;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long lng_stamp = timestamp.getTime();
		String filename = filepath.concat(Long.toString(lng_stamp)).concat(".jpeg");
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File srcfile = scrShot.getScreenshotAs(OutputType.FILE);
		File descfile = new File(filename);
		String sspath = descfile.getAbsolutePath();
		FileUtils.copyFile(srcfile, descfile);
		return sspath;
	}
	/*Method page_load is used to dynamically wait for the element when the webpage stopped its loading.
	 *It contains the Javascript executor class which helps to interact with the HTML directly using Javascript method
	 */ 
	public void page_load(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(new Function<WebDriver, Boolean>(){
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}
	//Method static_wait is used to wait for certain amount of time interval, which helps to not to fail until the time limit met.
	public void static_wait(WebDriver driver,int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	//Method scroll_to-view helps to scroll up/down based on the element located on the webpage using Javascript executor class.
	//Parameter passing: element (input parameter to scroll up/down based on the element located)
	public void scroll_to_view(WebDriver driver,WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("argument[0].scrollIntoView();", element);
	}
	//Method click_event helps to click the web elements(Radio button, date field, button) irrespective of its type, since it has xpath to locate
	//Parameter passing: path (helps to locate to the web element using the xpath given as parameter)
	public void click_event(WebDriver driver,String path) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(path)));
	}
	//Method enter_event helps to enter to value or input in text field using the locator as xpath using javascript executor.
	//Parameter passing: driver(instance of driver element), path (helps to locate to the web element), txt (input value to enter into the respective text field)
	public void enter_event(WebDriver driver,String path, String txt) {
		WebElement element = driver.findElement(By.xpath(path));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].value='"+txt+"';", element);
	}
}
