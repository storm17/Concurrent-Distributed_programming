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
public class ClienteT extends Thread {
    
    private String id;
    private Mostrador most;
    private String estado;
    
    
    //Inicializamos el logger para esta clase
    private final static Logger LOG_RAIZ = Logger.getLogger("pecl1");
    private final static Logger LOGGER = Logger.getLogger("pecl1.ClienteT");

    public ClienteT(String identificador, Mostrador mostrador) {
        this.id = identificador;
        this.most = mostrador;
    }

    
    public void run() {

        
        String pedido;
        int i = 0;

        try {

            //La variable i solo tendrá dos valores (0 o 1) porque
            //queremos 2 pedidos para cada cliente
            
            while (i <= 1) {
                
                //Pone nombre al pedido y lo deja en el mostrador
                pedido = id + "-" + "P" + i;
                i++;
                most.dejarPedido(pedido);
                
                //Guardamos en el .log la actividad
                LOGGER.log(Level.INFO, estado);
                //most.imprimirMostrador();
                //System.out.println(id + " deja pedido " + pedido + " en mostrador");
                
                //Los empleados esperan entre 500 y 1000 ms para volver a actuar
                sleep((int) (500 + 500 * Math.random()));

            }

        } catch (SecurityException ex) {
            Logger.getLogger(ClienteT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
