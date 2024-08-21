Feature: Gold Bar Game

  Scenario Outline: Find the fake gold bar

    Given User is on the gold bars game page called "<GamePageTitle>"
    When User performs weighings to find the fake bar
    Then User should be able to identify the fake bar and see "<SuccessMessage>" upon clicking the fake bar

    Examples:
      | GamePageTitle |  | SuccessMessage    |
      | React App     |  | Yay! You find it! |
