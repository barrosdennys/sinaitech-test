package calculations;

import utils.ResourcesParser;

public class Calculations {

    private final ResourcesParser resourcesParser = new ResourcesParser();

    /**
     * Method created to calculate the Electricity emission based on the info provided by the user.
     * The calculation is:
     * <p>     avgValue / costsPerWeek * emissionFactor * 12 (if the measure is Dollars)
     * <p>     avgValue * emissionFactor * 12 (if the measure is kWh)
     * <p>
     * emissionFactor value is based on the zipcode, and is found in the emissions_by_zipcode.json
     * costsPerWeek value is found in the properties.json file
     *
     * @param zipCode    string representing the zipcode of the household
     * @param avgValue   string representing the average value of the electricity home bill
     * @param measure    string representing the measure of the avgValue (kWh or Dollars)
     * @param percentage string representing the percentage of green Energy used
     * @author Dennys Barros
     */
    public double calculateElectricityEmission(String zipCode, double avgValue, String measure, double percentage) {
        double emissionFactor = resourcesParser.getZipCodeElectricityEF(zipCode);
        double costsPerKwh = resourcesParser.getAssumptionValue("cost_per_kWh");
        double result = 0;

        switch (measure) {
            case "Dollars":
                result = avgValue / costsPerKwh * emissionFactor * 12;
                break;
            case "kWh":
                result = avgValue * emissionFactor * 12;
                break;
        }

        if (percentage > 0) {
            double percentageValue = 1 - percentage / 100;
            result = result * percentageValue;
        }

        return Math.round(result);

    }

    /**
     * Method created to calculate the Natural Gas emission based on the info provided by the user.
     * The calculation is:
     * <p>     avgValue / naturalGasCost * naturalGasEmissionFactor * 12 (if the measure is Dollars)
     * <p>     avgValue * naturalGasEmissionFactor * 12 (if the measure is Thousand Cubic Feet)
     * <p>     avgValue * naturalGasThermsEmissionFactor * 12 (if the measure is Therms)
     * <p>
     * naturalGasCost, naturalGasEmissionFactor and naturalGasThermsEmissionFactor can be found in
     * properties.json file
     *
     * @param avgValue string representing the average value of the natural gas bill
     * @param measure  string representing the measure of the avgValue (Thousand Cubic Feet, Therms or Dollars)
     * @author Dennys Barros
     */
    public double calculateNaturalGasEmission(double avgValue, String measure) {
        double naturalGasCost = resourcesParser.getAssumptionValue("Natural_gas_cost_1000CF");
        double naturalGasEmissionFactor = resourcesParser.getAssumptionValue("EF_natural_gas");
        double naturalGasThermsEmissionFactor = resourcesParser.getAssumptionValue("EF_natural_gas_therm");
        double result = 0;

        switch (measure) {
            case "Dollars":
                result = avgValue / naturalGasCost * naturalGasEmissionFactor * 12;
                break;
            case "Thousand Cubic Feet":
                result = avgValue * naturalGasEmissionFactor * 12;
                break;
            case "Therms":
                result = avgValue * naturalGasThermsEmissionFactor * 12;
                break;
        }

        return Math.round(result);
    }

    /**
     * Method created to calculate the Fuel Oil emission based on the info provided by the user.
     * The calculation is:
     * <p>     avgValue / fuelOilCost * fuelOilEmissionFactor * 12 (if the measure is Dollars)
     * <p>     avgValue * fuelOilEmissionFactor * 12 (if the measure is Gallons)
     * <p>
     * fuelOilCost, fuelOilEmissionFactor can be found in properties.json file
     *
     * @param avgValue string representing the average value of the fuel oil bill
     * @param measure  string representing the measure of the avgValue (Gallons or Dollars)
     * @author Dennys Barros
     */
    public double calculateFuelOilEmission(double avgValue, String measure) {
        double fuelOilCost = resourcesParser.getAssumptionValue("fuel_oil_cost");
        double fuelOilEmissionFactor = resourcesParser.getAssumptionValue("EF_fuel_oil_gallon");
        double result = 0;

        switch (measure) {
            case "Dollars":
                result = avgValue / fuelOilCost * fuelOilEmissionFactor * 12;
                break;
            case "Gallons":
                result = avgValue * fuelOilEmissionFactor * 12;
                break;
        }

        return Math.round(result);
    }

    /**
     * Method created to calculate the Propane emission based on the info provided by the user.
     * The calculation is:
     * <p>     avgValue / propaneCost * propaneEmissionFactor * 12 (if the measure is Dollars)
     * <p>     avgValue * propaneEmissionFactor * 12 (if the measure is Gallons)
     * <p>
     * propaneCost, propaneEmissionFactor can be found in properties.json file
     *
     * @param avgValue string representing the average value of the propane bill
     * @param measure  string representing the measure of the avgValue (Gallons or Dollars)
     * @author Dennys Barros
     */
    public double calculatePropaneEmission(double avgValue, String measure) {
        double propaneCost = resourcesParser.getAssumptionValue("propane_cost");
        double propaneEmissionFactor = resourcesParser.getAssumptionValue("EF_propane");
        double result = 0;

        switch (measure) {
            case "Dollars":
                result = avgValue / propaneCost * propaneEmissionFactor * 12;
                break;
            case "Gallons":
                result = avgValue * propaneEmissionFactor * 12;
                break;
        }

        return Math.round(result);
    }

    /**
     * Method created to calculate the average emission per household.
     * The calculation is:
     * <p>     averageWasteEmissions / householdNumber *
     * <p>
     * averageWasteEmissions value can be found in properties.json file
     *
     * @param householdNumber integer representing the number of persons of the household
     * @author Dennys Barros
     */
    public double calculateWasteAverage(double householdNumber) {
        double averageWasteEmissions = resourcesParser.getAssumptionValue("average_waste_emissions");
        double finalAverageWasteEmissions = averageWasteEmissions * householdNumber;

        return Math.round(finalAverageWasteEmissions);
    }
}
