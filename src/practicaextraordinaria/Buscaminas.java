/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicaextraordinaria;
import java.io.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author j.pastor.2021
 */
public class Buscaminas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Administrador admin = new Administrador("admin", "Administrador del Juego");
        
        admin.darAltaJugador("Jorge09", "Jorge Pastor");
        admin.darAltaJugador("Jdelaf", "Jose Arauna");
        
        ArrayList<Jugador> listaJugadores = admin.obtenerJugadores();
        Jugador jugador1 = listaJugadores.get(0);
        Jugador jugador2 = listaJugadores.get(1);
        
        Partida partida = new Partida(jugador1, jugador2);
        new Interfaz(partida);
    }
    
}
