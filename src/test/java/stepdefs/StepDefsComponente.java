package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class StepDefsComponente extends BaseSteps {
	
	@And("^Clientes y meto usuario y contraseña (.*) y (.*)$")
	public void entrar(String correo, String contra) {
		componente.entrar(correo, contra);
	}
	@And("^Comprobar nombre (.*)$")
	public void comprobarnombre(String nombre) {
		componente.comprobarnombre(nombre);
	}
	@Then("^Seleccionar el (.*) y la (.*)$")
	public void selecionarMenu(String catalogo,String familia) {
		componente.selecionarMenu(catalogo,familia);
	}
	@And("^En el panel de filtrar seleccionar (.*) y (.*)$")
	public void filtrar(String numero,String marca) {
		componente.filtar(numero,marca);
	}
	@And("^Pulsamos el botón de comprar$")
	public void comprar() {
		componente.comprar();
	}
	@Then("^En cantidad añadimos el (.*) y pulsamos el botón actualizar$")
	public void cambiarnumero(String cantidad) {
		componente.cambiarnumero(cantidad);
	}
	@And("^Pulsamos el botón de realizar pedido si no está bajamos la cantidad$")
	public void pedido() {
		componente.pedido();
	}	
	
}
