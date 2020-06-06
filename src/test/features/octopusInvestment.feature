Feature: Octopus investment platform
  As a user I want to interract with the investment platform
  in order to be able to find investment advise

  Background:
    Given I am on the investment page

  Scenario:leadership titles
    Given I click on the modal popup as an "investor"
    And I confirm "investor" selection
    Then I can see the following chief officers:
      | Name            | Title                    |
      | Ruth Handcock   | Chief Executive Officer  |
      | Jonathan Digges | Chief Investment Officer |
      | Jonathan Dees   | Chief Operations Officer |
    And I can see text for Investor "Contact our team" on page
    And I click About us I can see
      | 2000 Octopus was founded | £8.6bn worth of funds under management*† |






