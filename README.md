🧩 Selenium Java Hybrid BDD Framework — OrangeHRM

This is a Hybrid Automation Framework built for testing the OrangeHRM demo site.
It follows the Page Object Model (POM) and uses Cucumber (BDD) for behavior-driven testing, TestNG for execution, and Extent Reports for reporting.

🏗️ Tech Stack

🏗| Component                 | Technology Used               |
| ------------------------- | ----------------------------- |
| **Language**              | Java 21                       |
| **Automation Tool**       | Selenium WebDriver 4          |
| **BDD Framework**         | Cucumber 7                    |
| **Test Runner**           | TestNG                        |
| **Build Tool**            | Maven                         |
| **Reporting**             | ExtentReports & Cucumber HTML |
| **Logging**               | Log4j2                        |
| **Dependency Management** | WebDriverManager              |
| **Design Pattern**        | Page Object Model (POM)       |

📁 Project Structure
Selenium_Java_Framework_OrangeHRM/
│
├── pom.xml                           # Maven dependencies and build setup
├── testng.xml                        # Optional TestNG suite runner
├── README.md                         # Project documentation
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── base                  # Common base classes
│   │   │   │   ├── BaseTest.java
│   │   │   │   └── BasePage.java
│   │   │   ├── config                # Configuration reader
│   │   │   │   └── ConfigReader.java
│   │   │   ├── driver                # Driver initialization and teardown
│   │   │   │   └── DriverManager.java
│   │   │   ├── pages                 # Page Object Model classes
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── DashboardPage.java
│   │   │   ├── reporting             # Extent Report Manager
│   │   │   │   └── ExtentManager.java
│   │   │   └── utils                 # Common utilities
│   │   │       └── WebDriverUtils.java
│   │   └── resources
│   │       ├── config.properties     # Environment & browser settings
│   │       └── log4j2.xml            # Log4j2 logging configuration
│   │
│   └── test
│       ├── java
│       │   ├── hooks                 # Cucumber Hooks for setup/teardown
│       │   │   └── Hooks.java
│       │   ├── runners               # Test Runner (Cucumber + TestNG)
│       │   │   └── TestRunner.java
│       │   └── stepDefinitions       # Step definitions for features
│       │       └── LoginSteps.java
│       └── resources
│           ├── features              # Cucumber feature files
│           │   └── Login.feature
│           ├── extent.properties     # Extent report configuration
│           └── extent-config.xml     # Custom Extent styling
│
├── test-output
│   ├── ExtentReport.html             # Main Extent report
│   ├── CucumberReport.html           # Cucumber BDD report
│   └── Screenshots/                  # Screenshots of failed steps
│
└── target/                           # Compiled classes and report outputs

⚙️ Configuration — config.properties

This file allows easy control over your test setup.

browser=chrome
headless=false
implicit.wait=10
page.load.timeout=20
base.url=https://opensource-demo.orangehrmlive.com/

🧠 Key Features

✅ Page Object Model (POM) — Clean, reusable UI interactions
✅ Cucumber BDD — Human-readable test scenarios
✅ TestNG — Parallel and flexible execution
✅ Extent Reports + Screenshots — Rich HTML reports with failed step screenshots
✅ WebDriverManager — Auto-handles browser drivers
✅ Config-driven Execution — Run tests across browsers/environments
✅ Log4j2 Logging — Structured logs for debugging
✅ Screenshots Folder — Stores images of failures in test-output/Screenshots

🧪 Running the Tests
▶ Option 1 — From IDE

Right-click on TestRunner.java

Select Run 'TestRunner'

View reports in:

test-output/ExtentReport/ExtentReport.html
test-output/ExtentReport/CucumberReport.html
test-output/Screenshots/

▶ Option 2 — From Terminal
mvn clean test

▶ Option 3 — With TestNG Suite
mvn test -DsuiteXmlFile=testng.xml

🧩 Reports & Screenshots

📘 ExtentReport.html — test-output/ExtentReport/ExtentReport.html
📗 CucumberReport.html — test-output/ExtentReport/CucumberReport.html
📸 Screenshots — Saved for failed scenarios in test-output/Screenshots/

🧰 Sample BDD Scenario

Login.feature

Feature: Login Functionality

Scenario: Valid Login
Given user is on the OrangeHRM login page
When user enters valid username and password
And clicks on the login button
Then user should be navigated to the Dashboard page

Scenario: Invalid Login
Given user is on the OrangeHRM login page
When user enters invalid username and password
And clicks on the login button
Then an error message should be displayed

📊 Example Report Screenshot

✅ Passed Test
❌ Failed Test (includes captured screenshot in report)

🧾 Logging

Logs are managed using Log4j2 and stored in the console or file (as configured in log4j2.xml).

💻 Prerequisites

Java 21+

Maven 3.9+

IDE: IntelliJ / Eclipse

Browser: Chrome, Firefox, or Edge

🚀 Future Enhancements

Integrate Jenkins CI/CD

Add Allure Reporting

Add API + Database validation

Support for Cross Browser Testing via Selenium Grid

👨‍💻 Author

Fatima Zafar
💼 QA Engineer
📧 fatima.zafar15398@gmail.com

🌐 https://github.com/FatimaZafar153