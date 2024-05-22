/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaextraordinaria;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author j.pastor.2021
 */
public class Partida implements Serializable{
    private static Jugador jugador1;
    private static Jugador jugador2;
    private int minasJ1;
    private int minasJ2;
    private boolean turnoJugador1;
    private boolean finalizada;
    private int ganador;

    private int numCasillasDescubiertas;
    private Resultado resultado;
    private Tablero tablero;
    private ArrayList<Jugador> listaJugadores;
    private ArrayList<String> secuenciaMovimientos;

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1=jugador1;
        this.jugador2=jugador2;
        this.tablero = new Tablero();
        this.minasJ1 = 0;
        this.minasJ2 = 0;
        this.turnoJugador1 = true;
        this.finalizada = false;
        this.ganador = 0;
        this.numCasillasDescubiertas = 0;
        this.listaJugadores = new ArrayList<>();
        this.secuenciaMovimientos = new ArrayList<>();
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public int getMinasJ1() {
        return minasJ1;
    }

    public int getMinasJ2() {
        return minasJ2;
    }
    
    public int getNumCasillasDescubiertas() {
        return numCasillasDescubiertas;
    }

    public Resultado getResultado() {
        return resultado;
    }
    
    public void incrementarCasillasDescubiertas() {
        numCasillasDescubiertas++;
    }
    
    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public boolean isPartidaFinalizada() {
        return finalizada;
    }

    public void setPartidaFinalizada(boolean partidaFinalizada) {
        this.finalizada = partidaFinalizada;
    }
    
    public Tablero getTablero() {return tablero;}
    
    public void iniciarPartida(){
        
    }
    
    public boolean realizarMovimiento(int fila, int columna){
        Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
        boolean minaEncontrada = tablero.descubrirCasilla(fila, columna, jugadorActual);
        secuenciaMovimientos.add((turnoJugador1 ? "J1" : "J2") + " " + fila + " " + columna);
        
        if(minaEncontrada){
            if(turnoJugador1){
                minasJ1++;
            }
            else{
                minasJ2++;
            }
            return true;
        }
        else {
            cambiarTurno();
            return false;
        }
    }
    
    private void cambiarTurno(){
        turnoJugador1 = !turnoJugador1;
    }
    
    public Jugador determinarGanador(){
        if(minasJ1==18){
            return jugador1;
        } else{
            return jugador2;
        }
    }
    
    public void imprimirTableroInicial(){
        tablero.imprimirTablero();
    }
    
    public void imprimirSecuenciaMovimientos(){
        for(String movimiento : secuenciaMovimientos){
            System.out.println(movimiento);
        }
    }
    
    public void guardarPartida(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Partida cargarPartida(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (Partida) ois.readObject(); 
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la partida. Se creará un nuevo archivo.");
            Partida partidaNueva = new Partida(jugador1, jugador2);
            partidaNueva.guardarPartida(nombreArchivo);
            return partidaNueva;
        }
    }
}
