package pages;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generics.BasePage;

public class ResultsPage extends BasePage {

	@FindBy(xpath = "//h1")
	private WebElement header;

	public ResultsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// SP: Se reemplazó el xpath por un header mapeado anteriormente
	public String getHeaderTitle(String name) {
		return getTextFromElement(header);
	}

	public void helloWorld() {
		System.out.println("This should not be here " + new Date());
	}

}
