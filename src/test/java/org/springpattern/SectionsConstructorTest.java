package org.springpattern;

import locators.PageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springpattern.factory.WebDriverFactory;
import org.testng.Assert;

import java.time.Duration;

public class SectionsConstructorTest {
    private WebDriver driver;
    PageObject pageObject;


    @AfterEach
    public void clean() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void sectionsConstructorTestFirefox() {
        navigatingSectionsConstructor("firefox");
    }

    @Test
    public void sectionsConstructorTestChrome() {
        navigatingSectionsConstructor("chrome");
    }

    public void navigatingSectionsConstructor(String browser) {
        pageObject = new PageObject();
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

// Ожидание, пока элемент "Соусы" станет доступным, и клик
        WebElement saucesButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.constructorButtonSauces));
        saucesButton.click();

        WebElement saucesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.constructorButtonSaucesClass));
        Assert.assertTrue( saucesTab.getAttribute("class").contains("tab_tab_type_current__2BEPc"),"Вкладка 'Соусы' не активна");

// Ожидание, пока элемент "Булки" станет доступным, и клик
        WebElement bunsButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.constructorButtonBuns));
        bunsButton.click();

        // Проверка, что вкладка "Булки" активна, и вкладка "Соусы" не активна
        WebElement bunsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.constructorButtonBunsClass));
        Assert.assertTrue( bunsTab.getAttribute("class").contains("tab_tab_type_current__2BEPc"),"Вкладка 'Булки' не активна");

// Ожидание, пока элемент "Начинки" станет доступным, и клик
        WebElement fillingsButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.constructorButtonFillings));
        fillingsButton.click();

        // Проверка, что вкладка "Начинки" активна, и вкладка "Булки" не активна
        WebElement fillingsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.constructorButtonFillingsClass));
        Assert.assertTrue( fillingsTab.getAttribute("class").contains("tab_tab_type_current__2BEPc"),"Вкладка 'Булки' не активна");
    }
}
