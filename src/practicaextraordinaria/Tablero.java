/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaextraordinaria;
import java.io.Serializable;
import java.util.Random;
/**
 *
 * @author j.pastor.2021
 */
public class Tablero implements Serializable{

    private Casilla[][] casillas;
    private static final int NUMMINAS =35;
    private static final int TAM = 10;

    public Tablero() {
        casillas = new Casilla[TAM][TAM];
        inicializarCasillas();
        colocarMinas();
        inicializarTablero();
    }
    
    private void inicializarCasillas(){
        for(int i=0; i<TAM; i++){
            for(int j=0; j<TAM; j++){
                casillas[i][j] = new Casilla();
            }
        }
    }
    
    private void colocarMinas(){
        Random random = new Random();
        int minasColocadas = 0;
        while(minasColocadas <NUMMINAS){
            int fila = random.nextInt(TAM);
            int columna = random.nextInt(TAM);
            if(!casillas[fila][columna].tieneMina()){
                casillas[fila][columna].colocarMina();
                minasColocadas++;
            }
        }
    }
    
    private void calcularMinasAdyacentes(){
        for(int i=0; i<TAM; i++){
            for(int j=0; j<TAM; j++){
                if(!casillas[i][j].tieneMina()){
                    int minasAdy = contarMinasAdyacentes(i,j);
                    casillas[i][j].setMinasAdyacentes(minasAdy);
                }
            }
        }
    }
    
    private int contarMinasAdyacentes(int fila, int columna){
        int minas = 0;
        for(int i = -1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                if(nuevaFila >= 0 && nuevaFila < TAM && nuevaColumna >=0 && nuevaColumna <TAM){
                    minas++;
                }
            }
        }
        return minas;
    }
    
    public boolean descubrirCasilla(int fila, int columna, Jugador jugador){
        Casilla casilla=casillas[fila][columna];
        if(casilla.estaDescubierta()){
            return false;
        }
        
        casilla.descubrir(jugador.getNick());
        if(casilla.tieneMina()){
            return true;
        } else if (casilla.getMinasAdyacentes() == 0){
            descubrirAdyacentes(fila, columna, jugador);
        }
        return false;
    }
    
    private void descubrirAdyacentes(int fila, int columna, Jugador jugador){
        for(int i=-1; i<=1; i++){
            for (int j = -1; j<=1; j++){
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                if(nuevaFila >= 0 && nuevaFila < TAM && nuevaColumna >=0 && nuevaColumna <TAM){
                    descubrirCasilla(nuevaFila, nuevaColumna, jugador);
                }
            }
        }
    }
    
    public Casilla[][] getCasillas() {return casillas;}
    
    private void inicializarTablero(){
        Random ran = new Random();
        for(int i =0; i<TAM; i++){
            for(int j =0; j<TAM; j++){
                casillas[i][j] = new Casilla();
            }
        }
        
        int minasColocadas = 0;
        while(minasColocadas < NUMMINAS){
            int fila = ran.nextInt(TAM);
            int columna = ran.nextInt(TAM);
            if(!casillas[fila][columna].tieneMina()){
            casillas[fila][columna].colocarMina();
            minasColocadas++;
            }
        }
        
        for(int i =0; i<TAM; i++){
            for(int j =0; j<TAM; j++){
                if(!casillas[i][j].tieneMina()){
                    casillas[i][j].setMinasAdyacentes(contarMinasAdyacentes(i, j));
                }
            }
        }
    }
    
    public boolean esMina(int fila, int columna){
        return casillas[fila][columna].tieneMina();
    }
    
    public void imprimirTablero(){
        for(int i =0; i<TAM; i++){
            for(int j =0; j<TAM; j++){
                System.out.print(casillas[i][j] + " ");
            }
            System.out.println();
        }
    }
}
