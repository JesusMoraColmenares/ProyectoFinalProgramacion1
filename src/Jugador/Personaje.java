
package Jugador;

import Juego.Juego;
import Niveles.Niveles;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import Partes.MuerteDelJugador;
import Juego.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Personaje extends Objetos implements ActionListener{
    /**Atributos de la clase*/
    int yMin, arriba;
    int indiceAnimacion = 1;
    boolean sobrePuente = false;
    Timer time;
    boolean agua, fuego, derecha, izquierda, volteadoIzquierda, volteadoDerecha;
    int velocidad = 7;// 5 pixeles se movera por ahora
    Niveles nivelActual;
    BufferedImage Apariencia;
    boolean nivelConPlataformas;
    int MatrizDelNivel[][];
    BufferedImage [][] animaciones;
    boolean niveLEmplezado = false;
    Juego juego;
    boolean PersonajeSobrePlataforma = false;
    int puntaje = 0;
    boolean sobreCaja = false;
    int minutosJugados;
    int segundosJugados;
    int CantidadDeNivelesGanados = 0;
    
    /**Metodo constructor, para crear un personaje en el juego*/
    public Personaje(int x, int y, int alto, int ancho,int[][] MatrizDelNivel, Juego juego) {
        super(x, y, alto, ancho);
        iniciarHitbox(x, y, 15, 15);
        time = new Timer(100, this);
        time.start();
        this.MatrizDelNivel = MatrizDelNivel;
        this.juego = juego;
        fuego = false;
        agua = false;
        derecha = false;
        izquierda = false;
        arriba = 0;
        yMin = 0;
       
    }
    
    /**Metodo para controlar las animaciones*/
    public void animaciones(){
        BufferedImage sprites = MetodosExtras.CargarImagenes.Imagen("recursos/sprites.png");
        animaciones = new BufferedImage[8][13];
        
        for(int j = 0; j < animaciones.length; j++){
            for(int i = 0; i < animaciones[0].length; i++){
                if(j == 1){
                    animaciones[j][i] = sprites.getSubimage(i * 16, j * 15, 16, 15);
                }else{
                    animaciones[j][i] = sprites.getSubimage(i * 16, j * 15, 16, 15);
                }   
            }
        }  
    }


    /**Metodo para cargar las animaciones del personaje de fuego*/
    public void establecerAparienciaFuego(){
        BufferedImage sprites = MetodosExtras.CargarImagenes.Imagen("recursos/fire.png");
        animaciones = new BufferedImage[8][13];

        for(int j = 0; j < animaciones.length; j++){
            for(int i = 0; i < animaciones[0].length; i++){
                if(j == 1){
                    animaciones[j][i] = sprites.getSubimage(i * 16, j * 15, 16, 15);
                }else{
                    animaciones[j][i] = sprites.getSubimage(i * 16, j * 15, 16, 15);
                } 
            }             
        }

        fuego = true;
    }
    
    /**Metodo para cargar las animaciones del personaje de agua*/    
    public void establecerAparienciaAgua(){
        BufferedImage sprites = MetodosExtras.CargarImagenes.Imagen("recursos/water.png");
        animaciones = new BufferedImage[8][13];
        for(int j = 0; j < animaciones.length; j++){
            for(int i = 0; i < animaciones[0].length; i++){
                if(j == 1){
                    animaciones[j][i] = sprites.getSubimage(i * 16, j * 15, 16, 15);
                }else{
                    animaciones[j][i] = sprites.getSubimage(i * 16, j * 15, 16, 15);
                }
            } 
        }
        agua = true;
    }

    /**Metodo para dibujar el personaje*/
    public void dibujarPersonaje(Graphics g){
        
        if(derecha && indiceAnimacion <= 7)
        g.drawImage(animaciones[1][indiceAnimacion], hitbox.x - 5 , hitbox.y - 10, 30, 30, null);
        if(izquierda && indiceAnimacion <= 7)
        g.drawImage(animaciones[1][indiceAnimacion], hitbox.x  + 20 , hitbox.y - 10, -30, 30, null);
        if(!izquierda && !derecha && volteadoDerecha)
        g.drawImage(animaciones[0][indiceAnimacion], hitbox.x  - 5 , hitbox.y - 10, 30, 30, null);
        if(!izquierda && !derecha && volteadoIzquierda)
        g.drawImage(animaciones[0][indiceAnimacion], hitbox.x  + 25 , hitbox.y - 10, -30, 30, null);
    
        if(!izquierda && !derecha && !volteadoDerecha && !volteadoIzquierda)
        g.drawImage(animaciones[0][indiceAnimacion], hitbox.x  - 5 , hitbox.y - 10, 30, 30, null);    
    
    }
    
    /**Metodo para determinar la interacción del personaje con las plataformas, activación y desactivación de las mismas*/
    public void verificacionDePlataformas(int i){
        if(this.hitbox.intersects(nivelActual.obtenerPlataformas()[i].obtenerPlaca().obtenerHitbox())){          
            if(!nivelActual.obtenerPlataformas()[i].isActivada() &&!Colisiones.DetectarColisiones.sobrePiso( nivelActual.obtenerPlataformas()[i].obtenerHitbox().x,  nivelActual.obtenerPlataformas()[i].obtenerHitbox().y+ 5, nivelActual.obtenerPlataformas()[i].obtenerHitbox().height,  nivelActual.obtenerPlataformas()[i].obtenerHitbox().width, MatrizDelNivel, juego)){
                nivelActual.obtenerPlataformas()[i].setMinY(nivelActual.obtenerPlataformas()[i].y); 
            }   
            if(!Colisiones.DetectarColisiones.sobrePiso( nivelActual.obtenerPlataformas()[i].obtenerHitbox().x,  nivelActual.obtenerPlataformas()[i].obtenerHitbox().y + 5, nivelActual.obtenerPlataformas()[i].obtenerHitbox().height,  nivelActual.obtenerPlataformas()[i].obtenerHitbox().width, MatrizDelNivel, juego)){
                nivelActual.obtenerPlataformas()[i].bajar();
            }
        }else{
            if(nivelActual.obtenerPlataformas()[i].isActivada()){
                nivelActual.obtenerPlataformas()[i].subir();
            }
        }            
    }

    /**Metodo para determinar la posicion del personaje*/
    public void actualizarPosicion(){
        /**Interacción del personaje con el puente corredizo*/
        if(nivelActual.isNivelConPuente()){
            
            
            if(hitbox.intersects(nivelActual.getPlacaDeLaPuerta()) && !Colisiones.DetectarColisiones.sobrePiso(nivelActual.getPuertaLevadiza().x , nivelActual.getPuertaLevadiza().y + nivelActual.getPuertaLevadiza().height, nivelActual.getPuertaLevadiza().height, nivelActual.getPuertaLevadiza().width, MatrizDelNivel, juego)){
                nivelActual.subirPuerta();
                nivelActual.setPuertaArriba(true);
            }
            if(Colisiones.DetectarColisiones.mover(nivelActual.getPuente().x, nivelActual.getPuente().y, nivelActual.getPuente().height, 1, MatrizDelNivel, juego)&& Colisiones.DetectarColisiones.activarPuente(hitbox.x,hitbox.y, hitbox.height, hitbox.width, MatrizDelNivel, juego)){
                nivelActual.moverPuente();
            }
            if(hitbox.intersects(nivelActual.getPuente())){ 
                hitbox.y-=7;
                sobrePuente = true;
            }else{
                sobrePuente = false;
            }
        }

        /**Victoria y derrota de niña agua*/
        if(agua){
            if(Colisiones.DetectarColisiones.sobreElemento(hitbox.x,hitbox.y, hitbox.height, hitbox.width, MatrizDelNivel, juego, false)){
                hitbox.y -=7;
            }
            if(Colisiones.DetectarColisiones.sobreElementoOpuesto(hitbox.x,hitbox.y, hitbox.height, hitbox.width, MatrizDelNivel, juego, false)){
                Partes.PartesDelJuego.Parte = Partes.PartesDelJuego.Muerte;
                establecerTiempoJugado();
            } 
            
            if(Partes.PartesDelJuego.Parte == Partes.PartesDelJuego.Jugar && niveLEmplezado){
                if(nivelActual.getPuertas(0).intersects(hitbox.x, hitbox.y , hitbox.width, hitbox.height)){
                    Partes.PartesDelJuego.Parte = Partes.PartesDelJuego.Victoria;
                    CantidadDeNivelesGanados++;
                    establecerTiempoJugado();
//juego.getPanel().obtenerGanar().setGanar(true);
                }
            }
        }

        /**Victoria y derrota de niña agua*/
        if(fuego){
            if(Colisiones.DetectarColisiones.sobreElemento(hitbox.x,hitbox.y, hitbox.height, hitbox.width, MatrizDelNivel, juego, true)){
                hitbox.y -=7;
            }
            
            if(Colisiones.DetectarColisiones.sobreElementoOpuesto(hitbox.x,hitbox.y, hitbox.height, hitbox.width, MatrizDelNivel, juego, true)){ 
                Partes.PartesDelJuego.Parte = Partes.PartesDelJuego.Muerte;
                    Partes.PartesDelJuego.Parte = Partes.PartesDelJuego.Muerte;
                    establecerTiempoJugado();
//                  juego.getPanel().obtenerGanar().setGanar(false);
            }

            if(Partes.PartesDelJuego.Parte == Partes.PartesDelJuego.Jugar && niveLEmplezado){
                if(nivelActual.getPuertas(1).intersects(hitbox.x, hitbox.y , hitbox.width, hitbox.height)){
                    Partes.PartesDelJuego.Parte = Partes.PartesDelJuego.Victoria;
                    CantidadDeNivelesGanados++;
                    establecerTiempoJugado();
                }
            } 
        }
        
        /**Verificacion de perdida por colision con ácido*/
        if(Colisiones.DetectarColisiones.sobreAcido(hitbox.x,hitbox.y, hitbox.height, hitbox.width, MatrizDelNivel, juego)){
            Partes.PartesDelJuego.Parte = Partes.PartesDelJuego.Muerte;
                    Partes.PartesDelJuego.Parte = Partes.PartesDelJuego.Muerte;
                    establecerTiempoJugado();

        }

        /**Verificación de colisiones con diamantes*/
        for(int j = 0; j < nivelActual.getCantidadDeDiamantes(); j++){
            if(nivelActual.obtenerDiamantesDelNivel()[j].isActivo() && this.hitbox.intersects(nivelActual.obtenerDiamantesDelNivel()[j].obtenerHitbox())){
                nivelActual.obtenerDiamantesDelNivel()[j].setActivo(false);
                niveLEmplezado = true;
                puntaje++;
                System.out.println(puntaje);
            }      
        }
            
        /**Verificar si el personaje se encuentra sobre una plataforma activa*/
        for(int i = 0; i < nivelActual.obtenerPlataformas().length;i++){
            if(nivelConPlataformas){
                verificacionDePlataformas(0);
                verificacionDePlataformas(1);

                if(this.hitbox.y + hitbox.height <= nivelActual.obtenerPlataformas()[i].obtenerHitbox().y && this.hitbox.y + hitbox.height >= nivelActual.obtenerPlataformas()[i].obtenerHitbox().y - 10 ){   
                    if(hitbox.x >= nivelActual.obtenerPlataformas()[i].hitbox.x - 10 && hitbox.x <= nivelActual.obtenerPlataformas()[i].hitbox.x + nivelActual.obtenerPlataformas()[i].hitbox.width)
                        hitbox.y -=7;
                }
                
                if(nivelActual.obtenerPlataformas()[i].isActivada() && this.hitbox.y + hitbox.height <= nivelActual.obtenerPlataformas()[i].obtenerHitbox().y && this.hitbox.y + hitbox.height >= nivelActual.obtenerPlataformas()[i].obtenerHitbox().y - nivelActual.obtenerPlataformas()[i].obtenerHitbox().height ){  
                    if(hitbox.x >= nivelActual.obtenerPlataformas()[i].hitbox.x && hitbox.x <= nivelActual.obtenerPlataformas()[i].hitbox.x + nivelActual.obtenerPlataformas()[i].hitbox.width)
                        hitbox.y -=8;
                }
            }
        }

        /**Actualización de movimiento hacia los lados*/
        if(Colisiones.DetectarColisiones.mover((int)hitbox.x + velocidad, (int)hitbox.y, hitbox.height, hitbox.width, MatrizDelNivel, juego)){   
            if(derecha && !nivelActual.getPuertaLevadiza().intersects(hitbox.x + hitbox.width , hitbox.y , hitbox.width, hitbox.height)){
                hitbox.x += velocidad;
            }  
        }
        
        if(Colisiones.DetectarColisiones.mover(hitbox.x - velocidad, (int)hitbox.y, hitbox.height, hitbox.width, MatrizDelNivel, juego)){
            if(izquierda && !nivelActual.getPuertaLevadiza().intersects(hitbox.x, hitbox.y , hitbox.width, hitbox.height)){
                hitbox.x -= velocidad;
            }
        }

        /**Caida del personaje al no estar posicionado sobre un bloque*/        
        if(!Colisiones.DetectarColisiones.sobrePiso(hitbox.x, (int)hitbox.y + velocidad, hitbox.height, hitbox.width, MatrizDelNivel, juego)){
            if(arriba == 0 ){
                hitbox.y += velocidad;
            }
        }
        
        /**Saltos del personaje*/
        if(!Colisiones.DetectarColisiones.choqueConTecho(hitbox.x, (int)hitbox.y - velocidad, hitbox.height, hitbox.width, MatrizDelNivel, juego)){
            if(!Colisiones.DetectarColisiones.bajoPlataforma(hitbox.x, (int)hitbox.y - velocidad, hitbox.height, hitbox.width, MatrizDelNivel, juego)){
                if(arriba == 1){
                    if(hitbox.y > yMin -60){ 
                      hitbox.y -= velocidad;    
                    }else{arriba = 0;}      
                } 
            }else {arriba = 0;}
        }else {arriba = 0;}  

    }
    
    /**Actualizacion de animaciones*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(indiceAnimacion == 12){
            indiceAnimacion = 0;
        }
        if(derecha && indiceAnimacion == 7 || izquierda && indiceAnimacion == 7){
            indiceAnimacion = 0;
        }else if(izquierda && indiceAnimacion == 5){
            indiceAnimacion = 7;
        }else if(izquierda && indiceAnimacion == 12){
            indiceAnimacion = 0;
        }else if(!izquierda && !derecha && indiceAnimacion == 3){
            indiceAnimacion = 0;
        }else{
            indiceAnimacion++;
        }
    }
    
    /**Setters y Getters de la clase*/
    public void establecerTiempoJugado(){
        minutosJugados = juego.getMin();
        segundosJugados = juego.getSec();
    }
    public boolean isAgua() {
        return agua;
    }

    public void setAgua(boolean agua) {
        this.agua = agua;
    }

    public boolean isFuego() {
        return fuego;
    }

    public void setFuego(boolean fuego) {
        this.fuego = fuego;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }

    public void setArriba(int arriba) {
        this.arriba = arriba;
    }

    public int getArriba() {
        return arriba;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    public int getY() {
        return y;
    }
    public int[][] obtenerNivel(){
        
        return MatrizDelNivel;
    }

    public boolean isNiveLEmplezado() {
        return niveLEmplezado;
    }

    public void setNiveLEmplezado(boolean niveLEmplezado) {
        this.niveLEmplezado = niveLEmplezado;
    }

    public int[][] getMatrizDelNivel() {
        return MatrizDelNivel;
    }

    public void setMatrizDelNivel(int[][] MatrizDelNivel) {
        this.MatrizDelNivel = MatrizDelNivel;
    }

    public boolean isSobrePuente() {
        return sobrePuente;
    }

    public void setSobrePuente(boolean sobrePuente) {
        this.sobrePuente = sobrePuente;
    } 
    
    public Rectangle obtenerHitbox(){
        return hitbox;
    }

    public boolean isPersonajeSobrePlataforma() {
        return PersonajeSobrePlataforma;
    }

    public void setPersonajeSobrePlataforma(boolean PersonajeSobrePlataforma) {
        this.PersonajeSobrePlataforma = PersonajeSobrePlataforma;
    }

    public boolean isNivelConPlataformas() {
        return nivelConPlataformas;
    }

    public void setNivelConPlataformas(boolean nivelConPlataformas) {
        this.nivelConPlataformas = nivelConPlataformas;
    }
    
    public void establecerNivelActual(Niveles nivel){
        
        this.nivelActual = nivel;
    }

    public Niveles getNivelActual() {
        return nivelActual;
    } 

    public boolean isSobreCaja() {
        return sobreCaja;
    }

    public void setSobreCaja(boolean sobreCaja) {
        this.sobreCaja = sobreCaja;
    }

    public int getIndiceAnimacion() {
        return indiceAnimacion;
    }

    public void setIndiceAnimacion(int indiceAnimacion) {
        this.indiceAnimacion = indiceAnimacion;
    }

    public boolean isVolteadoIzquierda() {
        return volteadoIzquierda;
    }

    public void setVolteadoIzquierda(boolean volteadoIzquierda) {
        this.volteadoIzquierda = volteadoIzquierda;
    }

    public boolean isVolteadoDerecha() {
        return volteadoDerecha;
    }

    public void setVolteadoDerecha(boolean volteadoDerecha) {
        this.volteadoDerecha = volteadoDerecha;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    public int getSegundosJugados() {
        return segundosJugados;
    }

    public void setSegundosJugados(int segundosJugados) {
        this.segundosJugados = segundosJugados;
    }

    public int getCantidadDeNivelesGanados() {
        return CantidadDeNivelesGanados;
    }

    public void setCantidadDeNivelesGanados(int CantidadDeNivelesGanados) {
        this.CantidadDeNivelesGanados = CantidadDeNivelesGanados;
    }
    
    
}
