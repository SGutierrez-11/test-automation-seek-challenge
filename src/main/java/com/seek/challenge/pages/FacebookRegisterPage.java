package com.seek.challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class FacebookRegisterPage extends BasePage {

    @FindAll({
            @FindBy(xpath = "//input[@name='firstname']"),
            @FindBy(xpath = "//label[text()='First name']/preceding-sibling::input")
    })
    public WebElement firstNameInput;

    @FindAll({
            @FindBy(xpath = "//input[@name='lastname']"),
            @FindBy(xpath = "//label[text()='Last name']/preceding-sibling::input")
    })
    public WebElement lastNameInput;

    @FindBy(id = "day")
    public WebElement dayDropdown;

    @FindBy(id = "month")
    public WebElement monthDropdown;

    @FindBy(id = "year")
    public WebElement yearDropdown;

    @FindBy(xpath = "//input[@name='reg_email__']")
    public WebElement emailInput;

    @FindAll({
            @FindBy(name = "reg_passwd__"),
            @FindBy(id = "password_step_input"),
            @FindBy(xpath = "//input[@type='password']")
    })
    public WebElement passwordInput;

    @FindAll({
            @FindBy(name = "websubmit"), // V1
            @FindBy(xpath = "//div[@role='button']//span[text()='Sign Up']") // V2
    })
    public WebElement btnSubmit;

    @FindBy(xpath = "//div[@role='listbox' or @role='combobox' and contains(@aria-label,'gender')]")
    public WebElement genderDropdown;

    public WebElement getDayOption(String day) {
        String dayOption = "//select[@id='day']/option[text()='%s']";
        return driver.findElement(By.xpath(String.format(dayOption, day)));
    }

    public WebElement getMonthOption(String month) {
        String monthOption = "//select[@id='month']/option[text()='%s']";
        return driver.findElement(By.xpath(String.format(monthOption, month)));
    }

    public WebElement getYearOption(String year) {
        String yearOption = "//select[@id='year']/option[text()='%s']";
        return driver.findElement(By.xpath(String.format(yearOption, year)));
    }

    public WebElement getOptionV2(String option) {
        String opt = "//div[@role='listbox']//div[@role='option' and text()='%s']";
        return driver.findElement(By.xpath(String.format(opt, option)));
    }

    public WebElement getGenderOptionV1(String gender) {
        String genderOption = "//input[@type='radio' and @name='sex' and @value='%s']";
        return driver.findElement(By.xpath(String.format(genderOption, gender)));
    }

    public WebElement getGenderOptionV2(String gender) {
        String genderOption = "//div[@role='listbox']//div[@role='option' and text()='%s']";
        return driver.findElement(By.xpath(String.format(genderOption, gender)));
    }
}
