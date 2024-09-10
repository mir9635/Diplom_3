package org.springpattern;

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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


//Переход из личного кабинета в конструктор
public class SwitchingPersonalAccountToConstructorTest {
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
    public void switchingPersonalAccountToConstructorFirefox() {
        authorizedUserTransitionConstructor("firefox");
        authorizedUserTransitionLogo("firefox");
    }

    @Test
    public void switchingPersonalAccountToConstructorChrome() {
        authorizedUserTransitionConstructor("chrome");
        authorizedUserTransitionLogo("chrome");
    }

    //Проверь переход по клику на «Конструктор»
    public void authorizedUserTransitionConstructor(String browser) {
        pageObject = new PageObject();
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logIn);

        WebElement personalAccountButton = driver.findElement(pageObject.headerButtonPersonalAccount);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", personalAccountButton);

        WebElement constructorButton = driver.findElement(pageObject.headerButtonConstructor);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", constructorButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.constructorTitle));

        // Проверка текста заголовка
        assertEquals("Соберите бургер", header.getText());
    }

    //Проверь переход по клику на логотип
    public void authorizedUserTransitionLogo (String browser) {
        pageObject = new PageObject();
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logIn);

        WebElement personalAccountButton = driver.findElement(pageObject.headerButtonPersonalAccount);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", personalAccountButton);

        WebElement constructorButton = driver.findElement(pageObject.headerLinkLogo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", constructorButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject.constructorTitle));

        // Проверка текста заголовка
        assertEquals("Соберите бургер", header.getText());

    }
}
