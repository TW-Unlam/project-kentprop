package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service @Transactional
public class ServicioPublicacionesDefault implements ServicioPublicaciones {

    private final RepositorioPublicaciones repositorioPublicaciones;
    private final RepositorioUsuario repositorioUsuario;
    @Autowired
    public ServicioPublicacionesDefault(RepositorioPublicaciones repositorioPublicaciones, RepositorioUsuario repositorioUsuario){
        this.repositorioPublicaciones = repositorioPublicaciones;
        this.repositorioUsuario = repositorioUsuario;
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
    public void indicarPublicacionFavorita(Integer idPublicacion, Integer usuarioId) {

        Favoritos existente =repositorioPublicaciones.buscarFavoritoExistente(idPublicacion,usuarioId);
        if(existente !=null){
            repositorioPublicaciones.eliminarfavorito(existente);
            return;
        }

        Favoritos publicacionFavorita = asignacionDeDatosDelFavorito(idPublicacion, usuarioId);
        repositorioPublicaciones.indicarFavorito(publicacionFavorita);
    }

    @Override
    public List<Publicacion> buscarPublicacionFavoritas(Integer usuarioId) {
        List <Favoritos> listaFavoritos=repositorioPublicaciones.BuscarFavoritosDelUsuario(usuarioId);
        List<Publicacion> resultado = new LinkedList<Publicacion>();

        for (Favoritos favoritoUni : listaFavoritos) {
          resultado.add(favoritoUni.getPublicacion());
        }

        return resultado;
    }

    private Favoritos asignacionDeDatosDelFavorito(Integer idPublicacion, Integer usuarioId) {
        Favoritos publicacionFavorita= new Favoritos();
        Publicacion publicacion=repositorioPublicaciones.buscarPublicacionId(idPublicacion);
        Usuario usuario=repositorioUsuario.obterneUsuario(usuarioId);
        publicacionFavorita.setPublicacion(publicacion);
        publicacionFavorita.setEstado(true);
        publicacionFavorita.setUsuario(usuario);
        return publicacionFavorita;
    }

    @Override
    public boolean obtenerEstadoFavorito(Integer publicacionId, Integer usuarioId) {
        Favoritos favorito = repositorioPublicaciones.buscarFavoritoExistente(publicacionId, usuarioId);

        return favorito == null ? false: favorito.getEstado();
    }
}
