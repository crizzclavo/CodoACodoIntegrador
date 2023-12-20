/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codoacodointegrador;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;

import javax.swing.JOptionPane;
import java.sql.DriverManager;

/**
 *
 * @author Usuario
 */
public class CConexion {
    
    Connection conectar = null;
    
    String usuario  = "root";
    String contrasenia = "kuragami";
    String baseDatos = "integrador_cac";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+baseDatos;
    
    public Connection establecerConexion(){
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
         
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            
            System.out.println("conexion exitosa");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos"+ e.toString());
            
            
            
        }
            
        
        return conectar;
    }
    
    
    
}
