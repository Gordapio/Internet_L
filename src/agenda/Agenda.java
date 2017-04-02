/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class Agenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        Persistence.generateSchema("AgendaPU", null);
    }
    
}
