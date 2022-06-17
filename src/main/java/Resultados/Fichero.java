/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resultados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doble
 */
public class Fichero<T> {

    private File fichero;
    private ArrayList<T> datos;
    /**
     * Recoge la direccion donde se aloja el fichero
     * @param fichero 
     */
    public Fichero(String fichero) {
        this.fichero = new File(fichero);
        this.datos = new ArrayList();
    }
    /**
     * Opcional por si se quiere enviar el tipo File desde GUI
     * @param fichero 
     */
    public Fichero(File fichero) {
        this.fichero = fichero;
        this.datos = new ArrayList();
    }

    public boolean obtenerDatos() {
        if (fichero.exists()) {
            T elemento;
            try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
                while (true) {
                    elemento = (T) ois.readObject();
                    datos.add(elemento);
                }
                
            } catch (EOFException ex) {
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }else{
            return false;
        }
    }

    public void guardarDatos(T elemento) {
        if (fichero.exists() && fichero.length() > 0) {
            try (MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream(fichero, true));) {
                oos.writeObject(elemento);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));) {
                oos.writeObject(elemento);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        datos.add(elemento);
    }

    public ArrayList<T> getDatos() {
        return datos;
    }
    //Muestra los datos de la lista, la clase debe tener un toString
    public String mostrarDatos(){
        String text="";
        for (T e: datos){
            text += e;
            System.out.println(e);
        }
        return text;
    }
    
    public boolean existeDato(T elemento){
        for(T e: datos){
            if(e.equals(elemento)){
                return true;
            }
        }
        return false;
    }
    
    public void borrarDato(T elemento){
        datos.remove(elemento);
        System.out.println();
        ArrayList<T> copia = datos;
        datos = new ArrayList<>();
        fichero.delete();
        for(T e:copia){
            guardarDatos(e);
        }
    }
}
