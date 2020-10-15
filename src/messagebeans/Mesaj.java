/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagebeans;

/**
 *
 * @author serge
 */
public class Mesaj implements java.io.Serializable{
   private int cod; 

    public Mesaj() {
    }

    public Mesaj(int cod) {
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
   
   
       
}
