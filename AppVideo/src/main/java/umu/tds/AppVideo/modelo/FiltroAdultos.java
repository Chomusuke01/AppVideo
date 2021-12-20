package umu.tds.AppVideo.modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class FiltroAdultos implements FiltroVideo {

	@Override
	public boolean isVideoOK(Video video, Usuario u) {
		
		LocalDate ahora = LocalDate.now();
		LocalDate fechaNacimiento = u.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period edad = Period.between(fechaNacimiento,ahora);
		
		return (video.getNombreEtiquetas().contains("adultos") && edad.getYears() >= 18) || (!video.getNombreEtiquetas().contains("adultos"));
	}

}
