package pageobject;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;

import org.junit.Assert;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Componente {

	private WebDriver driver;
	public static Actions action;
	private WebDriverWait wait;

	static String nombreproductoprimero;
	static String precioproductoprimero;
	static Integer cantid;

	@FindBy(xpath = "//span[contains(text(),'Accept')]")
	WebElement cookies;

	@FindBy(xpath = "//body[contains(@class,'catalogsearch-result-index')]/div[@class='wrapper']/div[@class='page']/div[@class='main-container col3-layout']/div[@class='main']/div[@class='col-wrapper']/div[@class='col-main']/div[@class='category-products']/div[@class='toolbar']/div[@class='sorter']/div[@class='sort-by']/select/option[2]")
	WebElement ordenar;

	@FindBy(xpath = "//div[@class='category-products']//li[1]")
	WebElement producto1;

	@FindBy(xpath = "//li[1]//div[1]//div[1]//div[1]//h2[1]/a")
	WebElement nombreproducto;

	@FindBy(xpath = "//li[1]//div[1]//div[1]//div[2]//div[1]//span//span")
	WebElement precioproducto;

	@FindBy(xpath = "//div[@class='toolbar-bottom']//option[contains(text(),'Precio (ascendente)')]")
	WebElement orden;

	@FindBy(xpath = "//div[@class='product-name']/h1")
	WebElement compronombre;

	@FindBy(xpath = "//div[@id='closeXButton']")
	WebElement pagina;

	@FindBy(xpath = "//span[contains(text(),'Garantía')]")
	WebElement garantia;

	@FindBy(xpath = "//div[contains(text(),'fondo')]")
	WebElement dimensiones;

	@FindBy(xpath = "//div[contains(text(),'kg')]")
	WebElement peso;

	@FindBy(xpath = "//span[@class='regular-price']/span")
	WebElement comproprecio;

	@FindBy(xpath = "//div[@class='menu-footer']")
	WebElement pie;

	@FindBy(xpath = "//a[contains(text(),'Dónde estamos')]")
	WebElement donde;

	@FindBy(xpath = "//span[@class='regular-price']//span[@class='price']")
	WebElement precioOtro;

	@FindBy(xpath = "//div[@class='product-name']//h1")
	WebElement nombreOtro;

	@FindBy(xpath = "//b[contains(text(),'Teléfono:')]")
	WebElement telefono;

	@FindBy(xpath = "//b[contains(text(),'Email:')]")
	WebElement correo;

	@FindBy(xpath = "//span[contains(text(),'Medidas')]")
	WebElement medidas;

	@FindBy(xpath = "//span[contains(text(),'Kilos')]")
	WebElement Otropeso;

	@FindBy(xpath = "//input[@id='qty']")
	WebElement canti;

	@FindBy(xpath = "//button[@class='button btn-cart']")
	WebElement carrito;
	@FindBy(xpath = "//span[contains(text(),'Clientes')]")
	WebElement cliente;

	@FindBy(xpath = "//input[@id='email']")
	WebElement email;

	@FindBy(xpath = "//input[@id='pass']")
	WebElement contra;

	@FindBy(xpath = "//span[contains(text(),'Iniciar sesión')]")
	WebElement entrar;
	@FindBy(xpath = "//p[@class='hello']")
	WebElement nombreusuario;

	@FindBy(xpath = "//dt[@class='last odd']")
	WebElement desplegarNumero;

	@FindBy(xpath = "//li[1]//div[3]//div[1]//div[3]//p[1]//button[1]")
	WebElement comprar;

	@FindBy(xpath = "//input[@title='Cantidad']")
	WebElement cantidad;

	@FindBy(xpath = "//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@class='button btn-proceed-checkout btn-checkout']")
	WebElement pedido;

	@FindBy(xpath = "//td[@class='product-cart-actions']//button[@name='update_cart_action']")
	WebElement botonActualizar;

	public void esperador(Integer milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Componente(final WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(this.driver, this);
	}

	public void quitarcookies() {
		cookies.click();
	}

	public void ordenar() {
		ordenar.click();

		orden.isSelected();
	}

	public void seleccionoprimero() {
		nombreproductoprimero = nombreproducto.getText().toString();
		precioproductoprimero = precioproducto.getText().replace("€", " ").replace(",", ".").trim();
		// System.out.println("Nombre del producto" + nombreproductoprimero );
		// System.out.println("Precio del producto" + precioproductoprimero );
		nombreproducto.click();
	}

	public void entrar(String correo, String contras) {
		cliente.click();
		email.sendKeys(correo);
		contra.sendKeys(contras);
		entrar.click();
	}

	public void comprobarnombre(String nombre) {
		Assert.assertTrue("No es el mismo", nombreusuario.getText().toString() != nombre);

	}

	public void comprobarnombreprecio() {
		Assert.assertTrue("No es el mismo nombre", nombreproductoprimero != compronombre.getText().toString());
		Double precio1 = Double.parseDouble(precioproductoprimero);
		Double precio2 = Double
				.parseDouble(comproprecio.getText().toString().replace("€", " ").replace(",", ".").trim());
		Assert.assertTrue("No es el mismo nombre", precio1 != precio2);
	}

	public void ponerfoco() {
		new Actions(driver).moveToElement(pie).click().perform();
		donde.click();
	}

	public void escribirTlfCorreo() {
		String cadena = telefono.getText().toString() + ", " + correo.getText().toString();
		MyUtils.escribir("datos.txt", cadena);
	}

	public void agregarficherootro() {
		esperador(3000);
		nombreproductoprimero = nombreOtro.getText().toString();
		precioproductoprimero = precioOtro.getText().toString();
		String cadena = "Nombre Producto: " + nombreOtro.getText().toString() + ", Precio: "
				+ precioOtro.getText().toString() + "," + garantia.getText().toString() + ", Medidas:"
				+ medidas.getText().toString().replace("Medidas ", " ").trim() + ", Peso: "
				+ Otropeso.getText().toString();
		MyUtils.escribir("datos.txt", cadena);
	}

	public void seleccionarcantidad(String cantidad) {
		canti.clear();
		esperador(3000);
		canti.sendKeys(cantidad);
	}

	public void pulsocarrito() {
		carrito.click();
	}

	public void selecionarMenu(String catalogo, String familia) {
		driver.findElement(By.xpath("//a[@class='level0 has-children'][contains(text(),'" + catalogo + "')]")).click();

		driver.findElement(By.xpath("//li[8]//a[1]//img[@alt='" + familia + "']")).click();
	}

	public void filtar(String numero, String marca) {
		esperador(8000);
		pagina.click();
		driver.findElement(
				By.xpath("//dd[@class='a-opn even']//a[@class='amshopby-attr'][contains(text(),'" + marca + "')]"))
				.click();

		desplegarNumero.click();

		driver.findElement(By.xpath("//dd[@class='last odd current']//a[@class='amshopby-attr'][contains(text(),'"
				+ numero + " micrófonos')]")).click();
	}

	public void comprar() {
		comprar.click();
	}

	public void cambiarnumero(String num) {
		cantidad.clear();
		cantidad.sendKeys(num);
		cantid = Integer.parseInt(num);
		esperador(3000);
		botonActualizar.click();
		esperador(3000);
	}

	public void pedido() {
		pedido.click();
		System.out.println("Pedido realizado");
		esperador(8000);
	}
}