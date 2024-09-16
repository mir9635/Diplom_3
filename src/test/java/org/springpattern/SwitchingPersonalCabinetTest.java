package org.springpattern;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Test;


public class SwitchingPersonalCabinetTest extends BaseTest {

    @Test
    @Description("Тест на переход по клику на «Личный кабинет» без авторизации через браузер Firefox")
    public void testTransitionUnauthorizedUserFirefox() {
        transitionUnauthorizedUser("firefox");
    }

    @Test
    @Description("Тест на переход по клику на «Личный кабинет» без авторизации через браузер Chrome")
    public void testTransitionUnauthorizedUserChrome() {
        transitionUnauthorizedUser("chrome");
    }

    @Test
    @Description("Тест на переход по клику на «Личный кабинет» с авторизацией через браузер Firefox")
    public void testSwitchingPersonalCabinetFirefox() {
        transitionAuthorizedUser("firefox");
    }

    @Test
    @Description("Тест на переход по клику на «Личный кабинет» с авторизацией через браузер Chrome")
    public void testSwitchingPersonalCabinetChrome() {
        transitionAuthorizedUser("chrome");
    }


    @Step("Переход по клику на «Личный кабинет» без авторизации в браузере {browser}")
    public void transitionUnauthorizedUser(String browser) {
        initializeDriver(browser);
        goPage("");
        switchingPersonalCabinet();
        checkName(pageObjectLogIn.logInTitleForm, "Заголовок формы не совпадает");

    }

    @Step("Переход по клику на «Личный кабинет» с авторизацией в браузере {browser}")
    public void transitionAuthorizedUser(String browser) {
        initializeDriver(browser);
        goPage("/login");
        performLogin();
        switchingPersonalCabinet();
        checkName(pageObjectPersonalAccount.personalAccountButtonExit, "Кнопка 'Выход' не найдена. Пользователь не авторизован или произошла ошибка.");
    }
}
