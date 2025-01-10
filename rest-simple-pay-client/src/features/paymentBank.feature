Feature: Payment
  Scenario: Successful Payment
    Given a user with name "Susan", last name "Baldwin", and CPR "030154-4421"
    And the user is registered with the bank and an initial balance of 1000 kr
    And the user is registered as a customer with Simple DTU Pay using their bank account
    And a user with name "Daniel", last name "Oliver", and CPR "131161-3045"
    And the user is registered with the bank and an initial balance of 1000 kr
    And the user is registered as a merchant with Simple DTU Pay using their bank account
    When the merchant initiates a payment for 10 kr from the customer
    Then the payment is successful
    And the balance of the customer at the bank is 990 kr
    And the balance of the merchant at the bank is 1010 kr

  Scenario: List of Payments
    Given a user with name "Susan", last name "Baldwin", and CPR "030154-4421"
    And the user is registered with the bank and an initial balance of 1000 kr
    And the user is registered as a customer with Simple DTU Pay using their bank account
    And a user with name "Daniel", last name "Oliver", and CPR "131161-3045"
    And the user is registered with the bank and an initial balance of 1000 kr
    And the user is registered as a merchant with Simple DTU Pay using their bank account
    Given a successful payment of 10 kr from the customer to the merchant
    When the manager asks for a list of payments
    Then the list contains a payment where customer "Susan" paid 10 kr to merchant "Daniel"


  Scenario: Customer is not known
    Given a user with name "Daniel", last name "Oliver", and CPR "131161-3045"
    And the user is registered with the bank and an initial balance of 1000 kr
    And the user is registered as a merchant with Simple DTU Pay using their bank account
    When the merchant initiates a payment for 10 kr using customer id "non-existent-id"
    Then the payment is not successful
    And an error message is returned saying "customer with id \"non-existent-id\" is unknown"


  Scenario: Merchant is not known
    Given a user with name "Daniel", last name "Oliver", and CPR "131161-3045"
    And the user is registered with the bank and an initial balance of 1000 kr
    And the user is registered as a customer with Simple DTU Pay using their bank account
    When the merchant initiates a payment for 10 kr using merchant id "non-existent-id"
    Then the payment is not successful
    And an error message is returned saying "merchant with id \"non-existent-id\" is unknown"


