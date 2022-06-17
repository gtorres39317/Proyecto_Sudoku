/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author doble
 */
public class Tiempo{

    private Timer timer = new Timer();
    private int segundos = 0;

    //Clase interna que funciona como contador
    class contador extends TimerTask {

        @Override
        public void run() {
            segundos++;
            //System.out.println("segundo: " + segundos);
        }
    }

    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void contar() {
        this.segundos = 0;
        timer = new Timer();
        timer.schedule(new contador(), 0, 1000);
    }

    //Detiene el contador
    public void detener() {
        timer.cancel();
    }

    //Metodo que retorna los segundos transcurridos
    public int getSegundos() {
        return this.segundos;
    }
}
