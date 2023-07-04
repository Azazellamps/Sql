package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class EntranceTest {

    @AfterAll
    static void cleanBase() {
        SQLHelper.cleanMysql();
    }


    @Test
    void shouldLogin() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.positiveLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.positiveVerify(verificationCode.getCode());
    }

    @Test
    public void EntranceTestNegative() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getNegativeAuthInfo();
        loginPage.negativeLogin(authInfo);
        loginPage.getErrorNotification();
    }

    @Test
    public void threTimesIncorrectPasswordEntryTheSystemIsBlocked() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getNegativePassword();
        loginPage.negativeLogin(authInfo);
        loginPage.cleanField();
        loginPage.negativeLogin(authInfo);
        loginPage.cleanField();
        loginPage.negativeLogin(authInfo);
        loginPage.blockNotification();
    }
}

