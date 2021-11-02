package umu.tds.AppVideo.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import umu.tds.AppVideo.controlador.ControladorAppVideo;

public class LoginView {

	private JFrame frmLogin;
	private JTextField textUsuario;
	private JPasswordField textPassword;
	private JButton btnRegistro;
	private JButton btnLogin;
	
	
	public LoginView() {
		initialize();
	}
	
	public void initialize() {
		
		frmLogin = new JFrame();
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmLogin.setBounds(100, 100, 1000, 650);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.GRAY);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		frmLogin.setContentPane(panelPrincipal);
		
		JPanel panelNorte = new JPanel();
		panelPrincipal.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel iconoVideo = new JLabel("");
		iconoVideo.setIcon(new ImageIcon (getClass().getResource("/iconoVideo3.png")));
		panelNorte.add(iconoVideo);
		
		JPanel panelSur = new JPanel();
		panelPrincipal.add(panelSur, BorderLayout.CENTER);
		panelSur.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelSur.add(panel, BorderLayout.NORTH);
		
		JLabel iconoLogin = new JLabel("");
		iconoLogin.setIcon(new ImageIcon (getClass().getResource("/login_icon.png")));
		panel.add(iconoLogin);
		
		textUsuario = new JTextField();
		panel.add(textUsuario);
		textUsuario.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		panelSur.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel iconoContraseña = new JLabel("");
		iconoContraseña.setIcon(new ImageIcon (getClass().getResource("/keyIcon2.png")));
		panel_2.add(iconoContraseña);
		
		textPassword = new JPasswordField();
		textPassword.setColumns(20);
		panel_2.add(textPassword);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 120, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 2;
		panel_3.add(btnLogin, gbc_btnLogin);
		crearManejadorBotonLogin(btnLogin);
		
		btnRegistro = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistro.gridx = 3;
		gbc_btnRegistro.gridy = 2;
		panel_3.add(btnRegistro, gbc_btnRegistro);
		crearManejadorBotonRegistro(btnRegistro);
		
	}
	
	private void crearManejadorBotonRegistro(JButton btnRegistro) {
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroView registroView = new RegistroView();
				registroView.mostrarVentana();
				frmLogin.dispose();
			}
		});
	}

	private void crearManejadorBotonLogin(JButton btnLogin) {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean login = ControladorAppVideo.getUnicaInstancia().loginUsuario(textUsuario.getText(), new String(textPassword.getPassword()));
				
				if (login) {
					AppView appView = new AppView();
					appView.mostrarVentana();
					frmLogin.dispose();
				}
				else {
					JOptionPane.showMessageDialog(frmLogin, "Nombre de usuario o contraseña no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public void mostrarVentana() {
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}
}
