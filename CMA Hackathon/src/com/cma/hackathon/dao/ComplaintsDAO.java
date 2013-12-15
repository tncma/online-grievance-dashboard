package com.cma.hackathon.dao;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.cma.hackathon.entity.Complaints;
import com.cma.hackathon.entity.EntityManagerUtil;


public class ComplaintsDAO {

	public static final Logger LOGGER = Logger.getLogger(ComplaintsDAO.class.getName());

	public static void insertComplaintsData(ArrayList<Complaints> assObj)
	throws Exception {
		EntityManager entityMgr = null;
		try {
			LOGGER.info("assObj.size()" + assObj.size());
			entityMgr = EntityManagerUtil.getEntityManager();
			entityMgr.getTransaction().begin();
			for (int i = 0; i < assObj.size(); i++) {
				Complaints asso = assObj.get(i);
				entityMgr.persist(asso);
			}
			LOGGER.log(Level.INFO, "Complaints data inserted into DB");
			entityMgr.getTransaction().commit();

		} catch (Exception e) {
			EntityManagerUtil.rollbackTransaction(entityMgr);
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityMgr);
		}
	}
	public static void main(String[] args) {
		ArrayList<Complaints> arlist=new ArrayList<Complaints>();
		Complaints cmpl=new Complaints();
		cmpl.setAddress("Taramani");
		cmpl.setName("Yogamani");
		cmpl.setArea("Tara");
		cmpl.setDetails("hello");
		cmpl.setEmail("yogamasniin");
		cmpl.setFilename("gg.jpb");
		cmpl.setLandmark("dghdh");
		cmpl.setLocality("dfhdhd");
		cmpl.setMobile("46754856856");
		cmpl.setTitle("health");
		arlist.add(cmpl);
		try {
			insertComplaintsData(arlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
