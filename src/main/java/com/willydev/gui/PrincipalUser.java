package com.willydev.gui;

import com.willydev.logic.Controladora;
import com.willydev.logic.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrincipalUser extends JFrame{
    private JPanel UserPrincipal1;
    private JPanel Principal2;
    private JLabel txtNombreUser;
    private JButton salirButton;
    private JTable tablaUsers;
    private JButton reloadButton;
    private JButton SalirButton;

    Controladora control;
    User usr;
    public PrincipalUser(Controladora control, User usr) {
        this.control = control;
        this.usr = usr;

        setContentPane(UserPrincipal1);
        setTitle("Sistema Administrador de Usuarios - WillyDev");
        if (usr!=null){
            this.txtNombreUser.setText(usr.getNameUser());
            cargarTabla();

        }

        //logica dell boton salir
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // para confirmar el cierre
                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Deseas salir del sistema", "Confirmar?",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        //logica boton recargar
        reloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTabla();

                System.out.println("Tabla recargada con éxito");
            }
        });

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void cargarTabla() {
        //definir el modelo que queremos que tenga la tabla
        DefaultTableModel modeloTabla = new DefaultTableModel(){

            //que fila y columnas no sean editables
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        //establecemos los nombres de las columnas
        String titulos[] = {"Id","User","Rol"};
        modeloTabla.setColumnIdentifiers(titulos);

        //tarer de la bd la lista de usuarios
        List<User> listaUsuarios =  control.traerUsuarios();

        //preguntamos si la lista esta vacia
        if (listaUsuarios != null){
            //recorremos la lista
            for (User usu : listaUsuarios){
                Object[] objecto = {usu.getId(), usu.getNameUser(), usu.getUnRol().getNombreRol()};

                modeloTabla.addRow(objecto);
            }
        }


        tablaUsers.setModel(modeloTabla);
    }

}
