package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service @Transactional
public class ServicioConsultaDefault implements ServicioConsulta{
    private final RepositorioConsulta repositorioConsulta;

    @Autowired
    public ServicioConsultaDefault(RepositorioConsulta repositorioConsulta) {
        this.repositorioConsulta = repositorioConsulta;
    }

    @Override
    public List<Consulta> buscarConsultasDePublicacion(Integer publicacionId) {
        List<Consulta> consultas = repositorioConsulta.buscarConsultasDePublicacion(publicacionId);
        return consultas;
    }

    @Override
    public Boolean hacerPregunta(Consulta consulta) {
        repositorioConsulta.guardarConsulta(consulta);
        return true;
    }

    @Override
    public Publicacion buscarPublicacionPorId(Integer publicacionId) {
        Publicacion publicacion = repositorioConsulta.buscarPublicacionPorId(publicacionId);
        return publicacion;
    }
}
