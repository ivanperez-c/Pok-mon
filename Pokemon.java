/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

/**
 *
 * @author r.alegre.2019
 */
import java.io.*;
import java.util.*;
import java.lang.*;
import java.lang.NullPointerException;
class Ser implements Serializable {
	int id;//identificador del ser
	String nombre; //nombre del ser
	int categoria; //{1-agua, 2-fuego, 3-roca, 4-veneno}
	int prob; //probabilidad de que salga entre 1 y 100
	int vida; //entre 20 y 50
	int fuerza; //fuerza entre 1 y 10
}
class Objeto implements Serializable {
	int id;//identificador del objeto
	String nombre; //nombre del objeto
	int tipoOb; //{1-caza, 2-vida, 3-lucha, 4-atrayente...}
	int prob; //probabilidad de que salga entre 1 y 50
	Boolean suerte; //{poca, mucha} los objetos de tipo 4-atrayente solo mucha
	Double fuerzaExtra; //solo pueden tenerlo los objetos de tipo 2-vida y 3-lucha
}

public class Pokemon {
public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
    	//zona declaración de variables y constantes
    	final int MAXSERES = 16, MAXOBJETOS = 11;
    	int menu = 0, topeSeres = 1, topeObjetos = 1, eleccion, nivel;
    	Scanner teclado = new Scanner(System.in);
    	Ser[] listaSeres = new Ser[MAXSERES];
    	Objeto[] bandoleraObjetos = new Objeto[MAXOBJETOS];
    	//fin zona declaración de varabes y constantes
    	System.out.println("Videojuego Pokemon del GRUPO A Formado por: ");
    	System.out.println("Alegre Marcos, Roberto");
    	System.out.println("Burgaleta de la Peña, Pablo");
    	System.out.println("Duran Cerro, Javier");
    	System.out.println("Higuero López, Juan");
    	System.out.println("Mijarra Benítez, Raquel");
    	System.out.println("Pérez Ciruelos, Iván");
    	System.out.println("-------------------------------------------------------------------");
    	do {
        	System.out.println("Vamos a jugar a Pokemon Go, ¿Por donde empezamos?");
        	System.out.println("Opciones del juego");
        	System.out.println("1. Inserta seres y objetos");
        	System.out.println("2. Insertar ser u objetos es posición determinada");
        	System.out.println("3. Modificar ser");
        	System.out.println("4. Eliminar ser");
        	System.out.println("5. Desprenderse de un objeto");
        	System.out.println("6. Mostar seres y obejros");
        	System.out.println("7. Mostar seres y obejetos ordenados alfabéticamente por el nombre");
        	System.out.println("8. Buscar ser u objeto");
        	System.out.println("9. Aumento de vidas");
        	System.out.println("10. Guardar todos los seres u objetos a fichero de texto");
        	System.out.println("11. Cargar situación de la última partida desde fichero de texto");
        	System.out.println("12. Guardar estado del PJ");
        	System.out.println("13. Cargar PJ");
        	System.out.println("14. Salir");
        	System.out.println("Introduzca su opción: ");
        	menu = teclado.nextInt();
        	switch (menu) {
            	case 1:
                	System.out.println("INTRODUCIR SERES Y OBJETOS");
                	System.out.println("¿Que nivel eres?");
                	nivel = teclado.nextInt();
                	while (nivel < 1 || nivel > 40) {
                    	System.out.println("Nivel erróneo, por favor, introduzca un nivel entre 1 y 40");
                    	nivel = teclado.nextInt();
                	}
                	eleccion = nivel;
                	if (eleccion >= 30 && eleccion <= 40) {
                    	System.out.println("Nivel demasiado alto para emplear ésta opción");
                	} else {
                    	topeSeres = IntroducirSeres(listaSeres, topeSeres);
                    	topeObjetos = IntroducirObjetos(bandoleraObjetos, topeObjetos);
                	}
                	break;
            	case 2:
                	System.out.println("INTRODUCIR SERES Y OBJETOS EN POSICIÓN DETERMINADA");
                	System.out.println("¿Que nivel eres?");
                	nivel = teclado.nextInt();
                	while (nivel < 1 || nivel > 40) {
                    	System.out.println("Nivel erróneo, por favor, introduzca un nivel entre 1 y 40");
                    	nivel = teclado.nextInt();
                	}
                	eleccion = nivel;
                	if (eleccion >= 1 && eleccion <= 29) {
                    	System.out.println("Nivel demasiado bajo para emplear ésta opción");
                	} else {
                    	topeSeres = IntroducirSeresOrdenados(listaSeres, topeSeres);
                    	topeObjetos = IntroducirObjetosOrdenados(bandoleraObjetos, topeObjetos);
                	}
                	break;
            	case 3:
                	System.out.println("*******************MODIFICAR SER*********************");
                	ModificarSer(listaSeres, topeSeres);
                	break;
            	case 4:
                    topeSeres=eliminarSer(listaSeres, topeSeres);
                    
                	break;
            	case 5:
                    DesprenderseDeUnObjeto( bandoleraObjetos,topeObjetos, teclado);
                    break;
            	case 6:
                    System.out.println(" ");
                    System.out.println("*********** MOSTRAR SERES Y OBJETOS ****************");
                    System.out.println(" ");
                    mostrarSeresyObjetos(listaSeres, topeSeres, bandoleraObjetos, topeObjetos);

                        
                	break;
            	case 7:
                    System.out.println(" ");
                    System.out.println("*********** MOSTRAR SERES Y OBJETOS ORDENADOS ALFABETICAMENTE ****************");
                    System.out.println(" ");
                    mostrarSeresyObjetosOrdenados(listaSeres, topeSeres, bandoleraObjetos, topeObjetos);

                	break;
            	case 8:
                    System.out.println("*********** MOSTRAR SER U OBJETO ****************");
                    System.out.println("¿Que quieres buscar? 1.Ser  2.Objeto");
                    eleccion=teclado.nextInt();
                    while(eleccion<1 || eleccion>2){   
                        System.out.println("respuesta no valida");
                        System.out.println("Escribe de nuevo el id");
                        eleccion=teclado.nextInt();
                    }
                    if(eleccion== 1){
                        BuscarSer(listaSeres,teclado);
                    }
                    if(eleccion==2){
                        BuscarObjeto(bandoleraObjetos,teclado);
                }
                	break;
            	case 9:
                    System.out.println("*************** AUMENTO DE VIDA ****************");
                    AumentarVida(listaSeres, teclado, topeSeres);
                	break;
            	case 10:
                    System.out.println("*******************GUARDAR SERES Y OBJETOS EN FICHERO***********");
	            GuardarSeresYObjetos(listaSeres,bandoleraObjetos, topeSeres, teclado,topeObjetos);

                	break;
            	case 11:
                	break;
            	case 12:
                    System.out.println("*************************** GUARDAR SERES Y OBJETOS EN DISCO *********************************");
                    GuardarPJDisco(listaSeres,bandoleraObjetos, topeSeres, teclado, topeObjetos);
                	break;
            	case 13:
                    CargarPJ( topeSeres,topeObjetos,listaSeres,bandoleraObjetos);
                	break;
            	case 14:
                    System.out.println("adios");
                	break;
        	}
    	} while (menu < 14);
	}//fin main
	////////////////////////////////////   CASE 1	///////////////////////////////////////
	private static int IntroducirSeres(Ser[] listaSeres, int topeSeres) {
    	//zona declararación de variables
    	int categoria, probabilidad, vida, fuerza, respuestaser = 1, respuestaint;
    	Scanner teclado = new Scanner(System.in);
    	//fin zona declaración de variables
    	System.out.println("************************CREAR LISTA DE SERES********************************");
    	for (int j = 1; j < listaSeres.length; j++) {
        	listaSeres[j] = new Ser();
    	}
    	while (respuestaser == 1 && topeSeres < 16) {
        	listaSeres[topeSeres].id = topeSeres;
        	System.out.println("Introduzca el nombre del ser " + topeSeres + ":");
        	teclado.nextLine();
        	listaSeres[topeSeres].nombre = teclado.nextLine();
        	System.out.println("Introduce su categoría del ser " + topeSeres + " {1-agua, 2-fuego, 3-roca, 4-veneno}:");
        	categoria = teclado.nextInt();
        	if (categoria < 1 || categoria > 4) {
            	System.out.println("Categoría errónea, por favor introduzca una categoría permitida");
            	categoria = teclado.nextInt();
        	} else {
            	listaSeres[topeSeres].categoria = categoria;
        	}
        	probabilidad = (int) Math.round((Math.random() * 99) + 1);
        	listaSeres[topeSeres].prob = probabilidad;
        	System.out.println("Introduzca la vida del ser " + topeSeres + " {valores permitidos enre 20 y 50}:");
        	vida = teclado.nextInt();
        	if (vida < 20 || vida > 50) {
            	System.out.println("Nivel de vida erróneo, por favor introduzca un valor permitido");
            	vida = teclado.nextInt();
        	} else {
            	listaSeres[topeSeres].vida = vida;
        	}
        	System.out.println("Introduzca la fuerza del ser " + topeSeres + " {valores permitidos enre 1 y 10}:");
        	fuerza = teclado.nextInt();
        	if (fuerza < 1 || fuerza > 10) {
            	System.out.println("Nivel de vida erróneo, por favor introduzca un valor permitido");
            	fuerza = teclado.nextInt();
        	} else {
            	listaSeres[topeSeres].fuerza = fuerza;
        	}
        	topeSeres++;
        	System.out.println("¿Quieres introducir más seres? 1.-Si 2.-No");
        	respuestaint = teclado.nextInt();
        	if (respuestaint < 1 || respuestaint > 2) {
            	System.out.println("Respuesta incorrecta, por favor introduzca una respuesta permitida");
        	} else {
            	respuestaser = respuestaint;
        	}
    	}
    	System.out.println("Ya has introducido todos los seres");
    	return topeSeres;
	}
	private static int IntroducirObjetos(Objeto[] bandoleraObjetos, int topeObjetos) {
    	//zona declararación de variables
    	int respuestaobjeto = 1, respuestaout, tipo, probabilidadOb;
    	Scanner teclado = new Scanner(System.in);
    	//fin zona declaración de variables
    	System.out.println("********************************CREAR LISTA DE OBJETOS*************************");
    	for (int j = 1; j < bandoleraObjetos.length; j++) {
        	bandoleraObjetos[j] = new Objeto();
    	}
    	while (respuestaobjeto == 1 && topeObjetos < 11) {
        	bandoleraObjetos[topeObjetos].id = topeObjetos;
        	System.out.println("Introduzca el nombre del objeto " + topeObjetos + ":");
        	teclado.nextLine();
        	bandoleraObjetos[topeObjetos].nombre = teclado.nextLine();
        	System.out.println("Introduzca el tipo de objeto " + topeObjetos + ": {1-caza, 2-vida, 3-lucha, 4-atrayente}");
        	tipo = teclado.nextInt();
        	if (tipo < 1 || tipo > 4) {
            	System.out.println("Tipo erróneo, por favor introduzca un valor permitido");
            	tipo = teclado.nextInt();
        	} else {
            	bandoleraObjetos[topeObjetos].tipoOb = tipo;
        	}
        	probabilidadOb = (int) ((Math.random() * 50));
        	bandoleraObjetos[topeObjetos].prob = probabilidadOb;
        	if (tipo == 4) {
            	System.out.println("La suerte del objeto " + topeObjetos + " de tipo 4-Atrayente es mucha");
            	bandoleraObjetos[topeObjetos].suerte = true;
        	} else {
            	System.out.println("Introduzca la suerte del objeto " + topeObjetos + ": True-Mucha False-Poca");
            	bandoleraObjetos[topeObjetos].suerte = teclado.nextBoolean();
        	}
        	if (tipo == 2 || tipo == 3) {
            	System.out.println("Como el objeto " + topeObjetos + " es de tipo 2-Vida o 3-Lucha se le asigna un valor de fuerza extra");
            	System.out.println("Introduzca el valor de la fuerza extra");
            	bandoleraObjetos[topeObjetos].fuerzaExtra = teclado.nextDouble();
        	} else {
            	bandoleraObjetos[topeObjetos].fuerzaExtra = 0.0;
        	}
        	topeObjetos++;
        	System.out.println("¿Quieres introducir más objetos? 1.-Si 2.-No");
        	respuestaout = teclado.nextInt();
        	if (respuestaout < 1 || respuestaout > 2) {
            	System.out.println("Respuesta incorrecta, por favor introduzca una respuesta permitida");
        	} else {
            	respuestaobjeto = respuestaout;
        	}
    	}
    	System.out.println("Ya has introducido todos los objetos");
    	return topeObjetos;
	}
