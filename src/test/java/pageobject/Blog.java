package pageobject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import stepdefs.StepCommons;


public class Blog {
	
		private WebDriver driver;
		public static Actions action;
		private WebDriverWait wait;
        static String nombreProducto;
        static String precioProducto;		

		@FindBy(xpath="//a[contains(text(),'Blog')]")
		WebElement botonSel;
			
		
		@FindBy(xpath="//div[@class='postWrapper'][1]//div[@class='postTitle']//a")
		WebElement titulo;		
		
		public Blog(final WebDriver driver) {
			this.driver = driver;
			this.action = new Actions(driver);
			this.wait = new WebDriverWait(driver, 20);
			PageFactory.initElements(this.driver, this);
		}

		public void seleccionar(String boton) {
			
			botonSel.click();
		}

		public void primeraArt() {
			titulo.click();
			try {
				StepCommons.screenshot("Pantallazo");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	
		
		
	
}