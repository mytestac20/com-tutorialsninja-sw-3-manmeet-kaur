package utilities;

import browserfactory.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Utility extends BaseTest {

    /**
     * This method will send text on element
     */
    public void sendTextOnElement(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * This method will get the text from element
     */
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    /**
     * This method will click on the element
     */
    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    /**
     * This method will return webElement
     */
    public WebElement findWebElement(By by) {
        return driver.findElement(by);
    }


    /**
     * This method will do mouse hoover on element
     */
    public void mouseHooverAndClickOnElement(WebElement webElement){
        Actions actions=new Actions(driver);
        actions.moveToElement(webElement).click().build().perform();
    }
}









