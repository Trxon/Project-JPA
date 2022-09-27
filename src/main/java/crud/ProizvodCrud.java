package crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Prodavac;
import model.Proizvod;
import utils.PersistenceUtil;

public class ProizvodCrud {

	public void insertProizvod(Proizvod p) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			
			em.persist(p);
			
			em.flush();
			et.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			if (et != null)
				et.rollback();
		} finally {
			if (em != null)
				em.close();
		}
	}
	
	
	public Proizvod nadjiProizvod(int idp) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		
		Proizvod p = em.find(Proizvod.class, idp);
		em.close();
		
		return p;
	}
	
	
	public List<Proizvod> listProizvodi() {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		
		String upit = "select p from Proizvod p";
		List<Proizvod> proizvodi = em.createQuery(upit).getResultList();
		
		em.close();
		
		return proizvodi;
	}
}
