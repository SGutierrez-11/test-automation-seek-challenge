package com.seek.challenge.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataManager {

    private static final Logger logger = LoggerFactory.getLogger(TestDataManager.class);
    private static final String DATA_FILE_PATH = "target/user-data.properties";

    /**
     * Save user credentials to a properties file for later retrieval in login tests.
     * This method is called after a successful registration test.
     *
     * @param email    user email to store
     * @param password user password to store
     * @param name     full name of the registered user
     */
    public static void saveUserCredentials(String email, String password, String name) {
        Properties properties = new Properties();
        properties.setProperty("email", email);
        properties.setProperty("password", password);
        properties.setProperty("name", name);

        try (FileOutputStream output = new FileOutputStream(DATA_FILE_PATH)) {
            properties.store(output, "Data created by Registration Test");
            logger.info("💾 Credentials stored correctly: {} / *****", email);
        } catch (IOException e) {
            logger.error("❌ Error storing credentials: {}", e.getMessage());
        }
    }

    /**
     * Retrieve the email previously stored by the registration flow.
     *
     * @return stored email string
     */
    public static String getRegisteredEmail() {
        return getProperty("email");
    }

    /**
     * Retrieve the password previously stored by the registration flow.
     *
     * @return stored password string
     */
    public static String getRegisteredPassword() {
        return getProperty("password");
    }

    /**
     * Retrieve the name previously stored by the registration flow.
     *
     * @return stored user full name
     */
    public static String getRegisteredName() {
        return getProperty("name");
    }

    /**
     * Internal helper to read a property from the persistent data file.
     * Throws a runtime exception if the file cannot be read.
     *
     * @param key property name to retrieve
     * @return the stored value for the key
     */
    private static String getProperty(String key) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(DATA_FILE_PATH)) {
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            logger.error("⚠️ Not able to read user data: {}", e.getMessage());
            throw new RuntimeException("No data found, first run the registration test to create user data.");
        }
    }
}