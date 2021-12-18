package umu.tds.AppVideo.modelo;

public class Filtro {
	
	FiltroVideo filtro;
	
	public Filtro (FiltroVideo filtro) {
		this.filtro = filtro;
	}
	
	
	public void setFiltro(FiltroVideo filtro) {
		this.filtro = filtro;
	}
	
	public boolean isVideoOK(Video video) {
		return filtro.isVideoOK(video);
	}
	
}
