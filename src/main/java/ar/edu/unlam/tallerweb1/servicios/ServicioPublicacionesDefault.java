package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class ServicioPublicacionesDefault implements ServicioPublicaciones {

    private final RepositorioPublicaciones repositorioPublicaciones;
    @Autowired
    public ServicioPublicacionesDefault(RepositorioPublicaciones repositorioPublicaciones){
        this.repositorioPublicaciones = repositorioPublicaciones;
    }

    @Override
    public List<Publicacion> buscarPublicacion(Accion accion, TipoPropiedad tipo, String descripcion){
        List<Publicacion> lista = repositorioPublicaciones.buscarPublicaciones(accion, tipo, descripcion);
        return lista;
    }

    @Override
    public Publicacion verDetallePublicacion(Integer id) {
        Publicacion resultado = repositorioPublicaciones.buscarDetallePublicacion(id);
        return resultado;
    }

    @Override
    public List<Imagen> traerImagenesPorId(Integer publicacion_id) {
        List<Imagen> lista = repositorioPublicaciones.buscarImagenesDeLaPublicacion(publicacion_id);
        return lista;
    }

    @Override
    public List<Publicacion> obtenerPublicacionesDestacadas() {
        List<Publicacion> destacadas = repositorioPublicaciones.buscarPublicacionesDestacadas();
        return destacadas;
    }

    @Override
    public void indicarPublicacionFavorita(Integer id, Integer usuarioId) {
        Favoritos publicacionFavorita= new Favoritos();
        Publicacion existente=repositorioPublicaciones.buscarPublicacionId(id);

        //publicacionFavorita.setPublicacion();
        //repositorioPublicaciones.indicarFavorito();
        //repositorioPublicaciones.indicarFavorito();
    }
}
