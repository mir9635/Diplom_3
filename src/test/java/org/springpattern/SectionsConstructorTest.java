package org.springpattern;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import locators.PageObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SectionsConstructorTest extends BaseTest {

    @Override
    @Before
    public void setUp() {
        pageObject = new PageObject();
    }


    @After
    public void clean() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Тест что в конструкторе работают переходы по разделам для браузера Firefox")
    public void testSectionsConstructorTestFirefox() {
        navigatingSectionsConstructor("firefox");
    }

    @Test
    @Description("Тест что в конструкторе работают переходы по разделам для браузера Chrome")
    public void testSectionsConstructorTestChrome() {
        navigatingSectionsConstructor("chrome");
    }

    @Step("Переходы по разделам в конструкторе для браузере {browser}")
    public void navigatingSectionsConstructor(String browser) {

        initializeDriver(browser);
        goPage("");

        // Ожидание, пока элемент "Соусы" станет доступным, и клик
        clickConstructorElement(pageObject.constructorButtonSauces);
        checkingElementActivity(pageObject.constructorButtonSaucesClass, "Вкладка 'Соусы' не активна");

        // Ожидание, пока элемент "Булки" станет доступным, и клик
        clickConstructorElement(pageObject.constructorButtonBuns);
        // Проверка, что вкладка "Булки" активна, и вкладка "Соусы" не активна
        checkingElementActivity(pageObject.constructorButtonBunsClass, "Вкладка 'Булки' не активна");

        // Ожидание, пока элемент "Начинки" станет доступным, и клик
        clickConstructorElement(pageObject.constructorButtonFillings);
        // Проверка, что вкладка "Начинки" активна, и вкладка "Булки" не активна
        checkingElementActivity(pageObject.constructorButtonFillingsClass, "Вкладка 'Начинки' не активна");

    }
}
