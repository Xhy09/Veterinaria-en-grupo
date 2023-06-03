/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql2;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Cconexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "";
    String bd = "refugio";
    String ip = "localhost";
    String puerto = "3307";
 
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
           // JOptionPane.showMessageDialog(null, "la conexion se ha realizado con exito");
            
            
            
        }catch(Exception e){
            JOptionPane.showInternalMessageDialog(null, "error al conectar a base de datos: "+e.toString());
        }
        return conectar;
    }
    
}
