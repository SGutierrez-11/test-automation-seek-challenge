package com.seek.challenge.utils;

import com.seek.challenge.config.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class WaitActions {

    private static final Logger logger = LoggerFactory.getLogger(WaitActions.class);
    private static final int TIMEOUT = ConfigReader.getIntProperty("page.load.timeout");

    // --- Wait Strategy Management ---

    /**
     * Set a global implicit wait for the current WebDriver instance.
     * This is a convenience wrapper over WebDriver.manage().timeouts().implicitlyWait.
     *
     * @param seconds number of seconds for implicit wait; 0 disables it.
     */
    public static void setImplicitWait(int seconds) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Reset implicit wait to the default configured value from config.properties.
     */
    public static void resetImplicitWait() {
        setImplicitWait(ConfigReader.getIntProperty("implicit.wait"));
    }

    /**
     * Temporarily turn off implicit waits (set to 0). Useful before using explicit waits.
     */
    public static void turnOffImplicitWait() {
        setImplicitWait(0);
    }

    // --- Core Waiting Logic ---

    /**
     * Create a fluent wait instance tied to the current WebDriver with the provided timeout.
     * The returned FluentWait ignores common transient exceptions.
     *
     * @param timeoutSeconds maximum wait time in seconds
     * @return configured FluentWait<WebDriver>
     */
    private static FluentWait<WebDriver> getFluentWait(int timeoutSeconds) {
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class);
    }

    /**
     * Wait until the given WebElement is visible on the page and return it.
     * Implicit waits are temporarily disabled while using explicit FluentWait.
     *
     * @param element the WebElement to wait for
     * @return the same WebElement when it becomes visible
     * @throws TimeoutException if the element is not visible within the configured timeout
     */
    public static WebElement waitForVisibility(WebElement element) {
        turnOffImplicitWait();
        try {
            return getFluentWait(TIMEOUT).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            logger.error("❌ Element was not visible after {} seconds.", TIMEOUT);
            throw e;
        } finally {
            resetImplicitWait();
        }
    }

    /**
     * Wait until an element located by the provided locator is visible and return it.
     *
     * @param locator the By locator used to find the element
     * @return the found WebElement when visible
     */
    public static WebElement waitForVisibility(By locator) {
        turnOffImplicitWait();
        try {
            return getFluentWait(TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } finally {
            resetImplicitWait();
        }
    }

    /**
     * Wait until the element is clickable and return it.
     *
     * @param element target WebElement
     * @return the WebElement when it becomes clickable
     */
    public static WebElement waitForClickability(WebElement element) {
        turnOffImplicitWait();
        try {
            return getFluentWait(TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
        } finally {
            resetImplicitWait();
        }
    }

    /**
     * Wait until an element located by the locator is clickable and return it.
     *
     * @param locator locator used to find the element
     * @return the WebElement when clickable
     */
    public static WebElement waitForClickability(By locator) {
        turnOffImplicitWait();
        try {
            return getFluentWait(TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
        } finally {
            resetImplicitWait();
        }
    }

    /**
     * Wait until the provided element is no longer visible on the page.
     *
     * @param element the WebElement to wait for invisibility
     * @return true if the element became invisible within the timeout, false otherwise
     */
    public static boolean waitForInvisibility(WebElement element) {
        turnOffImplicitWait();
        try {
            return getFluentWait(TIMEOUT).until(ExpectedConditions.invisibilityOf(element));
        } finally {
            resetImplicitWait();
        }
    }
}