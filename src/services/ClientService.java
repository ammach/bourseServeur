/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import classes.Client;
import dao.Users;

/**
 *
 * @author ammach
 */
public class ClientService {
    
    
    public Client getClient(String name,String pass){   
        for (Client c  : Users.clients) {
            if (c.getName()==name && c.getPassword()==pass) {
                return c;
            }
        }
        return null;
    }
}
