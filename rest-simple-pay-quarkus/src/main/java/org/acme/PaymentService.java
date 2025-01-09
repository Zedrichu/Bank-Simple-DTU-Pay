package org.acme;

import java.util.ArrayList;

public class PaymentService {
   ArrayList<Payment> payments = new ArrayList<>();
   CustomerService customerService = CustomerService.getInstance();
   MerchantService merchantService = MerchantService.getInstance();

   public ArrayList<Payment> getPayments() {
      return payments;
   }

   public void addPayment(Payment payment) throws UnknownAccountException {
      if (!customerService.checkCustomer(payment.customerId())) {
         throw new UnknownAccountException( String.format(
                     "customer with id \"%s\" is unknown", payment.customerId()));
      } else if (!merchantService.checkMerchant(payment.merchantId())) {
         throw new UnknownAccountException( String.format(
                     "merchant with id \"%s\" is unknown", payment.merchantId()
         ));
      }
     payments.add(payment);
   }
}
