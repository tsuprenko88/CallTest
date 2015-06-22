import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utility.Utils;


public class MyFilesPictures extends Utils {
	
	public AndroidDriver driver;
	public WebDriverWait wait;
	public String desiredPhoto = "20150607_182619.jpg";
	public Point point;
	
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
		
		List<WebElement> mainList = driver.findElements(By.id("text1"));
		
		mainList.get(1).click();
				
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'DCIM')]")).click();
		
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Camera')]")).click();
		
		boolean found = false;
		String temp = new String();
		
		while (true) {
			List<WebElement> names = driver.findElements(By.id("text1"));
			
			if (names.get(0).getText().equals(temp)) {
				System.out.println("Desired photo is absent in this folder");
				break;
			}
			
			temp = names.get(0).getText();
			
			int lastPhoto = names.size();
			
			for (int i = 0; i < lastPhoto; i++) {
				System.out.println(names.get(i).getText() + " != " + desiredPhoto);
				if (names.get(i).getText().equals(desiredPhoto)) {
					names.get(i).click();
					Thread.sleep(5000);
					found = true;
					break;
				}
			}
			
			if (found) {
				break;
			}
			else {
				point = names.get(lastPhoto - 1).getLocation();
				int startX = point.getX();
				int startY = point.getY() + names.get(lastPhoto - 1).getSize().getHeight() + 49;
				if (startY > 1920) {
					point = names.get(lastPhoto - 2).getLocation();
					startY = point.getY() + names.get(lastPhoto - 1).getSize().getHeight() + 49;
				}
				System.out.println(startX + "x" + startY + " --> " + startX + "x" + 312);
				driver.swipe(startX, startY, startX, 312, 4000);
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
				
		driver.quit();
		
		
		
	}

}
