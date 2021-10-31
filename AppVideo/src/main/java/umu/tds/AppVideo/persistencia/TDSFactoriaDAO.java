package umu.tds.AppVideo.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO{

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorEtiquetaDAO getEtiquetaDAO() {
		return AdaptadorEtiquetaTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorListaReproduccionDAO getListaReproduccionDAO() {
		return AdaptadorListaReproduccionTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorVideoDAO getVideoDAO() {
		return AdaptadorVideoTDS.getUnicaInstancia();
	}
}
