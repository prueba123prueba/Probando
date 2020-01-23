package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Carritocompra {
	
	private WebDriver driver;
	public static Actions action;
	private WebDriverWait wait;


	public Carritocompra(final WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(this.driver, this);
	}
	
	
	public void jota() {
		System.out.println("lere lere lere");
	}
}
