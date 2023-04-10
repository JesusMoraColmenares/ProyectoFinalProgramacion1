
package MetodosExtras;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class CargarImagenes {
    
    /**Metodo para cargar las imagenes del juego*/
    public static BufferedImage Imagen(String fileName){
        BufferedImage img = null;
        InputStream is = CargarImagenes.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        }catch (IOException ex) {
            /**Cierra el flujo de entrada*/
        }finally{
            try{
                is.close();  
            }catch(IOException ex){}
        }
        return img;
    } 
}
