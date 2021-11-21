package com.Main;

import com.BD.database;

public class Main {
    public static void main(String[] args) {
        
        /*Menu options = new Menu();
        
        options.menu();*/
        
        database db = new database();
        //db.procedureUPD("Colision", 20500, 6);
        db.procedureSelect();
    }
}
