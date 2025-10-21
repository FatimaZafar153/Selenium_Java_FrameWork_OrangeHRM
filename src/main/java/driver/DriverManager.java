package driver;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * DriverManager
 * ----------------
 * Purpose:
 * This class is responsible for:
 *  - Creating and returning WebDriver objects (Chrome, Firefox, Edge)
 *  - Managing browser settings (headless, timeouts)
 *  - Closing and quitting browsers safely
 *
 * Why it exists:
 * Instead of writing driver setup code everywhere,
 * we write it once here and reuse it everywhere else.
 */
public class DriverManager {

    // 'driver' represents your current browser session
    private static WebDriver driver;

    /**
     * getDriver()
     * --------------
     * Returns the current WebDriver instance.
     * If there isn’t one yet, it creates one using setupDriver().
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            setupDriver();
        }
        return driver;
    }

    /**
     * setupDriver()
     * ----------------
     * This method:
     *  - Reads browser name and headless option from config.properties
     *  - Uses WebDriverManager to set up the browser driver automatically
     *  - Creates the WebDriver object
     *  - Sets browser window size and timeouts
     *  - Opens the base URL
     */
    private static void setupDriver() {

        // Read values from config.properties
        String browser = ConfigReader.getProperty("browser");
        boolean isHeadless = ConfigReader.getBooleanProperty("headless");
        int implicitWait = ConfigReader.getIntProperty("implicit.wait");
        int pageLoadTimeout = ConfigReader.getIntProperty("page.load.timeout");
        String baseUrl = ConfigReader.getProperty("base.url");

        System.out.println("Starting browser: " + browser);

        switch (browser.toLowerCase()) {

            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                // New way to set headless in Selenium 4+
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                }

                driver = new ChromeDriver(chromeOptions);
            }

            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                // Updated headless setting
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
            }

            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();

                // Edge also uses addArguments for headless
                if (isHeadless) {
                    edgeOptions.addArguments("--headless=new");
                }

                driver = new EdgeDriver(edgeOptions);
            }

            default -> throw new RuntimeException("Unsupported browser in config.properties: " + browser);
        }

        // Make browser full screen
        driver.manage().window().maximize();

        // Setup timeouts for waiting
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));

        // Open the application URL
        driver.get(baseUrl);

        System.out.println("Browser launched successfully → " + baseUrl);
    }

    /**
     * closeDriver()
     * ----------------
     * Closes the browser tab (not entire session).
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }

    /**
     * quitDriver()
     * ----------------
     * Completely shuts down browser and ends the session.
     * Sets driver = null for next test run.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Browser session closed successfully.");
        }
    }
}
