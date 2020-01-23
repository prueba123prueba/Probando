Feature: test
#@run
Scenario Outline: test1 

	Given Entro en la <pagina>
	And En el buscador escribo una <palabra> y pulso el boton buscar
	Then Ordeno los resulados por <orden> y <clase>
	And Selecciono el primer resultado
	And Guardo nombre caracteristicas y garantia
	 

Examples:

	|pagina						  |palabra  |orden	   			|clase			|
	|https://www.masquesonido.com/|Karaoke	|Precio (ascendente)|toolbar-bottom |