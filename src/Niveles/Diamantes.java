
package Niveles;

import Jugador.Objetos;
import java.awt.Graphics;

public class Diamantes extends Objetos {
    boolean fuego;
    boolean activo = false;

    /** Metodo constructor, para crear los diamantes*/
    public Diamantes(int x, int y, boolean fuego) {
        super(x, y, (int)(16 * Juego.Juego.escala), (int)(Juego.Juego.escala * 16));
        iniciarHitbox(x, y, super.ancho, super.alto);
        this.fuego = fuego;
        cargarAparienciaDiamante();
        
    }
    
    /** Metodo encargado de dibujar diamantes en los niveles*/
    public void dibujarDiamantes(Graphics g){
        
        g.drawImage(apariencia, hitbox.x, hitbox.y, ancho, alto, null);
    }
    
    
    /**Metodo para asignarle un color al diamante dependiendo del elemento del jugador */
    public void cargarAparienciaDiamante(){
        
        if(!fuego){
            
            apariencia = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/DiamanteAgua.png");
        }else{
            
            apariencia = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/DiamanteFuego.png");
           
        }
    }

    
    /**Getters y Setters */
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    

    public boolean isFuego() {
        return fuego;
    }

    public void setFuego(boolean fuego) {
        this.fuego = fuego;
    }
    
}
