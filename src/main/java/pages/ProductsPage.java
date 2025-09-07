package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By titleTextCenter = By.cssSelector("h2.title.text-center"); // Works for ALL PRODUCTS & SEARCHED PRODUCTS
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By productNameElements = By.cssSelector(".productinfo p"); // product names on search result

    private By signupLoginLink = By.cssSelector("a[href='/login']");
    private By viewProductButtons = By.cssSelector(".features_items .choose a[href*='/product_details/']"); // all "View Product"
    private By firstAddToCartButton = By.cssSelector(".features_items .product-overlay .add-to-cart"); // Add to Cart inside overlay
    private By continueShoppingButton = By.cssSelector(".btn-success.close-modal.btn-block");
    private By viewCartButton = By.cssSelector("a[href='/view_cart'] u");

    // Category locators
    private By womenCategory = By.xpath("//a[contains(text(),'Women')]");
    private By dressSubCategory = By.xpath("//a[contains(text(),'Dress') and parent::li[parent::ul[@class='nav nav-pills nav-stacked']]]");
    private By menCategory = By.xpath("//a[contains(text(),'Men')]");
    private By tShirtsSubCategory = By.xpath("//a[contains(text(),'Tshirts') and parent::li[parent::ul[@class='nav nav-pills nav-stacked']]]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ✅ Navigate to login page
    public LoginPage clickSignupLogin() {
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink));
        loginLink.click();
        return new LoginPage(driver);
    }

    // ✅ Heading (ALL PRODUCTS / SEARCHED PRODUCTS)
    public WebElement getTitleTextCenter() {
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(titleTextCenter));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);
        return heading;
    }

    // ✅ Search
    public void fillSearchProductInput(String productName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    // ✅ Get product names after search
    public List<String> getProductsSearchNames() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameElements));
        List<String> names = new ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText());
        }
        return names;
    }

    // ✅ Click View Product of first product
    public ProductDetailPage viewProductOfFirstProductButtonClick() {
        WebElement firstViewProduct = wait.until(ExpectedConditions.elementToBeClickable(viewProductButtons));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstViewProduct);
        firstViewProduct.click();
        return new ProductDetailPage(driver);
    }

    // ✅ Add first product to cart and stay on Products page
    public ProductsPage addFirstProductToCartAndContinue() {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
        addButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
        return this;
    }

    // ✅ Add first product to cart & go directly to CartPage
    public CartPage addFirstProductToCartAndGoToCart() {
        try {
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
            addButton.click();

            WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
            viewCartLink.click();

        } catch (Exception e) {
            throw new RuntimeException("❌ Could not add product to cart!", e);
        }
        return new CartPage(driver);
    }

    // ✅ Directly go to Cart without adding
    public CartPage viewCart() {
        WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        viewCartLink.click();
        return new CartPage(driver);
    }

 

}
