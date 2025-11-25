package es.fpsumma.dam2.cursos.mapper;

import es.fpsumma.dam2.cursos.dto.response.CursoResponse;
import es.fpsumma.dam2.cursos.dto.response.CursoResumenResponse;
import es.fpsumma.dam2.cursos.persistence.jpa.entity.CursoEntity;

public class CursoMapper {

    public static CursoResumenResponse cursoEntityToCursoResumenResponse(CursoEntity e) {
        return new CursoResumenResponse(e.getId(), e.getTitulo(), e.getCategoria());
    }

    public static CursoResponse cursoEntityToCursoResponse(CursoEntity e) {
        return null;
    }
}
