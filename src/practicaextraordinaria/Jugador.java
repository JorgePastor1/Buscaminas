/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaextraordinaria;


import java.io.Serializable;
/**
 *
 * @author j.pastor.2021
 */
public class Jugador implements Serializable{
    private int jugadas;
    private int ganadas;
    private int perdidas;
    private int minasEncontradas;
    private int minasRivales;
    private String nick;
    private String nombre;

    public Jugador(String nick, String nombre) {
        this.jugadas = 0;
        this.ganadas = 0;
        this.perdidas = 0;
        this.minasEncontradas = 0;
        this.minasRivales = 0;
        this.nick = nick;
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public int getJugadas() {
        return jugadas;
    }

    public void incrementarJugadas() {
        jugadas++;
    }

    public int getGanadas() {
        return ganadas;
    }

    public void incrementarGanadas() {
        ganadas++;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void incrementarPerdidas() {
        perdidas++;
    }

    public int getMinasEncontradas() {
        return minasEncontradas;
    }

    public void incrementarMinasEncontradas() {
        minasEncontradas++;
    }

    public int getMinasRivales() {
        return minasRivales;
    }

    public void incrementarMinasRivales() {
        minasRivales++;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre + 
                ", Partidas Jugadas: " + jugadas +
                ", Partidas Ganadas:" + ganadas +
                ", Partidas Perdidas: " + perdidas +
                ", Minas Encontradas:" + minasEncontradas +
                ", Minas Rivales=" + minasRivales;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)return true;
        if (o == null)return false;
        if (getClass() != o.getClass())return false;
        Jugador j = (Jugador) o;
        return (this.nombre.equals(j.nombre));
    }
    
}
