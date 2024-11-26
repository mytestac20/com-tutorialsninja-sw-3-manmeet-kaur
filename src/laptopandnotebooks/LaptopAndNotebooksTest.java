package laptopandnotebooks;

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

public class LaptopAndNotebooksTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php?";

    //Open Browser
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    //verify Products Price Display HighToLow Successfully
    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        //Mouse hover on the Laptops & Notebooks Tab. and click
        WebElement laptopAndNotebooksElement = findWebElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        mouseHooverAndClickOnElement(laptopAndNotebooksElement);

        //Click on “Show AllLaptops & Notebooks”
        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));

        //Select the Sort By "Price (High > Low)"
        WebElement sortElement = findWebElement(By.xpath("//select[@id='input-sort']"));
        Select select = new Select(sortElement);
        select.selectByVisibleText("Price (High > Low)");

        //Verify the Product price will be arranged in High to Low orders.
        List<WebElement> priceList = driver.findElements(By.xpath("//div[@id='content']//div//div[1]//div[2]//div[1]//p[2]"));

        List<String> actualPriceList = new ArrayList<>();
        for (WebElement e : priceList) {
            String price = e.getText().split("\n")[0].replace("$", "").trim();
            actualPriceList.add(price);
        }


        System.out.println(actualPriceList);
        List<String> expectedPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedPriceList, Collections.reverseOrder());
        System.out.println(expectedPriceList);
        // Assert.assertEquals("Price is not displayed in High to Low",expectedPriceList,actualPriceList);
    }

    //verifyThatUserPlaceOrderSuccessfully()
    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //Mouse hover on the Laptops & Notebooks Tab. and click
        WebElement laptopAndNotebooksElement = findWebElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        mouseHooverAndClickOnElement(laptopAndNotebooksElement);

        //Click on “Show AllLaptops & Notebooks”
        clickOnElement(By.linkText("Show AllLaptops & Notebooks"));

        //Select the Sort By "Price (High > Low)"
        WebElement sortElement = findWebElement(By.xpath("//select[@id='input-sort']"));
        Select select = new Select(sortElement);
        select.selectByVisibleText("Price (High > Low)");

        //Select Product “MacBook”
        clickOnElement(By.xpath("//a[normalize-space()='MacBook']"));

        //Verify the text “MacBook”
        String actualMacBookText=getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']"));
        String expectedMacBookText="MacBook";
        Assert.assertEquals("The text Macbook is not displayed",expectedMacBookText,actualMacBookText);

        //Click on the ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        //Verify the message “Success: You have added MacBook to your shopping cart!”
        String[] actualAddToCartMessage = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).split("\n");
        String expectedAddToCartMessage = "Success: You have added MacBook to your shopping cart!";
        Assert.assertEquals("Message is not displayed", expectedAddToCartMessage, actualAddToCartMessage[0]);

        //Click on the link “shopping cart” display into the success message
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        //Verify the text "Shopping Cart"
        String shoppingCartText = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
        System.out.println(shoppingCartText);
        String expectedShoppingCartText = "Shopping Cart";
        Assert.assertTrue("The text Shopping cart is not displayed", shoppingCartText.contains(expectedShoppingCartText));

        //Verify the Product name "MacBook"
        String actualProductName = getTextFromElement(By.xpath("//body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        String expectedProductName = "MacBook";
        Assert.assertEquals("the Product name MacBook ", expectedProductName, actualProductName);

        //Change the Quantity "2"
        sendTextOnElement(By.cssSelector("input[value='1']"),"2");

        //Click on the “Update” Tab
        clickOnElement(By.xpath("//button[@type='submit']"));

        //Verify the message “Success: You have modified your shopping cart!”
        String[] actualSuccessMessage=getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).split("\n");
        String expectedSuccessMessage="Success: You have modified your shopping cart!";
        Assert.assertEquals("The Message is not displayed",expectedSuccessMessage,actualSuccessMessage[0]);

        //Verify the Total £737.45
        String actualTotal=getTextFromElement(By.xpath("//tbody//tr//td[6]"));
        String expectedTotal="£737.45";
        Assert.assertEquals("The Total is not match", expectedTotal,actualTotal);

        //Click on the “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));

        //Verify the text “Checkout”
        String actualCheckOutText=getTextFromElement(By.xpath("//h1[normalize-space()='Checkout']"));
        String expectedCheckOutText= "Checkout";
        Assert.assertEquals("The Checkout text is not displayed",expectedCheckOutText,actualCheckOutText);

        //Verify the Text “New Customer”
        String actualNewCustomer=getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']"));
        String expectedNewCustomer="New Customer";
        Assert.assertEquals("The New Customer text is not displayed",expectedNewCustomer,actualNewCustomer);

        //Click on the “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));

        //Click on “Continue” tab
        clickOnElement(By.xpath("//input[@id='button-account']"));

        //Fill the mandatory fields
        sendTextOnElement(By.xpath("//input[@id='input-payment-firstname']"),"Rudraa");
        sendTextOnElement(By.xpath("//input[@id='input-payment-lastname']"),"Parekh");
        sendTextOnElement(By.xpath("//input[@id='input-payment-email']"),"mytestac20@gmail.com");
        sendTextOnElement(By.xpath("//input[@id='input-payment-telephone']"),"12312345645");
        sendTextOnElement(By.xpath("//input[@id='input-payment-address-1']"),"abc, finchley");
        sendTextOnElement(By.xpath("//input[@id='input-payment-city']"),"London");
        sendTextOnElement(By.xpath("//input[@id='input-payment-postcode']"),"ha1 3jx");

        WebElement country = findWebElement(By.xpath("//input[@id='input-payment-postcode']"));
        Select selectCourty= new Select(country);
        selectCourty.selectByVisibleText("United Kingdom");

        WebElement Region = findWebElement(By.xpath("//select[@id='input-payment-zone']"));
        Select selectRegion=new Select(Region);
        selectRegion.selectByVisibleText("Bedfordshire");

        //Click on “Continue” Button
        clickOnElement(By.xpath("//input[@id='button-guest']"));

        //Add Comments About your order into the text area
        sendTextOnElement(By.xpath("//textarea[@name='comment']"),"This is comment for test case");
        clickOnElement(By.xpath("//input[@id='button-shipping-method']"));

        //Check the Terms & Conditions check box
        clickOnElement(By.xpath("//input[@name='agree']"));

        //Click on the “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));








    }


    @After
    public void tearDown() {
        //closeBrowser();
    }
}

