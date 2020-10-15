
package server;

import controllers.MainController;
import dbbeans.Useri;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import messagebeans.Mesaj;
import messagebeans.MesajText;
import messagebeans.Prieten;
import messagebeans.User;
import utils.CryptarePassword;


public class ServerThread extends Thread{
    private Socket socket; 
    private BufferedReader in; 
    private PrintWriter out; 
    private ObjectInputStream ois; 
    private ObjectOutputStream oos; 
    
    
    public ServerThread(Socket socket) throws Exception{
        this.socket = socket;
        
        out = new PrintWriter(socket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
         
        
    }
    
    public void run(){
        try{
                   
           while(true){
            Mesaj m =  (Mesaj) ois.readObject();
                           
                switch(m.getCod()){
                    case 1000 :
                        User user = (User) ois.readObject();
                        ArrayList<Prieten> listaPrietenilor =(ArrayList<Prieten>) MainController.getInstance().getPrieteniByUser(user.getId());
                        oos.writeObject(listaPrietenilor);
                    break;
                    
                    case 1001:
                        User u = (User) ois.readObject(); 
                            if(MainController.getInstance().verificareUser(u.getUser()) == null){
                                String pass = CryptarePassword.cryptWithMD5(u.getParola());
                                MainController.getInstance().adaugaUser(u.getUser(),pass);
                                oos.writeObject(new Mesaj(1002));
                            }else{
                                oos.writeObject(new Mesaj(1003));
                            }
                    break;
                    
                    case 1004:
                        User u1 = (User) ois.readObject();
                        Useri useri = MainController.getInstance().verificareUser(u1.getUser());
                        if(useri != null){
                            String pass = CryptarePassword.cryptWithMD5(u1.getParola()); 
                            if(pass.equals(useri.getParola())){
                                oos.writeObject(new Mesaj(1005));
                                oos.writeObject(new User(useri.getId(),useri.getUser(), useri.getParola()));
                            }
                        }else{
                                 oos.writeObject(new Mesaj(1006));
                        }
                        
                        break;
                        
                    case 1007:
                            Prieten p = (Prieten) ois.readObject();
                            User u4 = (User) ois.readObject();
                            if(MainController.getInstance().verificarePrieten(p) != null){
                                MainController.getInstance().adaugaPrieten(p.getNume(), u4.getId());
                                oos.writeObject(new Mesaj(1008));
                            }else{
                                oos.writeObject(new Mesaj(1009));
                            }
                            
                        break; 
                        
                    case 1010:
                           ArrayList<Prieten> listaNoiPrieteni = MainController.getInstance().getPrieteniByUser(0);
                           oos.writeObject(listaNoiPrieteni);
                        break;
                        
                    case 1011:  
                        
                          String line = in.readLine();
                            for(ServerThread st : Server.clienti){
                                st.sendMessage(line);                 
                            }
                        break;
                   
              }                
                                      
             
           }
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String message){
        out.println(message);
    }
    
        
}
