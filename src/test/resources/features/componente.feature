Feature: test
#@run
Scenario Outline: test1 

	Given Entro en la <pagina>
	And Clientes y meto usuario y contraseña <usuario> y <contraseña>
    And Comprobar nombre <nombre>
    Then Seleccionar el <catalogo> y la <familia>
    And En el panel de filtrar seleccionar <numero> y <marca>
    And Pulsamos el botón de comprar
    Then En cantidad añadimos el <cantidad> y pulsamos el botón actualizar
    And Pulsamos el botón de realizar pedido si no está bajamos la cantidad 
    
	 

Examples:

	|pagina						  |palabra  |orden	   |usuario					|contraseña |nombre			|catalogo  |familia		|numero		|marca				|cantidad 	|
	|https://www.masquesonido.com/|Karaoke	|ascendente|ylopez@grupo-sade.com	|Mk649Rj-	|Yolanda López	|Megafonía |Microfonía  |4			|Audio Music Systems|2			|	