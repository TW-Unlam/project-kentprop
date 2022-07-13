package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Accion;
import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.TipoPropiedad;

import java.util.List;

public interface ServicioPublicaciones {
    List<Publicacion> buscarPublicacion(Accion accion, TipoPropiedad tipo, String descripcion);

    Publicacion verDetallePublicacion(Integer id);

    List<Imagen> traerImagenesPorId(Integer publicacion_id);

    List<Publicacion> obtenerPublicacionesDestacadas();

    void indicarPublicacionFavorita(Integer id, Integer usuarioId);
}

// Vamos a usar test doubles con un mock, para hacer pruebas de un controlador que dependen de un servicio
// esto se hace para no mezclar en un mismo test el controlador con el servicio.
// Los test double se hacen cuando hay que testear un objeto que DEPENDE del otro.
// Ej: un controlador que depende de un servicio, un servicio de otro servicio, un servicio de un repositorio, etc.
// Esto se hace para probar solo un objeto y no dos en simultaneo.

/* Los metodos que usamos por el mock lo que hacen es que almacena un resultado que cuando son llamados adentro
del objeto del test, devuelven un resultado esperando.
 */