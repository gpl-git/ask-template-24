
@predefined
Feature: ASK Predefined

  Background:
    Given I go to "login" page
    When I type "ask_instr@aol.com" as email
    And I type "ABC123" as password
    When I click button "Sign In"
    And I wait for 1 sec
    And I click "Quizzes" menu item
    And I wait for 1 sec
    When I click button "Create New Quiz"
    And I wait for 1 sec

  @predefined1
  Scenario: Quiz Demo
    When I type "Demo Quiz TA" as quiz title
    And I add a question
    And I select "Single" question in "Q1"
    When I type "Question 1" into question field of "Q1"
    And I type "Option 1" into "Option 1*" of "Q1"
    And I type "Option 2" into "Option 2*" of "Q1"
    When I select "Option 1*" as a correct option in "Q1"
    And I click button "Save"
    And I wait for 1 sec
    Then "Demo Quiz TA" should be displayed on the list of quizzes
    And I delete "Demo Quiz TA" from the list of quizzes


  @predefined2
  Scenario: Quiz - Total Questions
    When I type "Demo Quiz Total Questions" as quiz title
    And I add 5 Textual questions
    And I click button "Save"
    And I wait for 1 sec
    Then "Demo Quiz Total Questions" should be displayed on the list of quizzes
    And I delete "Demo Quiz Total Questions" from the list of quizzes

  @predefined3
  Scenario: Quiz - SC Question Number of Options
    When I type "Demo Quiz SC- Options" as quiz title
    And I add a question
    And I select "Single" question in "Q1"
    When I type "Question 1" into question field of "Q1"
    And I type "Option 1" into "Option 1*" of "Q1"
    And I type "Option 2" into "Option 2*" of "Q1"
    When I select "Option 1*" as a correct option in "Q1"
    When I add up to 5 options in "Q1"
    And I wait for 3 sec
    And I click button "Save"
    And I wait for 1 sec
    Then "Demo Quiz SC- Options" should be displayed on the list of quizzes
    And I delete "Demo Quiz SC- Options" from the list of quizzes

