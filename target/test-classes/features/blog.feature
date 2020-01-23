Feature: test
@run
Scenario Outline: test1 

	Given Entro en la <pagina>
	And Seleccionamos el botón <boton>
	Then Ordeno los resulados por <orden> y <clase>
	And Seleccionar el primer articulo y hacer un pantallazo
   
	 

Examples:

	|pagina						  |boton    |orden  	   |clase	|
	|https://www.masquesonido.com/|Blog 	|Más Relevantes|sort-by |