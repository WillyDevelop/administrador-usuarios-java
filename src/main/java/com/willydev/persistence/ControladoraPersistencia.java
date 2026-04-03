package com.willydev.persistence;

import com.willydev.logic.Rol;
import com.willydev.logic.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ControladoraPersistencia {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("WillyDevPU");

    public List<User> traerUsuarios() {
        //usamos entitymanager en vez de JPA
        EntityManager em = emf.createEntityManager();
        List<User> lista = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        return lista;
        //SELECT * FROM usuarios
    }

    public List<Rol> traerRoles() {
        //usamos entitymanager en vez de JPA
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT r FROM Rol r", Rol.class).getResultList();
    }


    public void crearUsuario(User usu) {
        //usamos Entitymanager en vez de JPA
        EntityManager em = emf.createEntityManager();

        try {
            //iniciamos la transaccion
            em.getTransaction().begin();

            //usamos persist para el INSERT de SQL
            em.persist(usu);

            //confirmamos
            em.getTransaction().commit();

        } catch (Exception e) {
            //si falla algo cancelamos
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void borrarUsuarios(int idUsuario) {
        EntityManager em = emf.createEntityManager();
        try {
            //iniciamos la transaccion
            em.getTransaction().begin();

            //buscamos al usuario por su id
            User usu = em.find(User.class, idUsuario);

            //usamos el if para ver si el usuario existe
            if (usu != null){
                em.remove(usu);

                //confirmamos
                em.getTransaction().commit();
            }

        }catch (Exception e){
            //si se rompe volvemos atras
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public User traerUsuario(int idUsuario) {
        EntityManager em = emf.createEntityManager();
        User usu = em.find(User.class, idUsuario);
        em.close();
        return usu;
    }

    public void editarUsuario(User usu) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //usamos merge para reemplazar los datos de la bd
            em.merge(usu);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
