/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codoacodointegrador;




import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author Usuario
 */
public class Oradores {

    private int id;

    private String nombre;
    private String apellido;
    private String mail;
    private String Tema;
    private String fechaAlta;
    
     Connection con = null;
    PreparedStatement pst;
    ResultSet rs;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getTema() {
        return Tema;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTema(String Tema) {
        this.Tema = Tema;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

   
    

    
    
    public void insertarOrador(JTextField nombre, JTextField apellido, JTextField mail, JTextField tema, JTextField fechaAlta){
        
        this.setNombre(nombre.getText());
        this.setApellido(apellido.getText());
        this.setMail(mail.getText());
        this.setTema(tema.getText());
        this.setFechaAlta(fechaAlta.getText());
        
        
        CConexion objetoConexion = new CConexion();
        
        String consulta ="insert into oradores(nombre, apellido ,mail ,tema ,fecha_alta ) values (?, ?, ?, ?, ?);";
        
        try {
            CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getMail());
            cs.setString(4, getTema());
           cs.setString(5, getFechaAlta());
           cs.execute();
           
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el orador");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: no se inserto correctamente"+ e.toString());
        }
        
    }
    
    
    public void seleccionarOrador(JTable tablaOradores, JTextField paramId, JTextField paramNombre, JTextField paramApellido, JTextField paramMail, JTextField paramTema, JTextField paramFechaAlta){
        
        try {
            int fila= tablaOradores.getSelectedRow();
            
            if(fila>=0){
                
                paramId.setText(tablaOradores.getValueAt(fila, 0).toString());
                paramNombre.setText(tablaOradores.getValueAt(fila, 1).toString());
                paramApellido.setText(tablaOradores.getValueAt(fila, 2).toString());
                paramMail.setText(tablaOradores.getValueAt(fila, 3).toString());
                paramTema.setText(tablaOradores.getValueAt(fila, 4).toString());
                paramFechaAlta.setText(tablaOradores.getValueAt(fila, 5).toString());
            }
            
            else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de seleccion"+ e.toString());
            
        }
        
        
        
    }
    public void modificarOradores ( JTextField paramId, JTextField paramNombre, JTextField paramApellido, JTextField paramMail, JTextField paramTema, JTextField paramFechaAlta){
        
        
        this.setId(Integer.parseInt(paramId.getText()));
        this.setNombre(paramNombre.getText());
        this.setApellido(paramApellido.getText());
        this.setMail(paramMail.getText());
        this.setTema(paramTema.getText());
        this.setFechaAlta(paramFechaAlta.getText());
        
        
        CConexion objetoConexion = new CConexion();
        String consulta ="UPDATE oradores SET oradores.nombre=?, oradores.apellido=?, oradores.mail=?, oradores.tema=?, oradores.fecha_alta=? WHERE oradores.id_orador=?;";
        
        try {
            
            CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getMail());
            cs.setString(4, getTema());
           cs.setString(5, getFechaAlta());
            cs.setInt(6, getId());
           cs.execute();
           
            JOptionPane.showMessageDialog(null, "Se modifico correctamente el orador");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: no se logro modificar el orador"+ e.toString());
        }
        
    }
    
    public void borrarOrador (JTextField paramId){
        this.setId(Integer.parseInt(paramId.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta="DELETE FROM oradores WHERE id_orador=?;";
        
         try {
            
            CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
            
          
            cs.setInt(1, getId());
           cs.execute();
           
            JOptionPane.showMessageDialog(null, "Se borró correctamente el orador");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: no se logro borrar el orador"+ e.toString());
        }
        
        
    }
    
    public void mostrarOradores (JTable tablaOradores){
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new  DefaultTableModel();
        
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
        tablaOradores.setRowSorter(ordenarTabla);
        
        String consultaSql = "select * from oradores;";
        
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Mail");
        modelo.addColumn("Tema");
        modelo.addColumn("Fecha Alta");
        
        tablaOradores.setModel(modelo);
        
        String [] datos = new String[6];
        Statement st;
        
        try {
            st = objetoConexion.establecerConexion().createStatement();
            
            
            ResultSet rs = st.executeQuery(consultaSql);
            
            while (rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                
                modelo.addRow(datos);
            }
            
            tablaOradores.setModel(modelo);
            crearXml(objetoConexion);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error: no se pudo mostrar los registros"+ e.toString());
        }
        
    }
        
         public void crearXml(CConexion objetoConexion) throws IOException, SQLException {
        
        
        String filePath="D:/NetBeans/Proyectos/CodoACodoIntegrador/oradores.xml";
        Path path = Paths.get(filePath);
        Files.delete(path);
        
        String consulta="Select * from oradores";
        
        try{
          
            
            CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
            
           rs=cs.executeQuery();
        } catch (SQLException e1){
            e1.printStackTrace();
        }
        String line="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        FileWriter cb= new FileWriter(filePath, true);
        cb.write(line);
        cb.close();
        
        line="<oradores>";
        FileWriter ap= new FileWriter(filePath, true);
        ap.write(line);
        ap.close();
        
        
        while(rs.next()){
           
            line= "<orador><nombre>" +rs.getString("nombre")+"</nombre><apellido>"+rs.getString("apellido")+"</apellido> <mail>"+rs.getString("mail")+"</mail><tema>"+rs.getString("tema")+"</tema><fecha_alta>"+rs.getString("fecha_alta")+"</fecha_alta></orador>";
            
            FileWriter fw= new FileWriter(filePath, true);
            fw.write(line);
            fw.close();
        }
        
        
        line="</oradores>";
        FileWriter fo= new FileWriter(filePath, true);
        fo.write(line);
        fo.close();
        
    }
        
    }

