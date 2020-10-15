/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obspattern;

import java.util.ArrayList;

/**
 *
 * @author serge
 */
public interface SubiectPrieten {
    
    ArrayList<PrietenListener> obs = new ArrayList<PrietenListener>(); 
    
    public void addPrietenListener(PrietenListener pl);
    
    public void notifica(); 
    
}
