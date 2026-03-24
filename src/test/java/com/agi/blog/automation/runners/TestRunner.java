package com.agi.blog.automation.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.agi.blog.automation.steps",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        }
)
public class TestRunner {
}