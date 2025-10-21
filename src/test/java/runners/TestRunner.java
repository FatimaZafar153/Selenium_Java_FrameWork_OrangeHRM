package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * TestRunner
 * ----------
 * - Runs Cucumber feature files with TestNG
 * - Generates Extent Reports
 * - Opens HTML report automatically after execution
 */
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:test-output/CucumberReport.html",
                "json:test-output/cucumber-report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Automatically open the Extent HTML report after the test suite completes.
     */
    @AfterSuite
    public void openExtentReport() {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport.html";

        File reportFile = new File(reportPath);
        if (reportFile.exists()) {
            try {
                System.out.println("📂 Opening Extent Report: " + reportFile.getAbsolutePath());
                Desktop.getDesktop().browse(reportFile.toURI());
            } catch (IOException e) {
                System.err.println("⚠️ Unable to open Extent Report automatically: " + e.getMessage());
            }
        } else {
            System.err.println("❌ Extent Report not found at: " + reportPath);
        }
    }
}
