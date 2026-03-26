package com.agi.blog.automation.hooks;

import com.agi.blog.automation.driver.DriverFactory;
import com.agi.blog.automation.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                ScreenshotUtils.takeScreenshot(
                        DriverFactory.getDriver(),
                        scenario.getName().replaceAll(" ", "_")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao capturar screenshot: " + e.getMessage());
        } finally {
            DriverFactory.quitDriver();
        }
    }
}