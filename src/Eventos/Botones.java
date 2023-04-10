package Eventos;
import Partes.*;
import Juego.*;
import Niveles.Niveles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Botones{
    Menu menu;
    Panel panel;
    IngresarInformacion info;
    MuerteDelJugador ganar;
    VictoriaDelPersonaje Victoria;
    
    /**Metodos contructores con distintos parámetros, sus objetos se usarán para manejar los eventos de botones*/
    public Botones(Menu menu){
        this.menu = menu;
        eventosMenu();
    }
    
    public Botones(Panel panel, IngresarInformacion info){
        this.panel = panel;
        this.info = info;
        eventosEleccion();
    }

    public Botones(MuerteDelJugador ganar, Panel panel) {
        this.ganar = ganar;
        this.panel = panel;
        ganar.botonesMuerte();
        eventosMuerte();
    }
    
    public Botones(Panel panel, VictoriaDelPersonaje Victoria){
       this.panel = panel; 
       this.Victoria = Victoria;
    }
    
    /**Metodo para manejar eventos al perder*/
    public void eventosMuerte(){
        ganar.botonGanar[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    PartesDelJuego.Parte = PartesDelJuego.Jugar; 
                    panel.reiniciarPartida();
                }
            });
 
        
        ganar.botonGanar[1].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PartesDelJuego.Parte = PartesDelJuego.Menu;
                panel.reiniciarPartida();
                panel.ResetearJuego();
            }
        });
        
        
        ganar.botonGanar[2].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); 


    }
    
    /**Metodo para iniciar los eventos de los botones al ganar*/
    public void eventosGanar(){
        Victoria.botonGanar[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    Niveles nivel2 = new Niveles(MetodosExtras.CargarMapas.obtenerNivel2(), panel, 0, 16, false, false);
                    panel.obtenerJugar().obtenerJugador().establecerNivelActual(nivel2);
                    panel.obtenerJugar().getJugador().setMatrizDelNivel(nivel2.obtenerNivel());
                    nivel2.setJugador(panel.obtenerJugar().getJugador());

                    panel.obtenerJugar().getJugador().setNivelConPlataformas(false);
                    panel.obtenerJugar().getJugador().setNiveLEmplezado(false);
                    panel.obtenerJugar().obtenerJugador().obtenerHitbox().y = 400;
                    
                    nivel2.setNivelConPuente(true);   
                    PartesDelJuego.Parte = PartesDelJuego.Jugar; 
                    panel.reiniciarPartida();
                }
            });
                
        Victoria.botonGanar[1].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PartesDelJuego.Parte = PartesDelJuego.Menu;
                panel.reiniciarPartida();
            }
        });
        
        Victoria.botonGanar[2].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
 
    
    /**Metodo para manejar eventos del menu*/
    public void eventosMenu(){
        menu.botonesMenu[0].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PartesDelJuego.Parte = PartesDelJuego.Eleccion;
                menu.desaparecerBotones();
            }
        });
        
        
        menu.botonesMenu[1].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                PartesDelJuego.Parte = PartesDelJuego.Creditos;
                menu.desaparecerBotones();
            }
        });
        
        menu.botonesMenu[2].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                PartesDelJuego.Parte = PartesDelJuego.Instrucciones;
                menu.desaparecerBotones();
            }
        });
        
        menu.botonesMenu[3].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });        
    }
    
    /**Metodo para manejar eventos al elegir personaje*/
    public void eventosEleccion(){  
        info.getFuego().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.obtenerJugar().obtenerJugador().establecerAparienciaFuego();
                panel.obtenerJugar().obtenerJugador().getNivelActual().setFuego(true);
                info.setPersonajeElegido(true);
            }
        });
        
        info.getAgua().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.obtenerJugar().obtenerJugador().establecerAparienciaAgua();
                panel.obtenerJugar().obtenerJugador().getNivelActual().setFuego(false);

                info.setPersonajeElegido(true);
            }
        }); 

        info.getIngresarNombre().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                info.setNombre(info.getIngresarNombre().getText());
                System.out.println(info.getNombre());
                info.setNombreIngresado(true);
            }
        });
        
        info.getEmpezar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(info.getPersonajeElegido() && info.getNombreIngresado()){ 
                    PartesDelJuego.Parte = PartesDelJuego.Jugar;
                    info.verBotones(false);                    
                }
            }
        });
    } 
}
