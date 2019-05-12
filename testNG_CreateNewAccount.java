package sampletests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class testNG_CreateNewAccount {
	
	//public String baseUrl = "http://live.guru99.com/";
    public WebDriver driver ; 
    int randNum = (int) (Math.random() * 1000);
    String actualTitle;
    String expectedTitle;

    @Parameters({"url"})
    @BeforeTest    
    
      public void launchBrowser(String baseUrl) throws InterruptedException {
    	  System.out.println("launching chrome browser..."); 
          driver = new ChromeDriver();
         try { 
        	 driver.get(baseUrl);
        	 //Create a new account
		    driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		    driver.findElement(By.xpath("//*[@title=\"Create an Account\"]")).click();
		    
		    //Register new member
		    driver.findElement(By.id("firstname")).sendKeys("Test"+randNum);
		    driver.findElement(By.id("middlename")).sendKeys("Test"+randNum);
		    driver.findElement(By.id("lastname")).sendKeys("Test"+randNum);
		    driver.findElement(By.id("email_address")).sendKeys("Test"+randNum+"@gmail.com");
		    driver.findElement(By.id("password")).sendKeys("Test@123");
		    driver.findElement(By.id("confirmation")).sendKeys("Test@123");
		    driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();
		    
		    WebDriverWait ew = new WebDriverWait(driver,5);//defining explicitly for 5 seconds
		    ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"success-msg\"]")));
         }
         catch(Exception e){
             Assert.fail("Something's wrong with the search!");
         }
      }
	    
    @Test(priority=1)
	  public void createNewAccount() {
		   
		    //Testcase validation
		    actualTitle= driver.findElement(By.xpath("//*[@class=\"success-msg\"]")).getText();
		    //System.out.println(actual_name);
		    
		    expectedTitle = "Thank you for registering with Main Website Store.";
		    Assert.assertEquals(actualTitle, expectedTitle);
		    
	  }
	  
	  @AfterTest
      public void terminateBrowser(){
          driver.quit();
      }
	  
	  
}
