package org.acme;

import jakarta.ws.rs.NotFoundException;

import java.util.HashMap;
import java.util.UUID;

public class MerchantService {
   private final HashMap<String, Merchant> merchants = new HashMap<>();
   private static MerchantService instance;

   public static MerchantService getInstance() {
      if (instance == null) {
         instance = new MerchantService();
      }
      return instance;
   }

   public String register(Merchant merchant) {
      UUID uuid = UUID.randomUUID();
      merchants.put(uuid.toString(), merchant);
      return uuid.toString();
   }

   public void unregister(String id) throws UnknownAccountException {
      System.out.println("Unregistering " + id);
      merchants.keySet().forEach(System.out::println);
      if (!merchants.containsKey(id)) {
         throw new UnknownAccountException("Merchant with id " + id + " not found");
      }
      merchants.remove(id);
   }

   public boolean checkMerchant(String merchantId) {
      return merchants.containsKey(merchantId);
   }
}
