package com.seek.challenge.tasks;

import com.seek.challenge.pages.LoginPage; // Inherit locators from LoginPage
import com.seek.challenge.utils.SeleniumActions;
import com.seek.challenge.utils.WaitActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanupTasks extends LoginPage {

    private static final Logger logger = LoggerFactory.getLogger(CleanupTasks.class);

    /**
     * Attempt to delete a previously created account if the delete link is available.
     * This method handles the absence of a logged-in session gracefully.
     */
    public void deleteAccountSafely() {
        logger.info("🧹 Checking if generated account cleanup is required...");
        try {
            WaitActions.setImplicitWait(2);

            if (deleteAccountLink.isDisplayed()) {
                SeleniumActions.click(deleteAccountLink, "Delete Account Button (Cleanup)");
                logger.info("✅ Cleanup: Account deleted successfully.");
            } else {
                logger.info("ℹ️ Cleanup: No active session detected. No cleanup required.");
            }
        } catch (Exception e) {
            logger.info("ℹ️ Cleanup: 'Delete Account' button not visible (previous test failed or not logged in). {}", e.getMessage());
        } finally {
            WaitActions.resetImplicitWait();
        }
    }
}