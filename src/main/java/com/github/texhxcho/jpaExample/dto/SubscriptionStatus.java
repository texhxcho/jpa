package com.github.texhxcho.jpaExample.dto;

public enum SubscriptionStatus {
    ACTIVATE("구매 완료"),
    CANCEL("구매 취소");

    private String description;

    SubscriptionStatus(String description) {
        this.description = description;
    }

    public static SubscriptionStatus byDescription(String description) {
        for (SubscriptionStatus subscriptionStatus : values()) {
            if (subscriptionStatus.description.equals(description)) {
                return subscriptionStatus;
            }
        }

        return null;
    }
}
