package hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hooks
 * -----
 * Purpose:
 * - Setup and teardown browser before/after each scenario
 * - Capture screenshots on test failure
 * - Store screenshots in test-output/Screenshots/
 * - Attach screenshots to Cucumber & Extent Reports
 */
public class Hooks {

    @Before
    public void setup() {
        System.out.println("🚀 Launching browser and loading application...");
        DriverManager.getDriver(); // Initializes WebDriver & opens URL
        System.out.println("✅ Browser launched successfully!");
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        try {
            if (scenario.isFailed() && driver != null) {
                System.out.println("❌ Test failed → capturing screenshot...");

                // Take screenshot as file
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // Generate timestamp for filename
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                // Create folder if not exists
                File destDir = new File("test-output/Screenshots/");
                if (!destDir.exists()) destDir.mkdirs();

                // Define final file path
                String fileName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";
                File destFile = new File(destDir, fileName);

                // Save screenshot file
                FileUtils.copyFile(srcFile, destFile);

                // Attach screenshot to Cucumber/Extent report
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", "Failed Step Screenshot");

                System.out.println("📸 Screenshot saved → " + destFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("⚠️ Failed to save screenshot: " + e.getMessage());
        } finally {
            System.out.println("🧹 Closing browser session...");
            DriverManager.quitDriver();
            System.out.println("✅ Browser closed successfully!");
        }
    }
}
