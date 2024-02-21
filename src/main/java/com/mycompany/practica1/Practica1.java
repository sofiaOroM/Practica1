
package com.mycompany.practica1;
import static com.mycompany.practica1.Practica1.Wordle;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Practica1 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner (System.in);     
        int opcion;
        do {
            System.out.println("Bienvenido, escoge que la opción que deseas iniciar");
            System.out.println("1. Wordle");
            System.out.println("2. Basketball");
            System.out.println("3. 2048");
            System.out.println("4. Salir");
            opcion = scanner.nextInt();
        switch (opcion){
            case 1: 
                Wordle(scanner);
                break;
            case 2:
                Basketball(scanner);
                break;
            case 3:
                juego2048(scanner);
                break;
            case 4:
                System.out.println("Hasta luego"+"\n"+"---------- Fin del Programa ----------");
            default:
            }
        } while (opcion!=4);
    }    
    static void Wordle(Scanner scanner){
        int cuentaIniciosLocal=0;
        int cuentaPerdidoLocal=0;
        int cuentaIntentosLocal=0;
        String palabraoculta;
        String palabraadivinada;
        int intentos=6;
        ArrayList <String> suposiciones=new ArrayList <>();
        
        System.out.println("Escribe la palabra de 5 letras que estará oculta");
        palabraoculta= scanner.next().toUpperCase();
        System.out.println("\n"+"\n"+"\n"+"\n"+"\n"+"\n");
        cuentaIniciosLocal++;
    
        while (intentos>0) {
            System.out.println("¡Es tu momento de adivinar!");
            palabraadivinada=scanner.next().toUpperCase();
            
            if (palabraadivinada.equals(palabraoculta)){
                System.out.println("¡Ganaste! Descubriste la palabra oculta:"+palabraoculta);
                break;
            }   else {
                System.out.println("Intentos anteriores:"+suposiciones);
                System.out.println("Intentalo otra vez!");
                
                String color ="";
                for (int i=0;i<palabraoculta.length();i++){
                    if (palabraadivinada.charAt(i)==palabraoculta.charAt(i)){
                        System.out.print ("\u001B[32m" + palabraadivinada.charAt(i) + "\u001B[0m");
                    } else if(palabraoculta.contains(String.valueOf(palabraadivinada.charAt(i)))){
                        System.out.print("\u001B[33m" + palabraadivinada.charAt(i) + "\u001B[0m"); // AMARILLO
                    } else {
                        System.out.print("\u001B[31m" + palabraadivinada.charAt(i) + "\u001B[0m"); // ROJO
                    }
                }
                System.out.print(color);
                 
                suposiciones.add(palabraadivinada);
                cuentaIntentosLocal++;
            }
        if (intentos==0){
            System.out.println("\n"+"Has agotado todos tus intentos" +"\n"+"La palabra oculta es:"+palabraoculta);
            cuentaPerdidoLocal++;
        }        
    }       
    }
    static void Basketball(Scanner scanner) {
        Random random =new Random();

        System.out.println("Ingrese el nombre del jugador 1:");
        String jugador1Nombre=scanner.next(); 
        System.out.println("Ingrese el nombre del jugador 2:");
        String jugador2Nombre=scanner.next();
        
        System.out.println("Ingrese la cantidad de turnos");
        int turnos= scanner.nextInt();

        int puntosJugador1=0;
        int puntosJugador2=0;
        
        for (int turno = 1; turno<turnos; turno++) {
            System.out.println("\nturno"+ turno);
            puntosJugador1 +=accionJugador(jugador1Nombre,puntosJugador1, accionJugadorOponente(jugador2Nombre), scanner);
            puntosJugador2 +=accionJugador(jugador2Nombre,puntosJugador2, accionJugadorOponente(jugador1Nombre), scanner);
        }        
        determinarGanador(jugador1Nombre,puntosJugador1,jugador2Nombre,puntosJugador2);
    }
                private static int accionJugador(String nombreJugador, int puntosJugador, String accionOponente, Scanner scanner){
                    System.out.println(nombreJugador + " elige una acción (l)argo, (c)orto, (t)iro libre");
                    String accion=scanner.next();
                    int puntosAccion=0;

                    if(accion.equalsIgnoreCase("l")){
                        puntosAccion = tiro(3, 0.65, accionOponente);
                    } else if (accion.equalsIgnoreCase("c")){
                        puntosAccion = tiro(2, 0.8, accionOponente);
                    } else if (accion.equalsIgnoreCase("t")){
                        puntosAccion = tiroLibre(2, 0.9);
                    }
                    return puntosAccion;
                }
                private static String accionJugadorOponente(String nombreJugador){
                    Random random =new Random();
                    double defensa= random.nextDouble();
                    if (defensa<0.5){
                        return "d";
                    } else {
                        return "f";
                    }
                }
                private static int tiro(int puntos, double probabilidad, String accionOponente) {
                    Random random = new Random(); 
                    double roll=random.nextDouble();
                    if (roll<probabilidad){
                        if (accionOponente.equals("d")){
                            double defensaRoll =random.nextDouble();
                            if (defensaRoll<0.15){
                                System.out.println("Fallaste por defensa cuerpo a cuerpo");
                                return 0;
                        } else if (defensaRoll <0.3){
                                System.out.println("Fallaste por defensa fuerte");
                                return 0;
                            }
                        }
                        System.out.println("Anotaste"+ puntos + "puntos");
                        return puntos;
                    } else {
                        System.out.println("Fallaste");
                        return 0;
                        }
                    }
                private static int tiroLibre (int puntos, double probabilidad){
                    Random random = new Random();
                    double roll=random.nextDouble();
                    if (roll< probabilidad){
                        System.out.println("Anotaste"+ puntos+"puntos");
                    return puntos;
                    } else {
                        System.out.println("fallaste");
                        return 0;
                    }
                }
                private static void determinarGanador(String jugador1Nombre, int puntosJugador1, String jugador2Nombre, int puntosJugador2) {
                        if (puntosJugador1 > puntosJugador2) {
                         System.out.println(jugador1Nombre + " ha ganado el juego con " + puntosJugador1 + " puntos.");
                        } else if (puntosJugador2 > puntosJugador1) {
                            System.out.println(jugador2Nombre + " ha ganado el juego con " + puntosJugador2 + " puntos.");
                        } else {
                            System.out.println("El juego ha terminado en empate con " + puntosJugador1 + " puntos cada uno.");
                        }
                }
    static void juego2048(Scanner scanner){
        final int size=4;
        final int meta=2048;
        int[][] tablero= new int [size][size];
        int puntaje;
        int cuentaInicioLocal=0;
        int cuentaGanadosLocal=0;
        int cuentaAbandonadoLocal=0;
        
        puntaje=0;
        tablero[0][0]=2;    
        cuentaInicioLocal++;
        
        Random random=new Random();
        boolean ganado=false;
        boolean juego_t =false;
            
            while (!ganado && !juego_t && !estalleno(tablero)){
                System.out.println("Puntuacion"+puntaje);
                printTablero(tablero);
                System.out.println("Mueve la tabla con las teclas W = arriba, A = izquierda, S = abajo, D = derecha, E = salir");
                String movimiento=scanner.next();
                
                if (movimiento.equalsIgnoreCase("W")){
                    juego_t = moveUp(tablero,puntaje);
                } else if (movimiento.equalsIgnoreCase("A")){
                    juego_t = moveLeft(tablero,puntaje);
                } else if (movimiento.equalsIgnoreCase("S")){
                    juego_t =moveDown(tablero,puntaje);
                } else if (movimiento.equalsIgnoreCase("D")){
                    juego_t = moveRight(tablero,puntaje);
                } else if (movimiento.equalsIgnoreCase("E")){
                    System.out.println("Has salido del juego");
                    cuentaAbandonadoLocal++;
                    break;
                     
                }
                if (juego_t){
                    System.out.println("Lo siento, has perdido el juego");
                } else{
                    int celda = random.nextInt(10)== 0 ? 2 : 4 ;
                    agregarCelda(tablero,meta,celda, random);
                    
                    if(gana(tablero,meta)){
                        System.out.println("¡Felicidades! Has ganado");
                        cuentaGanadosLocal++;
                        juego_t = true;
                    }
                }
            }
        }
        private static boolean estalleno(int[][] tablero){
            for (int i=0;i<tablero.length;i++){
                for (int j=0;j<tablero[i].length;j++){
                    if (tablero[i][j]==0){
                        return false;
                    }
                }
                    
            }
            return true;
        }
                       
        private static void printTablero(int[][] tablero){
            for (int i=0;i<tablero.length;i++){
                for (int j=0;j<tablero[i].length;j++){
                    System.out.print(tablero[i][j]+"");
                }
                System.out.println();
            }
        }
        private static boolean moveUp(int[][] tablero, int puntaje){
            boolean movimiento = false;
            for (int j = 0 ; j<tablero[0].length ; j++){
                for (int i=1; i< tablero.length; i++){                        
                    for (int k = 0; k < i; k++){
                        if (tablero[k][j]==0 && tablero[i][j]!=0){
                            tablero[k][j]= tablero[i][j];
                            tablero[i][j]=0;
                            movimiento = true;
                        } else if  (tablero[k][j]== tablero[i][j] && tablero[i][j]!=0){
                                    tablero[k][j] *=2; 
                                    tablero[i][j]=0;
                                    puntaje += tablero[k][j];
                                    movimiento =true;
                            }
                        }
                    }                
                }
                return movimiento;
            }
        private static boolean moveLeft(int[][] tablero, int puntaje){
            boolean movimiento =false;
            for (int i=0;i<tablero.length;i++){
                for (int j=1; j<tablero[i].length;j++){
                    for (int k=0;k<j;k++){
                        if (tablero[i][k]==0 && tablero[i][j]!=0){
                            tablero[i][k]= tablero[i][j];
                            tablero[i][j]=0;
                            movimiento = true;
                        } else if  (tablero[i][k]== tablero[i][j] && tablero[i][j]!=0){
                                    tablero[i][k] *=2; 
                                    tablero[i][j]=0;
                                    puntaje += tablero[i][k];
                                    movimiento =true;
                            }
                        }
                    }                
                }
                return movimiento;
            }
        private static boolean moveDown(int[][] tablero, int puntaje){
            boolean movimiento =false;
            for (int j=0;j<tablero[0].length;j++){
                for (int i=tablero.length; i>=0; i--){
                    for (int k=tablero.length-1; k>i ; k--){
                        if (tablero[k][j]==0 && tablero[i][j]!=0){
                            tablero[k][j]= tablero[i][j];
                            tablero[i][j]=0;
                            movimiento = true;
                        } else if  (tablero[k][j]== tablero[i][j] && tablero[i][j]!=0){
                                    tablero[k][j] *=2; 
                                    tablero[i][j]=0;
                                    puntaje += tablero[k][j];
                                    movimiento =true;
                            }
                        }
                    }                
                }
                return movimiento;
            }
        private static boolean moveRight(int[][] tablero, int puntaje){
            boolean movimiento =false;
            for (int i=0; i<tablero.length ;i++){
                for (int j=tablero[i].length; j>=0;j--){
                    for (int k=tablero[i].length-1;k>j;k--){
                        if (tablero[i][k]==0 && tablero[i][j]!=0){
                            tablero[i][k]= tablero[i][j];
                            tablero[i][j]=0;
                            movimiento = true;
                        } else if  (tablero[i][k]== tablero[i][j] && tablero[i][j]!=0){
                                    tablero[i][k] *=2; 
                                    tablero[i][j]=0;
                                    puntaje += tablero[i][k];
                                    movimiento =true;
                            }
                        }
                    }                
                }
                return movimiento;
            }

        private static void agregarCelda(int[][] tablero, int meta, int celda, Random random){
            int fila,col;
            do{                
                fila =random.nextInt(tablero.length);
                col =random.nextInt(tablero[0].length);          
            } while (tablero[fila][col]!=0);
                tablero[fila][col]=celda;             
        }
        
        private static boolean gana(int[][] tablero, int meta){
            for(int i=0; i<tablero.length ;i++){
                for (int j=0; j<tablero[i].length; j++){
                    if (tablero[i][j]==meta){
                        return true;
                        }
                    }
                }
            return false;
        }} 
   


