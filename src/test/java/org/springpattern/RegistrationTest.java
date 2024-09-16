package org.springpattern;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import locators.PageObjectLogIn;
import locators.PageObjectRegistration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springpattern.api.UserCreationAndDeletion;




public class RegistrationTest extends BaseTest {
    Boolean registration = false;

    @Override
    @Before
    public void setUp() {
        pageObjectLogIn = new PageObjectLogIn();
        pageObjectRegistration = new PageObjectRegistration();
    }


    @Override
    @After
    public void clean() {
        if (registration) {
            userCreationAndDeletion = new UserCreationAndDeletion();
            accessToken = userCreationAndDeletion.authorizationUser(new User(email, password, name));
            userCreationAndDeletion.deleteUser(accessToken);
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Тест валидная регистрация через браузер Firefox")
    public void testValidRegistrationFirefox() {
        validRegistration("firefox");
    }

    @Test
    @Description("Тест валидная регистрация через браузер Chrome")
    public void testValidRegistrationChrome() {
        validRegistration("chrome");
    }

    @Test
    @Description("Тест регистрация невалидный пароль через браузер Firefox")
    public void testRegistrationInvalidPasswordFirefox() {
        registrationInvalidPassword("firefox");
    }

    @Test
    @Description("Тест регистрация невалидный пароль через браузер Chrome")
    public void testRegistrationInvalidPasswordChrome() {
        registrationInvalidPassword("chrome");
    }


    @Step("Валидная регистрация в браузере {browser}")
    public void validRegistration(String browser) {
        registration = true;
        initializeDriver(browser);
        goPage("/register");
        userRegistration();
        checkName(pageObjectLogIn.logInTitleForm, "Заголовок формы не совпадает");

    }

    @Step("Невалидная регистрация в браузере {browser}")
    public void registrationInvalidPassword(String browser) {
        initializeDriver(browser);
        goPage("/register");
        userRegistrationInvalid("12345");
        checkName(pageObjectRegistration.registrationErrorInvalidPassword, "Ошибка: сообщение о некорректном пароле не совпадает.");
    }
}

