package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ServicioConsulta {
     List<Consulta> buscarConsultasDePublicacion(Integer publicacionId);

     Boolean hacerPregunta(Consulta consulta);

     Publicacion buscarPublicacionPorId(Integer publicacionId);
}
