/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecl1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author Fran
 */
public class Mostrador {
    
    private ArrayList<String> most;
    private Semaphore mostradorLleno; //Huecos que quedan libres
    private Semaphore mostradorVacio = new Semaphore(0); //Num elementos en el array
    private Semaphore exclusionMutua = new Semaphore(1);
    private JTextField mo;
    private String estado;
    
    public Mostrador (int max, JTextField mo) { //10
        most = new ArrayList<String>();
        mostradorLleno = new Semaphore (max);
        this.mo = mo;
    }
    
    public void dejarPedido(String pedido) {

        try {
            mostradorLleno.acquire();
            exclusionMutua.acquire();

            most.add(pedido);
            imprimirMostrador();

        } catch (InterruptedException ie) {
        } finally {
            exclusionMutua.release();
            mostradorVacio.release();

        }

    }

    public String cogerPedido() {

        try {
            mostradorVacio.acquire();
            exclusionMutua.acquire();

            String pedido = most.get(0);
            most.remove(0);
            this.imprimirMostrador();
            return pedido;

        } catch (InterruptedException ie) {
        } finally {
            exclusionMutua.release();
            mostradorLleno.release();

        }
        return "";
    }

    public void imprimirMostrador() {
        String escribir = "";
        //System.out.println("ESTADO MOSTRADOR");
        for (int i = 0; i < most.size(); i++) {
            if (i == 0) {
                escribir = most.get(i);
            } else {
                escribir = escribir + ", " + most.get(i);
            }
        }
        //System.out.println(pintar);
        mo.setText(escribir);
    }

    public ArrayList<String> getMost() {
        return most;
    }

    public void setMost(ArrayList<String> most) {
        this.most = most;
    }

}
