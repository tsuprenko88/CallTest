import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;


public class WebIMDB {
	
	public static AndroidDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "MyAndroid");
		cap.setCapability("browserName", "Chrome");
		cap.setCapability("platformName", "com.android.chrome");
		cap.setCapability("platformName", "com.google.android.apps.chrome.Main");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		wait = new WebDriverWait(driver, 25);
		
		driver.get("http://m.imdb.com/");
		
		for (String contextName : driver.getContextHandles()) {
			//System.out.println(contextName);
			if (contextName.contains("WEBVIEW")) {
				driver.context(contextName);
				break;
			}
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
		driver.findElement(By.id("suggestion-search")).sendKeys("Jurassic World");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("autocomplete")));
		
		List<WebElement> names = driver.findElements(By.xpath("//div[@id='autocomplete']/a/div[@class='label']"));
		
		for (int i = 0; i < names.size(); i++) {
			//System.out.println(names.get(i).getText());
			if (names.get(i).getText().contains("Jurassic World (2015)")) {
				names.get(i).click();
				break;
			}
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
		System.out.println(driver.findElement(By.xpath("//p[@itemprop='description']")).getText());
		System.out.println();
		
		WebElement details = driver.findElement(By.xpath("//div[@class='container']/div[4]/div[1]/section"));
		//System.out.println(details.getText());
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		Point point = details.getLocation();
		//System.out.println(point.getX() + "x" + point.getY());
		
		jse.executeScript("window.scrollBy(0,"+point.getY()+")", "");
		
		System.out.println(details.getText());
		
		Thread.sleep(8000);
		
		driver.quit();

		
		

	}

}
