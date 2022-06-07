Feature: Microwave cooking

  Scenario: Start cooking in a closed microwave with item
    Given A closed microwave with item
    When Set the power with 800 W 
    And Set the timer with 60 seconds
    And Set microwave to start cooking
    Then Microwave start cooking
    And Heating heats
    And Lamp turns on
    And Turntable turns
    
  Scenario: Increase power while cooking
    Given A cooking microwave with 200 power and 60 timer
    When Increase the power
    Then Display shows "201"

  Scenario: Decrease power while cooking
    Given A cooking microwave with 200 power and 60 timer
    When Decrease the power
    Then Display shows "199"  
    
  Scenario: End cooking resetting the power
    Given A cooking microwave with 200 power and 60 timer
    When Reset the power
    Then Microwave not cooking
		And Heating doesn't heats
    And Lamp turns off
    And Turntable doesn't turns
    And Display shows "0"
    
  Scenario: Increase time while cooking
    Given A cooking microwave with 200 power and 60 timer
    When Increase the timer
    Then Display shows "61"
    And Timer has 61 seconds

  Scenario: Decrease time while cooking
    Given A cooking microwave with 200 power and 60 timer
    When Decrease the timer
    Then Display shows "59"
    And Timer has 59 seconds
   
  Scenario: End cooking resetting the timer
    Given A cooking microwave with 200 power and 60 timer
    When Reset the timer
    Then Microwave not cooking
		And Heating doesn't heats
    And Lamp turns off
    And Turntable doesn't turns
    And Display shows "0"
    
  Scenario: Stop cooking opening the door
    Given A cooking microwave with 200 power and 60 timer
    When Open the door
    Then Door opens
    And Microwave not cooking
		And Heating doesn't heats
    And Lamp turns on
    And Turntable doesn't turns

  Scenario Outline: Cooking time finishes correctly
    Given A closed microwave with item
    When Set the power with <a> W 
    And Set the timer with <b> seconds
    And Set microwave to start cooking
    Then Microwave start cooking
    And Timer has <c> seconds
    Then Microwave not cooking
    And Heating doesn't heats
    And Lamp turns off
    And Turntable doesn't turns
    And Beeper sounds 3 times
    And Display shows "Item ready"

    Examples: 
      | a   | b   | c   |
      | 100 |  30 |  30 |
      | 200 |  60 |  60 |
      | 500 | 120 | 120 |

      
 