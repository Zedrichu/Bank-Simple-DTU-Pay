package org.acme;

public class UnknownAccountException extends RuntimeException {
   public UnknownAccountException(String message) {
      super(message);
   }
}
