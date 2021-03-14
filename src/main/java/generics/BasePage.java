package generics;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	private WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger();

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	public void typeOnElement(WebElement element, String text) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			logger.error("Unable to type on element: " + e);
		}
	}

	public void clickOnElement(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			logger.info("Clicked on element: " + element);
		} catch (Exception e) {
			logger.error("Unable to click on element: " + e);
		}
	}

	public String getTextFromElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).getText();
	}

	public List<WebElement> getElements(List<WebElement> elements) {
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public WebElement waitForElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public boolean waitForElementToChange(WebElement element, String attribute, String value) {
		return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
	}

}
