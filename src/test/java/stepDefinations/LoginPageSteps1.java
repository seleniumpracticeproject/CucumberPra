/*
 * package stepDefinations;
 * 
 * import cucumber.api.java.en.And; import cucumber.api.java.en.Given; import
 * cucumber.api.java.en.Then; import cucumber.api.java.en.When; import
 * pageObjects.LoginPage1; import testCases.BaseTest;
 * 
 * public class LoginPageSteps1 extends BaseTest { //here we need to place the
 * steps mentioned on the feature file
 * 
 * @Given("^Initialize the browser$") public void initialize_the_browser()
 * throws Throwable { // Write code here that turns the phrase above into
 * concrete actions driver = initializeDriver(); }
 * 
 * @And("^Navigate to site \"([^\"]*)\"$") public void navigate_to_site(String
 * arg1) throws Throwable { // Write code here that turns the phrase above into
 * concrete actions driver.get(arg1); }
 * 
 * @When("^i enters (.+) and (.+)$") public void i_enters(String username,
 * String password) throws Throwable { LoginPage1 lp = new LoginPage1(driver);
 * lp.getEmail().sendKeys(username); lp.getPassword().sendKeys(password); }
 * 
 * @Then("^i click on log in button$") public void i_click_on_log_in_button()
 * throws Throwable { driver.quit(); }
 * 
 * @And("^close chrome browser$") public void close_chrome_browser() throws
 * Throwable { driver.quit(); }
 * 
 * }
 */