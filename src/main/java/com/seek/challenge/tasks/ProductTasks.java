package com.seek.challenge.tasks;

import com.seek.challenge.pages.ProductPage;
import com.seek.challenge.utils.DataGenerator;
import com.seek.challenge.utils.SeleniumActions;
import io.qameta.allure.Step;

public class ProductTasks extends ProductPage {

    public ProductTasks() {
        super();
    }

    /**
     * Click the Products menu link in the header to navigate to the Products page.
     */
    @Step("Navigate to the Products page")
    public void goToProductsPage() {
        SeleniumActions.click(productsMenuLink, "Products Menu");
    }

    /**
     * Open the details of the first product using a JS click.
     */
    @Step("Open first product details")
    public void viewFirstProduct() {
        SeleniumActions.clickJS(viewFirstProductBtn, "View Product Button (1)");
    }

    /**
     * Submit a product review using generated name/email and the provided comment.
     *
     * @param comment review text to submit
     */
    @Step("Write and submit review: {0}")
    public void submitReview(String comment) {
        String randomName = DataGenerator.getFirstName();
        String randomEmail = DataGenerator.getEmail();

        SeleniumActions.type(reviewNameInput, randomName, "Review Name Input");
        SeleniumActions.type(reviewEmailInput, randomEmail, "Review Email Input");
        SeleniumActions.type(reviewTextArea, comment, "Review Textarea");

        SeleniumActions.click(submitReviewBtn, "Submit Review Button");
    }
}