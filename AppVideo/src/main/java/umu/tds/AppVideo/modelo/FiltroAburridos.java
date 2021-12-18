package umu.tds.AppVideo.modelo;

public class FiltroAburridos implements FiltroVideo {

	@Override
	public boolean isVideoOK(Video video) {
		return video.getNombreEtiquetas().contains("comedia");
	}

}
