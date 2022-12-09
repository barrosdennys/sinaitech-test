package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

/**
 *
 * Class created to map all locators from the Reports page and to group the methods related to it
 *
 * @author Dennys Barros
 *
 */
public class ReportPage {

    private final BasePage basePage;
    private final By currentTotalValue = By.cssSelector("div#current-total div.totals-val");
    private final By newTotalValue = By.cssSelector("div#new-total div.totals-val");
    private final By AverageValueUS = By.cssSelector("div#us-avg div.totals-val");
    private final By shareItButton = By.cssSelector("button#openSharePanel");
    private final By goodWorkMessage = By.cssSelector("div#good-work");

    public ReportPage(WebDriver driver) {
        basePage = new BasePage(driver);
    }

    public boolean checkGoodWorkMessageVisibility(){
        return basePage.isElementVisible(goodWorkMessage);
    }
}
