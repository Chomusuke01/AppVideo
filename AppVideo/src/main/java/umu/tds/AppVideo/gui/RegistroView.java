package umu.tds.AppVideo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JDateChooser;


public class RegistroView {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroView frame = new RegistroView();
					frame.mostrarVentana();;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JFrame frmRegistro;
	private JPasswordField txtContraseña;
	private JPasswordField txtRepetir;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblFecha;
	private JLabel lblEmail;
	private JLabel lblUsuario;
	private JLabel lblContraseña;
	private JLabel lblRepetir;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JDateChooser fechaNacimiento;
	private JLabel lblNombreError;
	private JLabel lblUsuarioError;
	private JLabel lblContraseñaError;
	private JButton btnRegistrar;
	private JButton btnCancelar;

	
	public RegistroView () {
		initialize();
		ocultarErrores();

	}
	
	public void initialize() {
		frmRegistro = new JFrame();
		frmRegistro.setMaximumSize(new Dimension(970,620));
		frmRegistro.setMinimumSize(new Dimension(970,620));
		frmRegistro.setPreferredSize(new Dimension(970,620));
		frmRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistro.getContentPane().add(añadirPanelNorte(), BorderLayout.NORTH);
		frmRegistro.getContentPane().add(añadirPanelCentro(), BorderLayout.CENTER);
		
		frmRegistro.revalidate();
		frmRegistro.pack();
	}

	private JPanel añadirPanelNorte() {
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(Color.LIGHT_GRAY);
		panelNorte.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.rowWeights = new double[]{0.0};
		gbl_panelNorte.rowHeights = new int[]{0};
		panelNorte.setLayout(gbl_panelNorte);
		
		JLabel logoRegistro = new JLabel("");
		logoRegistro.setIcon(new ImageIcon(getClass().getResource("/sign_up.png")));
		panelNorte.add(logoRegistro,new GridBagConstraints());
		
		return panelNorte;
	}
	
