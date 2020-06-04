Feature: Octopus investment platform
  As a user I want to interract with the investment platform
  in order to be able to find investment advise

  Background:
    Given I am on the investment page

  Scenario:leadership titles
    Given I click on the modal popup as an "investor" and confirm selection
    Then I can see the following chief officers:
      | Ruth Handcock   | Chief Executive Officer  |
      | Jonathan Digges | Chief Investment Officer |
      | Jonathan Dees   | Chief Operations Officer |
    And I can see text for Investor "Contact our team" on page

  Scenario: local contact search
    Given I click on the modal popup as an "Adviser" and confirm selection
    When I can see text for Adviser "We’re here to support you" on page
    And I click on "Find your local contact"
    And I search postcode "abc"
    Then I see search result as 'Displaying results for "abc"'






