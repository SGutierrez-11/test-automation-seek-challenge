package com.seek.challenge.tasks;

import com.seek.challenge.pages.FacebookHomePage;

public class FacebookHomeTasks extends FacebookHomePage {

        public FacebookHomeTasks navigateToFacebookHomePage() {
            driver.get("https://www.facebook.com/");
            return  this;
        }

        public void clickCreateAccountButton() {
            createAccountButton.click();
        }
}
