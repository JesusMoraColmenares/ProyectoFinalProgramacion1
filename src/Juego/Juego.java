
package Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import static Partes.PartesDelJuego.Menu;
import java.awt.Color;


public class Juego{
    
    JLabel cronometro;
    Panel panel;
    int min = 0, sec = 0, contar=0;
    Timer time;
    
    /**Medidas y escalas a utilizar*/
    public static float escala = 1.3f;
    public int tilesHorizontales = 37;
    public int tilesVerticales = 29;
    public static  int TamañoTiles = (int)(16 * escala);
    public int ancho = (int)((TamañoTiles * tilesHorizontales)) ;
    public int alto = (int)((TamañoTiles * tilesVerticales));
    
    /**Contructor de la clase, inicia un juego*/
    public Juego(){
        panel = new Panel(this);
        Ventana ventana = new Ventana(panel);
        panel.regresar.setBounds(500, 10 , 83, 17);
        panel.add(panel.regresar);
        cronometro = new JLabel("Tiempo 0:00");
        cronometro.setBounds(20, 15, 100, 30);
        cronometro.setForeground(Color.white);
        panel.add(cronometro);
        cronometro.setVisible(false);
        
        time = new Timer(100, new ControlaTimer());
        time.start();
        System.out.println(panel.getWidth());
        System.out.println(panel.getHeight()); 
    }

    /**Clase interna para manejar el timer*/
    public class ControlaTimer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            /**Actualizacion de la pantalla*/
            panel.repaint();
            panel.obtenerJugar().obtenerJugador().actualizarPosicion();
            
            /**Manejo de cronómetro*/
            if(Partes.PartesDelJuego.Parte == Partes.PartesDelJuego.Jugar){
                contar+=1;
                if(contar==10){
                    contar=0;
                    sec+=1;
                    if(sec == 60){
                        min+=1;
                        sec=0;
                    }
                }
                cronometro.setText("Tiempo: "+min+":"+sec);
            }
        } 
    }
    
    /**Setters y getters e la clase*/
    
    public Timer obtenerTimerDelJuego(){
        return time; 
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getContar() {
        return contar;
    }

    public void setContar(int contar) {
        this.contar = contar;
    }

    public Panel getPanel() {
        return panel;
    }

    public float getEscala() {
        return escala;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
