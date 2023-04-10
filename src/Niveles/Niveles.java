
package Niveles;

import Juego.Panel;
import Jugador.Personaje;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Jugador.*;
import java.awt.Color;
import Partes.MuerteDelJugador;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

public class Niveles{
    private int nivel[][];
    BufferedImage tiles[];
    int cantindadDePlataformas;
    int cantidadDeDiamantes = 0;
    boolean fuego;//true para fuego y false para agua
    Rectangle puente = new Rectangle(0,0, Juego.Juego.TamañoTiles * 7, Juego.Juego.TamañoTiles);
    Rectangle PuertaLevadiza = new Rectangle(0,0, Juego.Juego.TamañoTiles , Juego.Juego.TamañoTiles * 4);
    Rectangle placaDeLaPuerta = new Rectangle(0,0, 32 , 3);
    Rectangle puertas[] = new Rectangle[2];
    boolean nivelConPuente;
    int maxXPuente;
    Rectangle barraRotatoria = new Rectangle((Juego.Juego.TamañoTiles * 11)-10, Juego.Juego.TamañoTiles * 11,  Juego.Juego.TamañoTiles * 3, 8);
    Rectangle barraRotatoria2 = new Rectangle((Juego.Juego.TamañoTiles * 23)-10, Juego.Juego.TamañoTiles * 11,  Juego.Juego.TamañoTiles * 3, 8);
    Rectangle botonPuente = new Rectangle(0,0, 32 , 3);
    int angulo = 0, angulo2 = 0;
    public static boolean rotando;
    
    BufferedImage agua = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/agua.png");
    BufferedImage acido = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/acido.png");
    BufferedImage lava = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/fuego.png");
    BufferedImage puertaFuego = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/puertafuego.png");
    BufferedImage puertaAgua = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/puertaAgua.png");
    BufferedImage AguaLarga = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/aguaLarga.png");
    BufferedImage LavaLarga = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/lavaLarga.png");
    BufferedImage acidoLargo = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/acidoLargo.png");
        
    Timer timerPuertaLevadiza = new Timer();
    boolean puertaArriba = false;
    
    Plataformas plataformas[];
    Diamantes diamantes[];
    Personaje Jugador;
  
    Rectangle caja = new Rectangle(0, 0, (int)(32 * Juego.Juego.escala), (int)(32 * Juego.Juego.escala));
    //este vector lo inciaremos con una cierta longitud dependiendo e en que nivel estemos
    
    Panel panel;
    
    /** Metodo constructor, para cargar los niveles*/
    public Niveles(int nivel [][] , Panel panel, int cantidadDePlataformas ,int cantidadDeDiamantes, boolean ConPuentes, boolean conCaja){
        this.nivel =  nivel;
        this.cantidadDeDiamantes = cantidadDeDiamantes;
        this.diamantes = new Diamantes[cantidadDeDiamantes];
        this.cantindadDePlataformas = cantidadDePlataformas;
        this.cantidadDeDiamantes = cantidadDeDiamantes;
        this.plataformas = new Plataformas[cantindadDePlataformas];
        this.panel = panel;
        this.nivelConPuente = ConPuentes;

        iniciarTimerPuerta();
        ubicarPuenteYPuertas();
        //ubicarCaja();
        cargarDiamantes();
        cargarPlataformas();
    }

    /**Metodo para poner en el mapa los diamantes del elemento que escogio el jugador */
    public void activarDiamantes(){
        
        for(int r = 0; r < cantidadDeDiamantes; r++){
            if(Jugador.isFuego()){
                 if(diamantes[r].fuego){
                    diamantes[r].setActivo(true);  
                }    
            }else{
                if(!diamantes[r].fuego){
                    diamantes[r].setActivo(true);  
                }  
            }
        }         
    }
    
    /**Metodo para inicializar los diamantes en su ubicacion correspondiente */
    public void cargarDiamantes(){
        int k = 0;
            
        for(int i = 0; i < nivel.length; i++){
            for(int j = 0; j < nivel[0].length; j++){
                if(obtenerUnPuntoDelNivel(j , i) == 11){
                    diamantes[k] = new Diamantes(j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles, true);
                    k++;
                }else if(obtenerUnPuntoDelNivel(j , i) == 10){
                    diamantes[k] = new Diamantes(j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles, false);
                    k++;
                }
            }
        }   
    }
    

    
    /**Metodo encargado De mover el puente hacia un lado cuando el jugador presiona su boton*/
    public void moverPuente(){
        puente.x--;
    }
    
    /**Metodo encargado de hacer subir la puerta cuando el jugador presiona su boton */
    public void subirPuerta(){
        
        PuertaLevadiza.y --;  
    }
    
