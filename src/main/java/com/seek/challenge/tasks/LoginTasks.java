package com.seek.challenge.tasks;

import com.seek.challenge.pages.LoginPage;
import com.seek.challenge.utils.SeleniumActions;
import io.qameta.allure.Step;

public class LoginTasks extends LoginPage {

    public LoginTasks() {
        super();
    }

    /**
     * Click the header link that navigates to the Login page.
     */
    @Step("Navigate to the Login page")
    public void clickSignupLoginLink() {
        SeleniumActions.click(signupLoginLink, "Link Signup/Login");
    }

    /**
     * Enter the email address into the login email input.
     *
     * @param email email address to use for login
     * @return this task instance for fluent chaining
     */
    @Step("Enter login email: {0}")
    public LoginTasks enterLoginEmail(String email) {
        SeleniumActions.type(loginEmailInput, email, "Input Email Login");
        return this;
    }

    /**
     * Enter the password into the login password input.
     *
     * @param password password to use for login
     * @return this task instance for fluent chaining
     */
    @Step("Enter login password")
    public LoginTasks enterLoginPassword(String password) {
        SeleniumActions.type(loginPasswordInput, password, "Input Password Login");
        return this;
    }

    /**
     * Click the Login button to submit the login form.
     */
    @Step("Click on Login button")
    public void clickLoginButton() {
        SeleniumActions.click(loginBtn, "Login Button");
    }
}