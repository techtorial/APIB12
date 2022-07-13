Feature: Covid API testing
  @covid
  Scenario: Get COVID information for all states
    When user sends GET request to Covid api
    Then status is 200
    And user get information about all states

  Scenario: Get COVID information for all countries
    When user sends GET request to Covid api to get countries info
    Then status is 200
    And user validates information about all counties