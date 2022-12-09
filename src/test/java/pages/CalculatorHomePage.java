package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

/**
 *
 * Class created to map all locators from the Calculator Home page and to group the methods related to it
 *
 * @author Dennys Barros
 *
 */
public class CalculatorHomePage {
    private final BasePage basePage;
    private final By householdNumberInput = By.cssSelector("input#ppl-in-household-input");
    private final By zipCodeInput = By.cssSelector("input#zip-code-input");
    private final By getStartedButton = By.cssSelector("button#get-started");

    public CalculatorHomePage(WebDriver driver) {
        basePage = new BasePage(driver);
    }

    public void fillCarbonFootprintValues (String householdNumber, String zipCode) {
        basePage.sendKeysToElement(householdNumberInput, householdNumber);
        basePage.sendKeysToElement(zipCodeInput, zipCode);
        basePage.clickOnElement(getStartedButton);
    }
}
