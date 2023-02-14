Feature: 
  To do positive testing upto Add to cart functionality in Old Navy website

  Scenario: 
    To verify and validate upto Add to cart functionality.

    Given Browser is open
    And User is on old navy home Page "https://oldnavy.gap.com/"
    When User enters search item "womens Dress" in serach button
    Then User have to select second item from the items displayed
    Then User have to select the size
    Then User clicks Add to Bag button
    Then User clicks checkout
    Then again User clicks Checkout
    Then verify whether the User is in the sign in page.
