package locators;

import org.openqa.selenium.By;

public class PageObjectMain {
    public By mainPageButtonLogIn = By.cssSelector("button.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");

    public By constructorTitle = By.xpath("//h1[contains(text(), 'Соберите бургер')]");
    public By constructorButtonSauces = By.xpath("//span[text()='Соусы']");
    public By constructorButtonSaucesClass = By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[2]");
    public By constructorButtonBuns = By.xpath("//span[text()='Булки']");
    public By constructorButtonBunsClass = By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[1]");
    public By constructorButtonFillings = By.xpath("//span[text()='Начинки']");
    public By constructorButtonFillingsClass = By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[3]");
}
