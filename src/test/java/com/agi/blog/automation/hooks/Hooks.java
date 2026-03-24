package com.agi.blog.automation.hooks;

import com.agi.blog.automation.driver.DriverFactory;
import com.agi.blog.automation.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtils.takeScreenshot(
                    DriverFactory.getDriver(),
                    scenario.getName()
            );
        }
        DriverFactory.quitDriver();
    }
}