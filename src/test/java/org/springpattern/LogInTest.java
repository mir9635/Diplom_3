package org.springpattern;

import locators.PageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springpattern.api.UserCreationAndDeletion;
import org.springpattern.factory.WebDriverFactory;
import org.testng.Assert;

import java.time.Duration;

public class LogInTest {
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
        userCreationAndDeletion.createUniqueUser(new User(email, password, name));
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
    public void logInTestFirefox() {
        testMainPageButtonLogIn("firefox");
        testMainPageButtonPersonalAccount("firefox");
        testRegistrationButtonLogIn("firefox");
        testRecoveryButtonLogIn("firefox");
    }

    @Test
    public void logInTestChrome() {
        testMainPageButtonLogIn("chrome");
        testMainPageButtonPersonalAccount("chrome");
        testRegistrationButtonLogIn("chrome");
        testRecoveryButtonLogIn("chrome");
    }

    //вход по кнопке «Войти в аккаунт» на главной,

    public void testMainPageButtonLogIn(String browser) {
        pageObject = new PageObject();
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(pageObject.mainPageButtonLogIn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.logInTitleForm));

        // Проверка на изменение заголовка
        WebElement loginTitle = driver.findElement(pageObject.logInTitleForm);
        String titleText = loginTitle.getText();
        Assert.assertEquals("Заголовок формы не совпадает", "Вход", titleText);

        // Ввод email
        WebElement emailField = driver.findElement(pageObject.logInInputEmail);
        emailField.sendKeys(email); // Укажите email для регистрации

        // Ввод пароля
        WebElement passwordField = driver.findElement(pageObject.logInInputPassword);
        passwordField.sendKeys(password);

        WebElement logIn = driver.findElement(pageObject.logInButtonLogin);
        logIn.click();

    }

    //вход через кнопку «Личный кабинет»,
    public void testMainPageButtonPersonalAccount(String browser) {
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

        // Ввод email
        WebElement emailField = driver.findElement(pageObject.logInInputEmail);
        emailField.sendKeys(email); // Укажите email для регистрации

        // Ввод пароля
        WebElement passwordField = driver.findElement(pageObject.logInInputPassword);
        passwordField.sendKeys(password);

        WebElement logIn = driver.findElement(pageObject.logInButtonLogin);
        logIn.click();
    }

    //вход через кнопку в форме регистрации,
    public void testRegistrationButtonLogIn(String browser) {
        pageObject = new PageObject();
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/register");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(pageObject.registrationButtonLogIn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.logInTitleForm));

        // Проверка на изменение заголовка
        WebElement loginTitle = driver.findElement(pageObject.logInTitleForm);
        String titleText = loginTitle.getText();
        Assert.assertEquals("Заголовок формы не совпадает", "Вход", titleText);

        // Ввод email
        WebElement emailField = driver.findElement(pageObject.logInInputEmail);
        emailField.sendKeys(email); // Укажите email для регистрации

        // Ввод пароля
        WebElement passwordField = driver.findElement(pageObject.logInInputPassword);
        passwordField.sendKeys(password);

        WebElement logIn = driver.findElement(pageObject.logInButtonLogin);
        logIn.click();

    }

    //вход через кнопку в форме восстановления пароля.
    public void testRecoveryButtonLogIn(String browser) {
        pageObject = new PageObject();
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(pageObject.recoveryButtonLogIn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.logInTitleForm));

        // Проверка на изменение заголовка
        WebElement loginTitle = driver.findElement(pageObject.logInTitleForm);
        String titleText = loginTitle.getText();
        Assert.assertEquals("Заголовок формы не совпадает", "Вход", titleText);

        // Ввод email
        WebElement emailField = driver.findElement(pageObject.logInInputEmail);
        emailField.sendKeys(email); // Укажите email для регистрации

        // Ввод пароля
        WebElement passwordField = driver.findElement(pageObject.logInInputPassword);
        passwordField.sendKeys(password);

        WebElement logIn = driver.findElement(pageObject.logInButtonLogin);
        logIn.click();
    }
}
