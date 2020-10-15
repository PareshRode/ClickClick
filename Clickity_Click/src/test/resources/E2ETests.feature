Feature: End to End test cases for Clickity Click application
Description : This file contains test cases for Clickity Click application

		Background:  
		Given user is  on homepage
		
		Scenario: Dialog does not aapears if any non perimeter icon is selected
		When user selects any non perimeter icon 
		And  user selects all icons of outer perimeter
		Then dialog does not appear
		
		Scenario Outline: New grid size is displyed as per users valid input 
    When user selects all icons of outer perimeter
    Then dialog appears 
    When user enters grid size as "<Grid_Value>"
    Then new grid should be displyed of size "<Grid_Value>"
    Examples:
    |Grid_Value|
    |3|
    |4|
    |5|
    |6|
    |7|
    |8|
    |9|
    
    Scenario Outline: Error messege dialog is displyed as per users invalid input
    When user selects all icons of outer perimeter
    Then dialog appears 
    When user enters grid size as "<Grid_Value>"
    Then error dialog appears
    Then new grid should be displyed of default size for input "<Grid_Value>"
    Examples:
    |Grid_Value|
    |0|
    |1|
    |2|
    |10|
    |987|
    |AEDHS|
    |A12|
    |5fgh|
    |6.457|
    |+856|
    |00000006|
    
    
