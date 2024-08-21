package pageObjects;

import Unitilities.WaitUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.By;
import stepDefinitions.Steps;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class GamePage {
    private static final Logger logger = LoggerFactory.getLogger(GamePage.class);

    // Locators
    private static final String GOLD_BAR_LOCATOR = "#coin_";
    private static final String LOCATOR_ON_LEFT_BOX = "#left_";
    private static final String LOCATOR_ON_RIGHT_BOX = "#right_";


    private SelenideElement weighButton = $(By.id("weigh"));
    private SelenideElement resetButton = $x("//button[text() = 'Reset']");
    private SelenideElement results = $(By.cssSelector(".result > button#reset"));
    private SelenideElement pageTitle = $("title");

    WaitUtils waitUtils = new WaitUtils();


    public void verifyGamePageHeader(String gamePageTitle) {
        String pageTitleText = pageTitle.getAttribute("innerText");
        assertEquals(gamePageTitle, pageTitleText);

    }

    // Actions
    public void weighElements() {
        weighButton.click();
    }

    public void resetTheScale() {

        waitUtils.waitForSelenideElementToBeVisible(resetButton, 5);
        resetButton.click();
    }

    public void clickOnBar(int barNumber) {
        $(GOLD_BAR_LOCATOR + barNumber).click();

    }

    public String getWeighingResult() {
        $x("//button[text() = '?']").shouldBe(Condition.disappear, Duration.ofSeconds(10, 3));
        return results.getText();
    }

    public void enterBarsIntoBowls(List<Integer> leftBars, List<Integer> rightBars) {
        // Enter bars into the left bowl
        for (Integer bar : leftBars) {
            $(LOCATOR_ON_LEFT_BOX + bar).setValue(String.valueOf(bar));
        }

        // Enter bars into the right bowl
        for (Integer bar : rightBars) {
            $(LOCATOR_ON_RIGHT_BOX + bar).setValue(String.valueOf(bar));
        }
    }

    public void weighGroups(List<Integer> leftBars, List<Integer> rightBars) {
        int minSize = Math.min(leftBars.size(), rightBars.size());

        for (int i = 0; i < minSize; i++) {
            SelenideElement leftInput = $(LOCATOR_ON_LEFT_BOX + leftBars.get(i));
            SelenideElement rightInput = $(LOCATOR_ON_RIGHT_BOX + rightBars.get(i));
            leftInput.setValue(String.valueOf(leftBars.get(i)));
            rightInput.setValue(String.valueOf(rightBars.get(i)));
        }

        weighElements();
    }

    public void verifySuccessMessage(String successMessage) {

        waitUtils.waitForAlertToPopUp();
        String alertText = switchTo().alert().getText();
        logger.info(() -> alertText + " " + "This is your alert message");

        assert alertText.equals(successMessage) : "Alert text does not match. Found: " + alertText;
        switchTo().alert().accept();
    }
}