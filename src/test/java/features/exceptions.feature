Feature: The system should be able to handle exceptions

  Scenario: The system should reject null values when creating a profile
    Given The user is on the signup page
    When The user clicks on the submit profile button
    Then An alert pops up saying form cannot accept null values

  Scenario: The system should reject null values when shark logs in
    Given The user is on the home page
    When The user clicks on the login button
    Then An alert pops up saying form cannot accept null values

  Scenario: The system should reject inputs that are too long when creating a profile
    Given The user is on the signup page
    When The user enters their first name
    When The user enters their last name
    When The user enters their business name
    When The user enters too long of a username
    When The user enters their password
    When The user clicks on drop down list
    When The user clicks on Shark
    When The user clicks on the submit profile button
    Then An alert pops up saying the entry has exceeded the allowed length

  Scenario: The system should reject inputs that are too long when shark logs in
    Given The user is on the home page
    When The user enters too long of a username
    When The user enters their password
    When The user clicks on the login button
    Then The shark is redirected to the shark page

  Scenario: The system should reject inputs that are too short when creating a profile
    Given The user is on the signup page
    When The user enters their first name
    When The user enters their last name
    When The user enters their business name
    When The user enters too short of a username
    When The user enters their password
    When The user clicks on drop down list
    When The user clicks on Shark
    When The user clicks on the submit profile button
    Then An alert pops up saying the entry has not met the allowed minimum length

  Scenario: The system should reject incorrect data types when creating a profile
    Given The user is on the signup page
    When The user enters non-alphabet characters as their first name
    When The user enters their last name
    When The user enters their business name
    When The user enters their username
    When The user enters their password
    When The user clicks on drop down list
    When The user clicks on Shark
    When The user clicks on the submit profile button
    Then An alert pops up saying the entry has incorrect data type

  Scenario: The system should reject incorrect data types when creating an offer
    Given The shark is on the shark page
    When The shark enters the pitch ID
    When The shark enters non-numeric characters for an amount
    When The shark enters a percentage
    When The shark clicks on the submit offer button
    Then An alert pops up saying the entry has incorrect data type

  Scenario: The system should reject a repeated username when creating a profile
    Given The user is on the signup page
    When The user enters their first name
    When The user enters their last name
    When The user enters their business name
    When The user enters their username
    When The user enters their password
    When The user clicks on drop down list
    When The user clicks on Shark
    When The user clicks on the submit profile button
    Then An alert pops up saying the username entered is already taken

  Scenario: The system should reject invalid login credentials
    Given The user is on the home page
    When The user enters their username
    When The user enters incorrect password
    When The user clicks on the login button
    Then An alert pops up saying either username or password or both are incorrect