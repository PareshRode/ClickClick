package testrunner;


import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources",
        glue= "stepdefinations",
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:Reports/Clickity_Click/Regression_report.html"}, 
        monochrome = true
                )

public class Runner {
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File("config/report.xml"));
    
    }
}


