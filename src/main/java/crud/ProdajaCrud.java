package crud;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Prodaja;
import model.Prodavac;
import model.Proizvod;
import utils.PersistenceUtil;

public class ProdajaCrud {

	public boolean insertProdaja(Prodavac prodavac, Proizvod proizvod, LocalDate datum, double kol) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		EntityTransaction et = null;
		
		boolean retVal = false;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			
			prodavac = em.merge(prodavac);
			proizvod = em.merge(proizvod);
			
			Prodaja prodaja = new Prodaja();
			prodaja.setProdavac(prodavac);
			prodaja.setProizvod(proizvod);
			prodaja.setDatum(datum.toString());
			prodaja.setKolicina(kol);
			
			prodavac.addProdaja(prodaja);
			proizvod.addProdaja(prodaja);
			
			em.merge(prodavac);
			em.merge(proizvod);
			em.merge(prodaja);
			
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
}
