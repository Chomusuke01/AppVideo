package umu.tds.AppVideo.modelo;

public class FiltroMisListas implements FiltroVideo {

	@Override
	public boolean isVideoOK(Video video, Usuario u) {
		
		return !u.getVideosFromListas().contains(video);
	}
}
