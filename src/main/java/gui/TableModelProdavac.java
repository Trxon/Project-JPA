package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Prodavac;

public class TableModelProdavac extends AbstractTableModel {
	

	private static final long serialVersionUID = 1L;	
	private static final String[] KOLONE = { "IME", "PREZIME", "ADRESA" };
	
	
	private List<Prodavac> prodavci;
	
	
	public TableModelProdavac(List<Prodavac> prodavci) {
		super();
		this.prodavci = prodavci;
	}
	

	@Override
	public int getRowCount() {
		return prodavci.size();
	}

	@Override
	public int getColumnCount() {
		return KOLONE.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex) {
			case 0: return prodavci.get(rowIndex).getIme();
			case 1: return prodavci.get(rowIndex).getPrezime();
			case 2: return prodavci.get(rowIndex).getAdresa();
		}
		
		return null;
	}

	
	@Override
	public String getColumnName(int column) {
		return KOLONE[column];
	}
}
