/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resultados;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author doble
 */
public class Jugador implements Serializable{

    public Jugador(String nombre) {
        this.nombre = nombre;
        total = 0;
    }
    private int[] puntuacion;
    private int total;
    private String nombre;

    public Jugador(int[] puntuacion, String nombre) {
        this.puntuacion = puntuacion;
        this.nombre = nombre;
        total = 0;
    }

    public int[] getPuntuacion() {
        return puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPuntuacion(int[] puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        return Objects.equals(this.nombre, other.nombre);
    }
    @Override
    public String toString() {
        String punt="";
        for (int i = 1; i < puntuacion.length; i++){
            total += puntuacion[i];
            punt += "\n\tJuego "+(i)+": "+puntuacion[i];
        }
        return "      \n\tJugador: " + nombre + ",   \n   Puntuaciones" + punt 
                + "\n\t  Total:" + total +"\n";
    }
}
