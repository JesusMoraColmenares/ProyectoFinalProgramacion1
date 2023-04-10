
package Eventos;

import Juego.Panel;
import Niveles.Niveles;
import Partes.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {
    
    Panel panel;
    IngresarInformacion info;
    Jugar jugar;
    
    /**Metodos contructores, sus objetos se usarán para maejar los eventos del teclado
     * @param infoo*/
    public Teclado(IngresarInformacion info){
        this.info = info;
    }
    
    public Teclado(Panel panel, Jugar jugar){
       this.panel = panel; 
       this.jugar = jugar; 
    }

    @Override
    public void keyTyped(KeyEvent evt){
        /**Evita que se puedan escribir mas de 8 caracteres al ingresar el nombre*/
        if(Partes.PartesDelJuego.Parte == Partes.PartesDelJuego.Eleccion){
            if(info.getIngresarNombre().getText().length() >= 8){
                evt.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        
        if(Partes.PartesDelJuego.Parte == Partes.PartesDelJuego.Jugar){

            /**Movimientos del personaje del niño fuego*/
            if(jugar.getJugador().isFuego()){
                switch(evt.getKeyCode()){        
                    case KeyEvent.VK_LEFT:
                        jugar.getJugador().setIzquierda(true);
                        jugar.getJugador().setVolteadoIzquierda(false);

                        break;
                    case KeyEvent.VK_RIGHT:
                        jugar.getJugador().setDerecha(true);
                        jugar.getJugador().setVolteadoIzquierda(false);

                        break;
                    case KeyEvent.VK_UP:
                        if(jugar.obtenerJugador().isSobrePuente() ||Niveles.rotando || Colisiones.DetectarColisiones.sobreElemento(jugar.obtenerJugador().obtenerHitbox().x, jugar.getJugador().obtenerHitbox().y + 10, jugar.getJugador().obtenerHitbox().height, jugar.getJugador().obtenerHitbox().width, jugar.getJugador().obtenerNivel(), panel.obtenerJuego(), true) ||Colisiones.DetectarColisiones.SobrePlataforma(jugar.obtenerJugador().obtenerHitbox().x, jugar.getJugador().obtenerHitbox().y + 10, jugar.getJugador().obtenerHitbox().height, jugar.getJugador().obtenerHitbox().width, jugar.getJugador().obtenerNivel(), panel.obtenerJuego())|| Colisiones.DetectarColisiones.sobrePiso(jugar.obtenerJugador().obtenerHitbox().x, jugar.getJugador().obtenerHitbox().y+ 7, jugar.getJugador().obtenerHitbox().height, jugar.getJugador().obtenerHitbox().width, jugar.getJugador().obtenerNivel(), panel.obtenerJuego())){
                            if(jugar.getJugador().getArriba()==0 ){
                                jugar.getJugador().setArriba(1);
                                jugar.getJugador().setyMin(jugar.getJugador().obtenerHitbox().y);
                            }
                        }
                        break;
                }  
            }  
            
            /**Movimientos del personaje de la niña agua*/
            if(jugar.getJugador().isAgua()){
                switch(evt.getKeyCode()){        
                    case KeyEvent.VK_A:
                        jugar.getJugador().setIzquierda(true);
                        jugar.getJugador().setVolteadoIzquierda(false);

                        break;
                    case KeyEvent.VK_D:
                        jugar.getJugador().setDerecha(true);
                        jugar.getJugador().setVolteadoDerecha(false);

                        break;
                        
                    case KeyEvent.VK_W:
                        if(jugar.obtenerJugador().isSobrePuente() ||Niveles.rotando || Colisiones.DetectarColisiones.sobreElemento(jugar.obtenerJugador().obtenerHitbox().x, jugar.getJugador().obtenerHitbox().y + 10, jugar.getJugador().obtenerHitbox().height, jugar.getJugador().obtenerHitbox().width, jugar.getJugador().obtenerNivel(), panel.obtenerJuego(), false)  ||Colisiones.DetectarColisiones.SobrePlataforma(jugar.obtenerJugador().obtenerHitbox().x, jugar.getJugador().obtenerHitbox().y+ 7, jugar.getJugador().obtenerHitbox().height, jugar.getJugador().obtenerHitbox().width, jugar.getJugador().obtenerNivel(), panel.obtenerJuego())|| Colisiones.DetectarColisiones.sobrePiso(jugar.obtenerJugador().obtenerHitbox().x, jugar.getJugador().obtenerHitbox().y+ 7, jugar.getJugador().obtenerHitbox().height, jugar.getJugador().obtenerHitbox().width, jugar.getJugador().obtenerNivel(), panel.obtenerJuego())){
                            if(jugar.getJugador().getArriba()==0 ){
                                jugar.getJugador().setArriba(1);
                                jugar.getJugador().setyMin(jugar.getJugador().obtenerHitbox().y);
                            }
                        }
                        break;
                }
            }  
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if(Partes.PartesDelJuego.Parte == Partes.PartesDelJuego.Jugar){
           /**Detener movimiento del personaje niño fuego*/
            if(jugar.getJugador().isFuego()){
                switch(evt.getKeyCode()){        
                case KeyEvent.VK_LEFT:
                    jugar.getJugador().setIzquierda(false);
                    jugar.getJugador().setIndiceAnimacion(0);
                    jugar.getJugador().setVolteadoIzquierda(true);

                    break;
                case KeyEvent.VK_RIGHT:
                    jugar.getJugador().setDerecha(false);
                    jugar.getJugador().setIndiceAnimacion(0);
                    jugar.getJugador().setVolteadoDerecha(true);

                    break;
                }
            } 
            
            /**Detener movimiento del personaje niña agua*/
            if(jugar.getJugador().isAgua()){
                switch(evt.getKeyCode()){        
                case KeyEvent.VK_A:
                    jugar.getJugador().setIzquierda(false);
                    jugar.getJugador().setIndiceAnimacion(0);
                    break;
                case KeyEvent.VK_D:
                    jugar.getJugador().setDerecha(false);
                    jugar.getJugador().setIndiceAnimacion(0);
                    break;
                }
            }
        }
    }    
}
