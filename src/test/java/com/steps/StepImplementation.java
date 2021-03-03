package com.steps;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import helper.ElementHelper;
import helper.StoreHelper;
import model.ElementInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class StepImplementation extends Driver {
    protected static Logger logger = LogManager.getLogger(StepImplementation.class);
    private String text;
    public static int DEFAULT_MAX_ITERATION_COUNT = 150;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;


    @Step("When I write <textBox> to <key> and press enter")
    public void sendKeys(String text, String key) {
        this.text = text;
        if (!key.isEmpty()) {
            findElement(key).clear();
            findElement(key).sendKeys(text + "" + Keys.ENTER);
            logger.info(text);
        }
    }

    @Step("Then I should see <contentText> item in ToDo list")
    public void contentControl(String content) {
        String getText = findElement(content).getText();
        Assert.assertEquals(getText, text);
        logger.info(getText);
    }


    @Step("When I click on <key>")
    public void clickElement(String key) {
        WebElement we = findElement(key);
        we.click();
    }

    @Step("Wait <value> seconds")
    public void waitBySeconds(int seconds) {
        try {
            logger.info(seconds + " second wait.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Hover Element <key>")
    public void hoverElement(String key) {
        Actions action = new Actions(driver);
        WebElement we = findElement(key);
        action.moveToElement(we).moveToElement(findElement(key)).click().build().perform();

    }

    @Step("Check if element <key> exists")
    public WebElement getElementWithKeyIfExists(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            if (driver.findElements(by).size() > 0) {
                return driver.findElement(by);
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assert.fail("Element: '" + key + "' doesn't exist.");
        logger.info("Element exist.");
        return null;
    }

    @Step("Check remove element <key>")
    public WebElement checkElement(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        if (driver.findElements(by).size() > 0) {
            Assert.assertFalse("Element", driver.findElements(by).size() > 0);
        }
        logger.info("Element doesn't exist..");
        return null;
    }

    @Step("Wait <value> milliseconds")
    public void waitByMilliSeconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebElement findElement(String key) {
        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            WebDriverWait webDriverWait = new WebDriverWait(driver, 5, 300);
            WebElement webElement = webDriverWait
                    .until(ExpectedConditions.presenceOfElementLocated(infoParam));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
            return webElement;

        } catch (TimeoutException e) {
            Assert.fail("The Called Employee Has Not Been Found In The Given Time " + key);
            return null;
        }
    }

}
