
package Niveles;

import Jugador.Objetos;
import java.awt.Color;
import java.awt.Graphics;

public class PlacaDePresion extends Objetos {
    
    /** Metodo constructor, crea una placa (botón) de presión*/
    public PlacaDePresion(int x, int y, int alto, int ancho) {
        super(x, y, alto, ancho);
        
        iniciarHitbox(x, y, ancho , alto);
        apariencia = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/placaDePresion.png");
 
    
    }
    
    /**Metodo encargado de imprimir las placas de presión en el mapa */
    public void DibujarPlaca(Graphics g){
        dibujarHitbox(g);
        g.drawImage(apariencia, hitbox.x, hitbox.y, ancho, alto, null);
    }

 
    
    
    
    
   
    
}
