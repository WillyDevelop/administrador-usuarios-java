package com.willydev.logic;

import com.willydev.persistence.ControladoraPersistencia;

import java.util.List;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public User validarUsuario(String user, String pass) {

        //String mensaje = "";
        User usr = null;
        List<User> listaUsuarios = controlPersis.traerUsuarios();
        for (User usu : listaUsuarios){
            System.out.println("Usario: "+ usu.getNameUser());
            if (usu.getNameUser().equals(user)) {
                if (usu.getPassword().equals(pass)){
                    //mensaje = "Usuario y contraseña correctos. Bienvenido!";
                    usr = usu;
                    return usr;
                }
                else {
                    //mensaje = "Contraseña incorrecta";
                    usr = null;
                    return usr;
                }
            }
            else {
                //mensaje = "Usuario no encontrado";
                //return mensaje;
                usr = null;
                //return usr;
            }
        }
        return usr;
    }

    public String validarRol(String user) {
        return null;
    }

    public List<User> traerUsuarios() {
        return controlPersis.traerUsuarios();

    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }


    public void crearUsuario(String usuario, String contra, String rolRecibido) {
        User usu = new User();
        usu.setNameUser(usuario);
        usu.setPassword(contra);
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        if (rolEncontrado != null) {
            usu.setUnRol(rolEncontrado);
        }

        controlPersis.crearUsuario(usu);
    }

    //metodo traer rol para que la logica se encargue de busca
    private Rol traerRol(String rolRecibido) {
        List<Rol> listaRoles = controlPersis.traerRoles();

        for (Rol rol : listaRoles){
            if (rol.getNombreRol().equals(rolRecibido)){
                return rol;
            }
        }
        return null;
    }

    public void borrarUsuario(int idUsuario) {
        controlPersis.borrarUsuarios(idUsuario);

    }

    public User traerUsuario(int idUsuario) {
        return controlPersis.traerUsuario(idUsuario);
    }

    public void editarUsuario(User usu, String usuario, String contra, String rolRecibido) {
        //seteamos los nuevos valores al objeto que ya trajimos de la bd
        usu.setNameUser(usuario);
        usu.setPassword(contra);

        //buscamos el nuevo rol si es que cambio
        Rol rolEncontrado = this.traerRol(rolRecibido);
        if (rolEncontrado != null) {
            usu.setUnRol(rolEncontrado);
        }

        //le pedimos a la persistencia que haga el merge
        controlPersis.editarUsuario(usu);
    }
}
