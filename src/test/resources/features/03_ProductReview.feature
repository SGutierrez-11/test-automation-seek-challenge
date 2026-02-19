@Review @Regression
Feature: Product Review (Comment Simulation)
  As a user
  I want to leave a review on a product
  So that I can share my opinion with others

  Scenario: User leaves a successful review on the first product
    Given the user is on the home page
    When the user navigates to the Products page
    And opens the first product details
    And submits a review with the comment "Excellent product, highly recommended!"
    Then the review success message "Thank you for your review." should be visible