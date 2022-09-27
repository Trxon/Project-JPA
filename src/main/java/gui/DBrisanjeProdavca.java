package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.ProdavacCrud;
import model.Prodavac;
import javax.swing.JLabel;

public class DBrisanjeProdavca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JComboBox<Prodavac> cbProdavci;
	private ProdavacCrud pc = new ProdavacCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DBrisanjeProdavca dialog = new DBrisanjeProdavca(null, true);
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
	public DBrisanjeProdavca(JFrame frm, boolean modal) {
		super(frm, modal);
		setTitle("Brisanje prodavca");
		setBounds(100, 100, 450, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			this.cbProdavci = new JComboBox<Prodavac>();
			List<Prodavac> prodavci = pc.listProdavci();
			for(Prodavac prodavac : prodavci)
				cbProdavci.addItem(prodavac);
			
			cbProdavci.setBounds(155, 23, 289, 27);
			contentPanel.add(cbProdavci);
		}
		{
			JLabel lblProdavac = new JLabel("Prodavac za brisanje");
			lblProdavac.setBounds(6, 27, 137, 16);
			contentPanel.add(lblProdavac);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnBrisanje = new JButton("Obrisi");
				btnBrisanje.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Prodavac p = (Prodavac) cbProdavci.getSelectedItem();
						boolean obrisan = pc.obrisiProdavca(p);
						
						if (obrisan)
							JOptionPane.showMessageDialog(
									DBrisanjeProdavca.this, 
									"Uspesno obrisan prodavac!");
						else
							JOptionPane.showMessageDialog(
									DBrisanjeProdavca.this, 
									"Prodavac nije obrisan...", 
									"Inane error", 
									JOptionPane.ERROR_MESSAGE);
						
					}
				});
				btnBrisanje.setActionCommand("OK");
				buttonPane.add(btnBrisanje);
				getRootPane().setDefaultButton(btnBrisanje);
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
