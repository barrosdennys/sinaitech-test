package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverFactory;
import utils.ResourcesParser;

public class TransportationTest {

    private final WebDriver driver = DriverFactory.getDriver();
    private final CalculatorHomePage calculatorHomePage = new CalculatorHomePage(driver);
    private final WastePage wastePage = new WastePage(driver);
    private final TransportationPage transportationPage = new TransportationPage(driver);
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
     * Test to check if Reduce Your Emissions block from the Waste section updates according to the
     * selections from the Current Emissions block. If a product is selected on Current Emissions block
     * the same product must disappear on the Reduce Your Emissions block.
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void checkReduceWasteSectionUpdatesAccordingToTheCurrentWasteEmissionsTest(){
        calculatorHomePage.fillCarbonFootprintValues("5", "00002");
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

    /**
     * Test to check if an error message dialog is displayed when the user tries to add data into the
     * Reduce Your Emissions section on Transportation before filling the data from the Current Emissions section
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void checkCompleteVehicleEntriesAboveFirstErrorMessageTest() {
        calculatorHomePage.fillCarbonFootprintValues("5", "00002");
        transportationPage.goToTransportationSection();
        transportationPage.setNumberOfVehicles("2");

        transportationPage.fillVehicle1ReduceData("1");

        Assertions.assertTrue(transportationPage.checkCompleteVehicleEntriesAboveFirstErrorMessageVisibility());
    }

    /**
     *
     * Test to check if the UI from the Reduce Your Emission section is updated according to the data added into the
     * Current Emission section. If the maintenance selected in the Current Emission is "Already Done", then the same
     * field in Reduce Your Emission should not appear.
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void reduceEmissionMaintUIUpdatesAccordingToCurrentMaintTest() {
        calculatorHomePage.fillCarbonFootprintValues("5", "00002");
        transportationPage.goToTransportationSection();
        transportationPage.setNumberOfVehicles("1");

        transportationPage.selectCurrentMaintenance("Already Done");

        Assertions.assertFalse(transportationPage.checkReduceMaintenanceVisibility());
    }

    /**
     *
     * Test to check if the UI from the Reduce Your Emission section is updated according to the data added into the
     * Current Emission section. The number of cars selected in the Current Emission must be the same as the
     * Reduce Your Emissions section.
     *
     * @author Dennys Barros
     *
     */
    @Test
    public void carFormUpdatesAccordingToCarNumberTest() {
        calculatorHomePage.fillCarbonFootprintValues("5", "00002");
        transportationPage.goToTransportationSection();
        transportationPage.setNumberOfVehicles("2");

        Assertions.assertTrue(transportationPage.checkVehicle2TableVisibility());

        transportationPage.setNumberOfVehicles("0");

        Assertions.assertFalse(transportationPage.checkVehicle1TableVisibility());
        Assertions.assertFalse(transportationPage.checkVehicle2TableVisibility());
    }
}
