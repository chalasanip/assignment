package StepDefinition;

import Utilities.TxtReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.util.List;

public class CarCheckResultsPage {

    TxtReader txtReader;
    WebDriver driver;

    public CarCheckResultsPage() {

    }

    @Given("^input txt file is  present to read the registrationNumbers$")
    public void inputTxtFile(){
      System.out.println("Input txt file is present to fetch registration numbers using regex");
    }

    @When("^user reads the registrationNumber from input file$")
    public List<String>readregistrationNumbersFromInput() throws IOException {
        txtReader = new TxtReader();
        return txtReader.readRegistrationNumbersUsingRegex();
    }

    @Then("^validates make,model,colour,year for all the registered cars against the output file$")
    public void checkOnWebsite() throws IOException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        txtReader = new TxtReader();
        List<String> registrationNumbers = readregistrationNumbersFromInput();
        for (String registrationNumber : registrationNumbers) {
            driver.get("https://cartaxcheck.co.uk/");
            driver.findElement(By.xpath("//input[@placeholder='Enter Registration']")).sendKeys(registrationNumber);
            driver.findElement(By.xpath("//button[contains(text(),'Free Car Check')]")).click();
            List<String> linesOutputFile = txtReader.readLines();
            for (String line : linesOutputFile) {
                if (!line.isEmpty()) {
                    String[] lineSplit = line.split(",");
                    if (lineSplit[0].toUpperCase().equals(registrationNumber.replaceAll(" ", "").toUpperCase())) {
                        // *****************************************************
                        //VALUES FROM APPLICATION
                        //*******************************************************
                        new WebDriverWait(driver, 3).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//dt[contains(text(),'Registration')]/following-sibling::dd")));
                        try {
                            String registrationUI = driver.findElement(By.xpath("//dt[contains(text(),'Registration')]/following-sibling::dd")).getText();
                            String makeUI = driver.findElement(By.xpath("//dt[contains(text(),'Make')]/following-sibling::dd")).getText();
                            String ModelUI = driver.findElement(By.xpath("//dt[contains(text(),'Model')]/following-sibling::dd")).getText();
                            String colourUI = driver.findElement(By.xpath("//dt[contains(text(),'Colour')]/following-sibling::dd")).getText();
                            String yearUI = driver.findElement(By.xpath("//dt[contains(text(),'Year')]/following-sibling::dd")).getText();

                            System.out.println("****************For Registration***********   ------" + registrationUI);
                            check("Registration", lineSplit[0], registrationUI, registrationUI);
                            check("Make", lineSplit[1], makeUI, registrationUI);
                            check("Model", lineSplit[2], ModelUI, registrationUI);
                            check("colour", lineSplit[3], colourUI, registrationUI);
                            check("year", lineSplit[4], yearUI, registrationUI);
                            System.out.println("********************************************");
                        }
                        catch (Exception e) {
                            System.out.println("Vehicle with RegistrationNumber----" + registrationNumber + "is not registered");
                        }

                    }
                    else if(!lineSplit[0].toUpperCase().equals(registrationNumber.replaceAll(" ", "").toUpperCase())){
                        try{
                            new WebDriverWait(driver, 3).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Please check you entered the registration correctly and try again.')]")));
                            driver.findElement(By.xpath("//*[contains(text(),'Please check you entered the registration correctly and try again.')]")).isDisplayed();
                            System.out.println("Vehicle with RegistrationNumber----" + registrationNumber + "---is not registered");
                        }
                        catch(Exception e) {
                            System.out.println("");
                        }
                    }
                }
            }
        }
        driver.quit();
    }

    public static void check(String property, String fileValue, String UIValue, String Registration) {
        if (fileValue.toUpperCase().equals(UIValue.toUpperCase())) {
            System.out.println("Values are matching for property----" + property + "---- for RegistrationNumber--" + Registration);

        } else {
            System.out.println("Values are not matching for property----" + property + "---- for RegistrationNumber--" + Registration);
        }
    }

}
