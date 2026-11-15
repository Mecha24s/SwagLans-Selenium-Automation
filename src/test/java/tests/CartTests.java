package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utilities.DataDriven;

import java.util.List;
import java.util.Map;

public class CartTests extends BaseTests {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private Map<String,Object>cartData;
    private List<String> cartProducts;

    @BeforeClass
    public void dataSetup(){
        cartData=DataDriven.readJson("testData/cartData.json");
        cartProducts=(List<String>) cartData.get("cartProducts");
    }
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
    @Test
    public void verifyCartProductsAndOrder(){


        for(String product : cartProducts){
            inventoryPage.addItem(product);
        }
        inventoryPage.clickCartIcon();
        List<String> actualProducts = cartPage.getProductsNames();

        Assert.assertEquals(actualProducts, cartProducts,
                "Cart products or their order do not match");


    }
    @Test
    public void verifyRemovingItemFromCart(){
        for(String product : cartProducts){
            inventoryPage.addItem(product);
        }
        inventoryPage.clickCartIcon();
        List<String> actualProducts = cartPage.getProductsNames();
        cartPage.clickContShoppingButton();
        Assert.assertEquals(inventoryPage.getButtonText("Sauce Labs Bolt T-Shirt")
                ,"Remove");
        inventoryPage.removeItem("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(inventoryPage.getButtonText("Sauce Labs Bolt T-Shirt")
                ,"Add to cart");
        Assert.assertEquals(
                inventoryPage.getButtonText("Sauce Labs Backpack"),
                "Remove"
        );

        Assert.assertEquals(
                inventoryPage.getButtonText("Sauce Labs Onesie"),
                "Remove"
        );
    }





}
