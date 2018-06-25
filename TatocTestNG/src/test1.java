import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class test1 {
WebDriver driver=new ChromeDriver();
	
	@BeforeClass
	//open TATOC
	public void LaunchURL(){
		System.setProperty("webdriver.chrome.driver","D:\\softwares\\chromedriver_win32\\chromedriver.exe");
	driver.get("http://10.0.1.86/tatoc");//Hit the URL
	
	}

}
