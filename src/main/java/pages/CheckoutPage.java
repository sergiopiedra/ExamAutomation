package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;

public class CheckoutPage extends BasePage {

	@FindBy(xpath = "h1")
	private WebElement header;

	@FindBy(xpath = "//p[text()='Your shopping cart is empty.']")
	private WebElement emptyCartMessage;

	@FindBy(id = "total_product_price_1_1_0")
	private WebElement productTotalPrice;

	@FindBy(xpath = "//span[@id='product_price_1_1_0']/span")
	private WebElement unitPrice;

	@FindBy(name = "quantity_1_1_0_0_hidden")
	private WebElement qty;

	// SP: Añadí mapeo de elementos necesarios
	@FindBy(xpath = "//*[@id=\"product_1_1_0_0\"]/td[7]/div")
	private WebElement deleteItem;

	@FindBy(xpath = "//*[@id=\"cart_quantity_down_1_1_0_0\"]/span")
	private WebElement minusIcon;

	@FindBy(xpath = "//*[@id=\"cart_quantity_up_1_1_0_0\"]/span")
	private WebElement plusIcon;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getHeaderTitle() {
		return getTextFromElement(header);
	}

	public boolean removeItem() {
		clickOnElement(deleteItem);
		return waitForElement(emptyCartMessage).isDisplayed();
	}

	public void reduceQuantityFromCart() {
		clickOnElement(minusIcon);
	}

	public boolean increaseQuantityFromCart() {
		clickOnElement(plusIcon);
		return waitForQtyChange("value", "2");
	}

	public String getCurrentProductTotal() {
		return getTextFromElement(productTotalPrice);
	}

	public String getCurrentUnitPrice() {
		return getTextFromElement(unitPrice);
	}

	private boolean waitForQtyChange(String attribute, String value) {
		return waitForElementToChange(qty, attribute, value);

	}

}
