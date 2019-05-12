package sampletests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class testNG_Navigate {
	 	
	
		//public String baseUrl = "http://live.guru99.com/";
	    public WebDriver driver ; 
	
	    @Parameters({"url"})
	    @BeforeTest    
	    
	      public void launchBrowser(String baseUrl) throws Exception {
	    	  System.out.println("launching chrome browser..."); 
	          driver = new ChromeDriver();
	          try{
	        	  driver.get(baseUrl);
	        	  }
	          catch(Exception e){
	              Assert.fail("Something's wrong with the search!");
	          }
	      }
	    	    
	  @Test(priority=0)
	  public void verifyHomepageTitle() {
	 
	      String expectedTitle = "Home page";
	      String actualTitle = driver.getTitle();
	      Assert.assertEquals(actualTitle, expectedTitle);
	  
	  }
	  
	  @AfterTest
      public void terminateBrowser(){
          driver.quit();
      }
	}