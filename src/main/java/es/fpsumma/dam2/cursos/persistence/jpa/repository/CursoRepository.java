package es.fpsumma.dam2.cursos.persistence.jpa.repository;

import es.fpsumma.dam2.cursos.persistence.jpa.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

}
