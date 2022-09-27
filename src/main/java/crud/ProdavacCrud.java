package crud;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Prodaja;
import model.Prodavac;
import model.Proizvod;
import utils.PersistenceUtil;

public class ProdavacCrud {
	

	public boolean insertProdavac(Prodavac p) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		
		boolean retVal = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			
			em.persist(p);
			
			em.flush();
			et.commit();
			
			retVal = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			if (et != null)
				et.rollback();
		} finally {
			if (em != null)
				em.close();
		}
		
		return retVal;
	}
	
	
	public boolean obrisiProdavca(Prodavac p) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		
		boolean retVal = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			
			List<Prodaja> prodaje = listProdajeZaProdavca(p);
			
			for (Prodaja prodaja : prodaje) {
				
				prodaja = em.merge(prodaja);
				p.removeProdaja(prodaja);
				em.remove(prodaja);
			}
			
			p = em.merge(p);
			em.remove(p);
			
			em.flush();
			et.commit();
			
			retVal = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			if (et != null)
				et.rollback();
		} finally {
			if (em != null)
				em.close();
		}
		
		return retVal;
	}
	
	
	public List<Prodavac> listProdavci() {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		
		String upit = "select p from Prodavac p";
		List<Prodavac> prodavci = em.createQuery(upit).getResultList();
		
		em.close();
		
		return prodavci;
	}
	
	
	public Prodavac nadjiProdavca(int sifp) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		
		Prodavac p = em.find(Prodavac.class, sifp);
		em.close();
		
		return p;
	}
	
	
	public List<Prodaja> listProdajeZaProdavca(Prodavac p) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		
		Query q = em.createQuery("select p from Prodaja p where p.prodavac=:pX");
		q.setParameter("pX", p);
		List<Prodaja> prodaje = q.getResultList();
		
		em.close();
		
		return prodaje;
	}
	
	
	public List<Prodavac> listProdavciZaProizvod(Proizvod proizvod) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		
		System.out.println(proizvod);
		
		proizvod = em.merge(proizvod);
		List<Prodaja> prodaje = proizvod.getProdajas();
		
		System.out.println(prodaje);
		
		List<Prodavac> prodavci = prodaje.stream()
				.map(Prodaja::getProdavac)
				.distinct()
				.collect(Collectors.toList());
		
		em.close();
		
		return prodavci;
	}
}
