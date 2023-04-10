
package MetodosExtras;

import Juego.Panel;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class CargarMapas {

    /**Metodo para cargar el mapa del nivel 1*/
    public static int [][] obtenerNivel1(){
        int [][] lvlData = new int [29][37];
        BufferedImage img = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/pixelesMapa.png");
        
        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i < img.getWidth(); i++){
                /**Se almacena cada pixel de la imagen y se obtiene su color, de ello dependerá su valor en matriz*/
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                    lvlData[j][i] = value; 
            }
        }
        return lvlData;
    }
       
    /**Metodo para cargar el mapa del nivel 2*/
    public static int [][] obtenerNivel2(){
        int [][] lvlData = new int [29][37];
        BufferedImage img = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/pixelesMapa2.png");
        
        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i < img.getWidth(); i++){
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                    lvlData[j][i] = value; 
            }
        }
        return lvlData;
    }
}
        
