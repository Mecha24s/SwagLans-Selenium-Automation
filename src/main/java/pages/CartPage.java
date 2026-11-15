package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage  extends BasePage{
    private final By cartItemsLocator=By.className("cart_item");
    public CartPage(WebDriver driver){
        super(driver);
    }
    public int countItemsInCart(){
        return actions.countElements(cartItemsLocator);

    }

}
