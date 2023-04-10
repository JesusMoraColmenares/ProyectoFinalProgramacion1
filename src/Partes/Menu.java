package Partes;

import Juego.Panel;
import static MetodosExtras.CargarImagenes.Imagen;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import Eventos.*;

public class Menu {
    /**Atributos de la clase*/
    Panel panel;
    public JButton [] botonesMenu;
    Botones eventoBoton;
    
    /** Metodo contructor, para crear menú principal*/
    public Menu(Panel panel){
        this.panel = panel;
        iniciarBotones();
        eventoBoton = new Botones(this);
    }

    public void renderizarMenu(Graphics g){

    }

    /**Metodo usado para hacer que los botones no se vean en otras partes del juego que no sean el menu */ 
    public void desaparecerBotones(){
        
        for(int i = 0; i < botonesMenu.length; i++){
            botonesMenu[i].setVisible(false); 
        }
    }
    
    /**Metodo encargado de hacer visibles los botones al estar en el menu */    
    public void reaparecerBotones(){ 
        for(int i = 0; i < botonesMenu.length; i++){
            botonesMenu[i].setVisible(true);    
        }
    }
    
    
    /**Metodo encargado de inicializar los botones del menu */
    private void iniciarBotones() {
        botonesMenu = new JButton[4];
        ImageIcon[] botones = new ImageIcon[4] ;

        for(int i = 0; i < botonesMenu.length; i++){
            botonesMenu[i] = new JButton();
            botonesMenu[i].setBounds( (int)(200 * panel.obtenerJuego().escala), (int)(i * 50 * panel.obtenerJuego().escala) + 250, (int)(197 * panel.obtenerJuego().escala),(int)(44 * panel.obtenerJuego().escala));   
            botonesMenu[i].setIcon(botones[i]);
            panel.add(botonesMenu[i]);  
        } 
        
        for(int j = 0; j < botonesMenu.length; j++){
            botones = cargarImageneBotones();
           botonesMenu[j].setIcon(botones[j]);
           panel.add(botonesMenu[j]);
        } 
    }
    
    /**Metodo usado para cargar las imagenes de los iconos que tendran los botones del menu */
    public ImageIcon[] cargarImageneBotones(){
        
        ImageIcon[] botones = new ImageIcon[4];
        
        for(int i = 0; i < botones.length; i++){
            String name = "/recursos/botones/"+i+".png";
            ImageIcon imagen = new ImageIcon(getClass().getResource(name));
            botones[i] = new ImageIcon(imagen.getImage().getScaledInstance(botonesMenu[i].getWidth(), botonesMenu[i].getHeight(), Image.SCALE_SMOOTH));  
        }
        return botones;
    }

    
   /**Getter */
    public JButton getBotonesMenu(int i) {
        return botonesMenu[i];
    }
}
