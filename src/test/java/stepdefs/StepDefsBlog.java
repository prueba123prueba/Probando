package stepdefs;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefsBlog extends BaseSteps{
	@Given("^Seleccionamos el botón (.*)$")
	public void entrarBoton(String boton) {
		blog.seleccionar(boton);
	}	
	
	@Given("^Seleccionar el primer articulo y hacer un pantallazo$")
	public void prirmerArt() {
		blog.primeraArt();
	}    
	
}
