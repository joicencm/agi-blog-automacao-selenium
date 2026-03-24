package com.agi.blog.automation.base;

import com.agi.blog.automation.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void acessarUrl(String url) {
        driver.get(url);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void write(By locator, String texto) {
        driver.findElement(locator).sendKeys(texto);
    }

    public void submit(By locator) {
        driver.findElement(locator).submit();
    }
}