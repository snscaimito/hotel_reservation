Feature: Reserve a room

Scenario: No rooms, reservation not possible
	When I request a reservation from "2016-10-12" to "2016-10-15"
	Then it is not confirmed
	