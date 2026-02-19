package com.seek.challenge.utils;

import com.seek.challenge.config.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumActions {

    private static final Logger logger = LoggerFactory.getLogger(SeleniumActions.class);

    // --- Actions ---

    /**
     * Clicks on a web element with explicit wait and retry logic.
     * This method will attempt a normal click and fall back to JS click if intercepted.
     *
     * @param element     the WebElement to click
     * @param description a friendly description used for reporting
     */
    @Step("Click on '{description}'")
    public static void click(WebElement element, String description) {
        logger.info("Clicking on: {}", description);
        try {
            WaitActions.waitForClickability(element).click();
        } catch (ElementClickInterceptedException e) {
            logger.warn("Click intercepted on '{}'. Retrying with JS Click...", description);
            clickJS(element, description);
        } catch (StaleElementReferenceException e) {
            logger.warn("Stale Element on '{}'. Retrying...", description);
            throw e;
        }
    }

    /**
     * Click using a locator. This overload finds the element and delegates to click(WebElement, String).
     *
     * @param locator     By locator to find the element
     * @param description friendly description used for reporting
     */
    @Step("Click on '{description}'")
    public static void click(By locator, String description) {
        logger.info("Clicking on: {}", description);
        WebElement element = WaitActions.waitForClickability(locator);
        click(element, description);
    }

    /**
     * Type text into an input element after waiting for visibility.
     * The element will be cleared before sending keys.
     *
     * @param element     target WebElement
     * @param text        text to type
     * @param description friendly description used for reporting
     */
    @Step("Type '{text}' into '{description}'")
    public static void type(WebElement element, String text, String description) {
        logger.info("Typing '{}' into '{}'", text, description);
        WebElement visibleElement = WaitActions.waitForVisibility(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    /**
     * Type text into an element found by locator. Delegates to type(WebElement, String, String).
     *
     * @param locator     By locator to find the input
     * @param text        text to type
     * @param description friendly description used for reporting
     */
    @Step("Type '{text}' into '{description}'")
    public static void type(By locator, String text, String description) {
        WebElement element = WaitActions.waitForVisibility(locator);
        type(element, text, description);
    }

    /**
     * Perform a JavaScript-based click on the provided element. Useful as a fallback when normal clicks fail.
     *
     * @param element     target WebElement
     * @param description friendly description used for logging
     */
    public static void clickJS(WebElement element, String description) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        executor.executeScript("arguments[0].click();", element);
        logger.debug("JS Click performed on: {}", description);
    }

    // --- Validation/Getters ---

    /**
     * Retrieve visible text from an element (waits for visibility first).
     *
     * @param element the WebElement to read
     * @return element's visible text
     */
    public static String getText(WebElement element) {
        return WaitActions.waitForVisibility(element).getText();
    }

    /**
     * Check whether an element is displayed. Returns false on NoSuchElement or timeout.
     *
     * @param element the WebElement to check
     * @return true when element.isDisplayed() succeeds, false otherwise
     */
    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
}