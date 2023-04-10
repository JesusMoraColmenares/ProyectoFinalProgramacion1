
package Niveles;

import Jugador.Objetos;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Plataformas extends Objetos{
    /**Atributos de la clase*/
    PlacaDePresion placa;
    int minY;
    boolean activada = false;

    /** Metodo constructor, crea una plataforma*/
    public Plataformas(int x, int y) {
        super(x, y, 8, Juego.Juego.TamañoTiles * 3);
        placa = new PlacaDePresion(0, 0, 3, 32);
        iniciarHitbox(x, y, super.ancho - 5, super.alto);
        apariencia = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/plataformaAmarilla.png");
        
    }
    
    /**Metodo encargado de dibujar las plataformas junto a sus placas de presion correspondientes*/
    public void dibujarPlataformas(Graphics g){
        g.drawImage(apariencia, hitbox.x, hitbox.y, ancho, alto, null);
        placa.DibujarPlaca(g);
    }
    
    
    /** Metodo encargado de hacer subir las plataformas a su posicion original*/
    public void subir(){
        if(hitbox.y > minY){   
            hitbox.y--;
        }
        
        if(hitbox.y == minY){
            activada = false;
        }
    }
    
    /**Metodo encargado de bajar a las plataformas */
    public void bajar(){
        hitbox.y++;
        activada = true;
    }
 
    /**Setters y getters de la clase*/
    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }

    public BufferedImage obtenerApariencia(){
        return apariencia;
    }
    
    public PlacaDePresion obtenerPlaca(){

        return placa;
    }
}
