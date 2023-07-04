package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public VerificationPage positiveLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }


    public void negativeLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }

    public void getErrorNotification() {
        errorNotification.shouldHave(text("Ошибка"));
    }

    public void blockNotification() {
            errorNotification.shouldHave(Condition.text("Ошибка"));
        }

        public void cleanField() {
            loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
            passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
        }
    }
