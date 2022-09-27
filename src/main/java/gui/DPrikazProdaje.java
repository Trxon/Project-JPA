package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import crud.ProdavacCrud;
import crud.ProizvodCrud;
import model.Prodavac;
import model.Proizvod;

public class DPrikazProdaje extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	private JComboBox<Proizvod> cbProizvodi;
	private ProizvodCrud proizvodCrud = new ProizvodCrud();
	
	private ProdavacCrud prodavacCrud = new ProdavacCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DPrikazProdaje dialog = new DPrikazProdaje();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DPrikazProdaje() {
		setTitle("Prikaz prodavaca");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblProizvod = new JLabel("Proizvod");
				panel.add(lblProizvod);
			}
			{
				this.cbProizvodi = new JComboBox<Proizvod>();
				List<Proizvod> proizvodi = proizvodCrud.listProizvodi();
				for (Proizvod proizvod : proizvodi)
					cbProizvodi.addItem(proizvod);
				
				panel.add(cbProizvodi);
			}
			{
				JButton btnPrikaz = new JButton("Prikazi");
				btnPrikaz.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Proizvod proizvod = (Proizvod) cbProizvodi.getSelectedItem();
						List<Prodavac> prodavci = prodavacCrud.listProdavciZaProizvod(proizvod);
						
						TableModelProdavac tmp = new TableModelProdavac(prodavci);
						table.setModel(tmp);
					}
				});
				panel.add(btnPrikaz);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
