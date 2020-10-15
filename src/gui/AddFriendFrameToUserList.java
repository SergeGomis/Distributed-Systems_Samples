/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import client.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import messagebeans.Mesaj;
import messagebeans.Prieten;
import messagebeans.User;
import obspattern.PrietenListener;
import obspattern.SubiectPrieten;

/**
 *
 * @author serge
 */
public class AddFriendFrameToUserList extends javax.swing.JFrame implements SubiectPrieten{
    DefaultListModel<Prieten> model = new DefaultListModel<Prieten>();
    private User user;
        
    /**
     * Creates new form AddFriendForm
     */
    public AddFriendFrameToUserList(User user) {
        initComponents();
        this.user = user;
        jList2.setModel(model);
        afisareNewPrieteni(); 
    }
    
    public void afisareNewPrieteni(){
        try {
            Mesaj m = new Mesaj(1010);
            Client.oos.writeObject(m);
            ArrayList<Prieten> prieteniNoi = (ArrayList<Prieten>) Client.ois.readObject();
            model.clear();
            for(Prieten p : prieteniNoi) model.addElement(p);
        } catch (IOException ex) {
            Logger.getLogger(AddFriendFrameToUserList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddFriendFrameToUserList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Adauga");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("NUME: ");

        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Prieten p = jList2.getSelectedValue();
            
            Mesaj m = new Mesaj(1007);
            Client.oos.writeObject(m);
            Client.oos.writeObject(p);
            Client.oos.writeObject(user);
          
            
            Mesaj m1 = (Mesaj) Client.ois.readObject();
            
            if(m1.getCod() == 1008){
                JOptionPane.showMessageDialog(null,"Prieten adaugat","Message", JOptionPane.INFORMATION_MESSAGE);
                notifica(); 
            }else{
                JOptionPane.showMessageDialog(null,"Alegeti alt nume","Error", JOptionPane.ERROR_MESSAGE);
            }
            
            dispose();
            
        } catch (Exception ex) {
            Logger.getLogger(AddFriendFrameToUserList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        if(evt.getClickCount() == 1){
            Prieten p = jList2.getSelectedValue();
            jTextField1.setText(p.getNume());
        }
    }//GEN-LAST:event_jList2MouseClicked

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<Prieten> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addPrietenListener(PrietenListener pl) {
       obs.add(pl);
    }

    @Override
    public void notifica() {
        for(PrietenListener pl : obs) pl.prieteniModificat();
    }
}
