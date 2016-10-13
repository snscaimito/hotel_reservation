Feature: Reserve a room

Scenario: Try a reservation
	When I request a reservation from "2016-10-12" to "2016-10-15"
	Then it is confirmed
	