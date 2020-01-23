package stepdefs;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import Util.Util;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class StepCommons extends BaseSteps {

//	@Before
//	public void setUp() {
//		initDriver();
//		initPageFactory(driver);
//	}

	public Scenario scenary;

	@Before
	public void setUp(final Scenario scenario) {
		String navegador = System.getProperty("browser");

		initDriver(navegador);
		// initDriver();
		initPageFactory(driver);
		scenary = scenario;
	}

//	    @After
//	    public void teardown() throws Exception {
//
//	        String error = "";
//	      
//	        
//	        if (ErrorTestlink.getMensajeError() != null && !scenary.getStatus().equals("passed")) {
//	            error = ErrorTestlink.getMensajeError();
//	            if (error.contains("Unable to")) {
//	                String elemento = error.substring(error.indexOf("'") + 1);
//	                error = elemento.substring(0, elemento.indexOf(",") - 2);
//	            }
//	        }
//
//	        if (driver != null) {
//	            if (!scenary.getStatus().equals("passed")) {
//	                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//	                scenary.embed(screenshot, "image/png");
//	            }
//	            driver.close();
//	            if(!navegador.toLowerCase().contains("firefox")) {
//	            driver.quit();
//	            }
//	           //utils.reportarTestLink(scenary, error);
//	        }
//	    }
	protected void initDriver(final String navegador) {

		final String os = System.getProperty("os.name").toLowerCase();
		if (navegador.toLowerCase().contains("chrome")) {
			if (os.contains("win")) {
				System.setProperty("webdriver.chrome.driver", "drivers" + File.separator + "chromedriver.exe");
			} else if (os.contains("nux")) {
				System.setProperty("webdriver.chrome.driver",
						"drivers" + File.separator + "linux" + File.separator + "chromedriver");
			}
			final ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments(Arrays.asList("--ignore-certificate-errors", "--ignore-ssl-errors=true",
					"--web-security=false", "--ssl-protocol=tlsv1"));

			if (navegador.toLowerCase().contains("headless")) {
				chromeOptions.addArguments("headless");
			}
			driver = new ChromeDriver(chromeOptions);

		} else if (navegador.toLowerCase().contains("firefox")) {
			if (os.contains("win")) {
				System.setProperty("webdriver.gecko.driver", "drivers" + File.separator + "geckodriver.exe");
			} else if (os.contains("nux")) {
				System.setProperty("webdriver.gecko.driver",
						"drivers" + File.separator + "linux" + File.separator + "geckodriver");
			}
			// ProfilesIni firProfiles = new ProfilesIni();
			// FirefoxProfile profile = firProfiles.getProfile("default");
			final FirefoxOptions firefoxOptions = new FirefoxOptions();
			ProfilesIni profileIni = new ProfilesIni();
			FirefoxProfile profile = profileIni.getProfile("default");

			// firefoxOptions.setProfile(profile);
			if (navegador.toLowerCase().contains("headless")) {
				firefoxOptions.setHeadless(true);
				firefoxOptions.setProfile(profile);
			}
			driver = new FirefoxDriver(firefoxOptions);

		} else if (navegador.toLowerCase().contains("ie")) {
			System.setProperty("webdriver.ie.driver", "drivers" + File.separator + "IEDriverServer.exe");
			final InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			// ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
			// true);
			ieOptions.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, Util.loadUrlFromJSON(""));
			ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			driver = new InternetExplorerDriver(ieOptions);

		} else if (navegador.toLowerCase().contains("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers" + File.separator + "MicrosoftWebDriver.exe");
			final EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
			edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			edgeOptions.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
			driver = new EdgeDriver(edgeOptions);

		} else {
			System.out.println("\nEl navegador '" + navegador + "' no es correcto\n");
		}
		driver.manage().timeouts().implicitlyWait(Util.TIMEOUT_10, TimeUnit.SECONDS);
//	        if (navegador.toLowerCase().contains("headless")) {
		final Dimension targetSize = new Dimension(Util.RESOLUCION_ANCHO, Util.RESOLUCION_ALTO);
		driver.manage().window().setSize(targetSize);
//	        } else {
//	            driver.manage().window().maximize();
//	        }
	}
//		protected void initDriver() {
//			String driverPath = System.getProperty("user.dir").concat("\\\\drivers\\chromedriver.exe");
//			System.setProperty("webdriver.chrome.driver", driverPath);
//			driver = new ChromeDriver();
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			driver.manage().window().maximize();
	//
//			initPageFactory(driver);
//		}

	@When("^Espera$")
	public void espera() throws Throwable {
		try {
			Thread.sleep(Util.SEGUNDO + Util.MEDIO_SEGUNDO);
		} catch (Throwable e) {
//			util.manejarExcepciones(e);
		}
	}

	@Given("^Voy a la pagina de inicio de (.*)$")
	public void pagina_inicio(final String enviroment) throws Throwable {
		try {
			driver.get(Util.loadUrlFromJSON(enviroment));
		} catch (Throwable e) {
//			util.manejarExcepciones(e);
		}
	}

	@After
	public void quit() {
		driver.quit();
	}

	public static void screenshot(String contenedor) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("imagenes/" + contenedor + ".png"));
	}

}
