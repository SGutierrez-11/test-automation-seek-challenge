package com.seek.challenge.tasks;

import com.seek.challenge.pages.FacebookRegisterPage;
import com.seek.challenge.utils.SeleniumActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class FacebookRegisterTasks extends FacebookRegisterPage {

    public FacebookRegisterTasks() {
        super();
    }

    @Step("Navigate to Facebook registration page")
    public FacebookRegisterTasks navigateToFacebookRegisterPage() {
        driver.get("https://www.facebook.com/r.php?entry_point=login");
        return this;
    }

    @Step("Enter personal details: {0} {1}")
    public FacebookRegisterTasks enterPersonalDetails(String firstName, String lastName) {
        SeleniumActions.type(firstNameInput, firstName, "Input First Name");
        SeleniumActions.type(lastNameInput, lastName, "Input Last Name");
        return this;
    }

    @Step("Enter email: {0}")
    public FacebookRegisterTasks enterEmail(String email) {
        SeleniumActions.type(emailInput, email, "Input Email");

        try {
            By confirmEmailLocator = By.name("reg_email_confirmation__");
            if (SeleniumActions.isDisplayed(driver.findElement(confirmEmailLocator))) {
                SeleniumActions.type(confirmEmailLocator, email, "Input Confirm Email");
            }
        } catch (Exception e) {
        }
        return this;
    }

    @Step("Enter password")
    public FacebookRegisterTasks enterPassword(String password) {
        SeleniumActions.type(passwordInput, password, "Input Password");
        return this;
    }

    @Step("Select birth date: {0}/{1}/{2}")
    public FacebookRegisterTasks selectBirthDate(String day, String month, String year) {
        String tagName = dayDropdown.getTagName();

        if ("select".equalsIgnoreCase(tagName)) {
            SeleniumActions.click(dayDropdown, "Select Day V1");
            SeleniumActions.click(getDayOption(day), "Day Option: " + day);

            SeleniumActions.click(monthDropdown, "Select Month V1");
            SeleniumActions.click(getMonthOption(month.toLowerCase()), "Month Option: " + month);

            SeleniumActions.click(yearDropdown, "Select Year V1");
            SeleniumActions.click(getYearOption(year), "Year Option: " + year);
        } else {
            SeleniumActions.click(dayDropdown, "Dropdown Day V2");
            SeleniumActions.click(getOptionV2(day), "Day Option V2: " + day);

            SeleniumActions.click(monthDropdown, "Dropdown Month V2");
            SeleniumActions.click(getOptionV2(month), "Month Option V2: " + month);

            SeleniumActions.click(yearDropdown, "Dropdown Year V2");
            SeleniumActions.click(getOptionV2(year), "Year Option V2: " + year);
        }
        return this;
    }

    @Step("Select gender: value={0}, text={1}")
    public void selectGender(String genderValue, String genderText) {
        if (SeleniumActions.isDisplayed(genderDropdown)) {
            SeleniumActions.click(genderDropdown, "Gender Dropdown V2");
            SeleniumActions.click(getOptionV2(genderText), "Gender Option V2: " + genderText);
        } else {
            SeleniumActions.click(getGenderOptionV1(genderValue), "Gender Radio V1: " + genderText);
        }
    }

    @Step("Submit registration form")
    public void clickSubmit() {
        SeleniumActions.clickJS(btnSubmit, "Sign Up Button");
    }
}
