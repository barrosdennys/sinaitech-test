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

public class WasteTest {
    private final WebDriver driver = DriverFactory.getDriver();
    private final CalculatorHomePage calculatorHomePage = new CalculatorHomePage(driver);
    private final WastePage wastePage = new WastePage(driver);
    private final SideTotalBoxPage sideTotalBoxPage = new SideTotalBoxPage(driver);
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
     * Test to check if the Average US Emission from the Waste section is being calculated correctly.
     *
     * @author Dennys Barros
     */
    @Test
    public void wasteAverageTest() {
        double householdNumber = 5;
        double wasteAverageCalculation = calculations.calculateWasteAverage(householdNumber);
        String expectedResult = utils.roundFormatNumberAndConvertToString(wasteAverageCalculation);

        calculatorHomePage.fillCarbonFootprintValues(String.valueOf(householdNumber), "00006");
        wastePage.goToWasteSection();

        Assertions.assertEquals(expectedResult, sideTotalBoxPage.getAverageEmissionsValue(), "Average calculation number is not correct");
    }
}
