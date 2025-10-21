package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * DashboardPage
 * -----------------
 * Purpose:
 *  - Represents the Dashboard page after a successful login.
 *  - Used to verify that login worked and user landed correctly.
 *
 * Why it extends BasePage:
 *  - To reuse methods like getText(), click(), and waits.
 */
public class DashboardPage extends BasePage {

    // ✅ Constructor - pass driver to BasePage and initialize elements
    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ✅ Page Elements
    // Dashboard header locator
    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    // ✅ Page Actions

    /** Check if Dashboard header is displayed */
    // returns true if dashboard header is visible
    public boolean isDashboardDisplayed() {
        try {
            waitForVisibility(dashboardHeader); // BasePage wait
            return dashboardHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}