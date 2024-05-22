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
public class Casilla implements Serializable{
    private boolean tieneMina;
    private boolean descubierta;
    private int minasAdyacentes;
    private String descubiertaPor;

    public Casilla() {
        this.tieneMina = false;
        this.minasAdyacentes =0;
        this.descubierta = false;
        this.descubiertaPor = null;
    }

    public boolean tieneMina(){
        return tieneMina;
    }
    
    public void colocarMina(){
        this.tieneMina = true;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }
    
    public boolean estaDescubierta(){
        return descubierta;
    }
    
    public void descubrir(String jugador){
        this.descubierta = true;
        this.descubiertaPor = jugador;
    }

    @Override
    public String toString() {
        if(!descubierta){
            return " ";
        }
        if(tieneMina){
            return "*" + descubiertaPor;
        }
        if(minasAdyacentes == 0){
            return "B";
        }
        return String.valueOf(minasAdyacentes);
    }
    
    
}
