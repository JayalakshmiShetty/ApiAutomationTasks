package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "json:target/cucumber.json", "json:target/cucumber.json",
		"de.monochromata.cucumber.report.PrettyReports:target/cucumber" }, tags={"@Tests"},
		features = {"src/test/java/Features" }, 
		glue = { "Steps" }, 
		dryRun = false, monochrome = true)
public class TestRunnerAll {

}
