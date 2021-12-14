package umu.tds.AppVideo.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import umu.tds.AppVideo.controlador.ControladorAppVideo;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AppView {


	private JFrame frmApp;
	private JPanel panelPrincipal;
	private JButton btnExplorar;
	private JButton btnMisListas;
	private JButton btnRecientes;
	private JButton btnNuevaLista;
	private JButton btnLogOut;
	private JButton btnPremium;
	private JPanel panelExplorar;
	private JPanel panelNuevaLista;
	private JPanel panelMisListas;
	private JPanel panelRecientes;
	private JPanel panelApp;
	private JPanel panelMenu;
	private PanelMasVistos panelTop10;
	private JMenuBar menuBar;
	private JMenu mnPremium;
	private JMenu mnFiltro;
	private JMenuItem mntmPDF;
	private JMenuItem mntmMasVistos;

	public AppView() {
		initialize();
	}
	
	public void initialize() {
		
		frmApp = new JFrame ();
		
		frmApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApp.setBounds(100, 100, 1100, 720);
		frmApp.setMaximumSize(new Dimension(1100,750));
		frmApp.setMinimumSize(new Dimension(1100,750));
		frmApp.setPreferredSize(new Dimension(1100,750));
		frmApp.setBackground(Color.GRAY);
		frmApp.setResizable(false);
		
		
		panelPrincipal = addPanelPrincipal();
		panelExplorar = new PanelExplorar();
		panelPrincipal.add(panelExplorar,"panelExplorar");
		panelNuevaLista = new PanelNuevaLista();
		panelPrincipal.add(panelNuevaLista,"panelNuevaLista");
		panelMisListas = new PanelMisListas();
		panelPrincipal.add(panelMisListas,"panelMisListas");
		panelRecientes = new PanelRecientes();
		panelPrincipal.add(panelRecientes,"panelRecientes");
		panelTop10 = new PanelMasVistos();
		panelPrincipal.add(panelTop10,"top10");
		
		panelApp = new JPanel();
		panelMenu = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelMenu.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelApp.setLayout(new BorderLayout(0, 0));
		
		panelApp.add(panelPrincipal,BorderLayout.CENTER);
		panelApp.add(addPanelNorte(),BorderLayout.NORTH);
		
		frmApp.getContentPane().add(panelApp,BorderLayout.CENTER);
		frmApp.getContentPane().add(panelMenu,BorderLayout.NORTH);
		
		menuBar = crearMenu();
		panelMenu.add(menuBar);
		
	}

	private JMenuBar crearMenu() {
		JMenuBar menu = new JMenuBar();
		mnPremium = new JMenu("Premium");
		menu.add(mnPremium);
		mnFiltro = new JMenu("Filtro");
		mnPremium.add(mnFiltro);
		mnPremium.add(new JSeparator());
		mntmPDF = new JMenuItem("Generar PDF");
		mnPremium.add(mntmPDF);
		mnPremium.add(new JSeparator());
		mntmMasVistos = new JMenuItem("TOP 10");
		crearManejadorTop10();
		mnPremium.add(mntmMasVistos);
		return menu;
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
		
		btnExplorar = new JButton("Explorar");
		panelBtn.add(btnExplorar);
		crearManejadorBotonExplorar(btnExplorar);
		btnMisListas = new JButton("Mis Listas\r\n");
		panelBtn.add(btnMisListas);
		crearManajadorBotonMisListas(btnMisListas);
		
		btnRecientes = new JButton("Recientes");
		panelBtn.add(btnRecientes);
		crearManejadorBotonRecientes(btnRecientes);
		
		btnNuevaLista = new JButton("Nueva Lista");
		panelBtn.add(btnNuevaLista);
		crearManejadorBotonNuevaLista(btnNuevaLista);
		
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
		
		btnLogOut = new JButton("Logout");
		panelbtn2.add(btnLogOut);
		crearManejadorBotonLogout(btnLogOut);
		
		btnPremium = new JButton("PREMIUM");
		btnPremium.setFont(new Font("Tahoma", Font.BOLD, 11));
		crearManejadorBotonPremium(btnPremium);
		panelbtn2.add(btnPremium);
		
		return panel_Norte;
	}
	
	public void mostrarVentana() {
		frmApp.setLocationRelativeTo(null);
		frmApp.setVisible(true);
	}
	
	private void crearManejadorBotonLogout(JButton btnLogout) {
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMain.videoWeb.cancel();
				LoginView loginView = new LoginView();
				loginView.mostrarVentana();
				frmApp.dispose();
			}
		});
	}
	
	private void crearManejadorBotonPremium(JButton btnPremium) {
		btnPremium.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean premium = ControladorAppVideo.getUnicaInstancia().usuarioPremium();
				
				if (premium) {
					JOptionPane.showMessageDialog(frmApp, "Has dejado de ser un usuario PREMIUM",
							"PREMIUM", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(frmApp, "Ahora eres un usuario PREMIUM",
							"PREMIUM", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		});
	}
	
	private void crearManajadorBotonMisListas (JButton boton) {
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMain.videoWeb.cancel();
				CardLayout cl = (CardLayout) (panelPrincipal.getLayout());
				cl.show(panelPrincipal, "panelMisListas");
				((PanelMisListas) panelMisListas).actualizar();
			}
		});
	}
	
	private void crearManejadorBotonExplorar(JButton boton) {
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMain.videoWeb.cancel();
				((PanelExplorar) panelExplorar).updateChanges();
				CardLayout cl = (CardLayout) (panelPrincipal.getLayout());
				cl.show(panelPrincipal, "panelExplorar");
			}
		});
	}
	
	private void crearManejadorBotonNuevaLista(JButton boton) {
		boton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				AppMain.videoWeb.cancel();
				CardLayout cl = (CardLayout) (panelPrincipal.getLayout());
				cl.show(panelPrincipal, "panelNuevaLista");
			}
		});
	}
	
	private void crearManejadorBotonRecientes(JButton boton) {
		boton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				AppMain.videoWeb.cancel();
				CardLayout cl = (CardLayout) (panelPrincipal.getLayout());
				cl.show(panelPrincipal, "panelRecientes");
				((PanelRecientes) panelRecientes).mostrarRecientes();
			}
		});
	}
	
	private void crearManejadorTop10() {
		mntmMasVistos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ControladorAppVideo.getUnicaInstancia().isUsuarioPremium()) {
					AppMain.videoWeb.cancel();
					panelTop10.mostrarTop10();
					CardLayout cl = (CardLayout) (panelPrincipal.getLayout());
					cl.show(panelPrincipal, "top10");
				}else {
					errorPremium();
				}
			}
		});
	}
	
	private void errorPremium() {
		JOptionPane.showMessageDialog(panelApp,"Tienes que ser PREMIUM para acceder a esta funcionalidad",
				"PREMIUM", JOptionPane.INFORMATION_MESSAGE);
	}
}