	private JPanel añadirPanelCentro() {
		JPanel panelCentro = new JPanel();
		panelCentro.setBackground(new Color(240, 240, 240));
		panelCentro.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{5, 0, 0, 0, 0, 0, 0, 5, 0};
		gbl_panelCentro.rowHeights = new int[]{0, 10, 0, 10, 10, 10, 30, 10, 0, 10, 0, 75, 0, 40, 0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);
		
		lblNombre = new JLabel("*Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 1;
		panelCentro.add(lblNombre, gbc_lblNombre);
	
		lblNombreError = new JLabel("");
		GridBagConstraints gbc_lblNombreError = new GridBagConstraints();
		gbc_lblNombreError.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombreError.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreError.gridx = 3;
		gbc_lblNombreError.gridy = 2;
		lblNombreError.setForeground(Color.RED);
		panelCentro.add(lblNombreError, gbc_lblNombreError);
	
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		panelCentro.add(txtNombre, gbc_textField);
				
		lblApellidos = new JLabel("Apellidos:");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 2;
		gbc_lblApellidos.gridy = 3;
		panelCentro.add(lblApellidos, gbc_lblApellidos);
		
		txtApellidos = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 3;
		panelCentro.add(txtApellidos, gbc_textField_1);
		txtApellidos.setColumns(10);
		
		lblFecha = new JLabel("*Fecha nacimiento:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 2;
		gbc_lblFecha.gridy = 4;
		panelCentro.add(lblFecha, gbc_lblFecha);
		
		fechaNacimiento = new JDateChooser();
	
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 3;
		gbc_dateChooser.gridy = 4;
		//fechaNacimiento.setDateFormatString("dd/mm/aa");
		panelCentro.add(fechaNacimiento, gbc_dateChooser);
		
		lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 5;
		panelCentro.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 5;
		panelCentro.add(txtEmail, gbc_textField_2);
		txtEmail.setColumns(10);
		
		lblUsuario = new JLabel("*Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 2;
		gbc_lblUsuario.gridy = 7;
		panelCentro.add(lblUsuario, gbc_lblUsuario);
		
		txtUsuario = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 7;
		panelCentro.add(txtUsuario, gbc_textField_3);
		txtUsuario.setColumns(10);
		
		lblUsuarioError = new JLabel(" ");
		GridBagConstraints gbc_lblUsuarioError = new GridBagConstraints();
		gbc_lblUsuarioError.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUsuarioError.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuarioError.gridx = 3;
		gbc_lblUsuarioError.gridy = 8;
		panelCentro.add(lblUsuarioError, gbc_lblUsuarioError);
		lblUsuarioError.setForeground(Color.RED);
		panelCentro.add(lblUsuarioError, gbc_lblUsuarioError);
		
		
		lblContraseña = new JLabel("*Contraseña:");
		GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
		gbc_lblContraseña.anchor = GridBagConstraints.EAST;
		gbc_lblContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblContraseña.gridx = 2;
		gbc_lblContraseña.gridy = 9;
		panelCentro.add(lblContraseña, gbc_lblContraseña);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setColumns(10);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 9;
		panelCentro.add(txtContraseña, gbc_passwordField);
		
		lblRepetir = new JLabel("*Repetir:");
		GridBagConstraints gbc_lblRepetir = new GridBagConstraints();
		gbc_lblRepetir.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRepetir.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepetir.gridx = 4;
		gbc_lblRepetir.gridy = 9;
		panelCentro.add(lblRepetir, gbc_lblRepetir);
		
		txtRepetir = new JPasswordField();
		txtRepetir.setColumns(10);
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.gridx = 5;
		gbc_passwordField_1.gridy = 9;
		panelCentro.add(txtRepetir, gbc_passwordField_1);
		
		lblContraseñaError = new JLabel("");
		GridBagConstraints gbc_lblContraseñaError = new GridBagConstraints();
		gbc_lblContraseñaError.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblContraseñaError.insets = new Insets(0, 0, 5, 5);
		gbc_lblContraseñaError.gridx = 3;
		gbc_lblContraseñaError.gridy = 10;
		lblContraseñaError.setForeground(Color.RED);
		panelCentro.add(lblContraseñaError, gbc_lblContraseñaError);
		
		btnRegistrar = new JButton("");
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrar.gridx = 3;
		gbc_btnRegistrar.gridy = 12;
		btnRegistrar.setIcon(new ImageIcon (getClass().getResource("/Register-PNGs.png")));
		panelCentro.add(btnRegistrar, gbc_btnRegistrar);
		crearManejadorBotonRegistar(btnRegistrar);
		
		btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(getClass().getResource("/salida2.png")));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 5;
		gbc_btnCancelar.gridy = 12;
		panelCentro.add(btnCancelar, gbc_btnCancelar);
		crearManejadorBotonCancelar(btnCancelar);
		
		JLabel lblCampos = new JLabel("*Campos obligatorios");
		GridBagConstraints gbc_lblCampos = new GridBagConstraints();
		gbc_lblCampos.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCampos.insets = new Insets(0, 0, 5, 5);
		gbc_lblCampos.gridx = 3;
		gbc_lblCampos.gridy = 14;
		panelCentro.add(lblCampos, gbc_lblCampos);
		
		return panelCentro;
	}
	
	public void mostrarVentana() {
		frmRegistro.setLocationRelativeTo(null);
		frmRegistro.setVisible(true);
	}
	
	private void ocultarErrores() {
		lblNombreError.setVisible(false);
		lblUsuarioError.setVisible(false);
		lblContraseñaError.setVisible(false);
		
		Border border = new JTextField().getBorder();
		txtNombre.setBorder(border);
		txtApellidos.setBorder(border);
		txtEmail.setBorder(border);
		txtUsuario.setBorder(border);
		txtContraseña.setBorder(border);
		txtRepetir.setBorder(border);
		txtContraseña.setBorder(border);
		txtRepetir.setBorder(border);
		txtUsuario.setBorder(border);
		
		lblNombre.setForeground(Color.BLACK);
		lblUsuario.setForeground(Color.BLACK);
		lblContraseña.setForeground(Color.BLACK);
		lblRepetir.setForeground(Color.BLACK);
	}
	
	private boolean checkFields() {
		boolean salida = true;
		/* borrar todos los errores en pantalla */
		ocultarErrores();
		
		if (txtNombre.getText().trim().isEmpty()) {
			lblNombreError.setText("* El nombre es obligatorio");
			lblNombreError.setVisible(true);
			lblNombre.setForeground(Color.RED);
			txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		if (txtUsuario.getText().trim().isEmpty()) {
			lblUsuarioError.setText("* El usuario es obligatorio");
			lblUsuarioError.setVisible(true);
			lblUsuario.setForeground(Color.RED);
			txtUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		String password = new String(txtContraseña.getPassword());
		String password2 = new String(txtRepetir.getPassword());
		if (password.isEmpty()) {
			lblContraseñaError.setText("* El password no puede estar vacio");
			lblContraseñaError.setVisible(true);
			lblContraseña.setForeground(Color.RED);
			salida = false;
		} 
		if (password2.isEmpty()) {
			lblContraseñaError.setText("* La contraseña no puede estar vacia");
			lblContraseñaError.setVisible(true);
			lblRepetir.setForeground(Color.RED);
			txtRepetir.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		} 
		if (!password.equals(password2)) {
			lblContraseñaError.setText("* Las contraseñas no coinciden");
			lblContraseñaError.setVisible(true);
			lblContraseña.setForeground(Color.RED);
			lblRepetir.setForeground(Color.RED);
			txtContraseña.setBorder(BorderFactory.createLineBorder(Color.RED));
			txtRepetir.setBorder(BorderFactory.createLineBorder(Color.RED));
			salida = false;
		}
		
		frmRegistro.revalidate();
		frmRegistro.pack();
		
		return salida;
	}
	
	
	private void crearManejadorBotonRegistar(JButton btnRegistrar) {
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean OK = false;
				OK = checkFields();
				if (OK) {
					
				}
			}
		});
	}
	
	private void crearManejadorBotonCancelar(JButton btnCancelar) {
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView loginView = new LoginView();
				loginView.mostrarVentana();
				frmRegistro.dispose();
			}
		});
	}
}
