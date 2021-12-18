package umu.tds.AppVideo.modelo;

public class NoFiltro implements FiltroVideo {

	@Override
	public boolean isVideoOK(Video video) {
		return true;
	}

}
