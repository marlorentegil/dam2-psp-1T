package es.fpsumma.dam2.cursos.web.controller;

import es.fpsumma.dam2.cursos.dto.request.CursoRequest;
import es.fpsumma.dam2.cursos.dto.response.CursoResponse;
import es.fpsumma.dam2.cursos.dto.response.CursoResumenResponse;
import es.fpsumma.dam2.cursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public List<CursoResumenResponse> getCursos(@RequestParam(defaultValue = "id") String ordenarPor,
                                                String direccion) {
        return null;
    }

    public CursoResponse getCurso(@PathVariable Long id) {
        return null;
    }

    public CursoResponse crearCurso(@Valid @RequestBody CursoRequest request) {
        return null;
    }

}
