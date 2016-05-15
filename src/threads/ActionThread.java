/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bourseServeur.ClientConnecte;
import classes.Action;
import classes.Change;
import classes.ModeChargement;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ammach
 */
public class ActionThread extends Thread{
    
    ArrayList<Action> actions;
    Socket socket;
    public ActionThread(Socket s) {
        this.socket=s;
        
        actions=new ArrayList<Action>();
        actions.add(new Action("MAZI", "12"));
        actions.add(new Action("AFMA", "12"));
        actions.add(new Action("AUTO HALL ", "12"));
        actions.add(new Action("AXA", "12"));
        
        this.start();
    }

    
    
    @Override
    public void run() {
        super.run(); 
        ArrayList<String> valeurs;
        while (true) {   
            if (ModeChargement.aleatoire=true) {
                try {
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("changement de valeurs d'action apres 3 secondes");
                valeurs=new ArrayList<String>();
                valeurs.add("action");
            for (Action action : actions) {
                double nbre=(double)(Math.random()*5);
                action.setValeur(String.valueOf(nbre).substring(0, 4));
                valeurs.add(action.getValeur());
                System.out.println(action.getNom()+"   "+action.getValeur()+" %");
                } 
                ClientConnecte clientConnecte=new ClientConnecte();
	        clientConnecte.connexion(socket.getInetAddress().getHostAddress().toString(), 40000);
	        clientConnecte.envoiObject(valeurs);           
               // clientConnecte.fermer();
            this.sleep(6000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChangeThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }
}
