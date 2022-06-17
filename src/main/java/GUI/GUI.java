/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package GUI;

import Resultados.Fichero;
import Resultados.Jugador;
import Sudoku.Sudoku;
import Sudoku.Tiempo;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author doble
 */
public class GUI extends JFrame {

    final public static GUI proy = new GUI();
    final private static Sudoku sud = new Sudoku();
    final private static Fichero<Jugador> jug = new Fichero<>("src\\main\\java\\Resultados\\datos.ddr");
    private static boolean validacion;
    //nombre del jugador ingresado en JOptionPane
    private static String name;
    private static int tipo;
    private static Tiempo reloj = new Tiempo();
    private static int puntaje;
    private static int[] p;
    //Matrices sudoku
    public static int matriz[][] = new int[4][4];
    public static int matriz2[][] = new int[4][4];
    //Componentes GUI
    private JPanel jPanel0;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel puntuacion;
    public static JTextField[] c;
    private JTextField nombre;
    private JButton facil;
    private JButton dificil;
    private JButton validar;
    private JButton reiniciar;
    private JButton iniciar;
    private JButton resultados;
    private VentanaResultados resultado;

    public static void main(String[] args) {
        //Cuadro de validacion e ingreso del nombre
        validacion = true;
        while (validacion) {
            name = JOptionPane.showInputDialog(null, "Bienvenido al Juego"
                    + " Sudoku 4x4", "Ingrese su nombre por favor");
            if (name == null) {
                System.exit(0);
            } else if (" ".equals(name) || "Ingrese su nombre por favor".equals(name)
                    || name.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Favor Ingresar nombre", "Error",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                p = new int[1];
                p[0] = 0;
                Jugador j1 = new Jugador(p, name);
                Boolean estado = jug.obtenerDatos();
                if (estado == false) {
                    jug.guardarDatos(j1);
                    validacion = false;
                } else {
                    proy.validacionNombre(j1, name);
                }
            }
        }
        //jug.mostrarDatos();
        //Carga de Componentes
        proy.iniciarComponentes();
        //Funcion Boton Cerrar
        proy.cerrar();
    }

