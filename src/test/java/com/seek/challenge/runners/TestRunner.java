package com.seek.challenge.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.seek.challenge.stepdefinitions",
                "com.seek.challenge.hooks"
        },
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        tags = "@Facebook",
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Data provider override for Cucumber scenarios. Keep parallel disabled to avoid shared state issues.
     *
     * @return array of scenarios used by TestNG
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}