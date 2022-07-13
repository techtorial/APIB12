Feature: Posts for user
  Scenario: User creates a new post
    Given User authorized for the endpoint
    When User creates a new post
    Then User should be able to see the post