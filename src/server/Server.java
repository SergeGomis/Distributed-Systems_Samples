/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.ServerSocket;
import java.util.ArrayList;



public class Server {
    static ServerSocket ss;
    static ServerThread [] clienti = new ServerThread[2];
    
    public static void main(String[] args) throws Exception{
        ss = new ServerSocket(4321);
        
        for(int i=0;i<clienti.length;i++){
            clienti[i] = new ServerThread(ss.accept());
            clienti[i].start();
        }
    }
    
}
