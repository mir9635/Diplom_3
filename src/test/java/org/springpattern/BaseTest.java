package org.springpattern;

import io.qameta.allure.Step;
import locators.PageObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springpattern.api.UserCreationAndDeletion;
import org.springpattern.factory.WebDriverFactory;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public String email = "user00002@yandex.ru";
    public String password = "password";
    public String name = "Username";
    public String accessToken;
    public UserCreationAndDeletion userCreationAndDeletion;
    public PageObject pageObject;


//    @BeforeEach
    @Before
    public void setUp() {
        userCreationAndDeletion = new UserCreationAndDeletion();
        userCreationAndDeletion.createUniqueUser(new User(email,password,name));
        accessToken = userCreationAndDeletion.authorizationUser();
        pageObject = new PageObject();

    }


    //@AfterEach
    @After
    public void clean() {
        System.out.println("DRIVER " +driver);
        if (accessToken != null) {
            userCreationAndDeletion.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }


    @Step("Инициализация драйвера для браузера {browser}")
    protected void initializeDriver(String browser) {
        driver = WebDriverFactory.getDriver(browser);
    }

    @Step("Переход на страницу: {url}")
    protected void goPage(String url) {
        driver.get("https://stellarburgers.nomoreparties.site"+url);
        driver.manage().window().maximize();
    }

    @Step("Регистрации")
    protected void userRegistration() {
        driver.findElement(pageObject.registrationInputName).sendKeys(name);
        driver.findElement(pageObject.registrationInputEmail).sendKeys(email);
        driver.findElement(pageObject.registrationInputPassword).sendKeys(password);
        WebElement registerButton = driver.findElement(pageObject.registrationButtonRegistration);
        clickElement(registerButton);
    }

    @Step("Регистрации без валидного пароля")
    protected void userRegistrationInvalid(String password) {
        driver.findElement(pageObject.registrationInputName).sendKeys(name);
        driver.findElement(pageObject.registrationInputEmail).sendKeys(email);
        driver.findElement(pageObject.registrationInputPassword).sendKeys(password);
        WebElement registerButton = driver.findElement(pageObject.registrationButtonRegistration);
        clickElement(registerButton);
    }

    @Step("Выполнение входа")
    protected void performLogin() {
        driver.findElement(pageObject.logInInputEmail).sendKeys(email);
        driver.findElement(pageObject.logInInputPassword).sendKeys(password);
        driver.findElement(pageObject.logInButtonLogin).click();

    }

    @Step("Проверка отображения элемента")
    protected  void checkName(By pageObject, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject));
        Assert.assertTrue(text, name.isDisplayed());

    }

    @Step("Переход в личный кабинет по кнопке 'Личный Кабинет'")
    public void switchingPersonalCabinet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement personalAccountButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.headerButtonPersonalAccount));

        clickElement(personalAccountButton);
    }

    @Step("Выполнение выхода")
    protected void performExit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement personalAccountExit = wait.until(ExpectedConditions.elementToBeClickable(pageObject.personalAccountButtonExit));

        clickElement(personalAccountExit);
    }

    @Step("Клик по кнопке «Конструктор»")
    public void clickConstructor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement constructorButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.headerButtonConstructor));

        clickElement(constructorButton);

    }

    @Step("Клик по лого")
    public void clickLogo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoButton = wait.until(ExpectedConditions.elementToBeClickable(pageObject.headerLinkLogo));

        clickElement(logoButton);
    }

    @Step("Клик по кнопке «Войти в аккаунт»")
    public void clickSignInAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mainPageLogIn = wait.until(ExpectedConditions.elementToBeClickable(pageObject.mainPageButtonLogIn));

        clickElement(mainPageLogIn);
    }

    @Step("Клик по кнопке «Войти» на странице регистрации")
    public void clickSignInAccountRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement refistration =  wait.until(ExpectedConditions.elementToBeClickable(pageObject.registrationButtonLogIn));

        clickElement(refistration);
    }

    @Step("Клик по кнопке «Войти» на восстановления пароля")
    public void clickSignInAccountrRecovery() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement recovery =   wait.until(ExpectedConditions.elementToBeClickable(pageObject.recoveryButtonLogIn));

        clickElement(recovery);
    }

    @Step("Клик по разделам в конструкторе")
    public void clickConstructorElement(By pageObject) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement clickConstructor = wait.until(ExpectedConditions.elementToBeClickable(pageObject));
        clickElement(clickConstructor);
    }

    @Step("Проверка активности элемента в конструкторе")
    public void checkingElementActivity(By pageObject, String text) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement saucesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObject));
//
//        Assert.assertTrue(text, saucesTab.getAttribute("class").contains("tab_tab_type_current__2BEPc"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Ожидание, пока элемент не станет видимым и будет содержать требуемый класс
        WebElement saucesTab = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(pageObject);
                return (element.isDisplayed() && element.getAttribute("class").contains("tab_tab_type_current__2BEPc")) ? element : null;
            }
        });

        // Если элементов не было, то кидаем исключение
        Assert.assertNotNull("Элемент не найден или не активен", saucesTab);
    }




    @Step("Клик по элементу")
    public void clickElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
