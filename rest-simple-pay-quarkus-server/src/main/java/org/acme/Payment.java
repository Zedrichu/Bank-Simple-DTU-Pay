package org.acme;

public record Payment(String customerId, String merchantId, int amount) {
}
