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
public class EmpleadoT extends Thread {

    private String id;
    private Mostrador most;
    private Mesa mesa;
    private String estado;
    private JTextField e;

    private boolean pausar;   //Detiene un hilo cuando es true 

    //Inicializamos el logger para esta clase
    private final static Logger LOG_RAIZ = Logger.getLogger("pecl1");
    private final static Logger LOGGER = Logger.getLogger("pecl1.EmpleadoT");

    public EmpleadoT(String id, Mostrador most, Mesa mesa, JTextField e) {
        this.id = id;
        this.most = most;
        this.mesa = mesa;
        this.e = e;
    }

    //Si pausar=true, se pausa el hilo
    public synchronized void pausarHilo() {
        pausar = true;
        notify();
    }

    //Si pausar=false, se reanuda el hilo
    public synchronized void reanudarHilo() {
        pausar = false;
        notify();
    }

    public void run() {

        
        String pedido;

        try {
            
            //Inicializamos los objetos Handler que crean un archivo .log
            //donde se guarda la actividad del programa con el formato hh:mm:ss
            
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("./evolucionRestaurante.log", false);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOG_RAIZ.addHandler(consoleHandler);
            LOG_RAIZ.addHandler(fileHandler);
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            while (true) {
                
                //Coge el pedido del mostrador
                pedido = most.cogerPedido();
                //most.imprimirMostrador();
                //System.out.println(id + " coge el pedido " + pedido + " del mostrador");
                
                //Deja el pedido que ha cogido del mostrador en la mesa
                //Despues imprime en el jTextField el string guardado en la variable estado
                mesa.dejarPedido(pedido);
                estado = "Empleado deja pedido " + pedido + " en la mesa";
                e.setText(estado);
                //Guardamos en el .log el estado del pedido
                LOGGER.log(Level.INFO, estado);
                //mesa.imprimirMesa();
                //System.out.println(id + " deja el pedido " + pedido + " en la mesa de pedidos");    
                
                //Los empleados esperan entre 300 y 700 ms para volver a actuar
                //Y se vacia el hueco una vez ha terminado de trabajar
                sleep((int) (300 + 400 * Math.random()));
                e.setText("");
                //Establecemos la condicion del monitor para que el hilo
                //espere mientras la variable sea true
                synchronized (this) {
                    while (pausar) {
                        wait();
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(EmpleadoT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(EmpleadoT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(EmpleadoT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
