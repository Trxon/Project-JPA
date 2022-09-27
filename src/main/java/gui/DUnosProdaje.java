package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.ProdajaCrud;
import crud.ProdavacCrud;
import crud.ProizvodCrud;
import model.Prodavac;
import model.Proizvod;
import javax.swing.JTextField;

public class DUnosProdaje extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JComboBox<Proizvod> cbProizvod;
	private ProizvodCrud proizvodCrud = new ProizvodCrud();
	
	private JComboBox<Prodavac> cbProdavac;
	private ProdavacCrud prodavacCrud = new ProdavacCrud();
	private JTextField tfDatum;
	private JTextField tfKol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DUnosProdaje dialog = new DUnosProdaje(null, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param modal 
	 * @param frm 
	 */
	public DUnosProdaje(JFrame frm, boolean modal) {
		super(frm, modal);
		setTitle("Unos prodaje");
		setBounds(100, 100, 450, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblProizvod = new JLabel("Proizvod");
			lblProizvod.setBounds(6, 30, 109, 16);
			contentPanel.add(lblProizvod);
		}
		{
			JLabel lblProdavac = new JLabel("Prodavac");
			lblProdavac.setBounds(6, 58, 109, 16);
			contentPanel.add(lblProdavac);
		}
		{
			JLabel lblDatum = new JLabel("Datum");
			lblDatum.setBounds(6, 86, 109, 16);
			contentPanel.add(lblDatum);
		}
		{
			JLabel lblKolicina = new JLabel("Kolicina");
			lblKolicina.setBounds(6, 114, 109, 16);
			contentPanel.add(lblKolicina);
		}
		
		this.cbProizvod = new JComboBox<Proizvod>();
		List<Proizvod> proizvodi = proizvodCrud.listProizvodi();
		for (Proizvod proizvod : proizvodi)
			cbProizvod.addItem(proizvod);
		
		cbProizvod.setBounds(127, 26, 317, 27);
		contentPanel.add(cbProizvod);
		
		this.cbProdavac = new JComboBox<Prodavac>();
		List<Prodavac> prodavci = prodavacCrud.listProdavci();
		for (Prodavac prodavac : prodavci)
			cbProdavac.addItem(prodavac);
		
		cbProdavac.setBounds(127, 54, 317, 27);
		contentPanel.add(cbProdavac);
		{
			tfDatum = new JTextField();
			tfDatum.setBounds(127, 81, 317, 26);
			contentPanel.add(tfDatum);
			tfDatum.setColumns(10);
		}
		{
			tfKol = new JTextField();
			tfKol.setColumns(10);
			tfKol.setBounds(127, 109, 317, 26);
			contentPanel.add(tfKol);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUnos = new JButton("Unesi");
				btnUnos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Prodavac prd = (Prodavac) cbProdavac.getSelectedItem();
						Proizvod prz = (Proizvod) cbProizvod.getSelectedItem();
						
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						
						LocalDate datum = LocalDate.parse(tfDatum.getText().trim(), dateFormat);
						double kol = Double.parseDouble(tfKol.getText().trim());
						
						ProdajaCrud prodajaCrud = new ProdajaCrud();
						boolean uneseno = prodajaCrud.insertProdaja(prd, prz, datum, kol);
						
						if (uneseno)
							JOptionPane.showMessageDialog(
									DUnosProdaje.this, 
									"Uspesno unesena prodaja!");
						else
							JOptionPane.showMessageDialog(
									DUnosProdaje.this, 
									"Prodaja nije unesena...", 
									"Inane error", 
									JOptionPane.ERROR_MESSAGE);
					}
				});
				btnUnos.setActionCommand("OK");
				buttonPane.add(btnUnos);
				getRootPane().setDefaultButton(btnUnos);
			}
			{
				JButton btnIzlaz = new JButton("Izadji");
				btnIzlaz.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnIzlaz.setActionCommand("Cancel");
				buttonPane.add(btnIzlaz);
			}
		}
	}
}
