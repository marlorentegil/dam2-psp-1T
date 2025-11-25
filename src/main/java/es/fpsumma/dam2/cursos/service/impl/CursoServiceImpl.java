package es.fpsumma.dam2.cursos.service.impl;

import es.fpsumma.dam2.cursos.dto.response.CursoResponse;
import es.fpsumma.dam2.cursos.dto.response.CursoResumenResponse;
import es.fpsumma.dam2.cursos.persistence.jpa.repository.CursoRepository;
import es.fpsumma.dam2.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<CursoResumenResponse> listarCursos(String ordenarPor, String direccion) {

        return List.of();
    }

    @Override
    public CursoResponse getCurso(Long id) {
        return null;
    }


}
