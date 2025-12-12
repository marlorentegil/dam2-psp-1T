package es.fpsumma.dam2.cursos.service.impl;

import es.fpsumma.dam2.cursos.dto.request.CursoRequest;
import es.fpsumma.dam2.cursos.dto.response.CursoResponse;
import es.fpsumma.dam2.cursos.dto.response.CursoResumenResponse;
import es.fpsumma.dam2.cursos.mapper.CursoMapper;
import es.fpsumma.dam2.cursos.persistence.jpa.entity.CursoEntity;
import es.fpsumma.dam2.cursos.persistence.jpa.repository.AlumnoRepository;
import es.fpsumma.dam2.cursos.persistence.jpa.repository.CursoRepository;
import es.fpsumma.dam2.cursos.service.CursoService;
import es.fpsumma.dam2.cursos.util.exception.CursoNoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final AlumnoRepository alumnoRepository;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository, AlumnoRepository alumnoRepository) {
        this.cursoRepository = cursoRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<CursoResumenResponse> listarCursos(String ordenarPor, String direccion) {
        List<CursoEntity> cursos = this.cursoRepository.findAll(Sort.by(Sort.Direction.fromString(direccion), ordenarPor));
        return cursos.stream().map(CursoMapper::cursoEntityToCursoResumenResponse).toList();
    }

    @Override
    public CursoResponse getCurso(Long id) {
        try{
            CursoEntity cursoEntity = this.cursoRepository.getReferenceById(id);
            Integer alumnosMatriculados = this.alumnoRepository.countByCursoEntityId(id);
            return CursoMapper.cursoEntityToCursoResponse(cursoEntity, alumnosMatriculados);
        }catch(EntityNotFoundException e){
            throw new CursoNoEncontradoException("Curso no encontrado");
        }
    }

    @Override
    public CursoResponse crearCurso(CursoRequest request) {
        CursoEntity curso = CursoMapper.cursoRequestToCursoEntity(request);
        CursoEntity result = this.cursoRepository.save(curso);
        return CursoMapper.cursoEntityToCursoResponse(result, 0);
    }

    @Override
    public CursoResponse actualizarCurso(Long id, CursoRequest request) {
        try{
            CursoEntity cursoEntity = CursoMapper.cursoRequestToCursoEntity(request);
            cursoEntity.setId(id);
            CursoEntity result = this.cursoRepository.save(cursoEntity);
            return CursoMapper.cursoEntityToCursoResponse(result, 0);
        }catch (CursoNoEncontradoException e){
            throw new CursoNoEncontradoException("Curso no encontrado");
        }
    }



    @Override
    public void eliminarCurso(Long id) {
        try{
            this.cursoRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw new CursoNoEncontradoException("Curso no encontrado");
        }
    }


}
