
package Colisiones;

import Juego.Juego;
public class DetectarColisiones {
    /**
     * 
     * Metodo para determinar si el personaje puede moverse a una posición válida
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @return retorna un booleano el cual dira si el elemento del cual se pasaron sus atributos se puede mover tanto a izquierda como a derecha
     */
    public static boolean mover(int x,  int y, int alto, int ancho, int [][]dataDelnivel, Juego juego){
        if(!choque(x, y , dataDelnivel, juego,23))
            if(!choque(x + ancho, y + alto, dataDelnivel, juego,23))
                if(!choque(x + ancho, y, dataDelnivel, juego,23))
                    if(!choque(x, y + alto, dataDelnivel, juego,23))
                        return true;
        
        return false; 
    }
    
    /**
     * Metodo para determinar si el personaje puede moverse a ambos lados
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @return retorna si el elemento del cual se pasaron sus parametros se puede mover a la izquierdda
     */
    public static boolean moverAlosLados(int x,  int y, int alto, int ancho, int [][]dataDelnivel, Juego juego){
        if(!choque(x + ancho, y , dataDelnivel, juego,23)){
            return true;
        }else return false;      
    }
    
    /**
     * Metodo para determinar si el personaje puede moverse especificamente a la zquierda
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @return da un booleano que dice si el elemento del cual se pasan sus atributos no colisiona con un muro
     */
    public static  boolean moverAlosLadosIzquierda(int x,  int y, int alto, int ancho, int [][]dataDelnivel, Juego juego){  
        if(!choque(x, y , dataDelnivel, juego,23)){
            return true;
        }else return false; 
    }
    
    /**
     * Metodo para determinar si el personaje se encuentra sobre una plataforma
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @return retorna si el es que el jugador o cualquier otro elemento que del cual se pasen sus parametros esta encima de una
     */
    public static boolean SobrePlataforma(int x,  int y, int alto, int ancho, int [][]dataDelnivel, Juego juego){
        if(choque(x + ancho, y + alto , dataDelnivel, juego,23)){
            return true; 
        }else if(choque(x, y + alto , dataDelnivel, juego,5)){
            return true;      
        }else return false;
    }
    
    /**
     * Metodo para determinar si el personaje se encuentra sobre agua o lava, de manera valida por su naturaleza
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @param fuego
     * @return 
     */
    public static boolean sobreElemento(int x,  int y , int alto, int ancho, int [][]dataDelnivel, Juego juego, boolean fuego){
        if(fuego){  
            if(choque(x, y + 10 , dataDelnivel, juego, 1) || choque(x, y + 10 , dataDelnivel, juego, 30)){
                    return true;
            } 
        } 
        if(!fuego){  
            if(choque(x, y + 10 , dataDelnivel, juego, 2) || choque(x, y + 10 , dataDelnivel, juego, 31) ){
                    return true;
            }
        } 
        return false;
    }
    
    /**
     * Metodo para determinar si el personaje está sobre ácido
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @return 
     */
    public static boolean sobreAcido(int x,  int y , int alto, int ancho, int [][]dataDelnivel, Juego juego){
        if(choque(x, y + 10 , dataDelnivel, juego, 32)){
            return true;
        }

        if(choque(x, y + 10 , dataDelnivel, juego, 33)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo para determinar si el personaje se encuentra sobre lava o agua, de manera invalida por su naturaleza
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @param fuego
     * @return 
     */
    public static boolean sobreElementoOpuesto(int x,  int y , int alto, int ancho, int [][]dataDelnivel, Juego juego, boolean fuego){
        if(fuego)  
            if(choque(x, y + 10 , dataDelnivel, juego, 2) || choque(x, y + 10 , dataDelnivel, juego, 31)){
                return true;
            }
        
        if(!fuego)  
            if(choque(x, y + 10 , dataDelnivel, juego, 1) || choque(x, y + 10 , dataDelnivel, juego, 30)){
                return true;
            } 
        return false;
    }
    
    /**
     * Metodo para comprobar la activacion de la plataforma corrediza del nivel 2
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @return 
     */
    public static boolean activarPuente(int x,  int y , int alto, int ancho, int [][]dataDelnivel, Juego juego){
        if(choque(x , y + alto , dataDelnivel, juego, 39)){
            return true; 
        } 
        
        return false;
    }
    
    /**
     * Metodo para saber si el personaje se encuentra bajo una plataforma
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @return 
     */
    public static boolean bajoPlataforma(int x,  int y, int alto, int ancho, int [][]dataDelnivel, Juego juego){
        if(choque(x, y , dataDelnivel, juego, 5)){
            return true;
        }else if(choque(x + ancho, y , dataDelnivel, juego,5)){
            return true;   
        }else{return false;}      
    }
    
    /**
     * Metodo para verificar la parte de abajo del personaje para saber si se encuentra sobre algún pis
     * @param x
     * @param y
     * @param alto
     * @param ancho
     * @param dataDelnivel
     * @param juego
     * @return 
     */
    public static boolean sobrePiso(int x,  int y, int alto, int ancho, int [][]dataDelnivel, Juego juego){
        if(choque(x + ancho, y + alto , dataDelnivel, juego, 23)){
            return true;        
        }else if(choque(x, y + alto , dataDelnivel, juego, 23)){
            return true;     
        }else return false;    
    }
    
    /**Metodo para determinar si el personaje intercepta con un bloque en su parte de arriba*/
    public static boolean choqueConTecho(int x,  int y, int alto, int ancho, int [][]dataDelnivel, Juego juego){
        if(choque(x, y , dataDelnivel, juego, 23) || choque(x, y , dataDelnivel, juego, 33)){
            return true;
        }else if(choque(x + ancho, y , dataDelnivel, juego, 23) || choque(x + ancho, y , dataDelnivel, juego, 33)){
            return true;    
        }
        
        if(choque(x, y , dataDelnivel, juego, 30)){
            return true;
            
        }else if(choque(x + ancho, y , dataDelnivel, juego, 30)){
            return true;      
        } 
  
        if(choque(x, y , dataDelnivel, juego, 31)){
            return true;
        }else if(choque(x + ancho, y , dataDelnivel, juego, 31)){
            return true;       
        }
        
        if(choque(x, y , dataDelnivel, juego, 32)){
            return true;  
        }else if(choque(x + ancho, y , dataDelnivel, juego, 32)){
            return true;      
        }else return false;
    }
    
    /**Metodo para determinar los numeros que hay en la matriz que se genera a partir de la imagen
    de los pixeles de colores del mapa, para saber si el personaje se encuentra sobre alguno y
    de esta manera retornar si intercepta dicho bloque*/
    public static boolean choque(int x, int y, int[][] dataDelNivel, Juego juego, int valorBuscado){
        
        if(x < 0 || x >=  juego.ancho ){
            return true;
        }
        if(y < 0 || y >= juego.alto)
            return true;
        
        float xMatriz = x / juego.TamañoTiles;
        float yMatriz = y / juego.TamañoTiles;
        
        int valor = dataDelNivel[(int)yMatriz][(int)xMatriz];

        if(valor == valorBuscado){
            return true;  
        }else {
            return false;
        }
    }
}
