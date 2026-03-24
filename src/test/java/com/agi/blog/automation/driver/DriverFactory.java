package com.agi.blog.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            ChromeOptions options = new ChromeOptions();

            // Configuração para CI / headless
            if (isCiEnvironment()) {
                options.addArguments("--headless=new"); // ou "--headless" se versão antiga
                options.addArguments("--disable-gpu"); // necessário no Linux headless
                options.addArguments("--window-size=1920,1080");
            }

            // Configurações comuns
            options.addArguments("--incognito");
            options.addArguments("--disable-cache");
            options.addArguments("--disk-cache-size=0");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-sandbox"); // recomendado no Linux CI
            options.addArguments("--disable-dev-shm-usage"); // evita crashes em runners

            // Caminho do ChromeDriver (apenas se necessário no CI)
            if (isCiEnvironment()) {
                System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            }

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

    // Detecta se está rodando no GitHub Actions / CI
    private static boolean isCiEnvironment() {
        String ci = System.getenv("CI");
        return ci != null && ci.equalsIgnoreCase("true");
    }
}