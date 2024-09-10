package org.springpattern;

import locators.PageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springpattern.api.UserCreationAndDeletion;
import org.springpattern.factory.WebDriverFactory;
import org.testng.Assert;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;


public class SwitchingPersonalCabinetTest {
    private WebDriver driver;
    private String email = "user0046@yandex.ru";
    private String password = "password";
    private String name = "Username";
    private String accessToken;
    private UserCreationAndDeletion userCreationAndDeletion;
    PageObject pageObject;

    @BeforeEach
    public void setUp() {
        userCreationAndDeletion = new UserCreationAndDeletion();
        userCreationAndDeletion.createUniqueUser(new User(email,password,name));
        accessToken = userCreationAndDeletion.authorizationUser();

    }


    @AfterEach
    public void clean() {
        if (accessToken != null) {
            userCreationAndDeletion.deleteUser(accessToken);
        }


        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void switchingPersonalCabinetFirefox() {
        transitionUnauthorizedUser("firefox");
        transitionAuthorizedUser("firefox");
    }

    @Test
    public void switchingPersonalCabinetChrome() {
        transitionUnauthorizedUser("chrome");
        transitionAuthorizedUser("chrome");
    }


    public  void transitionUnauthorizedUser(String browser) {
        pageObject = new PageObject();
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(pageObject.headerButtonPersonalAccount)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.logInTitleForm));

        // Проверка на изменение заголовка
        WebElement loginTitle = driver.findElement(pageObject.logInTitleForm);
        String titleText = loginTitle.getText();
        Assert.assertEquals("Заголовок формы не совпадает", "Вход", titleText);

    }

    public void transitionAuthorizedUser(String browser) {
        pageObject = new PageObject();
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/login");

        driver.manage().window().maximize();

        // Ввод email
        WebElement emailField = driver.findElement(pageObject.logInInputEmail);
        emailField.sendKeys(email);

        // Ввод пароля
        WebElement passwordField = driver.findElement(pageObject.logInInputPassword);
        passwordField.sendKeys(password);

        WebElement logIn = driver.findElement(pageObject.logInButtonLogin);
        logIn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement personalAccountButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.headerButtonPersonalAccount));

        try {
            personalAccountButton.click();
        } catch (Exception e) {
            // Если клик не сработал, используем JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", personalAccountButton);
        }


        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.personalAccountButtonExit));

        try {
            logoutButton.click();
        } catch (Exception e) {
            // Если клик не сработал, используем JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutButton);
        }

        // Проверка, что кнопка "Выход" отображается
        assertTrue("Кнопка 'Выход' не найдена. Пользователь не авторизован или произошла ошибка.", logoutButton.isDisplayed());
    }
}
