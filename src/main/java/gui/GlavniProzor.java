package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GlavniProzor {

	private JFrame frmBpklk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor window = new GlavniProzor();
					window.frmBpklk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GlavniProzor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBpklk = new JFrame();
		frmBpklk.setTitle("BP2_Klk2");
		frmBpklk.setBounds(100, 100, 250, 185);
		frmBpklk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBpklk.getContentPane().setLayout(null);
		
		JButton btnUnosProdavca = new JButton("Unos prodavca");
		btnUnosProdavca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DUnosProdavca dUnosProdavca = new DUnosProdavca(frmBpklk, true);
				dUnosProdavca.setVisible(true);
			}
		});
		btnUnosProdavca.setBounds(49, 6, 150, 29);
		frmBpklk.getContentPane().add(btnUnosProdavca);
		
		JButton btnBrisanjeProdavca = new JButton("Brisanje prodavca");
		btnBrisanjeProdavca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DBrisanjeProdavca dBrisanjeProdavca = new DBrisanjeProdavca(frmBpklk, true);
				dBrisanjeProdavca.setVisible(true);
			}
		});
		btnBrisanjeProdavca.setBounds(49, 47, 150, 29);
		frmBpklk.getContentPane().add(btnBrisanjeProdavca);
		
		JButton btnPrikazProdavaca = new JButton("Prikaz prodavaca");
		btnPrikazProdavaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DPrikazProdaje dPrikazProdaje = new DPrikazProdaje();
				dPrikazProdaje.setVisible(true);
			}
		});
		btnPrikazProdavaca.setBounds(49, 129, 150, 29);
		frmBpklk.getContentPane().add(btnPrikazProdavaca);
		
		JButton btnProdaja = new JButton("Unos prodaje");
		btnProdaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DUnosProdaje dUnosProdaje = new DUnosProdaje(frmBpklk, true);
				dUnosProdaje.setVisible(true);
			}
		});
		btnProdaja.setBounds(49, 88, 150, 29);
		frmBpklk.getContentPane().add(btnProdaja);
	}
}
