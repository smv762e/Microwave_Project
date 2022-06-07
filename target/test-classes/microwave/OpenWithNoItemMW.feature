Feature: Microwave opened with no item

  Scenario: Close an opened microwave
    Given An opened microwave with no item
    When Close the door
    Then Door closes
    And Heating doesn't heats
    And Lamp turns off
    And Turntable doesn't turns

  Scenario: Placing item in microwave
    Given An opened microwave with no item
    When Place an item
    Then Microwave has an item

  Scenario Outline: Set power in a microwave
    Given An opened microwave with no item
    When Set the power with <a> W
    Then Display shows "<b>"

    Examples: 
      | a   | b   |
      | -10 |   0 |
      |   0 |   0 |
      |  10 |  10 |
    
  Scenario: Reset power in a microwave
    Given An opened microwave with no item
    When Reset the power
    Then Power goes to zero    
    
  Scenario Outline: Set timer in a microwave
    Given An opened microwave with no item
    When Set the timer with <a> seconds
    Then Display shows "<b>"

    Examples: 
      | a   | b   |
      | -60 |   0 |
      |   0 |   0 |
      |  60 |  60 |
 
    
  Scenario: Reset timer in a microwave
    Given An opened microwave with no item
    When Reset the timer
    Then Timer goes to zero

  Scenario: Trying to cook in an opened microwave with no item
    Given An opened microwave with no item
    When Set the power with 800 W 
    And Set the timer with 60 seconds
    And Set microwave to start cooking
    Then Microwave doesn't start cooking

    