//////////////////////////////////////// CASE 2 ///////////////////////////////////////////////////
	private static int IntroducirSeresOrdenados(Ser[] listaSeres, int topeSeres) {
    	//zona declaracion de variables
    	int nivel, pos, respuesta1 = 1, respuesta2, identificador = 1, categoria, probabilidad, vida, fuerza;
    	Scanner teclado = new Scanner(System.in);
    	//fin zona declaración de variables
    	System.out.println("********************************CREAR LISTA DE SERES***********************************");
    	for (int j = 1; j < listaSeres.length; j++) {
        	listaSeres[j] = new Ser();
    	}
    	while (respuesta1 == 1 && topeSeres < 16) {
        	System.out.println("Introduzca la posición en la que quieres colocar el ser: (Máximo 15)");
        	pos = teclado.nextInt();
        	if (pos < 1 || pos > 15) {
            	System.out.println("No se puede insertar un ser en esa posición");
        	} else {
            	listaSeres[pos].id = pos;
            	System.out.println("Introduzca el nombre del ser " + identificador + ":");
            	teclado.nextLine();
            	listaSeres[pos].nombre = teclado.nextLine();
            	System.out.println("Introduce su categoría del ser " + identificador + " {1-agua, 2-fuego, 3-roca, 4-veneno}:");
            	categoria = teclado.nextInt();
            	if (categoria < 1 || categoria > 4) {
                	System.out.println("Categoría errónea, por favor introduzca una categoría permitida");
                	categoria = teclado.nextInt();
            	} else {
                	listaSeres[pos].categoria = categoria;
            	}
            	probabilidad = (int) Math.round((Math.random() * 99) + 1);
            	listaSeres[pos].prob = probabilidad;
            	System.out.println("Introduzca la vida del ser " + identificador + " {valores permitidos enre 20 y 50}:");
            	vida = teclado.nextInt();
            	if (vida < 20 || vida > 50) {
                	System.out.println("Nivel de vida erróneo, por favor introduzca un valor permitido");
                	vida = teclado.nextInt();
            	} else {
                	listaSeres[pos].vida = vida;
            	}
            	System.out.println("Introduzca la fuerza del ser " + identificador + " {valores permitidos enre 1 y 10}:");
            	fuerza = teclado.nextInt();
            	if (fuerza < 1 || fuerza > 10) {
                	System.out.println("Nivel de vida erróneo, por favor introduzca un valor permitido");
                	fuerza = teclado.nextInt();
            	} else {
                	listaSeres[pos].fuerza = fuerza;
            	}
            	identificador++;
            	if (topeSeres < pos) {
                	topeSeres = pos;
                	topeSeres++;
            	}
            	System.out.println("¿Quieres introducir más seres? 1.-Si 2.-No");
            	respuesta2 = teclado.nextInt();
            	if (respuesta2 < 1 || respuesta2 > 2) {
                	System.out.println("Respuesta incorrecta, por favor introduzca una respuesta permitida");
            	} else {
                	respuesta1 = respuesta2;
            	}
        	}
    	}
    	System.out.println("Ya has introducido todos los seres");
    	return topeSeres;
	}
	private static int IntroducirObjetosOrdenados(Objeto[] bandoleraObjetos, int topeObjetos) {
    	//zona declaracion de variables
    	int respuesta3 = 1, respuesta4, place, tipo, probabilidadOb, contador = 1;
    	Scanner teclado = new Scanner(System.in);
    	//fin zona declaración de variables
    	System.out.println("*******************************CREAR LISTA DE OBJETOS*****************************************");
    	for (int j = 1; j < bandoleraObjetos.length; j++) {
        	bandoleraObjetos[j] = new Objeto();
    	}
    	while (respuesta3 == 1 && topeObjetos < 11) {
        	System.out.println("Introduzca la posición en la que quieres colocar el objeto: (Máximo 15)");
        	place = teclado.nextInt();
        	if (place < 1 || place > 15) {
            	System.out.println("No se puede insertar un ser en esa posición");
        	} else {
            	bandoleraObjetos[place].id = place;
            	System.out.println("Introduzca el nombre del objeto " + contador + ":");
            	teclado.nextLine();
            	bandoleraObjetos[place].nombre = teclado.nextLine();
            	System.out.println("Introduzca el tipo de objeto " + contador + ": {1-caza, 2-vida, 3-lucha, 4-atrayente}");
            	tipo = teclado.nextInt();
            	if (tipo < 1 || tipo > 4) {
                	System.out.println("Tipo erróneo, por favor introduzca un valor permitido");
                	tipo = teclado.nextInt();
            	} else {
                	bandoleraObjetos[place].tipoOb = tipo;
            	}
            	probabilidadOb = (int) ((Math.random() * 50));
            	bandoleraObjetos[place].prob = probabilidadOb;
            	if (tipo == 4) {
                	System.out.println("La suerte del objeto" + contador + "de tipo 4-Atrayente es mucha");
                	bandoleraObjetos[place].suerte = true;
            	} else {
                	System.out.println("Introduzca la suerte del objeto " + contador + ": True-Mucha False-Poca");
                	bandoleraObjetos[place].suerte = teclado.nextBoolean();
            	}
            	if (tipo == 2 || tipo == 3) {
                	System.out.println("Como el objeto " + contador + " es de tipo 2-Vida o 3-Lucha se le asigna un valor de fuerza extra");
                	System.out.println("Introduzca el valor de la fuerza extra");
                	bandoleraObjetos[place].fuerzaExtra = teclado.nextDouble();
            	} else {
                	bandoleraObjetos[place].fuerzaExtra = 0.0;
            	}
            	contador++;
            	if (topeObjetos < place) {
                	topeObjetos = place;
                	topeObjetos++;
            	}
            	System.out.println("¿Quieres introducir más objetos? 1.-Si 2.-No");
            	respuesta4 = teclado.nextInt();
            	if (respuesta4 < 1 || respuesta4 > 2) {
                	System.out.println("Respuesta incorrecta, por favor introduzca una respuesta permitida");
            	} else {
                	respuesta3 = respuesta4;
            	}
        	}
    	}
    	System.out.println("Ya has introducido todos los objetos");
    	return topeObjetos;
	}
	////////////////////////////////   CASE 3   ////////////////////////////////////////////////////
	private static void ModificarSer(Ser[] listaSeres, int topeSeres) {
    	//zona declaración de variables
    	int identificador, opcion, nuevaProbabilidad, nuevaVida, nuevaFuerza, respuesta = 1, respuesta2;
    	Scanner teclado = new Scanner(System.in);
    	//fin zona delcaración de variables
    	do {
        	System.out.println("Tu lista de seres actual es: ");
        	for (int i = 1; i < topeSeres; i++) {
            	System.out.println("Ser " + i + ": " + listaSeres[i].nombre + " Categoría: " + listaSeres[i].categoria + " Probabilidad: " + listaSeres[i].prob + " Vida: " + listaSeres[i].vida + " Fuerza: " + listaSeres[i].fuerza);
        	}
        	System.out.println("Introduzca el identificador del ser a modificar: ");
        	identificador = teclado.nextInt();
        	while (identificador < 1 || identificador > 15) {
            	System.out.println("Has introducido un identificador errónero, por favor introduzca uno válido");
            	identificador = teclado.nextInt();
        	}
        	if (identificador > 0 && identificador < 16) {
            	System.out.println("¿Que campo quieres modificar? 1-Probabilidad 2-Vida 3-Fuerza");
            	opcion = teclado.nextInt();
            	if (opcion < 1 || opcion > 3) {
                	System.out.println("Ese campo no existe o no se puede modificar, por favor inroduzca un campo válido");
                	opcion = teclado.nextInt();
            	} else if (opcion < 4 && opcion > 0) {
                	if (opcion == 1) {
                    	System.out.println("Introduzca la nueva probabilidad");
                    	nuevaProbabilidad = teclado.nextInt();
                    	listaSeres[identificador].prob = nuevaProbabilidad;
                	}
                	if (opcion == 2) {
                    	System.out.println("Introduzca la nueva vida {valores permitidos enre 20 y 50}");
                    	nuevaVida = teclado.nextInt();
                    	if (nuevaVida < 20 || nuevaVida > 50) {
                        	System.out.println("Nivel de vida erróneo, por favor introduzca un valor permitido");
                    	} else {
                        	listaSeres[identificador].vida = nuevaVida;
                    	}
                	}
                	if (opcion == 3) {
                    	System.out.println("Introduzca la nueva fuerza {valores permitidos enre 1 y 10}");
                    	nuevaFuerza = teclado.nextInt();
                    	if (nuevaFuerza < 1 || nuevaFuerza > 10) {
                        	System.out.println("Nivel de fuerza erróneo, por favor introduzca un valor permitido");
                    	} else {
                        	listaSeres[identificador].fuerza = nuevaFuerza;
                    	}
                	}
                	System.out.println("¿Quieres seguir modificando a los seres? 1-Si 2-No");
                	respuesta2 = teclado.nextInt();
                	if (respuesta2 < 1 || respuesta2 > 2) {
                    	System.out.println("Respuesta incorrecta, por favor introduzca una respuesta permitida");
                	} else {
                    	respuesta = respuesta2;
                	}
            	}
        	}
    	} while (respuesta == 1);
	}
	////////////////////////////////   CASE 4   ////////////////////////////////////////////////////
        public static int eliminarSer(Ser[]listaSeres,int topeSeres){
               System.out.println("************************ELIMINAR SER******************************");
                int i=1;
                   do{
                      listaSeres[i]=listaSeres[i+1];
                      i++;
                    }while(i<topeSeres);
                       topeSeres=topeSeres-1;
                       return topeSeres;
    }

	
	////////////////////////////////   CASE 5   ////////////////////////////////////////////////////
        private static void DesprenderseDeUnObjeto (Objeto[]bandoleraObjetos,int topeObjetos, Scanner teclado){
         System.out.println("*********************************DESPRENDERSE DE UN OBJETO************************");
                    int posicionobjeto,respuesta,respuesta2=1;
                    do{
                    System.out.println("Introduzca la posicion del objeto que desea eliminar entre 1 y "+topeObjetos);
                    posicionobjeto=teclado.nextInt();
                    if(posicionobjeto<1||posicionobjeto>topeObjetos){
                        do{
                            System.out.println("Introduzca un valor que sirva");
                            posicionobjeto=teclado.nextInt();
                        }while(posicionobjeto<1||posicionobjeto>topeObjetos);
                    }
                        System.out.println("Objeto: " + posicionobjeto);
                        System.out.println("Nombre: "+ bandoleraObjetos[posicionobjeto].nombre);
                        System.out.println("Tipo de objeto: "+ bandoleraObjetos[posicionobjeto].tipoOb);
                        System.out.println("Prob: "+ bandoleraObjetos[posicionobjeto].prob);
                        System.out.println("suerte: "+ bandoleraObjetos[posicionobjeto].suerte);
                        System.out.println("Fueza extra: "+ bandoleraObjetos[posicionobjeto].fuerzaExtra);
                        System.out.println("¿Esta seguro de que quiere eliminar este objeto? 1/Si 2/No");
                        respuesta=teclado.nextInt();
                        if(respuesta<1||respuesta>2){
                            do{
                                System.out.println("Introduzca una respuesta que sirva 1/Si 2/No");
                                respuesta=teclado.nextInt();
                            }while (respuesta<1||respuesta>2);
                            }
                        if(respuesta==2){
                            do{
                            System.out.println("¿Quieres eliminar otro objeto? 1/Si 2/No");
                            respuesta2=teclado.nextInt();
                            }while (respuesta2<1||respuesta2>2);
                        }
                        if(respuesta==1){
                            System.out.println("Dime el nombre del objeto");
                            bandoleraObjetos[posicionobjeto].nombre=teclado.nextLine();
                            bandoleraObjetos[posicionobjeto].nombre=teclado.nextLine();
                            System.out.println("Dime el tipo de objeto  1/caza, 2/vida, 3/lucha, 4/atrayente");
                            bandoleraObjetos[posicionobjeto].tipoOb=teclado.nextInt();
                            if(bandoleraObjetos[posicionobjeto].tipoOb<1||bandoleraObjetos[posicionobjeto].tipoOb>4){
                                do{
                                System.out.println("Introduzca un valor que sirva");
                                bandoleraObjetos[posicionobjeto].tipoOb=teclado.nextInt();
                                }while(bandoleraObjetos[posicionobjeto].tipoOb<1||bandoleraObjetos[posicionobjeto].tipoOb>4);
                            }
                            System.out.println("Dime la suerte del objeto(True/False)");
                            bandoleraObjetos[posicionobjeto].suerte=teclado.nextBoolean();
                            if(bandoleraObjetos[posicionobjeto].tipoOb==2||bandoleraObjetos[posicionobjeto].tipoOb==3){
                            System.out.println("Dime la fuerza extra del objeto");
                            bandoleraObjetos[posicionobjeto].fuerzaExtra=teclado.nextDouble();
                                    if(bandoleraObjetos[posicionobjeto].fuerzaExtra<0||bandoleraObjetos[posicionobjeto].fuerzaExtra>100){
                                do{
                                System.out.println("Introduzca un valor que sirva");
                                bandoleraObjetos[posicionobjeto].fuerzaExtra=teclado.nextDouble();
                                }while(bandoleraObjetos[posicionobjeto].fuerzaExtra<0||bandoleraObjetos[posicionobjeto].fuerzaExtra>100);
                                    }
                        }
                        do{
                        System.out.println("¿Quieres eliminar otro objeto? 1/Si 2/No");
                        respuesta2=teclado.nextInt();
                        }while (respuesta2<1||respuesta2>2);
                        }
                    }while(respuesta2==1);
        }


        
	////////////////////////////////   CASE 6   ////////////////////////////////////////////////////
         private static void mostrarSeresyObjetos (Ser[] listaSeres, int topeSeres, Objeto[] bandoleraObjetos, int topeObjetos){
         //Zona de declaración de variables
         int contRoca=0, contAgua=0, contVeneno=0, contFuego=0, probAlta=0, probBaja=100;
         String serAlto="", serBajo="";
         System.out.println("Seres que se encuentran en la lista: ");
         for (int i=1; i<topeSeres; i++ ){
             System.out.println("El ser numero "+i+" es: " +listaSeres[i].nombre);
         }
         System.out.println("Objetos que se encuentran en la bandolera: ");
         for (int i=1; i<topeObjetos; i++ ){
             System.out.println("El objeto numero "+i+" que se encuentra en la bandolera es: "+bandoleraObjetos[i].nombre);
         }
         for (int i=1; i<topeSeres; i++) {
             if (listaSeres[i].categoria == 1) {
                 contAgua++;             
         }
             if (listaSeres[i].categoria == 2) {
                 contFuego++;             
         }
             if (listaSeres[i].categoria == 3) {
                 contRoca++;             
         }
             if (listaSeres[i].categoria == 4) {
                 contVeneno++;             
         }              
         }
             System.out.println("El número de seres en la categoria de Agua es: "+contAgua);  
             System.out.println("El número de seres en la categoria de Fuego es: "+contFuego); 
             System.out.println("El número de seres en la categoria de Roca es: "+contRoca); 
             System.out.println("El número de seres en la categoria de Veneno es: "+contVeneno); 
         for (int i=1; i<topeSeres; i++) {
          if (probAlta<listaSeres[i].prob) {
              probAlta=listaSeres[i].prob;
               serAlto = listaSeres[i].nombre;      
          }
          if (probBaja>listaSeres[i].prob){
              probBaja = listaSeres[i].prob;
              serBajo = listaSeres[i].nombre;
          }
         }
         System.out.println("El ser con mas probabilidad de aparecer es " +serAlto+ " con una probabilidad de aparecer de: " +probAlta);
         System.out.println("El ser con menor probabilidad de aparecer es " +serBajo+ " con una probabilidad de aparecer de: " +probBaja);
     }   

	////////////////////////////////   CASE 7   ////////////////////////////////////////////////////
             private static void mostrarSeresyObjetosOrdenados(Ser[] listaSeres, int topeSeres, Objeto[] bandoleraObjetos, int topeObjetos){
        String aux1, aux2 ="";
        String serOrdenado[]= new String[16];
        String objetoOrdenado[]= new String[16];
        for (int i=1; i < topeSeres; i++){
            serOrdenado[i] = listaSeres[i].nombre;
            objetoOrdenado[i]= bandoleraObjetos[i].nombre;
        }
        System.out.println("La lista de seres ordenados alfabeticamente es : ");
        for (int i=1; i < topeSeres; i++){
           for (int j = 1; j < topeSeres-i; j++) {
               if (serOrdenado[j].charAt(0) > (serOrdenado[j+1].charAt(0))){
                  aux1 = serOrdenado[j];
                  serOrdenado[j] = serOrdenado[j+1];
                  serOrdenado[j+1] = aux1;
               }
           }
           System.out.println(listaSeres[i].nombre);
        }
        System.out.println("La lista de objetos ordenados alfabeticamente es : ");
        for (int i=1; i < topeObjetos; i++){
           for (int j = 1; j < topeObjetos-i; j++) {
               if (objetoOrdenado[j].charAt(0) > (objetoOrdenado[j+1].charAt(0))){
                  aux2 = objetoOrdenado[j];
                  objetoOrdenado[j] = objetoOrdenado[j+1];
                  objetoOrdenado[j+1] = aux2;
               }
           }
           System.out.println(bandoleraObjetos[i].nombre);
        }
    }  

	////////////////////////////////   CASE 8   ////////////////////////////////////////////////////
        private static void BuscarSer(Ser[] listaSeres, Scanner teclado) {
            //zona de declaracion de variables
            int id,i;
            boolean encontrado;
            id=0;
            i=1;
            encontrado=false;
            System.out.println("Escriba el id del ser ");
            id=teclado.nextInt();
            if(id<1 || id>15){
                System.out.println("El id introducido no es valido");
                System.out.println("Escriba de nuevo el id");
                id=teclado.nextInt();
            }
            while(i< listaSeres.length && !encontrado){
                if(id == listaSeres[i].id){
                  encontrado=true;  
                }else{
                   i++;
                }
            }
            if(encontrado){
                System.out.println("el ser se encuentra en la posicion: "+ i);
                System.out.println("El nombre del pokemon con id "+id+" es: "+ listaSeres[i].nombre);
                System.out.println("La categoria del pokemon con id "+ id +" es: "+ listaSeres[i].categoria);
                System.out.println("La probabilidad de que salga el pokemon con id "+ id +" es: "+ listaSeres[i].prob);
                System.out.println("La vida del pokemon con id "+ id +" es: "+ listaSeres[i].vida);
                System.out.println("La fuerza del pokemon con id "+ id +" es: "+ listaSeres[i].fuerza);
            }else{
                System.out.println(" no existe un pokemon en la lista con ese id");
            }
            }
        private static void BuscarObjeto (Objeto[] bandoleraObjetos, Scanner teclado){
           //zona de declaracion de variables
            int id,i;
            boolean encontrado;
            id=0;
            i=1;
            encontrado=false;
            System.out.println("Escriba el id del objeto ");
            id=teclado.nextInt();
            if(id<1 || id>15){
                System.out.println("El id introducido no es valido");
                System.out.println("Escriba de nuevo el id");
                id=teclado.nextInt();
            }
            while(i< bandoleraObjetos.length && !encontrado){
                if(id == bandoleraObjetos[i].id){
                  encontrado=true;  
                }else{
                   i++;
                }
            }
            if(encontrado){
                System.out.println("el objeto se encuentra en la posicion: "+ i);
                System.out.println("El nombre del objeto con id "+id+" es: "+ bandoleraObjetos[i].nombre);
                System.out.println("El tipo del objeto con id "+ id +" es: "+ bandoleraObjetos[i].tipoOb);
                System.out.println("La probabilidad de que salga el objeto con id "+ id +" es: "+ bandoleraObjetos[i].prob);
                System.out.println("La suerte del objeto con id "+ id +" es: "+ bandoleraObjetos[i].suerte);
                System.out.println("La fuerza extra que aporta el objeto con id "+ id +" es: "+ bandoleraObjetos[i].fuerzaExtra);
            }else{
                System.out.println(" no existe un objeto en la bandolera con ese id");
            }  
        }
	////////////////////////////////   CASE 9   ////////////////////////////////////////////////////
        private static void AumentarVida(Ser[] listaSeres, Scanner teclado, int topeSeres){
            //zona de declaracion de variables
            int porcentaje;
            // fin de la zona 
            System.out.println("Escribe el porcentaje en el que quieres aumentar la vida");
            porcentaje=teclado.nextInt();
            for(int i=1; i<topeSeres; i++){
                listaSeres[i].vida=listaSeres[i].vida+((listaSeres[i].vida*porcentaje)/100);
                if (listaSeres[i].vida>50){
                    listaSeres[i].vida=50;
                }
            }
         }
	////////////////////////////////   CASE 10   ////////////////////////////////////////////////////
        private static void GuardarSeresYObjetos (Ser[]listaSeres,Objeto[] bandoleraObjetos,int topeSeres, Scanner teclado,int topeObjetos) throws IOException {
	//zona declaracion de variables
	int eleccion;
	//fin zona
	do{
         System.out.println("¿Que deseas guardar?1. Solo seres, 2. Solo objetos, 3. Ambos");
	     eleccion=teclado.nextInt();
	     switch (eleccion){
                case 1:
		try{
		      PrintStream flujo;
                      flujo = new PrintStream(new FileOutputStream("pjseres.txt"));
                      for(int i=0;i<=topeSeres;	i++){
                     // flujo.print(listaSeres[i].id +" "+listaSeres[i].nombre+" "+listaSeres[i].categoria+" "+listaSeres[i].prob+" "+listaSeres[i].vida+" "+listaSeres[i].fuerza); 
                      flujo.print(listaSeres[i].id);
                      flujo.print(listaSeres[i].nombre);
                      flujo.print(listaSeres[i].categoria);
                      flujo.print(listaSeres[i].prob);
                      flujo.print(listaSeres[i].vida);
                      flujo.print(listaSeres[i].fuerza);
                      
                 }
                  flujo.close();
                }catch (FileNotFoundException e){
                System.out.println("Fichero no encontrado");
                }
                
             break;
                case 2:
	    //if(eleccion==2){
                try{
		PrintStream flujo;
                flujo = new PrintStream(new FileOutputStream("pjobjetos.txt"));
                for(int i=0;i<=topeObjetos;i++){
                     flujo.println(bandoleraObjetos[i].id+bandoleraObjetos[i].nombre+bandoleraObjetos[i].tipoOb+listaSeres[i].prob+bandoleraObjetos[i].suerte+bandoleraObjetos[i].fuerzaExtra);     
                }
                flujo.close();
                }catch (FileNotFoundException e){
                System.out.println("Fichero no encontrado");
                }catch(IOException exc){
                System.out.println("Otro tipo de excepcion");
                }
                
              break;
               //if(eleccion==3){
               case 3:
                try{
		    PrintStream flujo;
                    flujo = new PrintStream(new FileOutputStream("pjseresyobjetos.txt"));
                    for(int i=0;i<=topeObjetos;	i++){
                    flujo.print(bandoleraObjetos[i].id+bandoleraObjetos[i].nombre+bandoleraObjetos[i].tipoOb+listaSeres[i].prob+bandoleraObjetos[i].suerte+bandoleraObjetos[i].fuerzaExtra);     
                    }for(int i=0;i<=topeSeres;	i++){
                    flujo.print(listaSeres[i].id+listaSeres[i].nombre+listaSeres[i].categoria+listaSeres[i].prob+listaSeres[i].vida+listaSeres[i].fuerza);     
                    }
                flujo.close();  
                }catch (FileNotFoundException e){
                System.out.println("Fichero no encontrado");
                }catch(IOException exc){
                System.out.println("Otro tipo de excepcion");
                }
                }
             break;
	
    }while(eleccion>3||eleccion<1);
    }

	////////////////////////////////   CASE 11  ////////////////////////////////////////////////////
	////////////////////////////////   CASE 12  ////////////////////////////////////////////////////
    private static void GuardarPJDisco(Ser[]listaSeres,Objeto[]bandoleraObjetos,int topeSeres, Scanner teclado ,int topeObjetos) throws FileNotFoundException, IOException {
    
        System.out.println("*************Guardar estado del personaje a disco***************");
        try{
        FileOutputStream out=new FileOutputStream("seres.dat");
        ObjectOutputStream wr = new ObjectOutputStream(out); 
             for(int i=0;i<=topeSeres;i++){
                 wr.writeObject(listaSeres[i]);
             }
                 out.close();
        }catch(FileNotFoundException e){
         System.out.println("Fichero no encontrado");
        }catch(IOException exc){
        System.out.println("Otro tipo de excepción");
        }
        try{
         FileOutputStream out =new FileOutputStream("objetos.dat");
         ObjectOutputStream wr = new ObjectOutputStream(out);
              for(int i=0;i<=topeObjetos;i++){
                wr.writeObject(bandoleraObjetos[i]);
             }
               System.out.println("Estado del personaje guardado en disco"); 
               wr.close();
        }catch(FileNotFoundException e){
         System.out.println("Fichero no encontrado");
        }catch(IOException exc){
        System.out.println("Otro tipo de excepción");
        }
        }


	////////////////////////////////   CASE 13  ////////////////////////////////////////////////////
     private static void CargarPJ(int topeSeres,int topeObjetos,Ser[]listaSeres,Objeto[]bandoleraObjetos)throws FileNotFoundException, IOException, ClassNotFoundException{
        try{
            FileInputStream in=new FileInputStream("seres.dat");
            ObjectInputStream rea=new ObjectInputStream(in);
            for (int i=0;i<=topeSeres;i++){
            listaSeres[i]=(Ser)rea.readObject();
            System.out.println("Categoría del ser "+i+" es: "+listaSeres[i].categoria);
            System.out.println("Fuerza del ser "+i+" es: "+listaSeres[i].fuerza);
            System.out.println("Id del ser "+i+" es: "+listaSeres[i].id);
            System.out.println("Nombre del ser "+i+" es: "+listaSeres[i].nombre);
            System.out.println("Prob del ser "+i+" es: "+listaSeres[i].prob);
            System.out.println("Vida del ser "+i+" es: "+listaSeres[i].vida);
            }
        }catch(FileNotFoundException e){

}
  try{
            FileInputStream in=new FileInputStream("objetos.dat");
            ObjectInputStream rea=new ObjectInputStream(in);
            for (int i=0;i<=topeObjetos;i++){
            bandoleraObjetos[i]=(Objeto)rea.readObject();
            System.out.println("El tipo del objeto "+i+" es: "+bandoleraObjetos[i].tipoOb);
            System.out.println("la suerte del objeto "+i+" es: "+bandoleraObjetos[i].suerte);
            System.out.println("Id del ser "+i+" es: "+bandoleraObjetos[i].id);
            System.out.println("Nombre del ser "+i+" es: "+bandoleraObjetos[i].nombre);
            System.out.println("Prob del ser "+i+" es: "+bandoleraObjetos[i].prob);
            System.out.println("La fuerza extra del objeto "+i+" es: "+bandoleraObjetos[i].fuerzaExtra);
            }
        }catch(FileNotFoundException e){

}      
}
}

   
   