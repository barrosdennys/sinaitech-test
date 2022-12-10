package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.DriverFactory;

/**
 * Class created to map all locators from Waste page and to group the methods related to it
 *
 * @author Dennys Barros
 */
public class WastePage {
    private final BasePage basePage;
    private final By wasteBanner = By.cssSelector("div#waste");
    private final By userWasteCurrent = By.cssSelector("span#userWasteCurrent");
    private final By wastWillSave = By.cssSelector("span.wasteWillSave");
    private final By continueToReportButton = By.cssSelector("button#to-report");

    public WastePage(WebDriver driver) {
        basePage = new BasePage(driver);
    }

    public void goToWasteSection() {
        basePage.clickOnElement(wasteBanner);
    }

    /**
     * Method created to click on the product that it is being recycled in the Current Emission section
     *
     * @param product string representing the product to be selected in the checkbox
     * @author Dennys Barros
     */
    public void clickOnProductFromCurrentEmission(String product) {
        By productCheckbox = By.xpath("//div[@id='waste-emissions']//label[contains(text(), '" + product + "')]");
        basePage.clickOnElement(productCheckbox);
    }

    /**
     * Method created to click on the product that it is being recycled in the Reduce Emission section
     *
     * @param product string representing the product to be selected in the checkbox
     * @author Dennys Barros
     */
    public void clickOnProductFromReduceEmission(String product) {
        By productCheckbox = By.xpath("//div[@id='waste-reduction']//label[contains(text(), '" + product + "')]");
        basePage.clickOnElement(productCheckbox);
    }

    /**
     * Method created to check if the product in the Reduce Your Emission section is visible in the UI
     *
     * @param product string representing the product to be selected in the checkbox
     * @author Dennys Barros
     */
    public boolean checkProductFromReduceEmissionVisibility(String product) {
        By productCheckbox = By.xpath("//div[@id='waste-reduction']//label[contains(text(), '" + product + "')]");
        return basePage.isElementVisible(productCheckbox);
    }

    public void continueToReport() {
        basePage.clickOnElement(continueToReportButton);
    }
}
