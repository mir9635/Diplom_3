package org.springpattern;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Test;


public class SwitchingPersonalAccountToConstructorTest extends BaseTest {

    @Test
    @Description("Тест перехода по клику на «Конструктор» из личного кабинета через браузер Firefox")
    public void testAuthorizedUserTransitionConstructorFirefox() {
        authorizedUserTransitionConstructor("firefox");
    }

    @Test
    @Description("Тест перехода по клику на «Конструктор» из личного кабинета через браузер Chrome")
    public void testAuthorizedUserTransitionConstructorChrome() {
        authorizedUserTransitionConstructor("chrome");
    }

    @Test
    @Description("Тест перехода по клику на лого из личного кабинета через браузер Firefox")
    public void testAuthorizedUserTransitionLogoFirefox() {
        authorizedUserTransitionLogo("firefox");
    }

    @Test
    @Description("Тест перехода по клику на лого из личного кабинета через браузер Chrome")
    public void testAuthorizedUserTransitionLogoChrome() {
        authorizedUserTransitionLogo("chrome");
    }

    @Step("Перехода по клику на «Конструктор»")
    public void authorizedUserTransitionConstructor(String browser) {
        initializeDriver(browser);
        goPage("/login");
        performLogin();
        switchingPersonalCabinet();
        clickConstructor();
        checkName(pageObjectMain.constructorTitle, "Соберите бургер");
    }

    @Step("Перехода по клику на лого")
    public void authorizedUserTransitionLogo(String browser) {
        initializeDriver(browser);
        goPage("/login");
        performLogin();
        switchingPersonalCabinet();
        clickLogo();
        checkName(pageObjectMain.constructorTitle, "Соберите бургер");
    }
}
