Feature: Add new book
  Scenario: the authorized user adds new book
    Given user is authorized
    And the list of books is available
    When user adds new book
    Then response 201 created will be shown