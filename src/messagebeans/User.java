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
public class User implements java.io.Serializable{
     private int id; 
    private String user; 
    private String parola; 

    public User() {
    }

    public User(String user, String parola) {
        this.user = user;
        this.parola = parola;
    }

    public User(int id, String user, String parola) {
        this.id = id;
        this.user = user;
        this.parola = parola;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return user;
    }

    
}
