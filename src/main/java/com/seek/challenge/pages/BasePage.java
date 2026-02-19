package com.seek.challenge.pages;

import com.seek.challenge.config.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;

    /**
     * Base page constructor that initializes WebDriver and PageFactory elements.
     */
    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
}