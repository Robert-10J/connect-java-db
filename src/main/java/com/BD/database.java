package com.BD;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class database {

    private Connection con = null;

    public database() {
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=nameDatabase;user=userName;password=XXXXXX;";  
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
    
    public void procedureINS(String nameSeguro, int precio, int limit) {
        try {
            CallableStatement proce = con.prepareCall("{call insertSEG(?, ?, ?)}"); 
            proce.setString("nameSeguro", nameSeguro);   
            proce.setInt("precio", precio);
            proce.setInt("limit", limit);
            proce.execute();        
        } catch(SQLException e) {
            System.out.println(e);
        }
    }   
    
    public void procedureDelete(int id) {
        try {
            CallableStatement proc = con.prepareCall("{call deleteSEG(?)}");
            proc.setInt("idCli", id);   
            proc.execute();
        } catch(SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public DefaultTableModel Mostrar() {
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("NumSeguro");
        model.addColumn("Tipo");
        model.addColumn("Costo");
        model.addColumn("Caducidad");
        
        try {
            CallableStatement proce = con.prepareCall("{call procViewSeg}");
            ResultSet rs = proce.executeQuery();
            while(rs.next()) {
                Object data [] =  new Object[4];
                for(int i = 0; i < 4; i++) {
                    data[i] = rs.getString(i+1);
                }
                
                model.addRow(data);
            }
            
        } catch( Exception e) {
            System.out.println(e);
        }
        return model;
    }
     
    public DefaultTableModel MostrarRest(String type) {
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NumSeguro");
        model.addColumn("Tipo");
        model.addColumn("Costo");
        model.addColumn("Caducidad");
        
        try {
            CallableStatement proc = con.prepareCall("{call searchSEG(?)}");
            proc.setString("typeSeg", type);
            ResultSet rs = proc.executeQuery();
            while(rs.next()) {
                Object data [] =  new Object[4];
                for(int i = 0; i < 4; i++) {
                    data[i] = rs.getString(i+1);
                }
                model.addRow(data);
            }
            
        } catch( Exception e) {
            System.out.println(e);
        }
        return model;
    }
}
