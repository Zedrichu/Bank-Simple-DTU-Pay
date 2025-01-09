package course.webservicedev;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SimpleDTUPaySteps {
   private Customer customer;
   private Merchant merchant;
   private String customerId, merchantId, customerId2, merchantId2;
   private SimpleDtuPayService dtupay = new SimpleDtuPayService();
   private boolean successful = false;
   private ArrayList<Payment> list;
   private RuntimeException exception;

   @Given("a customer with name {string}")
   public void aCustomerWithName(String name) {
      customer = new Customer(name);
   }

   @Given("the customer is registered with Simple DTU Pay")
   public void theCustomerIsRegisteredWithSimpleDTUPay() {
      customerId = dtupay.register(customer);
   }

   @Given("a merchant with name {string}")
   public void aMerchantWithName(String name) {
      merchant = new Merchant(name);
   }

   @Given("the merchant is registered with Simple DTU Pay")
   public void theMerchantIsRegisteredWithSimpleDTUPay() {
      merchantId = dtupay.register(merchant);
   }

   @When("the merchant initiates a payment for {int} kr from the customer")
   public void theMerchantInitiatesAPaymentForKrByTheCustomer(Integer amount) {
      successful = dtupay.pay(amount,customerId,merchantId);}

   @Then("the payment is successful")
   public void thePaymentIsSuccessful() {
      assertTrue(successful);
   }

   @When("registering a second customer with name {string}")
   public void registeringASecondCustomerWithName(String arg0) {
      customerId2 = dtupay.register(new Customer(arg0));
   }

   @Then("the customer IDs are different")
   public void theCustomerIDsAreDifferent() {
      assertNotEquals(customerId, customerId2);
   }

   @When("registering a second merchant with name {string}")
   public void registering_a_second_merchant_with_name(String arg0) {
      merchantId2 = dtupay.register(new Merchant(arg0));
   }

   @Then("the merchant IDs are different")
   public void the_merchant_i_ds_are_different() {
      assertNotEquals(merchantId, merchantId2);
   }

   @Given("a successful payment of {int} kr from the customer to the merchant")
   public void a_successful_payment_of_kr_from_the_customer_to_the_merchant(Integer int1) {
      dtupay.pay(int1, customerId, merchantId);
   }

   @When("the manager asks for a list of payments")
   public void the_manager_asks_for_a_list_of_payments() {
      list = dtupay.paymentList();
   }

   @Then("the list contains a payment where customer {string} paid {int} kr to merchant {string}")
   public void the_list_contains_a_payments_where_customer_paid_kr_to_merchant(String string, Integer int1, String string2) {
      assertTrue(list.contains(new Payment(customerId, merchantId, int1)));
   }

   @When("the merchant initiates a payment for {int} kr using customer id {string}")
   public void theMerchantInitiatesAPaymentForKrUsingCustomerId(int arg0, String arg1) {
      try {
         successful = dtupay.pay(arg0, arg1, merchantId);
      } catch (RuntimeException e) {
         exception = e;
      }
   }

   @Then("the payment is not successful")
   public void thePaymentIsNotSuccessful() {
      assertFalse(successful);
   }

   @And("an error message is returned saying {string}")
   public void anErrorMessageIsReturnedSayingNonExistentIdIsUnknown(String arg0) throws Throwable {
      assertNotNull(exception);
      assertTrue(exception instanceof UnknownAccountException);
      assertEquals(exception.getMessage(), arg0);
   }

   @When("the merchant initiates a payment for {int} kr using merchant id {string}")
   public void theMerchantInitiatesAPaymentForKrUsingMerchantId(int arg0, String arg1) {
      try {
         successful = dtupay.pay(arg0, customerId, arg1);
      }
      catch (RuntimeException e) {
         exception = e;
      }
   }

   @When("the customer is deregistered")
   public void theCustomerIsDeregistered() {
      try {
         dtupay.deregisterCustomer(customerId);
      } catch (RuntimeException e) {
         exception = e;
      }
   }


   @And("the merchant initiates a payment for {int} kr using obtained customer id")
   public void theMerchantInitiatesAPaymentForKrUsingObtainedCustomerId(int arg0) {
      try {
         successful = dtupay.pay(arg0, customerId, merchantId);
      } catch (RuntimeException e) {
         exception = e;
      }
   }


   @Then("the payment is not successful with error message containing {string}")
   public void thePaymentIsNotSuccessfulWithErrorMessage(String arg0) {
      assertFalse(successful);
      assertTrue(exception instanceof UnknownAccountException);
      String message = exception.getMessage();
      String[] parts = arg0.split("<id>");
      for (String part : parts) {
         assertTrue(message.contains(part));
      }
   }

   @When("the merchant is deregistered")
   public void theMerchantIsDeregistered() {
      try {
         dtupay.deregisterMerchant(merchantId);
      } catch (RuntimeException e) {
         exception = e;
      }
   }


   @And("the merchant initiates a payment for {int} kr using obtained merchant id")
   public void theMerchantInitiatesAPaymentForKrUsingObtainedMerchantId(int arg0) {
      try {
         successful = dtupay.pay(arg0, customerId, merchantId);
      } catch (RuntimeException e) {
         exception = e;
      }
   }
}