    private void iniciarComponentes() {
        /*
        Configuracion inicial del JFrame
         */
        this.setVisible(true);//Visibilidad
        this.setSize(426, 275);//Tamaño
        this.setLocationRelativeTo(null);//Ubicacion Centro
        this.setTitle("Sudoku 4x4");//Titulo
        this.setResizable(false);//Modificacion tamaño de la ventana
        this.setLayout(null);

        /*
        Creacion Componentes JFrame
         */
        jPanel0 = new JPanel();//Creacion JPanel general del sudoku
        jPanel1 = new JPanel();//Creacion JPanel 1 cuadrante sudoku
        jPanel2 = new JPanel();//Creacion JPanel 2 cuadrante sudoku
        jPanel3 = new JPanel();//Creacion JPanel 3 cuadrante sudoku
        jPanel4 = new JPanel();//Creacion JPanel 4 cuadrante sudoku
        jPanel5 = new JPanel();//Creacion JPanel parte superior derecha
        jPanel6 = new JPanel();//Creacion JPanel parte inf derecha
        jLabel1 = new JLabel("Sudoku");//Creacion JLabel
        jLabel2 = new JLabel("Bienvenido:");//Creacion JLabel
        jLabel3 = new JLabel("Puntaje:");//Creacion JLabel
        puntuacion = new JLabel();//Creacion JLabel de puntuacion
        c = new JTextField[16];//Creacion matriz JTextField
        nombre = new JTextField("");
        facil = new JButton();//Creacion JButton
        validar = new JButton();//Creacion JButton
        reiniciar = new JButton();//Creacion JButton
        dificil = new JButton();//Creacion JButton
        iniciar = new JButton();//Creacion JButton
        resultados = new JButton();//Creacion JButton
        //Carga de jLabel en JFrame
        jLabel1.setLayout(null);
        add(jLabel1);
        add(jLabel2);
        add(jLabel3);
        add(puntuacion);
        add(resultados);
        jLabel1.setBounds(13, 6, 50, 16);
        jLabel2.setBounds(192, 6, 90, 16);
        //Espacio inferior del JFrame
        jLabel3.setBounds(25, 185, 50, 30);
        puntuacion.setBounds(90, 185, 90, 30);
        puntuacion.setBorder(BorderFactory.createLineBorder(Color.black));
        resultados.setText("Resultados");
        resultados.setBounds(192, 185, 100, 25);
        resultados.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadosActionPerformed(evt);
            }
        });
        //Agregar Panel a Ventana
        this.getContentPane().add(jPanel0);
        this.getContentPane().add(jPanel5);
        this.getContentPane().add(jPanel6);
        jPanel0.setBounds(13, 28, 140, 140);//Tamaño caja sudoku
        jPanel0.setLayout(new GridLayout(2, 2));//Division en 4 cuadrantes
        //Acople jPanel 1,2,3 y 4 a 0
        jPanel0.add(jPanel1);
        jPanel0.add(jPanel2);
        jPanel0.add(jPanel3);
        jPanel0.add(jPanel4);
        //Bordeado cajas de Cuadrantes
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black));
        //Asignacion de Array jTextField
        for (int i = 0; i < c.length; i++) {
            c[i] = new JTextField();
            c[i].setVisible(true);
            c[i].setEditable(false);
            c[i].setFont(new Font("Tahoma", 1, 18)); //Fuente
            c[i].setForeground(new Color(0, 0, 255));//Blue
            c[i].setHorizontalAlignment(JTextField.CENTER);//Alineacion
            c[i].addKeyListener(new java.awt.event.KeyAdapter() {
                //Validacion ingreso de datos JTextField
                public void keyTyped(KeyEvent evt) {
                    cKeyTyped(evt);
                }
            });
        }
        //Division en 4 cuadrantes
        jPanel1.setLayout(new GridLayout(2, 2));
        //Acople jTextField a jPanel1
        jPanel1.add(c[0]);
        jPanel1.add(c[1]);
        jPanel1.add(c[4]);
        jPanel1.add(c[5]);
        //Division en 4 cuadrantes
        jPanel2.setLayout(new GridLayout(2, 2));
        //Acople jTextField a jPanel2
        jPanel2.add(c[2]);
        jPanel2.add(c[3]);
        jPanel2.add(c[6]);
        jPanel2.add(c[7]);
        //Division en 4 cuadrantes
        jPanel3.setLayout(new GridLayout(2, 2));
        //Acople jTextField a jPanel3
        jPanel3.add(c[8]);
        jPanel3.add(c[9]);
        jPanel3.add(c[12]);
        jPanel3.add(c[13]);
        //Division en 4 cuadrantes
        jPanel4.setLayout(new GridLayout(2, 2));
        //Acople jTextField a jPanel4
        jPanel4.add(c[10]);
        jPanel4.add(c[11]);
        jPanel4.add(c[14]);
        jPanel4.add(c[15]);
        //Refresca el jPanel0 ya que como es dinamico genera bug
        jPanel0.updateUI();
        //Ubicacion Panel Superior derecho del juego
        jPanel5.setBounds(192, 28, 189, 60);
        jPanel5.add(nombre);
        jPanel5.add(iniciar);
        jPanel5.setLayout(null);
        nombre.setBounds(0, 1, 189, 25);
        nombre.setText(name);
        nombre.setEditable(true);
        nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombreMouseClicked(evt);
            }
        });
        iniciar.setBounds(0, 35, 188, 25);
        iniciar.setText("Iniciar");
        //Refresca el jPanel5 ya que como es dinamico genera bug
        jPanel5.updateUI();
        //Evento de accion para iniciar
        iniciar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        //Ubicacion Panel Inferior derecho del juego
        jPanel6.setBounds(192, 100, 189, 70);
        jPanel6.add(facil);
        jPanel6.add(dificil);
        jPanel6.add(validar);
        jPanel6.add(reiniciar);
        jPanel6.setLayout(null);
        facil.setBounds(0, 1, 74, 25);
        facil.setText("Facil");
        facil.setEnabled(false);
        //Evento de accion para facil
        facil.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facilActionPerformed(evt);
            }
        });
        dificil.setBounds(103, 1, 85, 25);
        dificil.setText("Dificil");
        dificil.setEnabled(false);
        dificil.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dificilActionPerformed(evt);
            }
        });
        validar.setBounds(0, 45, 74, 25);
        validar.setText("Validar");
        validar.setEnabled(false);
        validar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarActionPerformed(evt);
            }
        });
        reiniciar.setBounds(103, 45, 85, 25);
        reiniciar.setText("Reiniciar");
        reiniciar.setEnabled(false);
        reiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });
        //Refresca el jPanel5 ya que como es dinamico genera bug
        jPanel5.updateUI();
    }

    //Metodo que borra el sudoku
    public void borrar() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz2[i][j] = 0;
            }
        }
        sud.imprimirSudoku();
        iniciar.setEnabled(true);
        reiniciar.setEnabled(false);
        validar.setEnabled(false);
        nombre.setEnabled(true);
        for (int i = 0; i < c.length; i++) {
            c[i].setEditable(false);
        }
    }

    //Metodo que evalua no cerrar sino preguntar al oprimir boton cerrar
    public void cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    confirmarSalida();
                }
            });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Cuadro de confirmacion de Cerrar
    public void confirmarSalida() {
        int valor = JOptionPane.showConfirmDialog(this, "Esta seguro de cerrar "
                + "el juego? Perdera todo el puntaje ganado", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Gracias por su visita, Hasta "
                    + "Pronto", "Adios", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    //Metodo que solo deja ingresar caracteres del 1 al 4
    private void cKeyTyped(KeyEvent evt) {
        int key = evt.getKeyChar();
        boolean numeros = key >= 49 && key <= 52;
        if (!numeros) {
            evt.consume();
        }
        if (key == 1) {
            evt.consume();
        }
    }

    //Metodo del boton dificil
    private void dificilActionPerformed(java.awt.event.ActionEvent evt) {
        facil.setEnabled(false);
        dificil.setEnabled(false);
        validar.setEnabled(true);
        reiniciar.setEnabled(true);
        sud.cero(6);
        sud.imprimirSudoku();
        reloj.contar();
        tipo = 2;
    }

    //Metodo del boton facil
    private void facilActionPerformed(java.awt.event.ActionEvent evt) {
        facil.setEnabled(false);
        dificil.setEnabled(false);
        validar.setEnabled(true);
        reiniciar.setEnabled(true);
        sud.cero(4);
        sud.imprimirSudoku();
        reloj.contar();
        tipo = 1;
    }

    //Metodo que realiza evento de boton iniciar
    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {
        String nombre1 = nombre.getText();
        //Validacion nombre ingresado
        if (" ".equals(nombre1) || nombre1.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Favor Ingresar nombre",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            //Coloca foco de texto en nombre
            nombre.requestFocus();
            nombre.setText(null);
        } else {
            if (validacion == true) {
                Jugador j1 = new Jugador(p, nombre1);
                validacionNombre(j1, nombre1);
                iniciarActionPerformed(evt);
            } else {
                nombre.setEnabled(false);
                iniciar.setEnabled(false);
                facil.setEnabled(true);
                dificil.setEnabled(true);
                sud.construirSudoku();
                Random aleatorio = new Random();
                int num = aleatorio.nextInt(12);
                switch (num) {
                    case 0 -> {
                        sud.numAleatorios(0, 0);
                        break;
                    }
                    case 1 -> {
                        sud.numAleatorios(0, 1);
                        break;
                    }
                    case 2 -> {
                        sud.numAleatorios(1, 0);
                        break;
                    }
                    case 3 -> {
                        sud.numAleatorios(1, 1);
                        break;
                    }
                    case 4 -> {
                        sud.numAleatorios(0, 0);
                        sud.numAleatorios(0, 1);
                        break;
                    }
                    case 5 -> {
                        sud.numAleatorios(0, 0);
                        sud.numAleatorios(1, 0);
                        break;
                    }
                    case 6 -> {
                        sud.numAleatorios(0, 0);
                        sud.numAleatorios(1, 1);
                        break;
                    }
                    case 7 -> {
                        sud.numAleatorios(0, 1);
                        sud.numAleatorios(1, 0);
                        break;
                    }
                    case 8 -> {
                        sud.numAleatorios(0, 1);
                        sud.numAleatorios(1, 1);
                        break;
                    }
                    case 9 -> {
                        sud.numAleatorios(1, 0);
                        sud.numAleatorios(1, 1);
                        break;
                    }
                    case 10 -> {
                        sud.numAleatorios(0, 0);
                        sud.numAleatorios(0, 1);
                        sud.numAleatorios(1, 0);
                        sud.numAleatorios(1, 1);
                        break;
                    }
                    case 11 -> {
                        break;
                    }
                }
            }
        }
    }

    //Metodo que realiza evento de clickear cuadro de nombre
    private void nombreMouseClicked(java.awt.event.MouseEvent evt) {
        // Borra mensaje al dar click:
        if (nombre.isEnabled()) {
            String n = nombre.getText();
            if (n.isEmpty() || " ".equals(n)) {
                nombre.setText(null);
            } else {
                int valor = JOptionPane.showConfirmDialog(this, "Esta seguro que quiere "
                        + "cambiar de nombre? Perdera todo el puntaje ganado", "Advertencia",
                        JOptionPane.YES_NO_OPTION);
                if (valor == JOptionPane.YES_OPTION) {
                    nombre.setText(null);
                    validacion = true;
                }
            }
        }
    }

    //Metodo que realiza evento de boton reiniciar
    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (validacion == false) {
            int valor = JOptionPane.showConfirmDialog(this, "Esta seguro que quiere "
                    + "reiniciar? Perdera todo el progreso", "Advertencia",
                    JOptionPane.YES_NO_OPTION);
            if (valor == JOptionPane.YES_OPTION) {
                borrar();
            }
        } else {
            borrar();
        }
    }

    //Metodo que realiza evento de boton resultados
    private void resultadosActionPerformed(java.awt.event.ActionEvent evt) {
        proy.setVisible(false);
        resultado = new VentanaResultados();
        resultado.cerrar();
        resultado.setText(jug.mostrarDatos());
        /*Prueba Ingreso de datos
        Jugador j1 = new Jugador(10, "Fernando");
        Jugador j2 = new Jugador(20, "Manuel");
        Jugador j3 = new Jugador(30, "Fernando");
        
        jug.guardarDatos(j1);
        jug.guardarDatos(j2);
        jug.guardarDatos(j3);
        System.out.println("Guardado con exito");
         */
        /*Prueba Eliminar objeto
        jug.mostrarDatos();
        Jugador j1 = new Jugador("Fernando");
        System.out.println(jug.existeDato(j1));
        jug.borrarDato(j1);
         */
        /*Otra forma de mostrar los objetos tipo Jugador
        ArrayList<Jugador> Jugadores = jug.getDatos();
        for(Jugador j: Jugadores){
            System.out.println(j);
        }*/
    }

    //Metodo que realiza evento de boton validar
    private void validarActionPerformed(java.awt.event.ActionEvent evt) {
        int cont = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!c[cont].getText().equals("")) {
                    matriz2[i][j] = Integer.parseInt(c[cont].getText());
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Hay espacios en blanco, revise por favor", "Error",
                            JOptionPane.WARNING_MESSAGE);
                    validacion = true;
                    break;
                }
                cont++;
            }
            if (validacion == true) {
                validacion = false;
                break;
            }
        }
        //Validacion de si son iguales matriz con matriz2
        if (Arrays.deepEquals(matriz, matriz2)) {
            validacion = true;
            reloj.detener();
            System.out.println("OK" + reloj.getSegundos());
            puntaje = sud.calcularPuntuacion(tipo, reloj.getSegundos());
            for (int i =0;i<jug.getDatos().size();i++) {
                if (jug.getDatos().get(i).getNombre().equals(nombre.getText())) {
                    //System.out.println(jug.getDatos());
                    int j = jug.getDatos().get(i).getPuntuacion().length;
                    p = new int[j + 1];
                    System.arraycopy(jug.getDatos().get(i).getPuntuacion(), 0, p, 0, j);
                    p[j] = puntaje;
                    System.out.println(Arrays.toString(p));
                }
                //System.out.println(jug.getDatos());
                
                //jug.getDatos().get(0).setNombre("rOBERTO");

                //System.out.println(jug.getDatos());

            }
            
            Jugador j1 = new Jugador(nombre.getText());
            //System.out.println(jug.getDatos());
            Jugador j2 = new Jugador(p, nombre.getText());
            jug.borrarDato(j1);
            jug.guardarDatos(j2);
            //jug.mostrarDatos();
            puntuacion.setText(puntaje + "");
        } else {
            System.out.println("no" + validacion);
        }
        /*Impresion de prueba de matriz
        System.out.println("matriz1 cero");
        for(int matriz3[]:matriz){
            System.out.println(Arrays.toString(matriz3));
        }
        System.out.println("matriz2 cero");
        for(int matriz1[]:matriz2){
            System.out.println(Arrays.toString(matriz1));
        }*/
    }

    //Metodo que valida si el nombre existe
    private void validacionNombre(Jugador j1, String n) {
        boolean estado = jug.existeDato(j1);
        if (estado) {
            int input = JOptionPane.showConfirmDialog(null, n + " desea continuar "
                    + "su partida? De click en NO si es un Jugador nuevo e ingrese "
                    + "un nombre distinto", "Customized Dialog",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                validacion = false;
            }
        } else {
            jug.guardarDatos(j1);
            validacion = false;
        }
    }
    //

}
