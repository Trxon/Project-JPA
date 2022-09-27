package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.ProdavacCrud;
import model.Prodavac;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DUnosProdavca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfIme;
	private JTextField tfPrz;
	private JTextField tfAdr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DUnosProdavca dialog = new DUnosProdavca(null, true);
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
	public DUnosProdavca(JFrame frm, boolean modal) {
		super(frm, modal);
		setTitle("Unos prodavca");
		setBounds(100, 100, 250, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setBounds(6, 11, 61, 16);
		contentPanel.add(lblIme);
		
		JLabel lblPrz = new JLabel("Prezime");
		lblPrz.setBounds(6, 40, 61, 16);
		contentPanel.add(lblPrz);
		
		JLabel lblAdr = new JLabel("Adresa");
		lblAdr.setBounds(6, 66, 61, 16);
		contentPanel.add(lblAdr);
		
		tfIme = new JTextField();
		tfIme.setBounds(79, 6, 165, 26);
		contentPanel.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrz = new JTextField();
		tfPrz.setColumns(10);
		tfPrz.setBounds(79, 35, 165, 26);
		contentPanel.add(tfPrz);
		
		tfAdr = new JTextField();
		tfAdr.setColumns(10);
		tfAdr.setBounds(79, 61, 165, 26);
		contentPanel.add(tfAdr);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUnos = new JButton("Unesi");
				btnUnos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String ime = tfIme.getText();
						String prezime = tfPrz.getText();
						String adresa = tfAdr.getText();
						
						Prodavac p = new Prodavac();
						p.setIme(ime);
						p.setPrezime(prezime);
						p.setAdresa(adresa);
						
						ProdavacCrud prodCrud = new ProdavacCrud();
						boolean retVal = prodCrud.insertProdavac(p);
						
						if (retVal)
							JOptionPane.showMessageDialog(
									DUnosProdavca.this, 
									"Uspesno unesen prodavac!");
						else
							JOptionPane.showMessageDialog(
									DUnosProdavca.this, 
									"Prodavac nije unesen...", 
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
