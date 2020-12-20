package stepDefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.LoginPage;

public class LoginPageSteps {
	//create object of login page
	LoginPage lgpg = new LoginPage();
	@Given("^The application url is launched$")
	public void the_application_url_is_launched() throws Throwable {
	    System.out.println("first step");
	    lgpg.ValidatLoginPage();
	}

	@When("^I enter username in the username field$")
	public void i_enter_username_in_the_username_field() throws Throwable {
	    System.out.println("second step");

	}

	@When("^I enter password in the password field$")
	public void i_enter_password_in_the_password_field() throws Throwable {
	    System.out.println("third step");

	}

	@Then("^I clicked on login button$")
	public void i_clicked_on_login_button() throws Throwable {
	    System.out.println("fourth step");

	}

	@Then("^I successfully logged in the application$")
	public void i_successfully_logged_in_the_application() throws Throwable {
	    System.out.println("fifth step");

	}
}
