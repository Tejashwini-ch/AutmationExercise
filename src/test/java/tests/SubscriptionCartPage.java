package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

@Listeners(utils.TestListener.class)
public class SubscriptionCartPage extends BasicTest {

    @Test(description = "Test Case 11: Verify Subscription in Cart page")
    public void verifySubscriptionInCartPage() {
        WebDriver driver = getDriver();

        // 1️ Launch browser and navigate to URL
        driver.get("http://automationexercise.com");

        // 2️ Verify that home page is visible
        HomePage homePage = new HomePage(driver);
        WebElement homeHeading = homePage.getHomePageHeading();
        Assert.assertTrue(homeHeading.isDisplayed(), " Home page is not visible!");

        // 3️ Click 'Cart' button
        homePage.clickCart();

        // 4️ Scroll to footer and verify 'SUBSCRIPTION' text
        WebElement subscriptionElement = homePage.getSubscriptionElement();
        Assert.assertTrue(subscriptionElement.isDisplayed(), "'SUBSCRIPTION' text is not visible!");

        // 5️ Enter email and click subscribe
        homePage.subscribe("abc@gmail.com"); // replace with desired test email

        // 6️ Verify success message
        WebElement successMessage = homePage.getAlertSuccessSubscribe();
        Assert.assertTrue(successMessage.isDisplayed(), " Success message not visible!");
        Assert.assertEquals(successMessage.getText(), "You have been successfully subscribed!",
                " Success message text is incorrect!");
    }
}
