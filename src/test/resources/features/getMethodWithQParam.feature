Feature: Get one book with query param

  Scenario: the authorized user gets exactly one book with query param
    Given user is authorized
    When user asks for exactly one book with query param
    Then the book will be shown