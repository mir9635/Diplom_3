package org.springpattern;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Test;


public class LoggingAccountTest extends BaseTest {


    @Test
    @Description("Тест выход по кнопке «Выйти» в личном кабинете через браузер Firefox")
    public void testLogInAccountInFirefox() {
        logAccount("firefox");
    }

    @Test
    @Description("Тест выход по кнопке «Выйти» в личном кабинете через браузер Chrome")
    public void testLogInAccountInChrome() {
        logAccount("chrome");
    }



    @Step("Выход из аккаунта в браузере {browser}")
    private void logAccount(String browser) {
        initializeDriver(browser);
        goPage("/login");
        performLogin();
        switchingPersonalCabinet();
        performExit();
        switchingPersonalCabinet();
        checkName(pageObjectLogIn.logInTitleForm, "Заголовок формы входа не отображается, выход не произошел.");

    }
}
