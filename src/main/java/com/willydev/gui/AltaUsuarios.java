package com.willydev.gui;

import com.willydev.logic.Rol;
import com.willydev.logic.Controladora;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AltaUsuarios extends JFrame{
    private JPanel PanelAlta;
    private JLabel AltaUsuarios;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JComboBox cmbRol;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton volverButton;

    Controladora control;

    public AltaUsuarios(Controladora control) {
        this.control = control;

        setContentPane(PanelAlta);
        setTitle("Alta de Usuarios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //para cargar los roles apenas se abra la ventana
        cargarRoles();

        //logica del boton guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contra = txtPassword.getText();
                String rol = (String) cmbRol.getSelectedItem();

                control.crearUsuario(usuario, contra, rol);

                mostrarMensaje("Usuario craedo correctamente","Info","Creacion exitosa");
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
