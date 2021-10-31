package umu.tds.AppVideo.persistencia;

import java.util.List;

import umu.tds.AppVideo.modelo.Video;

public interface IAdaptadorVideoDAO {
	public void addVideo(Video video);
	public List<Video> recuperarTodosVideos();
	public Video recuperarVideo(int codigo);
	public Video findUsuario (Video video);
	public boolean removeVideo(Video video);
}