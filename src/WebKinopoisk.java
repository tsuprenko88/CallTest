import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebKinopoisk {

	public static AndroidDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "MyAndroid");
		cap.setCapability("browserName", "Chrome");
		cap.setCapability("appPackage", "com.android.chrome");
		cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		wait = new WebDriverWait(driver, 25);
		
		driver.get("http://www.kinopoisk.ru/");
				
		for (String contextName : driver.getContextHandles()) {
			System.out.println();
			if (contextName.contains("WEBVIEW")) {
				driver.context(contextName);
				break;
			}
		}
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
		driver.findElement(By.xpath("//div[@id='page']/div[@class='continue']/a")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
		driver.findElement(By.id("search_input")).sendKeys("Jurassic World");
		
		driver.findElement(By.xpath("//input[@class='searchButton1']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
		List<WebElement> russianName = driver.findElements(By.xpath("//div[@class='info']/p[@class='name']/a"));
		
		List<WebElement> englishName = driver.findElements(By.xpath("//div[@class='info']/span[1]"));
		
		for (int i = 0; i < russianName.size(); i++) {
			if (englishName.get(i).getText().contains("Jurassic World")) {
				russianName.get(i).click();
				break;
			}
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
		
		
		
		
		
		
		
		driver.quit();
		
		
	}

}
