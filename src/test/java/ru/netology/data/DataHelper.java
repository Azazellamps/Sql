package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    private static Faker faker = new Faker();

    private DataHelper() {
    }
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getNegativeAuthInfo() {
        return new AuthInfo(faker.name().fullName(), faker.internet().password());
    }

    public static AuthInfo getNegativePassword() {
        return new AuthInfo("vasya", faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


}
