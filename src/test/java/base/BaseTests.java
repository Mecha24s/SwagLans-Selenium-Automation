package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTests {
    protected WebDriver driver;
    protected static final String BaseURL="https://www.saucedemo.com";

    @BeforeMethod
    public void setup(){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
    }
    @AfterMethod
    public void tearDown() {
        if (driver !=null) {
            driver.quit();
        }
        driver =null;

    }

}
