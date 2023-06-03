/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql2;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;

/**
 *
 * @author usuario291
 */
public class Canimales {
     int codigo;
    int ID;
    String NombreAnimal;
    String  EspecieAnimal;
    String RazaAnimal;
    String Edad;
String estatus;
    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreAnimal() {
        return NombreAnimal;
    }

    public void setNombreAnimal(String NombreAnimal) {
        this.NombreAnimal = NombreAnimal;
    }

    public String getEspecieAnimal() {
        return EspecieAnimal;
    }

    public void setEspecieAnimal(String EspecieAnimal) {
        this.EspecieAnimal = EspecieAnimal;
    }

    public String getRazaAnimal() {
        return RazaAnimal;
    }

    public void setRazaAnimal(String RazaAnimal) {
        this.RazaAnimal = RazaAnimal;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String Edad) {
        this.Edad = Edad;
    }
   public void InsertarAnimal(JTextField jTextNomAnimal,JTextField jTextEspeAnimal,JTextField jTextRazaAnimal,JTextField jTextEdadAnimal,JTextField txtEstatus){
       
       setNombreAnimal(jTextNomAnimal.getText());
       setEspecieAnimal(jTextEspeAnimal.getText());
       setRazaAnimal(jTextRazaAnimal.getText());
       setEdad(jTextEdadAnimal.getText());
       setEstatus(txtEstatus.getText());
       
       Cconexion objetoConexion = new Cconexion();
       
       String consulta = "INSERT INTO animal (nombre,especie,raza,edad,estatus) values(?,?,?,?,?);";
       
       try {
           CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
           
           cs.setString(1, getNombreAnimal());
           cs.setString(2, getEspecieAnimal());
           cs.setString(3, getRazaAnimal());
           cs.setString(4, getEdad());
           cs.setString(5, getEstatus());
           cs.execute();
           JOptionPane.showMessageDialog(null, "se inserto correctamente el animal");
           
           
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "no se inserto correctamente el animal, error: "+ e.toString());
       }
       
       
   }
   public void mostrarAnimales(JTable paramtablatotalanimales){
       Cconexion objetoConexion = new Cconexion();
       
       DefaultTableModel modelo =new DefaultTableModel();
       
       TableRowSorter<TableModel>Ordenartabla =new TableRowSorter<TableModel>(modelo);
       paramtablatotalanimales.setRowSorter(Ordenartabla);
       
       
       String sql="";
       
       modelo.addColumn("id");
       modelo.addColumn("Nombre");
       modelo.addColumn("Especie");
       modelo.addColumn("Raza");
       modelo.addColumn("Edad");
       modelo.addColumn("Estatus");
       
       paramtablatotalanimales.setModel(modelo);
       
       sql ="Select *from animal;";
       
       String[] datos = new String[6];
       Statement st;
       try {
           st= objetoConexion.estableceConexion().createStatement();
           
           ResultSet rs=st.executeQuery(sql);
           
           while(rs.next()){
               datos[0]=rs.getString(1);
               datos[1]=rs.getString(2);
               datos[2]=rs.getString(3);
               datos[3]=rs.getString(4);
               datos[4]=rs.getString(5);
               datos[5]=rs.getString(6);
               modelo.addRow(datos);
               
   
           }
           paramtablatotalanimales.setModel(modelo);
           
       } catch (Exception e) {
           
           JOptionPane.showInternalMessageDialog(null,"no se pudo mostrar los registros, error: "+e.toString());
       }
       
   }
   public void SeleccionarAnimales(JTable paramtablatotalanimales, JTextField paramid,JTextField paramNombres,JTextField paramEspecie,JTextField paramRaza,JTextField paramEdad,JTextField paramEstatus){
       
       try {
           int fila= paramtablatotalanimales.getSelectedRow();
           
           if(fila>=0){
               paramid.setText((paramtablatotalanimales.getValueAt(fila, 0).toString()));
               paramNombres.setText((paramtablatotalanimales.getValueAt(fila, 1).toString()));
               paramEspecie.setText((paramtablatotalanimales.getValueAt(fila, 2).toString()));
               paramRaza.setText((paramtablatotalanimales.getValueAt(fila, 3).toString()));
               paramEdad.setText((paramtablatotalanimales.getValueAt(fila, 4).toString()));
               paramEstatus.setText((paramtablatotalanimales.getValueAt(fila, 5).toString()));
               
               
           }else{
               JOptionPane.showInternalMessageDialog(null, "fila no selecionada");
           }
           
           
       } catch (Exception e) {
           JOptionPane.showInternalMessageDialog(null, "error de seleccion, error:"+e.toString());
       }
       
   }
   
   
   public void ModificarAnimales(JTextField paramid,JTextField paramNombres,JTextField paramEspecie,JTextField paramRaza,JTextField paramEdad,JTextField paramEstatus){
       
       setCodigo(Integer.parseInt(paramid.getText()));
       setNombreAnimal(paramNombres.getText());
       setEspecieAnimal(paramEspecie.getText());
       setRazaAnimal(paramRaza.getText());
       setEdad((paramEdad.getText()));
       setEstatus((paramEstatus.getText()));
     
       
       
       Cconexion objetocCconexion =new Cconexion() ;
       
       String consulta ="UPDATE animal SET animal.nombre = ?, animal.especie = ?, animal.Raza =?, animal.edad =?, animal.Estatus=? WHERE animal.id=?;";
       try {
           CallableStatement cs= objetocCconexion.estableceConexion().prepareCall(consulta);
           
          cs.setString(1, getNombreAnimal());
           cs.setString(2, getEspecieAnimal());
           cs.setString(3, getRazaAnimal());
           cs.setString(4, getEdad());
           cs.setString(5, getEstatus());

           cs.setInt(6, getCodigo());
           
           
           cs.execute();
           
           JOptionPane.showInternalMessageDialog(null, "modificacion exitosa ");
           
       } catch (SQLException e) {
           JOptionPane.showInternalMessageDialog(null, " no se modifico, error: "+e.toString());
       }
       
   }
   public void EliminarAnimales(JTextField paramcodigo){
       setCodigo(Integer.parseInt(paramcodigo.getText()));
       
       Cconexion objetoConexion = new Cconexion();
       
       String consulta="delete From animal WHere animal.id=?;";
       try {
           CallableStatement cs= objetoConexion.estableceConexion().prepareCall(consulta);
           cs.setInt(1, getCodigo());
           cs.execute();
           JOptionPane.showInternalMessageDialog(null, "se elimino correctamente el animal");
           
       } catch (SQLException e) {
           JOptionPane.showInternalMessageDialog(null, " no se elimino el animal, error: "+e.toString());
       }
       
   }
    
}
