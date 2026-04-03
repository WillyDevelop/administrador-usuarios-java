package com.willydev.logic;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private String nameUser;
    private String password;

    //conectamos user con rol. para que la relacion sea bidireccional
    @ManyToOne
    @JoinColumn(name="fk_rol")
    private Rol unRol;

    public User() {
    }

   public User(int id, String nameUser, String password, Rol unRol) {
        this.id = id;
        this.nameUser = nameUser;
        this.password = password;
        this.unRol = unRol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getUnRol() {
        return unRol;
    }

    public void setUnRol(Rol unRol) {
        this.unRol = unRol;
    }
}
