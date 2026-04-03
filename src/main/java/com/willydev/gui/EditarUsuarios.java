package com.willydev.gui;

import com.willydev.logic.Controladora;
import com.willydev.logic.Rol;
import com.willydev.logic.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditarUsuarios extends JFrame{
    private JPanel PanelEditar;
    private JLabel EditarUsuarios;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JComboBox cmbRol;
    private JButton guardarButton;
    private JButton limpiarButton;

    Controladora control;
    int id_usuario;
    public EditarUsuarios(Controladora control, int id_usuario) {
        this.control = control;
        this.id_usuario = id_usuario;

        setContentPane(PanelEditar);
        setTitle("Editar Usuarios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //buscar el usuario
        User usu = control.traerUsuario(id_usuario);

        //para cargar los roles apenas se abra la ventana
        cargarRoles();

        //para llenar los campos con los datos actuales
        if (usu != null) {
            txtUsuario.setText(usu.getNameUser());
            txtPassword.setText(usu.getPassword());
            cmbRol.setSelectedItem(usu.getUnRol().getNombreRol());
        }

        //logica del boton guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUsuario.getText();
                String contra = new String(txtPassword.getPassword());
                String rol = (String) cmbRol.getSelectedItem();

                // llamamos a editar usuario
                control.editarUsuario(usu, user, contra, rol);

                mostrarMensaje("Usuario editado correctamente", "Info", "Edición exitosa");
                dispose();
            }
        });

        //logica del boton limpiar
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUsuario.setText("");
                txtPassword.setText("");
            }
        });

    }

    private void cargarRoles(){
        //le pedimos la lista a la logica
        List<Rol> listaRoles = control.traerRoles();

        cmbRol.removeAllItems();

        if (listaRoles != null){
            for (Rol rol : listaRoles){
                cmbRol.addItem(rol.getNombreRol());
            }
        }
    }

    //funcion para mostrar ventanita de mensaje
    public void mostrarMensaje (String mensaje, String tipo, String titulo){
        JOptionPane optionPane = new JOptionPane(mensaje);
        if (tipo.equals("Info")){
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        } else if (tipo.equals("Error")) {
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        }
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }


}
