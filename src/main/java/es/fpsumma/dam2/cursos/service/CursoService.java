package es.fpsumma.dam2.cursos.service;

import es.fpsumma.dam2.cursos.dto.response.CursoResponse;
import es.fpsumma.dam2.cursos.dto.response.CursoResumenResponse;

import java.util.List;


public interface CursoService {

    public List<CursoResumenResponse> listarCursos(String ordenarPor, String direccion);

    CursoResponse getCurso(Long id);


}
