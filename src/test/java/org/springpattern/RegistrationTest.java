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

public class RegistrationTest {
    private WebDriver driver;
    private String email = "user0047@yandex.ru";
    private String password = "password";
    private String name = "Username";
    private String accessToken;
    private UserCreationAndDeletion userCreationAndDeletion;
    PageObject pageObject;
    Boolean registration = false;

    @BeforeEach
    public void setUp() {

    }


    @AfterEach
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
    public void registrationTestFirefox() {
        testValidRegistration("firefox");
        testRegistrationInvalidPassword("firefox");
    }

    @Test
    public void registrationTestChrome() {
        testValidRegistration("chrome");
        testRegistrationInvalidPassword("chrome");
    }


    public void testValidRegistration(String browser) {
        registration = true;
        pageObject = new PageObject();
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/register");

        driver.manage().window().maximize();

        // Ввод имени
        WebElement nameField = driver.findElement(pageObject.registrationInputName);
        nameField.sendKeys(name); // Укажите имя для регистрации

        // Ввод email
        WebElement emailField = driver.findElement(pageObject.registrationInputEmail);
        emailField.sendKeys(email); // Укажите email для регистрации

        // Ввод пароля
        WebElement passwordField = driver.findElement(pageObject.registrationInputPassword);
        passwordField.sendKeys(password); // Укажите пароль для регистрации

        // Нажатие кнопки регистрации
        WebElement registerButton = driver.findElement(pageObject.registrationButtonRegistration);
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.logInTitleForm));

// Проверка на изменение заголовка
        WebElement loginTitle = driver.findElement(pageObject.logInTitleForm);
        String titleText = loginTitle.getText();
        //Assert.assertEquals("Заголовок формы не совпадает", "Вход", titleText);  // Убедитесь, что текст заголовка совпадает с ожидаемым

    }


    public void testRegistrationInvalidPassword(String browser) {
        pageObject = new PageObject();
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.nomoreparties.site/register");

        driver.manage().window().maximize();

        // Ввод имени
        WebElement nameField = driver.findElement(pageObject.registrationInputName);
        nameField.sendKeys(name); // Укажите имя для регистрации

        // Ввод email
        WebElement emailField = driver.findElement(pageObject.registrationInputEmail);
        emailField.sendKeys(email); // Укажите email для регистрации

        // Ввод пароля
        WebElement passwordField = driver.findElement(pageObject.registrationInputPassword);
        passwordField.sendKeys("12345"); // Укажите пароль для регистрации

        // Нажатие кнопки регистрации
        WebElement registerButton = driver.findElement(pageObject.registrationButtonRegistration);
        registerButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageObject.registrationErrorInvalidPassword)
        );
        // Проверка, что текст ошибки соответствует ожидаемому
        String expectedErrorMessage = "Некорректный пароль"; // ожидаемое сообщение
        String actualErrorMessage = errorElement.getText();

       // Assert.assertEquals("Ошибка: сообщение о некорректном пароле не совпадает.", expectedErrorMessage, actualErrorMessage);
    }
}

