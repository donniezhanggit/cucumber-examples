Feature: allocation

  Scenario: allocate and free parking slots
    When I run the parking lot application with file "inputs_2.txt"
    Then output should contain "in file mode" 
    And output should contain "Slot No.	Registration No.	Colour"
    And output should contain "1	KA-01-HH-1234	White"
    And output should contain "2	KA-01-HH-9999	White"
    And output should contain "3	KA-01-BB-0001	Black"
    And output should contain "5	KA-01-HH-2701	Blue"
    And output should contain "6	KA-01-HH-3141	Black"
