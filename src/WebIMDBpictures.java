import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;


public class WebIMDBpictures {
		
	public static void main(String[] args) throws MalformedURLException, InterruptedException, AWTException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "MyAndroid");
		cap.setCapability("browserName", "Chrome");
		cap.setCapability("platformName", "com.android.chrome");
		cap.setCapability("platformName", "com.google.android.apps.chrome.Main");
		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		WebDriverWait wait = new WebDriverWait(driver, 25);
		
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
		
		driver.findElement(By.xpath("//section[@id='titleOverview']/div[@class='media']/a")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
		driver.findElement(By.xpath("//div[@class='controls text-center']/a[@class='close']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		
		List<WebElement> pictures = driver.findElements(By.xpath("//section[@id='links']/a"));
		//System.out.println(pictures.size());
		
		List<String> sources = new ArrayList<String>();
		
		for (int i = 0; i < pictures.size(); i++) {
			String src = pictures.get(i).getAttribute("href");
			sources.add(src);
		}
		
		for (int i = 0; i < sources.size(); i++) {
			System.out.println(sources.get(i));
		}
		
		Thread.sleep(3000);
		
		driver.quit();
		
		WebDriver driver2 = new FirefoxDriver();
		Robot robot = new Robot();
		
		for (int i = 0; i < sources.size(); i++) {
			driver2.get(sources.get(i));
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_S);
			robot.delay(500);
			/*for (int j = 0; j < 5; j++) {
				robot.keyPress(KeyEvent.VK_TAB);
			}*/
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(500);
		}
		
		driver2.close();
		
		
		
		

		
		

	}

}
