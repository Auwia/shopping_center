package login_shopping.app;

import java.util.concurrent.TimeUnit ;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.IAnnotationTransformer;
import org.testng.ITest;
import org.testng.annotations.ITestAnnotation;
import org.testng.TestNG;

import org.junit.BeforeClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class AppTest implements IAnnotationTransformer 
{
    @Test(invocationCount = 1, threadPoolSize = 1)
    public void shouldAnswerWithTrue()
    {
	try{
		Properties prop = new Properties();
		InputStream inputStream = new FileInputStream("resources/config.properties"); 
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file not found in the classpath");
		}
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String max_login = prop.getProperty("max_login");
		String max_browser_parallel = prop.getProperty("max_browser_parallel");
		System.out.println("BYE: " + username);
		WebDriver driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        	driver.get("https://www.radimage.it/3gs/");
        	System.out.println("Page Title is " + driver.getTitle());
        	Assert.assertEquals("Shopping Center", driver.getTitle());
		driver.findElement(By.id("pagelink_0")).click();
                driver.findElement(By.id("username")).sendKeys(username);
                driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='login']")).click();
                driver.quit();
	} catch (Exception ex) {
		System.out.println("Error: " + ex);
	} finally {
		System.out.println("ciao.");
	}
    }
}
