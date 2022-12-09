package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.DriverFactory;

public class CFCMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BasePage basePage;
    private final By householdNumberInput = By.cssSelector("input#ppl-in-household-input");
    private final By zipCodeInput = By.cssSelector("input#zip-code-input");
    private final By getStartedButton = By.cssSelector("button#get-started");

    public CFCMainPage(WebDriver driver) {
        this.driver = driver;
        basePage = new BasePage(driver);
        wait = DriverFactory.getWait();
    }
    public void fillCarbonFootprintValues (String householdNumber, String zipCode) {
        basePage.sendKeysToElement(householdNumberInput, householdNumber);
        basePage.sendKeysToElement(zipCodeInput, zipCode);
        basePage.clickOnElement(getStartedButton);
    }

}
