Feature: Facebook User Registration
  As a new user
  I want to create an account on Facebook
  So that I can access my profile and platform features

  @Register @Facebook
  Scenario: Successful user registration with random details
    Given the user is on the Facebook registration page
    When the user fills the registration form with valid random details
    And the user submits the registration form
    Then the system should prompt for email verification