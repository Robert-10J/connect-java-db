package com.Main;

import com.BD.database;
import javax.swing.JOptionPane;

public class Menu {
    
    private int option = 0;
    
    database db = new database();
    //db.procedureUPD("Colision", 20500, 3);
    public void menu() {
        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, "Opciones"));
                
                switch( option ) {
                    case 1:
                        System.out.println("Insert");
                        break;
                    case 2:
                        System.out.println("Delete");
                        break;
                }    
            } catch( NumberFormatException ex ) {
                JOptionPane.showMessageDialog(null, "Opcion no valida",
                    "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        } while( option != 3);

    }
}
