Feature: Verify all the attributes of car

  Scenario: Reading car data from input file and validating with application

    Given input txt file is  present to read the registrationNumbers
    When user reads the registrationNumber from input file
    Then validates make,model,colour,year for all the registered cars against the output file