package com.seek.challenge.questions;

import com.seek.challenge.pages.LoginPage;
import com.seek.challenge.utils.SeleniumActions;
import io.qameta.allure.Step;
import org.testng.Assert;

public class LoginQuestions extends LoginPage {

    public LoginQuestions() {
        super();
    }

    /**
     * Verify that the user displayed in the header matches the expected name.
     * Throws an assertion failure with a clear message if verification fails.
     *
     * @param expectedName expected full name as shown in the header
     */
    @Step("Verify logged in user is: {0}")
    public void verifyUserIsLoggedIn(String expectedName) {
        Assert.assertTrue(SeleniumActions.isDisplayed(loggedInUserText),
                "ERROR: Logged-in user indicator is not visible in the header.");

        String actualName = SeleniumActions.getText(loggedInUserText);
        Assert.assertEquals(actualName, expectedName,
                "ERROR: The user name in the header does not match the registered user.");

    }

    /**
     * Verify the login error message is visible and matches the expected text.
     *
     * @param expectedMessage expected error message text
     */
    @Step("Verify login error message: {0}")
    public void verifyErrorMessage(String expectedMessage) {
        Assert.assertTrue(SeleniumActions.isDisplayed(loginErrorMsg),
                "ERROR: The login error message is not visible on screen.");

        String actualMessage = SeleniumActions.getText(loginErrorMsg);
        Assert.assertEquals(actualMessage, expectedMessage,
                "ERROR: The error message text does not match.");

    }
}