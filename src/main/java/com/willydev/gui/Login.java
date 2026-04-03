package com.willydev.gui;

import com.willydev.logic.Controladora;
import com.willydev.logic.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JTextField txtUsuario;
    private JTextField txtPassword;
    private JTextArea textMensaje;
    private JButton CLEANButton;
    private JButton LOGINButton;
    private JPanel Botones;
    private JPanel panelPrincipal;
    private JPanel Gui;

    Controladora control;

    public Login() {
        //Le decimos a la ventana que use el panel del diseñador
        setContentPane(panelPrincipal);

        //Configuracion basica de la ventana
        setTitle("Login WillyDev");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tamaño y posición
        setSize(500, 600);
        setLocationRelativeTo(null);

        control = new Controladora();

        //logica boton login
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 1. Conectamos con el persistence.xml
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("WillyDevPU");
                EntityManager em = emf.createEntityManager();

                String user = txtUsuario.getText();
                String pass = txtPassword.getText();
                User usr = control.validarUsuario(user,pass);

                if (usr!=null) {
                      String rol = usr.getUnRol().getNombreRol();
                      if (rol.equals("admin")){
                          PrincipalAdmin pAdmin = new PrincipalAdmin(control,usr);
                          pAdmin.setVisible(true);
                          pAdmin.setLocationRelativeTo(null);
                          Login.this.dispose();
                      }
                      if (rol.equals("user")){
                          PrincipalUser pUser = new PrincipalUser(control,usr);
                          pUser.setVisible(true);
                          pUser.setLocationRelativeTo(null);
                          Login.this.dispose();
                      }
                }
                else {
                    textMensaje.setText("Usuario o contraseña incorrecta");
                }

            }

        });

        CLEANButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUsuario.setText("");
                txtPassword.setText("");
                textMensaje.setText("");
            }
        });
    }
}