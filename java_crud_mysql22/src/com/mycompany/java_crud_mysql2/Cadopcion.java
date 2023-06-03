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
public class Cadopcion {
int codigo;
    int ID;
    String NombreAdoptante;
    String  numeroDeTelefono;
    String idAnimal;
    String fechaAdopcion;
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

    public String getNombreAdoptante() {
        return NombreAdoptante;
    }

    public void setNombreAdoptante(String NombreAdoptante) {
        this.NombreAdoptante = NombreAdoptante;
    }

    public String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(String fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }
     
    
   public void InsertarAdopcion(JTextField jTextNomAdoptantel,JTextField txtnumtelefono,JTextField txtidanimal,JTextField txtfechaadopcion){
       
       setNombreAdoptante(jTextNomAdoptantel.getText());
       setNumeroDeTelefono(txtnumtelefono.getText());
       setIdAnimal(txtidanimal.getText());
       setFechaAdopcion(txtfechaadopcion.getText());
       
       
       Cconexion objetoConexion = new Cconexion();
       
       String consulta = "INSERT INTO adopcion(nombreadoptante,Numerodetelefono,animal_id,fecha_adopcion) values(?,?,?,?);";
       
       try {
           CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
           
           cs.setString(1, getNombreAdoptante());
           cs.setString(2, getNumeroDeTelefono());
           cs.setString(3, getIdAnimal());
           cs.setString(4, getFechaAdopcion());
           cs.execute();
           JOptionPane.showMessageDialog(null, "se inserto correctamente el animal");
           
           
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "no se inserto correctamente el animal, error: "+ e.toString());
       }
       
       
   }
   public void mostrarAdopcion(JTable tbtotaAdopcion){
       Cconexion objetoConexion = new Cconexion();
       
       DefaultTableModel modelo =new DefaultTableModel();
       
       TableRowSorter<TableModel>Ordenartabla =new TableRowSorter<TableModel>(modelo);
       tbtotaAdopcion.setRowSorter(Ordenartabla);
       
       
       String sql="";
       
       modelo.addColumn("id");
       modelo.addColumn("Nombre Adoptante");
       modelo.addColumn("Numero de telefono");
       modelo.addColumn("Id animal");
       modelo.addColumn("Fecha adopcion");
       
       tbtotaAdopcion.setModel(modelo);
       
       sql ="Select * from adopcion;";
       
       String[] datos = new String[5];
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
               modelo.addRow(datos);
               
   
           }
           tbtotaAdopcion.setModel(modelo);
           
       } catch (Exception e) {
           
           JOptionPane.showInternalMessageDialog(null,"no se pudo mostrar los registros, error: "+e.toString());
       }
       
   }
   public void SeleccionarAdopcion(JTable tbtotaAdopcion, JTextField paramidadopcion,JTextField paramnombresadopcion,JTextField paramnumero,JTextField paramidanimal,JTextField paramfecha){
       
       try {
           int fila= tbtotaAdopcion.getSelectedRow();
           
           if(fila>=0){
               paramidadopcion.setText((tbtotaAdopcion.getValueAt(fila, 0).toString()));
               paramnombresadopcion.setText((tbtotaAdopcion.getValueAt(fila, 1).toString()));
               paramnumero.setText((tbtotaAdopcion.getValueAt(fila, 2).toString()));
               paramidanimal.setText((tbtotaAdopcion.getValueAt(fila, 3).toString()));
               paramfecha.setText((tbtotaAdopcion.getValueAt(fila, 4).toString()));
               
               
           }else{
               JOptionPane.showInternalMessageDialog(null, "fila no selecionada");
           }
           
           
       } catch (Exception e) {
           JOptionPane.showInternalMessageDialog(null, "error de seleccion, error:"+e.toString());
       }
       
   }
   
   
   public void ModificarAdopcion(JTextField paramidadopcion,JTextField paramnombresadopcion,JTextField paramnumero,JTextField paramidanimal,JTextField paramfecha){
       
       setCodigo(Integer.parseInt(paramidadopcion.getText()));
       setNombreAdoptante(paramnombresadopcion.getText());
       setNumeroDeTelefono(paramnumero.getText());
       setIdAnimal(paramidanimal.getText());
       setFechaAdopcion((paramfecha.getText()));
 
     
       
       
       Cconexion objetocCconexion =new Cconexion() ;
       
       String consulta ="UPDATE adopcion SET adopcion.nombreadoptante = ?, adopcion.Numerodetelefono = ?, adopcion.animal_id =?, adopcion.fecha_adopcion =? WHERE adopcion.id=?;";
       try {
           CallableStatement cs= objetocCconexion.estableceConexion().prepareCall(consulta);
           
          cs.setString(1, getNombreAdoptante());
           cs.setString(2, getNumeroDeTelefono());
           cs.setString(3, getIdAnimal());
           cs.setString(4, getFechaAdopcion());

           cs.setInt(5, getCodigo());
           
           
           cs.execute();
           
           JOptionPane.showInternalMessageDialog(null, "modificacion exitosa ");
           
       } catch (SQLException e) {
           JOptionPane.showInternalMessageDialog(null, " no se modifico, error: "+e.toString());
       }
       
   }
   public void EliminarAdopcion(JTextField paramcodigo){
       setCodigo(Integer.parseInt(paramcodigo.getText()));
       
       Cconexion objetoConexion = new Cconexion();
       
       String consulta="delete From adopcion WHere adopcion.id=?;";
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
