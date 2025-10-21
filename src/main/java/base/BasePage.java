package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage
 * ----------
 * Purpose:
 * This is the "parent" class for all Page classes (LoginPage, DashboardPage, etc.)
 *
 * Why it exists:
 *  - To store common Selenium helper methods that all pages can use
 *  - To avoid repeating the same code everywhere
 *
 * Every page class (like LoginPage) extends this BasePage
 * so it automatically gets access to:
 *  - click()
 *  - type()
 *  - waitForVisibility()
 *  - getPageTitle()
 */
public class BasePage {

    protected WebDriver driver;        // shared browser instance
    protected WebDriverWait wait;      // wait utility for dynamic elements
    protected Actions actions;         // for mouse and keyboard actions

    // Constructor: initializes WebDriver and WebDriverWait
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // default wait
        this.actions = new Actions(driver);
    }

    /**
     * Clicks on a given element safely after waiting for it to be clickable.
     */
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * Sends text to a given input field.
     * Clears it first to avoid leftover data.
     */
    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Waits for a given element to be visible.
     */
    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Returns the current page title.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Scrolls down to a given element using JavaScript.
     */
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Gets text safely from an element.
     */
    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
}
