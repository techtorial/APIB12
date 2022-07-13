package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_defs",
        dryRun = false,
        tags = "@covid",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class Runner {
}
