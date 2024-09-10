package org.springpattern.factory;

import org.openqa.selenium.WebDriver;

public interface WebDriverProvider {
    WebDriver createDriver();
}
