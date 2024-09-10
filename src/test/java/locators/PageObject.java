package locators;

import org.openqa.selenium.By;

public class PageObject {

    //Шапка
    //кнопка личный кабинет на главной странице
    public  By headerButtonPersonalAccount = By.cssSelector("a.AppHeader_header__link__3D_hX[href='/account'] .AppHeader_header__linkText__3q_va");
    //Конструктор
    public By headerButtonConstructor = By.xpath("//ul[@class='AppHeader_header__list__3oKJj']/li[1]/a");
    //Лого
    public By headerLinkLogo = By.cssSelector(".AppHeader_header__logo__2D0X2 a");
    //Главная страница
    //кнопка входа
    public By mainPageButtonLogIn = By.cssSelector("button.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");


    //Страница Входа
    // заголовок формы
    public By logInTitleForm = By.xpath("//h2[text()='Вход']");
    //поле ввода email
    public By logInInputEmail = By.cssSelector("input[name='name']");
    //поле ввода пароля
    public By logInInputPassword = By.cssSelector("input[type='password'][name='Пароль']");
    //кнопка входа
    public By logInButtonLogin = By.cssSelector("button.button_button__33qZ0");
    //кнопка регестрации
    By logInButtonRegister = By.cssSelector("a.Auth_link__1fOlj[href='/register']");
    //кнопка востановить пароль
    By logInButtonRecoverPassword = By.cssSelector("a.Auth_link__1fOlj[href='/forgot-password']");

    //Регистрация
    // поле имя
    public  By registrationInputName = By.cssSelector("input[name='name']");
    // поле емаил
    public By registrationInputEmail = By.xpath("(//input[@name='name'])[2]");
    // поле пароль
    public By registrationInputPassword = By.cssSelector("input[type='password'][name='Пароль']");
    // кнопка регестрация
    public By registrationButtonRegistration = By.xpath("//button[text()='Зарегистрироваться']");
    // ошибка Некорректный пароль
    public By registrationErrorInvalidPassword = By.cssSelector(".input__error.text_type_main-default");
    // кнопка войти
    public By registrationButtonLogIn = By.cssSelector("a.Auth_link__1fOlj");


    // Восстановление пароля
    // Поле ввода ематл
    By recoveryInputEmail = By.cssSelector("input[name='name']");
    // Кнопка востановить
    By recoveryButtonRecovery = By.cssSelector("button.button_button_type_primary__1O7Bx");
    // Кнопка войти
    public By recoveryButtonLogIn = By.cssSelector("a.Auth_link__1fOlj");

    //Личный кабинет
    //кнопка выход
    public By personalAccountButtonExit = By.xpath("//li[contains(@class, 'Account_listItem__35dAP')]//button[text()='Выход']");

    //Конструктор
    //заголовок
    public By constructorTitle = By.xpath("//h1[contains(text(), 'Соберите бургер')]");
    public By constructorButtonSauces = By.xpath("//span[text()='Соусы']");
    public By constructorButtonSaucesClass = By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[2]");

    public By constructorButtonBuns = By.xpath("//span[text()='Булки']");
    public By constructorButtonBunsClass = By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[1]");

    public By constructorButtonFillings = By.xpath("//span[text()='Начинки']");
    public By constructorButtonFillingsClass = By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[3]");




}
