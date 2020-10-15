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
public class Prieten implements java.io.Serializable{
    private int id; 
    private String nume; 
    private int user; 

    public Prieten() {
    }

    public Prieten(String nume, int user) {
        this.nume = nume;
        this.user = user;
    }

    public Prieten(int id, String nume, int user) {
        this.id = id;
        this.nume = nume;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return nume;
    }
    
    
   
}
