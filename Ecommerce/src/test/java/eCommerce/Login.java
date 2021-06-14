package eCommerce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import  org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		
		
		WebDriver driver= new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		SoftAssert softassert= new SoftAssert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.xpath("//a[@class='login']")).click();
		
		
		driver.findElement(By.xpath("//a[@title='Contact Us']")).click();
		driver.navigate().back();
		driver.findElement(By.xpath("//button[@id='SubmitCreate']/span/i")).click();
		
		
		//Error Message Validation
		String expectedErrorMessage="Invalid email address.";
		
		String Actualerrormessage=driver.findElement(By.xpath("//div[@id='create_account_error']/ol/li")).getText();
		
		//There are two types of Assert
		//1. Assert- static  - use the methods by using the name of the class
		//Assert.assertEquals(expectedErrorMessage, Actualerrormessage);
		
		//2. SoftAssert - non-static- create the object for the class and access the methods
		softassert.assertEquals(expectedErrorMessage, Actualerrormessage);
		
		
		//Creating an Account
		driver.findElement(By.id("email_create")).sendKeys("ami@a.com");
		driver.findElement(By.xpath("//button[@id='SubmitCreate']/span/i")).click();
		
		WebElement visibleText=driver.findElement(By.xpath("//div[@id='noSlide']/h1"));
		
		wait.until(ExpectedConditions.visibilityOf(visibleText));
		System.out.println(visibleText.getText());
		
		
		//driver.close();
		
		softassert.assertAll();
		}
		
}
