/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecl1;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JTextField;

/**
 *
 * @author Fran
 */
public class CocineroT extends Thread {

    private String id;
    private Mesa mesa;
    private JTextField coc;
    private String estado;
    
    
    private boolean pausar;    //Detiene un hilo cuando es true

    //Inicializamos el logger para esta clase
    private final static Logger LOG_RAIZ = Logger.getLogger("pecl1");
    private final static Logger LOGGER = Logger.getLogger("pecl1.CocineroT");

    public CocineroT(String id, Mesa mesa, JTextField coc) {
        this.id = id;
        this.mesa = mesa;
        this.coc = coc;
    }
    
    //Si pausar=true, se pausa el hilo
    public synchronized void pausarHilo(){
        pausar=true;
        notify();
    }
    
//Si pausar=false, se reanuda el hilo
    public synchronized void reanudarHilo(){
        pausar=false;
        notify();
    }  
    
    
    public void run() {

        String pedido;

        try {

            while (true) {

                //Coge el pedido de la mesa
                //Despues imprime en el jTextField el string guardado en la variable estado
                pedido = mesa.cogerPedido();
                estado = "Cocinado " + pedido;
                coc.setText(estado);

                //Guardamos en el .log el estado del pedido
                LOGGER.log(Level.INFO, estado);
                //mesa.imprimirMesa();
                //System.out.println(id + " cocina el pedido " + pedido); 
                
                //Los empleados esperan entre 1500 y 2000 ms para volver a actuar
                //Y se vacia el hueco una vez ha terminado de trabajar
                sleep((int) (1500 + 500 * Math.random()));
                coc.setText("");
                
                //Establecemos la condicion del monitor para que el hilo
                //espere mientras la variable sea true
                synchronized (this) {
                    while (pausar) {
                        wait();
                    }
                    
            }
            }
        
        } catch (SecurityException ex) {
            Logger.getLogger(CocineroT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CocineroT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
