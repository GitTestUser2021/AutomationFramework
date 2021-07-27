package pageObjects;

import com.webdrivers.WebDriverManager;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class CommonUIStepDefs {
	

	@Given("^user launches the browser$")
    public void user_launch_the_browser(){
		WebDriverManager.startDriver();
		WebDriverManager.setWebUIFlag(true);
    }

    @And("^user closes the browser$")
    public void user_quit_the_browser(){
    	WebDriverManager.quitDriver();
    	WebDriverManager.setWebUIFlag(false);
    }

}
