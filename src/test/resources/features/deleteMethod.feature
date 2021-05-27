Feature: Delete a book
  Scenario: the authorized user deletes a book
    Given user is authorized
    And the list of books is available
    When user deletes a book
    Then response 200 OK will be shown
