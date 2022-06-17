/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sudoku;

import static GUI.GUI.c;
import static GUI.GUI.matriz;
import static GUI.GUI.matriz2;
import static java.lang.Math.round;
import java.util.Random;

/**
 *
 * @author doble
 */
public class Sudoku {

    public void construirSudoku() {
        //Variable m es la que se va a agregar a la matriz Sudoku, mientras que 
        //Variable o es la que va a validar el reinicio de la variable m en j=2
        int m, o = 1;
        for (int i = 0; i < 4; i++) {
            m = o;
            for (int j = 0; j < 4; j++) {
                if (m <= 4) {
                    matriz[i][j] = m;
                    m++;
                } else {
                    m = 1;
                    matriz[i][j] = m;
                    m++;
                }
            }
            o = m + 2;
            // valida primer salto de j0 a j1 asignando 3 a i0
            if (m == 5) {
                o = 3;
            }
            //Valida el salto de cuadrante vertical j1 a j2
            if (o > 4) {
                o = (o % 4) + 1;
            }
        }
    }

    public void numAleatorios(int tipo, int tipo2) {
        int w1, w2;
        int desde, hasta;
        if (tipo2 == 0) {
            desde = 0;
            hasta = 2;
        } else {
            desde = 2;
            hasta = 4;
        }
        Random aleatorio = new Random();

        w1 = aleatorio.nextInt(hasta - desde) + desde;
        do {
            w2 = aleatorio.nextInt(hasta - desde) + desde;
        } while (w1 == w2);
        if (tipo == 0) {
            intercambioFilas(w1, w2);
        } else if (tipo == 1) {
            intercambioColumnas(w1, w2);
        }
    }

    public void intercambioFilas(int w1, int w2) {
        int ward;
        for (int j = 0; j < 4; j++) {
            ward = matriz[w1][j];
            matriz[w1][j] = matriz[w2][j];
            matriz[w2][j] = ward;
        }
    }

    public void intercambioColumnas(int w1, int w2) {
        int wars;
        for (int j = 0; j < 4; j++) {
            wars = matriz[j][w1];
            matriz[j][w1] = matriz[j][w2];
            matriz[j][w2] = wars;
        }
    }

    public void cero(int ceroz) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz2[i][j] = matriz[i][j];
            }
        }
        //System.arraycopy(matriz, 0, matriz2, 0, 4);
        Random rc = new Random();
        for (int i = 0; i < ceroz; i++) {
            int f = rc.nextInt(4);
            int c = rc.nextInt(4);
            if (matriz2[f][c] != 0) {
                matriz2[f][c] = 0;
            } else {
                i--;
            }
        }
    }

    public void imprimirSudoku() {
        int cont = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matriz2[i][j] != 0) {
                    c[cont].setText(matriz2[i][j] + "");
                } else {
                    c[cont].setText("");
                    c[cont].setEditable(true);
                }
                cont++;
            }
        }
    }

    public Integer calcularPuntuacion(int tipo, double tiempo) {
        int puntuacion = 0;
        switch(tipo){
            case 1 ->{
                puntuacion += 5;
                puntuacion += round(tiempo);
                break;
            }
            case 2 ->{
                puntuacion += 8;
                tiempo *= 1.2;
                puntuacion += round(tiempo);
                break;
            }
        }
        return puntuacion;
    }
}
