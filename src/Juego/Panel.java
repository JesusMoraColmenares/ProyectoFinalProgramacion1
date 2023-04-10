package Juego;

import Eventos.Teclado;
import MetodosExtras.CargarImagenes;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import static MetodosExtras.CargarImagenes.Imagen;
import Partes.*;
import static Partes.PartesDelJuego.Creditos;
import static Partes.PartesDelJuego.Menu;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Partes.MuerteDelJugador;
import java.awt.Graphics2D;

public class Panel extends JPanel{
    /**Atributos de la clase*/
    Juego juego;
    Menu menu;
    Instrucciones instrucciones;
    Jugar jugar;
    Creditos creditos;
    IngresarInformacion eleccion;
    BufferedImage fondo;
    JButton regresar, pausa;
    MuerteDelJugador ganar;
    VictoriaDelPersonaje Victoria;
    boolean pausado;

    /**Metodo constructor, genera un panel para ubicar los elementos del juego*/
    public Panel(Juego juego){
        this.juego = juego;
        cargarElementos();
        Pausar();
        this.setLayout(null);
        cargarBotonDeRegresar();
        establecerTamaño();
        addKeyListener(new Teclado(this, jugar));
        this.setFocusable(true);
        this.requestFocus();
    }
    
    /**Metodo para inicializar los elementos del juego*/
    public void cargarElementos(){
        jugar = new Jugar(this);
        ganar = new MuerteDelJugador(this);
        Victoria = new VictoriaDelPersonaje(this);
        menu = new Menu(this);
        creditos = new Creditos();
        instrucciones = new Instrucciones();
        fondo = Imagen("recursos/fondo.png");
        eleccion = new IngresarInformacion(this);

    }

    /**Metodo para darle tamaño al panel*/
    private void establecerTamaño() {  
        Dimension Tamaño = new Dimension(juego.ancho, juego.alto);
        setMaximumSize(Tamaño);
        setPreferredSize(Tamaño);
        setMinimumSize(Tamaño);     
    }
    
    /**Metodo que genera un botón para regresar al menú de juego*/
    public void cargarBotonDeRegresar(){
        regresar = new JButton();

        ImageIcon atras = new ImageIcon(getClass().getResource("/recursos/botones/atras.jpg"));
        regresar.setIcon(atras);
        
            ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PartesDelJuego.Parte = PartesDelJuego.Menu;
                menu.reaparecerBotones();
                eleccion.verBotones(false);
                juego.cronometro.setVisible(false);
                pausa.setVisible(false);
                ResetearJuego();
            }     
        };
        
        regresar.addActionListener(accion);
    }
    
    /**Metodo para pausar el juego*/
    public void Pausar(){
        pausa = new JButton("Pausa");
        pausa.setVisible(false);
        pausa.setBounds(500, 30 , 83, 17);
        this.add(pausa);

        ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pausado){
                    juego.time.stop();
                    pausado = true;
                }else{
                    juego.time.start();
                    recuperarEventosDelTeclado();
                    pausado = false;
                    PartesDelJuego.Parte = PartesDelJuego.Jugar;
                }
            }     
        };
        
        pausa.addActionListener(accion);
        this.setFocusable(true);
        this.requestFocus();
    }
    
    /**Metodo para recuperar el foco en el panel*/
    public void recuperarEventosDelTeclado(){
        this.requestFocus(true);
    }
    
    /**Metodo para resetear los datos del jugador y los elementos del juego*/
    public void ResetearJuego(){
        juego.setContar(0);
        juego.setMin(0);
        juego.setSec(0);
        juego.obtenerTimerDelJuego().restart();
        eleccion.setPersonajeElegido(false);
        eleccion.setNombreIngresado(false);
        eleccion.getIngresarNombre().setText("");
        jugar.obtenerJugador().obtenerHitbox().x = 50;
        jugar.obtenerJugador().obtenerHitbox().y = 420;
        jugar.obtenerJugador().setFuego(false);
        jugar.obtenerJugador().setAgua(false);
        jugar.obtenerJugador().getNivelActual().activarDiamantes();
         jugar.obtenerJugador().getNivelActual().cargarDiamantes();
       


    }
    
    /**Metodo para volver a jugar una partida*/
    public void reiniciarPartida(){
        juego.setContar(0);
        juego.setMin(0);
        juego.setSec(0);
        
        jugar.getJugador().setIzquierda(false);
        jugar.getJugador().setPuntaje(0);
        jugar.getJugador().setDerecha(false);
        jugar.getJugador().getNivelActual().setAngulo(0);
        jugar.getJugador().getNivelActual().setAngulo2(0);
        juego.obtenerTimerDelJuego().restart();
        jugar.obtenerJugador().obtenerHitbox().x = 50;
        jugar.obtenerJugador().obtenerHitbox().y = 420;
        
        jugar.obtenerJugador().getNivelActual().activarDiamantes();
        jugar.obtenerJugador().getNivelActual().ubicarPuenteYPuertas();
    }
    
    /**Metodo para pintar los elementos sobre el panel*/
    @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       g.drawImage(fondo, 0, 0,juego.ancho, juego.alto, null);
       switch(Partes.PartesDelJuego.Parte){
		case Menu:
                    menu.renderizarMenu(g);
                    menu.reaparecerBotones();
                    regresar.setVisible(false);
                    ganar.mostrarBotones(false);
                    Victoria.mostrarBotones(false);
                    break;
                case Creditos:
                    creditos.renderizarCreditos(g);
                    regresar.setVisible(true);
                    ganar.mostrarBotones(false);
                    Victoria.mostrarBotones(false);
                    break;
                case Instrucciones:                  
                    instrucciones.renderizarInstrucciones(g);
                    regresar.setVisible(true);
                    ganar.mostrarBotones(false);
                    Victoria.mostrarBotones(false);
                    break;
                case Eleccion:
                    eleccion.renderizarIngresarInfo(g);
                    eleccion.verBotones(true);
                    regresar.setVisible(true);
                    ganar.mostrarBotones(false);
                    Victoria.mostrarBotones(false);
                    break;
                case Jugar:
                    juego.cronometro.setVisible(true);
                    regresar.setVisible(false);
                    pausa.setVisible(true);
                    ganar.mostrarBotones(false);
                    Victoria.mostrarBotones(false);
                    jugar.ImprimirMapaYjugador(g);
                    break;   
                case Muerte:
                    ganar.imprimirPantallaDeMuerte(g);
                    ganar.mostrarBotones(true);
                    juego.cronometro.setVisible(false);
                    Victoria.mostrarBotones(false);
                    pausa.setVisible(false);
                    break; 
                case Victoria:
                    Victoria.imprimirPantallaDeVictoria(g);
                    Victoria.mostrarBotones(true);
                    juego.cronometro.setVisible(false);
                    pausa.setVisible(false);  
                default:
			break;
        }        
    }
    
    /**Setters y getters de la clase*/
    public Jugar obtenerJugar(){
        return jugar;
    }
    public Juego obtenerJuego(){

        return juego;
    }
    
    public MuerteDelJugador obtenerGanar(){

        return ganar;
    }

    public IngresarInformacion getEleccion() {
        return eleccion;
    }

    public void setEleccion(IngresarInformacion eleccion) {
        this.eleccion = eleccion;
    }
}
