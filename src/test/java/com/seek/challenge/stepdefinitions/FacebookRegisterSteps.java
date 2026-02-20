package com.seek.challenge.stepdefinitions;

import com.seek.challenge.tasks.FacebookRegisterTasks;
import com.seek.challenge.utils.DataGenerator;
import com.seek.challenge.utils.TestDataManager;
import com.seek.challenge.utils.WaitActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FacebookRegisterSteps {

    private final FacebookRegisterTasks facebookTask = new FacebookRegisterTasks();

    @Given("the user is on the Facebook registration page")
    public void theUserIsOnTheFacebookRegistrationPage() {
        facebookTask.navigateToFacebookRegisterPage();
    }

    @When("the user fills the registration form with valid random details")
    public void fillsRegistrationFormWithRandomDetails() {
        String firstName = DataGenerator.getFirstName();
        String lastName = DataGenerator.getLastName();
        String putsboxEmail = DataGenerator.getPutsboxEmail();
        String password = DataGenerator.getPassword();

        facebookTask.enterPersonalDetails(firstName, lastName)
                .enterEmail(putsboxEmail)
                .enterPassword(password)
                .selectBirthDate("15", "May", "1995")
                .selectGender("2", "male");


        TestDataManager.saveUserCredentials(putsboxEmail, password, firstName + " " + lastName);
    }

    @And("the user submits the registration form")
    public void theUserSubmitsTheRegistrationForm() {
        facebookTask.clickSubmit();
    }

    @Then("the system should prompt for email verification")
    public void theSystemShouldPromptForEmailVerification() {
        WaitActions.setImplicitWait(30);
        System.out.println("Waiting for OTP validation implementation...");
    }

}
