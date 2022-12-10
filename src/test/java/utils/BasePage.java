package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = DriverFactory.getWait();
    }

    public void clickOnElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public void sendKeysToElement(By locator, String keys) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(keys);
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() != 0;
    }

    public boolean isElementVisible(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public String getTextFromLocator(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public void selectElementByText(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

}
