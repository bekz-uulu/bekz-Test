package stepDefinitions;

import Unitilities.BrowserUtilities;
import Unitilities.ConfigManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;


import static com.codeborne.selenide.Selenide.open;

public class Hooks {

    @Before
    public void setUp() {
        // Initialize browser configuration
        BrowserUtilities.initialize();

        // Open the base URL
        String baseUrl = ConfigManager.getProperty("baseUrl");
        if (baseUrl != null) {
            open(baseUrl);
        } else {
            throw new RuntimeException("Base URL not found in properties file");
        }
    }

    @After
    public void tearDown() {
        // Perform teardown actions here, like closing the browser
        // For example:
        // Selenide.closeWebDriver();
    }
}
