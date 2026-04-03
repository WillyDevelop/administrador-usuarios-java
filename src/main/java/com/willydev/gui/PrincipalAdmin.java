package com.willydev.gui;

import com.willydev.logic.Controladora;
import com.willydev.logic.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrincipalAdmin extends JFrame {
    private JPanel AdmPrincipal1;
    private JPanel Principal2;
    private JButton reloadButton;
    private JButton crearNuevoUsuarioButton;
    private JLabel txtNombreAdm;
    private JButton salirButton;
    private JTable tablaUsers;
    private JButton borrarButton;
    private JButton editarButton;

    Controladora control;
    User usr;

    public PrincipalAdmin(Controladora control, User usr) {
        this.control = control;
        this.usr = usr;

        setContentPane(AdmPrincipal1);
        setTitle("Sistema Administrador de Usuarios - WillyDev");

        if (usr != null) {
            this.txtNombreAdm.setText(usr.getNameUser());
            cargarTabla();
        }

        //logica del boton salir
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // confirmamos si queremos salir
                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Deseas salir del sistema, Willy?", "Confirmar?",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        //logica boton recargar
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTabla();

                System.out.println("Tabla recargada con éxito");
            }
        });

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        //logica boton crear usuario nuevo
        crearNuevoUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AltaUsuarios altaUsu = new AltaUsuarios(control);
                altaUsu.setVisible(true);
                altaUsu.setLocationRelativeTo(null);
                //PrincipalAdmin.this.dispose();
            }
        });

        //logica boton borrar
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //validamos que la tabla tenga elementos
                if (tablaUsers.getRowCount() > 0){

                    //controlar que se haya seleccionado un elemento
                    if (tablaUsers.getSelectedRow() != -1){

                        //obtengo la id del elemento a eliminar
                        int id_usuario = Integer.parseInt(String.valueOf(tablaUsers.getValueAt(tablaUsers.getSelectedRow(), 0)));

                        //Pregunto si quiere borrar
                        int respuesta = JOptionPane.showConfirmDialog(null,"¿Seguro que quiere borrar al usuario con el id: "+ id_usuario +"?\n","Confirmar Borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                        if (respuesta == JOptionPane.YES_OPTION) {
                            //llamo al metodo borrar
                            control.borrarUsuario(id_usuario);

                            //avisamos al user que se borro correctamente
                            mostrarMensaje("Se borró el usuario corectamente", "Info", "Eliminacion correcta");

                            cargarTabla();
                        }
                        else {
                            mostrarMensaje("Operacion cancelada", "Info", "Cancelado");
                        }
                    }
                    else {
                        mostrarMensaje("No selecciono ningun registro","Error","Error al borrar");
                    }
                }
                else {
                    mostrarMensaje("La tabla esta vacia","Error","Error al borrar");
                }

            }


        });


        //logica boton editar
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //validamos que la tabla tenga elementos
                if (tablaUsers.getRowCount() > 0){

                    //controlar que se haya seleccionado un elemento
                    if (tablaUsers.getSelectedRow() != -1) {

                        //obtengo la id del elemento a eliminar
                        int id_usuario = Integer.parseInt(String.valueOf(tablaUsers.getValueAt(tablaUsers.getSelectedRow(), 0)));

                        //llamo a la ventana edicion
                        EditarUsuarios pantallaEdic = new EditarUsuarios(control,id_usuario);
                        pantallaEdic.setVisible(true);
                        pantallaEdic.setLocationRelativeTo(null);


                    }else {
                        mostrarMensaje("No selecciono ningun registro","Error","Error al borrar");
                    }

                }else {
                    mostrarMensaje("La tabla esta vacia","Error","Error al borrar");
                }


            }
        });
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

