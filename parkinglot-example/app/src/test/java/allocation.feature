Feature: allocation

  Scenario: allocate parking slots
    When I run the parking lot application with file "inputs_2.txt"
    Then output should contain "in file mode" 
    And output should contain "Created a parking lot with 6 slots"
    And output should contain "Allocated slot number: 1"
    And output should contain "Allocated slot number: 2"
    And output should contain "Allocated slot number: 3"
    And output should contain "Allocated slot number: 4"
    And output should contain "Allocated slot number: 5"
    And output should contain "Allocated slot number: 6"
