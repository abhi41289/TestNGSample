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

public class testNG_VerifyAccount {
	
	//public String baseUrl = "http://live.guru99.com/";
    public WebDriver driver ; 
    String actualTitle;
    String expectedTitle;
    
    @Parameters({"url"})
    @BeforeTest    
    public void launchBrowser(String baseUrl) throws InterruptedException {
  	  System.out.println("launching chrome browser..."); 
        driver = new ChromeDriver();
        try {  
        driver.get(baseUrl);
        //login to the account
	    driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	    
	    // Explicit wait
	    WebDriverWait ew = new WebDriverWait(driver,5);//defining explicitly for 5 seconds
	    ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='email']")));
        }
        catch(Exception e)
        {
            Assert.fail("Something's wrong with the search!");
        }
    }
    
    @Parameters({"username","password"})
    @Test(priority=2)
	  public void loginToAccount(String username, String password) {
	
		  //entering credentials
		  driver.findElement(By.xpath("//*[@id='email']")).sendKeys(username);
		  driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		  driver.findElement(By.xpath("//*[@id='send2']")).click();
		  
		  // Explicit wait 
		  WebDriverWait ew = new WebDriverWait(driver,10);
		  ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),\"Hello, Test Test Test!\")]")));
		  expectedTitle ="Hello, Test Test Test!";
		  actualTitle= driver.findElement(By. xpath("//*[contains(text(),\"Hello, Test Test Test!\")]")).getText();
		  Assert.assertEquals(actualTitle, expectedTitle);
		   
    }    
		    
    @Test(priority=2)   
    public void verifyAccount() {  
    		WebDriverWait ew = new WebDriverWait(driver,10);
    		ew.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"dashboard\"]")));
		    expectedTitle = "MY DASHBOARD";
		    actualTitle= driver.findElement(By.xpath("//*[contains(text(),\"My Dashboard\")]")).getText();
		    Assert.assertEquals(actualTitle, expectedTitle);
		    
	  }
    
    @AfterTest
    public void terminateBrowser(){
        driver.quit();
    }
    
}
