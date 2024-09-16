package locators;

import org.openqa.selenium.By;

public class PageObjectRegistration {

    public By registrationInputName = By.cssSelector("input[name='name']");
    public By registrationInputEmail = By.xpath("(//input[@name='name'])[2]");
    public By registrationInputPassword = By.cssSelector("input[type='password'][name='Пароль']");
    public By registrationButtonRegistration = By.xpath("//button[text()='Зарегистрироваться']");
    public By registrationErrorInvalidPassword = By.cssSelector(".input__error.text_type_main-default");
    public By registrationButtonLogIn = By.cssSelector("a.Auth_link__1fOlj");
}
