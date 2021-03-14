package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;

public class ProductPage extends BasePage {

	@FindBy(xpath = "//h1")
	private WebElement header;

	@FindBy(name = "Submit")
	private WebElement addToCart;

	@FindBy(xpath = "//span[contains(text(),'There is 1 item in your cart.')]")
	private WebElement successMessage;

	@FindBy(xpath = "//a[@title='Proceed to checkout']")
	private WebElement proceedToCheckout;

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getHeaderTitle(String name) {
		return getTextFromElement(header);
	}

	public boolean addItemToCart() {
		clickOnElement(addToCart);
		return waitForElement(successMessage).isDisplayed();
	}

	public CheckoutPage addItemToCartAndGoToCheckout() {
		clickOnElement(addToCart);
		waitForElement(successMessage);
		clickOnElement(proceedToCheckout);
		return new CheckoutPage(driver);
	}

}
