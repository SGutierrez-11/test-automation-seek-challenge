package com.seek.challenge.stepdefinitions;

import com.seek.challenge.tasks.FacebookHomeTasks;
import io.cucumber.java.en.Given;

public class FacebookHomeSteps {

    private final FacebookHomeTasks facebookHomeTasks = new FacebookHomeTasks();


    @Given("the user is on the Facebook signup page")
    public void theUserIsOnTheFacebookSignupPage() {
        facebookHomeTasks
                .navigateToFacebookHomePage()
                .clickCreateAccountButton();
    }
}
