Feature: OrangeHRM Login

  Scenario: Successful login with valid credentials
    Given user is on the OrangeHRM login page
    When user enters valid username and password
    And clicks on the login button
    Then user should be navigated to the Dashboard page

  Scenario: Unsuccessful login with invalid credentials
    Given user is on the OrangeHRM login page
    When user enters invalid username and password
    And clicks on the login button
    Then user should see an error message
