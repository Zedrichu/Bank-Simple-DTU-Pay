Feature: Bank Registration

  Scenario: Successful Bank Account Creation
    Given a user with name "Susan", last name "Baldwin", and CPR "030154-4421"
    When the user is registered with the bank and an initial balance of 1000 kr
    Then the service returns a bank account number and no error

  Scenario: Successful Customer DTU Pay Registration
    Given a user with name "Susan", last name "Baldwin", and CPR "030154-4421"
    And the user is registered with the bank and an initial balance of 1000 kr
    When the user is registered as a customer with Simple DTU Pay using their bank account
    Then the customer is registered

  Scenario: Successful Merchant DTU Pay Registration
    Given a user with name "Daniel", last name "Baldwin", and CPR "030154-4422"
    And the user is registered with the bank and an initial balance of 1000 kr
    When the user is registered as a merchant with Simple DTU Pay using their bank account
    Then the merchant is registered