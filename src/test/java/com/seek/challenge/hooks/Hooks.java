package com.seek.challenge.hooks;

import com.seek.challenge.config.DriverManager;
import com.seek.challenge.tasks.CleanupTasks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    /**
     * Before hook executed before each scenario. Logs scenario start.
     *
     * @param scenario current Cucumber scenario
     */
    @Before
    public void setUp(Scenario scenario) {
        logger.info("****************************************************************");
        logger.info("   🚀 STARTING SCENARIO: {}", scenario.getName());
        logger.info("****************************************************************");
    }

    /**
     * After hook executed for scenarios tagged with @Login to perform data cleanup.
     * This deletes any created test account using CleanupTasks.
     *
     * @param scenario current Cucumber scenario
     */
    @After(value = "@Login", order = 10)
    public void cleanUpData(Scenario scenario) {
        logger.info("⚙️ Executing data teardown for scenario: {}", scenario.getName());
        new CleanupTasks().deleteAccountSafely();
    }

    /**
     * Final After hook executed after each scenario to take screenshot on failure and quit the driver.
     *
     * @param scenario current Cucumber scenario
     */
    @After(order = 1)
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        if (scenario.isFailed() && driver != null) {
            logger.error("❌ Scenario FAILED: {}. Capturing screenshot...", scenario.getName());
            try {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot Failure");
                logger.info("📸 Screenshot attached to report.");
            } catch (Exception e) {
                logger.error("Failed to capture screenshot: {}", e.getMessage());
            }
        } else {
            logger.info("✅ Scenario PASSED: {}", scenario.getName());
        }

        logger.info("🔌 Closing session for scenario: {}", scenario.getName());
        DriverManager.quitDriver();
        logger.info("****************************************************************");
    }
}