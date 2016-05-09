/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import bourseproject.ClientConnecte;
import classes.Change;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ammach
 */
public  class ChangeThread extends Thread{

    ArrayList<Change> changes;
    Socket socket;
    public ChangeThread(Socket s) {
        this.socket=s;
        
        changes=new ArrayList<Change>();
        changes.add(new Change("france", "12"));
        changes.add(new Change("espagne", "12"));
        changes.add(new Change("italie ", "12"));
        changes.add(new Change("arabie saoudite", "12"));
        
        this.start();
    }

    
    
    @Override
    public void run() {
        super.run(); 
        ArrayList<String> valeurs;
        while (true) {              
            try {
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("changement de valeurs de change apres 3 secondes");
                valeurs=new ArrayList<String>();
                valeurs.add("change");
            for (Change change : changes) {
                int nbre=(int)(Math.random()*20);
                change.setValeur(String.valueOf(nbre));
                valeurs.add(change.getValeur());
                System.out.println(change.getNom()+"   "+change.getValeur()+" MAD");
                } 
                ClientConnecte clientConnecte=new ClientConnecte();
	        clientConnecte.connexion(socket.getInetAddress().getHostAddress().toString(), 40000);
	        clientConnecte.envoiObject(valeurs);           
               // clientConnecte.fermer();
            this.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChangeThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
