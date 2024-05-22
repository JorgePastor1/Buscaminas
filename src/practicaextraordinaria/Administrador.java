/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaextraordinaria;
import java.io.*;
import java.util.*;
/**
 *
 * @author j.pastor.2021
 */
public class Administrador extends Jugador implements Serializable{
    private ArrayList<Jugador> listaJugadores;
    
    public Administrador(String nick, String nombre){
        super(nick, nombre);
        listaJugadores= new ArrayList<>();
    }
    
    public static ArrayList<Jugador> cargarJugadores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Jugadores.dat"))) {
            ArrayList<Jugador> listaJugadores = (ArrayList<Jugador>) ois.readObject();
            // Validar y limpiar la lista de jugadores
            listaJugadores.removeIf(jugador -> jugador.getNombre() == null || jugador.getNombre().isEmpty());
            return listaJugadores;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de jugadores no encontrado. Creando una nueva lista.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la lista de jugadores: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    
    public static void guardarJugadores(ArrayList<Jugador> nuevosJugadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Jugadores.dat"))) {
            oos.reset();
            oos.writeObject(nuevosJugadores);
            System.out.println("Jugadores guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los jugadores: " + e.getMessage());
        }
    }
    public void darAltaJugador(String nick, String nombre){
        for(Jugador jugador : listaJugadores){
            if(jugador.getNick().equals(nick)){
                return;
            }
        }
        listaJugadores.add(new Jugador(nick, nombre));
    }
    
    public void darBajaJugador(String nick){
        listaJugadores.removeIf(jugador->jugador.getNick().equals(nick));
    }
        
    public void cargarJugadoresDesdeFichero(ArrayList<String> datosJugadores){
        for (String dato : datosJugadores){
            String[] partes = dato.split("#");
            if(partes.length == 2){
                String nick = partes[0];
                String nombre = partes [1];
                if(listaJugadores.stream().noneMatch(j->j.getNick().equals(nick))){
                    darAltaJugador(nick, nombre);
                }
            }
        }
    }
    
    public ArrayList<Jugador> obtenerJugadores(){
        return listaJugadores;
    }

    public void obtenerInfoPartidas(ArrayList<Partida> partidas){
        for (Partida partida : partidas){
            System.out.println("Partida: "+ partida.getJugador1().getNick() + "vs " + partida.getJugador2().getNick());
            System.out.println("Marcador: "+ partida.getMinasJ1() + "- " + partida.getMinasJ2());
            System.out.println("Tablero inicial descubierto:");
            partida.imprimirTableroInicial();
            System.out.println("Secuencia de movimientos:");
            partida.imprimirSecuenciaMovimientos();
        }
    }
}
