package sampletests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class testNG_EndToEnd {
	//public String baseUrl = "http://live.guru99.com/";
    public WebDriver driver; 
    String actualTitle;
    String expectedTitle;
	
    	@Parameters({"url"})
	 	@BeforeTest    
	    public void placeOrder(String baseUrl) {
		 
		 	System.out.println("launching chrome browser..."); 		 	
	        driver = new ChromeDriver();
	        try {  
	        driver.get(baseUrl);
	        
	        //login to the account
		    driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		    
		    //entering credentials
		    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Test300@gmail.com");
		    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Test@123");
		    driver.findElement(By.xpath("//button[@id='send2']")).click();
		    
		    //place mobile order to cart 
		    driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
		    WebDriverWait ew = new WebDriverWait(driver,5);
		    ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@onclick,\"product/2\")]")));
		    driver.findElement(By.xpath("//*[contains(@onclick,\"product/2\")]")).click();    
		    
		    
		    //update order
		    driver.findElement(By.xpath("//*[@class='method-checkout-cart-methods-onepage-bottom']")).click();
  
		   //defining explicitly for 5 seconds
		    ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@onclick,\"billing.save\")]")));
		    
		    if(driver.findElement(By.id("billing:firstname")).isDisplayed()) {
		    	 //checkout				  
				  driver.findElement(By.id("billing:firstname")).sendKeys("Abhishek");
				  driver.findElement(By.id("billing:lastname")).sendKeys("Raj");
				  driver.findElement(By.id("billing:street1")).sendKeys("White House");
				  driver.findElement(By.name("billing[city]")).sendKeys("Washington DC");
				  
				  //Selecting state from dropdown 
				  Select s = new Select(driver.findElement(By.id("billing:region_id")));
					  s.selectByValue("16");
				 //enter zipcode	  
				 driver.findElement(By.id("billing:postcode")).sendKeys("500026");  
				 
				 //Selecting state from dropdown 
				  Select c = new Select(driver.findElement(By.id("billing:country_id")));
				  c.selectByValue("US");
				  
				  //telephone no
				  driver.findElement(By.id("billing:telephone")).sendKeys("9100751649");  
				  
				  //continue button
				  driver.findElement(By.xpath("//div[@id=\"billing-buttons-container\"]//button[@class=\"button\"]")).click(); 
		     }
		     else 
		     {
		    	 driver.findElement(By.xpath("//div[@id=\"billing-buttons-container\"]//button[@class=\"button\"]")).click();  
		     }
		     
		  //Shipping method
		    ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@onclick,'shippingMethod')]")));
			driver.findElement(By.xpath("//*[contains(@onclick,'shippingMethod')]")).click();
			
			//payment information
			ew.until(ExpectedConditions.visibilityOfElementLocated(By.name("payment[method]")));
			List<WebElement> oRadioButton = driver.findElements(By.name("payment[method]"));
			 
			 oRadioButton.get(1).click();
			 
			 driver.findElement(By.xpath("//div[@id='payment-buttons-container']//button[@class='button']")).click(); 
			 
			//order review
			 ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@onclick,\"payment.save\")]")));
			 driver.findElement(By.xpath("//*[contains(@onclick,\"payment.save\")]")).click();
		
			 //place order
			 ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@title,\"Place Order\")]")));
			 driver.findElement(By.xpath("//*[contains(@title,\"Place Order\")]")).click();
				
		    
		    //test validation
			 ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Your order has been received.')]")));
	        }
	        catch(Exception e)
	        {
	            Assert.fail("Something's wrong with the search!");
	        }
			 
		     }
    	@Test(priority=3)   
	    public void orderPlaced() {  

			    expectedTitle = "YOUR ORDER HAS BEEN RECEIVED.";
			    actualTitle= driver.findElement(By.xpath("//*[contains(text(),'Your order has been received.')] ")).getText();
			    Assert.assertEquals(actualTitle, expectedTitle);
			    
		  }
	    
	    @AfterTest
	    public void terminateBrowser(){
	        driver.quit();
	    }
}
