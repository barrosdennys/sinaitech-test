package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.DriverFactory;
import utils.ResourcesParser;

/**
 *
 * Class created to map all locators from the HomeEnergy page and to group the methods related to it
 *
 * @author Dennys Barros
 *
 */
public class HomeEnergyPage {
    private final BasePage basePage;

    private final By primaryHeatingSource = By.cssSelector("select#primaryHeatingSource");
    private final By naturalGasTextInput = By.cssSelector("input#naturalGasTextInput");
    private final By naturalGasSelectInput = By.cssSelector("select#naturalGasSelectInput");
    private final By electricityTextInput = By.cssSelector("input#electricityTextInput");
    private final By electricitySelectInput = By.cssSelector("select#electricitySelectInput");
    private final By electricityGreenInput = By.cssSelector("input#electricityGreenTextInput");
    private final By fuelTextInput = By.cssSelector("input#fuelTextInput");
    private final By fuelSelectInput = By.cssSelector("select#fuelSelectInput");
    private final By propaneTextInput = By.cssSelector("input#propaneTextInput");
    private final By propaneSelectInput = By.cssSelector("select#propaneSelectInput");
    private final By reduceACInput = By.cssSelector("input#energyAC");
    private final By reduceHeatInput = By.cssSelector("input#energyHeat");
    private final By lightsToReplaceInput = By.cssSelector("input#lightsToReplace");
    private final By powerManagementSelect = By.cssSelector("select#pwrMgmtSelect");
    private final By increaseGreenInput = By.cssSelector("input#increaseGreenInput");
    private final By coldWaterSelect = By.cssSelector("select#coldWaterSelect");
    private final By loadsPerWeek = By.cssSelector("input#loadsPerWeek");
    private final By airDrySelect = By.cssSelector("select#AirDrySelect");
    private final By percentageAirDrySelect = By.cssSelector("select#percentageAirDrySelect");
    private final By energyStartFridgeSelect = By.cssSelector("select#fridgeSelect");
    private final By energyStartFurnaceSelect = By.cssSelector("select#furnaceSelect");
    private final By energyStartWindowSelect = By.cssSelector("select#windowSelect");
    private final By continueToTransportationButton = By.cssSelector("button#to-transportation");
    private final By naturalGasCalculationValue = By.cssSelector("li.naturalGas .green-lb-total span");
    private final By electricityCalculationValue = By.cssSelector("li.electricity .green-lb-total span");
    private final By fuelOilCalculationValue = By.cssSelector("li.fuel .green-lb-total span");
    private final By propaneCalculationValue = By.cssSelector("li.propane .green-lb-total span");


    public HomeEnergyPage(WebDriver driver) {
        basePage = new BasePage(driver);
    }
    
    public void selectPrimaryHeatingSource(String heating){
        basePage.selectElementByText(primaryHeatingSource, heating);
    }

    public void fillNaturalGasMonthlyBill(String avgValue, String measure) {
        basePage.sendKeysToElement(naturalGasTextInput, avgValue);
        basePage.selectElementByText(naturalGasSelectInput, measure);
    }

    public void fillElectricityMonthlyBill(String avgValue, String measure, String greenPercentage) {
        basePage.sendKeysToElement(electricityTextInput, avgValue);
        basePage.selectElementByText(electricitySelectInput, measure);
        basePage.sendKeysToElement(electricityGreenInput, greenPercentage);
    }

    public void fillFuelOilMonthlyBill(String avgValue, String measure) {
        basePage.sendKeysToElement(fuelTextInput, avgValue);
        basePage.selectElementByText(fuelSelectInput, measure);
    }

    public void fillPropaneMonthlyBill(String avgValue, String measure) {
        basePage.sendKeysToElement(propaneTextInput, avgValue);
        basePage.selectElementByText(propaneSelectInput, measure);
    }

    public void fillHeatingAndCoolingSection(String turnUpThermostatBy, String turnDownThermostatBy) {
        basePage.sendKeysToElement(reduceACInput, turnUpThermostatBy);
        basePage.sendKeysToElement(reduceHeatInput, turnDownThermostatBy);
    }

    public void fillEnergyStarLightsNumber(String starLightNumber) {
        basePage.sendKeysToElement(lightsToReplaceInput, starLightNumber);
    }

    public void fillPowerSourceAndSettingsSection (String enablePowerManagement, String increaseGreenPower) {
        basePage.selectElementByText(powerManagementSelect, enablePowerManagement);
        basePage.sendKeysToElement(increaseGreenInput, increaseGreenPower);
    }

    public void fillWashingAndDrying(String coldWater, String loadsPerWeekNumber, String useDryingRack, String useDryingRackNumber) {
        basePage.selectElementByText(coldWaterSelect, coldWater);
        basePage.sendKeysToElement(loadsPerWeek, loadsPerWeekNumber);
        basePage.selectElementByText(airDrySelect, useDryingRack);
        basePage.sendKeysToElement(percentageAirDrySelect, useDryingRackNumber);
    }

    public void fillEnergyStarSection (String refrigerator, String furnace, String windows) {
        basePage.selectElementByText(energyStartFridgeSelect, refrigerator);
        basePage.selectElementByText(energyStartFurnaceSelect, furnace);
        basePage.selectElementByText(energyStartWindowSelect, windows);
    }

    public void continueToTransportation(){
        basePage.clickOnElement(continueToTransportationButton);
    }

    public String getNaturalGasCalculationValue(){
        return basePage.getTextFromLocator(naturalGasCalculationValue);
    }

    public String getElectricityCalculationValue(){
        return basePage.getTextFromLocator(electricityCalculationValue);
    }

    public String getPropaneCalculationValue(){
        return basePage.getTextFromLocator(propaneCalculationValue);
    }

    public String getFuelOilCalculationValue(){
        return basePage.getTextFromLocator(fuelOilCalculationValue);
    }

}
