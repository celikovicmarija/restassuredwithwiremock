Feature: Update book
  Scenario: the authorized user updates book
    Given user is authorized
    And the list of books is available
    When user updates the book
    Then response 200 OK will be shown