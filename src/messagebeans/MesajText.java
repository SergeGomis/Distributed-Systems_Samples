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
public class MesajText {
    private String text;
    private int user;

    public MesajText() {
    }

    public MesajText(String text) {
        this.text = text;
    }

    public MesajText(String text, int user) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return text;
    }
    
    
    
}
