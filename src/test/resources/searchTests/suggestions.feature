# Created by Owner at 9/16/2024
Feature: search suggestions
  check that the search suggestions are relevant

  #scenario will test that search suggestions are not visible when nothing is in the search bar
  Scenario: search suggestions exist but are not visible
    Given I am on the Ebay home page
    When The search bar is empty
    Then I expect there to be no search suggestions

  #scenario will test that search suggestions are visible when something is in the search bar
  Scenario: search suggestions exist
    Given I am on the Ebay home page
    When I am about to search for "Soccer"
    Then I expect there to be search suggestions

  #scenario will test that search suggestions are relevant to search term
  Scenario Outline: search suggestions are relevant
    Given I am on the Ebay home page
    When I am about to search for "<search>"
    Then I expect the search suggestions to contain the word "<suggestion>"

  Examples:
  | search | suggestion |
  | Soccer | soccer     |
  | Nerf   | nerf       |