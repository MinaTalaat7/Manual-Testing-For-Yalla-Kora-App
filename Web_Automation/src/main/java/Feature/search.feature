Feature: Add Items to Vodafone eShop Cart

  Scenario: Add items to the Vodafone eShop cart from the homepage
    Given I open the Vodafone eShop website
    When I click on the link
    And I locate the product
    And I click on the Add to Cart button
    And I make back
    And I click on the second product
    And I select the second product and click on Add to Cart
    Then I should have 2 items in the cart

  Scenario: Add items to the Vodafone eShop cart from the search bar
    Given I open the Vodafone eShop website
    When I click on the search field and write the search term
    And I click on Add to Cart for the third product
    Then I should have 1 additional item in the cart