    public void bajarPuerta(){
        if(!Jugador.obtenerHitbox().intersects(placaDeLaPuerta)&&!Colisiones.DetectarColisiones.sobrePiso(PuertaLevadiza.x + 5, PuertaLevadiza.y + 10 , PuertaLevadiza.height, PuertaLevadiza.width, nivel, panel.obtenerJuego())){
            PuertaLevadiza.y += 7;
            
        }
        
        
    
        
    }
    
    /**Metodo encargado de inicializar la puerta y puente del nivel 2 al igual que sus botones*/
    public void ubicarPuenteYPuertas(){
        for(int i = 0; i < nivel.length; i++){
            for(int j = 0 ; j < nivel[0].length; j++){
                if(obtenerUnPuntoDelNivel(j , i) == 27){
                    puente.x = j * Juego.Juego.TamañoTiles;
                    puente.y = i * Juego.Juego.TamañoTiles;
                   
                }
                if(obtenerUnPuntoDelNivel(j , i) == 25){
                    PuertaLevadiza.x = j * Juego.Juego.TamañoTiles;
                    PuertaLevadiza.y = i * Juego.Juego.TamañoTiles;
                }
                if(obtenerUnPuntoDelNivel(j , i) == 24){
                    placaDeLaPuerta.x = j * Juego.Juego.TamañoTiles;
                    placaDeLaPuerta.y = i * Juego.Juego.TamañoTiles + 13;
                }
            }
        }
    }
    
