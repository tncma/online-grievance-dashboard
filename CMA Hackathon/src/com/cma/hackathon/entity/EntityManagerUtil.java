/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cma.hackathon.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Yogamani Balusamy
 */
public class EntityManagerUtil {
	private static final Logger LOGGER = Logger
			.getLogger(EntityManagerUtil.class.getName());
	private static EntityManagerFactory sEMF;

	static {
		sEMF = Persistence.createEntityManagerFactory("hackathon");
	}

	public static void closeEntityManagerFactory() {
		if (sEMF != null) {
			sEMF.close();
		}
	}

	public static synchronized EntityManager getEntityManager() {
		EntityManager toReturn = sEMF.createEntityManager();
		return toReturn;
	}

	public static void closeEntityManager(EntityManager entityManager) {
		if (entityManager == null) {
			return;
		}
		entityManager.close();
		entityManager = null;
	}

	public static void rollbackTransaction(EntityManager entityManager) {
		if (entityManager == null) {
			return;
		}
		try {
			entityManager.getTransaction().rollback();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
