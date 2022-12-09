package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.DriverFactory;

/**
 *
 * Class created to map all locators from Transportation page and to group the methods related to it
 *
 *
 */
public class TransportationPage {
    private final BasePage basePage;
    private final By transportationBanner = By.cssSelector("div#transportation");
    private final By numVehiclesSelect = By.cssSelector("select#numVehiclesInput");
    private final By maintenanceCurrentSelect = By.cssSelector("select#maintCurrentSelect");
    private final By vehicle1Miles = By.cssSelector("input#vehicle1Miles");
    private final By vehicle1Select = By.cssSelector("select#vehicle1Select");
    private final By vehicle1GasMileage = By.cssSelector("input#vehicle1GasMileage");
    private final By vehicle1Co2 = By.cssSelector("span.vehicle1Co2");
    private final By vehicle2Miles = By.cssSelector("input#vehicle2Miles");
    private final By vehicle2Select = By.cssSelector("select#vehicle2Select");
    private final By vehicle2GasMileage = By.cssSelector("input#vehicle2GasMileage");
    private final By vehicle2Co2 = By.cssSelector("span.vehicle2Co2");
    private final By maintenanceReduceSelect = By.cssSelector("select#maintReduceSelect");
    private final By reduceMilesInput1 = By.cssSelector("input#reduceMilesInput1");
    private final By reduceMilesSelect1 = By.cssSelector("select#reduceMilesSelect1");
    private final By replaceVehicleInput1 = By.cssSelector("input#replaceVehicleInput1");
    private final By reduceMilesInput2 = By.cssSelector("input#reduceMilesInput2");
    private final By reduceMilesSelect2 = By.cssSelector("select#reduceMilesSelect2");
    private final By replaceVehicleInput2 = By.cssSelector("input#replaceVehicleInput2");
    private final By continueToWasteButton = By.cssSelector("button#to-waste");
    private final By backToHomeEnergyButton = By.cssSelector("button#back-to-home-energy");
    private final By vehicle1Table = By.cssSelector("table#revVehicle1");
    private final By vehicle2Table = By.cssSelector("table#revVehicle2");
    private final By errorMessageCompleteVehicleAbove = By.cssSelector("li#error-vehicle-reduction");

    public TransportationPage(WebDriver driver) {
        basePage = new BasePage(driver);
    }

    public void goToTransportationSection() {
        basePage.clickOnElement(transportationBanner);
    }

    public void setNumberOfVehicles(String number) {
        basePage.selectElementByText(numVehiclesSelect, number);
    }

    /**
     *
     * Method created to fill the form for the Vehicle 1 inside the Transportation section
     * 
     * @param avgMiles string representing the average miles the vehicle travel per a period of time
     * @param period string representing the period of time the avgMiles is referring to
     * @param avgGasMileage string representing the average gas mileage the car is performing
     *                      
     * @author Dennys Barros
     *
     */
    public void fillVehicle1CurrentData(String avgMiles, String period, String avgGasMileage) {
        basePage.sendKeysToElement(vehicle1Miles, avgMiles);
        basePage.selectElementByText(vehicle1Select, period);
        basePage.sendKeysToElement(vehicle1GasMileage, avgGasMileage);
    }

    public void fillVehicle2CurrentData(String avgMiles, String period, String avgGasMileage) {
        basePage.sendKeysToElement(vehicle2Miles, avgMiles);
        basePage.selectElementByText(vehicle2Select, period);
        basePage.sendKeysToElement(vehicle2GasMileage, avgGasMileage);
    }

    /**
     *
     * Method created to select if the car is currently going to maintenance or not
     *
     * @param value string representing if the car is being maintained or not.
     *             It accepts "Already Done" or "Do Not Do" values
     *              
     * @author Dennys Barros
     *
     */
    public void selectCurrentMaintenance(String value) {
        basePage.selectElementByText(maintenanceCurrentSelect, value);
    }

    public void selectReduceMaintenance(String value) {
        basePage.selectElementByText(maintenanceReduceSelect, value);
    }

    /**
     *
     * Method created to fill the form for the Vehicle 1 inside the Reduce Your Emission 
     * block inside Transportation section
     *
     * @param milesToBeReduced string representing the miles that may be reduced
     * @param period string representing the period of time the milesToBeReduced is referring to
     * @param avgGasMileage string representing the average gas mileage the car is performing
     *
     * @author Dennys Barros
     *
     */
    public void fillVehicle1ReduceData(String milesToBeReduced, String period, String avgGasMileage) {
        basePage.sendKeysToElement(reduceMilesInput1, milesToBeReduced);
        basePage.selectElementByText(reduceMilesInput1, period);
        basePage.sendKeysToElement(reduceMilesInput1, avgGasMileage);
    }

    /**
     *
     * Method created to fill the form for the Vehicle 1 inside the Reduce Your Emission
     *
     * @param milesToBeReduced string representing the miles that may be reduced
     *
     * @author Dennys Barros
     *
     */
    public void fillVehicle1ReduceData(String milesToBeReduced) {
        basePage.sendKeysToElement(reduceMilesInput1, milesToBeReduced);
    }

    public void fillVehicle2ReduceData(String milesToBeReduced, String period, String avgGasMileage) {
        basePage.sendKeysToElement(reduceMilesInput2, milesToBeReduced);
        basePage.selectElementByText(reduceMilesSelect2, period);
        basePage.sendKeysToElement(replaceVehicleInput2, avgGasMileage);
    }

    public void backToHomeEnergy() {
        basePage.clickOnElement(backToHomeEnergyButton);
    }

    public void continueToWaste() {
        basePage.clickOnElement(continueToWasteButton);
    }

    public boolean checkVehicle1TableVisibility() {
        return basePage.isElementVisible(vehicle1Table);
    }

    public boolean checkVehicle2TableVisibility() {
        return basePage.isElementVisible(vehicle2Table);
    }

    public boolean checkReduceMaintenanceVisibility() {
        return basePage.isElementVisible(maintenanceReduceSelect);
    }

    public boolean checkCompleteVehicleEntriesAboveFirstErrorMessageVisibility() {
        return basePage.isElementVisible(errorMessageCompleteVehicleAbove);
    }

}
