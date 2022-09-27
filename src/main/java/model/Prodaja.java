package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Prodaja database table.
 * 
 */
@Entity
@NamedQuery(name="Prodaja.findAll", query="SELECT p FROM Prodaja p")
public class Prodaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int oznaka;

	private String datum;

	private double kolicina;

	//bi-directional many-to-one association to Prodavac
	@ManyToOne
	@JoinColumn(name="sifp")
	private Prodavac prodavac;

	//bi-directional many-to-one association to Proizvod
	@ManyToOne
	@JoinColumn(name="idp")
	private Proizvod proizvod;

	public Prodaja() {
	}

	public int getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(int oznaka) {
		this.oznaka = oznaka;
	}

	public String getDatum() {
		return this.datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public double getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public Prodavac getProdavac() {
		return this.prodavac;
	}

	public void setProdavac(Prodavac prodavac) {
		this.prodavac = prodavac;
	}

	public Proizvod getProizvod() {
		return this.proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	
	@Override
	public String toString() {
		return String.format("%s %s | %s | %s %.2f", prodavac.getIme(), prodavac.getPrezime(), proizvod.getNaziv(), datum, kolicina);
	}
}