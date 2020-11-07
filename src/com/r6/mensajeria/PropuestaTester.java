/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nvidi
 */
public class PropuestaTester {

    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager em = null;

    public static void main(String[] args) {
        try{
            startEntityManagerFactory();
            stopEntityManagerFactory();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void startEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence
                        .createEntityManagerFactory("Mensajeria");
                em = entityManagerFactory.createEntityManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopEntityManagerFactory() {
        if (entityManagerFactory != null) {
            if (entityManagerFactory.isOpen()) {
                try {
                    entityManagerFactory.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            em.close();
            entityManagerFactory = null;
        }
    }

}
