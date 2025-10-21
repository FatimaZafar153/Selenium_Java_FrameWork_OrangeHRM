package stepDefinitions;

import driver.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("user is on the OrangeHRM login page")
    public void user_is_on_the_login_page() {
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("user enters valid username and password")
    public void user_enters_valid_credentials() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_credentials() {
        loginPage.enterUsername("InvalidUser");
        loginPage.enterPassword("wrongPassword");
    }

    @And("clicks on the login button")
    public void user_clicks_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("user should be navigated to the Dashboard page")
    public void user_should_be_navigated_to_dashboard() {
        String title = loginPage.getPageTitle();
        System.out.println("Page title: " + title);
        Assert.assertTrue(title.contains("OrangeHRM"),
                "User is not on Dashboard page!"); // OrangeHRM dashboard title check
    }

    @Then("user should see an error message")
    public void user_should_see_error_message() {
        String actualError = loginPage.getErrorMessage();
        System.out.println("Actual Error Message: " + actualError);
        Assert.assertTrue(actualError.contains("Invalid credentials"),
                "Error message not shown or incorrect!");
    }
}
