package es.fpsumma.dam2.cursos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CursoResponse(
        Long id,
        String titulo,
        String categoria,
        @JsonProperty("duracion_en_minutos")
        Integer duracionEnMinutos,
        Double precio,
        Integer alumnosMatriculados
) { }
