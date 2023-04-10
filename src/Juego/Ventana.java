
package Juego;

import javax.swing.JFrame;

public class Ventana {
    
    public JFrame ventana;
    
    /**Metodo constructor para crear un frame de juego*/
    public Ventana(Panel panel){
        ventana = new JFrame();
        ventana.add(panel);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);    
    }
}
