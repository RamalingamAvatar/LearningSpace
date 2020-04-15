import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class myntra {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath("//a[@data-group = 'women']"));
		action.moveToElement(we).build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(), 'Jackets & Coats')]")).click();
		
		Thread.sleep(2000);
		//Surpass notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
//        // accepting alert
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
        Thread.sleep(2000);
        
      //Get total count      		
      		String str = driver.findElementByClassName("title-count").getText();
      		String text = str.replaceAll("\\D","");
      		int intTotalCountOfItem =  Integer.parseInt(text);
      		
      	//get count of Jacket and coats    		
    		WebElement category_Jacket = driver.findElementByXPath("(//ul[@class='categories-list']//span[@class='categories-num'])[1]");
    		WebElement category_Coats = driver.findElementByXPath("(//ul[@class='categories-list']//span[@class='categories-num'])[2]");
    		
    		int intcategory_Jacket = Integer.parseInt(category_Jacket.getText().replaceAll("\\D", ""));
    		int intcategory_Coats = Integer.parseInt(category_Coats.getText().replaceAll("\\D", ""));
		
    		if((intcategory_Jacket+intcategory_Coats)==intTotalCountOfItem) {
    			System.out.println("Count matches");
    		}else {
    			System.out.println("Count is not matched");
    		}
    		
    		//Check coats:
    		//----------
    		
    		driver.findElementByXPath("//label[text()='Coats']//div").click();
    		
    		Thread.sleep(2000);
    		
    		//Click more under brand
    		//------------------------
    		
    		driver.findElementByXPath("//div[@class='brand-more']").click();
    		
    		Thread.sleep(2000);
    		
    		
			//Search brand:
    		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']")));
    		
    		driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']").sendKeys("MANGO");
    		
    		//Select MANGO check box:
    		//---------------------
    		//input[@value='MANGO']/parent::label[@class='vertical-filters-label common-customCheckbox']
    		
    		//wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//input[@value='MANGO']")));
    		
    		driver.findElementByXPath("//span[@class='FilterDirectory-count']/following-sibling::div").click();
    		
    		
    		//click close button
    		//----------------
    		
    		driver.findElementByXPath("//span[contains(@class,'myntraweb-sprite FilterDirectory-close')]").click();
    		
    		/*
    		String str_CoatsAndJacketsItems = driver.findElementByClassName("title-count").getText();
    		
    		String text_str_CoatsAndJacketsItems = str.replaceAll("\\D","");
    		 
    		int intTotalCountOfItem_CoatsAndJackets =  Integer.parseInt(text_str_CoatsAndJacketsItems);*/
    		
    		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//ul[@class='results-base']//h3[@class='product-brand'])[1]")));
    		
    		List<WebElement> lst = driver.findElementsByXPath("//ul[@class='results-base']//h3[@class='product-brand']");
    		
    		boolean bool_IfAnyNotMango = false;
    		
    		
    		
    		for(WebElement ele:lst)
    		{
    			//wait.until(ExpectedConditions.visibilityOf(ele));
    			if(!ele.getText().equalsIgnoreCase("MANGO"))
    			{
    				bool_IfAnyNotMango = true;
    			}
    			
    		}
    		
    		if(bool_IfAnyNotMango==false)
    		{
    			System.out.println("All Mango brands are only displayed");
    		}
    		
    		//Click on Sort by and select better discount
    		//----------------------------------------------
    		
    		action.moveToElement(driver.findElementByXPath("//div[@class='sort-sortBy']")).build().perform();
    		
    		
    		action.moveToElement(driver.findElementByXPath("//label[text()='Better Discount']")).click().perform();
    		
    		
    		//Find the price of first displayed item
    		//--------------------------------------
    		
    		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//ul[@class='results-base']//span[@class='product-discountedPrice'])[1]")));
    		
    		String text_Price = driver.findElementByXPath("(//ul[@class='results-base']//span[@class='product-discountedPrice'])[1]").getText();
    		
    		String text_PriceAlone = text_Price.replaceAll("\\D","");
    		
    		int intPrice = Integer.parseInt(text_PriceAlone);
    		
    		System.out.println("Price of first displayed item - "+intPrice);
    		
    		//Mouse over on size of the first item. Click on Wishlist Now
    		//--------------------------------------------------------
    		
    		
    		
    		action.moveToElement(driver.findElementByXPath("(//h4[@class='product-product'])[1]")).build().perform();
    		
    		driver.findElementByXPath("(//ul[@class='results-base']//span[@class='product-actionsButton product-wishlist product-prelaunchBtn'])[1]").click();
    		
    		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    		
    		if(driver.getTitle().equalsIgnoreCase("Login"))
    		{
    			driver.close();
    		}
	}

}
		
