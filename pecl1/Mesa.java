/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecl1;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.swing.JTextField;

/**
 *
 * @author Fran
 */
public class Mesa {
    
    private ArrayList<String> mesa;
    private Semaphore mesaLlena; //Huecos que quedan libres
    private Semaphore mesaVacia= new Semaphore(0); //Num elementos en el array
    private Semaphore exclusionMutua = new Semaphore(1);
    private JTextField me;
    private String estado;
    
    public Mesa (int max, JTextField me){      //20
        mesa = new ArrayList<String>();
        mesaLlena = new Semaphore (max); 
        this.me=me;
    }
    
    public void dejarPedido(String pedido){
        
        try {
        mesaLlena.acquire();
        exclusionMutua.acquire();
        
        mesa.add(pedido);
        imprimirMesa();
        } catch (InterruptedException ie){}
        
        finally {
        exclusionMutua.release();
        mesaVacia.release();

        }
    }
    
    public String cogerPedido (){
        
        try {
        mesaVacia.acquire();
        exclusionMutua.acquire();
        
        String pedido = mesa.get(0);    
        mesa.remove(0);
        imprimirMesa();
        return pedido; 
        
        } catch (InterruptedException ie){}
        
        finally {
        exclusionMutua.release();
        mesaLlena.release();     
        }
        return "";
    }
    
    public void imprimirMesa() {
        String escribir="";
        //System.out.println("ESTADO MESA");
        for (int i = 0; i<mesa.size();i++){
            if (i == 0){
                escribir = mesa.get(i);
            }
            else{
                escribir = escribir + ", " + mesa.get(i);
            }        
        }
        //System.out.println(pintar);
        me.setText(escribir);
    }    
    
    public String getString(int i){
        
        return this.mesa.get((int) i);
    }

    public ArrayList<String> getMesa() {
        return mesa;
    }

    public void setMesa(ArrayList<String> mesa) {
        this.mesa = mesa;
    }
    
    


    
}
