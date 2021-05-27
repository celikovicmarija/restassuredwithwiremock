Feature: Get one book with path param

  Scenario: the authorized user gets exactly one book with path param
    Given user is authorized
    When user asks for exactly one book
    Then the book will be shown