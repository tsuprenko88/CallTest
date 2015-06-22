import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;


public class Call {

	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Android");
		cap.setCapability("appPackage", "com.android.contacts");
		cap.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		driver.findElement(By.id("tab_custom_layout")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dialpad_container")));
		
		driver.findElement(By.id("seven")).click();
		driver.findElement(By.id("six")).click();
		driver.findElement(By.id("zero")).click();
		driver.findElement(By.id("eight")).click();
		driver.findElement(By.id("zero")).click();
		driver.findElement(By.id("five")).click();
		driver.findElement(By.id("seven")).click();
		driver.findElement(By.id("nine")).click();
		driver.findElement(By.id("seven")).click();
		driver.findElement(By.id("four")).click();
		
		driver.findElement(By.id("dialButton")).click();
		
	}

}
