package com.seek.challenge.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for login-related elements used by tasks and questions.
 */
public class LoginPage extends BasePage {

    @FindBy(css = "input[data-qa='login-email']")
    public WebElement loginEmailInput;

    @FindBy(css = "input[data-qa='login-password']")
    public WebElement loginPasswordInput;

    @FindBy(css = "button[data-qa='login-button']")
    public WebElement loginBtn;

    @FindBy(xpath = "//li[contains(., 'Logged in as')]/a/b")
    public WebElement loggedInUserText;

    @FindBy(xpath = "//a[contains(@href, '/login')]")
    public WebElement signupLoginLink;

    @FindBy(xpath = "//a[contains(@href, '/delete_account')]")
    public WebElement deleteAccountLink;

    @FindBy(xpath = "//form[@action='/login']/p")
    public WebElement loginErrorMsg;
}