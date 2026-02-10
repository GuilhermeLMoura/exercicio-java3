package school.sptech.cursos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cursos.entity.Curso;
import school.sptech.cursos.service.CursoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        List<Curso> cursos = cursoService.findAll();
        if(cursos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Integer id) {
        Curso curso = cursoService.findById(id);
        if(curso == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody @Valid Curso curso) {
        Curso novoCurso = cursoService.save(curso);

        if(novoCurso == null){
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.created(null).body(novoCurso);
    }
}
