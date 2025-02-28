package ru.itmo.bis.backend.constant;

public enum UserRole {

    ARTIST,

    LANDLORD;

    public static UserRole fromString(String operatorStr) {
        try {
            return UserRole.valueOf(operatorStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return ARTIST;
        }
    }
}
