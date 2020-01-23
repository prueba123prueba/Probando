package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Buscador {
	
		private WebDriver driver;
		public static Actions action;
		private WebDriverWait wait;
        static String nombreProducto;
        static String precioProducto;		

		@FindBy(xpath="//div[@class='sort-by']/select[1]")
		WebElement ordenar;
		@FindBy(xpath="//div[@class='toolbar-bottom']//option[contains(text(),'Precio (ascendente)')]")
		WebElement orden;
		@FindBy(xpath="//input[@name='q']")
		WebElement nombrebus;
		@FindBy(xpath="//button[contains(text(),'Buscar')]")
		WebElement botonbuscar;	
		@FindBy(xpath="//span[contains(text(),'Garantía')]")
		WebElement garantia;
		
		
		@FindBy(xpath="//li[contains(text(),'Dimensiones')]")
		WebElement dimensiones;	
		

		@FindBy(xpath="//li[contains(text(),'kg')]")
		WebElement peso;	
		
		
		@FindBy(xpath="//div[@class='caracteristicas']")
		WebElement caracteristicas;
		
		@FindBy(xpath="//h2[@class='product-name']//a[1]")
		WebElement primero;			
		
		public Buscador(final WebDriver driver) {
			this.driver = driver;
			this.action = new Actions(driver);
			this.wait = new WebDriverWait(driver, 20);
			PageFactory.initElements(this.driver, this);
		}
		
		public void entrarMasqueSonido(String pagina){
			driver.get(pagina);
		}
		
		public void busqueda(String nombrebuscar) {
			quitarcookies();
			nombrebus.sendKeys(nombrebuscar);
			botonbuscar.click();
		}
		public void quitarcookies() {
			//cookies.click();
		}		
		
		public void ordenar(String orden2,String clase) {

			driver.findElement(By.xpath("//div[@class='"+ clase +"']//option[contains(text(),'"+ orden2 +"')]")).click();
			driver.findElement(By.xpath("//div[@class='"+ clase +"']//option[contains(text(),'"+ orden2 +"')]")).isSelected();
		
		}

		public void pinchoPrimero() {
			primero.click();
			
		}

		public void guardarDatos() {
			String cadena="Nombre Producto: " + Buscador.nombreProducto + "Características: " + caracteristicas.getText().toString() + " Precio: " + Buscador.precioProducto + "," + garantia.getText().toString() + ", Dimensión:" + dimensiones.getText().toString() + ", Peso: " + peso.getText().toString();
			MyUtils.escribir("datos.txt", cadena);	
		}
	
}