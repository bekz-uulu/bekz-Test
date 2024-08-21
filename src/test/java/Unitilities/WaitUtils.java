package Unitilities;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Duration TIMEOUT_SECONDS = Duration.ofSeconds(10);

    public static void waitForAlertToPopUp() {
        WebDriver driver = Selenide.webdriver().driver().getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver,
                TIMEOUT_SECONDS);

        try {
            wait.until(driver1 -> {
                try {
                    driver1.switchTo().alert();
                    return true;
                } catch (NoAlertPresentException e) {
                    return false;
                }
            });
        } catch (TimeoutException e) {
            throw new RuntimeException("Alert was not present within 10 seconds.");
        }
    }


    // Method to wait for a Selenide element to be visible
    public static void waitForSelenideElementToBeVisible(SelenideElement element, long timeoutInSeconds) {
        element.shouldBe(Condition.visible, Duration.ofSeconds(timeoutInSeconds));
    }

}
