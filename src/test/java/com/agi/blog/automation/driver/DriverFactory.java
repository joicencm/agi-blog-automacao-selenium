package com.agi.blog.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            // 🔥 GARANTE driver compatível no CI
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // Configurações gerais
            options.addArguments("--incognito");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");

            String headless = System.getProperty("headless", "false");

            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");

                // 🔥 ESSENCIAIS no CI
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--remote-allow-origins=*");
            }

            driver = new ChromeDriver(options);

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