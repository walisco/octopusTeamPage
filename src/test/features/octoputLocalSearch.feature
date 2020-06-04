Feature: Octopus investment platform
  As a user I want to interract with the investment platform
  in order to be able to find investment advise

Background:
Given I am on the investment page

Scenario: local contact search
Given I click on the modal popup as an "Adviser"
And I confirm "Adviser" selection
When I can see text for Adviser "We’re here to support you" on page
#    And I click on "Find your local contact"
#    And I search postcode "abc"
#    Then I see search result as 'Displaying results for "abc"'