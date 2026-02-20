package com.seek.challenge.utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class DataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));

    /**
     * Generate a semi-unique email address for test users.
     *
     * @return synthetic email string
     */
    public static String getEmail() {
        return "auto_" + System.currentTimeMillis() + "@test.com";
    }

    /**
     * Generate a realistic first name.
     *
     * @return first name string
     */
    public static String getFirstName() {
        return faker.name().firstName();
    }

    /**
     * Generate a realistic last name.
     *
     * @return last name string
     */
    public static String getLastName() {
        return faker.name().lastName();
    }

    /**
     * Generate a test-safe password in a known pattern.
     *
     * @return password string
     */
    public static String getPassword() {
        return "Test@" + faker.number().digits(4); // Example: Test@1234
    }

    /**
     * Generate a realistic street address.
     *
     * @return address string
     */
    public static String getAddress() {
        return faker.address().streetAddress();
    }

    /**
     * Generate a realistic city name.
     *
     * @return city string
     */
    public static String getCity() {
        return faker.address().city();
    }

    /**
     * Generate a realistic postal/zip code.
     *
     * @return zip code string
     */
    public static String getZipCode() {
        return faker.address().zipCode();
    }

    /**
     * Generate a numeric mobile number string.
     *
     * @return mobile number string (10 digits)
     */
    public static String getMobileNumber() {
        return faker.number().digits(10);
    }

    /**
     * Generate a unique email address using the Putsbox service for testing email functionality.
     */
    public static String getPutsboxEmail() {
        return "qa_auto_" + System.currentTimeMillis() + "@putsbox.com";
    }
}