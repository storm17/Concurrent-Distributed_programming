/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuido;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Fran
 */
public class Servidor extends Thread {
    
    
    private ServerSocket server;
    private Socket conexion;
    private boolean continuaEnvio;
    private DataOutputStream streamSalida;
    private InetAddress ip;
    private ArrayList <javax.swing.JTextField> mensajes;
    

    public Servidor(ArrayList <javax.swing.JTextField> mensajes)
    {
        this.mensajes = mensajes;
        continuaEnvio = true;
    }

    @Override
    
    //En este caso, como novedad, creamos una variable para controlar
    //la conexion al servidor
    public void run() {
        try {

            ip = InetAddress.getLocalHost();

            server = new ServerSocket(5000);

            conexion = server.accept();

            streamSalida = new DataOutputStream(conexion.getOutputStream());

            //Escribimos los mensajes que se pasan por sockets en los
            //jTextField de la interfaz grafica del servidor
            //mientras que la condicion sea true
            
            while (continuaEnvio) {
                streamSalida.writeUTF(mensajes.get(0).getText());
                streamSalida.writeUTF(mensajes.get(1).getText());

            }
            //Cuando deja de haber mensajes, se cierra la conexion
            conexion.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
        //Cambiamos la variable a false para que se cierre la conexion
    public synchronized void cerrarConexion() {
        continuaEnvio = false;
    }

}
