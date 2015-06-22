import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utility.Utils;


public class MyFilesPicturesSwipe extends Utils {
	
	public AndroidDriver driver;
	public WebDriverWait wait;
	
	@Test
	public void cameraTest() throws Exception {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "MyAndroid");
		cap.setCapability("appPackage", "com.sec.android.app.myfiles");
		cap.setCapability("appActivity", "com.sec.android.app.myfiles.MainActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		wait = new WebDriverWait(driver, 25);
		
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'SD card')]")).click();
				
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'DCIM')]")).click();
		
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Camera')]")).click();
		
		List<WebElement> names = driver.findElements(By.id("text1"));
		
		names.get(0).click();
		
		Thread.sleep(2000);
		
		for (int i = 0; i < 5; i++) {
			driver.swipe(900, 960, 100, 960, 1000);
			Thread.sleep(2000);
		}
		
		
		
		
		
		
		
		
		
				
		driver.quit();
		
		
		
	}

}
