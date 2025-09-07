package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.CartPage;
import pages.ProductDetailPage;
import tests.BasicTest;
import utils.TestListener;

import java.util.List;

@Listeners(TestListener.class)
public class AddProductCart extends BasicTest {

 
    @Test
    public void verifyViewProduct() {
        driver.get("https://automationexercise.com/products");

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.fillSearchProductInput("GRAPHIC DESIGN MEN T SHIRT - BLUE");

        ProductDetailPage detailPage = productsPage.viewProductOfFirstProductButtonClick();

        Assert.assertTrue(detailPage.isProductNameDisplayed(),
                "❌ Product detail page did not open!");
    }
}
