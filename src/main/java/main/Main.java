package main;

import java.time.LocalDate;

import crud.ProdajaCrud;
import crud.ProdavacCrud;
import crud.ProizvodCrud;
import model.Prodavac;
import model.Proizvod;

public class Main {

	public static void main(String[] args) {
		
//		Prodavac p = new Prodavac();
//		p.setIme("Prodavko");
//		p.setPrezime("Prodavkovic");
//		p.setAdresa("Trgovacka 1, Novi Sad");
//		
//		ProdavacCrud pc = new ProdavacCrud();
//		pc.insertProdavac(p);
//		
////		System.out.println(pc.listProdavci());
//		
//		Proizvod proizvod = new Proizvod();
//		proizvod.setNaziv("sladoled");
//		
//		ProizvodCrud proizCrud = new ProizvodCrud();
//		proizCrud.insertProizvod(proizvod);
//		
//		ProdajaCrud prodCrud = new ProdajaCrud();
//		prodCrud.insertProdaja(p, proizvod, LocalDate.now(), 10);
		
		
//		Prodavac p6 = pc.nadjiProdavca(7);
//		System.out.println(p6);
//		
//		pc.obrisiProdavca(p6);
//		
//		System.out.println(proizvod);
//		System.out.println(pc.listProdavciZaProizvod(proizvod));

		
		ProdavacCrud pc = new ProdavacCrud();
		ProizvodCrud prc = new ProizvodCrud();
		ProdajaCrud prodCrud = new ProdajaCrud();
		
		Proizvod pr = prc.nadjiProizvod(8);
		System.out.println(pr);
		
//		prodCrud.insertProdaja(pc.nadjiProdavca(1), pr, LocalDate.now(), 500);
		
		System.out.println(pc.listProdavciZaProizvod(pr));
	}
}
