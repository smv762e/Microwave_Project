Feature: Microwave closed with item

  Scenario: Open the door of a closed microwave
    Given A closed microwave with item
    When Open the door
    Then Door opens
    And Heating doesn't heats
    And Lamp turns on
    And Turntable doesn't turns
    
  Scenario Outline: Set power in a microwave
    Given A closed microwave with item
    When Set the power with <a> W
    Then Display shows "<b>"

    Examples: 
      | a   | b   |
      | -10 |   0 |
      |   0 |   0 |
      |  10 |  10 |
    
  Scenario: Reset power in a microwave
    Given A closed microwave with item
    When Reset the power
    Then Power goes to zero    
    
  Scenario Outline: Set timer in a microwave
    Given A closed microwave with item
    When Set the timer with <a> seconds
    Then Display shows "<b>"

    Examples: 
      | a   | b   |
      | -60 |   0 |
      |   0 |   0 |
      |  60 |  60 |
 
    
  Scenario: Reset timer in a microwave
    Given A closed microwave with item
    When Reset the timer
    Then Timer goes to zero

  Scenario: Trying to cook in a closed microwave with item
    Given A closed microwave with item
    When Set the power with 800 W 
    And Set the timer with 60 seconds
    And Set microwave to start cooking
    Then Microwave start cooking

    