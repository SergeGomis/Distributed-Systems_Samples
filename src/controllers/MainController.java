/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dbbeans.Prieteni;
import dbbeans.Useri;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import messagebeans.Prieten;
import messagebeans.User;

/**
 *
 * @author serge
 */
public class MainController {
    private static MainController controller; 
    private UseriJpaController userController;
    private PrieteniJpaController prietenController; 
    private EntityManagerFactory emf; 
    
    private MainController(){
        emf = Persistence.createEntityManagerFactory("MessengerConferintaPU");
        userController = new UseriJpaController(emf); 
        prietenController = new PrieteniJpaController(emf); 
    }
    
    public static MainController getInstance(){
        if(controller == null) controller = new MainController();
        return controller;
    }
    
    public ArrayList<User> getAllUsers(){
        List<Useri> list = userController.findUseriEntities();
        
        ArrayList<User> useri = new ArrayList<User>();
        
        for(Useri u : list){
            useri.add(new User(u.getId(),u.getUser(),u.getParola()));
        }
        return useri;
    }
    
    public Useri login(User user){
        Useri u = verificareUser(user.getUser()); 
        if(u !=null){
            if(u.getParola().equals(user.getParola())){
                return u;
            }
        }return null;
    }
    
    public Useri verificareUser(String user){
        return userController.findUserByUser(user);
    }
    
    public void adaugaUser(String user, String parola){        
        userController.create(new Useri(0, user,parola));       
    }
    
    public Prieteni verificarePrieten(Prieten p){
        return prietenController.findPrietenByNume(p.getNume());
    }
       
    
    public void adaugaPrieten(String nume, int user){       
        prietenController.create(new Prieteni(0, nume, user));
    }
    
    public ArrayList<Prieten> getPrieteniByUser(int id){
       List<Prieteni> list   =  prietenController.getPrieteniByUser(id);
       ArrayList<Prieten> prieteni = new ArrayList<>();
       for(Prieteni p:list){
           prieteni.add(new Prieten(p.getId(),p.getNume(),p.getUser()));
       }
       return prieteni;
    }
    
    public void addPrietenToUser(Prieten p){
        ArrayList<Prieten> prieteniByUser = getPrieteniByUser(p.getUser());
        prieteniByUser.add(p);
    }
}
