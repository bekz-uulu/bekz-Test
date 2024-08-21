package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GamePage;

import java.util.ArrayList;
import java.util.List;


public class Steps {

    private static final String LEFT_SIDE_IS_HEAVIER = ">";
    private static final String RIGHT_SIDE_IS_HEAVIER = "<";
    private static final String BOTH_SIDES_ARE_EQUAL = "=";

    private GamePage gamePage = new GamePage();

    @Given("User is on the gold bars game page called {string}")
    public void userIsOnTheGoldBarsGamePage(String gamePageTitle) {
        gamePage.verifyGamePageHeader(gamePageTitle);
    }

    @When("User performs weighings to find the fake bar")
    public void userPerformsWeighingsToFindTheFakeBar() {

        List<Integer> bars = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            bars.add(i);
        }

        findFakeBar(bars);
    }

    @Then("User should be able to identify the fake bar and see {string} upon clicking the fake bar")
    public void userShouldBeAbleToIdentifyTheFakeBarAndSeeUponClickingTheFakeBar(String successMessage) {
        gamePage.verifySuccessMessage(successMessage);

    }

    private void findFakeBar(List<Integer> bars) {

        int groupSize = bars.size() / 3;
        List<Integer> group1 = bars.subList(0, groupSize);
        List<Integer> group2 = bars.subList(groupSize, 2 * groupSize);
        List<Integer> group3 = bars.subList(2 * groupSize, bars.size());

        gamePage.weighGroups(group1, group2);
        String result = gamePage.getWeighingResult();

        gamePage.resetTheScale();

        if (result.equals(LEFT_SIDE_IS_HEAVIER)) {
            weighIndividualBars(group2);
        } else if (result.equals(RIGHT_SIDE_IS_HEAVIER)) {
            weighIndividualBars(group1);
        } else {
            weighIndividualBars(group3);
        }
    }

    private void weighIndividualBars(List<Integer> barsToTest) {


        for (int i = 0; i < barsToTest.size() - 1; i += 2) {
            List<Integer> leftBars = List.of(barsToTest.get(i));
            List<Integer> rightBars = List.of(barsToTest.get(i + 1));

            gamePage.enterBarsIntoBowls(leftBars, rightBars);
            gamePage.weighElements();

            String result = gamePage.getWeighingResult();

            switch (result) {
                case LEFT_SIDE_IS_HEAVIER:
                    System.out.println("Bar " + barsToTest.get(i) + " is lighter, hence its a fake");
                    gamePage.clickOnBar(barsToTest.get(i + 1));
                    break;

                case RIGHT_SIDE_IS_HEAVIER:
                    System.out.println("Bar " + barsToTest.get(i + 1) + " is lighter, hence its a fake");
                    gamePage.clickOnBar(barsToTest.get(i));
                    break;

                case BOTH_SIDES_ARE_EQUAL:
                    System.out.println("Bars " + barsToTest.get(i) + " and " + barsToTest.get(i + 1) + " are the same. Third bar is the fake one.");
                    gamePage.clickOnBar(barsToTest.get(i + 2));
                    break;

                default:
                    throw new IllegalArgumentException("Scale is Broken :)" + result);
            }
        }

    }
}