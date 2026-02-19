package com.seek.challenge.questions;

import com.seek.challenge.pages.ProductPage;
import com.seek.challenge.utils.SeleniumActions;
import io.qameta.allure.Step;
import org.testng.Assert;

public class ProductQuestions extends ProductPage {

    public ProductQuestions() {
        super();
    }

    /**
     * Verify that the review success message is visible and matches the expected text.
     *
     * @param expectedMessage expected message text (e.g. "Thank you for your review.")
     */
    @Step("Verify success message after publishing review: {0}")
    public void verifyReviewSuccessMessage(String expectedMessage) {
        Assert.assertTrue(SeleniumActions.isDisplayed(reviewSuccessMsg),
                "ERROR: The review success message is not visible.");

        String actualMessage = SeleniumActions.getText(reviewSuccessMsg);
        Assert.assertEquals(actualMessage, expectedMessage,
                "ERROR: The review success message text does not match.");
    }
}