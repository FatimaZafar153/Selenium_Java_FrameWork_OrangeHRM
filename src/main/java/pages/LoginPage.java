package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage
 * ------------
 * Purpose:
 *  - Represents the Login Page of OrangeHRM
 *  - Contains all the elements and actions we can perform here
 *
 * Why it extends BasePage:
 *  - To use common helper methods like click(), type(), getText()
 *  - To share the same driver and wait logic
 */
public class LoginPage extends BasePage {

    // ✅ Constructor - initializes PageFactory elements and calls BasePage constructor
    public LoginPage(WebDriver driver) {
        super(driver); // sends driver to BasePage
        PageFactory.initElements(driver, this);
    }

    // ✅ Page Elements
    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class,'oxd-text oxd-text--p oxd-alert-content-text')]")
    private WebElement errorMessage; // appears when login fails

    // ✅ Page Actions (Methods)

    /** Enter username in the username field */
    public void enterUsername(String username) {
        type(usernameInput, username); // using BasePage method
    }

    /** Enter password in the password field */
    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    /** Click on the login button */
    public void clickLoginButton() {
        click(loginButton);
    }

    /** Perform complete login */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /** Read error message if login fails */
    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
