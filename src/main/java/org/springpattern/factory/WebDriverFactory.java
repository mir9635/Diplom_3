package org.springpattern.factory;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public static WebDriver getDriver(String browser) {
        WebDriverProvider provider;

        switch(browser.toLowerCase()) {
            case "chrome":
                provider = new ChromeDriverProvider();
                break;
            case "firefox":
                provider = new FirefoxDriverProvider();
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }

        return provider.createDriver();
    }
}
