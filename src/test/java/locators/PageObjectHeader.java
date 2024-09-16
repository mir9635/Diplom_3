package locators;

import org.openqa.selenium.By;

public class PageObjectHeader {
    public By headerButtonPersonalAccount = By.cssSelector("a.AppHeader_header__link__3D_hX[href='/account'] .AppHeader_header__linkText__3q_va");
    public By headerButtonConstructor = By.xpath("//ul[@class='AppHeader_header__list__3oKJj']/li[1]/a");
    public By headerLinkLogo = By.cssSelector(".AppHeader_header__logo__2D0X2 a");
}
