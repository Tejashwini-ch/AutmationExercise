package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ProductDetailPage;
import utils.TestListener;

import java.time.Duration;

@Listeners(TestListener.class)
public class AddReviewOnProduct extends BasicTest {

    @Test
    public void addReviewOnProduct() {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1️ Navigate directly to Products page
        driver.get("http://automationexercise.com/products");

        // 2️ Initialize ProductsPage
        ProductsPage productsPage = new ProductsPage(driver);

        // 3️ Verify 'ALL PRODUCTS' page
        WebElement allProductsHeading = wait.until(
                ExpectedConditions.visibilityOf(productsPage.getTitleTextCenter()));
        Assert.assertEquals(allProductsHeading.getText(), "ALL PRODUCTS",
                "User is not on ALL PRODUCTS page!");

        // 4️ Click 'View Product' of first product
        ProductDetailPage productDetailPage = productsPage.viewProductOfFirstProductButtonClick();

        // 5️ Verify 'WRITE YOUR REVIEW' section is visible
        WebElement writeReviewHeading = wait.until(
                ExpectedConditions.visibilityOf(productDetailPage.getWriteYourReview()));
        Assert.assertEquals(writeReviewHeading.getText(), "WRITE YOUR REVIEW",
                "'Write Your Review' section not visible!");

        // 6️ Fill in review form using existing writeReview() method
        productDetailPage.writeReview(
                "Test User", 
                "testuser@example.com", 
                "This is a test review."
        );

        // 7️ Verify success message
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOf(productDetailPage.getSuccessMessage()));
        Assert.assertEquals(successMessage.getText(), "Thank you for your review.",
                "Review submission failed!");
    }
}
