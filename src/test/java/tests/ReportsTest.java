package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverFactory;
import utils.ResourcesParser;

public class ReportsTest {

    private final WebDriver driver = DriverFactory.getDriver();
    private final CalculatorHomePage calculatorHomePage = new CalculatorHomePage(driver);
    private final SideTotalBoxPage sideTotalBoxPage = new SideTotalBoxPage(driver);
    private final HomeEnergyPage homeEnergyPage = new HomeEnergyPage(driver);
    private final ReportPage reportPage = new ReportPage(driver);
    private final ResourcesParser resourcesParser = new ResourcesParser();

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
     * Test to check if the Good Work message is being displayed in the report. It is displayed when the current
     * emission is lower than the average US emission.
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void checkGoodWorkMessageOnReportTest() {
        calculatorHomePage.fillCarbonFootprintValues("1", "00002");
        homeEnergyPage.selectPrimaryHeatingSource("Natural Gas");
        homeEnergyPage.fillNaturalGasMonthlyBill("1", "Dollars");

        sideTotalBoxPage.viewReport();

        Assertions.assertTrue(reportPage.checkGoodWorkMessageVisibility());
    }
}
