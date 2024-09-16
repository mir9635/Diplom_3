package org.springpattern;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import locators.PageObjectMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SectionsConstructorTest extends BaseTest {

    @Override
    @Before
    public void setUp() {
        pageObjectMain = new PageObjectMain();
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
        clickConstructorElement(pageObjectMain.constructorButtonSauces);
        checkingElementActivity(pageObjectMain.constructorButtonSaucesClass, "Вкладка 'Соусы' не активна");

        // Ожидание, пока элемент "Булки" станет доступным, и клик
        clickConstructorElement(pageObjectMain.constructorButtonBuns);
        checkingElementActivity(pageObjectMain.constructorButtonBunsClass, "Вкладка 'Булки' не активна");

        // Ожидание, пока элемент "Начинки" станет доступным, и клик
        clickConstructorElement(pageObjectMain.constructorButtonFillings);
        checkingElementActivity(pageObjectMain.constructorButtonFillingsClass, "Вкладка 'Начинки' не активна");

    }
}
