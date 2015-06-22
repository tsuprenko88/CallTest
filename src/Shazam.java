import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Shazam {
	
	public static AndroidDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "MyAndroid");
		cap.setCapability("appPackage", "com.shazam.android");
		cap.setCapability("appActivity", "com.shazam.android.activities.SplashActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		wait = new WebDriverWait(driver, 25);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button_skip")));
		
		driver.findElement(By.id("button_skip")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("view_tagging_button")));
		
		

		
		/*for (String context : driver.getContextHandles()) {
			System.out.println(context);
		}*/
		
		driver.quit();
		
	}

}
