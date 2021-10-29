package umu.tds.AppVideo.modelo;

import java.util.Map;
import java.util.stream.Collectors;

import umu.tds.AppVideo.persistencia.DAOException;
import umu.tds.AppVideo.persistencia.FactoriaDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {
	
	private Map<String,Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	
	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorUsuario = dao.getUsuarioDAO();
  			usuarios = adaptadorUsuario.recuperarTodosUsuarios().stream().collect(Collectors.toMap(u -> u.getUsuario(), u -> u)); // Posible explosion del codigo.
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	
	public static CatalogoUsuarios getUnicaInstancia(){
		return unicaInstancia;
	}
	
	public void addUsuario(Usuario u) {
		usuarios.put(u.getUsuario(), u);
	}
	
	public Usuario getUsuario(String usuario) {
		return usuarios.get(usuario);
	}
	
	
}