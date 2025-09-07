package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By productName = By.xpath("//td[contains(@class, 'cart_description')]//a");
    private By price = By.xpath("//td[contains(@class, 'cart_price')]/p");
    private By quantity = By.xpath("//td[contains(@class, 'cart_quantity')]/button");
    private By totalPrice = By.xpath("//p[contains(@class, 'cart_total_price')]");
    private By shoppingCart = By.cssSelector("li[class='active']");
    private By proceedToCheckoutButton = By.cssSelector("a.btn.btn-default.check_out");
    private By registerLoginButton = By.cssSelector("a[href='/login'] u");
    private By xButtons = By.cssSelector("a.cart_quantity_delete");
    private By emptyCartSpan = By.id("empty_cart");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    

    // ✅ Product names
    public List<String> getProductsNames() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productName));
        List<String> names = new ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText().trim());
        }
        return names;
    }

    // ✅ Prices
    public List<String> getPrices() {
        List<WebElement> elements = driver.findElements(price);
        List<String> prices = new ArrayList<>();
        for (WebElement e : elements) {
            prices.add(e.getText().trim());
        }
        return prices;
    }

    // ✅ Quantities
    public List<Integer> getQuantities() {
        List<WebElement> elements = driver.findElements(quantity);
        List<Integer> qtys = new ArrayList<>();
        for (WebElement e : elements) {
            qtys.add(Integer.parseInt(e.getText().trim()));
        }
        return qtys;
    }

    // ✅ Total prices
    public List<String> getTotalPrices() {
        List<WebElement> elements = driver.findElements(totalPrice);
        List<String> totals = new ArrayList<>();
        for (WebElement e : elements) {
            totals.add(e.getText().trim());
        }
        return totals;
    }

    // ✅ Shopping Cart heading
    public String getShoppingCartHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCart)).getText().trim();
    }

    // ✅ Proceed to checkout
    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
    }

    public void clickRegisterLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLoginButton)).click();
    }

    public CheckoutPage proceedToCheckoutLoggedButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
        return new CheckoutPage(driver);
    }

    // ✅ Delete all products
    public void deleteAllProducts() throws InterruptedException {
        List<WebElement> buttons = driver.findElements(xButtons);
        while (!buttons.isEmpty()) {
            buttons.get(0).click();
            Thread.sleep(500);
            buttons = driver.findElements(xButtons);
        }
    }

    // ✅ Empty cart message
    public String getEmptyCartMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartSpan)).getText().trim();
    }

    // ✅ Check if a product is present
    public boolean isProductInCart(String name) {
        for (String p : getProductsNames()) {
            if (p.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // ✅ Get quantity of a specific product
    public int getProductQuantity(String name) {
        List<String> products = getProductsNames();
        List<Integer> quantities = getQuantities();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equalsIgnoreCase(name)) {
                return quantities.get(i);
            }
        }
        return 0;
    }
}
