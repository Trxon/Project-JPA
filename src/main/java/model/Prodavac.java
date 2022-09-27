package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the Prodavac database table.
 * 
 */
@Entity
@NamedQuery(name="Prodavac.findAll", query="SELECT p FROM Prodavac p")
public class Prodavac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sifp;

	private String adresa;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to Prodaja
	@OneToMany(mappedBy="prodavac", fetch=FetchType.EAGER)
	private List<Prodaja> prodajas;

	public Prodavac() {
		this.prodajas = new ArrayList<Prodaja>();
	}

	public int getSifp() {
		return this.sifp;
	}

	public void setSifp(int sifp) {
		this.sifp = sifp;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Prodaja> getProdajas() {
		return this.prodajas;
	}

	public void setProdajas(List<Prodaja> prodajas) {
		this.prodajas = prodajas;
	}

	public Prodaja addProdaja(Prodaja prodaja) {
		getProdajas().add(prodaja);
		prodaja.setProdavac(this);

		return prodaja;
	}

	public Prodaja removeProdaja(Prodaja prodaja) {
		getProdajas().remove(prodaja);
		prodaja.setProdavac(null);

		return prodaja;
	}

	
	@Override
	public String toString() {
		return String.format("%s %s (%s)", ime, prezime, adresa);
	}
}