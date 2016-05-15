/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bourseServeur;

import bean.Action;
import dao.Actions;
import dao.Users;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ammach
 */
public class AttenteAjoutAction extends Thread{
    
    ServerSocket receptionAction;
    static final int port = 50000;
    ObjectInputStream objectInputStreamAction;

    public AttenteAjoutAction() {
        try {
            receptionAction = new ServerSocket(port);
        } catch (IOException e) {
            System.exit(1);
        }
        this.start();
    }

    public void run() {
        Socket socketAction;
        try {
            while (true) {
                socketAction = receptionAction.accept();
                System.out.println("Le serveur a accepté la connexion avec admin");
                
                
                objectInputStreamAction =new ObjectInputStream(socketAction.getInputStream()); 
                Action  action = (Action) objectInputStreamAction.readObject();
                if (action==null) {
                    System.out.println("action non ajoutée");
                }
                else{
                    System.out.println("ajout d'action en cours... ");
                    Actions.actions.add(action);
                }         
                System.out.println(" "+Actions.actions.size());
                for (Action a : Actions.actions) {
                    System.out.println("action ajoutée "+a.getNom());
                }
                
                socketAction.close();
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AttenteAjoutAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
