package es.fpsumma.dam2.cursos.dto.request;


import jakarta.validation.constraints.Size;

public record CursoRequest(
        @Size(min = 1, max = 255)
        String titulo,

        @Size(min = 1, max = 100)
        String categoria,

        Integer duracionEnMinutos,

        @Size(min = 2, max = 10)
        Double precio
) { }
