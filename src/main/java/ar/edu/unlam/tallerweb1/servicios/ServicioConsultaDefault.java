package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioConsultaDefault implements ServicioConsulta{
    @Override
    public List<Consulta> buscarConsultasDePublicacion(Integer publicacionId) {
        return null;
    }

    @Override
    public void hacerPregunta(Consulta consulta) {

    }

    @Override
    public Usuario buscarUsuarioPorId(Integer usuarioId) {
        return null;
    }

    @Override
    public Publicacion buscarPublicacionPorId(Integer publicacionId) {
        return null;
    }
}
