package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php?";

    //Open Browser
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    //selectMenu method
    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }

    @Test
    //verify User Should Navigate To Desktops Page Successfully
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        //Mouse hover on the “Desktops” Tab and click
        WebElement desktopMenu = findWebElement(By.linkText("Desktops"));
        mouseHooverAndClickOnElement(desktopMenu);

        //call the selectMenu() method and pass the menu = “Show AllDesktops”
        selectMenu("Show AllDesktops");

        //Verify the text ‘Desktops’
        String expectedDesktopsText = "Desktops";
        String actualDesktopsText = getTextFromElement(By.xpath("//h2"));
        Assert.assertEquals("Desktops text is not displayed", expectedDesktopsText, actualDesktopsText);
    }

    //verify User Should Navigate To Laptops And Notebooks Page Successfully
    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        //Mouse hover on the “Laptops & Notebooks” Tab and click
        WebElement laptopsAndNotebooksElement = findWebElement(By.linkText("Laptops & Notebooks"));
        mouseHooverAndClickOnElement(laptopsAndNotebooksElement);

        //call selectMenu() method and pass the menu = “Show AllLaptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");

        //2.3 Verify the text ‘Laptops & Notebooks’
        String expectedDesktopsText = "Laptops & Notebooks";
        String actualDesktopsText = getTextFromElement(By.xpath("//h2"));
        Assert.assertEquals("Laptops & Notebooks text is not displayed", expectedDesktopsText, actualDesktopsText);
    }

    //verify User Should Navigate To Components Page Successfully
    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        //Mouse hover on the “Components” Tab and click
        WebElement componentsElement = findWebElement(By.linkText("Components"));
        mouseHooverAndClickOnElement(componentsElement);

        //call the selectMenu() method and pass the menu = “Show AllComponents”
        selectMenu("Show AllComponents");

        //Verify the text ‘Components’
        String expectedDesktopsText = "Components";
        String actualDesktopsText = getTextFromElement(By.xpath("//h2"));
        Assert.assertEquals("Components text is not displayed", expectedDesktopsText, actualDesktopsText);
    }

    @After
    public void tearDown() {
        //closeBrowser();
    }
}
