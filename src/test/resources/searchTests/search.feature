# Created by Owner at 9/16/2024
Feature: searching
  checking a search result

  # scenario will test simple search functionality
  Scenario Outline: Searching for different items
    Given I am on the Ebay home page
    When I search for "<search>"
    Then the page title should start with "<title>"

  Examples:
    |   search  |   title   |
    | Nerf Toys | nerf toys |
    | Baseball  | baseball  |
    | Gherkins  | gherkins  |

  # searching for something that does not exist should return 0 results
  Scenario: Searching for something that does not exist
    Given I am on the Ebay home page
    When I search for "POISDZhjfgvvPA;EROIKFN"
    Then the page should have no matches

  # using the '-' operator on eBay equates to a NOT (in this case anything BUT baseball)
  # this is explicitly warned against on the eBay searching guide located here: https://developer.ebay.com/api-docs/user-guides/static/finding-user-guide/finding-searching-by-keywords.html
  Scenario: Using invalid search terms
    Given I am on the Ebay home page
    When I search for "-baseball"
    Then the page should reject your search