package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.DriverFactory;

import java.math.BigDecimal;

public class SideTotalBoxPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BasePage basePage;

    private By currentTotalEmissionsValue = By.cssSelector("span.totalEmissions");
    private By newTotalEmissionsValue = By.cssSelector("span.newEmissionTotal");
    private By averageUSValue = By.cssSelector("span.aSideUSTotal");
    private By numberOfPeopleValue = By.cssSelector("span.number-of-people");
    private By usersZipCodeValue = By.cssSelector("span.users-zip");
    private By viewFullReportButton = By.cssSelector("button#view-full-report");
    private By startOverLink = By.cssSelector("a#start-over");


    public SideTotalBoxPage(WebDriver driver) {
        this.driver = driver;
        basePage = new BasePage(driver);
        wait = DriverFactory.getWait();
    }

    public String getAverageEmissionsValue() {
        return basePage.getTextFromLocator(averageUSValue);
    }

    public String getCurrentTotalEmissions() {
        return basePage.getTextFromLocator(currentTotalEmissionsValue);
    }

}
