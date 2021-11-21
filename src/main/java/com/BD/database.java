package com.BD;
import java.sql.*;
public class database {

    private Connection con = null;

    public database() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AseguradoraAutos;user=DBA-Roberto;password=1234;";  
            con = DriverManager.getConnection(connectionUrl);   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void closeConnection() {
        try {
            con.close();
        } catch( SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void procedureUPD(String nameSeguro, int precio, int limit) {
        try {
            CallableStatement proce = con.prepareCall("{call insertSEG(?, ?, ?)}"); 
            proce.setString("nameSeguro", nameSeguro);   
            proce.setInt("precio", precio);
            proce.setInt("limit", limit);
            proce.execute();
            closeConnection();
            
        } catch(SQLException e) {
            System.out.println(e);
        }
    }   
    
    public void procedureSelect() {
        try {
            CallableStatement proce = con.prepareCall("{call selectSEG}");
            proce.execute();
            proce.close();
        } catch(SQLException ex) {
            System.out.println(ex);
        }
    }
}
