Feature: query

  Scenario: allocate parking slots and query status
    When I run the parking lot application with file "inputs_3.txt"
    Then output should contain "in file mode"
    And output should contain "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333"
    And output should contain "1, 2, 4"
    And output should contain "6"
    And output should contain "Not found"