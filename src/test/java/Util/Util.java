package Util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
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

//import com.GOT.utils.ErrorTestlink;
//import com.GOT.utils.excepciones.AssertException;
//import com.GOT.utils.excepciones.ElementoNoEncontrado;
//import com.GOT.utils.excepciones.XpathNoValidoException;

import cucumber.api.PendingException;

public class Util {
	protected static WebDriver driver;
	public static final String PREPRODUCCION = "preUrl";
	private static final String fileName = "casosError.txt";
	public static final int MAXIMO_INTENTOS = 4;
	public static final int SEGUNDO = 1000;
	public static final int MEDIO_SEGUNDO = 500;
	public static final int CUARTO_SEGUNDO = 250;
	public static final int UNSEGUNDO = 1000;
	public static final int DOSSEGUNDOS = 2000;
	public static final int CINCOSEGUNDOS = 5000;
	public static final int TIMEOUT_5 = 5;
	public static final int TIMEOUT_10 = 10;
	public static final int TIMEOUT_20 = 20;
	public static final int TIMEOUT_30 = 30;
	public static final int TIMEOUT_40 = 40;
	public static final int TIMEOUT_90 = 90;
	public static final int NUMERO_1 = 1;
	public static final int NUMERO_2 = 2;
	public static final int NUMERO_3 = 3;
	public static final int NUMERO_4 = 4;
	public static final int NUMERO_5 = 5;
	public static final int NUMERO_6 = 6;
	public static final int NUMERO_7 = 7;
	public static final int NUMERO_8 = 8;
	public static final int NUMERO_9 = 9;
	public static final int NUMERO_10 = 10;
	public static final int NUMERO_11 = 11;
	public static final int NUMERO_12 = 12;
	public static final int NUMERO_13 = 13;
	public static final int NUMERO_16 = 16;
	public static final int NUMERO_75 = 75;
	public static final int FINAL_MES = 28;
	public static final int RESOLUCION_ANCHO = 1920;
	public static final int RESOLUCION_ALTO = 1080;
	public static final int NUMERO_60 = 60;
	public static final int NUMERO_65 = 65;

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
//        if (navegador.toLowerCase().contains("headless")) {
		final Dimension targetSize = new Dimension(Util.RESOLUCION_ANCHO, Util.RESOLUCION_ALTO);
		driver.manage().window().setSize(targetSize);
//        } else {
//            driver.manage().window().maximize();
//        }
	}

	// Carga la ULR desde un json con los entornos
	public static String loadUrlFromJSON(final String destino) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader("src" + File.separator + "test" + File.separator + "" + "resources"
					+ File.separator + "properties" + File.separator + "" + "environment.json"));
			JSONObject jsonObject = (JSONObject) obj;
			String url = null;
			if (destino.equals("preUrl"))
				url = (String) jsonObject.get(PREPRODUCCION);
			return url;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
//    public void manejarExcepciones(final Throwable e) throws Throwable {
//        String mensajeError = "";
//        String linea = "";
//        if (e instanceof AssertionError) {
//            StackTraceElement[] traza = e.getStackTrace();
//            for (StackTraceElement trazaActual : traza) {
//                if (trazaActual.toString().contains("pageObject")) {
//                    linea = trazaActual.toString();
//                }
//            }
//            new ErrorTestlink("Error de Assert: " + e.getMessage());
//            throw new AssertException(e.getMessage() + "\n\t en " + linea);
//        } else if (e instanceof InvalidSelectorException) {
//            String aux = extraerElementoXpathExcepcion(e.getMessage(), e);
//            StackTraceElement[] traza = e.getStackTrace();
//            for (StackTraceElement trazaActual : traza) {
//                if (trazaActual.toString().contains("pageObject")) {
//                    linea = trazaActual.toString();
//                }
//            }
//            mensajeError = "El Xpath: " + aux + " no es valido\n\ten " + linea + " ";
//            new ErrorTestlink(mensajeError);
//            throw new XpathNoValidoException(mensajeError);
//        } else if (e instanceof NoSuchElementException) {
//            String aux = extraerElementoXpathExcepcion(e.getMessage(), e);
//            StackTraceElement[] traza = e.getStackTrace();
//            for (StackTraceElement trazaActual : traza) {
//                if (trazaActual.toString().contains("pageObject")) {
//                    linea = trazaActual.toString();
//                }
//            }
//            mensajeError = "No se encuentra el elemento: " + aux + "\n\t en " + linea;
//            new ErrorTestlink(mensajeError);
//            throw new ElementoNoEncontrado(mensajeError);
//        } else if (e instanceof TimeoutException) {
//            String aux = extraerElementoXpathExcepcion(e.getMessage(), e);
//            StackTraceElement[] traza = e.getStackTrace();
//            for (StackTraceElement trazaActual : traza) {
//                if (trazaActual.toString().contains("pageObject")) {
//                    linea = trazaActual.toString();
//                }
//            }
//            if(!aux.equals("timeout")){
//	            mensajeError = "Tiempo de espera de " + tiempoEsperaTimeoutException(e.getMessage()) + " segundos "
//	                    + "superado para el elemento: " + aux + "\n\t en " + linea;
//            }else{
//            	mensajeError = aux;
//            }
//            new ErrorTestlink(mensajeError);
//            throw new PendingException(mensajeError);
//        } else {
//            StackTraceElement[] traza = e.getStackTrace();
//            for (StackTraceElement trazaActual : traza) {
//                if (trazaActual.toString().contains("pageObject")) {
//                    linea = trazaActual.toString();
//                }
//            }
//            throw new Exception(e.getMessage() + "\n\t en " + linea);
//        }
//    }
	
}
