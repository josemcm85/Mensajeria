/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nvidi
 */
public class Servicio implements Serializable {

    private static Map<String, String> persistenceMap = new HashMap<String, String>();
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager em = null;
    private static String serverURL;
    private static String username;
    private static String password;
    private static String driver;
    private static String hbm2DDLprotocol;
    private static String dialect;
    private static String ubicacionMimes;
    public static void startEntityManagerFactory() {

        if (entityManagerFactory == null) {
            
            persistenceMap.put("javax.persistence.jdbc.url", serverURL);
            persistenceMap.put("javax.persistence.jdbc.user", username);
            persistenceMap.put("javax.persistence.jdbc.password", password);
            persistenceMap.put("javax.persistence.jdbc.driver", driver);
            persistenceMap.put("hibernate.hbm2ddl.auto", hbm2DDLprotocol);
            persistenceMap.put("hibernate.dialect", dialect);
            persistenceMap.put("hibernate.use_sql_comments", "true");
            persistenceMap.put("hibernate.show_sql", "false");
            persistenceMap.put("hibernate.format_sql","true");
            
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory("Mensajeria", persistenceMap);
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

    public static EntityManagerFactory getEmFact() {
        return entityManagerFactory;
    }

    public static void setEmFact(EntityManagerFactory emFact) {
        Servicio.entityManagerFactory = emFact;
    }

    public static EntityManager getEm() {
        return em;
    }

    public static void setEm(EntityManager em) {
        Servicio.em = em;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        Servicio.entityManagerFactory = entityManagerFactory;
    }

    public static String getServerURL() {
        return serverURL;
    }

    public static void setServerURL(String serverURL) {
        Servicio.serverURL = serverURL;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Servicio.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Servicio.password = password;
    }

    public static String getDriver() {
        return driver;
    }

    public static void setDriver(String driver) {
        Servicio.driver = driver;
    }

    public static String getHbm2DDLprotocol() {
        return hbm2DDLprotocol;
    }

    public static void setHbm2DDLprotocol(String hbm2DDLprotocol) {
        Servicio.hbm2DDLprotocol = hbm2DDLprotocol;
    }

    public static String getDialect() {
        return dialect;
    }

    public static void setDialect(String dialect) {
        Servicio.dialect = dialect;
    }

	public static String getUbicacionMimes() {
		return ubicacionMimes;
	}

	public static void setUbicacionMimes(String ubicacionMimes) {
		Servicio.ubicacionMimes = ubicacionMimes;
	}

}
