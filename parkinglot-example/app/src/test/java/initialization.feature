Feature: initialization

  # verify application is running.
  Scenario: verify application is in file mode
    When I run the parking lot application with file "inputs_1.txt"
    Then output should contain "in file mode"

  Scenario: initialize parking slot
    When I run the parking lot application with file "inputs_1.txt"
    Then output should contain "Created a parking lot with 6 slots"

  Scenario: initialize parking slot
    When I run the parking lot application with file "inputs_1.txt"
    Then output should contain "all slots are free"

#
#Feature: initialization
#    Scenario: first launch
#        Given a parking lot application
#        When I run the application for the first time
#        Then I should see text "How many parking slots do you want?"
#
#      Scenario: create parking lot
#
#      Given the parking lot application is running
#        When I type "10"
#      Then I should see text "Parking Lot created with 10 slots"
#
#Given the parking lot application is running
#When I type "abc"
#Then I should see text "Invalid Value"
#
#Given the parking lot application is running
#When I type "0"
#Then I should see text "Invalid Value"
#
#Given the parking lot application is running
#When I type "-1"
#Then I should see text "Invalid Value"