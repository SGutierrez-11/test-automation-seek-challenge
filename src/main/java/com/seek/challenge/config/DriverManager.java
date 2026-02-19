package com.seek.challenge.config;

import com.seek.challenge.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Return the thread-local WebDriver instance, initializing it if necessary according to configuration.
     * The method will configure browser-specific options, timeouts and window state.
     *
     * @return initialized WebDriver for the current thread
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            logger.info("Initializing WebDriver instance...");
            WebDriver instance;
            String browser = ConfigReader.getProperty("browser").toLowerCase();

            switch (browser) {
                case "firefox":
                    instance = new FirefoxDriver();
                    logger.info("Browser selected: Firefox");
                    break;
                case "chrome":
                default:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--disable-notifications");

                    if (ConfigReader.getBooleanProperty("incognito")) {
                        options.addArguments("--incognito");
                    }

                    if (ConfigReader.getBooleanProperty("headless")) {
                        options.addArguments("--headless");
                        options.addArguments("--window-size=1920,1080");
                        logger.info("Running in Headless mode");
                    }

                    instance = new ChromeDriver(options);
                    logger.info("Browser selected: Chrome");
                    break;
            }

            int implicitWait = ConfigReader.getIntProperty("implicit.wait");
            int pageLoad = ConfigReader.getIntProperty("page.load.timeout");

            instance.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            instance.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoad));
            instance.manage().window().maximize();

            driver.set(instance);
            logger.info("WebDriver initialized successfully.");
        }
        return driver.get();
    }

    /**
     * Quit the WebDriver instance for the current thread and remove it from the thread-local storage.
     * Safe to call even if no WebDriver was initialized.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Quitting WebDriver...");
            driver.get().quit();
            driver.remove();
            logger.info("WebDriver session closed.");
        }
    }
}