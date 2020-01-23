package run;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin =  { "pretty",
        "json:target/plantilla.json", "html:target/plantilla.html" },
glue = {"stepdefs"},
features = "src/test/resources/features",
tags = {"@run","~@skip"}
)

public class AppTest {
	
}
