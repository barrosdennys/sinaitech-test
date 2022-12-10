package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.DriverFactory;

import java.math.BigDecimal;

/**
 * Class created to map all locators from the Side Box and to group the methods related to it
 *
 * @author Dennys Barros
 */
public class SideTotalBoxPage {
    private final BasePage basePage;

    private final By currentTotalEmissionsValue = By.cssSelector("span.totalEmissions");
    private final By newTotalEmissionsValue = By.cssSelector("span.newEmissionTotal");
    private final By averageUSValue = By.cssSelector("span.aSideUSTotal");
    private final By numberOfPeopleValue = By.cssSelector("span.number-of-people");
    private final By usersZipCodeValue = By.cssSelector("span.users-zip");
    private final By viewFullReportButton = By.cssSelector("button#view-full-report");
    private final By startOverLink = By.cssSelector("a#start-over");

    public SideTotalBoxPage(WebDriver driver) {
        basePage = new BasePage(driver);
    }

    public String getAverageEmissionsValue() {
        return basePage.getTextFromLocator(averageUSValue);
    }

    public String getCurrentTotalEmissions() {
        return basePage.getTextFromLocator(currentTotalEmissionsValue);
    }

    public void viewReport() {
        basePage.clickOnElement(viewFullReportButton);
    }

}
