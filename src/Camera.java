import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import utility.Utils;


public class Camera extends Utils {
	
	public AndroidDriver driver;
	
	@Test
	public void camerTest() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "MyAndroid");
		cap.setCapability("appPackage", "com.sec.android.app.camera");
		cap.setCapability("appActivity", "com.sec.android.app.camera.Camera");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		Thread.sleep(2000);
		
		driver.sendKeyEvent(27);
		
		Thread.sleep(5000);
				
		driver.quit();
		
		
		
	}

}
