package stepdefs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageobject.Carritocompra;
import pageobject.Componente;
import pageobject.Buscador;
import pageobject.Blog;


public class BaseSteps {

	protected static WebDriver driver;
	protected static Buscador buscador;
	protected static Componente componente;
	protected static Blog blog;

	
	protected void initPageFactory(final WebDriver driver) {
		buscador = new Buscador(driver);
		componente = new Componente(driver);
		blog = new Blog(driver);

	}

	protected void initDriver() {
		String driverPath = System.getProperty("user.dir").concat("\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		initPageFactory(driver);
	}

}
