package umu.tds.AppVideo.persistencia;

import java.util.List;

import umu.tds.AppVideo.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {
	
	public void registrarUsuario(Usuario u);
	public List<Usuario> recuperarTodosUsuarios();
	public Usuario recuperarUsuario(int codigo);
}
