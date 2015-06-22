import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;


public class GetContactData {
	
	public static AndroidDriver driver;

	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Android");
		cap.setCapability("appPackage", "com.android.contacts");
		cap.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("decor_content_parent")));
				
		String temp = new String();
		
		while (true) {
			
			List<WebElement> contacts = driver.findElements(By.id("cliv_name_textview"));
			
			String currentContact = contacts.get(0).getText();
			
			if (currentContact.equals(temp)) {
				break;
			}
			
			System.out.println(currentContact);
			
			contacts.get(0).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("decor_content_parent")));
			
			List<WebElement> data = driver.findElements(By.id("data"));
			
			for (int i = 0; i < data.size(); i++) {
				String phoneNumber = data.get(i).getText();
				char[] characters = phoneNumber.toCharArray();
				int ascii = (int) characters[0];
				if (ascii >= 65 && ascii <= 90 || ascii >= 97 && ascii <= 122) {
					continue;
				}
				else {
					System.out.println(phoneNumber);
				}
					
				
			}
			
			driver.findElement(By.id("up")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("decor_content_parent")));
			
			Point point = contacts.get(0).getLocation();
								
			int x = 10;
			int y = point.getY() + contacts.get(0).getSize().height + 59;
			//System.out.println(y);
			
			driver.swipe(x, y, x, 435, 1000);
			
			temp = currentContact;
			
		} // end of while
		
		List<WebElement> contacts = driver.findElements(By.id("cliv_name_textview"));
		
		for (int k = 1; k < contacts.size(); k++) {
			System.out.println(contacts.get(k).getText());
			contacts.get(k).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("decor_content_parent")));
			
			List<WebElement> data = driver.findElements(By.id("data"));
			
			for (int i = 0; i < data.size(); i++) {
				String phoneNumber = data.get(i).getText();
				char[] characters = phoneNumber.toCharArray();
				int ascii = (int) characters[0];
				if (ascii >= 65 && ascii <= 90 || ascii >= 97 && ascii <= 122) {
					continue;
				}
				else {
					System.out.println(phoneNumber);
				}
					
				
			}
			
			driver.findElement(By.id("up")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("decor_content_parent")));
			
		}
				
		driver.quit();
		
		

	}

}
