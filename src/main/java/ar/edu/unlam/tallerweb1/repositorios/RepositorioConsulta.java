package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;

import java.util.List;

public interface RepositorioConsulta {

    List<Consulta> buscarConsultasDePublicacion(Integer idPublicacion);

    List<Consulta> buscarConsultasDeUsuario(long idUsuario);

    void guardarConsulta(Consulta consulta);

    Publicacion buscarPublicacionPorId(Integer publicacionId);
}
