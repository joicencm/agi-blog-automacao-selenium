package com.agi.blog.automation.hooks;

import com.agi.blog.automation.driver.DriverFactory;
import com.agi.blog.automation.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;

public class Hooks {

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtils.takeScreenshot(
                    DriverFactory.getDriver(),
                    scenario.getName().replaceAll(" ", "_") + "_step"
            );
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        DriverFactory.quitDriver();
    }
}