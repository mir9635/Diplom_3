package locators;

import org.openqa.selenium.By;

public class PageObjectLogIn {
    public By logInTitleForm = By.xpath("//h2[text()='Вход']");
    public By logInInputEmail = By.cssSelector("input[name='name']");
    public By logInInputPassword = By.cssSelector("input[type='password'][name='Пароль']");
    public By logInButtonLogin = By.cssSelector("button.button_button__33qZ0");
    By logInButtonRegister = By.cssSelector("a.Auth_link__1fOlj[href='/register']");
    By logInButtonRecoverPassword = By.cssSelector("a.Auth_link__1fOlj[href='/forgot-password']");
}
