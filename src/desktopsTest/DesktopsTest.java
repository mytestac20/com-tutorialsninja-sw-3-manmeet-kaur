package desktopsTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php?";

    //Open Browser
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    //verify Product Arrange In Alphabetical Order
    public void verifyProductArrangeInAlphabeticalOrder() {
        //Mouse hover on the Desktops Tab. and click
        WebElement desktopMenu = findWebElement(By.linkText("Desktops"));
        mouseHooverAndClickOnElement(desktopMenu);

        //Click on “Show AllDesktops”
        clickOnElement(By.linkText("Show AllDesktops"));

        //1.3 Select Sort By position "Name: Z to A"
        WebElement sortByElement = findWebElement(By.id("input-sort"));
        Select select = new Select(sortByElement);
        select.selectByVisibleText("Name (Z - A)");

        //1.4 Verify the Product will be arranged in Descending order.
        List<WebElement> productNames = driver.findElements(By.xpath("//div[@id='content']//div//div[1]//div[2]//div[1]//h4"));
        System.out.println(productNames.size());

        List<String> actualNames = new ArrayList<>();
        for (WebElement productName : productNames) {
            actualNames.add(productName.getText().toUpperCase());
        }

        //Create copy of actual array
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames, Collections.reverseOrder());
        Assert.assertEquals("product names are not displayed in the descending order", expectedNames, actualNames);
    }

    //verify Product Added To ShoppingCart SuccessFully
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //Mouse hover on the Currency Dropdown and click
        WebElement currencyElement = findWebElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']"));
        mouseHooverAndClickOnElement(currencyElement);

        //Mouse hover on the £Pound Sterling and click
        WebElement poundElement = findWebElement(By.cssSelector("button[name='GBP']"));
        mouseHooverAndClickOnElement(poundElement);

        //Mouse hover on the Desktops Tab.
        WebElement desktopMenu = findWebElement(By.linkText("Desktops"));
        mouseHooverAndClickOnElement(desktopMenu);

        //Click on “Show AllDesktops”
        clickOnElement(By.linkText("Show AllDesktops"));

        //Select the Sort By position "Name: A to Z"
        WebElement sortByElement = findWebElement(By.id("input-sort"));
        Select select = new Select(sortByElement);
        select.selectByVisibleText("Name (A - Z)");

        //Select product “HP LP3065”
        clickOnElement(By.linkText("HP LP3065"));

        //Verify the Text "HP LP3065"
        String actualLaptopName = getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']"));
        String expectedLaptopName = "HP LP3065";
        Assert.assertEquals("The Test is failed as the Text HP LP3065 is not displayed", expectedLaptopName, actualLaptopName);

        //Select Delivery Date "2024-11-27"
        clickOnElement(By.xpath("//div[@class='input-group date']//button[@type='button']"));

        String day = "27";
        String month = "November";
        String year = "2024";

        while (true) {
            String monthAndYear = getTextFromElement(By.cssSelector("div[class='datepicker-days'] th[class='picker-switch']"));
            String[] currentMonthAndYear = monthAndYear.split(" ");
            String currentMonth = currentMonthAndYear[0];
            String currentYear = currentMonthAndYear[1];

            if (currentMonth.equals(month) && currentYear.equals(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }

        List<WebElement> dates = driver.findElements(By.xpath("//tbody/tr/td[@class='day']"));
        for (WebElement date : dates) {
            System.out.println(date.getText());
            if (date.getText().equals(day)) {
                date.click();
                break;
            }
        }


        //Enter Qty "1” using Select class.
        sendTextOnElement(By.xpath("//input[@id='input-quantity']"), "1");

        //Click on the “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        //Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Thread.sleep(3000);
        String[] actualAddToCartMessage = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).split("\n");
        String expectedAddToCartMessage = "Success: You have added HP LP3065 to your shopping cart!";
        Assert.assertEquals("Message is not displayed", expectedAddToCartMessage, actualAddToCartMessage[0]);

        //Click on the link “shopping cart” displayed in a success message
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
//
//        //Verify the text "Shopping Cart"
        String shoppingCartText = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
        System.out.println(shoppingCartText);
        String expectedShoppingCartText = "Shopping Cart";
        Assert.assertTrue("The text Shopping cart is not displayed", shoppingCartText.contains(expectedShoppingCartText));
//
//        //Verify the Product name "HP LP3065"
        String actualProductName = getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        String expectedProductName = "HP LP3065";
        Assert.assertEquals("the Product name HP LP3065 ", expectedProductName, actualProductName);

        //Verify the Delivery Date "2024-11-27"
        String[] actualDeliveryDate = driver.findElement(By.cssSelector("body > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > form:nth-child(2) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2) > small:nth-child(3)")).getText().split(":");
        //System.out.println(deliveryDate[1]);
        String expectedDeliveryDate = "2024-11-27";
        Assert.assertEquals("The delivery date is not printed", expectedDeliveryDate, actualDeliveryDate[1]);


        //Verify the Model "Product21"
        String actualModel = getTextFromElement(By.xpath("//td[normalize-space()='Product 21']"));
        String expectedModel = "Product 21";
        Assert.assertEquals("The model Product 21 is not displayed", expectedModel, actualModel);

        //Verify the Total "£74.73"
        String actualTotal = getTextFromElement(By.xpath("//tbody//tr//td[6]"));
        String expectedTotal = "£74.73";
        Assert.assertEquals("The total price is not displayed", expectedTotal, actualTotal);
    }

    @After
    public void tearDown() {
        //closeBrowser();
    }
}