    /**
     * Metodo encargado de Mostrar los elementos del mapa
     * 
     * @param g 
     */
    public void ImprimirMapa(Graphics g){      
        if(nivelConPuente){
            BufferedImage puenteImagen = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/puente.png");
            BufferedImage botonPuente = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/botonPuente.png");            
            BufferedImage PuertaLevadiza = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/Puerta.png");
            BufferedImage PlacaDeLaPuerta = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/botonDePuerta.png");
            g.drawImage(puenteImagen, puente.x, puente.y, puente.width, puente.height, null);
           
            g.drawImage(botonPuente, 485, puente.y - 10, 32, 8, null);
            g.drawImage(PuertaLevadiza, this.PuertaLevadiza.x, this.PuertaLevadiza.y, this.PuertaLevadiza.width, this.PuertaLevadiza.height, null);
            g.drawImage(PlacaDeLaPuerta, this.placaDeLaPuerta.x, this.placaDeLaPuerta.y, 32, 8, null);
            //g.drawRect((int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height); 
        }


        if(cantindadDePlataformas == 0){
            BufferedImage nivel2 = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/nivel2.png");
            g.drawImage(nivel2, 0, 0,740, 580,null) ;        
        }else{
            BufferedImage nivel1 = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/nivel1.png");
            g.drawImage(nivel1, 0, 0,740, 580,null) ;
        }
      
        //cargar todas las plataformas
        if(cantindadDePlataformas > 0){
            for(int r = 0; r < cantindadDePlataformas; r++){
                plataformas[r].dibujarPlataformas(g);
            }
        }

        //el nivel comienza se considera empezado cuando el jugador tome el primer diamante
        if(!Jugador.isNiveLEmplezado()){
            activarDiamantes();
        }
        for(int r = 0; r < cantidadDeDiamantes; r++){
            if(diamantes[r].isActivo()){
                diamantes[r].dibujarDiamantes(g);
            }   
        } 

        for(int i = 0; i < nivel.length; i++){
           for(int j = 0 ; j < nivel[0].length; j++){
                if(obtenerUnPuntoDelNivel(j , i) == 32){
                    //impresionDeAcidoLargo
                    g.drawImage(acidoLargo,j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 6,Juego.Juego.TamañoTiles, null);
                    j+=6;
                }
                
                //impresion De Acido Normal
                if(obtenerUnPuntoDelNivel(j , i) == 33){
                    g.drawImage(acido,j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 3,Juego.Juego.TamañoTiles, null);
                    j+=6;
                } 
                
                //estas son las aguas y lava del nivel 2
                //impresionDeLavaLarga
                if(obtenerUnPuntoDelNivel(j , i) == 30){
                    g.drawImage(LavaLarga,j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 6,Juego.Juego.TamañoTiles, null);
                    j+=6;
                } 
                
                //impresionDeAguaLarga
                if(obtenerUnPuntoDelNivel(j , i) == 31){
                    g.drawImage(AguaLarga,j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 6,Juego.Juego.TamañoTiles, null);
                    j+=6;
                }
                
                //impresionDeAguaNormal
                if(obtenerUnPuntoDelNivel(j , i) == 2){
                    g.drawImage(agua,j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 3,Juego.Juego.TamañoTiles, null);
                    j+=2;
                } 
                
                //impresionDeLavaNormas
                if(obtenerUnPuntoDelNivel(j , i) == 1){
                    g.drawImage(lava,j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 3,Juego.Juego.TamañoTiles, null);
                    j+=2;
                } 

                //IMPRESION DE LAS PUERTAS, DENTRO DE ESTE IF POSICIONE LA HITBOX DE LAS PUERTAS
                if(obtenerUnPuntoDelNivel(j , i) == 200){
                    g.drawImage(puertaAgua,j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 2 ,Juego.Juego.TamañoTiles * 2, null);
                    puertas[0] = new Rectangle(j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 2 ,Juego.Juego.TamañoTiles * 2);
                }
                
                if(obtenerUnPuntoDelNivel(j , i) == 201){
                    g.drawImage(puertaFuego,j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 2,Juego.Juego.TamañoTiles * 2, null);
                    puertas[1] = new Rectangle(j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles,Juego.Juego.TamañoTiles * 2 ,Juego.Juego.TamañoTiles * 2);
                }  
            }
        }
    }
    
    
    /** Metodo encargado  de hacer que roten las barras del nivel 2*/
    public void rotar(Graphics2D g2d){
        if(nivelConPuente){
            BufferedImage barraRotatoriaImagen = MetodosExtras.CargarImagenes.Imagen("recursos/mapas/barraGiratoria.png");

            g2d.setColor(Color.white);
            int centerX1 = (int)(barraRotatoria.getX()+ (barraRotatoria.getWidth()/2));
            int centerY1 = (int)(barraRotatoria.getY()+ (barraRotatoria.getHeight()/2));
            
            int centerX2 = (int)(barraRotatoria2.getX()+ (barraRotatoria2.getWidth()/2));
            int centerY2 = (int)(barraRotatoria2.getY()+ (barraRotatoria2.getHeight()/2));

            AffineTransform rotacionAntes = g2d.getTransform();

            AffineTransform rotacion1 = new AffineTransform();
            rotacion1.rotate(Math.toRadians(angulo), centerX1, centerY1);
            g2d.transform(rotacion1);

            AffineTransform rotacion2 = new AffineTransform();
            rotacion2.rotate(Math.toRadians(angulo2), centerX2, centerY2);
            
            //g2d.drawRect((int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height);

            if(comprobarRotacion(rotacion1, (int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height)){
                if(Jugador.obtenerHitbox().getX()+(Jugador.getAncho()/2)<centerX1){
                   angulo = angulo-10;
                }else{angulo = angulo+10;}
                
                g2d.drawImage(barraRotatoriaImagen,(int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height, null);
                //g2d.fillRect((int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height);    
                g2d.setTransform(rotacion2);
                g2d.drawImage(barraRotatoriaImagen,(int)barraRotatoria2.getX(), (int)barraRotatoria2.getY(),(int) barraRotatoria2.width, (int)barraRotatoria2.height, null);
                //g2d.fillRect((int)barraRotatoria2.getX(), (int)barraRotatoria2.getY(),(int) barraRotatoria2.width, (int)barraRotatoria2.height);    
                Jugador.setArriba(0);
                rotando= true;
            }else if(comprobarRotacion(rotacion2, (int)barraRotatoria2.getX(), (int)barraRotatoria2.getY(),(int) barraRotatoria2.width, (int)barraRotatoria2.height)){
                if(Jugador.obtenerHitbox().getX()+(Jugador.getAncho()/2)<centerX2){
                   angulo2 = angulo2-10;
                }else{angulo2 = angulo2+10;}
                

                g2d.setTransform(rotacion2);
                g2d.drawImage(barraRotatoriaImagen,(int)barraRotatoria2.getX(), (int)barraRotatoria2.getY(),(int) barraRotatoria2.width, (int)barraRotatoria2.height, null);
                //g2d.fillRect((int)barraRotatoria2.getX(), (int)barraRotatoria2.getY(),(int) barraRotatoria2.width, (int)barraRotatoria2.height);
                
                g2d.setTransform(rotacion1);
                g2d.drawImage(barraRotatoriaImagen,(int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height, null);
                //g2d.fillRect((int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height);   
                Jugador.setArriba(0);
                rotando= true;
            }else{
                g2d.drawImage(barraRotatoriaImagen,(int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height, null);
               //g2d.fillRect((int)barraRotatoria.getX(), (int)barraRotatoria.getY(),(int) barraRotatoria.width, (int)barraRotatoria.height);
                
                
                g2d.setTransform(rotacion2);
                //g2d.fillRect((int)barraRotatoria2.getX(), (int)barraRotatoria2.getY(),(int) barraRotatoria2.width, (int)barraRotatoria2.height);    
                g2d.drawImage(barraRotatoriaImagen,(int)barraRotatoria2.getX(), (int)barraRotatoria2.getY(),(int) barraRotatoria2.width, (int)barraRotatoria2.height, null);
                rotando=false;
            }

            g2d.setTransform(rotacionAntes);
        } 
    }
    
    /**Metodo encargado de comprobar si el jugador intersecta una barra giratoria,  para hacerla rotar*/
    public boolean comprobarRotacion(AffineTransform rotacion, int x, int y, int w, int h){
        Shape barra = rotacion.createTransformedShape(new Rectangle(x,y,w,h));
        //Shape barra2 = rotacion.createTransformedShape(new Rectangle((int)barraRotatoria2.getX(), (int)barraRotatoria2.getY(),(int) barraRotatoria2.width, (int)barraRotatoria2.height));
                
        if(barra.intersects(Jugador.obtenerHitbox())){
            return true;
        }
        return false;
    }
    
    /** Metodo encargado de inicializar y ubicar las plataforas del nivel 1*/
    public void cargarPlataformas(){
        //este metodo se encargara de inicializar las plataformas
        int k = 0; 
            for(int i = 0; i < nivel.length; i++){
                for(int j = 0; j < nivel[0].length; j++){
                    //el 5 sera donde esten las plataformas
                    if(obtenerUnPuntoDelNivel(j , i) == 5){
                        plataformas[k] = new Plataformas(j * Juego.Juego.TamañoTiles, i *Juego.Juego.TamañoTiles);
                        k++;
                        j += 2;
                    } 
                }
            }
            
            k = 0;
            for(int i = 0; i < nivel.length; i++){
                for(int j = 0; j < nivel[0].length; j++){
                    if(obtenerUnPuntoDelNivel(j , i) == 6){
                        plataformas[k].placa.obtenerHitbox().x = j *Juego.Juego.TamañoTiles ;
                        plataformas[k].placa.obtenerHitbox().y = i *Juego.Juego.TamañoTiles + 12;
                        k++; 
                    }
                }                
            }
   
    }
    
    public void iniciarTimerPuerta(){
        
        TimerTask timer= new TimerTask(){
            @Override
            public void run() {
                if(puertaArriba)
                bajarPuerta();

            }
            
            
            
        };
        
        timerPuertaLevadiza.schedule(timer, 1000, 1000);
        
    }
    
    /**Setters y Getters de la clase*/
    public boolean isFuego() {
        return fuego;
    }

    public void setFuego(boolean fuego) {
        this.fuego = fuego;
    }
    
    public Plataformas[] obtenerPlataformas(){
        return plataformas;
    }

    public Personaje getJugador() {
        return Jugador;
    }

    public void setJugador(Personaje Jugador) {
        this.Jugador = Jugador;
    }

    public boolean isNivelConPuente() {
        return nivelConPuente;
    }

    public void setNivelConPuente(boolean nivelConPuente) {
        this.nivelConPuente = nivelConPuente;
    }

    public int getMaxXPuente() {
        return maxXPuente;
    }

    public void setMaxXPuente(int maxXPuente) {
        this.maxXPuente = maxXPuente;
    }

    public Rectangle getPuente() {
        return puente;
    }

    public void setPuente(Rectangle puente) {
        this.puente = puente;
    }

    public Rectangle getPuertaLevadiza() {
        return PuertaLevadiza;
    }

    public void setPuertaLevadiza(Rectangle PuertaLevadiza) {
        this.PuertaLevadiza = PuertaLevadiza;
    }

    public Rectangle getPlacaDeLaPuerta() {
        return placaDeLaPuerta;
    }

    public void setPlacaDeLaPuerta(Rectangle placaDeLaPuerta) {
        this.placaDeLaPuerta = placaDeLaPuerta;
    }
    
     public int getCantidadDeDiamantes() {
        return cantidadDeDiamantes;
    }

    public void setCantidadDeDiamantes(int cantidadDeDiamantes) {
        this.cantidadDeDiamantes = cantidadDeDiamantes;
    }
    
    public int  obtenerUnPuntoDelNivel(int x, int y){ 
        return nivel[y][x];
    }
    
    public int [][] obtenerNivel(){
        return nivel;
    }
    
    public Diamantes[] obtenerDiamantesDelNivel(){
        return diamantes;
    }

    public Rectangle getPuertas(int i) {
        return puertas[i];
    }

    public Panel getPanel() {
        return panel;
    }

    public Rectangle getCaja() {
        return caja;
    }

    public void setCaja(Rectangle caja) {
        this.caja = caja;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public void setAngulo2(int angulo2) {
        this.angulo2 = angulo2;
    }

    public Timer getTimerPuertaLevadiza() {
        return timerPuertaLevadiza;
    }

    public void setTimerPuertaLevadiza(Timer timerPuertaLevadiza) {
        this.timerPuertaLevadiza = timerPuertaLevadiza;
    }

    public boolean isPuertaArriba() {
        return puertaArriba;
    }

    public void setPuertaArriba(boolean puertaArriba) {
        this.puertaArriba = puertaArriba;
    }
    
    
}
