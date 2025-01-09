Feature: Payment

  Scenario: Successful Payment
    Given a customer with name "Susan"
    Given the customer is registered with Simple DTU Pay
    Given a merchant with name "Daniel"
    Given the merchant is registered with Simple DTU Pay
    When the merchant initiates a payment for 10 kr from the customer
    Then the payment is successful

  Scenario: List of payments
    Given a customer with name "Susan"
    And the customer is registered with Simple DTU Pay
    And a merchant with name "Daniel"
    And the merchant is registered with Simple DTU Pay
    Given a successful payment of 10 kr from the customer to the merchant
    When the manager asks for a list of payments
    Then the list contains a payment where customer "Susan" paid 10 kr to merchant "Daniel"

  Scenario: Customer is not known
    Given a merchant with name "Daniel"
    And the merchant is registered with Simple DTU Pay
    When the merchant initiates a payment for 10 kr using customer id "non-existent-id"
    Then the payment is not successful
    And an error message is returned saying "customer with id \"non-existent-id\" is unknown"

  Scenario: Merchant is not known
    Given a customer with name "Susan"
    And the customer is registered with Simple DTU Pay
    When the merchant initiates a payment for 10 kr using merchant id "non-existent-id"
    Then the payment is not successful
    And an error message is returned saying "merchant with id \"non-existent-id\" is unknown"
