
package Partes;

import MetodosExtras.CargarImagenes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Creditos {
    
    BufferedImage creditos;
    
    /** Metodo constructor, para generar la pantalla de créditos*/
    public Creditos(){
        creditos = CargarImagenes.Imagen("recursos/cuadroCreditos.png"); 
    }
    
    /**Metodo encargado de dibujar la imagen y el texto con el nombre de los autores */
    public void renderizarCreditos(Graphics g){
        g.drawImage(creditos, 100, 0, 400, 500,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("", 1, 20));
        g.drawString("-Santiago Arellano",120,150);
        g.drawString("-Jesus Miguel Mora",120,250);
        g.setFont(new Font("", 1, 40));
        g.drawString("CREADO POR:",150,60);
    }
}
