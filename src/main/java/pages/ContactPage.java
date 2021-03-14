package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import generics.BasePage;

public class ContactPage extends BasePage {

	@FindBy(xpath = "//p[text()='Your message has been successfully sent to our team.']")
	private WebElement successMessage;

	// SP: Añadí mapeo de elementos necesarios
	@FindBy(id = "id_contact")
	private WebElement subjectHeading;

	@FindBy(id = "email")
	private WebElement emailForm;

	@FindBy(id = "id_order")
	private WebElement orderForm;

	@FindBy(id = "message")
	private WebElement messageForm;

	@FindBy(id = "submitMessage")
	private WebElement btnSend;

	// SP: Añadí Page Factory que inicializará cada variable WebElement
	public ContactPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public ContactPage sendEmail(String email, String order, String message) {
		Select select = new Select(subjectHeading);
		select.selectByVisibleText("Customer service");
		typeOnElement(emailForm, email);
		typeOnElement(orderForm, order);
		typeOnElement(messageForm, message);
		clickOnElement(btnSend);
		return new ContactPage(driver);
	}

	public boolean isMessageDisplayed() {
		return waitForElement(successMessage).isDisplayed();
	}

}
