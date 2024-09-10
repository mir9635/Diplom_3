package org.springpattern;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import locators.PageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springpattern.api.UserCreationAndDeletion;
import org.springpattern.factory.WebDriverFactory;
import org.testng.Assert;

import java.time.Duration;

public class LogInAccountTest {
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
        pageObject = new PageObject();

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
    @Description("Тест входа в аккаунт через браузер Firefox!!!!!")
    public void logInAccountInFirefox() {
        logAccount("firefox");
    }

    @Test
    @Description("Тест входа в аккаунт через браузер Chrome!!!!")
    public void logInAccountInChrome() {
        logAccount("chrome");
    }

    @Step("Вход в аккаунт в браузере {browser}")
    public void logAccount(String browser) {
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/login");

        driver.manage().window().maximize();



        // Ввод email
        WebElement emailField = driver.findElement(pageObject.logInInputEmail);
        emailField.sendKeys(email); // Укажите email для регистрации

        // Ввод пароля
        WebElement passwordField = driver.findElement(pageObject.logInInputPassword);
        passwordField.sendKeys(password);

        WebElement logIn = driver.findElement(pageObject.logInButtonLogin);
        logIn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement personalAccountButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.headerButtonPersonalAccount));

        try {
            personalAccountButton.click();
        } catch (Exception e) {
            // Если клик не сработал, используем JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", personalAccountButton);
        }

        WebElement personalAccountExit = wait.until(ExpectedConditions.elementToBeClickable(pageObject.personalAccountButtonExit));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", personalAccountExit);

        WebElement logInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.logInTitleForm));


        Assert.assertTrue(logInTitle.isDisplayed(),"Заголовок формы входа не отображается, выход не произошел.");

    }
}
