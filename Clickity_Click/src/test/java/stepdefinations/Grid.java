package stepdefinations;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Grid {

	private static String defaultComparator = "55";
	private static String nonPerimeterIcon = "22";
	BaseClass baseClass = new BaseClass();



	@Given("^user is  on homepage$")
	public void user_is_on_homepage() throws Throwable {
		baseClass.getWebdriver();
		baseClass.driver.get("file:///C:/Users/Paresh/Downloads/QA task with ID.html");
	}

	@When("^user selects all icons of outer perimeter$")
	public void user_selects_all_icons_of_outer_perimeter() throws Throwable {
		int defaultGridValue=4;
		for(int i=1;i<=defaultGridValue;i++){  
			for(int j=1;j<=defaultGridValue;j++){  
				if (i==1 || i==defaultGridValue)
					baseClass.driver.findElement(By.id(""+i+""+j)).click();
				if (((j==1 || j==defaultGridValue) && (i!=1 && i!=defaultGridValue)))
					baseClass.driver.findElement(By.id(""+i+""+j)).click();
			}  

		}  
	}

	@Then("^dialog appears$")
	public void dialog_appears() throws Throwable {
		String exp_message = "Done! Ready for the next try? Give me a size [3-9]:";
		String actual_message = baseClass.driver.switchTo().alert().getText();
		Assert.assertEquals(exp_message, actual_message);

	}

	@When("^user enters grid size as \"([^\"]*)\"$")
	public void user_enters_grid_size_as(String gridValue) throws Throwable {
		Alert alertDialog= baseClass.driver.switchTo().alert();
		alertDialog.sendKeys(gridValue);
		alertDialog.accept();

	}

	@Then("^new grid should be displyed of size \"([^\"]*)\"$")
	public void new_grid_should_be_displyed_of_size(String gridValue) throws Throwable {

		Assert.assertTrue("Grid of given size is not displyed", baseClass.driver.findElement(By.id(""+gridValue+""+gridValue)).isDisplayed());
		baseClass.take_a_screenshot(gridValue);
		baseClass.close_the_browser();

	}

	@Then("^error dialog appears$")
	public void error_dialog_appears() throws Throwable {
		String exp_message = "Not a good size!";

		try {
			Alert alertError= baseClass.driver.switchTo().alert();
			String actual_message = alertError.getText();
			Assert.assertEquals(exp_message, actual_message);
			alertError.accept();
		}catch (NoAlertPresentException e) 
		{ 
			baseClass.close_the_browser();
			Assert.assertFalse("No Error dialog present", true);	
		} 

	}

	@Then("^new grid should be displyed of default size for input \"([^\"]*)\"$$")
	public void new_grid_should_be_displyed_of_default_size_for_input(String gridValue) throws Throwable {

		boolean exists = baseClass.driver.findElements( By.id(defaultComparator) ).size() != 0;
		if (exists) {
			baseClass.take_a_screenshot(gridValue);
			baseClass.close_the_browser();
			Assert.assertFalse("Grid Size is not default", exists);
		}
		else {
			baseClass.take_a_screenshot(gridValue);
			baseClass.close_the_browser();
		}
	}


	@When("^user selects any non perimeter icon$")
	public void user_selects_any_non_perimeter_icon() throws Throwable {
		baseClass.driver.findElement(By.id(nonPerimeterIcon)).click();	
	}

	@Then("^dialog does not appear$")
	public void dialog_does_not_appear() throws Throwable {
		try {
			baseClass.driver.switchTo().alert();
			baseClass.driver.quit(); 
			Assert.assertFalse("Dialog is displayed, It shouldn't!" , true);        	
		}catch (NoAlertPresentException e) 
		{ 
			File scrFile = ((TakesScreenshot)baseClass.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("E:\\NewSelenium\\EclipsWorkspace\\screenshot\\NonPerimetereIcon.jpg"));
			baseClass.close_the_browser();
			Assert.assertTrue("No dialog present", true);
		} 
	}






}