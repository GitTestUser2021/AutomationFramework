package cucumberExecutor;

import org.apache.log4j.Logger;

import com.framework.utils.GlobalVariables;
import com.framework.utils.LoggerHelper;
import com.framework.utils.Reporter;
import com.webdrivers.WebDriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private Logger log = LoggerHelper.getLogger(Hooks.class);
	
	@Before
	public void setup(Scenario scenario)
	{	
		log.debug("******************************************");
		log.debug("Current Scenario Running: " + scenario.getName());
		log.debug("******************************************");
		String uri = scenario.getId();
		GlobalVariables.currentFeature = uri.substring(uri.lastIndexOf('/')+1, uri.indexOf('.'));
		
		Reporter.TScenario.set(scenario);
	}
	
	@After
	public void teardown(Scenario scenario) {
		if(scenario.isFailed()) {
			if(WebDriverManager.getWebUIFlag() == true) {
				
				/*on failure resetting the webui flag*/
				WebDriverManager.setWebUIFlag(false);
				
				/*on failure taking screenshot*/
				Reporter.addScreenCapture("Click on image to see Failure Screenshot !!","#ff1a1a");
				
				/*quitting the browser*/
				WebDriverManager.quitDriver();				
				
			}
		}
		
	}
	
}
