# HO Assignment
Write a test automation suite which does following.
1. Reads given input file: car_input.txt
2. Extracts vehicle registration numbers based on pattern(s).
3. Each number extracted from input file is fed to https://cartaxcheck.co.uk/
(Peform Free Car Check)
4. Compare the output returned by https://cartaxcheck.co.uk/ with given
car_output.txt
5. Highlight/fail the test for any mismatches.
Showcase your skills so its easier to add more input files in future.
Utilise any JVM based language with broser automation tools.
Use design patterns where appropriate.

# Steps to run the code/tests
- git clone https://github.com/chalasanip/assignment.git
- Change directory to root folder - where ever  pom.xml is present
- run the following command - "mvn clean test"
- To view the result navigate to target folder and open report.html file in a browser.
