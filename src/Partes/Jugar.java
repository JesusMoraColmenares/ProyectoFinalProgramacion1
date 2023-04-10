
package Partes;

import Juego.Panel;
import Jugador.Personaje;
import Niveles.Niveles;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Jugar {
    /**Atributos de la clse*/
    Personaje jugador;
    Niveles nivel1;
    Panel panel;
    
    /** Metodo contructor, para iiciar personaje y nivel*/
    public Jugar(Panel panel){
        this.panel = panel;
        nivel1 = new Niveles(MetodosExtras.CargarMapas.obtenerNivel1(), panel, 2, 8, false, true);
        jugador = new Personaje(50, 400, 50, 30, nivel1.obtenerNivel(), panel.obtenerJuego()); 
        jugador.setNivelConPlataformas(true);
        jugador.establecerNivelActual(nivel1);
        nivel1.setJugador(jugador);
    }
    
    
    /**Metodo usado para mostrar por pantalla al personaje y al mapa por pantalla */
    public void ImprimirMapaYjugador(Graphics g){
        jugador.getNivelActual().ImprimirMapa(g);
        jugador.dibujarPersonaje(g);
        Graphics2D g2d = (Graphics2D)g;
        jugador.getNivelActual().rotar(g2d);
    }
    
    /**Getters de la clase*/
    public Personaje obtenerJugador(){ 
        return jugador;
    }

    public Personaje getJugador() {
        return jugador;
    }
    
}
