/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaextraordinaria;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author j.pastor.2021
 */
public class Interfaz {
    private JFrame frame;
    private JPanel panel;
    private JButton[][] botones;
    private JLabel marcadorJ1;
    private JLabel marcadorJ2;
    private Partida partida;
    
    public Interfaz(Partida partida){
        this.partida = partida;
        inicializarGUI();
        
    }
    
    private void inicializarGUI(){
        frame = new JFrame("Buscaminas");
        panel = new JPanel(new GridLayout(10, 10));
        marcadorJ1 = new JLabel("Jugador 1: 0");
        marcadorJ2 = new JLabel("Jugador 2: 0");
        botones = new JButton[10][10];
        
        /*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        panel.setLayout(new GridLayout(10, 10));*/
        
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                botones[i][j] = new JButton();
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                panel.add(botones[i][j]);
                int fila = i;
                int columna = j;
                botones[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        manejarClick(fila, columna);
                    }
                });
            }
        }
        
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(marcadorJ1, BorderLayout.NORTH);
        frame.add(marcadorJ2, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void manejarClick(int fila, int columna){
        boolean minaEncontrada = partida.realizarMovimiento(fila, columna);
        actualizarBotones();
        actualizarMarcadores();
        if(minaEncontrada && (partida.getMinasJ1() == 18 || partida.getMinasJ2() == 18)){
            Jugador ganador = partida.determinarGanador();
            JOptionPane.showMessageDialog(frame, "El ganador es: " + ganador.getNick());
        }
    }
    
    private void actualizarBotones(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                Casilla casilla = partida.getTablero().getCasillas()[i][j];
                if(casilla.estaDescubierta()){
                    botones[i][j].setText(casilla.toString());
                    botones[i][j].setEnabled(false);
                }
            }
        }
    }
    
    public void actualizarMarcadores(){
        marcadorJ1.setText("Jugador 1: " + partida.getMinasJ1());
        marcadorJ2.setText("Jugador 2: " + partida.getMinasJ2());
    }
}
