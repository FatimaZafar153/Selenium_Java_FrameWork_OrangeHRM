package base;

import config.ConfigReader;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest
 * -----------
 * This class acts as the "foundation" for all your test classes.
 * Every test class (like LoginTest, DashboardTest, etc.) will extend this BaseTest.

 * Purpose:
 * To launch the browser before each test
 * To close the browser after each test
 * To provide a common WebDriver instance that all tests can use
 */
public class BaseTest {

    // Declare a WebDriver variable that can be used by child classes.
    protected WebDriver driver;

    /**
     * Before every test method, open browser and setup the environment.
     * TestNG automatically runs this method before each @Test method.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.out.println("Loading config and setting up browser...");

        // Load config file first
        ConfigReader.loadProperties();

        // Get WebDriver instance from DriverManager
        driver = DriverManager.getDriver();

        System.out.println("Browser setup complete!");
    }

    /**
     * After every test method, quit the browser.
     * TestNG automatically runs this method after each @Test method.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("Cleaning up and closing browser...");
        DriverManager.quitDriver();
    }
}
