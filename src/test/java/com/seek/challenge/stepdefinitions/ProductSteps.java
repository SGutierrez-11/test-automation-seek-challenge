package com.seek.challenge.stepdefinitions;

import com.seek.challenge.questions.ProductQuestions;
import com.seek.challenge.tasks.ProductTasks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {

    private final ProductTasks productTask = new ProductTasks();
    private final ProductQuestions productQuestion = new ProductQuestions();

    /**
     * Step: navigate to the products listing page.
     */
    @When("the user navigates to the Products page")
    public void userNavigatesToProductsPage() {
        productTask.goToProductsPage();
    }

    /**
     * Step: open the first product's details page.
     */
    @And("opens the first product details")
    public void opensFirstProductDetails() {
        productTask.viewFirstProduct();
    }

    /**
     * Step: submit a review for the current product using the provided comment.
     *
     * @param comment review text to submit
     */
    @And("submits a review with the comment {string}")
    public void submitsReviewWithComment(String comment) {
        productTask.submitReview(comment);
    }

    /**
     * Step: assert that the review success message matches the expected text.
     *
     * @param expectedMessage expected visible success message
     */
    @Then("the review success message {string} should be visible")
    public void reviewSuccessMessageShouldBeVisible(String expectedMessage) {
        productQuestion.verifyReviewSuccessMessage(expectedMessage);
    }
}