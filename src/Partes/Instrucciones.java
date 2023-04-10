
package Partes;

import MetodosExtras.CargarImagenes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Instrucciones {
 
    BufferedImage instrucciones;

    /** Metodo contructor, para pantalla de instrucciones*/
    public Instrucciones(){
        instrucciones = CargarImagenes.Imagen("recursos/Instrucciones.png");     
    }
    
    /**Metodo usado para imprimir la imagen de instrucciones del juego */
    public void renderizarInstrucciones(Graphics g){
        g.setFont(new Font("", 1, 20));
        g.drawImage(instrucciones, 0, 0, 740, 580, null);
    }
    
}
