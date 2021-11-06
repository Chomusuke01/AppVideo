package umu.tds.AppVideo.controlador;


import java.util.Date;

import umu.tds.AppVideo.modelo.CatalogoUsuarios;
import umu.tds.AppVideo.modelo.Usuario;
import umu.tds.AppVideo.persistencia.DAOException;
import umu.tds.AppVideo.persistencia.FactoriaDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorUsuarioDAO;
public class ControladorAppVideo {
	
	private static ControladorAppVideo unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private CatalogoUsuarios catalogoUsuarios;
	

	private ControladorAppVideo() {
		inicializarAdaptadores(); 
		inicializarCatalogos();
	}
	
	public static ControladorAppVideo getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorAppVideo();
		return unicaInstancia;
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
	}
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
	
	public void registrarUsuario(String usuario, String nombre,String contraseña, String email, String apellidos, Date fecha_nacimiento) {

		Usuario u = new Usuario(nombre, apellidos, email, usuario, contraseña, fecha_nacimiento);
		adaptadorUsuario.registrarUsuario(u);
		catalogoUsuarios.addUsuario(u);
	}
	
	public boolean exiteUsuario(String usuario) {
		return catalogoUsuarios.getUsuario(usuario) != null;
	}
	
	public boolean loginUsuario (String usuario, String contraseña) {
		
		Usuario u = catalogoUsuarios.getUsuario(usuario);
		if (u != null && u.getContraseña().equals(contraseña))
			return true;
		
		return false;
	}
}
