@Register @Smoke
Feature: User Registration
  As a new user
  I want to register an account with dynamic data
  So that I can access the platform functionalities without data collision

  Background:
    Given the user is on the home page

  Scenario: Successful user registration with random valid data
    When the user navigates to the Sign Up page
    And fills the initial signup form with a random user
    And fills the complete registration form with valid random details
    And confirms the account creation
    Then the account should be created successfully
    And the success message "ACCOUNT CREATED!" should be visible