Feature: Get list of all possible books
  Scenario: the authorized user gets all available books
    Given user is authorized
    When  user asks for a list of books
    Then the list of all books will be shown