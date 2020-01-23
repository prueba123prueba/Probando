package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefsBuscador extends BaseSteps {

	@Given("^Entro en la(.*)$")
	public void inicio(String pagina) {
		buscador.entrarMasqueSonido(pagina);
	}
	@And("^En el buscador escribo una (.*) y pulso el boton buscar$")
	public void busqueda(String nombrebuscar) {
		buscador.busqueda(nombrebuscar);
	}
	@Then("^Ordeno los resulados por (.*) y (.*)$")
	public void Ordenar(String orden,String clase) {
		buscador.ordenar(orden,clase);
	}
	@And("^Selecciono el primer resultado$")
	public void pinchoPrimero() {
		buscador.pinchoPrimero();
	}
	@And("^Guardo nombre caracteristicas y garantia$")
	public void guardarDatos() {
		buscador.guardarDatos();
	}	
}