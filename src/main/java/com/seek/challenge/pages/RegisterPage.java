package com.seek.challenge.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object representing the registration / account creation pages.
 * Exposes commonly used web elements required by tasks and questions.
 */
public class RegisterPage extends BasePage {

    // --- Header ---
    @FindBy(xpath = "//a[contains(@href, '/login')]")
    public WebElement signupLoginLink;

    // --- Signup Form ---
    @FindBy(css = "input[data-qa='signup-name']")
    public WebElement nameInput;

    @FindBy(css = "input[data-qa='signup-email']")
    public WebElement emailInput;

    @FindBy(css = "button[data-qa='signup-button']")
    public WebElement signupBtn;

    // --- Account Info ---
    @FindBy(id = "id_gender1")
    public WebElement genderMaleRadio;

    @FindBy(css = "input[data-qa='password']")
    public WebElement passwordInput;

    @FindBy(css = "select[data-qa='days']")
    public WebElement daySelect;

    @FindBy(css = "select[data-qa='months']")
    public WebElement monthSelect;

    @FindBy(css = "select[data-qa='years']")
    public WebElement yearSelect;

    // --- Address Info ---
    @FindBy(css = "input[data-qa='first_name']")
    public WebElement firstNameInput;

    @FindBy(css = "input[data-qa='last_name']")
    public WebElement lastNameInput;

    @FindBy(css = "input[data-qa='address']")
    public WebElement addressInput;

    @FindBy(css = "select[data-qa='country']")
    public WebElement countrySelect;

    @FindBy(css = "input[data-qa='state']")
    public WebElement stateInput;

    @FindBy(css = "input[data-qa='city']")
    public WebElement cityInput;

    @FindBy(css = "input[data-qa='zipcode']")
    public WebElement zipcodeInput;

    @FindBy(css = "input[data-qa='mobile_number']")
    public WebElement mobileInput;

    @FindBy(css = "button[data-qa='create-account']")
    public WebElement createAccountBtn;

    @FindBy(css = "h2[data-qa='account-created']")
    public WebElement successMsg;
}