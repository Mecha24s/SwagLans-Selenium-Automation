package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTests extends BaseTests {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod
    public void loginSetup() {
        loginPage = new LoginPage(driver);

        loginPage.enterUsername((String) validLogin.get("username"));
        loginPage.enterPassword((String) validLogin.get("password"));
        loginPage.clickLoginButton();

        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }
    @Test
    public void checkCartIsEmpty(){
        inventoryPage.clickCartIcon();
        int itemsCount=cartPage.countItemsInCart();
        Assert.assertEquals(itemsCount,0);

    }

}
