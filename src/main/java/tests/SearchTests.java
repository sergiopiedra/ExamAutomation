package tests;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;

import org.testng.annotations.Test;

import generics.BaseTest;
import pages.CheckoutPage;
import pages.ContactPage;
import pages.HomePage;
import pages.ProductPage;
import pages.ResultsPage;

public class SearchTests extends BaseTest {

	/**
	 * Este test realiza una búsqueda básica y confirma que lo que se muestra en el
	 * encabezado corresponde al criterio de búsqueda.
	 */
	@Test
	public void testValidSearch() {
		String name = "PRINTED SUMMER DRESS";
		HomePage home = new HomePage(driver);
		ResultsPage result = home.searchFor(name);

		assertTrue(result.getHeaderTitle(name).contains(name));
	}

	/**
	 * Este test realiza una búsqueda de un producto y redirecciona automáticamente
	 * al producto encontrado, luego confirma que lo que se muestra en el encabezado
	 * corresponde al criterio de búsqueda.
	 */
	@Test
	public void testSearchAndGo() {
		String name = "shirt";
		HomePage home = new HomePage(driver);
		ProductPage product = home.searchAndClick(name);

		assertTrue(product.getHeaderTitle(name).contains(name));
	}

	/**
	 * Este test realiza una búsqueda de un producto, lo abre y lo agrega al
	 * carrito.
	 */
	@Test
	public void testAddToCart() {
		String name = "shirt";
		HomePage home = new HomePage(driver);
		ProductPage product = home.searchAndClick(name);

		assertTrue(product.addItemToCart());
	}

	/**
	 * Este test realiza una búsqueda de un producto, lo abre y lo agrega al carrito
	 * y una vez agreado lo remueve.
	 */
	@Test
	public void testRemoveFromCart() {
		String name = "shirt";
		HomePage home = new HomePage(driver);
		ProductPage product = home.searchAndClick(name);
		CheckoutPage checkout = product.addItemToCartAndGoToCheckout();

		assertTrue(checkout.removeItem());
	}

	/**
	 * Este test realiza una búsqueda de un producto, lo abre y lo agrega al carrito
	 * y una vez agreado incremente la cantidad de productos y confirme que hay más
	 * de 1.
	 */
	@Test
	public void testAddMoreItemsToCart() {
		// SP: Añadí las inicializaciones del metodo y su variable + assertTrue by
		// increaseQuantityFromCart
		String name = "shirt";
		HomePage home = new HomePage(driver);
		ProductPage product = home.searchAndClick(name);
		CheckoutPage checkout = product.addItemToCartAndGoToCheckout();

		assertTrue(checkout.increaseQuantityFromCart());
	}

	/**
	 * Este test permite validarq ue se envía un mensaje exitosamente.
	 */
	@Test
	public void testContactUs() {
		HomePage home = new HomePage(driver);
		ContactPage contact = home.goToContact();
		contact = contact.sendEmail("test@test.com", "123", "Testing");

		assertTrue(contact.isMessageDisplayed());
	}

	/**
	 * Este test permite confirmar que el precio del producto se actualiza basado en
	 * el incremento/deducción de la cantidad de producto.
	 */
	@Test
	public void Test_Adding_Quantity() throws ParseException {
		// SP: Cambio de la variable NAME a name
		String name = "shirt";
		HomePage home = new HomePage(driver);
		ProductPage product = home.searchAndClick(name);
		CheckoutPage checkout = product.addItemToCartAndGoToCheckout();

		double CURRENTAMOUNT = Double.valueOf(checkout.getCurrentProductTotal().substring(1));
		double unitPrice = Double.valueOf(checkout.getCurrentUnitPrice().substring(1));
		double expectedTotal = CURRENTAMOUNT + unitPrice;

		checkout.increaseQuantityFromCart();
		double newTotal = Double.valueOf(checkout.getCurrentProductTotal().substring(1));

		assertTrue(newTotal == expectedTotal);
	}

}