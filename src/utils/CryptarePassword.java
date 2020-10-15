/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class CryptarePassword {
    
    private static  MessageDigest md; 
    
    private CryptarePassword() throws Exception{
       
    }
    
    public static String cryptWithMD5(String pass){
        try {
           
            md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] password = pass.getBytes();
            byte[] digested = md.digest(password);
            
            StringBuffer sb = new StringBuffer();
            
            for(int i = 0; i<digested.length; i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            
            return sb.toString();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptarePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
