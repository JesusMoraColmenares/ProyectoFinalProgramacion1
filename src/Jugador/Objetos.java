
package Jugador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**Clase padre de los personajes y elementos */
public class Objetos {
    /**Atributos de la clase*/
    protected int x;
    protected int y;
    protected int alto;
    protected int ancho;
    protected BufferedImage apariencia;
    public Rectangle hitbox;

    /**Metodo constructor, para crear los elementos del juego*/
    public Objetos(int x, int y, int alto, int ancho) {
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho; 
    }
    
    /**Metodo para dibujar el rectangulo de colisión del objeto*/
    public void dibujarHitbox(Graphics g){
        g.setColor(Color.white);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
        
    }
    
    /**Metodo para crear el rectangulo de colisión del objeto*/
    public void iniciarHitbox(int x, int y,int  ancho, int alto) {
        hitbox = new Rectangle(x, y, ancho, alto);
    }
    
    /**Setters y getters de la clase*/
    public Rectangle obtenerHitbox(){
        return hitbox;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
}
