package com.seek.challenge.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class FacebookHomePage extends BasePage {

    @FindAll({
            @FindBy(xpath = "//span[contains(text(), 'Crear cuenta nueva')]"),
            @FindBy(xpath = "//a[contains(text(), 'Crear cuenta nueva')]"),
            @FindBy(xpath = "//span[contains(text(), 'Create new account') or contains(text(),'Create account') ]"),
            @FindBy(xpath = "//a[contains(text(), 'Create new account') or contains(text(),'Create account')]")
    })
    public WebElement createAccountButton;
}
