package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ServicioConsulta {
     List<Consulta> buscarConsultasDePublicacion(Integer publicacionId);

     void hacerPregunta(Consulta consulta);

     Usuario buscarUsuarioPorId(Integer usuarioId);

     Publicacion buscarPublicacionPorId(Integer publicacionId);
}
