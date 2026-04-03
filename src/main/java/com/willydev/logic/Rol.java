package com.willydev.logic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreRol;
    private String descripcion;
    //conectamos rol con user. Para que la relacion sea bidireccional
    @OneToMany(mappedBy = "unRol")
    private List<User> listaUsuarios;

    public Rol() {
    }

    public Rol(int id, String nombreRol, String descripcion) {
        this.id = id;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
