package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * ExtentManager
 * -----------------
 * Purpose:
 * This class creates and manages ExtentReports and ExtentTests.
 * It ensures that the report is created only once and reused across all tests.
 *
 * In simple terms:
 * - Think of it as the "report factory".
 * - It makes a single report file for all your tests.
 * - Each test case adds its own entries into the same report.
 */
public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String REPORT_PATH = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

    /**
     * getInstance()
     * ---------------
     * Ensures there is only ONE ExtentReports instance.
     * (Singleton pattern)
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    /**
     * createInstance()
     * -----------------
     * Sets up ExtentSparkReporter and configures the look of the HTML report.
     */
    private static void createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);

        // 🧠 Report customizations (you can style your report here)
        sparkReporter.config().setDocumentTitle("OrangeHRM Automation Report");
        sparkReporter.config().setReportName("Selenium Cucumber Test Execution");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add environment info (appears at top of report)
        extent.setSystemInfo("Application", "OrangeHRM Demo");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Your Name");

        System.out.println("📊 Extent Report initialized at: " + REPORT_PATH);
    }

    /**
     * createTest()
     * --------------
     * Creates a new test entry inside the report.
     * Each scenario/test method will have its own test section.
     */
    public ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    /**
     * flushReports()
     * ----------------
     * Writes (saves) all logs and results into the report file.
     * This should be called at the end of test execution.
     */
    public void flushReports() {
        if (extent != null) {
            extent.flush();
            System.out.println("🧾 Extent Report saved successfully at: " + REPORT_PATH);
        }
    }
}