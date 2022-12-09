package tests;

import calculations.Calculations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverFactory;
import utils.ResourcesParser;
import utils.Utils;

public class HomeEnergyTest {

    private final WebDriver driver = DriverFactory.getDriver();
    private final CalculatorHomePage calculatorHomePage = new CalculatorHomePage(driver);
    private final SideTotalBoxPage sideTotalBoxPage = new SideTotalBoxPage(driver);
    private final HomeEnergyPage homeEnergyPage = new HomeEnergyPage(driver);
    private final Calculations calculations = new Calculations();
    private final ResourcesParser resourcesParser = new ResourcesParser();
    private final Utils utils = new Utils();


    @BeforeEach
    public void setUp() {
        driver.get(resourcesParser.getAppProperty("cfc_prod_url"));
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    /**
     *
     * Test to check if the Gas Emission value is being correctly calculated.
     * @see Calculations#calculateNaturalGasEmission
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void naturalGasEmissionCalculationTest() {
        calculatorHomePage.fillCarbonFootprintValues("1", "00006");
        homeEnergyPage.selectPrimaryHeatingSource("Natural Gas");
        homeEnergyPage.fillNaturalGasMonthlyBill("23", "Dollars");

        double naturalGasCalculation = calculations.calculateNaturalGasEmission(Integer.parseInt("23"), "Dollars");

        String expectedResult = utils.roundFormatNumberAndConvertToString(naturalGasCalculation);
        Assertions.assertEquals(expectedResult, homeEnergyPage.getNaturalGasCalculationValue());
    }

    /**
     *
     * Test to check if the Electricity value is being correctly calculated.
     * @see Calculations#calculateElectricityEmission
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void electricityEmissionCalculationTest() {
        String zipCode = "00006";
        String avgValue = "250";
        String percentage = "10";

        calculatorHomePage.fillCarbonFootprintValues("1", zipCode);
        homeEnergyPage.selectPrimaryHeatingSource("Electricity");
        homeEnergyPage.fillElectricityMonthlyBill(avgValue, "Dollars", percentage);

        double electricityEmissionCalculation = calculations.calculateElectricityEmission(zipCode, Double.parseDouble(avgValue), "Dollars", Double.valueOf(percentage));

        String expectedResult = utils.roundFormatNumberAndConvertToString(electricityEmissionCalculation);
        Assertions.assertEquals(expectedResult, homeEnergyPage.getElectricityCalculationValue());
    }

    /**
     *
     * Test to check if the Fuel Oil value is being correctly calculated.
     * @see Calculations#calculateFuelOilEmission
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void fuelOilEmissionCalculationTest() {
        String zipCode = "00006";
        String avgValue = "80";

        calculatorHomePage.fillCarbonFootprintValues("1", zipCode);
        homeEnergyPage.selectPrimaryHeatingSource("Fuel Oil");
        homeEnergyPage.fillFuelOilMonthlyBill(avgValue, "Dollars");

        double fuelOilEmissionCalculation = calculations.calculateFuelOilEmission(Double.parseDouble(avgValue), "Dollars");

        String expectedResult = utils.roundFormatNumberAndConvertToString(fuelOilEmissionCalculation);
        Assertions.assertEquals(expectedResult, homeEnergyPage.getFuelOilCalculationValue());
    }

    /**
     *
     * Test to check if the Propane value is being correctly calculated.
     * @see Calculations#calculatePropaneEmission
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void propaneEmissionCalculationTest() {
        String zipCode = "00006";
        String avgValue = "37";

        calculatorHomePage.fillCarbonFootprintValues("1", zipCode);
        homeEnergyPage.selectPrimaryHeatingSource("Propane");
        homeEnergyPage.fillPropaneMonthlyBill(avgValue, "Dollars");

        String expectedResult = utils.roundFormatNumberAndConvertToString(calculations.calculatePropaneEmission(Double.parseDouble(avgValue), "Dollars"));
        Assertions.assertEquals(expectedResult, homeEnergyPage.getPropaneCalculationValue());
    }

    /**
     *
     * Test to check if the total emission for energy considering all forms of it are being correctly calculated
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void homeEnergyInDollarsCalculationTest() {
        int electricityPercentage = 0;
        String zipCode = "00006";
        String fuelOilMonthly = "80";
        String propaneMonthly = "37";
        String electricityMonthly = "250";
        String naturalGasMonthly = "24";

        calculatorHomePage.fillCarbonFootprintValues("1", zipCode);

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
}
