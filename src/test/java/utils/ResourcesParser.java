package utils;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourcesParser {
    private String resourcesTxt;
    private String zipCodeEfText;

    public ResourcesParser() {
        try {
            InputStream inputStreamResources = Files.newInputStream(Paths.get("resources/assumptions.json"));
            InputStream inputStreamZipCode = Files.newInputStream(Paths.get("resources/emissions_by_zipcode.json"));
            this.resourcesTxt = IOUtils.toString(inputStreamResources, StandardCharsets.UTF_8);
            this.zipCodeEfText = IOUtils.toString(inputStreamZipCode, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getAssumptionValue(String assumptionKey) {
        JSONObject json = new JSONObject(resourcesTxt);
        JSONObject carbonFootprintData = json.getJSONObject("carbonFootprintData");
        return carbonFootprintData.getBigDecimal(assumptionKey).doubleValue();

    }

    public double getZipCodeElectricityEF(String zipcode) {
        JSONObject json = new JSONObject(zipCodeEfText);
        JSONObject zipCodeData = json.getJSONObject(zipcode);
        return zipCodeData.getBigDecimal("e_factor").doubleValue() / 1000;
    }

}
