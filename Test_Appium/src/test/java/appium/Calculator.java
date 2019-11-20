package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Calculator {
	
	 private static  AppiumDriver<WebElement> driver;
	    

	@BeforeTest
	public void setUp() throws MalformedURLException {
		
		//Set up desired capabilities and pass the Android app-activity and app-package to Appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel");
		capabilities.setCapability("VERSION", "8.1.0"); 
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("platformName","Android");
	 
	   
	   capabilities.setCapability("appPackage", "com.android.calculator2");
	// This package name of your app (you can get it from apk info app)
		capabilities.setCapability("appActivity","com.android.calculator2.Calculator"); // This is Launcher activity of your app (you can get it from apk info app)
	//Create RemoteWebDriver instance and connect to the Appium server
	 //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver=new AndroidDriver<WebElement>(url,capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void Sum() {

		System.out.println("Calculate sum of two numbers");
		//Locate elements using By.name() to enter data and click +/= buttons
		driver.findElement(By.id("com.android.calculator2:id/digit_9")).click();
		driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
		driver.findElement(By.id("com.android.calculator2:id/digit_1")).click();
		driver.findElement(By.id("com.android.calculator2:id/eq")).click();
		
		// Get the result text
		WebElement sumOfNumbersEle = driver.findElement(By.id("com.android.calculator2:id/formula"));
		String sumOfNumbers = sumOfNumbersEle.getText();
		
		//verify if result is 10
		Assert.assertTrue(sumOfNumbers.endsWith("10"));
	}

	@AfterTest
	public void End() {
		driver.quit();
	}
	

}
