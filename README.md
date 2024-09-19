Cameron Fraser - Web App Search Testing

A testing framework created with Cucumber and Selenium WebDriver used to test common search functionality features and UI elements of eBay.

*Search Results Tests*

Test 1: This test scenario is meant to test the search feature of eBay and see if the resulting page is relevant to the search.

    Test 1.1: Searching for Nerf Toys

    Test 1.2: Searching for Baseball

    Test 1.3: Searching for Gherkins

Test 2: This test scenario is meant to test searching for something that doesn't exist.

Test 3: This test scenario is meant to test a search that will result in an error.

*Search Suggestion Tests*

Test 1: This test is meant to determine whether search suggestions are visible when nothing has been typed in the search bar.

Test 2: This test is meant to determine whether search suggestions are visible after something has been typed into the search bar.

Test 3: This test is meant to determine whether search suggestions are relevant to the terms in the search bar.

    Test 3.1: Searching for "Soccer" and looking at suggestions to confirm majority of suggestions have term "soccer" in them

    Test 3.2: Searching for "Nerf" and looking at suggestions to confirm majority of suggestions have term "nerf" in them

*Search Pagination Tests*

Test 1: This test determines whether the pagination UI elements exist on a common search.

Test 2: This test determines whether the forward arrow UI element moves the user to the next page.

Test 3: This test determines whether the backwards arrow UI element moves the user to the previous page.

Test 4: This test confirms whether a search will place the user on the first page of the result's pagination.

Test 5: This test confirms the ability to move to other pages by clicking that pages number, in this case the final furthest visible page.

