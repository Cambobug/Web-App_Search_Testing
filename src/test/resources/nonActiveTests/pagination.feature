# Created by Owner at 9/16/2024
Feature: pagination
  check that the search result has multiple pages

  Scenario: Looking for search result pagination
    Given I am on the Ebay home page
    When I search for "Soccer"
    Then The page should have result pagination

  Scenario: Moving forward in result pagination
    Given I have just searched for "Soccer"
    When I click the pagination forwards arrow
    Then The page should move forward to page "2"

  Scenario: Moving backwards in result pagination
    Given I have just searched for "Soccer" on the second page of results
    When I click the pagination backwards arrow
    Then The page should move back to page "1"

  Scenario: Starting on the first page after a search
    Given I am on the Ebay home page
    When I search for "Baseball"
    Then I should be on the first page of result pagination

  Scenario: Clicking the last page number after a search
    Given I have just searched for "Baseball"
    When I click the final page number
    Then I should end up on the final page of results
