package com.seek.challenge.tasks;

import com.seek.challenge.pages.RegisterPage;
import com.seek.challenge.utils.SeleniumActions;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.Select;

public class RegisterTasks extends RegisterPage {

    public RegisterTasks() {
        super();
    }

    /**
     * Navigate the browser to the provided base URL.
     *
     * @param url destination base URL to open
     */
    @Step("Open base URL")
    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Click the link that opens the Signup/Login page in the application header.
     */
    @Step("Go to Login/Register page")
    public void clickSignupLoginLink() {
        SeleniumActions.click(signupLoginLink, "Signup/Login Link");
    }

    /**
     * Fill the signup name input with the given value.
     *
     * @param name user display name to enter for signup
     * @return this task object for fluent chaining
     */
    @Step("Enter signup user name")
    public RegisterTasks enterSignupName(String name) {
        SeleniumActions.type(nameInput, name, "Signup Name Field");
        return this;
    }

    /**
     * Fill the signup email input with the provided email.
     *
     * @param email email to use during signup
     * @return this task object for fluent chaining
     */
    @Step("Enter signup email")
    public RegisterTasks enterSignupEmail(String email) {
        SeleniumActions.type(emailInput, email, "Signup Email Field");
        return this;
    }

    /**
     * Click the Signup button to submit the initial signup form.
     */
    @Step("Click on Signup button")
    public void clickSignupButton() {
        SeleniumActions.click(signupBtn, "Signup Button");
    }

    /**
     * Select the male gender option in the registration form.
     *
     * @return this task object for fluent chaining
     */
    @Step("Select gender: Male")
    public RegisterTasks selectGenderMale() {
        SeleniumActions.click(genderMaleRadio, "Radio Button Male");
        return this;
    }

    /**
     * Enter the account password into the registration form.
     *
     * @param password password to set for the new account
     * @return this task object for fluent chaining
     */
    @Step("Enter password")
    public RegisterTasks enterPassword(String password) {
        SeleniumActions.type(passwordInput, password, "Password Field");
        return this;
    }

    /**
     * Select the date of birth using visible text on the day/month/year selects.
     *
     * @param day   day as visible text (e.g. "12")
     * @param month month as visible text (e.g. "May")
     * @param year  year as visible text (e.g. "1990")
     * @return this task object for fluent chaining
     */
    @Step("Select date of birth")
    public RegisterTasks selectDateOfBirth(String day, String month, String year) {
        new Select(daySelect).selectByVisibleText(day);
        new Select(monthSelect).selectByVisibleText(month);
        new Select(yearSelect).selectByVisibleText(year);
        return this;
    }

    /**
     * Enter the user's first and last name into the personal information section.
     *
     * @param firstName first name to enter
     * @param lastName  last name to enter
     * @return this task object for fluent chaining
     */
    @Step("Enter personal information (First/Last name)")
    public RegisterTasks enterPersonalName(String firstName, String lastName) {
        SeleniumActions.type(firstNameInput, firstName, "Input First Name");
        SeleniumActions.type(lastNameInput, lastName, "Input Last Name");
        return this;
    }

    /**
     * Enter the street address for the new account.
     *
     * @param address address line to set
     * @return this task object for fluent chaining
     */
    @Step("Enter address")
    public RegisterTasks enterAddress(String address) {
        SeleniumActions.type(addressInput, address, "Input Address");
        return this;
    }

    /**
     * Select the country from the country dropdown.
     *
     * @param country visible country name to select
     * @return this task object for fluent chaining
     */
    @Step("Select country")
    public RegisterTasks selectCountry(String country) {
        new Select(countrySelect).selectByVisibleText(country);
        return this;
    }

    /**
     * Fill state, city and zip code inputs for the address section.
     *
     * @param state   state/province name
     * @param city    city name
     * @param zipCode postal/zip code
     * @return this task object for fluent chaining
     */
    @Step("Enter location details (State/City/Zip)")
    public RegisterTasks enterLocationDetails(String state, String city, String zipCode) {
        SeleniumActions.type(stateInput, state, "Input State");
        SeleniumActions.type(cityInput, city, "Input City");
        SeleniumActions.type(zipcodeInput, zipCode, "Input ZipCode");
        return this;
    }

    /**
     * Enter the mobile number into the registration form.
     *
     * @param mobile mobile number string
     */
    @Step("Enter mobile number")
    public void enterMobileNumber(String mobile) {
        SeleniumActions.type(mobileInput, mobile, "Input Mobile");
    }

    /**
     * Click the final create account button (submit full registration form).
     */
    @Step("Confirm account creation")
    public void clickCreateAccount() {
        SeleniumActions.clickJS(createAccountBtn, "Create Account Button");
    }
}