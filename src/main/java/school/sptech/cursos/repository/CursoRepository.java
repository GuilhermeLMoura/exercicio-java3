package school.sptech.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cursos.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso,Integer> {
    boolean existsByNomeIgnoreCase(String nome);
}
