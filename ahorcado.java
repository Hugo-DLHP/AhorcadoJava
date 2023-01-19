import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;

public class ahorcado { 
    public static void main(String[] args) throws InterruptedException, IOException {

        //VARIABLES
        Scanner leer = new Scanner(System.in);
        String palabra = "", dificultad, opcion, aux, mostrar_intentos = "", adivinar_palabra;
        char letra;
        int tamaño_palabra, tamaño_comprobar_palabra, aleatoria, adivinar;
        boolean comparacion = false, seguro = false, eleccion = false, comparar_palabras = false;
        int intentos = 0, contador_letras = 0;
        final int FACIL = 15;
        final int NORMAL = 10;
        final int DIFICIL = 5;


        //CREAMOS UNA LISTA DE PALABRAS
        String [] biblioteca_palabras = new String[] {"Ciclomotor", "Aeroplano", "Circunstancia", "Anexo", "Torbellino", "Alienado", "Procrastinar", "Coprofagia", "Inquisicion", "Fimosis", "Estrabismo"};
        /***************************************************************/


        /***************************************************************/


        //BUCLE DE LA ELECCION DE DIFICULTAD
        do {
            //ELECCION DE LA DIFICULTAD
            System.out.print("Este es el juego del ~AHORCADO~, antes de empezar elije el nivel de dificultad:\n");        
            System.out.println("1- Facil(15 intentos)\n2- Normal(10 intentos)\n3- Dificil(5 intentos)");
            System.out.print("¿Que dificultad elijes? ");
            dificultad = leer.next();

            switch (dificultad) {
                case "1":
                    dificultad = "Facil";
                    intentos = FACIL;
                    seguro = true;
                break;

                case "2":
                    dificultad = "Normal";
                    intentos = NORMAL;
                    seguro = true;
                break;

                case "3":
                    dificultad = "Dificil";
                    intentos = DIFICIL;
                    seguro = true;
                break;

                default:
                    System.out.println("\n\n\nOpcion no valida, elije de nuevo\n\n");
                break;
            }
            /***************************************************************/
        } while(seguro != true);
        /***************************************************************/       


        //BUCLE DE LA ELECCION SOLUCION
        do {
            System.out.print("\n\n");
            System.out.print("Antes de empezar dinos si quieres proponer tu la palabra o por el contrario quieres usar una palabra aleatoria:\n");
            System.out.print("1.- Introducir mi palabra.\n2.- Usar palabra aleatoria.\n");
            System.out.print("¿Que opcion elijes? ");
            opcion = leer.next();

            switch (opcion) {
                case "1":
                    opcion = "introducir una palabra";
                    eleccion = true;        
                    //PEDIR LA PALABRA
                    System.out.print("\nHas elegido el nivel " + dificultad + " y quieres " + opcion);
                    System.out.print("dime la palabra que tiene que adivinar: ");
                    palabra = leer.next(); 
                    /***************************************************************/
                break;

                case "2":
                    opcion = "usar palabra aleatoria";
                    System.out.print("Has elegido el nivel " + dificultad + " y quieres " + opcion);
                    aleatoria = (int) (Math.random()*(biblioteca_palabras.length - 0 + 1) + 0);
                    palabra = biblioteca_palabras[aleatoria];
                    eleccion = true;
                break;

                default:
                    System.out.println("\n\n\nOpcion no valida, elije de nuevo\n\n");
                break;
            }
        } while(eleccion != true);
        /***************************************************************/     


        //ASIGNAMOS TAMAÑO DEL ARRAY PALABRA
        tamaño_palabra = palabra.length();
        char [] palabra_oculta = palabra.toCharArray();
        palabra = palabra.toUpperCase();
        /***************************************************************/


        //ASIGNAMOS TAMAÑO DEL ARRAY COMPARAR_PALABRA
        char [] comprobar_palabra = palabra.toCharArray();
        tamaño_comprobar_palabra = comprobar_palabra.length;
        /***************************************************************/


        //ASIGNAMOS TAMAÑO DEL ARRAY DE LAAS LETRAS
        char [] letras_usadas = new char [intentos + tamaño_palabra];
        
        /***************************************************************/


        //AÑADIMOS A CADA ESPACIO DEL ARRAY UN ELEMENTO "_"
        for (int i = 0; i < tamaño_palabra; i++) {
            palabra_oculta[i] = '_';
        }
        /***************************************************************/


        //LIMPIAR CONSOLA
        limpiarConsola();
        /***************************************************************/


        //BUCLE DE LAS LETRAS
        do {
            //PEDIR LETRA QUE CREES QUE ESTA EN LA PALABRA
            System.out.println("\n");
            System.out.print("Dime una letra que creas que esta en la palabra: ");
            letra = leer.next().charAt(0);
            letra = Character.toUpperCase(letra);
            aux = String.valueOf(letra);
            letras_usadas[contador_letras] = letra;
            contador_letras++;
            /***************************************************************/     


            if (palabra.contains(aux)) {
                //REEMPLAZAR LETRA POR "_" DEL ARRAY palabra_oculta
                for (int i = 0; i < tamaño_comprobar_palabra; i++) {
                    if (comprobar_palabra[i] == letra) {
                        palabra_oculta[i] = letra;
                    }
                }
                /***************************************************************/
                mostrar_intentos = "Esa letra si esta, no pierdes ningun intento, te quedan: " + intentos;
            } else {
                //RESTAR INTENTOS
                intentos--;
                mostrar_intentos = "Esa letra no esta, has perdido 1 intento, te quedan: " + intentos;
                /***************************************************************/
            }


            //IMPRIMIR EL ARRAY DE LAS LETRAS USADAS
            System.out.println("Has dicho estas letras:");
            System.out.print("~");
            for(char caracter:letras_usadas) {
                System.out.print(caracter);
            }
            System.out.println("~");
            /***************************************************************/     
            

            //IMPRIMIR EL ARRAY DE LA PALABRA
            System.out.print("~");
            for(char caracter:palabra_oculta) {
                System.out.print(caracter);
            }
            System.out.println("~\n");
            /***************************************************************/


            //MOSTRAR INTENTOS
            System.out.println(mostrar_intentos);
            /***************************************************************/


            //ADIVINAR PALABRA
            System.out.println("\n");
            System.out.print("¿Quieres intentar adivinar la palabra? Si fallas perderas un intento.\n");
            System.out.print("1.- Adivinar palabra\n2.- Seguir poniendo letras\n");
            System.out.print("¿Que opcion elijes? ");
            adivinar = leer.nextInt();

            if (adivinar == 1) {
                System.out.println("\n");
                System.out.print("Introduce la palabra: ");
                adivinar_palabra = leer.next().toUpperCase();
                if (palabra.equals(adivinar_palabra)) {
                    comparar_palabras = true;
                } else {
                    intentos--;
                    System.out.print("Esa no es la palabra, pierdes un intento, te quedan: " + intentos);
                }
            }
            /***************************************************************/    


            //COMPARAR ARRAYS
            comparacion = Arrays.equals(palabra_oculta, comprobar_palabra);
            /***************************************************************/

            
        } while(comparacion != true && intentos != 0 && comparar_palabras != true);
        /***************************************************************/


        //FIN DEL JUEGO
        if (comparacion != true && comparar_palabras != true) {
            System.out.println("\n\n");
            System.out.println("Has perdido, te has quedado sin intentos. La palabra era ~" + palabra + "~");
            System.out.println("\n\n");
        } else {
            System.out.println("\n\n");
            System.out.println("Has acertado la palabra ~" + palabra + "~");
            System.out.println("\n\n");
        }
        /***************************************************************/
        leer.close();
    }

    public static void limpiarConsola() throws InterruptedException, IOException{

        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    }
    
}   
