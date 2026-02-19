package com.seek.challenge.stepdefinitions;

import com.seek.challenge.questions.RegisterQuestions;
import com.seek.challenge.tasks.RegisterTasks;
import com.seek.challenge.utils.ConfigReader;
import com.seek.challenge.utils.DataGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import com.seek.challenge.utils.TestDataManager;

public class RegisterSteps {

    private final RegisterTasks registerTask = new RegisterTasks();
    private final RegisterQuestions registerQuestion = new RegisterQuestions();

    private String generatedEmail;
    private String generatedName;

    /**
     * Step: open the application's home page.
     */
    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        registerTask.openUrl(ConfigReader.getProperty("base.url"));
    }

    /**
     * Step: navigate to the sign up / login page.
     */
    @When("the user navigates to the Sign Up page")
    public void theUserNavigatesToTheSignUpPage() {
        registerTask.clickSignupLoginLink();
    }

    /**
     * Step: fills initial signup fields with a random generated user.
     */
    @And("fills the initial signup form with a random user")
    public void fillsTheInitialSignupFormWithARandomUser() {
        generatedName = DataGenerator.getFirstName() + " " + DataGenerator.getLastName();
        generatedEmail = DataGenerator.getEmail();

        registerTask.enterSignupName(generatedName)
                .enterSignupEmail(generatedEmail)
                .clickSignupButton();
    }

    /**
     * Step: complete registration form with valid random details and save credentials.
     */
    @And("fills the complete registration form with valid random details")
    public void fillsTheCompleteRegistrationFormWithValidRandomDetails() {
        String generatedPassword = DataGenerator.getPassword();
        String firstName = DataGenerator.getFirstName();
        String lastName = DataGenerator.getLastName();
        String address = DataGenerator.getAddress();
        String city = DataGenerator.getCity();
        String zip = DataGenerator.getZipCode();
        String mobile = DataGenerator.getMobileNumber();

        registerTask.selectGenderMale()
                .enterPassword(generatedPassword)
                .selectDateOfBirth("12", "May", "1990")
                .enterPersonalName(firstName, lastName)
                .enterAddress(address)
                .selectCountry("Canada")
                .enterLocationDetails("Quebec", city, zip)
                .enterMobileNumber(mobile);

        TestDataManager.saveUserCredentials(generatedEmail, generatedPassword, generatedName);
    }

    /**
     * Step: confirm account creation by clicking the create account button.
     */
    @And("confirms the account creation")
    public void confirmsTheAccountCreation() {
        registerTask.clickCreateAccount();
    }

    /**
     * Step: assert that account was created successfully (ACCOUNT CREATED! visible).
     */
    @Then("the account should be created successfully")
    public void theAccountShouldBeCreatedSuccessfully() {
        Assert.assertTrue(registerQuestion.isAccountCreatedMessageDisplayed(),
                "ERROR: The success message 'ACCOUNT CREATED!' did not appear.");
    }

    /**
     * Step: verify a provided success message matches the visible text.
     *
     * @param expectedMessage expected visible message
     */
    @And("the success message {string} should be visible")
    public void theSuccessMessageShouldBeVisible(String expectedMessage) {
        Assert.assertEquals(registerQuestion.getSuccessMessageText(), expectedMessage,
                "ERROR: The message text does not match.");
    }
}