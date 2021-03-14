package generics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;
	// SP: Espera utilizada
	private WebDriverWait waitHere;

	@BeforeMethod
	public void setup() {
		String url = "http://automationpractice.com/";
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Page enable check");
		// SP: Espera para iniciar el test hasta que esté este un elemento disponible
		waitHere = new WebDriverWait(driver, 10);
		waitHere.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchbox\"]/button")));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
