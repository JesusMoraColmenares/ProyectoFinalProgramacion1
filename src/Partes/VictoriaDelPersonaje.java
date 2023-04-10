
package Partes;

import Eventos.Botones;
import Juego.Panel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VictoriaDelPersonaje {
    /**Atributos de la clase*/
    Panel panel;
    public JButton botonGanar[] = new JButton[3];
    Botones eventoBoton;
    
    /**Metodo contructor, para pantalla de victoria*/
    public VictoriaDelPersonaje(Panel panel){
        this.panel = panel;
        eventoBoton = new Botones( this.panel, this);
        botonesGanar();
    }
    
    
    /**Metodo encargado de imprimir por pantalla todos los elementos que corresponden a la hora de que el jugador gane */
    public void imprimirPantallaDeVictoria(Graphics g){
        g.setColor(Color.white);
        eventoBoton.eventosGanar();
        if(panel.obtenerJugar().obtenerJugador().getCantidadDeNivelesGanados() == 2){
            botonGanar[0].setBounds(1000, 100, 0, 0);
            botonGanar[1].setBounds(170, 400, 180, 50);
            botonGanar[2].setBounds(370, 400, 180, 50);
        }
            BufferedImage victoria = MetodosExtras.CargarImagenes.Imagen("recursos/VICTORIA.png");
            g.drawImage(victoria, 0, 0, 740, 580, null);
            g.drawString(panel.getEleccion().getNombre() + " LA CANTIDAD DE DIAMANTES QUE CONSEGUISTE FUE DE : " + panel.obtenerJugar().jugador.getPuntaje(), 190, 200);
            g.drawString(panel.getEleccion().getNombre() + " TU TIEMPO FUE DE : " + panel.obtenerJugar().obtenerJugador().getMinutosJugados() + "MINUTOS Y " + panel.obtenerJugar().obtenerJugador().getSegundosJugados() + " SEGUNDOS", 220, 300); 
    }
    
    
    /**Metodo encargado de inicializar , ubicar y darle iconos a los botones de la pantalla de victoria */
    public void botonesGanar(){
        botonGanar[0] = new JButton("continuar");
        botonGanar[1] = new JButton("Menu");
        botonGanar[2] = new JButton("Salir");
        botonGanar[0].setBounds(70, 400, 180, 50);
        botonGanar[1].setBounds(270, 400, 180, 50);
        botonGanar[2].setBounds(470, 400, 180, 50);

        panel.add(botonGanar[0]);
        panel.add(botonGanar[1]);
        panel.add(botonGanar[2]);
        mostrarBotones(false);
        
        ImageIcon imagen = new ImageIcon(getClass().getResource("/recursos/salir.png"));
        botonGanar[2].setIcon(new ImageIcon(imagen.getImage().getScaledInstance(botonGanar[2].getWidth() +20, botonGanar[2].getHeight(), Image.SCALE_SMOOTH))); 

        ImageIcon imagen2 = new ImageIcon(getClass().getResource("/recursos/menu.png"));
        botonGanar[1].setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(botonGanar[2].getWidth() + 20, botonGanar[2].getHeight(), Image.SCALE_SMOOTH))); 
        
        ImageIcon imagen3 = new ImageIcon(getClass().getResource("/recursos/continuar.png"));
        botonGanar[0].setIcon(new ImageIcon(imagen3.getImage().getScaledInstance(botonGanar[2].getWidth() +20, botonGanar[2].getHeight(), Image.SCALE_SMOOTH))); 
        
    }
     
    /**Metodo encargado de hacer visibles o invisibles a los botones de la pantalla de victoria */
    public void mostrarBotones(boolean mostrar){
        botonGanar[0].setVisible(mostrar);
        botonGanar[1].setVisible(mostrar);
        botonGanar[2].setVisible(mostrar);
    }
}
