package org.springpattern;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Test;


public class LogInTest extends BaseTest {

    @Test
    @Description("Тест кнопки «Войти в аккаунт» на главной страницу через браузер Firefox")
    public void testMainPageButtonLogInFirefox() {
        mainPageButtonLogIn("firefox");
    }

    @Test
    @Description("Тест кнопки «Войти в аккаунт» на главной страницу через браузер Chrome")
    public void testMainPageButtonLogInChrome() {
        mainPageButtonLogIn("chrome");
    }

    @Test
    @Description("Тест кнопки «Личный кабинет» в шапке страницы через браузер Firefox")
    public void testMainPageButtonPersonalAccountFirefox() {
        mainPageButtonPersonalAccount("firefox");
    }

    @Test
    @Description("Тест кнопки «Личный кабинет» в шапке страницы через браузер Chrome")
    public void testMainPageButtonPersonalAccountChrome() {
        mainPageButtonPersonalAccount("chrome");
    }


    @Test
    @Description("Тест кнопки «Войти» на странице регистрации через браузер Firefox")
    public void testRegistrationButtonLogInFirefox() {
        registrationButtonLogIn("firefox");
    }

    @Test
    @Description("Тест кнопки «Войти» на странице регистрации через браузер Chrome")
    public void testRegistrationButtonLogInChrome() {
        registrationButtonLogIn("chrome");
    }

    @Test
    @Description("Тест кнопки «Войти» на странице восстановления пароля через браузер Firefox")
    public void testRecoveryButtonLogInFirefox() {
        recoveryButtonLogIn("firefox");
    }

    @Test
    @Description("Тест кнопки «Войти» на странице восстановления пароля через браузер Chrome")
    public void testRecoveryButtonLogInChrome() {
        recoveryButtonLogIn("chrome");
    }



    @Step("Вход по кнопки «Войти в аккаунт» в браузере {browser}")
    public void mainPageButtonLogIn(String browser) {
        initializeDriver(browser);
        goPage("");

        clickSignInAccount();
        checkName(pageObjectLogIn.logInTitleForm, "Заголовок формы не совпадает");
        switchingPersonalCabinet();
    }

    @Step("Вход по кнопки «Личный кабинет» в браузере {browser}")
    public void mainPageButtonPersonalAccount(String browser) {
        initializeDriver(browser);
        goPage("");
        switchingPersonalCabinet();
        checkName(pageObjectLogIn.logInTitleForm, "Заголовок формы не совпадает");
        performLogin();
    }

    @Step("Вход по кнопки «Войти» на странице регистрации в браузере {browser}")
    public void registrationButtonLogIn(String browser) {
        initializeDriver(browser);
        goPage("/register");
        clickSignInAccountRegistration();
        checkName(pageObjectLogIn.logInTitleForm, "Заголовок формы не совпадает");
        performLogin();
    }

    @Step("Вход по кнопки «Войти» на странице восстановления пароля в браузере {browser}")
    public void recoveryButtonLogIn(String browser) {
        initializeDriver(browser);
        goPage("/forgot-password");
        clickSignInAccountrRecovery();
        checkName(pageObjectLogIn.logInTitleForm, "Заголовок формы не совпадает");
        performLogin();
    }
}
