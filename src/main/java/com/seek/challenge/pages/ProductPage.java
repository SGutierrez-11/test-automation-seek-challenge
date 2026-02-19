package com.seek.challenge.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object representing product listing and details page elements used by product tests.
 */
public class ProductPage extends BasePage {

    @FindBy(xpath = "//a[@href='/products']")
    public WebElement productsMenuLink;

    @FindBy(xpath = "//a[@href='/product_details/1']")
    public WebElement viewFirstProductBtn;

    @FindBy(id = "name")
    public WebElement reviewNameInput;

    @FindBy(id = "email")
    public WebElement reviewEmailInput;

    @FindBy(id = "review")
    public WebElement reviewTextArea;

    @FindBy(id = "button-review")
    public WebElement submitReviewBtn;

    @FindBy(xpath = "//div[contains(@class, 'alert-success')]/span")
    public WebElement reviewSuccessMsg;
}