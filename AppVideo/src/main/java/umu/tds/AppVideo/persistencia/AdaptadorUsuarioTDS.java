package umu.tds.AppVideo.persistencia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.modelo.Usuario;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	@Override
	public void registrarUsuario(Usuario u) {
		Entidad eCliente = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eCliente = servPersistencia.recuperarEntidad(u.getCodigo());
		} catch (NullPointerException e) {}
		if (eCliente != null) return;

		// registrar primero los atributos que son objetos
		//TODO Hay que insertar el código para registrar la lista de videos recientes y las listas de reproduccion del usuario.

		// crear entidad Cliente
		// TODO falta ver el caso de que sea premium como lo metemos.
		eCliente = new Entidad();
		eCliente.setNombre("usuario");
		eCliente.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", u.getNombre()), new Propiedad("apellidos", u.getApellidos()), 
						new Propiedad ("fecha_nacimiento",u.getFechaNacimiento().toString()), new Propiedad ("usuario",u.getUsuario()), new Propiedad("contraseña",u.getContraseña()), new Propiedad("email",u.getEmail()))));
		
		// registrar entidad cliente
		eCliente = servPersistencia.registrarEntidad(eCliente);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		u.setCodigo(eCliente.getId());
		
	}

	@Override
	// TODO a lo mejor esto no hace falta xD
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario recuperarUsuario(int codigo) {
		
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
		return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		
		Entidad eUsuario;
		//TODO hay que meter tambien las lista de reproduccion y los recientes.
		String usuario;
		String nombre;
		String apellidos;
		String fecha_nacimiento;
		String contraseña;
		String email;
		
		
		// recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(codigo);
		
		// recuperar propiedades que no son objetos
		usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuario");
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		contraseña = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contraseña");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		fecha_nacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fecha_nacimiento");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");
		Usuario u = null;
		try {
			u = new Usuario(nombre, apellidos, email, usuario, contraseña, formatoFecha.parse(fecha_nacimiento));
			u.setCodigo(codigo);
			PoolDAO.getUnicaInstancia().addObjeto(codigo, u);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return u;
	}

}
