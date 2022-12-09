package tests;

import calculations.Calculations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.BasePage;
import utils.DriverFactory;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class CFCTest {

    private final WebDriver driver = DriverFactory.getDriver();
    private final CFCMainPage cfcMainPage = new CFCMainPage(driver);
    private final WastePage wastePage = new WastePage(driver);
    private final SideTotalBoxPage sideTotalBoxPage = new SideTotalBoxPage(driver);
    private final HomeEnergyPage homeEnergyPage = new HomeEnergyPage(driver);
    private final TransportationPage transportationPage = new TransportationPage(driver);
    private final Calculations calculations = new Calculations();
    private final Utils utils = new Utils();

    @BeforeEach
    public void setUp() {
        driver.get("https://www3.epa.gov/carbon-footprint-calculator/");
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void wasteAverageTest() {
        cfcMainPage.fillCarbonFootprintValues("5", "00006");

        wastePage.goToWasteSection();

        double wasteAverageCalculation = calculations.calculateWasteAverage(5);

        String expectedResult = utils.roundFormatNumberAndConvertToString(wasteAverageCalculation);
        Assertions.assertEquals(expectedResult, sideTotalBoxPage.getAverageEmissionsValue(), "Average calculation number is not correct");
    }

    @Test
    public void naturalGasEmissionCalculationTest() {
        cfcMainPage.fillCarbonFootprintValues("1", "00006");
        homeEnergyPage.selectPrimaryHeatingSource("Natural Gas");
        homeEnergyPage.fillNaturalGasMonthlyBill("23", "Dollars");

        double naturalGasCalculation = calculations.calculateNaturalGasEmission(Integer.parseInt("23"), "Dollars");

        String expectedResult = utils.roundFormatNumberAndConvertToString(naturalGasCalculation);
        Assertions.assertEquals(expectedResult, homeEnergyPage.getNaturalGasCalculationValue());
    }

    @Test
    public void electricityEmissionCalculationTest() {
        String zipCode = "00006";
        String avgValue = "250";
        String percentage = "10";

        cfcMainPage.fillCarbonFootprintValues("1", zipCode);
        homeEnergyPage.selectPrimaryHeatingSource("Electricity");
        homeEnergyPage.fillElectricityMonthlyBill(avgValue, "Dollars", percentage);

        double electricityEmissionCalculation = calculations.calculateElectricityEmission(zipCode, Double.parseDouble(avgValue), "Dollars", Double.valueOf(percentage));

        String expectedResult = utils.roundFormatNumberAndConvertToString(electricityEmissionCalculation);
        Assertions.assertEquals(expectedResult, homeEnergyPage.getElectricityCalculationValue());
    }

    @Test
    public void fuelOilEmissionCalculationTest() {
        String zipCode = "00006";
        String avgValue = "80";

        cfcMainPage.fillCarbonFootprintValues("1", zipCode);
        homeEnergyPage.selectPrimaryHeatingSource("Fuel Oil");
        homeEnergyPage.fillFuelOilMonthlyBill(avgValue, "Dollars");

        double fuelOilEmissionCalculation = calculations.calculateFuelOilEmission(Double.parseDouble(avgValue), "Dollars");

        String expectedResult = utils.roundFormatNumberAndConvertToString(fuelOilEmissionCalculation);
        Assertions.assertEquals(expectedResult, homeEnergyPage.getFuelOilCalculationValue());
    }

    @Test
    public void propaneEmissionCalculationTest() {
        String zipCode = "00006";
        String avgValue = "37";

        cfcMainPage.fillCarbonFootprintValues("1", zipCode);
        homeEnergyPage.selectPrimaryHeatingSource("Propane");
        homeEnergyPage.fillPropaneMonthlyBill(avgValue, "Dollars");

        String expectedResult = utils.roundFormatNumberAndConvertToString(calculations.calculatePropaneEmission(Double.parseDouble(avgValue), "Dollars"));
        Assertions.assertEquals(expectedResult, homeEnergyPage.getPropaneCalculationValue());
    }

    @Test
    public void homeEnergyDollarsCalculationTest() {
        int electricityPercentage = 0;
        String zipCode = "00006";
        String fuelOilMonthly = "80";
        String propaneMonthly = "37";
        String electricityMonthly = "250";
        String naturalGasMonthly = "24";

        cfcMainPage.fillCarbonFootprintValues("1", zipCode);

        homeEnergyPage.selectPrimaryHeatingSource("Electricity");
        homeEnergyPage.fillFuelOilMonthlyBill(fuelOilMonthly, "Dollars");
        homeEnergyPage.fillPropaneMonthlyBill(propaneMonthly, "Dollars");
        homeEnergyPage.fillElectricityMonthlyBill(electricityMonthly, "Dollars", "0");
        homeEnergyPage.fillNaturalGasMonthlyBill(naturalGasMonthly, "Dollars");

        double fuelAvgValue = calculations.calculateFuelOilEmission(Double.parseDouble(fuelOilMonthly), "Dollars");
        double propaneAvgValue = calculations.calculatePropaneEmission(Double.parseDouble(propaneMonthly), "Dollars");
        double electricityAvgValue = calculations.calculateElectricityEmission(zipCode, Double.parseDouble(electricityMonthly), "Dollars", electricityPercentage);
        double naturalGasAvgValue = calculations.calculateNaturalGasEmission(Double.parseDouble(naturalGasMonthly), "Dollars");
        double finalNumber = fuelAvgValue + propaneAvgValue + electricityAvgValue + naturalGasAvgValue;

        String expectedResult = utils.roundFormatNumberAndConvertToString(finalNumber);
        Assertions.assertEquals(expectedResult, sideTotalBoxPage.getCurrentTotalEmissions());
    }

    @Test
    public void carFormUpdatesAccordingToCarNumberTest() {
        cfcMainPage.fillCarbonFootprintValues("5", "00002");
        transportationPage.goToTransportationSection();
        transportationPage.setNumberOfVehicles("2");

        Assertions.assertTrue(transportationPage.checkVehicle2TableVisibility());

        transportationPage.setNumberOfVehicles("0");

        Assertions.assertFalse(transportationPage.checkVehicle1TableVisibility());
        Assertions.assertFalse(transportationPage.checkVehicle2TableVisibility());
    }

    @Test
    public void reduceEmissionMaintUpdatesAccordingToCurrentMaintTest() {
        cfcMainPage.fillCarbonFootprintValues("5", "00002");
        transportationPage.goToTransportationSection();
        transportationPage.setNumberOfVehicles("1");

        transportationPage.selectCurrentMaintenance("Already Done");

        Assertions.assertFalse(transportationPage.checkReduceMaintenanceVisibility());
    }

    /**
     * Test to check if an error message dialog is displayed when the user tries to add data into the
     * Reduce Your Emissions section on Transportation before filling the data from the Current Emissions section
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void checkCompleteVehicleEntriesAboveFirstErrorMessageTest() {
        cfcMainPage.fillCarbonFootprintValues("5", "00002");
        transportationPage.goToTransportationSection();
        transportationPage.setNumberOfVehicles("2");

        transportationPage.fillVehicle1ReduceData("1");

        Assertions.assertTrue(transportationPage.checkCompleteVehicleEntriesAboveFirstErrorMessageVisibility());
    }

    /**
     * Test to check if Reduce Your Emissions block from the Waste section updates according to the
     * selections from the Current Emissions block. If a product is selected on Current Emissions block
     * the same product must disappear on the Reduce Your Emissions block.
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void checkReduceWasteSectionUpdatesAccordingToTheCurrentWasterEmissionsTest(){
        cfcMainPage.fillCarbonFootprintValues("5", "00002");
        wastePage.goToWasteSection();

        wastePage.clickOnProductFromCurrentEmission("plastic");
        wastePage.clickOnProductFromCurrentEmission("newspaper");

        Assertions.assertFalse(wastePage.checkProductFromReduceEmissionVisibility("plastic"));
        Assertions.assertFalse(wastePage.checkProductFromReduceEmissionVisibility("newspaper"));

        wastePage.clickOnProductFromCurrentEmission("plastic");
        wastePage.clickOnProductFromCurrentEmission("newspaper");

        Assertions.assertTrue(wastePage.checkProductFromReduceEmissionVisibility("plastic"));
        Assertions.assertTrue(wastePage.checkProductFromReduceEmissionVisibility("newspaper"));
    }

    public void startOverTest() {
        cfcMainPage.fillCarbonFootprintValues("5", "00002");
        homeEnergyPage.selectPrimaryHeatingSource("Electricity");
        homeEnergyPage.fillFuelOilMonthlyBill("200", "Dollars");
        homeEnergyPage.fillElectricityMonthlyBill("200", "kWh", "0");
        homeEnergyPage.fillPropaneMonthlyBill("300", "Gallons");
        homeEnergyPage.fillNaturalGasMonthlyBill("1", "Therms");

        homeEnergyPage.fillHeatingAndCoolingSection("2", "2");
        homeEnergyPage.fillEnergyStarLightsNumber("2");
        homeEnergyPage.fillPowerSourceAndSettingsSection("Will Do", "20");
        homeEnergyPage.fillWashingAndDrying("Will Not Do", "1", "Already Done", "50% of my Laundry");
        homeEnergyPage.fillEnergyStarSection("Will Do", "Will Do", "Already Done");

        homeEnergyPage.continueToTransportation();

        transportationPage.setNumberOfVehicles("2");
        transportationPage.selectCurrentMaintenance("Already Done");

        transportationPage.fillVehicle1CurrentData("240", "Per Week", "21.6");
        transportationPage.fillVehicle2CurrentData("240", "Per Week", "21.6");

        transportationPage.fillVehicle1ReduceData("40", "Per Week", "22");
        transportationPage.fillVehicle2ReduceData("40", "Per Week", "22");

        transportationPage.continueToWaste();

        wastePage.clickOnProductFromCurrentEmission("plastic");

        wastePage.clickOnProductFromReduceEmission("newspaper");

        wastePage.continueToReport();

    }
}
