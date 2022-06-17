/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static GUI.GUI.proy;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author doble
 */
public class VentanaResultados extends JFrame{
    
    JTextArea resultado = new JTextArea();
    
    public VentanaResultados() {
        //this.add(jTAEscribir());
        this.add(jSPBarra());
        this.iniciarComponentes();
    }
    
    //Inicializador de la ventana
    private void iniciarComponentes(){
        
        this.setSize(426, 275);//Tamaño
        this.setLayout(new BorderLayout());
        this.setTitle("Registro de Resultados");//Titulo
        this.setLocationRelativeTo(null);//Ubicacion Centro
        this.setVisible(true);//Visibilidad
        this.setResizable(false);//Modificacion tamaño de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    //Metodo que crea un jTextArea para escribir el resultado de los jugadores
   private JTextArea jTAEscribir(){
       //Variable inicializada
       
       //Salto de linea automatico cuando llega a final del JTextArea
       resultado.setLineWrap(true);
       //Posicion y tamaño
       resultado.setBounds(0, 0, 426, 275);
       //Color fondo
       resultado.setBackground(new Color(0,0,0));
       //Color letra
       resultado.setForeground(new Color(255,255,255));
       resultado.setEditable(false);
       return resultado;
   }
   //Creacion barra de desplazamiento
   private JScrollPane jSPBarra(){
       //Se inicializa el objeto y se le agrega a un JTextArea
       JScrollPane scroll = new JScrollPane(this.add(jTAEscribir()));
       //Posicion y tamaño
       scroll.setBounds(0, 0, 410, 215);
       scroll.getHorizontalScrollBar().setBackground(Color.white);
       scroll.getVerticalScrollBar().setBackground(Color.white);
       return scroll;
   }
   public void cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    proy.setVisible(true);
                    setVisible(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   public void setText(String resultado){
       this.resultado.setText(resultado);
   }
}
