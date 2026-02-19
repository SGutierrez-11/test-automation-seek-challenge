package com.seek.challenge.stepdefinitions;

import com.seek.challenge.questions.LoginQuestions;
import com.seek.challenge.tasks.LoginTasks;
import com.seek.challenge.utils.TestDataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final LoginTasks loginTask = new LoginTasks();
    private final LoginQuestions loginQuestion = new LoginQuestions();

    /**
     * Step: navigate to the Login page using the header link.
     */
    @When("the user navigates to the Login page")
    public void userNavigatesToLogin() {
        loginTask.clickSignupLoginLink();
    }

    /**
     * Step: login using credentials stored by a previous registration flow.
     */
    @And("logs in with the registered user credentials")
    public void logsInWithRegisteredUser() {
        String registeredEmail = TestDataManager.getRegisteredEmail();
        String registeredPassword = TestDataManager.getRegisteredPassword();

        loginTask.enterLoginEmail(registeredEmail)
                .enterLoginPassword(registeredPassword)
                .clickLoginButton();
    }

    /**
     * Step: assert that the logged user name is visible in the page header.
     */
    @Then("the user name should be visible in the header")
    public void userNameShouldBeVisible() {
        String expectedName = TestDataManager.getRegisteredName();

        loginQuestion.verifyUserIsLoggedIn(expectedName);
    }

    /**
     * Step: attempt to login with the provided email and password values.
     *
     * @param email provided email to test
     * @param password provided password to test
     */
    @And("attempts to login with email {string} and password {string}")
    public void attemptsToLoginWithEmailAndPassword(String email, String password) {
        loginTask.enterLoginEmail(email)
                .enterLoginPassword(password)
                .clickLoginButton();
    }

    /**
     * Step: verify that an expected error message is shown after a login attempt.
     *
     * @param expectedMessage expected visible error message
     */
    @Then("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String expectedMessage) {
        loginQuestion.verifyErrorMessage(expectedMessage);
    }
}