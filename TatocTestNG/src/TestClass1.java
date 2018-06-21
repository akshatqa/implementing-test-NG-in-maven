import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class TestClass1 {
	
	WebDriver driver=new ChromeDriver();
	
	@BeforeClass
	//open TATOC
	public void LaunchURL(){
		System.setProperty("webdriver.chrome.driver","D:\\softwares\\chromedriver_win32\\chromedriver.exe");
	driver.get("http://10.0.1.86/tatoc");//Hit the URL
	
	}
	
	@Test
	
	public void TestFirstProblem(){
		driver.findElement(By.linkText("Basic Course")).click();//Clicks on Basic Course
		driver.findElement(By.className("greenbox")).click();//Click Green Box
		System.out.println("First Test Case is passed successfully");
	}
	
	@Test
	
	public void TestSecondProblem(){
		 driver.switchTo().frame("main");
		    String box1 = driver.findElement(By.id("answer")).getAttribute("class");// get first box color
		    
		    while(true){
		            
		            driver.findElement(By.cssSelector("a")).click(); //click on repaint box 2
		            driver.switchTo().frame("child"); //goto child frame for box2
		            String box2 = driver.findElement(By.id("answer")).getAttribute("class");// get second box color
		            driver.switchTo().parentFrame(); //go back to parent form for repainting box
		            if(box1.equals(box2))
		            {
		                driver.findElements(By.cssSelector("a")).get(1).click(); //clicking on proceed
		                break;
		            }
		        }
		    System.out.println("Second Test Case is passed successfully");
	}
	
	@Test
	
	public void TestThirdProblem(){
		WebElement source=driver.findElement(By.id("dragbox"));//Stores dragbox in webelement
		WebElement destination=driver.findElement(By.id("dropbox"));//Stores dropbox in webelement
		Actions action=new Actions(driver);//Initiate Actions object
		action.dragAndDrop(source, destination).perform();;//performing drag and drop
		driver.findElement(By.linkText("Proceed")).click();;//click on proceed
		
		System.out.println("Third Test Case is passed successfully");
	}
	
	@Test
	
	public void TestFourthProblem(){
		String mainWindowHandler = driver.getWindowHandle(); // Store your parent window

	    driver.findElement(By.cssSelector("a")).click(); //click on popup window
	    String childWindowHandler = null;

	    Set<String> handles = driver.getWindowHandles(); // get all window handles
	    System.out.println(handles);
	    
	    Iterator<String> iterator = handles.iterator(); //creating iterator to get popup window
	    while (iterator.hasNext()){
	    childWindowHandler = iterator.next();
	    }
	    driver.switchTo().window(childWindowHandler); // switch to popup window
	    driver.findElement(By.id("name")).sendKeys("Mayank");// writing in area
	    driver.findElement(By.id("submit")).click();// clicking on submit

	    driver.switchTo().window(mainWindowHandler);  // switch back to parent window
	    driver.findElements(By.cssSelector("a")).get(1).click(); //click on proceed
	    
        System.out.println("Fourth Test Case passed successfully");	    
	}
	
	@Test
	
	public void TestFifthProblem(){
		int n=1;
	    while(n>0)
	    {
	        try {
	            driver.findElement(By.linkText("Generate Token")).click(); //click on generate token
	            String value = driver.findElement(By.id("token")).getText().split("Token: ")[1];// get token value
	            n=0; // break the loop
	         } catch (Exception e) {
	            System.out.println(e);
	            driver.navigate().refresh(); //refresh page if generate token does not give result
	         }
	    }
	    
	    String value = driver.findElement(By.id("token")).getText().split("Token: ")[1];//get token value
	    driver.manage().addCookie( new Cookie("Token", value, "/")); //add cookie
	    driver.findElement(By.linkText("Proceed")).click(); //click on proceed
	
	    System.out.println("Fifth Test Case passed successfully");
	}
	
	@AfterClass

	public void closeDriver(){
		driver.close();
		System.out.println("Driver is closed successfully");
	}
}