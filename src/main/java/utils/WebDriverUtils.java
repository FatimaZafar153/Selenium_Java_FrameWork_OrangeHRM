package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * WebDriverUtils
 * -----------------
 * Purpose:
 *  - Contains small, reusable WebDriver helper methods.
 *  - Keeps code clean and avoids duplication in test and page classes.
 */
public class WebDriverUtils {

    /**
     * Take screenshot and save it to 'screenshots' folder.
     * Returns the file path of the screenshot.
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destination = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destination;
    }

    /**
     * Scroll down the page using JavaScript
     */
    public static void scrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
    }

    /**
     * Scroll to a specific element
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
