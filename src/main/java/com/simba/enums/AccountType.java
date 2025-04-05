package com.simba.enums;

public enum AccountType {
    SAVINGS, CURRENT;

    public static AccountType fromOrdinal(int ordinal) {
        try {
            return AccountType.values()[ordinal];
        } catch (Exception e) {
            throw new RuntimeException("Invalid account type");
        }
    }
}
