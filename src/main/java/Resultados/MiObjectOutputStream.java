/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resultados;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author doble
 */
public class MiObjectOutputStream extends ObjectOutputStream{
    /**
     * Constructor que recibe OutputStream
     * @param out OutputStream
     * @throws IOException 
     */
    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    /**
     * Constructor sin parametros
     * @throws IOException
     * @throws SecurityException 
     */
    protected MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }
    
    @Override
    protected void writeStreamHeader() throws IOException{
        //No hacemos nada
    }   
}
