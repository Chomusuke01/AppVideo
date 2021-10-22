package umu.tds.AppVideo.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
public class AppView {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppView frame = new AppView();
					frame.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private JFrame frmApp;
	private JPanel panelPrincipal;
	
	
	public AppView() {
		initialize();
	}
	
	public void initialize() {
		
		frmApp = new JFrame ();
		
		frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApp.setBounds(100, 100, 1100, 720);
		frmApp.setMaximumSize(new Dimension(1100,720));
		frmApp.setMinimumSize(new Dimension(1100,720));
		frmApp.setPreferredSize(new Dimension(1100,720));
		frmApp.setBackground(Color.GRAY);
		
		frmApp.getContentPane().add(addPanelNorte(),BorderLayout.NORTH);
		panelPrincipal = addPanelPrincipal();
		frmApp.getContentPane().add(panelPrincipal,BorderLayout.CENTER);
	}
	
	private JPanel addPanelPrincipal() {
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new CardLayout(0, 0));
		
		return panelPrincipal;
	}
	
	
	private JPanel addPanelNorte() {
		
		JPanel panel_Norte = new JPanel();
		panel_Norte.setBackground(Color.LIGHT_GRAY);
		panel_Norte.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelBtn.setBackground(Color.LIGHT_GRAY);
		panel_Norte.add(panelBtn, BorderLayout.SOUTH);
		panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnExplorar = new JButton("Explorar");
		panelBtn.add(btnExplorar);
		
		JButton btnMisListas = new JButton("Mis Listas\r\n");
		panelBtn.add(btnMisListas);
		
		JButton btnRecientes = new JButton("Recientes");
		panelBtn.add(btnRecientes);
		
		JButton btnNuevaLista = new JButton("Nueva Lista");
		panelBtn.add(btnNuevaLista);
		
		JPanel panelNorteSuperior = new JPanel();
		panelNorteSuperior.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelNorteSuperior.setBackground(Color.LIGHT_GRAY);
		panel_Norte.add(panelNorteSuperior, BorderLayout.NORTH);
		panelNorteSuperior.setLayout(new BorderLayout(0, 0));
		
		JPanel Icono = new JPanel();
		Icono.setBackground(Color.LIGHT_GRAY);
		panelNorteSuperior.add(Icono, BorderLayout.WEST);
		Icono.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/iconoVideoPequ.png")));
		Icono.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panelbtn2 = new JPanel();
		panelbtn2.setBackground(Color.LIGHT_GRAY);
		panelNorteSuperior.add(panelbtn2, BorderLayout.EAST);
		
		JButton btnLogOut = new JButton("Logout");
		panelbtn2.add(btnLogOut);
		
		JButton btnPremium = new JButton("PREMIUM");
		btnPremium.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelbtn2.add(btnPremium);
		
		return panel_Norte;
	}
	
	public void mostrarVentana() {
		frmApp.setLocationRelativeTo(null);
		frmApp.setVisible(true);
	}
}
