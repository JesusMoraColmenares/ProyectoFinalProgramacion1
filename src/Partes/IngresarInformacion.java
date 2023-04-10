
package Partes;

import Juego.Panel;
import MetodosExtras.CargarImagenes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import Eventos.*;

public class IngresarInformacion {
    /**Atributos de la clase*/
    BufferedImage espacioDeSeleccion;
    BufferedImage ElegirPersonaje;
    BufferedImage IngresarNombre;
    JTextField ingresarNombre;
    Panel panel;
    JButton fuego;
    JButton agua;
    JButton empezar;
    BufferedImage ImagenEmpezar;
    boolean PersonajeElegido = false, nombreIngresado = false;
    Botones eventosB;
    Teclado eventosT;
    String nombre;
    
    /** Metodo constructor, para generar la pantalla de elección de personaje y nombre*/
    public IngresarInformacion(Panel panel){
        this.panel = panel;
        espacioDeSeleccion = CargarImagenes.Imagen("recursos/EspacioEleccion.png");
        ElegirPersonaje = CargarImagenes.Imagen("recursos/elegirElemento.png");
        IngresarNombre= CargarImagenes.Imagen("recursos/IngresarNombre.png");
        ImagenEmpezar = CargarImagenes.Imagen("recursos/empezar.png");
        iniciarElementos();
    }
    
    /**Metodo que inicializar y ubicar los botones y JTextField*/
    public void iniciarElementos(){
        
        fuego = new JButton();
        agua = new JButton();
        empezar = new JButton("COMENZAR");
        
        fuego.setBounds((int)(60 * panel.obtenerJuego().escala), (int)(200 * panel.obtenerJuego().escala), (int)(100 * panel.obtenerJuego().escala), (int)(200 * panel.obtenerJuego().escala));
       
        fuego.setBackground(Color.DARK_GRAY);
        ImageIcon iconoDeFuego = new ImageIcon(getClass().getResource( "/recursos/iconoFuego.png"));
        fuego.setIcon(new ImageIcon(iconoDeFuego.getImage().getScaledInstance(fuego.getWidth()-10, fuego.getHeight()-10, Image.SCALE_SMOOTH)));
        
        agua.setBounds((int)(200 * panel.obtenerJuego().escala), (int)(200 * panel.obtenerJuego().escala), (int)(100 * panel.obtenerJuego().escala), (int)(200 * panel.obtenerJuego().escala));
        agua.setBackground(Color.DARK_GRAY);        
        ImageIcon iconoDeAgua = new ImageIcon(getClass().getResource( "/recursos/iconoAgua.png"));
        agua.setIcon(new ImageIcon(iconoDeAgua.getImage().getScaledInstance(agua.getWidth(), agua.getHeight(), Image.SCALE_SMOOTH)));

        ingresarNombre = new JTextField(8);
        ingresarNombre.setBounds(540, 410, 100, 50);
        ingresarNombre.setVisible(false);
        
        empezar.setBounds((int)(520), (int)(480), 150, (int)40);
        ImageIcon iconoEmpezar = new ImageIcon(getClass().getResource( "/recursos/empezar.png"));
        empezar.setIcon(new ImageIcon(iconoEmpezar.getImage().getScaledInstance(empezar.getWidth() +20, empezar.getHeight(), Image.SCALE_SMOOTH)));
        
        fuego.setVisible(false);
        agua.setVisible(false);
        empezar.setVisible(false);

        panel.add(ingresarNombre);
        panel.add(fuego);
        panel.add(agua);
        panel.add(empezar);
        
        //Objetos para manejar eventos
        eventosB = new Botones(panel, this);
        eventosT = new Teclado(this);
        ingresarNombre.addKeyListener(eventosT);
    }
    
    
    /**Metodo usado para hacer visibles a los botones*/
    public void verBotones(boolean decision){
        ingresarNombre.setVisible(decision);
        fuego.setVisible(decision);
        agua.setVisible(decision);
        empezar.setVisible(decision);
    }
    
    /**Metodo encargado de imprimir los elementos para poder ingresarnombre , elegir elemento y empezar juego*/
    public void renderizarIngresarInfo(Graphics g){
        g.drawImage(espacioDeSeleccion,(int) (20 * panel.obtenerJuego().escala), (int)(30 * panel.obtenerJuego().escala), (int)(562 * panel.obtenerJuego().escala), (int)(434 * panel.obtenerJuego().escala), null);
        g.setColor(Color.white);
        g.setFont(new Font("", 1, 18));
        g.drawImage(ElegirPersonaje,(int)(90 * panel.obtenerJuego().escala),(int)(70 * panel.obtenerJuego().escala), null);
        g.drawImage(IngresarNombre,(int)(380 * panel.obtenerJuego().escala),(int)(200 * panel.obtenerJuego().escala), null);
    }
    
    /**Setters y getters de la clase*/
    public boolean getPersonajeElegido() {
        return PersonajeElegido;
    }

    public void setPersonajeElegido(boolean PersonajeElegido) {
        this.PersonajeElegido = PersonajeElegido;
    }

    public JButton getFuego() {
        return fuego;
    }

    public JButton getAgua() {
        return agua;
    }

    public JButton getEmpezar() {
        return empezar;
    }

    public JTextField getIngresarNombre() {
        return ingresarNombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getNombreIngresado() {
        return nombreIngresado;
    }

    public void setNombreIngresado(boolean nombreIngresado) {
        this.nombreIngresado = nombreIngresado;
    }
}
