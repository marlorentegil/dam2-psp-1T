package es.fpsumma.dam2.cursos.dto.request;


import jakarta.validation.constraints.Size;

public record CursoRequest(
        @Size(min = 1, max = 255)
        String titulo,

        String categoria,

        Integer duracionEnMinutos,
        Double precio) {
}
