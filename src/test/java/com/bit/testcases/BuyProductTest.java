package com.bit.testcases;

import com.bit.base.BaseTest;
import com.bit.pages.swaglabs.ConfirmationPage;
import com.bit.utilities.DataProviders;
import com.bit.utilities.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class BuyProductTest extends BaseTest {

//    public BuyProductTest(WebDriver driver) {
//        super(driver);
//    }
    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
    public void buyProductTest(Hashtable<String, String> data) {
        log.info("In start of the buyProductTest()");

        if (!data.get("runmode").equals("Y")) {
            throw new SkipException("Skipping the case as the Run mode for data set is NO");
        }
        try {
            String url = config.getProperty("appUrl");
            String username = data.get("username");
            String password = data.get("password");
            String firstName = data.get("firstName");
            String lastName = data.get("lastName");
            String postalCode = data.get("postalCode");
            String expPageTitle = "Swag Labs", actPageTitle;
            String screenshotName = "Img_" + new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
            driver.get(url);
            com.bit.pages.swaglabs.LoginPage loginPage = new com.bit.pages.swaglabs.LoginPage(driver);
            loginPage.login(username, password);
            TestUtil.captureScreenshot(userDir + "/target/" + screenshotName);
            actPageTitle = driver.getTitle();
            //System.out.println("Page title is: "+actPageTitle);    //For debugging
            Assert.assertEquals(actPageTitle, expPageTitle, "Login Test is not executed successfully!");
            log.info("In end of the loginTest()");
            log.info("In start of the ProductsPage()");
            com.bit.pages.swaglabs.ProductsPage productsPage = new com.bit.pages.swaglabs.ProductsPage(driver);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))));
            productsPage.clickOnBackpackProduct();
            log.info("Click on Add Backpack product to cart");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='shopping_cart_container']"))));
            productsPage.clickOnShoppingCart();
            log.info("Click on Shopping cart");
            log.info("In end of the ProductsPage()");
            log.info("In start of the CartPage()");
            com.bit.pages.swaglabs.CartPage cartPage = new com.bit.pages.swaglabs.CartPage(driver);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("checkout"))));
            cartPage.clickOnCheckout();
            log.info("Click on Checkout");
            log.info("In end of the CartPage()");
            log.info("In start of the CheckoutPage()");
            com.bit.pages.swaglabs.CheckoutPage checkoutPage = new com.bit.pages.swaglabs.CheckoutPage(driver);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("first-name"))));
            checkoutPage.checkOut(firstName, lastName, postalCode);
            log.info("Complete Checkout");
            log.info("In end of the CheckoutPage()");
            log.info("In start of the ConfirmationPage()");
            com.bit.pages.swaglabs.ConfirmationPage confirmationPage = new ConfirmationPage(driver);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("finish"))));
            confirmationPage.clickOnFinish();
            log.info("Click on Finish");
            log.info("In end of the ConfirmationPage()");
            log.info("In end of the buyProductTest()");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
