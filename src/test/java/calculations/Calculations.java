package calculations;

import utils.ResourcesParser;
import utils.Utils;

public class Calculations {

    private ResourcesParser resourcesParser = new ResourcesParser();
    private Utils utils = new Utils();

    public double calculateElectricityEmission(String zipCode, double avgValue, String measure, double percentage) {
        double emissionFactor = resourcesParser.getZipCodeElectricityEF(zipCode);
        double costsPerKwh = resourcesParser.getAssumptionValue("cost_per_kWh");
        double result = 0;

        switch (measure){
            case "Dollars" :
                result = avgValue / costsPerKwh * emissionFactor * 12;
                break;
            case "kWh" :
                result = avgValue * emissionFactor * 12;
                break;
        }

        if (percentage > 0) {
            double percentageValue = 1 - percentage/100;
            result =  result * percentageValue;
        }

        return Math.round(result);

    }

    public double calculateNaturalGasEmission (double avgValue, String measure) {
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

    public double calculateFuelOilEmission (double avgValue, String measure) {
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

    public double calculatePropaneEmission (double avgValue, String measure) {
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

    public double calculateWasteAverage(int householdNumber) {
        double averageWasteEmissions = resourcesParser.getAssumptionValue("average_waste_emissions");
        double finalAverageWasteEmissions = averageWasteEmissions * householdNumber;

        return Math.round(finalAverageWasteEmissions);
    }
}
