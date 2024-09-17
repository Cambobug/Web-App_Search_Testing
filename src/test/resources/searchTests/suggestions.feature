# Created by Owner at 9/16/2024
Feature: search suggestions
  check that the search suggestions are relevant

  Scenario: search suggestions exist but are not visible
    Given I am on the Ebay home page
    When The search bar is empty
    Then I expect there to be no search suggestions

  Scenario: search suggestions exist
    Given I am on the Ebay home page
    When I am about to search for "Soccer"
    Then I expect there to be search suggestions


  Scenario Outline: search suggestions are relevant
    Given I am on the Ebay home page
    When I am about to search for "<search>"
    Then I expect the search suggestions to contain the word "<suggestion>"

  Examples:
  | search | suggestion |
  | Soccer | soccer     |
  | Nerf   | nerf       |


  Scenario Outline: search suggestions do not contain unwanted items
    Given I am on the Ebay home page
    When I am about to search for "<search>"
    Then I expect the search resu to not contain the word "<irrelevant>"

  Examples:
  |     search      |     irrelevant    |
  |  soccer -balls  |       balls       |
  | running -shoes  |       shoes       |
  | pickle -gherkin |       gherkin     |
