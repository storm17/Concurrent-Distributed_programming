/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuido;

import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Fran
 */
public class Cliente extends Thread {
    
    private Socket cliente;
    private DataInputStream streamEntrada;
    private InetAddress ip;
    private ArrayList <javax.swing.JTextField> mensajes;
    
    
    
    public Cliente(ArrayList <javax.swing.JTextField> mensajes)
    {
        this.mensajes=mensajes;
    }
    
    //Asignamos a la ip nuestra ip local
    //Le asignamos al socket cliente la ip y el host 5000
    //Creamos un objeto que será la entrada de datos
    
    public void run()
    {
        try
        {
            
        ip = InetAddress.getLocalHost();
        
        cliente = new Socket(ip, 5000);
        
        streamEntrada = new DataInputStream(cliente.getInputStream());
        
        //Leemos los mensajes que se pasan por sockets

        while(true)
        {
            
            mensajes.get(0).setText(streamEntrada.readUTF());
            mensajes.get(1).setText(streamEntrada.readUTF());
            
        }
        
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
