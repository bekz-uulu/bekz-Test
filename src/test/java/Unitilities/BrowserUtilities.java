package Unitilities;

import com.codeborne.selenide.Configuration;

public class BrowserUtilities {

    public static void initialize() {

        String browser = ConfigManager.getProperty("browser");
        if (browser != null) {
            Configuration.browser = browser;
        } else {
            Configuration.browser = "chrome";
        }

        String browserSize = ConfigManager.getProperty("browserSize");
        if (browserSize != null) {
            Configuration.browserSize = browserSize;
        } else {
            Configuration.browserSize = "1920x1080";
        }

    }
}
