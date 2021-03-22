Feature: Purchase

  Background: launch site
    Given user launch the store site

  Scenario Outline: A Successful Purchase of pillow with Credit Card

    Given user clicks on buy now
    When click on checkout with credit card details as "<cardNumber>" and "<expiryDate>" and "<cvv>" and "<otp>"
    Then verify transaction successful message
    Then user navigates to home page
    Then verify purchase successful message

    Examples:
     |cardNumber|expiryDate|cvv|otp|
     |4811 1111 1111 1114|1224|123|112233|

     Scenario Outline: A UnSuccessful Purchase of pillow with Credit Card

         Given user clicks on buy now
         When click on checkout with credit card details as "<cardNumber>" and "<expiryDate>" and "<cvv>" and "<otp>"
         Then verify transaction failure message

         Examples:
          |cardNumber|expiryDate|cvv|otp|
          |4911 1111 1111 1113|0224|123|112233|
