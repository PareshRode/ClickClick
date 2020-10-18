package stepdefinations;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

	public WebDriver driver;

	public void getWebdriver() throws Throwable {
		System.setProperty("webdriver.chrome.driver","E:\\NewSelenium\\Workspace\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	public void take_a_screenshot(String gridValue) throws Throwable {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("E:\\NewSelenium\\EclipsWorkspace\\screenshot\\GridInput"+gridValue+".jpg"));

	}
	public void close_the_browser() throws Throwable {
		driver.quit(); 
	}    
	
	public void selectElementbyXpath(int row,int col) throws Throwable {
		driver.findElement(By.xpath("/html/body/div/div[1]/div["+row+"]/div["+col+"]")).click();	
		}
	
	public void selectElementbyId(int row,int col) throws Throwable {
		driver.findElement(By.id(""+row+""+col)).click();	
		}


}
