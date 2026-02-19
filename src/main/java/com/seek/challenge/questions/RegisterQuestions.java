package com.seek.challenge.questions;

import com.seek.challenge.pages.RegisterPage;
import com.seek.challenge.utils.SeleniumActions;

public class RegisterQuestions extends RegisterPage {

    public RegisterQuestions() {
        super();
    }

    /**
     * Check whether the account created success message is visible on the page.
     *
     * @return true if the success message element is displayed, false otherwise
     */
    public boolean isAccountCreatedMessageDisplayed() {
        return SeleniumActions.isDisplayed(successMsg);
    }

    /**
     * Retrieve the success message text shown after account creation.
     *
     * @return the message text (e.g. "ACCOUNT CREATED!")
     */
    public String getSuccessMessageText() {
        return SeleniumActions.getText(successMsg);
    }
}