package com.agi.blog.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            ChromeOptions options = new ChromeOptions();

            // Headless (necessário para CI/CD)
            options.addArguments("--headless=new"); // ou "--headless" se versão antiga
            options.addArguments("--window-size=1920,1080"); // garante que todos os elementos sejam visíveis

            // Evita cache e problemas de scripts antigos
            options.addArguments("--incognito");
            options.addArguments("--disable-cache");
            options.addArguments("--disk-cache-size=0");

            // Evita interferência de extensões
            options.addArguments("--disable-extensions");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}