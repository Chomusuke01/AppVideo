package umu.tds.AppVideo.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO{

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

}
