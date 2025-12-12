package es.fpsumma.dam2.cursos.mapper;

import es.fpsumma.dam2.cursos.dto.request.CursoRequest;
import es.fpsumma.dam2.cursos.dto.response.CursoResponse;
import es.fpsumma.dam2.cursos.dto.response.CursoResumenResponse;
import es.fpsumma.dam2.cursos.persistence.jpa.entity.CursoEntity;

public class CursoMapper {

    public static CursoResumenResponse cursoEntityToCursoResumenResponse(CursoEntity e) {
        return new CursoResumenResponse(
                e.getId(),
                e.getTitulo(),
                e.getCategoria()
        );
    }

    public static CursoResponse cursoEntityToCursoResponse(CursoEntity e, Integer alumnosMatriculados) {

        return new CursoResponse(
                e.getId(),
                e.getTitulo(),
                e.getCategoria(),
                e.getDuracionEnMinutos(),
                e.getPrecio(),
                alumnosMatriculados
        );
    }

    public static CursoEntity cursoRequestToCursoEntity(CursoRequest request) {
        return new CursoEntity(
                null,
                request.titulo(),
                request.categoria(),
                request.duracionEnMinutos(),
                request.precio()
        );
    }


}
