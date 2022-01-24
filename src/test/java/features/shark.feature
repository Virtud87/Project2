Feature: Shark should be able to interact with potential investments

  Scenario: Shark can create a shark profile
    Given The user is on the signup page
    When The user enters their first name
    When The user enters their last name
    When The user enters their business name
    When The user enters their username
    When The user enters their password
    When The user clicks on drop down list
    When The user clicks on Shark
    When The user clicks on the submit profile button
    Then The user is redirected to the home page

  Scenario: Shark logs in to their account
    Given The user is on the home page
    When The user enters their username
    When The user enters their password
    When The user clicks on the login button
    Then The shark is redirected to the shark page

  Scenario: Shark can make a comment
    Given The shark is on the shark page
    When The shark clicks on the comment button
    When The shark clicks on the recipient
    When The shark enters a date
    When The shark enters a comment
    When The shark clicks on the send comment button
    Then A message saying comment post was successful appears

  Scenario: Shark can make an offer on a pitch
    Given The shark is on the shark page
    When The shark clicks on the pitch button
    When The shark enters a percentage
    When The shark enters an amount
    When The shark clicks on the submit offer button
    Then A message saying offer was sent

  Scenario: Shark can accept a pitch
    Given The shark is on the shark page
    When The shark clicks on the accept button
    Then Message appears saying Accepted

  Scenario: Shark can log out
    Given The shark is on the shark page
    When The shark clicks on the logout button
    Then The user is redirected to the home page