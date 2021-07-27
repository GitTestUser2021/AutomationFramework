package cucumberExecutor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.framework.utils.EWSEmailService;
import com.framework.utils.GlobalVariables;
import com.framework.utils.PropertyFileUtils;
import com.testdata.DBManager;
import com.webdrivers.WebDriverManager;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources" }, 
				 glue = {"pageObjects", "cucumberExecutor" }, 
				 dryRun = false, 
				 monochrome = true, 
				 plugin = { "pretty",
						    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						    "json:target/cucumber-reports/cucumber.json"}, 
//				 tags = "@regression",
						 tags = "@ThirdFeatureFile",
				 publish = false)

public class CucumberRunner extends CustomAbstractTestNGCucumberTests {

	@BeforeSuite
	public void initialSetUp() {

		/* Initiating configuration properties file */
		GlobalVariables.configProp = new PropertyFileUtils("config.properties");
		System.out.println(GlobalVariables.configProp.getProperty("environment"));
		
		/* Initiating TestData & Feature file execution list*/
		DBManager.InitializeDB(GlobalVariables.configProp.getProperty("DBType"));
		
		WebDriverManager.configureDriver();
	}
	
	@AfterSuite
	public void teardown() {
		//Open html test result for temporary purpose				
		try {
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"\\target\\Spark\\ExtentSpark.html").toURI());
			EWSEmailService.sendeMail();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
}