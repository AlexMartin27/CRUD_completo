package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.PersonaDAO;
import vista.Vista;

public class Controlador implements ActionListener {

    PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Vista v) {
        this.vista = v;
        this.vista.td_guardar.addActionListener(this);
        this.vista.td_editar.addActionListener(this);
        this.vista.td_eliminar.addActionListener(this);
        this.vista.td_ok.addActionListener(this);
        listar(vista.tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.td_guardar) {
            String dui = vista.txtdui.getText();
            String nombre = vista.txtnombre.getText();
            if (dui.equals("") || nombre.equals("")) {
                JOptionPane.showMessageDialog(vista, "No ha completado todos los datos");
            } else {
                agregar();
                limpiarTabla();
                listar(vista.tabla);
            }

        } // fin del boton td_guardar
        if (e.getSource() == vista.td_editar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            } else {
                int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                String dui = (String) vista.tabla.getValueAt(fila, 1);
                String nombre = (String) vista.tabla.getValueAt(fila, 2);

                vista.txtid.setText("" + id);
                vista.txtdui.setText("" + dui);
                vista.txtnombre.setText(nombre);
            }
            limpiarTabla();
            listar(vista.tabla);
        }// fin del boton td_editar
        if (e.getSource() == vista.td_ok) {
            String dui = vista.txtdui.getText();
            String nombre = vista.txtnombre.getText();
           if (dui.equals("") || nombre.equals("")) {
                JOptionPane.showMessageDialog(vista, "No ha completado todos los datos");
            }else{ Actualizar();
            limpiarTabla();
            listar(vista.tabla);
           }
        }// fin de ok este sirve para actualizar(guarda los datos que editamos)

        if (e.getSource() == vista.td_eliminar) {
            int fila = vista.tabla.getSelectedRow();
            
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un Usuario");
            } else {
                int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(vista, "Usuario eliminado");
            }
            limpiarTabla();
            listar(vista.tabla);
        }// fin del boton eliminar;
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<Persona> lista = dao.listar();
        Object[] object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getDui();
            object[2] = lista.get(i).getNombre();
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);

    }

    public void agregar() {
        String dui = vista.txtdui.getText();
        String nombre = vista.txtnombre.getText();

        p.setDui(dui);
        p.setNombre(nombre);
        int r = dao.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario agregado con exito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error..");
        }
        vista.txtid.setText("");
        vista.txtdui.setText("");
        vista.txtnombre.setText("");

    }

    public void Actualizar() {
        int id = Integer.parseInt(vista.txtid.getText());
        String dui = vista.txtdui.getText();
        String nombre = vista.txtnombre.getText();

        p.setId(id);
        p.setDui(dui);
        p.setNombre(nombre);
        int r = dao.Actualizar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario actualizado con exito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error..");

        }
        vista.txtid.setText("");
        vista.txtdui.setText("");
        vista.txtnombre.setText("");
    }
}
