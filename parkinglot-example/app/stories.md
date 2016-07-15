## References: ##
- http://www.romanpichler.com/blog/10-tips-writing-good-user-stories/
- http://xp123.com/articles/invest-in-good-stories-and-smart-tasks/


### Personae: ###
1. parking lot attendant

Notes:

- I initially felt there could be two personae here : the driver of the car is the second.
- But the system is going to be interacted with by the _attendant_, therefore he is the only consumer type here.

## Epics
### The parking lot system should be properly initialized.

#### Story: Parking lot initialization

As a parking lot attendant
* I should be able to initialize the parking lot system with the number of parking slots
* So that the total number of available slots gets stored in the system.

**Acceptance Criteria**

Given a parking lot application
* when I start the com.vish.cucumber.app for the first time
* I should be asked how many parking slots are present in the parking lot.

Given I have started the parking lot com.vish.cucumber.app for the first time
* when I enter the number of parking slots
* I should get a success message.

Given I have started the parking lot com.vish.cucumber.app for the first time
* when I enter an invalid value
* I should get a failure message.

#### Story: Data Entry

As a parking  lot attendant
* I should be able to feed data into the system using either single commands or text files.
* So that the system is flexible.

**Acceptance Criteria**

When I run the application with file "input.txt"
* Then I should see output "in file mode"

When I run the application with zero arguments
* Then I should see output "in interactive mode"

####  The parking lot system should be able to allocate and free parking slots.

##### Story: allocate parking slot

As a parking lot attendant
* I should be able to request a parking ticket for a car based on its license plate and color
* So that the parking slot closest to the entry gets allocated to the car.

##### Story: all slots full

As a parking lot attendant
* I should be informed when all the parking spaces are full in the form of clear messages.

##### Story: free parking slot

As a parking lot attendant
* I should be able to free a vacated parking slot using its slot number
* So that this parking slot is now available for future cars.

#### The system should be able to query parking slots by license number, color or give overall status.


##### Story: find slots by color

As a parking lot attendant
* I should be able to find all parking slots occupied by cars with a particular color.
* So that I satisfy government regulations.

##### Story: find slot occupied by car

As a parking lot attendant
* I should be able to find the parking slot occupied by a car with a particular license number.
* So that I satisfy government regulations.

##### Story: find all slot status

As a parking lot attendant
* I should be able to query the system for current parking status
* So that I get a list of occupied parking slots and the details of the cars occupying them.
