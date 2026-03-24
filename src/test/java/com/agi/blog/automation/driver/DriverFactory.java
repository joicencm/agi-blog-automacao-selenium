package com.agi.blog.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            ChromeOptions options = new ChromeOptions();

            // Configurações gerais
            options.addArguments("--incognito");
            options.addArguments("--disable-cache");
            options.addArguments("--disk-cache-size=0");
            options.addArguments("--disable-extensions");

            // Headless quando rodar no CI ou quando passado -Dheadless=true
            String headless = System.getProperty("headless", "false");
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            // Cria driver
            driver = new ChromeDriver(options);

            // só maximiza fora do headless
            if (!headless.equalsIgnoreCase("true")) {
                driver.manage().window().maximize();
            }
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