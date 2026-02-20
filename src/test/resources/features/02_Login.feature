@Login @Regression
Feature: User Authentication
  As a registered user
  I want to login with my credentials
  So that I can access my account details securely

  Background:
    Given the user is on the home page

  @Positive
  Scenario: Login successful with previously registered user
    When the user navigates to the Login page
    And logs in with the registered user credentials
    Then the user name should be visible in the header

  @Negative
  Scenario Outline: Login attempt with invalid credentials
    When the user navigates to the Login page
    And attempts to login with email "<email>" and password "<password>"
    Then the error message "Your email or password is incorrect!" should be displayed

    Examples:
      | email                       | password       |
      | valid_user@example.com      | WrongPass123!  |
      | non_existent_user@test.com  | ValidPass123!  |
      | fake_format@domain.xyz      | AnyPassword123 |
      | bad_email_format            | ValidPass123!  |