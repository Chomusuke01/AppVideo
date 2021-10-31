package umu.tds.AppVideo.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.modelo.Etiqueta;

public class AdaptadorEtiquetaTDS implements IAdaptadorEtiquetaDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorEtiquetaTDS unicaInstancia;

	public static AdaptadorEtiquetaTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorEtiquetaTDS();
		
		return unicaInstancia;
	}
	
	
	private AdaptadorEtiquetaTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	@Override
	public void registrarEtiqueta(Etiqueta etiqueta) {
		Entidad eLabel = null;
		
		try {
			eLabel = servPersistencia.recuperarEntidad(etiqueta.getCodigo());
		} catch (NullPointerException e) {}
		if (eLabel != null) return;
		
		
		eLabel = new Entidad();
		eLabel.setNombre("etiqueta");
		
		eLabel.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre",etiqueta.getNombre()))));
		
		eLabel = servPersistencia.registrarEntidad(eLabel);
		
		etiqueta.setCodigo(eLabel.getId());
	}

	@Override
	public void borrarEtiqueta(Etiqueta etiqueta) {
		
		Entidad eLabel = servPersistencia.recuperarEntidad(etiqueta.getCodigo());
		servPersistencia.borrarEntidad(eLabel);

	}

	@Override
	public Etiqueta recuperarEtiqueta(int codigo) {
		Entidad eLabel;
		String nombre;
		
		eLabel = servPersistencia.recuperarEntidad(codigo);
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eLabel, "nombre");
		
		Etiqueta etiqueta = new Etiqueta(nombre);
		etiqueta.setCodigo(codigo);
		
		return etiqueta;
	}

	@Override
	public List<Etiqueta> recuperarTodasEtiquetas() {
		
		return servPersistencia.recuperarEntidades("etiqueta").stream()
				.map(e -> recuperarEtiqueta(e.getId())).collect(Collectors.toList());  // Es posible que explote, pero queda chulo.
	}	
}
